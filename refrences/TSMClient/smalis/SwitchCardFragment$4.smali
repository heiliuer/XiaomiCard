.class Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;
.super Ljava/lang/Object;
.source "SwitchCardFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


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
    .line 178
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 3
    .param p1, "v"    # Landroid/view/View;

    .prologue
    .line 181
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$600(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/widget/ImageView;

    move-result-object v0

    if-ne p1, v0, :cond_d

    .line 182
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;->this$0:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    # invokes: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showGuideDialog()V
    invoke-static {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->access$900(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    .line 184
    :cond_d
    return-void
.end method
