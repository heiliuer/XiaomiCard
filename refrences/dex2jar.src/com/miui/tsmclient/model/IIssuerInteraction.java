package com.miui.tsmclient.model;

import android.content.Intent;

public abstract interface IIssuerInteraction
{
  public abstract void issue();

  public abstract void onPreIssueFinished(Intent paramIntent);

  public abstract void postIssue();

  public abstract void preIssue();

  public abstract boolean shouldAutoIssue();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.IIssuerInteraction
 * JD-Core Version:    0.6.0
 */