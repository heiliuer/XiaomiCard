package com.miui.tsmclient.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.handler.SmartCardReader;
import com.tsmclient.smartcard.terminal.APDUConstants;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.CommandApdu;
import com.tsmclient.smartcard.terminal.I2CSmartMxTerminal;
import com.tsmclient.smartcard.terminal.SPISmartMxTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.ITLVValue;
import com.tsmclient.smartcard.tlv.TLVParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import miui.cloud.finddevice.FindDeviceInfo;
import miui.cloud.finddevice.FindDeviceStatusManager;
import miui.cloud.finddevice.FindDeviceStatusManager.FindDeviceStatusManagerException;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;

public class SysUtils
{
  private static final int LOCATION_AWAIT_TIME = 10000;
  private static final byte STATE_ACTIVATED = 1;
  private static final byte STATE_DEACTIVITED = 0;
  private static final byte STATE_NON_ACTIVATABLE = -128;
  private static final ByteArray TAG_AEF_ENTRANCE;
  private static final ByteArray TAG_AID;
  private static final ByteArray TAG_BANK_CUSTOM_DATA;
  private static final ByteArray TAG_FCI_DATA_TEMPLATE;
  private static final ByteArray TAG_LIFESTYLE_STATE;
  private static String sCIN;
  private static String sCPLC;
  private static String sDefaultBankCardAid;
  private static String sDefaultTransCardAid;
  private static String sImei;
  private static String sSeid;
  private static List<ByteArray> sSupportedTransCardAids;
  private static Map<String, Pair<String, String>> sSupportedTransCardMap;
  private static List<String> sSupportedTransCards;

  static
  {
    byte[] arrayOfByte1 = new byte[1];
    arrayOfByte1[0] = -91;
    TAG_FCI_DATA_TEMPLATE = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[1];
    arrayOfByte2[0] = 97;
    TAG_AEF_ENTRANCE = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[1];
    arrayOfByte3[0] = 79;
    TAG_AID = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[2];
    arrayOfByte4[0] = -97;
    arrayOfByte4[1] = 112;
    TAG_LIFESTYLE_STATE = ByteArray.wrap(arrayOfByte4);
    byte[] arrayOfByte5 = new byte[2];
    arrayOfByte5[0] = -65;
    arrayOfByte5[1] = 12;
    TAG_BANK_CUSTOM_DATA = ByteArray.wrap(arrayOfByte5);
    sSupportedTransCards = new ArrayList();
    sSupportedTransCardAids = new ArrayList();
    sSupportedTransCardMap = new HashMap();
    sSupportedTransCards.add("004");
    sSupportedTransCards.add("002");
    sSupportedTransCards.add("001");
    sSupportedTransCards.add("013");
    sSupportedTransCards.add("019");
    sSupportedTransCards.add("005");
    sSupportedTransCards.add("020");
    sSupportedTransCardAids.add(APDUConstants.AID_SZT);
    sSupportedTransCardAids.add(APDUConstants.AID_SPTC);
    sSupportedTransCardAids.add(APDUConstants.AID_BMAC);
    sSupportedTransCardAids.add(APDUConstants.AID_LNT);
    sSupportedTransCardAids.add(APDUConstants.AID_SUZHOUTONG);
    sSupportedTransCardAids.add(APDUConstants.AID_WHT);
    sSupportedTransCardAids.add(APDUConstants.AID_HZT);
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_SZT.toBytes()), new Pair("004", "SZT"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_SPTC.toBytes()), new Pair("002", "SPTC"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_BMAC.toBytes()), new Pair("001", "BMAC"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_LNT.toBytes()), new Pair("013", "LNT"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_SUZHOUTONG.toBytes()), new Pair("019", "SUZHOUTONG"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_WHT.toBytes()), new Pair("005", "WHT"));
    sSupportedTransCardMap.put(Coder.bytesToHexString(APDUConstants.AID_HZT.toBytes()), new Pair("020", "HZT"));
    sImei = null;
    sSeid = null;
    sCPLC = null;
    sCIN = null;
    sDefaultBankCardAid = null;
    sDefaultTransCardAid = null;
  }

  public static boolean activateCard(Context paramContext, String paramString)
  {
    int i = 0;
    LogUtils.d("activateCard appAid:" + paramString);
    HashMap localHashMap = new HashMap();
    localHashMap.put("aid", StringUtils.getAidFactor(paramString));
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[i] = "activateCard";
    AnalyticManager.recordEvent("nfc", String.format("operation_%s_launch", arrayOfObject1), localHashMap);
    SPISmartMxTerminal localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
    try
    {
      localSPISmartMxTerminal.connect();
      if (!ByteArray.equals(localSPISmartMxTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK))
      {
        localHashMap.put("fail_reason", "SELECT_CRS");
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = "activateCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject4), localHashMap);
        return i;
      }
      localScResponse = localSPISmartMxTerminal.transmit(buildSetStatusApdu(paramString, true).toBytes());
      if ((ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK)) || (ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OPERATION_FAILED)))
      {
        if (isBankCardAid(paramString))
        {
          sDefaultBankCardAid = paramString;
          PrefUtils.setDefaultCard(paramContext, sDefaultBankCardAid, false);
        }
        while (true)
        {
          LogUtils.d("activateCard appAid:" + paramString + " success");
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = "activateCard";
          AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject3), localHashMap);
          localSPISmartMxTerminal.close();
          i = 1;
          break;
          sDefaultTransCardAid = paramString;
          PrefUtils.setDefaultCard(paramContext, sDefaultTransCardAid, true);
        }
      }
    }
    catch (IOException localIOException)
    {
      ScResponse localScResponse;
      while (true)
      {
        LogUtils.e("failed to active card, aid = " + paramString, localIOException);
        localHashMap.put("fail_reason", "IOException");
        localSPISmartMxTerminal.close();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[i] = "activateCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject2), localHashMap);
      }
      if ("ACTIVATE_FAILED_SW_" + localScResponse.getStatus() == null);
      String str;
      for (Object localObject2 = "null"; ; localObject2 = str)
      {
        localHashMap.put("fail_reason", localObject2);
        localSPISmartMxTerminal.close();
        break;
        str = localScResponse.getStatus().toString();
      }
    }
    finally
    {
      localSPISmartMxTerminal.close();
    }
    throw localObject1;
  }

  private static CommandApdu buildSetStatusApdu(String paramString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (CommandApdu localCommandApdu = APDUConstants.COMM_PREFIX_ACTIVATE_CARD.clone(); ; localCommandApdu = APDUConstants.COMM_PREFIX_DEACTIVATE_CARD.clone())
    {
      byte[] arrayOfByte1 = Coder.hexStringToBytes(paramString);
      byte[] arrayOfByte2 = new byte[2 + arrayOfByte1.length];
      arrayOfByte2[0] = 79;
      arrayOfByte2[1] = (byte)(0xFF & arrayOfByte1.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 2, arrayOfByte1.length);
      localCommandApdu.setData(arrayOfByte2);
      return localCommandApdu;
    }
  }

  public static boolean checkFindMyDeviceStatus(Context paramContext)
  {
    int i = 1;
    FindDeviceStatusManager localFindDeviceStatusManager = FindDeviceStatusManager.obtain(paramContext);
    try
    {
      if (!localFindDeviceStatusManager.getFindDeviceInfo().isOpen)
      {
        localFindDeviceStatusManager.openSilently();
        boolean bool = localFindDeviceStatusManager.getFindDeviceInfo().isOpen;
        if (!bool);
      }
      else
      {
        return i;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        LogUtils.e("checkFindMyDeviceStatus failed", localInterruptedException);
        Thread.currentThread().interrupt();
        i = 0;
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        LogUtils.e("checkFindMyDeviceStatus failed", localRemoteException);
    }
    catch (FindDeviceStatusManager.FindDeviceStatusManagerException localFindDeviceStatusManagerException)
    {
      while (true)
        LogUtils.e("checkFindMyDeviceStatus failed", localFindDeviceStatusManagerException);
    }
  }

  public static boolean deactivateCard(Context paramContext, String paramString)
  {
    int i = 0;
    HashMap localHashMap = new HashMap();
    localHashMap.put("aid", StringUtils.getAidFactor(paramString));
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[i] = "deactivateCard";
    AnalyticManager.recordEvent("nfc", String.format("operation_%s_launch", arrayOfObject1), localHashMap);
    SPISmartMxTerminal localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
    try
    {
      localSPISmartMxTerminal.connect();
      if (!ByteArray.equals(localSPISmartMxTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK))
      {
        localHashMap.put("fail_reason", "SELECT_CRS");
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = "deactivateCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject4), localHashMap);
      }
      ScResponse localScResponse;
      while (true)
      {
        return i;
        localScResponse = localSPISmartMxTerminal.transmit(buildSetStatusApdu(paramString, false).toBytes());
        if ((!ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK)) && (!ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OPERATION_FAILED)))
          break;
        LogUtils.d("deactivateCard appAid:" + paramString + " success");
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = "deactivateCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject3), localHashMap);
        localSPISmartMxTerminal.close();
        i = 1;
      }
      if ("DEACTIVATE_FAILED_SW_" + localScResponse.getStatus() == null);
      String str;
      for (Object localObject2 = "null"; ; localObject2 = str)
      {
        localHashMap.put("fail_reason", localObject2);
        localSPISmartMxTerminal.close();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[i] = "deactivateCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject2), localHashMap);
        break;
        str = localScResponse.getStatus().toString();
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        LogUtils.e("failed to deactivate card, aid = " + paramString, localIOException);
        localHashMap.put("fail_reason", "IOException");
        localSPISmartMxTerminal.close();
      }
    }
    finally
    {
      localSPISmartMxTerminal.close();
    }
    throw localObject1;
  }

  public static Address getAddressByLocation(Context paramContext)
  {
    Location localLocation = getLocation(paramContext);
    Address localAddress;
    if (localLocation == null)
    {
      LogUtils.d("get location failed!");
      localAddress = null;
    }
    while (true)
    {
      return localAddress;
      Geocoder localGeocoder = new Geocoder(paramContext.getApplicationContext());
      Object localObject = null;
      try
      {
        List localList = localGeocoder.getFromLocation(localLocation.getLatitude(), localLocation.getLongitude(), 1);
        localObject = localList;
        if ((localObject != null) && (!localObject.isEmpty()))
          localAddress = (Address)localObject.get(0);
      }
      catch (IOException localIOException)
      {
        while (true)
          LogUtils.e(localIOException.getMessage(), localIOException);
        localAddress = null;
      }
    }
  }

  public static PackageInfo getAppInfo(Context paramContext, String paramString)
  {
    PackageInfo localPackageInfo;
    if (TextUtils.isEmpty(paramString))
      localPackageInfo = null;
    while (true)
    {
      return localPackageInfo;
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (true)
        if (localIterator.hasNext())
        {
          localPackageInfo = (PackageInfo)localIterator.next();
          if (!TextUtils.equals(paramString, localPackageInfo.packageName))
            continue;
          break;
        }
      localPackageInfo = null;
    }
  }

  public static int getBankCardCounts(Context paramContext)
    throws InvalidTLVException, IOException, TagNotFoundException
  {
    Set localSet = getSeBankCards(paramContext);
    if (localSet == null);
    for (int i = 0; ; i = localSet.size())
      return i;
  }

  public static String getCIN(Context paramContext)
    throws IOException
  {
    sCIN = PrefUtils.getString(paramContext, "key_cin", null);
    if (sCIN == null)
      getSEInfo(paramContext);
    return sCIN;
  }

  public static String getCPLC(Context paramContext)
    throws IOException
  {
    sCPLC = PrefUtils.getString(paramContext, "key_cplc", null);
    if (sCPLC == null)
      getSEInfo(paramContext);
    return sCPLC;
  }

  public static Map<String, ByteArray> getCardActivationState(Context paramContext, String paramString)
  {
    SPISmartMxTerminal localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
    HashMap localHashMap = new HashMap();
    try
    {
      localSPISmartMxTerminal.connect();
      if (!ByteArray.equals(localSPISmartMxTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK))
        throw new IOException("failed to select CRS");
    }
    catch (InvalidTLVException localInvalidTLVException)
    {
      LogUtils.e("getCardActivationState error", localInvalidTLVException);
      IOUtils.closeQuietly(localSPISmartMxTerminal);
      localHashMap = null;
      CommandApdu localCommandApdu;
      while (true)
      {
        return localHashMap;
        byte[] arrayOfByte1 = Coder.hexStringToBytes(paramString);
        byte[] arrayOfByte2 = new byte[5 + (2 + arrayOfByte1.length)];
        arrayOfByte2[0] = 79;
        arrayOfByte2[1] = (byte)(0xFF & arrayOfByte1.length);
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 2, arrayOfByte1.length);
        System.arraycopy(APDUConstants.COMM_TAG_GET_STATUS.toBytes(), 0, arrayOfByte2, 2 + arrayOfByte1.length, APDUConstants.COMM_TAG_GET_STATUS.toBytes().length);
        localCommandApdu = APDUConstants.COMM_PREFIX_GET_STATUS.clone();
        localCommandApdu.setData(arrayOfByte2);
        localScResponse = localSPISmartMxTerminal.transmit(localCommandApdu.toBytes());
        boolean bool1 = ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_REFERENCE_NOT_FOUND);
        if (!bool1)
          break;
        IOUtils.closeQuietly(localSPISmartMxTerminal);
        continue;
        localScResponse = localSPISmartMxTerminal.transmit(localCommandApdu.toBytes());
      }
      if ((ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_MORE_DATA_AVAILABLE)) || (ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK)))
      {
        if (localCommandApdu.getP2() != 1)
          localCommandApdu.setP2(1);
        Iterator localIterator = TLVParser.parseTLVValue(localScResponse.getData()).findTLVList(TAG_AEF_ENTRANCE).iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label439;
          ITLVObject localITLVObject = (ITLVObject)localIterator.next();
          str = Coder.bytesToHexString(localITLVObject.getValue().findTLV(TAG_AID).getValue().toBytes().toBytes());
          ByteArray localByteArray = localITLVObject.getValue().findTLV(TAG_LIFESTYLE_STATE).getValue().toBytes();
          if (localByteArray.get(1) == 1)
          {
            if (!isBankCardAid(str))
              break;
            sDefaultBankCardAid = str;
          }
          localHashMap.put(str, localByteArray);
        }
      }
    }
    catch (TagNotFoundException localTagNotFoundException)
    {
      while (true)
      {
        String str;
        LogUtils.e("getCardActivationState error", localTagNotFoundException);
        IOUtils.closeQuietly(localSPISmartMxTerminal);
        continue;
        sDefaultTransCardAid = str;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        ScResponse localScResponse;
        LogUtils.e("getCardActivationState error", localIOException);
        IOUtils.closeQuietly(localSPISmartMxTerminal);
        continue;
        label439: boolean bool2 = ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK);
        if (!bool2)
          continue;
        IOUtils.closeQuietly(localSPISmartMxTerminal);
        continue;
        IOUtils.closeQuietly(localSPISmartMxTerminal);
      }
    }
    finally
    {
      IOUtils.closeQuietly(localSPISmartMxTerminal);
    }
    throw localObject;
  }

  public static String getDefaultBankCard(Context paramContext)
  {
    if (TextUtils.isEmpty(sDefaultBankCardAid))
    {
      sDefaultBankCardAid = PrefUtils.getDefaultCard(paramContext, false);
      if (sDefaultBankCardAid == null)
      {
        getCardActivationState(paramContext, Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX));
        PrefUtils.setDefaultCard(paramContext, sDefaultBankCardAid, false);
      }
    }
    return sDefaultBankCardAid;
  }

  public static String getDefaultTransCard(Context paramContext)
  {
    String str;
    if (!TextUtils.isEmpty(sDefaultTransCardAid))
    {
      PrefUtils.putSecureSettings(paramContext, "key_trans_card_in_ese", 1);
      str = sDefaultTransCardAid;
    }
    while (true)
    {
      return str;
      sDefaultTransCardAid = PrefUtils.getDefaultCard(paramContext, true);
      if (sDefaultTransCardAid == null)
      {
        if (PrefUtils.getBoolean(paramContext, "transcard_checked", false))
        {
          str = sDefaultTransCardAid;
          continue;
        }
        Iterator localIterator = sSupportedTransCardAids.iterator();
        while (localIterator.hasNext())
        {
          getCardActivationState(paramContext, Coder.bytesToHexString(((ByteArray)localIterator.next()).toBytes()));
          if (TextUtils.isEmpty(sDefaultTransCardAid))
            continue;
          PrefUtils.setDefaultCard(paramContext, sDefaultTransCardAid, true);
        }
        PrefUtils.putBoolean(paramContext, "transcard_checked", true);
      }
      if (!TextUtils.isEmpty(sDefaultTransCardAid))
        PrefUtils.putSecureSettings(paramContext, "key_trans_card_in_ese", 1);
      str = sDefaultTransCardAid;
    }
  }

  public static String getDeviceLanguage6393()
  {
    return Locale.getDefault().getISO3Language();
  }

  public static String getDeviceMainArea(Context paramContext)
  {
    return getAddressByLocation(paramContext).getAdminArea();
  }

  public static String getDeviceName()
  {
    return android.os.Build.MODEL;
  }

  public static String getImei(Context paramContext)
  {
    if (TextUtils.isEmpty(sImei));
    try
    {
      sImei = ((android.telephony.TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      label25: return sImei;
    }
    catch (Exception localException)
    {
      break label25;
    }
  }

  public static Location getLocation(Context paramContext)
  {
    LocationManager localLocationManager = (LocationManager)paramContext.getApplicationContext().getSystemService("location");
    Location localLocation = localLocationManager.getLastKnownLocation("network");
    CountDownLatch localCountDownLatch;
    1 local1;
    if (localLocation == null)
    {
      LogUtils.d("request location update now!");
      localCountDownLatch = new CountDownLatch(1);
      local1 = new LocationListener(localCountDownLatch)
      {
        public void onLocationChanged(Location paramLocation)
        {
          LogUtils.d("onLocationChanged");
          SysUtils.this.countDown();
        }

        public void onProviderDisabled(String paramString)
        {
          LogUtils.d("onProviderDisabled");
          SysUtils.this.countDown();
        }

        public void onProviderEnabled(String paramString)
        {
          LogUtils.d("onProviderEnabled");
          SysUtils.this.countDown();
        }

        public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
        {
          LogUtils.d("onStatusChanged");
          SysUtils.this.countDown();
        }
      };
      localLocationManager.requestLocationUpdates("network", 0L, 0.0F, local1, Looper.getMainLooper());
    }
    try
    {
      localCountDownLatch.await(10000L, TimeUnit.MILLISECONDS);
      localLocation = localLocationManager.getLastKnownLocation("network");
      localLocationManager.removeUpdates(local1);
      return localLocation;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        Thread.currentThread().interrupt();
    }
  }

  public static String getMIUIRomType()
  {
    String str;
    if (miui.os.Build.IS_ALPHA_BUILD)
      str = "ALPHA";
    while (true)
    {
      return str;
      if (miui.os.Build.IS_DEVELOPMENT_VERSION)
      {
        str = "DEV";
        continue;
      }
      if (miui.os.Build.IS_STABLE_VERSION)
      {
        str = "STABLE";
        continue;
      }
      str = "OTHER";
    }
  }

  private static void getSEInfo(Context paramContext)
    throws IOException
  {
    I2CSmartMxTerminal localI2CSmartMxTerminal = new I2CSmartMxTerminal(paramContext);
    try
    {
      localI2CSmartMxTerminal.connect();
      readSEInfo(paramContext, localI2CSmartMxTerminal);
      try
      {
        localI2CSmartMxTerminal.close();
        return;
      }
      catch (Exception localException3)
      {
        while (true)
          LogUtils.e("getSeid failed when close terminal!", localException3);
      }
    }
    catch (NfcEeIOException localNfcEeIOException)
    {
      while (localNfcEeIOException.getErrorCode() == 5)
      {
        readSEInfo(paramContext, localI2CSmartMxTerminal);
        try
        {
          localI2CSmartMxTerminal.close();
        }
        catch (Exception localException2)
        {
          LogUtils.e("getSeid failed when close terminal!", localException2);
        }
      }
      throw localNfcEeIOException;
    }
    finally
    {
    }
    try
    {
      localI2CSmartMxTerminal.close();
      throw localObject;
    }
    catch (Exception localException1)
    {
      while (true)
        LogUtils.e("getSeid failed when close terminal!", localException1);
    }
  }

  public static List<String> getSIMNumber()
  {
    LinkedList localLinkedList = new LinkedList();
    miui.telephony.TelephonyManager localTelephonyManager = miui.telephony.TelephonyManager.getDefault();
    int i = 0;
    if (i < localTelephonyManager.getPhoneCount())
    {
      if (localTelephonyManager.hasIccCard(i))
      {
        SubscriptionInfo localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(i);
        if (TextUtils.isEmpty(localSubscriptionInfo.getDisplayNumber()))
          break label88;
        localLinkedList.add("1310000" + StringUtils.tail(localSubscriptionInfo.getDisplayNumber(), 4));
      }
      while (true)
      {
        i++;
        break;
        label88: localLinkedList.add("13100000000");
      }
    }
    return localLinkedList;
  }

  public static Set<String> getSeBankCards(Context paramContext)
    throws InvalidTLVException, IOException, TagNotFoundException
  {
    Map localMap = getCardActivationState(paramContext, Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX));
    if (localMap != null);
    for (Set localSet = localMap.keySet(); ; localSet = null)
      return localSet;
  }

  public static String getSeid(Context paramContext)
    throws IOException
  {
    sSeid = PrefUtils.getString(paramContext, "key_seid", null);
    if (sSeid == null)
      getSEInfo(paramContext);
    return sSeid;
  }

  public static Bundle getSignedSpiPK(Context paramContext)
  {
    SPISmartMxTerminal localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
    try
    {
      localSPISmartMxTerminal.connect();
      String str = localSPISmartMxTerminal.getSignedSpiPK();
      Bundle localBundle;
      if (str != null)
      {
        LogUtils.d("signedPK : " + str);
        String[] arrayOfString = str.split("&");
        HashMap localHashMap = new HashMap();
        for (int i = 0; i < arrayOfString.length; i++)
        {
          int j = arrayOfString[i].indexOf('=');
          localHashMap.put(arrayOfString[i].substring(0, j), arrayOfString[i].substring(j + 1, arrayOfString[i].length()));
        }
        localBundle = new Bundle();
        localBundle.putString("cpuModel", (String)localHashMap.get("cpuModel"));
        localBundle.putString("deviceModel", (String)localHashMap.get("deviceModel"));
        localBundle.putString("keyAlg", (String)localHashMap.get("keyAlg"));
        localBundle.putString("pkX", (String)localHashMap.get("pkX"));
        localBundle.putString("pkY", (String)localHashMap.get("pkY"));
        localBundle.putString("tzId", (String)localHashMap.get("tzId"));
        localBundle.putString("sign", (String)localHashMap.get("sign"));
      }
      while (true)
      {
        return localBundle;
        localSPISmartMxTerminal.close();
        localBundle = null;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        LogUtils.e("getSignedSpiPk error", localIOException);
        localSPISmartMxTerminal.close();
      }
    }
    finally
    {
      localSPISmartMxTerminal.close();
    }
    throw localObject;
  }

  public static List<ByteArray> getSupportedTransCardAids()
  {
    return sSupportedTransCardAids;
  }

  public static Map<String, Pair<String, String>> getSupportedTransCardMap()
  {
    return sSupportedTransCardMap;
  }

  public static int getTransCardCounts(Context paramContext)
    throws IOException
  {
    int i = 0;
    Iterator localIterator = sSupportedTransCards.iterator();
    while (localIterator.hasNext())
    {
      if (!SmartCardReader.readCard((String)localIterator.next(), paramContext).getBoolean("success"))
        continue;
      i++;
    }
    return i;
  }

  public static Bundle getTransCardState(Context paramContext, boolean paramBoolean)
    throws IOException, InvalidTLVException, TagNotFoundException
  {
    Bundle localBundle = new Bundle();
    String str1 = getDefaultTransCard(paramContext);
    Pair localPair = null;
    int i = 0;
    int j = 0;
    if (!TextUtils.isEmpty(str1))
    {
      localPair = (Pair)sSupportedTransCardMap.get(str1);
      if (localPair != null)
      {
        j = SmartCardReader.readCard((String)localPair.first, paramContext).getInt("e_balance");
        i = 0 + 1;
      }
    }
    if (PrefUtils.contains(paramContext, "transcard_num"))
    {
      i = PrefUtils.getInt(paramContext, "transcard_num", 0);
      localBundle.putInt("key_card_quantity", i);
      if (localPair != null)
        break label226;
    }
    label226: for (String str2 = null; ; str2 = (String)localPair.second)
    {
      localBundle.putString("key_default_card_type", str2);
      localBundle.putInt("key_default_card_balance", j);
      return localBundle;
      Iterator localIterator = sSupportedTransCards.iterator();
      while (true)
      {
        String str3;
        if (localIterator.hasNext())
        {
          str3 = (String)localIterator.next();
          if ((paramBoolean) || (i <= 1));
        }
        else
        {
          PrefUtils.putInt(paramContext, "transcard_num", i);
          break;
        }
        if (((localPair != null) && (TextUtils.equals((CharSequence)localPair.first, str3))) || (!SmartCardReader.readCard(str3, paramContext).getBoolean("success")))
          continue;
        i++;
      }
    }
  }

  public static boolean isAppletExist(Context paramContext, String paramString)
    throws IOException
  {
    I2CSmartMxTerminal localI2CSmartMxTerminal = new I2CSmartMxTerminal(paramContext);
    CommandApdu localCommandApdu = new CommandApdu(0, 164, 4, 0);
    try
    {
      localI2CSmartMxTerminal.connect();
      localCommandApdu.setData(Coder.hexStringToBytes(paramString));
      boolean bool = ByteArray.equals(localI2CSmartMxTerminal.transmit(localCommandApdu.toBytes()).getStatus(), ScResponse.STATUS_OK);
      return bool;
    }
    finally
    {
      localI2CSmartMxTerminal.close();
    }
    throw localObject;
  }

  public static final boolean isBankCardAid(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.startsWith(Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX))));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isForegroundApp(Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService(ActivityManager.class)).getRunningAppProcesses();
    if (localList != null)
    {
      int j = Process.myPid();
      Iterator localIterator = localList.iterator();
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
      do
      {
        if (!localIterator.hasNext())
          break;
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      }
      while ((localRunningAppProcessInfo.pid != j) || (!TextUtils.equals(localRunningAppProcessInfo.processName, paramContext.getPackageName())) || (localRunningAppProcessInfo.importance != 100));
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isNetworkLocationServiceEnabled(Context paramContext)
  {
    return ((LocationManager)paramContext.getApplicationContext().getSystemService("location")).isProviderEnabled("network");
  }

  private static void readSEInfo(Context paramContext, AbstractTerminal paramAbstractTerminal)
    throws IOException
  {
    if (ByteArray.equals(paramAbstractTerminal.transmit(APDUConstants.SELECT_ISD).getStatus(), ScResponse.STATUS_OK))
    {
      ScResponse localScResponse = paramAbstractTerminal.transmit(APDUConstants.GET_SEID);
      if (ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK))
      {
        byte[] arrayOfByte1 = localScResponse.getData().toBytes();
        if ((arrayOfByte1 == null) || (arrayOfByte1.length < 45))
          throw new IOException("data too small returned from se when getting cplc");
        int i = 0xFF & arrayOfByte1[2];
        byte[] arrayOfByte2 = new byte[i];
        System.arraycopy(arrayOfByte1, 3, arrayOfByte2, 0, i);
        sCPLC = Coder.bytesToHexString(arrayOfByte2);
        PrefUtils.putString(paramContext, "key_cplc", sCPLC);
        sSeid = Coder.encodeMD5(arrayOfByte2) + sCPLC.substring(24, 36);
        PrefUtils.putString(paramContext, "key_seid", sSeid);
        byte[] arrayOfByte3 = new byte[10];
        System.arraycopy(arrayOfByte2, i - 4, arrayOfByte3, 0, 4);
        System.arraycopy(arrayOfByte1, 15, arrayOfByte3, 4, 6);
        sCIN = Coder.bytesToHexString(arrayOfByte3);
        PrefUtils.putString(paramContext, "key_cin", sCIN);
      }
    }
  }

  public static boolean setDefaultCard(Context paramContext, String paramString)
  {
    int i = 0;
    if (TextUtils.isEmpty(paramString))
      LogUtils.w("Aid of default card must not be empty");
    while (true)
    {
      return i;
      if (paramString.contains(Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX)));
      for (String str1 = getDefaultBankCard(paramContext); ; str1 = getDefaultTransCard(paramContext))
      {
        if (!paramString.equals(str1))
          break label56;
        i = 1;
        break;
      }
      label56: HashMap localHashMap = new HashMap();
      localHashMap.put("aid", StringUtils.getAidFactor(paramString));
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[i] = "setDefaultCard";
      AnalyticManager.recordEvent("nfc", String.format("operation_%s_launch", arrayOfObject1), localHashMap);
      int j = NfcAdapter.getDefaultAdapter(paramContext).setConfig(paramString);
      if (j != 0)
      {
        localHashMap.put("fail_reason", "SET_CONFIG_" + j);
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[i] = "setDefaultCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject5), localHashMap);
        continue;
      }
      SPISmartMxTerminal localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
      try
      {
        localSPISmartMxTerminal.connect();
        if (!ByteArray.equals(localSPISmartMxTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK))
        {
          localHashMap.put("fail_reason", "SELECT_CRS");
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = "setDefaultCard";
          AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject4), localHashMap);
          localSPISmartMxTerminal.close();
          continue;
        }
        if (!TextUtils.isEmpty(str1))
          localSPISmartMxTerminal.transmit(buildSetStatusApdu(str1, false).toBytes());
        localScResponse = localSPISmartMxTerminal.transmit(buildSetStatusApdu(paramString, true).toBytes());
        if ((ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK)) || (ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OPERATION_FAILED)))
        {
          if (isBankCardAid(paramString))
          {
            sDefaultBankCardAid = paramString;
            PrefUtils.setDefaultCard(paramContext, sDefaultBankCardAid, false);
          }
          while (true)
          {
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = "setDefaultCard";
            AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject3), localHashMap);
            localSPISmartMxTerminal.close();
            i = 1;
            break;
            sDefaultTransCardAid = paramString;
            PrefUtils.setDefaultCard(paramContext, sDefaultTransCardAid, true);
            PrefUtils.putSecureSettings(paramContext, "key_trans_card_in_ese", 1);
          }
        }
      }
      catch (IOException localIOException)
      {
        ScResponse localScResponse;
        LogUtils.e("failed to set default bank card", localIOException);
        localHashMap.put("fail_reason", "IOException");
        localSPISmartMxTerminal.close();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[i] = "setDefaultCard";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject2), localHashMap);
        continue;
        if ("SET_DEFAULT_CARD_FAILED_SW_" + localScResponse.getStatus() == null);
        String str2;
        for (Object localObject2 = "null"; ; localObject2 = str2)
        {
          localHashMap.put("fail_reason", localObject2);
          localSPISmartMxTerminal.close();
          break;
          str2 = localScResponse.getStatus().toString();
        }
      }
      finally
      {
        localSPISmartMxTerminal.close();
      }
    }
    throw localObject1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.SysUtils
 * JD-Core Version:    0.6.0
 */