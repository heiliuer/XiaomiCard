.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$2;
.super Landroid/content/BroadcastReceiver;
.source "SwitchCardFragment.java"


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
    .line 145
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$2;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .registers 4
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "intent"    # Landroid/content/Intent;

    .prologue
    .line 148
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$2;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->handleBroadCast(Landroid/content/Intent;)V
    invoke-static {v0, p2}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$000(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Landroid/content/Intent;)V

    .line 149
    return-void
.end method
