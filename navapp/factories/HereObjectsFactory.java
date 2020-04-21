package com.com.navapp.factories;

import com.here.android.ui.common.GeoBoundingBox;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.Image;
import com.here.android.ui.mapping.MapMarker;
import com.here.android.ui.mapping.MapRoute;
import com.here.android.ui.routing.CoreRouter;
import com.here.android.ui.routing.Route;
import com.here.android.ui.routing.RouteOptions;
import com.here.android.ui.routing.RoutePlan;
import com.here.android.ui.routing.RouteWaypoint;
import com.here.android.ui.search.ExploreRequest;
import com.here.android.ui.search.HereRequest;
import com.here.android.ui.search.Location;
import com.here.android.ui.search.ReverseGeocodeRequest;
import com.here.android.ui.search.SearchRequest;

public class HereObjectsFactory
{
  public HereObjectsFactory() {}
  
  public static CoreRouter getCoreRouter()
  {
    return new CoreRouter();
  }
  
  public static ExploreRequest getExploreRequest()
  {
    return new ExploreRequest();
  }
  
  public static GeoBoundingBox getGeoBoundingBox(GeoCoordinate paramGeoCoordinate1, GeoCoordinate paramGeoCoordinate2)
  {
    return new GeoBoundingBox(paramGeoCoordinate1, paramGeoCoordinate2);
  }
  
  public static GeoCoordinate getGeoCoordinate(double paramDouble1, double paramDouble2)
  {
    return new GeoCoordinate(paramDouble1, paramDouble2);
  }
  
  public static HereRequest getHereRequest()
  {
    return new HereRequest();
  }
  
  public static Location getLocation(GeoCoordinate paramGeoCoordinate)
  {
    return new Location(paramGeoCoordinate);
  }
  
  public static MapMarker getMapMarker(GeoCoordinate paramGeoCoordinate, Image paramImage)
  {
    return new MapMarker(paramGeoCoordinate, paramImage);
  }
  
  public static MapRoute getMapRoute()
  {
    return new MapRoute();
  }
  
  public static MapRoute getMapRoute(Route paramRoute)
  {
    return new MapRoute(paramRoute);
  }
  
  public static ReverseGeocodeRequest getReverseGeocodeRequest(GeoCoordinate paramGeoCoordinate)
  {
    return new ReverseGeocodeRequest(paramGeoCoordinate);
  }
  
  public static RouteOptions getRouteOptions()
  {
    return new RouteOptions();
  }
  
  public static RoutePlan getRoutePlan()
  {
    return new RoutePlan();
  }
  
  public static RouteWaypoint getRouteWaypoint(double paramDouble1, double paramDouble2)
  {
    return new RouteWaypoint(getGeoCoordinate(paramDouble1, paramDouble2));
  }
  
  public static RouteWaypoint getRouteWaypoint(GeoCoordinate paramGeoCoordinate)
  {
    return new RouteWaypoint(paramGeoCoordinate);
  }
  
  public static SearchRequest getSearchRequest(String paramString)
  {
    return new SearchRequest(paramString);
  }
}
