package com.heiliuer.xiaomi_card;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.util.List;

public class ServiceMain extends Service {

    public static ServiceMain SERVICE_MAIN_RUNNING;
    private ScreenLockChecker screenLockChecker;
    private SinglonToast singlonToast;
    private ActivityManager activityManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singlonToast = new SinglonToast(this);
        registerScreenOnReceiver();
        SERVICE_MAIN_RUNNING = this;

        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    private void registerScreenOnReceiver() {
        screenLockChecker = new ScreenLockChecker(this);
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateReceiver, screenStateFilter);
    }


    /**
     * 打开刷卡页面
     */
    private void goToBusCard() {
        Intent intent = new Intent();
//      intent.setComponent(new ComponentName("com.miui.tsmclient", "com.miui.tsmclient.ui.ActMain"));//选择公交卡开通页面
        intent.setComponent(new ComponentName(Constants.DATA.PACKAGE, Constants.DATA.PACKAGE + Constants.DOTTED + Constants.DATA.UI + Constants.DOTTED + Constants.DATA.QUICK + Constants.DOTTED + Constants.DATA.ACTIVITY));
//        intent.putExtra("event_source","key_rf_on");
        intent.putExtra(Constants.INTENT_EXTRA_EVENT_SOURCE, Constants.EVENT_SOURCE_KEY_VOLUME_DOWN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // the activity becomes the new root of an otherwise empty task, and any old activities are finished.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            singlonToast.showSinglonToast(R.string.not_support, false);
        }
    }


    private BroadcastReceiver mScreenStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isAutoClickInLocked = ApplicationMy.sharedPreferences.getBoolean(Constants.SHARE_KEY_IS_AUTO_CLICK_IN_LOCKED, true);
            if (isAutoClickInLocked && Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                startListenCardAttatchPos();
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startListenCardAttatchPos() {

//        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
//        List<ActivityManager.RecentTaskInfo> taskInfos = new ArrayList<>();
//        for (ActivityManager.AppTask task :
//                appTasks) {
//            taskInfos.add(task.getTaskInfo());
//            task.finishAndRemoveTask();
//
//        }
//        String msg = new Gson().toJson(taskInfos);
//        Log.d("taskInfos", msg);


        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();

//        Log.d("runningAppProcesses", new Gson().toJson(runningAppProcesses));

        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {
//                    Log.d("topActivity", "#########::" + gson.toJson(processInfo));
            if (Constants.DATA.PACKAGE.equals(processInfo.processName)) {
                if (screenLockChecker.isScreenLocked()) {

                    Integer processState = (Integer) getFiled(processInfo, "processState");
                    //processState @hide api ActivityManager.PROCESS_STATE_TOP
                    if (processState != null && processState == 2) {
                        //goToBusCard();
                    }
                }
                break;
            }
        }
    }

    private Object getFiled(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScreenStateReceiver);
        SERVICE_MAIN_RUNNING = null;
    }
}
