package com.com.navapp.ui.settings;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FaqSettingsFragment_ViewBinding
  implements Unbinder
{
  private FaqSettingsFragment target;
  private View view7f0800aa;
  
  public FaqSettingsFragment_ViewBinding(FaqSettingsFragment paramFaqSettingsFragment, View paramView)
  {
    target = paramFaqSettingsFragment;
    mBackground = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131230891, "field 'mBackground'", ImageView.class));
    mWebView = ((WebView)Utils.findRequiredViewAsType(paramView, 2131230894, "field 'mWebView'", WebView.class));
    mProgressScreen = Utils.findRequiredView(paramView, 2131230893, "field 'mProgressScreen'");
    mErrorScreen = Utils.findRequiredView(paramView, 2131230892, "field 'mErrorScreen'");
    paramView = Utils.findRequiredView(paramView, 2131230890, "method 'closeFaq'");
    view7f0800aa = paramView;
    paramView.setOnClickListener(new FaqSettingsFragment_ViewBinding.1(this, paramFaqSettingsFragment));
  }
  
  public void unbind()
  {
    FaqSettingsFragment localFaqSettingsFragment = target;
    if (localFaqSettingsFragment != null)
    {
      target = null;
      mBackground = null;
      mWebView = null;
      mProgressScreen = null;
      mErrorScreen = null;
      view7f0800aa.setOnClickListener(null);
      view7f0800aa = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
