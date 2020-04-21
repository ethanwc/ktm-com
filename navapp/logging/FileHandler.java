package com.com.navapp.logging;

import android.content.Context;
import android.os.Environment;
import com.com.navapp.Globals;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandler
{
  static final String name = com.ktm.navapp.logging.FileHandler.class.getSimpleName();
  
  public FileHandler() {}
  
  static void deleteFiles(File paramFile, String paramString1, String paramString2)
  {
    String str1 = name;
    String str2 = String.valueOf(paramFile);
    int i = 0;
    RealmLog.append(str1, "deleteFiles( folder=%s, prefix=%s )", new Object[] { str2, String.valueOf(paramString1) });
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    while (i < j)
    {
      str1 = paramFile[i];
      if (((paramString1 == null) || (str1.getName().startsWith(paramString1))) && ((paramString2 == null) || (str1.getName().endsWith(paramString2)))) {
        str1.delete();
      }
      i += 1;
    }
  }
  
  static File getExternalDir()
  {
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
  }
  
  static long getExternalFreeSpace()
  {
    long l = getExternalDir().getFreeSpace();
    RealmLog.append(name, "getExternalFreeSpace(): %d", new Object[] { Long.valueOf(l) });
    return l;
  }
  
  static File getInternalDir()
  {
    return Globals.getContext().getFilesDir();
  }
  
  static long getInternalFreeSpace()
  {
    long l = getInternalDir().getFreeSpace();
    RealmLog.append(name, "getInternalFreeSpace(): %d", new Object[] { Long.valueOf(l) });
    return l;
  }
  
  static void writeLine(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new FileWriter(new File(getInternalDir(), paramString1), true);
      paramString1.append(paramString2).append('\n').flush();
      paramString1.close();
      return;
    }
    catch (IOException paramString1) {}
  }
  
  static void zipFiles(String paramString1, String paramString2)
  {
    RealmLog.append(name, "zipFiles( prefix=%s, zipFileName=%s )", new Object[] { String.valueOf(paramString1), paramString2 });
    try
    {
      Object localObject1 = getExternalDir();
      ((File)localObject1).mkdirs();
      paramString2 = new FileOutputStream(new File((File)localObject1, paramString2));
      paramString2 = new ZipOutputStream(paramString2);
      localObject1 = getInternalDir().listFiles();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        File localFile = localObject1[i];
        if (paramString1 != null)
        {
          boolean bool = localFile.getName().startsWith(paramString1);
          if (!bool) {}
        }
        else
        {
          Object localObject2 = name;
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Adding file: ");
          ((StringBuilder)localObject3).append(localFile.getName());
          RealmLog.i((String)localObject2, ((StringBuilder)localObject3).toString());
          localObject2 = new byte['?'];
          localObject3 = new FileInputStream(localFile);
          paramString2.putNextEntry(new ZipEntry(localFile.getName()));
          for (;;)
          {
            int k = ((FileInputStream)localObject3).read((byte[])localObject2);
            if (k <= 0) {
              break;
            }
            paramString2.write((byte[])localObject2, 0, k);
          }
          paramString2.closeEntry();
          ((FileInputStream)localObject3).close();
        }
        i += 1;
      }
      paramString2.close();
      return;
    }
    catch (IOException paramString1)
    {
      RealmLog.e("", paramString1.getMessage());
    }
  }
}
