.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/view/View$OnLayoutChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->onReceiveHciEvent(Lcom/miui/tsmclient/hcievent/HciEventInfo;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

.field final synthetic val$cardContent:Lcom/miui/tsmclient/ui/widget/FlashView;

.field final synthetic val$hciEventInfo:Lcom/miui/tsmclient/hcievent/HciEventInfo;

.field final synthetic val$viewOk:Landroid/widget/TextView;

.field final synthetic val$viewOkImg:Landroid/widget/ImageView;


# direct methods
.method constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/hcievent/HciEventInfo;Landroid/widget/ImageView;Landroid/widget/TextView;Lcom/miui/tsmclient/ui/widget/FlashView;)V
    .registers 6

    .prologue
    .line 436
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    iput-object p2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$hciEventInfo:Lcom/miui/tsmclient/hcievent/HciEventInfo;

    iput-object p3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOkImg:Landroid/widget/ImageView;

    iput-object p4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$viewOk:Landroid/widget/TextView;

    iput-object p5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$cardContent:Lcom/miui/tsmclient/ui/widget/FlashView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onLayoutChange(Landroid/view/View;IIIIIIII)V
    .registers 16
    .param p1, "v"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "top"    # I
    .param p4, "right"    # I
    .param p5, "bottom"    # I
    .param p6, "oldLeft"    # I
    .param p7, "oldTop"    # I
    .param p8, "oldRight"    # I
    .param p9, "oldBottom"    # I

    .prologue
    .line 439
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;
    invoke-static {v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/SlideView;

    move-result-object v3

    int-to-float v4, p5

    invoke-virtual {v3, v4}, Lcom/miui/tsmclient/ui/widget/SlideView;->setDelta(F)V

    .line 440
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;
    invoke-static {v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/SlideView;

    move-result-object v3

    new-instance v4, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;

    invoke-direct {v4, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5$1;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;)V

    invoke-virtual {v3, v4}, Lcom/miui/tsmclient/ui/widget/SlideView;->slideDown(Landroid/animation/Animator$AnimatorListener;)V

    .line 467
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->val$cardContent:Lcom/miui/tsmclient/ui/widget/FlashView;

    invoke-virtual {v3}, Lcom/miui/tsmclient/ui/widget/FlashView;->flash()V

    .line 468
    const/4 v3, 0x2

    invoke-static {v3}, Landroid/media/RingtoneManager;->getDefaultUri(I)Landroid/net/Uri;

    move-result-object v1

    .line 469
    .local v1, "soundUri":Landroid/net/Uri;
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-static {v3, v1}, Landroid/media/RingtoneManager;->getRingtone(Landroid/content/Context;Landroid/net/Uri;)Landroid/media/Ringtone;

    move-result-object v0

    .line 470
    .local v0, "ringtone":Landroid/media/Ringtone;
    invoke-virtual {v0}, Landroid/media/Ringtone;->play()V

    .line 471
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    const-string v4, "vibrator"

    invoke-virtual {v3, v4}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/os/Vibrator;

    .line 472
    .local v2, "vibrator":Landroid/os/Vibrator;
    if-eqz v2, :cond_44

    .line 473
    const-wide/16 v4, 0x1f4

    invoke-virtual {v2, v4, v5}, Landroid/os/Vibrator;->vibrate(J)V

    .line 475
    :cond_44
    return-void
.end method
