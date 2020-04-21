package com.com.navapp.search;

import android.text.Html;
import android.text.Spanned;
import com.com.navapp.logging.RealmLog;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.search.DiscoveryResult;
import com.here.android.ui.search.PlaceLink;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Waypoint
{
  public static final Waypoint MY_LOCATION = new Waypoint("MY_LOCATION");
  public String address;
  public String date;
  public double latitude;
  public double longitude;
  public String name;
  public double searchLat;
  public double searchLon;
  public String searchText;
  
  public Waypoint() {}
  
  public Waypoint(double paramDouble1, double paramDouble2)
  {
    date = Dealers.getId(paramDouble1, paramDouble2);
    name = date;
    latitude = paramDouble1;
    longitude = paramDouble2;
  }
  
  public Waypoint(Dealers.Dealer paramDealer, double paramDouble1, double paramDouble2)
  {
    date = Dealers.getId(paramDouble1, paramDouble2);
    name = generic_name;
    address = contact.getAddressMultiline();
    latitude = paramDouble1;
    longitude = paramDouble2;
  }
  
  public Waypoint(PlaceLink paramPlaceLink)
  {
    date = paramPlaceLink.getId();
    name = paramPlaceLink.getTitle();
    address = paramPlaceLink.getVicinity();
    latitude = paramPlaceLink.getPosition().getLatitude();
    longitude = paramPlaceLink.getPosition().getLongitude();
  }
  
  public Waypoint(PlaceLink paramPlaceLink, String paramString, double paramDouble1, double paramDouble2)
  {
    this(paramPlaceLink);
    searchText = paramString;
    searchLat = paramDouble1;
    searchLon = paramDouble2;
  }
  
  public Waypoint(String paramString)
  {
    date = paramString;
    name = paramString;
  }
  
  public Waypoint(String paramString1, String paramString2, String paramString3, double paramDouble1, double paramDouble2)
  {
    date = paramString1;
    name = paramString2;
    address = paramString3;
    latitude = paramDouble1;
    longitude = paramDouble2;
  }
  
  public static Waypoint address2destination(double paramDouble1, double paramDouble2, String paramString)
  {
    RealmLog.append("Waypoint", "address2destination(%f,%f,%s)", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2), paramString });
    Object localObject = LatLonParser.formatDecimal(paramDouble1, paramDouble2);
    String str = LatLonParser.formatDMS(paramDouble1, paramDouble2);
    localObject = new Waypoint(str, str, (String)localObject, paramDouble1, paramDouble2);
    searchText = str;
    searchLat = paramDouble1;
    searchLon = paramDouble2;
    if (paramString != null)
    {
      paramString = Html.fromHtml(paramString).toString().replaceAll("[ \t]+", " ").replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s*\\n\\s*", "\n").replace("\n", ", ").replaceAll("^\\s*,\\s*", "").replaceAll("\\s*,\\s*$", "");
      if (!paramString.trim().isEmpty())
      {
        int i = paramString.indexOf(',');
        if ((i > 0) && (i < paramString.length() - 1))
        {
          paramString = paramString.split(",", 2);
          name = paramString[0].trim();
          address = paramString[1].trim();
          return localObject;
        }
        name = paramString.trim();
        address = "";
      }
    }
    return localObject;
  }
  
  public static String applyHighlight(String paramString, Pattern paramPattern)
  {
    if (paramString == null) {
      return "";
    }
    String str = paramString.toUpperCase();
    paramString = str;
    if (paramPattern != null) {
      paramString = paramPattern.matcher(str).replaceAll("<b>$0</b>");
    }
    return paramString;
  }
  
  public static Pattern compileHighlight(String paramString)
  {
    if ((paramString != null) && (!paramString.trim().isEmpty()))
    {
      paramString = paramString.trim().toUpperCase().split("\\s+");
      Arrays.sort(paramString, Collections.reverseOrder());
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramString[i];
        if (localStringBuilder.length() != 0) {
          localStringBuilder.append("|");
        }
        localStringBuilder.append("(\\b");
        localStringBuilder.append(Pattern.quote(str));
        localStringBuilder.append(")");
        i += 1;
      }
      return Pattern.compile(localStringBuilder.toString());
    }
    return null;
  }
  
  public static boolean testPattern(String paramString, Pattern paramPattern)
  {
    return (paramString != null) && (paramPattern != null) && (paramPattern.matcher(paramString.toUpperCase()).find());
  }
  
  public static String toSingleLine(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.isEmpty()) {
        return "";
      }
      String str = paramString;
      if (paramString.indexOf('<') >= 0)
      {
        Spanned localSpanned = Html.fromHtml(paramString);
        str = paramString;
        if (localSpanned != null) {
          str = localSpanned.toString();
        }
      }
      return str.replaceAll("[ \t]+", " ").replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s*\\n\\s*", "\n").replace("\n", ", ");
    }
    return "";
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      Object localObject = (Waypoint)paramObject;
      if (latitude == latitude)
      {
        if (longitude != longitude) {
          return false;
        }
        paramObject = date;
        localObject = date;
        if (paramObject != null) {
          if (paramObject.equals(localObject)) {
            break label88;
          }
        } else if (localObject == null) {
          return true;
        }
      }
    }
    return false;
    label88:
    return true;
  }
  
  public String getAddressMultiline()
  {
    String str = address;
    if (str != null) {
      return str;
    }
    return "";
  }
  
  public String getAddressSingleLine()
  {
    return toSingleLine(getAddressMultiline());
  }
  
  public String getTitle()
  {
    String str2 = toSingleLine(name);
    String str1 = str2;
    if (str2.isEmpty()) {
      str1 = getAddressSingleLine();
    }
    if (str1.isEmpty())
    {
      if ((latitude != 0.0D) && (longitude != 0.0D)) {
        return String.format(Locale.US, "%.6f, %.6f", new Object[] { Double.valueOf(latitude), Double.valueOf(longitude) });
      }
      return String.valueOf(date);
    }
    return str1;
  }
  
  public Spanned getTitleHighlight(Pattern paramPattern)
  {
    return Html.fromHtml(applyHighlight(getTitle(), paramPattern));
  }
  
  public int hashCode()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.valueOf(latitude));
    localStringBuilder.append(String.valueOf(longitude));
    localStringBuilder.append(String.valueOf(date));
    return localStringBuilder.toString().hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Waypoint{id='");
    localStringBuilder.append(date);
    localStringBuilder.append('\'');
    localStringBuilder.append(", name='");
    localStringBuilder.append(name);
    localStringBuilder.append('\'');
    localStringBuilder.append(", address='");
    localStringBuilder.append(address);
    localStringBuilder.append('\'');
    localStringBuilder.append(", lat=");
    localStringBuilder.append(latitude);
    localStringBuilder.append(", lon=");
    localStringBuilder.append(longitude);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
