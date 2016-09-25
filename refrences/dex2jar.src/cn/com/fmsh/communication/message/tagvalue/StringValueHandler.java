package cn.com.fmsh.communication.message.tagvalue;

public abstract interface StringValueHandler
{
  public abstract String getTagvalue(byte[] paramArrayOfByte);

  public abstract byte[] setTagValue(String paramString);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler
 * JD-Core Version:    0.6.0
 */