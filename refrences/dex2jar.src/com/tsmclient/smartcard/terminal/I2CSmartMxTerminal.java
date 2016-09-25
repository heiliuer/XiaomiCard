package com.tsmclient.smartcard.terminal;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.text.TextUtils;
import com.android.nfc_extras.EeIOException;
import com.android.nfc_extras.NfcAdapterExtras;
import com.android.nfc_extras.NfcExecutionEnvironment;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import java.io.IOException;

public class I2CSmartMxTerminal extends AbstractTerminal
{
  private NfcAdapterExtras mAdapterExtras;
  private Context mContext;
  private NfcExecutionEnvironment mNfcEe;

  public I2CSmartMxTerminal(Context paramContext)
  {
    this.mContext = paramContext;
  }

  /** @deprecated */
  // ERROR //
  protected void internalClose()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 22	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +20 -> 28
    //   11: aload_0
    //   12: getfield 22	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   15: invokevirtual 27	com/android/nfc_extras/NfcExecutionEnvironment:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 22	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal:mNfcEe	Lcom/android/nfc_extras/NfcExecutionEnvironment;
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield 29	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal:mAdapterExtras	Lcom/android/nfc_extras/NfcAdapterExtras;
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
      String str = localEeIOException.getMessage();
      if ((!TextUtils.isEmpty(str)) && (TextUtils.equals(str, "SERESTRICTED")))
        throw new NfcEeIOException("nfc ee has been restricted", 5);
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
      if (this.mAdapterExtras == null)
        throw new NfcEeIOException("nfc ee is not open", 2);
    }
    finally
    {
      monitorexit;
    }
    byte[] arrayOfByte = this.mAdapterExtras.transciveThroughI2C(paramArrayOfByte);
    monitorexit;
    return arrayOfByte;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.I2CSmartMxTerminal
 * JD-Core Version:    0.6.0
 */