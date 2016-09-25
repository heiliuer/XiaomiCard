package com.miui.tsmclient.net.request;

import com.miui.tsmclient.entity.gson.MyPositionInfo;
import com.miui.tsmclient.net.request.base.GsonRequest;
import com.miui.tsmclientsdk.MiTsmCallback;

public class MyPositionInfoRequest extends GsonRequest<MyPositionInfo>
{
  public MyPositionInfoRequest(MiTsmCallback paramMiTsmCallback)
  {
    super("api/geography/queryMyInfo", MyPositionInfo.class, paramMiTsmCallback);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.MyPositionInfoRequest
 * JD-Core Version:    0.6.0
 */