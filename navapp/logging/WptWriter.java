package com.com.navapp.logging;

import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoCoordinate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringEscapeUtils;

class WptWriter
{
  private static final String CREATOR = "KtmMyRide";
  private static final String KEY_FILENAME;
  private static final String TAG = com.ktm.navapp.logging.WptWriter.class.getSimpleName();
  static final SimpleDateFormat WPT_TS_FORMAT = new SimpleDateFormat("dd.MM.yy HH:mm.ss", Locale.US);
  private static String sFileName = null;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".FILENAME");
    KEY_FILENAME = localStringBuilder.toString();
  }
  
  WptWriter() {}
  
  static void close()
  {
    if (init())
    {
      writeLine("</gpx>");
      String str = KEY_FILENAME;
      sFileName = "";
      Storage.put(str, "");
    }
  }
  
  static String escapeXML(String paramString)
  {
    return StringEscapeUtils.escapeXml10(paramString);
  }
  
  private static boolean init()
  {
    if (sFileName == null) {
      sFileName = Storage.get(KEY_FILENAME, "");
    }
    return sFileName.isEmpty() ^ true;
  }
  
  static void newWaypoint(GeoCoordinate paramGeoCoordinate, String paramString1, String paramString2, String paramString3)
  {
    if (init()) {
      writeLine(String.format(Locale.US, "<wpt lat='%f' lon='%f'><name>%s</name><desc>\n  %s,\n%s\n</desc></wpt>", new Object[] { Double.valueOf(paramGeoCoordinate.getLatitude()), Double.valueOf(paramGeoCoordinate.getLongitude()), escapeXML(paramString2), WPT_TS_FORMAT.format(new Date()), escapeXML(paramString3) }));
    }
  }
  
  static void open(String paramString)
  {
    close();
    String str = KEY_FILENAME;
    sFileName = paramString;
    Storage.put(str, paramString);
    if (init())
    {
      writeLine("<?xml version='1.0' encoding='UTF-8'?>");
      writeLine("<gpx xmlns='http://www.topografix.com/GPX/1/1' version='1.1' creator='KtmMyRide' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd'>");
    }
  }
  
  private static void writeLine(String paramString)
  {
    FileHandler.writeLine(sFileName, paramString);
  }
}
