package cn.com.fmsh.tsm.business.bean;

import b5;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import java.util.List;

public class MainOrder
{
  public static MainOrder fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    Object localObject1 = null;
    ITag[] arrayOfITag3;
    int i1;
    MainOrder localMainOrder;
    label32: int n;
    if (paramITag == null)
    {
      return localObject1;
      BusinessOrder localBusinessOrder = BusinessOrder.fromTag(arrayOfITag3[i1]);
      if (localBusinessOrder != null)
        localMainOrder.addBusinessOrders(localBusinessOrder);
      i1++;
      if (i1 < n)
        break label169;
    }
    while (true)
    {
      label39: int j;
      j++;
      ITag[] arrayOfITag1;
      while (true)
      {
        if (j < i)
          break label230;
        localObject1 = localMainOrder;
        break;
        localMainOrder.setState(EnumOrderStatus.getOrderStatus4ID(localObject2.getIntVal()));
        break label39;
        arrayOfITag1 = paramITag.getItemTags();
        if ((arrayOfITag1 == null) || (arrayOfITag1.length < 1))
          break;
        localMainOrder = new MainOrder();
        int i = arrayOfITag1.length;
        j = 0;
      }
      label169: label214: 
      while (true)
      {
        ITag[] arrayOfITag2;
        int m;
        PayOrder localPayOrder = PayOrder.fromTag(arrayOfITag2[m]);
        if (localPayOrder != null)
          localMainOrder.addPayOrders(localPayOrder);
        m++;
        while (true)
        {
          if (m < k)
            break label214;
          break label39;
          arrayOfITag3 = localObject2.getItemTags();
          if ((arrayOfITag3 == null) || (arrayOfITag3.length <= 0))
            break label39;
          n = arrayOfITag3.length;
          i1 = 0;
          break label32;
          break;
          localMainOrder.setTime(localObject2.getStringVal());
          break label39;
          arrayOfITag2 = localObject2.getItemTags();
          if ((arrayOfITag2 == null) || (arrayOfITag2.length <= 0))
            break label39;
          int k = arrayOfITag2.length;
          m = 0;
        }
      }
      localMainOrder.setId(localObject2.getBytesVal());
      continue;
      label230: Object localObject2 = arrayOfITag1[j];
      switch (localObject2.getId())
      {
      case 21:
      case 27:
      case 20:
      case 100:
      case 105:
      default:
        break;
      case 16:
        localMainOrder.setAmount(FM_Bytes.bytesToInt(localObject2.getBytesVal()));
        break;
      case 19:
        localMainOrder.setDate(localObject2.getStringVal());
      }
    }
  }

  public void addBusinessOrders(BusinessOrder paramBusinessOrder)
  {
    if (paramBusinessOrder != null)
      this.f.add(paramBusinessOrder);
  }

  public void addPayOrders(PayOrder paramPayOrder)
  {
    if (paramPayOrder != null)
      this.g.add(paramPayOrder);
  }

  public int getAmount()
  {
    return this.e;
  }

  public BusinessOrder[] getBusinessOrders()
  {
    try
    {
      arrayOfBusinessOrder = (BusinessOrder[])this.f.toArray(new BusinessOrder[0]);
      return arrayOfBusinessOrder;
    }
    catch (b5 localb5)
    {
      while (true)
        BusinessOrder[] arrayOfBusinessOrder = null;
    }
  }

  public String getDate()
  {
    return this.c;
  }

  public byte[] getId()
  {
    return this.b;
  }

  public PayOrder[] getPayOrders()
  {
    try
    {
      arrayOfPayOrder = (PayOrder[])this.g.toArray(new PayOrder[0]);
      return arrayOfPayOrder;
    }
    catch (b5 localb5)
    {
      while (true)
        PayOrder[] arrayOfPayOrder = null;
    }
  }

  public EnumOrderStatus getState()
  {
    return this.a;
  }

  public String getTime()
  {
    return this.d;
  }

  public void setAmount(int paramInt)
  {
    try
    {
      this.e = paramInt;
      label5: return;
    }
    catch (b5 localb5)
    {
      break label5;
    }
  }

  public void setDate(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (b5 localb5)
    {
      break label5;
    }
  }

  public void setId(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (b5 localb5)
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
    catch (b5 localb5)
    {
      break label5;
    }
  }

  public void setTime(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (b5 localb5)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.MainOrder
 * JD-Core Version:    0.6.0
 */