.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->onLayoutChange(Landroid/view/View;IIIIIIII)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;


# direct methods
.method constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;)V
    .registers 2

    .prologue
    .line 440
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .registers 2
    .param p1, "animation"    # Landroid/animation/Animator;

    .prologue
    .line 461
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .registers 8
    .param p1, "animation"    # Landroid/animation/Animator;

    .prologue
    const/4 v5, 0x0

    .line 447
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$hciEventInfo:Lcom/miui/tsmclient/hcievent/HciEventInfo;

    invoke-virtual {v0}, Lcom/miui/tsmclient/hcievent/HciEventInfo;->isTradeSuccess()Z

    move-result v0

    if-eqz v0, :cond_3d

    .line 448
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$hciEventInfo:Lcom/miui/tsmclient/hcievent/HciEventInfo;

    iget-boolean v0, v0, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mIsBankEvent:Z

    if-eqz v0, :cond_3e

    .line 449
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOkImg:Landroid/widget/ImageView;

    invoke-virtual {v0, v5}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 450
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOk:Landroid/widget/TextView;

    const v1, 0x7f090026

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    .line 454
    :goto_24
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOk:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v1, v1, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOk:Landroid/widget/TextView;

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setVisibility(I)V

    .line 455
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1000(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x4

    const-wide/16 v2, 0xbb8

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 457
    :cond_3d
    return-void

    .line 452
    :cond_3e
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v0, v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOk:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v1, v1, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    const v2, 0x7f09011e

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;->this$1:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    iget-object v4, v4, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$hciEventInfo:Lcom/miui/tsmclient/hcievent/HciEventInfo;

    iget v4, v4, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mBalance:I

    invoke-static {v4}, Lcom/miui/tsmclient/util/StringUtils;->formatAmount(I)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v3, v5

    invoke-virtual {v1, v2, v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_24
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .registers 2
    .param p1, "animation"    # Landroid/animation/Animator;

    .prologue
    .line 465
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .registers 2
    .param p1, "animation"    # Landroid/animation/Animator;

    .prologue
    .line 443
    return-void
.end method
