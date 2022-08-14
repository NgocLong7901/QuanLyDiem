/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.StudentDAOImpl;
import Interfaces.StudentDAO;
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

/**
 *
 * @author MSIISM
 */
public class StudentJFrame extends javax.swing.JFrame {

    private ArrayList<Student> list;
    DefaultTableModel model;
    StudentDAO studentDAO;
    private TableRowSorter<TableModel> rowSorter = null;

    /**
     * Creates new form StudentJFrame
     */
    public StudentJFrame() {
        initComponents();
        setTitle("Quản lý sinh viên");
        this.setLocationRelativeTo(null);
        studentDAO = new StudentDAOImpl();
        list = studentDAO.getStudents();
        setTable();
        showData();
        TableSortFilter();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBtnColor(btnSave);
        setBtnColor(btnAdd);
        setBtnColor(btnUpdate);
        setBtnColor(btnDelete);
        setBtnColor(btnCheck);
        setBtnColor(btnExit);

    }

    private void setTable() {

        model = (DefaultTableModel) jtbStudent.getModel();
        JTableHeader header = jtbStudent.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(100, 50));
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã sinh viên", "Họ Tên", "Giới tính", "Ngày Sinh", "Địa chỉ", "Chuyên ngành", "Lớp quản lý"
        });
    }

    int i = 1;

    private void showData() {
        list = studentDAO.getStudents();
        //DefaultTableModel model = (DefaultTableModel) jtbStudent.getModel();
        Object[] rows = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = i + 1;
            rows[1] = list.get(i).getStudentId();
            rows[2] = list.get(i).getStudentName();
            rows[3] = list.get(i).isGender() ? "Nam" : "Nữ";
            rows[4] = list.get(i).getBirthDate();
            rows[5] = list.get(i).getAddress();
            rows[6] = list.get(i).getMajor();
            rows[7] = list.get(i).getClassName();
            model.addRow(rows);

        }
        jtbStudent.invalidate();
        jtbStudent.validate();
        jtbStudent.repaint();
    }

    public static boolean checkMssv(String s) {
        try {
            Pattern pattern = Pattern.compile("^\\d*$");
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
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

    public void TableSortFilter() {
        jtbStudent.setRowSorter(rowSorter);

        rowSorter = new TableRowSorter<>(jtbStudent.getModel());
        jtbStudent.setRowSorter(rowSorter);
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
        jtfStudentId.setText("");
        jtfStudentName.setText("");
        jtfAdress.setText("");
        jtfMajor.setText("");
        jdcBirthDate.setCalendar(null);
        jtfSearch.setText("");
        jtfClass.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jlbStudentId = new javax.swing.JLabel();
        jtfStudentId = new javax.swing.JTextField();
        jlbStudentName = new javax.swing.JLabel();
        jtfStudentName = new javax.swing.JTextField();
        jlbGender = new javax.swing.JLabel();
        jrdNam = new javax.swing.JRadioButton();
        jrdNu = new javax.swing.JRadioButton();
        jlbAdress = new javax.swing.JLabel();
        jtfAdress = new javax.swing.JTextField();
        jlbBirthDate = new javax.swing.JLabel();
        jdcBirthDate = new com.toedter.calendar.JDateChooser();
        jlbMajor = new javax.swing.JLabel();
        jtfMajor = new javax.swing.JTextField();
        jlbClassName = new javax.swing.JLabel();
        jtfClass = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCheck = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jtfSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbStudent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(246, 239, 233));

        jlbStudentId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbStudentId.setText("Mã sinh viên:");

        jtfStudentId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbStudentName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbStudentName.setText("Họ và tên:");

        jtfStudentName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbGender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbGender.setText("Giới tính:");

        jrdNam.setBackground(new java.awt.Color(246, 239, 233));
        buttonGroup.add(jrdNam);
        jrdNam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrdNam.setSelected(true);
        jrdNam.setText("Nam");

        jrdNu.setBackground(new java.awt.Color(246, 239, 233));
        buttonGroup.add(jrdNu);
        jrdNu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrdNu.setText("Nữ");

        jlbAdress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbAdress.setText("Địa chỉ:");

        jtfAdress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbBirthDate.setText("Ngày sinh:");

        jdcBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbMajor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbMajor.setText("Chuyên ngành:");

        jtfMajor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlbClassName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbClassName.setText("Lớp quản lý:");

        jtfClass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCheck.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCheck.setText("Xem điểm");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO_DHXD (2).png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(44, 130, 201));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/baseline_supervised_user_circle_black_24dp.png"))); // NOI18N
        jLabel2.setText("Quản lý sinh viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Thông tin sinh viên :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbMajor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbStudentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfAdress)
                                    .addComponent(jtfMajor)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jrdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jrdNu))
                                    .addComponent(jtfStudentName)
                                    .addComponent(jtfClass)
                                    .addComponent(jdcBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(79, 79, 79))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jrdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbClassName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheck)
                    .addComponent(btnExit))
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(27, 27, 27))
        );

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));

        jtfSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 945, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(238, 238, 238));

        jScrollPane1.setBackground(new java.awt.Color(250, 244, 211));

        jtbStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtbStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Chuyên ngành", "Lớp"
            }
        ));
        jtbStudent.setRowHeight(40);
        jtbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbStudent);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        //studentDAO = new StudentDAOImpl();
        Student s = new Student();
        boolean isOK = true;
        ArrayList<String> listId = new ArrayList<>();
        if (jtfStudentId.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Mã sinh viên không được để trống!");
            isOK = false;

        } else if (!checkMssv(jtfStudentId.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Mã sinh viên chỉ gồm các chữ số!");
            jtfStudentId.setText("");
            isOK = false;
        } else {
            listId = studentDAO.getAllStudentId();
            if (listId.contains(jtfStudentId.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Mã sinh viên này đã tồn tại!");
                jtfStudentId.setText("");
                isOK = false;
            } else {
                s.setStudentId(Integer.parseInt(jtfStudentId.getText()));
                if (jtfStudentName.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Tên sinh viên không được để trống!");
                    isOK = false;
                } else {
                    if (!checkThongtin(jtfStudentName.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Tên không hợp lệ!");
                        jtfStudentName.setText("");
                        isOK = false;
                    } else {
                        s.setStudentName(jtfStudentName.getText());
                        if (jrdNam.isSelected()) {
                            s.setGender(true);
                            jrdNu.setSelected(false);
                        }
                        if (jrdNu.isSelected()) {
                            s.setGender(false);
                            jrdNam.setSelected(false);
                        }
                        if (jtfAdress.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập địa chỉ !");

                        } else {
                            s.setAddress(viethoa(jtfAdress.getText()));
                            if (jtfMajor.getText().equals("")) {
                                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chuyên ngành !");
                                isOK = false;
                            } else {
                                s.setMajor(viethoa(jtfMajor.getText()));
                                s.setBirthDate(jdcBirthDate.getDate());
                                if (jtfClass.getText().equals("")) {
                                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chuyên ngành !");
                                    isOK = false;
                                } else {
                                    s.setClassName(viethoa(jtfClass.getText()));
                                    
                                }
                            }
                        }

                    }
                }
            }

        }

        if (isOK) {
            if (new StudentDAOImpl().addStudent(s)) {
                JOptionPane.showMessageDialog(rootPane, "Thêm mới thành công!");
                list.add(s);
                showData();
                Reset();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm mới thất bại!");
        }

        model = (DefaultTableModel) jtbStudent.getModel();
        model.setRowCount(0);

        showData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:studentDAO = new StudentDAOImpl();
        //studentDAO = new StudentDAOImpl();
        Student s = new Student();
        boolean isok = true;
        int index = jtbStudent.getSelectedRow();
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có sinh viên nào để chỉnh sửa thông tin!");
        } else {
            if (index == -1) {
                JOptionPane.showMessageDialog(rootPane, "Chưa chọn sinh viên nào để chỉnh sửa thông tin!");
            } else {
                try {
                    if (jtfStudentId.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Mã sinh viên không được để trống!");
                        isok = false;
                    } else if (!checkMssv(jtfStudentId.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Mã sinh viên chỉ gồm các chữ số!");
                        jtfStudentId.setText("");
                        isok = false;
                    } else {
                        for (Student i : list) {
                            if (i.getStudentId() != (int) jtbStudent.getValueAt(index, 1)) {
                                if (Integer.parseInt(jtfStudentId.getText()) == i.getStudentId()) {
                                    JOptionPane.showMessageDialog(rootPane, "Mã sinh viên này đã tồn tại!");
                                    jtfStudentId.setText("");
                                    isok = false;
                                }
                            }
                        }
                    }
                    s.setStudentId(Integer.parseInt(jtfStudentId.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    if (jtfStudentName.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Tên sinh viên không được để trống!");
                        isok = false;
                    } else {
                        //if(!checkThongtin(jtfSubjectName.getText())){
                        //JOptionPane.showMessageDialog(rootPane, "Tên môn học chỉ gồm các chữ cái và số, có khoảng trắng và không được để khoảng trắng ở đầu, ví dụ: Tin Học Đại Cương");
                        //jtfSubjectName.setText("");
                        //isok=false;
                        //} 
                    }
                    s.setStudentName(viethoa(jtfStudentName.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Boolean gt = true;
                if (jrdNam.isSelected()) {
                    gt = true;
                }
                if (jrdNu.isSelected()) {
                    gt = false;
                }
                s.setGender(gt);
                if (!jtfAdress.getText().equals("")) {
                    s.setAddress(viethoa(jtfAdress.getText()));
                }
                if (!jtfMajor.getText().equals("")) {
                    s.setMajor(viethoa(jtfMajor.getText()));
                }
                s.setBirthDate(jdcBirthDate.getDate());
                if (!jtfClass.getText().equals("")) {
                    s.setClassName(viethoa(jtfClass.getText()));
                }
                if (isok) {
                    int response = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thay đổi thông tin môn học?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        if (new StudentDAOImpl().updateStudent(s, (int) jtbStudent.getValueAt(index, 1))) {
                            JOptionPane.showMessageDialog(rootPane, "Update success!");
                            model.setRowCount(0);
                            showData();

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Update Failed!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Update Failed!");
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jtbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbStudentMouseClicked
        // TODO add your handling code here:
        int view = jtbStudent.getSelectedRow();
        int index = jtbStudent.convertRowIndexToModel(view);
        
        jtfStudentId.setText(model.getValueAt(index, 1).toString());
        jtfStudentName.setText(model.getValueAt(index, 2).toString());
        String gender = model.getValueAt(index, 3).toString();
        if (gender.equals("Nam")) {
            jrdNam.setSelected(true);
            jrdNu.setSelected(false);
        }
        if (gender.equals("Nữ")) {
            jrdNu.setSelected(true);
            jrdNam.setSelected(false);
        }
        jdcBirthDate.setDate((Date) (model.getValueAt(index, 4)));
        jtfAdress.setText(model.getValueAt(index, 5).toString());
        jtfMajor.setText(model.getValueAt(index, 6).toString());
        jtfClass.setText((String) model.getValueAt(index, 7));
    }//GEN-LAST:event_jtbStudentMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        studentDAO = new StudentDAOImpl();
        int index = jtbStudent.getSelectedRow();
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có sinh viên nào để xóa!");
        } else if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn sinh viên nào để xóa!");
        } else {
            int response = JOptionPane.showConfirmDialog(rootPane, "Xác nhận xóa thông tin sinh viên?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(rootPane, "Xóa thông tin sinh viên thành công!");
                studentDAO.deleteStudent((int) jtbStudent.getValueAt(index, 1));
                model.setRowCount(0);
                showData();
                Reset();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        int index = Integer.parseInt(jtfStudentId.getText());
        StudentPointJFrame frame = new StudentPointJFrame(index);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setTitle("Chi tiết bảng điểm");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void jtfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchActionPerformed

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
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentJFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcBirthDate;
    private javax.swing.JLabel jlbAdress;
    private javax.swing.JLabel jlbBirthDate;
    private javax.swing.JLabel jlbClassName;
    private javax.swing.JLabel jlbGender;
    private javax.swing.JLabel jlbMajor;
    private javax.swing.JLabel jlbStudentId;
    private javax.swing.JLabel jlbStudentName;
    private javax.swing.JRadioButton jrdNam;
    private javax.swing.JRadioButton jrdNu;
    private javax.swing.JTable jtbStudent;
    private javax.swing.JTextField jtfAdress;
    private javax.swing.JTextField jtfClass;
    private javax.swing.JTextField jtfMajor;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfStudentId;
    private javax.swing.JTextField jtfStudentName;
    // End of variables declaration//GEN-END:variables
}
