package com.miui.tsmclient.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.ITLVValue;
import com.tsmclient.smartcard.tlv.TLVParser;

public class NfcUtils
{
  public static boolean disableAndroidBeam(Context paramContext)
  {
    boolean bool = false;
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(paramContext);
    if (localNfcAdapter != null)
      bool = localNfcAdapter.disableNdefPush();
    return bool;
  }

  // ERROR //
  public static String getBankCardNum(android.nfc.tech.IsoDep paramIsoDep)
    throws com.tsmclient.smartcard.exception.UnProcessableCardException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 5
    //   7: aload 5
    //   9: areturn
    //   10: aload_0
    //   11: invokevirtual 35	android/nfc/tech/IsoDep:connect	()V
    //   14: aload_0
    //   15: new 37	com/tsmclient/smartcard/terminal/CommandApdu
    //   18: dup
    //   19: iconst_0
    //   20: sipush 178
    //   23: iconst_1
    //   24: bipush 12
    //   26: invokespecial 40	com/tsmclient/smartcard/terminal/CommandApdu:<init>	(IIII)V
    //   29: invokevirtual 44	com/tsmclient/smartcard/terminal/CommandApdu:toBytes	()[B
    //   32: invokevirtual 48	android/nfc/tech/IsoDep:transceive	([B)[B
    //   35: pop
    //   36: aload_0
    //   37: getstatic 54	com/tsmclient/smartcard/terminal/APDUConstants:SELECT_PPSE	[B
    //   40: invokevirtual 48	android/nfc/tech/IsoDep:transceive	([B)[B
    //   43: astore 7
    //   45: aload 7
    //   47: ifnull +10 -> 57
    //   50: aload 7
    //   52: arraylength
    //   53: iconst_2
    //   54: if_icmpge +29 -> 83
    //   57: new 26	java/io/IOException
    //   60: dup
    //   61: ldc 56
    //   63: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   66: athrow
    //   67: astore_3
    //   68: ldc 61
    //   70: invokestatic 66	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;)V
    //   73: aload_0
    //   74: invokevirtual 69	android/nfc/tech/IsoDep:close	()V
    //   77: aconst_null
    //   78: astore 5
    //   80: goto -73 -> 7
    //   83: getstatic 73	com/tsmclient/smartcard/terminal/APDUConstants:NOT_EXISTS	Lcom/tsmclient/smartcard/ByteArray;
    //   86: aload 7
    //   88: invokestatic 79	com/tsmclient/smartcard/ByteArray:wrap	([B)Lcom/tsmclient/smartcard/ByteArray;
    //   91: invokestatic 83	com/tsmclient/smartcard/ByteArray:equals	(Lcom/tsmclient/smartcard/ByteArray;Lcom/tsmclient/smartcard/ByteArray;)Z
    //   94: ifeq +20 -> 114
    //   97: new 24	com/tsmclient/smartcard/exception/UnProcessableCardException
    //   100: dup
    //   101: ldc 85
    //   103: invokespecial 86	com/tsmclient/smartcard/exception/UnProcessableCardException:<init>	(Ljava/lang/String;)V
    //   106: athrow
    //   107: astore_1
    //   108: aload_0
    //   109: invokevirtual 69	android/nfc/tech/IsoDep:close	()V
    //   112: aload_1
    //   113: athrow
    //   114: aconst_null
    //   115: astore 8
    //   117: aconst_null
    //   118: astore 9
    //   120: aload 7
    //   122: iconst_0
    //   123: bipush 254
    //   125: aload 7
    //   127: arraylength
    //   128: iadd
    //   129: invokestatic 89	com/tsmclient/smartcard/ByteArray:wrap	([BII)Lcom/tsmclient/smartcard/ByteArray;
    //   132: invokestatic 95	com/tsmclient/smartcard/tlv/TLVParser:parse	(Lcom/tsmclient/smartcard/ByteArray;)Lcom/tsmclient/smartcard/tlv/ITLVObject;
    //   135: invokeinterface 101 1 0
    //   140: getstatic 104	com/tsmclient/smartcard/terminal/APDUConstants:TAG_FCI_DATA_TEMPLATE	Lcom/tsmclient/smartcard/ByteArray;
    //   143: invokeinterface 109 2 0
    //   148: invokeinterface 101 1 0
    //   153: getstatic 112	com/tsmclient/smartcard/terminal/APDUConstants:TAG_BANK_CUSTOM_DATA	Lcom/tsmclient/smartcard/ByteArray;
    //   156: invokeinterface 109 2 0
    //   161: invokeinterface 101 1 0
    //   166: getstatic 115	com/tsmclient/smartcard/terminal/APDUConstants:TAG_AEF_ENTRANCE	Lcom/tsmclient/smartcard/ByteArray;
    //   169: invokeinterface 109 2 0
    //   174: astore 19
    //   176: aload 19
    //   178: invokeinterface 101 1 0
    //   183: getstatic 118	com/tsmclient/smartcard/terminal/APDUConstants:TAG_AID	Lcom/tsmclient/smartcard/ByteArray;
    //   186: invokeinterface 109 2 0
    //   191: invokeinterface 101 1 0
    //   196: invokeinterface 121 1 0
    //   201: astore 8
    //   203: aload 19
    //   205: invokeinterface 101 1 0
    //   210: getstatic 124	com/tsmclient/smartcard/terminal/APDUConstants:TAG_APP	Lcom/tsmclient/smartcard/ByteArray;
    //   213: invokeinterface 109 2 0
    //   218: invokeinterface 101 1 0
    //   223: invokeinterface 121 1 0
    //   228: astore 20
    //   230: aload 20
    //   232: astore 9
    //   234: aload 8
    //   236: ifnull +8 -> 244
    //   239: aload 9
    //   241: ifnonnull +56 -> 297
    //   244: new 26	java/io/IOException
    //   247: dup
    //   248: ldc 126
    //   250: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   253: athrow
    //   254: astore 18
    //   256: new 128	java/lang/StringBuilder
    //   259: dup
    //   260: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   263: ldc 131
    //   265: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: aload 7
    //   270: invokestatic 141	com/tsmclient/smartcard/Coder:bytesToHexString	([B)Ljava/lang/String;
    //   273: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   279: invokestatic 66	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;)V
    //   282: goto -48 -> 234
    //   285: astore 10
    //   287: ldc 147
    //   289: aload 10
    //   291: invokestatic 150	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   294: goto -60 -> 234
    //   297: aload_0
    //   298: new 37	com/tsmclient/smartcard/terminal/CommandApdu
    //   301: dup
    //   302: iconst_0
    //   303: sipush 164
    //   306: iconst_4
    //   307: iconst_0
    //   308: aload 8
    //   310: invokevirtual 151	com/tsmclient/smartcard/ByteArray:toBytes	()[B
    //   313: invokespecial 154	com/tsmclient/smartcard/terminal/CommandApdu:<init>	(IIII[B)V
    //   316: invokevirtual 44	com/tsmclient/smartcard/terminal/CommandApdu:toBytes	()[B
    //   319: invokevirtual 48	android/nfc/tech/IsoDep:transceive	([B)[B
    //   322: astore 11
    //   324: aload 11
    //   326: ifnull +10 -> 336
    //   329: aload 11
    //   331: arraylength
    //   332: iconst_2
    //   333: if_icmpge +13 -> 346
    //   336: new 26	java/io/IOException
    //   339: dup
    //   340: ldc 156
    //   342: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   345: athrow
    //   346: aconst_null
    //   347: astore 12
    //   349: aload 11
    //   351: iconst_0
    //   352: bipush 254
    //   354: aload 11
    //   356: arraylength
    //   357: iadd
    //   358: invokestatic 89	com/tsmclient/smartcard/ByteArray:wrap	([BII)Lcom/tsmclient/smartcard/ByteArray;
    //   361: invokestatic 95	com/tsmclient/smartcard/tlv/TLVParser:parse	(Lcom/tsmclient/smartcard/ByteArray;)Lcom/tsmclient/smartcard/tlv/ITLVObject;
    //   364: invokeinterface 101 1 0
    //   369: getstatic 104	com/tsmclient/smartcard/terminal/APDUConstants:TAG_FCI_DATA_TEMPLATE	Lcom/tsmclient/smartcard/ByteArray;
    //   372: invokeinterface 109 2 0
    //   377: invokeinterface 101 1 0
    //   382: getstatic 159	com/tsmclient/smartcard/terminal/APDUConstants:TAG_PDOL	Lcom/tsmclient/smartcard/ByteArray;
    //   385: invokeinterface 109 2 0
    //   390: invokeinterface 101 1 0
    //   395: invokeinterface 121 1 0
    //   400: astore 17
    //   402: aload 17
    //   404: astore 12
    //   406: aload 12
    //   408: ifnonnull +56 -> 464
    //   411: new 26	java/io/IOException
    //   414: dup
    //   415: ldc 161
    //   417: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   420: athrow
    //   421: astore 16
    //   423: new 128	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   430: ldc 163
    //   432: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: aload 11
    //   437: invokestatic 141	com/tsmclient/smartcard/Coder:bytesToHexString	([B)Ljava/lang/String;
    //   440: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   446: invokestatic 66	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;)V
    //   449: goto -43 -> 406
    //   452: astore 13
    //   454: ldc 165
    //   456: aload 13
    //   458: invokestatic 150	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   461: goto -55 -> 406
    //   464: aload_0
    //   465: getstatic 169	com/tsmclient/smartcard/terminal/APDUConstants:COMM_PREFIX_READ_RECORD	Lcom/tsmclient/smartcard/terminal/CommandApdu;
    //   468: invokevirtual 44	com/tsmclient/smartcard/terminal/CommandApdu:toBytes	()[B
    //   471: invokevirtual 48	android/nfc/tech/IsoDep:transceive	([B)[B
    //   474: astore 14
    //   476: aload 14
    //   478: ifnull +10 -> 488
    //   481: aload 14
    //   483: arraylength
    //   484: iconst_2
    //   485: if_icmpge +13 -> 498
    //   488: new 26	java/io/IOException
    //   491: dup
    //   492: ldc 171
    //   494: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   497: athrow
    //   498: aload 14
    //   500: invokestatic 174	com/miui/tsmclient/util/NfcUtils:getCardNumString	([B)Ljava/lang/String;
    //   503: astore 5
    //   505: aload 5
    //   507: ifnonnull +13 -> 520
    //   510: new 26	java/io/IOException
    //   513: dup
    //   514: ldc 176
    //   516: invokespecial 59	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   519: athrow
    //   520: aload_0
    //   521: invokevirtual 69	android/nfc/tech/IsoDep:close	()V
    //   524: goto -517 -> 7
    //   527: astore 15
    //   529: goto -522 -> 7
    //   532: astore 4
    //   534: goto -457 -> 77
    //   537: astore_2
    //   538: goto -426 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   10	67	67	java/io/IOException
    //   83	107	67	java/io/IOException
    //   120	230	67	java/io/IOException
    //   244	346	67	java/io/IOException
    //   349	402	67	java/io/IOException
    //   411	520	67	java/io/IOException
    //   10	67	107	finally
    //   68	73	107	finally
    //   83	107	107	finally
    //   120	230	107	finally
    //   244	346	107	finally
    //   349	402	107	finally
    //   411	520	107	finally
    //   120	230	254	com/tsmclient/smartcard/exception/InvalidTLVException
    //   120	230	285	com/tsmclient/smartcard/exception/TagNotFoundException
    //   349	402	421	com/tsmclient/smartcard/exception/InvalidTLVException
    //   349	402	452	com/tsmclient/smartcard/exception/TagNotFoundException
    //   520	524	527	java/io/IOException
    //   73	77	532	java/io/IOException
    //   108	112	537	java/io/IOException
  }

  private static String getCardNumString(byte[] paramArrayOfByte)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    Object localObject = null;
    try
    {
      ByteArray localByteArray = TLVParser.parse(ByteArray.wrap(paramArrayOfByte, 0, -2 + paramArrayOfByte.length)).getValue().findTLV(APDUConstants.TAG_CARD_NUM).getValue().toBytes();
      int i = 0;
      while ((0xD0 ^ 0xF0 & localByteArray.get(i)) != 0)
      {
        i++;
        if ((0xD ^ 0xF & localByteArray.get(i)) != 0)
          continue;
        i++;
        localBoolean = Boolean.valueOf(true);
      }
      localObject = Coder.bytesToHexString(ByteArray.wrap(localByteArray.toBytes(), 0, i).toBytes());
      if (localBoolean.booleanValue())
      {
        String str = ((String)localObject).substring(0, -1 + ((String)localObject).length());
        localObject = str;
      }
      return localObject;
    }
    catch (InvalidTLVException localInvalidTLVException)
    {
      while (true)
        LogUtils.e("Invalid res: " + Coder.bytesToHexString(paramArrayOfByte));
    }
    catch (TagNotFoundException localTagNotFoundException)
    {
      while (true)
        LogUtils.e("Error when parse tlv", localTagNotFoundException);
    }
  }

  public static void startPoll(Activity paramActivity)
  {
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(paramActivity);
    if ((localNfcAdapter != null) && (localNfcAdapter.isEnabled()))
      localNfcAdapter.enableForegroundDispatch(paramActivity, PendingIntent.getActivity(paramActivity, 0, new Intent(paramActivity, paramActivity.getClass()).addFlags(536870912), 268435456), null, (String[][])null);
  }

  public static void stopPoll(Activity paramActivity)
  {
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(paramActivity);
    if (localNfcAdapter != null)
      localNfcAdapter.disableForegroundDispatch(paramActivity);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.NfcUtils
 * JD-Core Version:    0.6.0
 */