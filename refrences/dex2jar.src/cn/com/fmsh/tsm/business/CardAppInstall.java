package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.IssuerPrepareResult;
import cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardBusinessOpType;
import cn.com.fmsh.tsm.business.exception.BusinessException;

public abstract interface CardAppInstall
{
  public abstract boolean appletDownload(EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte, String paramString)
    throws BusinessException;

  public abstract void cancel();

  public abstract CardAppInfo deleteApp(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2, String paramString)
    throws BusinessException;

  public abstract byte[] deleteAppVer1(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2, String paramString)
    throws BusinessException;

  public abstract EnumCardAppStatus getAppIssuerStatus(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract EnumCardAppStatus getAppIssuerStatus4Platform(EnumCardAppType paramEnumCardAppType, String paramString, byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract boolean issuePrepare(byte[] paramArrayOfByte1, String paramString1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, String paramString2, String paramString3, byte[] paramArrayOfByte4, IssuerPrepareResult paramIssuerPrepareResult)
    throws BusinessException;

  public abstract boolean issuePrepareResultSearch(byte[] paramArrayOfByte, IssuerPrepareResult paramIssuerPrepareResult)
    throws BusinessException;

  public abstract boolean issuer(byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws BusinessException;

  public abstract boolean issuerVer2(byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws BusinessException;

  public abstract byte[] moveApp(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2, String paramString)
    throws BusinessException;

  public abstract boolean personlization(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract void registerIssuerProcessHandler(IssuerProcessHandler paramIssuerProcessHandler);

  public abstract boolean setApp(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2, String paramString, EnumAppManageOperateType paramEnumAppManageOperateType)
    throws BusinessException;

  public abstract boolean setAppVer2(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2, String paramString, EnumAppManageOperateType paramEnumAppManageOperateType)
    throws BusinessException;

  public abstract int setCardBusinessStatus(EnumCardBusinessOpType paramEnumCardBusinessOpType, String paramString1, String paramString2, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString3)
    throws BusinessException;

  public abstract int setCardBusinessStatusVer2(EnumCardBusinessOpType paramEnumCardBusinessOpType, String paramString1, String paramString2, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString3, byte[] paramArrayOfByte3)
    throws BusinessException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.CardAppInstall
 * JD-Core Version:    0.6.0
 */