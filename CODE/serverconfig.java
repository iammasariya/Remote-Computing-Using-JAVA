//package javaremotecomputing;

import java.awt.*;
import java.net.UnknownHostException;
import javax.swing.*;
import java.awt.event.*;
import java.net.InetAddress;

public class serverconfig extends JFrame
{
    GridBagLayout gbl;

    JPanel jp1;

    JButton jb1;
    JTextField jt1,jt2,jt3,jt4;
    JCheckBox jc1;
    JLabel jl1,jl2,jl3,jl4;

    static String ip,username,password;
    static int port;
    static Boolean ssl;
    Font f;

    InetAddress i;

    server s;
    Thread t;

    serverconfig()
    {
       super("Server Configuration");
       this.setLayout(new GridLayout(1,1));

       s=new server();

       ssl=false;
       f=new Font("Arial",0,17);

       gbl=new GridBagLayout();

       jp1=new JPanel();
       jp1.setLayout(gbl);

       GridBagConstraints c1=new GridBagConstraints();
       jl1=new JLabel("Port  :");
       jl1.setFont(f);
       c1.gridx=0;
       c1.gridy=0;
       c1.weightx=0.50;
       c1.weighty=0.1666;
       c1.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jl1, c1);
       jp1.add(jl1);

       GridBagConstraints c2=new GridBagConstraints();
       jl2=new JLabel("Your IP  :");
       jl2.setFont(f);
       c2.gridx=0;
       c2.gridy=1;
       c2.weightx=0.50;
       c2.weighty=0.1666;
       c2.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jl2, c2);
       jp1.add(jl2);

       GridBagConstraints c3=new GridBagConstraints();
       jt1=new JTextField("7000");
       jt1.setFont(f);
       c3.gridx=1;
       c3.gridy=0;
       c3.weightx=0.50;
       c3.weighty=0.1666;
       c3.ipadx=115;
       c3.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jt1, c3);
       jp1.add(jt1);

       GridBagConstraints c4=new GridBagConstraints();
       jt2=new JTextField("127.0.0.1");
       jt2.setFont(f);
       c4.gridx=1;
       c4.gridy=1;
       c4.weightx=0.50;
       c4.weighty=0.1666;
       c4.ipadx=80;
       c4.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jt2, c4);
       jp1.add(jt2);

       GridBagConstraints c5=new GridBagConstraints();
       jl3=new JLabel("Username  :");
       jl3.setFont(f);
       c5.gridx=0;
       c5.gridy=2;
       c5.weightx=0.50;
       c5.weighty=0.1666;
       c5.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jl3, c5);
       jp1.add(jl3);

       GridBagConstraints c6=new GridBagConstraints();
       jl4=new JLabel("Password  :");
       jl4.setFont(f);
       c6.gridx=0;
       c6.gridy=3;
       c6.weightx=0.50;
       c6.weighty=0.1666;
       c6.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jl4, c6);
       jp1.add(jl4);

       GridBagConstraints c7=new GridBagConstraints();
       jt3=new JTextField("admin");
       jt3.setFont(f);
       c7.gridx=1;
       c7.gridy=2;
       c7.weightx=0.50;
       c7.weighty=0.1666;
       c7.ipadx=110;
       c7.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jt3, c7);
       jp1.add(jt3);

       GridBagConstraints c8=new GridBagConstraints();
       jt4=new JTextField("admin");
       jt4.setFont(f);
       c8.gridx=1;
       c8.gridy=3;
       c8.weightx=0.50;
       c8.weighty=0.1666;
       c8.ipadx=110;
       c8.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jt4, c8);
       jp1.add(jt4);

       GridBagConstraints c9=new GridBagConstraints();
       jc1=new JCheckBox("Enable SSL");
       jc1.setFont(f);
	   jc1.setVisible(false);
       c9.gridx=0;
       c9.gridy=4;
       c9.weightx=1;
       c9.weighty=0.1666;
       c9.gridwidth=2;
       c9.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jc1, c9);
       jp1.add(jc1);

       jc1.addItemListener(new ItemListener()
       {
            public void itemStateChanged(ItemEvent e)
            {
                  if(jc1.isEnabled())
                  {
                      ssl=true;
                  }
                  else
                  {
                      ssl=false;
                  }
            }
        });

       GridBagConstraints c10=new GridBagConstraints();
       jb1=new JButton("OK");
       jb1.setFont(f);
       c10.gridx=0;
       c10.gridy=5;
       c10.weightx=1;
       c10.weighty=0.1666;
       c10.ipadx=50;
       c10.gridwidth=2;
       c10.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jb1, c10);
       jp1.add(jb1);

       jb1.addActionListener(new ActionListener()
       {
            public void actionPerformed(ActionEvent e)
            {
                port=Integer.parseInt(jt1.getText());
                ip=jt2.getText();
                username=jt3.getText();
                password=jt4.getText();
                s.server=true;
                t=new Thread(s);
                t.start();
                serverconfig.this.setVisible(false);
            }
        });

       this.getContentPane().add(jp1);

       setSize(500,500);
       addWindowListener(new WindowAdapter()
       {
           public void windowClosing(WindowEvent e)
           {
               setVisible(false);
	       SwingUtilities.invokeLater(new Runnable()
               {
                    public void run()
                    {
                        server_gui.jb1.setEnabled(true);
                        server_gui.jb2.setEnabled(true);
                    }
               });
               server_gui.started=0;
           }
       });
       setResizable(false);
    }

}