package com.com.navapp.search;

import com.com.navapp.utilities.Storage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SavedTrips
{
  private static final String KEY_SAVED_TRIPS = "DestinationManager.SavedTrips";
  private static final int MAX_SAVED_TRIPS = 50;
  
  public SavedTrips() {}
  
  public static void addToSavedTrips(Trip paramTrip)
  {
    if (paramTrip != null)
    {
      saved = true;
      Trips localTrips = (Trips)Storage.load("DestinationManager.SavedTrips", com.ktm.navapp.search.SavedTrips.Trips.class);
      while (trips.remove(paramTrip)) {}
      trips.add(0, paramTrip);
      while (trips.size() > 50) {
        trips.remove(50);
      }
      Storage.save("DestinationManager.SavedTrips", localTrips);
    }
  }
  
  public static void deleteFromSavedTrips(Trip paramTrip)
  {
    if (paramTrip != null)
    {
      Trips localTrips = (Trips)Storage.load("DestinationManager.SavedTrips", com.ktm.navapp.search.SavedTrips.Trips.class);
      while (trips.remove(paramTrip)) {}
      Storage.save("DestinationManager.SavedTrips", localTrips);
    }
  }
  
  public static Trip fixSavedOldTrip(Trip paramTrip)
  {
    if (!saved)
    {
      saved = true;
      waypointReached = 0;
      keepMyLocation = true;
      waypoints.add(0, Waypoint.MY_LOCATION);
    }
    return paramTrip;
  }
  
  public static Trip getSavedTrip(String paramString)
  {
    if (paramString != null)
    {
      Iterator localIterator = load"DestinationManager.SavedTrips"trips.iterator();
      while (localIterator.hasNext())
      {
        Trip localTrip = (Trip)localIterator.next();
        if (paramString.equals(name)) {
          return localTrip;
        }
      }
    }
    return null;
  }
  
  public static List getSavedTrips()
  {
    ArrayList localArrayList = load"DestinationManager.SavedTrips"trips;
    Collections.sort(localArrayList, new SavedTrips.1());
    return localArrayList;
  }
  
  public class Trips
  {
    public final ArrayList<com.ktm.navapp.search.Trip> trips = new ArrayList();
    
    public Trips() {}
  }
}
