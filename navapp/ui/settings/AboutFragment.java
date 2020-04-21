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
import com.com.navapp.ui.base.NavAppBaseFragment;

public class AboutFragment
  extends NavAppBaseFragment
{
  private TermsSettingsFragment mTermsSettingsFragment;
  
  public AboutFragment() {}
  
  private void setTermsSettingsView()
  {
    if (mTermsSettingsFragment == null) {
      mTermsSettingsFragment = new TermsSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mTermsSettingsFragment).addToBackStack(null).commit();
  }
  
  public void closeAboutSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427390, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onFaqSettings()
  {
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new FaqSettingsFragment()).addToBackStack(null).commit();
  }
  
  public void onInfoSettings()
  {
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new InfoSettingsFragment()).addToBackStack(null).commit();
  }
  
  public void onTermsSettings()
  {
    setTermsSettingsView();
  }
}
