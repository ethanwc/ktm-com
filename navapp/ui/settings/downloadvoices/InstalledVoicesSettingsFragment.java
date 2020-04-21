package com.com.navapp.ui.settings.downloadvoices;

import android.content.res.Resources;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import com.com.navapp.Globals;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.MapStorageLocation;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.downloading.voice.VoicePackageInfo;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.ui.settings.DownloadingList;
import java.util.List;

public class InstalledVoicesSettingsFragment
  extends VoicesSettingsFragment
  implements OnBackPressedHandler
{
  @BindView(2131230997)
  protected TextView mAvailableMemory;
  private DownloadVoicesSettingsFragment mDownloadVoicesSettingsFragment;
  @BindView(2131230998)
  protected ProgressBar mProgressBar;
  
  public InstalledVoicesSettingsFragment() {}
  
  private void cancelUpdate()
  {
    mDownloadManager.setOnDownloadUpdate(null);
  }
  
  private void configAvailableMemory()
  {
    long l1 = MapStorageLocation.getAvailable();
    long l2 = MapStorageLocation.getTotal();
    String str;
    if (MapStorageLocation.getUseExternalStorage()) {
      str = getResources().getString(2131558549);
    } else {
      str = getResources().getString(2131558546);
    }
    mAvailableMemory.setText(String.format("%s %s %s", new Object[] { str, Formatter.formatFileSize(Globals.getContext(), l1), getResources().getString(2131558439) }));
    mProgressBar.setProgress(100 - (int)(l1 * 100.0D / l2));
  }
  
  public void closeOfflineMapsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  protected InstalledVoicesListAdapter getAdapter()
  {
    return new InstalledVoicesListAdapter(getContext(), mDownloadItems);
  }
  
  protected List getItems()
  {
    List localList = VoiceManager.getInstance().getInstalledVoices();
    localList.add(new VoicePackageInfo(getString(2131558659), null));
    return localList;
  }
  
  protected void initialize()
  {
    super.initialize();
    configAvailableMemory();
  }
  
  public boolean onBackPressed()
  {
    closeOfflineMapsSettings();
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    cancelUpdate();
  }
  
  public void onDownloadVoicesSettings()
  {
    if (mDownloadVoicesSettingsFragment == null) {
      mDownloadVoicesSettingsFragment = new DownloadVoicesSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mDownloadVoicesSettingsFragment).addToBackStack(null).commit();
  }
  
  protected void performUpdate()
  {
    mDownloadManager.performInstall();
    mDownloadManager.setOnDownloadUpdate(new InstalledVoicesSettingsFragment.1(this));
  }
}
