package cn.com.fmsh.tsm.business.card;

import cn.com.fmsh.util.FM_Bytes;

public class CardTools
{
  public static String getFaceID4UID(byte[] paramArrayOfByte)
  {
    int i;
    int[] arrayOfInt;
    long l1;
    label44: int j;
    int k;
    label57: Object localObject2;
    byte[] arrayOfByte;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1))
    {
      Object localObject1 = null;
      while (true)
      {
        return localObject1;
        int i3 = i + 1;
        Object localObject3;
        arrayOfInt[i] = (int)(localObject3 % 10L);
        localObject3 /= 10L;
        i = i3;
        if (l1 > 0L)
          break;
        j = 0;
        k = 0;
        if (k >= i)
        {
          int n = (10 - j % 10) % 10;
          String str1 = localObject2 + n;
          long l2 = FM_Bytes.bytesToLong(arrayOfByte);
          localObject1 = str1;
          int i1 = 1;
          while (i1 < 11)
          {
            int i2 = (int)(l2 % 100L);
            String str2 = new StringBuilder(String.valueOf(localObject1)).append(i2 / 10).toString() + i2 % 10;
            l2 /= 100L;
            i1 += 2;
            localObject1 = str2;
          }
          continue;
        }
        if ((k & 0x1) != 0)
          break label266;
        int m = arrayOfInt[k] + arrayOfInt[k];
        j = j + m % 10 + m / 10;
      }
    }
    while (true)
    {
      k++;
      break label57;
      arrayOfByte = FM_Bytes.join(new byte[1], paramArrayOfByte);
      localObject2 = new String("");
      arrayOfInt = new int[20];
      l1 = FM_Bytes.bytesToLong(arrayOfByte);
      i = 0;
      break label44;
      break;
      label266: j += arrayOfInt[k];
    }
  }

  public static String getFaceNo4uidByLnt(byte[] paramArrayOfByte)
  {
    return FM_Bytes.bytesToHexString(paramArrayOfByte);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.CardTools
 * JD-Core Version:    0.6.0
 */