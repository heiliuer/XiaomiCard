package com.heiliuer.xiaomi_card;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class ThreadExceptionHandler {

    public static void handlerThreadException(final OnException onException) {
        final Thread.UncaughtExceptionHandler oldHandler =
                Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {

                        boolean delegate = onException.onException(paramThread, paramThrowable);
                        if (oldHandler != null) {
                            if (!delegate) {
                                oldHandler.uncaughtException(
                                        paramThread,
                                        paramThrowable
                                ); //Delegates to Android's error handling
                            }
                        } else {
                            System.exit(2); //Prevents the service/app from freezing
                        }
                    }
                });
    }

    interface OnException {
        /**
         * @param paramThread
         * @param paramThrowable
         * @return notDelegate Exception
         */
        boolean onException(Thread paramThread,
                            Throwable paramThrowable);
    }
}
