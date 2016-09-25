package rx.android.view;

import android.view.View;
import rx.Observable;
import rx.android.internal.Assertions;
import rx.android.schedulers.AndroidSchedulers;

public final class ViewObservable
{
  private ViewObservable()
  {
    throw new AssertionError("No instances");
  }

  public static <T> Observable<T> bindView(View paramView, Observable<T> paramObservable)
  {
    if ((paramView == null) || (paramObservable == null))
      throw new IllegalArgumentException("View and Observable must be given");
    Assertions.assertUiThread();
    return paramObservable.takeUntil(Observable.create(new OnSubscribeViewDetachedFromWindowFirst(paramView))).observeOn(AndroidSchedulers.mainThread());
  }

  public static Observable<OnClickEvent> clicks(View paramView)
  {
    return clicks(paramView, false);
  }

  public static Observable<OnClickEvent> clicks(View paramView, boolean paramBoolean)
  {
    return Observable.create(new OnSubscribeViewClick(paramView, paramBoolean));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.ViewObservable
 * JD-Core Version:    0.6.0
 */