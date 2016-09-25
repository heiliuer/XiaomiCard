package com.miui.tsmclient.demo;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.nfc.INfcAdapterExtras;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.miui.tsmclient.util.LogUtils;

public class TerminalDemoActivity extends Activity
{
  private static final int MSG_FAILURE = 2;
  private static final int MSG_SUCCESS = 1;
  public static final byte[] SELECT_NXP_CRS;
  public static byte[] disableCless;
  public static byte[] enableCless;
  public static byte[] enableClessWithC2;
  private byte[] GET_CPLC;
  private byte[] SELECT_ISD;
  Bundle bundle;
  private int counter;
  Binder mBinder = null;
  private CancellationSignal mCancellationSignal;
  private FingerprintManager mFingerManager;
  private Handler mHandler;
  private NfcAdapter mNfcAdapter;
  INfcAdapterExtras mNfcAdapterExtras = null;
  private boolean mSelfCancelled;
  NfcManager manager;
  private MyFingerAuthCallback myFingerAuthCallback;

  static
  {
    byte[] arrayOfByte1 = new byte[14];
    arrayOfByte1[0] = -128;
    arrayOfByte1[1] = -16;
    arrayOfByte1[2] = 1;
    arrayOfByte1[3] = 1;
    arrayOfByte1[4] = 9;
    arrayOfByte1[5] = 79;
    arrayOfByte1[6] = 7;
    arrayOfByte1[7] = -96;
    arrayOfByte1[8] = 0;
    arrayOfByte1[9] = 0;
    arrayOfByte1[10] = 3;
    arrayOfByte1[11] = 51;
    arrayOfByte1[12] = 1;
    arrayOfByte1[13] = 1;
    enableCless = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[39];
    arrayOfByte2[0] = -128;
    arrayOfByte2[1] = -16;
    arrayOfByte2[2] = 1;
    arrayOfByte2[3] = 1;
    arrayOfByte2[4] = 34;
    arrayOfByte2[5] = -62;
    arrayOfByte2[6] = 20;
    arrayOfByte2[7] = 1;
    arrayOfByte2[8] = 2;
    arrayOfByte2[9] = 3;
    arrayOfByte2[10] = 4;
    arrayOfByte2[11] = 5;
    arrayOfByte2[12] = 6;
    arrayOfByte2[13] = 7;
    arrayOfByte2[14] = 8;
    arrayOfByte2[15] = 9;
    arrayOfByte2[16] = 16;
    arrayOfByte2[17] = 17;
    arrayOfByte2[18] = 18;
    arrayOfByte2[19] = 19;
    arrayOfByte2[20] = 20;
    arrayOfByte2[21] = 21;
    arrayOfByte2[22] = 22;
    arrayOfByte2[23] = 23;
    arrayOfByte2[24] = 24;
    arrayOfByte2[25] = 25;
    arrayOfByte2[26] = 32;
    arrayOfByte2[27] = 79;
    arrayOfByte2[28] = 10;
    arrayOfByte2[29] = -96;
    arrayOfByte2[30] = 0;
    arrayOfByte2[31] = 0;
    arrayOfByte2[32] = 1;
    arrayOfByte2[33] = 81;
    arrayOfByte2[34] = 67;
    arrayOfByte2[35] = 82;
    arrayOfByte2[36] = 83;
    arrayOfByte2[37] = 16;
    arrayOfByte2[38] = 1;
    enableClessWithC2 = arrayOfByte2;
    byte[] arrayOfByte3 = new byte[14];
    arrayOfByte3[0] = -128;
    arrayOfByte3[1] = -16;
    arrayOfByte3[2] = 1;
    arrayOfByte3[3] = 0;
    arrayOfByte3[4] = 9;
    arrayOfByte3[5] = 79;
    arrayOfByte3[6] = 7;
    arrayOfByte3[7] = -96;
    arrayOfByte3[8] = 0;
    arrayOfByte3[9] = 0;
    arrayOfByte3[10] = 3;
    arrayOfByte3[11] = 51;
    arrayOfByte3[12] = 1;
    arrayOfByte3[13] = 1;
    disableCless = arrayOfByte3;
    byte[] arrayOfByte4 = new byte[14];
    arrayOfByte4[0] = 0;
    arrayOfByte4[1] = -92;
    arrayOfByte4[2] = 4;
    arrayOfByte4[3] = 0;
    arrayOfByte4[4] = 9;
    arrayOfByte4[5] = -96;
    arrayOfByte4[6] = 0;
    arrayOfByte4[7] = 0;
    arrayOfByte4[8] = 1;
    arrayOfByte4[9] = 81;
    arrayOfByte4[10] = 67;
    arrayOfByte4[11] = 82;
    arrayOfByte4[12] = 83;
    arrayOfByte4[13] = 0;
    SELECT_NXP_CRS = arrayOfByte4;
  }

  public TerminalDemoActivity()
  {
    byte[] arrayOfByte1 = new byte[13];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = -92;
    arrayOfByte1[2] = 4;
    arrayOfByte1[3] = 0;
    arrayOfByte1[4] = 8;
    arrayOfByte1[5] = -96;
    arrayOfByte1[6] = 0;
    arrayOfByte1[7] = 0;
    arrayOfByte1[8] = 1;
    arrayOfByte1[9] = 81;
    arrayOfByte1[10] = 0;
    arrayOfByte1[11] = 0;
    arrayOfByte1[12] = 0;
    this.SELECT_ISD = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[5];
    arrayOfByte2[0] = -128;
    arrayOfByte2[1] = -54;
    arrayOfByte2[2] = -97;
    arrayOfByte2[3] = 127;
    arrayOfByte2[4] = 0;
    this.GET_CPLC = arrayOfByte2;
    this.mHandler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        default:
        case 1:
        case 2:
        }
        while (true)
        {
          return;
          Toast.makeText(TerminalDemoActivity.this, "success", 0).show();
          continue;
          Toast.makeText(TerminalDemoActivity.this, "error", 0).show();
        }
      }
    };
    this.counter = 0;
    this.mSelfCancelled = true;
    this.myFingerAuthCallback = new MyFingerAuthCallback(null);
  }

  private boolean isFingerAuthAvailable()
  {
    if ((this.mFingerManager.isHardwareDetected()) && (this.mFingerManager.hasEnrolledFingerprints()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void startListener()
  {
    if (!isFingerAuthAvailable())
      LogUtils.w("finger auth unavailable");
    while (true)
    {
      return;
      if (this.mCancellationSignal == null)
        this.mCancellationSignal = new CancellationSignal();
      LogUtils.i("mSelfCancelled = " + this.mSelfCancelled);
      if (!this.mSelfCancelled)
        continue;
      this.mSelfCancelled = false;
      LogUtils.i("start listening authentication");
      this.mFingerManager.authenticate(null, this.mCancellationSignal, 0, this.myFingerAuthCallback, null);
    }
  }

  private void stopListener()
  {
    if (this.mCancellationSignal != null)
    {
      this.mCancellationSignal.cancel();
      this.mSelfCancelled = true;
      this.mCancellationSignal = null;
    }
  }

  public void disable(Context paramContext)
  {
    try
    {
      this.bundle = this.mNfcAdapterExtras.close(paramContext.getPackageName(), this.mBinder);
      if (this.bundle.getInt("e") != 0)
      {
        LogUtils.i("Unable to open the eSE connection : " + this.bundle.getString("m"));
        this.mHandler.obtainMessage(2).sendToTarget();
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        LogUtils.e("RemoteException caught : ", localRemoteException);
        this.mHandler.obtainMessage(2).sendToTarget();
        continue;
        this.mHandler.obtainMessage(1).sendToTarget();
      }
    }
  }

  public void enable(Context paramContext)
  {
    try
    {
      this.bundle = this.mNfcAdapterExtras.open(paramContext.getPackageName(), this.mBinder);
      if (this.bundle.getInt("e") != 0)
      {
        LogUtils.i("Unable to open the eSE connection : " + this.bundle.getString("m"));
        this.mHandler.obtainMessage(2).sendToTarget();
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        LogUtils.e("RemoteException caught : ", localRemoteException);
        this.mHandler.obtainMessage(2).sendToTarget();
        continue;
        this.mHandler.obtainMessage(1).sendToTarget();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Context localContext = getApplicationContext();
    this.manager = ((NfcManager)localContext.getSystemService("nfc"));
    this.mNfcAdapter = this.manager.getDefaultAdapter();
    if (this.mBinder == null)
      this.mBinder = new Binder();
    if (this.mNfcAdapterExtras == null)
      this.mNfcAdapterExtras = this.mNfcAdapter.getNfcAdapterExtrasInterface();
    this.mFingerManager = ((FingerprintManager)getSystemService("fingerprint"));
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localLinearLayout.setOrientation(1);
    Button localButton1 = new Button(this);
    localButton1.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton1.setText("Enable Contactless interface ");
    localButton1.setOnClickListener(new View.OnClickListener(localContext)
    {
      public void onClick(View paramView)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            if (TerminalDemoActivity.this.manager != null)
              TerminalDemoActivity.this.enable(TerminalDemoActivity.2.this.val$mContext);
          }
        }).start();
      }
    });
    Button localButton2 = new Button(this);
    localButton2.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton2.setText("Disable Contactless interface ");
    localButton2.setOnClickListener(new View.OnClickListener(localContext)
    {
      public void onClick(View paramView)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            if (TerminalDemoActivity.this.manager != null)
            {
              TerminalDemoActivity.this.stopListener();
              TerminalDemoActivity.this.disable(TerminalDemoActivity.3.this.val$mContext);
            }
          }
        }).start();
      }
    });
    Button localButton3 = new Button(this);
    localButton3.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton3.setText("select and get cplc");
    localButton3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TerminalDemoActivity.this.manager != null);
        try
        {
          TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.this.SELECT_ISD);
          TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.this.GET_CPLC);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          while (true)
            localRemoteException.printStackTrace();
        }
      }
    });
    Button localButton4 = new Button(this);
    localButton4.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton4.setText("activate test applet");
    localButton4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TerminalDemoActivity.access$302(TerminalDemoActivity.this, 0);
        TerminalDemoActivity.this.startListener();
      }
    });
    Button localButton5 = new Button(this);
    localButton5.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton5.setText("deactivate test applet");
    localButton5.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TerminalDemoActivity.access$302(TerminalDemoActivity.this, 1);
        TerminalDemoActivity.this.startListener();
      }
    });
    Button localButton6 = new Button(this);
    localButton6.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
    localButton6.setText("activate test applet with C2");
    localButton6.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TerminalDemoActivity.this.manager != null);
        try
        {
          TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.SELECT_NXP_CRS);
          TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.enableClessWithC2);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          while (true)
            localRemoteException.printStackTrace();
        }
      }
    });
    localLinearLayout.addView(localButton1);
    localLinearLayout.addView(localButton2);
    localLinearLayout.addView(localButton3);
    localLinearLayout.addView(localButton4);
    localLinearLayout.addView(localButton5);
    localLinearLayout.addView(localButton6);
    setContentView(localLinearLayout);
  }

  private class MyFingerAuthCallback extends FingerprintManager.AuthenticationCallback
  {
    private MyFingerAuthCallback()
    {
    }

    public void onAuthenticationError(int paramInt, CharSequence paramCharSequence)
    {
      LogUtils.d("onAuthenticationErro: errorCode = " + paramInt + " ,errString = " + paramCharSequence);
    }

    public void onAuthenticationFailed()
    {
      TerminalDemoActivity.this.stopListener();
      TerminalDemoActivity.this.startListener();
      LogUtils.d("onAuthenticationSucceeded");
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAuthenticationResult)
    {
      LogUtils.d("onAuthenticationSucceeded");
      TerminalDemoActivity.this.stopListener();
      new Thread(new Runnable()
      {
        public void run()
        {
          LogUtils.d("gonon0: " + System.currentTimeMillis());
          if ((TerminalDemoActivity.this.manager == null) || (TerminalDemoActivity.this.counter == 0));
          while (true)
          {
            try
            {
              TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.SELECT_NXP_CRS);
              TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.enableCless);
              LogUtils.d("gonon1: " + System.currentTimeMillis());
              return;
            }
            catch (RemoteException localRemoteException2)
            {
              localRemoteException2.printStackTrace();
              continue;
            }
            try
            {
              TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.SELECT_NXP_CRS);
              TerminalDemoActivity.this.mNfcAdapterExtras.transceive("com.miui.tsmclient", TerminalDemoActivity.disableCless);
            }
            catch (RemoteException localRemoteException1)
            {
              localRemoteException1.printStackTrace();
            }
          }
        }
      }).start();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.demo.TerminalDemoActivity
 * JD-Core Version:    0.6.0
 */