package cn.com.fmsh.communication.message.core;

import al;

public class MessageTagDefine
{
  public int getExist()
  {
    return this.d;
  }

  public int getMultiple()
  {
    return this.c;
  }

  public int getOrder()
  {
    return this.b;
  }

  public byte getTag()
  {
    return this.a;
  }

  public void setExist(int paramInt)
  {
    try
    {
      this.d = paramInt;
      label5: return;
    }
    catch (al localal)
    {
      break label5;
    }
  }

  public void setMultiple(int paramInt)
  {
    try
    {
      this.c = paramInt;
      label5: return;
    }
    catch (al localal)
    {
      break label5;
    }
  }

  public void setOrder(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (al localal)
    {
      break label5;
    }
  }

  public void setTag(byte paramByte)
  {
    try
    {
      this.a = paramByte;
      label5: return;
    }
    catch (al localal)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.core.MessageTagDefine
 * JD-Core Version:    0.6.0
 */