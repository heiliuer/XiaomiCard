package cn.com.fmsh.util;

import B;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import dh;
import java.io.PrintStream;
import java.util.Arrays;

public class FM_Bytes
{
  public byte[] data = new byte[0];

  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      b = BCCUtil.endsWith("a\"g$m.k(y:\004E\nO\bI", 338, 66);
      c = FM_Bytes.class.getName();
      label30: return;
    }
    catch (dh localdh)
    {
      break label30;
    }
  }

  public FM_Bytes()
  {
  }

  public FM_Bytes(String paramString)
  {
    valueof(paramString);
  }

  public static byte[] and(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      if (a != null)
        a.error(c, BCCUtil.endsWith("上连箏旴＀敦纄买:+$>", 5, 10));
    while (true)
    {
      return arrayOfByte;
      if (paramArrayOfByte1.length != paramArrayOfByte2.length)
      {
        if (a == null)
          continue;
        a.error(c, FM_Int.lastIndexOf(156, "丁迀箆旤？vlbr敨绝镥庽丑答"));
        continue;
      }
      arrayOfByte = (byte[])paramArrayOfByte1.clone();
      for (int i = 0; i < arrayOfByte.length; i++)
        arrayOfByte[i] = (byte)(arrayOfByte[i] & paramArrayOfByte2[i]);
    }
  }

  public static byte[] bytePatch4Des(byte[] paramArrayOfByte)
  {
    int i = 8;
    Object localObject;
    try
    {
      byte[] arrayOfByte1 = new byte[8];
      arrayOfByte1[0] = -128;
      if (paramArrayOfByte == null)
      {
        localObject = null;
      }
      else
      {
        int j = paramArrayOfByte.length;
        if (j % 8 != 0)
          i = 8 - j % 8;
        byte[] arrayOfByte2 = join(paramArrayOfByte, copyOf(arrayOfByte1, i));
        localObject = arrayOfByte2;
      }
    }
    catch (dh localdh)
    {
      localObject = null;
    }
    return localObject;
  }

  public static byte[] byteRemovePatch4Des(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      paramArrayOfByte = null;
    while (true)
    {
      return paramArrayOfByte;
      label8: int j;
      j--;
      try
      {
        while (j > -9 + paramArrayOfByte.length)
        {
          if (-128 != paramArrayOfByte[j])
            break label8;
          paramArrayOfByte = Arrays.copyOf(paramArrayOfByte, j);
          break;
          int i = paramArrayOfByte.length;
          j = i - 1;
        }
      }
      catch (dh localdh)
      {
        paramArrayOfByte = null;
      }
    }
  }

  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    if (paramArrayOfByte == null);
    while (true)
    {
      return localObject;
      try
      {
        String str = bytesToHexString(paramArrayOfByte, "", "");
        localObject = str;
      }
      catch (dh localdh)
      {
      }
    }
  }

  public static String bytesToHexString(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    String str1;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; ; i++)
      {
        if (i >= paramArrayOfByte.length)
        {
          str1 = localStringBuilder.toString();
          break;
        }
        String str2 = Integer.toHexString(0xFF & paramArrayOfByte[i]);
        if (str2.length() == 1)
          str2 = '0' + str2;
        if ((paramString1 != null) && (!"".equals(paramString1)))
          localStringBuilder.append(paramString1);
        localStringBuilder.append(str2.toUpperCase());
        if ((paramString2 == null) || ("".equals(paramString2)))
          continue;
        localStringBuilder.append(paramString2);
      }
    }
    catch (dh localdh)
    {
      str1 = null;
    }
    return str1;
  }

  public static int bytesToInt(byte[] paramArrayOfByte)
  {
    try
    {
      int j = bytesToInt(paramArrayOfByte, true);
      i = j;
      return i;
    }
    catch (dh localdh)
    {
      while (true)
        int i = 0;
    }
  }

  public static int bytesToInt(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int i = -1;
    int j = 1;
    if (paramArrayOfByte == null)
    {
      if (a != null)
        a.error(c, Util4Java.toString("孖苝攭纟輵扇匔辈剧攻攽施ｅ攷纁乹/*17", 2, 126));
      while (true)
      {
        label35: return i;
        while (paramArrayOfByte.length != j)
        {
          if (!paramBoolean)
            break label145;
          i = paramArrayOfByte[0];
          while (j < paramArrayOfByte.length)
          {
            i = i << 8 | 0xFF & paramArrayOfByte[j];
            j++;
          }
          break;
        }
        i = 0xFF & paramArrayOfByte[0];
      }
    }
    label145: label161: 
    while (true)
    {
      int k;
      i = i << 8 | 0xFF & paramArrayOfByte[k];
      k--;
      while (true)
      {
        if (k >= 0)
          break label161;
        break;
        if (paramArrayOfByte.length >= j)
          break label35;
        if (a == null)
          break;
        a.error(c, FM_CN.subSequence("孓芑攲纕輬扟匟迖刪敿敪斿ｔ攷纒镺庲丹\"", 278));
        break;
        i = paramArrayOfByte[(-1 + paramArrayOfByte.length)];
        k = -2 + paramArrayOfByte.length;
      }
    }
  }

  public static long bytesToLong(byte[] paramArrayOfByte)
  {
    try
    {
      long l2 = bytesToLong(paramArrayOfByte, true);
      l1 = l2;
      return l1;
    }
    catch (dh localdh)
    {
      while (true)
        long l1 = 0L;
    }
  }

  public static long bytesToLong(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    long l1;
    if (paramArrayOfByte == null)
    {
      if (a != null)
        a.error(c, FM_Int.lastIndexOf(3, "嬁苕攨纝輶手匝辆剨攫数旷．敳绀丿hrde"));
      l1 = -1L;
    }
    while (true)
    {
      return l1;
      if (paramArrayOfByte.length < 1)
      {
        if (a != null)
          a.error(c, FM_Utils.copyValueOf(4, 91, "存苈攵组輷扆卐迗剱收敭斮｟敾绍锻庹丠e"));
        l1 = -1L;
        continue;
      }
      if (paramArrayOfByte.length == 1)
      {
        l1 = 0xFF & paramArrayOfByte[0];
        continue;
      }
      do
      {
        long l3 = paramArrayOfByte[(-1 + paramArrayOfByte.length)];
        int j = -2 + paramArrayOfByte.length;
        l1 = l3;
        int k = j;
        while (k >= 0)
        {
          long l4 = l1 << 8 | 0xFF & paramArrayOfByte[k];
          k--;
          l1 = l4;
        }
        break;
      }
      while (!paramBoolean);
      l1 = 0xFF & paramArrayOfByte[0];
      int i = 1;
      while (i < paramArrayOfByte.length)
      {
        long l2 = l1 << 8 | 0xFF & paramArrayOfByte[i];
        i++;
        l1 = l2;
      }
    }
  }

  public static byte[] concatArrays(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1)
  {
    int i = paramArrayOfByte.length;
    int j = paramArrayOfByte1.length;
    int k = i;
    for (int m = 0; ; m++)
    {
      if (m >= j)
      {
        byte[] arrayOfByte1 = Arrays.copyOf(paramArrayOfByte, k);
        int n = paramArrayOfByte.length;
        int i1 = paramArrayOfByte1.length;
        int i2 = n;
        for (int i3 = 0; ; i3++)
        {
          if (i3 >= i1)
            return arrayOfByte1;
          byte[] arrayOfByte2 = paramArrayOfByte1[i3];
          System.arraycopy(arrayOfByte2, 0, arrayOfByte1, i2, arrayOfByte2.length);
          i2 += arrayOfByte2.length;
        }
      }
      k += paramArrayOfByte1[m].length;
    }
  }

  public static byte[] copyOf(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    if (paramArrayOfByte == null)
      throw new NullPointerException(Util4Java.toString("#b%hl<1h?}F#)d6*#$$!% s%", 4, 42));
    byte[] arrayOfByte = new byte[paramInt];
    if (paramArrayOfByte.length < paramInt);
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        while (true)
        {
          return arrayOfByte;
          do
          {
            arrayOfByte[i] = paramArrayOfByte[i];
            i++;
          }
          while (i < paramInt);
        }
      arrayOfByte[i] = paramArrayOfByte[i];
      i++;
    }
  }

  public static byte[] copyOfRange(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null);
    int i;
    byte[] arrayOfByte2;
    int j;
    byte[] arrayOfByte1;
    try
    {
      throw new NullPointerException(startsWith("{z=`$t9p'%^+a,~2;<|imh;}", 250, 58));
      while (true)
      {
        if ((paramArrayOfByte.length < paramInt2) || (paramArrayOfByte.length < paramInt1))
          throw new IllegalArgumentException(" ");
        i = paramInt2 - paramInt1;
        arrayOfByte2 = new byte[i];
        j = 0;
        break;
        if (paramInt2 - paramInt1 > 0)
          continue;
        throw new IllegalArgumentException(FM_Long.concat("zcbtkJ", 2) + paramInt1 + BCCUtil.endsWith("^9`H", 4, 4) + paramInt2 + "]");
        arrayOfByte2[j] = paramArrayOfByte[(paramInt1 + j)];
        j++;
      }
    }
    catch (dh localdh)
    {
      arrayOfByte1 = null;
    }
    while (true)
    {
      return arrayOfByte1;
      if (j < i)
        break;
      arrayOfByte1 = arrayOfByte2;
    }
  }

  public static byte getByteParity(byte[] paramArrayOfByte)
  {
    int i = 1;
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length == 0)
          break label59;
        while (true)
        {
          j = (byte)(j ^ paramArrayOfByte[i]);
          i++;
          while (i >= paramArrayOfByte.length)
          {
            break label62;
            if (paramArrayOfByte.length == i)
            {
              j = paramArrayOfByte[0];
              break label62;
            }
            j = paramArrayOfByte[0];
          }
        }
      }
      catch (dh localdh)
      {
        j = 0;
      }
    label59: int j = -1;
    label62: return j;
  }

  public static byte[] hexStringToBytes(String paramString)
  {
    int i = 0;
    Object localObject;
    label18: String str;
    int k;
    int i4;
    int i1;
    int m;
    label65: label68: int n;
    byte[] arrayOfByte;
    int j;
    if ((paramString == null) || (paramString.length() < 1))
      for (localObject = null; ; localObject = arrayOfByte)
      {
        return localObject;
        int i3 = str.charAt(k);
        i4 = b.indexOf(i3);
        if (i4 != -1)
        {
          i1++;
          if (i1 % 2 != 1)
            break;
          m = (byte)(m | i4 << 4);
        }
        k++;
        if (k < str.length())
          break label202;
        if (i1 % 2 == 1)
        {
          int i2 = n + 1;
          arrayOfByte[n] = m;
          n = i2;
        }
        if (n != j)
          break label211;
      }
    label202: label211: label229: 
    while (true)
    {
      localObject[i] = arrayOfByte[i];
      i++;
      while (true)
      {
        if (i < n)
          break label229;
        break;
        j = (1 + paramString.length()) / 2;
        if (j > 0)
        {
          arrayOfByte = new byte[j];
          str = paramString.toUpperCase();
          k = 0;
          m = 0;
          n = 0;
          i1 = 0;
          break label68;
          int i5 = (byte)(m | i4);
          int i6 = n + 1;
          arrayOfByte[n] = i5;
          n = i6;
          m = 0;
          break label65;
          break label18;
        }
        localObject = new byte[0];
        break;
        if (n == 0)
        {
          localObject = new byte[0];
          break;
        }
        localObject = new byte[n];
      }
    }
  }

  public static byte[] intToBytes(int paramInt1, int paramInt2)
  {
    try
    {
      byte[] arrayOfByte2 = intToBytes(paramInt1, paramInt2, true);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (dh localdh)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static byte[] intToBytes(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt2 < 1)
      if (a != null)
        a.error(c, FM_Int.lastIndexOf(5, "匙辂剬22)攪支转我孕芁整绁旰＋挏宓皎敻终镲庨靑歳"));
    byte[] arrayOfByte;
    int j;
    label56: for (Object localObject = null; ; localObject = arrayOfByte)
    {
      return localObject;
      arrayOfByte = new byte[paramInt2];
      if (!paramBoolean)
        break label121;
      j = -1 + arrayOfByte.length;
      if (j > -1)
        break;
    }
    label121: label125: 
    while (true)
    {
      int i;
      arrayOfByte[i] = Integer.valueOf(paramInt1 & 0xFF).byteValue();
      paramInt1 >>= 8;
      i++;
      while (true)
      {
        if (i < arrayOfByte.length)
          break label125;
        break label56;
        arrayOfByte[j] = Integer.valueOf(paramInt1 & 0xFF).byteValue();
        paramInt1 >>= 8;
        j--;
        break;
        i = 0;
      }
    }
  }

  public static boolean isEnd(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null));
    while (true)
    {
      return i;
      int n;
      label72: 
      do
      {
        try
        {
          int j;
          int k;
          do
          {
            int m = paramArrayOfByte1.length;
            n = -1 + paramArrayOfByte2.length;
            break label72;
            int i1 = paramArrayOfByte2[n];
            m--;
            if (i1 != paramArrayOfByte1[m])
              break;
            n--;
            break label72;
            j = paramArrayOfByte1.length;
            k = paramArrayOfByte2.length;
          }
          while (j >= k);
        }
        catch (dh localdh)
        {
        }
        break;
      }
      while (n > 0);
      i = 1;
    }
  }

  public static boolean isEnd9000(byte[] paramArrayOfByte)
  {
    int i = 0;
    int k;
    try
    {
      byte[] arrayOfByte = new byte[2];
      arrayOfByte[0] = -112;
      if (paramArrayOfByte != null)
      {
        if (arrayOfByte == null)
          break label72;
        while (true)
        {
          int j = paramArrayOfByte.length;
          k = -1 + arrayOfByte.length;
          break label77;
          if (paramArrayOfByte.length >= arrayOfByte.length)
            continue;
          break;
          int m = arrayOfByte[k];
          j--;
          int n = paramArrayOfByte[j];
          if (m == n);
        }
      }
    }
    catch (dh localdh)
    {
    }
    while (true)
    {
      label72: return i;
      k--;
      label77: if (k > 0)
        break;
      i = 1;
    }
  }

  public static boolean isPatch4Des(byte[] paramArrayOfByte)
  {
    int i;
    if (paramArrayOfByte == null)
      i = 0;
    while (true)
    {
      return i;
      label8: int k;
      k--;
      try
      {
        while (true)
        {
          if (k <= -9 + paramArrayOfByte.length)
          {
            i = 0;
            break;
          }
          if (-128 != paramArrayOfByte[k])
            break label8;
          i = 1;
          break;
          int j = paramArrayOfByte.length;
          k = j - 1;
        }
      }
      catch (dh localdh)
      {
        i = 0;
      }
    }
  }

  public static byte[] join(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null));
    while (true)
    {
      int j;
      int k;
      try
      {
        if (a != null)
        {
          a.error(c, FM_Int.lastIndexOf(2, "嬂苔攧纜呑帬断ｐ攭纚乥ntno"));
          break label101;
          j = paramArrayOfByte1.length;
          byte[] arrayOfByte = new byte[paramArrayOfByte1.length + paramArrayOfByte2.length];
          k = 0;
          break label103;
          if (i < paramArrayOfByte2.length)
            continue;
          paramArrayOfByte1 = arrayOfByte;
          break label101;
          arrayOfByte[(j + i)] = paramArrayOfByte2[i];
          i++;
          continue;
          arrayOfByte[k] = paramArrayOfByte1[k];
          k++;
        }
      }
      catch (dh localdh)
      {
        paramArrayOfByte1 = null;
      }
      label101: return paramArrayOfByte1;
      label103: if (k < j)
        continue;
    }
  }

  public static byte[] longToAsciiBytes(long paramLong)
  {
    char[] arrayOfChar = paramLong.toCharArray();
    int i = arrayOfChar.length;
    byte[] arrayOfByte = new byte[i];
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return arrayOfByte;
      arrayOfByte[j] = (byte)(48 + Integer.parseInt(arrayOfChar[j]));
    }
  }

  public static byte[] longToBytes(long paramLong, int paramInt)
  {
    try
    {
      byte[] arrayOfByte2 = longToBytes(paramLong, paramInt, true);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (dh localdh)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static byte[] longToBytes(long paramLong, int paramInt, boolean paramBoolean)
  {
    if (paramInt < 1);
    int j;
    byte[] arrayOfByte2;
    try
    {
      if (a != null)
      {
        a.error(c, CRCUtil.valueOf(3, "匘辑剭 r`x敤敱輾打嬃苇敦练旮％捝寑盘攽绚镰度靏歡"));
        break label129;
        while (true)
          if (j < arrayOfByte2.length)
          {
            arrayOfByte2[j] = Long.valueOf(paramLong & 0xFF).byteValue();
            paramLong >>= 8;
            j++;
            continue;
            arrayOfByte2 = new byte[paramInt];
            if (!paramBoolean)
              break;
            for (int i = -1 + arrayOfByte2.length; i > -1; i--)
            {
              arrayOfByte2[i] = Long.valueOf(paramLong & 0xFF).byteValue();
              paramLong >>= 8;
            }
          }
      }
    }
    catch (dh localdh)
    {
      arrayOfByte1 = null;
    }
    label129: for (byte[] arrayOfByte1 = null; ; arrayOfByte1 = arrayOfByte2)
    {
      return arrayOfByte1;
      j = 0;
      break;
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte = hexStringToBytes(CRCUtil.valueOf(1, "nx\032\b/O[L\032\025we\001&F#5k\017o\b)H.>4\024v\022\002&0S:i{\032\016+K/TGkvev\"7(1i\bd\016Z;B\"Ag}a\004'M)Hi|\034v\\(DP0\027|n\006/0-;\036|j~uUCWBc\005\025|(2.N\034\034dr$7-<c~ox[:)>o~uh\006&6$2\021\fh,<%<lasjqW4W4\036\bo}^N+M7cw\025uS5\"1iyl{-;YV8c\002e\006TB\\=\036\tk|Z<1SGf\005\027\002%I\\N\036|k]T3$2\022\006\024)?\\2n\016o\013sP2Q<gub\n(9*M\036xniy!:$3a\003\034p(8$<\034|\004f\006&=$@"));
      System.out.println(bytesToHexString(arrayOfByte, FM_Utils.copyValueOf(112, 39, "kz"), ","));
      label34: return;
    }
    catch (dh localdh)
    {
      break label34;
    }
  }

  public static byte[] not(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    if (paramArrayOfByte == null)
    {
      if (a != null)
        a.error(c, BCCUtil.endsWith("叅叐旱＝敫纁乵76!;", 276, 10));
      arrayOfByte = null;
    }
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = (byte[])paramArrayOfByte.clone();
      for (int i = 0; i < arrayOfByte.length; i++)
        arrayOfByte[i] = (byte)(0xFFFFFFFF ^ paramArrayOfByte[i]);
    }
  }

  public static byte[] or(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      if (a != null)
        a.error(c, Util4Java.toString("戒辀箋斾８数纈丢*e0$", 5, 76));
    while (true)
    {
      return arrayOfByte;
      if (paramArrayOfByte1.length == paramArrayOfByte2.length)
        break;
      if (a == null)
        continue;
      a.error(c, FM_Utils.copyValueOf(5, 41, "戆迉篕旽８?{=敱绎锬建丈笇"));
    }
    label108: 
    while (true)
    {
      int i;
      arrayOfByte[i] = (byte)(arrayOfByte[i] | paramArrayOfByte2[i]);
      i++;
      while (true)
      {
        if (i < arrayOfByte.length)
          break label108;
        break;
        arrayOfByte = (byte[])paramArrayOfByte1.clone();
        i = 0;
      }
    }
  }

  public static byte[] patch(byte[] paramArrayOfByte, int paramInt, byte paramByte)
  {
    int i = 0;
    Object localObject;
    if ((paramArrayOfByte == null) || (paramInt <= 0))
      localObject = null;
    int j;
    label38: byte[] arrayOfByte;
    int k;
    while (true)
    {
      return localObject;
      j = paramArrayOfByte.length;
      if (j < paramInt)
        break;
      localObject = (byte[])paramArrayOfByte.clone();
      continue;
      arrayOfByte[k] = paramArrayOfByte[k];
      k++;
      label50: if (k < j)
        break label81;
    }
    while (true)
    {
      if (i >= paramInt - j)
      {
        localObject = arrayOfByte;
        break;
        arrayOfByte = new byte[paramInt];
        k = 0;
        break label50;
        label81: break label38;
      }
      arrayOfByte[(j + i)] = paramByte;
      i++;
    }
  }

  public static void reverse(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    byte[] arrayOfByte = copyOf(paramArrayOfByte, i);
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      paramArrayOfByte[j] = arrayOfByte[(-1 + (i - j))];
    }
  }

  public static String startsWith(String paramString, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt1 + 1;
    try
    {
      char[] arrayOfChar = paramString.toCharArray();
      int k = arrayOfChar.length;
      while (i != k)
      {
        int m = arrayOfChar[i] ^ j & 0x5F;
        int n = j + paramInt2;
        int i1 = i + 1;
        arrayOfChar[i] = (char)m;
        i = i1;
        j = n;
      }
      String str2 = String.valueOf(arrayOfChar, 0, k).intern();
      str1 = str2;
      return str1;
    }
    catch (dh localdh)
    {
      while (true)
        String str1 = null;
    }
  }

  public static boolean tlv(byte[] paramArrayOfByte1, byte paramByte1, byte paramByte2, byte[] paramArrayOfByte2)
  {
    byte b1 = 0;
    if ((paramByte1 != paramArrayOfByte1[0]) || (paramByte2 != paramArrayOfByte1[1]) || (paramArrayOfByte1.length != paramByte2 + 2) || (paramArrayOfByte2.length != paramByte2));
    while (true)
    {
      return b1;
      do
      {
        paramArrayOfByte2[b1] = paramArrayOfByte1[(b1 + 2)];
        b1 += 1;
      }
      while (b1 < paramByte2);
      b1 = 1;
    }
  }

  public static byte[] xor(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      if (a != null)
        a.error(c, FM_Int.lastIndexOf(2, "彗所斡ｔ攩纞乡2(23"));
    while (true)
    {
      return arrayOfByte;
      if (paramArrayOfByte1.length == paramArrayOfByte2.length)
        break;
      if (a == null)
        continue;
      a.error(c, BCCUtil.endsWith("弝扔斳＄i7%q敧纞锢度乎笏", 160, 35));
    }
    label108: 
    while (true)
    {
      int i;
      arrayOfByte[i] = (byte)(arrayOfByte[i] ^ paramArrayOfByte2[i]);
      i++;
      while (true)
      {
        if (i < arrayOfByte.length)
          break label108;
        break;
        arrayOfByte = (byte[])paramArrayOfByte1.clone();
        i = 0;
      }
    }
  }

  public void clear()
  {
    try
    {
      if (this.data != null)
      {
        if (this.data.length > 0)
          this.data = new byte[0];
      }
      else
        this.data = new byte[0];
    }
    catch (dh localdh)
    {
    }
  }

  public void copy(int paramInt)
    throws FM_Exception
  {
    if (paramInt >= 0);
    try
    {
      if (paramInt < this.data.length)
        throw new FM_Exception(BCCUtil.endsWith("k=w+s3k%<w\"tx})x:c;i", 5, 71));
      this.data = copyOf(this.data, paramInt);
    }
    catch (dh localdh)
    {
    }
  }

  public int hashCode()
  {
    try
    {
      int j = Arrays.hashCode(this.data);
      i = j;
      return i;
    }
    catch (dh localdh)
    {
      while (true)
        int i = 0;
    }
  }

  public int intValue()
    throws FM_Exception
  {
    int i = 0;
    try
    {
      int j = intValue(0, false);
      i = j;
      label11: return i;
    }
    catch (dh localdh)
    {
      break label11;
    }
  }

  public int intValue(int paramInt, boolean paramBoolean)
    throws FM_Exception
  {
    if (paramInt + 4 > this.data.length)
      throw new FM_Exception(FM_Utils.copyValueOf(6, 60, "~;l7ri<eqy!`a6`?i(w8"));
    while (true)
    {
      int j;
      int k = i << 8 | 0xFF & this.data[(paramInt + j)];
      j++;
      int i = k;
      while (j >= 4)
      {
        return i;
        do
        {
          i = this.data[(paramInt + 3)];
          int m = 2;
          while (m >= 0)
          {
            int n = i << 8 | 0xFF & this.data[(paramInt + m)];
            m--;
            i = n;
          }
          break;
        }
        while (!paramBoolean);
        i = this.data[paramInt];
        j = 1;
      }
    }
  }

  public int intValue(boolean paramBoolean)
    throws FM_Exception
  {
    int i = 0;
    try
    {
      int j = intValue(0, paramBoolean);
      i = j;
      label13: return i;
    }
    catch (dh localdh)
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
    catch (dh localdh)
    {
      while (true)
        int i = 0;
    }
  }

  public long longValue()
    throws FM_Exception
  {
    try
    {
      long l2 = longValue(0, false);
      l1 = l2;
      return l1;
    }
    catch (dh localdh)
    {
      while (true)
        long l1 = 0L;
    }
  }

  public long longValue(int paramInt, boolean paramBoolean)
    throws FM_Exception
  {
    if (paramInt + 8 > this.data.length)
      throw new FM_Exception(BCCUtil.endsWith("zut->/,g%gi*})vr{g0-4", 310, 110));
    long l;
    if (paramBoolean)
      l = this.data[paramInt];
    for (int j = 1; ; j++)
    {
      if (j >= 8)
      {
        return l;
        label102: 
        while (true)
        {
          int i;
          l = l << 8 | 0xFF & this.data[(paramInt + i)];
          i--;
          while (true)
          {
            if (i >= 0)
              break label102;
            break;
            l = this.data[(paramInt + 7)];
            i = 6;
          }
        }
      }
      l = l << 8 | 0xFF & this.data[(paramInt + j)];
    }
  }

  public long longValue(boolean paramBoolean)
    throws FM_Exception
  {
    try
    {
      long l2 = longValue(0, paramBoolean);
      l1 = l2;
      return l1;
    }
    catch (dh localdh)
    {
      while (true)
        long l1 = 0L;
    }
  }

  public int preplace(int paramInt)
  {
    int i = 0;
    try
    {
      int j = preplace(paramInt, 0);
      i = j;
      label13: return i;
    }
    catch (dh localdh)
    {
      break label13;
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
    catch (dh localdh)
    {
      while (true)
      {
        break;
        label55: int j = 0;
      }
    }
    return i;
  }

  public boolean setData(int paramInt, String paramString)
    throws FM_Exception
  {
    int i = 0;
    int j = (1 + paramString.length()) / 2;
    if (j > 0)
    {
      byte[] arrayOfByte = new byte[j];
      String str = paramString.toUpperCase();
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      if (k >= str.length())
      {
        if (i1 % 2 == 1)
        {
          int i6 = n + 1;
          arrayOfByte[n] = m;
          n = i6;
        }
        if (paramInt + n > this.data.length)
          throw new FM_Exception(Util4Java.toString(".h>*ff*<)r+%=88a**z|", 194, 93));
      }
      else
      {
        int i2 = str.charAt(k);
        int i3 = b.indexOf(i2);
        if (i3 != -1)
        {
          i1 += 1;
          if (i1 % 2 != 1)
            break label163;
        }
        for (m = (byte)(m | i3 << 4); ; m = 0)
        {
          k++;
          break;
          label163: int i4 = (byte)(m | i3);
          int i5 = n + 1;
          arrayOfByte[n] = i4;
          n = i5;
        }
      }
      do
      {
        this.data[(paramInt + i)] = arrayOfByte[i];
        i++;
      }
      while (i < n);
    }
    return true;
  }

  public boolean setData(int paramInt, byte[] paramArrayOfByte)
    throws FM_Exception
  {
    if (paramInt + paramArrayOfByte.length > this.data.length)
      throw new FM_Exception(FM_CN.subSequence("~vjl>(.*)lose6,'ot~|d", 3));
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfByte.length)
        return true;
      this.data[(paramInt + i)] = paramArrayOfByte[i];
    }
  }

  public boolean setData(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    throws FM_Exception
  {
    if ((paramInt2 < 0) || (paramInt2 + paramInt3 > paramArrayOfByte.length))
      throw new FM_Exception(FM_Long.concat("3kfzjxxg\"<:23l`zxf", 2));
    for (int i = 0; ; i++)
    {
      if (i >= paramInt3)
      {
        return true;
        if (paramInt1 + paramInt3 <= this.data.length)
          break;
        throw new FM_Exception(FM_Long.concat("1?1-yae{65$2b/7v`}e}c", 230));
      }
      this.data[(paramInt1 + i)] = paramArrayOfByte[(paramInt2 + i)];
    }
  }

  public short shortValue()
    throws FM_Exception
  {
    int i = 0;
    try
    {
      int j = shortValue(0, false);
      i = j;
      label11: return i;
    }
    catch (dh localdh)
    {
      break label11;
    }
  }

  public short shortValue(int paramInt, boolean paramBoolean)
    throws FM_Exception
  {
    int i = paramInt + 2;
    int j;
    try
    {
      if (i > this.data.length)
        throw new FM_Exception(FM_Utils.copyValueOf(2, 81, "b(jr\"v6452myh49s*~b8"));
      if (paramBoolean)
      {
        j = (short)(this.data[paramInt] << 8) | 0xFF & this.data[(paramInt + 1)];
      }
      else
      {
        int k = (short)(this.data[(paramInt + 1)] << 8);
        int m = this.data[paramInt];
        j = k | m & 0xFF;
      }
    }
    catch (dh localdh)
    {
      j = 0;
    }
    return j;
  }

  public short shortValue(boolean paramBoolean)
    throws FM_Exception
  {
    int i = 0;
    try
    {
      int j = shortValue(0, paramBoolean);
      i = j;
      label13: return i;
    }
    catch (dh localdh)
    {
      break label13;
    }
  }

  public String toHexString(char paramChar)
  {
    String str;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; ; i++)
      {
        if (i >= this.data.length)
        {
          str = localStringBuilder.toString();
          break;
        }
        int j = this.data[i];
        localStringBuilder.append(b.charAt((j & 0xF0) >> 4));
        localStringBuilder.append(b.charAt(j & 0xF));
        if (paramChar == 0)
          continue;
        localStringBuilder.append(paramChar);
      }
    }
    catch (dh localdh)
    {
      str = null;
    }
    return str;
  }

  public String toString()
  {
    try
    {
      String str2 = toHexString(' ');
      str1 = str2;
      return str1;
    }
    catch (dh localdh)
    {
      while (true)
        String str1 = null;
    }
  }

  public boolean valueof(String paramString)
  {
    int i = 0;
    try
    {
      int j = (1 + paramString.length()) / 2;
      byte[] arrayOfByte;
      String str;
      int i1;
      if (j > 0)
      {
        arrayOfByte = new byte[j];
        str = paramString.toUpperCase();
        k = 0;
        m = 0;
        n = 0;
        i1 = 0;
        if (k < str.length())
          break label166;
        if (i1 % 2 == 1)
        {
          int i7 = n + 1;
          arrayOfByte[n] = m;
          n = i7;
        }
        if (n == j)
        {
          this.data = arrayOfByte;
          break label233;
        }
        if (n != 0)
          break label219;
        this.data = new byte[0];
        break label233;
      }
      int i3;
      label166: 
      do
      {
        int i4 = (byte)(m | i3);
        int i5 = n + 1;
        arrayOfByte[n] = i4;
        n = i5;
        m = 0;
        break label241;
        this.data = new byte[0];
        break;
        this.data[i6] = arrayOfByte[i6];
        i6++;
        break label247;
        int i2 = str.charAt(k);
        i3 = b.indexOf(i2);
        if (i3 == -1)
          break label241;
        i1 += 1;
      }
      while (i1 % 2 != 1);
      int m = (byte)(m | i3 << 4);
      break label241;
      label219: this.data = new byte[n];
      i6 = 0;
      break label247;
      label233: i = 1;
      return i;
    }
    catch (dh localdh)
    {
      while (true)
      {
        int k;
        int n;
        int i6;
        continue;
        label241: k++;
        continue;
        label247: if (i6 < n)
          continue;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Bytes
 * JD-Core Version:    0.6.0
 */