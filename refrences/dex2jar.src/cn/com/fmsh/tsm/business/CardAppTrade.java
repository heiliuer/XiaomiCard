package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.Activity;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.bean.CardBusinessStatus;
import cn.com.fmsh.tsm.business.bean.LoginInfo;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import cn.com.fmsh.tsm.business.bean.PasswordPrompt;
import cn.com.fmsh.tsm.business.bean.PayOrder;
import cn.com.fmsh.tsm.business.bean.PreDepositInfo;
import cn.com.fmsh.tsm.business.bean.TerminalBackInfo;
import cn.com.fmsh.tsm.business.bean.UserInfo;
import cn.com.fmsh.tsm.business.bean.VersionInfo;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumOrderType;
import cn.com.fmsh.tsm.business.enums.EnumResultsSortType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public abstract interface CardAppTrade
{
  public abstract MainOrder apply4Pay(int paramInt1, int paramInt2, byte[] paramArrayOfByte, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract MainOrder applyAct4Pay(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2)
    throws BusinessException;

  public abstract int deleteTerminalInfoBack(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract int doRefund(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract int doUnsolvedOrder(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException;

  public abstract byte[] getAppNo(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract Integer getBalance(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract CardAppInfo getCardAppInfo(int paramInt, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract List<CardAppRecord> getCardAppRecords(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract EnumCardAppType getCardAppType()
    throws BusinessException;

  public abstract String getFaceID(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract String getInvoiceToken(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract String getMOC(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract List<Notice> getNotices(int paramInt)
    throws BusinessException;

  public abstract String getTime4Validity(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract boolean isLock4Consume(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract boolean isLock4Load(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract boolean isRun4plateform()
    throws BusinessException;

  public abstract LoginInfo login(String paramString1, String paramString2)
    throws BusinessException;

  public abstract LoginInfo loginVer2(String paramString1, String paramString2)
    throws BusinessException;

  public abstract int logout()
    throws BusinessException;

  public abstract int modifyPassword(String paramString1, String paramString2)
    throws BusinessException;

  public abstract int modifyUserInfo(UserInfo paramUserInfo)
    throws BusinessException;

  public abstract int modifyUserInfoVer2(UserInfo paramUserInfo)
    throws BusinessException;

  public abstract List<Activity> queryActivities(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract BusinessOrder queryBusinessOrder(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract List<BusinessOrder> queryBusinessOrders(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, EnumOrderStatus paramEnumOrderStatus)
    throws BusinessException;

  public abstract List<BusinessOrder> queryBusinessOrdersVer3(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, EnumOrderStatus paramEnumOrderStatus, byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract List<BusinessOrder> queryBusinessOrdersVer4(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, List<EnumOrderStatus> paramList, byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract CardBusinessStatus queryCardBusinessStatus(String paramString)
    throws BusinessException;

  public abstract List<BusinessOrder> queryConfirmDoubtOrder(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract MainOrder queryMainOrder(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract List<MainOrder> queryMainOrders(int paramInt1, int paramInt2, EnumOrderStatus paramEnumOrderStatus, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract List<MainOrder> queryMainOrdersVer4(int paramInt1, int paramInt2, List<EnumOrderStatus> paramList, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract PayOrder queryPayOrder(byte[] paramArrayOfByte)
    throws BusinessException;

  public abstract List<PayOrder> queryPayOrders(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract List<PayOrder> queryPayOrdersVer4(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract PreDepositInfo queryPreDeposit(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract List<TerminalBackInfo> queryTerminalInfoBack(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, EnumResultsSortType paramEnumResultsSortType)
    throws BusinessException;

  public abstract List<BusinessOrder> queryUnsolvedOrder(EnumCardAppType paramEnumCardAppType)
    throws BusinessException;

  public abstract UserInfo queryUserInfo(String paramString)
    throws BusinessException;

  public abstract UserInfo queryUserInfoVer2(String paramString)
    throws BusinessException;

  public abstract VersionInfo queryVersion()
    throws BusinessException;

  public abstract int register(UserInfo paramUserInfo)
    throws BusinessException;

  public abstract int registerVer2(UserInfo paramUserInfo)
    throws BusinessException;

  public abstract boolean remoteRecharge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException;

  public abstract PasswordPrompt retrievePassword(String paramString)
    throws BusinessException;

  public abstract int setOrderStatus(byte[] paramArrayOfByte1, EnumOrderType paramEnumOrderType, byte[] paramArrayOfByte2, EnumOrderStatus paramEnumOrderStatus)
    throws BusinessException;

  public abstract int terminalInfoBack(TerminalBackInfo paramTerminalBackInfo)
    throws BusinessException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.CardAppTrade
 * JD-Core Version:    0.6.0
 */