package com.com.navapp.ui.routing;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.factories.HereObjectsFactory;
import com.com.navapp.managers.MapManager;
import com.com.navapp.managers.OptionsManager;
import com.com.navapp.riser.SmartSettingsRoundtripFragment;
import com.com.navapp.riser.SmartSettingsRouteFragment;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.Trip;
import com.com.navapp.search.Trip.RouteType;
import com.com.navapp.search.TripManager;
import com.com.navapp.search.Waypoint;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.widgets.ChartView;
import com.com.navapp.ui.widgets.ChartView.HeightUnit;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.GeoPosition;
import com.here.android.ui.guidance.NavigationManager.UnitSystem;
import com.here.android.ui.mapping.MapRoute;
import com.here.android.ui.routing.Route;
import java.util.List;

public class RoutePreviewFragment
  extends NavAppBaseFragment
{
  private static final String KEY_SKIP_WARNING;
  private static final int REMOTE_ROUTE_DISTANCE = 200;
  @BindView(2131230821)
  protected ChartView mChartView;
  @BindView(2131231151)
  protected TextView mDescriptionTextView;
  @BindView(2131231150)
  protected TextView mDistanceTextView;
  @BindView(2131230880)
  protected TextView mElevationText;
  @BindView(2131230881)
  protected View mElevationTextLayout;
  @BindView(2131230900)
  protected View mFindingRouteLayout;
  @BindView(2131230984)
  protected TextView mIndicatorTollroads;
  @BindView(2131231152)
  protected TextView mMainHeaderTextView;
  @BindView(2131231153)
  protected ImageButton mNavigateButton;
  @BindView(2131231172)
  protected View mRemoteRouteScreen;
  private Runnable mShowBusyRunnable = new Runnable()
  {
    public void run()
    {
      if (getActivity() != null) {
        mFindingRouteLayout.setVisibility(0);
      }
    }
  };
  @BindView(2131231155)
  protected TextView mTimeTextView;
  @BindView(2131231337)
  protected CheckBox mWarningCheckbox;
  @BindView(2131231338)
  protected TextView mWarningLabel;
  @BindView(2131231176)
  protected View mWarningScreen;
  @BindView(2131231339)
  protected AppCompatTextView mWarningText;
  public boolean openSettings = false;
  private boolean simulate = false;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(com.ktm.navapp.ui.routing.RoutePreviewFragment.class.getSimpleName());
    localStringBuilder.append(".SKIP_WARNING");
    KEY_SKIP_WARNING = localStringBuilder.toString();
  }
  
  public RoutePreviewFragment() {}
  
  private void configRoute()
  {
    mDistanceTextView.setText(RoutingManager.getInstance().getDistance());
    mTimeTextView.setText(RoutingManager.getInstance().getTime());
    Object localObject = mIndicatorTollroads;
    int i;
    if (RoutingManager.getInstance().hasTollRoads()) {
      i = 0;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
    if (RoutingManager.getInstance().hasRoute())
    {
      mChartView.setChartData(RoutingManager.getInstance().getSelectedRoute().getRoute().getRouteGeometryWithElevationData());
      if (mChartView.hasValidData())
      {
        long l = RoutingManager.getInstance().getRawDistance();
        String str = RoutingManager.getInstance().getUnitFormat().toLowerCase();
        if (OptionsManager.getInstance().getUnitSystem() != NavigationManager.UnitSystem.METRIC) {
          localObject = ChartView.HeightUnit.FOOTS;
        } else {
          localObject = ChartView.HeightUnit.METRES;
        }
        mChartView.setDistanceOnChart(l, str, (ChartView.HeightUnit)localObject);
        mChartView.invalidate();
        enableChart(true);
        return;
      }
      enableChart(false);
      return;
    }
    enableChart(false);
  }
  
  private void configTrip()
  {
    Trip localTrip = TripManager.getTrip();
    mMainHeaderTextView.setText(localTrip.getTitle());
    Object localObject = localTrip.getWaypoints();
    if (((List)localObject).size() == 3)
    {
      mDescriptionTextView.setText(getString(2131558610, new Object[] { ((Waypoint)((List)localObject).get(1)).getTitle() }));
    }
    else if (((List)localObject).size() > 3)
    {
      mDescriptionTextView.setText(getString(2131558609, new Object[] { Integer.valueOf(((List)localObject).size() - 2) }));
    }
    else
    {
      int i;
      if (fastestRoute)
      {
        localObject = mDescriptionTextView;
        if (!allowHighways) {
          i = 2131558618;
        } else {
          i = 2131558617;
        }
        ((TextView)localObject).setText(i);
      }
      else
      {
        localObject = mDescriptionTextView;
        if (!allowHighways) {
          i = 2131558620;
        } else {
          i = 2131558619;
        }
        ((TextView)localObject).setText(i);
      }
    }
    mDistanceTextView.setText("...");
    mTimeTextView.setText("...");
    mIndicatorTollroads.setVisibility(4);
  }
  
  private void enableChart(boolean paramBoolean)
  {
    View localView = mElevationTextLayout;
    int i;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 8;
    }
    localView.setVisibility(i);
    if (!paramBoolean) {
      showChart(false);
    }
  }
  
  private void reloadRoute()
  {
    Trip localTrip = TripManager.getTrip();
    mMainHeaderTextView.setText(localTrip.getTitle());
    Events.postDelayed(mShowBusyRunnable, 100L);
    MapManager.getInstance().clearRoutes();
    RoutingManager.getInstance().loadRoute(MapManager.getInstance().getCurrentPosition().getCoordinate(), localTrip, new RoutePreviewFragment.2(this, localTrip));
  }
  
  private void setGuidanceView()
  {
    TripManager.setChangesTrackingTrip(TripManager.getTrip());
    ((MainScreen)getActivity()).setGuidanceView(simulate);
  }
  
  private void showChart(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (mChartView.getVisibility() != 0)
      {
        mElevationText.setEnabled(false);
        mChartView.setOnInvalidatedListener(new RoutePreviewFragment.1(this));
      }
      else
      {
        mElevationText.setEnabled(true);
      }
      mElevationText.setText(2131558522);
      mChartView.setVisibility(0);
      return;
    }
    mElevationText.setText(2131558661);
    mChartView.setVisibility(8);
    mElevationText.setEnabled(true);
  }
  
  public void closeRoutePreview()
  {
    TripManager.initTrip(TripManager.getDestination());
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  boolean handleRemoteRoute()
  {
    Waypoint localWaypoint = TripManager.getTrip().getStart();
    GeoPosition localGeoPosition = MapManager.getInstance().getCurrentPosition();
    if ((localWaypoint != null) && (localGeoPosition != null))
    {
      if (!localGeoPosition.isValid()) {
        return false;
      }
      if (localGeoPosition.getCoordinate().distanceTo(HereObjectsFactory.getGeoCoordinate(latitude, longitude)) < 200.0D) {
        return false;
      }
      mRemoteRouteScreen.setVisibility(0);
      return true;
    }
    return false;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427419, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    configTrip();
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.CENTER_RIGHT);
    enableChart(false);
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    MapManager.getInstance().clearMarkers();
    MapManager.getInstance().clearRoutes();
    super.onDestroy();
  }
  
  protected void onElevationClick()
  {
    for (;;)
    {
      try
      {
        if (mChartView.getVisibility() != 0)
        {
          bool = true;
          showChart(bool);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  public void onRemoteRouteCancel()
  {
    mRemoteRouteScreen.setVisibility(4);
  }
  
  public void onRemoteRouteYes()
  {
    mRemoteRouteScreen.setVisibility(4);
    TripManager.addMyLocation();
    reloadRoute();
  }
  
  public void onResume()
  {
    super.onResume();
    reloadRoute();
    ((MainScreen)getActivity()).enableOnMapGestures();
  }
  
  public void onRouteOptions()
  {
    TripManager.backupTrip();
    Trip localTrip = TripManager.getTrip();
    if (routeType == Trip.RouteType.ROUTE)
    {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, RouteOptionsFragment.newInstance(false)).addToBackStack(null).commit();
      return;
    }
    if (routeType == Trip.RouteType.SMART_ROUTE)
    {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new SmartSettingsRouteFragment()).addToBackStack(null).commit();
      return;
    }
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new SmartSettingsRoundtripFragment()).addToBackStack(null).commit();
  }
  
  public void onRouteSelected()
  {
    configRoute();
  }
  
  public void onStartNavigate()
  {
    simulate = false;
    startGuidance();
  }
  
  public boolean onStartSimulate()
  {
    simulate = false;
    startGuidance();
    return true;
  }
  
  public void onWarningCheckboxClicked()
  {
    boolean bool = mWarningCheckbox.isChecked();
    Storage.enable(KEY_SKIP_WARNING, bool);
    TextView localTextView = mWarningLabel;
    Resources localResources = getResources();
    int i;
    if (bool) {
      i = 2131034172;
    } else {
      i = 2131034194;
    }
    localTextView.setTextColor(localResources.getColor(i, null));
  }
  
  public void onWarningOkClicked()
  {
    mWarningScreen.setVisibility(4);
    if (RoutingManager.getInstance().getSelectedRoute() != null) {
      setGuidanceView();
    }
  }
  
  public void setNoRouteOptionsView()
  {
    Trip localTrip = TripManager.getTrip();
    if (routeType == Trip.RouteType.ROUTE)
    {
      if (isAdded()) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new NoRouteOptionsFragment()).addToBackStack("SEARCH_FRAGMENT_TAG").commitAllowingStateLoss();
      }
    }
    else if (routeType == Trip.RouteType.SMART_ROUTE) {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new SmartSettingsRouteFragment().setNoRouteFound()).addToBackStack(null).commit();
    } else {
      getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new SmartSettingsRoundtripFragment().setNoRouteFound()).addToBackStack(null).commit();
    }
    getActivity().getSupportFragmentManager().executePendingTransactions();
  }
  
  boolean showWarningScreen()
  {
    if (Storage.getBoolean(KEY_SKIP_WARNING, false)) {
      return false;
    }
    mWarningScreen.setVisibility(0);
    return true;
  }
  
  void startGuidance()
  {
    if ((RoutingManager.getInstance().getSelectedRoute() != null) && (!handleRemoteRoute()) && (!showWarningScreen())) {
      setGuidanceView();
    }
  }
}
