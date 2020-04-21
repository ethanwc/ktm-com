package com.com.navapp.ui.mainscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.fragment.package_9.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.connectivity.bluetooth.BtControl;
import com.com.navapp.connectivity.bluetooth.BtControl.State;
import com.com.navapp.connectivity.endpoints.Dashboard;
import com.com.navapp.connectivity.endpoints.DashboardConnectionListener;
import com.com.navapp.connectivity.endpoints.DashboardManager;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.search.SavedTrips;
import com.com.navapp.search.Waypoint;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.widgets.AlphaAnimatedImageButton;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Utilities;
import com.here.android.ui.common.GeoCoordinate;
import com.ktm.navapp.ui.welcome.WelcomeScreenSelectBike;
import java.util.List;

public class MapControlsFragment
  extends NavAppBaseFragment
  implements DashboardConnectionListener
{
  @BindView(2131231034)
  AlphaAnimatedImageButton mCenterButton;
  private GeoCoordinate mCenterLastLocation = null;
  private double mCenterLastZoom = 18.0D;
  private boolean mCenterOnUpdateLocationLastState = true;
  @BindView(2131230834)
  Button mConnectBikeButton;
  @BindView(2131231086)
  ImageView mConnectBikeIcon;
  private Dashboard mConnectedBike = null;
  @BindView(2131231035)
  ImageButton mLetsRideButton;
  @BindView(2131231037)
  AlphaAnimatedImageButton mMinusButton;
  @BindView(2131231038)
  AlphaAnimatedImageButton mPlusButton;
  @BindView(2131231039)
  ImageButton mSearchButton;
  @BindView(2131231040)
  ImageButton mTripsButton;
  
  public MapControlsFragment() {}
  
  private void connectFavoriteMotorbike()
  {
    Dashboard localDashboard = DashboardManager.getInstance().getConnectedDashboard();
    Object localObject = DashboardManager.getInstance().favorite();
    if (localDashboard != null)
    {
      if ((localObject != null) && (((Dashboard)localObject).getName() != null)) {
        localObject = ((Dashboard)localObject).getName();
      } else {
        localObject = localDashboard.getName();
      }
      mConnectedBike = localDashboard;
      setConnectedBikeNameAndConnectedBikeIcon((String)localObject, true);
      return;
    }
    setConnectedBikeNameAndConnectedBikeIcon(getString(2131558567), false);
    if (localObject != null)
    {
      int i = 4.$SwitchMap$com$ktm$navapp$connectivity$bluetooth$BtControl$State[BtControl.getInstance().getState().ordinal()];
      if ((i != 1) && (i != 2))
      {
        if (i != 3)
        {
          if (i != 4) {
            return;
          }
          BtControl.getInstance().enable(getActivity(), 2410);
          return;
        }
        Events.doInBackground(new MapControlsFragment.3(this));
      }
    }
  }
  
  private void setConnectedBikeNameAndConnectedBikeIcon(String paramString, boolean paramBoolean)
  {
    getActivity().runOnUiThread(new MapControlsFragment.2(this, paramString, paramBoolean));
  }
  
  public void centerMap()
  {
    ((MainScreen)getActivity()).mapControlsShowButtons();
    MapManager.getInstance().setShowCurrentLocation(true, true);
  }
  
  public void connectBike()
  {
    Object localObject = BtControl.getInstance().getState();
    if (localObject == BtControl.State.UNAVAILABLE)
    {
      Utilities.toastShort("Bluetooth is not available on your device!");
      return;
    }
    if (localObject == BtControl.State.DISABLED)
    {
      Utilities.toastShort("enable bt!");
      if (((getActivity() instanceof MainScreen)) && (BtControl.getInstance().enable(getActivity(), 2001) == BtControl.State.DISABLED)) {
        Utilities.toastShort("Bluetooth error occured!");
      }
    }
    else if (localObject == BtControl.State.ENABLED)
    {
      localObject = new Intent(getActivity(), WelcomeScreenSelectBike.class);
      ((Intent)localObject).setFlags(603979776);
      startActivity((Intent)localObject);
    }
  }
  
  public void dashboardConnected(Dashboard paramDashboard)
  {
    mConnectedBike = paramDashboard;
    setConnectedBikeNameAndConnectedBikeIcon(paramDashboard.getName(), true);
  }
  
  public void dashboardDisConnected(Dashboard paramDashboard)
  {
    Dashboard localDashboard = mConnectedBike;
    if (localDashboard != null)
    {
      if (localDashboard.macAddress().compareTo(paramDashboard.macAddress()) != 0) {
        return;
      }
      mConnectedBike = null;
    }
    setConnectedBikeNameAndConnectedBikeIcon(getString(2131558567), false);
  }
  
  public void hideNavigateButtons()
  {
    mCenterButton.hide();
    mPlusButton.hide();
    mMinusButton.hide(new MapControlsFragment.1(this));
  }
  
  public void letsRide()
  {
    ((MainScreen)getActivity()).setLetsRideMenuView();
  }
  
  public void loadTrip()
  {
    ((MainScreen)getActivity()).setLoadTripView();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427409, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mCenterButton.setStartAlpha(0.0F, false);
    mPlusButton.setStartAlpha(0.0F, false);
    mMinusButton.setStartAlpha(0.0F, false);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    return paramLayoutInflater;
  }
  
  public void onDealerClicked(Waypoint paramWaypoint)
  {
    ((MainScreen)getActivity()).setDealerSelectedView(paramWaypoint);
  }
  
  public void onPause()
  {
    super.onPause();
    Events.unregister(this);
    DashboardManager.getInstance().setDashboardConnectionListener(null);
    mCenterLastLocation = MapManager.getInstance().getCenter();
    mCenterLastZoom = MapManager.getInstance().getZoom();
    mCenterOnUpdateLocationLastState = MapManager.getInstance().isFollowingCurrentLocation();
    RealmLog.append(_name, "mCenterOnUpdateLocationLastState=%b", new Object[] { Boolean.valueOf(mCenterOnUpdateLocationLastState) });
    MapManager.getInstance().setShowCurrentLocation(true, false);
    ((MainScreen)getActivity()).disableOnMapGestures();
  }
  
  public void onResume()
  {
    super.onResume();
    DashboardManager.getInstance().setDashboardConnectionListener(this);
    connectFavoriteMotorbike();
    ((MainScreen)getActivity()).enableOnMapGestures();
    MapManager.getInstance().setShowCurrentLocation(true, mCenterOnUpdateLocationLastState);
    if ((mCenterLastLocation != null) && (!mCenterOnUpdateLocationLastState)) {
      MapManager.getInstance().setCenter(mCenterLastLocation, mCenterLastZoom, true);
    }
    ImageButton localImageButton = mLetsRideButton;
    int i = 4;
    localImageButton.setVisibility(4);
    mSearchButton.setVisibility(0);
    localImageButton = mTripsButton;
    if (SavedTrips.getSavedTrips().size() > 0) {
      i = 0;
    }
    localImageButton.setVisibility(i);
    Events.register(this);
  }
  
  public void search()
  {
    ((MainScreen)getActivity()).setSearchView(false, -1);
  }
  
  public void showNavigateButtons()
  {
    mSearchButton.setImageResource(2131166012);
    mLetsRideButton.setImageResource(2131165894);
    mCenterButton.show();
    mPlusButton.show();
    mMinusButton.show();
  }
  
  public void showOptions()
  {
    ((MainScreen)getActivity()).setSettingsView();
  }
  
  public boolean showTestOptions()
  {
    return false;
  }
  
  public void zoomDown()
  {
    ((MainScreen)getActivity()).mapControlsShowButtons();
    MapManager.getInstance().zoomOut(true);
  }
  
  public void zoomUp()
  {
    ((MainScreen)getActivity()).mapControlsShowButtons();
    MapManager.getInstance().zoomIn(true, true);
  }
}
