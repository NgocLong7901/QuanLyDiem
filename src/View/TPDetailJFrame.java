/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.PointDAOImpl;
import Interfaces.PointDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ql_diem.Models.Point;
import ql_diem.Models.Student;
import Interfaces.StudentDAO;
import DAO.StudentDAOImpl;
import DAO.SubjectDAOImpl;
import DAO.TablePointDAOImpl;
import Interfaces.SubjectDAO;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class TPDetailJFrame extends javax.swing.JFrame {

    private ArrayList<Point> list;
    DefaultTableModel model;
    public ArrayList<String> studentIdDetail;
    Point p;
    static PointDAO pointDAO;
    private TableRowSorter<TableModel> rowSorter = null;
    String choosertitle;
    String location;

    /**
     * Creates new form TPDetailJFrame
     */
    public TPDetailJFrame(int tbPointId, int subjectId, String term, String year) {
        initComponents();
        setTitle("Quản lý môn học");
        this.setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pointDAO = new PointDAOImpl();
        list = pointDAO.getPoints(tbPointId);
        studentIdDetail = pointDAO.getStudentId(tbPointId);
        p = new Point();
        p.setTablePointId(tbPointId);
        checkPoint(subjectId, term, year);
        TableSortFilter();

        setBtnColor(btnSave);
        setBtnColor(btnSave);
        setBtnColor(btnUpdate);
        setBtnColor(btnDelete);
        setBtnColor(btnExit);
        setBtnColor(btnFile);
        setBtnColor(btnExport);
        setTable();
        showData(tbPointId);
    }

    private void checkPoint(int subjectId, String term, String year) {
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        String subjectName = subjectDAO.getSubjectNameById(subjectId);

        jlbSubjectName.setText("Môn học: " + subjectName);
        jlbTerm.setText(term);
        jlbYear.setText("Năm học: " + year);
    }

    private void setTable() {

        model = (DefaultTableModel) jtbPoint.getModel();
        JTableHeader header = jtbPoint.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(100, 50));
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã sinh viên", "Tên sinh viên", "Lớp quản lý", "Điểm quá trình", "Điểm thi"
        });
    }

    int i = 1;

    private void showData(int id) {

        list = pointDAO.getPoints(id);
        StudentDAO s = new StudentDAOImpl();
        //DefaultTableModel model = (DefaultTableModel) jtbStudent.getModel();
        Object[] rows = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = i + 1;
            rows[1] = list.get(i).getStudentId();
            rows[2] = s.getStudentNameById(list.get(i).getStudentId());
            rows[3] = s.getClassById(list.get(i).getStudentId());
            rows[4] = list.get(i).getProcessPoint();
            rows[5] = list.get(i).getTestPoint();

            model.addRow(rows);

        }
        jtbPoint.invalidate();
        jtbPoint.validate();
        jtbPoint.repaint();
    }

    public void TableSortFilter() {
        jtbPoint.setRowSorter(rowSorter);

        rowSorter = new TableRowSorter<>(jtbPoint.getModel());
        jtbPoint.setRowSorter(rowSorter);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private void setBtnColor(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }

        });

    }

    public void Reset() {
        jtfSearch.setText("");
        jtfStudentId.setText("");
        jtfProcessPoint.setText("");
        jtfTestPoint.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlbStudentId = new javax.swing.JLabel();
        jlfProcessPoint = new javax.swing.JLabel();
        jlbTestPoint = new javax.swing.JLabel();
        jtfStudentId = new javax.swing.JTextField();
        jtfProcessPoint = new javax.swing.JTextField();
        jtfTestPoint = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jlbSubjectName = new javax.swing.JLabel();
        jlbTerm = new javax.swing.JLabel();
        jlbYear = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jtfSearch = new javax.swing.JTextField();
        jlfSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPoint = new javax.swing.JTable();
        btnFile = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));

        jlbStudentId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbStudentId.setText("Mã sinh viên:");

        jlfProcessPoint.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlfProcessPoint.setText("Điểm quá trình:");

        jlbTestPoint.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbTestPoint.setText("Điểm thi:");

        jtfStudentId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jtfProcessPoint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jtfTestPoint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jlbSubjectName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jlbTerm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbTerm.setText("jLabel9");

        jlbYear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbYear.setText("jLabel10");

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlfProcessPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbTestPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfStudentId)
                            .addComponent(jtfProcessPoint)
                            .addComponent(jtfTestPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                    .addComponent(jlbSubjectName)
                    .addComponent(jlbTerm)
                    .addComponent(jlbYear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jlbSubjectName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbTerm)
                .addGap(18, 18, 18)
                .addComponent(jlbYear)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlfProcessPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfProcessPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTestPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTestPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addGap(35, 35, 35)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jtfSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlfSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlfSearch.setText("Tìm kiếm:");

        jtbPoint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtbPoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sinh viên", "Tên sinh viên", "Lớp quản lý", "Điểm quá trình", "Điểm thi"
            }
        ));
        jtbPoint.setRowHeight(40);
        jtbPoint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbPointMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbPoint);

        btnFile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnFile.setText("Nhập file");
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExport.setText("Xuất file");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlfSearch)
                .addGap(18, 18, 18)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                .addComponent(btnFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFile, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlfSearch)
                        .addComponent(btnExport)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRƯỜNG ĐẠI HỌC XÂY DỰNG HÀ NỘI");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("HA NOI NATIONAL UNIVERSITY OF CIVIL ENGNEERING");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/baseline_school_black_24dp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        StudentDAO studentDAO = new StudentDAOImpl();
        ArrayList<Student> list = new ArrayList<>();
        ArrayList<String> studentIdList = new ArrayList<>();
        list = studentDAO.getStudents();
        studentIdList = studentDAO.getAllStudentId();
        double process = Double.parseDouble(jtfProcessPoint.getText());
        double test = Double.parseDouble(jtfTestPoint.getText());
        process = (double) Math.round(process * 10) / 10;
        test = (double) Math.round(test * 10) / 10;
        String s = jtfStudentId.getText();
        int studentId = Integer.parseInt(jtfStudentId.getText());

        if (process < 0 || process > 10 || test < 0 || test > 10) {
            JOptionPane.showMessageDialog(this, "Điểm phải có giá trị từ 0-10");
        } else {
            if (studentIdList.contains(s)) {
                if (studentIdDetail.contains(s)) {
                    JOptionPane.showMessageDialog(rootPane, "Sinh viên này đã có điểm !");
                } else {
                    p.setStudentId(Integer.parseInt(jtfStudentId.getText()));
                    p.setProcessPoint(process);
                    p.setTestPoint(test);
                    pointDAO.addPoint(p);
                    JOptionPane.showMessageDialog(rootPane, "Add success");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Sinh viên không tồn tại !");
            }
        }
        model = (DefaultTableModel) jtbPoint.getModel();
        model.setRowCount(0);

        showData(p.getTablePointId());
        Reset();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jtbPointMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbPointMouseClicked
        // TODO add your handling code here:
        model = (DefaultTableModel) jtbPoint.getModel();
        int view = jtbPoint.getSelectedRow();
        int index = jtbPoint.convertRowIndexToModel(view);

        jtfStudentId.setText(model.getValueAt(index, 1).toString());
        jtfProcessPoint.setText(model.getValueAt(index, 4).toString());
        jtfTestPoint.setText(model.getValueAt(index, 5).toString());
    }//GEN-LAST:event_jtbPointMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        StudentDAO studentDAO = new StudentDAOImpl();
        ArrayList<String> studentIdList = new ArrayList<>();
        studentIdList = studentDAO.getAllStudentId();
        String s = jtfStudentId.getText();

        double process = Double.parseDouble(jtfProcessPoint.getText());
        double test = Double.parseDouble(jtfTestPoint.getText());
        if (process < 0 || process > 10 || test < 0 || test > 10) {
            JOptionPane.showMessageDialog(this, "Điểm phải có giá trị từ 0-10");
        } else {
            if (studentIdList.contains(s)) {
                if (studentIdDetail.contains(s)) {
                    JOptionPane.showMessageDialog(rootPane, "Sinh viên này đã có điểm !");
                } else {
                    int row = jtbPoint.getSelectedRow();
                    int studentId1 = Integer.parseInt(model.getValueAt(row, 1).toString());
                    int studentId2 = Integer.parseInt(jtfStudentId.getText());
                    //p.setStudentId(Integer.parseInt(jtfStudentId.getText()));
                    pointDAO.updatePoint(p.getTablePointId(), studentId1, studentId2, process, test);
                    JOptionPane.showMessageDialog(rootPane, "Update success");

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Sinh viên không tồn tại !");
            }
        }

//        int row = jtbPoint.getSelectedRow();
//        String value = (jtbPoint.getModel().getValueAt(row, 1).toString());
//
//        if (pointDAO.updatePoint(p)) {
//            JOptionPane.showMessageDialog(rootPane, "Update success");
//
//        }
        model = (DefaultTableModel) jtbPoint.getModel();
        model.setRowCount(0);

        showData(p.getTablePointId());
        Reset();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        pointDAO = new PointDAOImpl();
        //p = new Point();
        int row = -1;
        row = jtbPoint.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 bản ghi! ");
        } else {

            int studentId = Integer.parseInt(jtfStudentId.getText());
            int tbPointId = p.getTablePointId();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Confirm", dialogButton);
            if (dialogResult == 0) {
                pointDAO.deletePoint(tbPointId, studentId);

                model = (DefaultTableModel) jtbPoint.getModel();
                model.setRowCount(0);

                showData(p.getTablePointId());
                Reset();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        // TODO add your handling code here:
        ArrayList<Point> list = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAOImpl();
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            FileInputStream file = null;
            try {
                File selectedFile = jfc.getSelectedFile();
                file = new FileInputStream(selectedFile);
                XSSFWorkbook wb = new XSSFWorkbook(file);
                XSSFSheet sheet = wb.getSheetAt(0);
                FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    int studentId = (int) row.getCell(0).getNumericCellValue();
                    double processPoint = (double) row.getCell(1).getNumericCellValue();
                    double testPoint = (double) row.getCell(2).getNumericCellValue();

                    p.setStudentId(studentId);
                    p.setProcessPoint(processPoint);
                    p.setTestPoint(testPoint);
                    System.out.println(p);
                    pointDAO.addPoint(p);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            model = (DefaultTableModel) jtbPoint.getModel();
            model.setRowCount(0);

            showData(p.getTablePointId());
            Reset();

        }


    }//GEN-LAST:event_btnFileActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser;
        String fileName = JOptionPane.showInputDialog("Nhập tên file: ");
        if (fileName.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên file:");
        } else {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): "
                        + chooser.getCurrentDirectory());
                location = chooser.getSelectedFile().toString();
                System.out.println("getSelectedFile() : "
                        + chooser.getSelectedFile());
                SubjectDAO subjectDAO = new SubjectDAOImpl();

                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("Chi tiết bảng điểm");

                XSSFRow row = null;
                Cell cell = null;

                row = sheet.createRow(3);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã sinh viên");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Điểm quá trình");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Điểm thi kết thúc");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Điểm tổng kết");

                ArrayList<Point> list = pointDAO.getPoints(p.getTablePointId());
                if (list != null) {
                    FileOutputStream fOS = null;
                    try {
                        int s = list.size();
                        for (int i = 0; i < s; i++) {
                            Point p = list.get(i);
                            row = sheet.createRow(4 + i);

                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);

                            cell = row.createCell(1, CellType.NUMERIC);
                            cell.setCellValue(p.getStudentId());

                            cell = row.createCell(2, CellType.NUMERIC);
                            cell.setCellValue(p.getProcessPoint());

                            cell = row.createCell(3, CellType.NUMERIC);
                            cell.setCellValue(p.getTestPoint());

                            cell = row.createCell(4, CellType.NUMERIC);
                            cell.setCellValue(p.getFinalPoint(p.getProcessPoint(), p.getTestPoint()));

                        }

                        File file = new File(location + "\\" + fileName + ".xlsx");

                        fOS = new FileOutputStream(file);
                        wb.write(fOS);
                        fOS.close();
                        JOptionPane.showMessageDialog(rootPane, "Xuất thành công!");

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fOS.close();
                        } catch (IOException ex) {
                            Logger.getLogger(TPDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } else {
                System.out.println("No Selection ");
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TPDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TPDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TPDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TPDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pointDAO = new PointDAOImpl();
                System.err.println(pointDAO.getPoints(2));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbStudentId;
    private javax.swing.JLabel jlbSubjectName;
    private javax.swing.JLabel jlbTerm;
    private javax.swing.JLabel jlbTestPoint;
    private javax.swing.JLabel jlbYear;
    private javax.swing.JLabel jlfProcessPoint;
    private javax.swing.JLabel jlfSearch;
    private javax.swing.JTable jtbPoint;
    private javax.swing.JTextField jtfProcessPoint;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfStudentId;
    private javax.swing.JTextField jtfTestPoint;
    // End of variables declaration//GEN-END:variables
}
