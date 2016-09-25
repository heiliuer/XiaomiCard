package com.miui.tsmclient.entity.gson;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ConfigInfo extends CommonResponseInfo
{
  public static final String BANNER_IMG = "BANNER_IMG";
  public static final String BANNER_LINK = "BANNER_LINK";
  public static final String DETAIL_FOOTER = "DETAIL_FOOTER";
  public static final String DETAIL_FOOTER_LINK = "DETAIL_FOOTER_LINK";
  public static final String USER_GUIDE = "USER_GUIDE";

  @SerializedName("data")
  private Map<String, ConfigItem> mConfigData;

  public Map<String, ConfigItem> getConfigMap()
  {
    return this.mConfigData;
  }

  public static class ConfigItem
  {

    @SerializedName("content")
    public String mContent;

    @SerializedName("key")
    public String mKey;

    @SerializedName("name")
    public String mName;

    @SerializedName("type")
    public String mType;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.gson.ConfigInfo
 * JD-Core Version:    0.6.0
 */