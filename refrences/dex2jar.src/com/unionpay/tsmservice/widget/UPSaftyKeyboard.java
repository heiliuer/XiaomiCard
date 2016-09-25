package com.unionpay.tsmservice.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.unionpay.tsmservice.ITsmCallback.Stub;
import com.unionpay.tsmservice.OnSafetyKeyboardCallback.Stub;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener;
import com.unionpay.tsmservice.data.NinePatchInfo;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.result.GetEncryptDataResult;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UPSaftyKeyboard
{
  private Context a = null;
  private SafetyKeyboardRequestParams b = null;
  private UPTsmAddon c;
  private int d;
  private int e;
  private OnShowListener f;
  private OnHideListener g;
  private OnEditorListener h;
  private OnSafetyKeyboardCallback.Stub i;
  private UPTsmAddon.UPTsmConnectionListener j;
  private Handler.Callback k = new Handler.Callback()
  {
    public final boolean handleMessage(Message paramMessage)
    {
      int i = 1;
      switch (paramMessage.what)
      {
      default:
        i = 0;
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        return i;
        if (UPSaftyKeyboard.a(UPSaftyKeyboard.this) == null)
          continue;
        UPSaftyKeyboard.a(UPSaftyKeyboard.this).onShow();
        continue;
        if (UPSaftyKeyboard.b(UPSaftyKeyboard.this) != null)
          UPSaftyKeyboard.b(UPSaftyKeyboard.this).onHide();
        UPSaftyKeyboard.c(UPSaftyKeyboard.this);
        continue;
        if (UPSaftyKeyboard.d(UPSaftyKeyboard.this) == null)
          continue;
        UPSaftyKeyboard.a(UPSaftyKeyboard.this, paramMessage.arg1);
        UPSaftyKeyboard.d(UPSaftyKeyboard.this).onEditorChanged(UPSaftyKeyboard.e(UPSaftyKeyboard.this));
      }
    }
  };
  private final Handler l = new Handler(Looper.getMainLooper(), this.k);

  public UPSaftyKeyboard(Context paramContext, int paramInt)
    throws RemoteException
  {
    this(paramContext, paramInt, null);
  }

  public UPSaftyKeyboard(Context paramContext, int paramInt, Drawable paramDrawable)
    throws RemoteException
  {
    this.a = paramContext;
    this.d = paramInt;
    if ((paramInt < 2000) || (paramInt > 2001))
      throw new IllegalArgumentException("Type is error");
    this.b = new SafetyKeyboardRequestParams();
    this.c = UPTsmAddon.getInstance(this.a);
    if (!this.c.isConnected())
    {
      this.j = new UPTsmAddon.UPTsmConnectionListener()
      {
        public final void onTsmConnected()
        {
          UPSaftyKeyboard localUPSaftyKeyboard = UPSaftyKeyboard.this;
          UPSaftyKeyboard.g(UPSaftyKeyboard.this);
          UPSaftyKeyboard.h(localUPSaftyKeyboard);
        }

        public final void onTsmDisconnected()
        {
        }
      };
      this.c.addConnectionListener(this.j);
      this.c.bind();
    }
    while (true)
    {
      if (paramDrawable != null);
      try
      {
        setKeyboardBackground(paramDrawable);
        return;
        a();
      }
      catch (KeyboardDrawableErrorException localKeyboardDrawableErrorException)
      {
        while (true)
          localKeyboardDrawableErrorException.printStackTrace();
      }
    }
  }

  private String a(String paramString)
  {
    b localb = new b();
    b.a(localb, paramString);
    return b.a(localb, TimeUnit.MILLISECONDS);
  }

  private void a()
  {
    if (this.c != null);
    try
    {
      this.c.clearEncryptData(this.d);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  private void a(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
        localSafetyKeyboardRequestParams.setDoneForeBitmap(((BitmapDrawable)paramDrawable).getBitmap());
      do
      {
        a(localSafetyKeyboardRequestParams);
        return;
      }
      while (m != 1);
      throw new KeyboardDrawableErrorException();
    }
    throw new KeyboardDrawableErrorException();
  }

  private void a(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams)
    throws RemoteException
  {
    this.c.setSafetyKeyboardBitmap(paramSafetyKeyboardRequestParams);
  }

  private void b(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
        localSafetyKeyboardRequestParams.setDelForeBitmap(((BitmapDrawable)paramDrawable).getBitmap());
      do
      {
        a(localSafetyKeyboardRequestParams);
        return;
      }
      while (m != 1);
      throw new KeyboardDrawableErrorException();
    }
    throw new KeyboardDrawableErrorException();
  }

  private static int c(Drawable paramDrawable)
  {
    int m = -1;
    if (paramDrawable == null);
    while (true)
    {
      return m;
      if ((paramDrawable instanceof BitmapDrawable))
      {
        m = 0;
        continue;
      }
      if ((paramDrawable instanceof ColorDrawable))
      {
        m = 1;
        continue;
      }
      if (!(paramDrawable instanceof NinePatchDrawable))
        continue;
      m = 2;
    }
  }

  private static NinePatchInfo d(Drawable paramDrawable)
  {
    NinePatchDrawable localNinePatchDrawable = (NinePatchDrawable)paramDrawable;
    NinePatchInfo localNinePatchInfo = new NinePatchInfo();
    Rect localRect = new Rect();
    localNinePatchDrawable.getPadding(localRect);
    localNinePatchInfo.setPadding(localRect);
    Drawable.ConstantState localConstantState = localNinePatchDrawable.getConstantState();
    try
    {
      Field localField = Class.forName("android.graphics.drawable.NinePatchDrawable$NinePatchState").getDeclaredField("mNinePatch");
      localField.setAccessible(true);
      Object localObject = localField.get(localConstantState);
      Bitmap localBitmap = (Bitmap)Class.forName("android.graphics.NinePatch").getDeclaredMethod("getBitmap", new Class[0]).invoke(localObject, new Object[0]);
      localNinePatchInfo.setBitmap(localBitmap);
      localNinePatchInfo.setChunk(localBitmap.getNinePatchChunk());
      return localNinePatchInfo;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  /** @deprecated */
  public boolean clearPwd()
  {
    int m = 0;
    monitorenter;
    try
    {
      this.e = 0;
      try
      {
        int i1 = this.c.clearEncryptData(this.d);
        n = i1;
        if (n == 0)
          m = 1;
        monitorexit;
        return m;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
        {
          localRemoteException.printStackTrace();
          int n = -5;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void enableLightStatusBar(boolean paramBoolean)
  {
    this.b.setEnableLightStatusBar(paramBoolean);
  }

  public int getCurrentPinLength()
  {
    return this.e;
  }

  public String getInput()
  {
    return a("");
  }

  public String getInput(String paramString)
  {
    if (this.d != 2000);
    for (String str = ""; ; str = a(paramString))
      return str;
  }

  public boolean hide()
  {
    try
    {
      int i1 = this.c.hideKeyboard();
      m = i1;
      if (m == 0)
      {
        n = 1;
        return n;
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        int m = -5;
        continue;
        int n = 0;
      }
    }
  }

  public void setConfirmBtnOutPaddingRight(int paramInt)
  {
    this.b.setConfirmBtnOutPaddingRight(paramInt);
  }

  public void setConfirmBtnSize(int paramInt1, int paramInt2)
  {
    this.b.setConfirmBtnWidth(paramInt1);
    this.b.setConfirmBtnHeight(paramInt2);
  }

  public void setDelKeyDrawable(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    if (paramDrawable != null)
      b(paramDrawable);
  }

  public void setDelKeyDrawable(Drawable paramDrawable1, Drawable paramDrawable2)
    throws KeyboardDrawableErrorException, RemoteException
  {
    if (paramDrawable1 != null)
      b(paramDrawable1);
    int m;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams;
    if (paramDrawable2 != null)
    {
      m = c(paramDrawable2);
      if (m == -1)
        break label100;
      localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m != 0)
        break label63;
      localSafetyKeyboardRequestParams.setDelBgBitmap(((BitmapDrawable)paramDrawable2).getBitmap());
      localSafetyKeyboardRequestParams.setDelBgColor(-1);
    }
    while (true)
    {
      a(localSafetyKeyboardRequestParams);
      return;
      label63: if (m == 1)
      {
        localSafetyKeyboardRequestParams.setDelBgColor(((ColorDrawable)paramDrawable2).getColor());
        continue;
      }
      if (m != 2)
        continue;
      localSafetyKeyboardRequestParams.setDelKeyBgNinePatch(d(paramDrawable2));
    }
    label100: throw new KeyboardDrawableErrorException();
  }

  public void setDoneKeyDrawable(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    if (paramDrawable != null)
      a(paramDrawable);
  }

  public void setDoneKeyDrawable(Drawable paramDrawable1, Drawable paramDrawable2)
    throws KeyboardDrawableErrorException, RemoteException
  {
    if (paramDrawable1 != null)
      a(paramDrawable1);
    int m;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams;
    if (paramDrawable2 != null)
    {
      m = c(paramDrawable2);
      if (m == -1)
        break label100;
      localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m != 0)
        break label63;
      localSafetyKeyboardRequestParams.setDoneBgBitmap(((BitmapDrawable)paramDrawable2).getBitmap());
      localSafetyKeyboardRequestParams.setDoneBgColor(-1);
    }
    while (true)
    {
      a(localSafetyKeyboardRequestParams);
      return;
      label63: if (m == 1)
      {
        localSafetyKeyboardRequestParams.setDoneBgColor(((ColorDrawable)paramDrawable2).getColor());
        continue;
      }
      if (m != 2)
        continue;
      localSafetyKeyboardRequestParams.setDoneKeyBgNinePatch(d(paramDrawable2));
    }
    label100: throw new KeyboardDrawableErrorException();
  }

  public void setDoneKeyEnable(boolean paramBoolean)
  {
    boolean bool = true;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = this.b;
    if (paramBoolean == bool);
    while (true)
    {
      localSafetyKeyboardRequestParams.setEnableOKBtn(bool);
      return;
      bool = false;
    }
  }

  public void setDoneKeyRightMode(boolean paramBoolean)
  {
    boolean bool = true;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = this.b;
    if (paramBoolean == bool);
    while (true)
    {
      localSafetyKeyboardRequestParams.setDoneRight(bool);
      return;
      bool = false;
    }
  }

  public void setKeyAreaPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.b.setInnerPaddingLeft(paramInt1);
    this.b.setInnerPaddingRight(paramInt3);
    this.b.setInnerPaddingTop(paramInt2);
    this.b.setInnerPaddingBottom(paramInt4);
  }

  public void setKeyBoardSize(int paramInt1, int paramInt2)
  {
    this.b.setKeyboardWidth(paramInt1);
    this.b.setKeyboardHeight(paramInt2);
  }

  public void setKeyboardAudio(boolean paramBoolean)
  {
    boolean bool = true;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = this.b;
    if (paramBoolean == bool);
    while (true)
    {
      localSafetyKeyboardRequestParams.setIsAudio(bool);
      return;
      bool = false;
    }
  }

  public void setKeyboardBackground(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
      {
        localSafetyKeyboardRequestParams.setKeyboardBgBitmap(((BitmapDrawable)paramDrawable).getBitmap());
        localSafetyKeyboardRequestParams.setKeyboardBgColor(-1);
      }
      while (true)
      {
        a(localSafetyKeyboardRequestParams);
        return;
        if (m == 1)
        {
          localSafetyKeyboardRequestParams.setKeyboardBgColor(((ColorDrawable)paramDrawable).getColor());
          continue;
        }
        if (m != 2)
          continue;
        localSafetyKeyboardRequestParams.setKeyboardBgNinePatch(d(paramDrawable));
      }
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setKeyboardPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.b.setOutPaddingLeft(paramInt1);
    this.b.setOutPaddingRight(paramInt3);
    this.b.setOutPaddingTop(paramInt2);
    this.b.setOutPaddingBottom(paramInt4);
  }

  public void setKeyboardStartPosition(int paramInt1, int paramInt2)
  {
    this.b.setStartX(paramInt1);
    this.b.setStartY(paramInt2);
    this.b.setDefaultPosition(0);
  }

  public void setKeyboardVibrate(boolean paramBoolean)
  {
    boolean bool = true;
    SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = this.b;
    if (paramBoolean == bool);
    while (true)
    {
      localSafetyKeyboardRequestParams.setIsVibrate(bool);
      return;
      bool = false;
    }
  }

  public void setNumKeyBackgroud(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
      {
        localSafetyKeyboardRequestParams.setNumBgBitmap(((BitmapDrawable)paramDrawable).getBitmap());
        localSafetyKeyboardRequestParams.setNumBgColor(-1);
      }
      while (true)
      {
        a(localSafetyKeyboardRequestParams);
        return;
        if (m == 1)
        {
          localSafetyKeyboardRequestParams.setNumBgColor(((ColorDrawable)paramDrawable).getColor());
          continue;
        }
        if (m != 2)
          continue;
        localSafetyKeyboardRequestParams.setNumKeyBgNinePatch(d(paramDrawable));
      }
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setNumKeyMargin(int paramInt1, int paramInt2)
  {
    this.b.setMarginRow(paramInt1);
    this.b.setMarginCol(paramInt2);
  }

  public void setNumberKeyColor(int paramInt)
  {
    this.b.setNumberKeyColor(paramInt);
  }

  public void setNumberKeyDrawable(Drawable[] paramArrayOfDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = -1;
    if ((paramArrayOfDrawable == null) || (paramArrayOfDrawable.length <= 0));
    while (m == 0)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      ArrayList localArrayList = new ArrayList();
      int n = paramArrayOfDrawable.length;
      int i1 = 0;
      while (true)
        if (i1 < n)
        {
          Drawable localDrawable = paramArrayOfDrawable[i1];
          if (((BitmapDrawable)localDrawable).getBitmap() != null)
            localArrayList.add(((BitmapDrawable)localDrawable).getBitmap());
          i1++;
          continue;
          int i2 = paramArrayOfDrawable.length;
          for (int i3 = 0; ; i3++)
          {
            if (i3 >= i2)
              break label114;
            if (!(paramArrayOfDrawable[i3] instanceof BitmapDrawable))
              break;
          }
          label114: m = 0;
          break;
        }
      localSafetyKeyboardRequestParams.setNumForeBitmaps(localArrayList);
      a(localSafetyKeyboardRequestParams);
      return;
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setNumberKeySize(int paramInt)
  {
    this.b.setNumSize(paramInt);
  }

  public void setOnEditorListener(OnEditorListener paramOnEditorListener)
  {
    this.h = paramOnEditorListener;
  }

  public void setOnHideListener(OnHideListener paramOnHideListener)
  {
    this.g = paramOnHideListener;
  }

  public void setOnShowListener(OnShowListener paramOnShowListener)
  {
    this.f = paramOnShowListener;
  }

  public void setTitleBackground(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
      {
        localSafetyKeyboardRequestParams.setTitleBgBitmap(((BitmapDrawable)paramDrawable).getBitmap());
        localSafetyKeyboardRequestParams.setTitleBgColor(-1);
      }
      while (true)
      {
        a(localSafetyKeyboardRequestParams);
        return;
        if (m == 1)
        {
          localSafetyKeyboardRequestParams.setTitleBgColor(((ColorDrawable)paramDrawable).getColor());
          continue;
        }
        if (m != 2)
          continue;
        localSafetyKeyboardRequestParams.setTitleBgNinePatch(d(paramDrawable));
      }
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setTitleColor(int paramInt)
  {
    this.b.setTitleColor(paramInt);
  }

  public void setTitleConfirmDrawable(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
        localSafetyKeyboardRequestParams.setTitleDropBitmap(((BitmapDrawable)paramDrawable).getBitmap());
      do
      {
        a(localSafetyKeyboardRequestParams);
        return;
      }
      while (m != 1);
      throw new KeyboardDrawableErrorException();
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setTitleDrawable(Drawable paramDrawable)
    throws KeyboardDrawableErrorException, RemoteException
  {
    int m = c(paramDrawable);
    if (m != -1)
    {
      SafetyKeyboardRequestParams localSafetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
      if (m == 0)
        localSafetyKeyboardRequestParams.setTitleIconBitmap(((BitmapDrawable)paramDrawable).getBitmap());
      do
      {
        a(localSafetyKeyboardRequestParams);
        return;
      }
      while (m != 1);
      throw new KeyboardDrawableErrorException();
    }
    throw new KeyboardDrawableErrorException();
  }

  public void setTitleDrawablePadding(int paramInt)
  {
    this.b.setTitleDrawablePadding(paramInt);
  }

  public void setTitleDrawableSize(int paramInt1, int paramInt2)
  {
    this.b.setSecureWidth(paramInt1);
    this.b.setSecureHeight(paramInt2);
  }

  public void setTitleFont(Typeface paramTypeface)
  {
    this.b.setTitleFont(paramTypeface.getStyle());
  }

  public void setTitleHeight(int paramInt)
  {
    this.b.setTitleHeight(paramInt);
  }

  public void setTitleSize(int paramInt)
  {
    this.b.setTitleSize(paramInt);
  }

  public void setTitleText(String paramString)
  {
    this.b.setTitle(paramString);
  }

  /** @deprecated */
  public boolean show()
  {
    int m = 0;
    monitorenter;
    while (true)
    {
      try
      {
        if (this.i != null)
          continue;
        this.i = new a();
        try
        {
          int n = this.c.showSafetyKeyboard(this.b, this.d, this.i, this.a);
          if (n != 0)
          {
            this.i = null;
            monitorexit;
            return m;
          }
        }
        catch (RemoteException localRemoteException)
        {
          localRemoteException.printStackTrace();
          this.i = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      m = 1;
    }
  }

  class b extends FutureTask<String>
  {
    public b()
    {
      super();
    }

    private String a(TimeUnit paramTimeUnit)
    {
      try
      {
        str = (String)get(2000L, paramTimeUnit);
        return str;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
        {
          localInterruptedException.printStackTrace();
          cancel(true);
          String str = "";
        }
      }
      catch (ExecutionException localExecutionException)
      {
        while (true)
        {
          localExecutionException.printStackTrace();
          cancel(true);
        }
      }
      catch (TimeoutException localTimeoutException)
      {
        while (true)
        {
          localTimeoutException.printStackTrace();
          cancel(true);
        }
      }
      finally
      {
        cancel(true);
      }
      throw localObject;
    }

    class a extends ITsmCallback.Stub
    {
      a()
      {
      }

      public final void onError(String paramString1, String paramString2)
        throws RemoteException
      {
        UPSaftyKeyboard.b.b(UPSaftyKeyboard.b.this, "");
      }

      public final void onResult(Bundle paramBundle)
        throws RemoteException
      {
        paramBundle.setClassLoader(GetEncryptDataResult.class.getClassLoader());
        String str = ((GetEncryptDataResult)paramBundle.get("result")).getData();
        UPSaftyKeyboard.b.a(UPSaftyKeyboard.b.this, str);
      }
    }
  }

  public static abstract interface OnEditorListener
  {
    public abstract void onEditorChanged(int paramInt);
  }

  public static abstract interface OnHideListener
  {
    public abstract void onHide();
  }

  public static abstract interface OnShowListener
  {
    public abstract void onShow();
  }

  class a extends OnSafetyKeyboardCallback.Stub
  {
    a()
    {
    }

    public final void onEditorChanged(int paramInt)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      localMessage.what = 2;
      localMessage.arg1 = paramInt;
      UPSaftyKeyboard.f(UPSaftyKeyboard.this).sendMessage(localMessage);
    }

    public final void onHide()
      throws RemoteException
    {
      UPSaftyKeyboard.f(UPSaftyKeyboard.this).sendEmptyMessage(1);
    }

    public final void onShow()
      throws RemoteException
    {
      UPSaftyKeyboard.f(UPSaftyKeyboard.this).sendEmptyMessage(0);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.widget.UPSaftyKeyboard
 * JD-Core Version:    0.6.0
 */