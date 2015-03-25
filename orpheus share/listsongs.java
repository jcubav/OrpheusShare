/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jcub
 */
public class listsongs extends JFrame implements ActionListener{
    BufferedReader in1;
    PrintWriter out1;
    String friend,sname,ip;

    public listsongs(BufferedReader in,PrintWriter out,String frndid)
   {
       in1=in;
       out1=out;
       friend=frndid;
   }

     public void actionPerformed(ActionEvent e) {
        try {
            out1.println("*getIP");
            out1.println(friend);
            ip=in1.readLine();

            out1.println("*getfriendsongs");
            out1.println(friend);

            JFrame songs = new JFrame(friend + "'s Songs");
            songs.setUndecorated(true);
            songs.setSize(400, 400);
            songs.setLocationRelativeTo(null);
            //songs.setAlwaysOnTop(true);
            songs.setVisible(true);

            Container songcontainer = songs.getContentPane();
            songcontainer.setLayout(new BorderLayout());
            //songcontainer.setBackground(Color.BLACK);

            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            p1.setLayout(new FlowLayout());
            p2.setLayout(new BorderLayout());
            //p1.setBackground(Color.RED)
            //p2.setBackground(Color.RED);

            songcontainer.add(p1, BorderLayout.CENTER);
            songcontainer.add(p2, BorderLayout.SOUTH);
            JLabel uselcted = new JLabel("   Select a song:");
            p2.add(uselcted, BorderLayout.NORTH);
            JLabel currentsong = new JLabel("  ----------------------------------");
            p2.add(currentsong, BorderLayout.CENTER);
            JButton pauseorplay = new JButton(" Play/Pause");
            p2.add(pauseorplay, BorderLayout.EAST);
            JLabel time = new JLabel("   --:--   ");
            p2.add(time, BorderLayout.WEST);
            JButton exit = new JButton("Exit friend's player");
            closesonglist ex = new closesonglist();
            exit.addActionListener(ex);
            p2.add(exit, BorderLayout.SOUTH);
            
            closesonglist.slist = songs;
            closesonglist.mnframe.setVisible(false);

            sname = in1.readLine();
            if(sname.compareTo("*frndhasloggedout")==0){
                System.out.println(friend+"logged out");
                JLabel lab = new JLabel("    Sorry!! "+ friend+" has just logged out. Try later..");
                p1.add(lab);
            }

            else{
                connectforsongs conn;

                System.out.println(friend+"'s songs:");
                while (sname.compareTo("*endofsongs") != 0) {
                    System.out.println(sname);
                    JButton button = new JButton(sname);
                    p1.add(button);
                    conn = new connectforsongs(sname,ip,p1,p2,in1,out1,friend);
                    button.addActionListener(conn);
                    sname = in1.readLine();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(listsongs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
