package com.com.navapp.ui.mainscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.Globals;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.downloadmaps.InstalledMapsSettingsFragment;
import com.com.navapp.utilities.Utilities;
import java.util.Locale;

public class MapUpdateFragment
  extends NavAppBaseFragment
{
  static final String CLASS_TAG = com.ktm.navapp.ui.mainscreen.MapUpdateFragment.class.getSimpleName();
  @BindView(2131231050)
  Button mBtUpdate;
  
  public MapUpdateFragment() {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427412, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    paramViewGroup = String.format(Locale.getDefault(), "%s<br><b>%s</b>", new Object[] { Globals.getContext().getString(2131558557), MapDataUpdate.getSizeText(100) });
    mBtUpdate.setText(Utilities.replaceBoldWithFontAndSize(paramViewGroup, "fonts/TradeGothicLTStd-Cn18.otf", 16.0F));
    return paramLayoutInflater;
  }
  
  public void onSnoozeButton()
  {
    RealmLog.i(CLASS_TAG, "onSnoozeButton()");
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onUpdateButton()
  {
    RealmLog.i(CLASS_TAG, "onUpdateButton()");
    if (MapDataUpdate.startUpdate(getActivity()))
    {
      getActivity().getSupportFragmentManager().popBackStack();
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new InstalledMapsSettingsFragment()).addToBackStack(null).commit();
    }
  }
}
