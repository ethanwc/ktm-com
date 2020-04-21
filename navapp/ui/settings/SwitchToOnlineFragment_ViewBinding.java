package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SwitchToOnlineFragment_ViewBinding
  implements Unbinder
{
  private SwitchToOnlineFragment target;
  private View view7f08006f;
  private View view7f080278;
  
  public SwitchToOnlineFragment_ViewBinding(SwitchToOnlineFragment paramSwitchToOnlineFragment, View paramView)
  {
    target = paramSwitchToOnlineFragment;
    View localView = Utils.findRequiredView(paramView, 2131231352, "method 'switchToOnlineAndClose'");
    view7f080278 = localView;
    localView.setOnClickListener(new SwitchToOnlineFragment_ViewBinding.1(this, paramSwitchToOnlineFragment));
    paramView = Utils.findRequiredView(paramView, 2131230831, "method 'closeSwitchToOnline'");
    view7f08006f = paramView;
    paramView.setOnClickListener(new SwitchToOnlineFragment_ViewBinding.2(this, paramSwitchToOnlineFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080278.setOnClickListener(null);
      view7f080278 = null;
      view7f08006f.setOnClickListener(null);
      view7f08006f = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
