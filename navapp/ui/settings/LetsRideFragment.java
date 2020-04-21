package com.com.navapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.ButterKnife;
import com.com.navapp.search.Trip;
import com.com.navapp.search.Trip.RouteType;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.MainScreen;

public class LetsRideFragment
  extends NavAppBaseFragment
{
  public LetsRideFragment() {}
  
  public void closeLetsRide()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427406, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onMyTrips()
  {
    ((MainScreen)getActivity()).setLoadTripView();
  }
  
  public void onNavigation()
  {
    TripManager.setRouteType(Trip.RouteType.ROUTE);
    ((MainScreen)getActivity()).setSearchView(false, -1);
  }
  
  public void onSmartNavigation()
  {
    TripManager.setRouteType(Trip.RouteType.SMART_ROUTE);
    ((MainScreen)getActivity()).setSearchView(false, -1);
  }
  
  public void onSmartRoundTrip()
  {
    TripManager.setRouteType(Trip.RouteType.SMART_ROUND_TRIP);
    TripManager.initTrip(null);
    Trip localTrip = TripManager.getTrip();
    name = "Round Trip";
    TripManager.setTrip(localTrip);
    ((MainScreen)getActivity()).setRoutePreviewView(false);
  }
}
