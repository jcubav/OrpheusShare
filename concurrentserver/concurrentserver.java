package server_package;


import java.io.IOException;
import java.net.ServerSocket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jcub
 */
public class concurrentserver {
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss=null;
        try{
            ss=new ServerSocket(4444);
            
              }
        catch(IOException e){
            System.out.println("Could not listen to port");
            System.exit(1);
        }
        while(true)
        {
            new SocketServer(ss.accept()).start();
        }
    }
}