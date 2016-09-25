package com.miui.tsmclient.task;

import android.content.Context;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import java.util.List;

public class GetCardsFromDbTask extends BaseRxTask<Result>
{
  public GetCardsFromDbTask(Context paramContext)
  {
    super(paramContext, Result.class);
  }

  protected void doLoad(Result paramResult)
  {
    List localList1 = CardDataUtil.loadCardList(getContext(), null);
    List localList2 = CardDataUtil.loadBankCardList(getContext());
    paramResult.mTransCards = localList1;
    paramResult.mBankCards = localList2;
  }

  public static class Result
  {
    public List<BankCardInfo> mBankCards;
    public List<CardInfo> mTransCards;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.GetCardsFromDbTask
 * JD-Core Version:    0.6.0
 */