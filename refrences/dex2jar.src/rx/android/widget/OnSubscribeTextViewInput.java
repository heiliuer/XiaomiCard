package rx.android.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;
import rx.functions.Action0;

class OnSubscribeTextViewInput
  implements Observable.OnSubscribe<OnTextChangeEvent>
{
  private final boolean emitInitialValue;
  private final TextView input;

  public OnSubscribeTextViewInput(TextView paramTextView, boolean paramBoolean)
  {
    this.input = paramTextView;
    this.emitInitialValue = paramBoolean;
  }

  public void call(Subscriber<? super OnTextChangeEvent> paramSubscriber)
  {
    Assertions.assertUiThread();
    1 local1 = new SimpleTextWatcher(paramSubscriber)
    {
      public void afterTextChanged(Editable paramEditable)
      {
        this.val$observer.onNext(OnTextChangeEvent.create(OnSubscribeTextViewInput.this.input));
      }
    };
    Subscription localSubscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0(local1)
    {
      public void call()
      {
        OnSubscribeTextViewInput.this.input.removeTextChangedListener(this.val$watcher);
      }
    });
    if (this.emitInitialValue)
      paramSubscriber.onNext(OnTextChangeEvent.create(this.input));
    this.input.addTextChangedListener(local1);
    paramSubscriber.add(localSubscription);
  }

  private static class SimpleTextWatcher
    implements TextWatcher
  {
    public void afterTextChanged(Editable paramEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnSubscribeTextViewInput
 * JD-Core Version:    0.6.0
 */