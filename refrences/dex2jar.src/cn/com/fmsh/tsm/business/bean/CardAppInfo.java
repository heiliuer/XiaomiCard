package cn.com.fmsh.tsm.business.bean;

import bz;
import java.util.List;

public class CardAppInfo
{
  public void addRecord(CardAppRecord paramCardAppRecord)
  {
    if (paramCardAppRecord != null)
      this.h.add(paramCardAppRecord);
  }

  public void addRecord(CardAppRecord[] paramArrayOfCardAppRecord)
  {
    if (this.h == null);
    while (true)
    {
      return;
      int k = paramArrayOfCardAppRecord.length;
      for (int m = 0; m < k; m++)
      {
        CardAppRecord localCardAppRecord = paramArrayOfCardAppRecord[m];
        this.h.add(localCardAppRecord);
      }
    }
  }

  public Integer getBalance()
  {
    return this.c;
  }

  public String getBalanceDelayDate()
  {
    return this.f;
  }

  public byte[] getCardAppNo()
  {
    return this.e;
  }

  public int getCardType()
  {
    return this.d;
  }

  public String getFaceId()
  {
    return this.b;
  }

  public String getMoc()
  {
    return this.i;
  }

  public CardAppRecord[] getRecords()
  {
    try
    {
      if ((this.h != null) && (this.h.size() >= 1))
        arrayOfCardAppRecord = (CardAppRecord[])this.h.toArray(new CardAppRecord[0]);
    }
    catch (bz localbz)
    {
      arrayOfCardAppRecord = null;
    }
    CardAppRecord[] arrayOfCardAppRecord = null;
    return arrayOfCardAppRecord;
  }

  public String getTime4Validity()
  {
    return this.j;
  }

  public String getTitle()
  {
    return this.a;
  }

  public boolean isAppClose()
  {
    return this.g;
  }

  public void setAppClose(boolean paramBoolean)
  {
    try
    {
      this.g = paramBoolean;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setBalance(Integer paramInteger)
  {
    try
    {
      this.c = paramInteger;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setCardAppNo(byte[] paramArrayOfByte)
  {
    try
    {
      this.e = paramArrayOfByte;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setCardType(int paramInt)
  {
    try
    {
      this.d = paramInt;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setDelayDate(String paramString)
  {
    try
    {
      this.f = paramString;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setFaceId(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setMoc(String paramString)
  {
    try
    {
      this.i = paramString;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setTime4Validity(String paramString)
  {
    try
    {
      this.j = paramString;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }

  public void setTitle(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (bz localbz)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.CardAppInfo
 * JD-Core Version:    0.6.0
 */