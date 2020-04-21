package com.com.navapp.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.kmrc.KmrcVersion;
import com.com.navapp.Globals;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.here.android.ui.common.Version;

public class InfoSettingsFragment
  extends NavAppBaseFragment
{
  private static final boolean SHOW_DETAILED_VERSION_INFORMATION = false;
  @BindView(2131230793)
  TextView mTxtAppBuildNumber;
  @BindView(2131230794)
  TextView mTxtAppVersion;
  @BindView(2131230973)
  TextView mTxtHereSdkVersion;
  @BindView(2131231006)
  TextView mTxtKmrcVersion;
  @BindView(2131230986)
  View mVwMoreInfo;
  
  public InfoSettingsFragment() {}
  
  public void closeInfoSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    mTxtAppVersion.setText("2.3.2");
    mTxtAppBuildNumber.setText(Globals.getContext().getString(2131558447));
    mTxtKmrcVersion.setText(KmrcVersion.getVersion());
    mTxtHereSdkVersion.setText(Version.getSdkVersion());
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427403, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mVwMoreInfo.setVisibility(4);
    return paramLayoutInflater;
  }
}
