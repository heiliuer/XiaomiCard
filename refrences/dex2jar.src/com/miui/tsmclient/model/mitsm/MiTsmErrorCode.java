package com.miui.tsmclient.model.mitsm;

public class MiTsmErrorCode
{
  public static final int APPLY_TIMES_EXCEED_LIMIT = 3603;
  public static final int APP_APPLY_FAILED = 4006;
  public static final int APP_DUPLICATE_APPLY = 4009;
  public static final int APP_DUPLICATE_DELETE = 4008;
  public static final int APP_NOT_EXIST = 4005;
  public static final int BANK_CARD_NOT_SUPPORTED = 10014;
  public static final int BANK_SYSTEM_BUSYING = 10003;
  public static final int BIND_CARD_APPLET_UPDATE = 1002001;
  public static final int CARD_NOT_SUPPORT = 4014;
  public static final int CARD_PRODUCAT_INFO_NOT_EXIST = 4012;
  public static final int CARD_STATUS_NO_PRIVILEGE_3 = 3617;
  public static final int CARD_TYPE_NO_APPLY_PRIVILEGE_1 = 3608;
  public static final int ERROR_ISSUE_DUPLICATE = 1134;
  public static final int ERROR_NO_STOCK = 1126;
  public static final int ERROR_RECHARGE_DUPLICATE = 1135;
  public static final int ERROR_RECHARGE_FAILED_AND_REFUND = 1133;
  public static final int ERROR_RECHARGE_FAILED_AND_UNKNOWN = 1130;
  public static final int FAILED_ACTIVATE_CARD = 3618;
  public static final int FAILED_ACTIVATE_CARD_NO_RETRY = 3619;
  public static final int FAILED_TIME_EXCEED_LIMIT = 3605;
  public static final int FPAN_DUPLICATED_APPLY = 3317;
  public static final int INVALID_MSG_FORMAT = 4002;
  public static final int INVALID_OTP_METHOD = 4016;
  public static final int ISSUER_NOT_EXIST = 4015;
  public static final int MAP_RELATIONSHIP_FORBID_THE_OPERATION = 4011;
  public static final int NOT_SUPPORT_THE_TRADE = 4001;
  public static final int NULL_INPUT_INFOAMATION = 3106;
  public static final int ORGANIZATION_INFO_NOT_EXIST = 4013;
  public static final int OTP_INFO_EXPIRE = 3614;
  public static final int OTP_INFO_REVERIFY = 3613;
  public static final int OTP_INFO_VERIFY_FAILED = 3612;
  public static final int OTP_SEND_MSG_FAILED = 4018;
  public static final int OTP_SERVICE_UNAVAILABLE = 4017;
  public static final int PERSO_DATA_NOT_READY = 10048;
  public static final int SE_CARD_NOT_EXIST = 4010;
  public static final int SE_NOT_REGISTER = 4004;
  public static final int SUCCESS = 0;
  public static final int SYSTEM_ERROR = 4003;
  public static final int TOPUP_DATA_NOT_READY = 10050;
  public static final int TOTAL_APPLY_NUM_EXCEED_LIMIT = 10049;
  public static final int USER_ACCOUNT_EXPIRE = 3609;
  public static final int USER_ACCOUNT_NOT_EXIST = 3604;
  public static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE_2 = 3606;
  public static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE_3 = 3616;
  public static final int USER_ACOUNT_IN_BLANK_LIST = 3611;
  public static final int USER_CARD_VERIFY_FAILED = 3602;
  public static final int USER_IDENTITY_VERIFY_FAILED = 3601;
  public static final int USER_NOT_RESERVE_PHONE_NUM = 3610;

  public static int format(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    default:
      i = 1000000 + paramInt;
    case 3602:
    case 3609:
    case 3610:
    case 3612:
    case 3614:
    case 3603:
    case 3605:
    case 3608:
    case 3617:
    case 3606:
    case 3616:
    case 3613:
    case 3604:
    case 4014:
    case 10014:
    case 3601:
    case 10048:
    case 3106:
    case 3611:
    case 3317:
    case 4009:
    case 10049:
    case 3618:
    case 3619:
    case 1126:
    case 0:
    case 1002001:
    case 10003:
    }
    while (true)
    {
      return i;
      i = 3001;
      continue;
      i = 3002;
      continue;
      i = 3003;
      continue;
      i = 3004;
      continue;
      i = 3005;
      continue;
      i = 3008;
      continue;
      i = 3009;
      continue;
      i = 3020;
      continue;
      i = 3019;
      continue;
      i = 3010;
      continue;
      i = 3023;
      continue;
      i = 3011;
      continue;
      i = 3012;
      continue;
      i = 3013;
      continue;
      i = 3014;
      continue;
      i = 3007;
      continue;
      i = 3015;
      continue;
      i = 3016;
      continue;
      i = 3017;
      continue;
      i = 3018;
      continue;
      i = 3021;
      continue;
      i = 3022;
      continue;
      i = 2001;
      continue;
      i = 0;
      continue;
      i = 3024;
      continue;
      i = 3025;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.MiTsmErrorCode
 * JD-Core Version:    0.6.0
 */