import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.algorithm.RSA;
import cn.com.fmsh.util.log.FMLog;

public class n
{
  public static final int TEMP_KEY_LENGTH = 16;
  public static final int TERMINAL_RANDOM_LENGTH = 8;

  public byte[] getAppend()
  {
    return this.k;
  }

  public byte getBusinessVersion()
  {
    return this.c;
  }

  public byte[] getExponent()
  {
    return this.m;
  }

  public byte getKeyIndex()
  {
    return this.e;
  }

  public byte[] getModulus()
  {
    return this.l;
  }

  public byte[] getSecurityCode()
  {
    return this.j;
  }

  public byte[] getTempKey()
  {
    return this.i;
  }

  public byte[] getTerminalNumber()
  {
    return this.g;
  }

  public byte[] getTerminalRandom()
  {
    return this.f;
  }

  public byte[] getTerminalTime()
  {
    return this.h;
  }

  public byte[] getTerminalType()
  {
    return this.d;
  }

  public void setAppend(byte[] paramArrayOfByte)
  {
    try
    {
      this.k = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setBusinessVersion(byte paramByte)
  {
    try
    {
      this.c = paramByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setExponent(byte[] paramArrayOfByte)
  {
    try
    {
      this.m = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setKeyIndex(byte paramByte)
  {
    try
    {
      this.e = paramByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setModulus(byte[] paramArrayOfByte)
  {
    try
    {
      this.l = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setSecurityCode(byte[] paramArrayOfByte)
  {
    try
    {
      this.j = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setTempKey(byte[] paramArrayOfByte)
  {
    try
    {
      this.i = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setTerminalNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setTerminalRandom(byte[] paramArrayOfByte)
  {
    try
    {
      this.f = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setTerminalTime(byte[] paramArrayOfByte)
  {
    try
    {
      this.h = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public void setTerminalType(byte[] paramArrayOfByte)
  {
    try
    {
      this.d = paramArrayOfByte;
      label5: return;
    }
    catch (o localo)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    int n = 0;
    byte[] arrayOfByte1 = a();
    byte[] arrayOfByte2 = RSA.encrtyByPublic(this.l, this.m, arrayOfByte1, true);
    if (arrayOfByte2 == null);
    int i1;
    byte[] arrayOfByte3;
    for (Object localObject = null; ; localObject = null)
    {
      return localObject;
      i1 = arrayOfByte2.length;
      arrayOfByte3 = new byte[i1 + 5];
      if (this.d.length == 2)
        break;
      if (this.a == null)
        continue;
      this.a.debug(this.b, FM_Exception.getChars(5, 44, "~i?rf?tF;rbx") + FM_Bytes.bytesToHexString(arrayOfByte2));
    }
    arrayOfByte3[n] = 0;
    arrayOfByte3[1] = this.d[0];
    arrayOfByte3[2] = this.d[1];
    arrayOfByte3[3] = getBusinessVersion();
    arrayOfByte3[4] = this.e;
    while (true)
    {
      if (n >= i1)
      {
        localObject = arrayOfByte3;
        break;
      }
      arrayOfByte3[(n + 5)] = arrayOfByte2[n];
      n++;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     n
 * JD-Core Version:    0.6.0
 */