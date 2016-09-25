package com.miui.tsmclient.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import miui.widget.DatePicker;
import miui.widget.DatePicker.OnDateChangedListener;

public class ValidDatePickerDialog extends AlertDialog
{
  private static final String MONTH = "miui:month";
  private static final char[] VALIDATE_DATE_FORMAT_ORDER;
  private static final String YEAR = "miui:year";
  private final OnDateSetListener mCallBack;
  private final DatePicker mDatePicker;
  private DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener()
  {
    public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      ValidDatePickerDialog.this.updateTitle(paramInt1, paramInt2, paramInt3);
    }
  };

  static
  {
    char[] arrayOfChar = new char[2];
    arrayOfChar[0] = 77;
    arrayOfChar[1] = 121;
    VALIDATE_DATE_FORMAT_ORDER = arrayOfChar;
  }

  public ValidDatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext, paramInt1);
    this.mCallBack = paramOnDateSetListener;
    setButton(-1, getContext().getText(17039370), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        ValidDatePickerDialog.this.tryNotifyDateSet();
      }
    });
    setIcon(0);
    this.mDatePicker = new DatePicker(paramContext);
    this.mDatePicker.showDayPicker(false);
    this.mDatePicker.setDateFormatOrder(VALIDATE_DATE_FORMAT_ORDER);
    this.mDatePicker.init(paramInt2, paramInt3, paramInt4, this.mOnDateChangedListener);
    setView(this.mDatePicker);
    updateTitle(paramInt2, paramInt3, paramInt4);
  }

  public ValidDatePickerDialog(Context paramContext, OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramContext, 0, paramOnDateSetListener, paramInt1, paramInt2, paramInt3);
  }

  private void tryNotifyDateSet()
  {
    if (this.mCallBack != null)
    {
      this.mDatePicker.clearFocus();
      this.mCallBack.onDateSet(this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth());
    }
  }

  private void updateTitle(int paramInt1, int paramInt2, int paramInt3)
  {
    setTitle(getContext().getResources().getString(2131296486));
  }

  public DatePicker getDatePicker()
  {
    return this.mDatePicker;
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("miui:year");
    int j = paramBundle.getInt("miui:month");
    this.mDatePicker.init(i, j, 1, this.mOnDateChangedListener);
  }

  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    localBundle.putInt("miui:year", this.mDatePicker.getYear());
    localBundle.putInt("miui:month", this.mDatePicker.getMonth());
    return localBundle;
  }

  public void updateDate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mDatePicker.updateDate(paramInt1, paramInt2, paramInt3);
  }

  public static abstract interface OnDateSetListener
  {
    public abstract void onDateSet(int paramInt1, int paramInt2, int paramInt3);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.ValidDatePickerDialog
 * JD-Core Version:    0.6.0
 */