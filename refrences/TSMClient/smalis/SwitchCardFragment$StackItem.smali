.class public Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "StackItem"
.end annotation


# instance fields
.field private mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;


# direct methods
.method constructor <init>(Lcom/miui/tsmclient/entity/CardInfo;)V
    .registers 2
    .param p1, "cardInfo"    # Lcom/miui/tsmclient/entity/CardInfo;

    .prologue
    .line 866
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 867
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    .line 868
    return-void
.end method

.method static synthetic access$1100(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;)Lcom/miui/tsmclient/entity/CardInfo;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    .prologue
    .line 863
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    return-object v0
.end method


# virtual methods
.method public getCardInfo()Lcom/miui/tsmclient/entity/CardInfo;
    .registers 2

    .prologue
    .line 871
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    return-object v0
.end method
