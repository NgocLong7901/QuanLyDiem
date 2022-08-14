/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.SubjectDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ql_diem.Models.Student;
import ql_diem.Models.Subject;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public ArrayList<Subject> getSubjects() {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Subject";
            ArrayList<Subject> subjectList = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subjectId"));
                subject.setSubjectName(rs.getString("subjectName"));
                subject.setCredits(rs.getInt("credits"));
                subject.setRequired(rs.getBoolean("required"));

                subjectList.add(subject);
            }
            ps.close();
            rs.close();
            conn.close();
            return subjectList;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addSubject(Subject subject) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "INSERT INTO Subject(subjectId  ,subjectname, credits   , required ) VALUES(?,?,?,?)";
            ArrayList<Subject> subjectList = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, subject.getSubjectId());
            ps.setString(2, subject.getSubjectName());
            ps.setInt(3, subject.getCredits());
            ps.setBoolean(4, subject.getRequired());
           
            
            return ps.executeUpdate() > 0;
           


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSubject(Subject subject, int index) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "UPDATE Subject SET subjectId=? ,subjectName=?, credits=?, required=? WHERE subjectId=?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, subject.getSubjectId());
            ps.setString(2, subject.getSubjectName());
            ps.setInt(3, subject.getCredits());
            ps.setBoolean(4, subject.getRequired());
            ps.setInt(5, index);
            
            return ps.executeUpdate() > 0;
           


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteSubject(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "DELETE FROM Subject WHERE subjectId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getSubjectNameById(int id) {

        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT subjectName FROM Subject WHERE subjectId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String tm = rs.getString("subjectName");
                conn.close();
                return tm;
            }

        }
            catch (SQLException ex) {
                Logger.getLogger(TablePoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

    @Override
    public int getSubjectId(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT subjectId FROM Subject WHERE subjectId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int tm = rs.getInt("subjectId");
                conn.close();
                return tm;
            }

        }
            catch (SQLException ex) {
                Logger.getLogger(TablePoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return -1;
    }

    @Override
    public int getCreditBySubjectId(int subjectId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT credits FROM Subject WHERE subjectId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,subjectId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int credit = rs.getInt("credits");
                conn.close();
                return credit;
            }

        }
            catch (SQLException ex) {
                Logger.getLogger(TablePoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return -1;
    }

    @Override
    public ArrayList<String> getAllSubjectName() {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Subject";
            ArrayList<String> subjectList = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String subjectName = (rs.getString("subjectName"));
               
                subjectList.add(subjectName);
            }
            ps.close();
            rs.close();
            conn.close();
            return subjectList;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSubjectIdByName(String subjectName) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT subjectId FROM Subject WHERE subjectName=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,subjectName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int subjectId = rs.getInt("subjectId");
                conn.close();
                return subjectId;
            }

        }
            catch (SQLException ex) {
                Logger.getLogger(TablePoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return -1;
    }
}
