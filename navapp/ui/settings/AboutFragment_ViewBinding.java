package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class AboutFragment_ViewBinding
  implements Unbinder
{
  private AboutFragment target;
  private View view7f080007;
  private View view7f080008;
  private View view7f080009;
  private View view7f08000a;
  
  public AboutFragment_ViewBinding(AboutFragment paramAboutFragment, View paramView)
  {
    target = paramAboutFragment;
    View localView = Utils.findRequiredView(paramView, 2131230730, "method 'onTermsSettings'");
    view7f08000a = localView;
    localView.setOnClickListener(new AboutFragment_ViewBinding.1(this, paramAboutFragment));
    localView = Utils.findRequiredView(paramView, 2131230727, "method 'onFaqSettings'");
    view7f080007 = localView;
    localView.setOnClickListener(new AboutFragment_ViewBinding.2(this, paramAboutFragment));
    localView = Utils.findRequiredView(paramView, 2131230729, "method 'onInfoSettings'");
    view7f080009 = localView;
    localView.setOnClickListener(new AboutFragment_ViewBinding.3(this, paramAboutFragment));
    paramView = Utils.findRequiredView(paramView, 2131230728, "method 'closeAboutSettings'");
    view7f080008 = paramView;
    paramView.setOnClickListener(new AboutFragment_ViewBinding.4(this, paramAboutFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f08000a.setOnClickListener(null);
      view7f08000a = null;
      view7f080007.setOnClickListener(null);
      view7f080007 = null;
      view7f080009.setOnClickListener(null);
      view7f080009 = null;
      view7f080008.setOnClickListener(null);
      view7f080008 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
