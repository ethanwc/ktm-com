package com.com.navapp.ui.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.settings.common.SimpleFullscreenFragment;
import com.com.navapp.ui.widgets.SliderCheckbox;
import com.com.navapp.utilities.Utilities;

public class HamburgerFragment
  extends NavAppBaseFragment
{
  @BindView(2131230961)
  RadioButton mMapRadioButton;
  private final BroadcastReceiver mNetworkStateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (!OfflineMapsManager.getInstance().isNetworkConnected())
      {
        if ((!mOfflineMapsSwitch.isChecked()) && (!isInitialStickyBroadcast())) {
          Utilities.toastShort(2131558568);
        }
        mOfflineMapsSwitch.setChecked(true);
        return;
      }
      mOfflineMapsSwitch.setChecked(mOptionsManager.getUseOfflineMaps());
    }
  };
  @BindView(2131230963)
  SliderCheckbox mOfflineMapsSwitch;
  private OptionsManager mOptionsManager;
  @BindView(2131230965)
  RadioButton mSatelliteRadioButton;
  @BindView(2131230968)
  RadioButton mTerrainRadioButton;
  @BindView(2131230970)
  SliderCheckbox mTrafficInfoSwitch;
  
  public HamburgerFragment() {}
  
  private void configButtons()
  {
    configRadioButtons();
    configSwitchButtons();
  }
  
  private void configRadioButtons()
  {
    String str = mOptionsManager.getMapType();
    int i = str.hashCode();
    if (i != -1256825323)
    {
      if (i != -978706327)
      {
        if ((i == 1571582122) && (str.equals("hybrid.day")))
        {
          i = 2;
          break label78;
        }
      }
      else if (str.equals("satellite.day"))
      {
        i = 1;
        break label78;
      }
    }
    else if (str.equals("normal.day"))
    {
      i = 0;
      break label78;
    }
    i = -1;
    label78:
    if (i != 0)
    {
      if ((i != 1) && (i != 2))
      {
        mTerrainRadioButton.setChecked(true);
        return;
      }
      mSatelliteRadioButton.setChecked(true);
      return;
    }
    mMapRadioButton.setChecked(true);
  }
  
  private void configSwitchButtons()
  {
    mTrafficInfoSwitch.setChecked(mOptionsManager.getTrafficInformation());
  }
  
  public void aboutSettings()
  {
    ((MainScreen)getActivity()).setAboutSettingsView();
  }
  
  public void advancedSettings()
  {
    ((MainScreen)getActivity()).setAdvancedSettingsView();
  }
  
  public void closeSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onClickMapRadioButton()
  {
    if (mMapRadioButton.isChecked()) {
      mOptionsManager.setMapType("normal.day");
    }
  }
  
  public void onClickOfflineMapsSwitch()
  {
    if ((mOfflineMapsSwitch.isChecked()) && (!OfflineMapsManager.getInstance().isAnyMapInstalled()))
    {
      mOfflineMapsSwitch.setChecked(false);
      SimpleFullscreenFragment localSimpleFullscreenFragment = SimpleFullscreenFragment.newInstance(2131558663, 2131558662);
      getActivity().getSupportFragmentManager().beginTransaction().add(2131231033, localSimpleFullscreenFragment).addToBackStack(null).commit();
    }
    if ((!mOfflineMapsSwitch.isChecked()) && (!OfflineMapsManager.getInstance().isNetworkConnected()))
    {
      mOfflineMapsSwitch.setChecked(true);
      Utilities.toastLong(2131558681);
      return;
    }
    mOptionsManager.setUseOfflineMaps(mOfflineMapsSwitch.isChecked());
  }
  
  public void onClickSatelliteRadioButton()
  {
    if (mSatelliteRadioButton.isChecked())
    {
      if (!OfflineMapsManager.getInstance().isNetworkConnected()) {
        Utilities.toastLong(2131558682);
      }
      mOptionsManager.setMapType("hybrid.day");
    }
  }
  
  public void onClickTerrainRadioButton()
  {
    if (mTerrainRadioButton.isChecked()) {
      mOptionsManager.setMapType("terrain.day");
    }
  }
  
  public void onClickTrafficInfoSwitch()
  {
    if ((mTrafficInfoSwitch.isChecked()) && (!OfflineMapsManager.getInstance().isNetworkConnected()))
    {
      mTrafficInfoSwitch.setChecked(false);
      Utilities.toastLong(2131558683);
      return;
    }
    mOptionsManager.setTrafficInformation(mTrafficInfoSwitch.isChecked());
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427402, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mOptionsManager = OptionsManager.getInstance();
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
    configButtons();
    getActivity().registerReceiver(mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
}
