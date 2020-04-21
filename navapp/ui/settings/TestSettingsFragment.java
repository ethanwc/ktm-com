package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.FlicHandler;
import com.com.navapp.logging.LogExt;
import com.com.navapp.managers.MapManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.widgets.SliderCheckbox;

public class TestSettingsFragment
  extends NavAppBaseFragment
{
  @BindView(2131231207)
  protected Button mButtonDelete;
  @BindView(2131231209)
  protected SliderCheckbox mExtendedLoggingSwitch;
  @BindView(2131231168)
  protected View mScreenDelete;
  @BindView(2131231169)
  protected View mScreenHelp;
  @BindView(2131231341)
  protected WebView mWebview;
  
  public TestSettingsFragment() {}
  
  private void refreshUI()
  {
    mExtendedLoggingSwitch.setChecked(LogExt.isLoggingEnabled());
    mButtonDelete.setEnabled(LogExt.isLoggingEnabled() ^ true);
  }
  
  public void closeSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onClickLoggingSwitch()
  {
    LogExt.enableLogging(mExtendedLoggingSwitch.isChecked());
    refreshUI();
  }
  
  public void onCloseHelp()
  {
    mScreenHelp.setVisibility(8);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427435, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onDeleteFiles()
  {
    mScreenDelete.setVisibility(0);
  }
  
  public void onDeleteNo()
  {
    mScreenDelete.setVisibility(8);
  }
  
  public void onDeleteYes()
  {
    LogExt.deleteLogFiles();
    mScreenDelete.setVisibility(8);
  }
  
  public void onDestroy()
  {
    MapManager.getInstance().clearMarkers();
    super.onDestroy();
  }
  
  public void onGrabFlicButton()
  {
    FlicHandler.grabButton(getActivity());
  }
  
  public void onHelpButton()
  {
    mScreenHelp.setVisibility(0);
    mWebview.loadUrl("file:///android_asset/extlogging_help.html");
  }
  
  public void onResume()
  {
    super.onResume();
    refreshUI();
  }
}
