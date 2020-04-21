package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.Globals;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.DownloadManager.TaskType;
import com.com.navapp.downloading.voice.OnDownloadCatalog;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.downloading.voice.VoicePackageInfo;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.settings.DownloadingList.DataStatus;
import com.com.navapp.ui.settings.downloadvoices.DownloadVoice;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.ApplicationContext;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.guidance.VoiceCatalog;
import com.ktm.navapp.ui.onboarding.PairYourBikeOnBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VoiceOnBoard
  extends NavAppBaseActivity
{
  @BindView(2131230807)
  protected View mBottomLine;
  @BindView(2131230817)
  protected TextView mCannotDownloadLabel;
  private VoiceManager mDownloadManager;
  @BindView(2131230861)
  protected RelativeLayout mDownloadingItem;
  @BindView(2131231002)
  protected ProgressBar mDownloadingItemProgressBar;
  private final BroadcastReceiver mNetworkStateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((MapEngine.isInitialized()) && (VoiceManager.getInstance().isOnlineMode()))
      {
        if (!VoiceManager.getInstance().isNetworkConnected()) {
          return;
        }
        if (!VoiceCatalog.getInstance().isLocalCatalogAvailable())
        {
          mDownloadManager.downloadCatalog(mOnDownloadCatalog);
          return;
        }
        mOnDownloadCatalog.success();
      }
    }
  };
  private OnDownloadCatalog mOnDownloadCatalog = new OnDownloadCatalog()
  {
    public void failed()
    {
      VoiceOnBoard.this.showNoInternetConnection();
    }
    
    public void success()
    {
      VoiceOnBoard.this.showDownloadingArea();
      VoiceOnBoard.this.refresh(true);
      mDownloadManager.setOnDownloadUpdate(new VoiceOnBoard.2.1(this));
    }
  };
  
  public VoiceOnBoard() {}
  
  private DownloadVoice createDownloadItem(VoicePackageInfo paramVoicePackageInfo)
  {
    if ((!mDownloadManager.onUninstallList(Long.valueOf(subtitle))) && ((mDownloadManager.getTaskType() != DownloadManager.TaskType.UNINSTALL) || (!mDownloadManager.isActualPackageId(Long.valueOf(subtitle)))))
    {
      if (isLocal) {
        return new DownloadVoice(paramVoicePackageInfo, DownloadingList.DataStatus.INSTALLED, 100);
      }
      if (mDownloadManager.isActualPackageId(Long.valueOf(subtitle))) {
        return new DownloadVoice(paramVoicePackageInfo, DownloadingList.DataStatus.INSTALLING, mDownloadManager.getDownloadProgress());
      }
      if (mDownloadManager.onInstallList(Long.valueOf(subtitle))) {
        return new DownloadVoice(paramVoicePackageInfo, DownloadingList.DataStatus.WAITING, 0);
      }
      return new DownloadVoice(paramVoicePackageInfo, DownloadingList.DataStatus.NOT_INSTALLED, 0);
    }
    return new DownloadVoice(paramVoicePackageInfo, DownloadingList.DataStatus.WAITING, 0);
  }
  
  private DownloadVoice findDefaultVoice(List paramList)
  {
    String str = Globals.getContext().getString(2131558653);
    Locale localLocale = Locale.getDefault();
    if (isLanguage(localLocale, "en", "us")) {
      str = Globals.getContext().getString(2131558654);
    } else if (isLanguage(localLocale, "de")) {
      str = Globals.getContext().getString(2131558652);
    } else if (isLanguage(localLocale, "es")) {
      str = Globals.getContext().getString(2131558655);
    } else if (isLanguage(localLocale, "fr")) {
      str = Globals.getContext().getString(2131558656);
    } else if (isLanguage(localLocale, "it")) {
      str = Globals.getContext().getString(2131558657);
    }
    int i = 0;
    while (i < paramList.size())
    {
      if (getheader.equals(str)) {
        return (DownloadVoice)paramList.get(i);
      }
      i += 1;
    }
    return null;
  }
  
  private void hideCannotDownloadLabel()
  {
    mCannotDownloadLabel.setVisibility(4);
  }
  
  private void hideDownloadingArea()
  {
    mDownloadingItem.setVisibility(4);
    mDownloadingItemProgressBar.setVisibility(4);
    mBottomLine.setVisibility(0);
    hideCannotDownloadLabel();
  }
  
  private void init()
  {
    hideDownloadingArea();
    try
    {
      MapEngine localMapEngine = MapEngine.getInstance();
      ApplicationContext localApplicationContext = new ApplicationContext(this);
      localMapEngine.init(localApplicationContext, new VoiceOnBoard.3(this));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean isLanguage(Locale paramLocale, String paramString)
  {
    return paramLocale.getLanguage().equals(new Locale(paramString).getLanguage());
  }
  
  private boolean isLanguage(Locale paramLocale, String paramString1, String paramString2)
  {
    return (paramLocale.getLanguage().equals(new Locale(paramString1).getLanguage())) && (paramLocale.getCountry().equals(new Locale(paramString1, paramString2).getCountry()));
  }
  
  private void refresh()
  {
    refresh(false);
  }
  
  private void refresh(boolean paramBoolean)
  {
    List localList1 = mDownloadManager.getInstalledVoicesLocalIds();
    mDownloadManager.getInstalledVoices();
    List localList2 = mDownloadManager.getAvailableVoices();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList2.size())
    {
      if ((paramBoolean) && (getisLocal)) {
        if ((localList1 != null) && (localList1.size() != 0))
        {
          int j = 0;
          while (j < localList1.size())
          {
            if (((Long)localList1.get(j)).equals(Long.valueOf(getsubtitle)))
            {
              j = 0;
              break label148;
            }
            j += 1;
          }
          j = 1;
          label148:
          if (j != 0) {
            mDownloadManager.uninstallVoice(getsubtitle, null);
          }
        }
        else
        {
          mDownloadManager.uninstallVoice(getsubtitle, null);
        }
      }
      localArrayList.add(createDownloadItem((VoicePackageInfo)localList2.get(i)));
      i += 1;
    }
    update(findDefaultVoice(localArrayList));
  }
  
  private void setToWaiting(TextView paramTextView, ProgressBar paramProgressBar, View... paramVarArgs)
  {
    paramTextView.setText(2131558701);
    paramProgressBar.setIndeterminate(true);
    showViews(new View[] { paramTextView, paramProgressBar });
    hideViews(paramVarArgs);
  }
  
  private void showDownloadingArea()
  {
    mDownloadingItem.setVisibility(0);
    hideCannotDownloadLabel();
  }
  
  private void showNoInternetConnection()
  {
    hideDownloadingArea();
    mCannotDownloadLabel.setText(Globals.getContext().getString(2131558568));
    mCannotDownloadLabel.setVisibility(0);
  }
  
  private void update(DownloadVoice paramDownloadVoice)
  {
    if (paramDownloadVoice == null) {
      return;
    }
    Object localObject = (TextView)findViewById(2131231003);
    ImageButton localImageButton1 = (ImageButton)findViewById(2131230872);
    ImageButton localImageButton2 = (ImageButton)findViewById(2131230870);
    ImageButton localImageButton3 = (ImageButton)findViewById(2131230869);
    TextView localTextView1 = (TextView)findViewById(2131231004);
    TextView localTextView2 = (TextView)findViewById(2131231001);
    ProgressBar localProgressBar = (ProgressBar)findViewById(2131231002);
    ((TextView)localObject).setText(header);
    localTextView2.setText(paramDownloadVoice.getDownloadSize());
    int i = 6.$SwitchMap$com$ktm$navapp$ui$settings$DownloadingList$DataStatus[status.ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i == 4)
          {
            mDownloadManager.setSelectedLanguage(packageId);
            localProgressBar.setIndeterminate(false);
            localTextView2.setText(paramDownloadVoice.getContentSize());
            showViews(new View[] { localImageButton3, localTextView2, mBottomLine });
            hideViews(new View[] { localImageButton1, localImageButton2, localTextView1, localProgressBar });
          }
        }
        else {
          setToWaiting(localTextView1, localProgressBar, new View[] { localImageButton1, localImageButton2, localImageButton3, localTextView2 });
        }
      }
      else
      {
        localProgressBar.setIndeterminate(false);
        localProgressBar.setProgress(progress);
        if (progress != 100)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramDownloadVoice.getDownloadedSize());
          ((StringBuilder)localObject).append("/");
          localTextView1.setText(((StringBuilder)localObject).toString());
          showViews(new View[] { localTextView1, localTextView2, localProgressBar, localImageButton2 });
          hideViews(new View[] { localImageButton1, localImageButton3, mBottomLine });
        }
        else
        {
          localTextView1.setText(2131558524);
          showViews(new View[] { localTextView1, localProgressBar, localImageButton2 });
          hideViews(new View[] { localImageButton1, localImageButton3, localTextView2, mBottomLine });
        }
      }
    }
    else
    {
      localProgressBar.setIndeterminate(false);
      showViews(new View[] { localImageButton1, localTextView2, mBottomLine });
      hideViews(new View[] { localImageButton2, localImageButton3, localTextView1, localProgressBar });
    }
    localImageButton1.setOnClickListener(new VoiceOnBoard.4(this, paramDownloadVoice, localTextView1, localProgressBar, localImageButton1, localImageButton2, localTextView2));
    localImageButton2.setOnClickListener(new VoiceOnBoard.5(this, paramDownloadVoice, localTextView1, localProgressBar, localImageButton1, localImageButton2, localTextView2));
  }
  
  protected boolean VoiceManagerIsNotAvailable()
  {
    return (!VoiceManager.getInstance().isOnlineMode()) || (!VoiceManager.getInstance().isNetworkConnected());
  }
  
  protected void hideViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(8);
      i += 1;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427368);
    ButterKnife.bind(this);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.VOICE.name());
    init();
  }
  
  public void onPause()
  {
    unregisterReceiver(mNetworkStateReceiver);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    registerReceiver(mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  protected void onSkipClick()
  {
    startActivity(new Intent(this, PairYourBikeOnBoard.class));
    finish();
  }
  
  protected void showViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(0);
      i += 1;
    }
  }
}
