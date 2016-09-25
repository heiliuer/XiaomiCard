package com.miui.tsmclient.model;

public class BaseResponse
{
  public Object[] mDatas;
  public String mMsg;
  public int mResultCode = -1;

  public BaseResponse()
  {
  }

  public BaseResponse(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    this.mResultCode = paramInt;
    this.mMsg = paramString;
    this.mDatas = paramArrayOfObject;
  }

  public BaseResponse(int paramInt, Object[] paramArrayOfObject)
  {
    this.mResultCode = paramInt;
    this.mDatas = paramArrayOfObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.BaseResponse
 * JD-Core Version:    0.6.0
 */