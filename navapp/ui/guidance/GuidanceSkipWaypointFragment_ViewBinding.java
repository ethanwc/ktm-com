package com.com.navapp.ui.guidance;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class GuidanceSkipWaypointFragment_ViewBinding
  implements Unbinder
{
  private GuidanceSkipWaypointFragment target;
  private View view7f0800d7;
  private View view7f0800d8;
  private View view7f0800da;
  private View view7f0800e0;
  
  public GuidanceSkipWaypointFragment_ViewBinding(GuidanceSkipWaypointFragment paramGuidanceSkipWaypointFragment, View paramView)
  {
    target = paramGuidanceSkipWaypointFragment;
    mVwSkipLayout = Utils.findRequiredView(paramView, 2131230943, "field 'mVwSkipLayout'");
    mTvDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230945, "field 'mTvDescription'", TextView.class));
    mVwFindingLayout = Utils.findRequiredView(paramView, 2131230939, "field 'mVwFindingLayout'");
    mTvFindingRouteWpName = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230942, "field 'mTvFindingRouteWpName'", TextView.class));
    mVwFailedLayout = Utils.findRequiredView(paramView, 2131230937, "field 'mVwFailedLayout'");
    View localView = Utils.findRequiredView(paramView, 2131230944, "method 'onSkipOk'");
    view7f0800e0 = localView;
    localView.setOnClickListener(new GuidanceSkipWaypointFragment_ViewBinding.1(this, paramGuidanceSkipWaypointFragment));
    localView = Utils.findRequiredView(paramView, 2131230935, "method 'onSkipCancelOrFailed'");
    view7f0800d7 = localView;
    localView.setOnClickListener(new GuidanceSkipWaypointFragment_ViewBinding.2(this, paramGuidanceSkipWaypointFragment));
    localView = Utils.findRequiredView(paramView, 2131230938, "method 'onSkipCancelOrFailed'");
    view7f0800da = localView;
    localView.setOnClickListener(new GuidanceSkipWaypointFragment_ViewBinding.3(this, paramGuidanceSkipWaypointFragment));
    paramView = Utils.findRequiredView(paramView, 2131230936, "method 'onSkipCancelOrFailed'");
    view7f0800d8 = paramView;
    paramView.setOnClickListener(new GuidanceSkipWaypointFragment_ViewBinding.4(this, paramGuidanceSkipWaypointFragment));
  }
  
  public void unbind()
  {
    GuidanceSkipWaypointFragment localGuidanceSkipWaypointFragment = target;
    if (localGuidanceSkipWaypointFragment != null)
    {
      target = null;
      mVwSkipLayout = null;
      mTvDescription = null;
      mVwFindingLayout = null;
      mTvFindingRouteWpName = null;
      mVwFailedLayout = null;
      view7f0800e0.setOnClickListener(null);
      view7f0800e0 = null;
      view7f0800d7.setOnClickListener(null);
      view7f0800d7 = null;
      view7f0800da.setOnClickListener(null);
      view7f0800da = null;
      view7f0800d8.setOnClickListener(null);
      view7f0800d8 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
