/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.StudentPointDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSIISM
 */
public class StudentPointDAOImpl implements StudentPointDAO{

    @Override
    public int getSubjectIdByTPId(int tbPointId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql="SELECT subjectId FROM TablePoint WHERE tbPointId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,tbPointId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int tm = rs.getInt("subjectId");
                conn.close();
                return tm;
            }   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentPointDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    
    }
}
