package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.ButterKnife;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.utilities.Utilities;

public class SwitchToOnlineFragment
  extends NavAppBaseFragment
{
  public SwitchToOnlineFragment() {}
  
  public void closeSwitchToOnline()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427432, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void switchToOnlineAndClose()
  {
    if (OfflineMapsManager.getInstance().isNetworkConnected()) {
      OptionsManager.getInstance().setUseOfflineMaps(false);
    } else {
      Utilities.toastLong(2131558681);
    }
    getActivity().getSupportFragmentManager().popBackStack();
  }
}
