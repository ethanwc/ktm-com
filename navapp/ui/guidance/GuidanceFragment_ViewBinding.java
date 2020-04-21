package com.com.navapp.ui.guidance;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class GuidanceFragment_ViewBinding
  implements Unbinder
{
  private GuidanceFragment target;
  private View view7f0800c7;
  private View view7f0800d4;
  private View view7f0800e2;
  private View view7f08013a;
  private View view7f08013d;
  private View view7f08013e;
  
  public GuidanceFragment_ViewBinding(GuidanceFragment paramGuidanceFragment, View paramView)
  {
    target = paramGuidanceFragment;
    mGuidanceOverlay = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131230927, "field 'mGuidanceOverlay'", LinearLayout.class));
    View localView = Utils.findRequiredView(paramView, 2131230919, "field 'mCancelButton' and method 'onCancelButton'");
    mCancelButton = ((ImageButton)Utils.castView(localView, 2131230919, "field 'mCancelButton'", ImageButton.class));
    view7f0800c7 = localView;
    localView.setOnClickListener(new GuidanceFragment_ViewBinding.1(this, paramGuidanceFragment));
    mLeftManeuverLayout = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131231011, "field 'mLeftManeuverLayout'", LinearLayout.class));
    mRightManeuverLayout = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131231013, "field 'mRightManeuverLayout'", LinearLayout.class));
    mManeuverIcon = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131230929, "field 'mManeuverIcon'", ImageView.class));
    mManeuverDistance = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230928, "field 'mManeuverDistance'", TextView.class));
    mManeuverNextRoad = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230930, "field 'mManeuverNextRoad'", TextView.class));
    mEtaText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230926, "field 'mEtaText'", TextView.class));
    mDurationHr = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230922, "field 'mDurationHr'", TextView.class));
    mDurationHrUnit = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230923, "field 'mDurationHrUnit'", TextView.class));
    mDurationMin = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230924, "field 'mDurationMin'", TextView.class));
    mDurationMinUnit = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230925, "field 'mDurationMinUnit'", TextView.class));
    mDistance = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230920, "field 'mDistance'", TextView.class));
    mDistanceUnit = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230921, "field 'mDistanceUnit'", TextView.class));
    mMapControls = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131230931, "field 'mMapControls'", LinearLayout.class));
    localView = Utils.findRequiredView(paramView, 2131231034, "field 'mCenterButton' and method 'onCenterButton'");
    mCenterButton = ((ImageButton)Utils.castView(localView, 2131231034, "field 'mCenterButton'", ImageButton.class));
    view7f08013a = localView;
    localView.setOnClickListener(new GuidanceFragment_ViewBinding.2(this, paramGuidanceFragment));
    localView = Utils.findRequiredView(paramView, 2131231038, "field 'mPlusButton' and method 'onZoomIn'");
    mPlusButton = ((ImageButton)Utils.castView(localView, 2131231038, "field 'mPlusButton'", ImageButton.class));
    view7f08013e = localView;
    localView.setOnClickListener(new GuidanceFragment_ViewBinding.3(this, paramGuidanceFragment));
    localView = Utils.findRequiredView(paramView, 2131231037, "field 'mMinusButton' and method 'onZoomOut'");
    mMinusButton = ((ImageButton)Utils.castView(localView, 2131231037, "field 'mMinusButton'", ImageButton.class));
    view7f08013d = localView;
    localView.setOnClickListener(new GuidanceFragment_ViewBinding.4(this, paramGuidanceFragment));
    localView = Utils.findRequiredView(paramView, 2131230946, "field 'mSkipWaypointButton' and method 'onSkipWaypointButton'");
    mSkipWaypointButton = ((ImageButton)Utils.castView(localView, 2131230946, "field 'mSkipWaypointButton'", ImageButton.class));
    view7f0800e2 = localView;
    localView.setOnClickListener(new GuidanceFragment_ViewBinding.5(this, paramGuidanceFragment));
    paramView = Utils.findRequiredView(paramView, 2131230932, "method 'onOptionsButton'");
    view7f0800d4 = paramView;
    paramView.setOnClickListener(new GuidanceFragment_ViewBinding.6(this, paramGuidanceFragment));
  }
  
  public void unbind()
  {
    GuidanceFragment localGuidanceFragment = target;
    if (localGuidanceFragment != null)
    {
      target = null;
      mGuidanceOverlay = null;
      mCancelButton = null;
      mLeftManeuverLayout = null;
      mRightManeuverLayout = null;
      mManeuverIcon = null;
      mManeuverDistance = null;
      mManeuverNextRoad = null;
      mEtaText = null;
      mDurationHr = null;
      mDurationHrUnit = null;
      mDurationMin = null;
      mDurationMinUnit = null;
      mDistance = null;
      mDistanceUnit = null;
      mMapControls = null;
      mCenterButton = null;
      mPlusButton = null;
      mMinusButton = null;
      mSkipWaypointButton = null;
      view7f0800c7.setOnClickListener(null);
      view7f0800c7 = null;
      view7f08013a.setOnClickListener(null);
      view7f08013a = null;
      view7f08013e.setOnClickListener(null);
      view7f08013e = null;
      view7f08013d.setOnClickListener(null);
      view7f08013d = null;
      view7f0800e2.setOnClickListener(null);
      view7f0800e2 = null;
      view7f0800d4.setOnClickListener(null);
      view7f0800d4 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
