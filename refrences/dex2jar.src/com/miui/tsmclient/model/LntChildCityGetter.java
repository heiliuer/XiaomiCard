package com.miui.tsmclient.model;

import com.miui.tsmclient.entity.LntChildCity;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.util.Arrays;
import java.util.List;

public class LntChildCityGetter
  implements IMultiLevelCardGetter<LntChildCity>
{
  public String getAId()
  {
    return Coder.bytesToHexString(APDUConstants.AID_LNT.toBytes());
  }

  public List<LntChildCity> getChildCardDescs()
  {
    return Arrays.asList(LntChildCity.values());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.LntChildCityGetter
 * JD-Core Version:    0.6.0
 */