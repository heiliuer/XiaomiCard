package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import dr;
import java.io.PrintStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class DSA
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (dr localdr)
    {
      break label9;
    }
  }

  public static Map<String, byte[]> generateKey(int paramInt)
  {
    Object localObject = null;
    HashMap localHashMap = new HashMap();
    try
    {
      KeyPairGenerator localKeyPairGenerator2 = KeyPairGenerator.getInstance(FM_Long.concat("\036VQ", 2));
      localKeyPairGenerator1 = localKeyPairGenerator2;
      if (localKeyPairGenerator1 != null)
      {
        localKeyPairGenerator1.initialize(paramInt, new SecureRandom());
        KeyPair localKeyPair = localKeyPairGenerator1.generateKeyPair();
        localHashMap.put(FM_Utils.copyValueOf(4, 3, "_gwtr}Ja~"), localKeyPair.getPublic().getEncoded());
        localHashMap.put(FM_Utils.copyValueOf(2, 57, "]46n0~&W0w"), localKeyPair.getPrivate().getEncoded());
        localObject = localHashMap;
      }
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        if (a != null)
        {
          a.error(RSA.class.getName(), BCCUtil.endsWith("W\036T仺甚宋钰冧珵式幭", 6, 72));
          a.error(RSA.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        }
        System.out.println(Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
        KeyPairGenerator localKeyPairGenerator1 = null;
      }
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      new DSA();
      generateKey(1024);
      label15: return;
    }
    catch (dr localdr)
    {
      break label15;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.DSA
 * JD-Core Version:    0.6.0
 */