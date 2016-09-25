package cn.com.fmsh.tsm.business.card;

import cg;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.card.core.LntAppManager;
import cn.com.fmsh.tsm.business.card.core.ShTourAppManager;
import cn.com.fmsh.tsm.business.card.core.StpcManager;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class CardManagerFactory
{
  public static CardManagerFactory instance()
  {
    if (c == null)
      c = new CardManagerFactory();
    return c;
  }

  public EnumCardAppType getCardAppType()
    throws BusinessException
  {
    byte[] arrayOfByte1 = new byte[7];
    arrayOfByte1[1] = -92;
    arrayOfByte1[4] = 2;
    arrayOfByte1[5] = 63;
    arrayOfByte1[6] = 1;
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    byte[] arrayOfByte2;
    try
    {
      arrayOfByte2 = this.d.transceive(arrayOfByte1);
      if (!FM_Bytes.isEnd9000(arrayOfByte2))
      {
        if (this.a != null)
          this.a.warn(this.b, FM_Int.lastIndexOf(2, "菢厀匶盜簢埑断ｐ\034\016\033U挆仦扤衈弇帾=") + FM_Bytes.bytesToHexString(arrayOfByte2));
        throw new BusinessException(FM_Long.concat("菮叒卮皞籾垛旭ｊ讪厊vg旚仾夢贻", 1), BusinessException.ErrorMessage.local_business_execute_fail);
      }
    }
    catch (FMScriptHandleException localFMScriptHandleException)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Bytes.startsWith("F'#\"捀仳扠衛函珧彅幯", 6, 112) + Util4Java.getExceptionInfo(localFMScriptHandleException));
      if (this.d != null)
        this.d.close();
      throw new BusinessException(FM_CN.subSequence("Sqtj捉亹戫蠗凰玩弊帯", 4), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    EnumCardAppType localEnumCardAppType;
    if (arrayOfByte2[(-4 + arrayOfByte2.length)] == 74)
      localEnumCardAppType = EnumCardAppType.CARD_APP_TYPE_SH_TOUR;
    else
      localEnumCardAppType = EnumCardAppType.CARD_APP_TYPE_SH;
    return localEnumCardAppType;
  }

  public CardManager getCardManager(EnumCardAppType paramEnumCardAppType)
  {
    if (paramEnumCardAppType == null);
    try
    {
      localObject = new StpcManager();
      break label59;
      while (paramEnumCardAppType == EnumCardAppType.CARD_APP_TYPE_SH_TOUR)
      {
        localObject = new ShTourAppManager();
        break label59;
        if (paramEnumCardAppType != EnumCardAppType.CARD_APP_TYPE_SH)
          continue;
        localObject = new StpcManager();
        break label59;
      }
      localObject = new LntAppManager();
      label59: return localObject;
    }
    catch (cg localcg)
    {
      while (true)
        Object localObject = null;
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.d = paramApduHandler;
      label5: return;
    }
    catch (cg localcg)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.CardManagerFactory
 * JD-Core Version:    0.6.0
 */