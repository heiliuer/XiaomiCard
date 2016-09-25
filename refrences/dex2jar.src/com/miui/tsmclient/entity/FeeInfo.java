package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FeeInfo
  implements Parcelable, JSONSerializable, ObjectParser<FeeInfo>
{
  public static final Parcelable.Creator<FeeInfo> CREATOR = new Parcelable.Creator()
  {
    public FeeInfo createFromParcel(Parcel paramParcel)
    {
      return new FeeInfo(paramParcel, null);
    }

    public FeeInfo[] newArray(int paramInt)
    {
      return new FeeInfo[paramInt];
    }
  };
  private static final String KEY_ACTIONTYPE = "actionType";
  private static final String KEY_DISPLAYFEE = "displayFee";
  private static final String KEY_FEEID = "feeId";
  private static final String KEY_MSG = "msg";
  private static final String KEY_PAYFEE = "payFee";
  private static final String KEY_RECHARGEFEE = "rechargeFee";
  public ActionType mActionType;
  public int mDisplayFee;
  public int mId;
  public String mMsg;
  public int mPayFee;
  public int mRechargeFee;

  public FeeInfo()
  {
  }

  private FeeInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (((paramObject instanceof FeeInfo)) && (((FeeInfo)paramObject).mId == this.mId));
    for (int i = 1; ; i = 0)
      return i;
  }

  public int hashCode()
  {
    return this.mId;
  }

  public FeeInfo parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.mId = paramJSONObject.optInt("feeId");
      this.mDisplayFee = paramJSONObject.optInt("displayFee");
      this.mPayFee = paramJSONObject.optInt("payFee");
      this.mRechargeFee = paramJSONObject.optInt("rechargeFee");
      this.mMsg = paramJSONObject.optString("msg");
      this.mActionType = ActionType.newInstance(paramJSONObject.optInt("actionType"));
    }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mId = paramParcel.readInt();
    this.mDisplayFee = paramParcel.readInt();
    this.mPayFee = paramParcel.readInt();
    this.mRechargeFee = paramParcel.readInt();
    this.mMsg = paramParcel.readString();
    this.mActionType = ActionType.valueOf(paramParcel.readString());
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("feeId", this.mId);
      localJSONObject.put("displayFee", this.mDisplayFee);
      localJSONObject.put("payFee", this.mPayFee);
      localJSONObject.put("rechargeFee", this.mRechargeFee);
      localJSONObject.put("msg", this.mMsg);
      localJSONObject.put("actionType", this.mActionType.getId());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize feeinfo failed.", localJSONException);
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mId);
    paramParcel.writeInt(this.mDisplayFee);
    paramParcel.writeInt(this.mPayFee);
    paramParcel.writeInt(this.mRechargeFee);
    paramParcel.writeString(this.mMsg);
    paramParcel.writeString(this.mActionType.name());
  }

  public static enum ActionType
  {
    private int mId;

    static
    {
      ActionType[] arrayOfActionType = new ActionType[2];
      arrayOfActionType[0] = issue;
      arrayOfActionType[1] = recharge;
      $VALUES = arrayOfActionType;
    }

    private ActionType(int paramInt)
    {
      this.mId = paramInt;
    }

    public static ActionType newInstance(int paramInt)
    {
      ActionType[] arrayOfActionType = values();
      int i = arrayOfActionType.length;
      int j = 0;
      ActionType localActionType;
      if (j < i)
      {
        localActionType = arrayOfActionType[j];
        if (localActionType.mId != paramInt);
      }
      while (true)
      {
        return localActionType;
        j++;
        break;
        localActionType = null;
      }
    }

    public int getId()
    {
      return this.mId;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.FeeInfo
 * JD-Core Version:    0.6.0
 */