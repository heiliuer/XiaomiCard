package cn.com.fmsh.tsm.business.bean;

import cb;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PayOrder
{
  public static PayOrder fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    Object localObject1 = null;
    try
    {
      FMLog localFMLog = LogFactory.getInstance().getLog();
      PayOrder localPayOrder;
      Object localObject2;
      ITag[] arrayOfITag;
      int k;
      if (paramITag == null)
      {
        if (localFMLog != null)
        {
          localFMLog.warn(BusinessOrder.class.getName(), FM_Long.concat("\tit甁戙攻仇诨匀斶ｇ\002`k寮豣丷穢", 5));
          break label342;
          localPayOrder.setMainOrder(localObject2.getBytesVal());
          break label344;
          localPayOrder.setDate(localObject2.getStringVal());
          break label344;
          localPayOrder.setTime(localObject2.getStringVal());
          break label344;
          localPayOrder.setChannel(localObject2.getIntVal());
          break label344;
        }
      }
      else
      {
        arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break label342;
          localFMLog.warn(BusinessOrder.class.getName(), Util4Java.toString("U4.畂扁敪仁讯協旣％\t0\"嬉頴乻穯", 2, 116));
          break label342;
          localPayOrder.setId(localObject2.getBytesVal());
          break label344;
          localObject2 = arrayOfITag[k];
        }
      }
      int j;
      switch (localObject2.getId())
      {
      case 105:
      case 19:
      case 20:
      case 13:
      case 106:
      case 2:
        localPayOrder.setPayUser(localObject2.getStringVal());
        break;
        localPayOrder = new PayOrder();
        j = arrayOfITag.length;
        k = 0;
        break;
      case 21:
        localPayOrder.setState(EnumOrderStatus.getOrderStatus4ID(localObject2.getIntVal()));
        break;
      case 16:
        localPayOrder.setAmount(FM_Bytes.bytesToInt(localObject2.getBytesVal()));
        break;
      case 18:
        localPayOrder.setThirdPayInfo(localObject2.getStringVal());
        while (true)
        {
          label342: return localObject1;
          label344: k++;
          if (k < j)
            break;
          localObject1 = localPayOrder;
        }
      }
    }
    catch (cb localcb)
    {
      while (true)
        continue;
    }
  }

  public int getAmount()
  {
    return this.e;
  }

  public int getChannel()
  {
    return this.h;
  }

  public String getDate()
  {
    return this.c;
  }

  public byte[] getId()
  {
    return this.a;
  }

  public byte[] getMainOrder()
  {
    return this.i;
  }

  public String getPayUser()
  {
    return this.g;
  }

  public EnumOrderStatus getState()
  {
    return this.f;
  }

  public String getThirdPayInfo()
  {
    return this.b;
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
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setChannel(int paramInt)
  {
    try
    {
      this.h = paramInt;
      label5: return;
    }
    catch (cb localcb)
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
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setId(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = paramArrayOfByte;
      label5: return;
    }
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setMainOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.i = paramArrayOfByte;
      label5: return;
    }
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setPayUser(String paramString)
  {
    try
    {
      this.g = paramString;
      label5: return;
    }
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setState(EnumOrderStatus paramEnumOrderStatus)
  {
    try
    {
      this.f = paramEnumOrderStatus;
      label5: return;
    }
    catch (cb localcb)
    {
      break label5;
    }
  }

  public void setThirdPayInfo(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (cb localcb)
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
    catch (cb localcb)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.PayOrder
 * JD-Core Version:    0.6.0
 */