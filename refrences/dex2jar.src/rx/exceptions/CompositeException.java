package rx.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class CompositeException extends RuntimeException
{
  private static final long serialVersionUID = 3026362227162912146L;
  private Throwable cause = null;
  private final List<Throwable> exceptions;
  private final String message;

  public CompositeException(String paramString, Collection<? extends Throwable> paramCollection)
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    ArrayList localArrayList = new ArrayList();
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Throwable localThrowable = (Throwable)localIterator.next();
        if ((localThrowable instanceof CompositeException))
        {
          localLinkedHashSet.addAll(((CompositeException)localThrowable).getExceptions());
          continue;
        }
        if (localThrowable != null)
        {
          localLinkedHashSet.add(localThrowable);
          continue;
        }
        localLinkedHashSet.add(new NullPointerException());
      }
    }
    localLinkedHashSet.add(new NullPointerException());
    localArrayList.addAll(localLinkedHashSet);
    this.exceptions = Collections.unmodifiableList(localArrayList);
    this.message = (this.exceptions.size() + " exceptions occurred. ");
  }

  public CompositeException(Collection<? extends Throwable> paramCollection)
  {
    this(null, paramCollection);
  }

  private void appendStackTrace(StringBuilder paramStringBuilder, Throwable paramThrowable, String paramString)
  {
    paramStringBuilder.append(paramString).append(paramThrowable).append("\n");
    for (StackTraceElement localStackTraceElement : paramThrowable.getStackTrace())
      paramStringBuilder.append("\t\tat ").append(localStackTraceElement).append("\n");
    if (paramThrowable.getCause() != null)
    {
      paramStringBuilder.append("\tCaused by: ");
      appendStackTrace(paramStringBuilder, paramThrowable.getCause(), "");
    }
  }

  private final List<Throwable> getListOfCauses(Throwable paramThrowable)
  {
    ArrayList localArrayList = new ArrayList();
    Throwable localThrowable = paramThrowable.getCause();
    if (localThrowable == null);
    while (true)
    {
      return localArrayList;
      do
      {
        localThrowable = localThrowable.getCause();
        localArrayList.add(localThrowable);
      }
      while (localThrowable.getCause() != null);
    }
  }

  private void printStackTrace(PrintStreamOrWriter paramPrintStreamOrWriter)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this).append("\n");
    for (StackTraceElement localStackTraceElement : getStackTrace())
      localStringBuilder.append("\tat ").append(localStackTraceElement).append("\n");
    int k = 1;
    Iterator localIterator = this.exceptions.iterator();
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      localStringBuilder.append("  ComposedException ").append(k).append(" :").append("\n");
      appendStackTrace(localStringBuilder, localThrowable, "\t");
      k++;
    }
    synchronized (paramPrintStreamOrWriter.lock())
    {
      paramPrintStreamOrWriter.println(localStringBuilder.toString());
      return;
    }
  }

  /** @deprecated */
  public Throwable getCause()
  {
    monitorenter;
    while (true)
    {
      CompositeExceptionCausalChain localCompositeExceptionCausalChain;
      Object localObject2;
      Object localObject3;
      try
      {
        if (this.cause == null)
        {
          localCompositeExceptionCausalChain = new CompositeExceptionCausalChain();
          HashSet localHashSet = new HashSet();
          localObject2 = localCompositeExceptionCausalChain;
          Iterator localIterator1 = this.exceptions.iterator();
          if (localIterator1.hasNext())
          {
            localObject3 = (Throwable)localIterator1.next();
            if (localHashSet.contains(localObject3))
              continue;
            localHashSet.add(localObject3);
            Iterator localIterator2 = getListOfCauses((Throwable)localObject3).iterator();
            if (localIterator2.hasNext())
            {
              Throwable localThrowable3 = (Throwable)localIterator2.next();
              if (!localHashSet.contains(localThrowable3))
                continue;
              localObject3 = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
              continue;
              localHashSet.add(localThrowable3);
              continue;
            }
          }
        }
      }
      finally
      {
        monitorexit;
      }
      try
      {
        ((Throwable)localObject2).initCause((Throwable)localObject3);
        label166: localObject2 = ((Throwable)localObject2).getCause();
        continue;
        this.cause = localCompositeExceptionCausalChain;
        Throwable localThrowable1 = this.cause;
        monitorexit;
        return localThrowable1;
      }
      catch (Throwable localThrowable2)
      {
        break label166;
      }
    }
  }

  public List<Throwable> getExceptions()
  {
    return this.exceptions;
  }

  public String getMessage()
  {
    return this.message;
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    printStackTrace(new WrappedPrintStream(paramPrintStream));
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    printStackTrace(new WrappedPrintWriter(paramPrintWriter));
  }

  static final class CompositeExceptionCausalChain extends RuntimeException
  {
    static String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
    private static final long serialVersionUID = 3875212506787802066L;

    public String getMessage()
    {
      return MESSAGE;
    }
  }

  private static class WrappedPrintWriter extends CompositeException.PrintStreamOrWriter
  {
    private final PrintWriter printWriter;

    WrappedPrintWriter(PrintWriter paramPrintWriter)
    {
      super();
      this.printWriter = paramPrintWriter;
    }

    Object lock()
    {
      return this.printWriter;
    }

    void println(Object paramObject)
    {
      this.printWriter.println(paramObject);
    }
  }

  private static class WrappedPrintStream extends CompositeException.PrintStreamOrWriter
  {
    private final PrintStream printStream;

    WrappedPrintStream(PrintStream paramPrintStream)
    {
      super();
      this.printStream = paramPrintStream;
    }

    Object lock()
    {
      return this.printStream;
    }

    void println(Object paramObject)
    {
      this.printStream.println(paramObject);
    }
  }

  private static abstract class PrintStreamOrWriter
  {
    abstract Object lock();

    abstract void println(Object paramObject);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.exceptions.CompositeException
 * JD-Core Version:    0.6.0
 */