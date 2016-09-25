package rx.plugins;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class RxJavaPlugins
{
  static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER;
  private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
  private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference();
  private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference();
  private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference();

  static
  {
    DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler()
    {
    };
  }

  public static RxJavaPlugins getInstance()
  {
    return INSTANCE;
  }

  static Object getPluginImplementationViaProperty(Class<?> paramClass, Properties paramProperties)
  {
    String str1 = paramClass.getSimpleName();
    String str2 = paramProperties.getProperty("rxjava.plugin." + str1 + ".implementation");
    if (str2 == null)
    {
      Iterator localIterator = paramProperties.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str3 = localEntry.getKey().toString();
        if ((!str3.startsWith("rxjava.plugin.")) || (!str3.endsWith(".class")) || (!str1.equals(localEntry.getValue().toString())))
          continue;
        String str4 = str3.substring(0, str3.length() - ".class".length()).substring("rxjava.plugin.".length());
        String str5 = "rxjava.plugin." + str4 + ".impl";
        str2 = paramProperties.getProperty(str5);
        if (str2 != null)
          break;
        throw new RuntimeException("Implementing class declaration for " + str1 + " missing: " + str5);
      }
    }
    if (str2 != null);
    while (true)
    {
      try
      {
        Object localObject2 = Class.forName(str2).asSubclass(paramClass).newInstance();
        localObject1 = localObject2;
        return localObject1;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new RuntimeException(str1 + " implementation is not an instance of " + str1 + ": " + str2);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new RuntimeException(str1 + " implementation class not found: " + str2, localClassNotFoundException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new RuntimeException(str1 + " implementation not able to be instantiated: " + str2, localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new RuntimeException(str1 + " implementation not able to be accessed: " + str2, localIllegalAccessException);
      }
      Object localObject1 = null;
    }
  }

  public RxJavaErrorHandler getErrorHandler()
  {
    Object localObject;
    if (this.errorHandler.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaErrorHandler.class, System.getProperties());
      if (localObject != null)
        break label46;
      this.errorHandler.compareAndSet(null, DEFAULT_ERROR_HANDLER);
    }
    while (true)
    {
      return (RxJavaErrorHandler)this.errorHandler.get();
      label46: this.errorHandler.compareAndSet(null, (RxJavaErrorHandler)localObject);
    }
  }

  public RxJavaObservableExecutionHook getObservableExecutionHook()
  {
    Object localObject;
    if (this.observableExecutionHook.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, System.getProperties());
      if (localObject != null)
        break label46;
      this.observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
    }
    while (true)
    {
      return (RxJavaObservableExecutionHook)this.observableExecutionHook.get();
      label46: this.observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook)localObject);
    }
  }

  public RxJavaSchedulersHook getSchedulersHook()
  {
    Object localObject;
    if (this.schedulersHook.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, System.getProperties());
      if (localObject != null)
        break label46;
      this.schedulersHook.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
    }
    while (true)
    {
      return (RxJavaSchedulersHook)this.schedulersHook.get();
      label46: this.schedulersHook.compareAndSet(null, (RxJavaSchedulersHook)localObject);
    }
  }

  public void registerErrorHandler(RxJavaErrorHandler paramRxJavaErrorHandler)
  {
    if (!this.errorHandler.compareAndSet(null, paramRxJavaErrorHandler))
      throw new IllegalStateException("Another strategy was already registered: " + this.errorHandler.get());
  }

  public void registerObservableExecutionHook(RxJavaObservableExecutionHook paramRxJavaObservableExecutionHook)
  {
    if (!this.observableExecutionHook.compareAndSet(null, paramRxJavaObservableExecutionHook))
      throw new IllegalStateException("Another strategy was already registered: " + this.observableExecutionHook.get());
  }

  public void registerSchedulersHook(RxJavaSchedulersHook paramRxJavaSchedulersHook)
  {
    if (!this.schedulersHook.compareAndSet(null, paramRxJavaSchedulersHook))
      throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
  }

  void reset()
  {
    INSTANCE.errorHandler.set(null);
    INSTANCE.observableExecutionHook.set(null);
    INSTANCE.schedulersHook.set(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.plugins.RxJavaPlugins
 * JD-Core Version:    0.6.0
 */