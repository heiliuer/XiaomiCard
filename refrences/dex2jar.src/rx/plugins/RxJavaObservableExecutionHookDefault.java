package rx.plugins;

class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook
{
  private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();

  public static RxJavaObservableExecutionHook getInstance()
  {
    return INSTANCE;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.plugins.RxJavaObservableExecutionHookDefault
 * JD-Core Version:    0.6.0
 */