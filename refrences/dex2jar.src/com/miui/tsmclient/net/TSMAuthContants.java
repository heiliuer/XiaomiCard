package com.miui.tsmclient.net;

public class TSMAuthContants
{
  public static final String KEY_CITYID = "cityId";
  public static final String PARAM_BALANCE = "balance";
  public static final String PARAM_BANKCARD_TYPE = "bankcard_type";
  public static final String PARAM_CAPTURE_METHOD = "capture_method";
  public static final String PARAM_CARDNO = "cardNo";
  public static final String PARAM_CARD_BIN = "bin";
  public static final String PARAM_CARD_BIND_ID = "bindId";
  public static final String PARAM_CIPHER_CARD_INFO = "cipher_card_info";
  public static final String PARAM_CIPHER_CVV2_INFO = "cipher_cvv2_info";
  public static final String PARAM_CIPHER_PIN_INFO = "cipher_pin_info";
  public static final String PARAM_CITYID = "cityId";
  public static final String PARAM_CPLC = "cplc";
  public static final String PARAM_DEVICEID = "deviceId";
  public static final String PARAM_DEVICEMODEL = "deviceModel";
  public static final String PARAM_FEEID = "feeId";
  public static final String PARAM_ISSUER_CHANNEL = "issuer_channel";
  public static final String PARAM_ISSUER_ID = "issuer_id";
  public static final String PARAM_LANGUAGE = "lang";
  public static final String PARAM_MIUI_ROM_TYPE = "miuiRomType";
  public static final String PARAM_MIUI_SYSTEM_VERSION = "miuiSystemVersion";
  public static final String PARAM_ORDERID = "orderId";
  public static final String PARAM_PAN = "pan";
  public static final String PARAM_PHONE_NUM = "phone";
  public static final String PARAM_REQ = "req";
  public static final String PARAM_RISK_INFO = "risk_info";
  public static final String PARAM_SEID = "seId";
  public static final String PARAM_SERVICETOKEN = "serviceToken";
  public static final String PARAM_SMS_CODE = "smsCode";
  public static final String PARAM_TYPE = "type";
  public static final String PARAM_USERID = "userId";
  public static final String URL_BIZ_PASS = "api/login/user/bizPass";
  public static final String URL_CREATE_ORDER = "api/login/sporder/create";
  public static final String URL_CREATE_SESSION = "api/login/se/createSession";
  public static final String URL_DELETE_BANKCARD_INFO = "api/login/bankcard/deleteBankCardInfo";
  public static final String URL_DELETE_BANK_CARDS = "api/login/se/deleteAllBankCard";
  public static final String URL_ENROLL_UP_CARD = "api/login/se/enrollUPCard";
  public static final String URL_GEOGRAPHY_QUERY_MY_INFO = "api/geography/queryMyInfo";
  public static final String URL_GET_AVAILABLE_PROMO_TAGS = "api/login/user/getAvailablePromoTags";
  public static final String URL_GET_BANKCARD_INFO = "api/login/bankcard/getBankCardInfo";
  public static final String URL_GET_PROMO_CODE = "api/login/user/getPromoCode";
  public static final String URL_GET_SERVICE_STATUS = "api/login/user/getServiceStatus";
  public static final String URL_IS_BANKCARD_SERVICE_AVAILABLE = "api/login/se/isBankCardServiceAvailable";
  public static final String URL_PERSO_FINISH_NOTIFY = "api/login/se/persoFinishNotify";
  public static final String URL_PREPARE_PAY_APPLET = "api/login/se/preparePayApplet";
  public static final String URL_PROCESS_FINISH_NOTIFY = "api/login/se/processFinishNotify";
  public static final String URL_PROCESS_SE_RESPONSE = "api/login/se/processSeResponse";
  public static final String URL_PRODUCT_INFO = "api/login/se/queryProductInfo";
  public static final String URL_PULL_BUS_CARD_PERSO_DATA = "api/login/se/pullBusCardPersoData";
  public static final String URL_PULL_BUS_CARD_TOPUP_DATA = "api/login/se/pullBusCardTopUpData";
  public static final String URL_PULL_PERSO_DATA = "api/login/se/pullPersoData";
  public static final String URL_QUERY_BANK_CARD_INFO = "api/login/se/queryBankCardInfo";
  public static final String URL_QUERY_BY_ORDERID = "api/login/sporder/queryByOrderId";
  public static final String URL_QUERY_BY_USERID = "api/login/sporder/queryByUserId";
  public static final String URL_QUERY_CARD_PRODUCT = "api/login/spcard/queryCardProduct";
  public static final String URL_QUERY_CONFIG = "api/login/clientConfig/queryConfig";
  public static final String URL_QUERY_PAN = "api/login/se/queryPan";
  public static final String URL_REQUEST_VERTIFICATION_CODE = "api/login/se/requestVerificationCode";
  public static final String URL_SAVE_APP_KEY = "api/login/se/saveAppKey";
  public static final String URL_SAVE_BANKCARD_INFO = "api/login/bankcard/saveBankCardInfo";
  public static final String URL_SEND_SMS = "api/login/user/sendSms";
  public static final String URL_START_SE_OPERATION = "api/login/se/startSeOperation";
  public static final String URL_TOPUP = "api/login/se/topUp";
  public static final String URL_UPDATE_BANKCARD_INFO = "api/login/bankcard/updateBankCardInfo";
  public static final String URL_USER_SEND = "api/login/lstsm/userSend";
  public static final String URL_VALIDATE_SMS = "api/login/user/validateSms";
  public static final String URL_VERIFICATION_CODE = "api/login/se/verifyVerificationCode";
  public static final String URL_VERIFY_CARD_INFO = "api/login/se/verifyCardInfoForCMB";

  public static enum BussinessType
  {
    static
    {
      sbt = new BussinessType("sbt", 1);
      BussinessType[] arrayOfBussinessType = new BussinessType[2];
      arrayOfBussinessType[0] = sptc;
      arrayOfBussinessType[1] = sbt;
      $VALUES = arrayOfBussinessType;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.TSMAuthContants
 * JD-Core Version:    0.6.0
 */