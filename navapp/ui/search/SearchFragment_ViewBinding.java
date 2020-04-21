package com.com.navapp.ui.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SearchFragment_ViewBinding
  implements Unbinder
{
  private SearchFragment target;
  private View view7f08005a;
  private View view7f080068;
  private View view7f080145;
  private View view7f080148;
  private View view7f0801d8;
  private View view7f0801d9;
  private View view7f0801da;
  private View view7f0801dd;
  private View view7f0801de;
  
  public SearchFragment_ViewBinding(SearchFragment paramSearchFragment, View paramView)
  {
    target = paramSearchFragment;
    mSearchLayout = ((ViewGroup)Utils.findRequiredViewAsType(paramView, 2131231188, "field 'mSearchLayout'", ViewGroup.class));
    mEditText = ((EditText)Utils.findRequiredViewAsType(paramView, 2131230879, "field 'mEditText'", EditText.class));
    mListView = ((ListView)Utils.findRequiredViewAsType(paramView, 2131231027, "field 'mListView'", ListView.class));
    mSearchResultLayout = ((ViewGroup)Utils.findRequiredViewAsType(paramView, 2131231191, "field 'mSearchResultLayout'", ViewGroup.class));
    View localView = Utils.findRequiredView(paramView, 2131231197, "field 'mResultOffroadButton' and method 'onStartOffroad'");
    mResultOffroadButton = ((ImageButton)Utils.castView(localView, 2131231197, "field 'mResultOffroadButton'", ImageButton.class));
    view7f0801dd = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.1(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131231192, "field 'mResultHeader' and method 'onSearchTextClicked'");
    mResultHeader = ((TextView)Utils.castView(localView, 2131231192, "field 'mResultHeader'", TextView.class));
    view7f0801d8 = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.2(this, paramSearchFragment));
    mResultNavigate = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231196, "field 'mResultNavigate'", TextView.class));
    mResultNavigateSub = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231195, "field 'mResultNavigateSub'", TextView.class));
    mLayoutChooseOnMap = ((ViewGroup)Utils.findRequiredViewAsType(paramView, 2131231010, "field 'mLayoutChooseOnMap'", ViewGroup.class));
    mChooseOnMapInstruction = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230825, "field 'mChooseOnMapInstruction'", TextView.class));
    mLayoutMapPointDetails = ((ViewGroup)Utils.findRequiredViewAsType(paramView, 2131231012, "field 'mLayoutMapPointDetails'", ViewGroup.class));
    mMapPointDetail1 = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231046, "field 'mMapPointDetail1'", TextView.class));
    mMapPointDetail2 = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231047, "field 'mMapPointDetail2'", TextView.class));
    localView = Utils.findRequiredView(paramView, 2131231048, "field 'mMapPointSetAs' and method 'onMapPointSetButton'");
    mMapPointSetAs = ((TextView)Utils.castView(localView, 2131231048, "field 'mMapPointSetAs'", TextView.class));
    view7f080148 = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.3(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131230810, "method 'onButtonChooseOnMap'");
    view7f08005a = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.4(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131230824, "method 'onChooseOnMapBackButton'");
    view7f080068 = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.5(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131231045, "method 'onMapPointCancelButton'");
    view7f080145 = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.6(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131231198, "method 'onResumeSearch'");
    view7f0801de = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.7(this, paramSearchFragment));
    localView = Utils.findRequiredView(paramView, 2131231194, "method 'onStartNavigation'");
    view7f0801da = localView;
    localView.setOnClickListener(new SearchFragment_ViewBinding.8(this, paramSearchFragment));
    paramView = Utils.findRequiredView(paramView, 2131231193, "method 'onMenuButton'");
    view7f0801d9 = paramView;
    paramView.setOnClickListener(new SearchFragment_ViewBinding.9(this, paramSearchFragment));
  }
  
  public void unbind()
  {
    SearchFragment localSearchFragment = target;
    if (localSearchFragment != null)
    {
      target = null;
      mSearchLayout = null;
      mEditText = null;
      mListView = null;
      mSearchResultLayout = null;
      mResultOffroadButton = null;
      mResultHeader = null;
      mResultNavigate = null;
      mResultNavigateSub = null;
      mLayoutChooseOnMap = null;
      mChooseOnMapInstruction = null;
      mLayoutMapPointDetails = null;
      mMapPointDetail1 = null;
      mMapPointDetail2 = null;
      mMapPointSetAs = null;
      view7f0801dd.setOnClickListener(null);
      view7f0801dd = null;
      view7f0801d8.setOnClickListener(null);
      view7f0801d8 = null;
      view7f080148.setOnClickListener(null);
      view7f080148 = null;
      view7f08005a.setOnClickListener(null);
      view7f08005a = null;
      view7f080068.setOnClickListener(null);
      view7f080068 = null;
      view7f080145.setOnClickListener(null);
      view7f080145 = null;
      view7f0801de.setOnClickListener(null);
      view7f0801de = null;
      view7f0801da.setOnClickListener(null);
      view7f0801da = null;
      view7f0801d9.setOnClickListener(null);
      view7f0801d9 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
