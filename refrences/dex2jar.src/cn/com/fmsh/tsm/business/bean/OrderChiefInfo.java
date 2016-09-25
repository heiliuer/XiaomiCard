package cn.com.fmsh.tsm.business.bean;

import b7;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;

public class OrderChiefInfo
{
  public int getAmount()
  {
    return this.b;
  }

  public byte[] getOrder()
  {
    return this.c;
  }

  public EnumOrderStatus getState()
  {
    return this.a;
  }

  public EnumBusinessOrderType getType()
  {
    return this.d;
  }

  public void setAmount(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (b7 localb7)
    {
      break label5;
    }
  }

  public void setOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.c = paramArrayOfByte;
      label5: return;
    }
    catch (b7 localb7)
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
    catch (b7 localb7)
    {
      break label5;
    }
  }

  public void setType(EnumBusinessOrderType paramEnumBusinessOrderType)
  {
    try
    {
      this.d = paramEnumBusinessOrderType;
      label5: return;
    }
    catch (b7 localb7)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.OrderChiefInfo
 * JD-Core Version:    0.6.0
 */