.class public Lcom/miui/tsmclient/ui/BaseCardFragment;
.super Lcom/miui/tsmclient/ui/BaseFragment;
.source "BaseCardFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Lcom/miui/tsmclient/entity/CardInfo;",
        ">",
        "Lcom/miui/tsmclient/ui/BaseFragment;"
    }
.end annotation


# static fields
.field public static final EXTRA_CARD_INFO:Ljava/lang/String; = "card_info"

.field public static final EXTRA_CARD_INFO_LIST:Ljava/lang/String; = "card_info_list"

.field public static final EXTRA_CARD_TYPE:Ljava/lang/String; = "tag_card_type"

.field public static final EXTRA_DEFAULT_CARD_AID:Ljava/lang/String; = "tag_default_card_aid"

.field private static final MSG_CHECK_NFC_EE_STATUS:I = 0x3e8

.field private static final MSG_UN_RESTRICTE_SE:I = 0x3e9


# instance fields
.field protected mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field

.field protected mCardInfoList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<TT;>;"
        }
    .end annotation
.end field

.field private mCardManager:Lcom/miui/tsmclient/model/CardManager;

.field private mCheckNfcEEListener:Lcom/miui/tsmclient/task/TaskListener;

.field private mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

.field protected mDefaultCardAId:Ljava/lang/String;

.field private mFingerprintDialog:Landroid/app/AlertDialog;

.field private mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

.field protected mHandler:Landroid/os/Handler;

.field private mNfcOpenDialog:Landroid/app/AlertDialog;

.field private mTaskManager:Lmiui/util/async/TaskManager;

.field private mUnrestricteSEDialog:Landroid/app/AlertDialog;


# direct methods
.method public constructor <init>()V
    .registers 2

    .prologue
    .line 32
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseFragment;-><init>()V

    .line 67
    new-instance v0, Lcom/miui/tsmclient/ui/BaseCardFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$1;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEListener:Lcom/miui/tsmclient/task/TaskListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/tsmclient/ui/BaseCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/BaseCardFragment;

    .prologue
    .line 32
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->unrestrictEse()V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/tsmclient/ui/BaseCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/BaseCardFragment;

    .prologue
    .line 32
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->startNfcSettings()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/tsmclient/ui/BaseCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/BaseCardFragment;

    .prologue
    .line 32
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->dismissNfcOpenDialog()V

    return-void
.end method

.method private dismissNfcOpenDialog()V
    .registers 2

    .prologue
    .line 358
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    if-eqz v0, :cond_14

    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_14

    .line 359
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->dismiss()V

    .line 360
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    .line 362
    :cond_14
    return-void
.end method

.method private handleNfcEEStatus(I)V
    .registers 4
    .param p1, "nfcEEStatus"    # I

    .prologue
    .line 320
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "checked nfc ee state: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 321
    packed-switch p1, :pswitch_data_30

    .line 336
    :goto_19
    return-void

    .line 323
    :pswitch_1a
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onNFCDisable()V

    goto :goto_19

    .line 326
    :pswitch_1e
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->dismissNfcOpenDialog()V

    .line 327
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onNFCEnable()V

    goto :goto_19

    .line 330
    :pswitch_25
    const v0, 0x7f09005d

    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onNfcEERestricted(I)V

    goto :goto_19

    .line 333
    :pswitch_2c
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onESEDisable()V

    goto :goto_19

    .line 321
    :pswitch_data_30
    .packed-switch 0x0
        :pswitch_1e
        :pswitch_1a
        :pswitch_25
        :pswitch_2c
    .end packed-switch
.end method

.method private onNfcEEUnRestrictedSuccess()V
    .registers 5

    .prologue
    .line 301
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const v2, 0x7f09005e

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x7f09003e

    new-instance v3, Lcom/miui/tsmclient/ui/BaseCardFragment$12;

    invoke-direct {v3, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$12;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$11;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$11;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 316
    .local v0, "dialog":Landroid/app/AlertDialog;
    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 317
    return-void
.end method

.method private onUnRestrictNfcEERejected()V
    .registers 5

    .prologue
    .line 229
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const v2, 0x7f090035

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x7f090036

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x7f09003e

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$5;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$5;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 240
    .local v0, "dialog":Landroid/app/AlertDialog;
    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 241
    return-void
.end method

.method private showFingerprintAlertDialog()V
    .registers 4

    .prologue
    .line 276
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mFingerprintDialog:Landroid/app/AlertDialog;

    if-nez v0, :cond_3e

    .line 277
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const v1, 0x7f090020

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f090021

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setMessage(I)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f090022

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$10;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$10;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f09000f

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$9;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$9;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mFingerprintDialog:Landroid/app/AlertDialog;

    .line 297
    :cond_3e
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mFingerprintDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 298
    return-void
.end method

.method private showNfcOpenDialog(Z)V
    .registers 5
    .param p1, "isNfcEnable"    # Z

    .prologue
    .line 244
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    if-nez v0, :cond_44

    .line 245
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const v1, 0x7f09003a

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    if-eqz p1, :cond_4a

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getRoutingNotESEPrompt()Ljava/lang/String;

    move-result-object v0

    :goto_1a
    invoke-virtual {v1, v0}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f090017

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$8;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$8;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const/high16 v1, 0x1040000

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$7;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$7;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    new-instance v1, Lcom/miui/tsmclient/ui/BaseCardFragment$6;

    invoke-direct {v1, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$6;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    .line 272
    :cond_44
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 273
    return-void

    .line 245
    :cond_4a
    const v0, 0x7f090031

    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_1a
.end method

.method private startNfcSettings()V
    .registers 5

    .prologue
    .line 339
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const/4 v2, 0x0

    .line 340
    .local v2, "success":Z
    new-instance v0, Landroid/content/Intent;

    const-string v3, "android.settings.NFC_SETTINGS"

    invoke-direct {v0, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 342
    .local v0, "intent":Landroid/content/Intent;
    :try_start_8
    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->startActivity(Landroid/content/Intent;)V
    :try_end_b
    .catch Landroid/content/ActivityNotFoundException; {:try_start_8 .. :try_end_b} :catch_1a

    .line 343
    const/4 v2, 0x1

    .line 347
    :goto_c
    if-nez v2, :cond_19

    .line 349
    :try_start_e
    new-instance v1, Landroid/content/Intent;

    const-string v3, "android.settings.NFC_SETTINGS"

    invoke-direct {v1, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V
    :try_end_15
    .catch Landroid/content/ActivityNotFoundException; {:try_start_e .. :try_end_15} :catch_1c

    .line 350
    .end local v0    # "intent":Landroid/content/Intent;
    .local v1, "intent":Landroid/content/Intent;
    :try_start_15
    invoke-virtual {p0, v1}, Lcom/miui/tsmclient/ui/BaseCardFragment;->startActivity(Landroid/content/Intent;)V
    :try_end_18
    .catch Landroid/content/ActivityNotFoundException; {:try_start_15 .. :try_end_18} :catch_1e

    move-object v0, v1

    .line 355
    .end local v1    # "intent":Landroid/content/Intent;
    .restart local v0    # "intent":Landroid/content/Intent;
    :cond_19
    :goto_19
    return-void

    .line 344
    :catch_1a
    move-exception v3

    goto :goto_c

    .line 351
    :catch_1c
    move-exception v3

    goto :goto_19

    .end local v0    # "intent":Landroid/content/Intent;
    .restart local v1    # "intent":Landroid/content/Intent;
    :catch_1e
    move-exception v3

    move-object v0, v1

    .end local v1    # "intent":Landroid/content/Intent;
    .restart local v0    # "intent":Landroid/content/Intent;
    goto :goto_19
.end method

.method private unrestrictEse()V
    .registers 4

    .prologue
    .line 365
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const v0, 0x7f090013

    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->showDialog(I)V

    .line 366
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardManager:Lcom/miui/tsmclient/model/CardManager;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$13;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$13;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Lcom/miui/tsmclient/model/CardManager;->unrestrictESE(Landroid/content/Context;Lcom/miui/tsmclientsdk/MiTsmCallback;)V

    .line 377
    return-void
.end method


# virtual methods
.method protected checkFingerStatus()V
    .registers 2

    .prologue
    .line 152
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    invoke-virtual {v0}, Landroid/hardware/fingerprint/FingerprintManager;->hasEnrolledFingerprints()Z

    move-result v0

    if-eqz v0, :cond_9

    .line 156
    :goto_8
    return-void

    .line 155
    :cond_9
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->showFingerprintAlertDialog()V

    goto :goto_8
.end method

.method protected doCreate(Landroid/os/Bundle;)V
    .registers 5
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 103
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    invoke-super {p0, p1}, Lcom/miui/tsmclient/ui/BaseFragment;->doCreate(Landroid/os/Bundle;)V

    .line 104
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    .line 105
    .local v0, "bundle":Landroid/os/Bundle;
    if-eqz v0, :cond_23

    .line 106
    const-string v1, "card_info"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v1

    check-cast v1, Lcom/miui/tsmclient/entity/CardInfo;

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    .line 107
    const-string v1, "tag_default_card_aid"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mDefaultCardAId:Ljava/lang/String;

    .line 108
    const-string v1, "card_info_list"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getParcelableArrayList(Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfoList:Ljava/util/List;

    .line 110
    :cond_23
    invoke-static {}, Lmiui/util/async/TaskManager;->getDefault()Lmiui/util/async/TaskManager;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mTaskManager:Lmiui/util/async/TaskManager;

    .line 111
    new-instance v2, Lcom/miui/tsmclient/ui/BaseFragment$CardHandler;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    check-cast v1, Lmiui/app/Activity;

    invoke-direct {v2, p0, v1}, Lcom/miui/tsmclient/ui/BaseFragment$CardHandler;-><init>(Lcom/miui/tsmclient/ui/BaseFragment;Lmiui/app/Activity;)V

    iput-object v2, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mHandler:Landroid/os/Handler;

    .line 112
    new-instance v1, Lcom/miui/tsmclient/model/CardManager;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Lcom/miui/tsmclient/model/CardManager;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardManager:Lcom/miui/tsmclient/model/CardManager;

    .line 113
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    const-string v2, "fingerprint"

    invoke-virtual {v1, v2}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/hardware/fingerprint/FingerprintManager;

    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    .line 114
    return-void
.end method

.method protected getRoutingNotESEPrompt()Ljava/lang/String;
    .registers 2

    .prologue
    .line 167
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const v0, 0x7f090032

    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected handleMessage(Landroid/os/Message;Lmiui/app/Activity;)V
    .registers 5
    .param p1, "msg"    # Landroid/os/Message;
    .param p2, "activity"    # Lmiui/app/Activity;

    .prologue
    .line 81
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget v1, p1, Landroid/os/Message;->what:I

    packed-switch v1, :pswitch_data_32

    .line 99
    :goto_5
    return-void

    .line 83
    :pswitch_6
    iget-object v1, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-direct {p0, v1}, Lcom/miui/tsmclient/ui/BaseCardFragment;->handleNfcEEStatus(I)V

    goto :goto_5

    .line 86
    :pswitch_12
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->dismissDialog()V

    .line 87
    iget-object v1, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 88
    .local v0, "resultCode":I
    if-nez v0, :cond_23

    .line 89
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onNfcEEUnRestrictedSuccess()V

    goto :goto_5

    .line 90
    :cond_23
    const/16 v1, 0xbd2

    if-ne v0, v1, :cond_2b

    .line 91
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onUnRestrictNfcEERejected()V

    goto :goto_5

    .line 93
    :cond_2b
    const v1, 0x7f090035

    invoke-virtual {p0, v1}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onNfcEERestricted(I)V

    goto :goto_5

    .line 81
    :pswitch_data_32
    .packed-switch 0x3e8
        :pswitch_6
        :pswitch_12
    .end packed-switch
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .registers 6
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 118
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    invoke-super {p0, p1}, Lcom/miui/tsmclient/ui/BaseFragment;->onActivityCreated(Landroid/os/Bundle;)V

    .line 119
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->getActionBar()Landroid/app/ActionBar;

    move-result-object v0

    .line 120
    .local v0, "actionBar":Landroid/app/ActionBar;
    if-eqz v0, :cond_3d

    iget-object v2, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v2, :cond_3d

    .line 121
    iget-object v2, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v1, v2, Lcom/miui/tsmclient/entity/CardInfo;->mCardName:Ljava/lang/String;

    .line 122
    .local v1, "title":Ljava/lang/String;
    iget-object v2, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v2, v2, Lcom/miui/tsmclient/entity/CardInfo;->mCardSubName:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_3a

    .line 123
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\u00b7"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v3, v3, Lcom/miui/tsmclient/entity/CardInfo;->mCardSubName:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 125
    :cond_3a
    invoke-virtual {v0, v1}, Landroid/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 127
    .end local v1    # "title":Ljava/lang/String;
    :cond_3d
    return-void
.end method

.method public onDestroy()V
    .registers 3

    .prologue
    .line 145
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCardManager:Lcom/miui/tsmclient/model/CardManager;

    invoke-virtual {v0}, Lcom/miui/tsmclient/model/CardManager;->release()V

    .line 146
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mTaskManager:Lmiui/util/async/TaskManager;

    invoke-virtual {v0}, Lmiui/util/async/TaskManager;->shutdown()V

    .line 147
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 148
    invoke-super {p0}, Lcom/miui/tsmclient/ui/BaseFragment;->onDestroy()V

    .line 149
    return-void
.end method

.method protected onESEDisable()V
    .registers 2

    .prologue
    .line 190
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->showNfcOpenDialog(Z)V

    .line 191
    return-void
.end method

.method protected onNFCDisable()V
    .registers 2

    .prologue
    .line 182
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const-string v0, "onNFCDisabled"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 183
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->showNfcOpenDialog(Z)V

    .line 184
    return-void
.end method

.method protected onNFCEnable()V
    .registers 3

    .prologue
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const/4 v1, 0x0

    .line 171
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    if-eqz v0, :cond_14

    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_14

    .line 172
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->dismiss()V

    .line 173
    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mNfcOpenDialog:Landroid/app/AlertDialog;

    .line 175
    :cond_14
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    if-eqz v0, :cond_27

    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_27

    .line 176
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->dismiss()V

    .line 177
    iput-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    .line 179
    :cond_27
    return-void
.end method

.method protected onNfcEERestricted(I)V
    .registers 5
    .param p1, "titleId"    # I

    .prologue
    .line 197
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    if-nez v0, :cond_3e

    .line 198
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, p1}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f090034

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setMessage(I)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x104000a

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$4;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$4;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const/high16 v1, 0x1040000

    new-instance v2, Lcom/miui/tsmclient/ui/BaseCardFragment$3;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$3;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    new-instance v1, Lcom/miui/tsmclient/ui/BaseCardFragment$2;

    invoke-direct {v1, p0}, Lcom/miui/tsmclient/ui/BaseCardFragment$2;-><init>(Lcom/miui/tsmclient/ui/BaseCardFragment;)V

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    .line 223
    :cond_3e
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0, p1}, Landroid/app/AlertDialog;->setTitle(I)V

    .line 224
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mUnrestricteSEDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 225
    return-void
.end method

.method public onResume()V
    .registers 3

    .prologue
    .line 131
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    invoke-super {p0}, Lcom/miui/tsmclient/ui/BaseFragment;->onResume()V

    .line 132
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->showErrorWhenNFCOff()Z

    move-result v0

    if-nez v0, :cond_a

    .line 141
    :goto_9
    return-void

    .line 135
    :cond_a
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    if-eqz v0, :cond_13

    .line 136
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    invoke-virtual {v0}, Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;->cancel()V

    .line 138
    :cond_13
    new-instance v0, Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    .line 139
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEListener:Lcom/miui/tsmclient/task/TaskListener;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;->addListener(Lmiui/util/async/Task$Listener;)Lmiui/util/async/Task;

    .line 140
    iget-object v0, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mTaskManager:Lmiui/util/async/TaskManager;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/BaseCardFragment;->mCheckNfcEEStatusTask:Lcom/miui/tsmclient/task/CheckNfcEEStatusTask;

    invoke-virtual {v0, v1}, Lmiui/util/async/TaskManager;->add(Lmiui/util/async/Task;)V

    goto :goto_9
.end method

.method protected showErrorWhenNFCOff()Z
    .registers 2

    .prologue
    .line 159
    .local p0, "this":Lcom/miui/tsmclient/ui/BaseCardFragment;, "Lcom/miui/tsmclient/ui/BaseCardFragment<TT;>;"
    const/4 v0, 0x1

    return v0
.end method
