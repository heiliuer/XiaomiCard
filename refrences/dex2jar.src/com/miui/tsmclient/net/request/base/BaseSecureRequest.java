package com.miui.tsmclient.net.request.base;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.utils.AESCoder;
import com.xiaomi.accountsdk.utils.CryptCoder;
import com.xiaomi.accountsdk.utils.VersionUtils;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public abstract class BaseSecureRequest<T> extends BaseRequest<T>
{
  protected AccountInfo mAccountInfo;
  protected TSMAccountManager mAccountManager;
  protected CryptCoder mCoder;
  protected Context mContext;

  public BaseSecureRequest(Context paramContext, int paramInt, String paramString, MiTsmCallback paramMiTsmCallback)
  {
    super(paramInt, paramString, paramMiTsmCallback);
    this.mContext = paramContext;
    this.mAccountManager = new TSMAccountManager();
  }

  private void loadAccountInfo()
    throws AuthFailureError
  {
    if (this.mAccountInfo == null)
    {
      this.mAccountInfo = this.mAccountManager.loadAccountInfo(this.mContext);
      if (this.mAccountInfo == null)
        throw new AuthFailureError("Getting account info failed");
      this.mCoder = new AESCoder(this.mAccountInfo.getSecurity());
    }
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    loadAccountInfo();
    this.mCookies.put("userId", this.mAccountInfo.getUserId());
    this.mCookies.put("serviceToken", this.mAccountInfo.getServiceToken());
    this.mCookies.put("sdkVersion", VersionUtils.getVersion());
    this.mHeaders.put("Cookie", StringUtils.join(this.mCookies, "; "));
    return this.mHeaders;
  }

  protected Map<String, String> getParams()
    throws AuthFailureError
  {
    loadAccountInfo();
    this.mParams.put("userId", this.mAccountInfo.getUserId());
    String str;
    if (getMethod() == 0)
      str = "GET";
    try
    {
      while (true)
      {
        Map localMap = SecureRequest.encryptParams(str, getUrlWithoutParams(), this.mParams, this.mAccountInfo.getSecurity(), this.mCoder);
        return localMap;
        str = "POST";
      }
    }
    catch (CipherException localCipherException)
    {
    }
    throw new AuthFailureError(localCipherException.getMessage());
  }

  protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      String str = new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers));
      Response localResponse2 = parseNetworkResponse(paramNetworkResponse, this.mCoder.decrypt(str));
      localResponse1 = localResponse2;
      return localResponse1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localResponse1 = Response.error(new ParseError(localUnsupportedEncodingException));
    }
    catch (CipherException localCipherException)
    {
      while (true)
        Response localResponse1 = Response.error(new ParseError(localCipherException));
    }
  }

  protected abstract Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse, String paramString);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.base.BaseSecureRequest
 * JD-Core Version:    0.6.0
 */