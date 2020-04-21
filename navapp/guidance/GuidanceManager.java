package com.com.navapp.guidance;

import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.com.navapp.Globals;
import com.com.navapp.factories.HereObjectsFactory;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.TripManager;
import com.com.navapp.search.Waypoint;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.NavigationManagerProxy;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.guidance.NavigationManager;
import com.here.android.ui.guidance.NavigationManager.AudioEvent;
import com.here.android.ui.guidance.NavigationManager.AudioPlayer;
import com.here.android.ui.guidance.NavigationManager.MapUpdateMode;
import com.here.android.ui.guidance.NavigationManager.NavigationState;
import com.here.android.ui.mapping.MapRoute;
import com.here.android.ui.mapping.PositionIndicator;
import com.here.android.ui.mapping.class_3;
import com.here.android.ui.routing.Route;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class GuidanceManager
{
  private static GuidanceManager instance;
  private static boolean sGuidance;
  private static final String track = com.ktm.navapp.guidance.GuidanceManager.class.getSimpleName();
  MapRoute mMapRoute;
  
  private GuidanceManager() {}
  
  private void configureGuidanceMap(boolean paramBoolean)
  {
    if (MapManager.getInstance().getMap() == null)
    {
      Events.postDelayed(new GuidanceManager.1(this, paramBoolean), 1000L);
      return;
    }
    MapRoute localMapRoute;
    if (paramBoolean) {
      localMapRoute = null;
    } else {
      localMapRoute = RoutingManager.getInstance().getSelectedRoute();
    }
    configureGuidanceMap(localMapRoute);
  }
  
  public static GuidanceManager getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new GuidanceManager();
      }
      GuidanceManager localGuidanceManager = instance;
      return localGuidanceManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static boolean isGuidanceView()
  {
    return sGuidance;
  }
  
  public static void setGuidanceView(boolean paramBoolean1, boolean paramBoolean2)
  {
    sGuidance = paramBoolean1;
    NavigationManager localNavigationManager = NavigationManagerProxy.getInstance();
    NavigationManager.MapUpdateMode localMapUpdateMode;
    if (paramBoolean1) {
      localMapUpdateMode = NavigationManager.MapUpdateMode.ROADVIEW;
    } else {
      localMapUpdateMode = NavigationManager.MapUpdateMode.ROADVIEW_NOZOOM;
    }
    localNavigationManager.setMapUpdateMode(localMapUpdateMode);
    MapManager.getInstance().setGuidance(paramBoolean1, paramBoolean2);
  }
  
  public static void setNoMapUpdate()
  {
    sGuidance = false;
    NavigationManagerProxy.getInstance().setMapUpdateMode(NavigationManager.MapUpdateMode.NONE);
    MapManager.getInstance().setGuidance(false, false);
  }
  
  public void cancelGuidance()
  {
    RealmLog.append(track, "cancelGuidance()");
    if (NavigationManagerProxy.getInstance().getRunningState() != NavigationManager.NavigationState.IDLE) {
      NavigationManagerProxy.getInstance().stop();
    }
    NavigationManagerProxy.getInstance().getAudioPlayer().stop();
    MapManager.getInstance().getPositionIndicator().setVisible(false);
    setGuidanceView(false, false);
    OptionsManager.getInstance().navigateWithOptions();
    MapManager.getInstance().clearRoutes();
    MapManager.getInstance().clearMarkers();
  }
  
  void configureGuidanceMap(MapRoute paramMapRoute)
  {
    MapManager.getInstance().setShowCurrentLocation(false, false);
    class_3 localClass_3 = MapManager.getInstance().getMap();
    PositionIndicator localPositionIndicator = MapManager.getInstance().getPositionIndicator();
    String str = OptionsManager.getInstance().getMapType();
    switch (str.hashCode())
    {
    default: 
      break;
    case 1571582122: 
      if (str.equals("hybrid.day")) {
        i = 2;
      }
      break;
    case -437789341: 
      if (str.equals("terrain.day")) {
        i = 1;
      }
      break;
    case -978706327: 
      if (str.equals("satellite.day")) {
        i = 3;
      }
      break;
    case -1256825323: 
      if (str.equals("normal.day")) {
        i = 0;
      }
      break;
    }
    int i = -1;
    MapManager localMapManager;
    if ((i != 0) && (i != 1))
    {
      if ((i == 2) || (i == 3))
      {
        localMapManager = MapManager.getInstance();
        if (OptionsManager.getInstance().getTrafficInformation()) {
          str = "carnav.traffic.hybrid.day";
        } else {
          str = "carnav.hybrid.day";
        }
        localMapManager.setMapType(str);
      }
    }
    else
    {
      localMapManager = MapManager.getInstance();
      if (OptionsManager.getInstance().getTrafficInformation()) {
        str = "carnav.traffic.day";
      } else {
        str = "carnav.day";
      }
      localMapManager.setMapType(str);
    }
    NavigationManagerProxy.getInstance().setMap(localClass_3);
    NavigationManagerProxy.getInstance().setSpeedWarningEnabled(false);
    NavigationManagerProxy.getInstance().setEnabledAudioEvents(EnumSet.of(NavigationManager.AudioEvent.MANEUVER, NavigationManager.AudioEvent.attachment, NavigationManager.AudioEvent.VIBRATION));
    NavigationManagerProxy.getInstance().setDistanceUnit(OptionsManager.getInstance().getUnitSystem());
    LengthFormatter.setUnitSystem(NavigationManagerProxy.getInstance().getDistanceUnit());
    if (paramMapRoute != null)
    {
      setRoute(paramMapRoute.getRoute());
      localPositionIndicator.setVisible(true);
      setGuidanceView(true, false);
      return;
    }
    paramMapRoute = TripManager.getDestination();
    if (paramMapRoute != null)
    {
      paramMapRoute = HereObjectsFactory.getGeoCoordinate(latitude, longitude);
      MapManager.getInstance().setMarker("destination", 2131165368, paramMapRoute);
    }
    setGuidanceView(true, true);
    MapManager.getInstance().resetZoom(-2, true);
  }
  
  public void destroy()
  {
    instance = null;
  }
  
  public Route getRoute()
  {
    MapRoute localMapRoute = mMapRoute;
    if (localMapRoute != null) {
      return localMapRoute.getRoute();
    }
    return null;
  }
  
  public void setRoute(Route paramRoute)
  {
    Object localObject2 = mMapRoute;
    Object localObject1 = this;
    if (localObject2 == null)
    {
      mMapRoute = HereObjectsFactory.getMapRoute();
      mMapRoute.setTrafficEnabled(true);
      mMapRoute.setColor(ContextCompat.getColor(Globals.getContext(), 2131034197));
      mMapRoute.setUpcomingColor(ContextCompat.getColor(Globals.getContext(), 2131034197));
    }
    localObject1 = this;
    mMapRoute.setRoute(paramRoute);
    MapManager.getInstance().clearRoutes();
    MapManager.getInstance().clearMarkers();
    MapManager.getInstance().setRoute("active-route", mMapRoute);
    localObject1 = paramRoute.getWaypoints();
    if (localObject1 != null)
    {
      localObject1 = new ArrayList((Collection)localObject1);
      boolean bool = ((List)localObject1).isEmpty();
      int i = 0;
      if (!bool) {
        ((List)localObject1).remove(0);
      }
      if (!((List)localObject1).isEmpty()) {
        ((List)localObject1).remove(((List)localObject1).size() - 1);
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (GeoCoordinate)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          i += 1;
          MapManager localMapManager = MapManager.getInstance();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("waypoint");
          localStringBuilder.append(i);
          localMapManager.setMarker(localStringBuilder.toString(), 2131166026, (GeoCoordinate)localObject2);
        }
      }
    }
    MapManager.getInstance().setMarker("destination", 2131165369, paramRoute.getDestination());
  }
  
  public void startGuidance(boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = track;
    Boolean localBoolean = Boolean.valueOf(false);
    RealmLog.append((String)localObject, "startGuidance( simulate=%b, offroad=%b )", new Object[] { Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) });
    if ((!paramBoolean2) && (!com.com.navapp.services.NavAppService.isRunning(Globals.getContext())))
    {
      Storage.save("reacheddestination", localBoolean);
      Storage.save("isguidancecancelled", localBoolean);
      localObject = new Intent(Globals.getContext(), com.ktm.navapp.services.NavAppService.class);
      ((Intent)localObject).putExtra("SIMULATE", paramBoolean1);
      ((Intent)localObject).putExtra("OFFROAD", paramBoolean2);
      ((Intent)localObject).putExtra("GUIDANCE_COMMAND", 1209);
      ContextCompat.startForegroundService(Globals.getContext(), (Intent)localObject);
    }
    configureGuidanceMap(paramBoolean2);
  }
}
