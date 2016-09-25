package com.miui.tsmclient.net;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.LntChildCity;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.Locale;
import miui.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

public class TSMAuthManager extends BaseAuthManager
{
  public JSONObject createOrder(Context paramContext, int paramInt, CardInfo paramCardInfo, Bundle paramBundle)
    throws IOException, AuthApiException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONObject localJSONObject;
    if (localAccountInfo == null)
      localJSONObject = null;
    Object localObject;
    while (true)
    {
      return localJSONObject;
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/sporder/create", AuthRequest.RespContentType.json).addParams("userId", localAccountInfo.getUserId()).addParams("feeId", String.valueOf(paramInt)).addParams("seId", SysUtils.getSeid(paramContext)).addParams("deviceModel", Build.MODEL).addParams("cardNo", paramCardInfo.mCardNo).addParams("balance", String.valueOf(paramCardInfo.mCardBalance)).addParams("deviceId", Coder.hashDeviceInfo(SysUtils.getImei(paramContext))).addParams("cplc", SysUtils.getCPLC(paramContext)).addParams("miuiRomType", SysUtils.getMIUIRomType()).addParams("miuiSystemVersion", Build.VERSION.INCREMENTAL).create();
      if ((paramBundle != null) && (paramBundle.containsKey("cityId")))
        localAuthRequest.addParams("cityId", String.valueOf(paramBundle.getInt("cityId", LntChildCity.GUANGZHOU.getCityId())));
      try
      {
        localJSONObject = (JSONObject)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONObject != null)
          break label241;
        localObject = "null";
        LogUtils.d("createOrder api response: " + (String)localObject);
      }
      catch (AuthApiException localAuthApiException)
      {
        if (localAuthApiException.mErrorCode != 205)
          break label255;
      }
    }
    localAuthApiException.mErrorCode = 2001;
    while (true)
    {
      throw localAuthApiException;
      label241: String str = localJSONObject.toString();
      localObject = str;
      break;
      label255: if (localAuthApiException.mErrorCode != 211)
        continue;
      localAuthApiException.mErrorCode = 1011;
    }
  }

  public String genPassRequest(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    String str;
    if (localAccountInfo == null)
      str = null;
    while (true)
    {
      return str;
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/user/bizPass", AuthRequest.RespContentType.json).addParams("type", paramString1).addParams("userId", paramString2).create();
      try
      {
        str = (String)sendRequest(paramContext, localAccountInfo, localAuthRequest);
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("genPassRequest failed, errorCode: " + localAuthApiException.mErrorCode + ", msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        str = null;
      }
    }
  }

  public JSONObject queryByOrderId(Context paramContext, String paramString)
    throws IOException, AuthApiException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONObject localJSONObject;
    if (localAccountInfo == null)
    {
      localJSONObject = null;
      return localJSONObject;
    }
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/sporder/queryByOrderId", AuthRequest.RespContentType.json).addParams("userId", localAccountInfo.getUserId()).addParams("orderId", paramString).create();
    while (true)
    {
      try
      {
        localJSONObject = (JSONObject)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONObject == null)
        {
          localObject = "null";
          LogUtils.d("queryByOrderId api response: " + (String)localObject);
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("queryByOrderId failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        if (localAuthApiException.mErrorCode != 205)
          continue;
        localAuthApiException.mErrorCode = 2001;
        throw localAuthApiException;
      }
      String str = localJSONObject.toString();
      Object localObject = str;
    }
  }

  public JSONArray queryByUserId(Context paramContext)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONArray localJSONArray;
    if (localAccountInfo == null)
    {
      localJSONArray = null;
      return localJSONArray;
    }
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/sporder/queryByUserId", AuthRequest.RespContentType.json).addParams("userId", localAccountInfo.getUserId()).addParams("seId", SysUtils.getSeid(paramContext)).addParams("deviceModel", Build.MODEL).create();
    while (true)
    {
      try
      {
        localJSONArray = (JSONArray)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONArray != null)
          break label154;
        localObject = "null";
        LogUtils.d("queryByUserId api response: " + (String)localObject);
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("queryByUserId failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localJSONArray = null;
      }
      break;
      label154: String str = localJSONArray.toString();
      Object localObject = str;
    }
  }

  public JSONArray queryCardProduct(Context paramContext, CardGroupType paramCardGroupType)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONArray localJSONArray;
    if (localAccountInfo == null)
    {
      localJSONArray = null;
      return localJSONArray;
    }
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/spcard/queryCardProduct", AuthRequest.RespContentType.json).addParams("userId", localAccountInfo.getUserId()).addParams("type", String.valueOf(paramCardGroupType.getId())).addParams("deviceModel", Build.MODEL).addParams("lang", Locale.getDefault().toString()).addParams("miuiRomType", SysUtils.getMIUIRomType()).addParams("miuiSystemVersion", Build.VERSION.INCREMENTAL).create();
    while (true)
    {
      try
      {
        localJSONArray = (JSONArray)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONArray != null)
          break label186;
        localObject = "null";
        LogUtils.d("queryCardProduct api response: " + (String)localObject);
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("queryCardProduct failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localJSONArray = null;
      }
      break;
      label186: String str = localJSONArray.toString();
      Object localObject = str;
    }
  }

  protected Object sendRequest(Context paramContext, AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws IOException, AuthApiException
  {
    return super.sendRequest(paramContext, paramAccountInfo, paramAuthRequest);
  }

  public JSONObject sendSms(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONObject localJSONObject;
    if (localAccountInfo == null)
    {
      localJSONObject = null;
      return localJSONObject;
    }
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/user/sendSms", AuthRequest.RespContentType.json).addParams("phone", paramString2).addParams("type", paramString1).create();
    while (true)
    {
      try
      {
        localJSONObject = (JSONObject)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONObject != null)
          break label146;
        localObject = "null";
        LogUtils.d("sendSms api response: " + (String)localObject);
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("sendSms failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localJSONObject = null;
      }
      break;
      label146: String str = localJSONObject.toString();
      Object localObject = str;
    }
  }

  public JSONObject validateSms(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    JSONObject localJSONObject;
    if (localAccountInfo == null)
    {
      localJSONObject = null;
      return localJSONObject;
    }
    AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/user/validateSms", AuthRequest.RespContentType.json).addParams("phone", paramString1).addParams("smsCode", paramString2).create();
    while (true)
    {
      try
      {
        localJSONObject = (JSONObject)sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (localJSONObject != null)
          break label149;
        localObject = "null";
        LogUtils.d("validateSms api response: " + (String)localObject);
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("validateSms failed with an apiException,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localJSONObject = null;
      }
      break;
      label149: String str = localJSONObject.toString();
      Object localObject = str;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.TSMAuthManager
 * JD-Core Version:    0.6.0
 */