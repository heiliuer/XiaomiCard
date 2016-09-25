package com.miui.tsmclient.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseConstants
{
  public static final String AUTHORITY = "com.miui.tsmclient.provider";
  public static final String CACHE_KEY_PRODUCT_PREFFIX = "product_";
  public static final Uri CONTENT_URI = Uri.parse("content://com.miui.tsmclient.provider");
  public static final Uri CONTENT_URI_BANK_BIN;
  public static final Uri CONTENT_URI_CACHE;
  public static final String PRODUCT_NAME = "product_name";
  public static final String[] PROJECTION_CACHE;
  public static final String TABLE_BANK_BIN = "bank_bin";
  public static final String TABLE_CACHE = "cache";

  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "key";
    arrayOfString[1] = "value";
    PROJECTION_CACHE = arrayOfString;
    CONTENT_URI_CACHE = Uri.parse(CONTENT_URI + "/" + "cache");
    CONTENT_URI_BANK_BIN = Uri.parse(CONTENT_URI + "/" + "bank_bin");
  }

  public static final class BankBinTable
    implements BaseColumns
  {
    public static final String COLUMN_BANK_NAME = "bank_name";
    public static final String COLUMN_BIN_CODE = "bin_code";
    public static final String COLUMN_CARD_TYPE = "card_type";
  }

  public static final class CacheTable
    implements BaseColumns
  {
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_VALUE = "value";
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.database.DatabaseConstants
 * JD-Core Version:    0.6.0
 */