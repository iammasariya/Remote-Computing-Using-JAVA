//package javaremotecomputing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.awt.image.BufferedImage;

public class client extends JFrame 
{
   GridBagLayout gbl1,gbl2;

   JScrollPane jsp;

   jpal screen;

   JPanel jp1;

   BufferedImage bi;

   ImageIcon ii;

   JButton jb1;
   
   JComboBox jcb;
   
   Font f;

   int button;

   int imagetype;

   int px,py,rx,ry,mx,my,dx,dy,flag;

   int click;

   int mouse_type;

   Dimension d,resolution;

   screenconfig sc;

   int sob;
   static int setscreen=1;


   Thread tt;

   client()
   {
      super();

      sob=0;

      gbl1=new GridBagLayout();
      gbl2=new GridBagLayout();

      f=new Font("arial",0,17);

      this.setLayout(gbl1);

      GridBagConstraints c1=new GridBagConstraints();
      jp1=new JPanel();
      jp1.setLayout(gbl2);
      c1.gridx=0;
      c1.gridy=0;
      c1.weighty=0.025;
      c1.weightx=1.0;
      c1.fill=GridBagConstraints.BOTH;
      c1.anchor=GridBagConstraints.CENTER;
      gbl1.setConstraints(jp1, c1);
      this.getContentPane().add(jp1);

      GridBagConstraints c2=new GridBagConstraints();
      screen=new jpal();
      jsp=new JScrollPane(screen);
      c2.gridx=0;
      c2.gridy=1;
      c2.weighty=0.975;
      c2.weightx=1.0;
      c2.fill=GridBagConstraints.BOTH;
      c2.anchor=GridBagConstraints.CENTER;
      gbl1.setConstraints(jsp, c2);
      this.getContentPane().add(jsp);

      GridBagConstraints gc1=new GridBagConstraints();
      jb1=new JButton("Start");
      jb1.setFont(f);
      gc1.gridx=0;
      gc1.gridy=0;
      gc1.weightx=0.25;
      gc1.weighty=1;
      gc1.anchor=GridBagConstraints.CENTER;
      gc1.insets=new Insets(10,0,0,0);
      gbl2.setConstraints(jb1,gc1);
      jp1.add(jb1);

      sc=new screenconfig();

      jb1.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
           tt=new Thread()
           {
           public void run()
           {
           if(button==0)
           {
             getimage gi=new getimage(client.this,screen);
             int a=JOptionPane.showConfirmDialog(client.this,sc,"resolution and type",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);
             if(a==JOptionPane.YES_OPTION)
             {
                try
                {
                    connect.comp.setscaling(sc.d[sc.size]);
                    connect.comp.setscreentype(sc.type);
                    connect.comp.setscreenscale(sc.d[sc.size]);
                    if(sc.d[sc.size].height>720)
                    {
                        client.this.setSize(1000,720);
                        screen.setPreferredSize(sc.d[sc.size]);
                    }
                    else if(sc.d[sc.size].height==600)
                    {
                        client.this.setSize(700,500);
                        screen.setPreferredSize(sc.d[sc.size]);
                    }
                    else
                    {
                        client.this.setSize(sc.d[sc.size]);
                        screen.setPreferredSize(sc.d[sc.size]);
                    }
                    button=1;
                    jb1.setText("stop");
                    getimage.painting=true;
                    sob=1;
                    Thread t=new Thread(gi);
                    setscreen=0;
                    t.start();
                }
                catch (RemoteException ex)
                {
                    getimage.painting=false;
                    sob=0;
                    server_gui.clientrun=0;
                    JOptionPane.showMessageDialog(client.this,"Check other side if the server is runnig and try again  !");
                    client.this.dispose();
                    System.out.println("kk");
                }
                catch(Exception ee)
                {
                    getimage.painting=false;
                    sob=0;
                    server_gui.clientrun=0;
                    JOptionPane.showMessageDialog(client.this,"try connecting the server again. client will exit");
                    client.this.dispose();
                }
             }
           }
           else
           {
              sob=0;
              getimage.painting=false;
              jb1.setText("start");
              button=0;
           }
           }
           };
           tt.start();
          }

      });

      jb1.setFocusable(false);
      
      this.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
             getimage.painting=false;
             server_gui.clientrun=0;
             sob=0;
             if(tt!=null)
             {
             if(tt.isAlive())
             {
                tt.stop();
             }
             }
             dispose();
         }
      });

      screen.addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseMoved(MouseEvent e)
         {
             mx=e.getX();
             my=e.getY();
             mouse_type=2;
             Thread t=new Thread()
             {
                 public void run()
                 {
                        try
                        {
                            if(sob==1)
                            {
                               connect.comp.move(mx, my, click, mouse_type);
                            }
                        }
                        catch (RemoteException ex)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"Check other side if the server is running, connect it and try again  !");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                        catch(Exception ee)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"try connecting the server again. client will exit");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                 }
             };
             t.start();
         }
         public void mouseDragged(MouseEvent e)
         {
             dx=e.getX();
             dy=e.getY();
             mouse_type=3;
             Thread t=new Thread()
             {
                 public void run()
                 {
                        try
                        {
                            if(sob==1)
                            {
                               connect.comp.move(dx, dy, click, mouse_type);
                            }
                        }
                        catch (RemoteException ex)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"Check other side if the server is running, connect it and try again  !");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                        catch(Exception eee)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"try connecting the server again. client will exit");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                 }
             };
             t.start();
         }
      });

      screen.addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent e)
         {
             px=e.getX();
             py=e.getY();
             mouse_type=0;
             flag=0;
             if(e.isMetaDown())
             {
                click=1;
             }
             else
             {
                click=0;
             }
             Thread t=new Thread()
             {
                 public void run()
                 {
                        try
                        {
                           if(sob==1)
                           {
                              connect.comp.move(px, py, click, mouse_type);
                           }
                        }
                        catch (RemoteException ex)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"Check other side if the server is running, connect it and try again  !");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                        catch(Exception eee)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"try connecting the server again. client will exit");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                 }
             };
             t.start();
         }
         public void mouseReleased(MouseEvent e)
         {
             rx=e.getX();
             ry=e.getY();
             mouse_type=1;
             flag=0;
             if(e.isMetaDown())
             {
                click=1;
             }
             else
             {
                click=0;
             }
             Thread t=new Thread()
             {
                 public void run()
                 {
                        try
                        {
                            if(sob==1)
                            {
                                connect.comp.move(rx, ry, click, mouse_type);
                            }
                        }
                        catch (RemoteException ex)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"Check other side if the server is running, connect it and try again  !");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                        catch(Exception ee)
                        {
                            getimage.painting=false;
                            JOptionPane.showMessageDialog(client.this,"try connecting the server again. client will exit");
                            client.this.dispose();
                            sob=0;
                            server_gui.clientrun=0;
                        }
                 }
             };
             t.start();
         }
      });

      screen.setFocusable(true);

      screen.addKeyListener(new KeyAdapter()
      {
         public void keyPressed(KeyEvent e)
         {
             int key_event=e.getKeyCode();
             if(((key_event>64)&&(key_event<93))||
                 (key_event==32)||
                 (key_event==10)||
                 (key_event==8)||
                ((key_event>45)&&(key_event<58))||
                ((key_event>36)&&(key_event<41))||
                 (key_event==16)||
                 (key_event==17)||
                 (key_event==18)||
                 (key_event==27)||
                 (key_event==127)||
                 (key_event==44)||
                 (key_event==59)||
                 (key_event==61)||
                 (key_event==161)||
                 (key_event==162)||
                 (key_event==9)||
                 (key_event==27)||
                 (key_event==222)||
                 (key_event==109)||
                 (key_event==112)||
                ((key_event>111)&&(key_event<124)))
             {
                 keypress kp=new keypress(key_event,client.this);
                 Thread t=new Thread(kp);
                 if(sob==1)
                 {
                    t.start();
                 }
             }
         }
         public void keyReleased(KeyEvent e)
         {
             int key_event=e.getKeyCode();
              if(((key_event>64)&&(key_event<93))||
                 (key_event==32)||
                 (key_event==10)||
                 (key_event==8)||
                ((key_event>45)&&(key_event<58))||
                ((key_event>36)&&(key_event<41))||
                 (key_event==16)||
                 (key_event==17)||
                 (key_event==18)||
                 (key_event==27)||
                 (key_event==127)||
                 (key_event==44)||
                 (key_event==59)||
                 (key_event==61)||
                 (key_event==161)||
                 (key_event==162)||
                 (key_event==9)||
                 (key_event==27)||
                 (key_event==222)||
                 (key_event==109)||
                 (key_event==112)||
                ((key_event>111)&&(key_event<124)))
             {
                keyrelease kr=new keyrelease(key_event,client.this);
                Thread t=new Thread(kr);
                if(sob==1)
                {
                   t.start();
                }
             }
         }
      });
      client.this.setResizable(false);
      setSize(800,600);
      setVisible(true);
   }
}

class jpal extends JPanel
{
   public void paintComponent(Graphics g)
   {
      if(client.setscreen==1)
      {
         g.setColor(Color.white);
         g.fillRect(0,0,1920,1080);
      }
      else
      {
         g.drawImage(getimage.i, 0,0, this);
      }
   }
}

class keypress implements Runnable
{
    int k;
    client c;

    keypress(int a,client me)
    {
       k=a;
       c=me;
    }

    public void run()
    {
        try
        {
            connect.comp.keypress(k);
        }
        catch (RemoteException ex)
        {
            getimage.painting=false;
            JOptionPane.showMessageDialog(c,"Check other side if the server is running, connect it and try again  !");
            c.sob=0;
            c.dispose();
            server_gui.clientrun=0;
        }
        catch(Exception e)
        {
            getimage.painting=false;
            JOptionPane.showMessageDialog(c,"try connecting the server again. client will exit");
            c.sob=0;
            c.dispose();
            server_gui.clientrun=0;
        }
    }
}

class keyrelease implements Runnable
{
    int k;
    client c;

    keyrelease(int a,client me)
    {
       k=a;
       c=me;
    }

    public void run()
    {
        try
        {
            connect.comp.keyrelease(k);
        }
        catch (RemoteException ex)
        {
            getimage.painting=false;
            JOptionPane.showMessageDialog(c,"Check other side if the server is running, connect and try again  !");
            c.sob=0;
            c.dispose();
            server_gui.clientrun=0;
        }
        catch(Exception e)
        {
            getimage.painting=false;
            JOptionPane.showMessageDialog(c,"try connecting the server again. client will exit");
            c.sob=0;
            c.dispose();
            server_gui.clientrun=0;
        }
    }
}

class getimage implements Runnable
{
   ImageIcon ii;
   static Image i;
   client c;
   jpal jpanl;

   static boolean painting;

   public getimage(client frm,jpal j)
   {
      painting=false;
      c=frm;
      jpanl=j;
   }

   public void run()
   {
        try
        {
            while(painting==true)
            {
                ii = connect.comp.getscreen();
                i=ii.getImage();
                jpanl.repaint();
            }
        }
        catch (RemoteException ex)
        {
            painting=false;
            JOptionPane.showMessageDialog(c,"Check other side if the server is running,connect it and try again  !");
            c.dispose();
            c.sob=0;
            server_gui.clientrun=0;
        }
        catch (AWTException ex)
        {
            painting=false;
            JOptionPane.showMessageDialog(c,"Check other side if the server is running and try again  !");
            c.dispose();
            c.sob=0;
            server_gui.clientrun=0;
        }
        catch(Exception e)
        {
            painting=false;
            JOptionPane.showMessageDialog(c,"try connecting the server again. client will exit");
            c.dispose();
            c.sob=0;
            server_gui.clientrun=0;
        }
   }
}