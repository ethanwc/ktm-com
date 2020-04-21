package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class NotEnoughMemorySettingsFragment_ViewBinding
  implements Unbinder
{
  private NotEnoughMemorySettingsFragment target;
  private View view7f080179;
  
  public NotEnoughMemorySettingsFragment_ViewBinding(NotEnoughMemorySettingsFragment paramNotEnoughMemorySettingsFragment, View paramView)
  {
    target = paramNotEnoughMemorySettingsFragment;
    paramView = Utils.findRequiredView(paramView, 2131231097, "method 'onOk'");
    view7f080179 = paramView;
    paramView.setOnClickListener(new NotEnoughMemorySettingsFragment_ViewBinding.1(this, paramNotEnoughMemorySettingsFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080179.setOnClickListener(null);
      view7f080179 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
