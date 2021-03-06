package com.com.navapp.ui.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class LetsRideFragment_ViewBinding
  implements Unbinder
{
  private LetsRideFragment target;
  private View view7f080128;
  private View view7f080129;
  private View view7f08012a;
  private View view7f08012b;
  private View view7f08012c;
  
  public LetsRideFragment_ViewBinding(LetsRideFragment paramLetsRideFragment, View paramView)
  {
    target = paramLetsRideFragment;
    View localView = Utils.findRequiredView(paramView, 2131231016, "method 'closeLetsRide'");
    view7f080128 = localView;
    localView.setOnClickListener(new LetsRideFragment_ViewBinding.1(this, paramLetsRideFragment));
    localView = Utils.findRequiredView(paramView, 2131231018, "method 'onNavigation'");
    view7f08012a = localView;
    localView.setOnClickListener(new LetsRideFragment_ViewBinding.2(this, paramLetsRideFragment));
    localView = Utils.findRequiredView(paramView, 2131231019, "method 'onSmartNavigation'");
    view7f08012b = localView;
    localView.setOnClickListener(new LetsRideFragment_ViewBinding.3(this, paramLetsRideFragment));
    localView = Utils.findRequiredView(paramView, 2131231020, "method 'onSmartRoundTrip'");
    view7f08012c = localView;
    localView.setOnClickListener(new LetsRideFragment_ViewBinding.4(this, paramLetsRideFragment));
    paramView = Utils.findRequiredView(paramView, 2131231017, "method 'onMyTrips'");
    view7f080129 = paramView;
    paramView.setOnClickListener(new LetsRideFragment_ViewBinding.5(this, paramLetsRideFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080128.setOnClickListener(null);
      view7f080128 = null;
      view7f08012a.setOnClickListener(null);
      view7f08012a = null;
      view7f08012b.setOnClickListener(null);
      view7f08012b = null;
      view7f08012c.setOnClickListener(null);
      view7f08012c = null;
      view7f080129.setOnClickListener(null);
      view7f080129 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
