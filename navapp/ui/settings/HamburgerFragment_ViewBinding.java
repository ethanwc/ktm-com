package com.com.navapp.ui.settings;

import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class HamburgerFragment_ViewBinding
  implements Unbinder
{
  private HamburgerFragment target;
  private View view7f0800ed;
  private View view7f0800ef;
  private View view7f0800f1;
  private View view7f0800f3;
  private View view7f0800f5;
  private View view7f0800f6;
  private View view7f0800f8;
  private View view7f0800fa;
  
  public HamburgerFragment_ViewBinding(HamburgerFragment paramHamburgerFragment, View paramView)
  {
    target = paramHamburgerFragment;
    View localView = Utils.findRequiredView(paramView, 2131230961, "field 'mMapRadioButton' and method 'onClickMapRadioButton'");
    mMapRadioButton = ((RadioButton)Utils.castView(localView, 2131230961, "field 'mMapRadioButton'", RadioButton.class));
    view7f0800f1 = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.1(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230965, "field 'mSatelliteRadioButton' and method 'onClickSatelliteRadioButton'");
    mSatelliteRadioButton = ((RadioButton)Utils.castView(localView, 2131230965, "field 'mSatelliteRadioButton'", RadioButton.class));
    view7f0800f5 = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.2(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230968, "field 'mTerrainRadioButton' and method 'onClickTerrainRadioButton'");
    mTerrainRadioButton = ((RadioButton)Utils.castView(localView, 2131230968, "field 'mTerrainRadioButton'", RadioButton.class));
    view7f0800f8 = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.3(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230970, "field 'mTrafficInfoSwitch' and method 'onClickTrafficInfoSwitch'");
    mTrafficInfoSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131230970, "field 'mTrafficInfoSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f0800fa = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.4(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230963, "field 'mOfflineMapsSwitch' and method 'onClickOfflineMapsSwitch'");
    mOfflineMapsSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131230963, "field 'mOfflineMapsSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f0800f3 = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.5(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230966, "method 'advancedSettings'");
    view7f0800f6 = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.6(this, paramHamburgerFragment));
    localView = Utils.findRequiredView(paramView, 2131230957, "method 'aboutSettings'");
    view7f0800ed = localView;
    localView.setOnClickListener(new HamburgerFragment_ViewBinding.7(this, paramHamburgerFragment));
    paramView = Utils.findRequiredView(paramView, 2131230959, "method 'closeSettings'");
    view7f0800ef = paramView;
    paramView.setOnClickListener(new HamburgerFragment_ViewBinding.8(this, paramHamburgerFragment));
  }
  
  public void unbind()
  {
    HamburgerFragment localHamburgerFragment = target;
    if (localHamburgerFragment != null)
    {
      target = null;
      mMapRadioButton = null;
      mSatelliteRadioButton = null;
      mTerrainRadioButton = null;
      mTrafficInfoSwitch = null;
      mOfflineMapsSwitch = null;
      view7f0800f1.setOnClickListener(null);
      view7f0800f1 = null;
      view7f0800f5.setOnClickListener(null);
      view7f0800f5 = null;
      view7f0800f8.setOnClickListener(null);
      view7f0800f8 = null;
      view7f0800fa.setOnClickListener(null);
      view7f0800fa = null;
      view7f0800f3.setOnClickListener(null);
      view7f0800f3 = null;
      view7f0800f6.setOnClickListener(null);
      view7f0800f6 = null;
      view7f0800ed.setOnClickListener(null);
      view7f0800ed = null;
      view7f0800ef.setOnClickListener(null);
      view7f0800ef = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
