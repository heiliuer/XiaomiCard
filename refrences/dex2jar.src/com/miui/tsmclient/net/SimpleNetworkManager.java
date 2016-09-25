package com.miui.tsmclient.net;

import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleNetworkManager
{
  public static final String KEY_DATA = "data";
  public static final String KEY_ERRCODE = "errCode";
  public static final String KEY_ERRDESC = "errDesc";

  private Object handleJsonResult(SimpleRequest.StringContent paramStringContent)
    throws AuthApiException
  {
    Object localObject = null;
    if (paramStringContent == null);
    while (true)
    {
      return localObject;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramStringContent.getBody());
        int i = localJSONObject.optInt("errCode");
        if (i == 200)
        {
          localObject = localJSONObject.opt("data");
          continue;
        }
        throw new AuthApiException(i, localJSONObject.optString("errDesc"));
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("failed to parse tsm api response", localJSONException);
      }
    }
  }

  public JSONObject queryMyInfo()
    throws IOException, AuthApiException
  {
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(null, "api/geography/queryMyInfo", AuthRequest.RespContentType.json).create();
    try
    {
      JSONObject localJSONObject = (JSONObject)sendGetRequest(localAuthRequest);
      if (localJSONObject == null);
      String str;
      for (Object localObject = "null"; ; localObject = str)
      {
        LogUtils.d("queryMyInfo api response: " + (String)localObject);
        return localJSONObject;
        str = localJSONObject.toString();
      }
    }
    catch (AuthApiException localAuthApiException)
    {
      LogUtils.e("queryMyInfo failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      if (localAuthApiException.mErrorCode == 205)
        localAuthApiException.mErrorCode = 2001;
    }
    throw localAuthApiException;
  }

  Object sendGetRequest(AuthRequest paramAuthRequest)
    throws IOException, AuthApiException
  {
    Object localObject1 = null;
    try
    {
      SimpleRequest.StringContent localStringContent = SimpleRequest.getAsString(paramAuthRequest.getRequestFullUrl(), paramAuthRequest.getParams(), paramAuthRequest.getCookies(), true);
      localObject1 = localStringContent;
      if (localObject1 != null)
        if (paramAuthRequest.getRespContentType() == AuthRequest.RespContentType.json)
        {
          localObject2 = handleJsonResult(localObject1);
          return localObject2;
        }
    }
    catch (AccessDeniedException localAccessDeniedException)
    {
      while (true)
        LogUtils.e("AccessDeniedException", localAccessDeniedException);
    }
    catch (AuthenticationFailureException localAuthenticationFailureException)
    {
      while (true)
      {
        LogUtils.e("AuthenticationFailureException.", localAuthenticationFailureException);
        continue;
        Object localObject2 = localObject1.getBody();
        continue;
        localObject2 = null;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.SimpleNetworkManager
 * JD-Core Version:    0.6.0
 */