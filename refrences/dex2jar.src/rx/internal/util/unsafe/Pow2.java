package rx.internal.util.unsafe;

public final class Pow2
{
  private Pow2()
  {
    throw new IllegalStateException("No instances!");
  }

  public static boolean isPowerOfTwo(int paramInt)
  {
    if ((paramInt & paramInt - 1) == 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static int roundToPowerOfTwo(int paramInt)
  {
    return 1 << 32 - Integer.numberOfLeadingZeros(paramInt - 1);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.Pow2
 * JD-Core Version:    0.6.0
 */