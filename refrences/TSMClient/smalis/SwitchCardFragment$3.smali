.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Lcom/miui/tsmclient/ui/quick/CardStackAdapter$ICardStackViewChangedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;


# direct methods
.method constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 2

    .prologue
    .line 153
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCardsPoppedUp()V
    .registers 3

    .prologue
    .line 168
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$600(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/widget/ImageView;

    move-result-object v0

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 169
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$400(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Z

    move-result v0

    if-eqz v0, :cond_2b

    .line 170
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$700(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/WaveCircle;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->stopWave()V

    .line 171
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 175
    :cond_2a
    :goto_2a
    return-void

    .line 172
    :cond_2b
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    invoke-static {v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v1

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z
    invoke-static {v0, v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$300(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    if-nez v0, :cond_2a

    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/SlideView;

    move-result-object v0

    if-eqz v0, :cond_2a

    .line 173
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/SlideView;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->stopShading()V

    goto :goto_2a
.end method

.method public onDefaultCardChanged(I)V
    .registers 3
    .param p1, "touchedIndex"    # I

    .prologue
    .line 156
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->updateDefaultCardView(I)V
    invoke-static {v0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$100(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;I)V

    .line 157
    return-void
.end method

.method public onDefaultCardSelected()V
    .registers 3

    .prologue
    .line 161
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v0

    if-eqz v0, :cond_2c

    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    invoke-static {v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v1

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z
    invoke-static {v0, v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$300(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    if-nez v0, :cond_2c

    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$400(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Z

    move-result v0

    if-nez v0, :cond_2c

    .line 162
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 164
    :cond_2c
    return-void
.end method
