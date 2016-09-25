package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import di;
import java.io.PrintStream;
import java.util.Arrays;

public class FM_CN
{
  public byte[] data = new byte[0];

  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      b = FM_CN.class.getName();
      c = BCCUtil.endsWith("2-$c~1(/ju", 3, 26);
      label28: return;
    }
    catch (di localdi)
    {
      break label28;
    }
  }

  public FM_CN()
  {
  }

  public FM_CN(String paramString)
  {
    valueOf(paramString);
  }

  public static int bcdBytesToInt(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = paramArrayOfByte.length;
    int k = 0;
    if (i >= j);
    while (true)
    {
      return k;
      int m = (byte)((0xF0 & paramArrayOfByte[i]) >>> 4);
      if (m > 9)
        continue;
      int i1;
      do
      {
        k += (n + i1) * power(100, j - (i + 1));
        i++;
        break;
        int n = m * 10;
        i1 = (byte)(0xF & paramArrayOfByte[i]);
      }
      while (i1 <= 9);
    }
  }

  public static String bcdBytesToString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length << 1);
    int i = 0;
    if (i >= paramArrayOfByte.length);
    for (String str = localStringBuilder.toString(); ; str = str.substring(1))
    {
      if (str.charAt(0) != '0');
      while (true)
      {
        return str;
        if (str.length() == 1)
        {
          continue;
          localStringBuilder.append((byte)((0xF0 & paramArrayOfByte[i]) >>> 4));
          localStringBuilder.append((byte)(0xF & paramArrayOfByte[i]));
          i++;
          break;
        }
      }
    }
  }

  public static String bcdBytesToString(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte;
    if (paramArrayOfByte.length > paramInt)
      arrayOfByte = new byte[paramInt];
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        for (String str = bcdBytesToString(arrayOfByte); ; str = bcdBytesToString(paramArrayOfByte))
          return str;
      arrayOfByte[i] = paramArrayOfByte[i];
    }
  }

  public static byte[] intToBcdBytes(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte;
    if (paramInt2 < 1)
    {
      if (a != null)
        a.error(b, FM_Bytes.startsWith("升辎割:,e型敻攮輡扌\tYM砙子苔攵纐斵＞挆宊嬈苌攭纈镤庬靇歫", 5, 111));
      arrayOfByte = null;
    }
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramInt2];
      for (int i = paramInt2 - 1; i >= 0; i--)
      {
        int j = paramInt1 % 100;
        arrayOfByte[i] = ((byte)(j / 10 << 4) + (byte)(0xF & j % 10));
        paramInt1 /= 100;
      }
    }
  }

  public static byte[] longToBcdBytes(long paramLong, int paramInt)
  {
    Object localObject = null;
    if (paramInt < 1);
    byte[] arrayOfByte;
    int i;
    try
    {
      if (a != null)
      {
        a.error(b, FM_Long.concat("匚违刧ph|z埃攧攮輥扄\035IQ码孜芔攱纈斡ｎ捊寂孔芌敩绀镰庼霛欳", 3));
        break label91;
        int j = (int)(paramLong % 100L);
        arrayOfByte[i] = ((byte)(j / 10 << 4) + (byte)(0xF & j % 10));
        paramLong /= 100L;
        i--;
        break label93;
        arrayOfByte = new byte[paramInt];
        i = paramInt - 1;
      }
    }
    catch (di localdi)
    {
    }
    while (true)
    {
      label91: return localObject;
      label93: if (i >= 0)
        break;
      localObject = arrayOfByte;
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    FM_CN localFM_CN = new FM_CN(FM_Utils.copyValueOf(5, 72, " i2{%"));
    System.out.println(localFM_CN.intValue());
    byte[] arrayOfByte = new byte[3];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = 35;
    arrayOfByte[2] = 95;
    try
    {
      localFM_CN.setData(0, arrayOfByte);
      System.out.println(localFM_CN.intValue());
      return;
    }
    catch (FM_Exception localFM_Exception)
    {
      while (true)
        localFM_Exception.printStackTrace();
    }
  }

  public static int power(int paramInt1, int paramInt2)
  {
    int i = 1;
    for (int j = 0; ; j++)
    {
      if (j >= paramInt2)
        return i;
      i *= paramInt1;
    }
  }

  public static byte[] string2Bcd(String paramString)
  {
    int i = paramString.length();
    if (i % 2 != 0)
    {
      paramString = "0" + paramString;
      i = paramString.length();
    }
    if (i >= 2)
      i /= 2;
    byte[] arrayOfByte1 = new byte[i];
    byte[] arrayOfByte2 = paramString.getBytes();
    int m;
    label78: int k;
    for (int j = 0; j >= i; j++)
    {
      return arrayOfByte1;
      m = 10 + (-65 + arrayOfByte2[(1 + j * 2)]);
      arrayOfByte1[j] = (byte)(m + (k << 4));
    }
    if ((arrayOfByte2[(j * 2)] >= 48) && (arrayOfByte2[(j * 2)] <= 57))
      k = -48 + arrayOfByte2[(j * 2)];
    while (true)
    {
      if ((arrayOfByte2[(1 + j * 2)] >= 48) && (arrayOfByte2[(1 + j * 2)] <= 57))
      {
        m = -48 + arrayOfByte2[(1 + j * 2)];
        break label78;
        if ((arrayOfByte2[(j * 2)] >= 97) && (arrayOfByte2[(j * 2)] <= 122))
        {
          k = 10 + (-97 + arrayOfByte2[(j * 2)]);
          continue;
        }
      }
      else
      {
        if ((arrayOfByte2[(1 + j * 2)] < 97) || (arrayOfByte2[(1 + j * 2)] > 122))
          break;
        m = 10 + (-97 + arrayOfByte2[(1 + j * 2)]);
        break label78;
      }
      k = 10 + (-65 + arrayOfByte2[(j * 2)]);
    }
  }

  public static String subSequence(String paramString, int paramInt)
  {
    int i = paramInt + 14;
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = i;
    int m = 0;
    while (m != j)
    {
      int n = arrayOfChar[m] ^ k & 0x5F;
      int i1 = k + 15;
      int i2 = m + 1;
      arrayOfChar[m] = (char)n;
      m = i2;
      k = i1;
    }
    return String.valueOf(arrayOfChar, 0, j).intern();
  }

  public long bcd2Dec()
  {
    int i = this.data.length;
    int j = 0;
    long l1 = 0L;
    if (j >= i);
    int k;
    do
    {
      return l1;
      k = (byte)((0xF0 & this.data[j]) >>> 4);
    }
    while (k > 9);
    long l2 = k * 10;
    int m = (byte)(0xF & this.data[j]);
    long l3;
    if (m > 9)
      l3 = l2 + 0L;
    while (true)
    {
      l1 += l3 * power(100, i - (j + 1));
      j++;
      break;
      l3 = l2 + m;
    }
  }

  public void clear()
  {
    if (this.data != null)
      if (this.data.length <= 0);
    for (this.data = new byte[0]; ; this.data = new byte[0])
      return;
  }

  public String getBCD()
  {
    Object localObject = null;
    try
    {
      if (this.data.length > 0)
      {
        String str = toHexString('\000');
        localObject = str;
      }
      label18: return localObject;
    }
    catch (di localdi)
    {
      break label18;
    }
  }

  public byte getByte(int paramInt)
  {
    try
    {
      if (paramInt < this.data.length);
      for (i = this.data[paramInt]; ; i = -1)
        return i;
    }
    catch (di localdi)
    {
      while (true)
        int i = 0;
    }
  }

  public byte[] getData()
  {
    return this.data;
  }

  public byte getNumber(int paramInt)
  {
    if (paramInt != 0)
      try
      {
        if (paramInt <= this.data.length << 1)
        {
          int j = this.data[((paramInt - 1) / 2)];
          int k = paramInt % 2;
          if (k == 1)
          {
            i = (byte)(j >>> 4);
            break label69;
          }
          i = (byte)(j & 0xF);
        }
      }
      catch (di localdi)
      {
        i = 0;
      }
    int i = -1;
    label69: return i;
  }

  public int intValue()
  {
    long l;
    if (this.data.length > 0)
    {
      l = bcd2Dec();
      if ((l > 2147483647L) || (l < -2147483648L));
    }
    for (int i = (int)l; ; i = 0)
      return i;
  }

  public boolean isEmpty()
  {
    int i = 0;
    try
    {
      byte[] arrayOfByte = this.data;
      if (arrayOfByte == null)
        i = 1;
      label13: return i;
    }
    catch (di localdi)
    {
      break label13;
    }
  }

  public int length()
  {
    try
    {
      i = this.data.length;
      return i;
    }
    catch (di localdi)
    {
      while (true)
        int i = 0;
    }
  }

  public long longValue()
  {
    long l1 = 0L;
    try
    {
      if (this.data.length > 0)
      {
        long l2 = bcd2Dec();
        l1 = l2;
      }
      label19: return l1;
    }
    catch (di localdi)
    {
      break label19;
    }
  }

  public int preplace(int paramInt)
  {
    try
    {
      int j = preplace(paramInt, -1);
      i = j;
      return i;
    }
    catch (di localdi)
    {
      while (true)
        int i = 0;
    }
  }

  public int preplace(int paramInt, byte paramByte)
  {
    int i = 0;
    try
    {
      if (this.data.length != paramInt)
      {
        this.data = new byte[paramInt];
        break label55;
        while (true)
        {
          if (j >= paramInt)
          {
            i = this.data.length;
            break;
          }
          this.data[j] = paramByte;
          j++;
        }
      }
    }
    catch (di localdi)
    {
      while (true)
      {
        break;
        label55: int j = 0;
      }
    }
    return i;
  }

  public boolean setBCD_L(String paramString, int paramInt)
  {
    int j;
    int k;
    label157: 
    do
    {
      try
      {
        clear();
        if (paramInt <= 0)
          break label190;
        this.data = new byte[paramInt];
        Arrays.fill(this.data, -1);
        String str = paramString.toUpperCase();
        j = str.length();
        if (j <= 0)
          break label157;
        k = 0;
        int m = 0;
        continue;
        int n = str.charAt(k);
        int i1 = c.indexOf(n);
        if (i1 != -1)
        {
          if (k % 2 == 1)
          {
            int i3 = (byte)(i1 | 0xF0);
            byte[] arrayOfByte = this.data;
            arrayOfByte[m] = (i3 & arrayOfByte[m]);
            m++;
            break label184;
          }
          int i2 = (byte)(0xF | (i1 & 0xF) << 4);
          this.data[m] = i2;
        }
      }
      catch (di localdi)
      {
        i = 0;
        break;
      }
      i = 0;
      break;
      i = 0;
      break;
    }
    while ((k < j) && (k < paramInt << 1));
    label184: label190: for (int i = 1; ; i = 0)
    {
      return i;
      k++;
      break;
    }
  }

  public boolean setBCD_R(String paramString, int paramInt)
  {
    int i = 0;
    while (true)
    {
      int i4;
      int i1;
      try
      {
        clear();
        if (paramInt > 0)
        {
          this.data = new byte[paramInt];
          Arrays.fill(this.data, 0);
          String str = paramString.toUpperCase();
          j = str.length();
          if (j > 0)
          {
            if (j < this.data.length << 1)
              continue;
            j = this.data.length << 1;
            m = 0;
            continue;
            int i2 = str.charAt(n);
            int i3 = c.indexOf(i2);
            if (i3 != -1)
              continue;
            i4 = 15;
            break label187;
            this.data[(i1 >> 1)] = (byte)(i5 | this.data[(i1 >> 1)]);
            i1++;
            n++;
            continue;
            int k = this.data.length;
            m = (k << 1) - j;
            continue;
            i4 = (byte)(i3 & 0xF);
          }
        }
      }
      catch (di localdi)
      {
        int j;
        int m;
        break label185;
        int n = 0;
        i1 = m;
        if (n < j)
          continue;
        i = 1;
      }
      label185: return i;
      label187: if (i1 % 2 == 1)
      {
        i5 = i4 & 0xF;
        continue;
      }
      int i5 = 0xF0 & i4 << 4;
    }
  }

  public boolean setData(int paramInt, String paramString)
    throws FM_Exception
  {
    int i = 0;
    int j = (1 + paramString.length()) / 2;
    byte[] arrayOfByte;
    String str;
    int k;
    int m;
    int n;
    int i1;
    if (j > 0)
    {
      arrayOfByte = new byte[j];
      str = paramString.toUpperCase();
      k = 0;
      m = 0;
      n = 0;
      i1 = 0;
      if (k >= str.length())
      {
        if (i1 % 2 == 1)
        {
          int i6 = n + 1;
          arrayOfByte[n] = m;
          n = i6;
        }
        if (paramInt + n <= this.data.length)
          break label210;
        throw new FM_Exception(FM_Exception.getChars(72, 43, ".:r0>l&>90'e2t3?x& d"));
        label105: clear();
      }
    }
    while (true)
    {
      return i;
      label111: int i3;
      int i4 = (byte)(m | i3);
      int i5 = n + 1;
      arrayOfByte[n] = i4;
      n = i5;
      for (m = 0; ; m = (byte)(m | i3 << 4))
      {
        k++;
        break;
        int i2 = str.charAt(k);
        i3 = c.indexOf(i2);
        if (i3 == -1)
          break label105;
        i1++;
        if (i1 % 2 != 1)
          break label111;
      }
      label210: 
      do
      {
        this.data[(paramInt + i)] = arrayOfByte[i];
        i++;
      }
      while (i < n);
      i = 1;
    }
  }

  public boolean setData(int paramInt, byte[] paramArrayOfByte)
    throws FM_Exception
  {
    int i = 0;
    try
    {
      boolean bool = setData(paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
      i = bool;
      label16: return i;
    }
    catch (di localdi)
    {
      break label16;
    }
  }

  public boolean setData(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws FM_Exception
  {
    int i = 0;
    int j;
    int k;
    int n;
    if ((paramInt2 < 0) || (paramInt2 + paramInt3 > paramArrayOfByte.length))
    {
      throw new FM_Exception(subSequence("xny!5/zyyuw(177sc", 3));
      k = (byte)(0xF & paramArrayOfByte[(paramInt2 + j)] >> 4);
      if (k >= 10)
        break label200;
      byte[] arrayOfByte1 = this.data;
      int m = paramInt1 + j;
      arrayOfByte1[m] &= (0xF | k << 4);
      n = (byte)(0xF & paramArrayOfByte[(paramInt2 + j)]);
      if (n >= 10)
        break label214;
      byte[] arrayOfByte2 = this.data;
      int i1 = paramInt1 + j;
      arrayOfByte2[i1] &= (n | 0xF0);
      j++;
      label132: if (j < paramInt3)
        break label198;
    }
    while (true)
    {
      label139: i = 1;
      label142: return i;
      if (paramInt1 + paramInt3 > this.data.length)
        throw new FM_Exception(FM_Utils.copyValueOf(5, 28, "z-6sh}$px`!`>wa>(iv9"));
      label198: label200: label214: 
      do
      {
        clear();
        break label142;
        Arrays.fill(this.data, paramInt1, paramInt1 + paramInt3, -1);
        j = 0;
        break label132;
        break;
        if (k == 15)
          break label139;
        clear();
        break label142;
      }
      while (n != 15);
    }
  }

  public short shortValue()
  {
    long l;
    if (this.data.length > 0)
    {
      l = bcd2Dec();
      if ((l > 32767L) || (l < -32768L));
    }
    for (int i = (short)(int)l; ; i = 0)
      return i;
  }

  public String toHexString(char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; ; i++)
    {
      if (i >= this.data.length)
        return localStringBuilder.toString();
      int j = this.data[i];
      localStringBuilder.append(c.charAt((j & 0xF0) >> 4));
      localStringBuilder.append(c.charAt(j & 0xF));
      if (paramChar == 0)
        continue;
      localStringBuilder.append(paramChar);
    }
  }

  public boolean valueOf(String paramString)
  {
    int i = 0;
    clear();
    int j = (1 + paramString.length()) / 2;
    String str;
    int k;
    int m;
    int n;
    if (j > 0)
    {
      this.data = new byte[j];
      Arrays.fill(this.data, -1);
      str = paramString.toUpperCase();
      k = 0;
      m = 0;
      n = 0;
      if (k >= str.length())
        i = 1;
    }
    else
    {
      label62: return i;
    }
    int i1 = str.charAt(k);
    int i2 = c.indexOf(i1);
    if (i2 != -1)
    {
      if (k % 2 != 1)
        break label155;
      int i3 = (byte)(m & (i2 | 0xF0));
      byte[] arrayOfByte = this.data;
      int i4 = n + 1;
      arrayOfByte[n] = (i3 & arrayOfByte[n]);
      n = i4;
      m = 0;
    }
    while (true)
    {
      k++;
      break;
      clear();
      break label62;
      label155: m = (byte)(m | (0xF | i2 << 4));
      this.data[n] = m;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_CN
 * JD-Core Version:    0.6.0
 */