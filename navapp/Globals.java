package com.com.navapp;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import com.com.navapp.analytics.Analytics;
import com.com.navapp.logging.LogExt;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.utilities.Storage;
import com.jakewharton.processphoenix.ProcessPhoenix;
import com.ktm.navapp.App;
import io.github.inflationx.calligraphy3.CalligraphyConfig.Builder;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPump.Builder;

public class Globals
  extends Application
{
  private static final String TAG = App.class.getSimpleName();
  private static Context context;
  
  public Globals() {}
  
  public static Context getContext()
  {
    return context;
  }
  
  public void onCreate()
  {
    super.onCreate();
    context = getApplicationContext();
    if (ProcessPhoenix.isPhoenixProcess(context)) {
      return;
    }
    Storage.initialize(context);
    RealmLog.i(TAG, "onCreate()");
    Analytics.getInstance().setEnabled(false);
    LogExt.installUncaughtExceptionHandler();
    ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/TradeGothicLTStd.otf").setFontAttrId(2130903265).build())).build());
  }
}
