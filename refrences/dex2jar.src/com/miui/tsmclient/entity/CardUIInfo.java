package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CardUIInfo
  implements Parcelable, ObjectParser<CardUIInfo>, JSONSerializable
{
  public static final Parcelable.Creator<CardUIInfo> CREATOR = new Parcelable.Creator()
  {
    public CardUIInfo createFromParcel(Parcel paramParcel)
    {
      return new CardUIInfo(paramParcel, null);
    }

    public CardUIInfo[] newArray(int paramInt)
    {
      return new CardUIInfo[paramInt];
    }
  };
  public static final String KEY_DETAILDESC = "detailDesc";
  public static final String KEY_ISSUEDDETAILBG = "issuedDetailBg";
  public static final String KEY_ISSUEDLISTBG = "issuedListBg";
  public static final String KEY_ISSUEDLISTBGHD = "issuedListBgHd";
  public static final String KEY_LOGO = "logo";
  public static final String KEY_PREISSUEDDETAILBG = "preIssuedDetailBg";
  public static final String KEY_PREISSUEDLISTBG = "preIssuedListBg";
  public static final String KEY_SUBTITILE = "subTitle";
  public String mCardDesc;
  public String mCardDetailDesc;
  public String mCardIssuedListBgHdUrl;
  public String mCardIssuedListBgUrl;
  public String mCardLogoUrl;
  public String mCardPreIssuedListBgUrl;
  public String mIssuedDetailBgUrl;
  public String mPreIssuedDetailBgUrl;

  public CardUIInfo()
  {
  }

  private CardUIInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  private void doParse(JSONObject paramJSONObject)
  {
    this.mCardDesc = paramJSONObject.optString("subTitle");
    this.mCardPreIssuedListBgUrl = paramJSONObject.optString("preIssuedListBg");
    this.mCardIssuedListBgUrl = paramJSONObject.optString("issuedListBg");
    this.mCardLogoUrl = paramJSONObject.optString("logo");
    this.mPreIssuedDetailBgUrl = paramJSONObject.optString("preIssuedDetailBg");
    this.mCardDetailDesc = paramJSONObject.optString("detailDesc");
    this.mIssuedDetailBgUrl = paramJSONObject.optString("issuedDetailBg");
    this.mCardIssuedListBgHdUrl = paramJSONObject.optString("issuedListBgHd");
  }

  public int describeContents()
  {
    return 0;
  }

  public CardUIInfo parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      if (!paramJSONObject.has("card_ui_info"))
        break label25;
      doParse(paramJSONObject.optJSONObject("card_ui_info"));
    }
    while (true)
    {
      return this;
      label25: doParse(paramJSONObject);
    }
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mCardDesc = paramParcel.readString();
    this.mCardPreIssuedListBgUrl = paramParcel.readString();
    this.mCardIssuedListBgUrl = paramParcel.readString();
    this.mCardLogoUrl = paramParcel.readString();
    this.mPreIssuedDetailBgUrl = paramParcel.readString();
    this.mCardDetailDesc = paramParcel.readString();
    this.mIssuedDetailBgUrl = paramParcel.readString();
    this.mCardIssuedListBgHdUrl = paramParcel.readString();
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("subTitle", this.mCardDesc);
      localJSONObject.put("preIssuedListBg", this.mCardPreIssuedListBgUrl);
      localJSONObject.put("issuedListBg", this.mCardIssuedListBgUrl);
      localJSONObject.put("logo", this.mCardLogoUrl);
      localJSONObject.put("preIssuedDetailBg", this.mPreIssuedDetailBgUrl);
      localJSONObject.put("detailDesc", this.mCardDetailDesc);
      localJSONObject.put("issuedDetailBg", this.mIssuedDetailBgUrl);
      localJSONObject.put("issuedListBgHd", this.mCardIssuedListBgHdUrl);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize carduiinfo to jsonobject failed!", localJSONException);
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mCardDesc);
    paramParcel.writeString(this.mCardPreIssuedListBgUrl);
    paramParcel.writeString(this.mCardIssuedListBgUrl);
    paramParcel.writeString(this.mCardLogoUrl);
    paramParcel.writeString(this.mPreIssuedDetailBgUrl);
    paramParcel.writeString(this.mCardDetailDesc);
    paramParcel.writeString(this.mIssuedDetailBgUrl);
    paramParcel.writeString(this.mCardIssuedListBgHdUrl);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.CardUIInfo
 * JD-Core Version:    0.6.0
 */