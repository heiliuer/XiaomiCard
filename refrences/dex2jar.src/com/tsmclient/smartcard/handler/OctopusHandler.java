package com.tsmclient.smartcard.handler;

import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import java.io.IOException;

public class OctopusHandler
  implements ISmartCardHandler<NfcF>
{
  private static final byte CMD_READ_WO_ENCRYPTION = 6;
  private static final ByteArray POLLING_CMD;
  private static final byte[] SRV_OCTOPUS;
  private static final String TAG = "OctopusHandler";

  static
  {
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = 23;
    arrayOfByte1[1] = 1;
    SRV_OCTOPUS = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[6];
    arrayOfByte2[0] = 6;
    arrayOfByte2[1] = 0;
    arrayOfByte2[2] = -128;
    arrayOfByte2[3] = 8;
    arrayOfByte2[4] = 1;
    arrayOfByte2[5] = 0;
    POLLING_CMD = ByteArray.wrap(arrayOfByte2);
  }

  private int getBalance(float[] paramArrayOfFloat)
  {
    float f = 0.0F;
    int i = paramArrayOfFloat.length;
    for (int j = 0; j < i; j++)
      f += paramArrayOfFloat[j];
    return (int)(100.0F * f);
  }

  private FeliCa.ReadResponse readWithoutEncryption(byte[] paramArrayOfByte, byte paramByte, NfcF paramNfcF)
    throws IOException
  {
    byte[] arrayOfByte1 = paramNfcF.getTag().getId();
    byte[] arrayOfByte2 = new byte[6];
    arrayOfByte2[0] = 1;
    arrayOfByte2[1] = paramArrayOfByte[0];
    arrayOfByte2[2] = paramArrayOfByte[1];
    arrayOfByte2[3] = 1;
    arrayOfByte2[4] = -128;
    arrayOfByte2[5] = paramByte;
    FeliCa.Command localCommand = new FeliCa.Command(6, arrayOfByte1, arrayOfByte2);
    try
    {
      byte[] arrayOfByte3 = paramNfcF.transceive(localCommand.toBytes());
      return new FeliCa.ReadResponse(arrayOfByte3);
    }
    catch (IOException localIOException)
    {
    }
    throw new IOException("OctopusHandlerfailed to get response");
  }

  public int getTechType()
  {
    return 2;
  }

  public Bundle onHandleCard(NfcF paramNfcF)
    throws IOException, UnProcessableCardException
  {
    Bundle localBundle = new Bundle();
    byte[] arrayOfByte = paramNfcF.transceive(POLLING_CMD.toBytes());
    if ((arrayOfByte == null) || (arrayOfByte.length < 2))
      throw new UnProcessableCardException("OctopusHandler: unsupported card type");
    float[] arrayOfFloat = new float[3];
    arrayOfFloat[0] = 0.0F;
    arrayOfFloat[1] = 0.0F;
    arrayOfFloat[2] = 0.0F;
    int i = 0;
    byte b = 0;
    while (true)
    {
      FeliCa.ReadResponse localReadResponse;
      if (i < arrayOfFloat.length)
      {
        localReadResponse = readWithoutEncryption(SRV_OCTOPUS, b, paramNfcF);
        if (localReadResponse.isOkey());
      }
      else
      {
        int k = -999;
        if (i != 0)
          k = getBalance(arrayOfFloat);
        localBundle.putBoolean("success", true);
        localBundle.putInt("card_type", 2);
        localBundle.putString("card_id", "008");
        localBundle.putInt("e_balance", k);
        return localBundle;
      }
      int m = i + 1;
      arrayOfFloat[i] = ((-350 + Coder.bytesToInt(localReadResponse.getBlockData(), 0, 4)) / 10.0F);
      int j;
      b += 1;
      i = m;
    }
  }

  public Bundle onHandleCard(AbstractTerminal paramAbstractTerminal)
    throws IOException, UnProcessableCardException
  {
    return null;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.OctopusHandler
 * JD-Core Version:    0.6.0
 */