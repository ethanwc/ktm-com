package com.com.navapp.routing;

import android.os.Handler;
import android.os.Looper;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.factories.HereObjectsFactory;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.riser.Client;
import com.com.navapp.riser.Client.API_RISER;
import com.com.navapp.riser.Model.LatLong;
import com.com.navapp.riser.Model.LatLongs;
import com.com.navapp.search.PlacesApi;
import com.com.navapp.search.Trip;
import com.com.navapp.search.Trip.RouteType;
import com.com.navapp.utilities.Events;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.RoadElement;
import com.here.android.ui.common.RoadElement.Attribute;
import com.here.android.ui.guidance.NavigationManager.UnitSystem;
import com.here.android.ui.routing.CoreRouter;
import com.here.android.ui.routing.CoreRouter.Connectivity;
import com.here.android.ui.routing.Route;
import com.here.android.ui.routing.RouteElement;
import com.here.android.ui.routing.RouteElements;
import com.here.android.ui.routing.RouteOptions;
import com.here.android.ui.routing.RouteOptions.TransportMode;
import com.here.android.ui.routing.RouteOptions.Type;
import com.here.android.ui.routing.RoutePlan;
import com.here.android.ui.routing.RouteTta;
import com.ktm.navapp.riser.Model.RouteResponse;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;

public class RoutingManager
{
  private static final boolean EDITABLE_START = true;
  private static RoutingManager INSTANCE;
  private static final double MILE_IN_METERS = 1609.344D;
  private static final String ds = com.ktm.navapp.routing.RoutingManager.class.getSimpleName();
  private static final Handler mHandler = new Handler(Looper.getMainLooper());
  private RoutingCallback mCallback;
  private boolean mCancelling;
  private CoreRouter mCoreRouter;
  private boolean mProcessedOffline = false;
  private RouteResult mProcessedResult = RouteResult.CANCELLED;
  private Trip mProcessedTrip = null;
  private RouteOptions mRouteOptions;
  private RoutePlan mRoutePlan;
  private final List<com.here.android.mpa.mapping.MapRoute> mRoutes = new ArrayList();
  private com.here.android.ui.mapping.MapRoute mSelectedRoute;
  private Call<Model.RouteResponse> mSmartRequest;
  private final ArrayList<com.ktm.navapp.search.Waypoint> mSmartResult = new ArrayList();
  private long mSmartResultCurvature = 0L;
  
  private RoutingManager()
  {
    initialize();
  }
  
  private void callback(RouteResult paramRouteResult)
  {
    RealmLog.append(ds, "callback( %s )", new Object[] { String.valueOf(paramRouteResult.toString()) });
    RoutingCallback localRoutingCallback = mCallback;
    mCallback = null;
    mCancelling = false;
    mProcessedResult = paramRouteResult;
    if (paramRouteResult == RouteResult.CANCELLED)
    {
      mProcessedTrip = null;
    }
    else if (paramRouteResult != RouteResult.SUCCESS)
    {
      mRoutes.clear();
      mSelectedRoute = null;
    }
    else if (mSelectedRoute == null) {}
    if (localRoutingCallback != null) {
      localRoutingCallback.onResult(paramRouteResult, true);
    }
  }
  
  public static String formatDistance(double paramDouble, boolean paramBoolean)
  {
    Object localObject = OptionsManager.getInstance().getUnitSystem();
    double d;
    if ((localObject != NavigationManager.UnitSystem.IMPERIAL) && (localObject != NavigationManager.UnitSystem.IMPERIAL_US))
    {
      localObject = "KM";
      d = 1000.0D;
    }
    else
    {
      d = 1609.344D;
      localObject = "MI";
    }
    if (paramBoolean)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.format(Locale.getDefault(), "%d", new Object[] { Long.valueOf(Math.round(paramDouble / d)) }));
      localStringBuilder.append((String)localObject);
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.format(Locale.getDefault(), "%.2f", new Object[] { Double.valueOf(paramDouble / d) }));
    localStringBuilder.append((String)localObject);
    return localStringBuilder.toString();
  }
  
  public static RoutingManager getInstance()
  {
    try
    {
      if (INSTANCE == null) {
        INSTANCE = new RoutingManager();
      }
      RoutingManager localRoutingManager = INSTANCE;
      return localRoutingManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private void loadRoute0(Trip paramTrip, RoutingCallback paramRoutingCallback)
  {
    for (;;)
    {
      try
      {
        String str = ds;
        com.com.navapp.search.Waypoint localWaypoint = paramTrip.getStart();
        boolean bool2 = true;
        if (localWaypoint != null)
        {
          bool1 = true;
          RealmLog.Assert(str, bool1);
          bool1 = bool2;
          if (!OptionsManager.getInstance().getUseOfflineMaps())
          {
            if (OfflineMapsManager.getInstance().isNetworkConnected()) {
              break label252;
            }
            bool1 = bool2;
          }
          if ((mProcessedOffline == bool1) && (paramTrip.equalsForRouting(mProcessedTrip)))
          {
            if (isBusy())
            {
              mCallback = paramRoutingCallback;
              RealmLog.i(ds, "loadRoute(): ACCEPTING ONGOING CALCULATION");
              return;
            }
            if (mProcessedResult != RouteResult.CANCELLED)
            {
              Events.postOnMainThread(new RoutingManager.4(this, paramRoutingCallback));
              return;
            }
          }
          mHandler.removeCallbacksAndMessages(null);
          if (isBusy())
          {
            RealmLog.i(ds, "loadRoute(): CANCELLING CURRENT REQUEST");
            cancelCalculation();
            mHandler.postDelayed(new RoutingManager.5(this, paramTrip, paramRoutingCallback), 100L);
            return;
          }
          mCallback = paramRoutingCallback;
          mCancelling = false;
          mProcessedResult = RouteResult.CANCELLED;
          mProcessedTrip = paramTrip;
          mProcessedOffline = bool1;
          mRoutes.clear();
          mSelectedRoute = null;
          RealmLog.i(ds, "loadRoute(): NEW REQUEST");
          loadRoute00(mProcessedTrip, mProcessedOffline);
          return;
        }
      }
      catch (Throwable paramTrip)
      {
        throw paramTrip;
      }
      boolean bool1 = false;
      continue;
      label252:
      bool1 = false;
    }
  }
  
  private void loadRoute00(Trip paramTrip, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (routeType != Trip.RouteType.ROUTE)
        {
          Client.API_RISER localAPI_RISER = Client.create().apiRiser();
          localObject1 = routeType;
          Object localObject3 = Trip.RouteType.SMART_ROUND_TRIP;
          localInteger = null;
          Object localObject2 = null;
          if (localObject1 == localObject3)
          {
            RealmLog.i(ds, "loadRoute(): smartroute requesting ROUN_TRIP");
            localObject1 = paramTrip.getStart();
            localObject3 = new Model.LatLong(latitude, longitude);
            if ((!smartRoundUseDuration) || (smartRoundDurationMinutes <= 0)) {
              break label322;
            }
            localObject1 = Integer.valueOf(smartRoundDurationMinutes);
            if ((smartRoundUseDuration) || (smartRoundDistanceMeters <= 0)) {
              break label327;
            }
            localInteger = Integer.valueOf(smartRoundDistanceMeters);
            if (smartRoundWeightFactor100 > 0) {
              localObject2 = Integer.valueOf(smartRoundWeightFactor100);
            }
            mSmartRequest = localAPI_RISER.getRoundTrip((Model.LatLong)localObject3, (Integer)localObject1, localInteger, (Integer)localObject2);
          }
          else
          {
            RealmLog.i(ds, "loadRoute(): smartroute requesting ROUTE");
            localObject2 = new Model.LatLongs();
            localObject1 = paramTrip.getWaypoints().iterator();
            if (((Iterator)localObject1).hasNext())
            {
              localObject3 = (com.com.navapp.search.Waypoint)((Iterator)localObject1).next();
              ((Model.LatLongs)localObject2).update(latitude, longitude);
              continue;
            }
            if ((!smartRouteUseDuration) || (smartRouteDurationMinutes <= 0)) {
              break label333;
            }
            localObject1 = Integer.valueOf(smartRouteDurationMinutes);
            if (smartRouteWeightFactor100 > 0) {
              localInteger = Integer.valueOf(smartRouteWeightFactor100);
            }
            mSmartRequest = localAPI_RISER.getRoute((Model.LatLongs)localObject2, (Integer)localObject1, localInteger);
          }
          localObject1 = mSmartRequest;
          ((Call)localObject1).enqueue(new RoutingManager.6(this, (Call)localObject1, paramTrip, paramBoolean));
        }
        else
        {
          loadRouteHERE(paramTrip, paramBoolean);
        }
        return;
      }
      catch (Throwable paramTrip)
      {
        throw paramTrip;
      }
      label322:
      Object localObject1 = null;
      continue;
      label327:
      Integer localInteger = null;
      continue;
      label333:
      localObject1 = null;
    }
  }
  
  private void loadRouteHERE(Trip paramTrip, boolean paramBoolean)
  {
    try
    {
      mRoutePlan.removeAllWaypoints();
      Object localObject;
      if (routeType != Trip.RouteType.ROUTE)
      {
        mRouteOptions.setTollRoadsAllowed(true);
        mRouteOptions.setHighwaysAllowed(true);
        mRouteOptions.setDirtRoadsAllowed(true);
        mRouteOptions.setRouteType(RouteOptions.Type.SHORTEST);
        mRouteOptions.setTransportMode(RouteOptions.TransportMode.KATAKANA);
        mRouteOptions.setRouteCount(1);
        paramTrip = mSmartResult.iterator();
        while (paramTrip.hasNext())
        {
          localObject = (com.com.navapp.search.Waypoint)paramTrip.next();
          mRoutePlan.addWaypoint(HereObjectsFactory.getRouteWaypoint(latitude, longitude));
        }
      }
      mRouteOptions.setTollRoadsAllowed(allowTolls);
      mRouteOptions.setHighwaysAllowed(allowHighways);
      mRouteOptions.setDirtRoadsAllowed(allowDirtRoads);
      RouteOptions localRouteOptions = mRouteOptions;
      if (fastestRoute) {
        localObject = RouteOptions.Type.FASTEST;
      } else {
        localObject = RouteOptions.Type.SHORTEST;
      }
      localRouteOptions.setRouteType((RouteOptions.Type)localObject);
      mRouteOptions.setTransportMode(RouteOptions.TransportMode.KATAKANA);
      mRouteOptions.setRouteCount(5);
      paramTrip = paramTrip.getWaypoints().iterator();
      while (paramTrip.hasNext())
      {
        localObject = (com.com.navapp.search.Waypoint)paramTrip.next();
        mRoutePlan.addWaypoint(HereObjectsFactory.getRouteWaypoint(latitude, longitude));
      }
      mRoutePlan.setRouteOptions(mRouteOptions);
      paramTrip = mRoutePlan.getRouteOptions();
      if (paramBoolean)
      {
        RealmLog.i(ds, "loadRoute().setConnectivity(OFFLINE)");
        mCoreRouter.setConnectivity(CoreRouter.Connectivity.OFFLINE);
      }
      else
      {
        RealmLog.i(ds, "loadRoute().setConnectivity(ONLINE)");
        mCoreRouter.setConnectivity(CoreRouter.Connectivity.ONLINE);
      }
      mCoreRouter.calculateRoute(mRoutePlan, new RoutingManager.7(this, paramTrip));
      return;
    }
    catch (Throwable paramTrip)
    {
      throw paramTrip;
    }
  }
  
  static com.here.android.ui.mapping.MapRoute selectRoute(List paramList, RouteOptions paramRouteOptions)
  {
    int n = Integer.MAX_VALUE;
    int k = Integer.MAX_VALUE;
    int i = 0;
    int i2;
    for (int j = 0; i < paramList.size(); j = i2)
    {
      Route localRoute = ((com.here.android.ui.mapping.MapRoute)paramList.get(i)).getRoute();
      int i3 = localRoute.getLength();
      int i4 = localRoute.getTtaExcludingTraffic(268435455).getDuration();
      RealmLog.d(ds, "selectRoute() ROUTE-%d: length=%d, duration=%d", new Object[] { Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i4) });
      int m = 8.$SwitchMap$com$here$android$mpa$routing$RouteOptions$Type[paramRouteOptions.getRouteType().ordinal()];
      int i1;
      if (m != 1) {
        if (m != 2)
        {
          m = n;
          i1 = k;
          i2 = j;
          if (i != 0) {
            break label243;
          }
        }
      }
      for (;;)
      {
        i1 = i4;
        m = i3;
        i2 = j;
        break;
        if (i4 >= k)
        {
          m = n;
          i1 = k;
          i2 = j;
          if (i4 != k) {
            break;
          }
          m = n;
          i1 = k;
          i2 = j;
          if (i3 >= n) {
            break;
          }
          break label238;
          if (i3 >= n)
          {
            m = n;
            i1 = k;
            i2 = j;
            if (i3 != n) {
              break;
            }
            m = n;
            i1 = k;
            i2 = j;
            if (i4 >= k) {
              break;
            }
          }
        }
        label238:
        j = i;
      }
      label243:
      i += 1;
      n = m;
      k = i1;
    }
    RealmLog.append(ds, "selectRoute() SELECTED(%s): ROUTE-%d: length=%d, duration=%d", new Object[] { paramRouteOptions.getRouteType().name(), Integer.valueOf(j), Integer.valueOf(n), Integer.valueOf(k) });
    paramRouteOptions = (com.here.android.ui.mapping.MapRoute)paramList.remove(j);
    while (paramList.size() > 2) {
      paramList.remove(paramList.size() - 1);
    }
    paramList.add(paramRouteOptions);
    return paramRouteOptions;
  }
  
  public void cancelCalculation()
  {
    try
    {
      RealmLog.append(ds, "cancelCalculation() isBusy=%b", new Object[] { Boolean.valueOf(isBusy()) });
      if (isBusy())
      {
        mCancelling = true;
        mCoreRouter.cancel();
        if (mSmartRequest != null)
        {
          mSmartRequest.cancel();
          mSmartRequest = null;
          Events.postOnMainThread(new RoutingManager.1(this));
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void destroy()
  {
    INSTANCE = null;
  }
  
  public String getDistance()
  {
    try
    {
      String str = getDistance(false);
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public String getDistance(boolean paramBoolean)
  {
    try
    {
      String str;
      if (mSelectedRoute == null) {
        str = "...";
      } else {
        str = formatDistance(mSelectedRoute.getRoute().getLength(), paramBoolean);
      }
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getDistanceMeters()
  {
    com.here.android.ui.mapping.MapRoute localMapRoute = mSelectedRoute;
    if (localMapRoute != null) {
      return localMapRoute.getRoute().getLength();
    }
    return 0;
  }
  
  public long getRawDistance()
  {
    Object localObject = mSelectedRoute;
    if (localObject == null) {
      return 0L;
    }
    double d2 = ((com.here.android.ui.mapping.MapRoute)localObject).getRoute().getLength();
    localObject = OptionsManager.getInstance().getUnitSystem();
    double d1 = 1000.0D;
    if ((localObject == NavigationManager.UnitSystem.IMPERIAL) || (localObject == NavigationManager.UnitSystem.IMPERIAL_US)) {
      d1 = 1609.344D;
    }
    return Math.round(d2 / d1);
  }
  
  public List getRoutes()
  {
    try
    {
      List localList = mRoutes;
      return localList;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public com.here.android.ui.mapping.MapRoute getSelectedRoute()
  {
    try
    {
      com.here.android.ui.mapping.MapRoute localMapRoute = mSelectedRoute;
      return localMapRoute;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public String getTime()
  {
    try
    {
      if (mSelectedRoute == null) {
        return "...";
      }
      int j = mSelectedRoute.getRoute().getTtaExcludingTraffic(268435455).getDuration() / 60;
      int i = j % 60;
      j /= 60;
      if (j < 1)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(String.valueOf(i));
        ((StringBuilder)localObject).append("min");
        localObject = ((StringBuilder)localObject).toString();
        return localObject;
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(String.valueOf(j));
      ((StringBuilder)localObject).append("h ");
      ((StringBuilder)localObject).append(String.valueOf(i));
      ((StringBuilder)localObject).append("m");
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public String getUnitFormat()
  {
    NavigationManager.UnitSystem localUnitSystem = OptionsManager.getInstance().getUnitSystem();
    if (localUnitSystem == NavigationManager.UnitSystem.IMPERIAL) {
      return "MI";
    }
    if (localUnitSystem == NavigationManager.UnitSystem.IMPERIAL_US) {
      return "MI";
    }
    return "KM";
  }
  
  public boolean hasRoute()
  {
    try
    {
      com.here.android.ui.mapping.MapRoute localMapRoute = mSelectedRoute;
      boolean bool;
      if (localMapRoute != null) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean hasTollRoads()
  {
    Object localObject = mSelectedRoute;
    if (localObject != null)
    {
      localObject = ((com.here.android.ui.mapping.MapRoute)localObject).getRoute().getRouteElements().getElements().iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((RouteElement)((Iterator)localObject).next()).getRoadElement().getAttributes().contains(RoadElement.Attribute.TOLLROAD)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void initialize()
  {
    mCoreRouter = HereObjectsFactory.getCoreRouter();
    mRoutePlan = HereObjectsFactory.getRoutePlan();
    mRouteOptions = HereObjectsFactory.getRouteOptions();
    mCallback = null;
    mCancelling = false;
    mProcessedResult = RouteResult.CANCELLED;
    mProcessedTrip = null;
    mRoutes.clear();
    mSelectedRoute = null;
    mSmartRequest = null;
  }
  
  public boolean isBusy()
  {
    try
    {
      RoutingCallback localRoutingCallback = mCallback;
      boolean bool;
      if (localRoutingCallback != null) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void loadRoute(GeoCoordinate paramGeoCoordinate, Trip paramTrip, RoutingCallback paramRoutingCallback)
  {
    try
    {
      if (paramTrip.getStart() != null) {
        loadRoute0(paramTrip, paramRoutingCallback);
      } else if (paramGeoCoordinate.isValid())
      {
        if (!keepMyLocation)
        {
          PlacesApi.reverseGeocode2(paramGeoCoordinate.getLatitude(), paramGeoCoordinate.getLongitude(), new RoutingManager.2(this, paramTrip, paramGeoCoordinate, paramRoutingCallback));
        }
        else
        {
          paramTrip.finalizeStart(com.com.navapp.search.Waypoint.address2destination(paramGeoCoordinate.getLatitude(), paramGeoCoordinate.getLongitude(), null));
          loadRoute0(paramTrip, paramRoutingCallback);
        }
      }
      else {
        Events.postOnMainThread(new RoutingManager.3(this, paramRoutingCallback));
      }
      return;
    }
    catch (Throwable paramGeoCoordinate)
    {
      throw paramGeoCoordinate;
    }
  }
  
  public void onRouteSelected(com.here.android.ui.mapping.MapRoute paramMapRoute)
  {
    try
    {
      RealmLog.i(ds, "onRouteSelected()");
      if (mSelectedRoute != paramMapRoute)
      {
        MapManager.getInstance().changeRouteSelection(mSelectedRoute, paramMapRoute);
        mSelectedRoute = paramMapRoute;
      }
      return;
    }
    catch (Throwable paramMapRoute)
    {
      throw paramMapRoute;
    }
  }
  
  public void restoreSelectedRoute(com.here.android.ui.mapping.MapRoute paramMapRoute)
  {
    try
    {
      RealmLog.i(ds, "restoreSelectedRoute()");
      cancelCalculation();
      mProcessedResult = RouteResult.SUCCESS;
      mProcessedTrip = null;
      mRoutes.clear();
      mRoutes.add(paramMapRoute);
      mSelectedRoute = ((com.here.android.ui.mapping.MapRoute)mRoutes.get(0));
      return;
    }
    catch (Throwable paramMapRoute)
    {
      throw paramMapRoute;
    }
  }
  
  public void setRoute(Route paramRoute)
  {
    try
    {
      RealmLog.i(ds, "setRoute()");
      MapManager.getInstance().clearRoutes();
      cancelCalculation();
      mProcessedResult = RouteResult.SUCCESS;
      mProcessedTrip = null;
      mRoutes.clear();
      mRoutes.add(HereObjectsFactory.getMapRoute(paramRoute));
      mSelectedRoute = ((com.here.android.ui.mapping.MapRoute)mRoutes.get(0));
      return;
    }
    catch (Throwable paramRoute)
    {
      throw paramRoute;
    }
  }
}
