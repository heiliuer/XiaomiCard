package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import dm;

public class FM_Shorts
{
  public short[] data = new short[0];

  public static short[] copyOf(short[] paramArrayOfShort, int paramInt)
  {
    int i = 0;
    if (paramArrayOfShort == null);
    short[] arrayOfShort1;
    short[] arrayOfShort2;
    try
    {
      throw new NullPointerException(Util4Java.toString(" *x&s0p\"dmS%n`?xp|)?*|\"", 1, 69));
      arrayOfShort1[i] = paramArrayOfShort[i];
      i++;
      break label70;
      arrayOfShort1 = new short[paramInt];
      if (paramArrayOfShort.length < paramInt)
        while (i < paramArrayOfShort.length)
        {
          arrayOfShort1[i] = paramArrayOfShort[i];
          i++;
        }
    }
    catch (dm localdm)
    {
      arrayOfShort2 = null;
    }
    while (true)
    {
      return arrayOfShort2;
      label70: if (i < paramInt)
        break;
      arrayOfShort2 = arrayOfShort1;
    }
  }

  public static short[] copyOfRange(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    if (paramArrayOfShort == null);
    do
    {
      throw new NullPointerException(FM_Long.concat("{icu`{s)?~\b&-kls+2l97!4", 3));
      while (paramArrayOfShort.length >= paramInt1)
      {
        if (paramArrayOfShort.length < paramInt2)
          throw new IllegalArgumentException(FM_Exception.getChars(1, 32, "zuh3=st;64v?4}n2\001") + paramArrayOfShort.length + FM_Long.concat("��4gqR", 5) + paramInt2 + "]");
        int i = paramInt2 - paramInt1;
        short[] arrayOfShort = new short[i];
        for (int j = 0; ; j++)
        {
          if (j >= i)
            return arrayOfShort;
          arrayOfShort[j] = paramArrayOfShort[(j + paramInt1)];
        }
      }
      throw new IllegalArgumentException(FM_Long.concat("8l|p#&4$<ujtr`fu\023", 288) + paramArrayOfShort.length + FM_Exception.getChars(2, 15, "\0066mgL") + paramInt1 + "]");
    }
    while (paramInt2 - paramInt1 > 0);
    throw new IllegalArgumentException(CRCUtil.valueOf(4, "z-.\"sT") + paramInt1 + FM_Int.lastIndexOf(5, "\005g.4\007") + paramInt2 + "]");
  }

  public static short[] join(short[] paramArrayOfShort1, short[] paramArrayOfShort2)
  {
    int i = 0;
    if (paramArrayOfShort1 == null);
    while (true)
    {
      int k;
      short[] arrayOfShort2;
      int j;
      try
      {
        throw new NullPointerException(FM_CN.subSequence("3fp4.om\035yhha4v,'#|t|s", 5));
        if (paramArrayOfShort2 != null)
          continue;
        throw new NullPointerException(FM_Utils.copyValueOf(5, 96, "041dqbpQb\"1icp9c0>%||"));
        arrayOfShort1[k] = paramArrayOfShort1[k];
        k++;
        break label109;
        if (i < paramArrayOfShort2.length)
          continue;
        arrayOfShort2 = arrayOfShort1;
        break label106;
        arrayOfShort1[(j + i)] = paramArrayOfShort2[i];
        i++;
        continue;
        j = paramArrayOfShort1.length;
        short[] arrayOfShort1 = new short[paramArrayOfShort1.length + paramArrayOfShort2.length];
        k = 0;
      }
      catch (dm localdm)
      {
        arrayOfShort2 = null;
      }
      label106: return arrayOfShort2;
      label109: if (k < j)
        continue;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Shorts
 * JD-Core Version:    0.6.0
 */