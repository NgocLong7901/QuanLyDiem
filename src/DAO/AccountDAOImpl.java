/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.AccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class AccountDAOImpl implements AccountDAO {

    @Override
    public String getUserType(String username) {
         try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Accounts WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String userType = rs.getString("userType");
                conn.close();
                return userType;
            }

        }
            catch (SQLException ex) {
                Logger.getLogger(TablePoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }
    
}
