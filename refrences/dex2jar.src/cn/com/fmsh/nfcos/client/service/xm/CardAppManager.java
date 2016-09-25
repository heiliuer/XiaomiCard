package cn.com.fmsh.nfcos.client.service.xm;

import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract interface CardAppManager extends IInterface
{
  public abstract int applyIssue(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
    throws RemoteException;

  public abstract int applyIssueByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
    throws RemoteException;

  public abstract int applyRecharge(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
    throws RemoteException;

  public abstract int applyRechargeEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int cancelIssue(int paramInt)
    throws RemoteException;

  public abstract int cancelOrder(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int closeApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
    throws RemoteException;

  public abstract int deleteApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, CardAppInfo paramCardAppInfo)
    throws RemoteException;

  public abstract int doIssue(byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws RemoteException;

  public abstract int doIssueEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int doIssueExByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int doRefund(byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract int doUnsolvedOrder(byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract int downloadApplet(int paramInt, byte[] paramArrayOfByte, String paramString)
    throws RemoteException;

  public abstract int getAppIssueStatus(int paramInt, CardAppStatus paramCardAppStatus)
    throws RemoteException;

  public abstract int getAppIssueStatusByPlatform(int paramInt, byte[] paramArrayOfByte, String paramString, CardAppStatus paramCardAppStatus)
    throws RemoteException;

  public abstract int getInfo(int paramInt1, int paramInt2, CardAppInfo paramCardAppInfo)
    throws RemoteException;

  public abstract int getInvoiceToken(byte[] paramArrayOfByte, InvoiceToken paramInvoiceToken)
    throws RemoteException;

  public abstract int login(String paramString1, String paramString2, LoginInfo paramLoginInfo)
    throws RemoteException;

  public abstract int logout()
    throws RemoteException;

  public abstract int modifyPassword(String paramString1, String paramString2)
    throws RemoteException;

  public abstract int modifyUserInfo(UserInfo paramUserInfo)
    throws RemoteException;

  public abstract int moveApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, VoucherInfo paramVoucherInfo)
    throws RemoteException;

  public abstract int openApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
    throws RemoteException;

  public abstract int paid4order(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int queryActivities(int paramInt, List<NfcosActivity> paramList)
    throws RemoteException;

  public abstract int queryBusinessOrder(byte[] paramArrayOfByte, NfcosBusinessOrder paramNfcosBusinessOrder)
    throws RemoteException;

  public abstract int queryBusinessOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4, List<NfcosBusinessOrder> paramList)
    throws RemoteException;

  public abstract int queryMainOrder(byte[] paramArrayOfByte, NfcosMainOrder paramNfcosMainOrder)
    throws RemoteException;

  public abstract int queryMainOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, List<NfcosMainOrder> paramList)
    throws RemoteException;

  public abstract int queryPayOrder(byte[] paramArrayOfByte, NfcosPayOrder paramNfcosPayOrder)
    throws RemoteException;

  public abstract int queryPreDeposit(int paramInt, PreDepositInfo paramPreDepositInfo)
    throws RemoteException;

  public abstract int recharge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws RemoteException;

  public abstract int register(UserInfo paramUserInfo)
    throws RemoteException;

  public abstract int switchMode2NFC(Tag paramTag)
    throws RemoteException;

  public abstract int switchMode2OMA(int paramInt1, int paramInt2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements CardAppManager
  {
    private static final String DESCRIPTOR = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager";
    static final int TRANSACTION_applyIssue = 16;
    static final int TRANSACTION_applyIssueByProduct = 17;
    static final int TRANSACTION_applyRecharge = 15;
    static final int TRANSACTION_applyRechargeEx = 26;
    static final int TRANSACTION_cancelIssue = 11;
    static final int TRANSACTION_cancelOrder = 20;
    static final int TRANSACTION_closeApp = 8;
    static final int TRANSACTION_deleteApp = 7;
    static final int TRANSACTION_doIssue = 3;
    static final int TRANSACTION_doIssueEx = 4;
    static final int TRANSACTION_doIssueExByProduct = 5;
    static final int TRANSACTION_doRefund = 28;
    static final int TRANSACTION_doUnsolvedOrder = 27;
    static final int TRANSACTION_downloadApplet = 6;
    static final int TRANSACTION_getAppIssueStatus = 12;
    static final int TRANSACTION_getAppIssueStatusByPlatform = 13;
    static final int TRANSACTION_getInfo = 14;
    static final int TRANSACTION_getInvoiceToken = 29;
    static final int TRANSACTION_login = 31;
    static final int TRANSACTION_logout = 32;
    static final int TRANSACTION_modifyPassword = 33;
    static final int TRANSACTION_modifyUserInfo = 34;
    static final int TRANSACTION_moveApp = 10;
    static final int TRANSACTION_openApp = 9;
    static final int TRANSACTION_paid4order = 19;
    static final int TRANSACTION_queryActivities = 35;
    static final int TRANSACTION_queryBusinessOrder = 22;
    static final int TRANSACTION_queryBusinessOrders = 23;
    static final int TRANSACTION_queryMainOrder = 21;
    static final int TRANSACTION_queryMainOrders = 18;
    static final int TRANSACTION_queryPayOrder = 24;
    static final int TRANSACTION_queryPreDeposit = 36;
    static final int TRANSACTION_recharge = 25;
    static final int TRANSACTION_register = 30;
    static final int TRANSACTION_switchMode2NFC = 2;
    static final int TRANSACTION_switchMode2OMA = 1;

    public Stub()
    {
      attachInterface(this, "cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
    }

    public static CardAppManager asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
        if ((localIInterface != null) && ((localIInterface instanceof CardAppManager)))
        {
          localObject = (CardAppManager)localIInterface;
          continue;
        }
        localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
        while (true)
        {
          return bool;
          paramParcel2.writeString("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i54 = switchMode2OMA(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i54);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramParcel1.readInt() != 0);
          for (Tag localTag = (Tag)Tag.CREATOR.createFromParcel(paramParcel1); ; localTag = null)
          {
            int i53 = switchMode2NFC(localTag);
            paramParcel2.writeNoException();
            paramParcel2.writeInt(i53);
            bool = true;
            break;
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i52 = doIssue(paramParcel1.createByteArray(), paramParcel1.readByte(), paramParcel1.createByteArray(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i52);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i51 = doIssueEx(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i51);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i50 = doIssueExByProduct(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i50);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i49 = downloadApplet(paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i49);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte14 = paramParcel1.createByteArray();
          int i47 = paramParcel1.readInt();
          byte[] arrayOfByte15 = paramParcel1.createByteArray();
          String str8 = paramParcel1.readString();
          CardAppInfo localCardAppInfo2 = new CardAppInfo();
          int i48 = deleteApp(arrayOfByte14, i47, arrayOfByte15, str8, localCardAppInfo2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i48);
          if (localCardAppInfo2 != null)
          {
            paramParcel2.writeInt(1);
            localCardAppInfo2.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i46 = closeApp(paramParcel1.createByteArray(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i46);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i45 = openApp(paramParcel1.createByteArray(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i45);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte12 = paramParcel1.createByteArray();
          int i43 = paramParcel1.readInt();
          byte[] arrayOfByte13 = paramParcel1.createByteArray();
          String str7 = paramParcel1.readString();
          VoucherInfo localVoucherInfo = new VoucherInfo();
          int i44 = moveApp(arrayOfByte12, i43, arrayOfByte13, str7, localVoucherInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i44);
          if (localVoucherInfo != null)
          {
            paramParcel2.writeInt(1);
            localVoucherInfo.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i42 = cancelIssue(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i42);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i40 = paramParcel1.readInt();
          CardAppStatus localCardAppStatus2 = new CardAppStatus();
          int i41 = getAppIssueStatus(i40, localCardAppStatus2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i41);
          if (localCardAppStatus2 != null)
          {
            paramParcel2.writeInt(1);
            localCardAppStatus2.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i38 = paramParcel1.readInt();
          byte[] arrayOfByte11 = paramParcel1.createByteArray();
          String str6 = paramParcel1.readString();
          CardAppStatus localCardAppStatus1 = new CardAppStatus();
          int i39 = getAppIssueStatusByPlatform(i38, arrayOfByte11, str6, localCardAppStatus1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i39);
          if (localCardAppStatus1 != null)
          {
            paramParcel2.writeInt(1);
            localCardAppStatus1.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i35 = paramParcel1.readInt();
          int i36 = paramParcel1.readInt();
          CardAppInfo localCardAppInfo1 = new CardAppInfo();
          int i37 = getInfo(i35, i36, localCardAppInfo1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i37);
          if (localCardAppInfo1 != null)
          {
            paramParcel2.writeInt(1);
            localCardAppInfo1.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i31 = paramParcel1.readInt();
          int i32 = paramParcel1.readInt();
          int i33 = paramParcel1.readInt();
          byte[] arrayOfByte9 = paramParcel1.createByteArray();
          byte[] arrayOfByte10 = paramParcel1.createByteArray();
          NfcosMainOrder localNfcosMainOrder4 = new NfcosMainOrder();
          int i34 = applyRecharge(i31, i32, i33, arrayOfByte9, arrayOfByte10, localNfcosMainOrder4);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i34);
          if (localNfcosMainOrder4 != null)
          {
            paramParcel2.writeInt(1);
            localNfcosMainOrder4.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i27 = paramParcel1.readInt();
          int i28 = paramParcel1.readInt();
          int i29 = paramParcel1.readInt();
          byte[] arrayOfByte7 = paramParcel1.createByteArray();
          String str5 = paramParcel1.readString();
          byte[] arrayOfByte8 = paramParcel1.createByteArray();
          NfcosMainOrder localNfcosMainOrder3 = new NfcosMainOrder();
          int i30 = applyIssue(i27, i28, i29, arrayOfByte7, str5, arrayOfByte8, localNfcosMainOrder3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i30);
          if (localNfcosMainOrder3 != null)
          {
            paramParcel2.writeInt(1);
            localNfcosMainOrder3.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i24 = paramParcel1.readInt();
          String str3 = paramParcel1.readString();
          int i25 = paramParcel1.readInt();
          byte[] arrayOfByte5 = paramParcel1.createByteArray();
          String str4 = paramParcel1.readString();
          byte[] arrayOfByte6 = paramParcel1.createByteArray();
          NfcosMainOrder localNfcosMainOrder2 = new NfcosMainOrder();
          int i26 = applyIssueByProduct(i24, str3, i25, arrayOfByte5, str4, arrayOfByte6, localNfcosMainOrder2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i26);
          if (localNfcosMainOrder2 != null)
          {
            paramParcel2.writeInt(1);
            localNfcosMainOrder2.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i20 = paramParcel1.readInt();
          int i21 = paramParcel1.readInt();
          int i22 = paramParcel1.readInt();
          int[] arrayOfInt2 = paramParcel1.createIntArray();
          ArrayList localArrayList3 = new ArrayList();
          int i23 = queryMainOrders(i20, i21, i22, arrayOfInt2, localArrayList3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i23);
          paramParcel2.writeTypedList(localArrayList3);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i19 = paid4order(paramParcel1.createByteArray(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i19);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i18 = cancelOrder(paramParcel1.createByteArray(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i18);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte4 = paramParcel1.createByteArray();
          NfcosMainOrder localNfcosMainOrder1 = new NfcosMainOrder();
          int i17 = queryMainOrder(arrayOfByte4, localNfcosMainOrder1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i17);
          if (localNfcosMainOrder1 != null)
          {
            paramParcel2.writeInt(1);
            localNfcosMainOrder1.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte3 = paramParcel1.createByteArray();
          NfcosBusinessOrder localNfcosBusinessOrder = new NfcosBusinessOrder();
          int i16 = queryBusinessOrder(arrayOfByte3, localNfcosBusinessOrder);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i16);
          if (localNfcosBusinessOrder != null)
          {
            paramParcel2.writeInt(1);
            localNfcosBusinessOrder.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i11 = paramParcel1.readInt();
          int i12 = paramParcel1.readInt();
          int i13 = paramParcel1.readInt();
          int[] arrayOfInt1 = paramParcel1.createIntArray();
          int i14 = paramParcel1.readInt();
          ArrayList localArrayList2 = new ArrayList();
          int i15 = queryBusinessOrders(i11, i12, i13, arrayOfInt1, i14, localArrayList2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i15);
          paramParcel2.writeTypedList(localArrayList2);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte2 = paramParcel1.createByteArray();
          NfcosPayOrder localNfcosPayOrder = new NfcosPayOrder();
          int i10 = queryPayOrder(arrayOfByte2, localNfcosPayOrder);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
          if (localNfcosPayOrder != null)
          {
            paramParcel2.writeInt(1);
            localNfcosPayOrder.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i9 = recharge(paramParcel1.createByteArray(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i9);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i8 = applyRechargeEx(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i8);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i7 = doUnsolvedOrder(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i6 = doRefund(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i6);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          byte[] arrayOfByte1 = paramParcel1.createByteArray();
          InvoiceToken localInvoiceToken = new InvoiceToken();
          int i5 = getInvoiceToken(arrayOfByte1, localInvoiceToken);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          if (localInvoiceToken != null)
          {
            paramParcel2.writeInt(1);
            localInvoiceToken.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramParcel1.readInt() != 0);
          for (UserInfo localUserInfo2 = (UserInfo)UserInfo.CREATOR.createFromParcel(paramParcel1); ; localUserInfo2 = null)
          {
            int i4 = register(localUserInfo2);
            paramParcel2.writeNoException();
            paramParcel2.writeInt(i4);
            bool = true;
            break;
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          String str1 = paramParcel1.readString();
          String str2 = paramParcel1.readString();
          LoginInfo localLoginInfo = new LoginInfo();
          int i3 = login(str1, str2, localLoginInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          if (localLoginInfo != null)
          {
            paramParcel2.writeInt(1);
            localLoginInfo.writeToParcel(paramParcel2, 1);
          }
          while (true)
          {
            bool = true;
            break;
            paramParcel2.writeInt(0);
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i2 = logout();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int i1 = modifyPassword(paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          bool = true;
          continue;
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramParcel1.readInt() != 0);
          for (UserInfo localUserInfo1 = (UserInfo)UserInfo.CREATOR.createFromParcel(paramParcel1); ; localUserInfo1 = null)
          {
            int n = modifyUserInfo(localUserInfo1);
            paramParcel2.writeNoException();
            paramParcel2.writeInt(n);
            bool = true;
            break;
          }
          paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          int k = paramParcel1.readInt();
          ArrayList localArrayList1 = new ArrayList();
          int m = queryActivities(k, localArrayList1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          paramParcel2.writeTypedList(localArrayList1);
          bool = true;
        }
      case 36:
      }
      paramParcel1.enforceInterface("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
      int i = paramParcel1.readInt();
      PreDepositInfo localPreDepositInfo = new PreDepositInfo();
      int j = queryPreDeposit(i, localPreDepositInfo);
      paramParcel2.writeNoException();
      paramParcel2.writeInt(j);
      if (localPreDepositInfo != null)
      {
        paramParcel2.writeInt(1);
        localPreDepositInfo.writeToParcel(paramParcel2, 1);
      }
      while (true)
      {
        bool = true;
        break;
        paramParcel2.writeInt(0);
      }
    }

    private static class Proxy
      implements CardAppManager
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public int applyIssue(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeString(paramString);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosMainOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int applyIssueByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeString(paramString1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeString(paramString2);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosMainOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int applyRecharge(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosMainOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int applyRechargeEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(26, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public int cancelIssue(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int cancelOrder(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int closeApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int deleteApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, CardAppInfo paramCardAppInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramCardAppInfo.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int doIssue(byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByte(paramByte);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeByteArray(paramArrayOfByte3);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int doIssueEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeString(paramString);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int doIssueExByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeString(paramString1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeString(paramString2);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int doRefund(byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int doUnsolvedOrder(byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(27, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int downloadApplet(int paramInt, byte[] paramArrayOfByte, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getAppIssueStatus(int paramInt, CardAppStatus paramCardAppStatus)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramCardAppStatus.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getAppIssueStatusByPlatform(int paramInt, byte[] paramArrayOfByte, String paramString, CardAppStatus paramCardAppStatus)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString);
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramCardAppStatus.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getInfo(int paramInt1, int paramInt2, CardAppInfo paramCardAppInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramCardAppInfo.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String getInterfaceDescriptor()
      {
        return "cn.com.fmsh.nfcos.client.service.xm.CardAppManager";
      }

      public int getInvoiceToken(byte[] paramArrayOfByte, InvoiceToken paramInvoiceToken)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramInvoiceToken.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int login(String paramString1, String paramString2, LoginInfo paramLoginInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(31, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramLoginInfo.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int logout()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          this.mRemote.transact(32, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int modifyPassword(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(33, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int modifyUserInfo(UserInfo paramUserInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramUserInfo != null)
          {
            localParcel1.writeInt(1);
            paramUserInfo.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(34, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int moveApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, VoucherInfo paramVoucherInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramVoucherInfo.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int openApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int paid4order(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryActivities(int paramInt, List<NfcosActivity> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(35, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.readTypedList(paramList, NfcosActivity.CREATOR);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryBusinessOrder(byte[] paramArrayOfByte, NfcosBusinessOrder paramNfcosBusinessOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosBusinessOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryBusinessOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4, List<NfcosBusinessOrder> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeIntArray(paramArrayOfInt);
          localParcel1.writeInt(paramInt4);
          this.mRemote.transact(23, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.readTypedList(paramList, NfcosBusinessOrder.CREATOR);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryMainOrder(byte[] paramArrayOfByte, NfcosMainOrder paramNfcosMainOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosMainOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryMainOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, List<NfcosMainOrder> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeIntArray(paramArrayOfInt);
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.readTypedList(paramList, NfcosMainOrder.CREATOR);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryPayOrder(byte[] paramArrayOfByte, NfcosPayOrder paramNfcosPayOrder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramNfcosPayOrder.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int queryPreDeposit(int paramInt, PreDepositInfo paramPreDepositInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(36, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (localParcel2.readInt() != 0)
            paramPreDepositInfo.readFromParcel(localParcel2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int recharge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeByteArray(paramArrayOfByte1);
          localParcel1.writeByteArray(paramArrayOfByte2);
          this.mRemote.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int register(UserInfo paramUserInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramUserInfo != null)
          {
            localParcel1.writeInt(1);
            paramUserInfo.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(30, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int switchMode2NFC(Tag paramTag)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          if (paramTag != null)
          {
            localParcel1.writeInt(1);
            paramTag.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int switchMode2OMA(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("cn.com.fmsh.nfcos.client.service.xm.CardAppManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.CardAppManager
 * JD-Core Version:    0.6.0
 */