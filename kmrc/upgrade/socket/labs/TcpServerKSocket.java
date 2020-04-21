package com.com.kmrc.upgrade.socket.labs;

import com.com.kmrc.upgrade.Connection.LinkingListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class TcpServerKSocket
  extends TcpKSocket
{
  public TcpServerKSocket(String paramString, int paramInt)
    throws UnknownHostException
  {
    super(paramString, paramInt);
  }
  
  public void establishConnection(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    super.accept(paramLinkingListener);
  }
}
