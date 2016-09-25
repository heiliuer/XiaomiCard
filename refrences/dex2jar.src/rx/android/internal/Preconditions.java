package rx.android.internal;

public final class Preconditions
{
  private Preconditions()
  {
    throw new AssertionError("No instances");
  }

  public static void checkArgument(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(paramString);
  }

  public static <T> T checkNotNull(T paramT, String paramString)
  {
    if (paramT == null)
      throw new NullPointerException(paramString);
    return paramT;
  }

  public static void checkState(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new IllegalStateException(paramString);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.internal.Preconditions
 * JD-Core Version:    0.6.0
 */