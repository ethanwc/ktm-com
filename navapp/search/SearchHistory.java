package com.com.navapp.search;

import com.com.navapp.utilities.Storage;
import java.util.ArrayList;
import java.util.List;

public class SearchHistory
{
  private static final String KEY_SEARCH_HISTORY = "DestinationManager.SearchHistory";
  static final int MAX_SEARCH_HISTORY = 20;
  
  public SearchHistory() {}
  
  public static void addToHistory(Waypoint paramWaypoint)
  {
    if (paramWaypoint != null)
    {
      searchText = null;
      History localHistory = (History)Storage.load("DestinationManager.SearchHistory", com.ktm.navapp.search.SearchHistory.History.class);
      while (destinations.remove(paramWaypoint)) {}
      destinations.add(0, paramWaypoint);
      while (destinations.size() > 20) {
        destinations.remove(20);
      }
      Storage.save("DestinationManager.SearchHistory", localHistory);
    }
  }
  
  public static void clearHistory()
  {
    Storage.delete("DestinationManager.SearchHistory");
  }
  
  public static List getHistory()
  {
    return load"DestinationManager.SearchHistory"destinations;
  }
  
  public class History
  {
    public final ArrayList<com.ktm.navapp.search.Waypoint> destinations = new ArrayList();
    
    public History() {}
  }
}
