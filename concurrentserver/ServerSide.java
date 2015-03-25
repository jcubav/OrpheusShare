package server_package;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


class SocketServer extends Thread{
    private Socket s=null;
    String username;
    boolean logged;

    public SocketServer(Socket ss)
    {
        super("ServerThread");
        this.s=ss;
        logged = false;
    }
    @Override
    public void run()
    {
        Connection con1 = null;
        try {
            con1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/orpheus_share","orpheusthegreat","clear");
            } catch (SQLException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String protocol;
            try {
                while (true) {
                    protocol = in.readLine();
                    System.out.println("MAIN PROTOCOL:"+protocol);

                    //LOGIN
                    if (protocol.compareTo("*login") == 0) {
                        String uname = in.readLine();
                        String pass = in.readLine();
                        int valid = validate.validatelogin(uname, pass, con1);
                        System.out.println("login block");
                        if(valid==2)
                            out.println("*alreadylogged");
                        else if(valid==1){
                            out.println("*validatedlogin");
                            logged = true;
                        }
                        else
                            out.println("*notvalid");
                        username = uname;
                    }

                    //LOGGING OUT
                    else if(protocol.compareTo("*logout")==0)
                               break;

                    //SETTING IP
                    else if(protocol.compareTo("*IP")==0){
                        String ip = in.readLine();
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            stmt1.execute("UPDATE nowonline SET ip='"+ip+"' WHERE user='"+username+"'");
                        }
                    }


                    //GETTING IP
                    else if(protocol.compareTo("*getIP")==0){
                        String name = in.readLine();
                        String ip = null;
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            ResultSet rs = stmt1.executeQuery("SELECT ip FROM nowonline WHERE user='"+name+"'");
                            while(rs.next())
                                  ip = rs.getString("ip");
                            out.println(ip);
                        }
                    }


                    //GETTING FRIEND'S SONGS
                    else if (protocol.compareTo("*getfriendsongs") == 0) {
                        String tablename=in.readLine();
                        String songname,status = null;
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt = con1.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT status FROM nowonline WHERE user='"+tablename+"'");
                                while(rs.next())
                                   status = rs.getString("status");
                            if(status.compareTo("1")==0){
                                rs = stmt.executeQuery("SELECT * FROM "+tablename);
                                while (rs.next()) {
                                    songname = rs.getString("songname");
                                    out.println(songname);
                                }
                                 out.println("*endofsongs");
                            }
                            else
                               out.println("*frndhasloggedout");
                        }

                    }
                        

                    //CREATING USER SONG TABLE
                    else if(protocol.compareTo("*sendingsongtable")==0){
                        String uname = in.readLine();
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            stmt1.execute("CREATE TABLE "+uname+"(songname  varchar(150))");
                        String sname= in.readLine();
                        java.sql.Statement stmt2 = con1.createStatement();
                        while(sname.compareTo("*endofplaylist")!=0)
                        {
                         stmt2.execute("INSERT INTO " + uname + " VALUES('" + sname + "')");
                         sname= in.readLine();
                        }
                    }
                    }


                    //ACCEPTING A REQUEST
                    else if(protocol.compareTo("*acceptrequest")==0)
                    {
                        String uname = in.readLine();
                        String fname = in.readLine();
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            java.sql.Statement stmt2 = con1.createStatement();
                            stmt1.execute("INSERT INTO friends VALUES('" + fname + "','" + uname + "')");
                            stmt2.execute("DELETE FROM addfriends WHERE (newfriend='" + fname + "'AND friend='" + uname + "')");
                        }
                   }


                    //DECLINING A REQUEST
                    else if(protocol.compareTo("*deleterequest")==0)
                    {
                        String uname = in.readLine();
                        String fname = in.readLine();
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            stmt1.execute("DELETE FROM addfriends WHERE (newfriend='" + fname + "'AND friend='" + uname + "')");
                        }
                    }


                    //ADD FRIENDS-REQUEST
                    else if(protocol.compareTo("*addfriends")==0){
                        if (!con1.isClosed()) {

                            String uname = in.readLine();
                            int exist=0,found=0;
                            String n1,n2;
                            String frnd = in.readLine();
                            if(uname.compareTo(frnd)==0)
                                out.println("*ownrequest");
                            else
                            {
                            Statement stmt = (Statement) con1.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT username,name FROM user");
                            while (rs.next()) {
                                n1 = rs.getString("username");
                                n2 = rs.getString("name");
                                if (n1.compareTo(frnd)==0||n2.compareTo(frnd)==0)
                                {    found=1; break; }
                            }
                            if(found==0)
                                out.println("*nosuchuser");
                            rs = stmt.executeQuery("SELECT newfriend,friend FROM addfriends");
                            while (rs.next())
                            {
                                n1 = rs.getString("newfriend");
                                n2 = rs.getString("friend");
                                if ((n1.compareTo(uname)==0&&n2.compareTo(frnd)==0)||(n1.compareTo(frnd)==0&&n2.compareTo(uname)==0))
                                {    out.println("*requestexists");
                                     exist=1; break;
                                }
                            }
                            rs = stmt.executeQuery("SELECT f1,f2 FROM friends ");
                            while (rs.next()) {
                                n1 = rs.getString("f1");
                                n2 = rs.getString("f2");
                                if ((n1.compareTo(uname)==0&&n2.compareTo(frnd)==0)||(n1.compareTo(frnd)==0&&n2.compareTo(uname)==0))
                                {    out.println("*alreadyfriends");
                                     exist=1; break;
                                }
                             }

                            if(found==1 && exist==0)
                            {
                            Statement stmt1 = (Statement) con1.createStatement();
                            stmt1.execute("INSERT INTO addfriends VALUES('"+uname+"','"+frnd+"')");
                            out.println("*requestprocessed");
                            }
                           }
                         }
                     }

                    //EXISTING FRIEND REQUEST FOR CLIENT
                    else if (protocol.compareTo("*friendrequest") == 0) {
                        String st;
                        if (!con1.isClosed()) {
                            Statement stmt = (Statement) con1.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT newfriend FROM addfriends where friend='" + username + "'");
                            while (rs.next()) {
                                st = rs.getString("newfriend");
                                out.println(st);
                            }
                             out.println("*endofrequest");
                     }}

                    
                    //GETTING FRIEND STATUS
                    else if(protocol.compareTo("*getstatus")==0){
                        String name = in.readLine();
                        //System.out.println(name);
                        String status = null;
                        if (!con1.isClosed()) {
                            java.sql.Statement stmt1 = con1.createStatement();
                            ResultSet rs = stmt1.executeQuery("SELECT status FROM nowonline WHERE user='"+name+"'");
                            while(rs.next())
                                  status = rs.getString("status");
                            out.println(status);
                            //System.out.println(status);
                        }
                    }

                    
                   //LISTING FRIENDS
                    else if (protocol.compareTo("*listfriends") == 0) {
                        String s1 = null;
                        String s2 = null;
                        String status = null;
                        if (!con1.isClosed()) {
                            //System.out.println("hii");
                            Statement stmt1 = (Statement) con1.createStatement();
                            Statement stmt2 = (Statement) con1.createStatement();
                            Statement stmt3 = (Statement) con1.createStatement();
                            ResultSet rs1;

                            ResultSet rs2 = stmt1.executeQuery("SELECT f1 FROM friends where f2='" + username + "'");
                            while (rs2.next()) {
                                s1 = rs2.getString("f1");
                                rs1 = stmt3.executeQuery("SELECT status FROM nowonline WHERE user='"+s1+"'");
                                while(rs1.next())
                                   status = rs1.getString("status");
                                out.println(s1);
                                out.println(status);
                               }
                            ResultSet rs3 = stmt2.executeQuery("SELECT f2 FROM friends where f1='" + username + "'");
                            while (rs3.next()) {
                                s2 = rs3.getString("f2");
                                rs1 = stmt3.executeQuery("SELECT status FROM nowonline WHERE user='"+s2+"'");
                                while(rs1.next())
                                   status = rs1.getString("status");
                                out.println(s2);
                                out.println(status);
                            }
                            out.println("*endoflist");
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            out.close();
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if ((!con1.isClosed()) && logged) {
                    java.sql.Statement stmt1 = con1.createStatement();
                    stmt1.execute("DROP TABLE " + username);
                    stmt1.execute("UPDATE nowonline SET status=0 WHERE user='"+username+"'");
                    stmt1.execute("UPDATE nowonline SET ip='' WHERE user='"+username+"'");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }
}
