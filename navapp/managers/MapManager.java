package com.com.navapp.managers;

import android.content.Context;
import android.graphics.PointF;
import androidx.core.content.ContextCompat;
import com.com.navapp.Globals;
import com.com.navapp.analytics.Analytics;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.downloading.offlinemaps.OnIsMapAvailable;
import com.com.navapp.factories.HereObjectsFactory;
import com.com.navapp.logging.LogExt;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.Dealers;
import com.com.navapp.search.Waypoint;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Utilities;
import com.here.android.ui.cluster.BasicClusterStyle;
import com.here.android.ui.cluster.ClusterLayer;
import com.here.android.ui.cluster.ClusterStyle;
import com.here.android.ui.cluster.ClusterTheme;
import com.here.android.ui.common.GeoBoundingBox;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.GeoPosition;
import com.here.android.ui.common.IconCategory;
import com.here.android.ui.common.Image;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.common.PositioningManager;
import com.here.android.ui.common.PositioningManager.LocationMethod;
import com.here.android.ui.common.PositioningManager.LocationStatus;
import com.here.android.ui.common.PositioningManager.OnPositionChangedListener;
import com.here.android.ui.mapping.Map.Animation;
import com.here.android.ui.mapping.Map.OnTransformListener;
import com.here.android.ui.mapping.Map.Projection;
import com.here.android.ui.mapping.MapObject;
import com.here.android.ui.mapping.MapState;
import com.here.android.ui.mapping.PositionIndicator;
import com.here.android.ui.mapping.class_3;
import com.here.android.ui.routing.Route;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MapManager
  implements PositioningManager.OnPositionChangedListener
{
  private static final String APP_NAME = com.ktm.navapp.managers.MapManager.class.getSimpleName();
  public static final double DEFAULT_ZOOM = 18.0D;
  public static final String MARKER_LOCATION = "location";
  private static MapManager ourInstance;
  private final int colorRouteAlternative = ContextCompat.getColor(Globals.getContext(), 2131034198);
  private final int colorRouteSelected = ContextCompat.getColor(Globals.getContext(), 2131034197);
  protected GeoPosition mCurrentPosition;
  private ClusterLayer mDealerLayer = null;
  protected boolean mFollowLocation;
  protected boolean mIsTransforming;
  protected class_3 mMap;
  private final HashMap<String, com.here.android.mpa.mapping.MapMarker> mMarkerMap = new HashMap();
  protected OnIsMapAvailable mOnIsMapAvailable = new OnIsMapAvailable()
  {
    public void failed()
    {
      Utilities.toastShort(2131558698);
    }
    
    public void onAvailable() {}
    
    public void onNotAvailable()
    {
      Utilities.toastShort(2131558569);
    }
  };
  protected Map.OnTransformListener mOnTransformListener = new Map.OnTransformListener()
  {
    public void onMapTransformEnd(MapState paramAnonymousMapState)
    {
      mZoomLevel = paramAnonymousMapState.getZoomLevel();
      mIsTransforming = false;
    }
    
    public void onMapTransformStart()
    {
      mIsTransforming = true;
    }
  };
  private final HashMap<String, com.here.android.mpa.mapping.MapOverlay> mOverlayMap = new HashMap();
  protected PositionIndicator mPositionIndicator;
  private final HashMap<String, com.here.android.mpa.mapping.MapRoute> mRouteMap = new HashMap();
  protected boolean mShowLocation;
  protected double mZoomLevel;
  
  public MapManager() {}
  
  private void addDealerLayer()
  {
    try
    {
      if ((mMap != null) && (MapEngine.isInitialized()))
      {
        if (mDealerLayer == null)
        {
          mDealerLayer = new ClusterLayer();
          Object localObject1 = new BasicClusterStyle().setFillColor(Globals.getContext().getColor(2131034172)).setStrokeColor(Globals.getContext().getColor(2131034267)).setFontColor(Globals.getContext().getColor(2131034267));
          Object localObject2 = new ClusterTheme();
          ((ClusterTheme)localObject2).setStyleForDensityRange(2, 9999, (ClusterStyle)localObject1);
          mDealerLayer.setTheme((ClusterTheme)localObject2);
          localObject1 = Dealers.loadDealers();
          RealmLog.i(APP_NAME, "Adding Dealers..");
          localObject2 = Utilities.getImage(2131165348);
          ArrayList localArrayList = new ArrayList(((Collection)localObject1).size());
          Iterator localIterator = ((Collection)localObject1).iterator();
          while (localIterator.hasNext())
          {
            Waypoint localWaypoint = (Waypoint)localIterator.next();
            localArrayList.add(HereObjectsFactory.getMapMarker(HereObjectsFactory.getGeoCoordinate(latitude, longitude), (Image)localObject2));
          }
          mDealerLayer.addMarkers(localArrayList);
          RealmLog.append(APP_NAME, "Dealers added: %d", new Object[] { Integer.valueOf(((Collection)localObject1).size()) });
        }
        mMap.addClusterLayer(mDealerLayer);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static MapManager getInstance()
  {
    if (ourInstance == null) {
      ourInstance = new MapManager();
    }
    return ourInstance;
  }
  
  private void removeDealerLayer()
  {
    try
    {
      if ((mMap != null) && (mDealerLayer != null)) {
        mMap.removeClusterLayer(mDealerLayer);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void applyMapIconFilter()
  {
    mMap.setCartoMarkersVisible(true);
    mMap.setCartoMarkersVisible(IconCategory.RENT_A_CAR_FACILITY, false);
    mMap.setCartoMarkersVisible(IconCategory.METRO_STATION, false);
    mMap.setCartoMarkersVisible(IconCategory.METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.AUT_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.AUT_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.BEL_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.BEL_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.CZE_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.CZE_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.DEN_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.DEN_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.FIN_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.FIN_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.FRA_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.FRA_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.DEU_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.DEU_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.ITA_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.ITA_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.NOR_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.NOR_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.PRT_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.PRT_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_BARCELONA_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_BARCELONA_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_CERCANIAS_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_CERCANIAS_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_MADRID_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.ESP_MADRID_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.SWE_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.SWE_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.GBR_GLASGOW_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.GBR_GLASGOW_METRO_STOP, false);
    mMap.setCartoMarkersVisible(IconCategory.GBR_LONDON_METRO_ACCESS, false);
    mMap.setCartoMarkersVisible(IconCategory.GBR_LONDON_METRO_STOP, false);
  }
  
  public void changeRouteSelection(com.here.android.ui.mapping.MapRoute paramMapRoute1, com.here.android.ui.mapping.MapRoute paramMapRoute2)
  {
    if (paramMapRoute1 != null)
    {
      paramMapRoute1.setZIndex(100);
      paramMapRoute1.setColor(colorRouteAlternative);
    }
    if (paramMapRoute2 != null)
    {
      paramMapRoute2.setZIndex(101);
      paramMapRoute2.setColor(colorRouteSelected);
      zoomTo(paramMapRoute2.getRoute().getBoundingBox(), true);
    }
  }
  
  public void checkMapIsAvailable(GeoCoordinate paramGeoCoordinate)
  {
    OfflineMapsManager.getInstance().isMapAvailable(paramGeoCoordinate, mOnIsMapAvailable);
  }
  
  public void clearMap()
  {
    removeDealerLayer();
    clearRoutes();
    clearMarkers();
    clearOverlays();
  }
  
  public void clearMarkers()
  {
    try
    {
      if (mMap != null) {
        mMap.removeMapObjects(new ArrayList(mMarkerMap.values()));
      }
      mMarkerMap.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void clearOverlays()
  {
    try
    {
      if (mMap != null)
      {
        Iterator localIterator = mOverlayMap.values().iterator();
        while (localIterator.hasNext())
        {
          com.here.android.ui.mapping.MapOverlay localMapOverlay = (com.here.android.ui.mapping.MapOverlay)localIterator.next();
          mMap.removeMapOverlay(localMapOverlay);
        }
      }
      mOverlayMap.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void clearRoutes()
  {
    try
    {
      if (mMap != null) {
        mMap.removeMapObjects(new ArrayList(mRouteMap.values()));
      }
      mRouteMap.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void destroy()
  {
    ourInstance = null;
  }
  
  public GeoCoordinate getCenter()
  {
    return mMap.getCenter();
  }
  
  public GeoPosition getCurrentPosition()
  {
    if (mCurrentPosition == null) {
      mCurrentPosition = PositioningManager.getInstance().getLastKnownPosition();
    }
    return mCurrentPosition;
  }
  
  public class_3 getMap()
  {
    return mMap;
  }
  
  public com.here.android.ui.mapping.MapOverlay getOverlay(String paramString)
  {
    try
    {
      paramString = (com.here.android.ui.mapping.MapOverlay)mOverlayMap.get(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public PositionIndicator getPositionIndicator()
  {
    return mPositionIndicator;
  }
  
  public com.here.android.ui.mapping.MapRoute getRoute(String paramString)
  {
    try
    {
      paramString = (com.here.android.ui.mapping.MapRoute)mRouteMap.get(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public double getZoom()
  {
    return mZoomLevel;
  }
  
  public boolean isFollowingCurrentLocation()
  {
    return mFollowLocation;
  }
  
  public void onPositionFixChanged(PositioningManager.LocationMethod paramLocationMethod, PositioningManager.LocationStatus paramLocationStatus)
  {
    RealmLog.append(APP_NAME, "onPositionFixChanged( locationMethod=%s, locationStatus=%s )", new Object[] { paramLocationMethod.name(), paramLocationStatus.name() });
  }
  
  public void onPositionUpdated(PositioningManager.LocationMethod paramLocationMethod, GeoPosition paramGeoPosition, boolean paramBoolean)
  {
    mCurrentPosition = paramGeoPosition;
    LogExt.writePosition(paramGeoPosition);
    Analytics.getInstance().setPosition(paramGeoPosition);
    Events.postEvent(paramGeoPosition);
    if (mShowLocation)
    {
      if (LogExt.isLoggingEnabled()) {
        setMarker("location", 2131165283, paramGeoPosition.getCoordinate());
      } else {
        setMarker("location", 2131165282, paramGeoPosition.getCoordinate());
      }
      if ((mFollowLocation) && (!mIsTransforming))
      {
        setCenter(paramGeoPosition.getCoordinate(), false);
        checkMapIsAvailable(paramGeoPosition.getCoordinate());
      }
    }
  }
  
  public void refreshZoomLevel()
  {
    class_3 localClass_3 = mMap;
    if (localClass_3 != null) {
      mZoomLevel = localClass_3.getZoomLevel();
    }
  }
  
  public void removeMarker(String paramString)
  {
    try
    {
      paramString = (com.here.android.ui.mapping.MapMarker)mMarkerMap.remove(paramString);
      if ((paramString != null) && (mMap != null)) {
        mMap.removeMapObject(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void removeOverlay(String paramString)
  {
    try
    {
      paramString = (com.here.android.ui.mapping.MapOverlay)mOverlayMap.remove(paramString);
      if ((paramString != null) && (mMap != null)) {
        mMap.removeMapOverlay(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void removeRoute(String paramString)
  {
    try
    {
      paramString = (com.here.android.ui.mapping.MapRoute)mRouteMap.remove(paramString);
      if ((paramString != null) && (mMap != null)) {
        mMap.removeMapObject(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void resetZoom(int paramInt, boolean paramBoolean)
  {
    mZoomLevel = Math.max(Math.min(paramInt + 18.0D, mMap.getMaxZoomLevel()), mMap.getMinZoomLevel());
    class_3 localClass_3 = mMap;
    double d = mZoomLevel;
    Map.Animation localAnimation;
    if (paramBoolean) {
      localAnimation = Map.Animation.LINEAR;
    } else {
      localAnimation = Map.Animation.NONE;
    }
    localClass_3.setZoomLevel(d, localAnimation);
  }
  
  public void setCenter(GeoCoordinate paramGeoCoordinate, double paramDouble, boolean paramBoolean)
  {
    class_3 localClass_3 = mMap;
    if (localClass_3 != null)
    {
      Map.Animation localAnimation;
      if (paramBoolean) {
        localAnimation = Map.Animation.y;
      } else {
        localAnimation = Map.Animation.NONE;
      }
      localClass_3.setCenter(paramGeoCoordinate, localAnimation, paramDouble, 0.0F, -1.0F);
    }
  }
  
  public void setCenter(GeoCoordinate paramGeoCoordinate, boolean paramBoolean)
  {
    setCenter(paramGeoCoordinate, 18.0D, paramBoolean);
  }
  
  public void setCenterGuidanceOffroad(GeoCoordinate paramGeoCoordinate, float paramFloat)
  {
    mMap.executeSynchronized(new MapManager.2(this, paramGeoCoordinate, paramFloat));
  }
  
  public void setFollowCurrentLocation(boolean paramBoolean)
  {
    String str = APP_NAME;
    boolean bool2 = false;
    boolean bool3 = mShowLocation;
    if ((mShowLocation) && (paramBoolean)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    RealmLog.append(str, "setFollowCurrentLocation( follow=%b ) show=%b -> follow=%b", new Object[] { Boolean.valueOf(paramBoolean), Boolean.valueOf(bool3), Boolean.valueOf(bool1) });
    boolean bool1 = bool2;
    if (mShowLocation)
    {
      bool1 = bool2;
      if (paramBoolean) {
        bool1 = true;
      }
    }
    mFollowLocation = bool1;
  }
  
  public void setGuidance(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mMap != null)
    {
      if (!paramBoolean1) {
        getInstance().refreshZoomLevel();
      }
      if (LogExt.isLoggingEnabled())
      {
        if (paramBoolean1) {
          i = 2131165278;
        } else {
          i = 2131165283;
        }
      }
      else if (paramBoolean1) {
        i = 2131165277;
      } else {
        i = 2131165282;
      }
      if (paramBoolean2)
      {
        if (!setMarker("location", i, null))
        {
          localObject = getCurrentPosition();
          if (localObject != null) {
            setMarker("location", i, ((GeoPosition)localObject).getCoordinate());
          }
        }
      }
      else {
        mPositionIndicator.setMarker(Utilities.getImage(i));
      }
      Object localObject = mMap;
      int j = ((class_3)localObject).getWidth();
      int i = 2;
      float f = j / 2;
      j = mMap.getHeight();
      if (paramBoolean1) {
        i = 3;
      }
      ((class_3)localObject).setTransformCenter(new PointF(f, j * i / 4));
      localObject = mMap;
      if (paramBoolean1) {
        f = 45.0F;
      } else {
        f = 0.0F;
      }
      ((class_3)localObject).setTilt(f, Map.Animation.y);
    }
  }
  
  public void setMap(class_3 paramClass_3, PositionIndicator paramPositionIndicator)
  {
    if ((mMap != paramClass_3) && (mPositionIndicator != paramPositionIndicator))
    {
      RealmLog.i(APP_NAME, "setMap()");
      removeDealerLayer();
      mMap = paramClass_3;
      mMap.setProjectionMode(Map.Projection.GLOBE);
      applyMapIconFilter();
      mMap.setZoomLevel(18.0D);
      mMarkerMap.clear();
      mRouteMap.clear();
      mOverlayMap.clear();
      mZoomLevel = mMap.getZoomLevel();
      mMap.addTransformListener(mOnTransformListener);
      addDealerLayer();
      mPositionIndicator = paramPositionIndicator;
    }
  }
  
  public void setMapType(String paramString)
  {
    mMap.setMapScheme(paramString);
    applyMapIconFilter();
  }
  
  public boolean setMarker(String paramString, int paramInt, GeoCoordinate paramGeoCoordinate)
  {
    try
    {
      if (mMap != null)
      {
        GeoCoordinate localGeoCoordinate = paramGeoCoordinate;
        if (paramGeoCoordinate != null) {
          localGeoCoordinate = HereObjectsFactory.getGeoCoordinate(paramGeoCoordinate.getLatitude(), paramGeoCoordinate.getLongitude());
        }
        paramGeoCoordinate = (com.here.android.ui.mapping.MapMarker)mMarkerMap.get(paramString);
        if (paramGeoCoordinate != null)
        {
          if (localGeoCoordinate != null) {
            paramGeoCoordinate.setCoordinate(localGeoCoordinate);
          }
          if (paramInt != 0) {
            paramGeoCoordinate.setIcon(Utilities.getImage(paramInt));
          }
          return true;
        }
        if ((paramInt != 0) && (localGeoCoordinate != null))
        {
          paramGeoCoordinate = HereObjectsFactory.getMapMarker(localGeoCoordinate, Utilities.getImage(paramInt));
          mMarkerMap.put(paramString, paramGeoCoordinate);
          mMap.addMapObject(paramGeoCoordinate);
          return true;
        }
      }
      return false;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public boolean setOverlay(String paramString, com.here.android.ui.mapping.MapOverlay paramMapOverlay)
  {
    try
    {
      if (mMap != null)
      {
        com.here.android.ui.mapping.MapOverlay localMapOverlay = (com.here.android.ui.mapping.MapOverlay)mOverlayMap.get(paramString);
        if ((localMapOverlay != null) && (localMapOverlay == paramMapOverlay)) {
          return true;
        }
        if (localMapOverlay != null) {
          removeOverlay(paramString);
        }
        if (mMap.addMapOverlay(paramMapOverlay))
        {
          mOverlayMap.put(paramString, paramMapOverlay);
          return true;
        }
      }
      return false;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public boolean setRoute(String paramString, com.here.android.ui.mapping.MapRoute paramMapRoute)
  {
    try
    {
      if (mMap != null)
      {
        com.here.android.ui.mapping.MapRoute localMapRoute = (com.here.android.ui.mapping.MapRoute)mRouteMap.get(paramString);
        if ((localMapRoute != null) && (localMapRoute == paramMapRoute)) {
          return true;
        }
        if (localMapRoute != null) {
          removeRoute(paramString);
        }
        if ((paramMapRoute != null) && (mMap.addMapObject(paramMapRoute)))
        {
          mRouteMap.put(paramString, paramMapRoute);
          return true;
        }
      }
      return false;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void setShowCurrentLocation(boolean paramBoolean1, boolean paramBoolean2)
  {
    setShowCurrentLocation(paramBoolean1, paramBoolean2, true);
  }
  
  public void setShowCurrentLocation(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Object localObject = APP_NAME;
    boolean bool2 = false;
    RealmLog.append((String)localObject, "setShowCurrentLocation( show=%b, follow=%b, animate=%b )", new Object[] { Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2), Boolean.valueOf(paramBoolean3) });
    mShowLocation = paramBoolean1;
    boolean bool1 = bool2;
    if (paramBoolean1)
    {
      bool1 = bool2;
      if (paramBoolean2) {
        bool1 = true;
      }
    }
    mFollowLocation = bool1;
    if (mShowLocation)
    {
      localObject = getCurrentPosition();
      if ((localObject != null) && (((GeoPosition)localObject).isValid()))
      {
        if (LogExt.isLoggingEnabled()) {
          setMarker("location", 2131165283, ((GeoPosition)localObject).getCoordinate());
        } else {
          setMarker("location", 2131165282, ((GeoPosition)localObject).getCoordinate());
        }
        if (mFollowLocation)
        {
          setCenter(((GeoPosition)localObject).getCoordinate(), paramBoolean3);
          checkMapIsAvailable(((GeoPosition)localObject).getCoordinate());
        }
      }
      else
      {
        Utilities.toastShort(2131558698);
      }
    }
    else
    {
      removeMarker("location");
    }
  }
  
  public void setTrafficInformation(Boolean paramBoolean)
  {
    mMap.setTrafficInfoVisible(paramBoolean.booleanValue());
  }
  
  public void showPreviewRouteOnMap(boolean paramBoolean)
  {
    com.here.android.ui.mapping.MapRoute localMapRoute = RoutingManager.getInstance().getSelectedRoute();
    Object localObject1 = RoutingManager.getInstance().getRoutes();
    if ((localMapRoute != null) && (localObject1 != null))
    {
      if (((List)localObject1).size() == 0) {
        return;
      }
      int j = 0;
      int i = 0;
      Object localObject2;
      while (i < ((List)localObject1).size())
      {
        localObject2 = (com.here.android.ui.mapping.MapRoute)((List)localObject1).get(i);
        ((com.here.android.ui.mapping.MapRoute)localObject2).setTrafficEnabled(true);
        if (localObject2 == localMapRoute)
        {
          ((MapObject)localObject2).setZIndex(101);
          ((com.here.android.ui.mapping.MapRoute)localObject2).setColor(colorRouteSelected);
          ((com.here.android.ui.mapping.MapRoute)localObject2).setUpcomingColor(colorRouteSelected);
          setRoute(String.valueOf(i), (com.here.android.ui.mapping.MapRoute)localObject2);
          zoomTo(((com.here.android.ui.mapping.MapRoute)localObject2).getRoute().getBoundingBox(), paramBoolean);
        }
        else
        {
          ((MapObject)localObject2).setZIndex(100);
          ((com.here.android.ui.mapping.MapRoute)localObject2).setColor(colorRouteAlternative);
          setRoute(String.valueOf(i), (com.here.android.ui.mapping.MapRoute)localObject2);
        }
        i += 1;
      }
      setShowCurrentLocation(false, false);
      clearMarkers();
      localObject1 = localMapRoute.getRoute().getWaypoints();
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        i = j;
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (GeoCoordinate)((Iterator)localObject1).next();
          if (localObject2 != null)
          {
            i += 1;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("waypoint");
            localStringBuilder.append(i);
            setMarker(localStringBuilder.toString(), 2131166026, (GeoCoordinate)localObject2);
          }
        }
      }
      setMarker("position", 2131165943, localMapRoute.getRoute().getStart());
      setMarker("destination", 2131165369, localMapRoute.getRoute().getDestination());
    }
  }
  
  public void zoomIn(boolean paramBoolean1, boolean paramBoolean2)
  {
    mZoomLevel = Math.min(mZoomLevel + 1.0D, mMap.getMaxZoomLevel());
    class_3 localClass_3 = mMap;
    double d = mZoomLevel;
    Map.Animation localAnimation;
    if (paramBoolean1) {
      localAnimation = Map.Animation.LINEAR;
    } else {
      localAnimation = Map.Animation.NONE;
    }
    localClass_3.setZoomLevel(d, localAnimation);
    if (paramBoolean2)
    {
      localClass_3 = mMap;
      if (paramBoolean1) {
        localAnimation = Map.Animation.y;
      } else {
        localAnimation = Map.Animation.NONE;
      }
      localClass_3.setOrientation(0.0F, localAnimation);
    }
  }
  
  public void zoomOut(boolean paramBoolean)
  {
    mZoomLevel = Math.max(mZoomLevel - 1.0D, mMap.getMinZoomLevel());
    class_3 localClass_3 = mMap;
    double d = mZoomLevel;
    Map.Animation localAnimation;
    if (paramBoolean) {
      localAnimation = Map.Animation.LINEAR;
    } else {
      localAnimation = Map.Animation.NONE;
    }
    localClass_3.setZoomLevel(d, localAnimation);
  }
  
  protected void zoomTo(GeoBoundingBox paramGeoBoundingBox, boolean paramBoolean)
  {
    if (paramGeoBoundingBox == null) {
      return;
    }
    Object localObject1 = paramGeoBoundingBox.getTopLeft();
    Object localObject2 = paramGeoBoundingBox.getBottomRight();
    double d1;
    double d2;
    if (((GeoCoordinate)localObject1).getLatitude() > ((GeoCoordinate)localObject2).getLatitude())
    {
      d1 = ((GeoCoordinate)localObject1).getLatitude();
      d2 = ((GeoCoordinate)localObject2).getLatitude();
      localObject1 = HereObjectsFactory.getGeoBoundingBox(HereObjectsFactory.getGeoCoordinate(((GeoCoordinate)localObject1).getLatitude() + (d1 - d2) * 0.4D, ((GeoCoordinate)localObject1).getLongitude()), (GeoCoordinate)localObject2);
      localObject2 = mMap;
      if (paramBoolean) {
        paramGeoBoundingBox = Map.Animation.y;
      } else {
        paramGeoBoundingBox = Map.Animation.NONE;
      }
      ((class_3)localObject2).zoomTo((GeoBoundingBox)localObject1, paramGeoBoundingBox, 0.0F);
      return;
    }
    if (((GeoCoordinate)localObject1).getLatitude() < ((GeoCoordinate)localObject2).getLatitude())
    {
      d1 = ((GeoCoordinate)localObject2).getLatitude();
      d2 = ((GeoCoordinate)localObject1).getLatitude();
      localObject1 = HereObjectsFactory.getGeoBoundingBox((GeoCoordinate)localObject1, HereObjectsFactory.getGeoCoordinate(((GeoCoordinate)localObject2).getLatitude() + (d1 - d2) * 0.4D, ((GeoCoordinate)localObject2).getLongitude()));
      localObject2 = mMap;
      if (paramBoolean) {
        paramGeoBoundingBox = Map.Animation.y;
      } else {
        paramGeoBoundingBox = Map.Animation.NONE;
      }
      ((class_3)localObject2).zoomTo((GeoBoundingBox)localObject1, paramGeoBoundingBox, 0.0F);
      return;
    }
    localObject2 = mMap;
    if (paramBoolean) {
      localObject1 = Map.Animation.y;
    } else {
      localObject1 = Map.Animation.NONE;
    }
    ((class_3)localObject2).zoomTo(paramGeoBoundingBox, (Map.Animation)localObject1, 0.0F);
  }
  
  public void zoomTo(GeoBoundingBox paramGeoBoundingBox, boolean paramBoolean, float paramFloat1, float paramFloat2)
  {
    class_3 localClass_3 = mMap;
    if ((localClass_3 != null) && (paramGeoBoundingBox != null))
    {
      int i = localClass_3.getWidth();
      int j = Utilities.pxFromDp(paramFloat1);
      int k = mMap.getHeight();
      int m = Utilities.pxFromDp(paramFloat2);
      Map.Animation localAnimation;
      if (paramBoolean) {
        localAnimation = Map.Animation.y;
      } else {
        localAnimation = Map.Animation.NONE;
      }
      localClass_3.zoomTo(paramGeoBoundingBox, i - j * 2, k - m * 2, localAnimation, 0.0F);
    }
  }
}
