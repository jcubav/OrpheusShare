/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcub
 */
//Remember to kill thread at log off

public class classlistens implements Runnable{

    public classlistens(){
        online = true;
    }

    public static boolean online;
    public static ServerSocket ss;

    @Override
    public void run() {

        try{
            ss=new ServerSocket(5555);
            System.out.println("A port is ready.....listening for incomng connection..");
            }
        catch(IOException e){
            System.out.println("Could not listen to port");
            System.exit(1);
        }
        while(online)
        {   try {
                new socketpeer(ss.accept()).start();
            } catch (IOException ex) {
                Logger.getLogger(classlistens.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 }

