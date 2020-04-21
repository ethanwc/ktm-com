package com.com.navapp.utilities;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.javadocmd.simplelatlng.window.LatLngWindow;

public class GeoCalculator
{
  private LatLng point1 = new LatLng(0.0D, 0.0D);
  private LatLng point2 = new LatLng(0.0D, 0.0D);
  
  private GeoCalculator() {}
  
  public static GeoCalculator create()
  {
    return new GeoCalculator();
  }
  
  public double deltaLat2length(double paramDouble)
  {
    return LatLngWindow.latitudeDeltaToLength(paramDouble, LengthUnit.METER);
  }
  
  public double deltaLon2length(double paramDouble1, double paramDouble2)
  {
    return LatLngWindow.longitudeDeltaToLength(paramDouble1, LengthUnit.METER, paramDouble2);
  }
  
  public double getAzimuth()
  {
    return LatLngTool.initialBearing(point1, point2);
  }
  
  public double[] getDestination()
  {
    return new double[] { point2.getLatitude(), point2.getLongitude() };
  }
  
  public double getDestinationLatitude()
  {
    return point2.getLatitude();
  }
  
  public double getDestinationLongitude()
  {
    return point2.getLongitude();
  }
  
  public double getDistance()
  {
    return LatLngTool.distance(point1, point2, LengthUnit.METER);
  }
  
  public double length2deltaLat(double paramDouble)
  {
    return LatLngWindow.lengthToLatitudeDelta(paramDouble, LengthUnit.METER);
  }
  
  public double length2deltaLon(double paramDouble1, double paramDouble2)
  {
    return LatLngWindow.lengthToLongitudeDelta(paramDouble1, LengthUnit.METER, paramDouble2);
  }
  
  public GeoCalculator setDestination(double paramDouble1, double paramDouble2)
  {
    point2.setLatitudeLongitude(paramDouble1, paramDouble2);
    return this;
  }
  
  public GeoCalculator setDirection(double paramDouble1, double paramDouble2)
  {
    point2 = LatLngTool.travel(point1, paramDouble1, paramDouble2, LengthUnit.METER);
    return this;
  }
  
  public GeoCalculator setStart(double paramDouble1, double paramDouble2)
  {
    point1.setLatitudeLongitude(paramDouble1, paramDouble2);
    return this;
  }
}
