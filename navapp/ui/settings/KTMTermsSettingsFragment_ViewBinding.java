package com.com.navapp.ui.settings;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class KTMTermsSettingsFragment_ViewBinding
  implements Unbinder
{
  private KTMTermsSettingsFragment target;
  private View view7f080221;
  
  public KTMTermsSettingsFragment_ViewBinding(KTMTermsSettingsFragment paramKTMTermsSettingsFragment, View paramView)
  {
    target = paramKTMTermsSettingsFragment;
    mWebView = ((WebView)Utils.findRequiredViewAsType(paramView, 2131231267, "field 'mWebView'", WebView.class));
    mBackground = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131231266, "field 'mBackground'", ImageView.class));
    paramView = Utils.findRequiredView(paramView, 2131231265, "method 'closeTermsSettings'");
    view7f080221 = paramView;
    paramView.setOnClickListener(new KTMTermsSettingsFragment_ViewBinding.1(this, paramKTMTermsSettingsFragment));
  }
  
  public void unbind()
  {
    KTMTermsSettingsFragment localKTMTermsSettingsFragment = target;
    if (localKTMTermsSettingsFragment != null)
    {
      target = null;
      mWebView = null;
      mBackground = null;
      view7f080221.setOnClickListener(null);
      view7f080221 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
