package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.miui.tsmclient.seitsm.TsmRpcModels.BankCardInfo;
import com.miui.tsmclient.seitsm.TsmRpcModels.CardIssueChannel;
import com.miui.tsmclient.seitsm.TsmRpcModels.CardIssuerInfo;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BankCardInfo extends CardInfo
{
  public static final Parcelable.Creator<BankCardInfo> CREATOR = new Parcelable.Creator()
  {
    public BankCardInfo createFromParcel(Parcel paramParcel)
    {
      BankCardInfo localBankCardInfo = new BankCardInfo();
      localBankCardInfo.readFromParcel(paramParcel);
      return localBankCardInfo;
    }

    public BankCardInfo[] newArray(int paramInt)
    {
      return new BankCardInfo[paramInt];
    }
  };
  public static final int CREDIT = 2;
  public static final int DEBIT = 1;
  public static final int ISSUER_CHANNEL_CMB = 2;
  public static final int ISSUER_CHANNEL_UP = 1;
  public static final int VC_STATUS_ACTIVATED = 0;
  public static final int VC_STATUS_DELETE = 3;
  public static final int VC_STATUS_LOCK = 2;
  public static final int VC_STATUS_WAIT_ACTIVATE = 1;
  public String mBankCardPan;
  public int mBankCardType;
  public String mBankLogoUrl;
  public String mBankLogoWithNameUrl;
  public String mBankName;
  public String mCardArt;
  public String mCardProductName;
  public String mCardProductTypeId;
  public int mIssuerChannel;
  public String mIssuerId;
  public String mPanLastDigits;
  public String mPhoneLastDigits;
  public String mUserTerms;
  public String mVCReferenceId;
  public int mVCStatus;
  public String mVCardNumber;

  public BankCardInfo()
  {
    super("BANKCARD");
    this.mCardName = "MI Pay";
  }

  public static List<BankCardInfo> fillFromTsm(List<TsmRpcModels.BankCardInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      TsmRpcModels.BankCardInfo localBankCardInfo = (TsmRpcModels.BankCardInfo)localIterator.next();
      BankCardInfo localBankCardInfo1 = new BankCardInfo();
      localBankCardInfo1.mAid = localBankCardInfo.getAid();
      localBankCardInfo1.mVCReferenceId = localBankCardInfo.getVirtualCardReferenceId();
      localBankCardInfo1.mVCardNumber = localBankCardInfo.getVirtualCardNumber();
      localBankCardInfo1.mPanLastDigits = localBankCardInfo.getLastDigits();
      localBankCardInfo1.mVCStatus = localBankCardInfo.getVcStatus();
      localBankCardInfo1.mCardProductTypeId = localBankCardInfo.getProductId();
      localBankCardInfo1.mCardProductName = localBankCardInfo.getProductName();
      localBankCardInfo1.mUserTerms = localBankCardInfo.getUserTerms();
      localBankCardInfo1.mCardArt = localBankCardInfo.getCardArt();
      localBankCardInfo1.mBankCardType = localBankCardInfo.getCardType();
      TsmRpcModels.CardIssuerInfo localCardIssuerInfo = localBankCardInfo.getIssuerInfo();
      localBankCardInfo1.mBankName = localCardIssuerInfo.getBankName();
      localBankCardInfo1.mIssuerId = localCardIssuerInfo.getIssuerId();
      localBankCardInfo1.mIssuerChannel = localCardIssuerInfo.getCardIssueChannel().getNumber();
      localBankCardInfo1.mBankLogoUrl = localCardIssuerInfo.getLogoUrl();
      localBankCardInfo1.mBankLogoWithNameUrl = localCardIssuerInfo.getLogoWithBankNameUrl();
      localArrayList.add(localBankCardInfo1);
    }
    return localArrayList;
  }

  public CardInfo parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.mCardName = paramJSONObject.optString("title");
      if (paramJSONObject.has("card_no"))
        this.mCardNo = paramJSONObject.optString("card_no");
      this.mAid = paramJSONObject.optString("aid");
      this.mBankCardPan = paramJSONObject.optString("bank_card_pan");
      this.mBankCardType = paramJSONObject.optInt("bank_card_type");
      this.mVCReferenceId = paramJSONObject.optString("bank_card_vc_reference_id");
      this.mVCardNumber = paramJSONObject.optString("bank_card_vc_card_num");
      this.mCardProductTypeId = paramJSONObject.optString("bank_card_product_id");
      this.mIssuerId = paramJSONObject.optString("bank_card_issuer_id");
      this.mCardProductName = paramJSONObject.optString("bank_card_product_name");
      this.mUserTerms = paramJSONObject.optString("bank_card_user_terms");
      this.mCardArt = paramJSONObject.optString("bank_card_art");
      this.mVCStatus = paramJSONObject.optInt("bank_card_vc_status");
      this.mIssuerChannel = paramJSONObject.optInt("bank_card_issuer_channel");
      this.mPanLastDigits = paramJSONObject.optString("bank_card_pan_last_digits");
      this.mBankName = paramJSONObject.optString("bank_name");
      this.mBankLogoUrl = paramJSONObject.optString("bank_logo_url");
      this.mBankLogoWithNameUrl = paramJSONObject.optString("bank_logo_with_name_url");
    }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mCardName = paramParcel.readString();
    this.mCardNo = paramParcel.readString();
    this.mAid = paramParcel.readString();
    this.mBankCardPan = paramParcel.readString();
    this.mBankCardType = paramParcel.readInt();
    this.mVCReferenceId = paramParcel.readString();
    this.mVCardNumber = paramParcel.readString();
    this.mCardProductTypeId = paramParcel.readString();
    this.mIssuerId = paramParcel.readString();
    this.mCardProductName = paramParcel.readString();
    this.mUserTerms = paramParcel.readString();
    this.mCardArt = paramParcel.readString();
    this.mVCStatus = paramParcel.readInt();
    this.mPhoneLastDigits = paramParcel.readString();
    this.mIssuerChannel = paramParcel.readInt();
    this.mPanLastDigits = paramParcel.readString();
    this.mBankName = paramParcel.readString();
    this.mBankLogoUrl = paramParcel.readString();
    this.mBankLogoWithNameUrl = paramParcel.readString();
  }

  public JSONObject serialize()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("title", this.mCardName);
      localJSONObject.put("card_no", this.mCardNo);
      localJSONObject.put("aid", this.mAid);
      localJSONObject.put("bank_card_pan", this.mBankCardPan);
      localJSONObject.put("bank_card_type", this.mBankCardType);
      localJSONObject.put("bank_card_vc_reference_id", this.mVCReferenceId);
      localJSONObject.put("bank_card_vc_card_num", this.mVCardNumber);
      localJSONObject.put("bank_card_product_id", this.mCardProductTypeId);
      localJSONObject.put("bank_card_issuer_id", this.mIssuerId);
      localJSONObject.put("bank_card_product_name", this.mCardProductName);
      localJSONObject.put("bank_card_user_terms", this.mUserTerms);
      localJSONObject.put("bank_card_art", this.mCardArt);
      localJSONObject.put("bank_card_vc_status", this.mVCStatus);
      localJSONObject.put("bank_card_issuer_channel", this.mIssuerChannel);
      localJSONObject.put("bank_card_pan_last_digits", this.mPanLastDigits);
      localJSONObject.put("bank_name", this.mBankName);
      localJSONObject.put("bank_logo_url", this.mBankLogoUrl);
      localJSONObject.put("bank_logo_with_name_url", this.mBankLogoWithNameUrl);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        LogUtils.e("serialize bankcard info to json object failed!", localJSONException);
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mCardName);
    paramParcel.writeString(this.mCardNo);
    paramParcel.writeString(this.mAid);
    paramParcel.writeString(this.mBankCardPan);
    paramParcel.writeInt(this.mBankCardType);
    paramParcel.writeString(this.mVCReferenceId);
    paramParcel.writeString(this.mVCardNumber);
    paramParcel.writeString(this.mCardProductTypeId);
    paramParcel.writeString(this.mIssuerId);
    paramParcel.writeString(this.mCardProductName);
    paramParcel.writeString(this.mUserTerms);
    paramParcel.writeString(this.mCardArt);
    paramParcel.writeInt(this.mVCStatus);
    paramParcel.writeString(this.mPhoneLastDigits);
    paramParcel.writeInt(this.mIssuerChannel);
    paramParcel.writeString(this.mPanLastDigits);
    paramParcel.writeString(this.mBankName);
    paramParcel.writeString(this.mBankLogoUrl);
    paramParcel.writeString(this.mBankLogoWithNameUrl);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.BankCardInfo
 * JD-Core Version:    0.6.0
 */