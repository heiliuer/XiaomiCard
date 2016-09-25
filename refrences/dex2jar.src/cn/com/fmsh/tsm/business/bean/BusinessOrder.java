package cn.com.fmsh.tsm.business.bean;

import by;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.Serializable;

public class BusinessOrder
  implements Serializable
{
  public static BusinessOrder fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    BusinessOrder localBusinessOrder = null;
    FMLog localFMLog = LogFactory.getInstance().getLog();
    if (paramITag == null)
      if (localFMLog != null)
        localFMLog.warn(BusinessOrder.class.getName(), FM_CN.subSequence("[j甃扛乀勨诺卒无）@\"5宸谱乥穴", 1));
    label528: 
    while (true)
    {
      return localBusinessOrder;
      Object localObject;
      localBusinessOrder.setSeid(localObject.getBytesVal());
      while (true)
      {
        label47: int i2;
        i2++;
        while (true)
        {
          int i1;
          if (i2 >= i1)
            break label528;
          ITag[] arrayOfITag;
          localObject = arrayOfITag[i2];
          switch (localObject.getId())
          {
          case -79:
          default:
            break;
          case 13:
            localBusinessOrder.setPayChannel(localObject.getIntVal());
            break;
          case 15:
            localBusinessOrder.setCardNo(FM_Bytes.hexStringToBytes(localObject.getStringVal()));
            break;
          case 16:
            localBusinessOrder.setAmount(FM_Bytes.bytesToInt(localObject.getBytesVal()));
            break;
          case 47:
            localBusinessOrder.setCardIoType(EnumCardIoType.getCardIoType(localObject.getIntVal()));
            break;
          case 22:
            localBusinessOrder.setSerialNo(FM_Bytes.bytesToInt(localObject.getBytesVal()));
            break;
          case 14:
            localBusinessOrder.setCardAppType(EnumCardAppType.instance(localObject.getIntVal()));
            break label47;
            arrayOfITag = paramITag.getItemTags();
            if ((arrayOfITag == null) || (arrayOfITag.length < 1))
            {
              if (localFMLog == null)
                break;
              localFMLog.warn(BusinessOrder.class.getName(), Util4Java.toString("W}r甑戗业勸诰匞斲ｑ\002./嬑顣丩究", 292, 121));
            }
            break;
          case 17:
            localBusinessOrder.setOrder(localObject.getBytesVal());
            break;
          case 104:
            localBusinessOrder.setDeviceModel(localObject.getStringVal());
            break;
          case 23:
            localBusinessOrder.setTerminalNo(FM_Bytes.hexStringToBytes(localObject.getStringVal()));
            break label47;
            localBusinessOrder = new BusinessOrder();
            i1 = arrayOfITag.length;
            i2 = 0;
          case 21:
          case 20:
          case 72:
          case 24:
          case 103:
          case 19:
          case 105:
          }
        }
        localBusinessOrder.setTradeState(EnumOrderStatus.getOrderStatus4ID(localObject.getIntVal()));
        continue;
        localBusinessOrder.setTradeTime(localObject.getStringVal());
        continue;
        localBusinessOrder.setBusinessOrderType(EnumBusinessOrderType.instance(localObject.getIntVal()));
        continue;
        localBusinessOrder.setInvoiceStatus(localObject.getIntVal());
        continue;
        localBusinessOrder.setProduct(localObject.getStringVal());
        continue;
        localBusinessOrder.setTradeDate(localObject.getStringVal());
        continue;
        localBusinessOrder.setMainOrder(localObject.getBytesVal());
      }
    }
  }

  public int getAmount()
  {
    return this.d;
  }

  public EnumBusinessOrderType getBusinessOrderType()
  {
    return this.n;
  }

  public EnumCardAppType getCardAppType()
  {
    return this.m;
  }

  public EnumCardIoType getCardIoType()
  {
    return this.l;
  }

  public byte[] getCardNo()
  {
    return this.g;
  }

  public String getDeviceModel()
  {
    return this.q;
  }

  public int getInvoiceStatus()
  {
    return this.j;
  }

  public byte[] getMainOrder()
  {
    return this.r;
  }

  public byte[] getOrder()
  {
    return this.c;
  }

  public int getPayChannel()
  {
    return this.k;
  }

  public String getProduct()
  {
    return this.o;
  }

  public byte[] getSeid()
  {
    return this.p;
  }

  public int getSerialNo()
  {
    return this.f;
  }

  public String getTac()
  {
    return this.i;
  }

  public byte[] getTerminalNo()
  {
    return this.h;
  }

  public String getTradeDate()
  {
    return this.a;
  }

  public EnumOrderStatus getTradeState()
  {
    return this.e;
  }

  public String getTradeTime()
  {
    return this.b;
  }

  public void setAmount(int paramInt)
  {
    try
    {
      this.d = paramInt;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setBusinessOrderType(EnumBusinessOrderType paramEnumBusinessOrderType)
  {
    try
    {
      this.n = paramEnumBusinessOrderType;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setCardAppType(EnumCardAppType paramEnumCardAppType)
  {
    try
    {
      this.m = paramEnumCardAppType;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setCardIoType(EnumCardIoType paramEnumCardIoType)
  {
    try
    {
      this.l = paramEnumCardIoType;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setCardNo(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setDeviceModel(String paramString)
  {
    try
    {
      this.q = paramString;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setInvoiceStatus(int paramInt)
  {
    try
    {
      this.j = paramInt;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setMainOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.r = paramArrayOfByte;
      label5: return;
    }
    catch (by localby)
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
    catch (by localby)
    {
      break label5;
    }
  }

  public void setPayChannel(int paramInt)
  {
    try
    {
      this.k = paramInt;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setProduct(String paramString)
  {
    try
    {
      this.o = paramString;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setSeid(byte[] paramArrayOfByte)
  {
    try
    {
      this.p = paramArrayOfByte;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setSerialNo(int paramInt)
  {
    try
    {
      this.f = paramInt;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setTac(String paramString)
  {
    try
    {
      this.i = paramString;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setTerminalNo(byte[] paramArrayOfByte)
  {
    try
    {
      this.h = paramArrayOfByte;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setTradeDate(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setTradeState(EnumOrderStatus paramEnumOrderStatus)
  {
    try
    {
      this.e = paramEnumOrderStatus;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }

  public void setTradeTime(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (by localby)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.BusinessOrder
 * JD-Core Version:    0.6.0
 */