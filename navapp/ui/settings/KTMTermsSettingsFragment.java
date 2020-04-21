package com.com.navapp.ui.settings;

import android.os.BaseBundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.welcome.AcknowledgementScreen;

public class KTMTermsSettingsFragment
  extends NavAppBaseFragment
{
  public static final String ARG_NO_OPACITY = "ARG_NO_OPACITY";
  @BindView(2131231266)
  protected ImageView mBackground;
  @BindView(2131231267)
  protected WebView mWebView;
  
  public KTMTermsSettingsFragment() {}
  
  private void initialize(boolean paramBoolean)
  {
    mWebView.setWebViewClient(new KTMTermsSettingsFragment.1(this));
    mWebView.loadUrl("file:///android_asset/eula_en.html");
    mWebView.setBackgroundColor(0);
    mWebView.setLayerType(1, null);
    mWebView.getSettings().setJavaScriptEnabled(true);
    if (paramBoolean) {
      mBackground.setBackgroundColor(-1);
    }
  }
  
  public void closeTermsSettings()
  {
    if ((getActivity() instanceof AcknowledgementScreen)) {
      ((AcknowledgementScreen)getActivity()).setBackgroundToGray();
    }
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    boolean bool2 = false;
    paramLayoutInflater = paramLayoutInflater.inflate(2131427433, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    paramViewGroup = getArguments();
    boolean bool1 = bool2;
    if (paramViewGroup != null)
    {
      bool1 = bool2;
      if (paramViewGroup.getBoolean("ARG_NO_OPACITY", false)) {
        bool1 = true;
      }
    }
    initialize(bool1);
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
  }
}
