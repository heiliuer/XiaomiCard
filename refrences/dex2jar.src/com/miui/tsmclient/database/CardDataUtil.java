package com.miui.tsmclient.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardDataUtil
{
  public static final String CARD_INFO_FIELD_BANK_CARD_AID = "bank_card_aid";
  public static final String CARD_INFO_FIELD_BANK_CARD_ART = "bank_card_art";
  public static final String CARD_INFO_FIELD_BANK_CARD_EXPIRE_DATE = "bank_card_expire_date";
  public static final String CARD_INFO_FIELD_BANK_CARD_ISSUER_CHANNEL = "bank_card_issuer_channel";
  public static final String CARD_INFO_FIELD_BANK_CARD_ISSUER_ID = "bank_card_issuer_id";
  public static final String CARD_INFO_FIELD_BANK_CARD_PAN = "bank_card_pan";
  public static final String CARD_INFO_FIELD_BANK_CARD_PAN_LAST_DIGITS = "bank_card_pan_last_digits";
  public static final String CARD_INFO_FIELD_BANK_CARD_PRODUCT_NAME = "bank_card_product_name";
  public static final String CARD_INFO_FIELD_BANK_CARD_PRODUCT_TYPE_ID = "bank_card_product_id";
  public static final String CARD_INFO_FIELD_BANK_CARD_TYPE = "bank_card_type";
  public static final String CARD_INFO_FIELD_BANK_CARD_USERTERMS = "bank_card_user_terms";
  public static final String CARD_INFO_FIELD_BANK_CARD_USERTERMS_OPTION = "bank_card_user_terms_option";
  public static final String CARD_INFO_FIELD_BANK_CARD_VC_NUM = "bank_card_vc_card_num";
  public static final String CARD_INFO_FIELD_BANK_CARD_VC_REFERENCE_ID = "bank_card_vc_reference_id";
  public static final String CARD_INFO_FIELD_BANK_CARD_VC_STATUS = "bank_card_vc_status";
  public static final String CARD_INFO_FIELD_BANK_LOGO_URL = "bank_logo_url";
  public static final String CARD_INFO_FIELD_BANK_LOGO_WITH_NAME_URL = "bank_logo_with_name_url";
  public static final String CARD_INFO_FIELD_BANK_NAME = "bank_name";

  public static List<BankCardInfo> loadBankCardList(Context paramContext)
  {
    ArrayList localArrayList;
    if (paramContext == null)
      localArrayList = null;
    while (true)
    {
      return localArrayList;
      localArrayList = new ArrayList();
      Cursor localCursor = paramContext.getContentResolver().query(DatabaseConstants.CONTENT_URI_CACHE, DatabaseConstants.PROJECTION_CACHE, "key IN ('BANKCARD')", null, null);
      if (localCursor == null)
        continue;
      try
      {
        if (localCursor.moveToNext())
        {
          String str = localCursor.getString(localCursor.getColumnIndex("key"));
          JSONArray localJSONArray = new JSONArray(localCursor.getString(localCursor.getColumnIndex("value")));
          for (int i = 0; i < localJSONArray.length(); i++)
          {
            BankCardInfo localBankCardInfo = (BankCardInfo)CardInfoFactory.makeCardInfo(str, localJSONArray.getJSONObject(i));
            if (localBankCardInfo == null)
              continue;
            localArrayList.add(localBankCardInfo);
          }
        }
        localCursor.close();
        continue;
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("invalid JSON format", localJSONException);
        localCursor.close();
        continue;
      }
      finally
      {
        localCursor.close();
      }
    }
    throw localObject;
  }

  public static List<CardInfo> loadCardList(Context paramContext, List<CardInfo> paramList)
  {
    ArrayList localArrayList;
    if (paramContext == null)
      localArrayList = null;
    while (true)
    {
      return localArrayList;
      if (paramList == null)
        paramList = new ArrayList();
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramList.size();
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append("'").append(((CardInfo)paramList.get(j)).mCardType).append("'");
        if (j >= i - 1)
          continue;
        localStringBuilder.append(",");
      }
      String str1 = null;
      if (!TextUtils.isEmpty(localStringBuilder))
        str1 = "key IN (" + localStringBuilder.toString() + ")";
      Cursor localCursor = paramContext.getContentResolver().query(DatabaseConstants.CONTENT_URI_CACHE, DatabaseConstants.PROJECTION_CACHE, str1, null, null);
      localArrayList = new ArrayList();
      if (localCursor == null)
        continue;
      try
      {
        while (localCursor.moveToNext())
        {
          String str2 = localCursor.getString(localCursor.getColumnIndex("key"));
          if (TextUtils.equals(str2, "BANKCARD"))
            continue;
          JSONObject localJSONObject1 = new JSONObject(localCursor.getString(localCursor.getColumnIndex("value")));
          if (!localJSONObject1.has("cardName"))
            continue;
          CardInfo localCardInfo = CardInfoFactory.makeCardInfo(str2, localJSONObject1);
          JSONObject localJSONObject2 = query(paramContext, "product_" + str2);
          if (localJSONObject2 != null)
          {
            LogUtils.d("product:" + localJSONObject2.toString());
            localCardInfo.mCardSubName = localJSONObject2.optString("product_name");
          }
          if (localCardInfo == null)
            continue;
          localArrayList.add(localCardInfo);
        }
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("invalid jsonobject read from db", localJSONException);
        localCursor.close();
        continue;
        localCursor.close();
        continue;
      }
      finally
      {
        localCursor.close();
      }
    }
    throw localObject;
  }

  public static JSONObject query(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return localObject1;
      String str = "key ='" + paramString + "'";
      Cursor localCursor = paramContext.getContentResolver().query(DatabaseConstants.CONTENT_URI_CACHE, DatabaseConstants.PROJECTION_CACHE, str, null, null);
      if (localCursor == null)
        continue;
      try
      {
        if (localCursor.moveToNext())
        {
          JSONObject localJSONObject = new JSONObject(localCursor.getString(localCursor.getColumnIndex("value")));
          localCursor.close();
          localObject1 = localJSONObject;
          continue;
        }
        localCursor.close();
        continue;
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("invalid jsonobject readed from db", localJSONException);
        localCursor.close();
        continue;
      }
      finally
      {
        localCursor.close();
      }
    }
    throw localObject2;
  }

  public static void save(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    while (true)
    {
      return;
      ContentValues localContentValues = new ContentValues(2);
      localContentValues.put("key", paramString1);
      localContentValues.put("value", paramString2);
      paramContext.getContentResolver().insert(DatabaseConstants.CONTENT_URI_CACHE, localContentValues);
    }
  }

  public static void saveBankCardInfo(Context paramContext, List<BankCardInfo> paramList)
  {
    if (paramContext == null);
    while (true)
    {
      return;
      if ((paramList == null) || (paramList.isEmpty()))
      {
        LogUtils.d("save BankCardInfo to DB, card list got form server is empty");
        PrefUtils.putSecureSettings(paramContext, "key_bank_card_in_ese", 0);
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "BANKCARD";
        int i = paramContext.getContentResolver().delete(DatabaseConstants.CONTENT_URI_CACHE, "key = ?", arrayOfString);
        LogUtils.i("deleted cache count: " + i);
        continue;
      }
      PrefUtils.putSecureSettings(paramContext, "key_bank_card_in_ese", 1);
      ContentValues localContentValues = new ContentValues(2);
      localContentValues.put("key", "BANKCARD");
      localContentValues.put("value", BankCardUtil.serialize(paramList).toString());
      paramContext.getContentResolver().insert(DatabaseConstants.CONTENT_URI_CACHE, localContentValues);
    }
  }

  public static void saveCardInfo(Context paramContext, CardInfo paramCardInfo)
  {
    if ((paramContext == null) || (paramCardInfo == null) || (paramCardInfo.mCardType == null));
    while (true)
    {
      return;
      ContentValues localContentValues = new ContentValues(2);
      localContentValues.put("key", paramCardInfo.mCardType);
      localContentValues.put("value", paramCardInfo.serialize().toString());
      paramContext.getContentResolver().insert(DatabaseConstants.CONTENT_URI_CACHE, localContentValues);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.database.CardDataUtil
 * JD-Core Version:    0.6.0
 */