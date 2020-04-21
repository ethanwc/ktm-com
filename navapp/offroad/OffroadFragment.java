package com.com.navapp.offroad;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.guidance.GuidanceManager;
import com.com.navapp.guidance.LengthFormatter;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.managers.MapManager;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoPosition;

public class OffroadFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  static final int COMPASS_ANIMATION_MS = 400;
  static final String TAG = com.ktm.navapp.offroad.OffroadFragment.class.getSimpleName();
  @BindView(2131231102)
  ImageButton mCancelButton;
  @BindView(2131231103)
  ImageView mCompassDisk;
  @BindView(2131231104)
  TextView mCompassDistance;
  @BindView(2131231105)
  TextView mCompassHeading;
  @BindView(2131231106)
  ImageView mCompassNeedle;
  float mCurrentDiskRotation = 0.0F;
  float mCurrentNeedleRotation = 0.0F;
  @BindView(2131231107)
  Button mDestReached;
  final ValueAnimator mDiskAnimator = new ValueAnimator();
  @BindView(2131231109)
  TextView mFinishDescription;
  @BindView(2131231110)
  TextView mFinishHeader;
  @BindView(2131231112)
  View mFinishScreen;
  @BindView(2131231113)
  LinearLayout mGuidanceOverlay;
  @BindView(2131231114)
  LinearLayout mMapControls;
  final ValueAnimator mNeedleAnimator = new ValueAnimator();
  boolean mTextVisible = false;
  final Runnable mUpdateUiTask = new Runnable()
  {
    public void run()
    {
      updateUi();
      Events.postDelayed(this, 100L);
    }
  };
  
  public OffroadFragment() {}
  
  public static OffroadFragment newInstance()
  {
    RealmLog.i(TAG, "newInstance()");
    OffroadFragment localOffroadFragment = new OffroadFragment();
    localOffroadFragment.setArguments(new Bundle());
    return localOffroadFragment;
  }
  
  private static void setText(TextView paramTextView, String paramString)
  {
    if (!paramTextView.getText().toString().equals(paramString)) {
      paramTextView.setText(paramString);
    }
  }
  
  private static void setVisibility(View paramView, int paramInt)
  {
    if (paramView.getVisibility() != paramInt) {
      paramView.setVisibility(paramInt);
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    setVisibility(mCompassDistance, 4);
    setVisibility(mCompassHeading, 4);
    setVisibility(mDestReached, 4);
    paramBundle = mCompassDisk;
    mCurrentDiskRotation = 0.0F;
    paramBundle.setRotation(0.0F);
    paramBundle = mCompassNeedle;
    mCurrentNeedleRotation = 0.0F;
    paramBundle.setRotation(0.0F);
    mTextVisible = false;
    MapManager.getInstance().clearMarkers();
    MapManager.getInstance().clearRoutes();
    OffroadGuidance.setDestination(TripManager.getDestination());
    GuidanceManager.getInstance().startGuidance(false, true);
    Events.register(this);
    Events.postDelayed(mUpdateUiTask, 100L);
  }
  
  public boolean onBackPressed()
  {
    RealmLog.i(TAG, "onBackPressed()");
    if (!GuidanceManager.isGuidanceView())
    {
      switchGuidanceView(true);
      return true;
    }
    onCancelButton();
    return true;
  }
  
  public void onCancelButton()
  {
    mFinishHeader.setText(2131558448);
    mFinishDescription.setVisibility(0);
    mFinishScreen.setVisibility(0);
  }
  
  void onCenterButton()
  {
    switchGuidanceView(true);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    RealmLog.i(TAG, "onCreateView()");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427399, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    return paramLayoutInflater;
  }
  
  public void onDestinationReachedClicked()
  {
    mFinishHeader.setText(2131558712);
    mFinishDescription.setVisibility(8);
    mFinishScreen.setVisibility(0);
  }
  
  public void onDestroy()
  {
    Events.cancel(mUpdateUiTask);
    Events.unregister(this);
    mDiskAnimator.cancel();
    mNeedleAnimator.cancel();
    super.onDestroy();
  }
  
  public void onFinishBackClicked()
  {
    mFinishScreen.setVisibility(4);
  }
  
  public void onFinishOkClicked()
  {
    Storage.save("isguidancecancelled", Boolean.valueOf(true));
    GuidanceManager.getInstance().cancelGuidance();
    ((MainScreen)getActivity()).onGuidanceFinished();
  }
  
  public void onMapTouched()
  {
    switchGuidanceView(false);
  }
  
  public void onPositionChanged(GeoPosition paramGeoPosition)
  {
    OffroadGuidance.updatePosition(paramGeoPosition);
  }
  
  void onZoomIn()
  {
    MapManager.getInstance().zoomIn(false, false);
  }
  
  void onZoomOut()
  {
    MapManager.getInstance().zoomOut(false);
  }
  
  void switchGuidanceView(boolean paramBoolean)
  {
    GuidanceManager.setGuidanceView(paramBoolean, true);
    if (paramBoolean)
    {
      mMapControls.setVisibility(4);
      mCancelButton.setVisibility(0);
      mGuidanceOverlay.setVisibility(0);
      return;
    }
    mGuidanceOverlay.setVisibility(4);
    mCancelButton.setVisibility(8);
    mMapControls.setVisibility(0);
  }
  
  public void updateUi()
  {
    if (!OffroadGuidance.update()) {
      return;
    }
    MapManager.getInstance().setCenterGuidanceOffroad(OffroadGuidance.getCurrentPosition(), (float)OffroadGuidance.getCurrentHeading());
    float f2 = (float)OffroadGuidance.getHeadingNorthRelative();
    float f1 = (float)OffroadGuidance.getHeadingDestRelative();
    long l = OffroadGuidance.getDistanceDest();
    Button localButton = mDestReached;
    int i;
    if (OffroadGuidance.reachedDestination()) {
      i = 0;
    } else {
      i = 4;
    }
    setVisibility(localButton, i);
    setText(mCompassDistance, LengthFormatter.getFormatted(l));
    setText(mCompassHeading, OffroadGuidance.getCompassPoint(OffroadGuidance.getHeadingDest()));
    if (f2 != mCurrentDiskRotation)
    {
      mCurrentDiskRotation = f2;
      mDiskAnimator.cancel();
      float f3 = mCompassDisk.getRotation();
      mDiskAnimator.setFloatValues(new float[] { 0.0F, (float)OffroadGuidance.headDeltaSigned(f3, f2) });
      mDiskAnimator.setDuration(400L);
      mDiskAnimator.removeAllUpdateListeners();
      mDiskAnimator.addUpdateListener(new OffroadFragment.2(this, f3));
      mDiskAnimator.start();
    }
    if (f1 != mCurrentNeedleRotation)
    {
      mCurrentNeedleRotation = f1;
      mNeedleAnimator.cancel();
      f2 = mCompassNeedle.getRotation();
      mNeedleAnimator.setFloatValues(new float[] { 0.0F, (float)OffroadGuidance.headDeltaSigned(f2, f1) });
      mNeedleAnimator.setDuration(400L);
      mNeedleAnimator.removeAllUpdateListeners();
      mNeedleAnimator.addUpdateListener(new OffroadFragment.3(this, f2));
      mNeedleAnimator.start();
    }
    if (!mTextVisible)
    {
      mTextVisible = true;
      mCompassDistance.setVisibility(0);
      mCompassHeading.setVisibility(0);
    }
  }
}
