package com.com.navapp.utilities;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import java.util.concurrent.Executor;

public class Events
{
  private static final Bus mBus = new Bus();
  private static final Handler mHandler = new Handler(Looper.getMainLooper());
  
  public Events() {}
  
  public static void cancel(Runnable paramRunnable)
  {
    if (paramRunnable != null) {
      mHandler.removeCallbacks(paramRunnable);
    }
  }
  
  public static void doInBackground(Runnable paramRunnable)
  {
    AsyncTask.THREAD_POOL_EXECUTOR.execute(paramRunnable);
  }
  
  public static void postDelayed(Runnable paramRunnable, long paramLong)
  {
    if (paramRunnable != null) {
      mHandler.postDelayed(paramRunnable, paramLong);
    }
  }
  
  public static void postEvent(Object paramObject)
  {
    if (paramObject != null) {
      postOnMainThread(new Events.1(paramObject));
    }
  }
  
  public static void postOnMainThread(Runnable paramRunnable)
  {
    if (paramRunnable != null) {
      mHandler.post(paramRunnable);
    }
  }
  
  public static void register(Object paramObject)
  {
    mBus.register(paramObject);
  }
  
  public static void runOnMainThread(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        paramRunnable.run();
        return;
      }
      postOnMainThread(paramRunnable);
    }
  }
  
  public static void unregister(Object paramObject)
  {
    mBus.unregister(paramObject);
  }
}
