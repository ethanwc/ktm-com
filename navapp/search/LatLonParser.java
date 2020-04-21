package com.com.navapp.search;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatLonParser
{
  private static final String ACTION_UPDATE_ALL = "(\\d{1,3})\\s*[?D ]\\s*([0-6]?\\d)\\s*['M ]\\s*(?:([0-6]?\\d(?:[,.]\\d+)?)\\s*(?:\"|''|S)?)?";
  private static final String CLASS_TAG = com.ktm.navapp.search.LatLonParser.class.getSimpleName();
  private static final String DECS = "([+-]?\\s*\\d{1,3}[,.]\\d+)";
  private static final Pattern DEC_A = Pattern.compile("^([NSEOW])\\s*(\\d{1,3}[,.]\\d+)\\s*[,;/]?\\s*([NSEOW])\\s*(\\d{1,3}[,.]\\d+)$");
  private static final Pattern DEC_B = Pattern.compile("^(\\d{1,3}[,.]\\d+)\\s*([NSEOW])\\s*[,;/]?\\s*(\\d{1,3}[,.]\\d+)\\s*([NSEOW])$");
  private static final Pattern DEC_S = Pattern.compile("^([+-]?\\s*\\d{1,3}[,.]\\d+)\\s*[ ,;/]\\s*([+-]?\\s*\\d{1,3}[,.]\\d+)$");
  private static final Pattern DMS_A = Pattern.compile("^([NSEOW])\\s*(\\d{1,3})\\s*[?D ]\\s*([0-6]?\\d)\\s*['M ]\\s*(?:([0-6]?\\d(?:[,.]\\d+)?)\\s*(?:\"|''|S)?)?\\s*[,;/]?\\s*([NSEOW])\\s*(\\d{1,3})\\s*[?D ]\\s*([0-6]?\\d)\\s*['M ]\\s*(?:([0-6]?\\d(?:[,.]\\d+)?)\\s*(?:\"|''|S)?)?$");
  private static final Pattern DMS_B = Pattern.compile("^(\\d{1,3})\\s*[?D ]\\s*([0-6]?\\d)\\s*['M ]\\s*(?:([0-6]?\\d(?:[,.]\\d+)?)\\s*(?:\"|''|S)?)?\\s*([NSEOW])\\s*[,;/]?\\s*(\\d{1,3})\\s*[?D ]\\s*([0-6]?\\d)\\s*['M ]\\s*(?:([0-6]?\\d(?:[,.]\\d+)?)\\s*(?:\"|''|S)?)?\\s*([NSEOW])$");
  private static final String PAGE_KEY = "(\\d{1,3}[,.]\\d+)";
  
  public LatLonParser() {}
  
  public static String formatDMS(double paramDouble1, double paramDouble2)
  {
    char c1;
    if (paramDouble1 < 0.0D) {
      c1 = 'S';
    } else {
      c1 = 'N';
    }
    paramDouble1 = Math.abs(paramDouble1);
    int i = (int)paramDouble1;
    paramDouble1 = (paramDouble1 - i) * 60.0D;
    int j = (int)paramDouble1;
    double d1 = j;
    char c2;
    if (paramDouble2 < 0.0D) {
      c2 = 'W';
    } else {
      c2 = 'E';
    }
    paramDouble2 = Math.abs(paramDouble2);
    int k = (int)paramDouble2;
    paramDouble2 = (paramDouble2 - k) * 60.0D;
    int m = (int)paramDouble2;
    double d2 = m;
    return String.format(Locale.US, "%02d?%02d'%02.1f\"%c %02d?%02d'%02.1f\"%c", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf((paramDouble1 - d1) * 60.0D), Character.valueOf(c1), Integer.valueOf(k), Integer.valueOf(m), Double.valueOf((paramDouble2 - d2) * 60.0D), Character.valueOf(c2) });
  }
  
  public static String formatDecimal(double paramDouble1, double paramDouble2)
  {
    return String.format(Locale.US, "%.6f, %.6f", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
  }
  
  private static double fromDMS(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    double d3 = Double.parseDouble(paramString2.replace(',', '.'));
    double d2 = 0.0D;
    if (paramString3 == null) {
      d1 = 0.0D;
    } else {
      d1 = Double.parseDouble(paramString3) / 60.0D;
    }
    if (paramString4 != null) {
      d2 = Double.parseDouble(paramString4.replace(',', '.')) / 3600.0D;
    }
    d2 = d3 + d1 + d2;
    double d1 = d2;
    if ("SW".contains(paramString1)) {
      d1 = -d2;
    }
    return d1;
  }
  
  public static double[] parse(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.trim().toUpperCase();
      if (paramString.length() > 6)
      {
        Object localObject1;
        Object localObject2;
        String str;
        if ("NSEOW".indexOf(paramString.charAt(0)) >= 0)
        {
          localObject1 = DEC_A.matcher(paramString);
          if (((Matcher)localObject1).matches())
          {
            paramString = ((Matcher)localObject1).group(1);
            localObject2 = ((Matcher)localObject1).group(3);
            if (("NS".contains(paramString)) && ("EOW".contains((CharSequence)localObject2)))
            {
              paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject1).group(2), null, null));
              localObject1 = Double.valueOf(fromDMS((String)localObject2, ((Matcher)localObject1).group(4), null, null));
              break label730;
            }
            if ((!"NS".contains((CharSequence)localObject2)) || (!"EOW".contains(paramString))) {
              break label726;
            }
            paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject1).group(2), null, null));
            localObject1 = Double.valueOf(fromDMS((String)localObject2, ((Matcher)localObject1).group(4), null, null));
            break label717;
          }
          localObject2 = DMS_A.matcher(paramString);
          if (!((Matcher)localObject2).matches()) {
            break label726;
          }
          paramString = ((Matcher)localObject2).group(1);
          str = ((Matcher)localObject2).group(5);
          if (("NS".contains(paramString)) && ("EOW".contains(str)))
          {
            paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject2).group(2), ((Matcher)localObject2).group(3), ((Matcher)localObject2).group(4)));
            localObject1 = Double.valueOf(fromDMS(str, ((Matcher)localObject2).group(6), ((Matcher)localObject2).group(7), ((Matcher)localObject2).group(8)));
          }
          else if (("NS".contains(str)) && ("EOW".contains(paramString)))
          {
            localObject1 = Double.valueOf(fromDMS(paramString, ((Matcher)localObject2).group(2), ((Matcher)localObject2).group(3), ((Matcher)localObject2).group(4)));
            paramString = Double.valueOf(fromDMS(str, ((Matcher)localObject2).group(6), ((Matcher)localObject2).group(7), ((Matcher)localObject2).group(8)));
          }
          else
          {
            label330:
            paramString = null;
            localObject1 = null;
          }
        }
        for (;;)
        {
          break;
          if ("NSEOW".indexOf(paramString.charAt(paramString.length() - 1)) < 0) {
            break label650;
          }
          localObject1 = DEC_B.matcher(paramString);
          if (((Matcher)localObject1).matches())
          {
            paramString = ((Matcher)localObject1).group(2);
            localObject2 = ((Matcher)localObject1).group(4);
            if (("NS".contains(paramString)) && ("EOW".contains((CharSequence)localObject2)))
            {
              paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject1).group(1), null, null));
              localObject1 = Double.valueOf(fromDMS((String)localObject2, ((Matcher)localObject1).group(3), null, null));
              break;
            }
            if ((!"NS".contains((CharSequence)localObject2)) || (!"EOW".contains(paramString))) {
              break label726;
            }
            paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject1).group(1), null, null));
            localObject1 = Double.valueOf(fromDMS((String)localObject2, ((Matcher)localObject1).group(3), null, null));
            break label717;
          }
          localObject2 = DMS_B.matcher(paramString);
          if (!((Matcher)localObject2).matches()) {
            break label726;
          }
          paramString = ((Matcher)localObject2).group(4);
          str = ((Matcher)localObject2).group(8);
          if (("NS".contains(paramString)) && ("EOW".contains(str)))
          {
            paramString = Double.valueOf(fromDMS(paramString, ((Matcher)localObject2).group(1), ((Matcher)localObject2).group(2), ((Matcher)localObject2).group(3)));
            localObject1 = Double.valueOf(fromDMS(str, ((Matcher)localObject2).group(5), ((Matcher)localObject2).group(6), ((Matcher)localObject2).group(7)));
            break;
          }
          if ((!"NS".contains(str)) || (!"EOW".contains(paramString))) {
            break label330;
          }
          localObject1 = Double.valueOf(fromDMS(paramString, ((Matcher)localObject2).group(1), ((Matcher)localObject2).group(2), ((Matcher)localObject2).group(3)));
          paramString = Double.valueOf(fromDMS(str, ((Matcher)localObject2).group(5), ((Matcher)localObject2).group(6), ((Matcher)localObject2).group(7)));
        }
        label650:
        paramString = DEC_S.matcher(paramString);
        if (paramString.matches())
        {
          localObject1 = Double.valueOf(Double.parseDouble(paramString.group(1).replaceAll("\\s*", "").replace(',', '.')));
          paramString = Double.valueOf(Double.parseDouble(paramString.group(2).replaceAll("\\s*", "").replace(',', '.')));
          label717:
          localObject2 = paramString;
          paramString = (String)localObject1;
          localObject1 = localObject2;
        }
        else
        {
          label726:
          paramString = null;
          localObject1 = null;
        }
        label730:
        if ((paramString != null) && (-90.0D <= paramString.doubleValue()) && (paramString.doubleValue() <= 90.0D) && (-180.0D <= ((Double)localObject1).doubleValue()) && (((Double)localObject1).doubleValue() <= 180.0D)) {
          return new double[] { paramString.doubleValue(), ((Double)localObject1).doubleValue() };
        }
      }
    }
    return null;
  }
}
