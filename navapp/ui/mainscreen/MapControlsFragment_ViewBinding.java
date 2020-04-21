package com.com.navapp.ui.mainscreen;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MapControlsFragment_ViewBinding
  implements Unbinder
{
  private MapControlsFragment target;
  private View view7f080072;
  private View view7f08013a;
  private View view7f08013b;
  private View view7f08013c;
  private View view7f08013d;
  private View view7f08013e;
  private View view7f08013f;
  private View view7f080140;
  private View view7f08016e;
  
  public MapControlsFragment_ViewBinding(MapControlsFragment paramMapControlsFragment, View paramView)
  {
    target = paramMapControlsFragment;
    View localView = Utils.findRequiredView(paramView, 2131231039, "field 'mSearchButton' and method 'search'");
    mSearchButton = ((ImageButton)Utils.castView(localView, 2131231039, "field 'mSearchButton'", ImageButton.class));
    view7f08013f = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.1(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231040, "field 'mTripsButton' and method 'loadTrip'");
    mTripsButton = ((ImageButton)Utils.castView(localView, 2131231040, "field 'mTripsButton'", ImageButton.class));
    view7f080140 = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.2(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231035, "field 'mLetsRideButton' and method 'letsRide'");
    mLetsRideButton = ((ImageButton)Utils.castView(localView, 2131231035, "field 'mLetsRideButton'", ImageButton.class));
    view7f08013b = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.3(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231034, "field 'mCenterButton' and method 'centerMap'");
    mCenterButton = ((com.com.navapp.ui.widgets.AlphaAnimatedImageButton)Utils.castView(localView, 2131231034, "field 'mCenterButton'", com.ktm.navapp.ui.widgets.AlphaAnimatedImageButton.class));
    view7f08013a = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.4(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231038, "field 'mPlusButton' and method 'zoomUp'");
    mPlusButton = ((com.com.navapp.ui.widgets.AlphaAnimatedImageButton)Utils.castView(localView, 2131231038, "field 'mPlusButton'", com.ktm.navapp.ui.widgets.AlphaAnimatedImageButton.class));
    view7f08013e = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.5(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231037, "field 'mMinusButton' and method 'zoomDown'");
    mMinusButton = ((com.com.navapp.ui.widgets.AlphaAnimatedImageButton)Utils.castView(localView, 2131231037, "field 'mMinusButton'", com.ktm.navapp.ui.widgets.AlphaAnimatedImageButton.class));
    view7f08013d = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.6(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131230834, "field 'mConnectBikeButton' and method 'connectBike'");
    mConnectBikeButton = ((Button)Utils.castView(localView, 2131230834, "field 'mConnectBikeButton'", Button.class));
    view7f080072 = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.7(this, paramMapControlsFragment));
    localView = Utils.findRequiredView(paramView, 2131231086, "field 'mConnectBikeIcon' and method 'connectBike'");
    mConnectBikeIcon = ((ImageView)Utils.castView(localView, 2131231086, "field 'mConnectBikeIcon'", ImageView.class));
    view7f08016e = localView;
    localView.setOnClickListener(new MapControlsFragment_ViewBinding.8(this, paramMapControlsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231036, "method 'showOptions' and method 'showTestOptions'");
    view7f08013c = paramView;
    paramView.setOnClickListener(new MapControlsFragment_ViewBinding.9(this, paramMapControlsFragment));
    paramView.setOnLongClickListener(new MapControlsFragment_ViewBinding.10(this, paramMapControlsFragment));
  }
  
  public void unbind()
  {
    MapControlsFragment localMapControlsFragment = target;
    if (localMapControlsFragment != null)
    {
      target = null;
      mSearchButton = null;
      mTripsButton = null;
      mLetsRideButton = null;
      mCenterButton = null;
      mPlusButton = null;
      mMinusButton = null;
      mConnectBikeButton = null;
      mConnectBikeIcon = null;
      view7f08013f.setOnClickListener(null);
      view7f08013f = null;
      view7f080140.setOnClickListener(null);
      view7f080140 = null;
      view7f08013b.setOnClickListener(null);
      view7f08013b = null;
      view7f08013a.setOnClickListener(null);
      view7f08013a = null;
      view7f08013e.setOnClickListener(null);
      view7f08013e = null;
      view7f08013d.setOnClickListener(null);
      view7f08013d = null;
      view7f080072.setOnClickListener(null);
      view7f080072 = null;
      view7f08016e.setOnClickListener(null);
      view7f08016e = null;
      view7f08013c.setOnClickListener(null);
      view7f08013c.setOnLongClickListener(null);
      view7f08013c = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
