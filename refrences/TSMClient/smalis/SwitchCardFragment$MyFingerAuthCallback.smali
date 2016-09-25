.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;
.super Landroid/hardware/fingerprint/FingerprintManager$AuthenticationCallback;
.source "SwitchCardFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "MyFingerAuthCallback"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;


# direct methods
.method private constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 2

    .prologue
    .line 831
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Landroid/hardware/fingerprint/FingerprintManager$AuthenticationCallback;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;)V
    .registers 3
    .param p1, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
    .param p2, "x1"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;

    .prologue
    .line 831
    invoke-direct {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    return-void
.end method


# virtual methods
.method public onAuthenticationError(ILjava/lang/CharSequence;)V
    .registers 5
    .param p1, "errorCode"    # I
    .param p2, "errString"    # Ljava/lang/CharSequence;

    .prologue
    .line 859
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onAuthenticationErro: errorCode = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " ,errString = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->w(Ljava/lang/String;)V

    .line 860
    return-void
.end method

.method public onAuthenticationFailed()V
    .registers 6

    .prologue
    .line 845
    const-string v0, "onAuthenticationSucceeded"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 847
    const-string v0, "pay"

    const-string v1, "operation_%s_failed"

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "FingerprintAuthentication"

    aput-object v4, v2, v3

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 849
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # --operator for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$2106(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)I

    move-result v0

    if-gez v0, :cond_26

    .line 851
    const-string v0, "Fingerprint verification has exceeded the maximum number of times"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->w(Ljava/lang/String;)V

    .line 855
    :goto_25
    return-void

    .line 854
    :cond_26
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    goto :goto_25
.end method

.method public onAuthenticationSucceeded(Landroid/hardware/fingerprint/FingerprintManager$AuthenticationResult;)V
    .registers 8
    .param p1, "result"    # Landroid/hardware/fingerprint/FingerprintManager$AuthenticationResult;

    .prologue
    const/4 v5, 0x1

    .line 834
    const-string v0, "onAuthenticationSucceeded"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 836
    const-string v0, "pay"

    const-string v1, "operation_%s_success"

    new-array v2, v5, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "FingerprintAuthentication"

    aput-object v4, v2, v3

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 838
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    const/16 v1, 0xa

    # setter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I
    invoke-static {v0, v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$2102(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;I)I

    .line 839
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;

    move-result-object v0

    invoke-virtual {v0, v5}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 841
    return-void
.end method
