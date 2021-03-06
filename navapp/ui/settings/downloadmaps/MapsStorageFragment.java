package com.com.navapp.ui.settings.downloadmaps;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.Globals;
import com.com.navapp.downloading.offlinemaps.MapStorageLocation;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.ui.base.NavAppBaseFragment;

public class MapsStorageFragment
  extends NavAppBaseFragment
{
  static final String TAG = com.ktm.navapp.ui.settings.downloadmaps.MapsStorageFragment.class.getSimpleName();
  @BindView(2131231063)
  protected RadioButton mDeviceButton;
  @BindView(2131231065)
  protected ProgressBar mDeviceMemBar;
  @BindView(2131231066)
  protected TextView mDeviceMemText;
  @BindView(2131231068)
  protected View mPopupLayout;
  @BindView(2131231069)
  protected TextView mPopupText;
  @BindView(2131231070)
  protected RadioButton mSdCardButton;
  @BindView(2131231071)
  protected View mSdCardLayout;
  @BindView(2131231072)
  protected ProgressBar mSdCardMemBar;
  @BindView(2131231073)
  protected TextView mSdCardMemText;
  
  public MapsStorageFragment() {}
  
  private void switchStorage(boolean paramBoolean)
  {
    if (MapStorageLocation.getUseExternalStorage() != paramBoolean)
    {
      mDeviceButton.setChecked(paramBoolean ^ true);
      mSdCardButton.setChecked(paramBoolean);
      mPopupLayout.setVisibility(0);
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    mPopupLayout.setVisibility(4);
    mPopupText.setText(2131558548);
    updateUi();
  }
  
  public void onButtonBack()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onButtonCancel()
  {
    updateUi();
    mPopupLayout.setVisibility(4);
  }
  
  public void onButtonOk()
  {
    MapStorageLocation.setUseExternalStorage(MapStorageLocation.getUseExternalStorage() ^ true, true);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    RealmLog.i(TAG, "onCreateView()");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427411, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onSelectDevice()
  {
    switchStorage(false);
  }
  
  public void onSelectSdCard()
  {
    switchStorage(true);
  }
  
  public void updateUi()
  {
    boolean bool = MapStorageLocation.getUseExternalStorage();
    mDeviceButton.setChecked(bool ^ true);
    mSdCardButton.setChecked(bool);
    String str = getResources().getString(2131558439);
    long l1 = MapStorageLocation.getInternalAvailable();
    long l2 = MapStorageLocation.getInternalTotal();
    mDeviceMemText.setText(String.format("%s %s", new Object[] { Formatter.formatFileSize(Globals.getContext(), l1), str }));
    mDeviceMemBar.setProgress(100 - (int)(l1 * 100.0D / l2));
    if (MapStorageLocation.getExternalPath() != null)
    {
      l1 = MapStorageLocation.getExternalAvailable();
      l2 = MapStorageLocation.getExternalTotal();
      mSdCardMemText.setText(String.format("%s %s", new Object[] { Formatter.formatFileSize(Globals.getContext(), l1), str }));
      mSdCardMemBar.setProgress(100 - (int)(l1 * 100.0D / l2));
      mSdCardLayout.setVisibility(0);
      return;
    }
    mSdCardLayout.setVisibility(8);
  }
}
