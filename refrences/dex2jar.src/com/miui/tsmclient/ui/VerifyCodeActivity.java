package com.miui.tsmclient.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Fragment;

public class VerifyCodeActivity extends miui.app.Activity
{
  public static final String KEY_REQUEST_TYPE = "verify_code_request";
  public static final int REQUEST_CODE_VERIFY_SMS_CODE = 1;
  public static final int REQUEST_TYPE_COMMON = 0;
  public static final int REQUEST_TYPE_CUP = 2;

  public static void verifySmsCode(android.app.Activity paramActivity, Bundle paramBundle, CardInfo paramCardInfo)
  {
    if (paramBundle != null)
      paramBundle.putInt("verify_code_request", 0);
    Intent localIntent = new Intent(paramActivity, VerifyCodeActivity.class);
    localIntent.putExtras(paramBundle);
    paramActivity.startActivityForResult(localIntent, 1);
  }

  public void onBackPressed()
  {
    new AlertDialog.Builder(this).setMessage(2131296501).setPositiveButton(2131296281, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        VerifyCodeActivity.this.setResult(1);
        VerifyCodeActivity.this.finish();
      }
    }).setNegativeButton(2131296282, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
      }
    }).create().show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    VerifyCodeFragment localVerifyCodeFragment = new VerifyCodeFragment();
    localVerifyCodeFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, localVerifyCodeFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.VerifyCodeActivity
 * JD-Core Version:    0.6.0
 */