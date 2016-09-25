package com.tsmclient.smartcard.handler;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.I2CSmartMxTerminal;
import com.tsmclient.smartcard.terminal.SPISmartMxTerminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SmartCardReader
{
  private static final String TAG = "SmartCardReader";
  private final Activity mActivity;
  private final Handler mHandler;
  private final List<ISmartCardHandler<IsoDep>> mIsoDepHandlerList;
  private SmartCardReaderListener mListener;
  private final List<ISmartCardHandler<NfcF>> mNfcFHandlerList;
  private PendingIntent mPendingIntent;
  private boolean mPolling;
  private final AtomicBoolean mStop;
  private final Handler mWorkHandler;
  private HandlerThread mWorkThread;

  public SmartCardReader(Activity paramActivity)
  {
    this(paramActivity, null, null);
  }

  public SmartCardReader(Activity paramActivity, Handler paramHandler1, Handler paramHandler2)
  {
    if (paramHandler1 == null)
    {
      this.mWorkThread = new HandlerThread("SCHandlerThread");
      this.mWorkThread.start();
      paramHandler1 = new Handler(this.mWorkThread.getLooper());
    }
    if (paramHandler2 == null)
      paramHandler2 = new Handler(Looper.getMainLooper());
    this.mWorkHandler = paramHandler1;
    this.mHandler = paramHandler2;
    this.mIsoDepHandlerList = new ArrayList();
    this.mNfcFHandlerList = new ArrayList();
    this.mActivity = paramActivity;
    this.mStop = new AtomicBoolean();
  }

  private static Bundle doReadCard(String paramString, Context paramContext)
    throws IOException, UnProcessableCardException
  {
    ISmartCardHandler localISmartCardHandler = getHandler(paramString);
    if ((localISmartCardHandler instanceof BankCardHandler));
    for (Object localObject1 = new SPISmartMxTerminal(paramContext); ; localObject1 = new I2CSmartMxTerminal(paramContext))
      try
      {
        ((AbstractTerminal)localObject1).connect();
        if (localISmartCardHandler != null)
          break;
        throw new UnProcessableCardException("No matched handler");
      }
      finally
      {
        ((AbstractTerminal)localObject1).close();
      }
    Bundle localBundle = localISmartCardHandler.onHandleCard((AbstractTerminal)localObject1);
    ((AbstractTerminal)localObject1).close();
    return (Bundle)localBundle;
  }

  private static ISmartCardHandler getHandler(String paramString)
  {
    Object localObject;
    if (TextUtils.equals(paramString, "004"))
      localObject = new SZTCardHandler();
    while (true)
    {
      return localObject;
      if (TextUtils.equals(paramString, "002"))
      {
        localObject = new CityUCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "001"))
      {
        localObject = new BMACCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "013"))
      {
        localObject = new LingNanCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "A0000003330101"))
      {
        localObject = new BankCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "019"))
      {
        localObject = new SuZhouTongCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "005"))
      {
        localObject = new WHTCardHandler();
        continue;
      }
      if (TextUtils.equals(paramString, "020"))
      {
        localObject = new HZTCardHandler();
        continue;
      }
      localObject = null;
    }
  }

  // ERROR //
  private Bundle handleIsoDep(IsoDep paramIsoDep)
    throws IOException
  {
    // Byte code:
    //   0: new 189	android/os/Bundle
    //   3: dup
    //   4: invokespecial 190	android/os/Bundle:<init>	()V
    //   7: astore_2
    //   8: aload_1
    //   9: invokevirtual 193	android/nfc/tech/IsoDep:connect	()V
    //   12: aload_0
    //   13: getfield 80	com/tsmclient/smartcard/handler/SmartCardReader:mActivity	Landroid/app/Activity;
    //   16: ldc 195
    //   18: invokestatic 201	com/tsmclient/smartcard/ReaderUtil:getFromSharedPrefs	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   21: invokestatic 204	com/tsmclient/smartcard/ReaderUtil:getHandlerById	(Ljava/lang/String;)Lcom/tsmclient/smartcard/handler/ISmartCardHandler;
    //   24: astore 5
    //   26: aload 5
    //   28: ifnull +26 -> 54
    //   31: aload 5
    //   33: aload_1
    //   34: invokeinterface 207 2 0
    //   39: astore 14
    //   41: aload_1
    //   42: invokevirtual 208	android/nfc/tech/IsoDep:close	()V
    //   45: aload 14
    //   47: astore 8
    //   49: aload 8
    //   51: areturn
    //   52: astore 13
    //   54: aload_0
    //   55: getfield 76	com/tsmclient/smartcard/handler/SmartCardReader:mIsoDepHandlerList	Ljava/util/List;
    //   58: invokeinterface 214 1 0
    //   63: astore 6
    //   65: aload 6
    //   67: invokeinterface 220 1 0
    //   72: ifeq +62 -> 134
    //   75: aload 6
    //   77: invokeinterface 224 1 0
    //   82: checkcast 139	com/tsmclient/smartcard/handler/ISmartCardHandler
    //   85: astore 9
    //   87: aload 9
    //   89: aload_1
    //   90: invokeinterface 207 2 0
    //   95: astore 11
    //   97: aload_1
    //   98: invokevirtual 208	android/nfc/tech/IsoDep:close	()V
    //   101: aload 11
    //   103: astore 8
    //   105: goto -56 -> 49
    //   108: astore 10
    //   110: aload_2
    //   111: ldc 226
    //   113: iconst_0
    //   114: invokevirtual 230	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   117: aload_2
    //   118: ldc 232
    //   120: iconst_2
    //   121: invokevirtual 236	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   124: goto -59 -> 65
    //   127: astore_3
    //   128: aload_1
    //   129: invokevirtual 208	android/nfc/tech/IsoDep:close	()V
    //   132: aload_3
    //   133: athrow
    //   134: aload_1
    //   135: invokevirtual 208	android/nfc/tech/IsoDep:close	()V
    //   138: aload_2
    //   139: astore 8
    //   141: goto -92 -> 49
    //   144: astore 15
    //   146: goto -101 -> 45
    //   149: astore 12
    //   151: goto -50 -> 101
    //   154: astore 7
    //   156: goto -18 -> 138
    //   159: astore 4
    //   161: goto -29 -> 132
    //
    // Exception table:
    //   from	to	target	type
    //   31	41	52	com/tsmclient/smartcard/exception/UnProcessableCardException
    //   87	97	108	com/tsmclient/smartcard/exception/UnProcessableCardException
    //   8	26	127	finally
    //   31	41	127	finally
    //   54	87	127	finally
    //   87	97	127	finally
    //   110	124	127	finally
    //   41	45	144	java/io/IOException
    //   97	101	149	java/io/IOException
    //   134	138	154	java/io/IOException
    //   128	132	159	java/io/IOException
  }

  // ERROR //
  private Bundle handleNfcF(NfcF paramNfcF)
    throws IOException
  {
    // Byte code:
    //   0: new 189	android/os/Bundle
    //   3: dup
    //   4: invokespecial 190	android/os/Bundle:<init>	()V
    //   7: astore_2
    //   8: aload_1
    //   9: invokevirtual 239	android/nfc/tech/NfcF:connect	()V
    //   12: aload_0
    //   13: getfield 78	com/tsmclient/smartcard/handler/SmartCardReader:mNfcFHandlerList	Ljava/util/List;
    //   16: invokeinterface 214 1 0
    //   21: astore 5
    //   23: aload 5
    //   25: invokeinterface 220 1 0
    //   30: ifeq +62 -> 92
    //   33: aload 5
    //   35: invokeinterface 224 1 0
    //   40: checkcast 139	com/tsmclient/smartcard/handler/ISmartCardHandler
    //   43: astore 8
    //   45: aload 8
    //   47: aload_1
    //   48: invokeinterface 207 2 0
    //   53: astore 10
    //   55: aload_1
    //   56: invokevirtual 240	android/nfc/tech/NfcF:close	()V
    //   59: aload 10
    //   61: astore 7
    //   63: aload 7
    //   65: areturn
    //   66: astore 9
    //   68: aload_2
    //   69: ldc 226
    //   71: iconst_0
    //   72: invokevirtual 230	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   75: aload_2
    //   76: ldc 232
    //   78: iconst_2
    //   79: invokevirtual 236	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   82: goto -59 -> 23
    //   85: astore_3
    //   86: aload_1
    //   87: invokevirtual 240	android/nfc/tech/NfcF:close	()V
    //   90: aload_3
    //   91: athrow
    //   92: aload_1
    //   93: invokevirtual 240	android/nfc/tech/NfcF:close	()V
    //   96: aconst_null
    //   97: astore 7
    //   99: goto -36 -> 63
    //   102: astore 11
    //   104: goto -45 -> 59
    //   107: astore 6
    //   109: goto -13 -> 96
    //   112: astore 4
    //   114: goto -24 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   45	55	66	com/tsmclient/smartcard/exception/UnProcessableCardException
    //   8	45	85	finally
    //   45	55	85	finally
    //   68	82	85	finally
    //   55	59	102	java/io/IOException
    //   92	96	107	java/io/IOException
    //   86	90	112	java/io/IOException
  }

  public static Bundle readCard(String paramString, Context paramContext)
  {
    try
    {
      Bundle localBundle2 = doReadCard(paramString, paramContext);
      localBundle1 = localBundle2;
      return localBundle1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        localBundle1 = new Bundle();
        localBundle1.putBoolean("success", false);
        localBundle1.putInt("error", 1);
      }
    }
    catch (UnProcessableCardException localUnProcessableCardException)
    {
      while (true)
      {
        localBundle1 = new Bundle();
        localBundle1.putBoolean("success", false);
        localBundle1.putInt("error", 2);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Bundle localBundle1 = new Bundle();
        localBundle1.putBoolean("success", false);
        localBundle1.putInt("error", 3);
        Log.e("SmartCardReader", "RuntimeException :", localException);
      }
    }
  }

  public void addSmartCardHandler(ISmartCardHandler paramISmartCardHandler)
  {
    switch (paramISmartCardHandler.getTechType())
    {
    default:
      Log.w("SmartCardReader", "unknown card handler: " + paramISmartCardHandler.getClass());
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      this.mIsoDepHandlerList.add(paramISmartCardHandler);
      continue;
      this.mNfcFHandlerList.add(paramISmartCardHandler);
    }
  }

  public void handleTag(Tag paramTag)
  {
    if (this.mStop.get())
      Log.w("SmartCardReader", "card reader has stopped to handle tag");
    while (true)
    {
      return;
      if (this.mListener != null)
        this.mListener.onStartHandleTag();
      this.mWorkHandler.post(new Runnable(paramTag)
      {
        public void run()
        {
          Bundle localBundle1 = null;
          try
          {
            IsoDep localIsoDep = IsoDep.get(this.val$tag);
            if ((localIsoDep != null) && (!SmartCardReader.this.mIsoDepHandlerList.isEmpty()))
            {
              localBundle1 = SmartCardReader.this.handleIsoDep(localIsoDep);
              localBundle1.putParcelable("nfc_tag", this.val$tag);
            }
            if (localIsoDep == null)
            {
              NfcF localNfcF = NfcF.get(this.val$tag);
              if ((localNfcF != null) && (!SmartCardReader.this.mNfcFHandlerList.isEmpty()))
                localBundle1 = SmartCardReader.this.handleNfcF(localNfcF);
            }
            else if (localBundle1 == null)
            {
              throw new IOException("SmartCardReaderfailed to handle tag");
            }
          }
          catch (IOException localIOException)
          {
            localBundle1 = new Bundle();
            localBundle1.putBoolean("success", false);
            localBundle1.putInt("error", 1);
            SmartCardReader.SmartCardReaderListener localSmartCardReaderListener = SmartCardReader.this.mListener;
            Bundle localBundle2 = localBundle1;
            if (localSmartCardReaderListener != null)
              SmartCardReader.this.mHandler.post(new Runnable(localSmartCardReaderListener, localBundle2)
              {
                public void run()
                {
                  this.val$finalListenr.onCompleteHandle(this.val$finalResult);
                }
              });
            return;
            throw new UnProcessableCardException("SmartCardReaderunsupported card type");
          }
          catch (UnProcessableCardException localUnProcessableCardException)
          {
            while (true)
            {
              localBundle1 = new Bundle();
              localBundle1.putBoolean("success", false);
              localBundle1.putInt("error", 2);
            }
          }
          catch (Exception localException)
          {
            while (true)
              Log.e("SmartCardReader", "RuntimeException :", localException);
          }
        }
      });
    }
  }

  public boolean isCardPolling()
  {
    return this.mPolling;
  }

  public void setListener(SmartCardReaderListener paramSmartCardReaderListener)
  {
    this.mListener = paramSmartCardReaderListener;
  }

  public void shutdown()
  {
    this.mStop.set(true);
    if (this.mWorkThread != null)
    {
      this.mWorkThread.quit();
      this.mWorkThread.interrupt();
    }
    this.mWorkHandler.removeCallbacksAndMessages(null);
    this.mHandler.removeCallbacksAndMessages(null);
  }

  public void startPoll()
  {
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(this.mActivity);
    if ((localNfcAdapter != null) && (localNfcAdapter.isEnabled()))
    {
      if (this.mPendingIntent == null)
      {
        Intent localIntent = new Intent(this.mActivity, this.mActivity.getClass()).addFlags(536870912);
        this.mPendingIntent = PendingIntent.getActivity(this.mActivity, 0, localIntent, 268435456);
      }
      localNfcAdapter.enableForegroundDispatch(this.mActivity, this.mPendingIntent, null, (String[][])null);
      this.mPolling = true;
    }
  }

  public void stopPoll()
  {
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(this.mActivity);
    if ((localNfcAdapter != null) && (this.mPolling))
    {
      localNfcAdapter.disableForegroundDispatch(this.mActivity);
      this.mPolling = false;
    }
  }

  public static abstract interface SmartCardReaderListener
  {
    public abstract void onCompleteHandle(Bundle paramBundle);

    public abstract void onStartHandleTag();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.SmartCardReader
 * JD-Core Version:    0.6.0
 */