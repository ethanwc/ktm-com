package com.com.navapp.ui.startup;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class StartupActivity_ViewBinding
  implements Unbinder
{
  private StartupActivity target;
  private View view7f080175;
  private View view7f080176;
  
  public StartupActivity_ViewBinding(StartupActivity paramStartupActivity)
  {
    this(paramStartupActivity, paramStartupActivity.getWindow().getDecorView());
  }
  
  public StartupActivity_ViewBinding(StartupActivity paramStartupActivity, View paramView)
  {
    target = paramStartupActivity;
    View localView = Utils.findRequiredView(paramView, 2131231094, "method 'onButtonSwitchInternal'");
    view7f080176 = localView;
    localView.setOnClickListener(new StartupActivity_ViewBinding.1(this, paramStartupActivity));
    paramView = Utils.findRequiredView(paramView, 2131231093, "method 'onButtonCloseApp'");
    view7f080175 = paramView;
    paramView.setOnClickListener(new StartupActivity_ViewBinding.2(this, paramStartupActivity));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080176.setOnClickListener(null);
      view7f080176 = null;
      view7f080175.setOnClickListener(null);
      view7f080175 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
