.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showOpenCardDialog(Landroid/view/View;)V
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
    .line 714
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    .line 717
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Z

    move-result v0

    if-eqz v0, :cond_17

    .line 718
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->openTransCard()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1300(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    .line 722
    :goto_d
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    .line 723
    return-void

    .line 720
    :cond_17
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->openBankCard()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$1400(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    goto :goto_d
.end method
