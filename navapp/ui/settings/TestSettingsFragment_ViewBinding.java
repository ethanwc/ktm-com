package com.com.navapp.ui.settings;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class TestSettingsFragment_ViewBinding
  implements Unbinder
{
  private TestSettingsFragment target;
  private View view7f080070;
  private View view7f0800ef;
  private View view7f080134;
  private View view7f080136;
  private View view7f0801e7;
  private View view7f0801e9;
  private View view7f0801ea;
  private View view7f0801eb;
  
  public TestSettingsFragment_ViewBinding(TestSettingsFragment paramTestSettingsFragment, View paramView)
  {
    target = paramTestSettingsFragment;
    mScreenDelete = Utils.findRequiredView(paramView, 2131231168, "field 'mScreenDelete'");
    mScreenHelp = Utils.findRequiredView(paramView, 2131231169, "field 'mScreenHelp'");
    mWebview = ((WebView)Utils.findRequiredViewAsType(paramView, 2131231341, "field 'mWebview'", WebView.class));
    View localView = Utils.findRequiredView(paramView, 2131231209, "field 'mExtendedLoggingSwitch' and method 'onClickLoggingSwitch'");
    mExtendedLoggingSwitch = ((com.com.navapp.ui.widgets.SliderCheckbox)Utils.castView(localView, 2131231209, "field 'mExtendedLoggingSwitch'", com.ktm.navapp.ui.widgets.SliderCheckbox.class));
    view7f0801e9 = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.1(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231207, "field 'mButtonDelete' and method 'onDeleteFiles'");
    mButtonDelete = ((Button)Utils.castView(localView, 2131231207, "field 'mButtonDelete'", Button.class));
    view7f0801e7 = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.2(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231030, "method 'onDeleteYes'");
    view7f080136 = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.3(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231028, "method 'onDeleteNo'");
    view7f080134 = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.4(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231210, "method 'onGrabFlicButton'");
    view7f0801ea = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.5(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231211, "method 'onHelpButton'");
    view7f0801eb = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.6(this, paramTestSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131230832, "method 'onCloseHelp'");
    view7f080070 = localView;
    localView.setOnClickListener(new TestSettingsFragment_ViewBinding.7(this, paramTestSettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131230959, "method 'closeSettings'");
    view7f0800ef = paramView;
    paramView.setOnClickListener(new TestSettingsFragment_ViewBinding.8(this, paramTestSettingsFragment));
  }
  
  public void unbind()
  {
    TestSettingsFragment localTestSettingsFragment = target;
    if (localTestSettingsFragment != null)
    {
      target = null;
      mScreenDelete = null;
      mScreenHelp = null;
      mWebview = null;
      mExtendedLoggingSwitch = null;
      mButtonDelete = null;
      view7f0801e9.setOnClickListener(null);
      view7f0801e9 = null;
      view7f0801e7.setOnClickListener(null);
      view7f0801e7 = null;
      view7f080136.setOnClickListener(null);
      view7f080136 = null;
      view7f080134.setOnClickListener(null);
      view7f080134 = null;
      view7f0801ea.setOnClickListener(null);
      view7f0801ea = null;
      view7f0801eb.setOnClickListener(null);
      view7f0801eb = null;
      view7f080070.setOnClickListener(null);
      view7f080070 = null;
      view7f0800ef.setOnClickListener(null);
      view7f0800ef = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
