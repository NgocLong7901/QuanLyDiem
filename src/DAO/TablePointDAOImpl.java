/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.TablePointDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class TablePointDAOImpl implements TablePointDAO {

    @Override
    public ArrayList<TablePoint> getTablePoints() {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT TablePoint.tbPointId, Subject.subjectId, Subject.subjectName, Subject.credits, TablePoint.className, TablePoint.dateTest, TablePoint.term, TablePoint.year\n"
                    + "FROM Subject INNER JOIN TablePoint ON Subject.subjectId = TablePoint.subjectId;";
            ArrayList<TablePoint> tablePointlist = new ArrayList<>();

            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TablePoint tablePoint = new TablePoint();
                tablePoint.setTbPointId(rs.getInt("tbPointId"));
                tablePoint.setSubjectId(rs.getInt("subjectId"));
                tablePoint.setClassName(rs.getString("className"));
                tablePoint.setDateTest(rs.getDate("dateTest"));
                tablePoint.setTerm(rs.getString("term"));
                tablePoint.setYear(rs.getString("year"));
                tablePointlist.add(tablePoint);
            }
            ps.close();
            rs.close();
            conn.close();
            return tablePointlist;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public boolean addTablePoint(TablePoint tablePoint) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "INSERT INTO TablePoint(tbPointId ,subjectId, className, dateTest ,term ,year ) VALUES(?,?,?,?,?,?)";
            ArrayList<TablePoint> list = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tablePoint.getTbPointId());
            ps.setInt(2, tablePoint.getSubjectId());
            ps.setString(3, tablePoint.getClassName());
            ps.setDate(4, new Date(tablePoint.getDateTest().getTime()));
            ps.setString(5, tablePoint.getTerm());
            ps.setString(6, tablePoint.getYear());
           
            
            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public boolean updateTablePoint(TablePoint tablePoint) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "UPDATE TablePoint SET tbPointId=? ,subjectId=?, className=?, dateTest=?, term=?, year=? WHERE tbPointId=?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tablePoint.getTbPointId());
            ps.setInt(2, tablePoint.getSubjectId());
            ps.setString(3, tablePoint.getClassName());
            ps.setDate(4, new Date(tablePoint.getDateTest().getTime()));
            ps.setString(5, tablePoint.getTerm());
            
            
            ps.setString(6, tablePoint.getYear());
            ps.setInt(7, tablePoint.getTbPointId());
            
            return ps.executeUpdate() > 0;
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public void deleteTablePoint(int id) {
       try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "DELETE FROM TablePoint WHERE tbPointId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getClassNamebyTpId(int tbPointId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT className FROM TablePoint WHERE tbPointId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tbPointId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String className = rs.getString("className");
                conn.close();
                return className;
            }  
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> getTermYear(int studentId) {
        try {
            ArrayList<String> list = new ArrayList();
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "select CONCAT(t.term, N', Năm học ', t.year) as termYear \n" +
                            "from TablePoint t inner join Point p on t.tbPointId = p.tbPointId \n" +
                            "where studentId = ? \n" +
                            "group by CONCAT(t.term, N', Năm học ', t.year)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String termYear = rs.getString("termYear");
                list.add(termYear);
            }  
            ps.close();
            rs.close();
            conn.close();
            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getTermYearByTPId(int tbPointId) {
        try {
            
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "Select term, year from TablePoint where tbPointId = ? group by term, year order by year";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,tbPointId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String termYear = rs.getString("term") + ", Năm học "+ rs.getString("year");
                ps.close();
                rs.close();
                conn.close();
                return termYear;

            }  
                    } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getCreditByTPId(int tbPointId){
        try {  
        Connection conn=JdbcSQLServerConnection.getConnection();
        String sql="SELECT credits FROM Subject WHERE subjectId=(SELECT subjectId FROM TablePoint WHERE tbPointId=?)";
        
        
            PreparedStatement p=conn.prepareStatement(sql);
            p.setInt(1,tbPointId);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                int  tm=rs.getInt("credits");
                conn.close();
                return tm;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TablePointDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
