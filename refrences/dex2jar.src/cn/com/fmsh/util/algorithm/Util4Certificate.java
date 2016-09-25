package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import dw;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Util4Certificate
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (dw localdw)
    {
      break label9;
    }
  }

  public static Certificate decodeCertificate(byte[] paramArrayOfByte)
  {
    try
    {
      localX509Certificate = (X509Certificate)CertificateFactory.getInstance(Util4Java.toString("\\g;c!", 5, 69)).generateCertificate(new ByteArrayInputStream(paramArrayOfByte));
      return localX509Certificate;
    }
    catch (CertificateException localCertificateException)
    {
      while (true)
      {
        a.error(DES.class.getName(), Util4Java.getExceptionInfo(localCertificateException));
        X509Certificate localX509Certificate = null;
      }
    }
  }

  public static Certificate getCertificate(InputStream paramInputStream)
    throws Exception
  {
    try
    {
      Certificate localCertificate2 = CertificateFactory.getInstance(FM_Long.concat("\0020 \"", 2)).generateCertificate(paramInputStream);
      localCertificate1 = localCertificate2;
      return localCertificate1;
    }
    catch (dw localdw)
    {
      while (true)
        Certificate localCertificate1 = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.Util4Certificate
 * JD-Core Version:    0.6.0
 */