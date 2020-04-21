package com.com.kmrc.shell;

import com.com.kmrc.KmrcVersion;
import com.com.kmrc.services.navigation.NavClientConnection;
import com.com.kmrc.services.navigation.NavServerConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Kshell
{
  private final AsyncPrinter asyncErrPrinter = new AsyncPrinter(System.err);
  private final AsyncPrinter asyncOutPrinter = new AsyncPrinter(System.out);
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> dist2Target = new com.com.kmrc.services.navigation.Value();
  @Option(name="-e", usage="execute kmrc command, multiple -e options are accepted")
  private String[] exec = new String[0];
  private boolean isClientConnected = false;
  private boolean isEchoCmd = false;
  @Option(name="-h", usage="print this help")
  private boolean isHelp = false;
  private boolean isVerbose = true;
  private String mString = System.getProperty("user.dir");
  private NavClientConnection navClientConnection;
  private NavServerConnection navServerConnection;
  private Node rootNode;
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> stations = new com.com.kmrc.services.navigation.Value();
  private TransportType transportType = TransportType.TCPIP;
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> turnDist = new com.com.kmrc.services.navigation.Value();
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> turnDistUnit = new com.com.kmrc.services.navigation.Value();
  @Expose
  private com.ktm.kmrc.services.navigation.Value<com.ktm.kmrc.services.navigation.TurnIcon> turnIcon = new com.com.kmrc.services.navigation.Value();
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> turnInfo = new com.com.kmrc.services.navigation.Value();
  @Expose
  private com.ktm.kmrc.services.navigation.Value<String> turnRoad = new com.com.kmrc.services.navigation.Value();
  private final Object waitLock = new Object();
  
  private Kshell(String[] paramArrayOfString)
  {
    parseArgs(paramArrayOfString);
    paramArrayOfString = new Kshell.1(this);
    int i = 3.$SwitchMap$com$ktm$kmrc$shell$TransportType[transportType.ordinal()];
    StringBuilder localStringBuilder;
    if ((i != 1) && (i != 2))
    {
      if ((i == 3) || (i == 4))
      {
        paramArrayOfString = System.err;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Transport mode ");
        localStringBuilder.append(transportType);
        localStringBuilder.append(" not yet implemented");
        paramArrayOfString.println(localStringBuilder.toString());
        System.exit(1);
      }
    }
    else {
      navServerConnection = new NavServerConnection(paramArrayOfString);
    }
    paramArrayOfString = new Kshell.2(this);
    i = 3.$SwitchMap$com$ktm$kmrc$shell$TransportType[transportType.ordinal()];
    if ((i != 1) && (i != 2))
    {
      if ((i != 3) && (i != 4)) {
        return;
      }
      paramArrayOfString = System.err;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Transport mode ");
      localStringBuilder.append(transportType);
      localStringBuilder.append(" not yet implemented");
      paramArrayOfString.println(localStringBuilder.toString());
      System.exit(1);
      return;
    }
    navClientConnection = new NavClientConnection(paramArrayOfString);
  }
  
  private String formatLogMsg(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append(new SimpleDateFormat("HH:mm:ss.SSS").format(Long.valueOf(System.currentTimeMillis())));
    localStringBuilder.append("] ");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    Kshell localKshell = new Kshell(paramArrayOfString);
    rootNode = new RootNode();
    Object localObject2 = new Node(rootNode, "clt", "enter client menu", null);
    Object localObject3 = new Node(rootNode, "srv", "enter server menu", null);
    Object localObject4 = new Node(rootNode, "bin", "enter bin menu", null);
    paramArrayOfString = new Node(rootNode, "nav", "enter navigation menu", null);
    Node localNode = new Node(rootNode, "batch", "enter batch menu", null);
    new Node(rootNode, "exit", "exit program", new CmdImpl.Exit(localKshell));
    new Node(rootNode, "?", "help", new CmdImpl.ListContent());
    new Node((Node)localObject2, "connect", "[<server address>]", "connect to server, default localhost", new CmdImpl.EstablishConnection(navClientConnection, localKshell));
    new Node((Node)localObject2, "disconnect", "disconnect connection to server", new CmdImpl.DisconnectConnection(navClientConnection));
    new Node((Node)localObject2, "reconnect", "<true|false>", "set reconnect mode of client", new CmdImpl.SetReconnectMode(navClientConnection));
    new Node((Node)localObject2, "wait4connected", "wait until client is connected ", new CmdImpl.Wait4Connect(localKshell));
    new Node((Node)localObject2, "wait4disconnected", "wait until client is disconnected ", new CmdImpl.Wait4DisConnect(localKshell));
    new Node((Node)localObject2, "?", "help", new CmdImpl.ListContent());
    new Node((Node)localObject3, "accept", "[listen address]", "accept connection, default any local address", new CmdImpl.EstablishConnection(navServerConnection, localKshell));
    new Node((Node)localObject3, "disconnect", "disconnect server service", new CmdImpl.DisconnectConnection(navServerConnection));
    new Node((Node)localObject3, "reconnect", "<true|false>", "set reconnect mode of server service", new CmdImpl.SetReconnectMode(navServerConnection));
    new Node((Node)localObject3, "?", "help", new CmdImpl.ListContent());
    new Node((Node)localObject4, "sleep", "<milliseconds>", "sleep for the specified number of milliseconds", new CmdImpl.Sleep());
    new Node((Node)localObject4, "tcp", "set TCP/IP communication mode", new CmdImpl.ComMode(localKshell, TransportType.TCPIP));
    new Node((Node)localObject4, "bt", "set Bluetooth RFCOMM communication mode", new CmdImpl.ComMode(localKshell, TransportType.BTRFCOMM));
    new Node((Node)localObject4, "echo", "[true|false]", "echo commands passed to KMRC shell (toggle or set)", new CmdImpl.Echo(localKshell));
    new Node((Node)localObject4, "?", "help", new CmdImpl.ListContent());
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("enter new turn icon (");
    ((StringBuilder)localObject2).append(com.com.kmrc.services.navigation.TurnIcon.allowedValues());
    ((StringBuilder)localObject2).append(")");
    new Node(paramArrayOfString, "ticon", "<image>", ((StringBuilder)localObject2).toString(), new CmdImpl.KeyValueSetTurnIcon(turnIcon));
    new Node(paramArrayOfString, "tdist", "<text>", "enter new distance to turn", new CmdImpl.KeyValueSetString(turnDist));
    new Node(paramArrayOfString, "tunit", "<text>", "enter new distance to turn unit", new CmdImpl.KeyValueSetString(turnDistUnit));
    new Node(paramArrayOfString, "tinfo", "<text>", "enter new turn info", new CmdImpl.KeyValueSetString(turnInfo));
    new Node(paramArrayOfString, "troad", "<text>", "enter new turn road", new CmdImpl.KeyValueSetString(turnRoad));
    new Node(paramArrayOfString, "eta", "<text>", "enter new estimated arrival time", new CmdImpl.KeyValueSetString(stations));
    new Node(paramArrayOfString, "dist", "<text>", "enter new distance to target", new CmdImpl.KeyValueSetString(dist2Target));
    new Node(paramArrayOfString, "e", "list entered data", new CmdImpl.ListEntered(localKshell));
    new Node(paramArrayOfString, "gon", "[trace string]", "set guidance on", new CmdImpl.NavGon(navClientConnection));
    new Node(paramArrayOfString, "goff", "[trace string]", "set guidance off", new CmdImpl.NavGoff(navClientConnection));
    new Node(paramArrayOfString, "re", "[trace string]", "set processing rerouting", new CmdImpl.NavRerouting(navClientConnection));
    new Node(paramArrayOfString, "gps", "[trace string]", "gps signal is available", new CmdImpl.NavGpsAvailable(navClientConnection));
    new Node(paramArrayOfString, "gpsoff", "[trace string]", "gps signal lost", new CmdImpl.NavGpsLost(navClientConnection));
    new Node(paramArrayOfString, "mup", "[trace string]", "update maneuver (ticon, troad, tinfo, tdist & tunit)", new CmdImpl.ManeuverUpdate(localKshell));
    new Node(paramArrayOfString, "lup", "[trace string]", "update location (tdist, tunit, dist & eta)", new CmdImpl.LocationUpdate(localKshell));
    new Node(paramArrayOfString, "wait4transmitted", "wait untill all requests were sent to KMRC", new CmdImpl.Wait4Transmitted(navClientConnection));
    new Node(paramArrayOfString, "ls", "list navigation widgets states", new CmdImpl.NavEngineToString(navClientConnection));
    new Node(paramArrayOfString, "?", "help", new CmdImpl.ListContent());
    new Node(localNode, "pwd", "result current working directory", new CmdImpl.BatchPwd(localKshell));
    new Node(localNode, "cd", "<path>", "change current working directory", new CmdImpl.BatchCd(localKshell));
    new Node(localNode, "cat", "<path>", "cat content of batch file", new CmdImpl.BatchCat(localKshell));
    new Node(localNode, "source", "<filename>", "source and execute batch file", new CmdImpl.BatchSource(localKshell));
    new Node(localNode, "ls", "list current of working directory", new CmdImpl.BatchLs(localKshell));
    new Node(localNode, "?", "help", new CmdImpl.ListContent());
    localObject2 = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    localObject3 = exec;
    int j = localObject3.length;
    int i = 0;
    paramArrayOfString = rootNode;
    while (i < j)
    {
      localNode = localObject3[i];
      if (isEchoCmd) {}
      try
      {
        localKshell.logCmd("passed: ", localNode);
        localObject4 = rootNode;
        localNode = paramArrayOfString.process((Node)localObject4, localNode);
        paramArrayOfString = localNode;
      }
      catch (Exception localException1)
      {
        localKshell.logErr(localException1.getMessage());
      }
      i += 1;
    }
    localObject3 = KmrcVersion.getVersion();
    for (;;)
    {
      Object localObject1 = System.out;
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("kmrc.");
      ((StringBuilder)localObject4).append((String)localObject3);
      ((StringBuilder)localObject4).append(" ");
      ((StringBuilder)localObject4).append(paramArrayOfString.absPath());
      ((StringBuilder)localObject4).append(" -> ");
      ((PrintStream)localObject1).print(((StringBuilder)localObject4).toString());
      try
      {
        localObject1 = ((BufferedReader)localObject2).readLine();
        if (isEchoCmd) {}
        try
        {
          localKshell.logCmd("entered: ", (String)localObject1);
          localObject4 = rootNode;
          localObject1 = paramArrayOfString.process((Node)localObject4, (String)localObject1);
          paramArrayOfString = (String[])localObject1;
        }
        catch (Exception localException2)
        {
          localKshell.logErr(localException2.getMessage());
        }
      }
      catch (IOException localIOException)
      {
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("Error: ");
        ((StringBuilder)localObject4).append(localIOException.getMessage());
        localKshell.logErr(((StringBuilder)localObject4).toString());
      }
    }
  }
  
  private void parseArgs(String[] paramArrayOfString)
  {
    CmdLineParser localCmdLineParser = new CmdLineParser(this);
    try
    {
      localCmdLineParser.parseArgument(paramArrayOfString);
    }
    catch (CmdLineException paramArrayOfString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error: ");
      localStringBuilder.append(paramArrayOfString.getMessage());
      logErr(localStringBuilder.toString());
      printHelp(localCmdLineParser, System.err);
      System.exit(1);
    }
    if (isHelp)
    {
      printHelp(localCmdLineParser, System.out);
      System.exit(0);
    }
  }
  
  private void printHelp(CmdLineParser paramCmdLineParser, PrintStream paramPrintStream)
  {
    paramPrintStream.println("java -jar kmrc-all-1.0.jar [options...] arguments...");
    paramCmdLineParser.printUsage(paramPrintStream);
    paramPrintStream.println();
  }
  
  String getString()
  {
    return mString;
  }
  
  public boolean isEchoCmd()
  {
    return isEchoCmd;
  }
  
  String listeEntered()
  {
    GsonBuilder localGsonBuilder = new GsonBuilder();
    localGsonBuilder.serializeNulls();
    localGsonBuilder.excludeFieldsWithoutExposeAnnotation();
    localGsonBuilder.setPrettyPrinting();
    return localGsonBuilder.create().toJson(this);
  }
  
  void log(String paramString)
  {
    asyncOutPrinter.print(formatLogMsg(paramString));
  }
  
  void logCmd(String paramString1, String paramString2)
  {
    if ((!paramString2.isEmpty()) && (!paramString2.matches("^\\s*#.*$")))
    {
      if (paramString2.matches("^\\s.*\\s$")) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append("'");
      localStringBuilder.append(paramString2.trim());
      localStringBuilder.append("'");
      log(localStringBuilder.toString());
    }
  }
  
  void logErr(String paramString)
  {
    asyncErrPrinter.print(formatLogMsg(paramString));
  }
  
  NavClientConnection navClient()
  {
    return navClientConnection;
  }
  
  NavServerConnection navServer()
  {
    return navServerConnection;
  }
  
  Node rootNode()
  {
    return rootNode;
  }
  
  void setCwd(String paramString)
  {
    mString = paramString;
  }
  
  public void setEchoCmd(boolean paramBoolean)
  {
    isEchoCmd = paramBoolean;
  }
  
  void setTransportType(TransportType paramTransportType)
  {
    transportType = paramTransportType;
  }
  
  public void toggleEchoCmd()
  {
    isEchoCmd ^= true;
  }
  
  TransportType transportType()
  {
    return transportType;
  }
  
  void updateLocation()
  {
    navClient().onNewLocation((String)turnDist.get(), (String)turnDistUnit.get(), (String)dist2Target.get(), (String)stations.get());
  }
  
  void updateLocation(String paramString)
  {
    navClient().onNewLocation((String)turnDist.get(), (String)turnDistUnit.get(), (String)dist2Target.get(), (String)stations.get(), paramString);
  }
  
  void updateManeuver()
  {
    navClient().onNewGuidanceManeuver((com.com.kmrc.services.navigation.TurnIcon)turnIcon.get(), (String)turnRoad.get(), (String)turnInfo.get(), (String)turnDist.get(), (String)turnDistUnit.get());
  }
  
  void updateManeuver(String paramString)
  {
    navClient().onNewGuidanceManeuver((com.com.kmrc.services.navigation.TurnIcon)turnIcon.get(), (String)turnRoad.get(), (String)turnInfo.get(), (String)turnDist.get(), (String)turnDistUnit.get(), paramString);
  }
  
  void wait4ClientConnectionState(boolean paramBoolean)
  {
    Object localObject = waitLock;
    try
    {
      while (isClientConnected != paramBoolean) {
        waitLock.wait();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      try
      {
        throw localThrowable;
      }
      catch (InterruptedException localInterruptedException)
      {
        ((InterruptedException)localInterruptedException).printStackTrace();
      }
    }
  }
}
