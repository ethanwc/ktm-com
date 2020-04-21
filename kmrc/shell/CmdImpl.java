package com.com.kmrc.shell;

import com.com.kmrc.TransportMap;
import com.com.kmrc.services.navigation.NavClientConnection;
import com.com.kmrc.services.navigation.NavRepo;
import com.com.kmrc.services.navigation.NavServerConnection;
import com.com.kmrc.services.navigation.TurnIcon;
import com.com.kmrc.services.navigation.Value;
import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.socket.btbluecove.BtClientKSocket;
import com.com.kmrc.upgrade.socket.btbluecove.BtServerKSocket;
import com.com.kmrc.upgrade.socket.labs.TcpClientKSocket;
import com.com.kmrc.upgrade.socket.labs.TcpServerKSocket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CmdImpl
{
  CmdImpl() {}
  
  private static String join(List paramList)
  {
    int j = paramList.size();
    int i = 0;
    if (j == 1) {
      return (String)paramList.get(0);
    }
    String str = "";
    while (i < paramList.size())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append((String)paramList.get(i));
      if (i != paramList.size() - 1) {
        str = " ";
      } else {
        str = "";
      }
      localStringBuilder.append(str);
      str = localStringBuilder.toString();
      i += 1;
    }
    return str;
  }
  
  private static List readFile(String paramString1, String paramString2)
  {
    Object localObject1 = paramString1;
    if (!paramString1.startsWith(File.separator))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramString2);
      ((StringBuilder)localObject1).append(File.separator);
      ((StringBuilder)localObject1).append(paramString1);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    paramString2 = new File((String)localObject1).getAbsoluteFile();
    paramString1 = new ArrayList();
    try
    {
      boolean bool = paramString2.exists();
      if (bool)
      {
        bool = paramString2.isFile();
        if (bool)
        {
          bool = paramString2.canRead();
          if (bool)
          {
            paramString2 = new FileInputStream(paramString2.getCanonicalPath());
            Object localObject2 = StandardCharsets.UTF_8;
            paramString2 = new BufferedReader(new InputStreamReader(paramString2, (Charset)localObject2));
            for (;;)
            {
              localObject2 = paramString2.readLine();
              if (localObject2 == null) {
                break;
              }
              paramString1.add(localObject2);
            }
            paramString2.close();
            return paramString1;
          }
          paramString1 = new StringBuilder();
          paramString1.append((String)localObject1);
          paramString1.append(" not a file");
          paramString1 = new RuntimeException(paramString1.toString());
          throw paramString1;
        }
        paramString1 = new StringBuilder();
        paramString1.append((String)localObject1);
        paramString1.append(" not a file");
        paramString1 = new RuntimeException(paramString1.toString());
        throw paramString1;
      }
      paramString1 = new StringBuilder();
      paramString1.append((String)localObject1);
      paramString1.append(" does not exist");
      paramString1 = new RuntimeException(paramString1.toString());
      throw paramString1;
    }
    catch (Exception paramString1)
    {
      paramString2 = new StringBuilder();
      paramString2.append((String)localObject1);
      paramString2.append(" :");
      paramString2.append(((Exception)paramString1).getMessage());
      throw new RuntimeException(paramString2.toString());
    }
  }
  
  public class BatchCat
    implements Node.CmdNodeListener
  {
    BatchCat() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 1)
      {
        paramNode = CmdImpl.readFile((String)paramCommand.parameters().get(0), getString()).iterator();
        while (paramNode.hasNext())
        {
          paramCommand = (String)paramNode.next();
          System.out.println(paramCommand);
        }
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class BatchCd
    implements Node.CmdNodeListener
  {
    BatchCd() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 1)
      {
        paramCommand = (String)paramCommand.parameters().get(0);
        paramNode = paramCommand;
        if (!paramCommand.startsWith(File.separator))
        {
          paramNode = new StringBuilder();
          paramNode.append(getString());
          paramNode.append(File.separator);
          paramNode.append(paramCommand);
          paramNode = paramNode.toString();
        }
        paramCommand = new File(paramNode).getAbsoluteFile();
        if (paramCommand.exists())
        {
          if (paramCommand.isDirectory())
          {
            localObject = CmdImpl.this;
            try
            {
              ((Kshell)localObject).setCwd(paramCommand.getCanonicalPath());
              return;
            }
            catch (IOException paramCommand)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("cd ");
              ((StringBuilder)localObject).append(paramNode);
              ((StringBuilder)localObject).append(" :");
              ((StringBuilder)localObject).append(paramCommand.getMessage());
              throw new RuntimeException(((StringBuilder)localObject).toString());
            }
          }
          paramCommand = new StringBuilder();
          paramCommand.append("cd ");
          paramCommand.append(paramNode);
          paramCommand.append(" not a directory");
          throw new RuntimeException(paramCommand.toString());
        }
        paramCommand = new StringBuilder();
        paramCommand.append("cd ");
        paramCommand.append(paramNode);
        paramCommand.append(" does not exist");
        throw new RuntimeException(paramCommand.toString());
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("'");
      ((StringBuilder)localObject).append(paramCommand.line());
      ((StringBuilder)localObject).append("' parameter error, usage: ");
      ((StringBuilder)localObject).append(paramNode.getValue());
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramNode.params());
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
  }
  
  public class BatchLs
    implements Node.CmdNodeListener
  {
    BatchLs() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        paramNode = new File(getString());
        System.out.println(getString());
        paramNode = paramNode.list();
        int j = paramNode.length;
        int i = 0;
        while (i < j)
        {
          paramCommand = paramNode[i];
          System.out.println(paramCommand);
          i += 1;
        }
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class BatchPwd
    implements Node.CmdNodeListener
  {
    BatchPwd() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        System.out.println(getString());
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class BatchSource
    implements Node.CmdNodeListener
  {
    BatchSource() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 1)
      {
        paramCommand = CmdImpl.readFile((String)paramCommand.parameters().get(0), getString()).iterator();
        for (;;)
        {
          if (!paramCommand.hasNext()) {
            return;
          }
          localObject = (String)paramCommand.next();
          Kshell localKshell = CmdImpl.this;
          try
          {
            boolean bool = localKshell.isEchoCmd();
            if (bool)
            {
              localKshell = CmdImpl.this;
              localKshell.logCmd("Batch Cmd: ", (String)localObject);
            }
            localKshell = CmdImpl.this;
            paramNode = paramNode.process(localKshell.rootNode(), (String)localObject);
          }
          catch (Exception paramNode)
          {
            System.err.println(paramNode.getMessage());
            System.err.flush();
            return;
          }
        }
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("'");
      ((StringBuilder)localObject).append(paramCommand.line());
      ((StringBuilder)localObject).append("' parameter error, usage: ");
      ((StringBuilder)localObject).append(paramNode.getValue());
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramNode.params());
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
  }
  
  public class ComMode
    implements Node.CmdNodeListener
  {
    private final TransportType transportType;
    
    public ComMode(TransportType paramTransportType)
    {
      transportType = paramTransportType;
    }
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        setTransportType(transportType);
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.usage());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class DisconnectConnection
    implements Node.CmdNodeListener
  {
    DisconnectConnection() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      paramNode = CmdImpl.this;
      try
      {
        paramNode.disconnect();
        return;
      }
      catch (Exception paramNode)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("'");
        localStringBuilder.append(paramCommand.command());
        localStringBuilder.append("' : ");
        localStringBuilder.append(paramNode.getMessage());
        throw new RuntimeException(localStringBuilder.toString());
      }
    }
  }
  
  public class Echo
    implements Node.CmdNodeListener
  {
    Echo() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          toggleEchoCmd();
        }
        else if (((String)paramCommand.parameters().get(0)).equals("true"))
        {
          setEchoCmd(true);
        }
        else
        {
          if (!((String)paramCommand.parameters().get(0)).equals("false")) {
            break label126;
          }
          setEchoCmd(false);
        }
        if (isEchoCmd())
        {
          log("echoing enabled");
          return;
        }
        log("echoing disabled");
        return;
        label126:
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("'");
        localStringBuilder.append(paramCommand.line());
        localStringBuilder.append("' unknown parameter '");
        localStringBuilder.append((String)paramCommand.parameters().get(0));
        localStringBuilder.append("', usage: ");
        localStringBuilder.append(paramNode.getValue());
        localStringBuilder.append(" ");
        localStringBuilder.append(paramNode.params());
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class EstablishConnection
    implements Node.CmdNodeListener
  {
    private final Kshell kshell;
    
    EstablishConnection(Kshell paramKshell)
    {
      kshell = paramKshell;
    }
    
    public void execute(Node paramNode, Command paramCommand)
    {
      Object localObject = CmdImpl.1.$SwitchMap$com$ktm$kmrc$shell$TransportType;
      Kshell localKshell = kshell;
      try
      {
        int i = localKshell.transportType().ordinal();
        i = localObject[i];
        if (i != 1)
        {
          if (i != 2)
          {
            if ((i != 3) && (i != 4)) {
              return;
            }
            paramNode = new StringBuilder();
            paramNode.append("'");
            paramNode.append(paramCommand.line());
            paramNode.append("Transport type ");
            paramCommand = kshell;
            paramNode.append(paramCommand.toString());
            paramNode.append(" not yet implemented");
            paramNode = new IllegalArgumentException(paramNode.toString());
            throw paramNode;
          }
          if ((CmdImpl.this instanceof NavServerConnection))
          {
            i = paramCommand.parameters().size();
            if (i == 0)
            {
              i = paramCommand.parameters().size();
              if (i <= 1)
              {
                paramNode = CmdImpl.this;
                paramCommand = TransportMap.NAVIGATION;
                paramNode.establish(new BtServerKSocket("localhost", paramCommand.uuidString()));
                return;
              }
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("'");
              ((StringBuilder)localObject).append(paramCommand.line());
              ((StringBuilder)localObject).append("' parameter error, usage: ");
              ((StringBuilder)localObject).append(paramNode.getValue());
              ((StringBuilder)localObject).append(" ");
              ((StringBuilder)localObject).append(paramNode.params());
              paramNode = new IllegalArgumentException(((StringBuilder)localObject).toString());
              throw paramNode;
            }
          }
          if ((CmdImpl.this instanceof NavClientConnection))
          {
            i = paramCommand.parameters().size();
            if (i != 0)
            {
              paramNode = CmdImpl.this;
              paramCommand = CmdImpl.join(paramCommand.parameters());
              localObject = TransportMap.NAVIGATION;
              paramNode.establish(new BtClientKSocket(paramCommand, ((TransportMap)localObject).uuidString()));
              return;
            }
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("'");
            ((StringBuilder)localObject).append(paramCommand.line());
            ((StringBuilder)localObject).append("' parameter error, usage: ");
            ((StringBuilder)localObject).append(paramNode.getValue());
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(paramNode.params());
            paramNode = new IllegalArgumentException(((StringBuilder)localObject).toString());
            throw paramNode;
          }
        }
        else
        {
          i = paramCommand.parameters().size();
          if (i <= 1)
          {
            if ((CmdImpl.this instanceof NavServerConnection))
            {
              i = paramCommand.parameters().size();
              if (i == 0)
              {
                paramNode = CmdImpl.this;
                paramCommand = TransportMap.NAVIGATION;
                paramNode.establish(new TcpServerKSocket("0.0.0.0", paramCommand.port()));
                return;
              }
            }
            if ((CmdImpl.this instanceof NavClientConnection))
            {
              i = paramCommand.parameters().size();
              if (i == 0)
              {
                paramNode = CmdImpl.this;
                paramCommand = TransportMap.NAVIGATION;
                paramNode.establish(new TcpClientKSocket("127.0.0.1", paramCommand.port()));
                return;
              }
            }
            paramNode = CmdImpl.this;
            paramCommand = paramCommand.parameters().get(0);
            paramCommand = (String)paramCommand;
            localObject = TransportMap.NAVIGATION;
            paramNode.establish(new TcpClientKSocket(paramCommand, ((TransportMap)localObject).port()));
            return;
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("'");
          ((StringBuilder)localObject).append(paramCommand.line());
          ((StringBuilder)localObject).append("' parameter error, usage: ");
          ((StringBuilder)localObject).append(paramNode.getValue());
          ((StringBuilder)localObject).append(" ");
          ((StringBuilder)localObject).append(paramNode.params());
          paramNode = new IllegalArgumentException(((StringBuilder)localObject).toString());
          throw paramNode;
        }
      }
      catch (IOException paramNode)
      {
        paramCommand = System.err;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Error establish connection: ");
        ((StringBuilder)localObject).append(((IOException)paramNode).getMessage());
        paramCommand.println(((StringBuilder)localObject).toString());
      }
    }
  }
  
  public class Exit
    implements Node.CmdNodeListener
  {
    Exit() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      paramNode = CmdImpl.this;
      StringBuilder localStringBuilder;
      try
      {
        paramNode.navClient().disconnect();
      }
      catch (Exception paramNode)
      {
        paramCommand = System.err;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error stopping client: ");
        localStringBuilder.append(paramNode.getMessage());
        paramCommand.println(localStringBuilder.toString());
      }
      paramNode = CmdImpl.this;
      try
      {
        paramNode.navServer().disconnect();
      }
      catch (Exception paramNode)
      {
        paramCommand = System.err;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error stopping server: ");
        localStringBuilder.append(paramNode.getMessage());
        paramCommand.println(localStringBuilder.toString());
      }
      System.exit(1);
    }
  }
  
  public class KeyValueSetString
    implements Node.CmdNodeListener
  {
    KeyValueSetString() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      int i = 0;
      while (i < paramCommand.parameters().size())
      {
        if (((String)paramCommand.parameters().get(i)).equals("\"\"")) {
          paramCommand.parameters().set(i, "");
        }
        i += 1;
      }
      if (paramCommand.parameters().size() == 0)
      {
        put(null);
        return;
      }
      put(CmdImpl.join(paramCommand.parameters()));
    }
  }
  
  public class KeyValueSetTurnIcon
    implements Node.CmdNodeListener
  {
    KeyValueSetTurnIcon() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      int i = 0;
      while (i < paramCommand.parameters().size())
      {
        if (((String)paramCommand.parameters().get(i)).equals("\"\"")) {
          paramCommand.parameters().set(i, "");
        }
        i += 1;
      }
      if (paramCommand.parameters().size() == 0)
      {
        put(null);
        return;
      }
      put(TurnIcon.valueOf(CmdImpl.join(paramCommand.parameters())));
    }
  }
  
  public class ListContent
    implements Node.CmdNodeListener
  {
    public ListContent() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      paramCommand = paramNode.parent().getChildren();
      int j = 0;
      int i = 0;
      while (paramCommand.hasNext())
      {
        localObject = (Node)paramCommand.next();
        k = i;
        if (((Node)localObject).getValue().length() + ((Node)localObject).params().length() > i) {
          k = ((Node)localObject).getValue().length() + ((Node)localObject).params().length();
        }
        i = k;
        if (((Node)localObject).params().length() > 0)
        {
          j = 1;
          i = k;
        }
      }
      int k = i;
      if (j != 0) {
        k = i + 1;
      }
      paramCommand = String.format("\t%%-%ds : %%s", new Object[] { Integer.valueOf(k) });
      Object localObject = paramNode.parent().getChildren();
      while (((Iterator)localObject).hasNext())
      {
        Node localNode = (Node)((Iterator)localObject).next();
        PrintStream localPrintStream = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localNode.getValue());
        if (localNode.params().length() > 0)
        {
          paramNode = new StringBuilder();
          paramNode.append(" ");
          paramNode.append(localNode.params());
          paramNode = paramNode.toString();
        }
        else
        {
          paramNode = "";
        }
        localStringBuilder.append(paramNode);
        localPrintStream.println(String.format(paramCommand, new Object[] { localStringBuilder.toString(), localNode.usage() }));
      }
    }
  }
  
  public class ListEntered
    implements Node.CmdNodeListener
  {
    ListEntered() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        System.out.println(listeEntered());
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class LocationUpdate
    implements Node.CmdNodeListener
  {
    LocationUpdate() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          updateLocation();
          return;
        }
        updateLocation((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class ManeuverUpdate
    implements Node.CmdNodeListener
  {
    ManeuverUpdate() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          updateManeuver();
          return;
        }
        updateManeuver((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavEngineToString
    implements Node.CmdNodeListener
  {
    NavEngineToString() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        System.out.println(navRepo().toJson());
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavGoff
    implements Node.CmdNodeListener
  {
    NavGoff() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          onGuidanceFinished();
          return;
        }
        onGuidanceFinished((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavGon
    implements Node.CmdNodeListener
  {
    NavGon() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          onGuidanceStarted();
          return;
        }
        onGuidanceStarted((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavGpsAvailable
    implements Node.CmdNodeListener
  {
    NavGpsAvailable() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          onGpsSignalAvailable();
          return;
        }
        onGpsSignalAvailable((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavGpsLost
    implements Node.CmdNodeListener
  {
    NavGpsLost() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          onGpsSignalLost();
          return;
        }
        onGpsSignalLost((String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class NavRerouting
    implements Node.CmdNodeListener
  {
    NavRerouting() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() <= 1)
      {
        if (paramCommand.parameters().size() == 0)
        {
          onRerouting("Rerouting");
          return;
        }
        onRerouting("Rerouting", (String)paramCommand.parameters().get(0));
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class SetReconnectMode
    implements Node.CmdNodeListener
  {
    SetReconnectMode() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 1)
      {
        localObject = (String)paramCommand.parameters().get(0);
        int i = -1;
        int j = ((String)localObject).hashCode();
        if (j != 3569038)
        {
          if ((j == 97196323) && (((String)localObject).equals("false"))) {
            i = 1;
          }
        }
        else if (((String)localObject).equals("true")) {
          i = 0;
        }
        if (i != 0)
        {
          if (i == 1)
          {
            setShallReconnect(false);
            return;
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("'");
          ((StringBuilder)localObject).append(paramCommand.line());
          ((StringBuilder)localObject).append("' parameter error, usage: ");
          ((StringBuilder)localObject).append(paramNode.getValue());
          ((StringBuilder)localObject).append(" ");
          ((StringBuilder)localObject).append(paramNode.params());
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
        setShallReconnect(true);
        return;
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("'");
      ((StringBuilder)localObject).append(paramCommand.line());
      ((StringBuilder)localObject).append("' parameter error, usage: ");
      ((StringBuilder)localObject).append(paramNode.getValue());
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramNode.params());
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
  }
  
  public class Sleep
    implements Node.CmdNodeListener
  {
    public Sleep() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 1) {
        try
        {
          paramNode = paramCommand.parameters().get(0);
          paramNode = (String)paramNode;
          int i = Integer.parseInt(paramNode);
          long l = i;
          Thread.sleep(l);
          return;
        }
        catch (Exception paramNode)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("'");
          localStringBuilder.append(paramCommand.line());
          localStringBuilder.append("' runtime error: ");
          localStringBuilder.append(paramNode.getMessage());
          throw new RuntimeException(localStringBuilder.toString());
        }
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.usage());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public class Wait4Connect
    implements Node.CmdNodeListener
  {
    Wait4Connect() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      wait4ClientConnectionState(true);
    }
  }
  
  public class Wait4DisConnect
    implements Node.CmdNodeListener
  {
    Wait4DisConnect() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      wait4ClientConnectionState(false);
    }
  }
  
  public class Wait4Transmitted
    implements Node.CmdNodeListener
  {
    Wait4Transmitted() {}
    
    public void execute(Node paramNode, Command paramCommand)
    {
      if (paramCommand.parameters().size() == 0)
      {
        wait4RequestsSubmitted();
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("'");
      localStringBuilder.append(paramCommand.line());
      localStringBuilder.append("' parameter error, usage: ");
      localStringBuilder.append(paramNode.getValue());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramNode.params());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
}
