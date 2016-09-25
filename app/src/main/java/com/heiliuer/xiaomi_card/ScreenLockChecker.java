package com.heiliuer.xiaomi_card;

import android.app.KeyguardManager;
import android.content.Context;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class ScreenLockChecker {

    private Context context;
    private KeyguardManager km;

    public ScreenLockChecker(Context context) {
        this.context = context;
    }

    public boolean isScreenLocked() {
        if (km == null) {
            km = (KeyguardManager) this.context.getSystemService(Context.KEYGUARD_SERVICE);
        }
        return km.inKeyguardRestrictedInputMode();
    }
}
