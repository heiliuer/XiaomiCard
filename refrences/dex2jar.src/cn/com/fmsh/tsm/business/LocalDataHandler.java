package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import java.util.ArrayList;

public abstract interface LocalDataHandler
{
  public abstract void deleteHistoryByFaceId(String paramString);

  public abstract void deleteHistoryById(String paramString);

  public abstract void deleteNotiecById(long paramLong);

  public abstract String[] findAllFaceId();

  public abstract ArrayList<Notice> findAllNotice();

  public abstract BusinessOrder findRechargeRecordByInfo(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract BusinessOrder findRechargeRecordByOrder(String paramString);

  public abstract int getMaxNoticeId();

  public abstract void insertHistory(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, int paramInt4, String paramString3);

  public abstract void insertNotice(Notice paramNotice);

  public abstract void insertTrade(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, String paramString6);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.LocalDataHandler
 * JD-Core Version:    0.6.0
 */