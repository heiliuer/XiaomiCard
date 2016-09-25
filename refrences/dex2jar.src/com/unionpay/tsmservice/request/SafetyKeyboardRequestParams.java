package com.unionpay.tsmservice.request;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.data.NinePatchInfo;
import java.util.ArrayList;

public class SafetyKeyboardRequestParams extends RequestParams
{
  public static final Parcelable.Creator<SafetyKeyboardRequestParams> CREATOR = new Parcelable.Creator()
  {
    public final SafetyKeyboardRequestParams createFromParcel(Parcel paramParcel)
    {
      return new SafetyKeyboardRequestParams(paramParcel);
    }

    public final SafetyKeyboardRequestParams[] newArray(int paramInt)
    {
      return new SafetyKeyboardRequestParams[paramInt];
    }
  };
  private int mConfirmBtnHeight = -1;
  private int mConfirmBtnOutPaddingRight = -1;
  private int mConfirmBtnWidth = -1;
  private Bitmap mDelBgBitmap;
  private int mDelBgColor = -1;
  private Bitmap mDelForeBitmap;
  private Bitmap mDoneBgBitmap;
  private int mDoneBgColor = -1;
  private Bitmap mDoneForeBitmap;
  private int mDoneRight = 0;
  private boolean mEnableLightStatusBar = false;
  private int mEnableOKBtn = 1;
  private int mInnerPaddingBottom = -1;
  private int mInnerPaddingLeft = -1;
  private int mInnerPaddingRight = -1;
  private int mInnerPaddingTop = -1;
  private int mIsAudio = 0;
  private int mIsDefaultPosition = 1;
  private int mIsVibrate = 0;
  private Bitmap mKeyboardBgBitmap;
  private int mKeyboardBgColor = -1;
  private int mKeyboardHeight = -1;
  private int mKeyboardWidth = -1;
  private int mMarginCol = -1;
  private int mMarginRow = -1;
  private NinePatchInfo mNinePatchBackground;
  private NinePatchInfo mNinePatchDelKeyBg;
  private NinePatchInfo mNinePatchDoneKeyBg;
  private NinePatchInfo mNinePatchNumKeyBg;
  private NinePatchInfo mNinePatchTitleBg;
  private Bitmap mNumBgBitmap;
  private int mNumBgColor = -1;
  private ArrayList<Bitmap> mNumForeBitmaps;
  private int mNumSize = -1;
  private int mNumberKeyColor = -16777216;
  private int mOutPaddingBottom = -1;
  private int mOutPaddingLeft = -1;
  private int mOutPaddingRight = -1;
  private int mOutPaddingTop = -1;
  private int mSecureHeight = -1;
  private int mSecureWidth = -1;
  private int mStartX = 0;
  private int mStartY = 0;
  private String mTitle;
  private Bitmap mTitleBgBitmap;
  private int mTitleBgColor = -1;
  private int mTitleColor = -1;
  private int mTitleDrawablePadding = -1;
  private Bitmap mTitleDropBitmap;
  private int mTitleFont;
  private int mTitleHeight = -1;
  private Bitmap mTitleIconBitmap;
  private int mTitleSize = -1;

  public SafetyKeyboardRequestParams()
  {
  }

  public SafetyKeyboardRequestParams(Parcel paramParcel)
  {
    super(paramParcel);
    this.mTitle = paramParcel.readString();
    this.mKeyboardWidth = paramParcel.readInt();
    this.mKeyboardHeight = paramParcel.readInt();
    this.mTitleHeight = paramParcel.readInt();
    this.mMarginRow = paramParcel.readInt();
    this.mMarginCol = paramParcel.readInt();
    this.mOutPaddingLeft = paramParcel.readInt();
    this.mOutPaddingTop = paramParcel.readInt();
    this.mOutPaddingRight = paramParcel.readInt();
    this.mOutPaddingBottom = paramParcel.readInt();
    this.mInnerPaddingLeft = paramParcel.readInt();
    this.mInnerPaddingTop = paramParcel.readInt();
    this.mInnerPaddingRight = paramParcel.readInt();
    this.mInnerPaddingBottom = paramParcel.readInt();
    this.mConfirmBtnOutPaddingRight = paramParcel.readInt();
    this.mConfirmBtnWidth = paramParcel.readInt();
    this.mConfirmBtnHeight = paramParcel.readInt();
    this.mStartX = paramParcel.readInt();
    this.mStartY = paramParcel.readInt();
    this.mIsDefaultPosition = paramParcel.readInt();
    this.mNumSize = paramParcel.readInt();
    this.mKeyboardBgBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mTitleBgBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mTitleIconBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mTitleDropBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mDoneForeBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mDoneBgBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mDelForeBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mDelBgBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mNumBgBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mNumForeBitmaps = paramParcel.readArrayList(ArrayList.class.getClassLoader());
    this.mKeyboardBgColor = paramParcel.readInt();
    this.mTitleBgColor = paramParcel.readInt();
    this.mDoneBgColor = paramParcel.readInt();
    this.mDelBgColor = paramParcel.readInt();
    this.mNumBgColor = paramParcel.readInt();
    this.mIsAudio = paramParcel.readInt();
    this.mEnableOKBtn = paramParcel.readInt();
    this.mDoneRight = paramParcel.readInt();
    this.mIsVibrate = paramParcel.readInt();
    this.mSecureWidth = paramParcel.readInt();
    this.mSecureHeight = paramParcel.readInt();
    this.mTitleDrawablePadding = paramParcel.readInt();
    this.mTitleColor = paramParcel.readInt();
    this.mTitleSize = paramParcel.readInt();
    this.mNumberKeyColor = paramParcel.readInt();
    this.mNinePatchBackground = ((NinePatchInfo)paramParcel.readParcelable(NinePatchInfo.class.getClassLoader()));
    this.mNinePatchDelKeyBg = ((NinePatchInfo)paramParcel.readParcelable(NinePatchInfo.class.getClassLoader()));
    this.mNinePatchDoneKeyBg = ((NinePatchInfo)paramParcel.readParcelable(NinePatchInfo.class.getClassLoader()));
    this.mNinePatchNumKeyBg = ((NinePatchInfo)paramParcel.readParcelable(NinePatchInfo.class.getClassLoader()));
    this.mNinePatchTitleBg = ((NinePatchInfo)paramParcel.readParcelable(NinePatchInfo.class.getClassLoader()));
    if (paramParcel.readInt() == 1);
    for (boolean bool = true; ; bool = false)
    {
      this.mEnableLightStatusBar = bool;
      return;
    }
  }

  public int getConfirmBtnHeight()
  {
    return this.mConfirmBtnHeight;
  }

  public int getConfirmBtnOutPaddingRight()
  {
    return this.mConfirmBtnOutPaddingRight;
  }

  public int getConfirmBtnWidth()
  {
    return this.mConfirmBtnWidth;
  }

  public int getDefaultPosition()
  {
    return this.mIsDefaultPosition;
  }

  public Bitmap getDelBgBitmap()
  {
    return this.mDelBgBitmap;
  }

  public int getDelBgColor()
  {
    return this.mDelBgColor;
  }

  public Bitmap getDelForeBitmap()
  {
    return this.mDelForeBitmap;
  }

  public NinePatchInfo getDelKeyBgNinePatch()
  {
    return this.mNinePatchDelKeyBg;
  }

  public Bitmap getDoneBgBitmap()
  {
    return this.mDoneBgBitmap;
  }

  public int getDoneBgColor()
  {
    return this.mDoneBgColor;
  }

  public Bitmap getDoneForeBitmap()
  {
    return this.mDoneForeBitmap;
  }

  public NinePatchInfo getDoneKeyBgNinePatch()
  {
    return this.mNinePatchDoneKeyBg;
  }

  public int getDoneRight()
  {
    return this.mDoneRight;
  }

  public int getEnableOKBtn()
  {
    return this.mEnableOKBtn;
  }

  public int getInnerPaddingBottom()
  {
    return this.mInnerPaddingBottom;
  }

  public int getInnerPaddingLeft()
  {
    return this.mInnerPaddingLeft;
  }

  public int getInnerPaddingRight()
  {
    return this.mInnerPaddingRight;
  }

  public int getInnerPaddingTop()
  {
    return this.mInnerPaddingTop;
  }

  public int getIsAudio()
  {
    return this.mIsAudio;
  }

  public int getIsVibrate()
  {
    return this.mIsVibrate;
  }

  public Bitmap getKeyboardBgBitmap()
  {
    return this.mKeyboardBgBitmap;
  }

  public int getKeyboardBgColor()
  {
    return this.mKeyboardBgColor;
  }

  public NinePatchInfo getKeyboardBgNinePatch()
  {
    return this.mNinePatchBackground;
  }

  public int getKeyboardHeight()
  {
    return this.mKeyboardHeight;
  }

  public int getKeyboardWidth()
  {
    return this.mKeyboardWidth;
  }

  public int getMarginCol()
  {
    return this.mMarginCol;
  }

  public int getMarginRow()
  {
    return this.mMarginRow;
  }

  public Bitmap getNumBgBitmap()
  {
    return this.mNumBgBitmap;
  }

  public int getNumBgColor()
  {
    return this.mNumBgColor;
  }

  public ArrayList<Bitmap> getNumForeBitmaps()
  {
    return this.mNumForeBitmaps;
  }

  public NinePatchInfo getNumKeyBgNinePatch()
  {
    return this.mNinePatchNumKeyBg;
  }

  public int getNumSize()
  {
    return this.mNumSize;
  }

  public int getNumberKeyColor()
  {
    return this.mNumberKeyColor;
  }

  public int getOutPaddingBottom()
  {
    return this.mOutPaddingBottom;
  }

  public int getOutPaddingLeft()
  {
    return this.mOutPaddingLeft;
  }

  public int getOutPaddingRight()
  {
    return this.mOutPaddingRight;
  }

  public int getOutPaddingTop()
  {
    return this.mOutPaddingTop;
  }

  public int getSecureHeight()
  {
    return this.mSecureHeight;
  }

  public int getSecureWidth()
  {
    return this.mSecureWidth;
  }

  public int getStartX()
  {
    return this.mStartX;
  }

  public int getStartY()
  {
    return this.mStartY;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public Bitmap getTitleBgBitmap()
  {
    return this.mTitleBgBitmap;
  }

  public int getTitleBgColor()
  {
    return this.mTitleBgColor;
  }

  public NinePatchInfo getTitleBgNinePatch()
  {
    return this.mNinePatchTitleBg;
  }

  public int getTitleColor()
  {
    return this.mTitleColor;
  }

  public int getTitleDrawablePadding()
  {
    return this.mTitleDrawablePadding;
  }

  public Bitmap getTitleDropBitmap()
  {
    return this.mTitleDropBitmap;
  }

  public int getTitleFont()
  {
    return this.mTitleFont;
  }

  public int getTitleHeight()
  {
    return this.mTitleHeight;
  }

  public Bitmap getTitleIconBitmap()
  {
    return this.mTitleIconBitmap;
  }

  public int getTitleSize()
  {
    return this.mTitleSize;
  }

  public boolean isEnableLightStatusBar()
  {
    return this.mEnableLightStatusBar;
  }

  public void setConfirmBtnHeight(int paramInt)
  {
    this.mConfirmBtnHeight = paramInt;
  }

  public void setConfirmBtnOutPaddingRight(int paramInt)
  {
    this.mConfirmBtnOutPaddingRight = paramInt;
  }

  public void setConfirmBtnWidth(int paramInt)
  {
    this.mConfirmBtnWidth = paramInt;
  }

  public void setDefaultPosition(int paramInt)
  {
    this.mIsDefaultPosition = paramInt;
  }

  public void setDelBgBitmap(Bitmap paramBitmap)
  {
    this.mDelBgBitmap = paramBitmap;
  }

  public void setDelBgColor(int paramInt)
  {
    this.mDelBgColor = paramInt;
  }

  public void setDelForeBitmap(Bitmap paramBitmap)
  {
    this.mDelForeBitmap = paramBitmap;
  }

  public void setDelKeyBgNinePatch(NinePatchInfo paramNinePatchInfo)
  {
    this.mNinePatchDelKeyBg = paramNinePatchInfo;
  }

  public void setDoneBgBitmap(Bitmap paramBitmap)
  {
    this.mDoneBgBitmap = paramBitmap;
  }

  public void setDoneBgColor(int paramInt)
  {
    this.mDoneBgColor = paramInt;
  }

  public void setDoneForeBitmap(Bitmap paramBitmap)
  {
    this.mDoneForeBitmap = paramBitmap;
  }

  public void setDoneKeyBgNinePatch(NinePatchInfo paramNinePatchInfo)
  {
    this.mNinePatchDoneKeyBg = paramNinePatchInfo;
  }

  public void setDoneRight(int paramInt)
  {
    this.mDoneRight = paramInt;
  }

  public void setEnableLightStatusBar(boolean paramBoolean)
  {
    this.mEnableLightStatusBar = paramBoolean;
  }

  public void setEnableOKBtn(int paramInt)
  {
    this.mEnableOKBtn = paramInt;
  }

  public void setInnerPaddingBottom(int paramInt)
  {
    this.mInnerPaddingBottom = paramInt;
  }

  public void setInnerPaddingLeft(int paramInt)
  {
    this.mInnerPaddingLeft = paramInt;
  }

  public void setInnerPaddingRight(int paramInt)
  {
    this.mInnerPaddingRight = paramInt;
  }

  public void setInnerPaddingTop(int paramInt)
  {
    this.mInnerPaddingTop = paramInt;
  }

  public void setIsAudio(int paramInt)
  {
    this.mIsAudio = paramInt;
  }

  public void setIsVibrate(int paramInt)
  {
    this.mIsVibrate = paramInt;
  }

  public void setKeyboardBgBitmap(Bitmap paramBitmap)
  {
    this.mKeyboardBgBitmap = paramBitmap;
  }

  public void setKeyboardBgColor(int paramInt)
  {
    this.mKeyboardBgColor = paramInt;
  }

  public void setKeyboardBgNinePatch(NinePatchInfo paramNinePatchInfo)
  {
    this.mNinePatchBackground = paramNinePatchInfo;
  }

  public void setKeyboardHeight(int paramInt)
  {
    this.mKeyboardHeight = paramInt;
  }

  public void setKeyboardWidth(int paramInt)
  {
    this.mKeyboardWidth = paramInt;
  }

  public void setMarginCol(int paramInt)
  {
    this.mMarginCol = paramInt;
  }

  public void setMarginRow(int paramInt)
  {
    this.mMarginRow = paramInt;
  }

  public void setNumBgBitmap(Bitmap paramBitmap)
  {
    this.mNumBgBitmap = paramBitmap;
  }

  public void setNumBgColor(int paramInt)
  {
    this.mNumBgColor = paramInt;
  }

  public void setNumForeBitmaps(ArrayList<Bitmap> paramArrayList)
  {
    this.mNumForeBitmaps = paramArrayList;
  }

  public void setNumKeyBgNinePatch(NinePatchInfo paramNinePatchInfo)
  {
    this.mNinePatchNumKeyBg = paramNinePatchInfo;
  }

  public void setNumSize(int paramInt)
  {
    this.mNumSize = paramInt;
  }

  public void setNumberKeyColor(int paramInt)
  {
    this.mNumberKeyColor = paramInt;
  }

  public void setOutPaddingBottom(int paramInt)
  {
    this.mOutPaddingBottom = paramInt;
  }

  public void setOutPaddingLeft(int paramInt)
  {
    this.mOutPaddingLeft = paramInt;
  }

  public void setOutPaddingRight(int paramInt)
  {
    this.mOutPaddingRight = paramInt;
  }

  public void setOutPaddingTop(int paramInt)
  {
    this.mOutPaddingTop = paramInt;
  }

  public void setSecureHeight(int paramInt)
  {
    this.mSecureHeight = paramInt;
  }

  public void setSecureWidth(int paramInt)
  {
    this.mSecureWidth = paramInt;
  }

  public void setStartX(int paramInt)
  {
    this.mStartX = paramInt;
  }

  public void setStartY(int paramInt)
  {
    this.mStartY = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }

  public void setTitleBgBitmap(Bitmap paramBitmap)
  {
    this.mTitleBgBitmap = paramBitmap;
  }

  public void setTitleBgColor(int paramInt)
  {
    this.mTitleBgColor = paramInt;
  }

  public void setTitleBgNinePatch(NinePatchInfo paramNinePatchInfo)
  {
    this.mNinePatchTitleBg = paramNinePatchInfo;
  }

  public void setTitleColor(int paramInt)
  {
    this.mTitleColor = paramInt;
  }

  public void setTitleDrawablePadding(int paramInt)
  {
    this.mTitleDrawablePadding = paramInt;
  }

  public void setTitleDropBitmap(Bitmap paramBitmap)
  {
    this.mTitleDropBitmap = paramBitmap;
  }

  public void setTitleFont(int paramInt)
  {
    this.mTitleFont = paramInt;
  }

  public void setTitleHeight(int paramInt)
  {
    this.mTitleHeight = paramInt;
  }

  public void setTitleIconBitmap(Bitmap paramBitmap)
  {
    this.mTitleIconBitmap = paramBitmap;
  }

  public void setTitleSize(int paramInt)
  {
    this.mTitleSize = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.mTitle);
    paramParcel.writeInt(this.mKeyboardWidth);
    paramParcel.writeInt(this.mKeyboardHeight);
    paramParcel.writeInt(this.mTitleHeight);
    paramParcel.writeInt(this.mMarginRow);
    paramParcel.writeInt(this.mMarginCol);
    paramParcel.writeInt(this.mOutPaddingLeft);
    paramParcel.writeInt(this.mOutPaddingTop);
    paramParcel.writeInt(this.mOutPaddingRight);
    paramParcel.writeInt(this.mOutPaddingBottom);
    paramParcel.writeInt(this.mInnerPaddingLeft);
    paramParcel.writeInt(this.mInnerPaddingTop);
    paramParcel.writeInt(this.mInnerPaddingRight);
    paramParcel.writeInt(this.mInnerPaddingBottom);
    paramParcel.writeInt(this.mConfirmBtnOutPaddingRight);
    paramParcel.writeInt(this.mConfirmBtnWidth);
    paramParcel.writeInt(this.mConfirmBtnHeight);
    paramParcel.writeInt(this.mStartX);
    paramParcel.writeInt(this.mStartY);
    paramParcel.writeInt(this.mIsDefaultPosition);
    paramParcel.writeInt(this.mNumSize);
    paramParcel.writeParcelable(this.mKeyboardBgBitmap, 0);
    paramParcel.writeParcelable(this.mTitleBgBitmap, 0);
    paramParcel.writeParcelable(this.mTitleIconBitmap, 0);
    paramParcel.writeParcelable(this.mTitleDropBitmap, 0);
    paramParcel.writeParcelable(this.mDoneForeBitmap, 0);
    paramParcel.writeParcelable(this.mDoneBgBitmap, 0);
    paramParcel.writeParcelable(this.mDelForeBitmap, 0);
    paramParcel.writeParcelable(this.mDelBgBitmap, 0);
    paramParcel.writeParcelable(this.mNumBgBitmap, 0);
    paramParcel.writeList(this.mNumForeBitmaps);
    paramParcel.writeInt(this.mKeyboardBgColor);
    paramParcel.writeInt(this.mTitleBgColor);
    paramParcel.writeInt(this.mDoneBgColor);
    paramParcel.writeInt(this.mDelBgColor);
    paramParcel.writeInt(this.mNumBgColor);
    paramParcel.writeInt(this.mIsAudio);
    paramParcel.writeInt(this.mEnableOKBtn);
    paramParcel.writeInt(this.mDoneRight);
    paramParcel.writeInt(this.mIsVibrate);
    paramParcel.writeInt(this.mSecureWidth);
    paramParcel.writeInt(this.mSecureHeight);
    paramParcel.writeInt(this.mTitleDrawablePadding);
    paramParcel.writeInt(this.mTitleColor);
    paramParcel.writeInt(this.mTitleSize);
    paramParcel.writeInt(this.mNumberKeyColor);
    paramParcel.writeParcelable(this.mNinePatchBackground, paramInt);
    paramParcel.writeParcelable(this.mNinePatchDelKeyBg, paramInt);
    paramParcel.writeParcelable(this.mNinePatchDoneKeyBg, paramInt);
    paramParcel.writeParcelable(this.mNinePatchNumKeyBg, paramInt);
    paramParcel.writeParcelable(this.mNinePatchTitleBg, paramInt);
    if (this.mEnableLightStatusBar == i);
    while (true)
    {
      paramParcel.writeInt(i);
      return;
      i = 0;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.SafetyKeyboardRequestParams
 * JD-Core Version:    0.6.0
 */