package com.com.navapp.ui.settings.downloadmaps.region;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.downloadmaps.DownloadMapsSettingsFragment;
import com.ktm.navapp.ui.settings.downloadmaps.region.RegionMap;
import java.util.ArrayList;

public class RegionMapsSettingsFragment
  extends NavAppBaseFragment
{
  public static final String ARG_REGION_ID = "REGION_ID";
  public static final String ARG_REGION_NAME = "REGION_NAME";
  private DownloadMapsSettingsFragment mDownloadMapsSettingsFragment;
  @BindView(2131230860)
  TextView mHeader;
  private ArrayList<RegionMap> mItems;
  @BindView(2131230857)
  ListView mList;
  private ArrayAdapter<RegionMap> mListAdapter;
  private final BroadcastReceiver mNetworkStateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (OfflineMapsManager.getInstance().isOnlineMode())
      {
        if (!OfflineMapsManager.getInstance().isNetworkConnected()) {
          return;
        }
        RegionMapsSettingsFragment.access$002(RegionMapsSettingsFragment.this, OfflineMapsManager.getInstance().getRegions());
        if (mItems.size() == 0) {
          OfflineMapsManager.getInstance().reloadMapPackages(new RegionMapsSettingsFragment.1.1(this));
        }
      }
    }
  };
  @BindView(2131231087)
  TextView mNoItemsText;
  
  public RegionMapsSettingsFragment() {}
  
  private void checkListStatus()
  {
    if (mNoItemsText == null) {
      return;
    }
    try
    {
      int i = mItems.size();
      if (i == 0)
      {
        mNoItemsText.setVisibility(0);
        return;
      }
      mNoItemsText.setVisibility(8);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
    }
    mNoItemsText.setVisibility(0);
  }
  
  private void configClickListener()
  {
    mList.setOnItemClickListener(new RegionMapsSettingsFragment.2(this));
  }
  
  private void initialize()
  {
    mItems = OfflineMapsManager.getInstance().getRegions();
    mListAdapter = new RegionMapsListAdapter(getContext(), mItems);
    mList.setAdapter(mListAdapter);
    configClickListener();
    mHeader.setText(2131558639);
    checkListStatus();
  }
  
  private void setDownloadMapsSettingsView(Bundle paramBundle)
  {
    if (mDownloadMapsSettingsFragment == null) {
      mDownloadMapsSettingsFragment = new DownloadMapsSettingsFragment();
    }
    mDownloadMapsSettingsFragment.setArguments(paramBundle);
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, mDownloadMapsSettingsFragment).addToBackStack(null).commit();
  }
  
  public void closeOfflineMapsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427394, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    initialize();
    return paramLayoutInflater;
  }
  
  public void onPause()
  {
    getActivity().unregisterReceiver(mNetworkStateReceiver);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    getActivity().registerReceiver(mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
}
