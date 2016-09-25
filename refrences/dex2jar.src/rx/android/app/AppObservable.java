package rx.android.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import rx.Observable;
import rx.android.internal.Assertions;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public final class AppObservable
{
  private static final Func1<Activity, Boolean> ACTIVITY_VALIDATOR;
  private static final Func1<android.support.v4.app.Fragment, Boolean> FRAGMENTV4_VALIDATOR;
  private static final Func1<android.app.Fragment, Boolean> FRAGMENT_VALIDATOR;
  public static final boolean USES_SUPPORT_FRAGMENTS;

  static
  {
    boolean bool = false;
    try
    {
      Class.forName("android.support.v4.app.Fragment");
      bool = true;
      label10: USES_SUPPORT_FRAGMENTS = bool;
      ACTIVITY_VALIDATOR = new Func1()
      {
        public Boolean call(Activity paramActivity)
        {
          if (!paramActivity.isFinishing());
          for (boolean bool = true; ; bool = false)
            return Boolean.valueOf(bool);
        }
      };
      FRAGMENT_VALIDATOR = new Func1()
      {
        public Boolean call(android.app.Fragment paramFragment)
        {
          if ((paramFragment.isAdded()) && (!paramFragment.getActivity().isFinishing()));
          for (boolean bool = true; ; bool = false)
            return Boolean.valueOf(bool);
        }
      };
      FRAGMENTV4_VALIDATOR = new Func1()
      {
        public Boolean call(android.support.v4.app.Fragment paramFragment)
        {
          if ((paramFragment.isAdded()) && (!paramFragment.getActivity().isFinishing()));
          for (boolean bool = true; ; bool = false)
            return Boolean.valueOf(bool);
        }
      };
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label10;
    }
  }

  private AppObservable()
  {
    throw new AssertionError("No instances");
  }

  public static <T> Observable<T> bindActivity(Activity paramActivity, Observable<T> paramObservable)
  {
    Assertions.assertUiThread();
    return paramObservable.observeOn(AndroidSchedulers.mainThread()).lift(new OperatorConditionalBinding(paramActivity, ACTIVITY_VALIDATOR));
  }

  public static <T> Observable<T> bindFragment(Object paramObject, Observable<T> paramObservable)
  {
    Assertions.assertUiThread();
    Observable localObservable1 = paramObservable.observeOn(AndroidSchedulers.mainThread());
    if ((USES_SUPPORT_FRAGMENTS) && ((paramObject instanceof android.support.v4.app.Fragment)));
    for (Observable localObservable2 = localObservable1.lift(new OperatorConditionalBinding((android.support.v4.app.Fragment)paramObject, FRAGMENTV4_VALIDATOR)); ; localObservable2 = localObservable1.lift(new OperatorConditionalBinding((android.app.Fragment)paramObject, FRAGMENT_VALIDATOR)))
    {
      return localObservable2;
      if ((Build.VERSION.SDK_INT < 11) || (!(paramObject instanceof android.app.Fragment)))
        break;
    }
    throw new IllegalArgumentException("Target fragment is neither a native nor support library Fragment");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.app.AppObservable
 * JD-Core Version:    0.6.0
 */