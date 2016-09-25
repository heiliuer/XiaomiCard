package com.miui.tsmclient.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.miui.tsmclient.entity.CardInfo;
import miui.app.ActionBar;

public class WebViewFragment extends BaseFragment
{
  public static final String KEY_URL = "url";
  private CardInfo mCardInfo;
  private String mTitleText;
  WebChromeClient mWebChromeClient = new WebChromeClient()
  {
    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      super.onReceivedTitle(paramWebView, paramString);
      WebViewFragment.access$002(WebViewFragment.this, paramString);
      WebViewFragment.this.updateTitle();
    }
  };
  private WebView mWebView;
  WebViewClient mWebViewClient = new WebViewClient()
  {
    public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError)
    {
      super.onReceivedError(paramWebView, paramWebResourceRequest, paramWebResourceError);
      WebViewFragment.access$002(WebViewFragment.this, "");
      WebViewFragment.this.updateTitle();
    }
  };

  private void updateTitle()
  {
    ActionBar localActionBar = (ActionBar)getActivity().getActionBar();
    if (localActionBar != null)
    {
      if (TextUtils.isEmpty(this.mTitleText))
        break label34;
      localActionBar.setTitle(this.mTitleText);
    }
    while (true)
    {
      return;
      label34: if (this.mCardInfo != null)
      {
        localActionBar.setTitle(this.mCardInfo.mCardName);
        continue;
      }
      localActionBar.setTitle(2131296263);
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    Bundle localBundle = getArguments();
    if (localBundle != null)
      this.mCardInfo = ((CardInfo)localBundle.getParcelable("card_info"));
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903055, paramViewGroup, false);
    this.mWebView = ((WebView)localView.findViewById(2131492933));
    this.mWebView.getSettings().setCacheMode(1);
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    String str = getArguments().getString("url");
    this.mWebView.setWebViewClient(this.mWebViewClient);
    this.mWebView.setWebChromeClient(this.mWebChromeClient);
    this.mWebView.loadUrl(str);
    return localView;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.WebViewFragment
 * JD-Core Version:    0.6.0
 */