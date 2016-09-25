package cmb.pb.a;

import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class c
{
  static byte[] a;
  static String b;
  static PublicKey c;
  private static char[] d;

  static
  {
    byte[] arrayOfByte = new byte[''];
    arrayOfByte[0] = -78;
    arrayOfByte[1] = -70;
    arrayOfByte[2] = 1;
    arrayOfByte[3] = -46;
    arrayOfByte[4] = -82;
    arrayOfByte[5] = 78;
    arrayOfByte[6] = -3;
    arrayOfByte[7] = -95;
    arrayOfByte[8] = 7;
    arrayOfByte[9] = 10;
    arrayOfByte[10] = 57;
    arrayOfByte[11] = -23;
    arrayOfByte[12] = -61;
    arrayOfByte[13] = 75;
    arrayOfByte[14] = -50;
    arrayOfByte[15] = 18;
    arrayOfByte[16] = 36;
    arrayOfByte[17] = 9;
    arrayOfByte[18] = -35;
    arrayOfByte[19] = -43;
    arrayOfByte[20] = -114;
    arrayOfByte[21] = 6;
    arrayOfByte[22] = -117;
    arrayOfByte[23] = 27;
    arrayOfByte[24] = -91;
    arrayOfByte[25] = 28;
    arrayOfByte[26] = 80;
    arrayOfByte[27] = 73;
    arrayOfByte[28] = 100;
    arrayOfByte[29] = -2;
    arrayOfByte[30] = 75;
    arrayOfByte[31] = 14;
    arrayOfByte[32] = 106;
    arrayOfByte[33] = 96;
    arrayOfByte[34] = 97;
    arrayOfByte[35] = 74;
    arrayOfByte[36] = 97;
    arrayOfByte[37] = 1;
    arrayOfByte[38] = -18;
    arrayOfByte[39] = -78;
    arrayOfByte[40] = 50;
    arrayOfByte[41] = -104;
    arrayOfByte[42] = 74;
    arrayOfByte[43] = 42;
    arrayOfByte[44] = 71;
    arrayOfByte[45] = -2;
    arrayOfByte[46] = -45;
    arrayOfByte[47] = -99;
    arrayOfByte[48] = -87;
    arrayOfByte[49] = 125;
    arrayOfByte[50] = 42;
    arrayOfByte[51] = -108;
    arrayOfByte[52] = 99;
    arrayOfByte[53] = 117;
    arrayOfByte[54] = 48;
    arrayOfByte[55] = 106;
    arrayOfByte[56] = 13;
    arrayOfByte[57] = -76;
    arrayOfByte[58] = 14;
    arrayOfByte[59] = 108;
    arrayOfByte[60] = 124;
    arrayOfByte[61] = 32;
    arrayOfByte[62] = -27;
    arrayOfByte[63] = 63;
    arrayOfByte[64] = -43;
    arrayOfByte[65] = 68;
    arrayOfByte[66] = -102;
    arrayOfByte[67] = 77;
    arrayOfByte[68] = 54;
    arrayOfByte[69] = -89;
    arrayOfByte[70] = -85;
    arrayOfByte[71] = 92;
    arrayOfByte[72] = -62;
    arrayOfByte[73] = 83;
    arrayOfByte[74] = 69;
    arrayOfByte[75] = 40;
    arrayOfByte[76] = -49;
    arrayOfByte[77] = -53;
    arrayOfByte[78] = 72;
    arrayOfByte[79] = -118;
    arrayOfByte[80] = -12;
    arrayOfByte[81] = -123;
    arrayOfByte[82] = -121;
    arrayOfByte[83] = -59;
    arrayOfByte[84] = 9;
    arrayOfByte[85] = -105;
    arrayOfByte[86] = 61;
    arrayOfByte[87] = 78;
    arrayOfByte[88] = 112;
    arrayOfByte[89] = -52;
    arrayOfByte[90] = 69;
    arrayOfByte[91] = 58;
    arrayOfByte[92] = -94;
    arrayOfByte[93] = 111;
    arrayOfByte[94] = 7;
    arrayOfByte[95] = -110;
    arrayOfByte[96] = -18;
    arrayOfByte[97] = -5;
    arrayOfByte[98] = -60;
    arrayOfByte[99] = 23;
    arrayOfByte[100] = 103;
    arrayOfByte[101] = 79;
    arrayOfByte[102] = -113;
    arrayOfByte[103] = 22;
    arrayOfByte[104] = 101;
    arrayOfByte[105] = -94;
    arrayOfByte[106] = -35;
    arrayOfByte[107] = -38;
    arrayOfByte[108] = 89;
    arrayOfByte[109] = -37;
    arrayOfByte[110] = 94;
    arrayOfByte[111] = -72;
    arrayOfByte[112] = -105;
    arrayOfByte[113] = -89;
    arrayOfByte[114] = 35;
    arrayOfByte[115] = -88;
    arrayOfByte[116] = 127;
    arrayOfByte[117] = -101;
    arrayOfByte[118] = -33;
    arrayOfByte[119] = 120;
    arrayOfByte[120] = 96;
    arrayOfByte[121] = -90;
    arrayOfByte[122] = -20;
    arrayOfByte[123] = 16;
    arrayOfByte[124] = 42;
    arrayOfByte[125] = -38;
    arrayOfByte[126] = 23;
    arrayOfByte[127] = -97;
    a = arrayOfByte;
    b = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk+PFDSFypJvT2VZOvh7L3fUf52aAgtSjxZT4ifKEyj1PIDaj8FCC3880xD0chYEXg+CpkVnj9WYCfBDqeDpNYdRBSKIf2LP/26CObifDxd9VK+4VY/vsiW0qtqjuxVtLHficIjeB34FhdTve7mzXOaecepdwaRSMbzPsonIdeb3ysiSC20XYuzYmfolNVDQIgMVq8tRvacKhc65nLHRovBUTmeUa7tu+1rftsXW8/WbfsHp1YftnqFnb1V/MCtCUcmp3q3fYzxMjukS0lvIodw3zDyuC2vC9ne3ICAg60OHRbEWSKjxlvqrKNdnAYbaKgPbDwBLo2wBCC5qfFpMppQIDAQAB";
    c = null;
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = 48;
    arrayOfChar[1] = 49;
    arrayOfChar[2] = 50;
    arrayOfChar[3] = 51;
    arrayOfChar[4] = 52;
    arrayOfChar[5] = 53;
    arrayOfChar[6] = 54;
    arrayOfChar[7] = 55;
    arrayOfChar[8] = 56;
    arrayOfChar[9] = 57;
    arrayOfChar[10] = 97;
    arrayOfChar[11] = 98;
    arrayOfChar[12] = 99;
    arrayOfChar[13] = 100;
    arrayOfChar[14] = 101;
    arrayOfChar[15] = 102;
    d = arrayOfChar;
  }

  public static String a(String paramString1, String paramString2)
  {
    String str1 = null;
    if (paramString1 == null)
      return str1;
    if ((paramString2 == null) || (paramString2.length() < 5))
    {
      String str2 = Long.toString(System.currentTimeMillis());
      paramString2 = str2.substring(-12 + str2.length());
    }
    byte[] arrayOfByte1 = ("AAA" + paramString2.substring(-5 + paramString2.length()) + paramString1).getBytes();
    if (arrayOfByte1 != null);
    while (true)
    {
      try
      {
        if (c != null)
          continue;
        break label189;
        str1 = a.b(localObject);
        break;
        Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        localCipher.init(1, c);
        byte[] arrayOfByte2 = localCipher.doFinal(arrayOfByte1);
        localObject = arrayOfByte2;
        continue;
      }
      catch (InvalidKeyException localInvalidKeyException)
      {
        localInvalidKeyException.printStackTrace();
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
      catch (InvalidKeySpecException localInvalidKeySpecException)
      {
        localInvalidKeySpecException.printStackTrace();
      }
      catch (NoSuchPaddingException localNoSuchPaddingException)
      {
        localNoSuchPaddingException.printStackTrace();
      }
      catch (IllegalBlockSizeException localIllegalBlockSizeException)
      {
        localIllegalBlockSizeException.printStackTrace();
      }
      catch (BadPaddingException localBadPaddingException)
      {
        localBadPaddingException.printStackTrace();
      }
      break;
      label189: Object localObject = null;
    }
  }

  private static PublicKey a(byte[] paramArrayOfByte)
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte);
    return KeyFactory.getInstance("RSA").generatePublic(localX509EncodedKeySpec);
  }

  public static void a(String paramString)
  {
    Log.v("pbkey", paramString);
    PublicKey localPublicKey = a(a.a(b));
    byte[] arrayOfByte = a.a(paramString);
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(2, localPublicKey);
    c = a(localCipher.doFinal(arrayOfByte));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.a.c
 * JD-Core Version:    0.6.0
 */