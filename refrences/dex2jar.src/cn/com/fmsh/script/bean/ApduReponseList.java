package cn.com.fmsh.script.bean;

import be;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.core.MessageHandler;
import cn.com.fmsh.communication.message.core.Tag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApduReponseList
{
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte = FM_Bytes.hexStringToBytes(FM_Long.concat("l<(.", 2));
      ApduResponse localApduResponse = new ApduResponse();
      localApduResponse.setResult(arrayOfByte);
      localApduResponse.setId(1);
      ApduReponseList localApduReponseList = new ApduReponseList();
      localApduReponseList.add(localApduResponse);
      System.out.println(FM_Bytes.bytesToHexString(localApduReponseList.toBytes4A2()));
      System.out.println(FM_Bytes.bytesToHexString(localApduReponseList.toBytes4A3()));
      label71: return;
    }
    catch (be localbe)
    {
      break label71;
    }
  }

  public void add(ApduResponse paramApduResponse)
  {
    try
    {
      this.a.add(paramApduResponse);
      label11: return;
    }
    catch (be localbe)
    {
      break label11;
    }
  }

  public ApduResponse[] getApduResponse()
  {
    try
    {
      ApduResponse[] arrayOfApduResponse2 = new ApduResponse[this.a.size()];
      arrayOfApduResponse1 = (ApduResponse[])this.a.toArray(arrayOfApduResponse2);
      return arrayOfApduResponse1;
    }
    catch (be localbe)
    {
      while (true)
        ApduResponse[] arrayOfApduResponse1 = null;
    }
  }

  public List<byte[]> getApduResponseList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.a.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      ApduResponse localApduResponse = (ApduResponse)localIterator.next();
      if (localApduResponse == null)
        continue;
      localArrayList.add(localApduResponse.getResult());
    }
  }

  public ApduResponse[] getApduResponses()
  {
    try
    {
      arrayOfApduResponse = (ApduResponse[])this.a.toArray(new ApduResponse[0]);
      return arrayOfApduResponse;
    }
    catch (be localbe)
    {
      while (true)
        ApduResponse[] arrayOfApduResponse = null;
    }
  }

  public int size()
  {
    try
    {
      int j = this.a.size();
      i = j;
      return i;
    }
    catch (be localbe)
    {
      while (true)
        int i = 0;
    }
  }

  public byte[] toBytes4A2()
  {
    try
    {
      byte[] arrayOfByte2;
      if (this.a.size() > 0)
        arrayOfByte2 = ((ApduResponse)this.a.get(0)).toBytes();
      for (arrayOfByte1 = arrayOfByte2; ; arrayOfByte1 = null)
        return arrayOfByte1;
    }
    catch (be localbe)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public byte[] toBytes4A3()
  {
    byte[] arrayOfByte1 = new byte[0];
    Iterator localIterator = this.a.iterator();
    byte[] arrayOfByte2 = arrayOfByte1;
    int i;
    byte[] arrayOfByte3;
    if (!localIterator.hasNext())
    {
      i = arrayOfByte2.length;
      arrayOfByte3 = new byte[i + 2];
      arrayOfByte3[0] = -93;
      arrayOfByte3[1] = (byte)i;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        return arrayOfByte3;
        ApduResponse localApduResponse = (ApduResponse)localIterator.next();
        if (localApduResponse == null)
          break;
        arrayOfByte2 = FM_Bytes.join(arrayOfByte2, localApduResponse.toBytes());
        break;
      }
      arrayOfByte3[(j + 2)] = arrayOfByte2[j];
    }
  }

  public ITag toTag4A2()
    throws FMCommunicationMessageException
  {
    try
    {
      MessageHandler localMessageHandler = MessageHandleFactory.getMessageHandler();
      Tag localTag2;
      if (this.a.size() > 0)
        localTag2 = localMessageHandler.createTag(((ApduResponse)this.a.get(0)).toBytes());
      for (localTag1 = localTag2; ; localTag1 = null)
        return localTag1;
    }
    catch (be localbe)
    {
      while (true)
        Tag localTag1 = null;
    }
  }

  public ITag toTag4A3()
    throws FMCommunicationMessageException
  {
    Tag localTag1;
    try
    {
      MessageHandler localMessageHandler = MessageHandleFactory.getMessageHandler();
      Tag localTag2 = localMessageHandler.createTag(-93);
      Iterator localIterator = this.a.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          localTag1 = localTag2;
          break;
        }
        ApduResponse localApduResponse = (ApduResponse)localIterator.next();
        if (localApduResponse == null)
          continue;
        localTag2.addValue(localMessageHandler.createTag(localApduResponse.toBytes()));
      }
    }
    catch (be localbe)
    {
      localTag1 = null;
    }
    return localTag1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.bean.ApduReponseList
 * JD-Core Version:    0.6.0
 */