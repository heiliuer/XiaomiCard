package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;

public abstract interface ITag
{
  public static final int idSize = 1;

  public abstract int addValue(int paramInt)
    throws FMCommunicationMessageException;

  public abstract int addValue(ITag paramITag)
    throws FMCommunicationMessageException;

  public abstract int addValue(String paramString)
    throws FMCommunicationMessageException;

  public abstract int addValue(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract byte[] getBytesVal()
    throws FMCommunicationMessageException;

  public abstract byte getId();

  public abstract int getIntVal()
    throws FMCommunicationMessageException;

  public abstract byte[] getItemBytesVal(int paramInt)
    throws FMCommunicationMessageException;

  public abstract int getItemCount()
    throws FMCommunicationMessageException;

  public abstract int getItemIntVal(int paramInt)
    throws FMCommunicationMessageException;

  public abstract String getItemStringVal(int paramInt)
    throws FMCommunicationMessageException;

  public abstract int getItemTagID(int paramInt)
    throws FMCommunicationMessageException;

  public abstract ITag getItemTagVal(int paramInt)
    throws FMCommunicationMessageException;

  public abstract ITag[] getItemTags()
    throws FMCommunicationMessageException;

  public abstract ETagType getItemType(int paramInt)
    throws FMCommunicationMessageException;

  public abstract String getStringVal()
    throws FMCommunicationMessageException;

  public abstract byte[] getTagValue();

  public abstract ETagType getType();

  public abstract boolean isValid();

  public abstract int setValue(Tag paramTag, int paramInt)
    throws FMCommunicationMessageException;

  public abstract byte[] toBytes()
    throws FMCommunicationMessageException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.ITag
 * JD-Core Version:    0.6.0
 */