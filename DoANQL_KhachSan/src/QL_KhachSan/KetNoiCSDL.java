/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QL_KhachSan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHẠM T.Vi
 */
public class KetNoiCSDL {
  protected  Connection connection = null;
  protected Statement statement = null;
   protected ResultSet resultset;
  public KetNoiCSDL(){
     String StrSever = "DESKTOP-0VFU93U\\SQLEXPRESS2012";
        String StrDataBase = "QL_KhachSan";
        String StrUserName = "sa";
        String StrPassword = "1810nguyenthanhan";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QLKhachSan.class.getName()).log(Level.SEVERE, null, ex);
        }
        String connectionURL = "jdbc:sqlserver://" +StrSever+
                                ":1443;databaseName=" +StrDataBase +
                                 ";user ="+StrUserName+ ";password="+StrPassword;
        try {
            connection = DriverManager.getConnection(connectionURL);
            statement = connection.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
            
        } catch (SQLException ex) {
            Logger.getLogger(QLKhachSan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connection!=null){
            System.out.println("Kết nối dến CSDL thành công!");
        }
        else{
            System.out.println("Lỗi kết nối! Kiểm tra lại!!!!!!!");
        }
    }
}
