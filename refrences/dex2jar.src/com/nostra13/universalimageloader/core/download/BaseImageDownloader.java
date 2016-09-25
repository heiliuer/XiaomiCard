package com.nostra13.universalimageloader.core.download;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseImageDownloader
  implements ImageDownloader
{
  protected static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
  protected static final int BUFFER_SIZE = 32768;
  protected static final String CONTENT_CONTACTS_URI_PREFIX = "content://com.android.contacts/";
  public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 5000;
  public static final int DEFAULT_HTTP_READ_TIMEOUT = 20000;
  private static final String ERROR_UNSUPPORTED_SCHEME = "UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))";
  protected static final int MAX_REDIRECT_COUNT = 5;
  protected final int connectTimeout;
  protected final Context context;
  protected final int readTimeout;

  public BaseImageDownloader(Context paramContext)
  {
    this(paramContext, 5000, 20000);
  }

  public BaseImageDownloader(Context paramContext, int paramInt1, int paramInt2)
  {
    this.context = paramContext.getApplicationContext();
    this.connectTimeout = paramInt1;
    this.readTimeout = paramInt2;
  }

  @TargetApi(8)
  private InputStream getVideoThumbnailStream(String paramString)
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    if (Build.VERSION.SDK_INT >= 8)
    {
      Bitmap localBitmap = ThumbnailUtils.createVideoThumbnail(paramString, 2);
      if (localBitmap != null)
      {
        localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap.compress(Bitmap.CompressFormat.PNG, 0, localByteArrayOutputStream);
      }
    }
    for (ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()); ; localByteArrayInputStream = null)
      return localByteArrayInputStream;
  }

  private boolean isVideoContentUri(Uri paramUri)
  {
    String str = this.context.getContentResolver().getType(paramUri);
    if ((str != null) && (str.startsWith("video/")));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isVideoFileUri(String paramString)
  {
    String str1 = MimeTypeMap.getFileExtensionFromUrl(paramString);
    String str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str1);
    if ((str2 != null) && (str2.startsWith("video/")));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected HttpURLConnection createConnection(String paramString, Object paramObject)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(Uri.encode(paramString, "@#&=*+-_.,:!?()/~'%")).openConnection();
    localHttpURLConnection.setConnectTimeout(this.connectTimeout);
    localHttpURLConnection.setReadTimeout(this.readTimeout);
    return localHttpURLConnection;
  }

  @TargetApi(14)
  protected InputStream getContactPhotoStream(Uri paramUri)
  {
    ContentResolver localContentResolver = this.context.getContentResolver();
    if (Build.VERSION.SDK_INT >= 14);
    for (InputStream localInputStream = ContactsContract.Contacts.openContactPhotoInputStream(localContentResolver, paramUri, true); ; localInputStream = ContactsContract.Contacts.openContactPhotoInputStream(localContentResolver, paramUri))
      return localInputStream;
  }

  public InputStream getStream(String paramString, Object paramObject)
    throws IOException
  {
    InputStream localInputStream;
    switch (1.$SwitchMap$com$nostra13$universalimageloader$core$download$ImageDownloader$Scheme[ImageDownloader.Scheme.ofUri(paramString).ordinal()])
    {
    default:
      localInputStream = getStreamFromOtherSource(paramString, paramObject);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return localInputStream;
      localInputStream = getStreamFromNetwork(paramString, paramObject);
      continue;
      localInputStream = getStreamFromFile(paramString, paramObject);
      continue;
      localInputStream = getStreamFromContent(paramString, paramObject);
      continue;
      localInputStream = getStreamFromAssets(paramString, paramObject);
      continue;
      localInputStream = getStreamFromDrawable(paramString, paramObject);
    }
  }

  protected InputStream getStreamFromAssets(String paramString, Object paramObject)
    throws IOException
  {
    String str = ImageDownloader.Scheme.ASSETS.crop(paramString);
    return this.context.getAssets().open(str);
  }

  protected InputStream getStreamFromContent(String paramString, Object paramObject)
    throws FileNotFoundException
  {
    ContentResolver localContentResolver = this.context.getContentResolver();
    Uri localUri = Uri.parse(paramString);
    Object localObject;
    if (isVideoContentUri(localUri))
    {
      Bitmap localBitmap = MediaStore.Video.Thumbnails.getThumbnail(localContentResolver, Long.valueOf(localUri.getLastPathSegment()).longValue(), 1, null);
      if (localBitmap == null)
        break label105;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap.compress(Bitmap.CompressFormat.PNG, 0, localByteArrayOutputStream);
      localObject = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
    }
    while (true)
    {
      return localObject;
      if (paramString.startsWith("content://com.android.contacts/"))
      {
        localObject = getContactPhotoStream(localUri);
        continue;
      }
      label105: localObject = localContentResolver.openInputStream(localUri);
    }
  }

  protected InputStream getStreamFromDrawable(String paramString, Object paramObject)
  {
    int i = Integer.parseInt(ImageDownloader.Scheme.DRAWABLE.crop(paramString));
    return this.context.getResources().openRawResource(i);
  }

  protected InputStream getStreamFromFile(String paramString, Object paramObject)
    throws IOException
  {
    String str = ImageDownloader.Scheme.FILE.crop(paramString);
    if (isVideoFileUri(paramString));
    for (Object localObject = getVideoThumbnailStream(str); ; localObject = new ContentLengthInputStream(new BufferedInputStream(new FileInputStream(str), 32768), (int)new File(str).length()))
      return localObject;
  }

  protected InputStream getStreamFromNetwork(String paramString, Object paramObject)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = createConnection(paramString, paramObject);
    for (int i = 0; (localHttpURLConnection.getResponseCode() / 100 == 3) && (i < 5); i++)
      localHttpURLConnection = createConnection(localHttpURLConnection.getHeaderField("Location"), paramObject);
    InputStream localInputStream;
    try
    {
      localInputStream = localHttpURLConnection.getInputStream();
      if (!shouldBeProcessed(localHttpURLConnection))
      {
        IoUtils.closeSilently(localInputStream);
        throw new IOException("Image request failed with response code " + localHttpURLConnection.getResponseCode());
      }
    }
    catch (IOException localIOException)
    {
      IoUtils.readAndCloseStream(localHttpURLConnection.getErrorStream());
      throw localIOException;
    }
    return new ContentLengthInputStream(new BufferedInputStream(localInputStream, 32768), localHttpURLConnection.getContentLength());
  }

  protected InputStream getStreamFromOtherSource(String paramString, Object paramObject)
    throws IOException
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", arrayOfObject));
  }

  protected boolean shouldBeProcessed(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if (paramHttpURLConnection.getResponseCode() == 200);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.download.BaseImageDownloader
 * JD-Core Version:    0.6.0
 */