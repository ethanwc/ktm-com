package com.com.navapp.ui.settings.downloadvoices;

import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.DownloadManager.TaskType;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.settings.DownloadingList.DataStatus;
import com.ktm.navapp.downloading.voice.VoiceManager.VoiceError;

public abstract class VoicesSettingsFragment
  extends com.ktm.navapp.ui.settings.DownloadingList<com.ktm.navapp.downloading.voice.VoicePackageInfo, com.ktm.navapp.ui.settings.downloadvoices.DownloadVoice, Long, VoiceManager.VoiceError>
{
  public VoicesSettingsFragment() {}
  
  public void closeOfflineMapsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  protected DownloadVoice createDownloadItem(com.com.navapp.downloading.voice.VoicePackageInfo paramVoicePackageInfo)
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
  
  protected int getLayoutResource()
  {
    return 2131427405;
  }
  
  protected void initializeManager()
  {
    mDownloadManager = VoiceManager.getInstance();
    VoiceManager.getInstance().setMainScreen((MainScreen)getActivity());
  }
  
  protected void update()
  {
    VoiceManager.getInstance().isSelectedPackageInstalled();
    super.update();
  }
}
