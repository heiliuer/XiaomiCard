package com.miui.tsmclient.model;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.miui.tsmclient.SEInteractionService;
import com.miui.tsmclient.SEInteractionService.SEInteractionState;
import com.miui.tsmclient.SEInteractionService.SEInteractionState.Type;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.ui.CardIntroFragment;

public abstract class Issuer<T extends CardInfo>
  implements IIssuerInteraction
{
  protected T mCardInfo;
  protected Bundle mExtras;
  protected CardIntroFragment mFragment;

  protected Bundle buildIssueParams()
  {
    return null;
  }

  public Bundle buildQueryParams()
  {
    return null;
  }

  public SEInteractionService.SEInteractionState.Type getInteractionType()
  {
    return SEInteractionService.SEInteractionState.getInstance().getType();
  }

  public boolean hasCardIssuing()
  {
    return SEInteractionService.SEInteractionState.getInstance().isInstalling();
  }

  protected void init(CardIntroFragment paramCardIntroFragment, T paramT, Bundle paramBundle)
  {
    this.mFragment = paramCardIntroFragment;
    this.mCardInfo = paramT;
    this.mExtras = paramBundle;
  }

  public boolean isCurrentCardIssuing(String paramString)
  {
    return SEInteractionService.SEInteractionState.getInstance().isAppInstalling(paramString);
  }

  public void issue()
  {
    SEInteractionService.installCard(this.mFragment.getActivity(), this.mCardInfo, buildIssueParams());
  }

  public void postIssue()
  {
    this.mFragment.getHandler().sendEmptyMessage(4);
  }

  public void preIssue()
  {
    SEInteractionService.loadCard(this.mFragment.getActivity(), this.mCardInfo, buildIssueParams());
  }

  public void release()
  {
  }

  public void updateCardInfo(T paramT)
  {
    this.mCardInfo = paramT;
  }

  public static class IssuerFactory
  {
    public static Issuer makeIssuer(CardIntroFragment paramCardIntroFragment, CardInfo paramCardInfo, Bundle paramBundle)
    {
      PayableCardIssuer localPayableCardIssuer;
      if (paramCardInfo == null)
        localPayableCardIssuer = null;
      while (true)
      {
        return localPayableCardIssuer;
        localPayableCardIssuer = null;
        if (!TextUtils.equals(paramCardInfo.mCardType, "BANKCARD"))
          localPayableCardIssuer = new PayableCardIssuer();
        if (localPayableCardIssuer == null)
          continue;
        localPayableCardIssuer.init(paramCardIntroFragment, paramCardInfo, paramBundle);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.Issuer
 * JD-Core Version:    0.6.0
 */