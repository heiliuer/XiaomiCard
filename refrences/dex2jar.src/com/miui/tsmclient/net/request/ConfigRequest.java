package com.miui.tsmclient.net.request;

import android.content.Context;
import android.os.Build.VERSION;
import com.miui.tsmclient.entity.gson.ConfigInfo;
import com.miui.tsmclient.net.request.base.BaseRequest;
import com.miui.tsmclient.net.request.base.GsonSecureRequest;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.Locale;
import miui.os.Build;

public class ConfigRequest extends GsonSecureRequest<ConfigInfo>
{
  public ConfigRequest(Context paramContext, MiTsmCallback paramMiTsmCallback)
  {
    super(paramContext, 1, "api/login/clientConfig/queryConfig", ConfigInfo.class, paramMiTsmCallback);
    addParams("deviceModel", Build.MODEL).addParams("lang", Locale.getDefault().toString()).addParams("miuiRomType", SysUtils.getMIUIRomType()).addParams("miuiSystemVersion", Build.VERSION.INCREMENTAL);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.request.ConfigRequest
 * JD-Core Version:    0.6.0
 */