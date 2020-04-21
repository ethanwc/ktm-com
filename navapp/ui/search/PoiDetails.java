package com.com.navapp.ui.search;

import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.search.Dealers;
import com.com.navapp.search.PlacesApi;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Utilities;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.ViewObject;
import com.here.android.ui.common.ViewObject.Type;
import com.here.android.ui.mapping.Location;
import com.here.android.ui.mapping.LocationInfo;
import com.here.android.ui.mapping.LocationInfo.Field;
import com.here.android.ui.mapping.MapCartoMarker;
import com.here.android.ui.mapping.MapMarker;
import com.here.android.ui.mapping.MapObject;
import com.here.android.ui.mapping.MapOverlay;
import com.here.android.ui.mapping.MapProxyObject;
import com.here.android.ui.mapping.TransitStopInfo;
import com.here.android.ui.mapping.TransitStopObject;
import java.util.Iterator;
import java.util.List;

public class PoiDetails
{
  static final String APP_NAME = com.ktm.navapp.ui.search.PoiDetails.class.getSimpleName();
  static final String BUBBLE_ID = "PoiInfoBubble";
  static final int BUBBLE_OFFSET = Utilities.pxFromDp(30.0F);
  
  public PoiDetails() {}
  
  static boolean isBubbleShown()
  {
    try
    {
      MapOverlay localMapOverlay = MapManager.getInstance().getOverlay("PoiInfoBubble");
      boolean bool;
      if (localMapOverlay != null) {
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
  
  public static boolean onMapObjectsSelected(List paramList)
  {
    RealmLog.append(APP_NAME, "onMapObjectsSelected(%d)", new Object[] { Integer.valueOf(paramList.size()) });
    Object localObject1 = paramList.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      paramList = (ViewObject)((Iterator)localObject1).next();
      Object localObject2;
      if (paramList.getBaseType() == ViewObject.Type.USER_OBJECT)
      {
        localObject2 = (MapObject)paramList;
        RealmLog.append(APP_NAME, "MapObject: %s", new Object[] { String.valueOf(((MapObject)localObject2).getType()) });
        switch (4.$SwitchMap$com$here$android$mpa$mapping$MapObject$Type[localObject2.getType().ordinal()])
        {
        case 2: 
        case 3: 
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 9: 
        case 10: 
        default: 
          break;
        case 1: 
          paramList = Dealers.getDealer(((MapMarker)paramList).getCoordinate());
          if (paramList == null) {
            continue;
          }
          Events.postEvent(paramList);
          return true;
        }
      }
      else if (paramList.getBaseType() == ViewObject.Type.PROXY_OBJECT)
      {
        paramList = (MapProxyObject)paramList;
        RealmLog.append(APP_NAME, "MapProxyObject: %s", new Object[] { String.valueOf(paramList.getType()) });
        switch (4.$SwitchMap$com$here$android$mpa$mapping$MapProxyObject$Type[paramList.getType().ordinal()])
        {
        case 1: 
        case 2: 
        case 3: 
        case 4: 
        case 5: 
        case 7: 
        default: 
          break;
        case 9: 
          Events.postOnMainThread(new PoiDetails.2(paramList));
          return true;
        case 8: 
          localObject2 = ((MapCartoMarker)paramList).getLocation();
          if (localObject2 != null) {
            paramList = ((Location)localObject2).getInfo();
          } else {
            paramList = null;
          }
          if (paramList == null) {
            continue;
          }
          localObject1 = new GeoCoordinate(((Location)localObject2).getCoordinate().getLatitude(), ((Location)localObject2).getCoordinate().getLongitude());
          localObject2 = paramList.getField(LocationInfo.Field.PLACE_NAME);
          String str1 = paramList.getField(LocationInfo.Field.PLACE_PHONE_NUMBER);
          String str2 = paramList.getField(LocationInfo.Field.PLACE_CATEGORY);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("(");
          localStringBuilder.append(str2);
          localStringBuilder.append(")");
          showInfoBubble((GeoCoordinate)localObject1, (String)localObject2, new String[] { localStringBuilder.toString(), str1 });
          PlacesApi.details(paramList.getField(LocationInfo.Field.FOREIGN_ID_SOURCE), paramList.getField(LocationInfo.Field.FOREIGN_ID), new PoiDetails.1(str2, (GeoCoordinate)localObject1, (String)localObject2, str1));
          return true;
        case 6: 
          paramList = ((TransitStopObject)paramList).getTransitStopInfo();
          if (paramList == null) {
            continue;
          }
          showInfoBubble(new GeoCoordinate(paramList.getCoordinate().getLatitude(), paramList.getCoordinate().getLongitude()), paramList.getOfficialName(), new String[] { paramList.getInformalName() });
          return true;
        }
      }
      else
      {
        RealmLog.append(APP_NAME, "ViewObject: %s", new Object[] { String.valueOf(paramList.getBaseType()) });
      }
    }
    return false;
  }
  
  public static boolean onTapEvent()
  {
    removeInfoBubble();
    return false;
  }
  
  static void removeInfoBubble()
  {
    try
    {
      RealmLog.i(APP_NAME, "removeInfoBubble()");
      PlacesApi.cancelDetails();
      MapManager.getInstance().removeOverlay("PoiInfoBubble");
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  static void showInfoBubble(GeoCoordinate paramGeoCoordinate, String paramString, String... paramVarArgs)
  {
    try
    {
      RealmLog.append(APP_NAME, "showInfoBubble(%s)", new Object[] { paramString });
      if ((paramString != null) && (!paramString.trim().isEmpty()))
      {
        Events.postOnMainThread(new PoiDetails.3(paramVarArgs, paramString, paramGeoCoordinate));
        return;
      }
      return;
    }
    catch (Throwable paramGeoCoordinate)
    {
      throw paramGeoCoordinate;
    }
  }
}
