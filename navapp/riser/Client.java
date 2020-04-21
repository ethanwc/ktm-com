package com.com.navapp.riser;

import com.com.navapp.logging.RealmLog;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Client
{
  private static final String ACCEPT_VERSION = "1.3.0";
  private static final String API_KEY = "ynO7Zhw1QUAgexNYpoBlirCMkYStdg";
  private static final String BASE_URL = "https://routing.riserapp.com/";
  private static final Map<String, String> RESPONSES_RISER = new HashMap();
  private static final String TAG = "Client";
  private static final long TIME_OUT = 30000L;
  private static final String USER_AGENT = "KTM MY RIDE (Android)";
  private API_RISER mApiRiser;
  private String mBaseUrl = "https://routing.riserapp.com/";
  private long mTimeoutMillis = 30000L;
  
  public Client() {}
  
  public static void addDefaults()
  {
    addRiser("GET /status", "200 {\"status\":\"OK\"}");
    addRiser("GET /status?api_key=401", "401 {\"code\":\"The credentials you provided are not correct\"}");
    addRiser("GET /route?flattenResults=true", "200 {\"id\":\"1lp7w3kuajcutmznm\",\"routes\":{\"result\":[{\"duration\":27950148,\"distance\":444253.645,\"simple\":[48.303155,16.212132,47.812398,13.056006]}]},\"_meta\":{\"curvature\":40253}}");
    addRiser("GET /roundTrip?flattenResults=true", "200 {\"id\":\"1lp7w3kuajcutq1bn\",\"routes\":{\"result\":[{\"duration\":3426306,\"distance\":39848.148,\"simple\":[48.303155,16.212132,48.261353,16.088567,48.303155,16.212132]}],\"warning\":\"Result duration exceeds request duration\"},\"_meta\":{\"curvature\":0,\"weight\":\"curvature\"},\"warning\":\"Result duration exceeds request duration\"}");
  }
  
  public static void addRiser(String paramString1, String paramString2)
  {
    RESPONSES_RISER.put(paramString1, paramString2);
  }
  
  public static void clearResponses()
  {
    RESPONSES_RISER.clear();
  }
  
  public static Client create()
  {
    return new Client();
  }
  
  private static Object createService(Class paramClass, String paramString, long paramLong)
  {
    RealmLog.append("Client", "createService(service=%s, baseUrl=%s, timeout=%d)", new Object[] { paramClass.getSimpleName(), paramString, Long.valueOf(paramLong) });
    OkHttpClient.Builder localBuilder = new OkHttpClient.Builder();
    localBuilder.connectTimeout(paramLong, TimeUnit.MILLISECONDS).readTimeout(paramLong, TimeUnit.MILLISECONDS).writeTimeout(paramLong, TimeUnit.MILLISECONDS);
    localBuilder.addInterceptor(new Client.1(paramClass));
    String str = paramString;
    if (paramString == null) {
      if (paramClass == com.ktm.navapp.riser.Client.API_RISER.class)
      {
        localBuilder.addInterceptor(new ResponseInterceptor(RESPONSES_RISER));
        str = "http://fake-riser/";
      }
      else
      {
        str = "http://fake-service/";
      }
    }
    return new Retrofit.Builder().baseUrl(str).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(Model.createGson())).client(localBuilder.build()).build().create(paramClass);
  }
  
  public API_RISER apiRiser()
  {
    try
    {
      if (mApiRiser == null) {
        mApiRiser = ((API_RISER)createService(com.ktm.navapp.riser.Client.API_RISER.class, mBaseUrl, mTimeoutMillis));
      }
      API_RISER localAPI_RISER = mApiRiser;
      return localAPI_RISER;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Client setBaseUrl(String paramString)
  {
    mBaseUrl = paramString;
    return this;
  }
  
  public Client setTimeout(long paramLong)
  {
    mTimeoutMillis = paramLong;
    return this;
  }
  
  public abstract interface API_RISER
  {
    public abstract Call getRoundTrip(Model.LatLong paramLatLong, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);
    
    public abstract Call getRoute(Model.LatLongs paramLatLongs, Integer paramInteger1, Integer paramInteger2);
    
    public abstract Call getStatus();
  }
  
  public class ApiException
    extends IOException
  {
    private final int code;
    
    ApiException()
    {
      super();
      code = this$1;
    }
    
    public int code()
    {
      return code;
    }
  }
  
  class ResponseInterceptor
    implements Interceptor
  {
    ResponseInterceptor() {}
    
    String getResponse(String paramString)
    {
      Iterator localIterator = entrySet().iterator();
      int i = -1;
      label20:
      Map.Entry localEntry;
      for (String str1 = null; localIterator.hasNext(); str1 = (String)localEntry.getValue())
      {
        localEntry = (Map.Entry)localIterator.next();
        String str2;
        if (localEntry.getKey() != null) {
          str2 = (String)localEntry.getKey();
        } else {
          str2 = "";
        }
        if ((!paramString.startsWith(str2)) || (str2.length() <= i)) {
          break label20;
        }
        i = str2.length();
      }
      return str1;
    }
    
    public Response intercept(Interceptor.Chain paramChain)
      throws IOException
    {
      Object localObject3 = paramChain.request().method();
      String str1 = paramChain.request().url().encodedPath();
      Object localObject2 = paramChain.request().url().encodedQuery();
      Object localObject1 = "";
      if (localObject2 != null)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("?");
        ((StringBuilder)localObject2).append(paramChain.request().url().encodedQuery());
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      else
      {
        localObject2 = "";
      }
      String str2 = getResponse(String.format("%s %s%s", new Object[] { localObject3, str1, localObject2 }));
      int i = 404;
      str1 = "application/json";
      localObject3 = localObject1;
      localObject2 = str1;
      if (str2 != null)
      {
        localObject2 = str2.split(" ", 2);
        if (localObject2.length >= 2) {
          localObject1 = localObject2[1];
        }
        localObject2 = localObject2[0].split("\\|", 2);
        i = Integer.parseInt(localObject2[0]);
        if (localObject2.length < 2)
        {
          localObject3 = localObject1;
          localObject2 = str1;
        }
        else
        {
          localObject2 = localObject2[1];
          localObject3 = localObject1;
        }
      }
      if (i != 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append("; charset=UTF-8");
        localObject1 = ResponseBody.create(MediaType.parse(((StringBuilder)localObject1).toString()), ((String)localObject3).getBytes(StandardCharsets.UTF_8));
        return new Response.Builder().protocol(Protocol.HTTP_1_1).request(paramChain.request()).code(i).message("MAYBE").addHeader("Content-Type", String.valueOf(((ResponseBody)localObject1).contentType())).addHeader("Content-Length", String.valueOf(((ResponseBody)localObject1).contentLength())).body((ResponseBody)localObject1).build();
      }
      throw new IOException("no network");
    }
  }
}
