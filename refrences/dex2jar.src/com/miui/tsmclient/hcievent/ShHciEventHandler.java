package com.miui.tsmclient.hcievent;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;

public class ShHciEventHandler extends AbsTransportationEventHandler
{
  protected HciEventInfo doHandleData(String paramString, long paramLong, ByteArray paramByteArray)
  {
    ByteArray localByteArray1 = paramByteArray.duplicate(4, 4);
    ByteArray localByteArray2 = paramByteArray.duplicate(0, 4);
    return new HciEventInfo(paramString, paramLong, Coder.bytesToInt(localByteArray1.toBytes()), -800 + Coder.bytesToInt(localByteArray2.toBytes()), false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.ShHciEventHandler
 * JD-Core Version:    0.6.0
 */