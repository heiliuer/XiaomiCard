package com.miui.tsmclient.util;

import android.text.TextUtils;

public class ImageUtil
{
  private static String NORMAL_HOST = "http://file.market.xiaomi.com/mfc/download/";
  private static String THUMBNAIL_HOST = "http://file.market.xiaomi.com/mfc/thumbnail/";

  public static final String getUrl(String paramString, ThumbnailFormat paramThumbnailFormat)
  {
    String str2;
    if (paramThumbnailFormat == null)
      str2 = joinUrl(NORMAL_HOST, paramString);
    while (true)
    {
      return str2;
      String str1 = paramThumbnailFormat.getThumbnailFormatProperty();
      if (TextUtils.isEmpty(str1))
      {
        str2 = joinUrl(NORMAL_HOST, paramString);
        continue;
      }
      str2 = joinUrl(joinUrl(THUMBNAIL_HOST, str1), paramString);
    }
  }

  private static String joinUrl(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      paramString1 = paramString2;
    while (true)
    {
      return paramString1;
      if (TextUtils.isEmpty(paramString2))
        continue;
      if (paramString1.charAt(-1 + paramString1.length()) == '/')
        paramString1 = paramString1.substring(0, -1 + paramString1.length());
      if (paramString2.charAt(0) == '/')
        paramString2 = paramString2.substring(1);
      paramString1 = paramString1 + "/" + paramString2;
    }
  }

  public static class ThumbnailFormat
  {
    public static final int FORMAT_JPEG = 0;
    public static final int FORMAT_PNG = 1;
    public static final int FORMAT_WEBP = 2;
    public static final int QUALITY_DEFAULT = 80;
    public static final int THUMBNAIL_MAX_HEIGHT = 2;
    public static final int THUMBNAIL_MAX_LENGTH = 0;
    public static final int THUMBNAIL_MAX_WIDTH = 1;
    public static final int THUMBNAIL_MAX_WIDTH_HEIGHT = 3;
    private int mImageFormat;
    private int mImageQuality = 80;
    private int mMaxHeight = 0;
    private int mMaxLength = 0;
    private int mMaxWidth = 0;
    private int mThumbnailType;

    public static ThumbnailFormat getMaxHeightThumnail(int paramInt1, int paramInt2)
    {
      ThumbnailFormat localThumbnailFormat = new ThumbnailFormat();
      localThumbnailFormat.mMaxWidth = paramInt1;
      localThumbnailFormat.mThumbnailType = 2;
      localThumbnailFormat.mImageFormat = paramInt2;
      return localThumbnailFormat;
    }

    public static ThumbnailFormat getMaxLengthThumnail(int paramInt1, int paramInt2)
    {
      ThumbnailFormat localThumbnailFormat = new ThumbnailFormat();
      localThumbnailFormat.mMaxLength = paramInt1;
      localThumbnailFormat.mThumbnailType = 0;
      localThumbnailFormat.mImageFormat = paramInt2;
      return localThumbnailFormat;
    }

    public static ThumbnailFormat getMaxWidthHeightThumnail(int paramInt1, int paramInt2, int paramInt3)
    {
      ThumbnailFormat localThumbnailFormat = new ThumbnailFormat();
      localThumbnailFormat.mMaxWidth = paramInt1;
      localThumbnailFormat.mMaxHeight = paramInt2;
      localThumbnailFormat.mThumbnailType = 3;
      localThumbnailFormat.mImageFormat = paramInt3;
      return localThumbnailFormat;
    }

    public static ThumbnailFormat getMaxWidthThumnail(int paramInt1, int paramInt2)
    {
      ThumbnailFormat localThumbnailFormat = new ThumbnailFormat();
      localThumbnailFormat.mMaxWidth = paramInt1;
      localThumbnailFormat.mThumbnailType = 1;
      localThumbnailFormat.mImageFormat = paramInt2;
      return localThumbnailFormat;
    }

    public boolean checkFormat()
    {
      int i = 0;
      if ((this.mImageFormat != 0) && (this.mImageFormat != 1) && (this.mImageFormat != 2));
      while (true)
      {
        return i;
        if ((this.mThumbnailType == 0) && (this.mMaxLength > 0))
        {
          i = 1;
          continue;
        }
        if ((this.mThumbnailType == 1) && (this.mMaxWidth > 0))
        {
          i = 1;
          continue;
        }
        if ((this.mThumbnailType == 2) && (this.mMaxHeight > 0))
        {
          i = 1;
          continue;
        }
        if ((this.mThumbnailType != 3) || (this.mMaxWidth <= 0) || (this.mMaxHeight <= 0))
          continue;
        i = 1;
      }
    }

    String getThumbnailFormatProperty()
    {
      String str;
      if (!checkFormat())
      {
        str = null;
        return str;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.mImageFormat == 0)
      {
        localStringBuilder.append("jpeg");
        label35: localStringBuilder.append("/");
        if (this.mThumbnailType != 0)
          break label148;
        localStringBuilder.append("l" + this.mMaxLength);
      }
      while (true)
      {
        localStringBuilder.append("q" + this.mImageQuality);
        str = localStringBuilder.toString();
        break;
        if (this.mImageFormat == 1)
        {
          localStringBuilder.append("png");
          break label35;
        }
        if (this.mImageFormat != 2)
          break label35;
        localStringBuilder.append("webp");
        break label35;
        label148: if (this.mThumbnailType == 1)
        {
          localStringBuilder.append("w" + this.mMaxWidth);
          continue;
        }
        if (this.mThumbnailType == 2)
        {
          localStringBuilder.append("h" + this.mMaxHeight);
          continue;
        }
        if (this.mThumbnailType != 3)
          continue;
        localStringBuilder.append("w" + this.mMaxWidth);
        localStringBuilder.append("h" + this.mMaxHeight);
      }
    }

    public void setImageQuality(int paramInt)
    {
      if ((paramInt > 100) || (paramInt < 0))
        this.mImageQuality = 80;
      this.mImageQuality = paramInt;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.ImageUtil
 * JD-Core Version:    0.6.0
 */