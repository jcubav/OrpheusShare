package server_package;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jcub
 */
public class validate {
public static int validatelogin(String loginid,String passwords,Connection con)
   {
      int temp = 0;
      String s = null;
      try
      {
      //Class.forName("com.mysql.jdbc.Driver");
      if(!con.isClosed())
      {
      System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
      Statement stmt = (Statement) con.createStatement();
	  ResultSet rs1 = stmt.executeQuery("SELECT password FROM user where username='"+loginid+"'");
      while (rs1.next())
           s= rs1.getString("password");
       
      if(passwords.compareTo(s)==0){
          rs1 = stmt.executeQuery("SELECT status FROM nowonline WHERE user='"+loginid+"'");
          while (rs1.next())
               s= rs1.getString("status");
          
          if (s.compareTo("1")==0)
              temp=2;
          else{
              stmt.execute("UPDATE nowonline SET status=1 WHERE user='"+loginid+"'");
              temp=1;
              }
         }
        }
      }
      catch(Exception e)
      {
       return temp;
      }
      finally{
          return temp;
      }
    }
}
