/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jcub
 */
public class playerbutton implements ActionListener{
    public static JButton ref;
    JButton b1;
    JLabel l1;
    String cur;
    JPanel sup;
    public boolean b;
    public boolean stop;

    public playerbutton(JButton b, JLabel l,JPanel p){
        b1=b;
        l1=l;
        sup=p;        
    }

    public void actionPerformed(ActionEvent e) {
        stop = false;
        cur = b1.getText();
        
        if (cur.compareTo("Play")==0)   
        {    b1.setText("Pause");
             l1.setText("   Song Playing...");
             b = true;
        }
        else if (cur.compareTo("Pause")==0)
        {
             b1.setText("Play");
             l1.setText("   Song Paused...");
             b = false;
        }
        else if (cur.compareTo("Return to songlist")==0)
        {
            l1.setText("  Stopped. Select new song..");
            stop = true;
            sup.removeAll();
            JLabel uselcted = new JLabel("   Song stopped. Select new song :");
            sup.add(uselcted, BorderLayout.NORTH);
            JLabel currentsong = new JLabel("  ----------------------------------");
            sup.add(currentsong, BorderLayout.CENTER);
            JButton pauseorplay = new JButton(" Play/Pause");
            sup.add(pauseorplay, BorderLayout.EAST);
            JLabel time = new JLabel("   --:--   ");
            sup.add(time, BorderLayout.WEST);
            JButton exit = new JButton("Exit friend's player");
            closesonglist ex = new closesonglist();
            exit.addActionListener(ex);
            sup.add(exit, BorderLayout.SOUTH);

        }
        else if (cur.compareTo("Exit friend's player")==0)
        {
            l1.setText("   Exiting Player..Returning to friends menu..");
            ref.doClick();
            stop = true;
        }
        
        b1.revalidate();
        l1.revalidate();
         
    }


}
