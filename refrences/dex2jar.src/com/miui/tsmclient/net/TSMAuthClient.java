package com.miui.tsmclient.net;

import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class TSMAuthClient
{
  private static final String KEY_DATA = "data";
  private static final String KEY_ERRCODE = "errCode";
  private static final String KEY_ERRDESC = "errDesc";

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

  Object sendGetRequest(AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException, IOException, AuthApiException
  {
    Object localObject;
    if (paramAccountInfo == null)
      localObject = null;
    while (true)
    {
      return localObject;
      SimpleRequest.StringContent localStringContent = SecureRequest.postAsString(paramAuthRequest.getRequestFullUrl(), paramAuthRequest.getParams(), paramAuthRequest.getCookies(), true, paramAccountInfo.getSecurity());
      if (paramAuthRequest.getRespContentType() == AuthRequest.RespContentType.json)
      {
        localObject = handleJsonResult(localStringContent);
        continue;
      }
      localObject = localStringContent.getBody();
    }
  }

  Object sendPostRequest(AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException, IOException, AuthApiException
  {
    Object localObject;
    if (paramAccountInfo == null)
      localObject = null;
    while (true)
    {
      return localObject;
      SimpleRequest.StringContent localStringContent = SecureRequest.postAsString(paramAuthRequest.getRequestFullUrl(), paramAuthRequest.getParams(), paramAuthRequest.getCookies(), true, paramAccountInfo.getSecurity());
      if (paramAuthRequest.getRespContentType() == AuthRequest.RespContentType.json)
      {
        localObject = handleJsonResult(localStringContent);
        continue;
      }
      localObject = localStringContent.getBody();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.TSMAuthClient
 * JD-Core Version:    0.6.0
 */