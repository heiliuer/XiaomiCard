package cmb.pb.a;

import java.security.Key;
import javax.crypto.Cipher;

public final class b
{
  public static String a(String paramString, Key paramKey)
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("AES");
      localCipher.init(1, paramKey);
      String str2 = a.a(localCipher.doFinal(paramString.getBytes("UTF-8")));
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
        String str1 = null;
    }
  }

  public static String b(String paramString, Key paramKey)
  {
    Object localObject = null;
    try
    {
      Cipher localCipher = Cipher.getInstance("AES");
      localCipher.init(2, paramKey);
      byte[] arrayOfByte = localCipher.doFinal(a.a(paramString));
      if (arrayOfByte != null)
      {
        str = new String(arrayOfByte, "UTF-8");
        localObject = str;
      }
    }
    catch (Exception localException)
    {
      while (true)
        String str = null;
    }
    return localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.a.b
 * JD-Core Version:    0.6.0
 */