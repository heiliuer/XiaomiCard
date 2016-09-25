package com.miui.tsmclient.analytics;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.util.HashMap;
import java.util.Map;
import miui.analytics.Analytics;

public class AnalyticManager
{
  public static final String CATEGORY_BANK = "bank";
  public static final String CATEGORY_COMMON = "common";
  public static final String CATEGORY_MI_QUICK_PAY = "MIQuickPay";
  public static final String CATEGORY_NFC = "nfc";
  public static final String CATEGORY_PAY = "pay";
  public static final String EVENT_ID_CLICK_HELP = "click_help";
  public static final String EVENT_ID_CLICK_PURCHASE_RECORD = "click_purchase_record";
  public static final String EVENT_ID_INITESE = "initese";
  public static final String EVENT_ID_INSTALL_PREPARATION = "install_preparation";
  public static final String EVENT_ID_ISSUE = "issue";
  public static final String EVENT_ID_PAY = "pay";
  public static final String EVENT_ID_RECHARGE = "recharge";
  public static final String EVENT_PARAM_AID = "aid";
  public static final String EVENT_PARAM_CARD_TYPE = "card_type";
  public static final String EVENT_PARAM_ERROR_CODE = "error_code";
  public static final String EVENT_PARAM_EVENT_SOURCE = "event_source";
  public static final String EVENT_PARAM_FAIL_REASON = "fail_reason";
  public static final String EVENT_PARAM_HAS_ISSUE = "has_issue";
  public static final String EVENT_PARAM_ISSUE_BY_WHERE = "issue_source";
  public static final String EVENT_PARAM_IS_ENABLE = "is_enable";
  public static final String EVENT_PARAM_MIUI_ROM_TYPE = "miui_rom_type";
  public static final String EVENT_PARAM_MODEL = "model";
  public static final String EVENT_PARAM_PAY_PLUGIN_TYPE = "pay_plugin_type";
  public static final String EVENT_PARAM_PAY_RESULT = "pay_result";
  public static final String EVENT_PARAM_RECHARGE_AMOUNT = "recharge_amount";
  public static final String EVENT_PARAM_RECHARGE_BY_NFC = "recharge_by_nfc";
  public static final String EVENT_PARAM_SYSTEM_VERSION = "system_version";
  public static final String EVENT_TIME = "time";
  public static final String EVENT_TRADE_AMOUNT = "trade_amount";
  public static final String KEY_CARD_TYPE = "card_type";
  public static final String KEY_CLICK_HELP = "click_help";
  public static final String KEY_CLICK_PURCHASE_RECORD = "click_purchase_record";
  public static final String KEY_CREATE_ORDER = "create_order/%s";
  public static final String KEY_ENTER = "key_enter/%s";
  public static final String KEY_ERROR_CODE = "error_code";
  public static final String KEY_INIT_SE = "init_se";
  public static final String KEY_INSTALL_PREPARATION = "install_preparation";
  public static final String KEY_ISSUE_CARD = "issue_card";
  public static final String KEY_ISSUE_CARD_RESULT = "issue_card_result";
  public static final String KEY_ISSUE_SOURCE = "issue_source";
  public static final String KEY_NFC_EE_IO_EXCEPTION = "key_nfc_ee_io_exception";
  public static final String KEY_NFC_EE_RESTRICTED = "key_nfc_ee_restricted";
  public static final String KEY_NFC_RECHARGE = "nfc_recharge/%s";
  public static final String KEY_NFC_UNUSUAL_DISABLED = "key_nfc_unusual_disabled";
  public static final String KEY_NFC_UNUSUAL_DISABLED_RESTORE = "key_nfc_unusual_disabled_restore";
  public static final String KEY_OPERATION_FAILED = "operation_%s_failed";
  public static final String KEY_OPERATION_LAUNCH = "operation_%s_launch";
  public static final String KEY_OPERATION_SUCCESS = "operation_%s_success";
  public static final String KEY_PAY_RESULT = "pay_result";
  public static final String KEY_RECHARGE_AMOUNT = "recharge_amount/%s";
  public static final String PAY_PLUGIN_TYPE_MIPAY = "mipay";
  public static final String PAY_PLUGIN_TYPE_UNION_APK = "union_apk";
  public static final String UNION_PAY_RESULT_NOT_PAY = "not_pay";

  public static void bugReport(Context paramContext, String paramString, long paramLong)
  {
    Intent localIntent = new Intent("com.miui.klo.COMMON_LOG");
    localIntent.setPackage("com.miui.klo.bugreport");
    StringUtils.millsToTime(paramLong - 1000L, "MM-dd hh:mm:ss.mmm");
    localIntent.putExtra("name", paramString);
    localIntent.putExtra("logcatCmd", "*:V");
    paramContext.sendBroadcast(localIntent);
  }

  private void buildCommonParams(Map<String, String> paramMap)
  {
    paramMap.put("system_version", Build.VERSION.INCREMENTAL);
    paramMap.put("model", Build.MODEL);
    paramMap.put("miui_rom_type", SysUtils.getMIUIRomType());
  }

  public static AnalyticManager getInstance()
  {
    return Holder.INSTANCE;
  }

  private static void newBuildCommonParams(Map<String, String> paramMap)
  {
    paramMap.put("model", Build.MODEL);
    paramMap.put("miui_rom_type", SysUtils.getMIUIRomType());
  }

  public static void recordCalculateEvent(String paramString1, String paramString2, long paramLong)
  {
    recordCalculateEvent(paramString1, paramString2, paramLong, null);
  }

  public static void recordCalculateEvent(String paramString1, String paramString2, long paramLong, Map<String, String> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    newBuildCommonParams(paramMap);
    MiStatInterface.recordCalculateEvent(paramString1, paramString2, paramLong, paramMap);
  }

  public static void recordEvent(String paramString1, String paramString2)
  {
    recordEvent(paramString1, paramString2, null, null);
  }

  public static void recordEvent(String paramString1, String paramString2, Object paramObject)
  {
    recordEvent(paramString1, paramString2, paramObject, null);
  }

  private static void recordEvent(String paramString1, String paramString2, Object paramObject, Map<String, String> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    newBuildCommonParams(paramMap);
    if (paramObject == null)
      MiStatInterface.recordCountEvent(paramString1, paramString2, paramMap);
    while (true)
    {
      return;
      MiStatInterface.recordStringPropertyEvent(paramString1, paramString2, String.valueOf(paramObject));
    }
  }

  public static void recordEvent(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    recordEvent(paramString1, paramString2, null, paramMap);
  }

  public static void recordPageEnd()
  {
    MiStatInterface.recordPageEnd();
  }

  public static void recordPageStart(Context paramContext, String paramString)
  {
    MiStatInterface.recordPageStart(paramContext, paramString);
  }

  public void trackClick(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    paramMap.put("card_type", paramString2);
    trackEvent(paramContext, paramString1, paramMap);
  }

  public void trackEvent(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    Analytics.getInstance().startSession(paramContext);
    if (paramMap == null)
      paramMap = new HashMap();
    buildCommonParams(paramMap);
    Analytics.getInstance().trackEvent(paramString, paramMap);
    Analytics.getInstance().endSession();
  }

  public void trackPluginPayStatus(Context paramContext, String paramString1, boolean paramBoolean, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    paramMap.put("has_issue", String.valueOf(paramBoolean));
    paramMap.put("card_type", paramString1);
    paramMap.put("pay_plugin_type", paramString2);
    paramMap.put("pay_result", paramString3);
    trackEvent(paramContext, "pay", paramMap);
  }

  public void trackResult(Context paramContext, String paramString1, String paramString2, int paramInt, Map<String, String> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    paramMap.put("error_code", String.valueOf(paramInt));
    paramMap.put("card_type", paramString2);
    trackEvent(paramContext, paramString1, paramMap);
  }

  private static class Holder
  {
    static final AnalyticManager INSTANCE = new AnalyticManager(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.analytics.AnalyticManager
 * JD-Core Version:    0.6.0
 */