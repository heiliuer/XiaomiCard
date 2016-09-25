package com.heiliuer.xiaomi_card;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class ApplicationMy extends Application implements ThreadExceptionHandler.OnException {

    public static final int TRIGGER_AT_MILLIS = 8 * 1000;//异常重启时间
    public static final String SHARE_NAME_MAIN = "main-config";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editShare;

    @Override
    public void onCreate() {
        super.onCreate();
        ThreadExceptionHandler.handlerThreadException(this);
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARE_NAME_MAIN, MODE_PRIVATE);
        editShare = sharedPreferences.edit();
        lastExceptionTime = sharedPreferences.getLong("lastExceptionTime", 0);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (editShare != null) {
            editShare.commit();
        }
    }

    private long lastExceptionTime;

    public void saveLastExceptionTime(long value) {
        editShare.putLong("lastExceptionTime", value);
        editShare.commit();
    }

    @Override
    public boolean onException(Thread paramThread, Throwable paramThrowable) {
        Log.d("ApplicationMy", "lastExceptionTime：" + lastExceptionTime);
        if (System.currentTimeMillis() - lastExceptionTime >= 45 * 1000) {
            //定时重启
            Context context = this.getApplicationContext();
            PendingIntent myActivity = PendingIntent.getActivity(context,
                    192837, new Intent(context, ActMain.class),
                    PendingIntent.FLAG_ONE_SHOT);

            AlarmManager alarmManager;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    TRIGGER_AT_MILLIS, myActivity);
            saveLastExceptionTime(System.currentTimeMillis());
            //toast 无效
            //new SinglonToast(context).showSinglonToast(R.string.error, false);
            System.exit(2);
        }
        return false;
    }
}
