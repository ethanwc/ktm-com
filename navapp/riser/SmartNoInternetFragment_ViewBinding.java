package com.com.navapp.riser;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SmartNoInternetFragment_ViewBinding
  implements Unbinder
{
  private SmartNoInternetFragment target;
  private View view7f08018b;
  
  public SmartNoInternetFragment_ViewBinding(SmartNoInternetFragment paramSmartNoInternetFragment, View paramView)
  {
    target = paramSmartNoInternetFragment;
    paramView = Utils.findRequiredView(paramView, 2131231115, "method 'onOk'");
    view7f08018b = paramView;
    paramView.setOnClickListener(new SmartNoInternetFragment_ViewBinding.1(this, paramSmartNoInternetFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f08018b.setOnClickListener(null);
      view7f08018b = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
