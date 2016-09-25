package cn.com.fmsh.tsm.business.bean;

import b9;
import java.util.List;

public class OrderInfoEx
{
  public void AddOrderChiefInfo(OrderChiefInfo paramOrderChiefInfo)
  {
    try
    {
      this.a.add(paramOrderChiefInfo);
      label11: return;
    }
    catch (b9 localb9)
    {
      break label11;
    }
  }

  public OrderChiefInfo[] getOrderChiefInfos()
  {
    try
    {
      arrayOfOrderChiefInfo = (OrderChiefInfo[])this.a.toArray(new OrderChiefInfo[0]);
      return arrayOfOrderChiefInfo;
    }
    catch (b9 localb9)
    {
      while (true)
        OrderChiefInfo[] arrayOfOrderChiefInfo = null;
    }
  }

  public String getTn()
  {
    return this.b;
  }

  public void setTn(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (b9 localb9)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.OrderInfoEx
 * JD-Core Version:    0.6.0
 */