//package javaremotecomputing;

import javax.swing.*;
import java.rmi.*;
import java.rmi.registry.*;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import sun.management.jmxremote.LocalRMIServerSocketFactory;

public class connect implements Runnable
{
    static servicesinterface comp;

    Registry registry;

    connect()
    {
       comp=null;
    }

    public void run()
    {
       getobject();
    }

    void getobject()
    {
       try
       {
          Registry registry = LocateRegistry.getRegistry(clientconfig.ip,clientconfig.port);
          comp = (servicesinterface) registry.lookup("abc");
          String uname=comp.getusername();
          String pwd=comp.getpassword();
          if((uname.equals(clientconfig.username))&&(pwd.equals(clientconfig.password)))
          {
             server_gui.connected=2;
             server_gui.jt2.append("\n Server connected on "+clientconfig.ip+" and port "+clientconfig.port);
             SwingUtilities.invokeLater(new Runnable()
             {
                 public void run()
                 {
                     server_gui.jb3.setEnabled(true);
                     server_gui.jb4.setEnabled(true);
                 }
             });
          }
          else
          {
             server_gui.connected=0;
             comp=null;
             server_gui.jt2.append("\n Wrong username or password ! Can not connect server.");
             SwingUtilities.invokeLater(new Runnable()
             {
                 public void run()
                 {
                     server_gui.jb3.setEnabled(true);
                     server_gui.jb4.setEnabled(true);
                 }
             });
          }
       }
       catch (RemoteException ex)
       {
          comp=null;
          server_gui.connected=0;
          server_gui.jt2.append("\n-->Can't connect the server. Check other side if the server is running.");
          SwingUtilities.invokeLater(new Runnable()
             {
                 public void run()
                 {
                     server_gui.jb3.setEnabled(true);
                     server_gui.jb4.setEnabled(true);
                 }
             });
       }
       catch(NotBoundException nbe)
       {
          comp=null;
          server_gui.connected=0;
          server_gui.jt2.append("\n Can't connect the server. Check other side if the server is running.");
          SwingUtilities.invokeLater(new Runnable()
             {
                 public void run()
                 {
                     server_gui.jb3.setEnabled(true);
                     server_gui.jb4.setEnabled(true);
                 }
             });
       }
       catch(Exception e)
       {
          comp=null;
          server_gui.connected=0;
          server_gui.jt2.append("\n Can't connect the server. Check other side if the server is running.");
          SwingUtilities.invokeLater(new Runnable()
             {
                 public void run()
                 {
                     server_gui.jb3.setEnabled(true);
                     server_gui.jb4.setEnabled(true);
                 }
             });
       }
    }
}