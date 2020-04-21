package com.com.navapp.ui.settings.downloadmaps;

import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.DownloadManager.TaskType;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.settings.DownloadingList.DataStatus;
import com.here.android.ui.odml.MapPackage.InstallationState;
import com.ktm.navapp.downloading.offlinemaps.OfflineMapsManager.OfflineMapsError;
import java.util.List;

public abstract class MapsSettingsFragment
  extends com.ktm.navapp.ui.settings.DownloadingList<com.ktm.navapp.downloading.offlinemaps.MapPackageInfo, com.ktm.navapp.ui.settings.downloadmaps.DownloadMap, Integer, OfflineMapsManager.OfflineMapsError>
{
  public MapsSettingsFragment() {}
  
  public void closeOfflineMapsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  protected DownloadMap createDownloadItem(com.com.navapp.downloading.offlinemaps.MapPackageInfo paramMapPackageInfo)
  {
    if ((!mDownloadManager.onUninstallList(Integer.valueOf(subtitle))) && ((mDownloadManager.getTaskType() != DownloadManager.TaskType.UNINSTALL) || (!mDownloadManager.isActualPackageId(Integer.valueOf(subtitle)))))
    {
      if (installationState == MapPackage.InstallationState.INSTALLED) {
        return new DownloadMap(paramMapPackageInfo, DownloadingList.DataStatus.INSTALLED, 100);
      }
      if ((mDownloadManager.isActualPackageId(Integer.valueOf(subtitle))) && (!mDownloadManager.isActualCanceled())) {
        return new DownloadMap(paramMapPackageInfo, DownloadingList.DataStatus.INSTALLING, mDownloadManager.getDownloadProgress());
      }
      if (mDownloadManager.onInstallList(Integer.valueOf(subtitle))) {
        return new DownloadMap(paramMapPackageInfo, DownloadingList.DataStatus.WAITING, 0);
      }
      return new DownloadMap(paramMapPackageInfo, DownloadingList.DataStatus.NOT_INSTALLED, 0);
    }
    return new DownloadMap(paramMapPackageInfo, DownloadingList.DataStatus.WAITING, 0);
  }
  
  protected MapsListAdapter getAdapter()
  {
    return new MapsListAdapter(getContext(), mDownloadItems);
  }
  
  protected List getItems()
  {
    return OfflineMapsManager.getInstance().getInstalledCountries();
  }
  
  protected int getLayoutResource()
  {
    return 2131427404;
  }
  
  protected void initializeManager()
  {
    mDownloadManager = OfflineMapsManager.getInstance();
    OfflineMapsManager.getInstance().setMainScreen((MainScreen)getActivity());
  }
}
