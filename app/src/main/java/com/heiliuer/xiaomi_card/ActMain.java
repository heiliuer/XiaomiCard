package com.heiliuer.xiaomi_card;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

public class ActMain extends ActBase {
    private ScreenLockChecker screenLockChecker;
    public static final int REQUEST_CODE_ACT_BUSCARD = 22;

    public static final int REQUEST_CODE_CLOSE_NFC = 30;
    private static final int REQUEST_CODE_OPEN_NFC = 40;

    private NfcAdapter mNfcAdapter;
    private SinglonToast singlonToast;

    private SharedPreferences commomShare;
    private SharedPreferences.Editor editCommomShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);


        commomShare = ApplicationMy.sharedPreferences;
        editCommomShare = ApplicationMy.editShare;


        singlonToast = new SinglonToast(this);

        // openSystemSettings();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (isNoNfc()) {
            singlonToast.showSinglonToast(R.string.no_nfc, false);
        }

        screenLockChecker = new ScreenLockChecker(this);

        initEvents();

        if (commomShare.getBoolean(Constants.SHARE_KEY_IS_OPEN_CARD_ON_ENTER, true) || screenLockChecker.isScreenLocked()) {
            startTakeBus();
        }


        if (commomShare.getBoolean(Constants.SHARE_KEY_IS_AUTO_CLICK_IN_LOCKED, true)) {
            startMainService();
        }

    }

    private void startMainService() {
        if (ServiceMain.SERVICE_MAIN_RUNNING == null) {
            startService(new Intent(this, ServiceMain.class));
        }
    }


    private void infoDialog() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle(R.string.info);
        alertbox.setMessage(getString(R.string.msg_info));
        alertbox.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        /*alertbox.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActMain.this.finish();
            }
        });*/
        alertbox.show();
    }

    private void initEvents() {

        findViewById(R.id.imgBtn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActMain.this, ActInfo.class));
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTakeBus();
            }
        });

        initCheckedTextEvent(R.id.check_auto_click_in_locked, Constants.SHARE_KEY_IS_AUTO_CLICK_IN_LOCKED, true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckedTextView) v).isChecked();
                if (checked) {
                    startMainService();
                } else {
                    if (ServiceMain.SERVICE_MAIN_RUNNING != null) {
                        stopService(new Intent(ActMain.this, ServiceMain.class));
                    }
                }

                singlonToast.showSinglonToast(checked ? R.string.opened_service : R.string.closed_service, false);
            }
        });

        initCheckedTextEvent(R.id.check_close_nfc, Constants.SHARE_KEY_IS_CLOSE_NFC_AFTER, false, null);
        initCheckedTextEvent(R.id.check_open_card_on_enter, Constants.SHARE_KEY_IS_OPEN_CARD_ON_ENTER, true, null);
        initCheckedTextEvent(R.id.check_auto_click_card, Constants.SHARE_KEY_IS_AUTO_CLICK_CARD, true, null);
    }


    private void initCheckedTextEvent(int checkResId, final String key, boolean defValue, final View.OnClickListener toggleListener) {
        final CheckedTextView checkedTextView = (CheckedTextView) findViewById(checkResId);
        checkedTextView.setChecked(ApplicationMy.sharedPreferences.getBoolean(key, defValue));
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editCommomShare == null) {
                    ApplicationMy.editShare = commomShare.edit();
                }
                checkedTextView.toggle();
                editCommomShare.putBoolean(key, checkedTextView.isChecked());
                editCommomShare.apply();
                if (toggleListener != null) {
                    toggleListener.onClick(v);
                }
            }
        };
        checkedTextView.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (editCommomShare != null) {
            editCommomShare.commit();
        }
    }

    private void openSystemSettings() {
        //Intent intent = new Intent(Settings.ACTION_SETTINGS);
        //startActivity(intent);
    }

    private void startTakeBus() {
        if (!isNfcEnabled()) {
//            confirmOpenNfcWithDialog();
            confirmOpenNfcWithToast();
        } else {
            goToBusCard();
        }

    }

    private boolean isNfcEnabled() {
        if (isNoNfc()) {
            return false;
        }
        return mNfcAdapter.isEnabled();
    }

    private boolean isNoNfc() {
        return mNfcAdapter == null;
    }


    private void startNfcSetting(int requestCode) {
        if (isNoNfc()) {
            singlonToast.showSinglonToast(R.string.no_nfc, false);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
            startActivityForResult(intent, requestCode);
        } else {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivityForResult(intent, requestCode);
        }
    }


    private void confirmCloseNfcWithDialog() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle(R.string.info);
        alertbox.setMessage(getString(R.string.msg_nfcoff));
        alertbox.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNfcSetting(REQUEST_CODE_CLOSE_NFC);
            }
        });
        alertbox.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActMain.this.finish();
            }
        });
        alertbox.show();
    }

    private void confirmCloseNfcWithToast() {
        singlonToast.showSinglonToast(R.string.close_nfc_please, false);
        startNfcSetting(REQUEST_CODE_CLOSE_NFC);
    }


    private void confirmOpenNfcWithDialog() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle(R.string.info);
        alertbox.setMessage(getString(R.string.msg_nfcon));
        alertbox.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNfcSetting(REQUEST_CODE_OPEN_NFC);
            }
        });
        alertbox.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertbox.show();
    }

    private void confirmOpenNfcWithToast() {
        singlonToast.showSinglonToast(R.string.open_nfc_please, false);
        startNfcSetting(REQUEST_CODE_OPEN_NFC);
    }


    /**
     * 打开刷卡页面
     */
    private void goToBusCard() {
        Intent intent = new Intent();
//      intent.setComponent(new ComponentName("com.miui.tsmclient", "com.miui.tsmclient.ui.ActMain"));//选择公交卡开通页面
        intent.setComponent(new ComponentName(Constants.DATA.PACKAGE, Constants.DATA.PACKAGE + Constants.DOTTED + Constants.DATA.UI + Constants.DOTTED + Constants.DATA.QUICK + Constants.DOTTED + Constants.DATA.ACTIVITY));
//        intent.putExtra("event_source","key_rf_on");

        boolean isAutoClickCard = commomShare.getBoolean(Constants.SHARE_KEY_IS_AUTO_CLICK_CARD, true) || screenLockChecker.isScreenLocked();
        String eventSource = isAutoClickCard ? Constants.EVENT_SOURCE_KEY_VOLUME_DOWN : Constants.EVENT_SOURCE_KEY_RF_ON;
        intent.putExtra(Constants.INTENT_EXTRA_EVENT_SOURCE, eventSource);
        try {
            startActivityForResult(intent, REQUEST_CODE_ACT_BUSCARD);
        } catch (Exception e) {
            e.printStackTrace();
            singlonToast.showSinglonToast(R.string.not_support, false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CLOSE_NFC:
                // this.finish();
                break;
            case REQUEST_CODE_OPEN_NFC:
                if (isNfcEnabled()) {
                    goToBusCard();
                } else {
                    singlonToast.showSinglonToast(R.string.open_nfc_please, false);
                }
                break;
            case REQUEST_CODE_ACT_BUSCARD:
                if (screenLockChecker.isScreenLocked()) {
                    //锁屏状态下，退出activity，防着再次进入无法打开刷卡的问题
                    ActMain.this.finish();
                } else if (commomShare.getBoolean(Constants.SHARE_KEY_IS_CLOSE_NFC_AFTER, false)) {
                    confirmCloseNfcWithToast();
                }

                //解决打开指纹识别，需要验证指纹，home键才恢复正常的问题
               /* FingerprintManager fingerprintManager = (FingerprintManager) ActMain.this.getSystemService(Context.FINGERPRINT_SERVICE);
                if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                    fingerprintManager.authenticate(null, null, 0, callback, null);
                }*/
                break;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_goto_forum) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
