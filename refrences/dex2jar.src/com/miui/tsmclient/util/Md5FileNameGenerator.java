package com.miui.tsmclient.util;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.L;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Md5FileNameGenerator
  implements FileNameGenerator
{
  private static final String HASH_ALGORITHM = "MD5";
  private static final int RADIX = 36;
  private Pattern mPattern = Pattern.compile("(http://[ft]\\d+\\.market.xiaomi.com/)|(http://[ft]\\d+\\.market.mi-img.com/)");

  private byte[] getMD5(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      localObject = arrayOfByte;
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        L.e(localNoSuchAlgorithmException);
    }
  }

  public String generate(String paramString)
  {
    String str = paramString;
    Matcher localMatcher = this.mPattern.matcher(paramString);
    if (localMatcher.lookingAt())
      str = localMatcher.replaceFirst("");
    return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.Md5FileNameGenerator
 * JD-Core Version:    0.6.0
 */