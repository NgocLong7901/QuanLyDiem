/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.StudentDAOImpl;
import DAO.SubjectDAOImpl;
import Interfaces.SubjectDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

/**
 *
 * @author MSIISM
 */
public class SubjectJFrame extends javax.swing.JFrame {

    private ArrayList<Subject> list;
    DefaultTableModel model;
    SubjectDAO subjectDAO;
    private TableRowSorter<TableModel> rowSorter = null;

    /**
     * Creates new form SubjectJFrame
     */
    public SubjectJFrame() {
        initComponents();
        setTitle("Quản lý môn học");
        this.setLocationRelativeTo(null);
        subjectDAO = new SubjectDAOImpl();
        list = subjectDAO.getSubjects();
        setTable();
        showData();
        TableSortFilter();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBtnColor(btnSave);
        setBtnColor(btnAdd);
        setBtnColor(btnUpdate);
        setBtnColor(btnDelete);
        setBtnColor(btnExit);

    }

    private void setTable() {

        model = (DefaultTableModel) jtbSubject.getModel();
        JTableHeader header = jtbSubject.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(100, 50));
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã môn học", "Tên môn học", "Số tín chỉ", "Tiên quyết"
        });
    }

    //int i = 1;

    private void showData() {
        list = subjectDAO.getSubjects();
        //DefaultTableModel model = (DefaultTableModel) jtbStudent.getModel();
        Object[] rows = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = i + 1;
            rows[1] = list.get(i).getSubjectId();
            rows[2] = list.get(i).getSubjectName();
            rows[3] = list.get(i).getCredits();
            rows[4] = list.get(i).isRequired() ? "Tiên quyết" : "Không tiên quyết";

            model.addRow(rows);

        }

        jtbSubject.invalidate();//vô hiệu hóa
        jtbSubject.validate();//xác thực tính hợp lệ
        jtbSubject.repaint();//cập nhật lại bảng
    }

    public static boolean checkMamh(String s) {
        try {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean checkThongtin(String s) {
        try {
            Pattern pattern = Pattern.compile("^[\\w]+[\\D\\s]*$");
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

    public void TableSortFilter() { 

        rowSorter = new TableRowSorter<>(jtbSubject.getModel());
        jtbSubject.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    model = (DefaultTableModel) jtbSubject.getModel();

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
        jPanel4 = new javax.swing.JPanel();
        jlbSubject = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlbSubjectId = new javax.swing.JLabel();
        jtfSubjectId = new javax.swing.JTextField();
        jlbSubjectName = new javax.swing.JLabel();
        jtfSubjectName = new javax.swing.JTextField();
        jlbCredit = new javax.swing.JLabel();
        jtfCredit = new javax.swing.JTextField();
        jlbRequired = new javax.swing.JLabel();
        jcbYes = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSubject = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(196, 214, 229));

        jPanel2.setBackground(new java.awt.Color(113, 113, 245));

        jPanel4.setBackground(new java.awt.Color(75, 21, 241));

        jlbSubject.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jlbSubject.setForeground(new java.awt.Color(255, 255, 255));
        jlbSubject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSubject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/baseline_subject_black_24dp.png"))); // NOI18N
        jlbSubject.setText("QUẢN LÝ MÔN HỌC");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSubject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jlbSubject)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(214, 200, 200));

        jlbSubjectId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbSubjectId.setText("Mã môn học");

        jtfSubjectId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbSubjectName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbSubjectName.setText("Tên môn học");

        jtfSubjectName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbCredit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbCredit.setText("Số tín chỉ");

        jtfCredit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbRequired.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlbRequired.setText("Tiên quyết");

        jcbYes.setBackground(new java.awt.Color(214, 200, 200));
        jcbYes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jcbYes.setText("Tiên quyết");

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin môn học:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO_DHXD (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbSubjectName)
                            .addComponent(jlbSubjectId)
                            .addComponent(jlbCredit)
                            .addComponent(jlbRequired))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jcbYes)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jtfSubjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(jtfSubjectId)
                            .addComponent(jtfCredit))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(90, 90, 90))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSubjectId)
                    .addComponent(jtfSubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSubjectName)
                    .addComponent(jtfSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCredit)
                    .addComponent(jtfCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRequired)
                    .addComponent(jcbYes))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(btnExit)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jtbSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtbSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã môn học", "Tên môn học", "Số tín chỉ", "Tiên quyết"
            }
        ));
        jtbSubject.setRowHeight(40);
        jtbSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbSubjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbSubject);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tìm kiếm");

        jtfSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
        //subjectDAO = new SubjectDAOImpl();
        Subject s = new Subject();
        boolean isOK = true;
        try {
            if (jtfSubjectId.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Mã môn học không được để trống!");
                isOK = false;
            } else if (!checkMamh(jtfSubjectId.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Mã môn học chỉ gồm các chữ số, ví dụ: 224564");
                jtfSubjectId.setText("");
                isOK = false;
            } else {
                try {
                    for (Subject i : list) {
                        if (Integer.parseInt(jtfSubjectId.getText()) == i.getSubjectId()) {
                            JOptionPane.showMessageDialog(rootPane, "Mã môn học này đã tồn tại!");
                            jtfSubjectId.setText("");
                            isOK = false;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            s.setSubjectId(Integer.parseInt(jtfSubjectId.getText()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (jtfSubjectName.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Tên môn học không được để trống!");
            isOK = false;
        } else {
            //if(!checkThongtin(jtfSubjectName.getText())){
            //JOptionPane.showMessageDialog(rootPane, "Tên môn học chỉ gồm các chữ cái và số, có khoảng trắng và không được để khoảng trắng ở đầu, ví dụ: Tin Học Đại Cương");
            //jtfSubjectName.setText("");
            //isOK=false;
            //}
            //else {
            try {
                for (Subject i : list) {
                    if (jtfSubjectName.getText().equals(i.getSubjectName())) {
                        JOptionPane.showMessageDialog(rootPane, "Tên môn học này đã tồn tại!");
                        jtfSubjectName.setText("");
                        isOK = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //}
            s.setSubjectName(viethoa(jtfSubjectName.getText()));
        }
        try {
            if (jtfCredit.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Số tín chỉ của môn học không được để trống!");
                isOK = false;
            } else if (!checkMamh(jtfCredit.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Số tín chỉ chỉ gồm các chữ số!");
                jtfCredit.setText("");
                isOK = false;
            }
            s.setCredits(Integer.parseInt(jtfCredit.getText()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (jcbYes.isSelected()) {
            s.setRequired(true);
        } else {
            s.setRequired(false);
        }
        if (isOK) {
            if (new SubjectDAOImpl().addSubject(s)) {
                JOptionPane.showMessageDialog(rootPane, "Thêm mới thành công!");
                list.add(s);
                showData();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm mới thất bại!");
        }

        model = (DefaultTableModel) jtbSubject.getModel();
        model.setRowCount(0);

        showData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        jtfSearch.setText("");
        jtfSubjectId.setText("");
        jtfSubjectName.setText("");
        jtfCredit.setText("");
        jcbYes.setSelected(false);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        //subjectDAO = new SubjectDAOImpl();
        Subject s = new Subject();
        boolean isok = true;
        int index = jtbSubject.getSelectedRow();
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có môn học nào để chỉnh sửa thông tin!");
        } else {
            if (index == -1) {
                JOptionPane.showMessageDialog(rootPane, "Chưa chọn môn học nào để chỉnh sửa thông tin!");
            } else {
                try {
                    if (jtfSubjectId.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Mã môn học không được để trống!");
                        isok = false;
                    } else if (!checkMamh(jtfSubjectId.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Mã môn học chỉ gồm các chữ số!");
                        jtfSubjectId.setText("");
                        isok = false;
                    } else {
                        for (Subject i : list) {
                            if (i.getSubjectId() != (int) jtbSubject.getValueAt(index, 1)) {
                                if (Integer.parseInt(jtfSubjectId.getText()) == i.getSubjectId()) {
                                    JOptionPane.showMessageDialog(rootPane, "Mã môn học này đã tồn tại!");
                                    jtfSubjectId.setText("");
                                    isok = false;
                                }
                            }
                        }
                    }
                    s.setSubjectId(Integer.parseInt(jtfSubjectId.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    if (jtfSubjectName.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Tên môn học không được để trống!");
                        isok = false;
                    } else {
                        //if(!checkThongtin(jtfSubjectName.getText())){
                        //JOptionPane.showMessageDialog(rootPane, "Tên môn học chỉ gồm các chữ cái và số, có khoảng trắng và không được để khoảng trắng ở đầu, ví dụ: Tin Học Đại Cương");
                        //jtfSubjectName.setText("");
                        //isok=false;
                        //} 
                        for (Subject i : list) {
                            if (i.getSubjectName() != (String) jtbSubject.getValueAt(index, 2)) {
                                if (jtfSubjectName.getText().equals(i.getSubjectName())) {
                                    JOptionPane.showMessageDialog(rootPane, "Tên môn học này đã tồn tại!");
                                    jtfSubjectName.setText("");
                                    isok = false;
                                }
                            }
                        }
                    }
                    s.setSubjectName(viethoa(jtfSubjectName.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    if (jtfCredit.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Số tín chỉ của môn học không được để trống!");
                        isok = false;
                    } else if (!checkMamh(jtfCredit.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Số tín chỉ chỉ gồm các chữ số");
                        jtfCredit.setText("");
                        isok = false;
                    }
                    s.setCredits(Integer.parseInt(jtfCredit.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Boolean gt = false;
                if (jcbYes.isSelected()) {
                    gt = true;
                    s.setRequired(true);
                }
                if (isok) {
                    int response = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thay đổi thông tin môn học?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        subjectDAO = new SubjectDAOImpl();
                        if (subjectDAO.updateSubject(s, (int) jtbSubject.getValueAt(index, 1))) {
                            JOptionPane.showMessageDialog(rootPane, "Cập nhật thông tin môn học thành công!");
                            model.setRowCount(0);
                            showData();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Cập nhật thông tin môn học thất bại!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thông tin môn học thất bại!");
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jtbSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbSubjectMouseClicked
        // TODO add your handling code here:
        model = (DefaultTableModel) jtbSubject.getModel();
        int view = jtbSubject.getSelectedRow();
        int index = jtbSubject.convertRowIndexToModel(view);
        
        jtfSubjectId.setText(model.getValueAt(index, 1).toString());
        jtfSubjectName.setText(model.getValueAt(index, 2).toString());
        jtfCredit.setText((model.getValueAt(index, 3).toString()));
        String required = model.getValueAt(index, 4).toString();
        if (required.equals("Tiên quyết")) {
            jcbYes.setSelected(true);
        }
        if (required.equals("Không tiên quyết")) {
            jcbYes.setSelected(false);
        }

    }//GEN-LAST:event_jtbSubjectMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        subjectDAO = new SubjectDAOImpl();
        int index = jtbSubject.getSelectedRow();
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có môn học nào để xóa!");
        } else if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn môn học nào để xóa!");
        } else {
            int response = JOptionPane.showConfirmDialog(rootPane, "Xác nhận xóa thông tin môn học?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(rootPane, "Xóa thông tin môn học thành công!");
                subjectDAO.deleteSubject((int) jtbSubject.getValueAt(index, 1));
                model.setRowCount(0);
                showData();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbYes;
    private javax.swing.JLabel jlbCredit;
    private javax.swing.JLabel jlbRequired;
    private javax.swing.JLabel jlbSubject;
    private javax.swing.JLabel jlbSubjectId;
    private javax.swing.JLabel jlbSubjectName;
    private javax.swing.JTable jtbSubject;
    private javax.swing.JTextField jtfCredit;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfSubjectId;
    private javax.swing.JTextField jtfSubjectName;
    // End of variables declaration//GEN-END:variables
}
