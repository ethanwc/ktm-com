package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WelcomeScreenStart_ViewBinding
  implements Unbinder
{
  private WelcomeScreenStart target;
  private View view7f080270;
  private View view7f080273;
  
  public WelcomeScreenStart_ViewBinding(WelcomeScreenStart paramWelcomeScreenStart)
  {
    this(paramWelcomeScreenStart, paramWelcomeScreenStart.getWindow().getDecorView());
  }
  
  public WelcomeScreenStart_ViewBinding(WelcomeScreenStart paramWelcomeScreenStart, View paramView)
  {
    target = paramWelcomeScreenStart;
    View localView = Utils.findRequiredView(paramView, 2131231347, "method 'onStartClicked'");
    view7f080273 = localView;
    localView.setOnClickListener(new WelcomeScreenStart_ViewBinding.1(this, paramWelcomeScreenStart));
    paramView = Utils.findRequiredView(paramView, 2131231344, "method 'onCloseClicked'");
    view7f080270 = paramView;
    paramView.setOnClickListener(new WelcomeScreenStart_ViewBinding.2(this, paramWelcomeScreenStart));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080273.setOnClickListener(null);
      view7f080273 = null;
      view7f080270.setOnClickListener(null);
      view7f080270 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
