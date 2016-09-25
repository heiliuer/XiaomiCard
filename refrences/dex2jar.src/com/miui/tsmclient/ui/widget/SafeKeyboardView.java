package com.miui.tsmclient.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.miui.tsmclient.R.styleable;
import com.miui.tsmclient.util.UiUtils;

public class SafeKeyboardView extends LinearLayout
{
  private static final int[] KEYS;
  private View mCurrentEditView;
  private ImageButton mDelKey;
  private Runnable mDeleteAction = new Runnable()
  {
    public void run()
    {
      KeyEvent localKeyEvent1 = new KeyEvent(0, SafeKeyboardView.this.getKeyCode(SafeKeyboardView.this.mDelKey));
      SafeKeyboardView.this.mInputConnection.sendKeyEvent(localKeyEvent1);
      KeyEvent localKeyEvent2 = new KeyEvent(1, SafeKeyboardView.this.getKeyCode(SafeKeyboardView.this.mDelKey));
      SafeKeyboardView.this.mInputConnection.sendKeyEvent(localKeyEvent2);
      SafeKeyboardView.this.mHandler.postDelayed(this, ViewConfiguration.getKeyRepeatDelay());
    }
  };
  private Handler mHandler;
  private boolean mHideEnabled;
  private ImageView mHideIcon;
  private InputConnection mInputConnection;
  private View.OnTouchListener mKeyOnTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      KeyEvent localKeyEvent = null;
      if (paramMotionEvent.getAction() == 0)
        localKeyEvent = new KeyEvent(0, SafeKeyboardView.this.getKeyCode(paramView));
      while (true)
      {
        if ((localKeyEvent != null) && (SafeKeyboardView.this.mInputConnection != null))
          SafeKeyboardView.this.mInputConnection.sendKeyEvent(localKeyEvent);
        return false;
        if (paramMotionEvent.getAction() != 1)
          continue;
        localKeyEvent = new KeyEvent(1, SafeKeyboardView.this.getKeyCode(paramView));
      }
    }
  };
  private Drawable mKeyboardBarBackground;
  private View.OnClickListener mKeyboardBarOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      SafeKeyboardView.this.hide();
    }
  };
  private boolean mShowKeyboardAfterSystemHide;
  private MiuiDigitFontTextView mSpecialKey;
  private KeyboardObserver mSystemKeyboardObserver;
  private boolean mSystemKeyboardShown;
  private KeyboardObserver.KeyboardVisibilityListener mSystemKeyboardVisibilityListener = new KeyboardObserver.KeyboardVisibilityListener()
  {
    public void onKeyboardVisibilityChanged(boolean paramBoolean)
    {
      if (paramBoolean)
        SafeKeyboardView.access$502(SafeKeyboardView.this, true);
      while (true)
      {
        return;
        SafeKeyboardView.access$502(SafeKeyboardView.this, false);
        if (!SafeKeyboardView.this.mShowKeyboardAfterSystemHide)
          continue;
        SafeKeyboardView.this.show();
        SafeKeyboardView.access$602(SafeKeyboardView.this, false);
      }
    }
  };

  static
  {
    int[] arrayOfInt = new int[11];
    arrayOfInt[0] = 2131493043;
    arrayOfInt[1] = 2131493033;
    arrayOfInt[2] = 2131493034;
    arrayOfInt[3] = 2131493035;
    arrayOfInt[4] = 2131493036;
    arrayOfInt[5] = 2131493037;
    arrayOfInt[6] = 2131493038;
    arrayOfInt[7] = 2131493039;
    arrayOfInt[8] = 2131493040;
    arrayOfInt[9] = 2131493041;
    arrayOfInt[10] = 2131493042;
    KEYS = arrayOfInt;
  }

  public SafeKeyboardView(Context paramContext)
  {
    super(paramContext);
  }

  public SafeKeyboardView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SafeKeyboardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SafeKeyboardView);
    this.mKeyboardBarBackground = localTypedArray.getDrawable(0);
    this.mHideEnabled = localTypedArray.getBoolean(1, true);
    localTypedArray.recycle();
    this.mSystemKeyboardObserver = new KeyboardObserver((Activity)getContext());
    this.mSystemKeyboardObserver.setKeyboardListener(this.mSystemKeyboardVisibilityListener);
  }

  private int getKeyCode(View paramView)
  {
    return Integer.parseInt((String)paramView.getTag());
  }

  private void initKeyboardView()
  {
    String[] arrayOfString = getContext().getResources().getStringArray(2131427334);
    for (int i = 0; i < KEYS.length; i++)
    {
      View localView2 = findViewById(KEYS[i]);
      if (i < arrayOfString.length)
        ((MiuiDigitFontTextView)localView2).setText(arrayOfString[i]);
      localView2.setOnTouchListener(this.mKeyOnTouchListener);
    }
    View localView1 = findViewById(2131493031);
    this.mSpecialKey = ((MiuiDigitFontTextView)findViewById(2131493042));
    this.mDelKey = ((ImageButton)findViewById(2131493044));
    this.mHideIcon = ((ImageView)findViewById(2131493032));
    if (this.mKeyboardBarBackground != null)
      localView1.setBackground(this.mKeyboardBarBackground);
    if (isHideEnabled())
    {
      localView1.setOnClickListener(this.mKeyboardBarOnClickListener);
      this.mHideIcon.setVisibility(0);
    }
    while (true)
    {
      this.mDelKey.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramView)
        {
          if (SafeKeyboardView.this.mHandler == null)
          {
            SafeKeyboardView.access$002(SafeKeyboardView.this, new Handler());
            SafeKeyboardView.this.mHandler.post(SafeKeyboardView.this.mDeleteAction);
          }
          return true;
        }
      });
      this.mDelKey.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          switch (paramMotionEvent.getAction())
          {
          default:
          case 0:
          case 1:
          }
          while (true)
          {
            return false;
            KeyEvent localKeyEvent2 = new KeyEvent(0, SafeKeyboardView.this.getKeyCode(paramView));
            SafeKeyboardView.this.mInputConnection.sendKeyEvent(localKeyEvent2);
            continue;
            KeyEvent localKeyEvent1 = new KeyEvent(1, SafeKeyboardView.this.getKeyCode(paramView));
            SafeKeyboardView.this.mInputConnection.sendKeyEvent(localKeyEvent1);
            if (SafeKeyboardView.this.mHandler == null)
              continue;
            SafeKeyboardView.this.mHandler.removeCallbacks(SafeKeyboardView.this.mDeleteAction);
            SafeKeyboardView.access$002(SafeKeyboardView.this, null);
          }
        }
      });
      return;
      this.mHideIcon.setVisibility(4);
    }
  }

  private void show()
  {
    if (this.mCurrentEditView != null)
    {
      setVisibility(0);
      setTranslationY(0.0F);
      setEnabled(true);
      this.mCurrentEditView.requestFocus();
      SafeKeyboard.onKeyboardVisibilityChanged(true);
    }
  }

  private void updateSpecialButton(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 0:
    }
    while (true)
    {
      return;
      this.mSpecialKey.setEnabled(true);
      this.mSpecialKey.setText(getContext().getString(2131296509));
      this.mSpecialKey.setTag(String.valueOf(52));
      continue;
      this.mSpecialKey.setEnabled(true);
      this.mSpecialKey.setText(getContext().getString(2131296511));
      this.mSpecialKey.setTag(String.valueOf(158));
      continue;
      this.mSpecialKey.setEnabled(false);
      this.mSpecialKey.setText("");
    }
  }

  void attachEditView(View paramView)
  {
    this.mCurrentEditView = paramView;
    EditorInfo localEditorInfo = new EditorInfo();
    this.mInputConnection = paramView.onCreateInputConnection(localEditorInfo);
    int i = 0;
    if (localEditorInfo.extras != null)
      i = localEditorInfo.extras.getInt("com.mipay.input_extra.extended_input_type");
    updateSpecialButton(i);
  }

  void detachEditView()
  {
    this.mCurrentEditView = null;
  }

  void hide()
  {
    setVisibility(8);
    setEnabled(false);
    SafeKeyboard.onKeyboardVisibilityChanged(false);
  }

  boolean isEditing(View paramView)
  {
    if ((this.mCurrentEditView != null) && (this.mCurrentEditView == paramView));
    for (int i = 1; ; i = 0)
      return i;
  }

  boolean isHideEnabled()
  {
    return this.mHideEnabled;
  }

  protected void onDetachedFromWindow()
  {
    this.mSystemKeyboardObserver.removeKeyboardListener();
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    initKeyboardView();
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    if ((paramInt == 0) && (this.mCurrentEditView != null))
    {
      SafeKeyboard.bindLayout(this.mCurrentEditView);
      if (isShown())
        SafeKeyboard.onKeyboardVisibilityChanged(true);
    }
    super.onWindowVisibilityChanged(paramInt);
  }

  void requestShow(View paramView)
  {
    attachEditView(paramView);
    if (this.mSystemKeyboardShown)
    {
      this.mShowKeyboardAfterSystemHide = true;
      UiUtils.showSoftInputMethod(paramView.getContext(), paramView, false);
    }
    while (true)
    {
      return;
      show();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.SafeKeyboardView
 * JD-Core Version:    0.6.0
 */