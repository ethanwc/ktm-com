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
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.ui.widgets.SliderCheckbox;

public class SmartSettingsRoundtripFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  final String LOGTAG = com.ktm.navapp.riser.SmartSettingsRoundtripFragment.class.getSimpleName();
  @BindView(2131231223)
  NumberPicker mDistancePicker1;
  @BindView(2131231224)
  NumberPicker mDistancePicker10;
  @BindView(2131231225)
  NumberPicker mDistancePicker100;
  @BindView(2131231222)
  View mDistanceView;
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
  @BindView(2131231233)
  SliderCheckbox mShowDistanceSwitch;
  @BindView(2131231235)
  SliderCheckbox mShowDurationSwitch;
  
  public SmartSettingsRoundtripFragment() {}
  
  private void applyOptions()
  {
    Trip localTrip = TripManager.getTrip();
    smartRoundUseDuration = mShowDurationSwitch.isChecked();
    smartRoundDurationMinutes = (mDurationHoursPicker.getValue() * 60 + mDurationMinutesPicker.getValue());
    smartRoundDistanceMeters = ((mDistancePicker100.getValue() * 100 + mDistancePicker10.getValue() * 10 + mDistancePicker1.getValue()) * 1000);
    smartRoundWeightFactor100 = mFlowSeekbar.getProgress();
    TripManager.setTrip(localTrip);
  }
  
  private boolean areOptionsChanged()
  {
    Trip localTrip = TripManager.getTrip();
    int i;
    if (smartRoundUseDuration != mShowDurationSwitch.isChecked()) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i == 0) && (smartRoundDurationMinutes == mDurationHoursPicker.getValue() * 60 + mDurationMinutesPicker.getValue())) {
      i = 0;
    } else {
      i = 1;
    }
    int j = mDistancePicker100.getValue();
    int k = mDistancePicker10.getValue();
    int m = mDistancePicker1.getValue();
    if ((i == 0) && (smartRoundDistanceMeters == (j * 100 + k * 10 + m) * 1000)) {
      i = 0;
    } else {
      i = 1;
    }
    if (i == 0) {
      return smartRoundWeightFactor100 != mFlowSeekbar.getProgress();
    }
    return true;
  }
  
  private void refreshUi()
  {
    Trip localTrip = TripManager.getTrip();
    mHeaderText.setText(localTrip.getTitle());
    mShowDurationSwitch.setChecked(smartRoundUseDuration);
    mDurationMinutesPicker.setMinValue(0);
    mDurationMinutesPicker.setMaxValue(59);
    mDurationMinutesPicker.setValue(smartRoundDurationMinutes % 60);
    mDurationHoursPicker.setMinValue(0);
    mDurationHoursPicker.setMaxValue(24);
    mDurationHoursPicker.setValue(Math.min(24, smartRoundDurationMinutes / 60));
    mShowDistanceSwitch.setChecked(smartRoundUseDuration ^ true);
    int i = smartRoundDistanceMeters / 1000;
    mDistancePicker100.setMinValue(0);
    mDistancePicker100.setMaxValue(9);
    mDistancePicker100.setValue(i / 100 % 10);
    mDistancePicker10.setMinValue(0);
    mDistancePicker10.setMaxValue(9);
    mDistancePicker10.setValue(i / 10 % 10);
    mDistancePicker1.setMinValue(0);
    mDistancePicker1.setMaxValue(9);
    mDistancePicker1.setValue(i % 10);
    onClickShowDurationSwitch();
    mFlowSeekbar.setProgress(Math.max(0, Math.min(100, smartRoundWeightFactor100)));
  }
  
  public void closeRouteOptions()
  {
    TripManager.restoreTrip(false);
    getActivity().getSupportFragmentManager().popBackStack();
    if (mNoRouteFound) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
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
  
  public void onClickShowDistanceSwitch()
  {
    Object localObject = mShowDurationSwitch;
    boolean bool = ((CompoundButton)localObject).isChecked();
    int j = 0;
    if ((bool) && (!mShowDistanceSwitch.isChecked())) {
      bool = true;
    } else {
      bool = false;
    }
    ((CompoundButton)localObject).setChecked(bool);
    localObject = mDurationView;
    int i;
    if (mShowDurationSwitch.isChecked()) {
      i = 0;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
    localObject = mDistanceView;
    if (mShowDistanceSwitch.isChecked()) {
      i = j;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
  }
  
  public void onClickShowDurationSwitch()
  {
    Object localObject = mShowDistanceSwitch;
    boolean bool = ((CompoundButton)localObject).isChecked();
    int j = 0;
    if ((bool) && (!mShowDurationSwitch.isChecked())) {
      bool = true;
    } else {
      bool = false;
    }
    ((CompoundButton)localObject).setChecked(bool);
    localObject = mDurationView;
    int i;
    if (mShowDurationSwitch.isChecked()) {
      i = 0;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
    localObject = mDistanceView;
    if (mShowDistanceSwitch.isChecked()) {
      i = j;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427430, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    mFlowSeekbar.setOnSeekBarChangeListener(new SmartSettingsRoundtripFragment.1(this));
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
  
  public SmartSettingsRoundtripFragment setNoRouteFound()
  {
    mNoRouteFound = true;
    return this;
  }
}
