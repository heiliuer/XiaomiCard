package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.core.FilterPolicy;
import java.util.ArrayList;
import java.util.List;

public class ApduFilterDataInitImpl
  implements ApduFilterDataInit
{
  public byte[][] a = null;

  public ApduFilterDataInitImpl(byte[][] paramArrayOfByte)
  {
    this.a = paramArrayOfByte;
  }

  public List<FilterPolicy> getFilterPolicies()
  {
    int i = 0;
    Object localObject2;
    if (this.a == null)
    {
      localObject2 = null;
      return localObject2;
    }
    label94: 
    while (true)
    {
      byte[][] arrayOfByte;
      byte[] arrayOfByte1 = arrayOfByte[i];
      Object localObject1;
      if (arrayOfByte1 != null)
        ((FilterPolicy)localObject1).addFilterData(arrayOfByte1);
      i++;
      while (true)
      {
        if (i < j)
          break label94;
        localArrayList.add(localObject1);
        localObject2 = localArrayList;
        break;
        ArrayList localArrayList = new ArrayList();
        localObject1 = new FilterPolicy();
        ((FilterPolicy)localObject1).setCla(0);
        ((FilterPolicy)localObject1).setIns(-92);
        arrayOfByte = this.a;
        int j = arrayOfByte.length;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.ApduFilterDataInitImpl
 * JD-Core Version:    0.6.0
 */