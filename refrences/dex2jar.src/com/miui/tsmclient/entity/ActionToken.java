package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONObject;

public class ActionToken
  implements Parcelable, ObjectParser<ActionToken>
{
  public static final Parcelable.Creator<ActionToken> CREATOR = new Parcelable.Creator()
  {
    public ActionToken createFromParcel(Parcel paramParcel)
    {
      return new ActionToken(paramParcel, null);
    }

    public ActionToken[] newArray(int paramInt)
    {
      return new ActionToken[paramInt];
    }
  };
  private static final String KEY_RECHARGEAMOUNT = "rechargeAmount";
  private static final String KEY_TOKEN = "token";
  private static final String KEY_TYPE = "type";
  public int mRechargeAmount;
  public String mToken;
  public TokenType mType;

  public ActionToken()
  {
  }

  private ActionToken(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public ActionToken parse(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.mToken = paramJSONObject.optString("token");
      this.mType = TokenType.newInstance(paramJSONObject.optInt("type"));
      this.mRechargeAmount = paramJSONObject.optInt("rechargeAmount");
    }
    return this;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mToken = paramParcel.readString();
    this.mType = TokenType.valueOf(paramParcel.readString());
    this.mRechargeAmount = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mToken);
    paramParcel.writeString(this.mType.name());
    paramParcel.writeInt(this.mRechargeAmount);
  }

  public static enum TokenType
  {
    private int mId;

    static
    {
      TokenType[] arrayOfTokenType = new TokenType[2];
      arrayOfTokenType[0] = issue;
      arrayOfTokenType[1] = recharge;
      $VALUES = arrayOfTokenType;
    }

    private TokenType(int paramInt)
    {
      this.mId = paramInt;
    }

    public static TokenType newInstance(int paramInt)
    {
      TokenType[] arrayOfTokenType = values();
      int i = arrayOfTokenType.length;
      int j = 0;
      TokenType localTokenType;
      if (j < i)
      {
        localTokenType = arrayOfTokenType[j];
        if (localTokenType.mId != paramInt);
      }
      while (true)
      {
        return localTokenType;
        j++;
        break;
        localTokenType = null;
      }
    }

    public int getId()
    {
      return this.mId;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.ActionToken
 * JD-Core Version:    0.6.0
 */