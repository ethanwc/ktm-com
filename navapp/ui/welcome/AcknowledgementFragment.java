package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.BaseBundle;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.KTMTermsSettingsFragment;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.com.navapp.utilities.Utilities;
import com.ktm.navapp.ui.onboarding.permissions.PermissionLocationOnBoard;

public class AcknowledgementFragment
  extends NavAppBaseFragment
{
  @BindView(2131230846)
  protected TextView mDescription;
  private KTMTermsSettingsFragment mKTMTermsSettingsFragment;
  
  public AcknowledgementFragment() {}
  
  private void configDescription()
  {
    SpannableString localSpannableString1 = new SpannableString(getResources().getString(2131558428));
    localSpannableString1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), 2131034194)), 0, localSpannableString1.length(), 33);
    SpannableString localSpannableString2 = new SpannableString(getResources().getString(2131558517));
    localSpannableString2.setSpan(new AcknowledgementFragment.1(this), 0, localSpannableString2.length(), 33);
    SpannableString localSpannableString3 = new SpannableString("and");
    localSpannableString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), 2131034194)), 0, localSpannableString3.length(), 33);
    SpannableString localSpannableString4 = new SpannableString(getResources().getString(2131558539));
    localSpannableString4.setSpan(new AcknowledgementFragment.2(this), 0, localSpannableString4.length(), 33);
    mDescription.setText(localSpannableString1);
    mDescription.append(" ");
    mDescription.append(localSpannableString2);
    mDescription.append(" ");
    mDescription.append(localSpannableString3);
    mDescription.append(" ");
    mDescription.append(localSpannableString4);
    mDescription.setMovementMethod(LinkMovementMethod.getInstance());
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427391, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.ACKNOWLEDGEMENT.name());
    configDescription();
    return paramLayoutInflater;
  }
  
  protected void onGoClick()
  {
    startActivity(new Intent(getActivity(), PermissionLocationOnBoard.class));
    getActivity().finish();
  }
  
  protected void onHEREReadClick()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://legal.here.com/en-gb/terms")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Utilities.toastShort("No activity found");
  }
  
  protected void onKTMReadClick()
  {
    ((AcknowledgementScreen)getActivity()).setBackgroundToWhite();
    if (mKTMTermsSettingsFragment == null) {
      mKTMTermsSettingsFragment = new KTMTermsSettingsFragment();
    }
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("ARG_NO_OPACITY", true);
    mKTMTermsSettingsFragment.setArguments(localBundle);
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131230838, mKTMTermsSettingsFragment).addToBackStack(null).commit();
  }
}
