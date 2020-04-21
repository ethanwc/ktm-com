package com.com.navapp.ui.settings;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class OpenSourceLicensesSettingsFragment_ViewBinding
  implements Unbinder
{
  private OpenSourceLicensesSettingsFragment target;
  private View view7f08018e;
  
  public OpenSourceLicensesSettingsFragment_ViewBinding(OpenSourceLicensesSettingsFragment paramOpenSourceLicensesSettingsFragment, View paramView)
  {
    target = paramOpenSourceLicensesSettingsFragment;
    mWebView = ((WebView)Utils.findRequiredViewAsType(paramView, 2131231121, "field 'mWebView'", WebView.class));
    mBackground = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131231119, "field 'mBackground'", ImageView.class));
    paramView = Utils.findRequiredView(paramView, 2131231118, "method 'closeOpenSourceLicensesSettings'");
    view7f08018e = paramView;
    paramView.setOnClickListener(new OpenSourceLicensesSettingsFragment_ViewBinding.1(this, paramOpenSourceLicensesSettingsFragment));
  }
  
  public void unbind()
  {
    OpenSourceLicensesSettingsFragment localOpenSourceLicensesSettingsFragment = target;
    if (localOpenSourceLicensesSettingsFragment != null)
    {
      target = null;
      mWebView = null;
      mBackground = null;
      view7f08018e.setOnClickListener(null);
      view7f08018e = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
