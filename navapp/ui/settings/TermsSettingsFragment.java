package com.com.navapp.ui.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
import com.com.navapp.utilities.Utilities;

public class TermsSettingsFragment
  extends NavAppBaseFragment
{
  private KTMTermsSettingsFragment mKTMTermsSettingsFragment;
  private OpenSourceLicensesSettingsFragment mOpenSourceLicensesSettingsFragment;
  
  public TermsSettingsFragment() {}
  
  private void setKTMTermsSettingsView()
  {
    if (mKTMTermsSettingsFragment == null) {
      mKTMTermsSettingsFragment = new KTMTermsSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mKTMTermsSettingsFragment).addToBackStack(null).commit();
  }
  
  private void setOpenSourceLicensesSettingsView()
  {
    if (mOpenSourceLicensesSettingsFragment == null) {
      mOpenSourceLicensesSettingsFragment = new OpenSourceLicensesSettingsFragment();
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mOpenSourceLicensesSettingsFragment).addToBackStack(null).commit();
  }
  
  public void closeUnitsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427434, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onHEREPrivacySettings()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://legal.here.com/en-gb/privacy")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Utilities.toastShort("No activity found");
  }
  
  public void onHERESecuritySettings()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://legal.here.com/en-gb/security")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Utilities.toastShort("No activity found");
  }
  
  public void onHERETermsSettings()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://legal.here.com/en-gb/terms")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Utilities.toastShort("No activity found");
  }
  
  public void onKTMTermsSettings()
  {
    setKTMTermsSettingsView();
  }
  
  public void onOpenSourceLicensesSettings()
  {
    setOpenSourceLicensesSettingsView();
  }
}
