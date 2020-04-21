package com.com.navapp.riser;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SmartSettingsRoundtripFragment_ViewBinding
  implements Unbinder
{
  private SmartSettingsRoundtripFragment target;
  private View view7f08004e;
  private View view7f080088;
  private View view7f0801bb;
  private View view7f080201;
  private View view7f080203;
  
  public SmartSettingsRoundtripFragment_ViewBinding(SmartSettingsRoundtripFragment paramSmartSettingsRoundtripFragment, View paramView)
  {
    target = paramSmartSettingsRoundtripFragment;
    mHeaderText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230972, "field 'mHeaderText'", TextView.class));
    View localView = Utils.findRequiredView(paramView, 2131231235, "field 'mShowDurationSwitch' and method 'onClickShowDurationSwitch'");
    mShowDurationSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131231235, "field 'mShowDurationSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f080203 = localView;
    localView.setOnClickListener(new SmartSettingsRoundtripFragment_ViewBinding.1(this, paramSmartSettingsRoundtripFragment));
    mDurationView = Utils.findRequiredView(paramView, 2131231226, "field 'mDurationView'");
    mDurationHoursPicker = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231227, "field 'mDurationHoursPicker'", NumberPicker.class));
    mDurationMinutesPicker = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231228, "field 'mDurationMinutesPicker'", NumberPicker.class));
    localView = Utils.findRequiredView(paramView, 2131231233, "field 'mShowDistanceSwitch' and method 'onClickShowDistanceSwitch'");
    mShowDistanceSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131231233, "field 'mShowDistanceSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f080201 = localView;
    ((CompoundButton)localView).setOnCheckedChangeListener(new SmartSettingsRoundtripFragment_ViewBinding.2(this, paramSmartSettingsRoundtripFragment));
    mDistanceView = Utils.findRequiredView(paramView, 2131231222, "field 'mDistanceView'");
    mDistancePicker100 = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231225, "field 'mDistancePicker100'", NumberPicker.class));
    mDistancePicker10 = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231224, "field 'mDistancePicker10'", NumberPicker.class));
    mDistancePicker1 = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231223, "field 'mDistancePicker1'", NumberPicker.class));
    mFlowValues = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231231, "field 'mFlowValues'", TextView.class));
    mFlowSeekbar = ((SeekBar)Utils.findRequiredViewAsType(paramView, 2131231230, "field 'mFlowSeekbar'", SeekBar.class));
    localView = Utils.findRequiredView(paramView, 2131230856, "method 'onDone'");
    view7f080088 = localView;
    localView.setOnClickListener(new SmartSettingsRoundtripFragment_ViewBinding.3(this, paramSmartSettingsRoundtripFragment));
    localView = Utils.findRequiredView(paramView, 2131231163, "method 'onButtonSave'");
    view7f0801bb = localView;
    localView.setOnClickListener(new SmartSettingsRoundtripFragment_ViewBinding.4(this, paramSmartSettingsRoundtripFragment));
    paramView = Utils.findRequiredView(paramView, 2131230798, "method 'closeRouteOptions'");
    view7f08004e = paramView;
    paramView.setOnClickListener(new SmartSettingsRoundtripFragment_ViewBinding.5(this, paramSmartSettingsRoundtripFragment));
  }
  
  public void unbind()
  {
    SmartSettingsRoundtripFragment localSmartSettingsRoundtripFragment = target;
    if (localSmartSettingsRoundtripFragment != null)
    {
      target = null;
      mHeaderText = null;
      mShowDurationSwitch = null;
      mDurationView = null;
      mDurationHoursPicker = null;
      mDurationMinutesPicker = null;
      mShowDistanceSwitch = null;
      mDistanceView = null;
      mDistancePicker100 = null;
      mDistancePicker10 = null;
      mDistancePicker1 = null;
      mFlowValues = null;
      mFlowSeekbar = null;
      view7f080203.setOnClickListener(null);
      view7f080203 = null;
      ((CompoundButton)view7f080201).setOnCheckedChangeListener(null);
      view7f080201 = null;
      view7f080088.setOnClickListener(null);
      view7f080088 = null;
      view7f0801bb.setOnClickListener(null);
      view7f0801bb = null;
      view7f08004e.setOnClickListener(null);
      view7f08004e = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
