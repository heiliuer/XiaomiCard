package cn.com.fmsh.tsm.business.core;

import cv;
import java.util.Map;

public class ConfigKeyManager
{
  public ConfigKeyManager()
  {
    a();
  }

  public ConfigKey getConfigKey(int paramInt)
  {
    try
    {
      localConfigKey = (ConfigKey)this.a.get(Integer.valueOf(paramInt));
      return localConfigKey;
    }
    catch (cv localcv)
    {
      while (true)
        ConfigKey localConfigKey = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.ConfigKeyManager
 * JD-Core Version:    0.6.0
 */