package com.miui.tsmclient.net;

import android.content.Context;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import java.io.IOException;

public class BaseAuthManager
{
  protected TSMAccountManager mAccountManager = new TSMAccountManager();
  private TSMAuthClient mAuthClient = new TSMAuthClient();

  private Object retry(Context paramContext, AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws IOException, AuthApiException
  {
    this.mAccountManager.resetAccount(paramContext);
    AccountInfo localAccountInfo = this.mAccountManager.loadAccountInfo(paramContext);
    try
    {
      Object localObject2 = this.mAuthClient.sendPostRequest(localAccountInfo, paramAuthRequest);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    catch (AuthApiException localAuthApiException)
    {
      throw localAuthApiException;
    }
    catch (Exception localException)
    {
      while (true)
      {
        LogUtils.e("An AuthenticationFailureException happened,so retry,but still failed!", localException);
        Object localObject1 = null;
      }
    }
  }

  protected Object sendRequest(Context paramContext, AccountInfo paramAccountInfo, AuthRequest paramAuthRequest)
    throws IOException, AuthApiException
  {
    if (!NetworkUtil.isConnected(paramContext))
      throw new IOException("network unreachable!");
    try
    {
      Object localObject2 = this.mAuthClient.sendPostRequest(paramAccountInfo, paramAuthRequest);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (AccessDeniedException localAccessDeniedException)
    {
      while (true)
      {
        LogUtils.e(paramAuthRequest.getClass().getName() + " send failed with an AccessDeniedException!So return null!", localAccessDeniedException);
        localObject1 = null;
      }
    }
    catch (AuthenticationFailureException localAuthenticationFailureException)
    {
      while (true)
      {
        LogUtils.e(paramAuthRequest.getClass().getName() + " send failed with an AuthenticationFailureException!Now retry", localAuthenticationFailureException);
        Object localObject1 = retry(paramContext, paramAccountInfo, paramAuthRequest);
      }
    }
    catch (InvalidResponseException localInvalidResponseException)
    {
      while (true)
        LogUtils.e(paramAuthRequest.getClass().getName() + " send failed with an InvalidResponseException!So return null!", localInvalidResponseException);
    }
    catch (CipherException localCipherException)
    {
      while (true)
        LogUtils.e(paramAuthRequest.getClass().getName() + " send failed with a CipherException!So return null!", localCipherException);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.BaseAuthManager
 * JD-Core Version:    0.6.0
 */