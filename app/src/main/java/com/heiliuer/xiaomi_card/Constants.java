package com.heiliuer.xiaomi_card;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class Constants {
    public static final String SHARE_KEY_IS_AUTO_CLICK_CARD = "isAutoClickCard";
    public static final String SHARE_KEY_IS_OPEN_CARD_ON_ENTER = "isOpenCardOnEnter";
    public static final String SHARE_KEY_IS_CLOSE_NFC_AFTER = "isCloseNfcAfter";
    static final String SHARE_KEY_IS_AUTO_CLICK_IN_LOCKED = "isAutoClickInLocked";
    public static String UNDERSCORE = "_";
    static String DOTTED = ".";

    public static final String INTENT_EXTRA_EVENT_SOURCE = DATA.EVENT + UNDERSCORE + DATA.SOURCE;//"event_source";
    public static final String EVENT_SOURCE_KEY_RF_ON = DATA.KEY + UNDERSCORE + DATA.RF + UNDERSCORE + DATA.ON;//"key_rf_on";
    public static final String EVENT_SOURCE_KEY_VOLUME_DOWN = DATA.KEY + UNDERSCORE +
            DATA.VOLUME + UNDERSCORE + DATA.DOWN;//"key_volume_down";

    public static class DATA {
        public static final String KEY = "key";
        public static final String VOLUME = "volume";
        public static final String DOWN = "down";
        public static final String EVENT = "event";
        public static final String SOURCE = "source";
        public static final String RF = "rf";
        public static final String ON = "on";
        public static final String PACKAGE = "com.miui.tsmclient";
        public static final String UI = "ui";
        public static final String QUICK = "quick";
        public static final String ACTIVITY = "DoubleClickActivity";
    }

}
