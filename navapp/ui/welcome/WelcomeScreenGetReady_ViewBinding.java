package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WelcomeScreenGetReady_ViewBinding
  implements Unbinder
{
  private WelcomeScreenGetReady target;
  private View view7f080270;
  private View view7f080272;
  
  public WelcomeScreenGetReady_ViewBinding(WelcomeScreenGetReady paramWelcomeScreenGetReady)
  {
    this(paramWelcomeScreenGetReady, paramWelcomeScreenGetReady.getWindow().getDecorView());
  }
  
  public WelcomeScreenGetReady_ViewBinding(WelcomeScreenGetReady paramWelcomeScreenGetReady, View paramView)
  {
    target = paramWelcomeScreenGetReady;
    View localView = Utils.findRequiredView(paramView, 2131231344, "method 'onCloseClicked'");
    view7f080270 = localView;
    localView.setOnClickListener(new WelcomeScreenGetReady_ViewBinding.1(this, paramWelcomeScreenGetReady));
    paramView = Utils.findRequiredView(paramView, 2131231346, "method 'onOpenSettingsClicked'");
    view7f080272 = paramView;
    paramView.setOnClickListener(new WelcomeScreenGetReady_ViewBinding.2(this, paramWelcomeScreenGetReady));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080270.setOnClickListener(null);
      view7f080270 = null;
      view7f080272.setOnClickListener(null);
      view7f080272 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
