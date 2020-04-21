package com.com.navapp.ui.routing;

import android.os.BaseBundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.guidance.GuidanceManager;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;
import java.util.ArrayList;
import java.util.Collections;

public class RouteWaypointsFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  static final String ARG_SINGLE_BUTTON = "SINGLE_BUTTON";
  static final String TAG = com.ktm.navapp.ui.routing.RouteWaypointsFragment.class.getSimpleName();
  protected WaypointsAdapter mAdapter;
  final ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback()
  {
    public int getMovementFlags(RecyclerView paramAnonymousRecyclerView, RecyclerView.ViewHolder paramAnonymousViewHolder)
    {
      return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
    }
    
    public boolean onMove(RecyclerView paramAnonymousRecyclerView, RecyclerView.ViewHolder paramAnonymousViewHolder1, RecyclerView.ViewHolder paramAnonymousViewHolder2)
    {
      mAdapter.moveItem(paramAnonymousViewHolder1.getAdapterPosition(), paramAnonymousViewHolder2.getAdapterPosition());
      return true;
    }
    
    public void onSwiped(RecyclerView.ViewHolder paramAnonymousViewHolder, int paramAnonymousInt) {}
  });
  @BindView(2131231159)
  protected Button mRideItButton;
  @BindView(2131231160)
  protected RecyclerView mRvWaypoints;
  @BindView(2131231161)
  protected View mSaveOrPreview;
  
  public RouteWaypointsFragment() {}
  
  public static RouteWaypointsFragment newInstance(boolean paramBoolean)
  {
    RealmLog.append(TAG, "newInstance( singleButtonRideIt = %b )", new Object[] { Boolean.valueOf(paramBoolean) });
    RouteWaypointsFragment localRouteWaypointsFragment = new RouteWaypointsFragment();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("SINGLE_BUTTON", paramBoolean);
    localRouteWaypointsFragment.setArguments(localBundle);
    return localRouteWaypointsFragment;
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    mAdapter.loadData();
  }
  
  public boolean onBackPressed()
  {
    onButtonBack();
    return true;
  }
  
  public void onButtonBack()
  {
    if ((getArguments() != null) && (getArguments().getBoolean("SINGLE_BUTTON", false))) {
      GuidanceManager.setGuidanceView(true, false);
    }
    TripManager.restoreTrip(true);
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onButtonPreview()
  {
    mAdapter.saveData();
    getActivity().getSupportFragmentManager().popBackStack("ROUTE_PREVIEW_FRAGMENT_TAG", 0);
  }
  
  public void onButtonRideIt()
  {
    mAdapter.saveData();
    GuidanceManager.setGuidanceView(true, false);
    getActivity().getSupportFragmentManager().popBackStack();
    getActivity().getSupportFragmentManager().popBackStack();
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new NoPreviewFragment()).addToBackStack(null).commit();
  }
  
  public void onButtonSave()
  {
    mAdapter.saveData();
    ((MainScreen)getActivity()).setSaveTripView();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427420, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    if ((getArguments() != null) && (getArguments().getBoolean("SINGLE_BUTTON", false)))
    {
      GuidanceManager.setNoMapUpdate();
      mSaveOrPreview.setVisibility(8);
      mRideItButton.setVisibility(0);
    }
    else
    {
      mSaveOrPreview.setVisibility(0);
      mRideItButton.setVisibility(8);
    }
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    mAdapter = new WaypointsAdapter(mItemTouchHelper);
    mRvWaypoints.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRvWaypoints.setAdapter(mAdapter);
    mItemTouchHelper.attachToRecyclerView(mRvWaypoints);
    return paramLayoutInflater;
  }
  
  void startWaypointSearch(int paramInt)
  {
    ((MainScreen)getActivity()).setSearchView(false, paramInt);
  }
  
  class WaypointsAdapter
    extends RecyclerView.Adapter<com.ktm.navapp.ui.routing.RouteWaypointsFragment.WaypointsAdapter.ItemViewHolder>
  {
    static final String TAG = com.ktm.navapp.ui.routing.RouteWaypointsFragment.WaypointsAdapter.class.getSimpleName();
    private final ArrayList<com.ktm.navapp.search.Waypoint> mData = new ArrayList();
    private final ItemTouchHelper mTouchHelper;
    
    WaypointsAdapter(ItemTouchHelper paramItemTouchHelper)
    {
      mTouchHelper = paramItemTouchHelper;
    }
    
    void dismissItem(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < mData.size()) && (mData.size() > 3))
      {
        mData.remove(paramInt);
        notifyItemRemoved(paramInt);
      }
    }
    
    public int getItemCount()
    {
      return mData.size();
    }
    
    void loadData()
    {
      RealmLog.i(TAG, "loadData()");
      mData.clear();
      Object localObject = TripManager.getTrip();
      mData.addAll(waypoints);
      if (!waypoints.contains(null)) {
        if (mData.size() > 1)
        {
          localObject = mData;
          ((ArrayList)localObject).add(((ArrayList)localObject).size() - 1, null);
        }
        else
        {
          mData.add(null);
        }
      }
      notifyDataSetChanged();
    }
    
    void moveItem(int paramInt1, int paramInt2)
    {
      if ((paramInt1 >= 0) && (paramInt1 < mData.size()) && (paramInt2 >= 0) && (paramInt2 < mData.size()) && (!com.com.navapp.search.Waypoint.MY_LOCATION.equals(mData.get(paramInt1))) && (!com.com.navapp.search.Waypoint.MY_LOCATION.equals(mData.get(paramInt2))))
      {
        Collections.swap(mData, paramInt1, paramInt2);
        notifyItemMoved(paramInt1, paramInt2);
      }
    }
    
    public void onBindViewHolder(ItemViewHolder paramItemViewHolder, int paramInt)
    {
      com.com.navapp.search.Waypoint localWaypoint = (com.com.navapp.search.Waypoint)mData.get(paramInt);
      if (localWaypoint == null)
      {
        textView.setTextAppearance(2131624167);
        textView.setText(2131558611);
        dragHandle.setImageResource(2131165276);
        dragHandle.setVisibility(0);
        deleteHandle.setVisibility(4);
        textView.setOnClickListener(new RouteWaypointsFragment.WaypointsAdapter.1(this, paramItemViewHolder));
      }
      else if (com.com.navapp.search.Waypoint.MY_LOCATION.equals(localWaypoint))
      {
        textView.setTextAppearance(2131624167);
        textView.setText(2131558612);
        dragHandle.setVisibility(4);
        deleteHandle.setVisibility(4);
      }
      else
      {
        textView.setTextAppearance(2131624166);
        textView.setText(localWaypoint.getTitle());
        dragHandle.setImageResource(2131165360);
        dragHandle.setVisibility(0);
        deleteHandle.setVisibility(0);
      }
      dragHandle.setOnTouchListener(new RouteWaypointsFragment.WaypointsAdapter.2(this, paramItemViewHolder));
      deleteHandle.setOnClickListener(new RouteWaypointsFragment.WaypointsAdapter.3(this, paramItemViewHolder));
    }
    
    public ItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      return new ItemViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131427421, paramViewGroup, false));
    }
    
    void saveData()
    {
      RealmLog.i(TAG, "saveData()");
      TripManager.setWaypoints(mData);
    }
    
    class ItemViewHolder
      extends RecyclerView.ViewHolder
    {
      final ImageView deleteHandle;
      final ImageView dragHandle;
      final TextView textView;
      
      ItemViewHolder()
      {
        super();
        textView = ((TextView)this$1.findViewById(2131231276));
        dragHandle = ((ImageView)this$1.findViewById(2131230875));
        deleteHandle = ((ImageView)this$1.findViewById(2131230845));
      }
    }
  }
}
