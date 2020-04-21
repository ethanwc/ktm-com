package com.com.navapp.riser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.ui.routing.RouteWaypointsFragment;
import com.com.navapp.ui.widgets.SliderCheckbox;

public class SmartSettingsRouteFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  final String LOGTAG = com.ktm.navapp.riser.SmartSettingsRouteFragment.class.getSimpleName();
  @BindView(2131231227)
  NumberPicker mDurationHoursPicker;
  @BindView(2131231228)
  NumberPicker mDurationMinutesPicker;
  @BindView(2131231226)
  View mDurationView;
  @BindView(2131231230)
  SeekBar mFlowSeekbar;
  @BindView(2131231231)
  TextView mFlowValues;
  @BindView(2131230972)
  TextView mHeaderText;
  private boolean mNoRouteFound = false;
  @BindView(2131231235)
  SliderCheckbox mShowDurationSwitch;
  
  public SmartSettingsRouteFragment() {}
  
  private void applyOptions()
  {
    Trip localTrip = TripManager.getTrip();
    smartRouteUseDuration = mShowDurationSwitch.isChecked();
    smartRouteDurationMinutes = (mDurationHoursPicker.getValue() * 60 + mDurationMinutesPicker.getValue());
    smartRouteWeightFactor100 = mFlowSeekbar.getProgress();
    TripManager.setTrip(localTrip);
  }
  
  private boolean areOptionsChanged()
  {
    Trip localTrip = TripManager.getTrip();
    int i;
    if (smartRouteUseDuration != mShowDurationSwitch.isChecked()) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i == 0) && (smartRouteDurationMinutes == mDurationHoursPicker.getValue() * 60 + mDurationMinutesPicker.getValue())) {
      i = 0;
    } else {
      i = 1;
    }
    if (i == 0) {
      return smartRouteWeightFactor100 != mFlowSeekbar.getProgress();
    }
    return true;
  }
  
  private void refreshUi()
  {
    Trip localTrip = TripManager.getTrip();
    mHeaderText.setText(localTrip.getTitle());
    mShowDurationSwitch.setChecked(smartRouteUseDuration);
    mDurationMinutesPicker.setMinValue(0);
    mDurationMinutesPicker.setMaxValue(59);
    mDurationMinutesPicker.setValue(smartRouteDurationMinutes % 60);
    mDurationHoursPicker.setMinValue(0);
    mDurationHoursPicker.setMaxValue(24);
    mDurationHoursPicker.setValue(Math.min(24, smartRouteDurationMinutes / 60));
    onClickShowDurationSwitch();
    mFlowSeekbar.setProgress(Math.max(0, Math.min(100, smartRouteWeightFactor100)));
  }
  
  public void closeRouteOptions()
  {
    TripManager.restoreTrip(false);
    getActivity().getSupportFragmentManager().popBackStack();
    if (mNoRouteFound) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
  }
  
  public void onAddWaypoints()
  {
    applyOptions();
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, RouteWaypointsFragment.newInstance(false)).addToBackStack(null).commit();
  }
  
  public boolean onBackPressed()
  {
    closeRouteOptions();
    return true;
  }
  
  public void onButtonSave()
  {
    applyOptions();
    ((MainScreen)getActivity()).setSaveTripView();
  }
  
  public void onClickShowDurationSwitch()
  {
    View localView = mDurationView;
    int i;
    if (mShowDurationSwitch.isChecked()) {
      i = 0;
    } else {
      i = 8;
    }
    localView.setVisibility(i);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427431, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    mFlowSeekbar.setOnSeekBarChangeListener(new SmartSettingsRouteFragment.1(this));
    return paramLayoutInflater;
  }
  
  public void onDone()
  {
    if (areOptionsChanged()) {
      applyOptions();
    }
    if (!getActivity().getSupportFragmentManager().popBackStackImmediate("ROUTE_PREVIEW_FRAGMENT_TAG", 0))
    {
      RealmLog.i(LOGTAG, "New Preview");
      ((MainScreen)getActivity()).setRoutePreviewView(false);
      return;
    }
    RealmLog.i(LOGTAG, "Preview from backstack");
  }
  
  public void onResume()
  {
    super.onResume();
    refreshUi();
  }
  
  public SmartSettingsRouteFragment setNoRouteFound()
  {
    mNoRouteFound = true;
    return this;
  }
}
