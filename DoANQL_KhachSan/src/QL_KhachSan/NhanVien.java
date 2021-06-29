/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QL_KhachSan;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PHẠM T.Vi
 */
public class NhanVien extends javax.swing.JFrame {

    DefaultTableModel dfQLKH;
    KetNoiCSDL ks = new KetNoiCSDL();

    /**
     * Creates new form Phong
     */
    public NhanVien() {
        initComponents();
        this.setTitle("Nhân Viên");
        this.setLocationRelativeTo(null);
        dfQLKH = (DefaultTableModel) this.jTable1.getModel();
        HienThiCSDL();
    }
    public boolean KiemtraMaSoTonTai(String ma) {
        try {
            String ql = "select * from NhanVien where MaNV ='" + ma + "'";
            ResultSet rs = ks.statement.executeQuery(ql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
     private  void HienThiCSDL(){
         String sql = ("select *from NhanVien");
        try {
            ks.statement = ks.connection.createStatement();
            ks.resultset = ks.statement.executeQuery(sql);
            dfQLKH.setRowCount(0);
            while (ks.resultset.next()) {
                String ma = ks.resultset.getString("MaNV");
                String ten = ks.resultset.getString("HoTenNV");
                Date ngaySinh = ks.resultset.getDate("NgaySinh");
                String diaC = ks.resultset.getString("DiaChi");
                String sdt = ks.resultset.getString("SDT");
               Vector<Object>  vec = new Vector<Object>();
               vec.add(ma);
               vec.add(ten);
               vec.add(ngaySinh);
               vec.add(diaC);
               vec.add(sdt);
               dfQLKH.addRow(vec);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QLKhachSan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pTieuDe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnThongtin = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTEn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pnThem = new javax.swing.JButton();
        pnXoa = new javax.swing.JButton();
        pnSua = new javax.swing.JButton();
        pnThoat = new javax.swing.JButton();
        pnLuu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thông Tin  Nhân Viên");
        pTieuDe.add(jLabel1);

        getContentPane().add(pTieuDe, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Họ và tên NV", "Ngày Sinh", "Địa chỉ", "SĐT"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mã NV");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Họ Và Tên NV:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Ngày Sinh:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Địa chỉ: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("SĐT: ");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTEn, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(299, 299, 299))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTEn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnThongtinLayout = new javax.swing.GroupLayout(pnThongtin);
        pnThongtin.setLayout(pnThongtinLayout);
        pnThongtinLayout.setHorizontalGroup(
            pnThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnThongtinLayout.setVerticalGroup(
            pnThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongtinLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnThongtin, java.awt.BorderLayout.CENTER);

        pnThem.setIcon(new javax.swing.ImageIcon("F:\\HeThongThongTin\\DoANQL_KhachSan\\src\\Img\\THEM.png")); // NOI18N
        pnThem.setText("Thêm");
        pnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnThemActionPerformed(evt);
            }
        });

        pnXoa.setIcon(new javax.swing.ImageIcon("F:\\HeThongThongTin\\DoANQL_KhachSan\\src\\Img\\XOA.png")); // NOI18N
        pnXoa.setText("Xóa ");
        pnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnXoaActionPerformed(evt);
            }
        });

        pnSua.setIcon(new javax.swing.ImageIcon("F:\\HeThongThongTin\\DoANQL_KhachSan\\src\\Img\\SUA.png")); // NOI18N
        pnSua.setText("Sửa");
        pnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnSuaActionPerformed(evt);
            }
        });

        pnThoat.setIcon(new javax.swing.ImageIcon("F:\\HeThongThongTin\\DoANQL_KhachSan\\src\\Img\\THOAT.png")); // NOI18N
        pnThoat.setText("Thoát");
        pnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnThoatActionPerformed(evt);
            }
        });

        pnLuu.setIcon(new javax.swing.ImageIcon("F:\\HeThongThongTin\\DoANQL_KhachSan\\src\\Img\\Ok-icon 2.png")); // NOI18N
        pnLuu.setText("Lưu");
        pnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(pnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(pnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(pnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(pnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnThemActionPerformed
        // TODO add your handling code here:
        txtMaNV.setText("");
        txtHoTEn.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtMaNV.requestFocus();
    }//GEN-LAST:event_pnThemActionPerformed

    private void pnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnXoaActionPerformed
        // TODO add your handling code here:
        try {
            String sqlDelete = "Delete from NhanVien where MaNV = '" + txtMaNV.getText() + "'";
            int x = ks.statement.executeUpdate(sqlDelete);
            if (x >= 1) {
                HienThiCSDL();
                txtMaNV.setText("");
                txtHoTEn.setText("");
                txtNgaySinh.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                txtMaNV.requestFocus();
            }
            try {

            } catch (Exception ex) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnXoaActionPerformed

    private void pnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnSuaActionPerformed
        // TODO add your handling code here:
        try {
            String sqlUpdate = "Update NhanVien set HoTenNV = N'" + txtHoTEn.getText() + "'" + ",NgaySinh = '" + txtNgaySinh.getText() + "'" + ",DiaChi = N'" + txtDiaChi.getText() + "'" + ",SDT = '" + txtSDT.getText() + "' where MaNV = '" +txtMaNV.getText() + "'";
            int x = ks.statement.executeUpdate(sqlUpdate);
            if (x >= 1) {
                HienThiCSDL();
               
            }
            try {

            } catch (Exception ex) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }//GEN-LAST:event_pnSuaActionPerformed

    private void pnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnThoatActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát không?",
                "thoát", JOptionPane.YES_NO_CANCEL_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            new HienThiMenu().setVisible(true);
            this.dispose();
        } else if (n == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Không thì thôi");
        }
    }//GEN-LAST:event_pnThoatActionPerformed

    private void pnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnLuuActionPerformed
        // TODO add your handling code here:
        
         if (txtMaNV.getText().isEmpty() || txtHoTEn.getText().isEmpty() || txtNgaySinh.getText().isEmpty() || txtDiaChi.getText().isEmpty() || txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin!!");
        } else {
           
            if (KiemtraMaSoTonTai(txtMaNV.getText())) {
                JOptionPane.showMessageDialog(null, "Đã trung Mã phòng");
            } else {
                try {
                    String sqlInsert = "Insert into NhanVien "
                            + "Values('" + txtMaNV.getText() + "', N'" + txtHoTEn.getText() + "', '" + txtNgaySinh.getText() + "' , N'" + txtDiaChi.getText() + "'  , '" + txtSDT.getText() + "')";
                    int x = ks.statement.executeUpdate(sqlInsert);
                    if (x >= 1) {
                        HienThiCSDL();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtMaNV.setText("");
                txtHoTEn.setText("");
                txtNgaySinh.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                txtMaNV.requestFocus();
            }
        }
    }//GEN-LAST:event_pnLuuActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int row = this.jTable1.getSelectedRow();
        this.txtMaNV.setText((String)jTable1.getValueAt(row, 0));
        this.txtHoTEn.setText((String)jTable1.getValueAt(row, 1));
        this.txtNgaySinh.setText(jTable1.getValueAt(row, 2)+ "");
        this.txtDiaChi.setText((String)jTable1.getValueAt(row, 3));
        this.txtSDT.setText((String)jTable1.getValueAt(row, 4));
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pTieuDe;
    private javax.swing.JButton pnLuu;
    private javax.swing.JButton pnSua;
    private javax.swing.JButton pnThem;
    private javax.swing.JButton pnThoat;
    private javax.swing.JPanel pnThongtin;
    private javax.swing.JButton pnXoa;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTEn;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
