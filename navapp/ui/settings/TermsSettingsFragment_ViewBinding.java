package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class TermsSettingsFragment_ViewBinding
  implements Unbinder
{
  private TermsSettingsFragment target;
  private View view7f080190;
  private View view7f080226;
  private View view7f080228;
  private View view7f080229;
  private View view7f08022a;
  private View view7f08022b;
  
  public TermsSettingsFragment_ViewBinding(TermsSettingsFragment paramTermsSettingsFragment, View paramView)
  {
    target = paramTermsSettingsFragment;
    View localView = Utils.findRequiredView(paramView, 2131231275, "method 'onKTMTermsSettings'");
    view7f08022b = localView;
    localView.setOnClickListener(new TermsSettingsFragment_ViewBinding.1(this, paramTermsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231274, "method 'onHERETermsSettings'");
    view7f08022a = localView;
    localView.setOnClickListener(new TermsSettingsFragment_ViewBinding.2(this, paramTermsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231272, "method 'onHEREPrivacySettings'");
    view7f080228 = localView;
    localView.setOnClickListener(new TermsSettingsFragment_ViewBinding.3(this, paramTermsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231273, "method 'onHERESecuritySettings'");
    view7f080229 = localView;
    localView.setOnClickListener(new TermsSettingsFragment_ViewBinding.4(this, paramTermsSettingsFragment));
    localView = Utils.findRequiredView(paramView, 2131231120, "method 'onOpenSourceLicensesSettings'");
    view7f080190 = localView;
    localView.setOnClickListener(new TermsSettingsFragment_ViewBinding.5(this, paramTermsSettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231270, "method 'closeUnitsSettings'");
    view7f080226 = paramView;
    paramView.setOnClickListener(new TermsSettingsFragment_ViewBinding.6(this, paramTermsSettingsFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f08022b.setOnClickListener(null);
      view7f08022b = null;
      view7f08022a.setOnClickListener(null);
      view7f08022a = null;
      view7f080228.setOnClickListener(null);
      view7f080228 = null;
      view7f080229.setOnClickListener(null);
      view7f080229 = null;
      view7f080190.setOnClickListener(null);
      view7f080190 = null;
      view7f080226.setOnClickListener(null);
      view7f080226 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
