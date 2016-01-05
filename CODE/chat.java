//package javaremotecomputing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.rmi.*;
import javax.swing.text.DefaultCaret;

public class chat extends JFrame 
{
   JScrollPane jsp,jsp2;

   JPanel jp;

   jpanl jp2;

   JTextArea jt2;

   static JTextArea jt1;

   JButton jb1;

   JLabel jl;

   final int counter[];

   GridBagLayout gbl,gbl2,gbl3;

   String myname,clientname;

   int a;

   chat()
   {
      setSize(800,600);

      myname=serverconfig.username;
      
      Thread t=new Thread()
      {
         public void run()
         {
            try
            {
                clientname = connect.comp.getusername();
            }
            catch (RemoteException ex) 
            {
                JOptionPane.showMessageDialog(chat.this,"see other side if server is running, connect it and try again.\nchat will exit.");
                chat.this.dispose();
                server_gui.chatrun=0;
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(chat.this,"see other side if server is running, connect it and try again.\nchat will exit.");
                chat.this.dispose();
                server_gui.chatrun=0;
            }
         }
      };
      t.start();

      gbl=new GridBagLayout();
      gbl2=new GridBagLayout();
      gbl3=new GridBagLayout();

      this.setLayout(gbl2);

      counter=new int[1];
      counter[0]=0;

      jp2=new jpanl();
      jl=new JLabel(new ImageIcon("oom.png"));
      jp2.setLayout(gbl3);
      GridBagConstraints gbc=new GridBagConstraints();
      gbc.gridx=0;
      gbc.gridy=0;
      gbc.weightx=0.5;
      gbc.weighty=0.5;
      gbc.fill=GridBagConstraints.VERTICAL;
      gbc.anchor=GridBagConstraints.NORTHEAST;
      gbl3.setConstraints(jl,gbc);
      jp2.add(jl);

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
              chat.this.dispose();
              server_gui.chatrun=0;
          }
      });


      jp2.setBorder(new LineBorder(Color.darkGray));
      GridBagConstraints gc1=new GridBagConstraints();
      gc1.gridx=0;
      gc1.gridy=0;
      gc1.weightx=1;
      gc1.fill=GridBagConstraints.BOTH;
      gbl2.setConstraints(jp2, gc1);
      this.getContentPane().add(jp2);

      jp=new JPanel();
      jp.setLayout(gbl);
      GridBagConstraints gc2=new GridBagConstraints();
      jp.setBorder(new LineBorder(Color.darkGray));
      gc2.gridx=0;
      gc2.gridy=1;
      gc2.weighty=0.9;
      gc2.weightx=1;
      gc2.fill=GridBagConstraints.BOTH;
      gbl2.setConstraints(jp, gc2);
      this.getContentPane().add(jp);

      jt1=new JTextArea();
      DefaultCaret caret = (DefaultCaret)jt1.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
      jt1.setEditable(false);
      jsp=new JScrollPane(jt1);
      jsp.setBorder(new TitledBorder(null, "Chatting with "+clientname,2, 2,new Font("comic sans ms", 1, 20), Color.blue));
      GridBagConstraints c1=new GridBagConstraints();
      c1.gridx=0;
      c1.gridy=0;
      c1.weightx=1;
      c1.weighty=0.98;
      c1.gridwidth=2;
      c1.fill=GridBagConstraints.BOTH;
      gbl.setConstraints(jsp, c1);
      jp.add(jsp);

      jsp.setBackground(Color.blue);
      JScrollBar jsb=new JScrollBar();

      jsp.setVerticalScrollBar(jsb);
      jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jt1.setForeground(Color.yellow);
      jt1.setLineWrap(true);
      jt1.setWrapStyleWord(true);
      jt1.setFont(new Font("comic sans ms", 1, 20));
      jt1.setBackground(Color.blue);

      jt2=new JTextArea();
      DefaultCaret care = (DefaultCaret)jt2.getCaret();
      care.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

      jsp2=new JScrollPane(jt2);
      jt2.setBorder(new LineBorder(Color.darkGray));
      GridBagConstraints c2=new GridBagConstraints();
      c2.gridx=0;
      c2.gridy=1;
      c2.weightx=0.9;
      c2.weighty=0.02;
      c2.fill=GridBagConstraints.BOTH;
      gbl.setConstraints(jsp2, c2);
      jp.add(jsp2);

      jt2.setBackground(Color.blue);
      jt2.setLineWrap(true);
      jt2.setWrapStyleWord(true);
      jt2.setForeground(Color.yellow);
      jt2.setCaretColor(Color.orange);
      jt2.setFont(new Font("comic sans ms", 1, 20));
      
      jb1=new JButton("Send");
      jb1.setBackground(Color.blue);
      jb1.setForeground(Color.yellow);
      jb1.setFont(new Font("comic sans ms",1,30));
      GridBagConstraints c3=new GridBagConstraints();
      c3.gridx=1;
      c3.gridy=1;
      c3.weightx=0.1;
      c3.weighty=0.02;
      c3.fill=GridBagConstraints.BOTH;
      gbl.setConstraints(jb1, c3);
      jp.add(jb1);

      ActionListener acl=new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              final String s="\n\n"+myname+" :    "+jt2.getText();
              jt1.append(s);
              jt2.setText(null);
              Thread t=new Thread()
              {
		 public void run()
	         {
		    try
                    {
                       connect.comp.sendchat(s);
                    }
		    catch (RemoteException ex)
                    {
                       JOptionPane.showMessageDialog(chat.this,"see other side if server is running, connect it and try again.\nchat will exit.");
                       chat.this.dispose();
                       server_gui.chatrun=0;
                    }
                    catch(Exception e)
                    {
                       JOptionPane.showMessageDialog(chat.this,"try connecting the server again. chat will exit");
                       chat.this.dispose();
                       server_gui.chatrun=0;
                    }
                 }
              };
              t.start();
          }
      };
      jb1.addActionListener(acl);

      jt2.addKeyListener(new KeyAdapter()
      {
          public void keyPressed(KeyEvent e)
          {
              if(e.getKeyCode()==KeyEvent.VK_ENTER)
              {
                 final String s="\n\n"+myname+" :    "+jt2.getText();
              jt1.append(s);
              jt2.setText(null);
              Thread t=new Thread()
              {
		 public void run()
	         {
		    try
                    {
                       connect.comp.sendchat(s);
                    }
		    catch (RemoteException ex)
                    {
                       JOptionPane.showMessageDialog(chat.this,"see other side if server is running.\nIf it is running then connect it and try again.\nchat will exit.");
                       chat.this.dispose();
                       server_gui.chatrun=0;
                    }
                    catch(Exception e)
                    {
                       JOptionPane.showMessageDialog(chat.this,"try connecting the server again. chat will exit");
                       chat.this.dispose();
                       server_gui.chatrun=0;
                    }
                 }
              };
              t.start();
              e.consume();
              }
          }
      });

      this.setMaximizedBounds(new Rectangle(40,400));

      this.setUndecorated(true);
      //AWTUtilities.setWindowOpacity(this,0.7f);	   
	  this.setBackground(Color.blue);
      ComponentMover cm=new ComponentMover();
      cm.registerComponent(this);

      addWindowListener(new WindowAdapter() 
      {
          public void windowClosing(WindowEvent e)
          {
             dispose();
             server_gui.chatrun=0;
          }
      });

      setResizable(false);
      setVisible(true);
   }
}

class jpanl extends JPanel
{
   public void paintComponent(Graphics g)
   {
      Graphics2D g2d=(Graphics2D)g;
      Rectangle rct=this.getBounds();
      GradientPaint gp=new GradientPaint((float)rct.getMinX(),(float)rct.getMaxY()/2,Color.red,(float)rct.getMaxX(),(float)rct.getMaxY()/2,Color.darkGray);
      g2d.setPaint(gp);
      g2d.fillRect(0,0,(int)rct.getMaxX(),(int)rct.getMaxY());
   }
}