package com.com.navapp.ui.mainscreen;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.PointF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.package_8.ActivityCompat;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import com.com.navapp.Globals;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate.Availability;
import com.com.navapp.downloading.offlinemaps.MapPackageInfo;
import com.com.navapp.downloading.offlinemaps.MapStorageLocation;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.logging.FlicHandler;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.offroad.OffroadFragment;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.TripManager;
import com.com.navapp.search.Waypoint;
import com.com.navapp.services.NavAppService;
import com.com.navapp.ui.base.NavAppBaseFragmentActivity;
import com.com.navapp.ui.guidance.GuidanceFragment;
import com.com.navapp.ui.routing.RoutePreviewFragment;
import com.com.navapp.ui.search.DealerDetailFragment;
import com.com.navapp.ui.search.SearchFragment;
import com.com.navapp.ui.search.SearchFragment.SearchCallback;
import com.com.navapp.ui.settings.AboutFragment;
import com.com.navapp.ui.settings.GeneralSetupFragment;
import com.com.navapp.ui.settings.HamburgerFragment;
import com.com.navapp.ui.settings.LetsRideFragment;
import com.com.navapp.ui.settings.NotEnoughMemorySettingsFragment;
import com.com.navapp.ui.settings.TestSettingsFragment;
import com.com.navapp.ui.settings.downloadmaps.InstalledMapsSettingsFragment;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.ui.trip.LoadTripFragment;
import com.com.navapp.ui.trip.SaveTripFragment;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.NavigationManagerProxy;
import com.com.navapp.utilities.Storage;
import com.com.navapp.utilities.Utilities;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.common.PositioningManager;
import com.here.android.ui.common.PositioningManager.LocationMethod;
import com.here.android.ui.common.Version;
import com.here.android.ui.guidance.NavigationManager;
import com.here.android.ui.mapping.AndroidXMapFragment;
import com.here.android.ui.mapping.MapGesture;
import com.here.android.ui.mapping.MapGesture.OnGestureListener;
import com.here.android.ui.mapping.class_3;
import com.ktm.navapp.ui.welcome.WelcomeScreenSelectBike;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainScreen
  extends NavAppBaseFragmentActivity
  implements MapGesture.OnGestureListener, SearchFragment.SearchCallback
{
  public static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";
  private static final String LOG_TAG = com.ktm.navapp.ui.mainscreen.MainScreen.class.getSimpleName();
  public static final String NO_ROUTE_OPTIONS_TAG = "SEARCH_FRAGMENT_TAG";
  private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
  public static final int REQUEST_CODE_ENABLE_BLUETOOTH_AND_STAY = 2410;
  public static final int REQUEST_CODE_ENABLE_BLUETOOTH_GO_TO_LIST = 2001;
  public static final int REQUEST_CODE_ENABLE_NOTIFICATION = 3001;
  public static final int REQUEST_CODE_PERMISSIONS_SETTINGS = 4001;
  private static final String[] REQUIRED_SDK_PERMISSIONS = { "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE" };
  public static final String ROUTE_PREVIEW_FRAGMENT_TAG = "ROUTE_PREVIEW_FRAGMENT_TAG";
  private static final String SAVED_REACHED_DESTINATION = "reacheddestination";
  public static final String SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG";
  private static final String TAG = com.ktm.navapp.ui.mainscreen.MainScreen.class.getSimpleName();
  private AboutFragment mAboutFragment;
  private AndroidXMapFragment mAndroidXMapFragment = null;
  private AudioManager mAudioManager;
  private GeneralSetupFragment mGeneralSetupFragment;
  private HamburgerFragment mHamburgerFragment;
  final Runnable mHideNavigateButtonsRunnable = new Runnable()
  {
    public void run()
    {
      if (mMapControlsFragment != null) {
        mMapControlsFragment.hideNavigateButtons();
      }
    }
  };
  private boolean mInitFragmentOnResume = false;
  private boolean mIsResumed = false;
  private MapControlsFragment mMapControlsFragment;
  private NotEnoughMemorySettingsFragment mNotEnoughMemorySettingsFragment;
  private OptionsManager mOptionsManager;
  private PositioningManager mPositionManager;
  private RoutePreviewFragment mRoutePreviewFragment;
  
  public MainScreen() {}
  
  private void checkNotificationPermission()
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    if (localNotificationManager != null)
    {
      if (!localNotificationManager.isNotificationPolicyAccessGranted())
      {
        startActivityForResult(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"), 3001);
        return;
      }
      checkPermissions();
    }
  }
  
  private void finalizeInitialization()
  {
    RealmLog.i(TAG, "finalizeInitialization()");
    if (mAndroidXMapFragment.getMapGesture() != null) {
      mAndroidXMapFragment.getMapGesture().addOnGestureListener(this, 0, false);
    }
    if (mMapControlsFragment == null) {
      mMapControlsFragment = new MapControlsFragment();
    }
    getSupportFragmentManager().beginTransaction().replace(2131231033, mMapControlsFragment).commitNow();
    RealmLog.i(TAG, "finalizeInitialization(): OfflineMapsManager.initialize()");
    MapDataUpdate.outdateAvailability();
    OfflineMapsManager.getInstance().initialize(new MainScreen.3(this));
  }
  
  private void finalizeInitialization2()
  {
    RealmLog.i(TAG, "finalizeInitialization2()");
    mPositionManager = PositioningManager.getInstance();
    mPositionManager.addListener(new WeakReference(MapManager.getInstance()));
    mPositionManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
    VoiceManager.getInstance();
    boolean bool = startGuidanceIfServiceIsRunning();
    if (!Utilities.isConnectedOrConnecting())
    {
      Utilities.toastLong(2131558680);
    }
    else if (!bool)
    {
      Object localObject = MapStorageLocation.getDownloadList();
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          MapPackageInfo localMapPackageInfo = (MapPackageInfo)((Iterator)localObject).next();
          OfflineMapsManager.getInstance().addToInstall(Integer.valueOf(subtitle));
        }
        MapStorageLocation.clearDownloadList();
        getSupportFragmentManager().beginTransaction().replace(2131231033, new InstalledMapsSettingsFragment()).addToBackStack(null).commit();
      }
      else if (MapDataUpdate.getAvailability() == MapDataUpdate.Availability.YES)
      {
        getSupportFragmentManager().beginTransaction().replace(2131231033, new MapUpdateFragment()).addToBackStack(null).commitAllowingStateLoss();
      }
    }
    Events.postOnMainThread(new MainScreen.4(this));
  }
  
  private String[] getRequiredPermissions()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(REQUIRED_SDK_PERMISSIONS));
    if (Build.VERSION.SDK_INT > 28) {
      localArrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
  
  private void initialize()
  {
    RealmLog.i(TAG, "initialize()");
    try
    {
      Object localObject = OptionsManager.getInstance();
      mOptionsManager = ((OptionsManager)localObject);
      localObject = mOptionsManager;
      ((OptionsManager)localObject).initialize();
      localObject = mAndroidXMapFragment;
      ((AndroidXMapFragment)localObject).init(new MainScreen.2(this));
      return;
    }
    catch (Exception localException)
    {
      RealmLog.v(TAG, "initialize()", localException);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Init1 failed: ");
      localStringBuilder.append(String.valueOf(localException));
      Utilities.toastLong(localStringBuilder.toString());
    }
  }
  
  private void initializeMap()
  {
    RealmLog.i(TAG, "initializeMap()");
    MapManager.getInstance().setMap(mAndroidXMapFragment.getMap(), mAndroidXMapFragment.getPositionIndicator());
    TripManager.resetOptions();
    RoutingManager.getInstance().initialize();
    mOptionsManager.navigateWithOptions();
  }
  
  private boolean startGuidanceIfServiceIsRunning()
  {
    if (NavAppService.isRunning(getApplicationContext()))
    {
      RealmLog.i(TAG, "SERVICE RUNNING");
      setGuidanceView(true);
      return true;
    }
    RealmLog.i(TAG, "SERVICE NOT RUNNING");
    NavigationManagerProxy.getInstance().stop();
    return false;
  }
  
  protected void checkPermissions()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = getRequiredPermissions();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (ContextCompat.checkSelfPermission(this, str) != 0) {
        localArrayList.add(str);
      }
      i += 1;
    }
    if (!localArrayList.isEmpty())
    {
      ActivityCompat.requestPermissions(this, (String[])localArrayList.toArray(new String[0]), 1);
      return;
    }
    initialize();
  }
  
  public void configCopyrightIconPosition(ExtendedLogoPosition paramExtendedLogoPosition)
  {
    Events.postDelayed(new MainScreen.5(this, paramExtendedLogoPosition), 100L);
  }
  
  public void disableOnMapGestures()
  {
    if (mAndroidXMapFragment.getMapGesture() != null) {
      mAndroidXMapFragment.getMapGesture().setAllGesturesEnabled(false);
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (mAudioManager == null) {}
    try
    {
      Object localObject = Globals.getContext().getSystemService("audio");
      mAudioManager = ((AudioManager)localObject);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          int i = paramKeyEvent.getStreamVolume(3);
          paramKeyEvent = mAudioManager;
          int j = paramKeyEvent.getStreamMaxVolume(3);
          k = j / 10;
          k = Math.max(k, 1);
          i = Math.min(i + k, j);
          paramKeyEvent = mAudioManager;
          paramKeyEvent.setStreamVolume(3, i, 1);
          paramKeyEvent = mAudioManager;
          paramKeyEvent.getStreamVolume(5);
          return true;
        }
        catch (Exception paramKeyEvent) {}
        localException = localException;
      }
    }
    if (mAudioManager == null) {
      return false;
    }
    i = paramKeyEvent.getAction();
    j = paramKeyEvent.getKeyCode();
    if (j != 24)
    {
      if (j != 25) {
        return super.dispatchKeyEvent(paramKeyEvent);
      }
      if (i == 0) {
        paramKeyEvent = mAudioManager;
      }
    }
    else
    {
      try
      {
        i = paramKeyEvent.getStreamVolume(3);
        paramKeyEvent = mAudioManager;
        j = paramKeyEvent.getStreamMaxVolume(3);
        j /= 10;
        j = Math.max(j, 1);
        i = Math.max(i - j, 0);
        paramKeyEvent = mAudioManager;
        paramKeyEvent.setStreamVolume(3, i, 1);
        paramKeyEvent = mAudioManager;
        paramKeyEvent.getStreamVolume(5);
        return true;
      }
      catch (Exception paramKeyEvent)
      {
        int k;
        return true;
      }
      if (i == 0) {
        paramKeyEvent = mAudioManager;
      }
    }
    return true;
  }
  
  public void enableOnMapGestures()
  {
    if (mAndroidXMapFragment.getMapGesture() != null)
    {
      mAndroidXMapFragment.getMapGesture().setAllGesturesEnabled(true);
      mAndroidXMapFragment.getMapGesture().setRotateEnabled(false);
    }
  }
  
  Fragment getActiveFragment()
  {
    return getSupportFragmentManager().findFragmentById(2131231033);
  }
  
  public void mapControlsShowButtons()
  {
    MapControlsFragment localMapControlsFragment = mMapControlsFragment;
    if (localMapControlsFragment != null) {
      localMapControlsFragment.showNavigateButtons();
    }
    Events.cancel(mHideNavigateButtonsRunnable);
    Events.postDelayed(mHideNavigateButtonsRunnable, 5000L);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2001)
    {
      if (paramInt2 == -1) {
        startActivity(new Intent(this, WelcomeScreenSelectBike.class));
      }
    }
    else if (paramInt1 == 2410)
    {
      if (paramInt2 == -1) {
        Events.doInBackground(new MainScreen.1(this));
      }
    }
    else if (paramInt1 == 3001)
    {
      if (paramInt2 == -1) {
        checkPermissions();
      } else {
        checkPermissions();
      }
    }
    else if (paramInt1 == 4001) {
      checkPermissions();
    } else {
      FlicHandler.handleGrabButtonResult(paramInt1, paramInt2, paramIntent);
    }
    if ((paramInt2 != -1) && (paramInt1 != 3001)) {
      RealmLog.i(LOG_TAG, "Failed to enable bluetooth!");
    }
  }
  
  public void onBackPressed()
  {
    Fragment localFragment = getActiveFragment();
    if ((!(localFragment instanceof OnBackPressedHandler)) || (!((OnBackPressedHandler)localFragment).onBackPressed())) {
      super.onBackPressed();
    }
  }
  
  public void onBackPressed(View paramView)
  {
    onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    String str = TAG;
    boolean bool2 = MapEngine.isInitialized();
    boolean bool1;
    if (paramBundle != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    RealmLog.append(str, "onCreate(): MapEngine inited: %b, instanceState: %b", new Object[] { Boolean.valueOf(bool2), Boolean.valueOf(bool1) });
    RealmLog.logError(TAG, "HERE-SDK-VERSION: %s", new Object[] { Version.getSdkVersion() });
    if (!MapEngine.isInitialized())
    {
      paramBundle = null;
      mInitFragmentOnResume = false;
    }
    super.onCreate(paramBundle);
    setContentView(2131427362);
    mAndroidXMapFragment = ((AndroidXMapFragment)getSupportFragmentManager().findFragmentById(2131231032));
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.DONE.name());
    if (Build.VERSION.SDK_INT >= 24)
    {
      checkNotificationPermission();
      return;
    }
    checkPermissions();
  }
  
  public void onDestroy()
  {
    RealmLog.i(TAG, "onDestroy()");
    PositioningManager localPositioningManager = mPositionManager;
    if (localPositioningManager != null) {
      localPositioningManager.removeListener(MapManager.getInstance());
    }
    super.onDestroy();
  }
  
  public boolean onDoubleTapEvent(PointF paramPointF)
  {
    Events.postOnMainThread(new MainScreen.11(this));
    onMapTouched();
    return true;
  }
  
  public void onGuidanceFinished()
  {
    Storage.save("reacheddestination", Boolean.valueOf(false));
    Events.postDelayed(new MainScreen.6(this), 100L);
    getSupportFragmentManager().executePendingTransactions();
    getSupportFragmentManager().popBackStackImmediate(null, 1);
  }
  
  public boolean onLongPressEvent(PointF paramPointF)
  {
    Events.postEvent(MapManager.getInstance().getMap().pixelToGeo(paramPointF));
    return true;
  }
  
  public void onLongPressRelease() {}
  
  public boolean onMapObjectsSelected(List paramList)
  {
    Events.postOnMainThread(new MainScreen.9(this, paramList));
    return true;
  }
  
  public void onMapTouched()
  {
    Events.postOnMainThread(new MainScreen.7(this));
  }
  
  public void onMultiFingerManipulationEnd() {}
  
  public void onMultiFingerManipulationStart()
  {
    onMapTouched();
  }
  
  public void onPanEnd() {}
  
  public void onPanStart()
  {
    onMapTouched();
  }
  
  public void onPause()
  {
    PositioningManager localPositioningManager = mPositionManager;
    if (localPositioningManager != null) {
      localPositioningManager.stop();
    }
    Events.unregister(this);
    mIsResumed = false;
    super.onPause();
  }
  
  public void onPinchLocked() {}
  
  public boolean onPinchZoomEvent(float paramFloat, PointF paramPointF)
  {
    Events.postOnMainThread(new MainScreen.12(this));
    onMapTouched();
    return true;
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramInt != 1) {
      return;
    }
    paramInt = paramArrayOfString.length - 1;
    while (paramInt >= 0)
    {
      if (paramArrayOfInt[paramInt] != 0)
      {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, paramArrayOfString[paramInt]))
        {
          paramArrayOfInt = new StringBuilder();
          paramArrayOfInt.append("Required permission '");
          paramArrayOfInt.append(paramArrayOfString[paramInt]);
          paramArrayOfInt.append("' not granted.");
          Utilities.toastLong(paramArrayOfInt.toString());
          checkPermissions();
          return;
        }
        paramArrayOfString = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        paramArrayOfString.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(paramArrayOfString, 4001);
        return;
      }
      paramInt -= 1;
    }
    initialize();
  }
  
  public void onResume()
  {
    super.onResume();
    mIsResumed = true;
    Events.register(this);
    if (mInitFragmentOnResume)
    {
      RealmLog.i(TAG, "onResume(): mInitFragmentOnResume");
      mInitFragmentOnResume = false;
      finalizeInitialization();
      return;
    }
    PositioningManager localPositioningManager = mPositionManager;
    if (localPositioningManager != null) {
      localPositioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
    }
  }
  
  public boolean onRotateEvent(float paramFloat)
  {
    return false;
  }
  
  public void onRotateLocked() {}
  
  public void onSearchCancelled()
  {
    getSupportFragmentManager().popBackStack();
  }
  
  public void onSearchResult(Waypoint paramWaypoint)
  {
    setRoutePreviewView(false);
  }
  
  public boolean onTapEvent(PointF paramPointF)
  {
    onMapTouched();
    Events.postOnMainThread(new MainScreen.10(this));
    return false;
  }
  
  public boolean onTiltEvent(float paramFloat)
  {
    return false;
  }
  
  public boolean onTwoFingerTapEvent(PointF paramPointF)
  {
    onMapTouched();
    return true;
  }
  
  public void setAboutSettingsView()
  {
    if (mAboutFragment == null) {
      mAboutFragment = new AboutFragment();
    }
    getSupportFragmentManager().beginTransaction().replace(2131231033, mAboutFragment).addToBackStack(null).commit();
  }
  
  public void setAdvancedSettingsView()
  {
    if (mGeneralSetupFragment == null) {
      mGeneralSetupFragment = new GeneralSetupFragment();
    }
    getSupportFragmentManager().beginTransaction().replace(2131231033, mGeneralSetupFragment).addToBackStack(null).commit();
  }
  
  public void setDealerSelectedView(Waypoint paramWaypoint)
  {
    getSupportFragmentManager().beginTransaction().replace(2131231033, DealerDetailFragment.newInstance(paramWaypoint)).addToBackStack(null).commit();
  }
  
  public void setGuidanceView(boolean paramBoolean)
  {
    getSupportFragmentManager().beginTransaction().setCustomAnimations(2130771996, 2130771997, 2130771996, 2130771997).replace(2131231033, GuidanceFragment.newInstance(paramBoolean)).addToBackStack(null).commitAllowingStateLoss();
  }
  
  public void setLetsRideMenuView()
  {
    getSupportFragmentManager().beginTransaction().replace(2131231033, new LetsRideFragment()).addToBackStack(null).commit();
  }
  
  public void setLoadTripView()
  {
    getSupportFragmentManager().beginTransaction().replace(2131231033, new LoadTripFragment()).addToBackStack(null).commit();
  }
  
  public void setNotEnoughMemoryErrorView()
  {
    if (mNotEnoughMemorySettingsFragment == null) {
      mNotEnoughMemorySettingsFragment = new NotEnoughMemorySettingsFragment();
    }
    getSupportFragmentManager().beginTransaction().add(2131231033, mNotEnoughMemorySettingsFragment).addToBackStack(null).commitAllowingStateLoss();
  }
  
  public void setOffroadView()
  {
    getSupportFragmentManager().beginTransaction().setCustomAnimations(2130771996, 2130771997, 2130771996, 2130771997).replace(2131231033, OffroadFragment.newInstance()).addToBackStack(null).commitAllowingStateLoss();
  }
  
  public void setRoutePreviewView(boolean paramBoolean)
  {
    if (getSupportFragmentManager().isStateSaved()) {
      return;
    }
    if (mRoutePreviewFragment == null) {
      mRoutePreviewFragment = new RoutePreviewFragment();
    }
    MapManager.getInstance().showPreviewRouteOnMap(paramBoolean ^ true);
    mRoutePreviewFragment.openSettings = paramBoolean;
    getSupportFragmentManager().beginTransaction().replace(2131231033, mRoutePreviewFragment, "ROUTE_PREVIEW_FRAGMENT_TAG").addToBackStack("ROUTE_PREVIEW_FRAGMENT_TAG").commit();
  }
  
  public void setSaveTripView()
  {
    getSupportFragmentManager().beginTransaction().replace(2131231033, new SaveTripFragment()).addToBackStack(null).commit();
  }
  
  public void setSearchView(boolean paramBoolean, int paramInt)
  {
    Object localObject = MapManager.getInstance().getCenter();
    localObject = SearchFragment.newInstance(null, ((GeoCoordinate)localObject).getLatitude(), ((GeoCoordinate)localObject).getLongitude(), paramBoolean, paramInt);
    getSupportFragmentManager().beginTransaction().setCustomAnimations(2130771996, 2130771997, 2130771996, 2130771997).replace(2131231033, (Fragment)localObject, "SEARCH_FRAGMENT_TAG").addToBackStack("SEARCH_FRAGMENT_TAG").commitAllowingStateLoss();
  }
  
  public void setSettingsView()
  {
    if (mHamburgerFragment == null) {
      mHamburgerFragment = new HamburgerFragment();
    }
    getSupportFragmentManager().beginTransaction().replace(2131231033, mHamburgerFragment).addToBackStack(null).commitAllowingStateLoss();
  }
  
  public void setTestSettingsView()
  {
    getSupportFragmentManager().beginTransaction().replace(2131231033, new TestSettingsFragment()).addToBackStack(null).commit();
  }
}
