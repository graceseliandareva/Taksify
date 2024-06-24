package main;


import java.sql.Connection;
import config.koneksi;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MenuUtama; // Pastikan MenuUtama berada di package yang benar


public class form_login extends javax.swing.JFrame {
    int xx, xy;

    public form_login() {
        initComponents();
    }

    void bersih() {
        t_username.setText("username");
        txtPassword.setText("");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_username = new javax.swing.JTextField();
        bt_login = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        cbShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_cancel_20px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        t_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_username.setText("username");
        t_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        t_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_usernameFocusLost(evt);
            }
        });

        bt_login.setBackground(new java.awt.Color(102, 153, 255));
        bt_login.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bt_login.setForeground(new java.awt.Color(255, 255, 255));
        bt_login.setText("LOGIN");
        bt_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_loginActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Taskify");

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        cbShowPassword.setForeground(new java.awt.Color(255, 255, 255));
        cbShowPassword.setText("Show Password");
        cbShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(256, 256, 256)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(t_username, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(txtPassword)
                                .addComponent(cbShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 155, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(188, 188, 188)
                    .addComponent(bt_login, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(185, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(23, 23, 23)
                    .addComponent(jLabel2)
                    .addGap(29, 29, 29)
                    .addComponent(t_username, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbShowPassword)
                    .addGap(16, 16, 16)
                    .addComponent(bt_login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 340));

        pack();
        setLocationRelativeTo(null);
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        dispose();
    }

    private void bt_loginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = t_username.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || username.equals("username")) {
            JOptionPane.showMessageDialog(this, "Username tidak boleh kosong!");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password tidak boleh kosong!");
            return;
        }

        int result = isValidUsernameAndPassword(username, password);
        if (result == 0) {
            JOptionPane.showMessageDialog(this, "Login Berhasil!");
            new MenuUtama().setVisible(true); // Menampilkan halaman utama
            this.dispose(); // Menutup form login
        } else if (result == 1) {
            JOptionPane.showMessageDialog(this, "Password salah!");
        } else if (result == 2) {
            JOptionPane.showMessageDialog(this, "Username salah!");
        } else {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghubungi database");
        }
    }

    private void t_usernameFocusGained(java.awt.event.FocusEvent evt) {
        String username = t_username.getText();
        if (username.equals("username")) {
            t_username.setText("");
        }
    }

    private void t_usernameFocusLost(java.awt.event.FocusEvent evt) {
        String username = t_username.getText();
        if (username.equals("") || username.equals("username")) {
            t_username.setText("username");
        }
    }

    private void cbShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbShowPassword.isSelected()) {
        txtPassword.setEchoChar((char) 0);
        txtPassword.setHorizontalAlignment(JPasswordField.CENTER); // Set teks di tengah
        Font currentFont = txtPassword.getFont();
        Font newFont = new Font(currentFont.getName(), Font.PLAIN, currentFont.getSize());
        txtPassword.setFont(newFont); // Set font tidak bold
    } else {
        txtPassword.setEchoChar('\u2022'); // Set karakter echo ke bullet character
        txtPassword.setHorizontalAlignment(JPasswordField.CENTER); // Set teks di tengah
        Font currentFont = txtPassword.getFont();
        Font newFont = new Font(currentFont.getName(), Font.BOLD, currentFont.getSize());
        txtPassword.setFont(newFont); // Set font kembali bold
    }
    }

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {
      if (cbShowPassword.isSelected()) {
        txtPassword.setEchoChar((char) 0); // Jika Show Password dicentang, tampilkan teks biasa
    } else {
        txtPassword.setEchoChar('\u2022'); // Jika tidak, tampilkan karakter bulatan bold
    }
    txtPassword.setHorizontalAlignment(JPasswordField.CENTER); // Set kursor di tengah
    String password = new String(txtPassword.getPassword());
    if (password.isEmpty()) {
        txtPassword.setText(""); // Menghapus teks default jika ada
    }
    }

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {
        
    }

    private int isValidUsernameAndPassword(String username, String password) {
        try {
            Connection conn = koneksi.getConnection();
            String queryUser = "SELECT * FROM users WHERE username = ?";
            PreparedStatement psUser = conn.prepareStatement(queryUser);
            psUser.setString(1, username);
            ResultSet rsUser = psUser.executeQuery();

            if (!rsUser.next()) {
                return 2; // Username salah
            }

            String queryPass = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement psPass = conn.prepareStatement(queryPass);
            psPass.setString(1, username);
            psPass.setString(2, password);
            ResultSet rsPass = psPass.executeQuery();

            if (rsPass.next()) {
                return 0; // Username dan password valid
            } else {
                return 1; // Password salah
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // Terjadi kesalahan saat menghubungi database
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_login().setVisible(true);
            }
        });
    }

    private javax.swing.JButton bt_login;
    private javax.swing.JCheckBox cbShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField t_username;
    private javax.swing.JPasswordField txtPassword;
}



