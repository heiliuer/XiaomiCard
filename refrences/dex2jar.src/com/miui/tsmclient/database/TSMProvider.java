package com.miui.tsmclient.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.miui.tsmclient.util.LogUtils;

public class TSMProvider extends ContentProvider
{
  private static final int CODE_URI_BANK_BIN = 2;
  private static final int CODE_URI_CACHE;
  private static final UriMatcher sUriMatcher = new UriMatcher(-1);
  private TSMDatabaseHelper mDbHelper;

  static
  {
    sUriMatcher.addURI("com.miui.tsmclient.provider", "cache", 0);
    sUriMatcher.addURI("com.miui.tsmclient.provider", "bank_bin", 2);
  }

  private static int findMatch(Uri paramUri, String paramString)
  {
    int i = sUriMatcher.match(paramUri);
    if (i < 0)
      throw new IllegalArgumentException("Unknown uri: " + paramUri);
    LogUtils.v(paramString + ": uri=" + paramUri + ", match is " + i);
    return i;
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = this.mDbHelper.getWritableDatabase();
    int i;
    switch (findMatch(paramUri, "delete"))
    {
    default:
      i = 0;
    case 0:
    }
    while (true)
    {
      return i;
      i = localSQLiteDatabase.delete("cache", paramString, paramArrayOfString);
      if (i <= 0)
        continue;
      getContext().getContentResolver().notifyChange(paramUri, null);
    }
  }

  public String getType(Uri paramUri)
  {
    return null;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    SQLiteDatabase localSQLiteDatabase = this.mDbHelper.getWritableDatabase();
    switch (findMatch(paramUri, "insert"))
    {
    default:
    case 0:
    }
    while (true)
    {
      Object localObject2 = null;
      label35: return localObject2;
      String str1 = paramContentValues.getAsString("key");
      String str2 = "key='" + str1 + "'";
      Cursor localCursor = null;
      try
      {
        localCursor = localSQLiteDatabase.query("cache", null, str2, null, null, null, null);
        if (localCursor.getCount() > 0)
        {
          int i = update(paramUri, paramContentValues, str2, null);
          if (i > 0)
          {
            Uri localUri2 = ContentUris.withAppendedId(paramUri, i);
            localObject2 = localUri2;
            if (localCursor == null)
              break label35;
            localCursor.close();
            break label35;
          }
        }
        else
        {
          long l = localSQLiteDatabase.insert("cache", null, paramContentValues);
          if (l > 0L)
          {
            getContext().getContentResolver().notifyChange(paramUri, null);
            Uri localUri1 = ContentUris.withAppendedId(paramUri, l);
            localObject2 = localUri1;
            if (localCursor == null)
              break label35;
            localCursor.close();
            break label35;
          }
        }
        if (localCursor == null)
          continue;
        localCursor.close();
        continue;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
    throw localObject1;
  }

  public boolean onCreate()
  {
    this.mDbHelper = new TSMDatabaseHelper(getContext());
    return true;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = this.mDbHelper.getReadableDatabase();
    switch (findMatch(paramUri, "query"))
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    while (true)
    {
      return localCursor;
      localCursor = localSQLiteDatabase.query("cache", paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2);
      continue;
      localCursor = localSQLiteDatabase.query("bank_bin", paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2);
    }
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = this.mDbHelper.getWritableDatabase();
    int i;
    switch (findMatch(paramUri, "update"))
    {
    default:
      i = 0;
    case 0:
    }
    while (true)
    {
      return i;
      i = localSQLiteDatabase.update("cache", paramContentValues, paramString, paramArrayOfString);
      if (i <= 0)
        continue;
      getContext().getContentResolver().notifyChange(paramUri, null);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.database.TSMProvider
 * JD-Core Version:    0.6.0
 */