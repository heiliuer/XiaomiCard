package rx.android.internal;

import android.os.Looper;

public final class Assertions
{
  private Assertions()
  {
    throw new AssertionError("No instances");
  }

  public static void assertUiThread()
  {
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("Observers must subscribe from the main UI thread, but was " + Thread.currentThread());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.internal.Assertions
 * JD-Core Version:    0.6.0
 */