package com.com.kmrc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class KmrcVersion
{
  private static final String CONFIGFILENAME = "/config.properties";
  private static final String KMRCVERSIONPROPERTY = "kmrc.version";
  
  public KmrcVersion() {}
  
  public static String getVersion()
  {
    try
    {
      Object localObject = com.ktm.kmrc.KmrcVersion.class.getResourceAsStream("/config.properties");
      localObject = new BufferedReader(new InputStreamReader((InputStream)localObject));
      Properties localProperties = new Properties();
      localProperties.load((Reader)localObject);
      localObject = localProperties.getProperty("kmrc.version");
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "unknown KMRC version";
  }
}
