package com.com.kmrc.upgrade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class KSocket
{
  protected DataInputStream a = null;
  protected DataOutputStream b = null;
  
  public KSocket() {}
  
  public DataOutputStream b()
  {
    DataOutputStream localDataOutputStream = b;
    if (localDataOutputStream != null) {
      return localDataOutputStream;
    }
    throw new NullPointerException("KSocket.out == null");
  }
  
  public DataInputStream c()
  {
    DataInputStream localDataInputStream = a;
    if (localDataInputStream != null) {
      return localDataInputStream;
    }
    throw new NullPointerException("KSocket.in == null");
  }
  
  public abstract void close()
    throws IOException;
  
  public abstract boolean equals(Object paramObject);
  
  public abstract void establishConnection(Connection.LinkingListener paramLinkingListener)
    throws IOException;
  
  public abstract String toString();
}
