package com.com.navapp.ui.guidance;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.Globals;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.Trip;
import com.com.navapp.search.Waypoint;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.here.android.ui.common.GeoPosition;
import java.util.ArrayList;
import java.util.List;

public class GuidanceSkipWaypointFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  static final String TAG = com.ktm.navapp.ui.guidance.GuidanceSkipWaypointFragment.class.getSimpleName();
  private boolean mIsResumed = false;
  private boolean mPopAfterPause = false;
  private Trip mTrip = null;
  @BindView(2131230945)
  protected TextView mTvDescription;
  @BindView(2131230942)
  protected TextView mTvFindingRouteWpName;
  @BindView(2131230937)
  protected View mVwFailedLayout;
  @BindView(2131230939)
  protected View mVwFindingLayout;
  @BindView(2131230943)
  protected View mVwSkipLayout;
  
  public GuidanceSkipWaypointFragment() {}
  
  private void showBusy(boolean paramBoolean)
  {
    if (getActivity() != null)
    {
      View localView = mVwFailedLayout;
      int j = 4;
      localView.setVisibility(4);
      localView = mVwFindingLayout;
      int i;
      if (paramBoolean) {
        i = 0;
      } else {
        i = 4;
      }
      localView.setVisibility(i);
      localView = mVwSkipLayout;
      if (paramBoolean) {
        i = j;
      } else {
        i = 0;
      }
      localView.setVisibility(i);
    }
  }
  
  private void showFailed()
  {
    if (getActivity() != null)
    {
      mVwFailedLayout.setVisibility(0);
      mVwFindingLayout.setVisibility(4);
      mVwSkipLayout.setVisibility(4);
    }
  }
  
  void calculateNewRoute(Trip paramTrip)
  {
    GeoPosition localGeoPosition = MapManager.getInstance().getCurrentPosition();
    if ((localGeoPosition != null) && (localGeoPosition.isValid()))
    {
      mTvFindingRouteWpName.setText(((Waypoint)paramTrip.getWaypoints().get(1)).getTitle());
      showBusy(true);
      RoutingManager.getInstance().loadRoute(localGeoPosition.getCoordinate(), paramTrip, new GuidanceSkipWaypointFragment.1(this, paramTrip));
      return;
    }
    RealmLog.d(TAG, "calculateNewRoute() NO VALID POSITION");
    showFailed();
  }
  
  public boolean onBackPressed()
  {
    onSkipCancelOrFailed();
    return true;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramViewGroup = paramLayoutInflater.inflate(2131427400, paramViewGroup, false);
    ButterKnife.bind(this, paramViewGroup);
    paramLayoutInflater = mTrip;
    if (paramLayoutInflater != null) {
      paramLayoutInflater = paramLayoutInflater.getWaypointsLeft();
    } else {
      paramLayoutInflater = null;
    }
    if ((paramLayoutInflater != null) && (paramLayoutInflater.size() > 1)) {
      paramLayoutInflater = ((Waypoint)paramLayoutInflater.get(0)).getTitle();
    } else {
      paramLayoutInflater = "";
    }
    showBusy(false);
    mTvDescription.setText(Globals.getContext().getString(2131558666, new Object[] { paramLayoutInflater }));
    return paramViewGroup;
  }
  
  public void onPause()
  {
    super.onPause();
    mIsResumed = false;
  }
  
  public void onResume()
  {
    super.onResume();
    mIsResumed = true;
    if (mPopAfterPause) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
  }
  
  public void onSkipCancelOrFailed()
  {
    RealmLog.i(TAG, "onSkipCancelOrFailed()");
    RoutingManager.getInstance().cancelCalculation();
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onSkipOk()
  {
    RealmLog.i(TAG, "onSkipOk()");
    Object localObject = mTrip;
    if (localObject != null) {
      localObject = ((Trip)localObject).getWaypointsLeft();
    } else {
      localObject = null;
    }
    if ((localObject != null) && (((List)localObject).size() > 1))
    {
      Trip localTrip = new Trip(mTrip);
      waypoints.clear();
      waypoints.add(Waypoint.MY_LOCATION);
      waypoints.addAll(((List)localObject).subList(1, ((List)localObject).size()));
      waypointReached = 0;
      keepMyLocation = true;
      calculateNewRoute(localTrip);
      return;
    }
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public GuidanceSkipWaypointFragment setTrip(Trip paramTrip)
  {
    mTrip = paramTrip;
    return this;
  }
}
