package com.com.kmrc.shell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Command
{
  private String command;
  private String line;
  private List<String> parameters = new ArrayList();
  
  Command(String paramString)
  {
    line = paramString;
    parseCommandLine();
  }
  
  private void parseCommandLine()
  {
    Object localObject = line;
    try
    {
      String str = ((String)localObject).trim();
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (((String)localObject).isEmpty()) {
      return;
    }
    localObject = ((String)localObject).split(" +");
    command = localObject[0];
    if (localObject.length > 1)
    {
      parameters.addAll(Arrays.asList((Object[])localObject).subList(1, localObject.length));
      return;
    }
  }
  
  String command()
  {
    String str = command;
    if (str == null) {
      return "";
    }
    return str;
  }
  
  String line()
  {
    return line;
  }
  
  List parameters()
  {
    return parameters;
  }
}
