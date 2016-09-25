package com.miui.tsmclient.util;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Formatter
{
  protected char SEPARATOR = ' ';
  protected EditText mEditText;

  public void bindFormattingTextWatcher(EditText paramEditText)
  {
    FormattingTextWatcher localFormattingTextWatcher = new FormattingTextWatcher();
    this.mEditText = paramEditText;
    this.mEditText.addTextChangedListener(localFormattingTextWatcher);
  }

  public String clean(String paramString)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    clean(localSpannableStringBuilder);
    return localSpannableStringBuilder.toString();
  }

  public void clean(Editable paramEditable)
  {
    int i = 0;
    while (i < paramEditable.length())
    {
      if (!isValidCharacter(paramEditable.charAt(i)))
      {
        paramEditable.delete(i, i + 1);
        continue;
      }
      i++;
    }
  }

  public String cover(String paramString)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    cover(localSpannableStringBuilder);
    return localSpannableStringBuilder.toString();
  }

  public void cover(Editable paramEditable)
  {
    format(paramEditable);
    int i = 0;
    for (int j = -1 + paramEditable.length(); j >= 0; j--)
    {
      if (isSeparator(paramEditable.charAt(j)))
        continue;
      i++;
      if (i <= 4)
        continue;
      paramEditable.replace(j, j + 1, "*");
    }
  }

  public String format(String paramString)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    format(localSpannableStringBuilder);
    return localSpannableStringBuilder.toString();
  }

  public abstract void format(Editable paramEditable);

  public char getSeparator()
  {
    return this.SEPARATOR;
  }

  public boolean isSeparator(char paramChar)
  {
    if (this.SEPARATOR == paramChar);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isValidCharacter(char paramChar)
  {
    return Character.isDigit(paramChar);
  }

  class FormattingTextWatcher
    implements TextWatcher
  {
    private int mDeletedIndex;
    private boolean mDeletedIsSeparator;
    private boolean mStopFormatting = false;

    FormattingTextWatcher()
    {
    }

    private boolean hasSeparator(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      int i = paramInt1;
      if (i < paramInt1 + paramInt2)
      {
        char c = paramCharSequence.charAt(i);
        if (!Formatter.this.isSeparator(c));
      }
      for (int j = 1; ; j = 0)
      {
        return j;
        i++;
        break;
      }
    }

    public void afterTextChanged(Editable paramEditable)
    {
      if (this.mDeletedIsSeparator)
      {
        this.mDeletedIsSeparator = false;
        Formatter.this.mEditText.getEditableText().insert(this.mDeletedIndex, Character.toString(Formatter.this.getSeparator()));
        Formatter.this.mEditText.setSelection(this.mDeletedIndex);
      }
      while (true)
      {
        return;
        if (!this.mStopFormatting)
        {
          this.mStopFormatting = true;
          Formatter.this.format(paramEditable);
          this.mStopFormatting = false;
          continue;
        }
      }
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.mStopFormatting);
      while (true)
      {
        return;
        if ((paramInt2 > 0) && (hasSeparator(paramCharSequence, paramInt1, paramInt2)))
        {
          this.mDeletedIsSeparator = true;
          this.mDeletedIndex = paramInt1;
          continue;
        }
      }
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.Formatter
 * JD-Core Version:    0.6.0
 */