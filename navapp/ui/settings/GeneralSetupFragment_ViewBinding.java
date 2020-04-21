package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class GeneralSetupFragment_ViewBinding
  implements Unbinder
{
  private GeneralSetupFragment target;
  private View view7f0800be;
  private View view7f0800c0;
  private View view7f0800c1;
  private View view7f0800c2;
  private View view7f0800c3;
  private View view7f080104;
  
  public GeneralSetupFragment_ViewBinding(GeneralSetupFragment paramGeneralSetupFragment, View paramView)
  {
    target = paramGeneralSetupFragment;
    View localView = Utils.findRequiredView(paramView, 2131230980, "method 'onStorageLocationSettings'");
    view7f080104 = localView;
    localView.setOnClickListener(new GeneralSetupFragment_ViewBinding.1(this, paramGeneralSetupFragment));
    localView = Utils.findRequiredView(paramView, 2131230914, "method 'onUnitsSettings'");
    view7f0800c2 = localView;
    localView.setOnClickListener(new GeneralSetupFragment_ViewBinding.2(this, paramGeneralSetupFragment));
    localView = Utils.findRequiredView(paramView, 2131230915, "method 'onVoicesSettings'");
    view7f0800c3 = localView;
    localView.setOnClickListener(new GeneralSetupFragment_ViewBinding.3(this, paramGeneralSetupFragment));
    localView = Utils.findRequiredView(paramView, 2131230913, "method 'onDownloadMapsSettings'");
    view7f0800c1 = localView;
    localView.setOnClickListener(new GeneralSetupFragment_ViewBinding.4(this, paramGeneralSetupFragment));
    localView = Utils.findRequiredView(paramView, 2131230910, "method 'closeAdvancedSettings'");
    view7f0800be = localView;
    localView.setOnClickListener(new GeneralSetupFragment_ViewBinding.5(this, paramGeneralSetupFragment));
    paramView = Utils.findRequiredView(paramView, 2131230912, "method 'onClearHistory'");
    view7f0800c0 = paramView;
    paramView.setOnClickListener(new GeneralSetupFragment_ViewBinding.6(this, paramGeneralSetupFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080104.setOnClickListener(null);
      view7f080104 = null;
      view7f0800c2.setOnClickListener(null);
      view7f0800c2 = null;
      view7f0800c3.setOnClickListener(null);
      view7f0800c3 = null;
      view7f0800c1.setOnClickListener(null);
      view7f0800c1 = null;
      view7f0800be.setOnClickListener(null);
      view7f0800be = null;
      view7f0800c0.setOnClickListener(null);
      view7f0800c0 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
