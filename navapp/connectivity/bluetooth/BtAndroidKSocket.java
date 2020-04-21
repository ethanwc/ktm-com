package com.com.navapp.connectivity.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import com.com.kmrc.upgrade.Connection.LinkingListener;
import com.com.kmrc.upgrade.KSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public abstract class BtAndroidKSocket
  extends KSocket
{
  protected final String btMacAddress;
  private BluetoothDevice device;
  protected final String serviceName;
  private BluetoothSocket transportConnection;
  private final UUID uuid;
  protected final String uuidString;
  private BluetoothServerSocket welcomeSocket;
  
  public BtAndroidKSocket(String paramString1, String paramString2, String paramString3)
  {
    btMacAddress = paramString1;
    uuidString = paramString2;
    serviceName = paramString3;
    if (paramString1 != null) {}
    try
    {
      try
      {
        paramString1 = BtControl.getPairedBtDeviceMacAddress(paramString1);
        device = paramString1;
      }
      catch (Throwable paramString1)
      {
        device = null;
        throw paramString1;
      }
    }
    catch (Exception paramString1)
    {
      for (;;) {}
    }
    device = null;
    uuid = UUID.fromString(paramString2);
  }
  
  public void accept(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    welcomeSocket = BluetoothAdapter.getDefaultAdapter().listenUsingRfcommWithServiceRecord(serviceName, uuid);
    paramLinkingListener.linking();
    transportConnection = welcomeSocket.accept();
    a = new DataInputStream(transportConnection.getInputStream());
    b = new DataOutputStream(transportConnection.getOutputStream());
    welcomeSocket.close();
  }
  
  public void close()
    throws IOException
  {
    if (a != null) {
      localObject = a;
    }
    try
    {
      ((DataInputStream)localObject).close();
      if (b != null)
      {
        localObject = b;
        ((DataOutputStream)localObject).close();
      }
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    Object localObject = welcomeSocket;
    if (localObject != null) {
      ((BluetoothServerSocket)localObject).close();
    }
    localObject = transportConnection;
    if (localObject != null)
    {
      ((BluetoothSocket)localObject).close();
      return;
    }
  }
  
  public void connect(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    Object localObject = device;
    if (localObject != null)
    {
      UUID localUUID = uuid;
      try
      {
        localObject = ((BluetoothDevice)localObject).createRfcommSocketToServiceRecord(localUUID);
        transportConnection = ((BluetoothSocket)localObject);
        paramLinkingListener.linking();
        paramLinkingListener = transportConnection;
        paramLinkingListener.connect();
        paramLinkingListener = transportConnection;
        paramLinkingListener = new DataInputStream(paramLinkingListener.getInputStream());
        a = paramLinkingListener;
        paramLinkingListener = transportConnection;
        paramLinkingListener = new DataOutputStream(paramLinkingListener.getOutputStream());
        b = paramLinkingListener;
        return;
      }
      catch (IOException paramLinkingListener) {}
    }
    try
    {
      close();
      throw paramLinkingListener;
      throw new IOException("Unknown Bluetooth Device to connect");
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof BtAndroidKSocket)) {
      return false;
    }
    paramObject = (BtAndroidKSocket)paramObject;
    return (btMacAddress.equals(btMacAddress)) && (uuid.equals(uuid)) && (serviceName.equals(serviceName));
  }
  
  public abstract void establishConnection(Connection.LinkingListener paramLinkingListener)
    throws IOException;
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bt://");
    localStringBuilder.append(btMacAddress);
    localStringBuilder.append(":");
    localStringBuilder.append(uuidString);
    return localStringBuilder.toString();
  }
}
