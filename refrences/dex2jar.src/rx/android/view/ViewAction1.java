package rx.android.view;

import android.view.View;
import java.lang.ref.WeakReference;
import rx.functions.Action1;

public abstract class ViewAction1<V extends View, T>
  implements Action1<T>
{
  private final WeakReference<V> viewReference;

  public ViewAction1(V paramV)
  {
    this.viewReference = new WeakReference(paramV);
  }

  public abstract void call(V paramV, T paramT);

  public final void call(T paramT)
  {
    View localView = (View)this.viewReference.get();
    if (localView != null)
      call(localView, paramT);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.ViewAction1
 * JD-Core Version:    0.6.0
 */