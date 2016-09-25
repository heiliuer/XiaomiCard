package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import java.io.InputStream;

public abstract interface IMessageHandler
{
  public abstract IMessage createMessage(int paramInt);

  public abstract IMessage createMessage(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract IMessage createMessage(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract IMessage createMessageAndRetCode(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract IMessage createMessageAndRetCode(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract ITag createTag(byte paramByte);

  public abstract ITag createTag(byte paramByte, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract Tag createTag(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException;

  public abstract boolean isLoad();

  public abstract int loadDefine(InputStream paramInputStream)
    throws FMCommunicationMessageException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.IMessageHandler
 * JD-Core Version:    0.6.0
 */