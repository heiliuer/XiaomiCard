package cn.com.fmsh.tsm.business.bean;

import b_;
import cn.com.fmsh.tsm.business.enums.EnumTradeType;
import java.io.Serializable;

public class CardAppRecord
  implements Serializable
{
  public int getAmount()
  {
    return this.d;
  }

  public int getBalance()
  {
    return this.e;
  }

  public byte getOriTradeType()
  {
    return this.c;
  }

  public int getOverdraft()
  {
    return this.j;
  }

  public byte getTerminalTradeType()
  {
    return this.b;
  }

  public String getTradeDate()
  {
    return this.g;
  }

  public String getTradeDevice()
  {
    return this.i;
  }

  public int getTradeNo()
  {
    return this.f;
  }

  public String getTradeTime()
  {
    return this.h;
  }

  public EnumTradeType getTradeType()
  {
    return this.a;
  }

  public void setAmount(int paramInt)
  {
    try
    {
      this.d = paramInt;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setBalance(int paramInt)
  {
    try
    {
      this.e = paramInt;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setOriTradeType(byte paramByte)
  {
    try
    {
      this.c = paramByte;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setOverdraft(int paramInt)
  {
    try
    {
      this.j = paramInt;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTerminalTradeType(byte paramByte)
  {
    try
    {
      this.b = paramByte;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTradeDate(String paramString)
  {
    try
    {
      this.g = paramString;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTradeDevice(String paramString)
  {
    try
    {
      this.i = paramString;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTradeNo(int paramInt)
  {
    try
    {
      this.f = paramInt;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTradeTime(String paramString)
  {
    try
    {
      this.h = paramString;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }

  public void setTradeType(EnumTradeType paramEnumTradeType)
  {
    try
    {
      this.a = paramEnumTradeType;
      label5: return;
    }
    catch (b_ localb_)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.CardAppRecord
 * JD-Core Version:    0.6.0
 */