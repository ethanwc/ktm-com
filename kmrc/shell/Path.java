package com.com.kmrc.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Path
{
  static final String DELIMITER = "/";
  static final String ROOT_CMD = "";
  private final List<String> elements;
  private final String path;
  
  Path(String paramString)
  {
    path = paramString.trim();
    elements = new ArrayList();
    if (paramString.startsWith("/")) {
      elements.add("");
    }
    paramString = new StringTokenizer(paramString, "/", false);
    while (paramString.hasMoreTokens()) {
      elements.add(paramString.nextToken());
    }
  }
  
  final List elements()
  {
    return elements;
  }
  
  public String getPath()
  {
    return path;
  }
  
  boolean isAbsolutePath()
  {
    return ((String)elements.get(0)).equals("");
  }
}
