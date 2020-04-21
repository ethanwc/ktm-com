package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WelcomeScreenSelectBike_ViewBinding
  implements Unbinder
{
  private WelcomeScreenSelectBike target;
  private View view7f08026e;
  private View view7f080270;
  
  public WelcomeScreenSelectBike_ViewBinding(WelcomeScreenSelectBike paramWelcomeScreenSelectBike)
  {
    this(paramWelcomeScreenSelectBike, paramWelcomeScreenSelectBike.getWindow().getDecorView());
  }
  
  public WelcomeScreenSelectBike_ViewBinding(WelcomeScreenSelectBike paramWelcomeScreenSelectBike, View paramView)
  {
    target = paramWelcomeScreenSelectBike;
    mBikesListView = ((ListView)Utils.findRequiredViewAsType(paramView, 2131231343, "field 'mBikesListView'", ListView.class));
    View localView = Utils.findRequiredView(paramView, 2131231344, "method 'onCloseClicked'");
    view7f080270 = localView;
    localView.setOnClickListener(new WelcomeScreenSelectBike_ViewBinding.1(this, paramWelcomeScreenSelectBike));
    paramView = Utils.findRequiredView(paramView, 2131231342, "method 'onAddBikeClicked'");
    view7f08026e = paramView;
    paramView.setOnClickListener(new WelcomeScreenSelectBike_ViewBinding.2(this, paramWelcomeScreenSelectBike));
  }
  
  public void unbind()
  {
    WelcomeScreenSelectBike localWelcomeScreenSelectBike = target;
    if (localWelcomeScreenSelectBike != null)
    {
      target = null;
      mBikesListView = null;
      view7f080270.setOnClickListener(null);
      view7f080270 = null;
      view7f08026e.setOnClickListener(null);
      view7f08026e = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
