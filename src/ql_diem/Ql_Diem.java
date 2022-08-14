/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ql_diem;

import DAO.PointDAOImpl;
import DAO.StudentDAOImpl;
import DAO.StudentPointDAOImpl;
import DAO.SubjectDAOImpl;
import DAO.TablePointDAOImpl;
import Interfaces.PointDAO;
import Interfaces.StudentDAO;
import Interfaces.StudentPointDAO;
import Interfaces.SubjectDAO;
import Interfaces.TablePointDAO;
import View.LoginJFrame;
import View.HomeJFrame;
import View.StudentJFrame;
import View.StudentJFrame;

import View.StudentPointJFrame;
import View.SubjectJFrame;
import View.TablePointJFrame;
import java.util.ArrayList;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class Ql_Diem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //StudentPointJFrame frame = new StudentPointJFrame(2);
        //SubjectJFrame frame = new SubjectJFrame();
        LoginJFrame frame = new LoginJFrame();
        //HomeJFrame frame = new HomeJFrame("admin");
        //        frame.setVisible(true);
        //TablePointJFrame frame = new TablePointJFrame();

        //UploadFileJFrame frame = new UploadFileJFrame();
        frame.setVisible(true);
//       
//        StudentDAO student = new StudentDAOImpl();
//        
//        System.out.println(student.getStudents());
//        StudentPointDAO s = new StudentPointDAOImpl();
//        int t = s.getSubjectIdByTPId(2);
//        System.out.print(t);
        ArrayList<TablePoint> list = new ArrayList<>();
        TablePointDAO tbPointDAO = new TablePointDAOImpl();
        list = tbPointDAO.getTablePoints();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
