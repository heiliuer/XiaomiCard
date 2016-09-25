package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import java.util.List;

class PrimitiveTLVValue
  implements ITLVValue
{
  private ByteArray mValue;

  PrimitiveTLVValue(ByteArray paramByteArray)
  {
    this.mValue = paramByteArray;
  }

  public ITLVObject findTLV(ByteArray paramByteArray)
    throws TagNotFoundException
  {
    throw new TagNotFoundException("it is a primitive tlv");
  }

  public List<ITLVObject> findTLVList(ByteArray paramByteArray)
    throws TagNotFoundException
  {
    throw new TagNotFoundException("it is a primitive tlv");
  }

  public ByteArray toBytes()
  {
    return this.mValue;
  }

  public String toString()
  {
    return this.mValue.toString();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.PrimitiveTLVValue
 * JD-Core Version:    0.6.0
 */