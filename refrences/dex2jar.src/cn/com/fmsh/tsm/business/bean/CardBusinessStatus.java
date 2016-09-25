package cn.com.fmsh.tsm.business.bean;

import b0;
import cn.com.fmsh.tsm.business.enums.EnumAppActivationStatus;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderStatus;

public class CardBusinessStatus
{
  public EnumAppActivationStatus getActivationStatus()
  {
    return this.a;
  }

  public EnumBusinessOrderStatus getBusinessOrderStatus()
  {
    return this.b;
  }

  public void setActivationStatus(EnumAppActivationStatus paramEnumAppActivationStatus)
  {
    try
    {
      this.a = paramEnumAppActivationStatus;
      label5: return;
    }
    catch (b0 localb0)
    {
      break label5;
    }
  }

  public void setBusinessOrderStatus(EnumBusinessOrderStatus paramEnumBusinessOrderStatus)
  {
    try
    {
      this.b = paramEnumBusinessOrderStatus;
      label5: return;
    }
    catch (b0 localb0)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.CardBusinessStatus
 * JD-Core Version:    0.6.0
 */