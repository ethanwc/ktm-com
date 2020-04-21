package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.ButterKnife;
import com.com.navapp.search.SearchHistory;
import com.com.navapp.ui.base.NavAppBaseFragment;

public class ClearHistorySettingsFragment
  extends NavAppBaseFragment
{
  public ClearHistorySettingsFragment() {}
  
  public void closeClearHistorySettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onClearHistory()
  {
    SearchHistory.clearHistory();
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427392, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
}
