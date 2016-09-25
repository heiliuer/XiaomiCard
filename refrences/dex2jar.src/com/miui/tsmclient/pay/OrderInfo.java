package com.miui.tsmclient.pay;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ObjectParser;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderInfo
  implements Parcelable, ObjectParser<OrderInfo>
{
  public static final Parcelable.Creator<OrderInfo> CREATOR = new Parcelable.Creator()
  {
    public OrderInfo createFromParcel(Parcel paramParcel)
    {
      return new OrderInfo(paramParcel, null);
    }

    public OrderInfo[] newArray(int paramInt)
    {
      return new OrderInfo[paramInt];
    }
  };
  public static final String KEY_ACTIONTOKEN = "actionToken";
  public static final String KEY_ORDERID = "orderId";
  public static final String KEY_ORDERSTATUS = "orderStatus";
  public static final String KEY_PAYFEE = "payFee";
  public static final String KEY_PAYSTRING = "payString";
  public static final String KEY_PAYTIME = "payTime";
  public List<ActionToken> mActionTokens;
  public String mCardType;
  public String mCityId;
  public String mOrderId;
  public OrderStatus mOrderStatus;
  public String mPayExtra;
  public int mPayFee;
  public long mPayTime;
  public PayType mPayType = PayType.Mipay;

  public OrderInfo()
  {
  }

  private OrderInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public OrderInfo parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.mOrderId = paramJSONObject.optString("orderId");
      if (paramJSONObject.has("orderStatus"))
        this.mOrderStatus = OrderStatus.newInstance(paramJSONObject.optInt("orderStatus"));
      this.mPayExtra = paramJSONObject.optString("payString");
      this.mPayTime = paramJSONObject.optLong("payTime");
      this.mPayFee = paramJSONObject.optInt("payFee");
      this.mCardType = paramJSONObject.optString("cardName");
      this.mCityId = paramJSONObject.optString("cityId");
      JSONArray localJSONArray = paramJSONObject.optJSONArray("actionToken");
      if (localJSONArray != null)
      {
        this.mActionTokens = new ArrayList();
        int i = 0;
        while (true)
          if (i < localJSONArray.length())
          {
            ActionToken localActionToken = new ActionToken();
            try
            {
              this.mActionTokens.add(localActionToken.parse(localJSONArray.getJSONObject(i)));
              i++;
            }
            catch (JSONException localJSONException)
            {
              while (true)
                LogUtils.e("parse action token failed.", localJSONException);
            }
          }
      }
    }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mOrderId = paramParcel.readString();
    String str1 = paramParcel.readString();
    if (!TextUtils.isEmpty(str1))
      this.mOrderStatus = OrderStatus.valueOf(str1);
    this.mActionTokens = paramParcel.readArrayList(ActionToken.class.getClassLoader());
    this.mPayExtra = paramParcel.readString();
    String str2 = paramParcel.readString();
    if (!TextUtils.isEmpty(str2))
      this.mPayType = PayType.valueOf(str2);
    this.mPayTime = paramParcel.readLong();
    this.mPayFee = paramParcel.readInt();
    this.mCardType = paramParcel.readString();
    this.mCityId = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mOrderId);
    String str1;
    if (this.mOrderStatus == null)
    {
      str1 = "";
      paramParcel.writeString(str1);
      paramParcel.writeList(this.mActionTokens);
      paramParcel.writeString(this.mPayExtra);
      if (this.mPayType != null)
        break label100;
    }
    label100: for (String str2 = ""; ; str2 = this.mPayType.name())
    {
      paramParcel.writeString(str2);
      paramParcel.writeLong(this.mPayTime);
      paramParcel.writeInt(this.mPayFee);
      paramParcel.writeString(this.mCardType);
      paramParcel.writeString(this.mCityId);
      return;
      str1 = this.mOrderStatus.name();
      break;
    }
  }

  public static enum OrderStatus
  {
    private int mId;

    static
    {
      paid = new OrderStatus("paid", 1, 2);
      finish = new OrderStatus("finish", 2, 0);
      OrderStatus[] arrayOfOrderStatus = new OrderStatus[3];
      arrayOfOrderStatus[0] = waitingpay;
      arrayOfOrderStatus[1] = paid;
      arrayOfOrderStatus[2] = finish;
      $VALUES = arrayOfOrderStatus;
    }

    private OrderStatus(int paramInt)
    {
      this.mId = paramInt;
    }

    public static OrderStatus newInstance(int paramInt)
    {
      OrderStatus[] arrayOfOrderStatus = values();
      int i = arrayOfOrderStatus.length;
      int j = 0;
      OrderStatus localOrderStatus;
      if (j < i)
      {
        localOrderStatus = arrayOfOrderStatus[j];
        if (localOrderStatus.mId != paramInt);
      }
      while (true)
      {
        return localOrderStatus;
        j++;
        break;
        localOrderStatus = null;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.OrderInfo
 * JD-Core Version:    0.6.0
 */