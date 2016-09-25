package com.miui.tsmclient.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;

public class PrefUtils
{
  public static final String PREF_KEY_CIN = "key_cin";
  public static final String PREF_KEY_CPLC = "key_cplc";
  public static final String PREF_KEY_DEFAULT_BANK_CARD = "key_default_bank_card";
  public static final String PREF_KEY_DEFAULT_TRANSCARD_CHECKED = "transcard_checked";
  public static final String PREF_KEY_DEFAULT_TRANS_CARD = "key_default_trans_card";
  public static final String PREF_KEY_LAST_CARD = "key_last_card";
  public static final String PREF_KEY_POPUP_RECHARGE_TIPS_ALREADY = "key_popup_recharge_tips_already";
  public static final String PREF_KEY_SEID = "key_seid";
  public static final String PREF_KEY_SET_PRESS_VOLUME_DOWN_ALREADY = "key_set_press_volume_down_already";
  public static final String PREF_KEY_SPI_PK_STATE = "key_spi_pk_state";
  public static final String PREF_KEY_TRANSCARD_NUM = "transcard_num";
  private static final String PREF_NAME = "pref_com_miui_tsmclient";
  public static final String SETTINGS_SYSTEM_PREF_KEY_BANK_CARD = "key_bank_card_in_ese";
  public static final String SETTINGS_SYSTEM_PREF_KEY_NFC_STATE = "system_key_nfc_state";
  public static final String SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD = "key_trans_card_in_ese";
  public static final int SETTINGS_SYSTEM_PREF_VALUE_DEFAULT = 0;
  public static final int SETTINGS_SYSTEM_PREF_VALUE_HAS_CARD = 1;

  public static boolean contains(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).contains(paramString);
  }

  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).getBoolean(paramString, paramBoolean);
  }

  public static String getDefaultCard(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean);
    for (String str = getString(paramContext, "key_default_trans_card", null); ; str = getString(paramContext, "key_default_bank_card", null))
      return str;
  }

  public static int getInt(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).getInt(paramString, paramInt);
  }

  public static String getLongPressVolumeDownState(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "key_long_press_volume_down");
    LogUtils.d("getLongPressVolumeDownState: " + str);
    return str;
  }

  public static int getSecureSettings(Context paramContext, String paramString)
  {
    int i = -1;
    try
    {
      int j = Settings.Secure.getInt(paramContext.getContentResolver(), paramString);
      i = j;
      LogUtils.d("get secure value for key: " + paramString + ", value: " + i);
      return i;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      while (true)
        LogUtils.e("failed to get secure value for key: " + paramString, localSettingNotFoundException);
    }
  }

  public static String getString(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).getString(paramString1, paramString2);
  }

  public static void putBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.apply();
  }

  public static void putInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.apply();
  }

  public static void putSecureSettings(Context paramContext, String paramString, int paramInt)
  {
    putSecureSettings(paramContext, paramString, paramInt, true);
  }

  public static void putSecureSettings(Context paramContext, String paramString, int paramInt, boolean paramBoolean)
  {
    LogUtils.d("save secure settings, key: " + paramString + ", value: " + paramInt);
    if (paramBoolean)
      putInt(paramContext, paramString, paramInt);
    Settings.Secure.putInt(paramContext.getContentResolver(), paramString, paramInt);
  }

  public static void putString(Context paramContext, String paramString1, String paramString2)
  {
    putString(paramContext, "pref_com_miui_tsmclient", paramString1, paramString2);
  }

  public static void putString(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString1, 0).edit();
    localEditor.putString(paramString2, paramString3);
    localEditor.apply();
  }

  public static boolean remove(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("pref_com_miui_tsmclient", 0).edit().remove(paramString).commit();
  }

  public static void setDefaultCard(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
      putString(paramContext, "key_default_trans_card", paramString);
    while (true)
    {
      return;
      putString(paramContext, "key_default_bank_card", paramString);
    }
  }

  public static void setLongPressVolumeDownState(Context paramContext, String paramString)
  {
    LogUtils.d("setLongPressVolumeDownState:" + paramString);
    Settings.Secure.putString(paramContext.getContentResolver(), "key_long_press_volume_down", paramString);
  }

  public static void setLongPressVolumeDownStateToPay(Context paramContext)
  {
    setLongPressVolumeDownState(paramContext, "public_transportation_shortcuts");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.PrefUtils
 * JD-Core Version:    0.6.0
 */