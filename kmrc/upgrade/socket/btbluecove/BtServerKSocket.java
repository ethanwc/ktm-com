package com.com.kmrc.upgrade.socket.btbluecove;

import com.com.kmrc.upgrade.Connection.LinkingListener;
import java.io.IOException;

public class BtServerKSocket
  extends BtKSocket
{
  public BtServerKSocket(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public void establishConnection(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    super.accept(paramLinkingListener);
  }
}
