package cn.com.fmsh.script.core;

import bk;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ApduFilter
{
  public void addFilterPolicy(FilterPolicy paramFilterPolicy)
  {
    try
    {
      this.a.add(paramFilterPolicy);
      label11: return;
    }
    catch (bk localbk)
    {
      break label11;
    }
  }

  public byte[] filter(byte[] paramArrayOfByte)
  {
    FMLog localFMLog = LogFactory.getInstance().getLog();
    Object localObject;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 5))
    {
      if (localFMLog != null)
        localFMLog.warn(ApduFilter.class.getName(), FM_Long.concat("讫汅迕滹旾？诩氋攤挱乇呝法", 4));
      localObject = null;
      return localObject;
    }
    label145: label227: 
    while (true)
    {
      Iterator localIterator;
      FilterPolicy localFilterPolicy = (FilterPolicy)localIterator.next();
      byte[] arrayOfByte1;
      byte[][] arrayOfByte;
      int j;
      if ((localFilterPolicy != null) && (localFilterPolicy.getCla() == paramArrayOfByte[0]) && (localFilterPolicy.getIns() == paramArrayOfByte[1]))
      {
        arrayOfByte1 = Arrays.copyOfRange(paramArrayOfByte, 5, paramArrayOfByte.length);
        arrayOfByte = localFilterPolicy.getFilterDatas();
        int i = arrayOfByte.length;
        j = 0;
        label105: if (j < i)
          break label145;
      }
      while (true)
      {
        if (localIterator.hasNext())
          break label227;
        localObject = null;
        break;
        if (this.a.size() < 1)
        {
          localObject = null;
          break;
          byte[] arrayOfByte2 = arrayOfByte[j];
          if (Arrays.equals(arrayOfByte2, arrayOfByte1))
          {
            if (localFMLog != null)
              localFMLog.debug(ApduFilter.class.getName(), FM_Long.concat("-==m莹叏皀NS\001ｊ", 234) + FM_Bytes.bytesToHexString(arrayOfByte2));
            localObject = arrayOfByte2;
            break;
          }
          j++;
          break label105;
        }
        localIterator = this.a.iterator();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.core.ApduFilter
 * JD-Core Version:    0.6.0
 */