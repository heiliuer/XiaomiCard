package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.Coder;
import org.json.JSONException;
import org.json.JSONObject;

public class FmshCardInfo extends PayableCardInfo
{
  public static final String CARD_INFO_FEILD_APP_NO = "app_no";
  public static final Parcelable.Creator<FmshCardInfo> CREATOR = new Parcelable.Creator()
  {
    public FmshCardInfo createFromParcel(Parcel paramParcel)
    {
      FmshCardInfo localFmshCardInfo = new FmshCardInfo();
      localFmshCardInfo.readFromParcel(paramParcel);
      return localFmshCardInfo;
    }

    public FmshCardInfo[] newArray(int paramInt)
    {
      return new FmshCardInfo[paramInt];
    }
  };
  private static final String PATTERN_TRADE_TIME_SOURCE_DEFAULT = "yyyyMMdd HHmmss";
  private static final String PATTERN_TRADE_TIME_TARGET_DEFAULT = "yyyy/MM/dd HH:mm:ss";
  public byte[] mAppNo;

  public FmshCardInfo()
  {
    this(null);
  }

  public FmshCardInfo(CardAppInfo paramCardAppInfo)
  {
    this(paramCardAppInfo, null);
  }

  public FmshCardInfo(CardAppInfo paramCardAppInfo, String paramString)
  {
    super(paramString);
    fillInfo(paramCardAppInfo);
  }

  private void fillInfo(CardAppInfo paramCardAppInfo)
  {
    if (paramCardAppInfo != null)
    {
      this.mHasIssue = true;
      this.mCardBalance = paramCardAppInfo.balance;
      this.mCardNo = paramCardAppInfo.cardFaceNo;
      this.mAppNo = paramCardAppInfo.appNo;
      if (paramCardAppInfo.cardType == EnumCardAppType.CARD_APP_TYPE_SH.getId())
        this.mCardType = "SPTC";
    }
  }

  private byte[] readBytesWithNull(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    byte[] arrayOfByte = null;
    if (i >= 0)
    {
      arrayOfByte = new byte[i];
      paramParcel.readByteArray(arrayOfByte);
    }
    return arrayOfByte;
  }

  private void writeBytesWithNull(Parcel paramParcel, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      paramParcel.writeInt(-1);
    while (true)
    {
      return;
      paramParcel.writeInt(paramArrayOfByte.length);
      paramParcel.writeByteArray(paramArrayOfByte);
    }
  }

  public String getTradeTimeSourceFormat()
  {
    return "yyyyMMdd HHmmss";
  }

  public String getTradeTimeTargetFormat()
  {
    return "yyyy/MM/dd HH:mm:ss";
  }

  public CardInfo parse(JSONObject paramJSONObject)
  {
    super.parse(paramJSONObject);
    if (paramJSONObject != null)
      this.mAppNo = Coder.hexStringToBytes(paramJSONObject.optString("app_no"));
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    super.readFromParcel(paramParcel);
    this.mAppNo = readBytesWithNull(paramParcel);
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = super.serialize();
    try
    {
      localJSONObject.put("app_no", Coder.bytesToHexString(this.mAppNo));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize cardinfo to jsonobject failed!", localJSONException);
    }
  }

  public void updateInfo(FmshCardInfo paramFmshCardInfo)
  {
    super.updateInfo(paramFmshCardInfo);
    this.mAppNo = paramFmshCardInfo.mAppNo;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    writeBytesWithNull(paramParcel, this.mAppNo);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.FmshCardInfo
 * JD-Core Version:    0.6.0
 */