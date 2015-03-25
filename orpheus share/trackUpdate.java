/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

public class trackUpdate {

  int tabCounter = 0;
  BufferedReader in1;
    PrintWriter out1;
    String uname;

trackUpdate(BufferedReader in,PrintWriter out,String user){
    in1=in;
    out1=out;
    uname=user;
}
  public void listFilesAndFolders(String folder) {

    File file = new File(folder);
    File[] fileArray = file.listFiles();
    out1.println(uname);
    if (!file.exists() || !file.isDirectory()) {

      System.out.println("Parameter is not a directory");
      System.exit(1);

    }
    for (int i = 0; i < fileArray.length; i++) {

      if (fileArray[i].isDirectory())
      {//listFilesAndFolders(fileArray[i].getAbsolutePath());
      }
      else
          out1.println(fileArray[i].getName());

      }
    out1.println("*endofplaylist");
  }
}




