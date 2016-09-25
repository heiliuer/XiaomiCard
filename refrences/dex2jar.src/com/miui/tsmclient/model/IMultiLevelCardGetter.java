package com.miui.tsmclient.model;

import com.miui.tsmclient.entity.IChildCity;
import java.util.List;

public abstract interface IMultiLevelCardGetter<T extends IChildCity>
{
  public abstract String getAId();

  public abstract List<T> getChildCardDescs();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.IMultiLevelCardGetter
 * JD-Core Version:    0.6.0
 */