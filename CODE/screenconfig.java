//package javaremotecomputing;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class screenconfig extends JPanel
{
  JLabel jl1,jl2;

  JComboBox jcb1,jcb2;

  String[] screentype={"Full Colors","16 bit color","256 colors","black and white"};
  String[] resolution={"1920-1080","1366-768","1280-1024","1280-720","1024-768","800-600"};

  int type,size;

  Dimension[] d;

  screenconfig()
  {
     d=new Dimension[6];
     d[0]=new Dimension(1920,1080);
     d[1]=new Dimension(1366,768);
     d[2]=new Dimension(1280,1024);
     d[3]=new Dimension(1280,720);
     d[4]=new Dimension(1024,768);
     d[5]=new Dimension(800,600);

     this.setLayout(new GridLayout(2,2));
     jl1=new JLabel("Type of screen :");
     this.add(jl1);

     jcb1=new JComboBox(screentype);
     jcb1.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e)
        {
            type=jcb1.getSelectedIndex();
        }
     });
     this.add(jcb1);

     jl2=new JLabel("Resolotion");
     this.add(jl2);

     jcb2=new JComboBox(resolution);
     jcb2.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e)
        {
            size=jcb2.getSelectedIndex();
        }
     });
     this.add(jcb2);
  }
}
