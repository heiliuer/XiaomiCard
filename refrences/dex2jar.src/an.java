import cn.com.fmsh.communication.message.enumerate.ETagType;
import java.util.List;

public class an
{
  public void addTagItem(ap paramap)
  {
    if (paramap != null)
      this.e.add(paramap);
  }

  public String getDesc()
  {
    return this.d;
  }

  public byte getId()
  {
    return this.a;
  }

  public int getLength()
  {
    return this.b;
  }

  public ap[] getTagItemDefines()
  {
    try
    {
      ap[] arrayOfap2 = new ap[0];
      arrayOfap1 = (ap[])this.e.toArray(arrayOfap2);
      return arrayOfap1;
    }
    catch (ao localao)
    {
      while (true)
        ap[] arrayOfap1 = null;
    }
  }

  public ETagType getType()
  {
    return this.c;
  }

  public void setDesc(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (ao localao)
    {
      break label5;
    }
  }

  public void setId(byte paramByte)
  {
    try
    {
      this.a = paramByte;
      label5: return;
    }
    catch (ao localao)
    {
      break label5;
    }
  }

  public void setLength(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (ao localao)
    {
      break label5;
    }
  }

  public void setType(ETagType paramETagType)
  {
    try
    {
      this.c = paramETagType;
      label5: return;
    }
    catch (ao localao)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     an
 * JD-Core Version:    0.6.0
 */