package cn.com.fmsh.nfcos.client.service.constants;

import a5;

public abstract interface Constants
{
  public static final int SUCESS_CODE;
  public static final byte[] aid;

  static
  {
    try
    {
      byte[] arrayOfByte = new byte[9];
      arrayOfByte[0] = -96;
      arrayOfByte[4] = 3;
      arrayOfByte[5] = -122;
      arrayOfByte[6] = -104;
      arrayOfByte[7] = 7;
      arrayOfByte[8] = 1;
      aid = arrayOfByte;
      label40: return;
    }
    catch (a5 locala5)
    {
      break label40;
    }
  }

  public static abstract interface ApduHandlerType
  {
    public static final int NFC = 1;
    public static final int OMA;
  }

  public static abstract interface BusinessOrderType
  {
    public static final int ORDER_TYPE_ISSUE = 2;
    public static final int ORDER_TYPE_PROMOTION = 3;
    public static final int ORDER_TYPE_RECHARGE = 1;
    public static final int TRANSFER = 4;
    public static final int UNKNOW;
  }

  public static abstract interface CardAppStatus
  {
    public static final int STATUS_ACTIVATE = 5;
    public static final int STATUS_INSTALL = 3;
    public static final int STATUS_LOADED = 2;
    public static final int STATUS_NO_APP = 1;
    public static final int STATUS_PERSONALIZED = 4;
    public static final int STATUS_UNKNOWN = 10;
  }

  public static abstract interface CardAppType
  {
    public static final int CARD_APP_TYPE_LTN = 2;
    public static final int CARD_APP_TYPE_SH = 1;
  }

  public static abstract interface CardIoType
  {
    public static final int CARD_IO_TYPE_IN = 2;
    public static final int CARD_IO_TYPE_OUT = 1;
    public static final int CARD_IO_UNKNOW;
  }

  public static abstract interface ErrorCode
  {
    public static final int AIDL_PARAMETER_NULL = 9112;
    public static final int BUSINESS_HANDLE_FAIL = 99;
    public static final int ISSUER_FAIL_NO_ORDER = 9800;
    public static final int ISSUER_FAIL_ORDER_NO_PAID = 1102;
    public static final int NFC_TAG_INVAILD = 9110;
    public static final int OPEN_MOBILE_CHANNEL_INVAILD = 9100;
    public static final int OPEN_MOBILE_OPEN_CHANNEL_FAIL = 9104;
    public static final int OPEN_MOBILE_OPEN_READER_FAIL = 9102;
    public static final int OPEN_MOBILE_OPEN_SESSION_FAIL = 9103;
    public static final int OPEN_MOBILE_SESERVICE_NULL = 9101;
    public static final int TERMINAL_SECURITY_CODE_INVAILD = 9111;
  }

  public static abstract interface IssueFlag
  {
    public static final byte all = 1;
    public static final byte end4Activity = -124;
    public static final byte end4download = -127;
    public static final byte end4install = -126;
    public static final byte end4personalization = -125;
    public static final byte personalization = 2;
  }

  public static abstract interface IssueProcess
  {
    public static final int APPLIED = 0;
    public static final int APP_ACTIVATION = 50;
    public static final int APP_INSTALL = 30;
    public static final int APP_LOAD = 20;
    public static final int APP_LOCK = 60;
    public static final int APP_PERSONAL = 40;
    public static final int APP_REMOVE = 70;
    public static final int SSD_KEY_UPDATED = 10;
  }

  public static abstract interface OrderStatus
  {
    public static final int APPLY_FOR_REFUND = 6;
    public static final int DUBIOUS = 12;
    public static final int ERROR = 99;
    public static final int FAILURE = 4;
    public static final int HAS_PAIED = 2;
    public static final int HAS_REFUNDED = 7;
    public static final int INVALID = 13;
    public static final int NOT = 0;
    public static final int PAY_FAILURE = 9;
    public static final int RECHARGING = 11;
    public static final int REFUND_FAILURE = 8;
    public static final int SUCCESS = 3;
    public static final int UNKNOWN = 5;
  }

  public static abstract interface OrderType
  {
    public static final int BUSINESS = 2;
    public static final int MAIN = 1;
    public static final int PAY = 3;
  }

  public static abstract interface RechargeMode
  {
    public static final int MIPAY = 81;
    public static final int UNIONPAY = 3;
    public static final int UNIONPAY_CARD = 49;
  }

  public static abstract interface TagName4Attach
  {
    public static final byte AMOUNT_NAME = 1;
    public static final byte APP_NO_NAME = 3;
    public static final byte CHANNEL_NAME = 2;
    public static final byte CIN_NAME = 4;
    public static final byte MODULE_NAME = 5;
    public static final byte PRODUCT_NAME = 1;
    public static final byte SEID_NAME = 3;
  }

  public static abstract interface TradeType
  {
    public static final int BUS = 1;
    public static final int COMPOSITE_CONSUMPTION = 16;
    public static final int CONSUMPTION = 15;
    public static final int EXPRESSWAY = 9;
    public static final int FERRY = 7;
    public static final int GAS_STATION = 11;
    public static final int MAGLEV = 5;
    public static final int ONLINE_CONSUMPTION = 13;
    public static final int ONLINE_RECHARGE = 12;
    public static final int OTHERS = 14;
    public static final int PARK = 10;
    public static final int PRIVILEGE = 2;
    public static final int RECHARGE = 6;
    public static final int SUBWAY_CONSUMPTION = 3;
    public static final int SUBWAY_UPDATE = 4;
    public static final int TAXI = 8;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.constants.Constants
 * JD-Core Version:    0.6.0
 */