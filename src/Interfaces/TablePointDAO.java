/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;
import ql_diem.Models.Subject;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public interface TablePointDAO {
    public ArrayList<TablePoint> getTablePoints();
    public boolean addTablePoint(TablePoint tablePoint);
    public boolean updateTablePoint(TablePoint tablePoint);
    public void deleteTablePoint(int id);
    public String getClassNamebyTpId(int id);
    public ArrayList<String> getTermYear(int studentId);
    public String getTermYearByTPId(int tbPointId);
    public int getCreditByTPId(int tbPointId);
}
