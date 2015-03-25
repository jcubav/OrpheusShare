/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jdesktop.application.SingleFrameApplication;

/**
 *
 * @author jcub
 */
public class loggingout implements ActionListener{

    PrintWriter out;
    JFrame f;
    String user;
    SingleFrameApplication m;
    OrpheusshareView o;
    Thread t;

    public loggingout(PrintWriter out1,JFrame f1,SingleFrameApplication m1,OrpheusshareView o1,String user1,Thread t1)
    {
        out = out1;
        f = f1;
        user = user1;
        m = m1;
        o = o1;
        t=t1;

    }

    public void actionPerformed(ActionEvent e) {
        f.dispose();
        classlistens.online = false;
        try {
            classlistens.ss.close();
            System.out.println("classlistens thread closed");
        } catch (IOException ex) {
            Logger.getLogger(loggingout.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("*logout");
        o.jLabel3.setText(user+" has logged out..");
        m.show(o);
    }


}
