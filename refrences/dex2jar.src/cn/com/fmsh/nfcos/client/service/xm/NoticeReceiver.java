package cn.com.fmsh.nfcos.client.service.xm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.io.PrintStream;

public class NoticeReceiver extends BroadcastReceiver
{
  private final String messageKey = "cn.com.fmsh.tsm.sptc.broadcast.message.key";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    System.out.println("=======================receive brodcast=================");
    switch (((BroadCastParameter)paramIntent.getExtras().get("cn.com.fmsh.tsm.sptc.broadcast.message.key")).broadcastType)
    {
    case 1:
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NoticeReceiver
 * JD-Core Version:    0.6.0
 */