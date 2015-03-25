/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.JButton;

/**
 *
 * @author jcub
 */
public class acceptFriend implements ActionListener{
    BufferedReader in1;
    PrintWriter out1;
    String userid,frnd_add,yesorno;
    
    public acceptFriend(BufferedReader in,PrintWriter out,String id,String frnd,String yesno)
   {
       in1=in;
       out1=out;
       userid=id;
       frnd_add=frnd;
       yesorno=yesno;
   }

   public void actionPerformed(ActionEvent e)
   {
       if(yesorno.compareTo("yes")==0)
       {
           out1.println("*acceptrequest");
           out1.println(userid);
           out1.println(frnd_add);
           playerbutton.ref.doClick();
       }
       if(yesorno.compareTo("no")==0)
       {
           out1.println("*deleterequest");
           out1.println(userid);
           out1.println(frnd_add);
           playerbutton.ref.doClick();
       }

   }


}

