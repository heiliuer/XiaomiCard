package com.miui.tsmclient.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class NetworkUtil
{
  public static final String URL_XIAOMI_HOST = "http://cdn.fds.api.xiaomi.com/mipay.nextpay/app/";

  public static String getLocalIpAddress()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration2;
        while (!localEnumeration2.hasMoreElements())
        {
          if (!localEnumeration1.hasMoreElements())
            break;
          localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
      }
      while ((localInetAddress.isLoopbackAddress()) || (localInetAddress.isLinkLocalAddress()));
      String str2 = localInetAddress.getHostAddress().toString();
      str1 = str2;
      return str1;
    }
    catch (SocketException localSocketException)
    {
      while (true)
      {
        LogUtils.e("WifiPreference IpAddress", localSocketException);
        String str1 = null;
      }
    }
  }

  public static int getNetworkType(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null);
    for (int i = localNetworkInfo.getType(); ; i = -1)
      return i;
  }

  public static boolean isConnected(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isMobileConnected(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isNetworkMetered(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.isActiveNetworkMetered()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isWifiConnected(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    int i;
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      i = 1;
      if (Build.VERSION.SDK_INT >= 16)
        break label65;
      if ((i == 0) || (localNetworkInfo.getType() != 1))
        break label59;
    }
    label59: for (int j = 1; ; j = 0)
    {
      return j;
      i = 0;
      break;
    }
    label65: if ((i != 0) && (!localConnectivityManager.isActiveNetworkMetered()));
    for (j = 1; ; j = 0)
      break;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.NetworkUtil
 * JD-Core Version:    0.6.0
 */