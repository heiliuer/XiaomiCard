package cn.com.fmsh.script.bean;

import bh;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import java.io.PrintStream;

public class ApduResponse
{
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte = FM_Bytes.hexStringToBytes(CRCUtil.valueOf(216, "~."));
      ApduResponse localApduResponse = new ApduResponse();
      localApduResponse.setResult(arrayOfByte);
      System.out.println(FM_Bytes.bytesToHexString(localApduResponse.toBytes()));
      label38: return;
    }
    catch (bh localbh)
    {
      break label38;
    }
  }

  public byte[] getApdu()
  {
    return this.b;
  }

  public int getId()
  {
    return this.a;
  }

  public byte[] getResult()
  {
    return this.c;
  }

  public void setApdu(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (bh localbh)
    {
      break label5;
    }
  }

  public void setId(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (bh localbh)
    {
      break label5;
    }
  }

  public void setResult(byte[] paramArrayOfByte)
  {
    try
    {
      this.c = paramArrayOfByte;
      label5: return;
    }
    catch (bh localbh)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    int i = 0;
    byte[] arrayOfByte1;
    try
    {
      if ((this.c != null) && (this.c.length >= 1))
      {
        byte[] arrayOfByte2 = new byte[3 + this.c.length];
        arrayOfByte2[0] = -94;
        arrayOfByte2[1] = (byte)(1 + this.c.length);
        arrayOfByte2[2] = (byte)this.a;
        while (true)
        {
          if (i >= this.c.length)
          {
            arrayOfByte1 = arrayOfByte2;
            break;
          }
          arrayOfByte2[(i + 3)] = this.c[i];
          i++;
        }
      }
    }
    catch (bh localbh)
    {
      arrayOfByte1 = null;
      break label100;
      arrayOfByte1 = null;
    }
    label100: return arrayOfByte1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.bean.ApduResponse
 * JD-Core Version:    0.6.0
 */