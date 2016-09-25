package cn.com.fmsh.util.socket;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import d0;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReceiveHandler
{
  public FMLog b;

  public static ReceiveHandler instance()
  {
    try
    {
      localReceiveHandler = new ReceiveHandler();
      return localReceiveHandler;
    }
    catch (d0 locald0)
    {
      while (true)
        ReceiveHandler localReceiveHandler = null;
    }
  }

  public void cancel()
  {
    try
    {
      setStop(true);
      label5: return;
    }
    catch (d0 locald0)
    {
      break label5;
    }
  }

  public boolean isStop()
  {
    return this.a;
  }

  public byte[] receive(DataLengthHandle paramDataLengthHandle, int paramInt, DataInputStream paramDataInputStream)
    throws IOException
  {
    int i = 0;
    try
    {
      this.a = false;
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      int j;
      byte[] arrayOfByte;
      if (paramDataLengthHandle == null)
      {
        localObject1 = receive(paramDataInputStream);
      }
      else
      {
        j = paramDataLengthHandle.getDataSize(paramDataInputStream);
        if (j > 0)
          break label209;
        localObject1 = receive(paramDataInputStream);
        break label184;
        while (true)
        {
          i += paramDataInputStream.read(arrayOfByte, i, j - i);
          break;
          Object localObject2;
          if (System.currentTimeMillis() - localObject2 < paramInt)
            continue;
          if (this.b != null)
            this.b.debug(FM_Long.concat("H 3>/'9Ossl{;", 66), BCCUtil.endsWith("揪双攷挽趚施", 240, 44));
          throw new IOException(FM_Exception.getChars(5, 100, "坶捅寜皎旸閦\r") + paramInt + Util4Java.toString("^凌朥揰攭剱挀寗镬廿皛敵挥", 4, 70));
          arrayOfByte = new byte[j];
          long l = System.currentTimeMillis();
        }
      }
      while (true)
      {
        label184: return localObject1;
        if (i < j)
          break;
        localObject1 = arrayOfByte;
      }
    }
    catch (d0 locald0)
    {
      while (true)
      {
        Object localObject1 = null;
        continue;
        label209: if (paramInt > 0)
          continue;
        paramInt = 5000;
      }
    }
  }

  public byte[] receive(DataInputStream paramDataInputStream)
    throws IOException
  {
    byte[] arrayOfByte1 = null;
    byte[] arrayOfByte2 = new byte[1024];
    int i = paramDataInputStream.available();
    if (this.b != null)
      this.b.debug(Util4Java.toString("Pk9c{(o^#`~*wl", 3, 44), BCCUtil.endsWith("\001v=:D1a6!T-yx.lsda(r!>s/p}", 102, 50) + i);
    while (true)
    {
      int j = paramDataInputStream.read(arrayOfByte2);
      if (j <= 0)
        return arrayOfByte1;
      if (this.b != null)
        this.b.debug(BCCUtil.endsWith("Py5u#r;P3b\"l&", 3, 58), FM_Utils.copyValueOf(3, 27, "ggj-?ttk*drp&%2") + j);
      if (arrayOfByte1 == null)
      {
        arrayOfByte1 = Arrays.copyOf(arrayOfByte2, j);
        continue;
      }
      FM_Bytes.join(arrayOfByte1, Arrays.copyOf(arrayOfByte2, j));
    }
  }

  public void setStop(boolean paramBoolean)
  {
    try
    {
      this.a = paramBoolean;
      label5: return;
    }
    catch (d0 locald0)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.socket.ReceiveHandler
 * JD-Core Version:    0.6.0
 */