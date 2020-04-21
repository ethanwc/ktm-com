package com.com.navapp.logging;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class LogStream
  extends OutputStream
{
  public static final PrintStream err = new PrintStream(new LogStream("System.err"));
  public static final PrintStream out = new PrintStream(new LogStream("System.out"));
  private final String address;
  private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
  
  private LogStream(String paramString)
  {
    address = paramString;
  }
  
  public void write(int paramInt)
  {
    if ((char)paramInt == '\n')
    {
      RealmLog.append(address, new String(baos.toByteArray()));
      baos.reset();
      return;
    }
    baos.write(paramInt);
  }
}
