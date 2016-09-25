package cn.com.fmsh.tsm.business.bean;

import b8;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;

public class OrderInfo
{
  public byte[] getOrder()
  {
    return this.b;
  }

  public EnumOrderStatus getState()
  {
    return this.a;
  }

  public String getTn()
  {
    return this.c;
  }

  public void setOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (b8 localb8)
    {
      break label5;
    }
  }

  public void setState(EnumOrderStatus paramEnumOrderStatus)
  {
    try
    {
      this.a = paramEnumOrderStatus;
      label5: return;
    }
    catch (b8 localb8)
    {
      break label5;
    }
  }

  public void setTn(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (b8 localb8)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.OrderInfo
 * JD-Core Version:    0.6.0
 */