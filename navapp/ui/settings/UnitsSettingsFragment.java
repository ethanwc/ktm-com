package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.here.android.ui.guidance.NavigationManager.UnitSystem;

public class UnitsSettingsFragment
  extends NavAppBaseFragment
{
  @BindView(2131231322)
  protected RadioButton mImperialUkRadioButton;
  @BindView(2131231324)
  protected RadioButton mImperialUsRadioButton;
  @BindView(2131231326)
  protected RadioButton mMetricRadioButton;
  private OptionsManager mOptionsManager;
  
  public UnitsSettingsFragment() {}
  
  private void configRadioButtons()
  {
    NavigationManager.UnitSystem localUnitSystem = mOptionsManager.getUnitSystem();
    if (localUnitSystem == NavigationManager.UnitSystem.METRIC)
    {
      mMetricRadioButton.setChecked(true);
      return;
    }
    if (localUnitSystem == NavigationManager.UnitSystem.IMPERIAL)
    {
      mImperialUkRadioButton.setChecked(true);
      return;
    }
    if (localUnitSystem == NavigationManager.UnitSystem.IMPERIAL_US) {
      mImperialUsRadioButton.setChecked(true);
    }
  }
  
  public void closeUnitsSettings()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onClickImperialUkLabel()
  {
    mImperialUkRadioButton.performClick();
    onClickImperialUkRadioButton();
  }
  
  public void onClickImperialUkRadioButton()
  {
    mOptionsManager.setUnitSystem(NavigationManager.UnitSystem.IMPERIAL);
  }
  
  public void onClickImperialUsLabel()
  {
    mImperialUsRadioButton.performClick();
    onClickImperialUsRadioButton();
  }
  
  public void onClickImperialUsRadioButton()
  {
    mOptionsManager.setUnitSystem(NavigationManager.UnitSystem.IMPERIAL_US);
  }
  
  public void onClickMetricLabel()
  {
    mMetricRadioButton.performClick();
    onClickMetricRadioButton();
  }
  
  public void onClickMetricRadioButton()
  {
    mOptionsManager.setUnitSystem(NavigationManager.UnitSystem.METRIC);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427436, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mOptionsManager = OptionsManager.getInstance();
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    configRadioButtons();
  }
}
