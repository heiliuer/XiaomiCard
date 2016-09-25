.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;
.super Ljava/util/ArrayList;
.source "SwitchCardFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/util/ArrayList",
        "<",
        "Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;


# direct methods
.method constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 2

    .prologue
    .line 122
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    return-void
.end method


# virtual methods
.method public add(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;)Z
    .registers 6
    .param p1, "object"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    .prologue
    .line 126
    invoke-virtual {p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->getCardInfo()Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v2

    instance-of v2, v2, Lcom/miui/tsmclient/entity/BankCardInfo;

    if-eqz v2, :cond_3b

    .line 127
    invoke-virtual {p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->getCardInfo()Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/entity/BankCardInfo;

    .line 128
    .local v0, "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "add bankCard to MI Pay list: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, v0, Lcom/miui/tsmclient/entity/BankCardInfo;->mCardName:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ", "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v3, v0, Lcom/miui/tsmclient/entity/BankCardInfo;->mVCStatus:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/miui/tsmclient/util/LogUtils;->i(Ljava/lang/String;)V

    .line 130
    iget v2, v0, Lcom/miui/tsmclient/entity/BankCardInfo;->mVCStatus:I

    if-nez v2, :cond_6e

    .line 131
    invoke-super {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move-result v2

    .line 141
    .end local v0    # "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    :goto_3a
    return v2

    .line 134
    :cond_3b
    invoke-virtual {p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->getCardInfo()Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v1

    .line 135
    .local v1, "cardInfo":Lcom/miui/tsmclient/entity/CardInfo;
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "add card to MI Pay list: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, v1, Lcom/miui/tsmclient/entity/CardInfo;->mCardName:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ", "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, v1, Lcom/miui/tsmclient/entity/CardInfo;->mStatus:Lcom/miui/tsmclient/entity/CardInfo$Status;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/miui/tsmclient/util/LogUtils;->i(Ljava/lang/String;)V

    .line 137
    iget-object v2, v1, Lcom/miui/tsmclient/entity/CardInfo;->mStatus:Lcom/miui/tsmclient/entity/CardInfo$Status;

    sget-object v3, Lcom/miui/tsmclient/entity/CardInfo$Status;->ACTIVE:Lcom/miui/tsmclient/entity/CardInfo$Status;

    if-ne v2, v3, :cond_6e

    .line 138
    invoke-super {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move-result v2

    goto :goto_3a

    .line 141
    .end local v1    # "cardInfo":Lcom/miui/tsmclient/entity/CardInfo;
    :cond_6e
    const/4 v2, 0x0

    goto :goto_3a
.end method

.method public bridge synthetic add(Ljava/lang/Object;)Z
    .registers 3
    .param p1, "x0"    # Ljava/lang/Object;

    .prologue
    .line 122
    check-cast p1, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    .end local p1    # "x0":Ljava/lang/Object;
    invoke-virtual {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;->add(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;)Z

    move-result v0

    return v0
.end method
