/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcub
 */
public class socketpeer extends Thread{
    private Socket s=null;
    String username;
    public socketpeer(Socket ss)
    {
        super("ServerThread");
        this.s=ss;
        
    }
    @Override
    public void run(){
        BufferedReader in = null;
        try {
              System.out.println("Socketpeer for sending song established..");
              in = new BufferedReader(new InputStreamReader(s.getInputStream()));
              PrintWriter out = new PrintWriter(s.getOutputStream(), true);
              String filename = in.readLine();
              System.out.println("Sending "+filename);
              File myFile = new File ("C:/file/"+filename);
              byte [] mybytearray  = new byte [(int)myFile.length()];
              FileInputStream fis = new FileInputStream(myFile);
              
              fis.read(mybytearray,0,mybytearray.length);
              OutputStream os = s.getOutputStream();
              
              //System.out.println("Size :"+mybytearray.length);
              int off = 0;
              int x = 1024;
              int y = mybytearray.length-1;

              while(classlistens.online && off < y)
              {
                  os.write(mybytearray,off,x);
                  off = off + x;
                  if((y - off)<1024)
                         x = y - off;
              }

              /*if(off < y)
                  out.println("*interrupted");
              else
              {
                  System.out.println("Sending "+filename+" completed.");
                  out.println("*not_interrupted");
              }*/

              //System.out.println(filename+" successfully played."); //song might have been stopped by user
              
            } catch (IOException ex) {
                    System.out.println("Connection has been terminated by listener.");
                    Logger.getLogger(socketpeer.class.getName()).log(Level.SEVERE, null, ex);
            }  finally {
                try {
                    in.close();
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(socketpeer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }
}
