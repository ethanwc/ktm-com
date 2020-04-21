package com.com.navapp.ui.trip;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.routing.RoutingManager;
import com.com.navapp.search.SavedTrips;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import java.util.ArrayList;
import java.util.List;

public class SaveTripFragment
  extends NavAppBaseFragment
{
  final String _name = com.ktm.navapp.ui.trip.SaveTripFragment.class.getSimpleName();
  protected TripsAdapter mAdapter;
  @BindView(2131231314)
  protected EditText mEditText;
  @BindView(2131231315)
  protected RecyclerView mRvTrips;
  @BindView(2131231171)
  protected View mScreenOverwrite;
  @BindView(2131231175)
  protected View mScreenSave;
  protected String mTripName;
  
  public SaveTripFragment() {}
  
  private void showKeyboard(boolean paramBoolean)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    if (paramBoolean)
    {
      if (localInputMethodManager != null) {
        localInputMethodManager.showSoftInput(mEditText, 1);
      }
    }
    else if (localInputMethodManager != null) {
      localInputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    showOverwriteConfirmation(false);
    mEditText.setText("");
    mEditText.requestFocus();
    mAdapter.loadData();
  }
  
  public void onBackButton()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427422, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    mAdapter = new TripsAdapter();
    mRvTrips.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRvTrips.setAdapter(mAdapter);
    mEditText.setClickable(true);
    mEditText.setOnTouchListener(new SaveTripFragment.1(this));
    mEditText.setOnClickListener(new SaveTripFragment.2(this));
    mEditText.setOnEditorActionListener(new SaveTripFragment.3(this));
    mEditText.setOnFocusChangeListener(new SaveTripFragment.4(this));
    mRvTrips.setOnTouchListener(new SaveTripFragment.5(this));
    return paramLayoutInflater;
  }
  
  public void onOverwriteNo()
  {
    showOverwriteConfirmation(false);
  }
  
  public void onOverwriteYes()
  {
    showOverwriteConfirmation(false);
    saveTrip(mTripName);
  }
  
  void saveCheckOverwrite(String paramString)
  {
    com.com.navapp.search.Trip localTrip = TripManager.getTrip();
    name = paramString;
    if (SavedTrips.getSavedTrips().contains(localTrip))
    {
      mTripName = paramString;
      showOverwriteConfirmation(true);
      return;
    }
    saveTrip(paramString);
  }
  
  void saveTrip(String paramString)
  {
    com.com.navapp.search.Trip localTrip = TripManager.getTrip();
    name = paramString;
    dateMs = System.currentTimeMillis();
    SavedTrips.addToSavedTrips(localTrip);
    TripManager.setTrip(localTrip);
    TripManager.backupTrip();
    getActivity().getSupportFragmentManager().popBackStack("ROUTE_PREVIEW_FRAGMENT_TAG", 0);
  }
  
  void showOverwriteConfirmation(boolean paramBoolean)
  {
    int j = 0;
    if (paramBoolean) {
      showKeyboard(false);
    }
    View localView = mScreenOverwrite;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 4;
    }
    localView.setVisibility(i);
    localView = mScreenSave;
    int i = j;
    if (paramBoolean) {
      i = 4;
    }
    localView.setVisibility(i);
  }
  
  class TripsAdapter
    extends RecyclerView.Adapter<com.ktm.navapp.ui.trip.SaveTripFragment.TripsAdapter.ItemViewHolder>
  {
    final String TAG = com.ktm.navapp.ui.trip.SaveTripFragment.TripsAdapter.class.getSimpleName();
    private final List<com.ktm.navapp.search.Trip> mData = new ArrayList();
    
    TripsAdapter() {}
    
    public int getItemCount()
    {
      return mData.size();
    }
    
    void loadData()
    {
      RealmLog.i(TAG, "loadData()");
      mData.clear();
      List localList = SavedTrips.getSavedTrips();
      mData.addAll(localList);
      notifyDataSetChanged();
    }
    
    public void onBindViewHolder(ItemViewHolder paramItemViewHolder, int paramInt)
    {
      com.com.navapp.search.Trip localTrip = (com.com.navapp.search.Trip)mData.get(paramInt);
      textView1.setText(localTrip.getTitle());
      textView2.setText(String.format("%s | %s", new Object[] { DateUtils.formatDateTime(getContext(), dateMs, 131092), RoutingManager.formatDistance(length, false) }));
      itemView.setOnClickListener(new SaveTripFragment.TripsAdapter.1(this, localTrip));
    }
    
    public ItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      return new ItemViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131427423, paramViewGroup, false));
    }
    
    class ItemViewHolder
      extends RecyclerView.ViewHolder
    {
      final TextView textView1;
      final TextView textView2;
      
      ItemViewHolder()
      {
        super();
        textView1 = ((TextView)this$1.findViewById(2131231309));
        textView2 = ((TextView)this$1.findViewById(2131231310));
      }
    }
  }
}
