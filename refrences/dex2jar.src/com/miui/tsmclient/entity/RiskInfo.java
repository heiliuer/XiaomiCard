package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.miui.tsmclient.seitsm.TsmRpcModels.ApplyChannel;
import com.miui.tsmclient.seitsm.TsmRpcModels.CaptureMethod;
import com.miui.tsmclient.seitsm.TsmRpcModels.DeviceType;
import com.miui.tsmclient.seitsm.TsmRpcModels.RiskInfo;
import com.miui.tsmclient.seitsm.TsmRpcModels.RiskInfo.Builder;
import com.miui.tsmclient.util.SysUtils;
import java.util.List;

public class RiskInfo
  implements Parcelable
{
  public static final Parcelable.Creator<RiskInfo> CREATOR = new Parcelable.Creator()
  {
    public RiskInfo createFromParcel(Parcel paramParcel)
    {
      return new RiskInfo(paramParcel);
    }

    public RiskInfo[] newArray(int paramInt)
    {
      return new RiskInfo[paramInt];
    }
  };
  private String mAccountEmailLife = "";
  private String mAccountIDHash = "";
  private int mAccountScore;
  private TsmRpcModels.ApplyChannel mApplyChannel;
  private String mBillingAddress = "";
  private String mBillingZip = "";
  private TsmRpcModels.CaptureMethod mCaptureMethod;
  private String mCardHolderName = "";
  private String mDeviceLanguage = "";
  private String mDeviceLocation = "";
  private String mDeviceName = "";
  private String mDeviceNumber = "";
  private int mDeviceSIMNumber;
  private int mDeviceScore;
  private TsmRpcModels.DeviceType mDeviceType;
  private String mExtensiveDeviceLocation = "";
  private List<String> mFullDeviceNumber;
  private int mPhoneNumberScore;
  private List<String> mRiskReasonCode;
  private int mRiskScore;
  private String mRiskStandardVersion = "";
  private String mSourceIP = "";

  private RiskInfo()
  {
  }

  protected RiskInfo(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    TsmRpcModels.DeviceType localDeviceType;
    int j;
    TsmRpcModels.ApplyChannel localApplyChannel;
    label130: int k;
    if (i == -1)
    {
      localDeviceType = null;
      this.mDeviceType = localDeviceType;
      this.mDeviceLanguage = paramParcel.readString();
      this.mDeviceName = paramParcel.readString();
      j = paramParcel.readInt();
      if (j != -1)
        break label301;
      localApplyChannel = null;
      this.mApplyChannel = localApplyChannel;
      this.mDeviceLocation = paramParcel.readString();
      this.mExtensiveDeviceLocation = paramParcel.readString();
      k = paramParcel.readInt();
      if (k != -1)
        break label312;
    }
    while (true)
    {
      this.mCaptureMethod = localCaptureMethod;
      this.mAccountEmailLife = paramParcel.readString();
      this.mCardHolderName = paramParcel.readString();
      this.mBillingAddress = paramParcel.readString();
      this.mBillingZip = paramParcel.readString();
      this.mRiskScore = paramParcel.readInt();
      this.mRiskStandardVersion = paramParcel.readString();
      this.mDeviceScore = paramParcel.readInt();
      this.mAccountScore = paramParcel.readInt();
      this.mPhoneNumberScore = paramParcel.readInt();
      this.mRiskReasonCode = paramParcel.createStringArrayList();
      this.mDeviceNumber = paramParcel.readString();
      this.mFullDeviceNumber = paramParcel.createStringArrayList();
      this.mSourceIP = paramParcel.readString();
      this.mDeviceSIMNumber = paramParcel.readInt();
      this.mAccountIDHash = paramParcel.readString();
      return;
      localDeviceType = TsmRpcModels.DeviceType.values()[i];
      break;
      label301: localApplyChannel = TsmRpcModels.ApplyChannel.values()[j];
      break label130;
      label312: localCaptureMethod = TsmRpcModels.CaptureMethod.values()[k];
    }
  }

  public TsmRpcModels.RiskInfo.Builder buildTSMRiskInfoBuilder()
  {
    TsmRpcModels.RiskInfo.Builder localBuilder = TsmRpcModels.RiskInfo.newBuilder();
    localBuilder.setDeviceType(getDeviceType());
    localBuilder.setDeviceLanguage(getDeviceLanguage());
    localBuilder.setDeviceName(getDeviceName());
    localBuilder.setApplyChannel(getApplyChannel());
    localBuilder.setDeviceLocation(getDeviceLocation());
    localBuilder.setExtensiveDeviceLocation(getExtensiveDeviceLocation());
    localBuilder.setCaptureMethod(getCaptureMethod());
    localBuilder.setAccountEmailLife(getAccountEmailLife());
    localBuilder.setCardHolderName(getCardHolderName());
    localBuilder.setBillingAddress(getBillingAddress());
    localBuilder.setBillingZip(getBillingZip());
    localBuilder.setRiskScore(getRiskScore());
    localBuilder.setRiskStandardVersion(getRiskStandardVersion());
    localBuilder.setDeviceScore(getDeviceScore());
    localBuilder.setAccountScore(getAccountScore());
    localBuilder.setPhoneNumberScore(getPhoneNumberScore());
    localBuilder.setDeviceType(getDeviceType());
    localBuilder.setDeviceNumber(getDeviceNumber());
    localBuilder.addAllFullDeviceNumber(getFullDeviceNumber());
    localBuilder.setSourceIP(getSourceIP());
    localBuilder.setDeviceSIMNumber(getDeviceSIMNumber());
    return localBuilder;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAccountEmailLife()
  {
    return this.mAccountEmailLife;
  }

  public String getAccountIDHash()
  {
    return this.mAccountIDHash;
  }

  public int getAccountScore()
  {
    return this.mAccountScore;
  }

  public TsmRpcModels.ApplyChannel getApplyChannel()
  {
    return this.mApplyChannel;
  }

  public String getBillingAddress()
  {
    return this.mBillingAddress;
  }

  public String getBillingZip()
  {
    return this.mBillingZip;
  }

  public TsmRpcModels.CaptureMethod getCaptureMethod()
  {
    return this.mCaptureMethod;
  }

  public String getCardHolderName()
  {
    return this.mCardHolderName;
  }

  public String getDeviceLanguage()
  {
    return this.mDeviceLanguage;
  }

  public String getDeviceLocation()
  {
    return this.mDeviceLocation;
  }

  public String getDeviceName()
  {
    return this.mDeviceName;
  }

  public String getDeviceNumber()
  {
    return this.mDeviceNumber;
  }

  public int getDeviceSIMNumber()
  {
    return this.mDeviceSIMNumber;
  }

  public int getDeviceScore()
  {
    return this.mDeviceScore;
  }

  public TsmRpcModels.DeviceType getDeviceType()
  {
    return this.mDeviceType;
  }

  public String getExtensiveDeviceLocation()
  {
    return this.mExtensiveDeviceLocation;
  }

  public List<String> getFullDeviceNumber()
  {
    return this.mFullDeviceNumber;
  }

  public int getPhoneNumberScore()
  {
    return this.mPhoneNumberScore;
  }

  public List<String> getRiskReasonCode()
  {
    return this.mRiskReasonCode;
  }

  public int getRiskScore()
  {
    return this.mRiskScore;
  }

  public String getRiskStandardVersion()
  {
    return this.mRiskStandardVersion;
  }

  public String getSourceIP()
  {
    return this.mSourceIP;
  }

  public void setAccountEmailLife(String paramString)
  {
    this.mAccountEmailLife = paramString;
  }

  public void setAccountIDHash(String paramString)
  {
    this.mAccountIDHash = paramString;
  }

  public void setAccountScore(int paramInt)
  {
    this.mAccountScore = paramInt;
  }

  public void setApplyChannel(TsmRpcModels.ApplyChannel paramApplyChannel)
  {
    this.mApplyChannel = paramApplyChannel;
  }

  public void setBillingAddress(String paramString)
  {
    this.mBillingAddress = paramString;
  }

  public void setBillingZip(String paramString)
  {
    this.mBillingZip = paramString;
  }

  public void setCaptureMethod(TsmRpcModels.CaptureMethod paramCaptureMethod)
  {
    this.mCaptureMethod = paramCaptureMethod;
  }

  public void setCardHolderName(String paramString)
  {
    this.mCardHolderName = paramString;
  }

  public void setDeviceLanguage(String paramString)
  {
    this.mDeviceLanguage = paramString;
  }

  public void setDeviceLocation(String paramString)
  {
    this.mDeviceLocation = paramString;
  }

  public void setDeviceName(String paramString)
  {
    this.mDeviceName = paramString;
  }

  public void setDeviceNumber(String paramString)
  {
    this.mDeviceNumber = paramString;
  }

  public void setDeviceSIMNumber(int paramInt)
  {
    this.mDeviceSIMNumber = paramInt;
  }

  public void setDeviceScore(int paramInt)
  {
    this.mDeviceScore = paramInt;
  }

  public void setDeviceType(TsmRpcModels.DeviceType paramDeviceType)
  {
    this.mDeviceType = paramDeviceType;
  }

  public void setExtensiveDeviceLocation(String paramString)
  {
    this.mExtensiveDeviceLocation = paramString;
  }

  public void setFullDeviceNumber(List<String> paramList)
  {
    this.mFullDeviceNumber = paramList;
  }

  public void setPhoneNumberScore(int paramInt)
  {
    this.mPhoneNumberScore = paramInt;
  }

  public void setRiskReasonCode(List<String> paramList)
  {
    this.mRiskReasonCode = paramList;
  }

  public void setRiskScore(int paramInt)
  {
    this.mRiskScore = paramInt;
  }

  public void setRiskStandardVersion(String paramString)
  {
    this.mRiskStandardVersion = paramString;
  }

  public void setSourceIP(String paramString)
  {
    this.mSourceIP = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = -1;
    int j;
    int k;
    if (this.mDeviceType == null)
    {
      j = i;
      paramParcel.writeInt(j);
      paramParcel.writeString(this.mDeviceLanguage);
      paramParcel.writeString(this.mDeviceName);
      if (this.mApplyChannel != null)
        break label212;
      k = i;
      label45: paramParcel.writeInt(k);
      paramParcel.writeString(this.mDeviceLocation);
      paramParcel.writeString(this.mExtensiveDeviceLocation);
      if (this.mCaptureMethod != null)
        break label224;
    }
    while (true)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.mAccountEmailLife);
      paramParcel.writeString(this.mCardHolderName);
      paramParcel.writeString(this.mBillingAddress);
      paramParcel.writeString(this.mBillingZip);
      paramParcel.writeInt(this.mRiskScore);
      paramParcel.writeString(this.mRiskStandardVersion);
      paramParcel.writeInt(this.mDeviceScore);
      paramParcel.writeInt(this.mAccountScore);
      paramParcel.writeInt(this.mPhoneNumberScore);
      paramParcel.writeStringList(this.mRiskReasonCode);
      paramParcel.writeString(this.mDeviceNumber);
      paramParcel.writeStringList(this.mFullDeviceNumber);
      paramParcel.writeString(this.mSourceIP);
      paramParcel.writeInt(this.mDeviceSIMNumber);
      paramParcel.writeString(this.mAccountIDHash);
      return;
      j = this.mDeviceType.ordinal();
      break;
      label212: k = this.mApplyChannel.ordinal();
      break label45;
      label224: i = this.mCaptureMethod.ordinal();
    }
  }

  public static class Builder
  {
    private RiskInfo mRiskInfo = new RiskInfo(null);

    public RiskInfo build()
    {
      this.mRiskInfo.setDeviceLanguage(SysUtils.getDeviceLanguage6393());
      List localList = SysUtils.getSIMNumber();
      this.mRiskInfo.setFullDeviceNumber(localList);
      this.mRiskInfo.setDeviceSIMNumber(localList.size());
      this.mRiskInfo.setDeviceName(SysUtils.getDeviceName());
      return this.mRiskInfo;
    }

    public Builder setAccountEmailLife(String paramString)
    {
      this.mRiskInfo.setAccountEmailLife(paramString);
      return this;
    }

    public Builder setApplyChannel(TsmRpcModels.ApplyChannel paramApplyChannel)
    {
      this.mRiskInfo.setApplyChannel(paramApplyChannel);
      return this;
    }

    public Builder setBillingAddress(String paramString)
    {
      this.mRiskInfo.setBillingAddress(paramString);
      return this;
    }

    public Builder setBillingZip(String paramString)
    {
      this.mRiskInfo.setBillingZip(paramString);
      return this;
    }

    public Builder setCaptureMethod(TsmRpcModels.CaptureMethod paramCaptureMethod)
    {
      this.mRiskInfo.setCaptureMethod(paramCaptureMethod);
      return this;
    }

    public Builder setCardHolerName(String paramString)
    {
      this.mRiskInfo.setCardHolderName(paramString);
      return this;
    }

    public Builder setDeviceType(TsmRpcModels.DeviceType paramDeviceType)
    {
      this.mRiskInfo.setDeviceType(paramDeviceType);
      return this;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.RiskInfo
 * JD-Core Version:    0.6.0
 */