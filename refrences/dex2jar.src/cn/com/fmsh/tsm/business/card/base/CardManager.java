package cn.com.fmsh.tsm.business.card.base;

import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public abstract interface CardManager
{
  public abstract byte[] getAid();

  public abstract byte[] getAppNo()
    throws BusinessException;

  public abstract String getFaceID()
    throws BusinessException;

  public abstract String getMOC()
    throws BusinessException;

  public abstract EnumCardAppStatus getStatus()
    throws BusinessException;

  public abstract String getTime4Validity()
    throws BusinessException;

  public abstract boolean isLock4Consume()
    throws BusinessException;

  public abstract boolean isLock4Load()
    throws BusinessException;

  public abstract int queryBalance()
    throws BusinessException;

  public abstract List<CardAppRecord> readAppRecords()
    throws BusinessException;

  public abstract void setApduHandler(ApduHandler paramApduHandler);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.base.CardManager
 * JD-Core Version:    0.6.0
 */