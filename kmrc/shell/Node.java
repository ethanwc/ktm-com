package com.com.kmrc.shell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class Node
{
  private List<com.ktm.kmrc.shell.Node> children = new ArrayList();
  private final CmdNodeListener listener;
  private final String params;
  private final Node parent;
  private final String usage;
  private final String value;
  
  Node(Node paramNode, String paramString1, String paramString2, CmdNodeListener paramCmdNodeListener)
  {
    params = "";
    usage = paramString2;
    parent = paramNode;
    listener = paramCmdNodeListener;
    if (paramNode == null)
    {
      value = "";
      return;
    }
    value = paramString1;
    children.add(this);
  }
  
  Node(Node paramNode, String paramString1, String paramString2, String paramString3, CmdNodeListener paramCmdNodeListener)
  {
    params = paramString2;
    usage = paramString3;
    parent = paramNode;
    listener = paramCmdNodeListener;
    if (paramNode == null)
    {
      value = "";
      return;
    }
    value = paramString1;
    children.add(this);
  }
  
  private Node findNode(Node paramNode, Path paramPath, int paramInt)
  {
    int i;
    if (((String)paramPath.elements().get(paramInt)).equals(".."))
    {
      if (parent != null)
      {
        i = paramPath.elements().size();
        paramInt += 1;
        if (i == paramInt) {
          return parent;
        }
        return parent.findNode(paramNode, paramPath, paramInt);
      }
      throw new NoSuchElementException("no such element");
    }
    if ((this == paramNode) && (((String)paramPath.elements().get(paramInt)).equals("")))
    {
      i = paramPath.elements().size();
      paramInt += 1;
      if (i == paramInt) {
        return paramNode;
      }
      return findNode(paramNode, paramPath, paramInt);
    }
    Iterator localIterator = children.iterator();
    while (localIterator.hasNext())
    {
      Node localNode = (Node)localIterator.next();
      if (value.equals(paramPath.elements().get(paramInt)))
      {
        i = paramPath.elements().size();
        paramInt += 1;
        if (i == paramInt) {
          return localNode;
        }
        return localNode.findNode(paramNode, paramPath, paramInt);
      }
    }
    paramNode = new StringBuilder();
    paramNode.append(paramPath.getPath());
    paramNode.append(" no such element");
    throw new NoSuchElementException(paramNode.toString());
  }
  
  String absPath()
  {
    ArrayList localArrayList = new ArrayList();
    for (Object localObject = this; localObject != null; localObject = parent) {
      localArrayList.add(value);
    }
    Collections.reverse(localArrayList);
    localObject = new StringBuilder();
    int i = 0;
    int j = localArrayList.size();
    while (i < j)
    {
      ((StringBuilder)localObject).append((String)localArrayList.get(i));
      if ((((String)localArrayList.get(i)).equals("")) || (i < localArrayList.size() - 1)) {
        ((StringBuilder)localObject).append("/");
      }
      i += 1;
    }
    return ((StringBuilder)localObject).toString();
  }
  
  Iterator getChildren()
  {
    return children.iterator();
  }
  
  String getValue()
  {
    return value;
  }
  
  String params()
  {
    return params;
  }
  
  Node parent()
  {
    return parent;
  }
  
  Node process(Node paramNode, String paramString)
  {
    if ((!paramString.isEmpty()) && (!paramString.matches("^\\s*#.*$")))
    {
      if (paramString.matches("^\\s.*\\s$")) {
        return this;
      }
      paramString = new Command(paramString);
      Object localObject = new Path(paramString.command());
      if (((Path)localObject).isAbsolutePath()) {
        paramNode = paramNode.findNode(paramNode, (Path)localObject, 0);
      } else {
        paramNode = findNode(paramNode, (Path)localObject, 0);
      }
      if (children.size() == 0)
      {
        localObject = listener;
        if (localObject != null)
        {
          ((CmdNodeListener)localObject).execute(paramNode, paramString);
          return this;
        }
      }
      if (paramString.parameters().size() == 0) {
        return paramNode;
      }
      paramNode = new StringBuilder();
      paramNode.append(paramString.line());
      paramNode.append(": too much parameters");
      throw new IllegalArgumentException(paramNode.toString());
    }
    return this;
  }
  
  String usage()
  {
    return usage;
  }
  
  public abstract interface CmdNodeListener
  {
    public abstract void execute(Node paramNode, Command paramCommand);
  }
}
