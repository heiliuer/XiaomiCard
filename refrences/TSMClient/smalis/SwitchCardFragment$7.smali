.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$7;
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
    .line 725
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$7;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    .line 728
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$7;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    .line 729
    return-void
.end method
