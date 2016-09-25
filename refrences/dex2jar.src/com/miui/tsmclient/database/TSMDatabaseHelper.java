package com.miui.tsmclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.miui.tsmclient.util.LogUtils;

public class TSMDatabaseHelper extends SQLiteOpenHelper
{
  private static final String CONTENT_SPLIT = ",";
  private static final String DB_NAME = "tsmclient.db";
  private static final int DB_VERSION = 4;
  private static final String FILE_BANK_BIN = "bank_bin";
  private static final String TAG = "TSMDatabaseHelper";
  private Context mContext;

  public TSMDatabaseHelper(Context paramContext)
  {
    super(paramContext, "tsmclient.db", null, 4);
    this.mContext = paramContext;
  }

  private void createBankBinTb(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS bank_bin(_id INTEGER PRIMARY KEY AUTOINCREMENT,bin_code TEXT NOT NULL,bank_name TEXT NOT NULL,card_type INTEGER);");
  }

  private void createCacheTb(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cache(_id INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,value TEXT);");
  }

  private void createTables(SQLiteDatabase paramSQLiteDatabase)
  {
    createCacheTb(paramSQLiteDatabase);
    createBankBinTb(paramSQLiteDatabase);
  }

  // ERROR //
  private void initBankBinTb(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 52	java/io/BufferedReader
    //   5: dup
    //   6: new 54	java/io/InputStreamReader
    //   9: dup
    //   10: aload_0
    //   11: getfield 29	com/miui/tsmclient/database/TSMDatabaseHelper:mContext	Landroid/content/Context;
    //   14: invokevirtual 60	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   17: ldc 17
    //   19: invokevirtual 66	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   22: invokespecial 69	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: invokespecial 72	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   28: astore_3
    //   29: new 74	android/content/ContentValues
    //   32: dup
    //   33: invokespecial 77	android/content/ContentValues:<init>	()V
    //   36: astore 4
    //   38: aload_3
    //   39: invokevirtual 81	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore 9
    //   44: aload 9
    //   46: ifnull +109 -> 155
    //   49: aload 4
    //   51: invokevirtual 84	android/content/ContentValues:clear	()V
    //   54: aload 9
    //   56: ldc 8
    //   58: invokevirtual 90	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   61: astore 11
    //   63: aload 4
    //   65: ldc 92
    //   67: aload 11
    //   69: iconst_0
    //   70: aaload
    //   71: invokevirtual 96	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload 4
    //   76: ldc 98
    //   78: aload 11
    //   80: iconst_1
    //   81: aaload
    //   82: invokevirtual 96	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: iconst_0
    //   86: istore 12
    //   88: aload 11
    //   90: iconst_2
    //   91: aaload
    //   92: invokestatic 104	android/text/TextUtils:isDigitsOnly	(Ljava/lang/CharSequence;)Z
    //   95: ifeq +15 -> 110
    //   98: aload 11
    //   100: iconst_2
    //   101: aaload
    //   102: invokestatic 110	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   105: invokevirtual 114	java/lang/Integer:intValue	()I
    //   108: istore 12
    //   110: aload 4
    //   112: ldc 116
    //   114: iload 12
    //   116: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   119: invokevirtual 122	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   122: aload_1
    //   123: ldc 17
    //   125: aconst_null
    //   126: aload 4
    //   128: invokevirtual 126	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   131: pop2
    //   132: goto -94 -> 38
    //   135: astore 7
    //   137: aload_3
    //   138: astore_2
    //   139: ldc 128
    //   141: aload 7
    //   143: invokestatic 134	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   146: aload_2
    //   147: ifnull +7 -> 154
    //   150: aload_2
    //   151: invokevirtual 137	java/io/BufferedReader:close	()V
    //   154: return
    //   155: aload_3
    //   156: ifnull +71 -> 227
    //   159: aload_3
    //   160: invokevirtual 137	java/io/BufferedReader:close	()V
    //   163: goto -9 -> 154
    //   166: astore 10
    //   168: ldc 139
    //   170: aload 10
    //   172: invokestatic 134	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   175: goto -21 -> 154
    //   178: astore 8
    //   180: ldc 139
    //   182: aload 8
    //   184: invokestatic 134	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   187: goto -33 -> 154
    //   190: astore 5
    //   192: aload_2
    //   193: ifnull +7 -> 200
    //   196: aload_2
    //   197: invokevirtual 137	java/io/BufferedReader:close	()V
    //   200: aload 5
    //   202: athrow
    //   203: astore 6
    //   205: ldc 139
    //   207: aload 6
    //   209: invokestatic 134	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   212: goto -12 -> 200
    //   215: astore 5
    //   217: aload_3
    //   218: astore_2
    //   219: goto -27 -> 192
    //   222: astore 7
    //   224: goto -85 -> 139
    //   227: goto -73 -> 154
    //
    // Exception table:
    //   from	to	target	type
    //   29	132	135	java/io/IOException
    //   159	163	166	java/io/IOException
    //   150	154	178	java/io/IOException
    //   2	29	190	finally
    //   139	146	190	finally
    //   196	200	203	java/io/IOException
    //   29	132	215	finally
    //   2	29	222	java/io/IOException
  }

  private void initTables(SQLiteDatabase paramSQLiteDatabase)
  {
    initBankBinTb(paramSQLiteDatabase);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    createTables(paramSQLiteDatabase);
    initTables(paramSQLiteDatabase);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    LogUtils.i("upgrading db from" + paramInt1 + " to " + paramInt2);
    switch (paramInt1)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      paramSQLiteDatabase.beginTransaction();
      try
      {
        createBankBinTb(paramSQLiteDatabase);
        initBankBinTb(paramSQLiteDatabase);
        paramSQLiteDatabase.setTransactionSuccessful();
        paramSQLiteDatabase.endTransaction();
      }
      catch (Throwable localThrowable)
      {
        while (true)
          LogUtils.e("error occurred to upgrade db: " + localThrowable.getMessage());
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.database.TSMDatabaseHelper
 * JD-Core Version:    0.6.0
 */