package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.model.TradeLog;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CardInfo
  implements Parcelable, JSONSerializable, ObjectParser<CardInfo>
{
  public static final String CARD_TYPE_BANKCARD = "BANKCARD";
  public static final String CARD_TYPE_BMAC = "BMAC";
  public static final String CARD_TYPE_HZT = "HZT";
  public static final String CARD_TYPE_LNT = "LNT";
  public static final String CARD_TYPE_SPTC = "SPTC";
  public static final String CARD_TYPE_SUZHOUTONG = "SUZHOUTONG";
  public static final String CARD_TYPE_SZT = "SZT";
  public static final String CARD_TYPE_WHT = "WHT";
  public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator()
  {
    public CardInfo createFromParcel(Parcel paramParcel)
    {
      return new CardInfo(paramParcel, null);
    }

    public CardInfo[] newArray(int paramInt)
    {
      return new CardInfo[paramInt];
    }
  };
  public static final String KEY_AID = "aid";
  public static final String KEY_CARDNAME = "cardName";
  public static final String KEY_CARD_BALANCE = "card_balance";
  public static final String KEY_CARD_NO = "card_no";
  public static final String KEY_CARD_STATUS = "card_status";
  public static final String KEY_CARD_SUB_NAME = "card_sub_name";
  public static final String KEY_CARD_UI_INFO = "card_ui_info";
  public static final String KEY_HAS_ISSUE = "has_issue";
  public static final String KEY_ISSUEFEE = "issueFee";
  public static final String KEY_IS_DEFAULT = "is_default";
  public static final String KEY_LAST_USED = "last_used";
  public static final String KEY_STATUS = "status";
  public static final String KEY_TITLE = "title";
  public String mAid;
  public int mCardBalance;
  public String mCardName;
  public String mCardNo;
  public String mCardSubName;
  public String mCardType;
  public CardUIInfo mCardUIInfo;
  public boolean mHasIssue;
  public boolean mIsDefault;
  public boolean mIsPack = false;
  public int mIssueFee;
  public ServiceStatus mServiceStatus;
  public Status mStatus = null;
  public List<TradeLog> mTradeLogs;

  private CardInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public CardInfo(String paramString)
  {
    this.mCardType = paramString;
  }

  @Deprecated
  public static List<String> getActivedCards()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "SZT";
    arrayOfString[1] = "SPTC";
    return Arrays.asList(arrayOfString);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (this == paramObject)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
        continue;
      }
      CardInfo localCardInfo = (CardInfo)paramObject;
      bool = TextUtils.equals(this.mCardType, localCardInfo.mCardType);
    }
  }

  public int hashCode()
  {
    return this.mCardType.hashCode();
  }

  public CardInfo parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.mCardName = paramJSONObject.optString("title");
      if (paramJSONObject.has("card_no"))
        this.mCardNo = paramJSONObject.optString("card_no");
      if (paramJSONObject.has("has_issue"))
        this.mHasIssue = paramJSONObject.optBoolean("has_issue");
      this.mIsDefault = paramJSONObject.optBoolean("is_default");
      if (paramJSONObject.has("card_balance"))
        this.mCardBalance = paramJSONObject.optInt("card_balance");
      this.mCardType = paramJSONObject.optString("cardName");
      if (paramJSONObject.has("card_sub_name"))
        this.mCardSubName = paramJSONObject.optString("card_sub_name");
      this.mIssueFee = paramJSONObject.optInt("issueFee");
      this.mAid = paramJSONObject.optString("aid");
      this.mServiceStatus = ServiceStatus.newInstance(paramJSONObject.optInt("status"));
      this.mCardUIInfo = new CardUIInfo();
      this.mCardUIInfo.parse(paramJSONObject);
      if (paramJSONObject.has("card_status"))
        this.mStatus = Status.valueOf(paramJSONObject.optString("card_status"));
    }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    int i = 1;
    this.mCardName = paramParcel.readString();
    this.mCardNo = paramParcel.readString();
    int j;
    int k;
    if (paramParcel.readByte() == i)
    {
      j = i;
      this.mHasIssue = j;
      if (paramParcel.readByte() != i)
        break label184;
      k = i;
      label44: this.mIsDefault = k;
      this.mCardBalance = paramParcel.readInt();
      this.mCardType = paramParcel.readString();
      String str1 = paramParcel.readString();
      if (!TextUtils.isEmpty(str1))
        this.mServiceStatus = ServiceStatus.valueOf(str1);
      if (paramParcel.readByte() != i)
        break label190;
    }
    while (true)
    {
      this.mIsPack = i;
      this.mCardSubName = paramParcel.readString();
      this.mCardUIInfo = ((CardUIInfo)paramParcel.readParcelable(CardUIInfo.class.getClassLoader()));
      this.mIssueFee = paramParcel.readInt();
      this.mAid = paramParcel.readString();
      this.mTradeLogs = paramParcel.readArrayList(TradeLog.class.getClassLoader());
      String str2 = paramParcel.readString();
      if (!TextUtils.isEmpty(str2))
        this.mStatus = Status.valueOf(str2);
      return;
      j = 0;
      break;
      label184: k = 0;
      break label44;
      label190: i = 0;
    }
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("title", this.mCardName);
      localJSONObject.put("card_no", this.mCardNo);
      localJSONObject.put("has_issue", this.mHasIssue);
      localJSONObject.put("is_default", this.mIsDefault);
      localJSONObject.put("card_balance", this.mCardBalance);
      if (this.mCardType != null)
        localJSONObject.put("cardName", this.mCardType.toString());
      localJSONObject.put("card_sub_name", this.mCardSubName);
      localJSONObject.put("issueFee", this.mIssueFee);
      localJSONObject.put("aid", this.mAid);
      if (this.mServiceStatus != null)
        localJSONObject.put("status", this.mServiceStatus.getId());
      if (this.mCardUIInfo != null)
        localJSONObject.put("card_ui_info", this.mCardUIInfo.serialize());
      if (this.mStatus != null)
        localJSONObject.put("card_status", this.mStatus.toString());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize cardinfo to jsonobject failed!", localJSONException);
    }
  }

  public void updateInfo(CardInfo paramCardInfo)
  {
    this.mCardNo = paramCardInfo.mCardNo;
    this.mHasIssue = paramCardInfo.mHasIssue;
    this.mIsDefault = paramCardInfo.mIsDefault;
    this.mCardBalance = paramCardInfo.mCardBalance;
    this.mCardSubName = paramCardInfo.mCardSubName;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.mCardName);
    paramParcel.writeString(this.mCardNo);
    int j;
    int k;
    label45: String str1;
    if (this.mHasIssue)
    {
      j = i;
      paramParcel.writeByte((byte)j);
      if (!this.mIsDefault)
        break label165;
      k = i;
      paramParcel.writeByte((byte)k);
      paramParcel.writeInt(this.mCardBalance);
      paramParcel.writeString(this.mCardType);
      if (this.mServiceStatus != null)
        break label171;
      str1 = "";
      label80: paramParcel.writeString(str1);
      if (!this.mIsPack)
        break label183;
      label93: paramParcel.writeByte((byte)i);
      paramParcel.writeString(this.mCardSubName);
      paramParcel.writeParcelable(this.mCardUIInfo, paramInt);
      paramParcel.writeInt(this.mIssueFee);
      paramParcel.writeString(this.mAid);
      paramParcel.writeList(this.mTradeLogs);
      if (this.mStatus != null)
        break label188;
    }
    label165: label171: label183: label188: for (String str2 = ""; ; str2 = this.mStatus.name())
    {
      paramParcel.writeString(str2);
      return;
      j = 0;
      break;
      k = 0;
      break label45;
      str1 = this.mServiceStatus.name();
      break label80;
      i = 0;
      break label93;
    }
  }

  public static enum Status
  {
    static
    {
      DATA_ILLEGAL = new Status("DATA_ILLEGAL", 3);
      Status[] arrayOfStatus = new Status[4];
      arrayOfStatus[0] = ACTIVE;
      arrayOfStatus[1] = IN_BLACKLIST;
      arrayOfStatus[2] = NEGATIVE;
      arrayOfStatus[3] = DATA_ILLEGAL;
      $VALUES = arrayOfStatus;
    }
  }

  public static enum ServiceStatus
  {
    private int mId;

    static
    {
      ServiceStatus[] arrayOfServiceStatus = new ServiceStatus[3];
      arrayOfServiceStatus[0] = active;
      arrayOfServiceStatus[1] = negative;
      arrayOfServiceStatus[2] = no_stock;
      $VALUES = arrayOfServiceStatus;
    }

    private ServiceStatus(int paramInt)
    {
      this.mId = paramInt;
    }

    public static ServiceStatus newInstance(int paramInt)
    {
      ServiceStatus[] arrayOfServiceStatus = values();
      int i = arrayOfServiceStatus.length;
      int j = 0;
      ServiceStatus localServiceStatus;
      if (j < i)
      {
        localServiceStatus = arrayOfServiceStatus[j];
        if (localServiceStatus.mId != paramInt);
      }
      while (true)
      {
        return localServiceStatus;
        j++;
        break;
        localServiceStatus = null;
      }
    }

    public int getId()
    {
      return this.mId;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.CardInfo
 * JD-Core Version:    0.6.0
 */