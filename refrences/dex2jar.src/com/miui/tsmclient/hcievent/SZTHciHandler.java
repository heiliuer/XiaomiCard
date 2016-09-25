package com.miui.tsmclient.hcievent;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;

public class SZTHciHandler extends AbsTransportationEventHandler
{
  private static final int LENGTH_TOP_UP_DATA = 23;
  private static final int LENGTH_TRANSACTION_DATA = 17;

  protected HciEventInfo doHandleData(String paramString, long paramLong, ByteArray paramByteArray)
  {
    int i = paramByteArray.length();
    ByteArray localByteArray1 = ByteArray.EMPTY;
    ByteArray localByteArray2 = ByteArray.EMPTY;
    boolean bool = true;
    if (23 <= i)
    {
      localByteArray1 = paramByteArray.duplicate(1, 4);
      localByteArray2 = paramByteArray.duplicate(19, 4);
      if (!bool)
        break label149;
    }
    label149: for (int j = Coder.bytesToInt(localByteArray2.toBytes()) + -2147483648; ; j = 0)
    {
      HciEventInfo localHciEventInfo = new HciEventInfo(paramString, paramLong, Coder.bytesToInt(localByteArray1.toBytes()), j, false);
      localHciEventInfo.setTradeState(bool);
      return localHciEventInfo;
      if (17 != i)
        break;
      localByteArray1 = paramByteArray.duplicate(3, 4);
      localByteArray2 = paramByteArray.duplicate(7, 4);
      ByteArray localByteArray3 = paramByteArray.duplicate(1, 1);
      if (!ByteArray.equals(ByteArray.wrap(0), localByteArray3))
        break;
      bool = false;
      break;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.SZTHciHandler
 * JD-Core Version:    0.6.0
 */