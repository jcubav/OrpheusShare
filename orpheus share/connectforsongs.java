/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;

/**
 *
 * @author jcub
 */
public class connectforsongs implements ActionListener{
    String songname,friendip,fname;
    JPanel p,q;
    mPlayer player;
    JLabel time;
    playerbutton pp,st,et;
    Socket s;
    BufferedReader in;
    PrintWriter out;


    public connectforsongs(String sname, String fip,JPanel p2,JPanel p1,BufferedReader in1,PrintWriter out1,String f){
        songname=sname;
        friendip=fip;
        p=p1;
        q=p2;
        in=in1;
        out=out1;
        fname=f;
    }


    public void actionPerformed(ActionEvent e) {
        try {

            q.setVisible(false);
            p.removeAll();
            p.setLayout(new BorderLayout());
            JLabel uselcted = new JLabel("   You selected the song:");
            p.add(uselcted, BorderLayout.NORTH);
            JLabel currentsong = new JLabel(" "+songname);
            p.add(currentsong, BorderLayout.CENTER);
            JButton pauseorplay = new JButton("Play");
            pp = new playerbutton(pauseorplay, uselcted, p);
            pauseorplay.addActionListener(pp);
            p.add(pauseorplay, BorderLayout.EAST);
            time = new JLabel("   00:00   ");
            p.add(time, BorderLayout.WEST);
                        
            JPanel goback = new JPanel();
            goback.setLayout(new BorderLayout());
            JButton stop = new JButton("Return to songlist");
            st = new playerbutton(stop, uselcted, p);
            stop.addActionListener(st);
            goback.add(stop, BorderLayout.EAST);
            JButton exit = new JButton("Exit friend's player");
            et = new playerbutton(exit, uselcted, p);
            exit.addActionListener(et);
            goback.add(exit, BorderLayout.WEST);
            p.add(goback, BorderLayout.SOUTH);

            p.repaint();
            p.revalidate();

            out.println("*getstatus");
            out.println(fname);
            String status = in.readLine();
            
            if(status.compareTo("1") == 0)
            {
                s = new Socket(friendip, 5555);
                System.out.println(fname+"'s IP: "+friendip);

                PrintWriter out2 = new PrintWriter(s.getOutputStream(), true);
                InputStream is = s.getInputStream();
                out2.println(songname);
                player = new mPlayer(is);

                PlayerThread pt = new PlayerThread();
                TimerThread tt = new TimerThread();
                pt.start();
                tt.start();

                System.out.print("STREAMING "+songname);
            }
            else
            {
                q.removeAll();
                JLabel lab = new JLabel("    Sorry!! "+ fname +" has just logged out. Try later..");
                q.add(lab);
                JButton exit2 = new JButton("Exit friend's player");
                closesonglist ex = new closesonglist();
                exit2.addActionListener(ex);
                p.add(exit2, BorderLayout.SOUTH);
                q.setVisible(true);
            }
        } catch (JavaLayerException ex) {
            Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


    class PlayerThread extends Thread
    {
        @Override
        public void run()
          {
            try {
                player.play();
                s.close();
                q.setVisible(true);
                p.removeAll();
                JLabel uselcted = new JLabel("   Song over. Select new song :");
                p.add(uselcted, BorderLayout.NORTH);
                JLabel currentsong = new JLabel("  ----------------------------------");
                p.add(currentsong, BorderLayout.CENTER);
                JButton pauseorplay = new JButton(" Play/Pause");
                p.add(pauseorplay, BorderLayout.EAST);
                JLabel time = new JLabel("   --:--   ");
                p.add(time, BorderLayout.WEST);
                JButton exit = new JButton("Exit friend's player");
                closesonglist ex = new closesonglist();
                exit.addActionListener(ex);
                p.add(exit, BorderLayout.SOUTH);

            } catch (IOException ex) {
                Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
    }

    class TimerThread extends Thread
    {
        @Override
        public void run()
          {
            int mn,sc,total;
            String min="",sec="",tm;
            total=0;
            boolean br = false;

            while (!player.isComplete()) {

                player.playing = pp.b;
                
                if(st.stop || et.stop){
                    try {
                        s.close();
                        player.close();
                    }
                    catch (IOException ex) { Logger.getLogger(connectforsongs.class.getName()).log(Level.SEVERE, null, ex); }
                    if(st.stop) q.setVisible(true);
                    if(et.stop){
                        closesonglist.slist.dispose();
                        closesonglist.mnframe.setVisible(true);
                    }
                    br = true;
                    break;
                }
                p.remove(time);
                mn = total/60;
                sc = total%60;
                min = Integer.toString(mn);
                sec = Integer.toString(sc);
                tm="   ";
                if (mn<10) tm+="0";
                tm+=min;
                tm+=":";
                if (sc<10) tm+="0";
                tm+=sec;
                tm+="   ";
                time = new JLabel(tm);
                System.out.println(tm);
                p.add(time, BorderLayout.WEST);
                p.revalidate();
                if(pp.b==true) total++;
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
           }
    }
}
