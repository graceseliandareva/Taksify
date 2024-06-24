package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.koneksi;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.table.TableColumnModel;

public class MenuTask extends JPanel {
    private JTable jTable1;
    private JButton btnDelete;
    private JButton btnSelesai; // New button for marking task as "Selesai"

    public MenuTask() {
        initComponents();
        loadTaskData();
    }

    MenuTask(MenuHome menuHome) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    MenuTask(JPanel parentPanel, CardLayout cardLayout, NewTask newTaskPanel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addTaskToTable(String judul, String deskripsi, java.util.Date tanggalTenggat, String prioritas) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{judul, deskripsi, tanggalTenggat, prioritas, false});
    }

    private void initComponents() {
        JPanel TaskList = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        JLabel jLabel1 = new JLabel();
  
        btnDelete = new JButton();
        btnSelesai = new JButton(); // Initialize the new button

        setLayout(new java.awt.CardLayout());

        TaskList.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jTable1.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Judul Tugas", "Deskripsi", "Tanggal Tenggat", "Prioritas", "Waktu Alarm", "Selesai"}
        ) {
            Class<?>[] columnTypes = new Class<?>[]{
                String.class, String.class, String.class, String.class, String.class, Boolean.class
            };

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        // Set up the column model and adjust column widths
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); // Judul Tugas
        columnModel.getColumn(1).setPreferredWidth(200); // Deskripsi
        columnModel.getColumn(2).setPreferredWidth(150); // Tanggal Tenggat
        columnModel.getColumn(3).setPreferredWidth(100); // Prioritas
        columnModel.getColumn(4).setPreferredWidth(100); // Waktu Alarm
        columnModel.getColumn(5).setPreferredWidth(50);  // Selesai

        jTable1.setRowHeight(30); // Ganti 30 dengan tinggi yang diinginkan untuk setiap baris
        jTable1.getTableHeader().setPreferredSize(new Dimension(jTable1.getTableHeader().getWidth(), 40));
        jTable1.getTableHeader().setBackground(new Color(0, 102, 204));
      
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36));
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Task List");

        btnDelete.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        // Configure btnSelesai
        btnSelesai.setBackground(new java.awt.Color(51, 204, 0)); // Green color for "Selesai"
        btnSelesai.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnSelesai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesai.setText("Selesai");
        btnSelesai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        GroupLayout TaskListLayout = new GroupLayout(TaskList);
        TaskList.setLayout(TaskListLayout);
        TaskListLayout.setHorizontalGroup(
            TaskListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(TaskListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(TaskListLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSelesai, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(TaskListLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel1)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TaskListLayout.setVerticalGroup(
            TaskListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, TaskListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(TaskListLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete)
                        .addComponent(btnSelesai)) // Include btnSelesai in the layout
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addGap(21, 21, 21))
        );

        add(TaskList, "card2");
    }

    private void loadTaskData() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    try (Connection connection = koneksi.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE selesai = false");
         java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            String judulTugas = resultSet.getString("judul");
            String deskripsiTugas = resultSet.getString("deskripsi");
            String tanggalTenggat = resultSet.getString("tanggal_tenggat");
            String prioritas = resultSet.getString("prioritas");
            String waktuAlarm = resultSet.getString("waktu_alarm");
            boolean selesai = resultSet.getBoolean("selesai");

            model.addRow(new Object[]{judulTugas, deskripsiTugas, tanggalTenggat, prioritas, waktuAlarm, selesai});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void btnDeleteActionPerformed(ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String judulTugas = (String) model.getValueAt(selectedRow, 0); // Asumsikan 'judul' adalah unique identifier

            // Hapus data dari database
            try (Connection connection = koneksi.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE judul = ?")) {
                preparedStatement.setString(1, judulTugas);
                int deletedRows = preparedStatement.executeUpdate();

                if (deletedRows > 0) {
                    // Hapus baris dari JTable
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Tugas berhasil dihapus.");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus tugas. Tugas mungkin tidak ditemukan di database.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus tugas.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.");
        }
    }

 private void btnSelesaiActionPerformed(ActionEvent evt) {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String judulTugas = (String) model.getValueAt(selectedRow, 0); // Ambil judul tugas
        boolean selesai = (Boolean) model.getValueAt(selectedRow, 5); // Ambil status checkbox

        // Perbarui status 'selesai' di tabel tasks
        try (Connection connection = koneksi.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement("UPDATE tasks SET selesai = true WHERE judul = ?")) {
            updateStatement.setString(1, judulTugas);
            int updatedRows = updateStatement.executeUpdate();

            if (updatedRows > 0) {
                // Simpan data ke tabel riwayat_tugas
                try (PreparedStatement insertStatement = connection.prepareStatement(
                             "INSERT INTO riwayat_tugas (id_task, judul, tanggal_tenggat, prioritas, selesai) " +
                             "SELECT id_task, judul, tanggal_tenggat, prioritas, ? FROM tasks WHERE judul = ?")) {

                    insertStatement.setBoolean(1, selesai); // Menggunakan nilai selesai yang sudah diambil sebelumnya
                    insertStatement.setString(2, judulTugas);
                    int insertedRows = insertStatement.executeUpdate();

                    if (insertedRows > 0) {
                        // Hapus baris yang dipilih dari model tabel
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Status tugas berhasil diperbarui dan disimpan di riwayat.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal menyimpan tugas ke riwayat.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui status tugas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memperbarui status tugas.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan diperbarui statusnya.");
    }
}

    void refreshTaskList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

