package rx.android.content;

import android.database.Cursor;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final class OnSubscribeCursor
  implements Observable.OnSubscribe<Cursor>
{
  private final Cursor cursor;

  OnSubscribeCursor(Cursor paramCursor)
  {
    this.cursor = paramCursor;
  }

  public void call(Subscriber<? super Cursor> paramSubscriber)
  {
    try
    {
      if ((!paramSubscriber.isUnsubscribed()) && (this.cursor.moveToNext()))
        paramSubscriber.onNext(this.cursor);
    }
    finally
    {
      if (!this.cursor.isClosed())
        this.cursor.close();
    }
    paramSubscriber.onCompleted();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.content.OnSubscribeCursor
 * JD-Core Version:    0.6.0
 */