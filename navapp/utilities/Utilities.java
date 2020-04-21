package com.com.navapp.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import androidx.core.content.ContextCompat;
import com.com.navapp.Globals;
import io.github.inflationx.calligraphy3.CalligraphyTypefaceSpan;
import io.github.inflationx.calligraphy3.TypefaceUtils;
import java.util.WeakHashMap;

public class Utilities
{
  private static final WeakHashMap<Integer, com.here.android.mpa.common.Image> mImages = new WeakHashMap();
  
  public Utilities() {}
  
  public static Bitmap getBitmap(int paramInt)
  {
    Drawable localDrawable = ContextCompat.getDrawable(Globals.getContext(), paramInt);
    Bitmap localBitmap = Bitmap.createBitmap(localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
    localDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  public static com.here.android.ui.common.Image getImage(int paramInt)
  {
    com.here.android.ui.common.Image localImage2 = (com.here.android.ui.common.Image)mImages.get(Integer.valueOf(paramInt));
    com.here.android.ui.common.Image localImage1 = localImage2;
    if (localImage2 == null)
    {
      localImage1 = new com.here.android.ui.common.Image();
      localImage1.setBitmap(getBitmap(paramInt));
      mImages.put(Integer.valueOf(paramInt), localImage1);
    }
    return localImage1;
  }
  
  public static boolean isConnectedOrConnecting()
  {
    Object localObject = (ConnectivityManager)Globals.getContext().getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).isConnectedOrConnecting())) {
        return true;
      }
    }
    return false;
  }
  
  public static int pxFromDp(float paramFloat)
  {
    return (int)(paramFloat * getContextgetResourcesgetDisplayMetricsdensity + 0.5F);
  }
  
  public static Spanned replaceBoldWithColor(String paramString, int paramInt)
  {
    paramString = Html.fromHtml(paramString);
    SpannableString localSpannableString = new SpannableString(paramString.toString());
    int j = paramString.length();
    int i = 0;
    StyleSpan[] arrayOfStyleSpan = (StyleSpan[])paramString.getSpans(0, j, StyleSpan.class);
    j = arrayOfStyleSpan.length;
    while (i < j)
    {
      StyleSpan localStyleSpan = arrayOfStyleSpan[i];
      if (localStyleSpan.getStyle() == 1) {
        localSpannableString.setSpan(new ForegroundColorSpan(paramInt), paramString.getSpanStart(localStyleSpan), paramString.getSpanEnd(localStyleSpan), 33);
      }
      i += 1;
    }
    return localSpannableString;
  }
  
  public static Spanned replaceBoldWithFontAndSize(String paramString1, String paramString2, float paramFloat)
  {
    paramString1 = Html.fromHtml(paramString1);
    paramString2 = TypefaceUtils.load(Globals.getContext().getAssets(), paramString2);
    SpannableString localSpannableString = new SpannableString(paramString1.toString());
    int j = paramString1.length();
    int i = 0;
    StyleSpan[] arrayOfStyleSpan = (StyleSpan[])paramString1.getSpans(0, j, StyleSpan.class);
    j = arrayOfStyleSpan.length;
    while (i < j)
    {
      StyleSpan localStyleSpan = arrayOfStyleSpan[i];
      if (localStyleSpan.getStyle() == 1)
      {
        localSpannableString.setSpan(new CalligraphyTypefaceSpan(paramString2), paramString1.getSpanStart(localStyleSpan), paramString1.getSpanEnd(localStyleSpan), 33);
        localSpannableString.setSpan(new AbsoluteSizeSpan(pxFromDp(paramFloat)), paramString1.getSpanStart(localStyleSpan), paramString1.getSpanEnd(localStyleSpan), 33);
      }
      i += 1;
    }
    return localSpannableString;
  }
  
  public static Spanned replaceStyleWithFont(Spanned paramSpanned, int paramInt, String paramString)
  {
    paramString = TypefaceUtils.load(Globals.getContext().getAssets(), paramString);
    SpannableString localSpannableString = new SpannableString(paramSpanned.toString());
    int j = paramSpanned.length();
    int i = 0;
    StyleSpan[] arrayOfStyleSpan = (StyleSpan[])paramSpanned.getSpans(0, j, StyleSpan.class);
    j = arrayOfStyleSpan.length;
    while (i < j)
    {
      StyleSpan localStyleSpan = arrayOfStyleSpan[i];
      if (localStyleSpan.getStyle() == paramInt) {
        localSpannableString.setSpan(new CalligraphyTypefaceSpan(paramString), paramSpanned.getSpanStart(localStyleSpan), paramSpanned.getSpanEnd(localStyleSpan), 33);
      }
      i += 1;
    }
    return localSpannableString;
  }
  
  public static void toastLong(int paramInt)
  {
    if (Globals.getContext() != null) {
      Events.runOnMainThread(new Utilities.2(paramInt));
    }
  }
  
  public static void toastLong(CharSequence paramCharSequence)
  {
    if (Globals.getContext() != null) {
      Events.runOnMainThread(new Utilities.4(paramCharSequence));
    }
  }
  
  public static void toastShort(int paramInt)
  {
    if (Globals.getContext() != null) {
      Events.runOnMainThread(new Utilities.1(paramInt));
    }
  }
  
  public static void toastShort(CharSequence paramCharSequence)
  {
    if (Globals.getContext() != null) {
      Events.runOnMainThread(new Utilities.3(paramCharSequence));
    }
  }
}
