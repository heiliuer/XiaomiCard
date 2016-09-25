package cn.com.fmsh.tsm.business.bean;

import b1;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class ElectronicTakeUp
{
  public static ElectronicTakeUp fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    try
    {
      FMLog localFMLog = LogFactory.getInstance().getLog();
      ElectronicTakeUp localElectronicTakeUp2;
      Object localObject;
      int i1;
      int i2;
      if (paramITag == null)
      {
        if (localFMLog != null)
        {
          localFMLog.warn(BusinessOrder.class.getName(), FM_CN.subSequence("轺捧@\"5宸谱副画孍卻俺急斯ｄ\003gr寽豲乸稫", 136));
          break label643;
          localElectronicTakeUp2.setPrice(localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setUseCount(localObject.getIntVal());
          break label647;
        }
      }
      else
      {
        ITag[] arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break label669;
          localFMLog.warn(BusinessOrder.class.getName(), BCCUtil.endsWith("\"轺挨J3a寣谯刲厛馂俿怽旰ｖZ#1寳谿丨稼", 3, 52));
          break label669;
          localElectronicTakeUp2.setType((byte)localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setOrderElseThing(localObject.getStringVal());
          break label647;
          localElectronicTakeUp2.setTransferFlag((byte)localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setOrder(localObject.getBytesVal());
          break label647;
          localElectronicTakeUp2.setState((byte)localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setStartTime(localObject.getStringVal());
          break label647;
          localElectronicTakeUp2.setOutState((byte)localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setTradeState((byte)localObject.getIntVal());
          break label647;
          localElectronicTakeUp2.setId(localObject.getBytesVal());
          break label647;
          localElectronicTakeUp2.setPublishEndTime(localObject.getStringVal());
          break label647;
          localElectronicTakeUp2.setTypeId(localObject.getBytesVal());
          break label647;
        }
        localElectronicTakeUp2 = new ElectronicTakeUp();
        i1 = arrayOfITag.length;
        i2 = 0;
        break label650;
        localElectronicTakeUp2.setUseType((byte)localObject.getIntVal());
        break label647;
        localElectronicTakeUp2.setPublishStartTime(localObject.getStringVal());
        break label647;
        localObject = arrayOfITag[i2];
      }
      switch (localObject.getId())
      {
      case 124:
      case 119:
      case 115:
      case 18:
      case 117:
      case 17:
      case 121:
      case 110:
      case 122:
      case 21:
      case 112:
      case 109:
      case 113:
      case 116:
      case 127:
      case 111:
        localElectronicTakeUp2.setEndTime(localObject.getStringVal());
        break;
      case 125:
        localElectronicTakeUp2.setPrice4favourable(localObject.getIntVal());
        break;
      case 114:
        localElectronicTakeUp2.setNumber(localObject.getStringVal());
        break;
      case 126:
        localElectronicTakeUp2.setOutSerial(localObject.getBytesVal());
        break;
      case 123:
        localElectronicTakeUp2.setUseTime(localObject.getStringVal());
        break;
      case 120:
        localElectronicTakeUp2.setAppType((byte)localObject.getIntVal());
        break;
      case 118:
        localElectronicTakeUp2.setFrozenFlag((byte)localObject.getIntVal());
        label643: for (localElectronicTakeUp1 = null; ; localElectronicTakeUp1 = localElectronicTakeUp2)
        {
          return localElectronicTakeUp1;
          label647: i2++;
          label650: if (i2 < i1)
            break;
        }
      }
    }
    catch (b1 localb1)
    {
      while (true)
      {
        ElectronicTakeUp localElectronicTakeUp1 = null;
        continue;
        label669: localElectronicTakeUp1 = null;
        continue;
      }
    }
  }

  public byte getAppType()
  {
    return this.i;
  }

  public String getEndTime()
  {
    return this.s;
  }

  public byte getFrozenFlag()
  {
    return this.g;
  }

  public byte[] getId()
  {
    return this.a;
  }

  public String getNumber()
  {
    return this.c;
  }

  public byte[] getOrder()
  {
    return this.u;
  }

  public String getOrderElseThing()
  {
    return this.v;
  }

  public byte[] getOutSerial()
  {
    return this.o;
  }

  public byte getOutState()
  {
    return this.k;
  }

  public int getPrice()
  {
    return this.m;
  }

  public int getPrice4favourable()
  {
    return this.n;
  }

  public String getPublishEndTime()
  {
    return this.q;
  }

  public String getPublishStartTime()
  {
    return this.p;
  }

  public String getStartTime()
  {
    return this.r;
  }

  public byte getState()
  {
    return this.j;
  }

  public byte getTradeState()
  {
    return this.t;
  }

  public byte getTransferFlag()
  {
    return this.f;
  }

  public byte getType()
  {
    return this.d;
  }

  public byte[] getTypeId()
  {
    return this.b;
  }

  public int getUseCount()
  {
    return this.h;
  }

  public String getUseTime()
  {
    return this.l;
  }

  public byte getUseType()
  {
    return this.e;
  }

  public void setAppType(byte paramByte)
  {
    try
    {
      this.i = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setEndTime(String paramString)
  {
    try
    {
      this.s = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setFrozenFlag(byte paramByte)
  {
    try
    {
      this.g = paramByte;
      label5: return;
    }
    catch (b1 localb1)
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
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setNumber(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.u = paramArrayOfByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setOrderElseThing(String paramString)
  {
    try
    {
      this.v = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setOutSerial(byte[] paramArrayOfByte)
  {
    try
    {
      this.o = paramArrayOfByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setOutState(byte paramByte)
  {
    try
    {
      this.k = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setPrice(int paramInt)
  {
    try
    {
      this.m = paramInt;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setPrice4favourable(int paramInt)
  {
    try
    {
      this.n = paramInt;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setPublishEndTime(String paramString)
  {
    try
    {
      this.q = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setPublishStartTime(String paramString)
  {
    try
    {
      this.p = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setStartTime(String paramString)
  {
    try
    {
      this.r = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setState(byte paramByte)
  {
    try
    {
      this.j = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setTradeState(byte paramByte)
  {
    try
    {
      this.t = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setTransferFlag(byte paramByte)
  {
    try
    {
      this.f = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setType(byte paramByte)
  {
    try
    {
      this.d = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setTypeId(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setUseCount(int paramInt)
  {
    try
    {
      this.h = paramInt;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setUseTime(String paramString)
  {
    try
    {
      this.l = paramString;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }

  public void setUseType(byte paramByte)
  {
    try
    {
      this.e = paramByte;
      label5: return;
    }
    catch (b1 localb1)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.ElectronicTakeUp
 * JD-Core Version:    0.6.0
 */