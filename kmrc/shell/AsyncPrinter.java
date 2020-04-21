package com.com.kmrc.shell;

import java.io.PrintStream;
import java.util.LinkedList;

public class AsyncPrinter
{
  private final LinkedList<String> printQueue = new LinkedList();
  
  AsyncPrinter(PrintStream paramPrintStream)
  {
    new Thread(new AsyncPrinter.1(this, paramPrintStream)).start();
  }
  
  public void print(String paramString)
  {
    LinkedList localLinkedList = printQueue;
    try
    {
      printQueue.add(paramString);
      printQueue.notify();
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
}
