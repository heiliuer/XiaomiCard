package com.miui.tsmclient.ui;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NdefRecord;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.ui.bankcard.BankCardNumActivity;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.UiUtils;
import java.util.ArrayList;
import java.util.List;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.Fragment;
import miui.os.Build;

public class MainActivity extends Activity
{
  public static final String ACTION_ISSUE = "issue";
  public static final String ACTION_RECHARGE = "recharge";
  private static final String[] DANGEOURS_PERMS;
  public static final String PARAM_ACTION = "action";
  public static final String PARAM_CARD_GROUP_TYPE = "card_group_type";
  public static final String PARAM_SOURCE_CHANNEL = "source_channel";
  public static final String PARAM_TYPE = "type";
  private static final String PERMISSION_FINELOCATION = "android.permission.ACCESS_FINE_LOCATION";
  private static final String PERMISSION_READPHONE = "android.permission.READ_PHONE_STATE";
  private static final String PERMISSION_SECURE_SETTINGS = "android.permission.WRITE_SECURE_SETTINGS";
  private static final String PERMISSION_SMARTCARD = "org.simalliance.openmobileapi.SMARTCARD";
  protected static final int REQUEST_CODE_CARD_DETAIL = 2;
  protected static final int REQUEST_CODE_CARD_INTRO = 1;
  protected static final int REQUEST_CODE_CARD_RECHARGE = 3;
  private Fragment mFragment;
  private ImmersionMenu mImmersionMenu;

  static
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "org.simalliance.openmobileapi.SMARTCARD";
    arrayOfString[1] = "android.permission.READ_PHONE_STATE";
    arrayOfString[2] = "android.permission.ACCESS_FINE_LOCATION";
    arrayOfString[3] = "android.permission.WRITE_SECURE_SETTINGS";
    DANGEOURS_PERMS = arrayOfString;
  }

  private boolean checkPermissions()
  {
    int i = 1;
    ArrayList localArrayList = new ArrayList();
    for (String str : DANGEOURS_PERMS)
    {
      if (getPackageManager().checkPermission(str, getPackageName()) == 0)
        continue;
      localArrayList.add(str);
    }
    LogUtils.d("denied permission size = " + localArrayList.size());
    if ((!localArrayList.isEmpty()) && (!Build.IS_GLOBAL_BUILD))
      getPackageManager().grantRuntimePermission(getPackageName(), "org.simalliance.openmobileapi.SMARTCARD", new UserHandle(UserHandle.myUserId()));
    while (true)
    {
      return i;
      if (localArrayList.isEmpty())
        continue;
      String[] arrayOfString2 = new String[localArrayList.size()];
      for (int m = 0; m < arrayOfString2.length; m++)
        arrayOfString2[m] = ((String)localArrayList.get(m));
      requestPermissions(arrayOfString2, 0);
      i = 0;
    }
  }

  private String getNFCData()
  {
    Parcelable[] arrayOfParcelable = getIntent().getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
    if ((arrayOfParcelable != null) && (arrayOfParcelable.length > 0));
    for (String str = new String(((android.nfc.NdefMessage)arrayOfParcelable[0]).getRecords()[0].getPayload()); ; str = null)
      return str;
  }

  private boolean launchAppByAction(String paramString1, String paramString2, String paramString3)
  {
    int i = 0;
    Intent localIntent = new Intent();
    Bundle localBundle = getIntent().getExtras();
    if (localBundle == null)
      localBundle = new Bundle();
    CardInfo localCardInfo = CardInfoFactory.makeCardInfo(paramString2, null);
    if ((TextUtils.isEmpty(paramString1)) || (localCardInfo == null))
      return i;
    int j = 0;
    if (TextUtils.equals(paramString1, "issue"))
      if ((localCardInfo instanceof BankCardInfo))
        localIntent.setClass(this, BankCardNumActivity.class);
    while (true)
    {
      localBundle.putParcelable("card_info", localCardInfo);
      if (!TextUtils.isEmpty(paramString3))
        localBundle.putString("extra_source_channel", paramString3);
      localIntent.putExtras(localBundle);
      startActivityForResult(localIntent, j);
      i = 1;
      break;
      localIntent.setClass(this, CardIntroActivity.class);
      j = 1;
      continue;
      if (!TextUtils.equals(paramString1, "recharge"))
        break;
      localCardInfo.mHasIssue = true;
      localIntent.setClass(this, RechargeActivity.class);
      localBundle.putBoolean("need_check_card_status", true);
      j = 3;
    }
  }

  private void parseIntent()
  {
    Uri localUri = getIntent().getData();
    if ((localUri == null) && (getIntent().hasExtra("android.nfc.extra.NDEF_MESSAGES")))
      localUri = Uri.parse(getNFCData());
    String str2;
    if (localUri != null)
    {
      str2 = localUri.getQueryParameter("type");
      if (!TextUtils.isEmpty(str2))
        break label165;
      String str3 = localUri.getQueryParameter("card_group_type");
      if (!TextUtils.isEmpty(str3))
        getIntent().putExtra("card_group_type", Integer.valueOf(str3));
    }
    while (true)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
      {
        String str1 = localBundle.getString("key_default_card_type");
        if (!TextUtils.isEmpty(str1))
        {
          CardInfo localCardInfo = CardInfoFactory.makeCardInfo(str1, null);
          Intent localIntent = new Intent(this, CardDetailActivity.class);
          localIntent.setFlags(67108864);
          localBundle.putParcelable("card_info", localCardInfo);
          localIntent.putExtras(localBundle);
          startActivity(localIntent);
          finish();
        }
      }
      while (true)
      {
        return;
        label165: if (!launchAppByAction(localUri.getQueryParameter("action"), str2, localUri.getQueryParameter("source_channel")))
          break;
        continue;
        if (localBundle.getInt("key_card_quantity") > 0)
        {
          startActivity(new Intent(this, IssuedTransCardListActivity.class));
          finish();
          continue;
        }
        this.mFragment = new CardIssueListFragment();
        this.mFragment.setArguments(getIntent().getExtras());
        UiUtils.replaceFragment(this, this.mFragment, false);
      }
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.mFragment != null)
      this.mFragment.onActivityResult(paramInt1, paramInt2, paramIntent);
    while (true)
    {
      return;
      setResult(paramInt2, paramIntent);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ActionBar localActionBar = getActionBar();
    getWindow().addFlags(4194304);
    if (localActionBar != null)
    {
      localActionBar.setDisplayShowTitleEnabled(true);
      localActionBar.setDisplayHomeAsUpEnabled(true);
      this.mImmersionMenu = ((ImmersionMenu)getLayoutInflater().inflate(2130903041, null));
      this.mImmersionMenu.setVisibility(8);
      localActionBar.setCustomView(this.mImmersionMenu, new ActionBar.LayoutParams(-2, -2, 5));
    }
    if (checkPermissions())
      parseIntent();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (this.mFragment != null);
    for (boolean bool = this.mFragment.onCreateOptionsMenu(paramMenu); ; bool = super.onCreateOptionsMenu(paramMenu))
      return bool;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (this.mFragment != null);
    for (boolean bool = this.mFragment.onOptionsItemSelected(paramMenuItem); ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.mFragment != null)
      this.mFragment.onPrepareOptionsMenu(paramMenu);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    int j;
    if (paramInt == 0)
    {
      int i = 1;
      j = 0;
      if (j < paramArrayOfString.length)
      {
        if (paramArrayOfInt[j] == 0)
          break label37;
        i = 0;
      }
      if (i == 0)
        break label43;
      parseIntent();
    }
    while (true)
    {
      return;
      label37: j++;
      break;
      label43: finish();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.MainActivity
 * JD-Core Version:    0.6.0
 */