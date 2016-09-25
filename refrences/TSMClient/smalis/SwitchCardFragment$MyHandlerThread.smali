.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;
.super Landroid/os/HandlerThread;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/os/Handler$Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "MyHandlerThread"
.end annotation


# static fields
.field public static final MSG_CLOSE_CARD_EMULATION:I = 0x2

.field public static final MSG_FP_AUTH_TIMER:I = 0x3

.field public static final MSG_OEPN_CARD_EMULATION:I = 0x1


# instance fields
.field final synthetic this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;


# direct methods
.method public constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Ljava/lang/String;)V
    .registers 3
    .param p2, "tag"    # Ljava/lang/String;

    .prologue
    .line 803
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .line 804
    invoke-direct {p0, p2}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    .line 805
    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)Z
    .registers 8
    .param p1, "msg"    # Landroid/os/Message;

    .prologue
    const/4 v5, 0x3

    const/4 v4, 0x0

    const/4 v2, 0x1

    .line 809
    iget v0, p1, Landroid/os/Message;->what:I

    packed-switch v0, :pswitch_data_60

    .line 825
    :goto_8
    return v4

    .line 811
    :pswitch_9
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v2, v1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 812
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    invoke-virtual {v0, v5}, Landroid/os/Handler;->removeMessages(I)V

    .line 813
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const-wide/32 v2, 0xea60

    invoke-virtual {v0, v5, v2, v3}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 814
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->enableCardEmulation()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1600(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    goto :goto_8

    .line 817
    :pswitch_35
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1700(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v2, v1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 818
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->disableCardEmulation()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    goto :goto_8

    .line 821
    :pswitch_4c
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->restartListener()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1900(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    .line 822
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    goto :goto_8

    .line 809
    :pswitch_data_60
    .packed-switch 0x1
        :pswitch_9
        :pswitch_35
        :pswitch_4c
    .end packed-switch
.end method
