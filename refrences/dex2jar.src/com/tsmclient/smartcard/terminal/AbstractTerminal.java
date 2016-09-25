package com.tsmclient.smartcard.terminal;

import android.util.Log;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;

public abstract class AbstractTerminal
  implements IScTerminal
{
  protected static final String SE_RESTRICTED = "SERESTRICTED";
  private static final String TAG = "AbstractTerminal";
  private boolean mNfcChannelOpen;

  private static byte[] appendResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt + paramArrayOfByte1.length];
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, paramArrayOfByte1.length, paramInt);
    return arrayOfByte;
  }

  /** @deprecated */
  public void close()
  {
    monitorenter;
    try
    {
      internalClose();
      this.mNfcChannelOpen = false;
      Log.v("AbstractTerminal", "terminal closed");
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void connect()
    throws IOException
  {
    monitorenter;
    try
    {
      internalConnect();
      this.mNfcChannelOpen = true;
      Log.v("AbstractTerminal", "terminal opened");
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected abstract void internalClose();

  protected abstract void internalConnect()
    throws IOException;

  protected abstract byte[] internalTransmit(byte[] paramArrayOfByte)
    throws IOException;

  public boolean isNfcChannelOpen()
  {
    return this.mNfcChannelOpen;
  }

  /** @deprecated */
  public ScResponse transmit(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    Object localObject2;
    try
    {
      localObject2 = internalTransmit(paramArrayOfByte);
      Log.d("AbstractTerminal", "send: " + Coder.bytesToHexString(paramArrayOfByte));
      Log.d("AbstractTerminal", "resp: " + Coder.bytesToHexString(localObject2));
      if ((localObject2 == null) || (localObject2.length < 2))
        throw new IOException("response too small");
    }
    finally
    {
      monitorexit;
    }
    int i = 0xFF & localObject2[(-2 + localObject2.length)];
    if (i == 108)
    {
      paramArrayOfByte[(-1 + paramArrayOfByte.length)] = localObject2[(-1 + localObject2.length)];
      localObject2 = internalTransmit(paramArrayOfByte);
    }
    while (true)
    {
      ByteArray localByteArray = ByteArray.wrap(localObject2);
      ScResponseImpl localScResponseImpl = new ScResponseImpl(localByteArray.duplicate(0, -2 + localByteArray.length()), localByteArray.duplicate(-2 + localByteArray.length(), 2));
      monitorexit;
      return localScResponseImpl;
      if (i != 97)
        continue;
      byte[] arrayOfByte1 = new byte[5];
      arrayOfByte1[0] = paramArrayOfByte[0];
      arrayOfByte1[1] = -64;
      arrayOfByte1[2] = 0;
      arrayOfByte1[3] = 0;
      arrayOfByte1[4] = 0;
      byte[] arrayOfByte2 = new byte[-2 + localObject2.length];
      System.arraycopy(localObject2, 0, arrayOfByte2, 0, -2 + localObject2.length);
      while (true)
      {
        arrayOfByte1[4] = localObject2[(-1 + localObject2.length)];
        localObject2 = internalTransmit(arrayOfByte1);
        if ((localObject2.length < 2) || (localObject2[(-2 + localObject2.length)] != 97))
          break;
        arrayOfByte2 = appendResponse(arrayOfByte2, localObject2, -2 + localObject2.length);
      }
      byte[] arrayOfByte3 = appendResponse(arrayOfByte2, localObject2, localObject2.length);
      localObject2 = arrayOfByte3;
    }
  }

  static final class ScResponseImpl
    implements ScResponse
  {
    private ByteArray mData;
    private ByteArray mStatus;

    public ScResponseImpl()
    {
      this.mData = ByteArray.EMPTY;
      this.mStatus = STATUS_OK;
    }

    public ScResponseImpl(ByteArray paramByteArray1, ByteArray paramByteArray2)
    {
      this.mData = paramByteArray1;
      this.mStatus = paramByteArray2;
    }

    public ByteArray getData()
    {
      return this.mData;
    }

    public ByteArray getStatus()
    {
      return this.mStatus;
    }

    public byte[] toBytes()
    {
      return this.mData.append(this.mStatus).toBytes();
    }

    public String toString()
    {
      return Coder.bytesToHexString(getStatus().toBytes());
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.AbstractTerminal
 * JD-Core Version:    0.6.0
 */