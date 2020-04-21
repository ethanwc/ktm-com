package com.com.navapp.ui.settings.downloadmaps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import com.com.navapp.Globals;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate.Availability;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate.MapDataUpdateEvent;
import com.com.navapp.downloading.offlinemaps.MapStorageLocation;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.DownloadingList;
import com.com.navapp.ui.settings.downloadmaps.region.RegionMapsSettingsFragment;
import com.com.navapp.utilities.Events;
import java.util.ArrayList;
import java.util.List;

public class InstalledMapsSettingsFragment
  extends MapsSettingsFragment
{
  static final String FLOAT = com.ktm.navapp.ui.settings.downloadmaps.InstalledMapsSettingsFragment.class.getSimpleName();
  @BindView(2131230992)
  protected TextView mAvailableMemory;
  private final BroadcastReceiver mConnectivityChangedReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      onConnectivityChanged();
    }
  };
  @BindView(2131230991)
  protected ProgressBar mProgressBar;
  protected ImageButton mUpBtCancel;
  protected ImageButton mUpBtDownload;
  protected ProgressBar mUpPgProgress;
  protected TextView mUpTvProg1;
  protected TextView mUpTvProg2;
  protected TextView mUpTvTitle;
  @BindView(2131231049)
  protected TextView mUpdateViewButtonSearch;
  @BindView(2131231054)
  protected View mUpdateViewOutdated;
  @BindView(2131231055)
  protected View mUpdateViewSeparator;
  @BindView(2131231053)
  protected TextView mUpdateViewTextSearch;
  @BindView(2131231056)
  protected View mUpdateViewUnkown;
  @BindView(2131231057)
  protected View mUpdateViewUpdating;
  @BindView(2131231058)
  protected View mUpdateViewUptodate;
  
  public InstalledMapsSettingsFragment() {}
  
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
  
  protected List getItems()
  {
    return OfflineMapsManager.getInstance().getInstalledCountries(true);
  }
  
  protected void initialize()
  {
    super.initialize();
    configAvailableMemory();
  }
  
  void onConnectivityChanged()
  {
    if ((OfflineMapsManager.getInstance().isOnlineMode()) && (OfflineMapsManager.getInstance().isNetworkConnected()) && (OfflineMapsManager.getInstance().getRegions().size() == 0)) {
      OfflineMapsManager.getInstance().reloadMapPackages(new InstalledMapsSettingsFragment.3(this));
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    mUpBtDownload = ((ImageButton)mUpdateViewUpdating.findViewById(2131230865));
    mUpBtCancel = ((ImageButton)mUpdateViewUpdating.findViewById(2131230863));
    mUpPgProgress = ((ProgressBar)mUpdateViewUpdating.findViewById(2131230866));
    mUpTvTitle = ((TextView)mUpdateViewUpdating.findViewById(2131230867));
    mUpTvProg1 = ((TextView)mUpdateViewUpdating.findViewById(2131230868));
    mUpTvProg2 = ((TextView)mUpdateViewUpdating.findViewById(2131230864));
    mUpBtDownload.setVisibility(4);
    mUpBtCancel.setVisibility(0);
    mUpBtCancel.setOnClickListener(new InstalledMapsSettingsFragment.2(this));
    mUpTvTitle.setText(2131558553);
    mUpdateViewUptodate.setVisibility(8);
    mUpdateViewOutdated.setVisibility(8);
    mUpdateViewUnkown.setVisibility(8);
    mUpdateViewSeparator.setVisibility(8);
    mUpdateViewUpdating.setVisibility(8);
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    if (mDownloadManager != null) {
      mDownloadManager.setOnDownloadUpdate(null);
    }
    super.onDestroy();
  }
  
  public void onDownloadMapsSettings()
  {
    if (!MapDataUpdate.isUpdating()) {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new RegionMapsSettingsFragment()).addToBackStack(null).commit();
    }
  }
  
  public void onPause()
  {
    Events.unregister(this);
    getActivity().unregisterReceiver(mConnectivityChangedReceiver);
    super.onPause();
  }
  
  public void onRefresh(MapDataUpdate.MapDataUpdateEvent paramMapDataUpdateEvent)
  {
    boolean bool = MapDataUpdate.isUpdating();
    Object localObject;
    if ((paramMapDataUpdateEvent != null) && (!stateChanged))
    {
      if ((!isMapUpdate) && (mListAdapter != null)) {
        mListAdapter.notifyDataSetChanged();
      }
    }
    else
    {
      if (bool)
      {
        mUpdateViewUptodate.setVisibility(8);
        mUpdateViewOutdated.setVisibility(8);
        mUpdateViewUnkown.setVisibility(8);
        mUpdateViewSeparator.setVisibility(8);
        mUpdateViewUpdating.setVisibility(0);
      }
      else
      {
        mUpdateViewButtonSearch.setEnabled(true);
        mUpdateViewButtonSearch.setTextColor(Globals.getContext().getColor(2131034172));
        mUpdateViewTextSearch.setTextColor(Globals.getContext().getColor(2131034194));
        paramMapDataUpdateEvent = MapDataUpdate.getAvailability();
        localObject = mUpdateViewUptodate;
        int i;
        if (paramMapDataUpdateEvent == MapDataUpdate.Availability.NO) {
          i = 0;
        } else {
          i = 8;
        }
        ((View)localObject).setVisibility(i);
        localObject = mUpdateViewOutdated;
        if (paramMapDataUpdateEvent == MapDataUpdate.Availability.YES) {
          i = 0;
        } else {
          i = 8;
        }
        ((View)localObject).setVisibility(i);
        localObject = mUpdateViewUnkown;
        if (paramMapDataUpdateEvent == MapDataUpdate.Availability.UNKNOWN) {
          i = 0;
        } else {
          i = 8;
        }
        ((View)localObject).setVisibility(i);
        mUpdateViewSeparator.setVisibility(0);
        mUpdateViewUpdating.setVisibility(8);
      }
      if (mListAdapter != null) {
        mListAdapter.notifyDataSetChanged();
      }
    }
    if (bool)
    {
      mUpPgProgress.setProgress(MapDataUpdate.getProgress());
      paramMapDataUpdateEvent = mUpTvProg1;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(MapDataUpdate.getSizeText(MapDataUpdate.getProgress()));
      ((StringBuilder)localObject).append("/");
      paramMapDataUpdateEvent.setText(((StringBuilder)localObject).toString());
      mUpTvProg2.setText(MapDataUpdate.getSizeText(100));
    }
    configAvailableMemory();
  }
  
  public void onResume()
  {
    super.onResume();
    getActivity().registerReceiver(mConnectivityChangedReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    Events.register(this);
    onRefresh(null);
  }
  
  public void onSearchUpdate()
  {
    if (MapDataUpdate.searchUpdate(getActivity()))
    {
      mUpdateViewButtonSearch.setEnabled(false);
      mUpdateViewButtonSearch.setTextColor(Globals.getContext().getColor(2131034200));
      mUpdateViewTextSearch.setTextColor(Globals.getContext().getColor(2131034200));
    }
  }
  
  public void onUpdateMaps()
  {
    if (MapDataUpdate.startUpdate(getActivity())) {
      onRefresh(null);
    }
  }
  
  protected void performUpdate()
  {
    mDownloadManager.performInstall();
    mDownloadManager.setOnDownloadUpdate(new InstalledMapsSettingsFragment.4(this));
  }
}
