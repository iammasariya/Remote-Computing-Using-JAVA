//package javaremotecomputing;

import java.rmi.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;


public interface servicesinterface extends Remote
{
   ImageIcon getscreen() throws RemoteException,AWTException;
   Dimension getscreensize() throws RemoteException;
   void setscreenscale(Dimension dd) throws RemoteException;
   void setscaling(Dimension d) throws RemoteException;
   void setscreentype(int a) throws RemoteException;
   void move(int a,int b,int c,int d) throws RemoteException;
   void keypress(int k) throws RemoteException;
   void keyrelease(int k) throws RemoteException;
   void sendname(String file) throws RemoteException;
   void sendfile(byte[] b) throws RemoteException;
   void sendchat(String s) throws RemoteException;
   int chatrunning() throws RemoteException;
   String getusername() throws RemoteException;
   String getpassword() throws RemoteException;
   void check() throws RemoteException;
}
