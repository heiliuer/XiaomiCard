package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import dq;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (dq localdq)
    {
      break label9;
    }
  }

  public static byte[] decrypt4des(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, FM_Exception.getChars(2, 52, "\037J\020"));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(FM_Utils.copyValueOf(2, 99, "I\025��9\\\037\035mKg\033/up>4z"));
      localCipher.init(2, localSecretKeySpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
  }

  public static byte[] decrypt4des3(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      a.error(DES.class.getName(), BCCUtil.endsWith("导敦挩迃蠅iO\031^觽安时｝伢其盀^\003\016戞聜徏劻宊皙放挱个/g/8", 6, 49));
    while (true)
    {
      return arrayOfByte1;
      if (paramArrayOfByte1.length % 8 != 0)
        a.error(DES.class.getName(), FM_Exception.getChars(226, 98, "客敭捱迚蠏vCL\030覮寉旧｟併兲皝\020\030F皅敳挫锸庯丆呅沚"));
      byte[] arrayOfByte2 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
      arrayOfByte1 = decrypt4des(arrayOfByte2, encrypt4des(FM_Bytes.copyOfRange(paramArrayOfByte1, 8, paramArrayOfByte1.length), decrypt4des(arrayOfByte2, paramArrayOfByte2)));
    }
  }

  public static byte[] decrypt4des3CBC(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
    {
      a.error(DES.class.getName(), BCCUtil.endsWith("寺敱挱辆蠗j\023\020��覲安斻ｇ佩儢盁\b\004\006手聞応勷宓盗攡挡乷%<+)", 4, 126));
      arrayOfByte = null;
      return arrayOfByte;
    }
    byte[][] arrayOfByte1;
    int j;
    label163: 
    while (true)
    {
      int m;
      arrayOfByte = FM_Bytes.join(arrayOfByte, FM_Bytes.xor(decrypt4des3(paramArrayOfByte1, arrayOfByte1[m]), arrayOfByte1[(m - 1)]));
      m++;
      while (true)
      {
        if (m < i)
          break label163;
        break;
        if (paramArrayOfByte1.length % 8 != 0)
          a.error(DES.class.getName(), FM_Int.lastIndexOf(290, "寬敦捹迃衕)_YN觽寙时－伢兦皀NC^皌敹捤镴庪一吆泚"));
        int i = paramArrayOfByte2.length / 8;
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = i;
        arrayOfInt[1] = 8;
        arrayOfByte1 = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
        j = 0;
        if (j < i)
          break label198;
        arrayOfByte = FM_Bytes.xor(decrypt4des3(paramArrayOfByte1, arrayOfByte1[0]), paramArrayOfByte3);
        m = 1;
      }
    }
    label198: label202: 
    while (true)
    {
      int k;
      arrayOfByte1[j][k] = paramArrayOfByte2[(k + (j << 3))];
      k++;
      while (true)
      {
        if (k < 8)
          break label202;
        j++;
        break;
        k = 0;
      }
    }
  }

  public static byte[] decrypt4desCBC(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, BCCUtil.endsWith("@\006Q", 5, 63));
    Object localObject = null;
    String str = FM_Long.concat("\030BA2KQ]f\0320\0324dook", 4);
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(paramArrayOfByte3);
    try
    {
      Cipher localCipher = Cipher.getInstance(str);
      localCipher.init(2, localSecretKeySpec, localIvParameterSpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidAlgorithmParameterException));
    }
  }

  public static byte[] decrypt4desPadding(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, FM_CN.subSequence("VDC", 4));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(BCCUtil.endsWith("ER\036,\034L\007tA&y7`q2", 2, 54));
      localCipher.init(2, localSecretKeySpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
  }

  public static byte[] encrypt4des(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 8))
      paramArrayOfByte1 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, FM_Utils.copyValueOf(1, 43, "HR\021"));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(FM_Exception.getChars(3, 96, "\030\031O3\031\037^3\0223L}88ur;"));
      localCipher.init(1, localSecretKeySpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
  }

  public static byte[] encrypt4des3(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Object localObject = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null));
    try
    {
      a.error(DES.class.getName(), FM_CN.subSequence("宯敵捺还衞r\024\032\035勽寊旭＆伹儭盓\r\020]戅耇徔勠安盚攽挲丱t|t+", 232));
      break label103;
      if (paramArrayOfByte1.length % 8 != 0)
        a.error(DES.class.getName(), FM_Exception.getChars(116, 10, "宴攧捯运衙,MVN勧宗断ｉ佯儼皇FRX皏敥捱锶廵乐呏沄"));
      byte[] arrayOfByte1 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
      byte[] arrayOfByte2 = encrypt4des(arrayOfByte1, decrypt4des(FM_Bytes.copyOfRange(paramArrayOfByte1, 8, paramArrayOfByte1.length), encrypt4des(arrayOfByte1, paramArrayOfByte2)));
      localObject = arrayOfByte2;
    }
    catch (dq localdq)
    {
    }
    label103: return localObject;
  }

  public static byte[] encrypt4des3CBC(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    Object localObject = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      a.error(DES.class.getName(), BCCUtil.endsWith("宦攩捽迖蠋r\037P\\勩宅斫；伱儮盁\024\\J扛聂径劻寓盋改捭丧y$'i", 352, 26));
    byte[][] arrayOfByte;
    int j;
    while (true)
    {
      return localObject;
      if (paramArrayOfByte1.length % 8 != 0)
        a.error(DES.class.getName(), CRCUtil.valueOf(3, "宠攺挵辗衑=[UR勲宅斢ｉ伶兢皜B\037\022盘攽捰镰度东吊沆"));
      int i = paramArrayOfByte2.length / 8;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = i;
      arrayOfInt[1] = 8;
      arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
      j = 0;
      if (j < i)
        break;
      new byte[8];
      byte[] arrayOfByte1 = encrypt4des3(paramArrayOfByte1, FM_Bytes.xor(arrayOfByte[0], paramArrayOfByte3));
      int m = 1;
      localObject = arrayOfByte1;
      while (m < i)
      {
        arrayOfByte1 = encrypt4des3(paramArrayOfByte1, FM_Bytes.xor(arrayOfByte1, arrayOfByte[m]));
        localObject = FM_Bytes.join(localObject, arrayOfByte1);
        m++;
      }
    }
    for (int k = 0; ; k++)
    {
      if (k >= 8)
      {
        j++;
        break;
      }
      arrayOfByte[j][k] = paramArrayOfByte2[(k + (j << 3))];
    }
  }

  public static byte[] encrypt4des3Padding(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null))
      a.error(DES.class.getName(), FM_Long.concat("客敶捿过衋!Y\r��勾宏斢ｓ伪兰的@S\030扚聒忇勭实皇敾捷举ao)<", 3));
    while (true)
    {
      return arrayOfByte1;
      if (paramArrayOfByte1.length % 8 != 0)
        a.error(DES.class.getName(), FM_Utils.copyValueOf(1, 7, "寵散捴迚衄<RX\027勫宔斯ｌ佧儫监\027FS皕敨捱镹庫丙吓沗"));
      byte[] arrayOfByte2 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
      arrayOfByte1 = encrypt4desPadding(arrayOfByte2, decrypt4desPadding(FM_Bytes.copyOfRange(paramArrayOfByte1, 8, paramArrayOfByte1.length), encrypt4desPadding(arrayOfByte2, paramArrayOfByte2)));
    }
  }

  public static byte[] encrypt4desCBC(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 8))
      paramArrayOfByte1 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
    String str = FM_Exception.getChars(2, 73, "\037\001^y\\\nRu\rc\005#t0,l");
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(paramArrayOfByte3);
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, FM_Long.concat("\030BA", 4));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(str);
      localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidAlgorithmParameterException));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
  }

  public static byte[] encrypt4desPadding(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 8))
      paramArrayOfByte1 = FM_Bytes.copyOf(paramArrayOfByte1, 8);
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, FM_Bytes.startsWith("@ZI", 3, 27));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(FM_Exception.getChars(3, 56, "\030Q\037+\031W\016+L5h u:k"));
      localCipher.init(1, localSecretKeySpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localBadPaddingException));
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localNoSuchPaddingException));
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localIllegalBlockSizeException));
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
    }
  }

  public static byte[] javaDes3(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, CRCUtil.valueOf(3, "\035\017\b)yk"));
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(FM_CN.subSequence("K[^y/?", 1));
      localCipher.init(1, localSecretKeySpec);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      localObject = arrayOfByte;
      return localObject;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      while (true)
        System.out.println(localNoSuchPaddingException.getMessage());
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      while (true)
        System.out.println(localIllegalBlockSizeException.getMessage());
    }
    catch (BadPaddingException localBadPaddingException)
    {
      while (true)
        System.out.println(localBadPaddingException.getMessage());
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        System.out.println(localNoSuchAlgorithmException.getMessage());
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        System.out.println(localInvalidKeyException.getMessage());
    }
  }

  public static void main(String[] paramArrayOfString)
  {
  }

  public static void showArray(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        System.out.println();
        return;
      }
      int k = paramArrayOfByte[j];
      System.out.print(k + "\t");
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.DES
 * JD-Core Version:    0.6.0
 */