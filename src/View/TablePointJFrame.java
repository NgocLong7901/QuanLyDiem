/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.StudentDAOImpl;
import DAO.SubjectDAOImpl;
import DAO.TablePointDAOImpl;
import Interfaces.SubjectDAO;
import Interfaces.TablePointDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ql_diem.Models.Student;
import ql_diem.Models.Subject;
import ql_diem.Models.TablePoint;

/**
 *
 * @author MSIISM
 */
public class TablePointJFrame extends javax.swing.JFrame {

    private ArrayList<TablePoint> list;
    DefaultTableModel model;
    private TableRowSorter<TableModel> rowSorter = null;
    TablePointDAO tbPointDAO;

    /**
     * Creates new form TablePointJFrame
     */
    public TablePointJFrame() {
        initComponents();
        setTitle("Quản lý môn học");
        this.setLocationRelativeTo(null);
        tbPointDAO = new TablePointDAOImpl();
        list = tbPointDAO.getTablePoints();
        setTable();
        showData();
        TableSortFilter();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBtnColor(btnSave);
        setBtnColor(btnAdd);
        setBtnColor(btnUpdate);
        setBtnColor(btnDelete);
        setBtnColor(btnCheckPoint);
        setBtnColor(btnExit);
        setSubjectToComboBox();
    }

    private void setTable() {

        model = (DefaultTableModel) jtbTbPoint.getModel();
        JTableHeader header = jtbTbPoint.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(100, 50));
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã bảng điểm", "Mã môn học", "Tên môn học", "Lớp môn học", "Ngày thi", "Học kỳ", "Năm học"
        });
    }

    int i = 1;

    private void showData() {
        list = tbPointDAO.getTablePoints();
        SubjectDAO s = new SubjectDAOImpl();

        //DefaultTableModel model = (DefaultTableModel) jtbStudent.getModel();
        Object[] rows = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = i + 1;
            rows[1] = list.get(i).getTbPointId();
            rows[2] = list.get(i).getSubjectId();
            rows[3] = s.getSubjectNameById(list.get(i).getSubjectId());
            rows[4] = list.get(i).getClassName();
            rows[5] = list.get(i).getDateTest();
            rows[6] = list.get(i).getTerm();
            rows[7] = list.get(i).getYear();

            model.addRow(rows);

        }
    }

    public void TableSortFilter() {
        jtbTbPoint.setRowSorter(rowSorter);

        rowSorter = new TableRowSorter<>(jtbTbPoint.getModel());
        jtbTbPoint.setRowSorter(rowSorter);
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

    public static boolean checkThongtin(String s) {
        try {
            Pattern pattern = Pattern.compile("^[\\D]+[\\D\\s]*$");
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String viethoa(String s) {
        char[] c = s.toCharArray();
        boolean foundspace = true;
        try {
            for (int i = 0; i < c.length; i++) {
                if (Character.isLetter(c[i])) {
                    if (foundspace) {
                        c[i] = Character.toUpperCase(c[i]);
                        foundspace = false;
                    }
                } else {
                    foundspace = true;
                }
            }
            return String.valueOf(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    void setSubjectToComboBox() {
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        ArrayList<String> subjectList = subjectDAO.getAllSubjectName();

        for (String subject : subjectList) {
            jcbSubjectId.addItem(subject);
        }
    }

    public void Reset() {
        jtfTbPointId.setText("");
        jcbSubjectId.setSelectedIndex(0);
        jtfClassName.setText("");
        jcbTerm.setSelectedIndex(0);
        jtfYear.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jlbTbPointId = new javax.swing.JLabel();
        jtfTbPointId = new javax.swing.JTextField();
        jlbSubjectId = new javax.swing.JLabel();
        jlbClassName = new javax.swing.JLabel();
        jtfClassName = new javax.swing.JTextField();
        jlbDateTest = new javax.swing.JLabel();
        jlbTerm = new javax.swing.JLabel();
        jtfYear = new javax.swing.JTextField();
        jlbYear = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCheckPoint = new javax.swing.JButton();
        jcbTerm = new javax.swing.JComboBox<>();
        btnExit = new javax.swing.JButton();
        jcbSubjectId = new javax.swing.JComboBox<>();
        jdcDateTest = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTbPoint = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(87, 87, 224));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/baseline_description_black_24dp.png"))); // NOI18N
        jLabel1.setText("Quản lý bảng điểm");

        jlbTbPointId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbTbPointId.setText("Mã bảng điểm:");

        jtfTbPointId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfTbPointId.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jlbSubjectId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbSubjectId.setText("Mã môn học:");

        jlbClassName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbClassName.setText("Lớp môn học:");

        jtfClassName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfClassName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jlbDateTest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbDateTest.setText("Ngày thi:");

        jlbTerm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbTerm.setText("Học kỳ:");

        jtfYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jlbYear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbYear.setText("Năm học:");

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

        btnCheckPoint.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckPoint.setText("Xem điểm");
        btnCheckPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckPointActionPerformed(evt);
            }
        });

        jcbTerm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbTerm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Học kỳ 1", "Học kỳ 2", "Học kỳ 3" }));

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jcbSubjectId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jdcDateTest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Thông tin bảng điểm :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDateTest, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbSubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbTbPointId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfTbPointId)
                                    .addComponent(jtfYear)
                                    .addComponent(jtfClassName)
                                    .addComponent(jcbTerm, 0, 200, Short.MAX_VALUE)
                                    .addComponent(jcbSubjectId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcDateTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCheckPoint)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTbPointId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTbPointId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbSubjectId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbSubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbDateTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcDateTest, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbTerm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckPoint)
                    .addComponent(btnExit))
                .addContainerGap(293, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        jtfSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jtbTbPoint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtbTbPoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bảng điểm", "Mã môn học", "Tên môn học", "Lớp môn học", "Ngày thi", "Học kỳ", "Năm học"
            }
        ));
        jtbTbPoint.setRowHeight(40);
        jtbTbPoint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbTbPointMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbTbPoint);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(747, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        Subject s = new Subject();
        tbPointDAO = new TablePointDAOImpl();
        TablePoint tbPoint = new TablePoint();
        if (jtfTbPointId.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Mã bảng điểm không được để trống");
        } else {

        }
        int id = Integer.parseInt(jtfTbPointId.getText());
        int subjectId = subjectDAO.getSubjectIdByName(jcbSubjectId.getSelectedItem().toString());
        tbPoint.setTbPointId(id);
        tbPoint.setSubjectId(subjectId);
        tbPoint.setClassName(jtfClassName.getText());
        tbPoint.setDateTest(jdcDateTest.getDate());
        tbPoint.setTerm(jcbTerm.getSelectedItem().toString());
        tbPoint.setYear(jtfYear.getText());
        if (subjectDAO.getSubjectId(subjectId) == -1) {
            JOptionPane.showMessageDialog(rootPane, "Môn học không tồn tại");

        } else {
            if (tbPointDAO.addTablePoint(tbPoint)) {
                JOptionPane.showMessageDialog(rootPane, "Add success");
                list.add(tbPoint);
            }
        }
        model = (DefaultTableModel) jtbTbPoint.getModel();
        model.setRowCount(0);
        showData();
        Reset();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        tbPointDAO = new TablePointDAOImpl();
        TablePoint t = new TablePoint();
        int row = jtbTbPoint.getSelectedRow();
        String value = (jtbTbPoint.getModel().getValueAt(row, 1).toString());
        t.setTbPointId(Integer.parseInt(jtfTbPointId.getText()));
        t.setSubjectId(subjectDAO.getSubjectIdByName(jcbSubjectId.getSelectedItem().toString()));
        t.setClassName(jtfClassName.getText());
        t.setDateTest(jdcDateTest.getDate());
        System.out.println(jdcDateTest.getDate());
        t.setTerm(jcbTerm.getSelectedItem().toString());
        t.setYear(jtfYear.getText());

        if (tbPointDAO.updateTablePoint(t)) {
            JOptionPane.showMessageDialog(rootPane, "Update success");

        }
        model = (DefaultTableModel) jtbTbPoint.getModel();
        model.setRowCount(0);

        showData();
        Reset();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jtbTbPointMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbTbPointMouseClicked
        // TODO add your handling code here:
        model = (DefaultTableModel) jtbTbPoint.getModel();
        int view = jtbTbPoint.getSelectedRow();
        int index = jtbTbPoint.convertRowIndexToModel(view);

        jtfTbPointId.setText(model.getValueAt(index, 1).toString());
        jcbSubjectId.setSelectedItem(model.getValueAt(index, 3).toString());
        jtfClassName.setText(model.getValueAt(index, 4).toString());
        jdcDateTest.setDate((Date) model.getValueAt(index, 5));
        jcbTerm.setSelectedItem(model.getValueAt(index, 6));
        jtfYear.setText(model.getValueAt(index, 7).toString());
    }//GEN-LAST:event_jtbTbPointMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        tbPointDAO = new TablePointDAOImpl();
        TablePoint t = new TablePoint();
        int row = -1;
        row = jtbTbPoint.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 bản ghi! ");
        } else {
            int id = Integer.parseInt(jtfTbPointId.getText());

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Confirm", dialogButton);
            if (dialogResult == 0) {
                tbPointDAO.deleteTablePoint(id);
                model = (DefaultTableModel) jtbTbPoint.getModel();
                model.setRowCount(0);

                showData();
            } else {

            }
        }
        Reset();


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCheckPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckPointActionPerformed
        // TODO add your handling code here:
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        int row = -1;
        row = jtbTbPoint.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 bản ghi! ");
        } else {
            int index = Integer.parseInt(jtfTbPointId.getText());
            SubjectDAO s = new SubjectDAOImpl();
            String term, year;
            int subjectId = subjectDAO.getSubjectIdByName(jcbSubjectId.getSelectedItem().toString());
            term = jcbTerm.getSelectedItem().toString();
            year = jtfYear.getText();

            TPDetailJFrame frame = new TPDetailJFrame(index, subjectId, term, year);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.setTitle("Chi tiết bảng điểm");

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }


    }//GEN-LAST:event_btnCheckPointActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(TablePointJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablePointJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablePointJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablePointJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablePointJFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheckPoint;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbSubjectId;
    private javax.swing.JComboBox<String> jcbTerm;
    private com.toedter.calendar.JDateChooser jdcDateTest;
    private javax.swing.JLabel jlbClassName;
    private javax.swing.JLabel jlbDateTest;
    private javax.swing.JLabel jlbSubjectId;
    private javax.swing.JLabel jlbTbPointId;
    private javax.swing.JLabel jlbTerm;
    private javax.swing.JLabel jlbYear;
    private javax.swing.JTable jtbTbPoint;
    private javax.swing.JTextField jtfClassName;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfTbPointId;
    private javax.swing.JTextField jtfYear;
    // End of variables declaration//GEN-END:variables
}
