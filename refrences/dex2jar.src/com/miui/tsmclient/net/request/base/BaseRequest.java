package com.miui.tsmclient.net.request.base;

import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class BaseRequest<T> extends Request<T>
  implements Response.ErrorListener
{
  private static final String SERVER_ONLINE = "https://tsmapi.pay.xiaomi.com/";
  private static final String SERVER_STAGING = "http://staging.tsmapi.pay.xiaomi.com/";
  public static final boolean STAGING = new File("/data/system/xiaomi_account_preview").exists();
  protected Map<String, String> mCookies = new HashMap();
  protected Map<String, String> mHeaders = new HashMap();
  protected final MiTsmCallback mListener;
  protected Map<String, String> mParams = new HashMap();

  public BaseRequest(int paramInt, String paramString, MiTsmCallback paramMiTsmCallback)
  {
    super(paramInt, paramString, null);
    this.mListener = paramMiTsmCallback;
  }

  private String appendParamsToUrl(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("origin is not allowed null");
    StringBuilder localStringBuilder1 = new StringBuilder(paramString);
    try
    {
      Map localMap = getParams();
      String str1 = getParamsEncoding();
      localStringBuilder2 = new StringBuilder();
      Iterator localIterator = localMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localStringBuilder2.length() > 0)
          localStringBuilder2.append('&');
        localStringBuilder2.append(URLEncoder.encode((String)localEntry.getKey(), str1));
        localStringBuilder2.append('=');
        localStringBuilder2.append(URLEncoder.encode((String)localEntry.getValue(), str1));
      }
    }
    catch (AuthFailureError localAuthFailureError)
    {
      StringBuilder localStringBuilder2;
      LogUtils.e("append Url failed", localAuthFailureError);
      while (true)
      {
        return localStringBuilder1.toString();
        String str2 = localStringBuilder2.toString();
        if (TextUtils.isEmpty(str2))
          continue;
        if (!paramString.contains("?"))
          break;
        localStringBuilder1.append("&");
        localStringBuilder1.append(str2);
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        LogUtils.e("append Url failed", localUnsupportedEncodingException);
        continue;
        localStringBuilder1.append("?");
      }
    }
  }

  public BaseRequest<T> addCookie(String paramString1, String paramString2)
  {
    this.mCookies.put(paramString1, paramString2);
    return this;
  }

  public BaseRequest<T> addHeader(String paramString1, String paramString2)
  {
    this.mHeaders.put(paramString1, paramString2);
    return this;
  }

  public BaseRequest<T> addParams(String paramString1, String paramString2)
  {
    this.mParams.put(paramString1, paramString2);
    return this;
  }

  public void deliverError(VolleyError paramVolleyError)
  {
    onErrorResponse(paramVolleyError);
  }

  protected void deliverResponse(T paramT)
  {
    ErrorInfoWrapper localErrorInfoWrapper;
    if (this.mListener != null)
    {
      localErrorInfoWrapper = new ErrorInfoWrapper();
      if (!isSuccess(paramT, localErrorInfoWrapper))
        break label52;
      MiTsmCallback localMiTsmCallback3 = this.mListener;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramT;
      localMiTsmCallback3.onSuccess(0, arrayOfObject3);
    }
    while (true)
    {
      return;
      label52: ErrorInfo localErrorInfo = localErrorInfoWrapper.mErrorInfo;
      if (localErrorInfo != null)
      {
        MiTsmCallback localMiTsmCallback2 = this.mListener;
        int i = localErrorInfo.mErrorCode;
        String str = localErrorInfo.mErrorDescription;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramT;
        localMiTsmCallback2.onFail(i, str, arrayOfObject2);
        continue;
      }
      MiTsmCallback localMiTsmCallback1 = this.mListener;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramT;
      localMiTsmCallback1.onFail(9999, "", arrayOfObject1);
    }
  }

  public String getCacheKey()
  {
    return getServer() + super.getUrl();
  }

  public Response.ErrorListener getErrorListener()
  {
    return this;
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    return this.mHeaders;
  }

  protected Map<String, String> getParams()
    throws AuthFailureError
  {
    return this.mParams;
  }

  protected String getServer()
  {
    if (STAGING);
    for (String str = "http://staging.tsmapi.pay.xiaomi.com/"; ; str = "https://tsmapi.pay.xiaomi.com/")
      return str;
  }

  public String getUrl()
  {
    String str = getUrlWithoutParams();
    if (getMethod() == 0)
      str = appendParamsToUrl(str);
    return str;
  }

  public String getUrlWithoutParams()
  {
    return getServer() + super.getUrl();
  }

  protected boolean isSuccess(T paramT, ErrorInfoWrapper paramErrorInfoWrapper)
  {
    return true;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (this.mListener != null)
    {
      int i = 2;
      String str = "Network Error on " + super.getUrl();
      if ((paramVolleyError != null) && (paramVolleyError.networkResponse != null))
        i = paramVolleyError.networkResponse.statusCode;
      MiTsmCallback localMiTsmCallback = this.mListener;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramVolleyError;
      localMiTsmCallback.onFail(i, str, arrayOfObject);
    }
  }

  public static class ErrorInfoWrapper
  {
    public BaseRequest.ErrorInfo mErrorInfo;
  }

  public static class ErrorInfo
  {
    public int mErrorCode;
    public String mErrorDescription;

    public ErrorInfo(int paramInt, String paramString)
    {
      this.mErrorCode = paramInt;
      this.mErrorDescription = paramString;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.base.BaseRequest
 * JD-Core Version:    0.6.0
 */