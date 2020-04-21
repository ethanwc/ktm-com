package com.com.navapp.ui.routing;

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
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.SavedTrips;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Utilities;
import com.here.android.ui.common.GeoPosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NoPreviewFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  static final String TAG = com.ktm.navapp.ui.routing.NoPreviewFragment.class.getSimpleName();
  private static Trip mNewChangesTrip;
  private final Runnable mContinueAfterSave = new Runnable()
  {
    public void run()
    {
      NoPreviewFragment.this.showScreen(null);
      calculateNewRoute();
    }
  };
  private boolean mIsResumed = false;
  private boolean mPopAfterPause = false;
  @BindView(2131230947)
  protected View mScreenChangesSaved;
  @BindView(2131230949)
  protected View mScreenFailed;
  @BindView(2131230950)
  protected View mScreenFindingRoute;
  @BindView(2131230954)
  protected View mScreenSaveChanges;
  @BindView(2131230955)
  protected TextView mTvSaveText;
  @BindView(2131230956)
  protected TextView mTvSaveTripName;
  
  public NoPreviewFragment() {}
  
  private void showScreen(View paramView)
  {
    if (getActivity() != null)
    {
      View localView = mScreenSaveChanges;
      int j = 0;
      int i;
      if (paramView == localView) {
        i = 0;
      } else {
        i = 4;
      }
      localView.setVisibility(i);
      localView = mScreenChangesSaved;
      if (paramView == localView) {
        i = 0;
      } else {
        i = 4;
      }
      localView.setVisibility(i);
      localView = mScreenFindingRoute;
      if (paramView == localView) {
        i = 0;
      } else {
        i = 4;
      }
      localView.setVisibility(i);
      localView = mScreenFailed;
      if (paramView == localView) {
        i = j;
      } else {
        i = 4;
      }
      localView.setVisibility(i);
    }
  }
  
  void calculateNewRoute()
  {
    Trip localTrip = TripManager.getTrip();
    GeoPosition localGeoPosition = MapManager.getInstance().getCurrentPosition();
    if ((localGeoPosition != null) && (localGeoPosition.isValid()))
    {
      showScreen(mScreenFindingRoute);
      RoutingManager.getInstance().loadRoute(localGeoPosition.getCoordinate(), localTrip, new NoPreviewFragment.2(this, localTrip));
      return;
    }
    RealmLog.d(TAG, "calculateNewRoute() NO VALID POSITION");
    showScreen(mScreenFailed);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    Object localObject1 = TripManager.getBackupTrip();
    Object localObject2 = TripManager.getTrip();
    paramBundle = TripManager.getChangesTrackingTrip();
    if ((saved) && (name != null) && (!((Trip)localObject1).equalsForRouting(localObject2)))
    {
      paramBundle.copyRouteOptions((Trip)localObject2);
      ArrayList localArrayList = paramBundle.getWaypoints();
      localObject1 = ((Trip)localObject1).getWaypointsRemoveMyLocation();
      localObject2 = ((Trip)localObject2).getWaypointsRemoveMyLocation();
      Object localObject3 = TAG;
      boolean bool;
      if (localArrayList.size() >= ((List)localObject1).size()) {
        bool = true;
      } else {
        bool = false;
      }
      RealmLog.Assert((String)localObject3, bool);
      localObject3 = localArrayList.subList(localArrayList.size() - ((List)localObject1).size(), localArrayList.size());
      RealmLog.Assert(TAG, ((List)localObject1).equals(localObject3));
      ((List)localObject3).clear();
      localArrayList.addAll((Collection)localObject2);
      waypoints.clear();
      waypoints.addAll(localArrayList);
      mNewChangesTrip = paramBundle;
      mTvSaveTripName.setText(String.format(Locale.getDefault(), "\"%s\"", new Object[] { paramBundle.getTitle() }));
      showScreen(mScreenSaveChanges);
      return;
    }
    mNewChangesTrip = null;
    showScreen(null);
    calculateNewRoute();
  }
  
  public boolean onBackPressed()
  {
    onCancelOrFailed();
    return true;
  }
  
  public void onCancelOrFailed()
  {
    RealmLog.i(TAG, "onCancelOrFailed()");
    RoutingManager.getInstance().cancelCalculation();
    TripManager.restoreTrip(false);
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427401, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mTvSaveText.setText(Utilities.replaceBoldWithColor(getContext().getString(2131558512), getContext().getColor(2131034172)));
    return paramLayoutInflater;
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
  
  public void onSaveTripNo()
  {
    RealmLog.i(TAG, "onSaveTripNo()");
    calculateNewRoute();
  }
  
  public void onSaveTripYes()
  {
    RealmLog.i(TAG, "onSaveTripYes()");
    SavedTrips.addToSavedTrips(mNewChangesTrip);
    showScreen(mScreenChangesSaved);
    Events.postDelayed(mContinueAfterSave, 2000L);
  }
  
  public void onSavedOk()
  {
    RealmLog.i(TAG, "onSavedOk()");
    Events.cancel(mContinueAfterSave);
    calculateNewRoute();
  }
}
