/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_diem.Models;

/**
 *
 * @author MSIISM
 */
public class Point {
    private int tablePointId;
    private int studentId;
    private double processPoint;
    private double testPoint;
    

    public Point() {
    }

    public Point(int tablePointId, int studentId, double processPoint, double testPoint) {
        this.tablePointId = tablePointId;
        this.studentId = studentId;
        this.processPoint = processPoint;
        this.testPoint = testPoint;
    }    
    
    public int getTablePointId() {
        return tablePointId;
    }

    public void setTablePointId(int tablePointId) {
        this.tablePointId = tablePointId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getProcessPoint() {
        return processPoint;
    }

    public void setProcessPoint(double processPoint) {
        this.processPoint = processPoint;
    }

    public double getTestPoint() {
        return testPoint;
    }

    public void setTestPoint(double testPoint) {
        this.testPoint = testPoint;
    }

    public double getFinalPoint(double processPoint, double testpoint){
        return (0.3*processPoint + 0.7 * testpoint);
    }

    @Override
    public String toString() {
        return "Point{" + "tablePointId=" + tablePointId + ", studentId=" + studentId + ", processPoint=" + processPoint + ", testPoint=" + testPoint ;
    }

    

    
}
