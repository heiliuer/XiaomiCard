.class public Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
.super Lcom/miui/tsmclient/ui/BaseCardFragment;
.source "SwitchCardFragment.java"

# interfaces
.implements Lcom/miui/tsmclient/presenter/IHandleError;
.implements Lcom/miui/tsmclient/presenter/SwitchCardContract$View;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;,
        Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;,
        Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/tsmclient/ui/BaseCardFragment",
        "<",
        "Lcom/miui/tsmclient/entity/CardInfo;",
        ">;",
        "Lcom/miui/tsmclient/presenter/IHandleError;",
        "Lcom/miui/tsmclient/presenter/SwitchCardContract$View;"
    }
.end annotation


# static fields
.field private static final DisableCe:I = 0xc000000

.field private static final EnableCe:I = 0x140000

.field private static final EnableCeForLinNanTong:I = 0x40000

.field private static final FINGER_VERIFY_MAX:I = 0xa

.field private static final MSG_DELAY_DESTORY:I = 0x5

.field private static final MSG_DELAY_SHOW_POS:I = 0x2

.field private static final MSG_FINISHE_SELF:I = 0x4

.field private static final MSG_REFRESH_VIEW:I = 0x1

.field private static final MSG_UPDATE_ADAPTER_DATA:I = 0x3

.field private static final TAG:Ljava/lang/String; = "SwitchCardFragment"

.field private static final TIME_PATTERN:Ljava/lang/String; = " yyyy-MM-dd HH:mm"


# instance fields
.field private mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

.field private mAttatchedActivitySource:Ljava/lang/String;

.field private mCMOpened:Z

.field private mCalledFromRFOn:Z

.field private mCalledFromVolumnDown:Z

.field private mCancellationSignal:Landroid/os/CancellationSignal;

.field private mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

.field private mCardList:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;",
            ">;"
        }
    .end annotation
.end field

.field private mCardManager:Lcom/miui/tsmclient/model/CardManager;

.field private mDefaultCardChangedListener:Lcom/miui/tsmclient/ui/quick/CardStackAdapter$ICardStackViewChangedListener;

.field private mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

.field private mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

.field private mFadeInAnim:Landroid/view/animation/Animation;

.field private mFingerLayer:Landroid/view/View;

.field private mFingerPrinterDrawable:Landroid/graphics/drawable/AnimationDrawable;

.field private mFingerVefifyedCount:I

.field private mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

.field private mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

.field private mIcMore:Landroid/widget/ImageView;

.field private mLastActivatedCardAid:Ljava/lang/String;

.field private mNfcAdapter:Landroid/nfc/NfcAdapter;

.field private mNfcHandler:Landroid/os/Handler;

.field private mNoticeIcon:Landroid/widget/ImageView;

.field private mNoticeText:Landroid/widget/TextView;

.field private mOnClickListener:Landroid/view/View$OnClickListener;

.field private mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

.field private mSelfCancelled:Z

.field private mTransactionReciver:Landroid/content/BroadcastReceiver;

.field private mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

.field private myFingerAuthCallback:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;


# direct methods
.method public constructor <init>()V
    .registers 3

    .prologue
    const/4 v1, 0x0

    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;-><init>()V

    .line 103
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mSelfCancelled:Z

    .line 104
    iput-boolean v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    .line 105
    iput-boolean v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromRFOn:Z

    .line 118
    const/16 v0, 0xa

    iput v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    .line 119
    iput-boolean v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    .line 122
    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    .line 145
    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$2;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mTransactionReciver:Landroid/content/BroadcastReceiver;

    .line 152
    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$3;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardChangedListener:Lcom/miui/tsmclient/ui/quick/CardStackAdapter$ICardStackViewChangedListener;

    .line 178
    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;

    invoke-direct {v0, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$4;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mOnClickListener:Landroid/view/View$OnClickListener;

    .line 829
    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$1;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->myFingerAuthCallback:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;

    .line 863
    return-void
.end method

.method static synthetic access$000(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Landroid/content/Intent;)V
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
    .param p1, "x1"    # Landroid/content/Intent;

    .prologue
    .line 72
    invoke-direct {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->handleBroadCast(Landroid/content/Intent;)V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;I)V
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
    .param p1, "x1"    # I

    .prologue
    .line 72
    invoke-direct {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->updateDefaultCardView(I)V

    return-void
.end method

.method static synthetic access$1000(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic access$1200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Z
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    return v0
.end method

.method static synthetic access$1300(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->openTransCard()V

    return-void
.end method

.method static synthetic access$1400(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->openBankCard()V

    return-void
.end method

.method static synthetic access$1500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic access$1600(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->enableCardEmulation()V

    return-void
.end method

.method static synthetic access$1700(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic access$1800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->disableCardEmulation()V

    return-void
.end method

.method static synthetic access$1900(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->restartListener()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/entity/CardInfo;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    return-object v0
.end method

.method static synthetic access$2102(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;I)I
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
    .param p1, "x1"    # I

    .prologue
    .line 72
    iput p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    return p1
.end method

.method static synthetic access$2106(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)I
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    return v0
.end method

.method static synthetic access$300(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/entity/CardInfo;)Z
    .registers 3
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;
    .param p1, "x1"    # Lcom/miui/tsmclient/entity/CardInfo;

    .prologue
    .line 72
    invoke-direct {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$400(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Z
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    return v0
.end method

.method static synthetic access$500(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/os/Handler;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic access$600(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Landroid/widget/ImageView;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    return-object v0
.end method

.method static synthetic access$700(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/WaveCircle;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    return-object v0
.end method

.method static synthetic access$800(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)Lcom/miui/tsmclient/ui/widget/SlideView;
    .registers 2
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    return-object v0
.end method

.method static synthetic access$900(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V
    .registers 1
    .param p0, "x0"    # Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;

    .prologue
    .line 72
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showGuideDialog()V

    return-void
.end method

.method private disableCardEmulation()V
    .registers 9

    .prologue
    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 560
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    if-nez v1, :cond_7

    .line 584
    :goto_6
    return-void

    .line 564
    :cond_7
    const-string v1, "nfc"

    const-string v2, "operation_%s_launch"

    new-array v3, v7, [Ljava/lang/Object;

    const-string v4, "disableCardEmulation"

    aput-object v4, v3, v6

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 567
    :try_start_18
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mLastActivatedCardAid:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_37

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mLastActivatedCardAid:Ljava/lang/String;

    invoke-static {v1}, Lcom/miui/tsmclient/util/SysUtils;->isBankCardAid(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_37

    .line 569
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mLastActivatedCardAid:Ljava/lang/String;

    invoke-static {v1, v2}, Lcom/miui/tsmclient/util/SysUtils;->deactivateCard(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_37

    .line 570
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mLastActivatedCardAid:Ljava/lang/String;

    .line 573
    :cond_37
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcAdapter:Landroid/nfc/NfcAdapter;

    const/high16 v2, 0xc000000

    invoke-virtual {v1, v2}, Landroid/nfc/NfcAdapter;->setListenTechMask(I)V

    .line 574
    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    .line 576
    const-string v1, "nfc"

    const-string v2, "operation_%s_success"

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    const-string v5, "disableCardEmulation"

    aput-object v5, v3, v4

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_54
    .catch Ljava/io/IOException; {:try_start_18 .. :try_end_54} :catch_55

    goto :goto_6

    .line 578
    :catch_55
    move-exception v0

    .line 579
    .local v0, "e":Ljava/io/IOException;
    const-string v1, "failed to disable card emulation"

    invoke-static {v1, v0}, Lcom/miui/tsmclient/util/LogUtils;->e(Ljava/lang/String;Ljava/lang/Exception;)V

    .line 581
    const-string v1, "nfc"

    const-string v2, "operation_%s_failed"

    new-array v3, v7, [Ljava/lang/Object;

    const-string v4, "disableCardEmulation"

    aput-object v4, v3, v6

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_6
.end method

.method private enableCardEmulation()V
    .registers 14

    .prologue
    const/4 v12, 0x0

    const/4 v11, 0x1

    .line 511
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    .line 512
    .local v4, "start":J
    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v6, :cond_14

    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v6, v6, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_15

    .line 557
    :cond_14
    :goto_14
    return-void

    .line 515
    :cond_15
    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    iget-object v7, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v7, v7, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-virtual {v6, v7}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;->changeLastUsedCard(Ljava/lang/String;)V

    .line 516
    const/4 v3, 0x1

    .line 518
    .local v3, "result":Z
    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v6}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v6

    if-eqz v6, :cond_d5

    .line 519
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    invoke-static {v6, v12}, Lcom/miui/tsmclient/util/PrefUtils;->getDefaultCard(Landroid/content/Context;Z)Ljava/lang/String;

    move-result-object v0

    .line 524
    .local v0, "cachedCard":Ljava/lang/String;
    :goto_2f
    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v6, v6, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v0, v6}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v6

    if-nez v6, :cond_47

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-nez v6, :cond_47

    .line 526
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    invoke-static {v6, v0}, Lcom/miui/tsmclient/util/SysUtils;->deactivateCard(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v3

    .line 528
    :cond_47
    if-eqz v3, :cond_55

    .line 529
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    iget-object v7, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v7, v7, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v6, v7}, Lcom/miui/tsmclient/util/SysUtils;->activateCard(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v3

    .line 531
    :cond_55
    if-eqz v3, :cond_103

    .line 533
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 534
    .local v2, "params":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
    const-string v6, "aid"

    iget-object v7, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v7, v7, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v7}, Lcom/miui/tsmclient/util/StringUtils;->getAidFactor(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v2, v6, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 535
    const-string v6, "nfc"

    const-string v7, "operation_%s_launch"

    new-array v8, v11, [Ljava/lang/Object;

    const-string v9, "enableCardEmulation"

    aput-object v9, v8, v12

    invoke-static {v7, v8}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    .line 538
    :try_start_7a
    const-string v6, "set card emulation mask"

    invoke-static {v6}, Lcom/miui/tsmclient/util/LogUtils;->i(Ljava/lang/String;)V

    .line 539
    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v6, v6, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    iput-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mLastActivatedCardAid:Ljava/lang/String;

    .line 540
    iget-object v7, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcAdapter:Landroid/nfc/NfcAdapter;

    iget-object v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v6, v6, Lcom/miui/tsmclient/entity/CardInfo;->mCardType:Ljava/lang/String;

    const-string v8, "LNT"

    invoke-static {v6, v8}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_df

    const/high16 v6, 0x40000

    :goto_95
    invoke-virtual {v7, v6}, Landroid/nfc/NfcAdapter;->setListenTechMask(I)V

    .line 542
    const/4 v6, 0x1

    iput-boolean v6, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    .line 544
    const-string v6, "nfc"

    const-string v7, "operation_%s_success"

    const/4 v8, 0x1

    new-array v8, v8, [Ljava/lang/Object;

    const/4 v9, 0x0

    const-string v10, "enableCardEmulation"

    aput-object v10, v8, v9

    invoke-static {v7, v8}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V
    :try_end_ae
    .catch Ljava/io/IOException; {:try_start_7a .. :try_end_ae} :catch_e2

    .line 556
    .end local v2    # "params":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
    :goto_ae
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "active time = "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    sub-long/2addr v8, v4

    invoke-virtual {v6, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ", active result: "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v6}, Lcom/miui/tsmclient/util/LogUtils;->i(Ljava/lang/String;)V

    goto/16 :goto_14

    .line 521
    .end local v0    # "cachedCard":Ljava/lang/String;
    :cond_d5
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    invoke-static {v6, v11}, Lcom/miui/tsmclient/util/PrefUtils;->getDefaultCard(Landroid/content/Context;Z)Ljava/lang/String;

    move-result-object v0

    .restart local v0    # "cachedCard":Ljava/lang/String;
    goto/16 :goto_2f

    .line 540
    .restart local v2    # "params":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
    :cond_df
    const/high16 v6, 0x140000

    goto :goto_95

    .line 546
    :catch_e2
    move-exception v1

    .line 547
    .local v1, "e":Ljava/io/IOException;
    const-string v6, "failed to enable card emulation"

    invoke-static {v6, v1}, Lcom/miui/tsmclient/util/LogUtils;->e(Ljava/lang/String;Ljava/lang/Exception;)V

    .line 549
    const-string v6, "nfc"

    const-string v7, "operation_%s_failed"

    new-array v8, v11, [Ljava/lang/Object;

    const-string v9, "enableCardEmulation"

    aput-object v9, v8, v12

    invoke-static {v7, v8}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7, v2}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    .line 551
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    const-string v7, "MIQuickPay"

    invoke-static {v6, v7, v4, v5}, Lcom/miui/tsmclient/analytics/AnalyticManager;->bugReport(Landroid/content/Context;Ljava/lang/String;J)V

    goto :goto_ae

    .line 554
    .end local v1    # "e":Ljava/io/IOException;
    .end local v2    # "params":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
    :cond_103
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v6

    const-string v7, "MIQuickPay"

    invoke-static {v6, v7, v4, v5}, Lcom/miui/tsmclient/analytics/AnalyticManager;->bugReport(Landroid/content/Context;Ljava/lang/String;J)V

    goto :goto_ae
.end method

.method private getTradeAmountTextSize(Ljava/lang/String;)I
    .registers 5
    .param p1, "formatAmount"    # Ljava/lang/String;

    .prologue
    .line 880
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    const/4 v2, 0x7

    if-gt v1, v2, :cond_13

    .line 881
    const v0, 0x7f0800a5

    .line 889
    .local v0, "resourceID":I
    :goto_a
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    return v1

    .line 882
    .end local v0    # "resourceID":I
    :cond_13
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x9

    if-gt v1, v2, :cond_1f

    .line 883
    const v0, 0x7f0800a6

    .restart local v0    # "resourceID":I
    goto :goto_a

    .line 884
    .end local v0    # "resourceID":I
    :cond_1f
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0xb

    if-gt v1, v2, :cond_2b

    .line 885
    const v0, 0x7f0800a7

    .restart local v0    # "resourceID":I
    goto :goto_a

    .line 887
    .end local v0    # "resourceID":I
    :cond_2b
    const v0, 0x7f0800a8

    .restart local v0    # "resourceID":I
    goto :goto_a
.end method

.method private handleBroadCast(Landroid/content/Intent;)V
    .registers 8
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 381
    if-nez p1, :cond_3

    .line 399
    :cond_2
    :goto_2
    return-void

    .line 384
    :cond_3
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v3

    const-string v4, "com.miui.nfc.action.TRANSACTION"

    invoke-static {v3, v4}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_67

    .line 385
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    const/4 v4, 0x2

    invoke-virtual {v3, v4}, Landroid/os/Handler;->removeMessages(I)V

    .line 386
    const-string v3, "com.miui.nfc.extras.AID"

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getByteArrayExtra(Ljava/lang/String;)[B

    move-result-object v0

    .line 387
    .local v0, "aid":[B
    const-string v3, "com.miui.nfc.extras.DATA"

    invoke-virtual {p1, v3}, Landroid/content/Intent;->getByteArrayExtra(Ljava/lang/String;)[B

    move-result-object v1

    .line 388
    .local v1, "data":[B
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "aid = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-static {v0}, Lcom/tsmclient/smartcard/Coder;->bytesToHexString([B)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 389
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "data = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-static {v1}, Lcom/tsmclient/smartcard/Coder;->bytesToHexString([B)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 391
    invoke-static {v0}, Lcom/miui/tsmclient/hcievent/HciEventUtils;->getHciEventHandler([B)Lcom/miui/tsmclient/hcievent/IHciEventHandler;

    move-result-object v2

    .line 392
    .local v2, "eventHandler":Lcom/miui/tsmclient/hcievent/IHciEventHandler;
    if-eqz v2, :cond_2

    .line 395
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    invoke-interface {v2, v0, v4, v5, v1}, Lcom/miui/tsmclient/hcievent/IHciEventHandler;->handleData([BJ[B)Lcom/miui/tsmclient/hcievent/HciEventInfo;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->onReceiveHciEvent(Lcom/miui/tsmclient/hcievent/HciEventInfo;)V

    goto :goto_2

    .line 396
    .end local v0    # "aid":[B
    .end local v1    # "data":[B
    .end local v2    # "eventHandler":Lcom/miui/tsmclient/hcievent/IHciEventHandler;
    :cond_67
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v3

    const-string v4, "android.intent.action.SCREEN_OFF"

    invoke-static {v3, v4}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 397
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-virtual {v3}, Landroid/app/Activity;->finish()V

    goto :goto_2
.end method

.method private initView(Lcom/miui/tsmclient/entity/SwitchPageData;)V
    .registers 4
    .param p1, "switchPageData"    # Lcom/miui/tsmclient/entity/SwitchPageData;

    .prologue
    const/4 v1, 0x0

    .line 671
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->getItem(I)Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    move-result-object v0

    if-nez v0, :cond_a

    .line 678
    :goto_9
    return-void

    .line 674
    :cond_a
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/ui/widget/SlideView;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    .line 675
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerLayer:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 676
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->setVisibility(I)V

    .line 677
    const/4 v0, -0x1

    invoke-direct {p0, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->updateDefaultCardView(I)V

    goto :goto_9
.end method

.method private isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z
    .registers 4
    .param p1, "cardInfo"    # Lcom/miui/tsmclient/entity/CardInfo;

    .prologue
    .line 338
    if-eqz p1, :cond_e

    iget-object v0, p1, Lcom/miui/tsmclient/entity/CardInfo;->mCardType:Ljava/lang/String;

    const-string v1, "BANKCARD"

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_e

    const/4 v0, 0x1

    :goto_d
    return v0

    :cond_e
    const/4 v0, 0x0

    goto :goto_d
.end method

.method private isFingerAuthAvailable()Z
    .registers 2

    .prologue
    .line 376
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    invoke-virtual {v0}, Landroid/hardware/fingerprint/FingerprintManager;->isHardwareDetected()Z

    move-result v0

    if-eqz v0, :cond_12

    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    invoke-virtual {v0}, Landroid/hardware/fingerprint/FingerprintManager;->hasEnrolledFingerprints()Z

    move-result v0

    if-eqz v0, :cond_12

    const/4 v0, 0x1

    :goto_11
    return v0

    :cond_12
    const/4 v0, 0x0

    goto :goto_11
.end method

.method private onReceiveHciEvent(Lcom/miui/tsmclient/hcievent/HciEventInfo;)V
    .registers 16
    .param p1, "hciEventInfo"    # Lcom/miui/tsmclient/hcievent/HciEventInfo;

    .prologue
    .line 402
    if-nez p1, :cond_3

    .line 478
    :cond_2
    :goto_2
    return-void

    .line 405
    :cond_3
    new-instance v7, Ljava/util/HashMap;

    invoke-direct {v7}, Ljava/util/HashMap;-><init>()V

    .line 406
    .local v7, "params":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
    const-string v0, "aid"

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v1, v1, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v1}, Lcom/miui/tsmclient/util/StringUtils;->getAidFactor(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v7, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 407
    const-string v0, "time"

    iget-wide v12, p1, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mTradeTime:J

    const-string v1, "MM-dd hh:mm:ss"

    invoke-static {v12, v13, v1}, Lcom/miui/tsmclient/util/StringUtils;->millsToTime(JLjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v7, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 408
    const-string v0, "trade_amount"

    iget v1, p1, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mTradeAmount:I

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v7, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 409
    const-string v1, "MIQuickPay"

    invoke-virtual {p1}, Lcom/miui/tsmclient/hcievent/HciEventInfo;->isTradeSuccess()Z

    move-result v0

    if-eqz v0, :cond_f6

    const-string v0, "operation_%s_success"

    :goto_37
    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v11, 0x0

    const-string v12, "hciEvent"

    aput-object v12, v2, v11

    invoke-static {v0, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0, v7}, Lcom/miui/tsmclient/analytics/AnalyticManager;->recordEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    .line 412
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    if-eqz v0, :cond_2

    .line 416
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->setScreenTouchable(Z)V

    .line 417
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->getCount()I

    move-result v0

    const/4 v1, 0x1

    if-le v0, v1, :cond_67

    .line 418
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    const/4 v1, 0x1

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v2}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->getCount()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v0, v1, v2}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->removeViews(II)V

    .line 420
    :cond_67
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    .line 421
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->stopWave()V

    .line 422
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 423
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 424
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v1, 0x7f0c008d

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v9

    check-cast v9, Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;

    .line 425
    .local v9, "tradeAmount":Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;
    iget v0, p1, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mTradeAmount:I

    invoke-static {v0}, Lcom/miui/tsmclient/util/StringUtils;->formatAmount(I)Ljava/lang/String;

    move-result-object v6

    .line 426
    .local v6, "formatAmount":Ljava/lang/String;
    invoke-direct {p0, v6}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getTradeAmountTextSize(Ljava/lang/String;)I

    move-result v8

    .line 427
    .local v8, "textSize":I
    const/4 v0, 0x0

    int-to-float v1, v8

    invoke-virtual {v9, v0, v1}, Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;->setTextSize(IF)V

    .line 428
    invoke-virtual {v9, v6}, Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;->setText(Ljava/lang/CharSequence;)V

    .line 429
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v1, 0x7f0c008e

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v10

    check-cast v10, Landroid/widget/TextView;

    .line 430
    .local v10, "tradeTime":Landroid/widget/TextView;
    iget-wide v0, p1, Lcom/miui/tsmclient/hcievent/HciEventInfo;->mTradeTime:J

    const-string v2, " yyyy-MM-dd HH:mm"

    invoke-static {v0, v1, v2}, Lcom/miui/tsmclient/util/StringUtils;->millsToTime(JLjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v10, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 431
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v1, 0x7f0c0092

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/TextView;

    .line 432
    .local v4, "viewOk":Landroid/widget/TextView;
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v1, 0x7f0c002b

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v5

    check-cast v5, Lcom/miui/tsmclient/ui/widget/FlashView;

    .line 433
    .local v5, "cardContent":Lcom/miui/tsmclient/ui/widget/FlashView;
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v1, 0x7f0c0091

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/ImageView;

    .line 435
    .local v3, "viewOkImg":Landroid/widget/ImageView;
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->getActionBar()Landroid/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_fa

    const/4 v0, 0x1

    :goto_de
    invoke-virtual {v1, v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->setShowTitleBar(Z)V

    .line 436
    iget-object v11, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    new-instance v0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;

    move-object v1, p0

    move-object v2, p1

    invoke-direct/range {v0 .. v5}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$5;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Lcom/miui/tsmclient/hcievent/HciEventInfo;Landroid/widget/ImageView;Landroid/widget/TextView;Lcom/miui/tsmclient/ui/widget/FlashView;)V

    invoke-virtual {v11, v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->showHeaderView(Landroid/view/View$OnLayoutChangeListener;)V

    .line 477
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto/16 :goto_2

    .line 409
    .end local v3    # "viewOkImg":Landroid/widget/ImageView;
    .end local v4    # "viewOk":Landroid/widget/TextView;
    .end local v5    # "cardContent":Lcom/miui/tsmclient/ui/widget/FlashView;
    .end local v6    # "formatAmount":Ljava/lang/String;
    .end local v8    # "textSize":I
    .end local v9    # "tradeAmount":Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;
    .end local v10    # "tradeTime":Landroid/widget/TextView;
    :cond_f6
    const-string v0, "operation_%s_failed"

    goto/16 :goto_37

    .line 435
    .restart local v3    # "viewOkImg":Landroid/widget/ImageView;
    .restart local v4    # "viewOk":Landroid/widget/TextView;
    .restart local v5    # "cardContent":Lcom/miui/tsmclient/ui/widget/FlashView;
    .restart local v6    # "formatAmount":Ljava/lang/String;
    .restart local v8    # "textSize":I
    .restart local v9    # "tradeAmount":Lcom/miui/tsmclient/ui/widget/MiuiDigitFontTextView;
    .restart local v10    # "tradeTime":Landroid/widget/TextView;
    :cond_fa
    const/4 v0, 0x0

    goto :goto_de
.end method

.method private openBankCard()V
    .registers 6

    .prologue
    .line 324
    invoke-static {}, Lcom/miui/tsmclientsdk/MiTsmManager;->getInstance()Lcom/miui/tsmclientsdk/MiTsmManager;

    move-result-object v1

    .line 326
    .local v1, "miTsmManager":Lcom/miui/tsmclientsdk/MiTsmManager;
    :try_start_4
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const/4 v3, 0x1

    const/4 v4, 0x0

    invoke-virtual {v1, v2, v3, v4}, Lcom/miui/tsmclientsdk/MiTsmManager;->startOpenCard(Landroid/app/Activity;ILandroid/os/Bundle;)V
    :try_end_d
    .catch Lcom/miui/tsmclientsdk/UnSupportedException; {:try_start_4 .. :try_end_d} :catch_e

    .line 330
    :goto_d
    return-void

    .line 327
    :catch_e
    move-exception v0

    .line 328
    .local v0, "e":Lcom/miui/tsmclientsdk/UnSupportedException;
    const-string v2, "Not support to open bankcard"

    invoke-static {v2}, Lcom/miui/tsmclient/util/LogUtils;->e(Ljava/lang/String;)V

    goto :goto_d
.end method

.method private openTransCard()V
    .registers 4

    .prologue
    .line 333
    new-instance v0, Landroid/content/Intent;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    const-class v2, Lcom/miui/tsmclient/ui/MainActivity;

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->startActivity(Landroid/content/Intent;)V

    .line 334
    return-void
.end method

.method private refreshFingerView(Z)V
    .registers 8
    .param p1, "authencated"    # Z

    .prologue
    .line 481
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    .line 482
    .local v0, "isBankCard":Z
    if-eqz p1, :cond_4d

    .line 483
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    if-eqz v1, :cond_11

    .line 484
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->stopShading()V

    .line 486
    :cond_11
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    invoke-virtual {v1}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->startWave()V

    .line 487
    if-eqz v0, :cond_3c

    .line 488
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerPrinterDrawable:Landroid/graphics/drawable/AnimationDrawable;

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 489
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerPrinterDrawable:Landroid/graphics/drawable/AnimationDrawable;

    invoke-virtual {v1}, Landroid/graphics/drawable/AnimationDrawable;->start()V

    .line 490
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v2, 0x7f090025

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(I)V

    .line 491
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFadeInAnim:Landroid/view/animation/Animation;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->startAnimation(Landroid/view/animation/Animation;)V

    .line 492
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    const/4 v2, 0x2

    const-wide/16 v4, 0x258

    invoke-virtual {v1, v2, v4, v5}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 508
    :goto_3b
    return-void

    .line 494
    :cond_3c
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v2, 0x7f020062

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 495
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v2, 0x7f09002a

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(I)V

    goto :goto_3b

    .line 498
    :cond_4d
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    invoke-virtual {v1}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->stopWave()V

    .line 499
    if-eqz v0, :cond_6f

    .line 500
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v2, 0x7f02005c

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 501
    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    iget v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    const/16 v3, 0xa

    if-ge v1, v3, :cond_6b

    const v1, 0x7f090024

    :goto_67
    invoke-virtual {v2, v1}, Landroid/widget/TextView;->setText(I)V

    goto :goto_3b

    :cond_6b
    const v1, 0x7f09001f

    goto :goto_67

    .line 503
    :cond_6f
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v1}, Lcom/miui/tsmclient/ui/widget/SlideView;->startShading()V

    .line 504
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v2, 0x7f020064

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 505
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v2, 0x7f090029

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(I)V

    goto :goto_3b
.end method

.method private refreshIcMoreView()V
    .registers 4

    .prologue
    const/16 v2, 0x8

    const/4 v1, 0x0

    .line 742
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    if-eqz v0, :cond_d

    .line 743
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 755
    :goto_c
    return-void

    .line 746
    :cond_d
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromRFOn:Z

    if-eqz v0, :cond_17

    .line 747
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_c

    .line 750
    :cond_17
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    if-eqz v0, :cond_25

    .line 751
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_c

    .line 753
    :cond_25
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_c
.end method

.method private restartListener()V
    .registers 1

    .prologue
    .line 363
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->stopListener()V

    .line 364
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->startListener()V

    .line 365
    return-void
.end method

.method private showEmptyView()V
    .registers 3

    .prologue
    .line 681
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->stopListener()V

    .line 682
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->setVisibility(I)V

    .line 683
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showGuideDialog()V

    .line 684
    return-void
.end method

.method private showGuideDialog()V
    .registers 9

    .prologue
    .line 687
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v5

    invoke-static {v5}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v5

    const v6, 0x7f030023

    const/4 v7, 0x0

    invoke-virtual {v5, v6, v7}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v4

    .line 688
    .local v4, "view":Landroid/view/View;
    const v5, 0x7f0c000c

    invoke-virtual {v4, v5}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/TextView;

    .line 689
    .local v3, "title":Landroid/widget/TextView;
    const v5, 0x7f0c00bd

    invoke-virtual {v4, v5}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    .line 690
    .local v2, "subTitle":Landroid/widget/TextView;
    const v5, 0x7f0c00be

    invoke-virtual {v4, v5}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/ImageView;

    .line 691
    .local v1, "img":Landroid/widget/ImageView;
    const v5, 0x7f0c00bf

    invoke-virtual {v4, v5}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 692
    .local v0, "desc":Landroid/widget/TextView;
    iget-boolean v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    if-nez v5, :cond_44

    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v5, :cond_68

    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v5}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v5

    if-nez v5, :cond_68

    .line 693
    :cond_44
    const v5, 0x7f090112

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(I)V

    .line 694
    const v5, 0x7f090113

    invoke-virtual {v2, v5}, Landroid/widget/TextView;->setText(I)V

    .line 695
    const v5, 0x7f090116

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setText(I)V

    .line 696
    const v5, 0x7f020095

    invoke-virtual {v1, v5}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 703
    :goto_5c
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_81

    .line 704
    invoke-direct {p0, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showOpenCardDialog(Landroid/view/View;)V

    .line 709
    :goto_67
    return-void

    .line 698
    :cond_68
    const v5, 0x7f090114

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(I)V

    .line 699
    const v5, 0x7f090115

    invoke-virtual {v2, v5}, Landroid/widget/TextView;->setText(I)V

    .line 700
    const v5, 0x7f090117

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setText(I)V

    .line 701
    const v5, 0x7f020012

    invoke-virtual {v1, v5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_5c

    .line 706
    :cond_81
    const/16 v5, 0x8

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setVisibility(I)V

    .line 707
    invoke-direct {p0, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showQuickStartDialog(Landroid/view/View;)V

    goto :goto_67
.end method

.method private showOpenCardDialog(Landroid/view/View;)V
    .registers 6
    .param p1, "customView"    # Landroid/view/View;

    .prologue
    .line 712
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v1, p1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x7f09011a

    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;

    invoke-direct {v3, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$8;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x7f090119

    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$7;

    invoke-direct {v3, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$7;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    new-instance v2, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$6;

    invoke-direct {v2, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$6;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 738
    .local v0, "dialog":Landroid/app/AlertDialog;
    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 739
    return-void
.end method

.method private showQuickStartDialog(Landroid/view/View;)V
    .registers 8
    .param p1, "customView"    # Landroid/view/View;

    .prologue
    const/4 v5, 0x0

    .line 758
    new-instance v3, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v4

    invoke-direct {v3, v4}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v3, p1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v3

    const v4, 0x7f09003e

    invoke-virtual {v3, v4, v5}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 761
    .local v0, "builder":Landroid/app/AlertDialog$Builder;
    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v3

    if-nez v3, :cond_51

    .line 762
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/tsmclient/util/PrefUtils;->getLongPressVolumeDownState(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    .line 764
    .local v2, "longPressState":Ljava/lang/String;
    const-string v3, "public_transportation_shortcuts"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_51

    .line 765
    const v3, 0x7f0c00bf

    invoke-virtual {p1, v3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 766
    .local v1, "desc":Landroid/widget/TextView;
    const v3, 0x7f090118

    invoke-virtual {v1, v3}, Landroid/widget/TextView;->setText(I)V

    .line 767
    const/4 v3, 0x0

    invoke-virtual {v1, v3}, Landroid/widget/TextView;->setVisibility(I)V

    .line 768
    const v3, 0x7f090119

    invoke-virtual {v0, v3, v5}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 769
    const v3, 0x7f09011b

    new-instance v4, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;

    invoke-direct {v4, p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$9;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;)V

    invoke-virtual {v0, v3, v4}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 779
    .end local v1    # "desc":Landroid/widget/TextView;
    .end local v2    # "longPressState":Ljava/lang/String;
    :cond_51
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v3

    invoke-virtual {v3}, Landroid/app/AlertDialog;->show()V

    .line 780
    return-void
.end method

.method private startListener()V
    .registers 7

    .prologue
    const/4 v1, 0x0

    const/4 v3, 0x0

    .line 342
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/tsmclient/util/SysUtils;->isForegroundApp(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_d

    .line 360
    :cond_c
    :goto_c
    return-void

    .line 345
    :cond_d
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isFingerAuthAvailable()Z

    move-result v0

    if-nez v0, :cond_19

    .line 346
    const-string v0, "finger auth unavailable"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->w(Ljava/lang/String;)V

    goto :goto_c

    .line 349
    :cond_19
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_c

    .line 352
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    if-nez v0, :cond_2c

    .line 353
    new-instance v0, Landroid/os/CancellationSignal;

    invoke-direct {v0}, Landroid/os/CancellationSignal;-><init>()V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    .line 355
    :cond_2c
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mSelfCancelled:Z

    if-eqz v0, :cond_c

    .line 356
    iput-boolean v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mSelfCancelled:Z

    .line 357
    const-string v0, "start listening authentication"

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 358
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->myFingerAuthCallback:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyFingerAuthCallback;

    move-object v5, v1

    invoke-virtual/range {v0 .. v5}, Landroid/hardware/fingerprint/FingerprintManager;->authenticate(Landroid/hardware/fingerprint/FingerprintManager$CryptoObject;Landroid/os/CancellationSignal;ILandroid/hardware/fingerprint/FingerprintManager$AuthenticationCallback;Landroid/os/Handler;)V

    goto :goto_c
.end method

.method private stopListener()V
    .registers 2

    .prologue
    .line 368
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    if-eqz v0, :cond_f

    .line 369
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    invoke-virtual {v0}, Landroid/os/CancellationSignal;->cancel()V

    .line 370
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mSelfCancelled:Z

    .line 371
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCancellationSignal:Landroid/os/CancellationSignal;

    .line 373
    :cond_f
    return-void
.end method

.method private updateDefaultCardView(I)V
    .registers 12
    .param p1, "touchedIndex"    # I

    .prologue
    const v7, 0x7f0c002c

    const/4 v9, 0x0

    .line 641
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->restartListener()V

    .line 642
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;

    const/4 v6, 0x2

    invoke-virtual {v5, v6}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v5

    invoke-virtual {v5}, Landroid/os/Message;->sendToTarget()V

    .line 643
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    const v6, 0x7f0c0015

    invoke-virtual {v5, v6}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 644
    .local v1, "cardNumView":Landroid/widget/TextView;
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v5, v9}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->getItem(I)Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    move-result-object v5

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    invoke-static {v5}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->access$1100(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;)Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v5

    iput-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    .line 645
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v5, v7}, Lcom/miui/tsmclient/ui/widget/SlideView;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    .line 646
    .local v2, "cardTitleView":Landroid/widget/TextView;
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v5, v5, Lcom/miui/tsmclient/entity/CardInfo;->mCardName:Ljava/lang/String;

    invoke-virtual {v2, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 647
    const/4 v5, -0x1

    if-eq p1, v5, :cond_59

    .line 648
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    invoke-virtual {v5, p1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 649
    .local v4, "touchedView":Landroid/view/View;
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v5, p1}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->getItem(I)Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    move-result-object v3

    .line 650
    .local v3, "touchedData":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    if-eqz v4, :cond_59

    if-eqz v3, :cond_59

    .line 651
    invoke-virtual {v4, v7}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v5

    check-cast v5, Landroid/widget/TextView;

    invoke-virtual {v3}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->getCardInfo()Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v6

    iget-object v6, v6, Lcom/miui/tsmclient/entity/CardInfo;->mCardName:Ljava/lang/String;

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 654
    .end local v3    # "touchedData":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    .end local v4    # "touchedView":Landroid/view/View;
    :cond_59
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->refreshIcMoreView()V

    .line 655
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v5}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v5

    if-eqz v5, :cond_9b

    .line 656
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v5}, Lcom/miui/tsmclient/ui/widget/SlideView;->stopShading()V

    .line 657
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v6, 0x7f02005c

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 658
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v6, 0x7f09001f

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setText(I)V

    .line 659
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    check-cast v0, Lcom/miui/tsmclient/entity/BankCardInfo;

    .line 660
    .local v0, "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    const v5, 0x7f0900cf

    const/4 v6, 0x1

    new-array v6, v6, [Ljava/lang/Object;

    iget-object v7, v0, Lcom/miui/tsmclient/entity/BankCardInfo;->mPanLastDigits:Ljava/lang/String;

    const/4 v8, 0x4

    invoke-static {v7, v8}, Lcom/miui/tsmclient/util/StringUtils;->tail(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v7

    aput-object v7, v6, v9

    invoke-virtual {p0, v5, v6}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 661
    invoke-virtual {v1, v9}, Landroid/widget/TextView;->setVisibility(I)V

    .line 667
    .end local v0    # "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    :goto_96
    const/16 v5, 0xa

    iput v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerVefifyedCount:I

    .line 668
    return-void

    .line 663
    :cond_9b
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v6, 0x7f020064

    invoke-virtual {v5, v6}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 664
    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v6, 0x7f090029

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setText(I)V

    .line 665
    const/16 v5, 0x8

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setVisibility(I)V

    goto :goto_96
.end method


# virtual methods
.method public doCreate(Landroid/os/Bundle;)V
    .registers 6
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 226
    invoke-super {p0, p1}, Lcom/miui/tsmclient/ui/BaseCardFragment;->doCreate(Landroid/os/Bundle;)V

    .line 227
    new-instance v1, Lcom/miui/tsmclient/model/CardManager;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Lcom/miui/tsmclient/model/CardManager;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardManager:Lcom/miui/tsmclient/model/CardManager;

    .line 228
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-static {v1}, Landroid/nfc/NfcAdapter;->getDefaultAdapter(Landroid/content/Context;)Landroid/nfc/NfcAdapter;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcAdapter:Landroid/nfc/NfcAdapter;

    .line 229
    new-instance v1, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    const-string v2, "SwitchCardFragment"

    invoke-direct {v1, p0, v2}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;-><init>(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;Ljava/lang/String;)V

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    .line 230
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    invoke-virtual {v1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->start()V

    .line 231
    new-instance v1, Landroid/os/Handler;

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    invoke-virtual {v2}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    invoke-direct {v1, v2, v3}, Landroid/os/Handler;-><init>(Landroid/os/Looper;Landroid/os/Handler$Callback;)V

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;

    .line 233
    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    .line 234
    .local v0, "filter":Landroid/content/IntentFilter;
    const-string v1, "com.miui.nfc.action.TRANSACTION"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 235
    const-string v1, "android.intent.action.SCREEN_OFF"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 236
    const/16 v1, 0x3e8

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->setPriority(I)V

    .line 237
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mTransactionReciver:Landroid/content/BroadcastReceiver;

    invoke-virtual {v1, v2, v0}, Landroid/app/Activity;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 239
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    const-string v2, "fingerprint"

    invoke-virtual {v1, v2}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/hardware/fingerprint/FingerprintManager;

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerprintManager:Landroid/hardware/fingerprint/FingerprintManager;

    .line 241
    new-instance v1, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    invoke-direct {v1}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;-><init>()V

    iput-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    .line 242
    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;->init(Landroid/content/Context;Landroid/os/Bundle;)V

    .line 243
    return-void
.end method

.method public handleError(ILjava/lang/String;)V
    .registers 5
    .param p1, "errorCode"    # I
    .param p2, "errorDesc"    # Ljava/lang/String;

    .prologue
    .line 788
    const/4 v0, 0x1

    if-ne p1, v0, :cond_9

    .line 790
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    invoke-virtual {v0}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;->downloadCardData()V

    .line 795
    :cond_8
    :goto_8
    return-void

    .line 791
    :cond_9
    const/4 v0, 0x2

    if-ne p1, v0, :cond_8

    .line 792
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showEmptyView()V

    .line 793
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    const v1, 0x7f090139

    invoke-static {v0, v1}, Lcom/miui/tsmclient/util/UiUtils;->showToast(Landroid/content/Context;I)V

    goto :goto_8
.end method

.method protected handleMessage(Landroid/os/Message;Lmiui/app/Activity;)V
    .registers 9
    .param p1, "msg"    # Landroid/os/Message;
    .param p2, "activity"    # Lmiui/app/Activity;

    .prologue
    .line 189
    invoke-super {p0, p1, p2}, Lcom/miui/tsmclient/ui/BaseCardFragment;->handleMessage(Landroid/os/Message;Lmiui/app/Activity;)V

    .line 190
    iget v4, p1, Landroid/os/Message;->what:I

    packed-switch v4, :pswitch_data_7a

    .line 222
    :goto_8
    return-void

    .line 192
    :pswitch_9
    iget-object v4, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v4, Ljava/lang/Boolean;

    invoke-virtual {v4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    invoke-direct {p0, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->refreshFingerView(Z)V

    goto :goto_8

    .line 195
    :pswitch_15
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    const v5, 0x7f020063

    invoke-virtual {v4, v5}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 196
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFadeInAnim:Landroid/view/animation/Animation;

    invoke-virtual {v4, v5}, Landroid/widget/ImageView;->startAnimation(Landroid/view/animation/Animation;)V

    .line 197
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    const v5, 0x7f090023

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setText(I)V

    .line 198
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    iget-object v5, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFadeInAnim:Landroid/view/animation/Animation;

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->startAnimation(Landroid/view/animation/Animation;)V

    goto :goto_8

    .line 201
    :pswitch_34
    iget-object v1, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v1, Ljava/util/List;

    .line 202
    .local v1, "cardInfoList":Ljava/util/List;, "Ljava/util/List<Lcom/miui/tsmclient/entity/CardInfo;>;"
    if-eqz v1, :cond_65

    invoke-interface {v1}, Ljava/util/List;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_65

    .line 203
    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .local v2, "i$":Ljava/util/Iterator;
    :goto_44
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_5b

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/entity/CardInfo;

    .line 204
    .local v0, "cardInfo":Lcom/miui/tsmclient/entity/CardInfo;
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    invoke-direct {v3, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 205
    .local v3, "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_44

    .line 207
    .end local v0    # "cardInfo":Lcom/miui/tsmclient/entity/CardInfo;
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_5b
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v4}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->notifyDataSetChanged()V

    .line 208
    const/4 v4, 0x0

    invoke-direct {p0, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->refreshFingerView(Z)V

    goto :goto_8

    .line 210
    .end local v2    # "i$":Ljava/util/Iterator;
    :cond_65
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v4

    invoke-virtual {v4}, Landroid/app/Activity;->finish()V

    goto :goto_8

    .line 215
    .end local v1    # "cardInfoList":Ljava/util/List;, "Ljava/util/List<Lcom/miui/tsmclient/entity/CardInfo;>;"
    :pswitch_6d
    const-string v4, "SwitchCardFragment will finish its attached activity "

    invoke-static {v4}, Lcom/miui/tsmclient/util/LogUtils;->d(Ljava/lang/String;)V

    .line 216
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v4

    invoke-virtual {v4}, Landroid/app/Activity;->finish()V

    goto :goto_8

    .line 190
    :pswitch_data_7a
    .packed-switch 0x1
        :pswitch_9
        :pswitch_15
        :pswitch_34
        :pswitch_6d
        :pswitch_6d
    .end packed-switch
.end method

.method public onDestroy()V
    .registers 3

    .prologue
    .line 311
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->disableCardEmulation()V

    .line 312
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardManager:Lcom/miui/tsmclient/model/CardManager;

    invoke-virtual {v0}, Lcom/miui/tsmclient/model/CardManager;->release()V

    .line 313
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->quit()Z

    .line 314
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandlerThread:Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$MyHandlerThread;->interrupt()V

    .line 315
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 316
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mTransactionReciver:Landroid/content/BroadcastReceiver;

    invoke-virtual {v0, v1}, Landroid/app/Activity;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 317
    invoke-super {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onDestroy()V

    .line 318
    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .registers 6
    .param p1, "inflater"    # Landroid/view/LayoutInflater;
    .param p2, "container"    # Landroid/view/ViewGroup;
    .param p3, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 247
    const v0, 0x7f030028

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onPause()V
    .registers 5

    .prologue
    .line 296
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->stopWave()V

    .line 297
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    invoke-virtual {v0}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;->detach()V

    .line 298
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    if-eqz v0, :cond_13

    .line 299
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->stopShading()V

    .line 302
    :cond_13
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x5

    const-wide/16 v2, 0xbb8

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 303
    invoke-super {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onPause()V

    .line 304
    return-void
.end method

.method public onResume()V
    .registers 3

    .prologue
    .line 269
    invoke-super {p0}, Lcom/miui/tsmclient/ui/BaseCardFragment;->onResume()V

    .line 270
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 271
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SwitchCardFragment onResumed, event_source: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAttatchedActivitySource:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/tsmclient/util/LogUtils;->i(Ljava/lang/String;)V

    .line 272
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAttatchedActivitySource:Ljava/lang/String;

    const-string v1, "key_volume_down"

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    .line 273
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAttatchedActivitySource:Ljava/lang/String;

    const-string v1, "key_rf_on"

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromRFOn:Z

    .line 274
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    if-nez v0, :cond_3c

    .line 275
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->checkFingerStatus()V

    .line 277
    :cond_3c
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->refreshIcMoreView()V

    .line 278
    iget-boolean v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCMOpened:Z

    if-eqz v0, :cond_5a

    .line 279
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/WaveCircle;->startWave()V

    .line 280
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    if-eqz v0, :cond_51

    .line 281
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->stopShading()V

    .line 286
    :cond_51
    :goto_51
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->startListener()V

    .line 287
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mPresenter:Lcom/miui/tsmclient/presenter/SwitchCardPresenter;

    invoke-virtual {v0, p0}, Lcom/miui/tsmclient/presenter/SwitchCardPresenter;->attach(Lcom/miui/tsmclient/presenter/IView;)V

    .line 288
    return-void

    .line 283
    :cond_5a
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v0, :cond_51

    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v0

    if-nez v0, :cond_51

    .line 284
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultView:Lcom/miui/tsmclient/ui/widget/SlideView;

    invoke-virtual {v0}, Lcom/miui/tsmclient/ui/widget/SlideView;->startShading()V

    goto :goto_51
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .registers 6
    .param p1, "view"    # Landroid/view/View;
    .param p2, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 252
    const v0, 0x7f0c008f

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    .line 253
    const v0, 0x7f0c00d5

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerLayer:Landroid/view/View;

    .line 254
    const v0, 0x7f0c00d6

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeIcon:Landroid/widget/ImageView;

    .line 255
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    const v1, 0x7f040003

    invoke-static {v0, v1}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFadeInAnim:Landroid/view/animation/Animation;

    .line 256
    const v0, 0x7f0c00d7

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNoticeText:Landroid/widget/TextView;

    .line 257
    const v0, 0x7f0c00d4

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/ui/widget/WaveCircle;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mWaveCircle:Lcom/miui/tsmclient/ui/widget/WaveCircle;

    .line 258
    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f02005b

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Landroid/graphics/drawable/AnimationDrawable;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerPrinterDrawable:Landroid/graphics/drawable/AnimationDrawable;

    .line 259
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mFingerPrinterDrawable:Landroid/graphics/drawable/AnimationDrawable;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/AnimationDrawable;->setOneShot(Z)V

    .line 260
    new-instance v0, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-direct {v0, v1, v2}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;-><init>(Landroid/content/Context;Ljava/util/List;)V

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    .line 261
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardChangedListener:Lcom/miui/tsmclient/ui/quick/CardStackAdapter$ICardStackViewChangedListener;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->setDefaultCardChangedListener(Lcom/miui/tsmclient/ui/quick/CardStackAdapter$ICardStackViewChangedListener;)V

    .line 262
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardLayout:Lcom/miui/tsmclient/ui/widget/CardStackLayout;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v0, v1}, Lcom/miui/tsmclient/ui/widget/CardStackLayout;->setAdapter(Lcom/miui/tsmclient/ui/quick/CardStackAdapter;)V

    .line 263
    const v0, 0x7f0c00d8

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    .line 264
    iget-object v0, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mIcMore:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mOnClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 265
    return-void
.end method

.method public setAttachedActivitySource(Ljava/lang/String;)V
    .registers 2
    .param p1, "eventSource"    # Ljava/lang/String;

    .prologue
    .line 783
    iput-object p1, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAttatchedActivitySource:Ljava/lang/String;

    .line 784
    return-void
.end method

.method protected showErrorWhenNFCOff()Z
    .registers 2

    .prologue
    .line 291
    const/4 v0, 0x0

    return v0
.end method

.method public updateCardStack(Lcom/miui/tsmclient/entity/SwitchPageData;)V
    .registers 9
    .param p1, "switchPageData"    # Lcom/miui/tsmclient/entity/SwitchPageData;

    .prologue
    .line 588
    iget-boolean v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    if-eqz v4, :cond_45

    .line 589
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v4, :cond_14

    .line 590
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v3, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 591
    .local v3, "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 627
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_14
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_d0

    .line 628
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mAdapter:Lcom/miui/tsmclient/ui/quick/CardStackAdapter;

    invoke-virtual {v4}, Lcom/miui/tsmclient/ui/quick/CardStackAdapter;->notifyDataSetChanged()V

    .line 629
    invoke-direct {p0, p1}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->initView(Lcom/miui/tsmclient/entity/SwitchPageData;)V

    .line 630
    iget-boolean v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCalledFromVolumnDown:Z

    if-eqz v4, :cond_41

    .line 631
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    const/4 v5, 0x0

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    # getter for: Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->mCardInfo:Lcom/miui/tsmclient/entity/CardInfo;
    invoke-static {v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;->access$1100(Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;)Lcom/miui/tsmclient/entity/CardInfo;

    move-result-object v4

    iput-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mDefaultCardInfo:Lcom/miui/tsmclient/entity/CardInfo;

    .line 632
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mNfcHandler:Landroid/os/Handler;

    const/4 v5, 0x1

    invoke-virtual {v4, v5}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v4

    invoke-virtual {v4}, Landroid/os/Message;->sendToTarget()V

    .line 634
    :cond_41
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->startListener()V

    .line 638
    :goto_44
    return-void

    .line 594
    :cond_45
    const/4 v2, 0x0

    .line 598
    .local v2, "lastUsedBankAid":Ljava/lang/String;
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    if-nez v4, :cond_7f

    .line 599
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v4, :cond_5a

    .line 600
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    new-instance v5, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v6, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v5, v6}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 619
    :cond_5a
    :goto_5a
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mBankCardInfos:Ljava/util/List;

    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    .local v1, "i$":Ljava/util/Iterator;
    :cond_60
    :goto_60
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_14

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/tsmclient/entity/BankCardInfo;

    .line 620
    .local v0, "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    iget-object v4, v0, Lcom/miui/tsmclient/entity/BankCardInfo;->mAid:Ljava/lang/String;

    invoke-static {v4, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_60

    .line 623
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    invoke-direct {v3, v0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 624
    .restart local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_60

    .line 602
    .end local v0    # "bankCardInfo":Lcom/miui/tsmclient/entity/BankCardInfo;
    .end local v1    # "i$":Ljava/util/Iterator;
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_7f
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {p0, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->isBankCard(Lcom/miui/tsmclient/entity/CardInfo;)Z

    move-result v4

    if-eqz v4, :cond_a8

    .line 603
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v2, v4, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    .line 604
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v3, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 605
    .restart local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 606
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    if-eqz v4, :cond_5a

    .line 607
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    new-instance v5, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v6, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v5, v6}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_5a

    .line 610
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_a8
    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v4, v4, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    iget-object v5, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    iget-object v5, v5, Lcom/miui/tsmclient/entity/CardInfo;->mAid:Ljava/lang/String;

    invoke-static {v4, v5}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_c3

    .line 611
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mLastUsedCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v3, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 612
    .restart local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_5a

    .line 614
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_c3
    new-instance v3, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;

    iget-object v4, p1, Lcom/miui/tsmclient/entity/SwitchPageData;->mDefaultTransCard:Lcom/miui/tsmclient/entity/CardInfo;

    invoke-direct {v3, v4}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;-><init>(Lcom/miui/tsmclient/entity/CardInfo;)V

    .line 615
    .restart local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    iget-object v4, p0, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->mCardList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_5a

    .line 636
    .end local v2    # "lastUsedBankAid":Ljava/lang/String;
    .end local v3    # "stackItem":Lcom/miui/tsmclient/ui/quick/SwitchCardFragment$StackItem;
    :cond_d0
    invoke-direct {p0}, Lcom/miui/tsmclient/ui/quick/SwitchCardFragment;->showEmptyView()V

    goto/16 :goto_44
.end method
