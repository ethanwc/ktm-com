package com.com.navapp.search;

import com.com.navapp.Globals;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.utilities.Callback;
import com.com.navapp.utilities.HEREHelper;
import com.here.android.mpa.search.ResultListener;
import com.here.android.ui.common.ApplicationContext;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.search.Address;
import com.here.android.ui.search.AutoSuggestPlace;
import com.here.android.ui.search.AutoSuggestSearch;
import com.here.android.ui.search.Category;
import com.here.android.ui.search.DiscoveryRequest;
import com.here.android.ui.search.DiscoveryResult;
import com.here.android.ui.search.DiscoveryResult.ResultType;
import com.here.android.ui.search.ErrorCode;
import com.here.android.ui.search.PlaceLink;
import com.here.android.ui.search.PlaceRequest;
import com.here.android.ui.search.Request;
import com.here.android.ui.search.ReverseGeocodeRequest;
import com.here.android.ui.search.TextAutoSuggestionRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlacesApi
{
  private static final String HOST = com.ktm.navapp.search.PlacesApi.class.getSimpleName();
  static TextAutoSuggestionRequest mAutoSuggestRequest;
  private static PlaceRequest mDetailsRequest;
  private static ReverseGeocodeRequest mReverseGeocodeRequest;
  private static DiscoveryRequest mSearchRequest;
  private static TextAutoSuggestionRequest mTextAutoSuggestRequest;
  
  public PlacesApi() {}
  
  public static void autoSuggest(String paramString, double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "autoSuggest(%s,%f,%f)", new Object[] { paramString, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.10(paramString, paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public static void cancelDetails()
  {
    try
    {
      if (mDetailsRequest != null) {
        mDetailsRequest.cancel();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static boolean cancelReverseGeocode()
  {
    try
    {
      if (mReverseGeocodeRequest != null)
      {
        boolean bool = mReverseGeocodeRequest.cancel();
        return bool;
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static boolean cancelSearch()
  {
    try
    {
      if (mSearchRequest != null)
      {
        boolean bool = mSearchRequest.cancel();
        return bool;
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static void cancelSuggest()
  {
    try
    {
      if (mTextAutoSuggestRequest != null) {
        mTextAutoSuggestRequest.cancel();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static void details(PlaceLink paramPlaceLink, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "details(%s)", new Object[] { String.valueOf(paramPlaceLink) });
      initializeEngine(new PlacesApi.7(paramPlaceLink, paramCallback));
      return;
    }
    catch (Throwable paramPlaceLink)
    {
      throw paramPlaceLink;
    }
  }
  
  public static void details(String paramString1, String paramString2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "details(%s,%s)", new Object[] { paramString1, paramString2 });
      if ((!paramString1.isEmpty()) && (!paramString2.isEmpty()))
      {
        initializeEngine(new PlacesApi.8(paramString1, paramString2, paramCallback));
        return;
      }
      paramCallback.onResult(null);
      return;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  private static List expandCategories(String paramString, List paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Category localCategory = (Category)paramList.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(localCategory.getName());
      localArrayList.add(localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("+ ");
      localArrayList.addAll(expandCategories(localStringBuilder.toString(), localCategory.getSubCategories()));
    }
    return localArrayList;
  }
  
  public static void explore(double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "explore(%f,%f)", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.5(paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  public static void here(double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "here(%f,%f)", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.6(paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  private static void initializeEngine(Callback paramCallback)
  {
    try
    {
      if (MapEngine.isInitialized())
      {
        if (paramCallback != null) {
          paramCallback.onResult(Boolean.valueOf(true));
        }
      }
      else {
        MapEngine.getInstance().init(new ApplicationContext(Globals.getContext()), new PlacesApi.1(paramCallback));
      }
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  public static void logCategories()
  {
    Iterator localIterator = expandCategories("", Category.globalCategories()).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      RealmLog.append(HOST, "CAT: %s", new Object[] { str });
    }
  }
  
  public static Waypoint parseCoordinates(String paramString, double paramDouble1, double paramDouble2)
  {
    Object localObject = LatLonParser.parse(paramString);
    if (localObject != null)
    {
      String str1 = LatLonParser.formatDecimal(localObject[0], localObject[1]);
      String str2 = LatLonParser.formatDMS(localObject[0], localObject[1]);
      localObject = new Waypoint(str2, str2, str1, localObject[0], localObject[1]);
      searchText = paramString;
      searchLat = paramDouble1;
      searchLon = paramDouble2;
      return localObject;
    }
    return null;
  }
  
  public static void reverseGeocode(double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      reverseGeocode(paramDouble1, paramDouble2, paramCallback, false);
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  public static void reverseGeocode(double paramDouble1, double paramDouble2, Callback paramCallback, boolean paramBoolean)
  {
    try
    {
      RealmLog.append(HOST, "reverseGeocode(%f,%f)", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.2(paramDouble1, paramDouble2, paramCallback, paramBoolean));
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  public static void reverseGeocode2(double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "reverseGeocode2(%f,%f)", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.3(paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramCallback)
    {
      throw paramCallback;
    }
  }
  
  public static void search(String paramString, double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "search(%s,%f,%f)", new Object[] { paramString, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.4(paramString, paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public static void suggest(String paramString, double paramDouble1, double paramDouble2, Callback paramCallback)
  {
    try
    {
      RealmLog.append(HOST, "suggest(%s,%f,%f)", new Object[] { paramString, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      initializeEngine(new PlacesApi.9(paramString, paramDouble1, paramDouble2, paramCallback));
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public class AutoSuggestResultListener
    implements ResultListener<List<com.here.android.mpa.search.AutoSuggest>>
  {
    AutoSuggestResultListener() {}
    
    public void onCompleted(List paramList, ErrorCode paramErrorCode)
    {
      if (paramErrorCode != ErrorCode.NONE)
      {
        RealmLog.append(PlacesApi.HOST, "autoSuggest.onCompleted(code: %s)", new Object[] { String.valueOf(paramErrorCode) });
        onResult(null);
        return;
      }
      RealmLog.append(PlacesApi.HOST, "autoSuggest.onCompleted(count: %d)", new Object[] { Integer.valueOf(paramList.size()) });
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        paramErrorCode = (com.here.android.ui.search.AutoSuggest)paramList.next();
        RealmLog.append(PlacesApi.HOST, "AutoSuggest-Title: %s", new Object[] { paramErrorCode.getTitle() });
        RealmLog.append(PlacesApi.HOST, "AutoSuggest-TitleHighlight: %s", new Object[] { paramErrorCode.getHighlightedTitle() });
        if ((paramErrorCode instanceof AutoSuggestPlace))
        {
          paramErrorCode = (AutoSuggestPlace)paramErrorCode;
          RealmLog.append(PlacesApi.HOST, "AutoSuggestPlace-Category: %s", new Object[] { paramErrorCode.getCategory() });
          RealmLog.append(PlacesApi.HOST, "AutoSuggestPlace-Vicinity: %s", new Object[] { paramErrorCode.getVicinity() });
        }
        else if ((paramErrorCode instanceof AutoSuggestSearch))
        {
          paramErrorCode = (AutoSuggestSearch)paramErrorCode;
          RealmLog.append(PlacesApi.HOST, "AutoSuggestSearch-Category: %s", new Object[] { String.valueOf(paramErrorCode.getCategory()) });
        }
      }
      onResult(null);
    }
  }
  
  public class DetailsResultListener
    implements ResultListener<com.here.android.mpa.search.Place>
  {
    DetailsResultListener() {}
    
    public void onCompleted(com.here.android.ui.search.Place paramPlace, ErrorCode paramErrorCode)
    {
      if (paramErrorCode != ErrorCode.NONE)
      {
        RealmLog.append(PlacesApi.HOST, "details.onCompleted(code: %s)", new Object[] { String.valueOf(paramErrorCode) });
        onResult(null);
        return;
      }
      RealmLog.append(PlacesApi.HOST, "details.onCompleted(id: %s, name: %s)", new Object[] { paramPlace.getId(), paramPlace.getName() });
      onResult(paramPlace);
    }
  }
  
  public class ExploreResultListener
    implements ResultListener<com.here.android.mpa.search.DiscoveryResultPage>
  {
    protected String action;
    protected double date;
    protected double message;
    
    ExploreResultListener(double paramDouble1, double paramDouble2)
    {
      date = paramDouble1;
      message = paramDouble2;
      action = "explore";
    }
    
    protected void addToResults(List paramList, DiscoveryResult paramDiscoveryResult)
    {
      paramList.add(new Waypoint(HEREHelper.discoveryResultToPlaceLink(paramDiscoveryResult), "", date, message));
    }
    
    public void onCompleted(com.here.android.ui.search.DiscoveryResultPage paramDiscoveryResultPage, ErrorCode paramErrorCode)
    {
      if (paramErrorCode != ErrorCode.NONE)
      {
        paramDiscoveryResultPage = PlacesApi.HOST;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(action);
        ((StringBuilder)localObject).append(".onCompleted(code: %s)");
        RealmLog.append(paramDiscoveryResultPage, ((StringBuilder)localObject).toString(), new Object[] { String.valueOf(paramErrorCode) });
        onResult(null);
        return;
      }
      paramDiscoveryResultPage = paramDiscoveryResultPage.getItems();
      paramErrorCode = PlacesApi.HOST;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(action);
      ((StringBuilder)localObject).append(".onCompleted(count: %d)");
      RealmLog.append(paramErrorCode, ((StringBuilder)localObject).toString(), new Object[] { Integer.valueOf(paramDiscoveryResultPage.size()) });
      paramErrorCode = new ArrayList();
      paramDiscoveryResultPage = paramDiscoveryResultPage.iterator();
      while (paramDiscoveryResultPage.hasNext())
      {
        localObject = (DiscoveryResult)paramDiscoveryResultPage.next();
        if (((DiscoveryResult)localObject).getResultType() == DiscoveryResult.ResultType.PLACE) {
          addToResults(paramErrorCode, (DiscoveryResult)localObject);
        } else {
          RealmLog.append(PlacesApi.HOST, "UNHANDLED RESULT: %s", new Object[] { ((DiscoveryResult)localObject).getResultType().toString() });
        }
      }
      onResult(paramErrorCode);
    }
  }
  
  public class HereResultListener
    extends PlacesApi.ExploreResultListener
  {
    HereResultListener(double paramDouble1, double paramDouble2)
    {
      super(paramDouble1, paramDouble2);
      action = "here";
    }
  }
  
  public class ReverseGeocodeResultListener
    implements ResultListener<com.here.android.mpa.search.Location>
  {
    private boolean onlyCountry;
    
    ReverseGeocodeResultListener(boolean paramBoolean)
    {
      onlyCountry = paramBoolean;
    }
    
    public void onCompleted(com.here.android.ui.search.Location paramLocation, ErrorCode paramErrorCode)
    {
      if (paramErrorCode != ErrorCode.NONE)
      {
        RealmLog.append(PlacesApi.HOST, "reverseGeocode.onCompleted(code: %s)", new Object[] { String.valueOf(paramErrorCode) });
        onResult(null);
        return;
      }
      if (paramLocation != null)
      {
        paramLocation = paramLocation.getAddress();
        if (paramLocation != null)
        {
          if (onlyCountry) {
            paramLocation = paramLocation.getCountryCode();
          } else {
            paramLocation = Waypoint.toSingleLine(paramLocation.getText());
          }
          if (!paramLocation.isEmpty())
          {
            RealmLog.append(PlacesApi.HOST, "reverseGeocode.onCompleted(address: %s)", new Object[] { paramLocation });
            onResult(paramLocation);
            return;
          }
        }
      }
      RealmLog.i(PlacesApi.HOST, "reverseGeocode.onCompleted(empty result)");
      onResult(null);
    }
  }
  
  public class SearchResultListener
    extends PlacesApi.ExploreResultListener
  {
    private String text;
    
    SearchResultListener(double paramDouble1, double paramDouble2, String paramString)
    {
      super(paramDouble1, paramDouble2);
      action = "search";
      text = paramString;
    }
    
    protected void addToResults(List paramList, DiscoveryResult paramDiscoveryResult)
    {
      paramList.add(new Waypoint(HEREHelper.discoveryResultToPlaceLink(paramDiscoveryResult), text, date, message));
    }
  }
  
  public class SuggestResultListener
    implements ResultListener<List<com.here.android.mpa.search.AutoSuggest>>
  {
    SuggestResultListener() {}
    
    public void onCompleted(List paramList, ErrorCode paramErrorCode)
    {
      if (paramErrorCode != ErrorCode.NONE)
      {
        RealmLog.append(PlacesApi.HOST, "suggest.onCompleted(code: %s)", new Object[] { String.valueOf(paramErrorCode) });
        onResult(null);
        return;
      }
      RealmLog.append(PlacesApi.HOST, "suggest.onCompleted(count: %d)", new Object[] { Integer.valueOf(paramList.size()) });
      onResult(paramList);
    }
  }
}
