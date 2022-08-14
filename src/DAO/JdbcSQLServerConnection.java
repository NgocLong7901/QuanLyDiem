/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSIISM
 */
public class JdbcSQLServerConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        try {
            Connection conn = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;Database=db_qldiem;user=sa;password=123456;encrypt=true;trustServerCertificate=true;Trusted_Connection=True;");
            return conn;
        }
        catch(Exception ex){
            System.out.println("JDbc errror");
            ex.printStackTrace();
        }
        return null;
    }
    
}

