package com.heiliuer.xiaomi_card;


public class RunningAppProcessInfoMask {
    public int processState;

    public String processName;

    /**
     * The pid of this process; 0 if none
     */
    public int pid;

    /**
     * The user id of this process.
     */
    public int uid;

    /**
     * All packages that have been loaded into the process.
     */
    public String pkgList[];

    public int flags;

    /**
     * Last memory trim level reported to the process: corresponds to
     * the values supplied to {@link android.content.ComponentCallbacks2#onTrimMemory(int)
     * ComponentCallbacks2.onTrimMemory(int)}.
     */
    public int lastTrimLevel;
}