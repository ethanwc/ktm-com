package com.com.navapp.logging;

import com.com.navapp.utilities.Storage;

class TxtWriter
{
  private static final String INTEGER = com.ktm.navapp.logging.TxtWriter.class.getSimpleName();
  private static final String KEY_FILENAME;
  private static String sFileName = null;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(INTEGER);
    localStringBuilder.append(".FILENAME");
    KEY_FILENAME = localStringBuilder.toString();
  }
  
  TxtWriter() {}
  
  static void close()
  {
    if (init())
    {
      RealmLog.append("[CLOSE]", sFileName);
      String str = KEY_FILENAME;
      sFileName = "";
      Storage.put(str, "");
    }
  }
  
  private static boolean init()
  {
    if (sFileName == null) {
      sFileName = Storage.getSilent(KEY_FILENAME, "");
    }
    return sFileName.isEmpty() ^ true;
  }
  
  static void newLine(String paramString)
  {
    if (init()) {
      writeLine(paramString);
    }
  }
  
  static void open(String paramString)
  {
    close();
    String str = KEY_FILENAME;
    sFileName = paramString;
    Storage.put(str, paramString);
    if (init())
    {
      System.setOut(LogStream.out);
      System.setErr(LogStream.err);
      RealmLog.append("[OPEN]", sFileName);
    }
  }
  
  private static void writeLine(String paramString)
  {
    FileHandler.writeLine(sFileName, paramString);
  }
}
