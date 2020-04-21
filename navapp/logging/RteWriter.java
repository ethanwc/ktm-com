package com.com.navapp.logging;

import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.routing.Maneuver;
import com.here.android.ui.routing.Route;
import com.here.android.ui.routing.RouteOptions;
import com.here.android.ui.routing.RoutePlan;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringEscapeUtils;

public class RteWriter
{
  private static final String CREATOR = "KtmMyRide";
  private static final String KEY_COUNTER1;
  private static final String KEY_COUNTER2;
  private static final String KEY_FILENAME;
  private static final String KEY_INSTRUCTION;
  private static final String TAG = com.ktm.navapp.logging.RteWriter.class.getSimpleName();
  private static long sCounter1 = 0L;
  private static long sCounter2 = 0L;
  private static String sFileName;
  private static long sInstruction = 0L;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".FILENAME");
    KEY_FILENAME = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".COUNTER1");
    KEY_COUNTER1 = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".COUNTER2");
    KEY_COUNTER2 = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".INSTRUCTION");
    KEY_INSTRUCTION = localStringBuilder.toString();
    sFileName = null;
  }
  
  public RteWriter() {}
  
  static void close()
  {
    if (init())
    {
      String str = KEY_FILENAME;
      sFileName = "";
      Storage.put(str, "");
      str = KEY_COUNTER1;
      sCounter1 = 0L;
      Storage.put(str, 0L);
      str = KEY_COUNTER2;
      sCounter2 = 0L;
      Storage.put(str, 0L);
      str = KEY_INSTRUCTION;
      sInstruction = 0L;
      Storage.put(str, 0L);
    }
  }
  
  private static String escapeXML(String paramString)
  {
    return StringEscapeUtils.escapeXml10(paramString);
  }
  
  static String getNextInstructionId()
  {
    if (init())
    {
      String str = KEY_INSTRUCTION;
      long l = sInstruction + 1L;
      sInstruction = l;
      Storage.put(str, l);
    }
    return String.valueOf(sInstruction);
  }
  
  static String getRouteId()
  {
    init();
    return String.format(Locale.US, "%d.%d", new Object[] { Long.valueOf(sCounter1), Long.valueOf(sCounter2) });
  }
  
  private static boolean init()
  {
    if (sFileName == null)
    {
      sFileName = Storage.get(KEY_FILENAME, "");
      sCounter1 = Storage.getVersion(KEY_COUNTER1, 0L);
      sCounter2 = Storage.getVersion(KEY_COUNTER2, 0L);
      sInstruction = Storage.getVersion(KEY_INSTRUCTION, 0L);
    }
    return sFileName.isEmpty() ^ true;
  }
  
  static void newRoute(Route paramRoute, boolean paramBoolean)
  {
    if (init())
    {
      long l;
      if (paramBoolean)
      {
        str = KEY_COUNTER2;
        l = sCounter2 + 1L;
        sCounter2 = l;
        Storage.put(str, l);
      }
      else
      {
        str = KEY_COUNTER1;
        l = sCounter1 + 1L;
        sCounter1 = l;
        Storage.put(str, l);
        str = KEY_COUNTER2;
        sCounter2 = 0L;
        Storage.put(str, 0L);
        str = KEY_INSTRUCTION;
        sInstruction = 0L;
        Storage.put(str, 0L);
      }
      String str = TripManager.getTrip().getTitle().replaceAll("\\W+", "_");
      writeRoute(String.format(Locale.US, "%s_%d.%d_%s.gpx", new Object[] { sFileName, Long.valueOf(sCounter1), Long.valueOf(sCounter2), str }), paramRoute);
    }
  }
  
  static void open(String paramString)
  {
    close();
    String str = KEY_FILENAME;
    sFileName = paramString;
    Storage.put(str, paramString);
    paramString = KEY_COUNTER1;
    sCounter1 = 0L;
    Storage.put(paramString, 0L);
    paramString = KEY_COUNTER2;
    sCounter2 = 0L;
    Storage.put(paramString, 0L);
    paramString = KEY_INSTRUCTION;
    sInstruction = 0L;
    Storage.put(paramString, 0L);
  }
  
  private static void writeRoute(String paramString, Route paramRoute)
  {
    RealmLog.append(TAG, "writeRoute(): fname=%s", new Object[] { paramString });
    String str = String.format(Locale.US, "%d.%d", new Object[] { Long.valueOf(sCounter1), Long.valueOf(sCounter2) });
    Object localObject = LogExt.getSessionId();
    FileHandler.writeLine(paramString, "<?xml version='1.0' encoding='UTF-8'?>");
    FileHandler.writeLine(paramString, "<gpx xmlns='http://www.topografix.com/GPX/1/1' version='1.1' creator='KtmMyRide' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd'>");
    FileHandler.writeLine(paramString, String.format(Locale.US, "<rte><name>%s, Route %s, Session %s</name>", new Object[] { escapeXML(TripManager.getTrip().getTitle()), str, localObject }));
    FileHandler.writeLine(paramString, String.format(Locale.US, "<desc>\n  Highways: %b,\n  TollRoads: %b,\n  DirtRoads: %b,\n  RouteType: %s,\n  Language: %s\n</desc>", new Object[] { Boolean.valueOf(paramRoute.getRoutePlan().getRouteOptions().areHighwaysAllowed()), Boolean.valueOf(paramRoute.getRoutePlan().getRouteOptions().areTollRoadsAllowed()), Boolean.valueOf(paramRoute.getRoutePlan().getRouteOptions().areDirtRoadsAllowed()), paramRoute.getRoutePlan().getRouteOptions().getRouteType().name(), Locale.getDefault().getLanguage() }));
    localObject = paramRoute.getRouteGeometry().iterator();
    GeoCoordinate localGeoCoordinate;
    while (((Iterator)localObject).hasNext())
    {
      localGeoCoordinate = (GeoCoordinate)((Iterator)localObject).next();
      FileHandler.writeLine(paramString, String.format(Locale.US, "<rtept lat='%f' lon='%f' />", new Object[] { Double.valueOf(localGeoCoordinate.getLatitude()), Double.valueOf(localGeoCoordinate.getLongitude()) }));
    }
    FileHandler.writeLine(paramString, "</rte>");
    paramRoute = paramRoute.getManeuvers().iterator();
    int i = 0;
    while (paramRoute.hasNext())
    {
      localObject = (Maneuver)paramRoute.next();
      localGeoCoordinate = ((Maneuver)localObject).getCoordinate();
      Locale localLocale = Locale.US;
      double d1 = localGeoCoordinate.getLatitude();
      double d2 = localGeoCoordinate.getLongitude();
      i += 1;
      FileHandler.writeLine(paramString, String.format(localLocale, "<wpt lat='%f' lon='%f'><name>Maneuver %d, Route %s</name><desc>\n  Type: %s,\n  Current Road: %s,\n  Next Road: %s,\n  Angle: %d\n</desc></wpt>", new Object[] { Double.valueOf(d1), Double.valueOf(d2), Integer.valueOf(i), str, ((Maneuver)localObject).getIcon().name(), escapeXML(((Maneuver)localObject).getRoadName()), escapeXML(((Maneuver)localObject).getNextRoadName()), Integer.valueOf(((Maneuver)localObject).getAngle()) }));
    }
    FileHandler.writeLine(paramString, "</gpx>");
  }
}
