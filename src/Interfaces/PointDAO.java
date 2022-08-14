/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;
import ql_diem.Models.Point;

/**
 *
 * @author MSIISM
 */
public interface PointDAO {
    public ArrayList<Point> getPoints(int id);
    public ArrayList<Point> getPointsByStudentId(int studentId, String termYear);
    public ArrayList<Point> getPointByTbPointId(int id);
    public boolean addPoint(Point point);
    public boolean updatePoint(int tbPointId, int studentId1 , int studentId2, double processPoint, double testPoint);
    public void deletePoint(int tbPointId, int studentId);
    public ArrayList<String> getStudentId(int tbPointId);
}
