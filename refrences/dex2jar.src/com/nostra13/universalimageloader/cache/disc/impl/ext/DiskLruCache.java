package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramInt)
      throws IOException
    {
    }
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.this.journalWriter != null)
        {
          DiskLruCache.this.trimToSize();
          DiskLruCache.this.trimToFileCount();
          if (DiskLruCache.this.journalRebuildRequired())
          {
            DiskLruCache.this.rebuildJournal();
            DiskLruCache.access$502(DiskLruCache.this, 0);
          }
        }
      }
      return null;
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private int fileCount = 0;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private int maxFileCount;
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;

  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
    this.maxFileCount = paramInt3;
  }

  private void checkNotClosed()
  {
    if (this.journalWriter == null)
      throw new IllegalStateException("cache is closed");
  }

  /** @deprecated */
  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    monitorenter;
    Entry localEntry;
    try
    {
      localEntry = paramEditor.entry;
      if (localEntry.currentEditor != paramEditor)
        throw new IllegalStateException();
    }
    finally
    {
      monitorexit;
    }
    if ((paramBoolean) && (!localEntry.readable))
      for (int j = 0; j < this.valueCount; j++)
      {
        if (paramEditor.written[j] == 0)
        {
          paramEditor.abort();
          throw new IllegalStateException("Newly created entry didn't create value for index " + j);
        }
        if (localEntry.getDirtyFile(j).exists())
          continue;
        paramEditor.abort();
        monitorexit;
        return;
      }
    for (int i = 0; ; i++)
      if (i < this.valueCount)
      {
        File localFile1 = localEntry.getDirtyFile(i);
        if (paramBoolean)
        {
          if (!localFile1.exists())
            continue;
          File localFile2 = localEntry.getCleanFile(i);
          localFile1.renameTo(localFile2);
          long l2 = localEntry.lengths[i];
          long l3 = localFile2.length();
          localEntry.lengths[i] = l3;
          this.size = (l3 + (this.size - l2));
          this.fileCount = (1 + this.fileCount);
        }
        else
        {
          deleteIfExists(localFile1);
        }
      }
      else
      {
        this.redundantOpCount = (1 + this.redundantOpCount);
        Entry.access$802(localEntry, null);
        if ((paramBoolean | localEntry.readable))
        {
          Entry.access$702(localEntry, true);
          this.journalWriter.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
          if (paramBoolean)
          {
            long l1 = this.nextSequenceNumber;
            this.nextSequenceNumber = (1L + l1);
            Entry.access$1302(localEntry, l1);
          }
        }
        while (true)
        {
          this.journalWriter.flush();
          if ((this.size <= this.maxSize) && (this.fileCount <= this.maxFileCount) && (!journalRebuildRequired()))
            break;
          this.executorService.submit(this.cleanupCallable);
          break;
          this.lruEntries.remove(localEntry.key);
          this.journalWriter.write("REMOVE " + localEntry.key + '\n');
        }
      }
  }

  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete()))
      throw new IOException();
  }

  /** @deprecated */
  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    Editor localEditor1 = null;
    monitorenter;
    while (true)
    {
      Entry localEntry;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (paramLong == -1L)
          continue;
        if (localEntry == null)
          continue;
        long l = localEntry.sequenceNumber;
        if (l != paramLong)
          return localEditor1;
        if (localEntry == null)
        {
          localEntry = new Entry(paramString, null);
          this.lruEntries.put(paramString, localEntry);
          localEditor1 = new Editor(localEntry, null);
          Entry.access$802(localEntry, localEditor1);
          this.journalWriter.write("DIRTY " + paramString + '\n');
          this.journalWriter.flush();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Editor localEditor2 = localEntry.currentEditor;
      if (localEditor2 == null)
        continue;
    }
  }

  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    return Util.readFully(new InputStreamReader(paramInputStream, Util.UTF_8));
  }

  private boolean journalRebuildRequired()
  {
    if ((this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong, int paramInt3)
    throws IOException
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt3 <= 0)
      throw new IllegalArgumentException("maxFileCount <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    File localFile1 = new File(paramFile, "journal.bkp");
    File localFile2;
    DiskLruCache localDiskLruCache1;
    if (localFile1.exists())
    {
      localFile2 = new File(paramFile, "journal");
      if (localFile2.exists())
        localFile1.delete();
    }
    else
    {
      localDiskLruCache1 = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong, paramInt3);
      if (!localDiskLruCache1.journalFile.exists())
        break label236;
    }
    while (true)
    {
      try
      {
        localDiskLruCache1.readJournal();
        localDiskLruCache1.processJournal();
        localDiskLruCache1.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localDiskLruCache1.journalFile, true), Util.US_ASCII));
        localObject = localDiskLruCache1;
        return localObject;
        renameTo(localFile1, localFile2, false);
      }
      catch (IOException localIOException)
      {
        System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        localDiskLruCache1.delete();
      }
      label236: paramFile.mkdirs();
      DiskLruCache localDiskLruCache2 = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong, paramInt3);
      localDiskLruCache2.rebuildJournal();
      Object localObject = localDiskLruCache2;
    }
  }

  private void processJournal()
    throws IOException
  {
    deleteIfExists(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      if (localEntry.currentEditor == null)
      {
        for (int j = 0; j < this.valueCount; j++)
        {
          this.size += localEntry.lengths[j];
          this.fileCount = (1 + this.fileCount);
        }
        continue;
      }
      Entry.access$802(localEntry, null);
      for (int i = 0; i < this.valueCount; i++)
      {
        deleteIfExists(localEntry.getCleanFile(i));
        deleteIfExists(localEntry.getDirtyFile(i));
      }
      localIterator.remove();
    }
  }

  private void readJournal()
    throws IOException
  {
    StrictLineReader localStrictLineReader = new StrictLineReader(new FileInputStream(this.journalFile), Util.US_ASCII);
    try
    {
      String str1 = localStrictLineReader.readLine();
      String str2 = localStrictLineReader.readLine();
      String str3 = localStrictLineReader.readLine();
      String str4 = localStrictLineReader.readLine();
      String str5 = localStrictLineReader.readLine();
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(this.appVersion).equals(str3)) || (!Integer.toString(this.valueCount).equals(str4)) || (!"".equals(str5)))
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
    }
    finally
    {
      Util.closeQuietly(localStrictLineReader);
    }
    int i = 0;
    try
    {
      while (true)
      {
        readJournalLine(localStrictLineReader.readLine());
        i++;
      }
    }
    catch (EOFException localEOFException)
    {
      this.redundantOpCount = (i - this.lruEntries.size());
      Util.closeQuietly(localStrictLineReader);
    }
  }

  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
      throw new IOException("unexpected journal line: " + paramString);
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    String str;
    if (k == -1)
    {
      str = paramString.substring(j);
      if ((i != "REMOVE".length()) || (!paramString.startsWith("REMOVE")))
        break label106;
      this.lruEntries.remove(str);
    }
    label106: 
    do
      while (true)
      {
        return;
        str = paramString.substring(j, k);
        Entry localEntry = (Entry)this.lruEntries.get(str);
        if (localEntry == null)
        {
          localEntry = new Entry(str, null);
          this.lruEntries.put(str, localEntry);
        }
        if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
        {
          String[] arrayOfString = paramString.substring(k + 1).split(" ");
          Entry.access$702(localEntry, true);
          Entry.access$802(localEntry, null);
          localEntry.setLengths(arrayOfString);
          continue;
        }
        if ((k != -1) || (i != "DIRTY".length()) || (!paramString.startsWith("DIRTY")))
          break;
        Entry.access$802(localEntry, new Editor(localEntry, null));
      }
    while ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")));
    throw new IOException("unexpected journal line: " + paramString);
  }

  /** @deprecated */
  private void rebuildJournal()
    throws IOException
  {
    monitorenter;
    BufferedWriter localBufferedWriter;
    while (true)
    {
      Entry localEntry;
      try
      {
        if (this.journalWriter == null)
          continue;
        this.journalWriter.close();
        localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.appVersion));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.valueCount));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          Iterator localIterator = this.lruEntries.values().iterator();
          if (!localIterator.hasNext())
            break;
          localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor != null)
          {
            localBufferedWriter.write("DIRTY " + localEntry.key + '\n');
            continue;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
      }
      finally
      {
        monitorexit;
      }
      localBufferedWriter.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
    }
    localBufferedWriter.close();
    if (this.journalFile.exists())
      renameTo(this.journalFile, this.journalFileBackup, true);
    renameTo(this.journalFileTmp, this.journalFile, false);
    this.journalFileBackup.delete();
    this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
    monitorexit;
  }

  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      deleteIfExists(paramFile2);
    if (!paramFile1.renameTo(paramFile2))
      throw new IOException();
  }

  private void trimToFileCount()
    throws IOException
  {
    while (this.fileCount > this.maxFileCount)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + paramString + "\"");
  }

  /** @deprecated */
  public void close()
    throws IOException
  {
    monitorenter;
    while (true)
    {
      try
      {
        Writer localWriter = this.journalWriter;
        if (localWriter == null)
          return;
        Iterator localIterator = new ArrayList(this.lruEntries.values()).iterator();
        if (localIterator.hasNext())
        {
          Entry localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor == null)
            continue;
          localEntry.currentEditor.abort();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      trimToSize();
      trimToFileCount();
      this.journalWriter.close();
      this.journalWriter = null;
    }
  }

  public void delete()
    throws IOException
  {
    close();
    Util.deleteContents(this.directory);
  }

  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }

  /** @deprecated */
  public long fileCount()
  {
    monitorenter;
    try
    {
      int i = this.fileCount;
      long l = i;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void flush()
    throws IOException
  {
    monitorenter;
    try
    {
      checkNotClosed();
      trimToSize();
      trimToFileCount();
      this.journalWriter.flush();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public Snapshot get(String paramString)
    throws IOException
  {
    Snapshot localSnapshot = null;
    monitorenter;
    try
    {
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)this.lruEntries.get(paramString);
      if (localEntry == null);
      while (true)
      {
        return localSnapshot;
        if (!localEntry.readable)
          continue;
        File[] arrayOfFile = new File[this.valueCount];
        InputStream[] arrayOfInputStream = new InputStream[this.valueCount];
        int i = 0;
        int j;
        try
        {
          while (i < this.valueCount)
          {
            File localFile = localEntry.getCleanFile(i);
            arrayOfFile[i] = localFile;
            arrayOfInputStream[i] = new FileInputStream(localFile);
            i++;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          j = 0;
        }
        while ((j < this.valueCount) && (arrayOfInputStream[j] != null))
        {
          Util.closeQuietly(arrayOfInputStream[j]);
          j++;
        }
        continue;
        this.redundantOpCount = (1 + this.redundantOpCount);
        this.journalWriter.append("READ " + paramString + '\n');
        if (journalRebuildRequired())
          this.executorService.submit(this.cleanupCallable);
        localSnapshot = new Snapshot(paramString, localEntry.sequenceNumber, arrayOfFile, arrayOfInputStream, localEntry.lengths, null);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public File getDirectory()
  {
    return this.directory;
  }

  /** @deprecated */
  public int getMaxFileCount()
  {
    monitorenter;
    try
    {
      int i = this.maxFileCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public long getMaxSize()
  {
    monitorenter;
    try
    {
      long l = this.maxSize;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean isClosed()
  {
    monitorenter;
    try
    {
      Writer localWriter = this.journalWriter;
      if (localWriter == null)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  /** @deprecated */
  public boolean remove(String paramString)
    throws IOException
  {
    monitorenter;
    while (true)
    {
      Entry localEntry;
      int j;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (localEntry == null)
          continue;
        Editor localEditor = localEntry.currentEditor;
        if (localEditor == null)
          continue;
        i = 0;
        return i;
        j = 0;
        if (j >= this.valueCount)
          break label157;
        File localFile = localEntry.getCleanFile(j);
        if ((localFile.exists()) && (!localFile.delete()))
          throw new IOException("failed to delete " + localFile);
      }
      finally
      {
        monitorexit;
      }
      this.size -= localEntry.lengths[j];
      this.fileCount = (-1 + this.fileCount);
      localEntry.lengths[j] = 0L;
      j++;
      continue;
      label157: this.redundantOpCount = (1 + this.redundantOpCount);
      this.journalWriter.append("REMOVE " + paramString + '\n');
      this.lruEntries.remove(paramString);
      if (journalRebuildRequired())
        this.executorService.submit(this.cleanupCallable);
      int i = 1;
    }
  }

  /** @deprecated */
  public void setMaxSize(long paramLong)
  {
    monitorenter;
    try
    {
      this.maxSize = paramLong;
      this.executorService.submit(this.cleanupCallable);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public long size()
  {
    monitorenter;
    try
    {
      long l = this.size;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;

    private Entry(String arg2)
    {
      Object localObject;
      this.key = localObject;
      this.lengths = new long[DiskLruCache.this.valueCount];
    }

    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }

    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount)
        throw invalidLengths(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i++;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }

    public File getCleanFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "" + paramInt);
    }

    public File getDirtyFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "" + paramInt + ".tmp");
    }

    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (long l : this.lengths)
        localStringBuilder.append(' ').append(l);
      return localStringBuilder.toString();
    }
  }

  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;

    private Editor(DiskLruCache.Entry arg2)
    {
      DiskLruCache.Entry localEntry;
      this.entry = localEntry;
      if (localEntry.readable);
      for (boolean[] arrayOfBoolean = null; ; arrayOfBoolean = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = arrayOfBoolean;
        return;
      }
    }

    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }

    public void abortUnlessCommitted()
    {
      if (!this.committed);
      try
      {
        abort();
        label11: return;
      }
      catch (IOException localIOException)
      {
        break label11;
      }
    }

    public void commit()
      throws IOException
    {
      if (this.hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        DiskLruCache.this.remove(this.entry.key);
      }
      while (true)
      {
        this.committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }

    public String getString(int paramInt)
      throws IOException
    {
      InputStream localInputStream = newInputStream(paramInt);
      if (localInputStream != null);
      for (String str = DiskLruCache.access$1800(localInputStream); ; str = null)
        return str;
    }

    // ERROR //
    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_2
      //   2: aload_0
      //   3: getfield 23	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:this$0	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache;
      //   6: astore_3
      //   7: aload_3
      //   8: monitorenter
      //   9: aload_0
      //   10: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   13: invokestatic 88	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:access$800	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;)Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor;
      //   16: aload_0
      //   17: if_acmpeq +18 -> 35
      //   20: new 90	java/lang/IllegalStateException
      //   23: dup
      //   24: invokespecial 91	java/lang/IllegalStateException:<init>	()V
      //   27: athrow
      //   28: astore 4
      //   30: aload_3
      //   31: monitorexit
      //   32: aload 4
      //   34: athrow
      //   35: aload_0
      //   36: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   39: invokestatic 34	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:access$700	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;)Z
      //   42: ifne +7 -> 49
      //   45: aload_3
      //   46: monitorexit
      //   47: aload_2
      //   48: areturn
      //   49: new 93	java/io/FileInputStream
      //   52: dup
      //   53: aload_0
      //   54: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   57: iload_1
      //   58: invokevirtual 97	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:getCleanFile	(I)Ljava/io/File;
      //   61: invokespecial 100	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   64: astore 5
      //   66: aload_3
      //   67: monitorexit
      //   68: aload 5
      //   70: astore_2
      //   71: goto -24 -> 47
      //   74: astore 6
      //   76: aload_3
      //   77: monitorexit
      //   78: goto -31 -> 47
      //
      // Exception table:
      //   from	to	target	type
      //   9	32	28	finally
      //   35	47	28	finally
      //   49	66	28	finally
      //   66	78	28	finally
      //   49	66	74	java/io/FileNotFoundException
    }

    // ERROR //
    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 23	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:this$0	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   11: invokestatic 88	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:access$800	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;)Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor;
      //   14: aload_0
      //   15: if_acmpeq +16 -> 31
      //   18: new 90	java/lang/IllegalStateException
      //   21: dup
      //   22: invokespecial 91	java/lang/IllegalStateException:<init>	()V
      //   25: athrow
      //   26: astore_3
      //   27: aload_2
      //   28: monitorexit
      //   29: aload_3
      //   30: athrow
      //   31: aload_0
      //   32: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   35: invokestatic 34	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:access$700	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;)Z
      //   38: ifne +10 -> 48
      //   41: aload_0
      //   42: getfield 36	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:written	[Z
      //   45: iload_1
      //   46: iconst_1
      //   47: bastore
      //   48: aload_0
      //   49: getfield 28	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:entry	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry;
      //   52: iload_1
      //   53: invokevirtual 105	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Entry:getDirtyFile	(I)Ljava/io/File;
      //   56: astore 4
      //   58: new 107	java/io/FileOutputStream
      //   61: dup
      //   62: aload 4
      //   64: invokespecial 108	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   67: astore 5
      //   69: new 9	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor$FaultHidingOutputStream
      //   72: dup
      //   73: aload_0
      //   74: aload 5
      //   76: aconst_null
      //   77: invokespecial 111	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor$FaultHidingOutputStream:<init>	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor;Ljava/io/OutputStream;Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$1;)V
      //   80: astore 6
      //   82: aload_2
      //   83: monitorexit
      //   84: goto +39 -> 123
      //   87: astore 7
      //   89: aload_0
      //   90: getfield 23	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:this$0	Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache;
      //   93: invokestatic 115	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:access$2000	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache;)Ljava/io/File;
      //   96: invokevirtual 121	java/io/File:mkdirs	()Z
      //   99: pop
      //   100: new 107	java/io/FileOutputStream
      //   103: dup
      //   104: aload 4
      //   106: invokespecial 108	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   109: astore 5
      //   111: goto -42 -> 69
      //   114: astore 9
      //   116: invokestatic 125	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:access$2100	()Ljava/io/OutputStream;
      //   119: astore 6
      //   121: aload_2
      //   122: monitorexit
      //   123: aload 6
      //   125: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   7	29	26	finally
      //   31	58	26	finally
      //   58	69	26	finally
      //   69	100	26	finally
      //   100	111	26	finally
      //   116	123	26	finally
      //   58	69	87	java/io/FileNotFoundException
      //   100	111	114	java/io/FileNotFoundException
    }

    // ERROR //
    public void set(int paramInt, String paramString)
      throws IOException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: new 129	java/io/OutputStreamWriter
      //   5: dup
      //   6: aload_0
      //   7: iload_1
      //   8: invokevirtual 131	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:newOutputStream	(I)Ljava/io/OutputStream;
      //   11: getstatic 137	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:UTF_8	Ljava/nio/charset/Charset;
      //   14: invokespecial 140	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
      //   17: astore 4
      //   19: aload 4
      //   21: aload_2
      //   22: invokevirtual 146	java/io/Writer:write	(Ljava/lang/String;)V
      //   25: aload 4
      //   27: invokestatic 150	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   30: return
      //   31: astore 5
      //   33: aload_3
      //   34: invokestatic 150	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   37: aload 5
      //   39: athrow
      //   40: astore 5
      //   42: aload 4
      //   44: astore_3
      //   45: goto -12 -> 33
      //
      // Exception table:
      //   from	to	target	type
      //   2	19	31	finally
      //   19	25	40	finally
    }

    private class FaultHidingOutputStream extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream arg2)
      {
        super();
      }

      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
            DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
            DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
            DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
            DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }
    }
  }

  public final class Snapshot
    implements Closeable
  {
    private File[] files;
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;

    private Snapshot(String paramLong, long arg3, File[] paramArrayOfInputStream, InputStream[] paramArrayOfLong, long[] arg7)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.files = paramArrayOfInputStream;
      this.ins = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

    public void close()
    {
      InputStream[] arrayOfInputStream = this.ins;
      int i = arrayOfInputStream.length;
      for (int j = 0; j < i; j++)
        Util.closeQuietly(arrayOfInputStream[j]);
    }

    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }

    public File getFile(int paramInt)
    {
      return this.files[paramInt];
    }

    public InputStream getInputStream(int paramInt)
    {
      return this.ins[paramInt];
    }

    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }

    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.access$1800(getInputStream(paramInt));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache
 * JD-Core Version:    0.6.0
 */