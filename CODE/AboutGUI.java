//package javaremotecomputing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class AboutGUI extends JFrame
{
   GridBagLayout gbl;
   JTextArea jt1;

   jpan jp;

   JButton jb1;

   AboutGUI()
   {
      super("About");
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
      jt1.setBorder(new TitledBorder("About"));
      jt1.setBackground(Color.white);
      jt1.setText("\nJRA is an application wich lets user control pc over local area network. Other featurs include chat ,file transfer(upto 2 gb).\n\n"
              +"Developed by :-\n\n"
              + "1> Krunal Panchal\n"
              + "2> Snehal Parikh \n\n"
              + "-->Any bugs, suggestions, questions, comments please contact us at\n\n"
              + "kgp.opensource@gmail.com \n"
              + "snehl_par@yahoo.com");

      jt1.setEditable(false);
      c1.gridx=0;
      c1.gridy=1;
      c1.weightx=1.0;
      c1.weighty=1;
      c1.fill=GridBagConstraints.BOTH;
      c1.anchor=GridBagConstraints.CENTER;
      gbl.setConstraints(jt1, c1);
      this.add(jt1);

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            dispose();
            server_gui.about=0;
         }
      });

      this.setResizable(false);
      this.setSize(500,550);
      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      this.setVisible(true);
   }
    public static void main(String[] args)
    {
       new AboutGUI();
    }
}

class jpan extends JPanel
{
   int a;
   jpan(int k)
   {
      a=k;
   }
   public void paintComponent(Graphics g)
   {
      if(a==0)
      {
         g.setFont(new Font("Arial",1,30));
         g.setColor(Color.blue);
         Rectangle rxt=this.getBounds();
         g.fillRect(0,0,(int)rxt.getMaxX(),(int)rxt.getMaxY());
         g.setColor(Color.yellow);
         g.drawString("Java Remote Computing",20,40 );
      }
      else if(a==1)
      {
         g.setFont(new Font("Arial",1,50));
         g.setColor(Color.black);
         g.drawString("How To",300,80 );
      }
   }
}