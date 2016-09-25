package com.miui.tsmclientsdk;

public class MiTsmConstants
{
  public static final String EXTRA_CARD_AID = "extra_card_aid";
  public static final String KEY_ACTIVE_CARD = "key_active_card";
  public static final String KEY_CARD_INFO = "key_card_info";
  public static final String KEY_CARD_QUANTITY = "key_card_quantity";
  public static final String KEY_CPLC_DATA = "key_cplc_data";
  public static final String KEY_DEFAULT_CARD_AID = "key_default_card_aid";
  public static final String KEY_DEFAULT_CARD_BALANCE = "key_default_card_balance";
  public static final String KEY_DEFAULT_CARD_TYPE = "key_default_card_type";
  public static final String KEY_FAST_CARD_BIND_ID = "bind_id";
  public static final String KEY_FAST_TAIL_NUM = "tail_num";
  public static final String KEY_RESULT_CODE = "key_result_code";
  public static final String KEY_RESULT_MSG = "key_result_msg";
  public static final String KEY_SE_BANK_CARD = "se_bank_card";
  public static final int MIPAY_BINDCARD_REQUEST_CODE = 10000;
  public static final int NO_ERROR = 0;
  public static final int SERVICE_STATUS_NFC_CLOSE = 4;
  public static final int SERVICE_STATUS_NO_NFC = 2;
  public static final int SERVICE_STATUS_NO_TSMCLIENT = 1;
  public static final int SERVICE_STATUS_SUPPORT_ALLAPP = 8;
  public static final int SPTSM_ID_CUP = 0;
  public static final String URI_TSMCLIENT = "https://tsmclient.miui.com";

  public class ErrorCode
  {
    public static final int CACELLED_BY_USER = 202;
    public static final int ERROR_UNKOWN = -1;
    public static final int INTERRUPTED = 3;
    public static final int INVALID_IMEI = 104;
    public static final int INVALID_PARAM = 5;
    public static final int INVALID_SEID = 105;
    public static final int IO_EXCEPTION = 1;
    public static final int NO_CARD_INSTALLED = 201;
    public static final int SERVER_RESPONSE_SUCCESS = 300;
    public static final int SE_NOT_INITIALED = 101;
    public static final int SUCCESS = 0;
    public static final int TAG_INVALID_TLV = 103;
    public static final int TAG_NOT_FOUND = 102;
    public static final int TIME_OUT = 2;
    public static final int UNAVAILABLE_SERVICE = 4;

    private ErrorCode()
    {
    }
  }

  public static enum CardType
  {
    static
    {
      CardType[] arrayOfCardType = new CardType[2];
      arrayOfCardType[0] = BANK;
      arrayOfCardType[1] = TRAFFIC;
      $VALUES = arrayOfCardType;
    }
  }

  public static enum OperationType
  {
    static
    {
      DELETE = new OperationType("DELETE", 1);
      OperationType[] arrayOfOperationType = new OperationType[2];
      arrayOfOperationType[0] = LOCK;
      arrayOfOperationType[1] = DELETE;
      $VALUES = arrayOfOperationType;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclientsdk.MiTsmConstants
 * JD-Core Version:    0.6.0
 */