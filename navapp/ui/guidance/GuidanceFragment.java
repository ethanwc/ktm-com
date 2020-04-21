package com.com.navapp.ui.guidance;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.BaseBundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.Globals;
import com.com.navapp.guidance.GuidanceManager;
import com.com.navapp.guidance.IconManager;
import com.com.navapp.guidance.LengthFormatter;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.services.NavigationEvent;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.ui.routing.RouteOptionsFragment;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.NavigationManagerProxy;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.guidance.NavigationManager;
import com.here.android.ui.routing.Maneuver;
import com.here.android.ui.routing.Maneuver.Action;
import java.util.Date;
import java.util.List;

public class GuidanceFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  static final String ARG_SIMULATE = "SIMULATE";
  static final String ARG_STARTED = "STARTED";
  static final String DEBUG_TAG = com.ktm.navapp.ui.guidance.GuidanceFragment.class.getSimpleName();
  private AudioManager mAudioManager;
  @BindView(2131230919)
  ImageButton mCancelButton;
  @BindView(2131231034)
  ImageButton mCenterButton;
  @BindView(2131230920)
  TextView mDistance;
  @BindView(2131230921)
  TextView mDistanceUnit;
  @BindView(2131230922)
  TextView mDurationHr;
  @BindView(2131230923)
  TextView mDurationHrUnit;
  @BindView(2131230924)
  TextView mDurationMin;
  @BindView(2131230925)
  TextView mDurationMinUnit;
  @BindView(2131230926)
  TextView mEtaText;
  @BindView(2131230927)
  LinearLayout mGuidanceOverlay;
  private int mInitialNotificationVolume;
  @BindView(2131231011)
  LinearLayout mLeftManeuverLayout;
  @BindView(2131230928)
  TextView mManeuverDistance;
  @BindView(2131230929)
  ImageView mManeuverIcon;
  @BindView(2131230930)
  TextView mManeuverNextRoad;
  @BindView(2131230931)
  LinearLayout mMapControls;
  @BindView(2131231037)
  ImageButton mMinusButton;
  @BindView(2131231038)
  ImageButton mPlusButton;
  @BindView(2131231013)
  LinearLayout mRightManeuverLayout;
  @BindView(2131230946)
  ImageButton mSkipWaypointButton;
  private final java.text.DateFormat mTimeFormat = android.text.format.DateFormat.getTimeFormat(Globals.getContext());
  
  public GuidanceFragment() {}
  
  public static GuidanceFragment newInstance(boolean paramBoolean)
  {
    RealmLog.append(DEBUG_TAG, "newInstance(simulate=%b)", new Object[] { Boolean.valueOf(paramBoolean) });
    GuidanceFragment localGuidanceFragment = new GuidanceFragment();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("SIMULATE", paramBoolean);
    localBundle.putBoolean("STARTED", false);
    localGuidanceFragment.setArguments(localBundle);
    return localGuidanceFragment;
  }
  
  private void restoreNotificationVolume()
  {
    AudioManager localAudioManager = mAudioManager;
    if (localAudioManager != null)
    {
      int i = mInitialNotificationVolume;
      try
      {
        localAudioManager.setStreamVolume(5, i, 0);
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  private static void setText(TextView paramTextView, String paramString)
  {
    if (!paramTextView.getText().toString().equals(paramString)) {
      paramTextView.setText(paramString);
    }
  }
  
  public void handleGuidanceNewManeuver(Maneuver paramManeuver, String paramString1, String paramString2, long paramLong)
  {
    mManeuverIcon.setImageResource(IconManager.getIconResource(paramManeuver));
    mManeuverDistance.setTextAppearance(2131624143);
    mManeuverDistance.setTextColor(-1);
    mManeuverDistance.setText(LengthFormatter.getFormatted(paramLong));
    String str = IconManager.getIconExit(paramManeuver);
    if (paramManeuver.getAction() != Maneuver.Action.STOPOVER) {
      paramString1 = paramString2;
    }
    if (!str.isEmpty())
    {
      mManeuverNextRoad.setBackgroundColor(-16777216);
      paramManeuver = mManeuverNextRoad;
      paramString1 = new StringBuilder();
      paramString1.append(getString(2131558496));
      paramString1.append(" ");
      paramString1.append(str);
      paramManeuver.setText(paramString1.toString());
      mManeuverNextRoad.setVisibility(0);
      return;
    }
    if ((paramString1 != null) && (!paramString1.trim().isEmpty()))
    {
      mManeuverNextRoad.setBackground(getResources().getDrawable(2131165375, null));
      mManeuverNextRoad.setText(paramString1);
      mManeuverNextRoad.setVisibility(0);
      return;
    }
    mManeuverNextRoad.setVisibility(4);
  }
  
  public void handleGuidanceUpdate(long paramLong1, long paramLong2, Date paramDate)
  {
    long l = (paramDate.getTime() - System.currentTimeMillis()) / 60000L;
    mManeuverDistance.setTextAppearance(2131624143);
    mManeuverDistance.setTextColor(-1);
    setText(mManeuverDistance, LengthFormatter.getFormatted(paramLong1));
    setText(mEtaText, mTimeFormat.format(paramDate));
    paramDate = LengthFormatter.getFormattedExt(paramLong2);
    setText(mDistance, paramDate[0]);
    setText(mDistanceUnit, paramDate[1]);
    paramLong1 = l / 60L;
    paramLong2 = l % 60L;
    if (paramLong1 < 1L)
    {
      setText(mDurationHr, "");
      setText(mDurationHrUnit, "");
      mDurationHrUnit.setVisibility(8);
      setText(mDurationMin, String.valueOf(paramLong2));
      setText(mDurationMinUnit, "min");
      return;
    }
    setText(mDurationHr, String.valueOf(paramLong1));
    setText(mDurationHrUnit, "h");
    mDurationHrUnit.setVisibility(0);
    setText(mDurationMin, String.valueOf(paramLong2));
    setText(mDurationMinUnit, "m");
  }
  
  public void handleNavigationEvent(NavigationEvent paramNavigationEvent)
  {
    Object localObject = GuidanceManager.getInstance();
    switch (3.$SwitchMap$com$ktm$navapp$services$NavigationEvent$EventType[type.ordinal()])
    {
    default: 
      return;
    case 7: 
      RealmLog.i(DEBUG_TAG, "GUIDANCE_ENDED");
      ((GuidanceManager)localObject).cancelGuidance();
      ((MainScreen)getActivity()).onGuidanceFinished();
      return;
    case 6: 
      RealmLog.i(DEBUG_TAG, "REACHED_DESTINATION");
      showReachedDestinationView();
      return;
    case 5: 
      RealmLog.append(DEBUG_TAG, "REACHED_STOPOVER %d", new Object[] { Integer.valueOf(stopOver) });
      updateSkipButton();
      return;
    case 4: 
      RealmLog.i(DEBUG_TAG, "GUIDANCE_STARTED");
      updateSkipButton();
      return;
    case 3: 
      mManeuverIcon.setImageResource(2131165986);
      mManeuverDistance.setTextAppearance(2131624171);
      mManeuverDistance.setTextColor(-1);
      mManeuverDistance.setText(Globals.getContext().getString(2131558515));
      mManeuverNextRoad.setVisibility(4);
      return;
    case 2: 
      Maneuver localManeuver = NavigationManagerProxy.getInstance().getNextManeuver();
      if (localManeuver != null)
      {
        String str3 = DEBUG_TAG;
        localObject = localManeuver.getAction();
        String str2 = "";
        if (localObject != null) {
          localObject = localManeuver.getAction().name();
        } else {
          localObject = "";
        }
        String str1;
        if (localManeuver.getTurn() != null) {
          str1 = localManeuver.getTurn().name();
        } else {
          str1 = "";
        }
        if (localManeuver.getIcon() != null) {
          str2 = localManeuver.getIcon().name();
        }
        RealmLog.append(str3, "onNewInstructionEvent() action=%s, turn=%s, icon=%s, distance=%d, road=%s, nextRoad=%s", new Object[] { localObject, str1, str2, Long.valueOf(distMan), localManeuver.getRoadName(), localManeuver.getNextRoadName() });
        handleGuidanceNewManeuver(localManeuver, localManeuver.getRoadName(), localManeuver.getNextRoadName(), distMan);
      }
      handleGuidanceUpdate(distMan, distDest, etaDest);
      return;
    }
    handleGuidanceUpdate(distMan, distDest, etaDest);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    mLeftManeuverLayout.setOnTouchListener(new GuidanceFragment.1(this));
    mRightManeuverLayout.setOnTouchListener(new GuidanceFragment.2(this));
    if (!getArguments().getBoolean("STARTED", false))
    {
      RealmLog.i(DEBUG_TAG, "onActivityCreated(): STARTING GUIDANCE");
      MapManager.getInstance().clearMarkers();
      MapManager.getInstance().clearRoutes();
      mManeuverIcon.setImageResource(IconManager.getIconResource(null));
      mAudioManager = ((AudioManager)Globals.getContext().getSystemService("audio"));
      paramBundle = mAudioManager;
      if (paramBundle != null) {
        mInitialNotificationVolume = paramBundle.getStreamVolume(5);
      }
      GuidanceManager.getInstance().startGuidance(getArguments().getBoolean("SIMULATE", false), false);
      getArguments().putBoolean("STARTED", true);
    }
  }
  
  public boolean onBackPressed()
  {
    RealmLog.i(DEBUG_TAG, "onBackPressed()");
    if (!GuidanceManager.isGuidanceView())
    {
      switchGuidanceView(true);
      return true;
    }
    GuidanceCancelFragment localGuidanceCancelFragment = new GuidanceCancelFragment();
    getActivity().getSupportFragmentManager().beginTransaction().add(2131231033, localGuidanceCancelFragment).addToBackStack(null).commitAllowingStateLoss();
    return true;
  }
  
  public void onCancelButton()
  {
    RealmLog.i(DEBUG_TAG, "onCancelButton()");
    getActivity().getSupportFragmentManager().beginTransaction().add(2131231033, new GuidanceCancelFragment()).addToBackStack(null).commitAllowingStateLoss();
  }
  
  void onCenterButton()
  {
    switchGuidanceView(true);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    RealmLog.i(DEBUG_TAG, "onCreateView()");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427397, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    return paramLayoutInflater;
  }
  
  public void onMapTouched()
  {
    switchGuidanceView(false);
  }
  
  public void onOptionsButton()
  {
    RealmLog.i(DEBUG_TAG, "onOptionsButton()");
    TripManager.setTrip(TripManager.getActiveRemainingTrip());
    TripManager.backupTrip();
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, RouteOptionsFragment.newInstance(true)).addToBackStack(null).commit();
  }
  
  public void onPause()
  {
    restoreNotificationVolume();
    mGuidanceOverlay.setVisibility(4);
    mCancelButton.setVisibility(4);
    mSkipWaypointButton.setVisibility(4);
    Events.unregister(this);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    restoreGuidanceState();
    mGuidanceOverlay.setVisibility(0);
    mCancelButton.setVisibility(0);
    Events.register(this);
  }
  
  public void onSkipWaypointButton()
  {
    RealmLog.i(DEBUG_TAG, "onSkipWaypointButton()");
    Trip localTrip = (Trip)mSkipWaypointButton.getTag();
    if (localTrip != null) {
      getActivity().getSupportFragmentManager().beginTransaction().add(2131231033, new GuidanceSkipWaypointFragment().setTrip(localTrip)).addToBackStack(null).commit();
    }
  }
  
  void onZoomIn()
  {
    MapManager.getInstance().zoomIn(false, false);
  }
  
  void onZoomOut()
  {
    MapManager.getInstance().zoomOut(false);
  }
  
  public void restoreGuidanceState()
  {
    Maneuver localManeuver = NavigationManagerProxy.getInstance().getNextManeuver();
    if (localManeuver != null)
    {
      long l = NavigationManagerProxy.getInstance().getNextManeuverDistance();
      handleGuidanceNewManeuver(localManeuver, localManeuver.getRoadName(), localManeuver.getNextRoadName(), l);
    }
    updateSkipButton();
    if (((Boolean)Storage.load("reacheddestination", Boolean.class, Boolean.valueOf(false))).booleanValue()) {
      showReachedDestinationView();
    }
  }
  
  public void showReachedDestinationView()
  {
    ReachedDestinationFragment localReachedDestinationFragment = new ReachedDestinationFragment();
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, localReachedDestinationFragment).addToBackStack(null).commit();
  }
  
  void switchGuidanceView(boolean paramBoolean)
  {
    GuidanceManager.setGuidanceView(paramBoolean, false);
    LinearLayout localLinearLayout1 = mGuidanceOverlay;
    if ((localLinearLayout1 != null) && (mCancelButton != null))
    {
      LinearLayout localLinearLayout2 = mMapControls;
      if (localLinearLayout2 != null)
      {
        if (paramBoolean)
        {
          localLinearLayout2.setVisibility(4);
          mCancelButton.setVisibility(0);
          mGuidanceOverlay.setVisibility(0);
          return;
        }
        localLinearLayout1.setVisibility(4);
        mCancelButton.setVisibility(4);
        mMapControls.setVisibility(0);
      }
    }
  }
  
  void updateSkipButton()
  {
    Trip localTrip = TripManager.getActiveTrip();
    if (localTrip.getWaypointsLeft().size() > 1)
    {
      mSkipWaypointButton.setTag(localTrip);
      mSkipWaypointButton.setVisibility(0);
      return;
    }
    mSkipWaypointButton.setTag(null);
    mSkipWaypointButton.setVisibility(4);
  }
}
