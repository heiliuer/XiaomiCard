package com.heiliuer.xiaomi_card;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Heiliuer on 2016/9/24 0024.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isAutoClickInLocked = ApplicationMy.sharedPreferences.getBoolean(Constants.SHARE_KEY_IS_AUTO_CLICK_IN_LOCKED, true);
        if (isAutoClickInLocked && ServiceMain.SERVICE_MAIN_RUNNING == null) {
            Intent myIntent = new Intent(context, ServiceMain.class);
            context.startService(myIntent);
        }
    }
}
