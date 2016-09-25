package com.miui.tsmclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;

public class NfcStateReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (!"android.nfc.action.ADAPTER_STATE_CHANGED".equals(paramIntent.getAction()));
    while (true)
    {
      return;
      int i = paramIntent.getIntExtra("android.nfc.extra.ADAPTER_STATE", 1);
      LogUtils.d("NfcStateReceiver nfcState:" + i);
      NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(paramContext);
      PrefUtils.putSecureSettings(paramContext.getApplicationContext(), "system_key_nfc_state", i);
      if ((i == 3) && (localNfcAdapter != null) && (localNfcAdapter.getSeRouting() == 1))
      {
        LogUtils.d("NfcStateReceiver ese is ready");
        if ((PrefUtils.contains(paramContext, "key_trans_card_in_ese")) || (PrefUtils.contains(paramContext, "key_bank_card_in_ese")))
        {
          int j = PrefUtils.getInt(paramContext, "key_trans_card_in_ese", 0);
          PrefUtils.putSecureSettings(paramContext, "key_trans_card_in_ese", j, false);
          PrefUtils.putSecureSettings(paramContext, "key_bank_card_in_ese", PrefUtils.getInt(paramContext, "key_bank_card_in_ese", 0), false);
          if (PrefUtils.getBoolean(paramContext, "key_set_press_volume_down_already", false))
            continue;
          String str = PrefUtils.getLongPressVolumeDownState(paramContext);
          if ((j != 0) && (str != "Street-snap"))
            PrefUtils.setLongPressVolumeDownStateToPay(paramContext);
          PrefUtils.putBoolean(paramContext, "key_set_press_volume_down_already", true);
          continue;
        }
        NfcStateService.queryCardState(paramContext);
        continue;
      }
      PrefUtils.putSecureSettings(paramContext, "key_trans_card_in_ese", 0, false);
      PrefUtils.putSecureSettings(paramContext, "key_bank_card_in_ese", 0, false);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.NfcStateReceiver
 * JD-Core Version:    0.6.0
 */