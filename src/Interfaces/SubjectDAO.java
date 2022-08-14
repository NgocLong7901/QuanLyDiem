/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;
import ql_diem.Models.Student;
import ql_diem.Models.Subject;

/**
 *
 * @author MSIISM
 */
public interface SubjectDAO {
    public ArrayList<Subject> getSubjects();
    public boolean addSubject(Subject subject);
    public boolean updateSubject(Subject subject, int index);
    public void deleteSubject(int id);
    public String getSubjectNameById(int id);
    public int getSubjectId(int id);
    public int getCreditBySubjectId(int subjectId);
    public ArrayList<String> getAllSubjectName();
    public int getSubjectIdByName(String subjectName);
}
