package com.miui.tsmclient.entity.gson;

import com.google.gson.annotations.SerializedName;

public class CommonResponseInfo
{

  @SerializedName("errCode")
  private int mErrorCode;

  @SerializedName("errDesc")
  private String mErrorDesc;

  public int getErrorCode()
  {
    return this.mErrorCode;
  }

  public String getErrorDesc()
  {
    return this.mErrorDesc;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.gson.CommonResponseInfo
 * JD-Core Version:    0.6.0
 */