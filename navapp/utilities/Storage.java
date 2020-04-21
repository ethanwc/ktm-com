package com.com.navapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Storage
{
  private static final boolean DEBUG_LOG = false;
  private static final String TAG = com.ktm.navapp.utilities.Storage.class.getSimpleName();
  private static final Gson gson = createGson();
  private static SharedPreferences preferences;
  
  public Storage() {}
  
  public static boolean changed(String paramString, Object paramObject)
  {
    if (paramObject != null) {
      paramObject = toJson(paramObject);
    } else {
      paramObject = null;
    }
    SharedPreferences localSharedPreferences = preferences;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("o.");
    localStringBuilder.append(paramString);
    paramString = localSharedPreferences.getString(localStringBuilder.toString(), null);
    if (paramString == null) {
      return paramObject != null;
    }
    return paramString.equals(paramObject) ^ true;
  }
  
  public static void clear()
  {
    preferences.edit().clear().apply();
  }
  
  public static Gson createGson()
  {
    return new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).disableHtmlEscaping().create();
  }
  
  public static void delete(String paramString)
  {
    SharedPreferences.Editor localEditor = preferences.edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("o.");
    localStringBuilder.append(paramString);
    localEditor.remove(localStringBuilder.toString()).apply();
  }
  
  public static void enable(String paramString, boolean paramBoolean)
  {
    preferences.edit().putBoolean(paramString, paramBoolean).apply();
  }
  
  public static void forceCommit()
  {
    preferences.edit().putLong("__FORCE_COMMIT__", System.currentTimeMillis()).commit();
  }
  
  public static Object fromJson(String paramString, Class paramClass)
    throws JsonSyntaxException
  {
    return gson.fromJson(paramString, paramClass);
  }
  
  public static String get(String paramString1, String paramString2)
  {
    return preferences.getString(paramString1, paramString2);
  }
  
  public static boolean getBoolean(String paramString, boolean paramBoolean)
  {
    return preferences.getBoolean(paramString, paramBoolean);
  }
  
  public static String getSilent(String paramString1, String paramString2)
  {
    return preferences.getString(paramString1, paramString2);
  }
  
  public static long getVersion(String paramString, long paramLong)
  {
    return preferences.getLong(paramString, paramLong);
  }
  
  public static void initialize(Context paramContext)
  {
    try
    {
      if (preferences == null)
      {
        Context localContext = paramContext.getApplicationContext();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getPackageName());
        localStringBuilder.append("_preferences");
        preferences = localContext.getSharedPreferences(localStringBuilder.toString(), 0);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static Object load(String paramString, Class paramClass)
  {
    try
    {
      paramString = load(paramString, paramClass, paramClass.newInstance());
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static Object load(String paramString, Class paramClass, Object paramObject)
  {
    SharedPreferences localSharedPreferences = preferences;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("o.");
    localStringBuilder.append(paramString);
    paramString = localSharedPreferences.getString(localStringBuilder.toString(), null);
    if (paramString != null) {
      paramObject = fromJson(paramString, paramClass);
    }
    return paramObject;
  }
  
  public static void put(String paramString, long paramLong)
  {
    preferences.edit().putLong(paramString, paramLong).apply();
  }
  
  public static void put(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      preferences.edit().remove(paramString1).apply();
      return;
    }
    preferences.edit().putString(paramString1, paramString2).apply();
  }
  
  public static void save(String paramString, Object paramObject)
  {
    Object localObject = toJson(paramObject);
    if ((paramObject != null) && (localObject != null))
    {
      paramObject = preferences.edit();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("o.");
      localStringBuilder.append(paramString);
      paramObject.putString(localStringBuilder.toString(), (String)localObject).apply();
      return;
    }
    paramObject = preferences.edit();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("o.");
    ((StringBuilder)localObject).append(paramString);
    paramObject.remove(((StringBuilder)localObject).toString()).apply();
  }
  
  public static String toJson(Object paramObject)
  {
    return gson.toJson(paramObject);
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
