package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import java.util.List;

public abstract interface ITLVValue
{
  public abstract ITLVObject findTLV(ByteArray paramByteArray)
    throws TagNotFoundException;

  public abstract List<ITLVObject> findTLVList(ByteArray paramByteArray)
    throws TagNotFoundException;

  public abstract ByteArray toBytes();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.ITLVValue
 * JD-Core Version:    0.6.0
 */