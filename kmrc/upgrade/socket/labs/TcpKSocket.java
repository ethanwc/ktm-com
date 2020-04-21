package com.com.kmrc.upgrade.socket.labs;

import com.com.kmrc.upgrade.Connection.LinkingListener;
import com.com.kmrc.upgrade.KSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TcpKSocket
  extends KSocket
{
  private String hostname;
  private InetAddress inetAddress;
  private int port;
  private ServerSocket serverSocket = null;
  private Socket transportSocket = null;
  
  protected TcpKSocket(String paramString, int paramInt)
    throws UnknownHostException
  {
    inetAddress = InetAddress.getByName(paramString);
    hostname = paramString;
    port = paramInt;
  }
  
  void accept(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    serverSocket = new ServerSocket(port, 1, inetAddress);
    port = serverSocket.getLocalPort();
    paramLinkingListener.linking();
    transportSocket = serverSocket.accept();
    a = new DataInputStream(transportSocket.getInputStream());
    b = new DataOutputStream(transportSocket.getOutputStream());
    serverSocket.close();
  }
  
  public void close()
    throws IOException
  {
    Object localObject = transportSocket;
    if (localObject != null) {
      ((Socket)localObject).close();
    }
    localObject = serverSocket;
    if (localObject != null) {
      ((ServerSocket)localObject).close();
    }
  }
  
  protected void connect(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    paramLinkingListener.linking();
    transportSocket = new Socket(inetAddress, port);
    a = new DataInputStream(transportSocket.getInputStream());
    b = new DataOutputStream(transportSocket.getOutputStream());
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof TcpKSocket)) {
      return false;
    }
    paramObject = (TcpKSocket)paramObject;
    return (inetAddress.equals(inetAddress)) && (port == port);
  }
  
  public int port()
  {
    return port;
  }
  
  public String toString()
  {
    Object localObject = transportSocket;
    if ((localObject != null) && (((Socket)localObject).isConnected()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("tcp://");
      ((StringBuilder)localObject).append(transportSocket.getLocalAddress().getHostAddress());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(transportSocket.getLocalPort());
      ((StringBuilder)localObject).append(" -> tcp://");
      ((StringBuilder)localObject).append(transportSocket.getInetAddress().getHostAddress());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(transportSocket.getPort());
      return ((StringBuilder)localObject).toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("tcp://");
    ((StringBuilder)localObject).append(hostname);
    ((StringBuilder)localObject).append(":");
    ((StringBuilder)localObject).append(port);
    return ((StringBuilder)localObject).toString();
  }
}
