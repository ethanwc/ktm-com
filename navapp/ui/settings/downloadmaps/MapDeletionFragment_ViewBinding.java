package com.com.navapp.ui.settings.downloadmaps;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MapDeletionFragment_ViewBinding
  implements Unbinder
{
  private MapDeletionFragment target;
  private View view7f080141;
  private View view7f080144;
  
  public MapDeletionFragment_ViewBinding(MapDeletionFragment paramMapDeletionFragment, View paramView)
  {
    target = paramMapDeletionFragment;
    mDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231042, "field 'mDescription'", TextView.class));
    View localView = Utils.findRequiredView(paramView, 2131231044, "method 'onOk'");
    view7f080144 = localView;
    localView.setOnClickListener(new MapDeletionFragment_ViewBinding.1(this, paramMapDeletionFragment));
    paramView = Utils.findRequiredView(paramView, 2131231041, "method 'onCancel'");
    view7f080141 = paramView;
    paramView.setOnClickListener(new MapDeletionFragment_ViewBinding.2(this, paramMapDeletionFragment));
  }
  
  public void unbind()
  {
    MapDeletionFragment localMapDeletionFragment = target;
    if (localMapDeletionFragment != null)
    {
      target = null;
      mDescription = null;
      view7f080144.setOnClickListener(null);
      view7f080144 = null;
      view7f080141.setOnClickListener(null);
      view7f080141 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
