package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.ElectronicTakeUp;
import cn.com.fmsh.tsm.business.bean.IdentifyingCode;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public abstract interface BusinessExtend
{
  public abstract int applyForElectronicTakeUp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException;

  public abstract IdentifyingCode obtainIdentifyingCode(int paramInt, String paramString)
    throws BusinessException;

  public abstract ElectronicTakeUp queryElectronicTakeUp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException;

  public abstract List<ElectronicTakeUp> queryElectronicTakeUps(byte[] paramArrayOfByte, byte paramByte, int paramInt)
    throws BusinessException;

  public abstract MainOrder useElectronicTakeUp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, EnumCardIoType paramEnumCardIoType)
    throws BusinessException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.BusinessExtend
 * JD-Core Version:    0.6.0
 */