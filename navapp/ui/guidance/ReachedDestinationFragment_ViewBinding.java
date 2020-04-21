package com.com.navapp.ui.guidance;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ReachedDestinationFragment_ViewBinding
  implements Unbinder
{
  private ReachedDestinationFragment target;
  private View view7f08019d;
  
  public ReachedDestinationFragment_ViewBinding(ReachedDestinationFragment paramReachedDestinationFragment, View paramView)
  {
    target = paramReachedDestinationFragment;
    paramView = Utils.findRequiredView(paramView, 2131231133, "method 'onOk'");
    view7f08019d = paramView;
    paramView.setOnClickListener(new ReachedDestinationFragment_ViewBinding.1(this, paramReachedDestinationFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f08019d.setOnClickListener(null);
      view7f08019d = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
