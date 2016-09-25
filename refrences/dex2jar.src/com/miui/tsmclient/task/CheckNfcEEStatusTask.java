package com.miui.tsmclient.task;

import android.content.Context;
import android.nfc.NfcAdapter;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.I2CSmartMxTerminal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import miui.util.async.Task;

public class CheckNfcEEStatusTask extends Task<Integer>
{
  public static final int ESE_ROUTE_DISABLED = 3;
  public static final int NFC_DISABLED = 1;
  public static final int NFC_EE_ERROR_UNKNOWN = -1;
  public static final int NFC_EE_NORMAL = 0;
  public static final int SE_RESTRICTED = 2;
  private Context mContext;
  private NfcAdapter mNfcAdapter;

  public CheckNfcEEStatusTask(Context paramContext)
  {
    this.mContext = paramContext;
    this.mNfcAdapter = NfcAdapter.getDefaultAdapter(this.mContext);
  }

  private int checkNFCStatus()
  {
    int i = -1;
    if (this.mNfcAdapter == null)
      LogUtils.w("nfc adapter is null");
    while (true)
    {
      return i;
      long l = System.currentTimeMillis();
      if (!this.mNfcAdapter.isEnabled())
      {
        int j = PrefUtils.getSecureSettings(this.mContext, "system_key_nfc_state");
        if (j == 3)
        {
          LogUtils.w("Saved Nfc state is: " + j + ", but NfcAdapter returned disabled");
          HashMap localHashMap = new HashMap();
          localHashMap.put("is_enable", "first_try");
          AnalyticManager.recordEvent("nfc", "key_nfc_unusual_disabled", localHashMap);
          try
          {
            Thread.sleep(500L);
            if (this.mNfcAdapter.isEnabled())
            {
              LogUtils.w("Saved Nfc state is: " + j + ", NfcAdapter returned enabled when retry");
              AnalyticManager.recordEvent("nfc", "key_nfc_unusual_disabled_restore");
              i = 0;
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            while (true)
              Thread.currentThread().interrupt();
            LogUtils.w("Saved Nfc state is: " + j + ", but NfcAdapter returned disabled when retry");
            localHashMap.put("is_enable", "double_try_failed");
            AnalyticManager.recordEvent("nfc", "key_nfc_unusual_disabled", localHashMap);
            AnalyticManager.bugReport(this.mContext, "nfc", l);
          }
          continue;
        }
        i = 1;
        continue;
      }
      if (this.mNfcAdapter.getSeRouting() != 1)
      {
        LogUtils.d("se route is not ese");
        i = 3;
        continue;
      }
      i = 0;
    }
  }

  public Integer doLoad()
    throws Exception
  {
    int i = checkNFCStatus();
    Object localObject2;
    if (i != 0)
    {
      LogUtils.d("CheckNFCEEStatusTask state: " + i);
      localObject2 = Integer.valueOf(i);
    }
    while (true)
    {
      return localObject2;
      I2CSmartMxTerminal localI2CSmartMxTerminal = new I2CSmartMxTerminal(this.mContext);
      try
      {
        localI2CSmartMxTerminal.connect();
        localI2CSmartMxTerminal.close();
        localObject2 = Integer.valueOf(0);
        continue;
      }
      catch (NfcEeIOException localNfcEeIOException)
      {
        LogUtils.e("CheckNFCEEStatusTask occurred NfcEeIOException, code: " + localNfcEeIOException.getErrorCode(), localNfcEeIOException);
        switch (localNfcEeIOException.getErrorCode())
        {
        default:
          AnalyticManager.recordEvent("nfc", "key_nfc_ee_io_exception");
          Integer localInteger4 = Integer.valueOf(-1);
          localObject2 = localInteger4;
          localI2CSmartMxTerminal.close();
          break;
        case 1:
          Integer localInteger3 = Integer.valueOf(1);
          localObject2 = localInteger3;
          localI2CSmartMxTerminal.close();
          break;
        case 5:
          AnalyticManager.recordEvent("nfc", "key_nfc_ee_restricted");
          Integer localInteger2 = Integer.valueOf(2);
          localObject2 = localInteger2;
          localI2CSmartMxTerminal.close();
          continue;
        }
      }
      catch (IOException localIOException)
      {
        LogUtils.e("IOException when checking nfc ee state", localIOException);
        Integer localInteger1 = Integer.valueOf(-1);
        localObject2 = localInteger1;
        localI2CSmartMxTerminal.close();
        continue;
      }
      finally
      {
        localI2CSmartMxTerminal.close();
      }
    }
    throw localObject1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.CheckNfcEEStatusTask
 * JD-Core Version:    0.6.0
 */