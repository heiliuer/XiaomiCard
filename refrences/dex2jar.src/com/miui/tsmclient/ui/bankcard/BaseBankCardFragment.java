package com.miui.tsmclient.ui.bankcard;

import android.app.Activity;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.ui.BaseCardFragment;
import com.miui.tsmclient.util.UiUtils;
import com.unionpay.tsmservice.UPTsmAddon;

public class BaseBankCardFragment extends BaseCardFragment<BankCardInfo>
{
  protected void checkUPServiceStatus()
  {
    if (!UPTsmAddon.getInstance(getActivity()).isConnected())
    {
      UiUtils.showToast(getActivity(), 2131296475);
      getActivity().setResult(0);
      getActivity().finish();
    }
  }

  protected String getRoutingNotESEPrompt()
  {
    return getString(2131296307);
  }

  public void onResume()
  {
    super.onResume();
    checkFingerStatus();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BaseBankCardFragment
 * JD-Core Version:    0.6.0
 */