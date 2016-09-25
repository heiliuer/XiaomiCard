package com.tsmclient.smartcard.terminal;

import android.content.Context;
import android.nfc.NfcAdapter;
import com.android.nfc_extras.EeIOException;
import com.android.nfc_extras.NfcAdapterExtras;
import com.android.nfc_extras.NfcExecutionEnvironment;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import java.io.IOException;

public class SPISmartMxTerminal extends AbstractTerminal
{
  private NfcAdapterExtras mAdapterExtras;
  private Context mContext;
  private NfcExecutionEnvironment mNfcEe;

  public SPISmartMxTerminal(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public String getSignedSpiPK()
    throws NfcEeIOException
  {
    if (this.mAdapterExtras == null)
      throw new NfcEeIOException("nfc ee is not open", 2);
    return this.mAdapterExtras.getSpiSignedPK();
  }

  /** @deprecated */
  // ERROR //
  protected void internalClose()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +20 -> 28
    //   11: aload_0
    //   12: getfield 38	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   15: invokevirtual 43	com/android/nfc_extras/NfcExecutionEnvironment:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 38	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield 23	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:mAdapterExtras	Lcom/android/nfc_extras/NfcAdapterExtras;
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //   36: astore_3
    //   37: goto -19 -> 18
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   11	18	31	finally
    //   18	28	31	finally
    //   11	18	36	java/io/IOException
  }

  /** @deprecated */
  protected void internalConnect()
    throws IOException
  {
    monitorenter;
    try
    {
      if (this.mNfcEe != null)
        throw new NfcEeIOException("nfc ee is open, close it first", 3);
    }
    finally
    {
      monitorexit;
    }
    if (this.mContext == null)
      throw new IOException("context is null!");
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(this.mContext.getApplicationContext());
    if (localNfcAdapter == null)
      throw new NfcEeIOException("nfc is unavailable on this device", 1);
    if (!localNfcAdapter.isEnabled())
      throw new NfcEeIOException("nfc is disabled", 1);
    this.mAdapterExtras = NfcAdapterExtras.get(localNfcAdapter);
    this.mNfcEe = this.mAdapterExtras.getEmbeddedExecutionEnvironment();
    try
    {
      this.mNfcEe.open();
      monitorexit;
      return;
    }
    catch (EeIOException localEeIOException)
    {
    }
    throw new NfcEeIOException("nfc was dead or nfc ee occurred an unknown error", 4);
  }

  /** @deprecated */
  protected byte[] internalTransmit(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    try
    {
      if (this.mNfcEe == null)
        throw new NfcEeIOException("nfc ee is not open", 2);
    }
    finally
    {
      monitorexit;
    }
    try
    {
      byte[] arrayOfByte = this.mNfcEe.transceive(paramArrayOfByte);
      monitorexit;
      return arrayOfByte;
    }
    catch (EeIOException localEeIOException)
    {
    }
    throw new NfcEeIOException("nfc was dead or  nfc ee occurred an unknown error", 4);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.SPISmartMxTerminal
 * JD-Core Version:    0.6.0
 */