package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class ArrayTLVValue
  implements ITLVValue
{
  private ByteArray mOrigin;
  private List<ITLVObject> mValues;

  public ArrayTLVValue(ByteArray paramByteArray, Collection<? extends ITLVObject> paramCollection)
  {
    this.mOrigin = paramByteArray;
    this.mValues = new ArrayList();
    this.mValues.addAll(paramCollection);
  }

  public ITLVObject findTLV(ByteArray paramByteArray)
    throws TagNotFoundException
  {
    Iterator localIterator = this.mValues.iterator();
    while (localIterator.hasNext())
    {
      ITLVObject localITLVObject = (ITLVObject)localIterator.next();
      if (ByteArray.equals(paramByteArray, localITLVObject.getTag()))
        return localITLVObject;
    }
    throw new TagNotFoundException("tag not found: " + Coder.bytesToHexString(paramByteArray.toBytes()));
  }

  public List<ITLVObject> findTLVList(ByteArray paramByteArray)
    throws TagNotFoundException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mValues.iterator();
    while (localIterator.hasNext())
    {
      ITLVObject localITLVObject = (ITLVObject)localIterator.next();
      if (!ByteArray.equals(paramByteArray, localITLVObject.getTag()))
        continue;
      localArrayList.add(localITLVObject);
    }
    if (localArrayList.size() > 0)
      return localArrayList;
    throw new TagNotFoundException("tag not found: " + Coder.bytesToHexString(paramByteArray.toBytes()));
  }

  public int getSize()
  {
    return this.mValues.size();
  }

  public ByteArray toBytes()
  {
    return this.mOrigin;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.ArrayTLVValue
 * JD-Core Version:    0.6.0
 */