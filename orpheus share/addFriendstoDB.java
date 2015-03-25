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
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jcub
 */
public class addFriendstoDB implements ActionListener{
    BufferedReader in1;
    PrintWriter out1;
    String userid,frnd_add;
    JPanel p1,p2,p3;
    JTextField t1;
    JLabel l1;
    

   public addFriendstoDB(BufferedReader in,PrintWriter out,String id,JTextField t, JLabel l, JPanel p)
   {
       in1=in;
       out1=out;
       userid=id;
       p1=p;
       t1=t;
       l1=l;
   }

   public void actionPerformed(ActionEvent e)
   {
       System.out.println("addFriendstoDB");
       frnd_add=t1.getText();
       System.out.println("TO ADD:"+frnd_add);
       p1.remove(l1);
       
       try {
            out1.println("*addfriends");
            out1.println(userid);
            out1.println(frnd_add);
            String reply = in1.readLine();
            if (reply.compareTo("*nosuchuser") == 0)
                l1 = new JLabel(" No such user exists!!");
            else if (reply.compareTo("*requestexists") == 0) 
                l1 = new JLabel(" A request exists already!!");
            else if (reply.compareTo("*requestprocessed") == 0)
                l1 = new JLabel(" Request has been placed!!");
            else if (reply.compareTo("*alreadyfriends") == 0)
                l1 = new JLabel(" Already a friend!!");
            else if (reply.compareTo("*ownrequest") == 0)
                l1 = new JLabel(" Be serious. That's urself!!");

            p1.add(l1, BorderLayout.NORTH);
           
        } catch (IOException ex) {
            Logger.getLogger(addFriendstoDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             p1.repaint();
             p1.validate();
        }
    }
}
