package com.com.navapp.search;

import com.here.android.ui.common.GeoCoordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealers
{
  private static final String TAG = com.ktm.navapp.search.Dealers.class.getSimpleName();
  private static final Map<String, com.ktm.navapp.search.Waypoint> destinations = new HashMap();
  public final List<com.ktm.navapp.search.Dealers.Dealer> dealers = new ArrayList();
  
  public Dealers() {}
  
  public static Waypoint getDealer(GeoCoordinate paramGeoCoordinate)
  {
    return (Waypoint)destinations.get(getId(paramGeoCoordinate.getLatitude(), paramGeoCoordinate.getLongitude()));
  }
  
  static String getId(double paramDouble1, double paramDouble2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramDouble1);
    localStringBuilder.append(",");
    localStringBuilder.append(paramDouble2);
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static java.util.Collection loadDealers()
  {
    // Byte code:
    //   0: ldc 20
    //   2: monitorenter
    //   3: getstatic 28	com/com/navapp/search/Dealers:TAG	Ljava/lang/String;
    //   6: ldc 88
    //   8: invokestatic 94	com/com/navapp/logging/RealmLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   11: getstatic 35	com/com/navapp/search/Dealers:destinations	Ljava/util/Map;
    //   14: invokeinterface 97 1 0
    //   19: invokestatic 103	com/com/navapp/Globals:getContext	()Landroid/content/Context;
    //   22: invokevirtual 109	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   25: invokevirtual 115	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   28: ldc 117
    //   30: invokevirtual 123	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   33: astore 4
    //   35: new 125	com/google/gson/Gson
    //   38: dup
    //   39: invokespecial 126	com/google/gson/Gson:<init>	()V
    //   42: astore 5
    //   44: getstatic 132	java/nio/charset/StandardCharsets:UTF_8	Ljava/nio/charset/Charset;
    //   47: astore 6
    //   49: aload 5
    //   51: new 134	java/io/InputStreamReader
    //   54: dup
    //   55: aload 4
    //   57: aload 6
    //   59: invokespecial 137	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   62: ldc 20
    //   64: invokevirtual 141	com/google/gson/Gson:fromJson	(Ljava/io/Reader;Ljava/lang/Class;)Ljava/lang/Object;
    //   67: astore 5
    //   69: aload 5
    //   71: checkcast 2	com/com/navapp/search/Dealers
    //   74: astore 5
    //   76: aload 4
    //   78: invokevirtual 146	java/io/InputStream:close	()V
    //   81: aload 5
    //   83: ifnull +175 -> 258
    //   86: aload 5
    //   88: getfield 42	com/com/navapp/search/Dealers:dealers	Ljava/util/List;
    //   91: astore 4
    //   93: aload 4
    //   95: invokeinterface 152 1 0
    //   100: astore 4
    //   102: aload 4
    //   104: invokeinterface 158 1 0
    //   109: istore_3
    //   110: iload_3
    //   111: ifeq +147 -> 258
    //   114: aload 4
    //   116: invokeinterface 162 1 0
    //   121: astore 5
    //   123: aload 5
    //   125: checkcast 8	com/com/navapp/search/Dealers$Dealer
    //   128: astore 5
    //   130: aload 5
    //   132: getfield 165	com/com/navapp/search/Dealers$Dealer:location	Ljava/util/List;
    //   135: astore 6
    //   137: aload 6
    //   139: invokeinterface 169 1 0
    //   144: istore_2
    //   145: iload_2
    //   146: iconst_1
    //   147: if_icmple -45 -> 102
    //   150: aload 5
    //   152: getfield 165	com/com/navapp/search/Dealers$Dealer:location	Ljava/util/List;
    //   155: astore 6
    //   157: aload 6
    //   159: iconst_0
    //   160: invokeinterface 172 2 0
    //   165: astore 6
    //   167: aload 6
    //   169: checkcast 174	java/lang/Double
    //   172: astore 6
    //   174: aload 6
    //   176: invokevirtual 177	java/lang/Double:doubleValue	()D
    //   179: dstore_0
    //   180: aload 5
    //   182: getfield 165	com/com/navapp/search/Dealers$Dealer:location	Ljava/util/List;
    //   185: astore 6
    //   187: aload 6
    //   189: iconst_1
    //   190: invokeinterface 172 2 0
    //   195: astore 6
    //   197: aload 6
    //   199: checkcast 174	java/lang/Double
    //   202: astore 6
    //   204: new 65	com/com/navapp/search/Waypoint
    //   207: dup
    //   208: aload 5
    //   210: dload_0
    //   211: aload 6
    //   213: invokevirtual 177	java/lang/Double:doubleValue	()D
    //   216: invokespecial 180	com/com/navapp/search/Waypoint:<init>	(Lcom/com/navapp/search/Dealers$Dealer;DD)V
    //   219: astore 5
    //   221: getstatic 35	com/com/navapp/search/Dealers:destinations	Ljava/util/Map;
    //   224: astore 6
    //   226: aload 5
    //   228: getfield 183	com/com/navapp/search/Waypoint:date	Ljava/lang/String;
    //   231: astore 7
    //   233: aload 6
    //   235: aload 7
    //   237: aload 5
    //   239: invokeinterface 187 3 0
    //   244: pop
    //   245: goto -143 -> 102
    //   248: astore 4
    //   250: getstatic 28	com/com/navapp/search/Dealers:TAG	Ljava/lang/String;
    //   253: aload 4
    //   255: invokestatic 191	com/com/navapp/logging/RealmLog:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   258: getstatic 28	com/com/navapp/search/Dealers:TAG	Ljava/lang/String;
    //   261: ldc -63
    //   263: iconst_1
    //   264: anewarray 4	java/lang/Object
    //   267: dup
    //   268: iconst_0
    //   269: getstatic 35	com/com/navapp/search/Dealers:destinations	Ljava/util/Map;
    //   272: invokeinterface 194 1 0
    //   277: invokestatic 200	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   280: aastore
    //   281: invokestatic 203	com/com/navapp/logging/RealmLog:append	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   284: getstatic 35	com/com/navapp/search/Dealers:destinations	Ljava/util/Map;
    //   287: invokeinterface 206 1 0
    //   292: astore 4
    //   294: ldc 20
    //   296: monitorexit
    //   297: aload 4
    //   299: areturn
    //   300: astore 4
    //   302: ldc 20
    //   304: monitorexit
    //   305: aload 4
    //   307: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   179	32	0	d	double
    //   144	4	2	i	int
    //   109	2	3	bool	boolean
    //   33	82	4	localObject1	Object
    //   248	6	4	localException	Exception
    //   292	6	4	localCollection	java.util.Collection
    //   300	6	4	localThrowable	Throwable
    //   42	196	5	localObject2	Object
    //   47	187	6	localObject3	Object
    //   231	5	7	str	String
    // Exception table:
    //   from	to	target	type
    //   19	35	248	java/lang/Exception
    //   35	44	248	java/lang/Exception
    //   49	69	248	java/lang/Exception
    //   76	81	248	java/lang/Exception
    //   93	102	248	java/lang/Exception
    //   102	110	248	java/lang/Exception
    //   114	123	248	java/lang/Exception
    //   137	145	248	java/lang/Exception
    //   157	167	248	java/lang/Exception
    //   174	180	248	java/lang/Exception
    //   187	197	248	java/lang/Exception
    //   204	221	248	java/lang/Exception
    //   233	245	248	java/lang/Exception
    //   3	19	300	java/lang/Throwable
    //   19	35	300	java/lang/Throwable
    //   35	44	300	java/lang/Throwable
    //   44	49	300	java/lang/Throwable
    //   49	69	300	java/lang/Throwable
    //   69	76	300	java/lang/Throwable
    //   76	81	300	java/lang/Throwable
    //   86	93	300	java/lang/Throwable
    //   93	102	300	java/lang/Throwable
    //   102	110	300	java/lang/Throwable
    //   114	123	300	java/lang/Throwable
    //   123	137	300	java/lang/Throwable
    //   137	145	300	java/lang/Throwable
    //   150	157	300	java/lang/Throwable
    //   157	167	300	java/lang/Throwable
    //   167	174	300	java/lang/Throwable
    //   174	180	300	java/lang/Throwable
    //   180	187	300	java/lang/Throwable
    //   187	197	300	java/lang/Throwable
    //   197	204	300	java/lang/Throwable
    //   204	221	300	java/lang/Throwable
    //   233	245	300	java/lang/Throwable
    //   250	258	300	java/lang/Throwable
    //   258	294	300	java/lang/Throwable
  }
  
  public class Contact
  {
    public String city = "";
    public String country = "";
    public String mail = "";
    public String name = "";
    public String organization = "";
    public String phone = "";
    public String state = "";
    public String street = "";
    
    public Contact() {}
    
    public String getAddressMultiline()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(street);
      localStringBuilder.append("\n");
      localStringBuilder.append(state);
      localStringBuilder.append(" ");
      localStringBuilder.append(city);
      localStringBuilder.append("\n");
      localStringBuilder.append(country);
      return localStringBuilder.toString();
    }
  }
  
  public class Dealer
  {
    public final Dealers.Contact contact = new Dealers.Contact();
    public String generic_name = "";
    public final List<Double> location = new ArrayList();
    
    public Dealer() {}
  }
}
