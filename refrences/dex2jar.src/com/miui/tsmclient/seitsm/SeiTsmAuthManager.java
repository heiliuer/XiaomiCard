package com.miui.tsmclient.seitsm;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.RiskInfo;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.net.AuthRequest.AuthRequestBuilder;
import com.miui.tsmclient.net.AuthRequest.RespContentType;
import com.miui.tsmclient.net.BaseAuthManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import miui.os.Build;

public class SeiTsmAuthManager extends BaseAuthManager
{
  public static final String EXTRA_AUTHENTICATION_CODE = "authentication_code";
  public static final String EXTRA_CITY_ID = "extra_city_id";
  public static final String EXTRA_SOURCE_CHANNEL = "extra_source_channel";

  public TsmRpcModels.TsmSessionInfo createSession(Context paramContext, CardInfo paramCardInfo)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      byte[] arrayOfByte = Coder.hexStringToBytes(SysUtils.getCPLC(paramContext));
      String str1 = SysUtils.getImei(paramContext);
      if ((arrayOfByte == null) || (TextUtils.isEmpty(str1)))
      {
        LogUtils.d("createSession invoke,but param is invalid.");
        continue;
      }
      TsmRpcModels.SeInfo localSeInfo = TsmRpcModels.SeInfo.newBuilder().setCplc(ByteString.copyFrom(arrayOfByte)).setDeviceType(Build.MODEL).setDeviceId(Coder.hashDeviceInfo(str1)).setActionSource(TsmRpcModels.ActionSource.APP_CLIENT).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/createSession", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localSeInfo.toByteArray())).create();
      try
      {
        String str2 = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str2))
          continue;
        TsmRpcModels.TsmSessionInfo localTsmSessionInfo = TsmRpcModels.TsmSessionInfo.parseFrom(Coder.decodeBase64ToByteArray(str2));
        localObject = localTsmSessionInfo;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("createSession failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.CommonResponse deleteAllBankCards(Context paramContext)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/deleteAllBankCard", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localCommonResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("deleteAllBankCards failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.VirtualCardInfoResponse enrollUPCard(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, CardInfo paramCardInfo, Bundle paramBundle)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    Object localObject;
    if (localAccountInfo == null)
      localObject = null;
    while (true)
    {
      return localObject;
      TsmRpcModels.EnrollUPCardRequest.Builder localBuilder = TsmRpcModels.EnrollUPCardRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId());
      localBuilder.setCardInfo(ByteString.copyFrom(paramBundle.getByteArray("cipher_card_info")));
      RiskInfo localRiskInfo = (RiskInfo)paramBundle.getParcelable("risk_info");
      TsmRpcModels.RiskInfo.Builder localBuilder1 = TsmRpcModels.RiskInfo.newBuilder();
      if (localRiskInfo != null)
        localBuilder1 = localRiskInfo.buildTSMRiskInfoBuilder();
      localBuilder.setRiskInfo(localBuilder1.build());
      label126: AuthRequest localAuthRequest;
      if (((BankCardInfo)paramCardInfo).mBankCardType == 1)
      {
        localBuilder.setBankCardType(TsmRpcModels.BankCardType.DEBIT);
        localBuilder.setPinInfo(ByteString.copyFrom(paramBundle.getByteArray("cipher_pin_info")));
        localBuilder.setCardNumber(((BankCardInfo)paramCardInfo).mBankCardPan);
        switch (paramBundle.getInt("bankcard_type"))
        {
        default:
          label168: TsmRpcModels.EnrollUPCardRequest localEnrollUPCardRequest = localBuilder.build();
          localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/enrollUPCard", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localEnrollUPCardRequest.toByteArray())).create();
        case 1:
        case 2:
        }
      }
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str))
        {
          TsmRpcModels.VirtualCardInfoResponse localVirtualCardInfoResponse = TsmRpcModels.VirtualCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
          localObject = localVirtualCardInfoResponse;
          continue;
          localBuilder.setBankCardType(TsmRpcModels.BankCardType.CREDIT);
          localBuilder.setCvn2Info(ByteString.copyFrom(paramBundle.getByteArray("cipher_cvv2_info")));
          break label126;
          localBuilder.setBankCardType(TsmRpcModels.BankCardType.DEBIT);
          break label168;
          localBuilder.setBankCardType(TsmRpcModels.BankCardType.CREDIT);
          break label168;
        }
        localObject = null;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("enrollUPCard failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.CommonResponse isBankCardServiceAvailable(Context paramContext)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.DeviceInfo localDeviceInfo = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(Build.MODEL).setLang(Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry()).setMiuiSystemVersion(Build.VERSION.INCREMENTAL).setMiuiRomType(SysUtils.getMIUIRomType()).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/isBankCardServiceAvailable", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localDeviceInfo.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localCommonResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("isBankCardServiceAvailable failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.CommonResponse persoFinishNotify(Context paramContext, CardInfo paramCardInfo, boolean paramBoolean, List<TsmRpcModels.SeAPDUResponseItem> paramList, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    Object localObject;
    if (localAccountInfo == null)
      localObject = null;
    while (true)
    {
      return localObject;
      TsmRpcModels.PersoFinishNotifyRequest.Builder localBuilder = TsmRpcModels.PersoFinishNotifyRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId());
      localBuilder.setOperationResult(paramBoolean);
      localBuilder.setVirtualCardReferenceId(((BankCardInfo)paramCardInfo).mVCReferenceId);
      localBuilder.addAllResponse(paramList);
      TsmRpcModels.PersoFinishNotifyRequest localPersoFinishNotifyRequest = localBuilder.build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/persoFinishNotify", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localPersoFinishNotifyRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str))
        {
          TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
          localObject = localCommonResponse;
          continue;
        }
        localObject = null;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("persoFinishNotify failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand preparePayApplet(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, CardInfo paramCardInfo, Bundle paramBundle)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    Object localObject;
    if ((localAccountInfo == null) || (paramBundle == null))
      localObject = null;
    while (true)
    {
      return localObject;
      TsmRpcModels.PreparePayAppletRequest.Builder localBuilder = TsmRpcModels.PreparePayAppletRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId());
      int i = paramBundle.getInt("bankcard_type");
      int j = paramBundle.getInt("issuer_channel");
      String str1 = paramBundle.getString("issuer_id");
      label92: TsmRpcModels.CardIssuerInfo.Builder localBuilder1;
      label112: AuthRequest localAuthRequest;
      switch (i)
      {
      default:
        localBuilder1 = TsmRpcModels.CardIssuerInfo.newBuilder();
        if (j != 2)
          break;
        localBuilder1.setCardIssueChannel(TsmRpcModels.CardIssueChannel.CMB);
        localBuilder1.setIssuerId(str1);
        localBuilder.setCardIssuerInfo(localBuilder1.build());
        TsmRpcModels.PreparePayAppletRequest localPreparePayAppletRequest = localBuilder.build();
        localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/preparePayApplet", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localPreparePayAppletRequest.toByteArray())).create();
      case 1:
      case 2:
      }
      try
      {
        String str2 = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str2))
        {
          TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str2));
          localObject = localTsmAPDUCommand;
          continue;
          localBuilder.setCardType(TsmRpcModels.BankCardType.DEBIT);
          break label92;
          localBuilder.setCardType(TsmRpcModels.BankCardType.CREDIT);
          break label92;
          if (j != 1)
            break label112;
          localBuilder1.setCardIssueChannel(TsmRpcModels.CardIssueChannel.UP);
          break label112;
        }
        localObject = null;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("preparePayApplet failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.CommonResponse processFinishNotify(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, boolean paramBoolean, TsmRpcModels.SeOperationType paramSeOperationType, List<TsmRpcModels.SeAPDUResponseItem> paramList)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.ProcessFinishNotifyRequest localProcessFinishNotifyRequest = TsmRpcModels.ProcessFinishNotifyRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setActionResult(paramBoolean).setActionType(paramSeOperationType).addAllResponse(paramList).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/processFinishNotify", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localProcessFinishNotifyRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localCommonResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("processFinishNotify failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand processSeResponse(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, List<TsmRpcModels.SeAPDUResponseItem> paramList)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if ((localAccountInfo == null) || (paramTsmSessionInfo == null));
    while (true)
    {
      return localObject;
      TsmRpcModels.SeAPDUResponse localSeAPDUResponse = TsmRpcModels.SeAPDUResponse.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).addAllResponse(paramList).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/processSeResponse", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localSeAPDUResponse.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localTsmAPDUCommand;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("processSeResponse failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand pullBusCardPersoData(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.PullBusCardDataRequest localPullBusCardDataRequest = TsmRpcModels.PullBusCardDataRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/pullBusCardPersoData", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localPullBusCardDataRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localTsmAPDUCommand;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("pullBusCardPersoData failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand pullBusCardTopUpData(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.PullBusCardDataRequest localPullBusCardDataRequest = TsmRpcModels.PullBusCardDataRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/pullBusCardTopUpData", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localPullBusCardDataRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localTsmAPDUCommand;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("pullBusCardTopUpData failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand pullPersoData(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, CardInfo paramCardInfo, Bundle paramBundle)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.PullPersoDataRequest localPullPersoDataRequest = TsmRpcModels.PullPersoDataRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/pullPersoData", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localPullPersoDataRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localTsmAPDUCommand;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("pullPersoData failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.QueryBankCardInfoResponse queryBankCardInfo(Context paramContext, String paramString)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.QueryBankCardInfoRequest.Builder localBuilder = TsmRpcModels.QueryBankCardInfoRequest.newBuilder().setCplc(SysUtils.getCPLC(paramContext));
      if (!TextUtils.isEmpty(paramString))
        localBuilder.setAid(paramString);
      TsmRpcModels.QueryBankCardInfoRequest localQueryBankCardInfoRequest = localBuilder.build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/queryBankCardInfo", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localQueryBankCardInfoRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.QueryBankCardInfoResponse localQueryBankCardInfoResponse = TsmRpcModels.QueryBankCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localQueryBankCardInfoResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("queryBankCardInfo failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.QueryPanResponse queryPan(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, Bundle paramBundle)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if (localAccountInfo == null);
    while (true)
    {
      return localObject;
      TsmRpcModels.QueryPanRequest.Builder localBuilder = TsmRpcModels.QueryPanRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId());
      AuthRequest localAuthRequest;
      if (paramBundle.getLong("bindId") != 0L)
      {
        localBuilder.setBindId(paramBundle.getLong("bindId"));
        TsmRpcModels.QueryPanRequest localQueryPanRequest = localBuilder.build();
        localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/queryPan", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localQueryPanRequest.toByteArray())).create();
      }
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.QueryPanResponse localQueryPanResponse = TsmRpcModels.QueryPanResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localQueryPanResponse;
        continue;
        localBuilder.setPan(paramBundle.getString("pan"));
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("queryPan failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.CommonResponse requestVerificationCode(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, String paramString)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if ((localAccountInfo == null) || (paramTsmSessionInfo == null) || (TextUtils.isEmpty(paramString)));
    while (true)
    {
      return localObject;
      TsmRpcModels.RequestVerificationCode localRequestVerificationCode = TsmRpcModels.RequestVerificationCode.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setVirtualCardReferenceId(paramString).setOptMethod(TsmRpcModels.OtpMethod.SMS).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/requestVerificationCode", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localRequestVerificationCode.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localCommonResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("request Sms failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand saveAppKey(Context paramContext, Bundle paramBundle, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if ((localAccountInfo == null) || (paramBundle == null));
    while (true)
    {
      return localObject;
      TsmRpcModels.SaveAppKeyRequest localSaveAppKeyRequest = TsmRpcModels.SaveAppKeyRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setAppName("tsmclient").setCpuModel(paramBundle.getString("cpuModel")).setTzId(paramBundle.getString("tzId")).setKeyAlg(paramBundle.getString("keyAlg")).setPkX(paramBundle.getString("pkX")).setPkY(paramBundle.getString("pkY")).setClientSign(paramBundle.getString("sign")).setDeviceModel(paramBundle.getString("deviceModel")).setDeviceId(SysUtils.getImei(paramContext)).setCplc(SysUtils.getCPLC(paramContext)).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/saveAppKey", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localSaveAppKeyRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localTsmAPDUCommand;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("saveAppKey failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }

  protected String sendRequest(Context paramContext, AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws IOException, AuthApiException
  {
    return (String)super.sendRequest(paramContext, paramAccountInfo, paramAuthRequest);
  }

  public TsmRpcModels.TsmAPDUCommand startSeOperation(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, TsmRpcModels.SeOperationType paramSeOperationType, CardInfo paramCardInfo, Bundle paramBundle)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    Object localObject;
    if (localAccountInfo == null)
      localObject = null;
    while (true)
    {
      return localObject;
      TsmRpcModels.SeOperation.Builder localBuilder = TsmRpcModels.SeOperation.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setType(paramSeOperationType);
      if ((paramCardInfo != null) && (!TextUtils.isEmpty(paramCardInfo.mAid)))
        localBuilder.setAid(paramCardInfo.mAid);
      if ((paramBundle != null) && (paramBundle.containsKey("authentication_code")))
      {
        String str4 = paramBundle.getString("authentication_code");
        if (TextUtils.isEmpty(str4))
        {
          localObject = null;
          continue;
        }
        localBuilder.setAuthenticationCode(ByteString.copyFrom(Coder.hexStringToBytes(str4)));
      }
      if ((paramBundle != null) && (paramBundle.containsKey("extra_city_id")))
      {
        String str3 = paramBundle.getString("extra_city_id");
        if (!TextUtils.isEmpty(str3))
          localBuilder.setCityId(str3);
      }
      if ((paramBundle != null) && (paramBundle.containsKey("extra_source_channel")))
      {
        String str2 = paramBundle.getString("extra_source_channel");
        if (!TextUtils.isEmpty(str2))
        {
          LogUtils.d("issuer sourceChannel: " + str2);
          localBuilder.setSourceChannel(str2);
        }
      }
      TsmRpcModels.SeOperation localSeOperation = localBuilder.build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/startSeOperation", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localSeOperation.toByteArray())).create();
      try
      {
        String str1 = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str1))
        {
          TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str1));
          localObject = localTsmAPDUCommand;
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("startSeOperation failed with an apiException, code:" + localAuthApiException.mErrorCode + ", msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.TsmAPDUCommand startTopupOperation(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, CardInfo paramCardInfo, String paramString, Bundle paramBundle)
    throws IOException
  {
    Object localObject;
    if ((TextUtils.isEmpty(paramString)) || (TextUtils.isEmpty(paramCardInfo.mAid)) || (paramTsmSessionInfo == null))
      localObject = null;
    while (true)
    {
      return localObject;
      AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
      if (localAccountInfo == null)
      {
        localObject = null;
        continue;
      }
      TsmRpcModels.TopUpOperation.Builder localBuilder1 = TsmRpcModels.TopUpOperation.newBuilder();
      TsmRpcModels.TopUpOperation.Builder localBuilder2 = localBuilder1.setSessionId(paramTsmSessionInfo.getSessionId()).setAid(paramCardInfo.mAid).setAuthenticationCode(ByteString.copyFrom(Coder.hexStringToBytes(paramString)));
      String str1;
      AuthRequest localAuthRequest;
      if (paramCardInfo.mCardNo == null)
      {
        str1 = "";
        localBuilder2.setCardNumber(str1).setBalance(paramCardInfo.mCardBalance);
        if ((paramBundle != null) && (paramBundle.containsKey("extra_city_id")))
        {
          String str3 = paramBundle.getString("extra_city_id");
          if (!TextUtils.isEmpty(str3))
            localBuilder1.setCityId(str3);
        }
        TsmRpcModels.TopUpOperation localTopUpOperation = localBuilder1.build();
        localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/topUp", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localTopUpOperation.toByteArray())).create();
      }
      try
      {
        String str2 = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str2))
        {
          TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str2));
          localObject = localTsmAPDUCommand;
          continue;
          str1 = paramCardInfo.mCardNo;
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("startTopupOperation failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.VirtualCardInfoResponse verifyCardInfo(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, String paramString, byte[] paramArrayOfByte, Bundle paramBundle)
    throws IOException
  {
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    Object localObject;
    if ((localAccountInfo == null) || (paramTsmSessionInfo == null) || (TextUtils.isEmpty(paramString)) || (paramArrayOfByte == null))
      localObject = null;
    while (true)
    {
      return localObject;
      RiskInfo localRiskInfo = (RiskInfo)paramBundle.getParcelable("risk_info");
      TsmRpcModels.RiskInfo.Builder localBuilder = TsmRpcModels.RiskInfo.newBuilder();
      if (localRiskInfo != null)
        localBuilder = localRiskInfo.buildTSMRiskInfoBuilder();
      TsmRpcModels.VerifyCardInfoRequest localVerifyCardInfoRequest = TsmRpcModels.VerifyCardInfoRequest.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setCardNumber(paramString).setCipherData(ByteString.copyFrom(paramArrayOfByte)).setRiskInfo(localBuilder.build()).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/verifyCardInfoForCMB", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localVerifyCardInfoRequest.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (!TextUtils.isEmpty(str))
        {
          TsmRpcModels.VirtualCardInfoResponse localVirtualCardInfoResponse = TsmRpcModels.VirtualCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
          localObject = localVirtualCardInfoResponse;
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("verifyCardInfo failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
        localObject = null;
      }
    }
  }

  public TsmRpcModels.CommonResponse verifyVerificationCode(Context paramContext, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, String paramString1, String paramString2)
    throws IOException
  {
    Object localObject = null;
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    if ((localAccountInfo == null) || (paramTsmSessionInfo == null) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
    while (true)
    {
      return localObject;
      TsmRpcModels.VerifyVerificationCode localVerifyVerificationCode = TsmRpcModels.VerifyVerificationCode.newBuilder().setSessionId(paramTsmSessionInfo.getSessionId()).setVirtualCardReferenceId(paramString1).setVerificationCode(paramString2).build();
      AuthRequest localAuthRequest = AuthRequest.AuthRequestBuilder.newBuilder(localAccountInfo, "api/login/se/verifyVerificationCode", AuthRequest.RespContentType.protobuf).addParams("userId", localAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(localVerifyVerificationCode.toByteArray())).create();
      try
      {
        String str = sendRequest(paramContext, localAccountInfo, localAuthRequest);
        if (TextUtils.isEmpty(str))
          continue;
        TsmRpcModels.CommonResponse localCommonResponse = TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(str));
        localObject = localCommonResponse;
      }
      catch (AuthApiException localAuthApiException)
      {
        LogUtils.e("validateSms failed with an apiExcepiton,code:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.seitsm.SeiTsmAuthManager
 * JD-Core Version:    0.6.0
 */