package com.com.navapp.ui.trip;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.SwipeLayout.ShowMode;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.daimajia.swipe.util.Attributes.Mode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class LoadTripFragment
  extends NavAppBaseFragment
{
  final String _name = com.ktm.navapp.ui.trip.LoadTripFragment.class.getSimpleName();
  protected TripsAdapter mAdapter;
  @BindView(2131231306)
  protected TextView mDeleteText;
  @BindView(2131231314)
  protected EditText mEditText;
  @BindView(2131231315)
  protected RecyclerView mRvTrips;
  @BindView(2131231168)
  protected View mScreenDelete;
  @BindView(2131231170)
  protected View mScreenLoad;
  protected com.com.navapp.search.Trip mTrip2Delete;
  
  public LoadTripFragment() {}
  
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
  
  void checkDelete(com.com.navapp.search.Trip paramTrip)
  {
    mTrip2Delete = paramTrip;
    mDeleteText.setText(Html.fromHtml(getString(2131558684, new Object[] { Html.escapeHtml(paramTrip.getTitle()) })));
    showDeleteConfirmation(true);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    showDeleteConfirmation(false);
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
    paramLayoutInflater = paramLayoutInflater.inflate(2131427407, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    mAdapter = new TripsAdapter();
    mRvTrips.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRvTrips.setAdapter(mAdapter);
    mEditText.setClickable(true);
    mEditText.setOnTouchListener(new LoadTripFragment.1(this));
    mEditText.setOnClickListener(new LoadTripFragment.2(this));
    mEditText.addTextChangedListener(new LoadTripFragment.3(this));
    mEditText.setOnEditorActionListener(new LoadTripFragment.4(this));
    mEditText.setOnFocusChangeListener(new LoadTripFragment.5(this));
    mRvTrips.setOnTouchListener(new LoadTripFragment.6(this));
    return paramLayoutInflater;
  }
  
  public void onDeleteNo()
  {
    showDeleteConfirmation(false);
    mAdapter.loadData();
  }
  
  public void onDeleteYes()
  {
    showDeleteConfirmation(false);
    SavedTrips.deleteFromSavedTrips(mTrip2Delete);
    mAdapter.loadData();
    if (SavedTrips.getSavedTrips().isEmpty()) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
  }
  
  void showDeleteConfirmation(boolean paramBoolean)
  {
    View localView = mScreenDelete;
    int j = 4;
    int i;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 4;
    }
    localView.setVisibility(i);
    localView = mScreenLoad;
    if (paramBoolean) {
      i = j;
    } else {
      i = 0;
    }
    localView.setVisibility(i);
    showKeyboard(false);
  }
  
  class TripsAdapter
    extends RecyclerSwipeAdapter<com.ktm.navapp.ui.trip.LoadTripFragment.TripsAdapter.ItemViewHolder>
  {
    final String dir = com.ktm.navapp.ui.trip.LoadTripFragment.TripsAdapter.class.getSimpleName();
    private final List<com.ktm.navapp.search.Trip> mData = new ArrayList();
    private final List<com.ktm.navapp.search.Trip> mData0 = new ArrayList();
    private Pattern mFilter;
    private String mFilterText;
    
    TripsAdapter()
    {
      setMode(Attributes.Mode.Single);
    }
    
    public int getItemCount()
    {
      return mData.size();
    }
    
    public int getSwipeLayoutResourceId(int paramInt)
    {
      return 2131231135;
    }
    
    void loadData()
    {
      RealmLog.i(dir, "loadData()");
      mData0.clear();
      List localList = SavedTrips.getSavedTrips();
      mData0.addAll(localList);
      setFilter(null);
    }
    
    public void onBindViewHolder(ItemViewHolder paramItemViewHolder, int paramInt)
    {
      com.com.navapp.search.Trip localTrip = (com.com.navapp.search.Trip)mData.get(paramInt);
      revealLayout.close(false);
      revealLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
      mItemManger.bindView(itemView, paramInt);
      textView1.setText(localTrip.getTitle());
      textView2.setText(String.format("%s | %s", new Object[] { DateUtils.formatDateTime(getContext(), dateMs, 131092), RoutingManager.formatDistance(length, false) }));
      textContainer.setOnClickListener(new LoadTripFragment.TripsAdapter.1(this, localTrip));
      buttonEdit.setOnClickListener(new LoadTripFragment.TripsAdapter.2(this, localTrip, paramItemViewHolder));
      buttonDelete.setOnClickListener(new LoadTripFragment.TripsAdapter.3(this, localTrip, paramItemViewHolder));
    }
    
    public ItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      return new ItemViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131427408, paramViewGroup, false));
    }
    
    public void setFilter(String paramString)
    {
      if (paramString != null) {
        mFilterText = paramString.trim();
      }
      mData.clear();
      paramString = mData0.iterator();
      while (paramString.hasNext())
      {
        com.com.navapp.search.Trip localTrip = (com.com.navapp.search.Trip)paramString.next();
        String str = mFilterText;
        if ((str == null) || (str.isEmpty()) || (localTrip.getTitle().contains(mFilterText))) {
          mData.add(localTrip);
        }
      }
      notifyDataSetChanged();
    }
    
    class ItemViewHolder
      extends RecyclerView.ViewHolder
    {
      final ImageButton buttonDelete;
      final ImageButton buttonEdit;
      final SwipeLayout revealLayout;
      final View textContainer;
      final TextView textView1;
      final TextView textView2;
      
      ItemViewHolder()
      {
        super();
        revealLayout = ((SwipeLayout)this$1.findViewById(2131231135));
        buttonEdit = ((ImageButton)this$1.findViewById(2131230812));
        buttonDelete = ((ImageButton)this$1.findViewById(2131230811));
        textContainer = this$1.findViewById(2131231308);
        textView1 = ((TextView)this$1.findViewById(2131231309));
        textView2 = ((TextView)this$1.findViewById(2131231310));
      }
    }
  }
}
