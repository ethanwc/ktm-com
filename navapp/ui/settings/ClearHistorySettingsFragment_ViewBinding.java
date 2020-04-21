package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ClearHistorySettingsFragment_ViewBinding
  implements Unbinder
{
  private ClearHistorySettingsFragment target;
  private View view7f08006b;
  private View view7f0801e6;
  
  public ClearHistorySettingsFragment_ViewBinding(ClearHistorySettingsFragment paramClearHistorySettingsFragment, View paramView)
  {
    target = paramClearHistorySettingsFragment;
    View localView = Utils.findRequiredView(paramView, 2131230827, "method 'closeClearHistorySettings'");
    view7f08006b = localView;
    localView.setOnClickListener(new ClearHistorySettingsFragment_ViewBinding.1(this, paramClearHistorySettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231206, "method 'onClearHistory'");
    view7f0801e6 = paramView;
    paramView.setOnClickListener(new ClearHistorySettingsFragment_ViewBinding.2(this, paramClearHistorySettingsFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f08006b.setOnClickListener(null);
      view7f08006b = null;
      view7f0801e6.setOnClickListener(null);
      view7f0801e6 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
