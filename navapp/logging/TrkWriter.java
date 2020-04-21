package com.com.navapp.logging;

import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.GeoPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringEscapeUtils;

class TrkWriter
{
  private static final String CREATOR = "KtmMyRide";
  private static final String KEY_FILENAME;
  private static final String KEY_MSBETWEEN;
  private static final String KEY_SEQCOUNT;
  private static final String KEY_SEQOPEN;
  private static final String KEY_TRACKNAME;
  private static final String TAG = com.ktm.navapp.logging.TrkWriter.class.getSimpleName();
  private static final SimpleDateFormat TRKPT_TS_FORMAT;
  private static String sFileName;
  private static long sLastTrackPointTs = 0L;
  private static long sSequenceCounter;
  private static boolean sSequenceOpen;
  private static long sTimeBetweenPointsMs;
  private static String sTrackName;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".FILENAME");
    KEY_FILENAME = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".TRACKNAME");
    KEY_TRACKNAME = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".SEQCOUNT");
    KEY_SEQCOUNT = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".SEQOPEN");
    KEY_SEQOPEN = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append(".MSBETWEEN");
    KEY_MSBETWEEN = localStringBuilder.toString();
    TRKPT_TS_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
    sFileName = null;
    sTrackName = null;
    sTimeBetweenPointsMs = 1000L;
    sSequenceCounter = 0L;
    sSequenceOpen = false;
  }
  
  TrkWriter() {}
  
  static void close()
  {
    if (init())
    {
      closeSequence();
      writeLine("</gpx>");
      String str = KEY_FILENAME;
      sFileName = "";
      Storage.put(str, "");
    }
  }
  
  static void closeSequence()
  {
    if ((init()) && (sSequenceOpen))
    {
      writeLine("</trkseg></trk>");
      String str = KEY_SEQOPEN;
      sSequenceOpen = false;
      Storage.enable(str, false);
    }
  }
  
  private static String escapeXML(String paramString)
  {
    return StringEscapeUtils.escapeXml10(paramString);
  }
  
  private static boolean init()
  {
    if (sFileName == null)
    {
      sFileName = Storage.get(KEY_FILENAME, "");
      sTrackName = Storage.get(KEY_TRACKNAME, "Track");
      sTimeBetweenPointsMs = Storage.getVersion(KEY_MSBETWEEN, 1000L);
      sSequenceCounter = Storage.getVersion(KEY_SEQCOUNT, 0L);
      sSequenceOpen = Storage.getBoolean(KEY_SEQOPEN, false);
    }
    return sFileName.isEmpty() ^ true;
  }
  
  static boolean newPosition(GeoPosition paramGeoPosition)
  {
    if ((init()) && (paramGeoPosition != null))
    {
      Date localDate = paramGeoPosition.getTimestamp();
      if (sLastTrackPointTs + sTimeBetweenPointsMs < localDate.getTime())
      {
        sLastTrackPointTs = localDate.getTime();
        openSequence();
        GeoCoordinate localGeoCoordinate = paramGeoPosition.getCoordinate();
        writeLine(String.format(Locale.US, "<trkpt lat='%f' lon='%f'><ele>%f</ele><time>%s</time><desc>speed=%f</desc></trkpt>", new Object[] { Double.valueOf(localGeoCoordinate.getLatitude()), Double.valueOf(localGeoCoordinate.getLongitude()), Double.valueOf(localGeoCoordinate.getAltitude()), TRKPT_TS_FORMAT.format(localDate), Double.valueOf(paramGeoPosition.getSpeed()) }));
        return true;
      }
    }
    return false;
  }
  
  static void open(String paramString1, String paramString2, long paramLong)
  {
    close();
    String str = KEY_FILENAME;
    sFileName = paramString1;
    Storage.put(str, paramString1);
    paramString1 = KEY_TRACKNAME;
    sTrackName = paramString2;
    Storage.put(paramString1, paramString2);
    paramString1 = KEY_MSBETWEEN;
    sTimeBetweenPointsMs = paramLong;
    Storage.put(paramString1, paramLong);
    paramString1 = KEY_SEQCOUNT;
    sSequenceCounter = 0L;
    Storage.put(paramString1, 0L);
    paramString1 = KEY_SEQOPEN;
    sSequenceOpen = false;
    Storage.enable(paramString1, false);
    if (init())
    {
      writeLine("<?xml version='1.0' encoding='UTF-8'?>");
      writeLine("<gpx xmlns='http://www.topografix.com/GPX/1/1' version='1.1' creator='KtmMyRide' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd'>");
    }
  }
  
  static void openSequence()
  {
    if ((init()) && (!sSequenceOpen))
    {
      writeLine(String.format(Locale.US, "<trk><name>%s</name><trkseg>", new Object[] { escapeXML(String.format(Locale.US, "%s%02d", new Object[] { sTrackName, Long.valueOf(sSequenceCounter) })) }));
      String str = KEY_SEQOPEN;
      sSequenceOpen = true;
      Storage.enable(str, true);
      str = KEY_SEQCOUNT;
      long l = sSequenceCounter + 1L;
      sSequenceCounter = l;
      Storage.put(str, l);
    }
  }
  
  private static void writeLine(String paramString)
  {
    FileHandler.writeLine(sFileName, paramString);
  }
}
