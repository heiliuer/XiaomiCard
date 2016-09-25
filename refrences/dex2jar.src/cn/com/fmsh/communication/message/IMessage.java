package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;

public abstract interface IMessage
{
  public static final int codeSize = 2;
  public static final int retCodeSize = 2;

  public abstract int addTag(ITag paramITag)
    throws FMCommunicationMessageException;

  public abstract int getCode();

  public abstract byte[] getRetCode();

  public abstract ITag getTag4Id(int paramInt)
    throws FMCommunicationMessageException;

  public abstract ITag getTag4Id(int paramInt1, int paramInt2)
    throws FMCommunicationMessageException;

  public abstract ITag getTag4Index(int paramInt)
    throws FMCommunicationMessageException;

  public abstract int getTagCount();

  public abstract int getTagCount4Id(int paramInt);

  public abstract boolean isValid();

  public abstract void setRetCode(byte[] paramArrayOfByte);

  public abstract int setVal(ITag paramITag, int paramInt)
    throws FMCommunicationMessageException;

  public abstract byte[] toBytes()
    throws FMCommunicationMessageException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.IMessage
 * JD-Core Version:    0.6.0
 */