package rx.android.app;

import android.util.Log;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.android.internal.Assertions;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

final class OperatorConditionalBinding<T, R>
  implements Observable.Operator<T, T>
{
  private static final String LOG_TAG = "ConditionalBinding";
  private R boundRef;
  private final Func1<? super R, Boolean> predicate;

  public OperatorConditionalBinding(R paramR)
  {
    this.boundRef = paramR;
    this.predicate = UtilityFunctions.alwaysTrue();
  }

  public OperatorConditionalBinding(R paramR, Func1<? super R, Boolean> paramFunc1)
  {
    this.boundRef = paramR;
    this.predicate = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private void handleLostBinding(String paramString)
      {
        log("bound object has become invalid; skipping " + paramString);
        log("unsubscribing...");
        OperatorConditionalBinding.access$002(OperatorConditionalBinding.this, null);
        unsubscribe();
      }

      private void log(String paramString)
      {
        if (Log.isLoggable("ConditionalBinding", 3))
          Log.d("ConditionalBinding", paramString);
      }

      private boolean shouldForwardNotification()
      {
        if ((OperatorConditionalBinding.this.boundRef != null) && (((Boolean)OperatorConditionalBinding.this.predicate.call(OperatorConditionalBinding.this.boundRef)).booleanValue()));
        for (int i = 1; ; i = 0)
          return i;
      }

      public void onCompleted()
      {
        Assertions.assertUiThread();
        if (shouldForwardNotification())
          this.val$child.onCompleted();
        while (true)
        {
          return;
          handleLostBinding("onCompleted");
        }
      }

      public void onError(Throwable paramThrowable)
      {
        Assertions.assertUiThread();
        if (shouldForwardNotification())
          this.val$child.onError(paramThrowable);
        while (true)
        {
          return;
          handleLostBinding("onError");
        }
      }

      public void onNext(T paramT)
      {
        Assertions.assertUiThread();
        if (shouldForwardNotification())
          this.val$child.onNext(paramT);
        while (true)
        {
          return;
          handleLostBinding("onNext");
        }
      }
    };
  }

  R getBoundRef()
  {
    return this.boundRef;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.app.OperatorConditionalBinding
 * JD-Core Version:    0.6.0
 */