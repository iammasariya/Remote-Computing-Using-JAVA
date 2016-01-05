//package javaremotecomputing;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class clientconfig extends JFrame
{

    GridBagLayout gbl;

    JPanel jp1;

    JButton jb1,jb2;
    JTextField jt1,jt2,jt3,jt4;
    JCheckBox jc1;
    JLabel jl1,jl2,jl3,jl4,jl5;

    static String ip,username,password;
    static int port;
    connect connect_server;

    Font f;

    clientconfig()
    {
       super("client Configuration");
       this.setLayout(new GridLayout(1,1));

       f=new Font("Arial",0,17);

       connect_server=new connect();

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
       jl2=new JLabel("IP  :");
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
       c8.ipadx=120;
       c8.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jt4, c8);
       jp1.add(jt4);

       GridBagConstraints c9=new GridBagConstraints();
       jc1=new JCheckBox("Enable SSL");
       jc1.setFont(f);
       c9.gridx=0;
       c9.gridy=4;
       c9.weightx=1;
       c9.weighty=0.1666;
       c9.gridwidth=2;
       c9.anchor=GridBagConstraints.CENTER;
       gbl.setConstraints(jc1, c9);
       //jp1.add(jc1);

       GridBagConstraints c10=new GridBagConstraints();
       jb1=new JButton("Connect");
       jb1.setFont(f);
       c10.gridx=0;
       c10.gridy=5;
       c10.weightx=0.33;
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
                server_gui.jt2.append("\n-->Wait !");
                Thread t=new Thread(connect_server);
                t.start();
                clientconfig.this.dispose();
            }
        });

       this.getContentPane().add(jp1);

       setSize(500,500);
       addWindowListener(new WindowAdapter()
       {
           public void windowClosing(WindowEvent e)
           {
               dispose();
               SwingUtilities.invokeLater(new Runnable()
               {
                    public void run()
                    {
                        server_gui.jb3.setEnabled(true);
                        server_gui.jb4.setEnabled(true);
                    }
               });
	       server_gui.connected=0;
           }
       });
       setResizable(false);
       setVisible(true);
    }
}


