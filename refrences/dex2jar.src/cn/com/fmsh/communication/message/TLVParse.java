package cn.com.fmsh.communication.message;

import ae;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TLVParse
{
  public static TLVParse intance()
  {
    try
    {
      localTLVParse = new TLVParse();
      return localTLVParse;
    }
    catch (ae localae)
    {
      while (true)
        TLVParse localTLVParse = null;
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    byte[] arrayOfByte = FM_Bytes.hexStringToBytes(FM_CN.subSequence("S0 .~m\ri:/9&vdtc3#0>.|\035xj8(7", 4));
    try
    {
      intance().parse(arrayOfByte, 1);
      return;
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
        localFMCommunicationMessageException.printStackTrace();
    }
  }

  public List<TagEntry> parse(byte[] paramArrayOfByte, int paramInt)
    throws FMCommunicationMessageException
  {
    ArrayList localArrayList = new ArrayList();
    if (paramInt < 1)
      throw new FMCommunicationMessageException(BCCUtil.endsWith("W\027\005攻挭覸柃施ｏ伻其皏W敫捽镴庥乖呛沞", 4, 120));
    try
    {
      ByteArrayInputStream localByteArrayInputStream;
      int j = localByteArrayInputStream.read();
      label44: byte[] arrayOfByte3;
      if (j < 0)
      {
        break label232;
        arrayOfByte3 = new byte[1];
        arrayOfByte3[0] = (byte)j;
      }
      else
      {
        int k;
        do
        {
          byte[] arrayOfByte4 = new byte[j];
          localByteArrayInputStream.read(arrayOfByte4);
          byte[] arrayOfByte2;
          localArrayList.add(new TagEntry(arrayOfByte2, arrayOfByte3, arrayOfByte4));
          byte[] arrayOfByte1;
          do
          {
            arrayOfByte2 = new byte[paramInt];
            int i = localByteArrayInputStream.read(arrayOfByte2);
            if (i != -1)
              break;
            break label232;
            do
            {
              localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
              arrayOfByte1 = new byte[2];
              break;
            }
            while ((paramArrayOfByte != null) && (paramArrayOfByte.length >= paramInt + 1));
            throw new FMCommunicationMessageException(BCCUtil.endsWith("W��C攮捩観枉旴ｇ伴儸皂政捶丌呂泆", 4, 73));
          }
          while (j == 0);
          if (j != 255)
            break label44;
          arrayOfByte3 = new byte[3];
          k = localByteArrayInputStream.read(arrayOfByte1);
          arrayOfByte3[0] = 0;
          arrayOfByte3[1] = arrayOfByte1[0];
          arrayOfByte3[2] = arrayOfByte1[1];
          int m = FM_Bytes.bytesToInt(arrayOfByte3);
          j = m;
        }
        while (k != -1);
      }
    }
    catch (IOException localIOException)
    {
    }
    label232: return localArrayList;
  }

  public class TagEntry
  {
    public TagEntry(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] arg4)
    {
      this.a = paramArrayOfByte1;
      this.b = paramArrayOfByte2;
      Object localObject;
      this.c = localObject;
    }

    public byte[] getData()
    {
      return this.c;
    }

    public byte[] getLengthData()
    {
      return this.b;
    }

    public byte[] getTag()
    {
      return this.a;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.TLVParse
 * JD-Core Version:    0.6.0
 */