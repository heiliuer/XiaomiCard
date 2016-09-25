package cn.com.fmsh.script.core;

import bl;
import java.lang.reflect.Array;
import java.util.List;

public class FilterPolicy
{
  public void addFilterData(byte[] paramArrayOfByte)
  {
    try
    {
      this.c.add(paramArrayOfByte);
      label11: return;
    }
    catch (bl localbl)
    {
      break label11;
    }
  }

  public byte getCla()
  {
    return this.a;
  }

  public byte[][] getFilterDatas()
  {
    try
    {
      List localList = this.c;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfByte = (byte[][])localList.toArray((byte[][])Array.newInstance(Byte.TYPE, arrayOfInt));
      return arrayOfByte;
    }
    catch (bl localbl)
    {
      while (true)
        byte[][] arrayOfByte = null;
    }
  }

  public byte getIns()
  {
    return this.b;
  }

  public void setCla(byte paramByte)
  {
    try
    {
      this.a = paramByte;
      label5: return;
    }
    catch (bl localbl)
    {
      break label5;
    }
  }

  public void setIns(byte paramByte)
  {
    try
    {
      this.b = paramByte;
      label5: return;
    }
    catch (bl localbl)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.core.FilterPolicy
 * JD-Core Version:    0.6.0
 */