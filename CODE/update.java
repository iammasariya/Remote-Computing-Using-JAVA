//package javaremotecomputing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class update extends JFrame
{
   GridBagLayout gbl;
   JTextArea jt1;

   jpan jp;

   JButton jb1;

   update()
   {
      super("Update");
      gbl=new GridBagLayout();
      this.setLayout(gbl);
      
      GridBagConstraints c0=new GridBagConstraints();
      jp=new jpan(0);
      c0.gridx=0;
      c0.gridy=0;
      c0.weightx=1.0;
      c0.ipady=70;
      c0.fill=GridBagConstraints.BOTH;
      c0.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jp, c0);
      this.add(jp);

      GridBagConstraints c1=new GridBagConstraints();
      jt1=new JTextArea();
      jt1.setFont(new Font("arial",1,20));
      jt1.setLineWrap(true);
      jt1.setWrapStyleWord(true);
      jt1.setBorder(new TitledBorder("update"));
      jt1.setBackground(Color.white);
      jt1.setText("\nVersion 1.0 \n\nThis version supports remote control,chatting and file transfer in LAN only \n\n"
              + "There are currently no Updates availabel");
      jt1.setEditable(false);
      c1.gridx=0;
      c1.gridy=1;
      c1.weightx=1.0;
      c1.weighty=0.7;
      c1.fill=GridBagConstraints.BOTH;
      c1.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jt1, c1);
      this.add(jt1);

      GridBagConstraints c2=new GridBagConstraints();
      jb1=new JButton("Update");
      //jb1.setEnabled(false);
      c2.gridx=0;
      c2.gridy=2;
      c2.weightx=1.0;
      c2.weighty=0.1;
      c2.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jb1, c2);
      this.add(jb1);

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            dispose();
            server_gui.update=0;
         }
      });

      this.setResizable(false);
      this.setSize(500,550);
      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      this.setVisible(true);
   }
    public static void main(String[] args)
    {
       new update();
    }
}

