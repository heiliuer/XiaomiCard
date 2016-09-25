package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayableCardInfo extends CardInfo
{
  public static final Parcelable.Creator<PayableCardInfo> CREATOR = new Parcelable.Creator()
  {
    public PayableCardInfo createFromParcel(Parcel paramParcel)
    {
      PayableCardInfo localPayableCardInfo = new PayableCardInfo(null);
      localPayableCardInfo.readFromParcel(paramParcel);
      return localPayableCardInfo;
    }

    public PayableCardInfo[] newArray(int paramInt)
    {
      return new PayableCardInfo[paramInt];
    }
  };
  private static final String KEY_FEES = "fees";
  private Map<FeeInfo.ActionType, List<FeeInfo>> mActionType2FeeInfoListMap = new HashMap();
  private List<FeeInfo> mActiveFeeInfos;
  public List<OrderInfo> mUnfinishOrderInfos = new CopyOnWriteArrayList();

  public PayableCardInfo(String paramString)
  {
    super(paramString);
  }

  public List<FeeInfo> getActiveFeeInfoList()
  {
    if (this.mActiveFeeInfos == null)
      if (!this.mHasIssue)
        break label38;
    label38: for (this.mActiveFeeInfos = ((List)this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.recharge)); ; this.mActiveFeeInfos = ((List)this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.issue)))
      return this.mActiveFeeInfos;
  }

  public CardInfo parse(JSONObject paramJSONObject)
  {
    super.parse(paramJSONObject);
    if ((paramJSONObject != null) && (paramJSONObject.has("fees")))
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("fees");
        for (int i = 0; i < localJSONArray.length(); i++)
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          FeeInfo localFeeInfo = new FeeInfo();
          localFeeInfo.parse(localJSONObject);
          if (this.mActionType2FeeInfoListMap.get(localFeeInfo.mActionType) == null)
            this.mActionType2FeeInfoListMap.put(localFeeInfo.mActionType, new ArrayList());
          if (((List)this.mActionType2FeeInfoListMap.get(localFeeInfo.mActionType)).contains(localFeeInfo))
            continue;
          ((List)this.mActionType2FeeInfoListMap.get(localFeeInfo.mActionType)).add(localFeeInfo);
        }
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("parse fee info list failed.serialize object:" + paramJSONObject.toString(), localJSONException);
      }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    super.readFromParcel(paramParcel);
    int i = paramParcel.readInt();
    for (int j = 0; j < i; j++)
      this.mActionType2FeeInfoListMap.put(FeeInfo.ActionType.valueOf(paramParcel.readString()), paramParcel.readArrayList(FeeInfo.class.getClassLoader()));
    this.mUnfinishOrderInfos = new CopyOnWriteArrayList();
    this.mUnfinishOrderInfos.addAll(paramParcel.readArrayList(OrderInfo.class.getClassLoader()));
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = super.serialize();
    JSONArray localJSONArray;
    if ((localJSONObject != null) && (this.mActionType2FeeInfoListMap != null))
    {
      localJSONArray = new JSONArray();
      Iterator localIterator1 = this.mActionType2FeeInfoListMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        FeeInfo.ActionType localActionType = (FeeInfo.ActionType)localIterator1.next();
        Iterator localIterator2 = ((List)this.mActionType2FeeInfoListMap.get(localActionType)).iterator();
        while (localIterator2.hasNext())
          localJSONArray.put(((FeeInfo)localIterator2.next()).serialize());
      }
    }
    try
    {
      localJSONObject.put("fees", localJSONArray);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize fee info list failed.fee array:" + localJSONArray.toString(), localJSONException);
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    if (this.mActionType2FeeInfoListMap == null)
      paramParcel.writeInt(0);
    while (true)
    {
      paramParcel.writeList(this.mUnfinishOrderInfos);
      return;
      paramParcel.writeInt(this.mActionType2FeeInfoListMap.size());
      Iterator localIterator = this.mActionType2FeeInfoListMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        FeeInfo.ActionType localActionType = (FeeInfo.ActionType)localIterator.next();
        paramParcel.writeString(localActionType.name());
        paramParcel.writeList((List)this.mActionType2FeeInfoListMap.get(localActionType));
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.PayableCardInfo
 * JD-Core Version:    0.6.0
 */