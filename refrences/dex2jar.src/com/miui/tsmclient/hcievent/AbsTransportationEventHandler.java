package com.miui.tsmclient.hcievent;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;

public abstract class AbsTransportationEventHandler
  implements IHciEventHandler
{
  protected abstract HciEventInfo doHandleData(String paramString, long paramLong, ByteArray paramByteArray);

  public final HciEventInfo handleData(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0));
    for (HciEventInfo localHciEventInfo = new HciEventInfo(Coder.bytesToHexString(paramArrayOfByte1), paramLong, false); ; localHciEventInfo = doHandleData(Coder.bytesToHexString(paramArrayOfByte1), paramLong, ByteArray.wrap(paramArrayOfByte2)))
      return localHciEventInfo;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.AbsTransportationEventHandler
 * JD-Core Version:    0.6.0
 */