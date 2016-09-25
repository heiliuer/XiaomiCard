package rx.android.widget;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import rx.Observable;
import rx.android.view.OnCheckedChangeEvent;

public final class WidgetObservable
{
  private WidgetObservable()
  {
    throw new AssertionError("No instances");
  }

  public static Observable<OnCheckedChangeEvent> input(CompoundButton paramCompoundButton)
  {
    return input(paramCompoundButton, false);
  }

  public static Observable<OnCheckedChangeEvent> input(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    return Observable.create(new OnSubscribeCompoundButtonInput(paramCompoundButton, paramBoolean));
  }

  public static Observable<OnItemClickEvent> itemClicks(AdapterView<?> paramAdapterView)
  {
    return Observable.create(new OnSubscribeAdapterViewOnItemClick(paramAdapterView));
  }

  public static Observable<OnListViewScrollEvent> listScrollEvents(AbsListView paramAbsListView)
  {
    return Observable.create(new OnSubscribeListViewScroll(paramAbsListView));
  }

  public static Observable<OnTextChangeEvent> text(TextView paramTextView)
  {
    return text(paramTextView, false);
  }

  public static Observable<OnTextChangeEvent> text(TextView paramTextView, boolean paramBoolean)
  {
    return Observable.create(new OnSubscribeTextViewInput(paramTextView, paramBoolean));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.WidgetObservable
 * JD-Core Version:    0.6.0
 */