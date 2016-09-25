package com.miui.tsmclient.entity;

import org.json.JSONObject;

public abstract interface ObjectParser<T>
{
  public abstract T parse(JSONObject paramJSONObject);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.ObjectParser
 * JD-Core Version:    0.6.0
 */