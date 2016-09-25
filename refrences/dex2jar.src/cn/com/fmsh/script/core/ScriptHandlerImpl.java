package cn.com.fmsh.script.core;

import bm;
import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ApduHandler.ApduHandlerType;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequest;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.bean.ApduResponse;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ScriptHandlerImpl
  implements ScriptHandler
{
  public ScriptHandlerImpl(ApduHandler paramApduHandler)
  {
    this.c = paramApduHandler;
    this.d = new ApduFilter();
  }

  public void cancel()
  {
    try
    {
      setStop(true);
      label5: return;
    }
    catch (bm localbm)
    {
      break label5;
    }
  }

  public ApduReponseList execute(ApduRequestList paramApduRequestList)
    throws FMScriptHandleException
  {
    Object localObject1 = null;
    try
    {
      if (this.a == null)
        this.a = LogFactory.getInstance().getLog();
      this.e = false;
      if (paramApduRequestList == null)
        break label671;
      while (true)
      {
        ApduRequest localApduRequest1;
        byte[] arrayOfByte4;
        label233: label234: byte[] arrayOfByte1;
        Object localObject3;
        if (isStop())
        {
          FMScriptHandleException localFMScriptHandleException1 = new FMScriptHandleException(FM_Int.lastIndexOf(3, "奌朶损亽戽蠗斪q弍划操佝袩偟武"));
          localFMScriptHandleException1.setType(FMScriptHandleException.ScriptHandleExceptionType.STOPED);
          throw localFMScriptHandleException1;
          localApduReponseList = new ApduReponseList();
          localApduRequest1 = paramApduRequestList.getFirstApduRequest();
          if (localApduRequest1 != null)
            break label673;
          if (this.a != null)
            this.a.error(this.b, Util4Java.toString("夞朡挛仼戳蠜旺＄菳厖笰乘朵挗仨她赡", 5, 92));
          new FMScriptHandleException(CRCUtil.valueOf(5, "奁札捚亪扸衜旷＞莴厂筩乖朦挟仭夫贮")).setType(FMScriptHandleException.ScriptHandleExceptionType.INVALID_FIRST_ID);
          break label671;
          while (((ApduRequest)localObject2).getNext4Expectation(arrayOfByte4) == -1)
          {
            if (this.a == null)
              break;
            this.a.error(this.b, FM_Long.concat("奀杤挗仿扡衝旪ｋ\t", 2) + ((ApduRequest)localObject2).getId() + FM_Int.lastIndexOf(5, "\005捞亾咖廈硜\005") + FM_Bytes.bytesToHexString(arrayOfByte4) + Util4Java.toString("Y东期朕倠乇签", 5, 14));
            break;
          }
          localApduRequest2 = paramApduRequestList.getApduRequest(1 + ((ApduRequest)localObject2).getId());
          break label466;
          if (localApduRequest2 == null)
            break label695;
          if (localApduRequest2.getId() > ((ApduRequest)localObject2).getId())
            break label701;
          FMScriptHandleException localFMScriptHandleException2 = new FMScriptHandleException(FM_Utils.copyValueOf(4, 111, "夕朿捊亸戬衖旿４徂捑亡盐么杳挆仴盛罘厪乁夼亄朵杩捐亢监桃试．扶行奾赻"));
          localFMScriptHandleException2.setType(FMScriptHandleException.ScriptHandleExceptionType.INVALID_NEXT);
          throw localFMScriptHandleException2;
        }
        else
        {
          arrayOfByte1 = ((ApduRequest)localObject2).getApdu();
          if (this.c.getApduHandlerType() != ApduHandler.ApduHandlerType.OPEN_MOBILE)
            break label651;
          byte[] arrayOfByte6 = this.d.filter(arrayOfByte1);
          if (arrayOfByte6 == null)
            break label635;
          if (!this.c.open(arrayOfByte6))
            break label603;
          localObject3 = new byte[2];
          localObject3[0] = -112;
        }
        while (true)
        {
          label343: ApduResponse localApduResponse = new ApduResponse();
          localApduResponse.setId(((ApduRequest)localObject2).getId());
          localApduResponse.setApdu(((ApduRequest)localObject2).getApdu());
          if ((localObject3 == null) || (localObject3.length < 2))
          {
            byte[] arrayOfByte3 = new byte[1];
            arrayOfByte3[0] = 1;
            localApduResponse.setResult(arrayOfByte3);
            localApduReponseList.add(localApduResponse);
            if (this.a == null)
              break label695;
            this.a.warn(this.b, FM_Int.lastIndexOf(3, "奌朶损亽戽蠗斪ｑ\005") + ((ApduRequest)localObject2).getId() + CRCUtil.valueOf(198, "A挊仺扨行奠赧"));
            break label695;
            label466: if (((ApduRequest)localObject2).getTag() != -92)
              break label234;
            if (!((ApduRequest)localObject2).isHaveExpectation())
              break label623;
            i = ((ApduRequest)localObject2).getNext4Expectation(arrayOfByte4);
            if (i != 0)
              break label687;
          }
          label603: label623: for (i = 1 + ((ApduRequest)localObject2).getId(); ; i = 1 + ((ApduRequest)localObject2).getId())
          {
            localApduRequest2 = paramApduRequestList.getApduRequest(i);
            break label234;
            localApduResponse.setResult(localObject3);
            localApduReponseList.add(localApduResponse);
            arrayOfByte4 = Arrays.copyOfRange(localObject3, -2 + localObject3.length, localObject3.length);
            if (((ApduRequest)localObject2).getTag() != -96)
              break label233;
            byte[] arrayOfByte5 = new byte[2];
            arrayOfByte5[0] = -1;
            arrayOfByte5[1] = -1;
            if (((ApduRequest)localObject2).getNext4Expectation(arrayOfByte5) == -1)
              break;
            localApduRequest2 = paramApduRequestList.getApduRequest(1 + ((ApduRequest)localObject2).getId());
            break label234;
            localObject3 = new byte[2];
            localObject3[0] = 105;
            localObject3[1] = -123;
            break label343;
          }
          label635: localObject3 = this.c.transceive(arrayOfByte1);
          continue;
          label651: byte[] arrayOfByte2 = this.c.transceive(arrayOfByte1);
          localObject3 = arrayOfByte2;
        }
        label671: return localObject1;
        label673: localObject2 = localApduRequest1;
        localApduRequest2 = null;
      }
    }
    catch (bm localbm)
    {
      while (true)
      {
        ApduReponseList localApduReponseList;
        ApduRequest localApduRequest2;
        int i;
        continue;
        label687: if (i != 255)
          continue;
        label695: localObject1 = localApduReponseList;
        continue;
        label701: Object localObject2 = localApduRequest2;
      }
    }
  }

  public byte[] execute(byte[] paramArrayOfByte)
    throws FMScriptHandleException
  {
    try
    {
      byte[] arrayOfByte2 = this.c.transceive(paramArrayOfByte);
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (bm localbm)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  /** @deprecated */
  public boolean isStop()
  {
    monitorenter;
    try
    {
      boolean bool = this.e;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setApduFilterDataInit(ApduFilterDataInit paramApduFilterDataInit)
  {
    Iterator localIterator;
    if (paramApduFilterDataInit != null)
      localIterator = paramApduFilterDataInit.getFilterPolicies().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      FilterPolicy localFilterPolicy = (FilterPolicy)localIterator.next();
      this.d.addFilterPolicy(localFilterPolicy);
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.c = paramApduHandler;
      label5: return;
    }
    catch (bm localbm)
    {
      break label5;
    }
  }

  /** @deprecated */
  // ERROR //
  public void setStop(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield 61	cn/com/fmsh/script/core/ScriptHandlerImpl:e	Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_3
    //   11: goto -4 -> 7
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	10	bm
    //   2	7	14	finally
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.core.ScriptHandlerImpl
 * JD-Core Version:    0.6.0
 */