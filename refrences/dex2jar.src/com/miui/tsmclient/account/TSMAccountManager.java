package com.miui.tsmclient.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TSMAccountManager
{
  private static final String ACCOUNT_TYPE = "com.xiaomi";
  private static final int GET_AUTHTOKEN_TIMEOUT = 30000;
  private static final int MAX_RETRY_COUNT = 1;
  private static final String SERVICE_ID = "tsm-auth";
  private static volatile String sAuthToken;
  private ThreadLocal<Integer> mRetryCount = new ThreadLocal()
  {
    protected Integer initialValue()
    {
      return Integer.valueOf(0);
    }
  };

  public Account getXiaomiAccount(Context paramContext)
  {
    Object localObject = null;
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccountsByType("com.xiaomi");
    if (arrayOfAccount.length > 0)
      localObject = arrayOfAccount[0];
    return localObject;
  }

  public AccountInfo loadAccountInfo(Context paramContext)
  {
    Object localObject = null;
    Account localAccount = getXiaomiAccount(paramContext);
    if (localAccount == null);
    while (true)
    {
      return localObject;
      AccountInfo localAccountInfo = new AccountInfo();
      AccountManagerFuture localAccountManagerFuture;
      if ((paramContext instanceof Activity))
        localAccountManagerFuture = AccountManager.get(paramContext).getAuthToken(localAccount, "tsm-auth", null, (Activity)paramContext, null, null);
      try
      {
        while (true)
        {
          Bundle localBundle = (Bundle)localAccountManagerFuture.getResult(30000L, TimeUnit.MILLISECONDS);
          if ((!localAccountManagerFuture.isDone()) || (localBundle == null))
            break;
          localAccountInfo.setUserId(localBundle.getString("authAccount"));
          String str = localBundle.getString("authtoken");
          ExtendedAuthToken localExtendedAuthToken = ExtendedAuthToken.parse(str);
          sAuthToken = str;
          if (localExtendedAuthToken == null)
            break label176;
          this.mRetryCount.set(Integer.valueOf(0));
          localAccountInfo.setServiceToken(localExtendedAuthToken.authToken);
          localAccountInfo.setSecurity(localExtendedAuthToken.security);
          localObject = localAccountInfo;
          break;
          localAccountManagerFuture = AccountManager.get(paramContext).getAuthToken(localAccount, "tsm-auth", null, true, null, null);
        }
        label176: if (((Integer)this.mRetryCount.get()).intValue() < 1)
        {
          LogUtils.d("getAuthToken met an invalid token,so invalidateAuthToken.");
          this.mRetryCount.set(Integer.valueOf(1 + ((Integer)this.mRetryCount.get()).intValue()));
          resetAccount(paramContext);
          localObject = loadAccountInfo(paramContext);
          continue;
        }
        this.mRetryCount.set(Integer.valueOf(0));
        LogUtils.w("After invalidating the AuthToken,but result is still invalid.");
      }
      catch (OperationCanceledException localOperationCanceledException)
      {
        LogUtils.e("TSMAccountManager#loadAccountInfo invoke failed with an OperationCanceledException!So return null!", localOperationCanceledException);
      }
      catch (AuthenticatorException localAuthenticatorException)
      {
        LogUtils.e("TSMAccountManager#loadAccountInfo invoke failed with an AuthenticatorException!So return null!", localAuthenticatorException);
      }
      catch (IOException localIOException)
      {
        LogUtils.e("TSMAccountManager#loadAccountInfo invoke failed with an IOException!So return null!", localIOException);
      }
    }
  }

  public void resetAccount(Context paramContext)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.xiaomi", sAuthToken);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.account.TSMAccountManager
 * JD-Core Version:    0.6.0
 */