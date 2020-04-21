package com.com.navapp.riser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Model
{
  private static final Gson gson = ;
  
  private Model() {}
  
  public static Gson createGson()
  {
    return new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).registerTypeAdapter(com.ktm.navapp.riser.Model.LatLong.class, new LatLongTypeAdapter()).disableHtmlEscaping().create();
  }
  
  public static Gson createPrettyGson()
  {
    return new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).registerTypeAdapter(com.ktm.navapp.riser.Model.LatLong.class, new LatLongTypeAdapter()).disableHtmlEscaping().setPrettyPrinting().create();
  }
  
  public static Object fromJson(String paramString, Class paramClass)
    throws JsonSyntaxException
  {
    return gson.fromJson(paramString, paramClass);
  }
  
  public static boolean string_equals(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return paramString2 == null;
    }
    return paramString1.equals(paramString2);
  }
  
  public static String toJson(Object paramObject)
  {
    return gson.toJson(paramObject);
  }
  
  public class LatLong
  {
    double lat;
    double lon;
    
    public LatLong(double paramDouble)
    {
      lat = this$1;
      lon = paramDouble;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.valueOf(lat));
      localStringBuilder.append(",");
      localStringBuilder.append(String.valueOf(lon));
      return localStringBuilder.toString();
    }
  }
  
  class LatLongTypeAdapter
    implements JsonSerializer<com.ktm.navapp.riser.Model.LatLong>, JsonDeserializer<com.ktm.navapp.riser.Model.LatLong>
  {
    LatLongTypeAdapter() {}
    
    public Model.LatLong deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      try
      {
        paramJsonElement = paramJsonElement.getAsString().split(",");
        paramType = paramJsonElement[0];
        double d = Double.parseDouble(paramType);
        paramJsonElement = paramJsonElement[1];
        paramJsonElement = new Model.LatLong(d, Double.parseDouble(paramJsonElement));
        return paramJsonElement;
      }
      catch (Throwable paramJsonElement) {}catch (Exception paramJsonElement)
      {
        throw new JsonSyntaxException(paramJsonElement);
      }
      throw paramJsonElement;
    }
    
    public JsonElement serialize(Model.LatLong paramLatLong, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      try
      {
        paramType = new StringBuilder();
        paramType.append(String.valueOf(lat));
        paramType.append(",");
        paramType.append(String.valueOf(lon));
        paramLatLong = new JsonPrimitive(paramType.toString());
        return paramLatLong;
      }
      catch (Throwable paramLatLong)
      {
        throw paramLatLong;
      }
    }
  }
  
  public class LatLongs
  {
    public final List<com.ktm.navapp.riser.Model.LatLong> data = new ArrayList();
    
    public LatLongs() {}
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = data.iterator();
      while (localIterator.hasNext())
      {
        Model.LatLong localLatLong = (Model.LatLong)localIterator.next();
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(',');
        }
        localStringBuilder.append(localLatLong.toString());
      }
      localStringBuilder.append(']');
      localStringBuilder.insert(0, '[');
      return localStringBuilder.toString();
    }
    
    public LatLongs update(double paramDouble1, double paramDouble2)
    {
      data.add(new Model.LatLong(paramDouble1, paramDouble2));
      return this;
    }
  }
  
  public class RouteResponse
  {
    public String SQL_UPDATE_6_4;
    public final Meta _meta = new Meta();
    public String code;
    public final Routes routes = new Routes();
    public String warning;
    
    public RouteResponse() {}
    
    public class Meta
    {
      public long curvature;
      public String weight;
      
      public Meta() {}
    }
    
    public class Route
    {
      public double distance;
      public double duration;
      public final List<Double> simple = new ArrayList();
      
      public Route() {}
    }
    
    public class Routes
    {
      public final List<com.ktm.navapp.riser.Model.RouteResponse.Route> result = new ArrayList();
      public String warning;
      
      public Routes() {}
    }
  }
  
  public class StatusResponse
  {
    public String status;
    
    public StatusResponse() {}
  }
  
  class UtcDateTypeAdapter
    implements JsonSerializer<Date>, JsonDeserializer<Date>
  {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'", Locale.US);
    
    public UtcDateTypeAdapter()
    {
      dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      paramType = dateFormat;
      try
      {
        paramJsonElement = paramType.parse(paramJsonElement.getAsString());
        return paramJsonElement;
      }
      catch (Throwable paramJsonElement) {}catch (ParseException paramJsonElement)
      {
        throw new JsonSyntaxException(paramJsonElement);
      }
      throw paramJsonElement;
    }
    
    public JsonElement serialize(Date paramDate, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      try
      {
        paramDate = new JsonPrimitive(dateFormat.format(paramDate));
        return paramDate;
      }
      catch (Throwable paramDate)
      {
        throw paramDate;
      }
    }
  }
}
