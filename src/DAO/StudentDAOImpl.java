package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Interfaces.StudentDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import ql_diem.Models.Student;

/**
 *
 * @author MSIISM
 */
public class StudentDAOImpl implements StudentDAO  {

    @Override
    public ArrayList<Student> getStudents() {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Student";
            ArrayList<Student> studentList = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("studenId"));
                student.setStudentName(rs.getNString("studentName"));
                student.setGender(rs.getBoolean("gender"));
                student.setBirthDate(rs.getDate("birthDate"));
                student.setAddress(rs.getNString("address"));
                student.setMajor(rs.getString("mojor"));
                student.setClassName(rs.getString("class"));
                studentList.add(student);
            }
            ps.close();
            rs.close();
            conn.close();
            return studentList;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "INSERT INTO Student(studenId,studentName, gender, birthDate, address, mojor, class) VALUES(?,?,?,?,?,?,?)";
            ArrayList<Student> studentList = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getStudentName());
            ps.setBoolean(3, student.getGender());
            ps.setDate(4, new Date(student.getBirthDate().getTime()));
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getMajor());
            ps.setString(7, student.getClassName());
            
            return ps.executeUpdate() > 0;
           


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student, int index) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "UPDATE Student SET studenId=? ,studentName=?, gender=?, birthDate=?, address=?, mojor=?, class=? WHERE studenId=?";
           
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getStudentName());
            ps.setBoolean(3, student.getGender());
            ps.setDate(4, new Date(student.getBirthDate().getTime()));
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getMajor());
            ps.setString(7, student.getClassName());
            ps.setInt(8, index);
            
            return ps.executeUpdate() > 0;
           


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteStudent(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "DELETE FROM Student WHERE studenId=?";
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
    public String getStudentNameById(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT studentName FROM Student WHERE studenId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String tm = rs.getString("studentName");
                conn.close();
                return tm;
            }
        }
            catch (SQLException ex) {
                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

    @Override
    public String getClassById(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT class FROM Student WHERE studenId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String tm = rs.getString("class");
                conn.close();
                return tm;
            }
        }
            catch (SQLException ex) {
                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

///////////

    @Override
    public Student getStudentById(int studentId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Student WHERE studenId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,studentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("studenId"));
                student.setStudentName(rs.getNString("studentName"));
                student.setGender(rs.getBoolean("gender"));
                student.setBirthDate(rs.getDate("birthDate"));
                student.setAddress(rs.getNString("address"));
                student.setMajor(rs.getString("mojor"));
                student.setClassName(rs.getString("class"));
                return student;
            }
            
        }
            catch (SQLException ex) {
                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

    @Override
    public ArrayList<String> getAllStudentId() {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT StudenId FROM Student";
            ArrayList<String> list = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String subjectName = (rs.getString("studenId"));
               
                list.add(subjectName);
            }
            ps.close();
            rs.close();
            conn.close();
            return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
