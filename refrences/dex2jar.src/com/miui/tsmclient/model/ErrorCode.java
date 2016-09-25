package com.miui.tsmclient.model;

import android.content.Context;
import android.content.res.Resources;
import com.tsmclient.smartcard.Coder;

public class ErrorCode
{
  public static final int APPLY_TIMES_EXCEED_LIMIT = 3008;
  public static final int BANK_CARD_NOT_SUPPORTED = 3013;
  public static final int BANK_SYSTEM_BUSYING = 3025;
  public static final int BIND_CARD_APPLET_UPDATE = 3024;
  public static final int CARD_STATUS_ERROR = 3019;
  public static final int CARD_TYPE_ERROR = 3020;
  public static final int CARD_VERIFY_FAILED = 3001;
  public static final int CONTACT_CUSTOMER_SERVICE = 3022;
  public static final int DEFAULT_VALUE = -1;
  public static final int ERROR_ACCOUNT_LOCKED = 1009;
  public static final int ERROR_AMOUNT_LARGER_THAN_TOTAL_AMOUNT = 1008;
  public static final int ERROR_AUTH_FAILED = 7;
  public static final int ERROR_BALANCE_SMALLER_THAN_MIN_BALANCE = 1013;
  public static final int ERROR_CARD_CONFLICT = 2000;
  public static final int ERROR_CARD_INVALID = 2002;
  public static final int ERROR_CARD_NO_STOCK = 2001;
  public static final int ERROR_CLIENT_FORCE_INTERRUPT = 6;
  public static final int ERROR_CLIENT_INVALID_PARAM = 1;
  public static final int ERROR_CONFLICT_APP = 9;
  public static final int ERROR_DUPLICAT_PAY = 1000;
  public static final int ERROR_HANDLE_UNSOLVED_ORDER_FAILED = 1003;
  public static final int ERROR_HAS_NO_PROMOTION_RESOURCE = 1002;
  public static final int ERROR_LOGIN_FAILED = 5;
  public static final int ERROR_MIUI_VERSION_RESTRICTED = 1011;
  public static final int ERROR_NETWORK = 2;
  public static final int ERROR_NFC = 10;
  public static final int ERROR_NOT_GET_SERVICE = 3;
  public static final int ERROR_ORDER_HANDLE_UNFINISH = 1010;
  public static final int ERROR_ORDER_STATE_NEED_CONFIRM = 1004;
  public static final int ERROR_PAY_AMOUNT_TOO_LARGE = 1007;
  public static final int ERROR_PAY_AMOUNT_TOO_SMALL = 1006;
  public static final int ERROR_PLUGIN_NOT_FOUND = 1005;
  public static final int ERROR_RECHARGE_FAILED = 1001;
  public static final int ERROR_RECHARGE_FAILED_AND_REFUND = 1012;
  public static final int ERROR_REMOTE_FAILED = 4;
  public static final int ERROR_TRANSMIT_APDU = 8;
  public static final int ERROR_UNKNOWN = 9999;
  public static final int FAILED_TIME_EXCEED_LIMIT = 3009;
  public static final int FPAN_DUPLICATED_APPLY = 3017;
  public static final int NULL_KEY_INFOMATION = 3015;
  public static final int OTP_INFO_EXPIRE = 3005;
  public static final int OTP_INFO_REVERIFY = 3011;
  public static final int OTP_INFO_VERIFY_FAILED = 3004;
  public static final int PERSO_DATA_NOT_READY = 3007;
  public static final int REJECT_UNRESTRICT_ESE = 3026;
  public static final int RETRY_OR_CONTACT_CUSTOMER_SERVICE = 3021;
  public static final int SUCCESS = 0;
  public static final int TOTAL_APPLY_NUM_EXCEED_LIMIT = 3018;
  public static final int USER_ACCOUNT_EXPIRE = 3002;
  public static final int USER_ACCOUNT_IN_BLANK_LIST = 3016;
  public static final int USER_ACCOUNT_NOT_EXIST = 3012;
  public static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE = 3010;
  public static final int USER_IDENTITY_VERIFY_FAILED = 3014;
  public static final int USER_NOT_RESERVE_PHONE_NUM = 3003;
  public static final int USER_NO_APPLY_PRIVILEGE = 3023;

  public static String findText(Context paramContext, int paramInt)
  {
    String str;
    if (paramContext == null)
      str = null;
    while (true)
    {
      return str;
      if (paramInt == 0)
      {
        str = paramContext.getResources().getString(2131296301);
        continue;
      }
      switch (paramInt)
      {
      default:
        if (paramInt - 1000000 <= 0)
          break;
      case 2:
      case 10:
      case 1001:
      case 1002:
      case 3001:
      case 3015:
      case 3002:
      case 3003:
      case 3004:
      case 3005:
      case 3008:
      case 3009:
      case 3010:
      case 3011:
      case 3012:
      case 3013:
      case 3014:
      case 3016:
      case 3017:
      case 3018:
      case 3022:
      case 3021:
      case 3019:
      case 3020:
      case 3023:
      case 3025:
      }
      for (int i = paramInt - 1000000; ; i = paramInt)
      {
        if ((i != 10041) && (Coder.sizeOfInt(i) != 4))
          break label612;
        str = paramContext.getString(2131296302) + " : " + paramInt;
        break;
        str = paramContext.getResources().getString(2131296299);
        break;
        str = paramContext.getResources().getString(2131296300);
        break;
        str = paramContext.getResources().getString(2131296303);
        break;
        str = paramContext.getResources().getString(2131296376);
        break;
        str = paramContext.getResources().getString(2131296494);
        break;
        str = paramContext.getResources().getString(2131296495);
        break;
        str = paramContext.getResources().getString(2131296496);
        break;
        str = paramContext.getResources().getString(2131296443);
        break;
        str = paramContext.getResources().getString(2131296445);
        break;
        str = paramContext.getResources().getString(2131296446);
        break;
        str = paramContext.getResources().getString(2131296447);
        break;
        str = paramContext.getResources().getString(2131296448);
        break;
        str = paramContext.getResources().getString(2131296452);
        break;
        str = paramContext.getResources().getString(2131296453);
        break;
        str = paramContext.getResources().getString(2131296473);
        break;
        str = paramContext.getResources().getString(2131296454);
        break;
        str = paramContext.getResources().getString(2131296455);
        break;
        str = paramContext.getString(2131296456);
        break;
        str = paramContext.getResources().getString(2131296457);
        break;
        str = paramContext.getString(2131296459);
        break;
        str = paramContext.getString(2131296458);
        break;
        str = paramContext.getString(2131296450);
        break;
        str = paramContext.getString(2131296451);
        break;
        str = paramContext.getString(2131296449);
        break;
        str = paramContext.getString(2131296462);
        break;
      }
      label612: str = paramContext.getString(2131296301) + " : " + paramInt;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.ErrorCode
 * JD-Core Version:    0.6.0
 */