package com.miui.tsmclient.pay;

public abstract interface IPluginAPKPayTool extends IPayTool
{
  public abstract void downloadPlugin();

  public abstract String getPluginName();

  public abstract String getPluginPackageName();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.IPluginAPKPayTool
 * JD-Core Version:    0.6.0
 */