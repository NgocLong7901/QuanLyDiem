package ql_diem.Models;

import java.util.Date;

public class TablePoint {
    private int tbPointId;
    private int subjectId;
    private String className;
    private Date dateTest;
    private String term;
    private String year;

    public TablePoint() {
    }

    public TablePoint(int tbPointId, int subjectId, String className, Date dateTest, String term, String year) {
        this.tbPointId = tbPointId;
        this.subjectId = subjectId;
        this.className = className;
        this.dateTest = dateTest;
        this.term = term;
        this.year = year;
    }

    public int getTbPointId() {
        return tbPointId;
    }

    public void setTbPointId(int tbPointId) {
        this.tbPointId = tbPointId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDateTest() {
        return dateTest;
    }

    public void setDateTest(Date dateTest) {
        this.dateTest = dateTest;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "TablePoint{" + "tbPointId=" + tbPointId + ", subjectId=" + subjectId + ", className=" + className + ", dateTest=" + dateTest + ", term=" + term + ", year=" + year + '}';
    }

    
    
}