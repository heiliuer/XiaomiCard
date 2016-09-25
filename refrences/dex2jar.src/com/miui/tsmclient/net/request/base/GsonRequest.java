package com.miui.tsmclient.net.request.base;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.entity.gson.CommonResponseInfo;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.io.UnsupportedEncodingException;

public class GsonRequest<T extends CommonResponseInfo> extends BaseRequest<T>
{
  private Gson mGson = new Gson();
  private TypeToken<T> mTypeToken;

  public GsonRequest(int paramInt, String paramString, TypeToken<T> paramTypeToken, MiTsmCallback paramMiTsmCallback)
  {
    super(paramInt, paramString, paramMiTsmCallback);
    this.mTypeToken = paramTypeToken;
  }

  public GsonRequest(int paramInt, String paramString, Class<T> paramClass, MiTsmCallback paramMiTsmCallback)
  {
    this(paramInt, paramString, TypeToken.get(paramClass), paramMiTsmCallback);
  }

  public GsonRequest(String paramString, Class<T> paramClass, MiTsmCallback paramMiTsmCallback)
  {
    this(0, paramString, paramClass, paramMiTsmCallback);
  }

  protected boolean isSuccess(T paramT, BaseRequest.ErrorInfoWrapper paramErrorInfoWrapper)
  {
    if (paramT != null)
      if (paramT.getErrorCode() != 200);
    for (int i = 1; ; i = 0)
    {
      return i;
      paramErrorInfoWrapper.mErrorInfo = new BaseRequest.ErrorInfo(paramT.getErrorCode(), paramT.getErrorDesc());
    }
  }

  protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      String str = new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers));
      Response localResponse2 = Response.success(this.mGson.fromJson(str, this.mTypeToken.getType()), HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
      localResponse1 = localResponse2;
      return localResponse1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        Response localResponse1 = Response.error(new ParseError(localUnsupportedEncodingException));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.base.GsonRequest
 * JD-Core Version:    0.6.0
 */