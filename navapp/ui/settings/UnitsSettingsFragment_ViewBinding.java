package com.com.navapp.ui.settings;

import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class UnitsSettingsFragment_ViewBinding
  implements Unbinder
{
  private UnitsSettingsFragment target;
  private View view7f080257;
  private View view7f080259;
  private View view7f08025a;
  private View view7f08025b;
  private View view7f08025c;
  private View view7f08025d;
  private View view7f08025e;
  
  public UnitsSettingsFragment_ViewBinding(UnitsSettingsFragment paramUnitsSettingsFragment, View paramView)
  {
    target = paramUnitsSettingsFragment;
    View localView = Utils.findRequiredView(paramView, 2131231326, "field 'mMetricRadioButton' and method 'onClickMetricRadioButton'");
    mMetricRadioButton = ((RadioButton)Utils.castView(localView, 2131231326, "field 'mMetricRadioButton'", RadioButton.class));
    view7f08025e = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.1(this, paramUnitsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231322, "field 'mImperialUkRadioButton' and method 'onClickImperialUkRadioButton'");
    mImperialUkRadioButton = ((RadioButton)Utils.castView(localView, 2131231322, "field 'mImperialUkRadioButton'", RadioButton.class));
    view7f08025a = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.2(this, paramUnitsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231324, "field 'mImperialUsRadioButton' and method 'onClickImperialUsRadioButton'");
    mImperialUsRadioButton = ((RadioButton)Utils.castView(localView, 2131231324, "field 'mImperialUsRadioButton'", RadioButton.class));
    view7f08025c = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.3(this, paramUnitsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231325, "method 'onClickMetricLabel'");
    view7f08025d = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.4(this, paramUnitsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231321, "method 'onClickImperialUkLabel'");
    view7f080259 = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.5(this, paramUnitsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231323, "method 'onClickImperialUsLabel'");
    view7f08025b = localView;
    localView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.6(this, paramUnitsSettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231319, "method 'closeUnitsSettings'");
    view7f080257 = paramView;
    paramView.setOnClickListener(new UnitsSettingsFragment_ViewBinding.7(this, paramUnitsSettingsFragment));
  }
  
  public void unbind()
  {
    UnitsSettingsFragment localUnitsSettingsFragment = target;
    if (localUnitsSettingsFragment != null)
    {
      target = null;
      mMetricRadioButton = null;
      mImperialUkRadioButton = null;
      mImperialUsRadioButton = null;
      view7f08025e.setOnClickListener(null);
      view7f08025e = null;
      view7f08025a.setOnClickListener(null);
      view7f08025a = null;
      view7f08025c.setOnClickListener(null);
      view7f08025c = null;
      view7f08025d.setOnClickListener(null);
      view7f08025d = null;
      view7f080259.setOnClickListener(null);
      view7f080259 = null;
      view7f08025b.setOnClickListener(null);
      view7f08025b = null;
      view7f080257.setOnClickListener(null);
      view7f080257 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
