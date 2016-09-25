package com.miui.tsmclient.net.request.base;

import android.content.Context;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.entity.gson.CommonResponseInfo;
import com.miui.tsmclientsdk.MiTsmCallback;

public class GsonSecureRequest<T extends CommonResponseInfo> extends BaseSecureRequest<T>
{
  private Gson mGson = new Gson();
  private TypeToken<T> mTypeToken;

  public GsonSecureRequest(Context paramContext, int paramInt, String paramString, TypeToken<T> paramTypeToken, MiTsmCallback paramMiTsmCallback)
  {
    super(paramContext, paramInt, paramString, paramMiTsmCallback);
    this.mTypeToken = paramTypeToken;
  }

  public GsonSecureRequest(Context paramContext, int paramInt, String paramString, Class<T> paramClass, MiTsmCallback paramMiTsmCallback)
  {
    this(paramContext, paramInt, paramString, TypeToken.get(paramClass), paramMiTsmCallback);
  }

  public GsonSecureRequest(Context paramContext, String paramString, Class<T> paramClass, MiTsmCallback paramMiTsmCallback)
  {
    this(paramContext, 0, paramString, paramClass, paramMiTsmCallback);
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

  protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse, String paramString)
  {
    return Response.success(this.mGson.fromJson(paramString, this.mTypeToken.getType()), HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.base.GsonSecureRequest
 * JD-Core Version:    0.6.0
 */