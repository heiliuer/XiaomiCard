package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import ds;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (ds localds)
    {
      break label9;
    }
  }

  public static byte[] md5(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(FM_CN.subSequence("BZ8", 1));
      localObject = localMessageDigest;
      localObject.update(paramArrayOfByte);
      return localObject.digest();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(Digest.class.getName(), FM_CN.subSequence("刍委匘夬赩ｗ\007<{dgr!\027+6e|j一攳捊\027\rm々", 2));
        a.error(Digest.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
  }

  public static byte[] sha(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(Util4Java.toString("P\032��", 4, 111));
      localObject = localMessageDigest;
      localObject.update(paramArrayOfByte);
      return localObject.digest();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        a.error(Digest.class.getName(), CRCUtil.valueOf(4, "則妀半奼贻＃Mtq %2#Sa~o(8乐敡挞CIS、"));
        a.error(Digest.class.getName(), Util4Java.getExceptionInfo(localNoSuchAlgorithmException));
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.Digest
 * JD-Core Version:    0.6.0
 */