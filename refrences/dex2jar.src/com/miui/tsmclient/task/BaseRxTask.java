package com.miui.tsmclient.task;

import android.content.Context;
import rx.Observable.OnSubscribe;

public abstract class BaseRxTask<R>
  implements Observable.OnSubscribe<R>
{
  private Context mContext;
  private R mResult;
  private Class<R> mResultClazz;

  public BaseRxTask(Context paramContext, Class<R> paramClass)
  {
    this.mResultClazz = paramClass;
    this.mContext = paramContext;
    if ((this.mResultClazz != null) && (Void.class.equals(this.mResultClazz)))
      throw new IllegalArgumentException();
  }

  // ERROR //
  public void call(rx.Subscriber<? super R> paramSubscriber)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 22	com/miui/tsmclient/task/BaseRxTask:mResultClazz	Ljava/lang/Class;
    //   5: invokevirtual 52	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   8: putfield 54	com/miui/tsmclient/task/BaseRxTask:mResult	Ljava/lang/Object;
    //   11: aload_0
    //   12: aload_0
    //   13: getfield 54	com/miui/tsmclient/task/BaseRxTask:mResult	Ljava/lang/Object;
    //   16: invokevirtual 57	com/miui/tsmclient/task/BaseRxTask:doLoad	(Ljava/lang/Object;)V
    //   19: aload_1
    //   20: aload_0
    //   21: getfield 54	com/miui/tsmclient/task/BaseRxTask:mResult	Ljava/lang/Object;
    //   24: invokevirtual 60	rx/Subscriber:onNext	(Ljava/lang/Object;)V
    //   27: aload_1
    //   28: invokevirtual 63	rx/Subscriber:onCompleted	()V
    //   31: return
    //   32: astore_3
    //   33: new 65	java/lang/IllegalStateException
    //   36: dup
    //   37: aload_3
    //   38: invokespecial 68	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   41: athrow
    //   42: astore_2
    //   43: new 65	java/lang/IllegalStateException
    //   46: dup
    //   47: aload_2
    //   48: invokespecial 68	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   51: athrow
    //   52: astore 4
    //   54: ldc 70
    //   56: aload 4
    //   58: invokestatic 76	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   61: aload_1
    //   62: aload 4
    //   64: invokevirtual 79	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
    //   67: goto -36 -> 31
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	32	java/lang/InstantiationException
    //   0	11	42	java/lang/IllegalAccessException
    //   11	31	52	java/lang/Exception
  }

  protected abstract void doLoad(R paramR);

  public Context getContext()
  {
    return this.mContext;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.BaseRxTask
 * JD-Core Version:    0.6.0
 */