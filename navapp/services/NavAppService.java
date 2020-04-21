package com.com.navapp.services;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.BaseBundle;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import androidx.core.package_8.NotificationCompat.Builder;
import com.com.navapp.Globals;
import com.com.navapp.analytics.Analytics;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.guidance.GuidanceManager;
import com.com.navapp.guidance.IconManager;
import com.com.navapp.guidance.LengthFormatter;
import com.com.navapp.logging.LogExt;
import com.com.navapp.logging.LogExt.Symbol;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.TripManager;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.NavigationManagerProxy;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.ApplicationContext;
import com.here.android.ui.common.GeoPosition;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.guidance.NavigationManager.AudioEvent;
import com.here.android.ui.guidance.NavigationManager.AudioFeedbackListener;
import com.here.android.ui.guidance.NavigationManager.AudioPlayer;
import com.here.android.ui.guidance.NavigationManager.Error;
import com.here.android.ui.guidance.NavigationManager.GpsSignalListener;
import com.here.android.ui.guidance.NavigationManager.ManeuverEventListener;
import com.here.android.ui.guidance.NavigationManager.MapUpdateMode;
import com.here.android.ui.guidance.NavigationManager.NavigationManagerEventListener;
import com.here.android.ui.guidance.NavigationManager.NavigationMode;
import com.here.android.ui.guidance.NavigationManager.NavigationState;
import com.here.android.ui.guidance.NavigationManager.NewInstructionEventListener;
import com.here.android.ui.guidance.NavigationManager.PositionListener;
import com.here.android.ui.guidance.NavigationManager.RerouteListener;
import com.here.android.ui.guidance.VoiceCatalog;
import com.here.android.ui.guidance.VoiceGuidanceOptions;
import com.here.android.ui.guidance.VoiceSkin;
import com.here.android.ui.mapping.MapRoute;
import com.here.android.ui.routing.Maneuver;
import com.here.android.ui.routing.Maneuver.Action;
import com.here.android.ui.routing.Route;
import com.here.android.ui.routing.Route.TrafficPenaltyMode;
import com.here.android.ui.routing.RouteResult;
import com.here.android.ui.routing.RoutingError;
import com.ktm.navapp.ui.mainscreen.MainScreen;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class NavAppService
  extends Service
{
  public static final String GUIDANCE_CMD = "GUIDANCE_COMMAND";
  protected static final int NOTIFICATION_ID = 5432;
  public static final String OFFROAD_ARG = "OFFROAD";
  public static final String SAVED_IS_GUIDANCE_CANCELLED = "isguidancecancelled";
  public static final String SAVED_REACHED_DESTINATION = "reacheddestination";
  protected static final String SAVED_ROUTE = "recentroute";
  public static final String SERVICE_NAME = "NavAppService";
  public static final String SIMULATE_ARG = "SIMULATE";
  public static final int START_GUIDANCE = 1209;
  public static final int STOP_GUIDANCE = 407;
  protected static final String TERMINATED = com.ktm.navapp.services.NavAppService.class.getSimpleName();
  final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
  protected NavigationManager.AudioFeedbackListener mAudioFeedbackListener = new NavigationManager.AudioFeedbackListener()
  {
    public void onAudioEnd()
    {
      RealmLog.i(NavAppService.TERMINATED, "onAudioEnd()");
      super.onAudioEnd();
      unFocusAudio();
    }
    
    public void onAudioStart()
    {
      RealmLog.i(NavAppService.TERMINATED, "onAudioStart()");
      super.onAudioStart();
      focusAudio();
    }
  };
  protected AudioManager mAudioManager;
  protected AudioManager.OnAudioFocusChangeListener mFocusMusicChangeListener = new AudioManager.OnAudioFocusChangeListener()
  {
    public void onAudioFocusChange(int paramAnonymousInt)
    {
      RealmLog.append(NavAppService.TERMINATED, "onAudioFocusChange(%d)", new Object[] { Integer.valueOf(paramAnonymousInt) });
    }
  };
  protected boolean mGpsRestored = false;
  protected final NavigationManager.GpsSignalListener mGpsSignalListener = new NavigationManager.GpsSignalListener()
  {
    public void onGpsLost()
    {
      RealmLog.i(NavAppService.TERMINATED, "onGpsLost()");
      LogExt.writeGpsLostEvent();
      super.onGpsLost();
      NavAppService.sendToDashboard(new NavAppService.11.1(this));
    }
    
    public void onGpsRestored()
    {
      RealmLog.i(NavAppService.TERMINATED, "onGpsRestored()");
      mGpsRestored = true;
      super.onGpsRestored();
      NavAppService.sendToDashboard(new NavAppService.11.2(this));
    }
  };
  protected int mInitialNotificationVolume;
  protected final NavigationManager.NewInstructionEventListener mInstructionListener = new NavigationManager.NewInstructionEventListener()
  {
    public void onNewInstructionEvent()
    {
      RealmLog.i(NavAppService.TERMINATED, "onNewInstructionEvent()");
      super.onNewInstructionEvent();
      Maneuver localManeuver = NavigationManagerProxy.getInstance().getNextManeuver();
      if (localManeuver != null)
      {
        long l = NavigationManagerProxy.getInstance().getNextManeuverDistance();
        RealmLog.append(NavAppService.TERMINATED, "onNewInstructionEvent() action=%s, turn=%s, icon=%s, distance=%d, road=%s, nextRoad=%s", new Object[] { localManeuver.getAction().name(), localManeuver.getTurn().name(), localManeuver.getIcon().name(), Long.valueOf(l), localManeuver.getRoadName(), localManeuver.getNextRoadName() });
        if (localManeuver.getAction() == Maneuver.Action.TERMINATED) {
          mReachedDestination = true;
        }
        LogExt.writeInstructionEvent(l, localManeuver);
        Analytics.getInstance().logManeuver(localManeuver);
        String[] arrayOfString = LengthFormatter.getFormattedExt(l);
        String str = IconManager.getIconExit(localManeuver);
        Object localObject = str;
        if (str.length() > 0)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(getString(2131558496));
          ((StringBuilder)localObject).append(" ");
          ((StringBuilder)localObject).append(str);
          localObject = ((StringBuilder)localObject).toString();
        }
        NavAppService.sendToDashboard(new NavAppService.8.1(this, localManeuver, (String)localObject, arrayOfString));
        updateDistance(NavigationEvent.EventType.INSTRUCTION_UPDATE, l);
      }
    }
  };
  protected boolean mIsOffroad = false;
  protected boolean mIsSimulated = false;
  protected final NavigationManager.ManeuverEventListener mManeuverEventListener = new NavigationManager.ManeuverEventListener()
  {
    public void onManeuverEvent()
    {
      RealmLog.i(NavAppService.TERMINATED, "onManeuverEvent()");
      super.onManeuverEvent();
      NavigationManagerProxy.getInstance().getNextManeuver();
    }
  };
  protected final NavigationManager.NavigationManagerEventListener mNavigationManagerEventListener = new NavigationManager.NavigationManagerEventListener()
  {
    public void onCountryInfo(String paramAnonymousString1, String paramAnonymousString2)
    {
      RealmLog.append(NavAppService.TERMINATED, "onCountryInfo() current=%s, next=%s", new Object[] { paramAnonymousString1, paramAnonymousString2 });
      super.onCountryInfo(paramAnonymousString1, paramAnonymousString2);
    }
    
    public void onEnded(NavigationManager.NavigationMode paramAnonymousNavigationMode)
    {
      RealmLog.i(NavAppService.TERMINATED, "onEnded()");
      super.onEnded(paramAnonymousNavigationMode);
      boolean bool = ((Boolean)Storage.load("isguidancecancelled", Boolean.class, Boolean.valueOf(false))).booleanValue();
      if ((mReachedDestination) && (!bool))
      {
        Storage.save("reacheddestination", Boolean.valueOf(true));
        Events.postEvent(new NavigationEvent(NavigationEvent.EventType.REACHED_DESTINATION));
      }
      else
      {
        Events.postEvent(new NavigationEvent(NavigationEvent.EventType.GUIDANCE_ENDED));
      }
      Analytics.getInstance().logNavigationEnd();
      paramAnonymousNavigationMode = NavAppService.this;
      paramAnonymousNavigationMode.stopGuidanceCommand(NavAppService.ServiceHandler.access$000(mServiceHandler));
    }
    
    public void onMapUpdateModeChanged(NavigationManager.MapUpdateMode paramAnonymousMapUpdateMode)
    {
      super.onMapUpdateModeChanged(paramAnonymousMapUpdateMode);
      RealmLog.append(NavAppService.TERMINATED, "onMapUpdateModeChanged() %s", new Object[] { String.valueOf(paramAnonymousMapUpdateMode) });
    }
    
    public void onNavigationModeChanged()
    {
      super.onNavigationModeChanged();
      NavigationManager.NavigationMode localNavigationMode = NavigationManagerProxy.getInstance().getNavigationMode();
      RealmLog.append(NavAppService.TERMINATED, "onNavigationModeChanged() %s", new Object[] { String.valueOf(localNavigationMode) });
      if ((localNavigationMode == NavigationManager.NavigationMode.SIMULATION) || (localNavigationMode == NavigationManager.NavigationMode.NAVIGATION))
      {
        TripManager.onGuidanceStart();
        Events.postEvent(new NavigationEvent(NavigationEvent.EventType.GUIDANCE_STARTED));
      }
    }
    
    public void onRunningStateChanged()
    {
      super.onRunningStateChanged();
      RealmLog.append(NavAppService.TERMINATED, "onRunningStateChanged() %s", new Object[] { String.valueOf(NavigationManagerProxy.getInstance().getRunningState()) });
    }
    
    public void onStopoverReached(int paramAnonymousInt)
    {
      RealmLog.append(NavAppService.TERMINATED, "onStopoverReached() %d", new Object[] { Integer.valueOf(paramAnonymousInt) });
      super.onStopoverReached(paramAnonymousInt);
      TripManager.onGuidanceWaypointReached(paramAnonymousInt);
      Events.postEvent(new NavigationEvent(NavigationEvent.EventType.REACHED_STOPOVER, 0L, 0L, null, paramAnonymousInt));
    }
  };
  protected final NavigationManager.PositionListener mPositionListener = new NavigationManager.PositionListener()
  {
    public void onPositionUpdated(GeoPosition paramAnonymousGeoPosition)
    {
      super.onPositionUpdated(paramAnonymousGeoPosition);
      LogExt.writePosition(paramAnonymousGeoPosition);
      Analytics.getInstance().setPosition(paramAnonymousGeoPosition);
      if (mGpsRestored)
      {
        mGpsRestored = false;
        LogExt.writeGpsRestoredEvent();
      }
      long l = NavigationManagerProxy.getInstance().getNextManeuverDistance();
      updateDistance(NavigationEvent.EventType.POSITION_UPDATE, l);
    }
  };
  protected boolean mReachedDestination;
  protected final NavigationManager.RerouteListener mRerouteListener = new NavigationManager.RerouteListener()
  {
    public void onRerouteBegin()
    {
      RealmLog.i(NavAppService.TERMINATED, "onRerouteBegin()");
      LogExt.writeEvent(LogExt.Symbol.PIN_YELLOW, "Reroute Begin", "");
      super.onRerouteBegin();
      Events.postEvent(new NavigationEvent(NavigationEvent.EventType.REROUTING));
      NavAppService.sendToDashboard(new NavAppService.12.1(this, Globals.getContext().getString(2131558515)));
    }
    
    public void onRerouteEnd(RouteResult paramAnonymousRouteResult, RoutingError paramAnonymousRoutingError)
    {
      if ((paramAnonymousRoutingError != RoutingError.NONE) && (paramAnonymousRoutingError != RoutingError.VIOLATES_OPTIONS))
      {
        RealmLog.i(NavAppService.TERMINATED, "onRerouteFailed()");
        LogExt.writeEvent(LogExt.Symbol.PIN_RED, "Reroute Failed", "");
        super.onRerouteEnd(paramAnonymousRouteResult, paramAnonymousRoutingError);
        return;
      }
      RealmLog.i(NavAppService.TERMINATED, "onRerouteEnd()");
      LogExt.writeEvent(LogExt.Symbol.PIN_GREEN, "Reroute End", "");
      paramAnonymousRouteResult.getViolatedOptions();
      super.onRerouteEnd(paramAnonymousRouteResult, paramAnonymousRoutingError);
      GuidanceManager.getInstance().setRoute(paramAnonymousRouteResult.getRoute());
      LogExt.writeRoute(paramAnonymousRouteResult.getRoute(), true);
    }
  };
  protected ServiceHandler mServiceHandler;
  protected Looper mServiceLooper;
  protected PowerManager.WakeLock mWakeLock;
  
  public NavAppService() {}
  
  protected static VoiceSkin getNoneVoiceSkin()
  {
    Object localObject = VoiceCatalog.getInstance().getLocalVoiceSkins().iterator();
    while (((Iterator)localObject).hasNext())
    {
      VoiceSkin localVoiceSkin = (VoiceSkin)((Iterator)localObject).next();
      if (localVoiceSkin.getLanguage().toLowerCase().contains("none")) {
        return localVoiceSkin;
      }
    }
    localObject = VoiceCatalog.getInstance().getLocalVoiceSkins();
    if (((List)localObject).size() > 0) {
      return (VoiceSkin)((List)localObject).get(0);
    }
    return null;
  }
  
  public static boolean isRunning(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = paramContext.getRunningServices(Integer.MAX_VALUE).iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
        if (com.ktm.navapp.services.NavAppService.class.getName().equals(service.getClassName()))
        {
          RealmLog.i(TERMINATED, "isRunning(): true");
          return true;
        }
      }
    }
    RealmLog.i(TERMINATED, "isRunning(): false");
    return false;
  }
  
  private void runAsForeground()
  {
    RealmLog.i(TERMINATED, "runAsForeground()");
    initNotificationChannel(this);
    Object localObject = new Intent(this, MainScreen.class);
    ((Intent)localObject).addFlags(268435456);
    localObject = PendingIntent.getActivity(this, 0, (Intent)localObject, 134217728);
    startForeground(5432, new NotificationCompat.Builder(this, "navapp-guidance").setSmallIcon(2131165922).setContentTitle(getString(2131558437)).setContentIntent((PendingIntent)localObject).build());
  }
  
  static void sendToDashboard(Runnable paramRunnable)
  {
    paramRunnable.run();
  }
  
  protected static void turnOffSpeedWarnings()
  {
    NavigationManagerProxy.getInstance().setSpeedWarningEnabled(false);
    NavigationManagerProxy.getInstance().setEnabledAudioEvents(EnumSet.of(NavigationManager.AudioEvent.MANEUVER, NavigationManager.AudioEvent.attachment, NavigationManager.AudioEvent.VIBRATION));
  }
  
  protected void configAudioFocus()
  {
    try
    {
      try
      {
        Object localObject = Globals.getContext().getSystemService("audio");
        mAudioManager = ((AudioManager)localObject);
        if (mAudioManager != null)
        {
          localObject = mAudioManager;
          int i = ((AudioManager)localObject).getStreamVolume(5);
          mInitialNotificationVolume = i;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected void focusAudio()
  {
    try
    {
      RealmLog.i(TERMINATED, "focusAudio()");
      localAudioManager = mAudioManager;
      if (localAudioManager == null) {
        return;
      }
      localAudioManager = mAudioManager;
    }
    catch (Throwable localThrowable)
    {
      AudioManager localAudioManager;
      AudioManager.OnAudioFocusChangeListener localOnAudioFocusChangeListener;
      label61:
      throw localThrowable;
    }
    try
    {
      localAudioManager.setStreamVolume(5, 0, 0);
      localAudioManager = mAudioManager;
      localOnAudioFocusChangeListener = mFocusMusicChangeListener;
      localAudioManager.requestAudioFocus(localOnAudioFocusChangeListener, NavigationManagerProxy.getInstance().getAudioPlayer().getStreamId(), 3);
    }
    catch (Exception localException)
    {
      break label61;
    }
  }
  
  public void initNotificationChannel(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramContext = (NotificationManager)paramContext.getSystemService("notification");
      if (paramContext != null) {
        paramContext.createNotificationChannel(new NotificationChannel("navapp-guidance", getString(2131558437), 2));
      }
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    Object localObject = new HandlerThread("NavAppService", 10);
    ((Thread)localObject).start();
    mServiceLooper = ((HandlerThread)localObject).getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);
    localObject = (PowerManager)getSystemService("power");
    if (localObject != null) {
      mWakeLock = ((PowerManager)localObject).newWakeLock(1, "myride:NavServiceWakeLock");
    }
  }
  
  public void onDestroy()
  {
    restoreNotificationVolume();
    RealmLog.append(TERMINATED, "onDestroy: %s", new Object[] { "NavAppService" });
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    RealmLog.append(TERMINATED, "service starting: %s (startId: %d)", new Object[] { "NavAppService", Integer.valueOf(paramInt2) });
    mServiceHandler.obtainMessage();
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getExtras();
      if (paramIntent != null)
      {
        start(paramIntent, paramInt2);
        return 1;
      }
      stop(paramInt2);
      return 1;
    }
    RealmLog.append(TERMINATED, "service REstarting: %s (startId: %d)", new Object[] { "NavAppService", Integer.valueOf(paramInt2) });
    if (!MapEngine.isInitialized()) {
      MapEngine.getInstance().init(new ApplicationContext(Globals.getContext()), new NavAppService.1(this, paramInt2));
    }
    return 1;
  }
  
  /* Error */
  public void restoreNotificationVolume()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 96	com/com/navapp/services/NavAppService:TERMINATED	Ljava/lang/String;
    //   5: ldc_w 502
    //   8: invokestatic 236	com/com/navapp/logging/RealmLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   11: aload_0
    //   12: getfield 355	com/com/navapp/services/NavAppService:mAudioManager	Landroid/media/AudioManager;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull +20 -> 37
    //   20: aload_0
    //   21: getfield 355	com/com/navapp/services/NavAppService:mAudioManager	Landroid/media/AudioManager;
    //   24: astore_2
    //   25: aload_0
    //   26: getfield 361	com/com/navapp/services/NavAppService:mInitialNotificationVolume	I
    //   29: istore_1
    //   30: aload_2
    //   31: iconst_5
    //   32: iload_1
    //   33: iconst_0
    //   34: invokevirtual 368	android/media/AudioManager:setStreamVolume	(III)V
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: astore_2
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_2
    //   44: athrow
    //   45: astore_2
    //   46: goto -9 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	NavAppService
    //   29	4	1	i	int
    //   15	16	2	localAudioManager	AudioManager
    //   40	4	2	localThrowable	Throwable
    //   45	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	16	40	java/lang/Throwable
    //   30	37	40	java/lang/Throwable
    //   30	37	45	java/lang/Exception
  }
  
  protected void start(Bundle paramBundle, int paramInt)
  {
    Message localMessage = mServiceHandler.obtainMessage();
    if (paramBundle != null)
    {
      mIsSimulated = paramBundle.getBoolean("SIMULATE", false);
      mIsOffroad = paramBundle.getBoolean("OFFROAD", false);
      if (paramBundle.containsKey("GUIDANCE_COMMAND"))
      {
        i = paramBundle.getInt("GUIDANCE_COMMAND");
        break label58;
      }
    }
    int i = 1209;
    label58:
    arg1 = paramInt;
    arg2 = i;
    mServiceHandler.sendMessage(localMessage);
    runAsForeground();
    paramBundle = mWakeLock;
    if (paramBundle != null) {
      paramBundle.acquire();
    }
  }
  
  protected NavigationManager.Error startGuidance(MapRoute paramMapRoute, boolean paramBoolean1, boolean paramBoolean2)
  {
    RealmLog.append(TERMINATED, "configureGuidanceMap() isSimulated=%b, isOffroad=%b", new Object[] { Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) });
    VoiceSkin localVoiceSkin = VoiceManager.getInstance().getSelectedLanguage();
    if (localVoiceSkin != null)
    {
      RealmLog.append(TERMINATED, "configureGuidanceMap(): selected voiceSkin: %s", new Object[] { localVoiceSkin.getLanguage() });
      NavigationManagerProxy.getInstance().getVoiceGuidanceOptions().setVoiceSkin(localVoiceSkin);
      configAudioFocus();
      NavigationManagerProxy.getInstance().addAudioFeedbackListener(new WeakReference(mAudioFeedbackListener));
    }
    else
    {
      RealmLog.i(TERMINATED, "configureGuidanceMap(): selected voiceSkin: null");
      turnOffVoiceSkin();
    }
    NavigationManagerProxy.getInstance().addGpsSignalListener(new WeakReference(mGpsSignalListener));
    NavigationManagerProxy.getInstance().addPositionListener(new WeakReference(mPositionListener));
    NavigationManagerProxy.getInstance().addNavigationManagerEventListener(new WeakReference(mNavigationManagerEventListener));
    if (!paramBoolean2)
    {
      NavigationManagerProxy.getInstance().addNewInstructionEventListener(new WeakReference(mInstructionListener));
      NavigationManagerProxy.getInstance().addManeuverEventListener(new WeakReference(mManeuverEventListener));
      NavigationManagerProxy.getInstance().addRerouteListener(new WeakReference(mRerouteListener));
      LogExt.writeRoute(paramMapRoute.getRoute(), false);
      LogExt.writeInstructionEvent(0L, null);
      Analytics.getInstance().logNavigationStart();
    }
    if (paramBoolean2) {
      return NavigationManagerProxy.getInstance().startTracking();
    }
    if (paramBoolean1) {
      return NavigationManagerProxy.getInstance().simulate(paramMapRoute.getRoute(), 30L);
    }
    return NavigationManagerProxy.getInstance().startNavigation(paramMapRoute.getRoute());
  }
  
  protected void startGuidanceCommand(int paramInt)
  {
    RealmLog.append(TERMINATED, "startGuidanceCommand()");
    ServiceHandler.access$002(mServiceHandler, paramInt);
    Object localObject = RoutingManager.getInstance().getSelectedRoute();
    if ((localObject == null) || (((MapRoute)localObject).getRoute() == null))
    {
      RealmLog.e(TERMINATED, "No valid route provided. Stopping the service!");
      stop();
    }
    if (localObject != null) {
      Route.serializeAsync(((MapRoute)localObject).getRoute(), new NavAppService.2(this));
    }
    localObject = startGuidance((MapRoute)localObject, mIsSimulated, mIsOffroad);
    RealmLog.logError(TERMINATED, "configureGuidanceMap() result: %s", new Object[] { ((Enum)localObject).name() });
    if (localObject != NavigationManager.Error.NONE)
    {
      localObject = TERMINATED;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(com.here.android.mpa.guidance.NavigationManager.class.getSimpleName());
      localStringBuilder.append(" error!\n Stopping the service!");
      RealmLog.e((String)localObject, localStringBuilder.toString());
      stop();
    }
    sendToDashboard(new NavAppService.3(this));
  }
  
  protected void stop()
  {
    RealmLog.append(TERMINATED, "Service: %s stopped", new Object[] { "NavAppService" });
    stop(mServiceHandler.mLastStartedId);
  }
  
  protected void stop(int paramInt)
  {
    stopSelf(paramInt);
    PowerManager.WakeLock localWakeLock = mWakeLock;
    if (localWakeLock != null) {
      localWakeLock.release();
    }
  }
  
  protected void stopGuidanceCommand(int paramInt)
  {
    stopGuidanceCommand(paramInt, true);
  }
  
  protected void stopGuidanceCommand(int paramInt, boolean paramBoolean)
  {
    RealmLog.append(TERMINATED, "stopGuidanceCommand()");
    if (paramInt < 0) {
      return;
    }
    if (NavigationManagerProxy.getInstance().getRunningState() != NavigationManager.NavigationState.IDLE) {
      NavigationManagerProxy.getInstance().stop();
    }
    sendToDashboard(new NavAppService.4(this));
    if (paramBoolean) {
      stop();
    }
    ServiceHandler.access$002(mServiceHandler, -1);
  }
  
  protected void turnOffVoiceSkin()
  {
    VoiceSkin localVoiceSkin = getNoneVoiceSkin();
    if (localVoiceSkin != null)
    {
      NavigationManagerProxy.getInstance().getVoiceGuidanceOptions().setVoiceSkin(localVoiceSkin);
      NavigationManagerProxy.getInstance().removeAudioFeedbackListener(mAudioFeedbackListener);
    }
  }
  
  protected void unFocusAudio()
  {
    try
    {
      RealmLog.i(TERMINATED, "unFocusAudio()");
      localAudioManager = mAudioManager;
      if (localAudioManager == null) {
        return;
      }
      localAudioManager = mAudioManager;
      i = mInitialNotificationVolume;
    }
    catch (Throwable localThrowable)
    {
      AudioManager localAudioManager;
      int i;
      AudioManager.OnAudioFocusChangeListener localOnAudioFocusChangeListener;
      label56:
      throw localThrowable;
    }
    try
    {
      localAudioManager.setStreamVolume(5, i, 0);
      localAudioManager = mAudioManager;
      localOnAudioFocusChangeListener = mFocusMusicChangeListener;
      localAudioManager.abandonAudioFocus(localOnAudioFocusChangeListener);
    }
    catch (Exception localException)
    {
      break label56;
    }
  }
  
  protected void updateDistance(NavigationEvent.EventType paramEventType, long paramLong)
  {
    long l = NavigationManagerProxy.getInstance().getDestinationDistance();
    if ((paramLong < 2147483647L) && (l >= 0L))
    {
      Date localDate = NavigationManagerProxy.getInstance().getEta(true, Route.TrafficPenaltyMode.DISABLED);
      RealmLog.d(TERMINATED, "updateDistance() distMan=%d, distDest=%d", new Object[] { Long.valueOf(paramLong), Long.valueOf(l) });
      sendToDashboard(new NavAppService.6(this, LengthFormatter.getFormattedExt(paramLong), l, localDate));
      Events.postEvent(new NavigationEvent(paramEventType, paramLong, l, localDate, 0));
    }
  }
  
  public final class ServiceHandler
    extends Handler
  {
    private int mLastStartedId = -1;
    
    public ServiceHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = arg2;
      if (i == 407)
      {
        stopGuidanceCommand(mLastStartedId);
        return;
      }
      if (i == 1209)
      {
        if (NavigationManagerProxy.getInstance().getRunningState() != NavigationManager.NavigationState.IDLE) {
          stopGuidanceCommand(mLastStartedId, false);
        }
        startGuidanceCommand(arg1);
      }
    }
  }
}
