//package javaremotecomputing;


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.rmi.RemoteException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

public class server_gui extends JFrame
{
    JMenuBar jMenuBar1;
    JMenu jMenu1,jMenu2;
    JMenuItem jmenuitem1,jmenuitem2,jmenuitem3,jmenuitem4,jmenuitem5,jmenuitem6;
    JSeparator jsep;

    jpanel jp1,jp3,jpk,jp5;

    JPanel jp2,jp4;

    JScrollPane jsp,jsp2;

    JButton jb5,jb6,jb7,jbk;
    static JButton jb1,jb2,jb3,jb4;
    JCheckBox jcb1;
    JOptionPane jop;

    static JTextArea jt1,jt2;

    JFileChooser jfc;

    Font f;

    GridBagLayout gbl;

    JOptionPane jop1;

    static int started,connected,chatrun=0,clientrun=0,learn=0,update=0,about=0,filetransfer=0;

    serverconfig scon;

    clientconfig ccon;

    FilePacket fp;

    static BufferedImage bc;
    static Image mc;

    public server_gui()
    {
        super("Java Remote Computing");

        started=0;
        connected=0;

        scon=new serverconfig();

        
        gbl=new GridBagLayout();
        this.setLayout(gbl);

        f=new Font("arial", 0,18);

        jsep=new JSeparator();

        jMenuBar1=new JMenuBar();
        jMenuBar1.setPreferredSize(new Dimension(74,60));
        jMenu1=new JMenu();
        jMenu1.setFont(f);
		jMenu1.setVisible(false);
        jMenuBar1.add(jMenu1);
        jMenu1.setText("Help");
        jmenuitem1=new JMenuItem("About");
        jmenuitem1.setFont(f);
        jmenuitem5=new JMenuItem("Update");
        jmenuitem5.setFont(f);
        jmenuitem6=new JMenuItem("Learn");
        jmenuitem6.setFont(f);
        jMenu1.add(jmenuitem1);
        jMenu1.insertSeparator(1);
        jMenu1.add(jmenuitem5);
        jMenu1.insertSeparator(3);
        jMenu1.add(jmenuitem6);

        jMenu2=new JMenu();
        jMenu2.setFont(f);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(jMenu2);
        jMenu2.setText("Extras");
        jmenuitem2=new JMenuItem("History");
        jmenuitem2.setFont(f);
        jmenuitem3=new JMenuItem("chat");
        jmenuitem3.setFont(f);
        jmenuitem4=new JMenuItem("Audio call");
        jmenuitem4.setFont(f);

        jmenuitem3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Thread t=new Thread()
                {
                    public void run()
                    {
                        try
                        {
                             connect.comp.check();
                             if((started==2))
                             {
                                if(chatrun==0)
                                {
                                   new chat();
                                }
                                chatrun=1;
                             }
                             else
                             {
                                JOptionPane.showMessageDialog(server_gui.this,"Check if your server is running");
                             }
                        }
                        catch (RemoteException ex)
                        {
                             JOptionPane.showMessageDialog(server_gui.this,"Check if server is running is on other side.\n If it is running  connect it.");
                        }
                        catch(Exception e)
                        {
                             JOptionPane.showMessageDialog(server_gui.this,"Check if server is running on other side.\n If it is running  connect it.");
                        }
                    }
                };
                t.start();
            }
        });

        jmenuitem1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(about==0)
                {
                   AboutGUI abg=new AboutGUI();
                   about=1;
                }
            }
        });

        jmenuitem5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(update==0)
                {
                  new update();
                  update=1;
                }
            }
        });

        jmenuitem6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(learn==0)
                {
                   new howto();
                   learn=1;
                }
            }
        });

        //jMenu2.add(jmenuitem2);
        //jMenu2.insertSeparator(1);
        jMenu2.add(jmenuitem3);

        setJMenuBar(jMenuBar1);
                                                                        //menu bar over
        jp1=new jpanel(0);
        jp1.setBorder(new LineBorder(Color.darkGray));
        GridBagConstraints c1=new GridBagConstraints();
        c1.gridx=0;
        c1.gridy=0;
        c1.weightx=0.0;
        c1.weighty=1.0;
        c1.gridheight=3;
        c1.fill=GridBagConstraints.VERTICAL;
        c1.anchor=GridBagConstraints.NORTHWEST;
        c1.ipadx=220;
        gbl.setConstraints(jp1, c1);
        //this.getContentPane().add(jp1);

        jpk=new jpanel(1);
        GridBagConstraints ck=new GridBagConstraints();
        ck.gridx=1;
        ck.gridy=0;
        ck.weightx=1;
        ck.weighty=0.0;
        ck.gridwidth=2;
        ck.fill=GridBagConstraints.HORIZONTAL;
        ck.ipady=100;
        ck.anchor=GridBagConstraints.PAGE_START;
        gbl.setConstraints(jpk, ck);
        this.getContentPane().add(jpk);

        jt1=new JTextArea();
        DefaultCaret caret = (DefaultCaret)jt1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jt1.setLineWrap(true);
        jt1.setWrapStyleWord(true);
        jt1.setBackground(Color.white);
        jt1.setEditable(false);
        jt1.setFont(new Font("Arial", 0, 17));
        jsp=new JScrollPane(jt1);
        jp2=new JPanel(new GridLayout(1,1));
        jp2.setBackground(Color.white);
        jsp.setBorder(new TitledBorder("Server Status"));
        jp2.add(jsp);

        GridBagConstraints c2=new GridBagConstraints();
        c2.gridx=1;
        c2.gridy=1;
        c2.weightx=0.65;
        c2.weighty=0.25;
        c2.fill=GridBagConstraints.BOTH;
        c2.anchor=GridBagConstraints.NORTHWEST;
        gbl.setConstraints(jp2, c2);
        this.getContentPane().add(jp2);

        jp3=new jpanel(3);
        jp3.setBackground(Color.white);
        jp3.setBorder(new LineBorder(Color.lightGray,3));
        GridBagConstraints c3=new GridBagConstraints();
        c3.gridx=2;
        c3.gridy=1;
        c3.weightx=0.35;
        c3.weighty=0.25;
        c3.fill=GridBagConstraints.BOTH;
        c3.anchor=GridBagConstraints.NORTHWEST;
        gbl.setConstraints(jp3, c3);

        GridBagLayout gl=new GridBagLayout();
        jp3.setLayout(gl);

        jb1=new JButton("Start Server");
        jb1.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc1=new GridBagConstraints();
        gc1.gridx=0;
        gc1.gridy=0;
        gc1.weightx=0.7;
        gc1.weighty=0.25;
        gc1.ipady=20;
        gc1.ipadx=30;
        gc1.insets=new Insets(0,20,0,0);
        gc1.anchor=GridBagConstraints.LINE_START;
        gl.setConstraints(jb1, gc1);

        jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 if(started==0)
                 {
                    jb1.setEnabled(false);
                    jb2.setEnabled(false);
                    scon.setVisible(true);
                    started=1;
                 }
                 else if(started==1)
                 {
                     JOptionPane.showMessageDialog(server_gui.this,"Please Enter IP Address and Port !");
                 }
                 else if(started==2)
                 {
                    JOptionPane.showMessageDialog(server_gui.this,"Server is already running !");
                 }
            }
        });

        jp3.add(jb1);

        jb2=new JButton("Stop Server");
        jb2.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc2=new GridBagConstraints();
        gc2.gridx=0;
        gc2.gridy=1;
        gc2.weightx=1;
        gc2.weighty=0.25;
        gc2.ipady=20;
        gc2.ipadx=32;
        gc2.insets=new Insets(0,20,0,0);
        gc2.anchor=GridBagConstraints.LINE_START;
        gl.setConstraints(jb2, gc2);
        jp3.add(jb2);

        jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(started==0)
                {
                   JOptionPane.showMessageDialog(server_gui.this,"Server has been already stopped !");
                }
                else if(started==1)
                {
                   JOptionPane.showMessageDialog(server_gui.this,"Server has been already stopped ! !");
                }
                else if(started==2)
                {
                   scon.s.server=false;
                   jb1.setEnabled(false);
                   jb2.setEnabled(false);
                   Thread t=new Thread(scon.s);
                   t.start();
                }
            }
         });

        jb3=new JButton("Connect Server");
        jb3.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc3=new GridBagConstraints();
        gc3.gridx=0;
        gc3.gridy=2;
        gc3.weightx=1;
        gc3.weighty=0.25;
        gc3.ipady=20;
        gc3.ipadx=5;
        gc3.insets=new Insets(0,20,0,0);
        gc3.anchor=GridBagConstraints.LINE_START;
        gl.setConstraints(jb3, gc3);
        jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(connected==0)
                {
                     jb3.setEnabled(false);
                     jb4.setEnabled(false);
                     ccon=new clientconfig();
                     connected=1;
                }
                else if(connected==1)
                {
                     JOptionPane.showMessageDialog(server_gui.this,"First connect the server !");
                }
                if(connected==2)
                {
                     Thread t=new Thread()
                     {
                        public void run()
                        {
                            try
                            {
                                SwingUtilities.invokeLater(new  Runnable()
                                {
                                    public void run()
                                    {
                                        jb3.setEnabled(false);
                                        jb4.setEnabled(false);
                                    }
                                });
                                connect.comp.check();
                                JOptionPane.showMessageDialog(server_gui.this,"Connection Already Exists !");
                                SwingUtilities.invokeLater(new  Runnable()
                                {
                                    public void run()
                                    {
                                        jb3.setEnabled(true);
                                        jb4.setEnabled(true);
                                    }
                                });
                            }
                            catch (RemoteException ex)
                            {
                                SwingUtilities.invokeLater(new  Runnable()
                                {
                                    public void run()
                                    {
                                        jb3.setEnabled(false);
                                        jb4.setEnabled(false);
                                    }
                                });
                                ccon=new clientconfig();
                                connected=1;
                            }
                        }
                     };
                     t.start();
                }
            }
        });
        jp3.add(jb3);

        jb4=new JButton("Disconnect");
        jb4.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc4=new GridBagConstraints();
        gc4.gridx=0;
        gc4.gridy=3;
        gc4.weightx=1;
        gc4.weighty=0.25;
        gc4.ipady=20;
        gc4.ipadx=36;
        gc4.insets=new Insets(0,20,0,0);
        gc4.anchor=GridBagConstraints.LINE_START;
        gl.setConstraints(jb4, gc4);

        jb4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(connected==2)
                {
                     connect.comp=null;
                     server_gui.jt2.append("\n-->Server at "+clientconfig.ip+" and port "+clientconfig.port+" disconnected");
                     connected=0;
                }
                else if(connected==1)
                {
                     JOptionPane.showMessageDialog(server_gui.this,"You are not connected to any server.");
                }
                else if(connected==0)
                {
                     JOptionPane.showMessageDialog(server_gui.this,"You are not connected to any server.");
                }
            }
        });

        jp3.add(jb4);

        jbk=new JButton("Control");
        jbk.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc5=new GridBagConstraints();
        gc5.gridx=1;
        gc5.gridy=0;
        gc5.weightx=0.3;
        gc5.weighty=0.25;
        gc5.ipady=20;
        gc5.ipadx=30;
        gc5.anchor=GridBagConstraints.LINE_START;
        gl.setConstraints(jbk, gc5);
        jp3.add(jbk);

        jbk.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(connected==2)
                {
                    Thread t=new Thread()
                    {
                        public void run()
                        {
                            try
                            {
                                connect.comp.check();
                                if(clientrun==0)
                                {
                                   if(filetransfer==0)
                                   {
                                       new client();
                                       clientrun=1;
                                   }
                                   else
                                   {
                                       JOptionPane.showMessageDialog(server_gui.this,"Open it after file transfer is done.");
                                   }
                                }
                            }
                            catch (RemoteException ex)
                            {
                                JOptionPane.showMessageDialog(server_gui.this,"first connect to remote server");
                            }
                        }
                    };
                    t.start();
                }
                else if(connected==1)
                {
                    JOptionPane.showMessageDialog(server_gui.this,"First connect the server !");
                }
                else if(connected==0)
                {
                    JOptionPane.showMessageDialog(server_gui.this,"you are not connected to any server");
                }
            }
        });

        ImageIcon i=new ImageIcon("images.png");
        final JLabel jl=new JLabel(i);
		jl.setVisible(false);
        GridBagConstraints gc6=new GridBagConstraints();
        gc6.gridx=1;
        gc6.gridy=3;
        gc6.weightx=0.3;
        gc6.weighty=0.25;
        gc6.anchor=GridBagConstraints.CENTER;
        gl.setConstraints(jl, gc6);

        jl.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                 jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e)
            {
                 jl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            public void mouseClicked(MouseEvent e)
            {
                if(learn==0)
                {
                   new howto();
                   learn=1;
                }
            }
        });
        jp3.add(jl);

        this.getContentPane().add(jp3);

        jt2=new JTextArea();
        DefaultCaret care = (DefaultCaret)jt2.getCaret();
        care.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jt2.setLineWrap(true);
        jt2.setWrapStyleWord(true);
        jt2.setBackground(Color.white);
        jt2.setEditable(false);
        jt2.setFont(new Font("Arial", 0, 17));
        jsp2=new JScrollPane(jt2);
        jp4=new JPanel(new GridLayout(1,1));
        jp4.setBackground(Color.white);
        jsp2.setBorder(new TitledBorder("Connections "));
        jp4.add(jsp2);
        GridBagConstraints c4=new GridBagConstraints();
        c4.gridx=1;
        c4.gridy=2;
        c4.weightx=0.65;
        c4.weighty=0.8;
        c4.fill=GridBagConstraints.BOTH;
        c4.anchor=GridBagConstraints.NORTHWEST;
        gbl.setConstraints(jp4, c4);
        this.getContentPane().add(jp4);

        GridBagLayout gbl2=new GridBagLayout();

        jp5=new jpanel(5);
        jp5.setLayout(gbl2);
        jp5.setBackground(Color.white);
        jp5.setBorder(new LineBorder(Color.lightGray,3));
        GridBagConstraints c5=new GridBagConstraints();
        c5.gridx=2;
        c5.gridy=2;
        c5.weightx=0.35;
        c5.weighty=0.8;
        c5.fill=GridBagConstraints.BOTH;
        c5.anchor=GridBagConstraints.NORTHWEST;
        gbl.setConstraints(jp5, c5);

        jb6=new JButton("Select File");
        jb6.setFont(new Font("Arial", 0, 17));
        GridBagConstraints gc7=new GridBagConstraints();
        gc7.gridx=0;
        gc7.gridy=0;
        gc7.weightx=0.5;
        gc7.weighty=1;
        gc7.ipadx=10;
        gc7.ipady=20;
        gc7.anchor=GridBagConstraints.CENTER;
        gbl2.setConstraints(jb6, gc7);
        jp5.add(jb6);

        jb7=new JButton("Send File");
        jb7.setFont(new Font("Arial", 0, 17));
        jb7.setEnabled(false);
        GridBagConstraints gc8=new GridBagConstraints();
        gc8.gridx=1;
        gc8.gridy=0;
        gc8.weightx=0.5;
        gc8.weighty=1;
        gc8.ipadx=10;
        gc8.ipady=20;
        gc8.anchor=GridBagConstraints.CENTER;
        gbl2.setConstraints(jb7, gc8);
        jp5.add(jb7);

        jb7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Thread t=new Thread()
                {
                   public void run()
                   {
                        filetransfer=1;
                        jb7.setEnabled(false);
                        server_gui.jt2.append("\n-->Wait....... !");
                        try
                        {
                            jb7.setEnabled(false);
                            connect.comp.sendname(fp.sendfile.getName());
                            for(int i=0;i<25;i++)
                            {
                              fp.readIn();
                              connect.comp.sendfile(fp.data);
                            }
                            server_gui.jt2.append("\n File sent successfully !");
                            filetransfer=0;
                        }
                        catch (RemoteException ex)
                        {
                            server_gui.jt2.append("\n Could not send the file. \nCheck other side if the server is running, connect it and try again  !");
                            SwingUtilities.invokeLater(new  Runnable() 
                            {
                                public void run() 
                                {
                                    jb7.setEnabled(false);
                                }
                            });
                            filetransfer=0;
                        }
                        catch(Exception e)
                        {
                            server_gui.jt2.append("\n Could not send the file. Connect the server . !");
                            SwingUtilities.invokeLater(new  Runnable()
                            {
                                public void run()
                                {
                                    jb7.setEnabled(false);
                                }
                            });
                            filetransfer=0;
                        }
                   }
                };
                t.start();
            }
        });

        this.getContentPane().add(jp5);

        jfc=new JFileChooser();

        jb6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(clientrun==0)
                {
                   jfc.showOpenDialog(server_gui.this);
                   File file=null;
                   file=jfc.getSelectedFile();
                   if(file!=null)
                   {
                      fp=new FilePacket(file);
                      jfc.setSelectedFile(null);
                      jb7.setEnabled(true);
                   }
                   else
                   {
                      jb7.setEnabled(false);
                      jfc.setSelectedFile(null);
                   }
                }
                else
                {
                    JOptionPane.showMessageDialog(server_gui.this,"To send file first stop your remote control window");
                }
            }
        });

        addWindowListener(new WindowAdapter()
        {
           public void windowClosing(WindowEvent e)
           {
               if(started==2)
               {
                  scon.s.stopserver();
               }
               if(connected==2)
               {
                  connect.comp=null;
               }
               File f=new File("history.txt");
               try
               {
                   PrintStream ps=new PrintStream(new FileOutputStream(f,true));
                   ps.println(jt2.getText());
                   ps.close();
               }
               catch (FileNotFoundException ex)
               {
                   Logger.getLogger(server_gui.class.getName()).log(Level.SEVERE, null, ex);
               }
               dispose();
               System.exit(0);
           }
        });

        setSize(1024,800);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        try
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
               if ("Nimbus".equals(info.getName()))
               {
                  UIManager.setLookAndFeel(info.getClassName());
                  break;
               }
            }
        }
        catch (Exception e)
        {
        }
        new server_gui();
    }
}
class jpanel extends JPanel
{
    int i;
    Image ii;
    Rectangle rct;

    jpanel(int a)
    {
        i=a;
    }

    public void paintComponent(Graphics g)
    {
        if(i==1)
        {
            Graphics2D g2d=(Graphics2D)g;
            Rectangle rct=this.getBounds();
            GradientPaint  p=new GradientPaint(0f,(float)rct.getMaxY()/2, new Color(95,88,88), (float)rct.getMaxX(),(float)rct.getMaxY()/2, new Color(95,88,88));
            g2d.setPaint(p);
            g2d.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
            g2d.setColor(new Color(255,255,255));
            g2d.setFont(new Font("Arial", 0,40));
            g2d.drawString("    Welcome to Java Remote Computing", (int)((100*10)/rct.getMaxX()),(int)rct.getMaxY()/2);
            g2d.setFont(new Font("Arial", 1,20));
            g2d.drawString("(OpenSource)", (int)rct.getMaxX()-(int)((100*30)/rct.getMaxX()),(int)rct.getMaxY()-(int)((100*20)/rct.getMaxY()));
        }
        if(i==2)
        {
            Rectangle rct=this.getBounds();
            g.setColor(Color.white);
            g.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
            g.setColor(Color.black);
            int x=50;
            g.setFont(new Font("Arial", 0, 15));
        }
        if(i==3)
        {
            Rectangle rct=this.getBounds();
            Graphics2D g2d=(Graphics2D)g;
            GradientPaint  p=new GradientPaint(80f,0f, new Color(255,255,255), 80,400, new Color(255,255,255));
            g2d.setPaint(p);
            g2d.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
        }
        if(i==4)
        {
            Rectangle rct=this.getBounds();
            g.setColor(Color.white);
            g.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 15));
        }
        if(i==5)
        {
            Rectangle rct=this.getBounds();
            Graphics2D g2d=(Graphics2D)g;
            GradientPaint  p=new GradientPaint(80f,0f, new Color(255,255,255), 80,400, new Color(255,255,255));
            g2d.setPaint(p);
            g2d.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Arial", 3,40));
            g2d.drawString("File Transfer",(int)((rct.getMaxX()*2)/100),(int)((rct.getMaxY()*5)/100));
        }
    }
}