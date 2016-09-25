package com.miui.tsmclient.ui.quick;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QRFragment extends Fragment
{
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903082, null);
    TextView localTextView = (TextView)localView.findViewById(2131492876);
    localTextView.setTextColor(-1);
    localTextView.setText("Are you ok?");
    return localView;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.quick.QRFragment
 * JD-Core Version:    0.6.0
 */