//package javaremotecomputing;

import java.io.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilePacket implements Serializable
{
  byte[] data;

  int packet=0;

  File sendfile;

  FileInputStream fip;

  public FilePacket(File file )
  {
  	sendfile=file;
        try
        {
            fip = new FileInputStream(sendfile);
        }
        catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }
  }

  public void readIn()
  {
  	try
        {
     	    data = new byte[(int)(sendfile.length()/25)];
            fip.read( data );
            packet++;
            if(packet==25)
            {
               packet=0;
               fip.close();
            }
        }
        catch( Exception e )
        {
            System.out.println("unable to read file");
            server_gui.filetransfer=0;
   	}
  }

  public void writeTo(FileOutputStream out)
  {
        try
        {
           out.write( data );
           out.close();
        }
        catch( Exception e )
        {
            System.out.println("unable to write");
        }
  }
}