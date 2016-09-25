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
import dv;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA
{
  public static final String EXPONENT_KEY = "Exponent";
  public static final String MODULUS_KEY = "Modulus";
  public static final String PRIVAET_KEY = "privateKey";
  public static final String PUBLIC_KEY = "publicKey";

  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (dv localdv)
    {
      break label9;
    }
  }

  public static byte[] decrtyByPrivate(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    Object localObject = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1));
    while (true)
    {
      return localObject;
      label16: if ((paramArrayOfByte3 == null) || (paramArrayOfByte3.length < 1))
        continue;
      RSAPrivateKeySpec localRSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, paramArrayOfByte1), new BigInteger(1, paramArrayOfByte2));
      try
      {
        PrivateKey localPrivateKey = KeyFactory.getInstance(FM_CN.subSequence("CSN", 3)).generatePrivate(localRSAPrivateKeySpec);
        localObject = localPrivateKey;
        localObject = a(2, (Key)localObject, paramArrayOfByte3, paramBoolean);
        continue;
        if (paramArrayOfByte2 == null)
          continue;
        if (paramArrayOfByte2.length >= 1)
          break label16;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), FM_Bytes.startsWith("QVF秈钮觮寉凫玣弗帯", 2, 2));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        }
      }
      catch (InvalidKeySpecException localInvalidKeySpecException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), FM_CN.subSequence("@RQ秞铫覾宊冡玺弛帰", 4));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
        }
      }
    }
  }

  public static byte[] decryptByPrivate(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean)
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(paramArrayOfByte1);
    Object localObject = null;
    try
    {
      PrivateKey localPrivateKey = KeyFactory.getInstance(FM_Utils.copyValueOf(6, 35, "CG\026")).generatePrivate(localPKCS8EncodedKeySpec);
      localObject = localPrivateKey;
      return a(2, localObject, paramArrayOfByte2, paramBoolean);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Int.lastIndexOf(244, "\025\033\b覩宍斺冷現彍幨"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), CRCUtil.valueOf(3, "\013\031\032覯寛旸凥玠弃幪"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
  }

  public static byte[] decryptByPublic(PublicKey paramPublicKey, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    try
    {
      byte[] arrayOfByte2 = a(2, paramPublicKey, paramArrayOfByte, paramBoolean);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (dv localdv)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static byte[] decryptByPublic(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    Object localObject = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1));
    while (true)
    {
      return localObject;
      if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length < 1) || (paramArrayOfByte3 == null) || (paramArrayOfByte3.length < 1))
        continue;
      RSAPublicKeySpec localRSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(1, paramArrayOfByte1), new BigInteger(1, paramArrayOfByte2));
      try
      {
        PublicKey localPublicKey = KeyFactory.getInstance(BCCUtil.endsWith("\017CB", 254, 19)).generatePublic(localRSAPublicKeySpec);
        localObject = localPublicKey;
        localObject = a(2, (Key)localObject, paramArrayOfByte3, paramBoolean);
      }
      catch (InvalidKeySpecException localInvalidKeySpecException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), BCCUtil.endsWith("S\r\032覻宓斤况珼彋幾", 2, 125));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), BCCUtil.endsWith("Q\f\032覴宕方冱珷彁幧", 4, 124));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        }
      }
    }
  }

  public static byte[] encrtyByPrivate(PrivateKey paramPrivateKey, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    try
    {
      byte[] arrayOfByte2 = a(1, paramPrivateKey, paramArrayOfByte, paramBoolean);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (dv localdv)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static byte[] encrtyByPrivate(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean)
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(paramArrayOfByte1);
    Object localObject = null;
    try
    {
      PrivateKey localPrivateKey = KeyFactory.getInstance(FM_Long.concat("\017[R", 5)).generatePrivate(localPKCS8EncodedKeySpec);
      localObject = localPrivateKey;
      return a(1, localObject, paramArrayOfByte2, paramBoolean);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), CRCUtil.valueOf(3, "\013\031\032禍钸劮寙凪玱彐幻"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), BCCUtil.endsWith("VF\007秖铭勹富冡玼弟并", 5, 49));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
  }

  public static byte[] encrtyByPrivate(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    Object localObject = null;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2.length < 1));
    while (true)
    {
      return localObject;
      if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1) || (paramArrayOfByte3 == null) || (paramArrayOfByte3.length < 1))
        continue;
      RSAPrivateKeySpec localRSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, paramArrayOfByte1), new BigInteger(1, paramArrayOfByte2));
      try
      {
        PrivateKey localPrivateKey = KeyFactory.getInstance(Util4Java.toString("SB@", 2, 16)).generatePrivate(localRSAPrivateKeySpec);
        localObject = localPrivateKey;
        localObject = a(1, (Key)localObject, paramArrayOfByte3, paramBoolean);
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), FM_CN.subSequence("@RQ秞铫勽宊冡玺弛帰", 4));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        }
      }
      catch (InvalidKeySpecException localInvalidKeySpecException)
      {
        while (true)
        {
          a.error(RSA.class.getName(), FM_Bytes.startsWith("ESH禓链勤寋凬珯彊幩", 310, 105));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
        }
      }
    }
  }

  public static byte[] encrtyByPublic(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean)
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte1);
    Object localObject = null;
    try
    {
      PublicKey localPublicKey = KeyFactory.getInstance(FM_Long.concat("\tUP", 3)).generatePublic(localX509EncodedKeySpec);
      localObject = localPublicKey;
      return a(1, localObject, paramArrayOfByte2, paramBoolean);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), CRCUtil.valueOf(208, "\024DI秘钯勻宊冧現弝帨"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), BCCUtil.endsWith("W\001\036禍钼劦寕出珽彘广", 134, 109));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
  }

  // ERROR //
  public static byte[] encrtyByPublic(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +9 -> 13
    //   7: aload_0
    //   8: arraylength
    //   9: iconst_1
    //   10: if_icmpge +162 -> 172
    //   13: aload 4
    //   15: areturn
    //   16: astore 8
    //   18: getstatic 35	cn/com/fmsh/util/algorithm/RSA:a	Lcn/com/fmsh/util/log/FMLog;
    //   21: ldc 2
    //   23: invokevirtual 90	java/lang/Class:getName	()Ljava/lang/String;
    //   26: ldc 247
    //   28: iconst_1
    //   29: invokestatic 59	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   32: invokeinterface 104 3 0
    //   37: getstatic 35	cn/com/fmsh/util/algorithm/RSA:a	Lcn/com/fmsh/util/log/FMLog;
    //   40: ldc 2
    //   42: invokevirtual 90	java/lang/Class:getName	()Ljava/lang/String;
    //   45: aload 8
    //   47: invokestatic 110	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   50: invokeinterface 104 3 0
    //   55: iconst_1
    //   56: aload 4
    //   58: aload_2
    //   59: iload_3
    //   60: invokestatic 176	cn/com/fmsh/util/algorithm/RSA:a	(ILjava/security/Key;[BZ)[B
    //   63: astore 4
    //   65: goto -52 -> 13
    //   68: aload_2
    //   69: ifnull -56 -> 13
    //   72: aload_2
    //   73: arraylength
    //   74: iconst_1
    //   75: if_icmplt -62 -> 13
    //   78: new 210	java/security/spec/RSAPublicKeySpec
    //   81: dup
    //   82: new 157	java/math/BigInteger
    //   85: dup
    //   86: iconst_1
    //   87: aload_0
    //   88: invokespecial 160	java/math/BigInteger:<init>	(I[B)V
    //   91: new 157	java/math/BigInteger
    //   94: dup
    //   95: iconst_1
    //   96: aload_1
    //   97: invokespecial 160	java/math/BigInteger:<init>	(I[B)V
    //   100: invokespecial 211	java/security/spec/RSAPublicKeySpec:<init>	(Ljava/math/BigInteger;Ljava/math/BigInteger;)V
    //   103: astore 5
    //   105: ldc 249
    //   107: iconst_3
    //   108: bipush 49
    //   110: invokestatic 134	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   113: invokestatic 170	java/security/KeyFactory:getInstance	(Ljava/lang/String;)Ljava/security/KeyFactory;
    //   116: aload 5
    //   118: invokevirtual 217	java/security/KeyFactory:generatePublic	(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
    //   121: astore 7
    //   123: aload 7
    //   125: astore 4
    //   127: goto -72 -> 55
    //   130: astore 6
    //   132: getstatic 35	cn/com/fmsh/util/algorithm/RSA:a	Lcn/com/fmsh/util/log/FMLog;
    //   135: ldc 2
    //   137: invokevirtual 90	java/lang/Class:getName	()Ljava/lang/String;
    //   140: ldc 251
    //   142: iconst_3
    //   143: invokestatic 59	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   146: invokeinterface 104 3 0
    //   151: getstatic 35	cn/com/fmsh/util/algorithm/RSA:a	Lcn/com/fmsh/util/log/FMLog;
    //   154: ldc 2
    //   156: invokevirtual 90	java/lang/Class:getName	()Ljava/lang/String;
    //   159: aload 6
    //   161: invokestatic 110	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   164: invokeinterface 104 3 0
    //   169: goto -114 -> 55
    //   172: aload_1
    //   173: ifnull -160 -> 13
    //   176: aload_1
    //   177: arraylength
    //   178: iconst_1
    //   179: if_icmpge -111 -> 68
    //   182: goto -169 -> 13
    //
    // Exception table:
    //   from	to	target	type
    //   105	123	16	java/security/NoSuchAlgorithmException
    //   105	123	130	java/security/spec/InvalidKeySpecException
  }

  public static byte[] encryptByPublic(PublicKey paramPublicKey, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    try
    {
      byte[] arrayOfByte2 = a(1, paramPublicKey, paramArrayOfByte, paramBoolean);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (dv localdv)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static Map<String, byte[]> generateKey(int paramInt)
  {
    Object localObject = null;
    HashMap localHashMap = new HashMap();
    try
    {
      KeyPairGenerator localKeyPairGenerator2 = KeyPairGenerator.getInstance(FM_CN.subSequence("AQP", 5));
      localKeyPairGenerator1 = localKeyPairGenerator2;
      if (localKeyPairGenerator1 != null)
      {
        localKeyPairGenerator1.initialize(paramInt, new SecureRandom());
        KeyPair localKeyPair = localKeyPairGenerator1.generateKeyPair();
        localHashMap.put(FM_Exception.getChars(300, 22, "un3+4pBz,"), localKeyPair.getPublic().getEncoded());
        localHashMap.put(FM_Long.concat("09?7-#gF}z", 200), localKeyPair.getPrivate().getEncoded());
        localObject = localHashMap;
      }
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Exception.getChars(1, 114, "\b\037\037价畝寒钣凢玺彞并"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        KeyPairGenerator localKeyPairGenerator1 = null;
      }
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte1 = FM_Bytes.hexStringToBytes(Util4Java.toString("1(!yqh", 2, 24));
      byte[] arrayOfByte2 = FM_Bytes.hexStringToBytes(FM_Bytes.startsWith("34uw7:yy;8}{?&ae#,eo'(ik", 2, 33));
      byte[] arrayOfByte3 = encrtyByPublic(FM_Bytes.hexStringToBytes(FM_CN.subSequence("[\004c\003\022AP0T\\zo\bh8*;V\005bp\0265RF;-}oz\036O]FR\006e{\027@(^=-yb\016m8!@&td{e3M&5*\016l\fk6%D#\005ft\024j?[4\"\b\035|aB-B#��duy\034?^=Yzkq\0252,0+taohJX:+\rdrf<Q3 \002\013\034}\035I(=Yw\027qe2#1#Yzi\t\031KX<.rdwdATA7,\016o\bh0[7$sf\006\0204$^J-\r\037\bjNU2,\007as`A?\\>*\016j|\0346R<,\005etanJYM\"{\030pn5!5V{i\001{\0308)2Xpa\004d5-3 y\024\031\n\031MXO.\f\023we6%G(\004\n\030}e;\\:", 170)), arrayOfByte1, arrayOfByte2, true);
      System.out.println(FM_Bytes.bytesToHexString(arrayOfByte3));
      label57: return;
    }
    catch (dv localdv)
    {
      break label57;
    }
  }

  public static Map<String, BigInteger> privateKey2RSA(PrivateKey paramPrivateKey)
  {
    try
    {
      RSAPrivateKey localRSAPrivateKey = (RSAPrivateKey)paramPrivateKey;
      localHashMap = new HashMap();
      localHashMap.put(FM_Exception.getChars(1, 38, "\027o\"9~m-"), localRSAPrivateKey.getModulus());
      localHashMap.put(Util4Java.toString("D)q>/t/e", 2, 80), localRSAPrivateKey.getPrivateExponent());
      return localHashMap;
    }
    catch (dv localdv)
    {
      while (true)
        HashMap localHashMap = null;
    }
  }

  public static Map<String, BigInteger> publicKey2RSA(PublicKey paramPublicKey)
  {
    try
    {
      RSAPublicKey localRSAPublicKey = (RSAPublicKey)paramPublicKey;
      localHashMap = new HashMap();
      localHashMap.put(FM_Utils.copyValueOf(3, 105, "C8$<~nw"), localRSAPublicKey.getModulus());
      localHashMap.put(FM_Long.concat("\031brfvp=", 4), localRSAPublicKey.getPublicExponent());
      return localHashMap;
    }
    catch (dv localdv)
    {
      while (true)
        HashMap localHashMap = null;
    }
  }

  public static byte[] sign(byte[] paramArrayOfByte, PrivateKey paramPrivateKey)
  {
    Object localObject = null;
    try
    {
      Signature localSignature = Signature.getInstance(FM_Long.concat("\bNP-p{i \001\r\b", 3));
      localSignature.initSign(paramPrivateKey);
      localSignature.update(paramArrayOfByte);
      byte[] arrayOfByte = localSignature.sign();
      localObject = arrayOfByte;
      return localObject;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Utils.copyValueOf(1, 67, "种铪筬员弚幣"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
      }
    }
    catch (SignatureException localSignatureException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), BCCUtil.endsWith("秀铹筩吟彏幰", 2, 91));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localSignatureException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), Util4Java.toString("禎铸笵呔弅席", 208, 14));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
  }

  public static byte[] sign(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(paramArrayOfByte2);
    Object localObject = null;
    try
    {
      KeyFactory localKeyFactory = KeyFactory.getInstance(FM_Int.lastIndexOf(242, "\027\025\006"));
      Signature localSignature = Signature.getInstance(FM_CN.subSequence("@JPq8794YIH", 5));
      localSignature.initSign(localKeyFactory.generatePrivate(localPKCS8EncodedKeySpec));
      localSignature.update(paramArrayOfByte1);
      byte[] arrayOfByte = localSignature.sign();
      localObject = arrayOfByte;
      return localObject;
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Bytes.startsWith("禎铣筣呙彉帺", 238, 87));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
    catch (SignatureException localSignatureException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_CN.subSequence("秒钧筯呍彍幦", 5));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localSignatureException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), BCCUtil.endsWith("秃铨符呎弌帡", 3, 107));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), Util4Java.toString("秃钼笮吊彜席", 3, 55));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
      }
    }
  }

  public static byte[] sign(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    RSAPrivateKeySpec localRSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, paramArrayOfByte2), new BigInteger(1, paramArrayOfByte3));
    Object localObject = null;
    try
    {
      KeyFactory localKeyFactory = KeyFactory.getInstance(FM_Int.lastIndexOf(5, "\n\n\033"));
      Signature localSignature = Signature.getInstance(FM_Long.concat("\017OS,zj!\006\f\013", 4));
      localSignature.initSign(localKeyFactory.generatePrivate(localRSAPrivateKeySpec));
      localSignature.update(paramArrayOfByte1);
      byte[] arrayOfByte = localSignature.sign();
      localObject = arrayOfByte;
      return localObject;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_CN.subSequence("秒钧筯呍彍幦", 5));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Long.concat("秃钨筦呎彌幡", 298));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
    catch (SignatureException localSignatureException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Long.concat("秃钨筦后弌帡", 138));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localSignatureException));
      }
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), BCCUtil.endsWith("秃铤笾吒彜幥", 3, 95));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
  }

  public static boolean verify(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4)
  {
    RSAPublicKeySpec localRSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(1, paramArrayOfByte2), new BigInteger(1, paramArrayOfByte3));
    int i = 0;
    try
    {
      KeyFactory localKeyFactory = KeyFactory.getInstance(FM_Long.concat("V\\[", 300));
      Signature localSignature = Signature.getInstance(CRCUtil.valueOf(5, "\b\004\034hyuzQ\007\004"));
      localSignature.initVerify(localKeyFactory.generatePublic(localRSAPublicKeySpec));
      localSignature.update(paramArrayOfByte1);
      boolean bool = localSignature.verify(paramArrayOfByte4);
      i = bool;
      return i;
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), CRCUtil.valueOf(122, "儼铤髞笽弖帽"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeySpecException));
      }
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Bytes.startsWith("儭钡髋筴彏帨", 320, 67));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localInvalidKeyException));
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), CRCUtil.valueOf(6, "儰铨髒笱彂帩"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
    catch (SignatureException localSignatureException)
    {
      while (true)
      {
        a.error(RSA.class.getName(), FM_Int.lastIndexOf(4, "儻铽髕笤彙幤"));
        a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localSignatureException));
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.RSA
 * JD-Core Version:    0.6.0
 */