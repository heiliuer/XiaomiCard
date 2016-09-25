package com.unionpay.tsmservice.data;

import android.text.TextUtils;

public class ResultCode
{
  public static final String ERROR_DETAIL_DEFAULT = "0000";
  public static final String ERROR_DETAIL_FORCE_UPDATE = "0019";
  public static final String ERROR_DETAIL_NETWORK = "0001";
  public static final String ERROR_DETAIL_NFC_NOT_ENABLE = "0009";
  public static final String ERROR_DETAIL_NOT_SUPPORT = "0004";
  public static final String ERROR_DETAIL_NO_PERMISSION = "0003";
  public static final String ERROR_DETAIL_SE_SERVICE_CONNTECT = "0010";
  public static final String ERROR_DETAIL_SIGNATURE_INVALID = "0015";
  public static final String ERROR_DETAIL_UNKNOWN_HOST = "0002";
  public static final String ERROR_DOWNLOAD_FILE = "10004";
  public static final String ERROR_INTERFACE_ENCRYPT_DATA = "10004";
  public static final String ERROR_INTERFACE_EXCHANGE_KEY = "10003";
  public static final String ERROR_INTERFACE_GET_ENCRYPT_DATA = "10036";
  public static final String ERROR_INTERFACE_GET_PUBLIC_KEY = "10002";
  public static final String ERROR_INTERFACE_INIT = "10001";
  public static final String ERROR_INTERFACE_RESULT_DECRYPT_FAIL = "10035";
  public static final String ERROR_INTERFACE_RESULT_ENCRYPT_FAIL = "10034";
  public static final String ERROR_LOCAL_BEGIN = "10000";
  public static final String ERROR_NETWORK = "10001";
  public static final String ERROR_RESPONSE_FORMAT = "10002";
  public static final String ERROR_SOURCE_ADDON = "0";
  public static final String ERROR_SOURCE_TSM = "1";
  public static final String ERROR_STORAGE_NOT_ENOUGHT = "10003";
  public static final String SUCCESS = "10000";

  public static String getResultCode(String paramString)
  {
    String str;
    if (!TextUtils.isEmpty(paramString))
      if ("0000".equals(paramString))
        str = "";
    while (true)
    {
      return str;
      if ("10001".equals(paramString))
      {
        str = "00001";
        continue;
      }
      if ("10017".equals(paramString))
      {
        str = "00004";
        continue;
      }
      if ("10024".equals(paramString))
      {
        str = "00009";
        continue;
      }
      if ("10021".equals(paramString))
      {
        str = "00010";
        continue;
      }
      if ("10004".equals(paramString))
      {
        str = "00001";
        continue;
      }
      if ("10010".equals(paramString))
      {
        str = "00001";
        continue;
      }
      if ("10016".equals(paramString))
      {
        str = "00001";
        continue;
      }
      str = "1" + paramString;
      continue;
      str = "00000";
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.data.ResultCode
 * JD-Core Version:    0.6.0
 */