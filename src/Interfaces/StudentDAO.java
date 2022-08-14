/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;
import javax.swing.JTextField;
import ql_diem.Models.Student;

/**
 *
 * @author MSIISM
 */
public interface StudentDAO{
    public ArrayList<Student> getStudents();
    public String getStudentNameById(int id);
    public String getClassById(int id);
    public boolean addStudent(Student student);
    public boolean updateStudent(Student student, int index);
    public void deleteStudent(int id);
    public Student getStudentById(int studentId);
    public ArrayList<String> getAllStudentId();
}
