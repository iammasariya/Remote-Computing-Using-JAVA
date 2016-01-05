//package javaremotecomputing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;


public class services implements servicesinterface
{
    Robot rbt;

    BufferedImage bi;
    int screentype=0;
    Toolkit tk;
    Dimension server_dimension,client_dimension;
    ImageIcon ii;
    Double width_scale,height_scale;

    String filename;
    FileOutputStream fos;

    Boolean ok;

    int counter;

    services()
    {
        tk=Toolkit.getDefaultToolkit();
        server_dimension=tk.getScreenSize();
        try
        {
            rbt = new Robot();
        }
        catch (AWTException ex)
        {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ImageIcon getscreen() throws RemoteException, AWTException
    {
         if(screentype==0)
         {
             BufferedImage ki=new BufferedImage(server_dimension.width, server_dimension.height, BufferedImage.TYPE_INT_ARGB);
             bi=rbt.createScreenCapture(new Rectangle(server_dimension));
             Graphics2D g=ki.createGraphics();
             g.drawImage(bi,0,0,server_dimension.width, server_dimension.height,null);
             Image img=(Image)ki.getScaledInstance(client_dimension.width, client_dimension.height, BufferedImage.SCALE_SMOOTH);
             ii=new ImageIcon();
             ii.setImage(img);
         }
         else if(screentype==1)
         {
             BufferedImage ki=new BufferedImage(server_dimension.width, server_dimension.height, BufferedImage.TYPE_USHORT_555_RGB);
             bi=rbt.createScreenCapture(new Rectangle(server_dimension));
             Graphics2D g=ki.createGraphics();
             g.drawImage(bi,0,0,server_dimension.width, server_dimension.height,null);
             Image img=(Image)ki.getScaledInstance(client_dimension.width, client_dimension.height, BufferedImage.SCALE_SMOOTH);
             ii=new ImageIcon();
             ii.setImage(img);
         }
         else if(screentype==2)
         {
             BufferedImage ki=new BufferedImage(server_dimension.width, server_dimension.height, BufferedImage.TYPE_BYTE_INDEXED);
             bi=rbt.createScreenCapture(new Rectangle(server_dimension));
             Graphics2D g=ki.createGraphics();
             g.drawImage(bi,0,0,server_dimension.width, server_dimension.height,null);
             Image img=(Image)ki.getScaledInstance(client_dimension.width, client_dimension.height, BufferedImage.SCALE_SMOOTH);
             ii=new ImageIcon();
             ii.setImage(img);
         }
         else if(screentype==3)
         {
             BufferedImage ki=new BufferedImage(server_dimension.width, server_dimension.height, BufferedImage.TYPE_BYTE_GRAY);
             bi=rbt.createScreenCapture(new Rectangle(server_dimension));
             Graphics2D g=ki.createGraphics();
             g.drawImage(bi,0,0,server_dimension.width, server_dimension.height,null);
             Image img=(Image)ki.getScaledInstance(client_dimension.width, client_dimension.height, BufferedImage.SCALE_SMOOTH);
             ii=new ImageIcon();
             ii.setImage(img);
         }
        return ii;
    }

    public Dimension getscreensize() throws RemoteException
    {
         return(server_dimension);
    }
    public void setscreentype(int a)
    {
         if(a==0)
         {
             screentype=0;
         }
         else if(a==1)
         {
             screentype=1;
         }
         else if(a==2)
         {
             screentype=2;
         }
         else if(a==3)
         {
             screentype=3;
         }
    }

    public void setscaling(Dimension dd)
    {
         client_dimension=dd;
    }

    public void setscreenscale(Dimension dd) throws RemoteException
    {
         width_scale=(double)((double)server_dimension.width/(double)dd.width);
         height_scale=(double)((double)server_dimension.height/(double)dd.height);
    }

    public void move(int a,int b,int click,int type) throws RemoteException
    {
        int x,y;
        x=(int)(a*width_scale);
        y=(int)(b*height_scale);
        if(type==0)
        {
            if(click==0)
            {
                rbt.mouseMove(x,y);
                rbt.mousePress(InputEvent.BUTTON1_MASK);
            }
            else if(click==1)
            {
                rbt.mouseMove(x,y);
                rbt.mousePress(InputEvent.BUTTON3_MASK);
            }
        }
        else if(type==1)
        {
            if(click==0)
            {
                rbt.mouseMove(x,y);
                rbt.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            else if(click==1)
            {
                rbt.mouseMove(x,y);
                rbt.mouseRelease(InputEvent.BUTTON3_MASK);
            }
        }
        else if(type==2)
        {
            rbt.mouseMove(x,y);
        }
        else if(type==3)
        {
            rbt.mouseMove(x, y);
        }
    }

    public void keypress(final int k) throws RemoteException
    {
        Thread t=new Thread()
        {
           public void run()
           {
              try 
              {
                 Thread.sleep(500);
                 rbt=new Robot();
                 rbt.keyPress(k);
              }
              catch (InterruptedException ex) 
              {
                 Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch(AWTException e)
              {
              }
           }
        };
        t.start();
    }
    public void keyrelease(final int k) throws RemoteException
    {
        Thread t=new Thread()
        {
           public void run()
           {
              try
              {
                 Thread.sleep(500);
                 rbt=new Robot();
                 rbt.keyRelease(k);
              }
              catch (InterruptedException ex)
              {
                 Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch(AWTException e)
              {
              }
           }
        };
        t.start();
    }

    public void sendname(String name)
    {
        this.filename=name;
        try
        {
            fos = new FileOutputStream(new File(filename));
        }
        catch (FileNotFoundException ex) {
            System.out.println("file not found servie");
        }
    }

    public void sendfile(byte[] b) throws RemoteException
    {
        try
        {
            fos.write(b);
            counter++;
            if(counter==25)
            {
               counter=0;
               try
               {
                  fos.close();
               }
               catch (IOException ex)
               {
                  server_gui.jt1.append("\n-->could not write file");
               }
            }
        }
        catch (FileNotFoundException ex)
        {
            server_gui.jt1.append("\n-->could not write file");
        }
        catch (IOException ex)
        {
            server_gui.jt1.append("\n-->could not write file");
        }
    }

    public void sendchat(String s) throws RemoteException
    {
        if(server_gui.chatrun==1)
        {
           chat.jt1.append(s);
        }
    }

    public int chatrunning()
    {
        if(server_gui.chatrun==1)
        {
            return(1);
        }
        else
        {
            return(0);
        }

    }

    public String getusername() throws RemoteException
    {
        return(serverconfig.username);
    }

    public String getpassword() throws RemoteException
    {
        return(serverconfig.password);
    }

    public void check() throws RemoteException
    {
    }
}
