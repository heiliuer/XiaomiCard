package cn.com.fmsh.tsm.business.bean;

import b6;
import java.io.Serializable;

public class Notice
  implements Serializable
{
  public static int NOTICE_TXT = 0;
  public static int NOTICE_UNSOLVED = 0;
  public int a;
  public String b;
  public String c;
  public String d;
  public String e;
  public int f;
  public byte[] g;

  static
  {
    try
    {
      NOTICE_TXT = 22;
      NOTICE_UNSOLVED = 33;
      label10: return;
    }
    catch (b6 localb6)
    {
      break label10;
    }
  }

  public String getContent()
  {
    return this.c;
  }

  public String getEndDate()
  {
    return this.e;
  }

  public int getNo()
  {
    return this.a;
  }

  public byte[] getOrder()
  {
    return this.g;
  }

  public String getStartDate()
  {
    return this.d;
  }

  public String getTitle()
  {
    return this.b;
  }

  public int getType()
  {
    return this.f;
  }

  public void setContent(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setEndDate(String paramString)
  {
    try
    {
      this.e = paramString;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setNo(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setOrder(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setStartDate(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setTitle(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }

  public void setType(int paramInt)
  {
    try
    {
      this.f = paramInt;
      label5: return;
    }
    catch (b6 localb6)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.Notice
 * JD-Core Version:    0.6.0
 */