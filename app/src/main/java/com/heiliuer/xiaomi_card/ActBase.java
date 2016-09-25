package com.heiliuer.xiaomi_card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public abstract class ActBase extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isImmerseStatusBar()) {
            //状态栏沉浸
            Utils.immerseStatusBar(this, true);
        }
    }

    protected boolean isImmerseStatusBar() {
        return true;
    }
}
