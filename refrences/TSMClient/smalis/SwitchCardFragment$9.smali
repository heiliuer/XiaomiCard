.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showQuickStartDialog(Landroid/view/View;)V
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
    .line 769
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 5
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    .line 773
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/tsmclient/util/PrefUtils;->setLongPressVolumeDownStateToPay(Landroid/content/Context;)V

    .line 774
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    const v1, 0x7f09011c

    invoke-static {v0, v1}, Lcom/miui/tsmclient/util/UiUtils;->showToast(Landroid/content/Context;I)V

    .line 775
    return-void
.end method
