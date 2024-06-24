package main;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class NewTask extends javax.swing.JPanel {

    private MenuHome parentMenuHome;
    private JSpinner timeSpinner;

    public void setParentMenuHome(MenuHome parentMenuHome) {
        this.parentMenuHome = parentMenuHome;
    }

    public NewTask() {
        initComponents();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        Tanggaltenggat.setDate(calendar.getTime());

        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
    }

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {
        String judul = Judultugas.getText();
        String deskripsi = Deskripsitugas.getText();
        java.util.Date tanggalTenggat = Tanggaltenggat.getDate();
        String prioritas = (String) cmb_prioritas.getSelectedItem();
        java.util.Date waktuAlarm = (java.util.Date) timeSpinner.getValue();

        // Save task to database
        saveTaskToDatabase(judul, deskripsi, tanggalTenggat, prioritas, waktuAlarm);

        // Add task to MenuHome
        if (parentMenuHome != null) {
            parentMenuHome.addNewTask(judul); // Add task name as a new checkbox
        }

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

        // Clear input fields after saving
        Judultugas.setText("");
        Deskripsitugas.setText("");
        Tanggaltenggat.setDate(null); 
        cmb_prioritas.setSelectedIndex(0);
    }

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear fields when cancel button is clicked
        Judultugas.setText("");
        Deskripsitugas.setText("");
        Tanggaltenggat.setDate(null);
        cmb_prioritas.setSelectedIndex(0);
    }

    private void saveTaskToDatabase(String judul, String deskripsi, java.util.Date tanggalTenggat, String prioritas, java.util.Date waktuAlarm) {
        try (Connection connection = koneksi.getConnection()) {
            String sql = "INSERT INTO tasks (judul, deskripsi, tanggal_tenggat, prioritas, waktu_alarm) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, judul);
            preparedStatement.setString(2, deskripsi);
            preparedStatement.setDate(3, new java.sql.Date(tanggalTenggat.getTime()));
            preparedStatement.setString(4, prioritas);
            preparedStatement.setTime(5, new java.sql.Time(waktuAlarm.getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        TambahTugas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Deskripsitugas = new JTextArea(5, 20);
        Judultugas = new JTextArea(2, 20);
        JScrollPane scrollPaneDeskripsi = new JScrollPane(Deskripsitugas);
        JScrollPane scrollPaneJudul = new JScrollPane(Judultugas);
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Tanggaltenggat = new com.toedter.calendar.JDateChooser();
        cmb_prioritas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());

        TambahTugas.setBackground(new java.awt.Color(255, 255, 255));
        TambahTugas.setForeground(new java.awt.Color(0, 102, 204));
        TambahTugas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setText("Tambah Tugas");

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Judul Tugas");

        Deskripsitugas.setLineWrap(true);
        Deskripsitugas.setWrapStyleWord(true);

        Judultugas.setLineWrap(true);
        Judultugas.setWrapStyleWord(true);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Deskripsi Tugas");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Prioritas");

        cmb_prioritas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Low", "Medium", "High" }));
        cmb_prioritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_prioritasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tanggal Tenggat");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Waktu Alarm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(scrollPaneDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(Tanggaltenggat, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_prioritas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tanggaltenggat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_prioritas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btn_simpan.setBackground(new java.awt.Color(0, 102, 204));
        btn_simpan.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Simpan");

        btn_batal.setBackground(new java.awt.Color(255, 0, 0));
        btn_batal.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btn_batal.setForeground(new java.awt.Color(255, 255, 255));
        btn_batal.setText("Batal");

        javax.swing.GroupLayout TambahTugasLayout = new javax.swing.GroupLayout(TambahTugas);
        TambahTugas.setLayout(TambahTugasLayout);
        TambahTugasLayout.setHorizontalGroup(
            TambahTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahTugasLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(TambahTugasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TambahTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TambahTugasLayout.createSequentialGroup()
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        TambahTugasLayout.setVerticalGroup(
            TambahTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahTugasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(TambahTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        add(TambahTugas, "card2");
    }

    private void cmb_prioritasActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private JTextArea Deskripsitugas;
    private JTextArea Judultugas;
    private com.toedter.calendar.JDateChooser Tanggaltenggat;
    private javax.swing.JPanel TambahTugas;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cmb_prioritas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;

    void setParentMenuTask(MenuTask menuTaskPanel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}