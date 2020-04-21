package com.com.kmrc.services.navigation;

import com.com.kmrc.services.navigation.dump.NavRepoDump;
import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.Connection.State;
import com.com.kmrc.upgrade.KSocket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class NavClientConnection
  extends Connection
  implements NavigationRequest
{
  private final Thread SenderThread;
  private final AtomicInteger countAtomic;
  private final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
  private boolean isSending = false;
  private final NavRepo navRepo = new NavRepo();
  private final LinkedList<com.ktm.kmrc.services.navigation.NavClientConnection.PushCmd> sendQueue = new LinkedList();
  private final NavServiceListener serviceListener;
  
  public NavClientConnection(NavServiceListener paramNavServiceListener)
  {
    super(new NavClientConnectionListener(paramNavServiceListener));
    serviceListener = paramNavServiceListener;
    navRepo.modifyUiContext(UiContext.DEFAULT);
    navRepo.modifyGpsIconWidget(GpsIcon.ORIGINAL, Visibility.FULL);
    countAtomic = new AtomicInteger(0);
    SenderThread = new Thread(new Sender(null));
    SenderThread.start();
  }
  
  public NavRepo navRepo()
  {
    return navRepo;
  }
  
  public void onGpsSignalAvailable()
  {
    navRepo.setGpsSignalAvailable(this, "gps");
  }
  
  public void onGpsSignalAvailable(String paramString)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gps#");
    localStringBuilder.append(paramString);
    localNavRepo.setGpsSignalAvailable(this, localStringBuilder.toString());
  }
  
  public void onGpsSignalLost()
  {
    navRepo.setGpsSignalLost(this, "gpsoff");
  }
  
  public void onGpsSignalLost(String paramString)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gpsoff#");
    localStringBuilder.append(paramString);
    localNavRepo.setGpsSignalLost(this, localStringBuilder.toString());
  }
  
  public void onGuidanceFinished()
  {
    navRepo.setGuidanceFinished(this, "goff");
  }
  
  public void onGuidanceFinished(String paramString)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("goff#");
    localStringBuilder.append(paramString);
    localNavRepo.setGuidanceFinished(this, localStringBuilder.toString());
  }
  
  public void onGuidanceStarted()
  {
    navRepo.setGuidanceStarted(this, "gon");
  }
  
  public void onGuidanceStarted(String paramString)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gon#");
    localStringBuilder.append(paramString);
    localNavRepo.setGuidanceStarted(this, localStringBuilder.toString());
  }
  
  public void onNewGuidanceManeuver(TurnIcon paramTurnIcon, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    navRepo.setNewGuidanceManeuver(this, paramTurnIcon, paramString1, paramString2, paramString3, paramString4, "mup");
  }
  
  public void onNewGuidanceManeuver(TurnIcon paramTurnIcon, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mup#");
    localStringBuilder.append(paramString5);
    localNavRepo.setNewGuidanceManeuver(this, paramTurnIcon, paramString1, paramString2, paramString3, paramString4, localStringBuilder.toString());
  }
  
  public void onNewLocation(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    navRepo.setNewLocation(this, paramString1, paramString2, paramString3, paramString4, "lup");
  }
  
  public void onNewLocation(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("lup#");
    localStringBuilder.append(paramString5);
    localNavRepo.setNewLocation(this, paramString1, paramString2, paramString3, paramString4, localStringBuilder.toString());
  }
  
  public void onRerouting(String paramString)
  {
    navRepo.setRerouting(this, paramString, "re");
  }
  
  public void onRerouting(String paramString1, String paramString2)
  {
    NavRepo localNavRepo = navRepo;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("re#");
    localStringBuilder.append(paramString2);
    localNavRepo.setRerouting(this, paramString1, localStringBuilder.toString());
  }
  
  public void pushIfNeeded(NavRepoDump paramNavRepoDump, String paramString)
  {
    if (!paramNavRepoDump.hasValue())
    {
      paramNavRepoDump = serviceListener;
      localObject = ksocket();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Push (");
      localStringBuilder.append(paramString);
      localStringBuilder.append(") nothing to push");
      paramNavRepoDump.onServiceMsg((KSocket)localObject, localStringBuilder.toString());
      return;
    }
    Object localObject = sendQueue;
    try
    {
      sendQueue.add(new PushCmd(paramNavRepoDump, paramString));
      sendQueue.notifyAll();
      return;
    }
    catch (Throwable paramNavRepoDump)
    {
      throw paramNavRepoDump;
    }
  }
  
  void restore()
  {
    navRepo.restore(this);
  }
  
  public void wait4RequestsSubmitted()
  {
    LinkedList localLinkedList1 = sendQueue;
    for (;;)
    {
      try
      {
        if ((!isSending) && (sendQueue.size() == 0)) {
          return;
        }
        localLinkedList2 = sendQueue;
      }
      catch (Throwable localThrowable)
      {
        try
        {
          LinkedList localLinkedList2;
          localLinkedList2.wait();
        }
        catch (InterruptedException localInterruptedException) {}
        localThrowable = localThrowable;
        throw localThrowable;
      }
    }
  }
  
  class PushCmd
  {
    private NavRepoDump navRepoDump;
    private String trace;
    
    PushCmd(NavRepoDump paramNavRepoDump, String paramString)
    {
      navRepoDump = paramNavRepoDump;
      trace = paramString;
    }
  }
  
  class Sender
    implements Runnable
  {
    private Sender() {}
    
    private void send(NavClientConnection.PushCmd paramPushCmd)
    {
      JsonObject localJsonObject = navRepoDump.toJsonTree();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(trace);
      ((StringBuilder)localObject).append("#");
      ((StringBuilder)localObject).append(countAtomic.incrementAndGet());
      localJsonObject.addProperty("MsgId", ((StringBuilder)localObject).toString());
      String str;
      if (!state().equals(Connection.State.CONNECTED))
      {
        localObject = "not connected";
      }
      else
      {
        localObject = NavClientConnection.this;
        try
        {
          ((NavClientConnection)localObject).sendMsg(localJsonObject.toString().getBytes());
          localObject = "sent";
        }
        catch (IOException localIOException)
        {
          str = localIOException.getMessage();
        }
      }
      NavServiceListener localNavServiceListener = serviceListener;
      KSocket localKSocket = ksocket();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Push (");
      localStringBuilder.append(trace);
      localStringBuilder.append(") ");
      localStringBuilder.append(str);
      localStringBuilder.append(":\n");
      localStringBuilder.append(gsonBuilder.toJson(localJsonObject));
      localNavServiceListener.onServiceMsg(localKSocket, localStringBuilder.toString());
    }
    
    public void run()
    {
      LinkedList localLinkedList = sendQueue;
      for (;;)
      {
        try
        {
          NavClientConnection.access$202(NavClientConnection.this, false);
          if (sendQueue.size() == 0)
          {
            sendQueue.notifyAll();
            int i = sendQueue.size();
            if (i == 0) {
              localObject = NavClientConnection.this;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          Object localObject;
          throw localThrowable;
        }
        try
        {
          sendQueue.wait();
        }
        catch (InterruptedException localInterruptedException) {}
        localObject = (NavClientConnection.PushCmd)sendQueue.removeFirst();
        NavClientConnection.access$202(NavClientConnection.this, true);
        if (localObject == null) {
          break;
        }
        send((NavClientConnection.PushCmd)localObject);
        break;
      }
    }
  }
}
