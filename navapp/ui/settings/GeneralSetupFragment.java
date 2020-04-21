package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.ButterKnife;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.downloadmaps.InstalledMapsSettingsFragment;
import com.com.navapp.ui.settings.downloadmaps.MapsStorageFragment;
import com.com.navapp.ui.settings.downloadvoices.InstalledVoicesSettingsFragment;

public class GeneralSetupFragment
  extends NavAppBaseFragment
{
  private ClearHistorySettingsFragment mClearHistorySettingsFragment;
  private InstalledVoicesSettingsFragment mInstalledVoicesSettingsFragment;
  private TermsSettingsFragment mTermsSettingsFragment;
  private UnitsSettingsFragment mUnitsSettingsFragment;
  
  public GeneralSetupFragment() {}
  
  private void setInstalledMapsSettingsView()
  {
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new InstalledMapsSettingsFragment()).addToBackStack(null).commit();
  }
  
  private void setInstalledVoicesSettingsView()
  {
    if (mInstalledVoicesSettingsFragment == null) {
      mInstalledVoicesSettingsFragment = new InstalledVoicesSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mInstalledVoicesSettingsFragment).addToBackStack(null).commit();
  }
  
  private void setUnitsSettingsView()
  {
    if (mUnitsSettingsFragment == null) {
      mUnitsSettingsFragment = new UnitsSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mUnitsSettingsFragment).addToBackStack(null).commit();
  }
  
  public void closeAdvancedSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onClearHistory()
  {
    if (mClearHistorySettingsFragment == null) {
      mClearHistorySettingsFragment = new ClearHistorySettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mClearHistorySettingsFragment).addToBackStack(null).commit();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427396, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onDownloadMapsSettings()
  {
    setInstalledMapsSettingsView();
  }
  
  public void onStorageLocationSettings()
  {
    if (!MapDataUpdate.isUpdating()) {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new MapsStorageFragment()).addToBackStack(null).commit();
    }
  }
  
  public void onUnitsSettings()
  {
    setUnitsSettingsView();
  }
  
  public void onVoicesSettings()
  {
    setInstalledVoicesSettingsView();
  }
}
