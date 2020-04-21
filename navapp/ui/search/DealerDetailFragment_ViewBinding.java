package com.com.navapp.ui.search;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class DealerDetailFragment_ViewBinding
  implements Unbinder
{
  private DealerDetailFragment target;
  private View view7f080145;
  private View view7f080148;
  
  public DealerDetailFragment_ViewBinding(DealerDetailFragment paramDealerDetailFragment, View paramView)
  {
    target = paramDealerDetailFragment;
    mMapPointDetail1 = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231046, "field 'mMapPointDetail1'", TextView.class));
    mMapPointDetail2 = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231047, "field 'mMapPointDetail2'", TextView.class));
    View localView = Utils.findRequiredView(paramView, 2131231045, "method 'onDealerCancelButton'");
    view7f080145 = localView;
    localView.setOnClickListener(new DealerDetailFragment_ViewBinding.1(this, paramDealerDetailFragment));
    paramView = Utils.findRequiredView(paramView, 2131231048, "method 'onDealerSetButton'");
    view7f080148 = paramView;
    paramView.setOnClickListener(new DealerDetailFragment_ViewBinding.2(this, paramDealerDetailFragment));
  }
  
  public void unbind()
  {
    DealerDetailFragment localDealerDetailFragment = target;
    if (localDealerDetailFragment != null)
    {
      target = null;
      mMapPointDetail1 = null;
      mMapPointDetail2 = null;
      view7f080145.setOnClickListener(null);
      view7f080145 = null;
      view7f080148.setOnClickListener(null);
      view7f080148 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
