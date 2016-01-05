//package javaremotecomputing;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import javax.swing.SwingUtilities;
import sun.management.jmxremote.LocalRMIServerSocketFactory;

public class server implements Runnable
{
    servicesinterface s,t;
    Registry registry;

    static boolean server;

    server()
    {
        t=null;
        s=null;
    }

    public void run()
    {
        if(server==true)
        {
            startserver();
        }
        else
        {
            stopserver();
        }
    }

    public void startserver()
    {
        try
        {
            s = new services();
            if(serverconfig.ssl==true)
            {
               //t=(servicesinterface)UnicastRemoteObject.exportObject(s, 8000, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
			   t=(servicesinterface)UnicastRemoteObject.exportObject(s,8000);
            }
            else
            {
               t=(servicesinterface)UnicastRemoteObject.exportObject(s,8000);
            }
            registry = LocateRegistry.createRegistry(serverconfig.port);
            registry.rebind("abc",t);
            server_gui.started=2;
            server_gui.jt1.append("\n Server started succesfully on port "+serverconfig.port);
            server_gui.jt1.append("\n Your ip address is "+serverconfig.ip);
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
        catch (RemoteException e)
        {
            server_gui.jt1.setText("\n Can not create server on the given port choose anotheer port and try again.");
            server_gui.started=0;
            SwingUtilities.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
        catch(Exception e)
        {
            server_gui.jt1.append("\n Can not create server on the given port choose anotheer port and try again.!");
            server_gui.started=0;
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
    }
    public void stopserver()
    {
        try
        {
            registry.unbind("abc");
            UnicastRemoteObject.unexportObject(s, true);
            UnicastRemoteObject.unexportObject(registry, true);
            s=null;
            t=null;
            registry=null;
            server_gui.started=0;
            server_gui.jt1.append("\n Server stopped on the port "+serverconfig.port);
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
        catch (RemoteException ex)
        {
            server_gui.started=2;
            server_gui.jt1.append("\nCan't stop server try again. If error comes again then terminate the application.");
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
        catch (NotBoundException ex)
        {
            server_gui.started=0;
            server_gui.jt1.append("\n Can't stop server try again. If error comes again then terminate the application.");
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    server_gui.jb1.setEnabled(true);
                    server_gui.jb2.setEnabled(true);
                }
            });
        }
    }
}