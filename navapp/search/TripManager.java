package com.com.navapp.search;

import com.com.navapp.utilities.Storage;
import java.util.ArrayList;
import java.util.List;

public class TripManager
{
  private static final String KEY_BACKUP_TRIP = "TripManager.BackupTrip";
  private static final String KEY_CHANGES_TRIP = "TripManager.ChangesTrip";
  private static final String KEY_CURRENT_TRIP = "TripManager.CurrentTrip";
  private static final String KEY_GUIDANCE_TRIP = "TripManager.GuidanceTrip";
  
  public TripManager() {}
  
  public static void addMyLocation()
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    if (localTrip.getStart() != null)
    {
      waypoints.add(0, Waypoint.MY_LOCATION);
      keepMyLocation = true;
      Storage.save("TripManager.CurrentTrip", localTrip);
    }
  }
  
  public static void addWaypoint(int paramInt, Waypoint paramWaypoint)
  {
    SearchHistory.addToHistory(paramWaypoint);
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    if (paramWaypoint != null)
    {
      if ((paramInt >= 0) && (paramInt <= waypoints.size())) {
        waypoints.add(paramInt, paramWaypoint);
      } else {
        waypoints.add(paramWaypoint);
      }
      Storage.save("TripManager.CurrentTrip", localTrip);
    }
  }
  
  public static void backupTrip()
  {
    setBackupTrip(getTrip());
  }
  
  public static Trip getActiveRemainingTrip()
  {
    Trip localTrip1 = getActiveTrip();
    Trip localTrip2 = new Trip(localTrip1);
    waypoints.clear();
    waypoints.add(Waypoint.MY_LOCATION);
    waypoints.addAll(localTrip1.getWaypointsLeft());
    waypointReached = 0;
    keepMyLocation = true;
    return localTrip2;
  }
  
  public static Trip getActiveTrip()
  {
    return (Trip)Storage.load("TripManager.GuidanceTrip", com.ktm.navapp.search.Trip.class);
  }
  
  public static Trip getBackupTrip()
  {
    return (Trip)Storage.load("TripManager.BackupTrip", com.ktm.navapp.search.Trip.class);
  }
  
  public static Trip getChangesTrackingTrip()
  {
    return (Trip)Storage.load("TripManager.ChangesTrip", com.ktm.navapp.search.Trip.class);
  }
  
  public static Waypoint getDestination()
  {
    return ((Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class)).getDestination();
  }
  
  public static Trip getTrip()
  {
    return (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
  }
  
  public static void initTrip(Waypoint paramWaypoint)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    waypoints.clear();
    dateMs = 0L;
    length = 0;
    name = null;
    saved = false;
    keepMyLocation = false;
    waypointReached = 0;
    if (paramWaypoint != null)
    {
      waypoints.add(Waypoint.MY_LOCATION);
      waypoints.add(paramWaypoint);
      SearchHistory.addToHistory(paramWaypoint);
    }
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
  
  public static void onGuidanceStart()
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    keepMyLocation = true;
    waypointReached = 0;
    Storage.save("TripManager.GuidanceTrip", localTrip);
  }
  
  public static void onGuidanceWaypointReached(int paramInt)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.GuidanceTrip", com.ktm.navapp.search.Trip.class);
    keepMyLocation = true;
    waypointReached = paramInt;
    Storage.save("TripManager.GuidanceTrip", localTrip);
  }
  
  public static void resetOptions()
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    fastestRoute = true;
    allowTolls = true;
    allowHighways = false;
    allowDirtRoads = false;
    smartRouteUseDuration = false;
    smartRouteDurationMinutes = 60;
    smartRouteWeightFactor100 = 50;
    smartRoundUseDuration = false;
    smartRoundDurationMinutes = 60;
    smartRoundDistanceMeters = 50000;
    smartRoundWeightFactor100 = 50;
    routeType = Trip.RouteType.ROUTE;
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
  
  public static void restoreTrip(boolean paramBoolean)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.BackupTrip", com.ktm.navapp.search.Trip.class);
    if (paramBoolean)
    {
      setWaypoints(waypoints);
      return;
    }
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
  
  public static void setBackupTrip(Trip paramTrip)
  {
    Storage.save("TripManager.BackupTrip", paramTrip);
  }
  
  public static void setChangesTrackingTrip(Trip paramTrip)
  {
    Storage.save("TripManager.ChangesTrip", paramTrip);
  }
  
  public static void setDestination(Waypoint paramWaypoint)
  {
    if (paramWaypoint != null)
    {
      Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
      if (waypoints.isEmpty())
      {
        waypoints.add(Waypoint.MY_LOCATION);
      }
      else if (localTrip.getWaypoints().size() > 1)
      {
        int i = waypoints.size() - 1;
        while ((i >= 0) && (waypoints.get(i) == null)) {
          i -= 1;
        }
        if (i >= 0) {
          waypoints.remove(i);
        }
      }
      waypoints.add(paramWaypoint);
      Storage.save("TripManager.CurrentTrip", localTrip);
      SearchHistory.addToHistory(paramWaypoint);
    }
  }
  
  public static void setOptions(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    fastestRoute = paramBoolean1;
    allowTolls = paramBoolean2;
    allowHighways = paramBoolean3;
    allowDirtRoads = paramBoolean4;
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
  
  public static void setRouteType(Trip.RouteType paramRouteType)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    routeType = paramRouteType;
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
  
  public static void setTrip(Trip paramTrip)
  {
    Storage.save("TripManager.CurrentTrip", paramTrip);
  }
  
  public static void setWaypoints(List paramList)
  {
    Trip localTrip = (Trip)Storage.load("TripManager.CurrentTrip", com.ktm.navapp.search.Trip.class);
    waypoints.clear();
    if (paramList != null) {
      waypoints.addAll(paramList);
    }
    Storage.save("TripManager.CurrentTrip", localTrip);
  }
}
