package com.com.navapp.riser;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SmartSettingsRouteFragment_ViewBinding
  implements Unbinder
{
  private SmartSettingsRouteFragment target;
  private View view7f080041;
  private View view7f08004e;
  private View view7f080088;
  private View view7f0801bb;
  private View view7f080203;
  
  public SmartSettingsRouteFragment_ViewBinding(SmartSettingsRouteFragment paramSmartSettingsRouteFragment, View paramView)
  {
    target = paramSmartSettingsRouteFragment;
    mHeaderText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230972, "field 'mHeaderText'", TextView.class));
    View localView = Utils.findRequiredView(paramView, 2131231235, "field 'mShowDurationSwitch' and method 'onClickShowDurationSwitch'");
    mShowDurationSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131231235, "field 'mShowDurationSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f080203 = localView;
    localView.setOnClickListener(new SmartSettingsRouteFragment_ViewBinding.1(this, paramSmartSettingsRouteFragment));
    mDurationView = Utils.findRequiredView(paramView, 2131231226, "field 'mDurationView'");
    mDurationHoursPicker = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231227, "field 'mDurationHoursPicker'", NumberPicker.class));
    mDurationMinutesPicker = ((NumberPicker)Utils.findRequiredViewAsType(paramView, 2131231228, "field 'mDurationMinutesPicker'", NumberPicker.class));
    mFlowValues = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231231, "field 'mFlowValues'", TextView.class));
    mFlowSeekbar = ((SeekBar)Utils.findRequiredViewAsType(paramView, 2131231230, "field 'mFlowSeekbar'", SeekBar.class));
    localView = Utils.findRequiredView(paramView, 2131230785, "method 'onAddWaypoints'");
    view7f080041 = localView;
    localView.setOnClickListener(new SmartSettingsRouteFragment_ViewBinding.2(this, paramSmartSettingsRouteFragment));
    localView = Utils.findRequiredView(paramView, 2131230856, "method 'onDone'");
    view7f080088 = localView;
    localView.setOnClickListener(new SmartSettingsRouteFragment_ViewBinding.3(this, paramSmartSettingsRouteFragment));
    localView = Utils.findRequiredView(paramView, 2131231163, "method 'onButtonSave'");
    view7f0801bb = localView;
    localView.setOnClickListener(new SmartSettingsRouteFragment_ViewBinding.4(this, paramSmartSettingsRouteFragment));
    paramView = Utils.findRequiredView(paramView, 2131230798, "method 'closeRouteOptions'");
    view7f08004e = paramView;
    paramView.setOnClickListener(new SmartSettingsRouteFragment_ViewBinding.5(this, paramSmartSettingsRouteFragment));
  }
  
  public void unbind()
  {
    SmartSettingsRouteFragment localSmartSettingsRouteFragment = target;
    if (localSmartSettingsRouteFragment != null)
    {
      target = null;
      mHeaderText = null;
      mShowDurationSwitch = null;
      mDurationView = null;
      mDurationHoursPicker = null;
      mDurationMinutesPicker = null;
      mFlowValues = null;
      mFlowSeekbar = null;
      view7f080203.setOnClickListener(null);
      view7f080203 = null;
      view7f080041.setOnClickListener(null);
      view7f080041 = null;
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
