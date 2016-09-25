package com.tsmclient.smartcard;

import java.util.HashMap;

public class CardConstants
{
  public static final String ATC = "atc";
  public static final String BANK_CARD_ID = "A0000003330101";
  public static final String BANK_CARD_TYPE = "bank_card_type";
  public static final String BMAC = "001";
  public static final String CARD_SCHEME = "card_scheme";
  public static final String CITYU = "017";
  public static final String CQTK = "003";
  public static final int CURRENCY_CODE_AMERICAN = 9;
  public static final int CURRENCY_CODE_CHINA = 1;
  public static final int CURRENCY_CODE_HONGKONG = 2;
  public static final int CURRENCY_CODE_INDIA = 3;
  public static final int CURRENCY_CODE_JAPAN = 4;
  public static final int CURRENCY_CODE_KOREA = 5;
  public static final int CURRENCY_CODE_MACAU = 6;
  public static final int CURRENCY_CODE_MALAYSIA = 8;
  public static final int CURRENCY_CODE_SINGAPORE = 7;
  public static final int CURRENCY_CODE_TAIWAN = 10;
  public static final String E_BALANCE = "e_balance";
  public static final String E_BALANCE_LIMIT = "e_balance_limit";
  public static final String E_PER_LIMIT = "per_limit";
  public static final String GUIYANG = "009";
  public static final String HAERBIN = "012";
  public static final String HZT = "020";
  public static final String IN_BLACK_LIST = "in_black_list";
  public static final String IS_VALID_END_DATE = "is_valid_end_date";
  public static final String IS_VALID_START_DATE = "is_valid_start_date";
  public static final String KEY_ACCOUNT_NUM = "account_num";
  public static final String KEY_ID = "card_id";
  public static final String KEY_TAG = "nfc_tag";
  public static final String KEY_TYPE = "card_type";
  public static final String KUNMING = "011";
  public static final String LANZHOU = "006";
  public static final String LINGNAN = "013";
  public static final String NANCHANG = "015";
  public static final String NFC_PREFS_NAME = "nfc_read_card";
  public static final String NFC_TAG = "tag";
  public static final String NINGBO = "016";
  public static final String OCTOPUS = "008";
  public static final String OVER_DRAWN = "overdrawn";
  public static final String PREFS_KEY_LAST_TRAN = "last_trans_card";
  public static final String QINGDAO = "014";
  public static final int RESULT_INVALID = -999;
  public static final int SCHEME_UNIONPAY = 1;
  public static final int SCHEME_VISA = 2;
  public static final String SPTC = "002";
  public static final String STATUS = "status";
  public static final String SUZHOUTONG = "019";
  public static final String SZT = "004";
  public static final int TRADE_CONSUME = 1;
  public static final int TRADE_DEPOSIT = 3;
  public static final int TRADE_DEPOSIT_ECASH = 5;
  public static final String TRADE_LOG = "trade_log";
  public static final int TRADE_QUERY_BALANCE = 4;
  public static final int TRADE_RECHARGE = 2;
  public static final int TRADE_WITHDRAW = 6;
  public static final int TYPE_BANK_CARD = 1;
  public static final int TYPE_CREDIT = 2;
  public static final int TYPE_DEBIT = 1;
  public static final int TYPE_ECASH = 4;
  public static final int TYPE_QUASI_CREDIT = 3;
  public static final int TYPE_TRANS_CARD = 2;
  public static final String VALID_END = "valid_end";
  public static final String VALID_START = "valid_start";
  public static final String WHT = "005";
  public static final String XIAN = "010";
  public static final String ZHENGZHOU = "007";
  public static final String ZHOUSHAN = "018";
  public static HashMap<String, Integer> sCurrencyCodeMap = new HashMap();

  static
  {
    sCurrencyCodeMap.put("0156", Integer.valueOf(1));
    sCurrencyCodeMap.put("0344", Integer.valueOf(2));
    sCurrencyCodeMap.put("0356", Integer.valueOf(3));
    sCurrencyCodeMap.put("0392", Integer.valueOf(4));
    sCurrencyCodeMap.put("0410", Integer.valueOf(5));
    sCurrencyCodeMap.put("0446", Integer.valueOf(6));
    sCurrencyCodeMap.put("0702", Integer.valueOf(7));
    sCurrencyCodeMap.put("0458", Integer.valueOf(8));
    sCurrencyCodeMap.put("0840", Integer.valueOf(9));
    sCurrencyCodeMap.put("0901", Integer.valueOf(10));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.CardConstants
 * JD-Core Version:    0.6.0
 */