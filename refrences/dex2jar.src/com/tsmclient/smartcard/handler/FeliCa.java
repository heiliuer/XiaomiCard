package com.tsmclient.smartcard.handler;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import java.util.Arrays;

public class FeliCa
{
  public static final byte[] EMPTY = new byte[0];
  public static final int STA1_ERROR = 255;
  public static final int STA1_NORMAL;
  protected byte[] data;

  protected FeliCa()
  {
  }

  protected FeliCa(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      paramArrayOfByte = EMPTY;
    this.data = paramArrayOfByte;
  }

  public int size()
  {
    return this.data.length;
  }

  public byte[] toBytes()
  {
    return this.data;
  }

  public String toString()
  {
    return Coder.bytesToHexString(this.data);
  }

  public static final class ReadResponse extends FeliCa
  {
    public static final byte[] EMPTY;
    private final byte[] blockData;

    static
    {
      byte[] arrayOfByte = new byte[12];
      arrayOfByte[0] = 0;
      arrayOfByte[1] = 0;
      arrayOfByte[2] = 0;
      arrayOfByte[3] = 0;
      arrayOfByte[4] = 0;
      arrayOfByte[5] = 0;
      arrayOfByte[6] = 0;
      arrayOfByte[7] = 0;
      arrayOfByte[8] = 0;
      arrayOfByte[9] = 0;
      arrayOfByte[10] = -1;
      arrayOfByte[11] = -1;
      EMPTY = arrayOfByte;
    }

    public ReadResponse(byte[] paramArrayOfByte)
    {
      super();
      if ((getStatusFlag1() == 0) && (getBlockCount() > 0));
      for (this.blockData = Arrays.copyOfRange(this.data, 13, this.data.length); ; this.blockData = FeliCa.EMPTY)
        return;
    }

    public int getBlockCount()
    {
      if (this.data.length > 12);
      for (int i = 0xFF & this.data[12]; ; i = 0)
        return i;
    }

    public byte[] getBlockData()
    {
      return this.blockData;
    }

    public int getStatusFlag1()
    {
      return 0xFF & this.data[10];
    }

    public int getStatusFlag12()
    {
      return getStatusFlag1() << 8 | getStatusFlag2();
    }

    public int getStatusFlag2()
    {
      return 0xFF & this.data[11];
    }

    public boolean isOkey()
    {
      if (getStatusFlag1() == 0);
      for (int i = 1; ; i = 0)
        return i;
    }
  }

  public static final class Command extends FeliCa
  {
    private final byte code;
    private final byte[] idm;
    private final int length;

    public Command(byte paramByte, byte[] paramArrayOfByte)
    {
      this.code = paramByte;
      if (paramArrayOfByte.length >= 8)
        this.idm = Arrays.copyOfRange(paramArrayOfByte, 0, 8);
      for (this.data = Arrays.copyOfRange(paramArrayOfByte, 8, paramArrayOfByte.length); ; this.data = paramArrayOfByte)
      {
        this.length = (2 + paramArrayOfByte.length);
        return;
        this.idm = null;
      }
    }

    public Command(byte paramByte, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    {
      this.code = paramByte;
      this.idm = paramArrayOfByte1;
      this.data = paramArrayOfByte2;
      this.length = (2 + (paramArrayOfByte1.length + this.data.length));
    }

    public Command(byte[] paramArrayOfByte)
    {
      this(paramArrayOfByte[0], Arrays.copyOfRange(paramArrayOfByte, 1, paramArrayOfByte.length));
    }

    public byte[] toBytes()
    {
      ByteArray localByteArray1 = ByteArray.wrap((byte)this.length);
      if (this.idm != null);
      for (ByteArray localByteArray2 = localByteArray1.append(this.code).append(this.idm).append(this.data); ; localByteArray2 = localByteArray1.append(this.code).append(this.data))
        return localByteArray2.toBytes();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.FeliCa
 * JD-Core Version:    0.6.0
 */