/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jcub
 */
public class refreshfriends implements ActionListener{
    BufferedReader in;
    PrintWriter out;
    JPanel p,q;
    String user;
    
    public refreshfriends(BufferedReader in1,PrintWriter out1,JPanel p1,JPanel p2,String user1)
    {
        in=in1;
        out=out1;
        p=p1;
        q=p2;
        user=user1;
    }
    public void actionPerformed(ActionEvent e) {
        p.removeAll();
        q.removeAll();
        try {
            JLabel friendslist = new JLabel("                                    FRIENDS                                ");
            p.add(friendslist);
            out.println("*listfriends");
            String fs,fn;
            fs = in.readLine();
            while (fs.compareTo("*endoflist") != 0) {
                    fn=fs;
                    System.out.println("frnd name:"+fn);
                    fs = in.readLine();
                    System.out.println("frnd status:"+fs);
                    if(fs.compareTo("1")==0){
                      JButton button = new JButton(fn);
                      button.setBackground(Color.RED);
                      //button.setAlignmentX(Component.CENTER_ALIGNMENT);
                      p.add(button);
                      listsongs b = new listsongs(in, out, fn);
                      button.addActionListener(b);
                    }
                    else{
                      JLabel lab = new JLabel(fn);
                      //lab.setAlignmentX(Component.CENTER_ALIGNMENT);
                      p.add(lab);
                    }
                    fs = in.readLine();
            }
            JLabel reqfrnds = new JLabel("                              FRIEND REQUESTS                              ");
            q.add(reqfrnds);
            out.println("*friendrequest");
            String req = in.readLine();
            if(req.compareTo("*endofrequest") == 0)
                    reqfrnds.setText("                            -NO FRIEND REQUESTS FOR YOU-                             ");
            while (req.compareTo("*endofrequest") != 0) {
                    JLabel friendrequest = new JLabel("Is " + req + " your friend?   ");
                    System.out.println(req);
                    q.add(friendrequest);
                    JButton yes = new JButton("yes");
                    q.add(yes);
                    acceptFriend a = new acceptFriend(in, out, user, req, "yes");
                    yes.addActionListener(a);
                    JButton no = new JButton("no");
                    acceptFriend b = new acceptFriend(in, out, user, req, "no");
                    no.addActionListener(b);
                    q.add(no);
                    req = in.readLine();
                }
        } catch (IOException ex) {
            Logger.getLogger(refreshfriends.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.repaint();
        q.repaint();
        p.validate();
        q.validate();
    }

}
