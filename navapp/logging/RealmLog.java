package com.com.navapp.logging;

import android.os.Process;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class RealmLog
{
  private static final char[] PRIORITY_CHARS = { 48, 49, 86, 68, 73, 87, 69, 65 };
  private static final SimpleDateFormat TXTLOG_TS_FORMAT = new SimpleDateFormat("dd.MM.yy HH:mm.ss.SSS", Locale.US);
  
  private RealmLog() {}
  
  public static void Assert(String paramString, boolean paramBoolean) {}
  
  public static void append(String paramString1, String paramString2) {}
  
  public static void append(String paramString1, String paramString2, Object... paramVarArgs) {}
  
  public static void d(String paramString1, String paramString2) {}
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public static void d(String paramString1, String paramString2, Object... paramVarArgs) {}
  
  public static void d(String paramString, Throwable paramThrowable) {}
  
  public static void e(String paramString1, String paramString2) {}
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  static String format(String paramString, Object... paramVarArgs)
  {
    try
    {
      paramString = String.format(Locale.getDefault(), paramString, paramVarArgs);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramVarArgs = new StringBuilder();
      paramVarArgs.append("Log.format: \n");
      paramVarArgs.append(Log.getStackTraceString(paramString));
    }
    return paramVarArgs.toString();
  }
  
  public static void i(String paramString1, String paramString2) {}
  
  public static void i(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public static void logError(String paramString1, String paramString2, Object... paramVarArgs) {}
  
  public static void remove(String paramString1, String paramString2) {}
  
  public static void remove(String paramString1, String paramString2, Object... paramVarArgs) {}
  
  public static void v(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public static void v(String paramString1, String paramString2, Object... paramVarArgs) {}
  
  public static void w(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  private static void writeLog(int paramInt, String paramString1, String paramString2)
  {
    Log.println(paramInt, paramString1, paramString2);
    if (LogExt.isLoggingEnabled())
    {
      if (paramInt >= 0)
      {
        char[] arrayOfChar = PRIORITY_CHARS;
        if (paramInt < arrayOfChar.length)
        {
          c = arrayOfChar[paramInt];
          break label40;
        }
      }
      char c = '?';
      label40:
      TxtWriter.newLine(format("%s %X %c/%s: %s", new Object[] { TXTLOG_TS_FORMAT.format(new Date()), Integer.valueOf(Process.myTid()), Character.valueOf(c), paramString1, paramString2 }));
    }
  }
}
