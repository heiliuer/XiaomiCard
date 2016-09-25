package com.miui.tsmclient.net;

import com.miui.tsmclient.account.AccountInfo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AuthRequest
{
  private static final String SERVER_ONLINE = "https://tsmapi.pay.xiaomi.com/";
  private static final String SERVER_STAGING = "http://staging1.tsmapi.pay.xiaomi.com/";
  public static final boolean STAGING = new File("/data/system/xiaomi_account_preview").exists();
  private Map<String, String> mCookies;
  private Map<String, String> mParams;
  private RespContentType mRespContentType;
  private String mUrl;

  private String getServer()
  {
    if (STAGING);
    for (String str = "http://staging1.tsmapi.pay.xiaomi.com/"; ; str = "https://tsmapi.pay.xiaomi.com/")
      return str;
  }

  public void addParams(String paramString1, String paramString2)
  {
    this.mParams.put(paramString1, paramString2);
  }

  public Map<String, String> getCookies()
  {
    return this.mCookies;
  }

  public Map<String, String> getParams()
  {
    return this.mParams;
  }

  public String getRequestFullUrl()
  {
    if (this.mUrl == null)
      this.mUrl = "";
    return getServer() + this.mUrl;
  }

  public RespContentType getRespContentType()
  {
    return this.mRespContentType;
  }

  public static class AuthRequestBuilder
  {
    private Map<String, String> mCookies = new HashMap();
    private Map<String, String> mParams = new HashMap();
    private AuthRequest.RespContentType mRespContentType;
    private String mUrl;

    public static AuthRequestBuilder newBuilder(AccountInfo paramAccountInfo, String paramString, AuthRequest.RespContentType paramRespContentType)
    {
      AuthRequestBuilder localAuthRequestBuilder = new AuthRequestBuilder();
      if (paramAccountInfo != null)
      {
        localAuthRequestBuilder.mCookies.put("userId", paramAccountInfo.getUserId());
        localAuthRequestBuilder.mCookies.put("serviceToken", paramAccountInfo.getServiceToken());
        localAuthRequestBuilder.mParams.put("userId", paramAccountInfo.getUserId());
      }
      localAuthRequestBuilder.mUrl = paramString;
      localAuthRequestBuilder.mRespContentType = paramRespContentType;
      return localAuthRequestBuilder;
    }

    public AuthRequestBuilder addParams(String paramString1, String paramString2)
    {
      this.mParams.put(paramString1, paramString2);
      return this;
    }

    public AuthRequest create()
    {
      AuthRequest localAuthRequest = new AuthRequest(null);
      AuthRequest.access$102(localAuthRequest, this.mParams);
      AuthRequest.access$202(localAuthRequest, this.mCookies);
      AuthRequest.access$302(localAuthRequest, this.mUrl);
      AuthRequest.access$402(localAuthRequest, this.mRespContentType);
      return localAuthRequest;
    }
  }

  public static enum RespContentType
  {
    static
    {
      RespContentType[] arrayOfRespContentType = new RespContentType[2];
      arrayOfRespContentType[0] = json;
      arrayOfRespContentType[1] = protobuf;
      $VALUES = arrayOfRespContentType;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.AuthRequest
 * JD-Core Version:    0.6.0
 */