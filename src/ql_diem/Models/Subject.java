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

public class Subject {
    private int subjectId;
    private String subjectName;
    private int credits; // Số tín chỉ
    private boolean required = false; // bắt buộc hay không true: không bắt buộc, flase: bắt buộc

    public Subject() {
    }

    public Subject(int subjectId, String subjectName, int credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    

    public boolean getRequired() {
        return this.required;
    }

    

}
