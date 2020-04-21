package com.com.navapp.ui.routing;

import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class RouteWaypointsFragment_ViewBinding
  implements Unbinder
{
  private RouteWaypointsFragment target;
  private View view7f0801b4;
  private View view7f0801b5;
  private View view7f0801b6;
  private View view7f0801b7;
  
  public RouteWaypointsFragment_ViewBinding(RouteWaypointsFragment paramRouteWaypointsFragment, View paramView)
  {
    target = paramRouteWaypointsFragment;
    mRvWaypoints = ((RecyclerView)Utils.findRequiredViewAsType(paramView, 2131231160, "field 'mRvWaypoints'", RecyclerView.class));
    mSaveOrPreview = Utils.findRequiredView(paramView, 2131231161, "field 'mSaveOrPreview'");
    View localView = Utils.findRequiredView(paramView, 2131231159, "field 'mRideItButton' and method 'onButtonRideIt'");
    mRideItButton = ((Button)Utils.castView(localView, 2131231159, "field 'mRideItButton'", Button.class));
    view7f0801b7 = localView;
    localView.setOnClickListener(new RouteWaypointsFragment_ViewBinding.1(this, paramRouteWaypointsFragment));
    localView = Utils.findRequiredView(paramView, 2131231158, "method 'onButtonSave'");
    view7f0801b6 = localView;
    localView.setOnClickListener(new RouteWaypointsFragment_ViewBinding.2(this, paramRouteWaypointsFragment));
    localView = Utils.findRequiredView(paramView, 2131231157, "method 'onButtonPreview'");
    view7f0801b5 = localView;
    localView.setOnClickListener(new RouteWaypointsFragment_ViewBinding.3(this, paramRouteWaypointsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231156, "method 'onButtonBack'");
    view7f0801b4 = paramView;
    paramView.setOnClickListener(new RouteWaypointsFragment_ViewBinding.4(this, paramRouteWaypointsFragment));
  }
  
  public void unbind()
  {
    RouteWaypointsFragment localRouteWaypointsFragment = target;
    if (localRouteWaypointsFragment != null)
    {
      target = null;
      mRvWaypoints = null;
      mSaveOrPreview = null;
      mRideItButton = null;
      view7f0801b7.setOnClickListener(null);
      view7f0801b7 = null;
      view7f0801b6.setOnClickListener(null);
      view7f0801b6 = null;
      view7f0801b5.setOnClickListener(null);
      view7f0801b5 = null;
      view7f0801b4.setOnClickListener(null);
      view7f0801b4 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
