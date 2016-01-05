//package javaremotecomputing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class howto extends JFrame
{
   GridBagLayout gbl;
   JTextArea jt1;
   JScrollPane jsp;

   jpan jp;

   howto()
   {
      gbl=new GridBagLayout();
      this.setLayout(gbl);

      GridBagConstraints c0=new GridBagConstraints();
      jp=new jpan(1);
      c0.gridx=0;
      c0.gridy=0;
      c0.weightx=1.0;
      c0.weighty=0.15;
      c0.fill=GridBagConstraints.BOTH;
      c0.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jp, c0);
      this.add(jp);

      GridBagConstraints c1=new GridBagConstraints();
      jt1=new JTextArea();
      jt1.setFont(new Font("arial",1,20));
      jsp=new JScrollPane(jt1);
      jt1.setLineWrap(true);
      jt1.setWrapStyleWord(true);
      jt1.setBorder(new TitledBorder(null, "How to",0,0, new Font("arial",0,20)));
      jt1.setBackground(Color.white);
      jt1.setText("\nOperating instructions for user:\n\n"
              +
"1> Java Remote Computing(JRA) works only in a Local Area Network. It will not work on the internet.(Future version will !) \n\n"+

"2> To use JRA you must have JRA.jar file at both the end computer in LAN and your pc's firewall should allow this application. Otherwise it will create some errors.(It may not display screen)\n\n"+

"3> To start the application just double click on the .jar file or goto cmd and change the directory to the directory where your jar file is and execute 'java -jar JRA.jar' command.\n\n" +

"4> Now wait untill your GUI starts properly.\n\n"+

"5> Once the GUI has been started first you have to start the server by clicking on the 'start server' button. It will prompt for the IP port number, user id ,password and SSL enable options. Ip is just for user's conveniency .It will be displayed at the server status area so user doesn't have to every time check ip from network connections.\n\n"+

"6> ID and Password are for authentication and ssl is for security.\n\n" +

"7> Once server has been started you can control it from another pc by connecting it.\n\n"+

"8> To control the remoter pc you must first start the server at the remote pc.\n\n"+

"9> Now click on the 'connect server' button, it will prompt you for ip ,port ,user id and password which you just gave on the remote pc.\n\n"+

"10> If 'connection successfull' messege comes then you can use 'remote control' button to start remote controlling or you can send files from file transfer panel.\n\n"+

"11> For text chatting you must start server at both ends and connect it to each other. \n\n");
      jt1.setEditable(false);
      c1.gridx=0;
      c1.gridy=1;
      c1.weightx=1.0;
      c1.weighty=0.85;
      c1.fill=GridBagConstraints.BOTH;
      c1.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jsp, c1);
      this.add(jsp);

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            dispose();
            server_gui.learn=0;
         }
      });

      this.setResizable(false);
      this.setSize(800,800);
      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      this.setVisible(true);
   }
}
