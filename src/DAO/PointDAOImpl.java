/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.PointDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ql_diem.Models.Point;
import ql_diem.Models.Student;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class PointDAOImpl implements PointDAO {

    @Override
    public ArrayList<Point> getPoints(int id) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "SELECT * FROM Point WHERE tbPointId=?";
            ArrayList<Point> pointList = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Point point = new Point();
                point.setTablePointId(rs.getInt("tbPointId"));
                point.setStudentId(rs.getInt("studentId"));
                point.setProcessPoint(rs.getDouble("processPoint"));
                point.setTestPoint(rs.getDouble("testPoint"));

                pointList.add(point);
            }
            ps.close();
            rs.close();
            conn.close();
            return pointList;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Point> getPointByTbPointId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addPoint(Point point) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "INSERT INTO Point(tbPointId,studentId, processPoint, testPoint) VALUES(?,?,?,?)";
            ArrayList<Student> studentList = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, point.getTablePointId());
            ps.setInt(2, point.getStudentId());
            ps.setDouble(3, point.getProcessPoint());
            ps.setDouble(4, point.getTestPoint());

            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePoint(int tbPointId, int studentId1, int studentId2, double processPoint, double testPoint) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "UPDATE Point SET studentId=?, processPoint=?, testPoint=? WHERE tbPointId=? and studentId=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, studentId2);
            ps.setDouble(2, processPoint);
            ps.setDouble(3, testPoint);
            ps.setInt(4, tbPointId);
            ps.setInt(5, studentId1);

            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void deletePoint(int tbPointId, int studentId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "DELETE FROM Point WHERE tbPointId=? and studentId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tbPointId);
            ps.setInt(2, studentId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Point> getPointsByStudentId(int studentId, String termYear) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "select t.tbPointId, p.studentId, p.processPoint, p.testPoint, CONCAT(t.term, N', Năm học ', t.year) as termYear \n"
                    + "from                       TablePoint t inner join Point p on t.tbPointId = p.tbPointId \n"
                    + "where                      CONCAT(t.term, N', Năm học ', t.year)=? and studentId=?";
            ArrayList<Point> pointList = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, termYear);
            ps.setInt(2, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Point point = new Point();
                point.setTablePointId(rs.getInt("tbPointId"));
                point.setStudentId(rs.getInt("studentId"));
                point.setProcessPoint(rs.getDouble("processPoint"));
                point.setTestPoint(rs.getDouble("testPoint"));

                pointList.add(point);
            }
            ps.close();
            rs.close();
            conn.close();
            return pointList;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getStudentId(int tbPointId) {
        try {
            Connection conn = JdbcSQLServerConnection.getConnection();
            String sql = "select * from Point where tbPointId = ? ";
            ArrayList<String> list = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, tbPointId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String studentId = (rs.getString("studentId"));

                list.add(studentId);
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
