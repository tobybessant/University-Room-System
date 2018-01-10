/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsys.gui;

import java.util.Arrays;
import javax.swing.JOptionPane;
import universityroomsystem.University;
import urs.cards.Roles;
import urs.cards.Roles.Role;

/**
 *
 * @author Toby
 */
public class AddUser extends javax.swing.JFrame {
    
    private University uniModel;
    
    /**
     * Creates new form AddUser
     */
    public AddUser() {
        
        initComponents();
        
        Role[] roles = Role.values();
        
        for(Role r : roles){
            jcmbAddUserRole.addItem(r.name());
        }
    }
    public void setUniversity(University objTarget){
        uniModel = objTarget;
    }
    public void addNewUser() {
        
        String name = null;
        String role = null;
        
        if(jtxtAddUserName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a name", "Null field", JOptionPane.ERROR_MESSAGE);
        }
        else if(jcmbAddUserRole.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Please select a role", "Null field", JOptionPane.ERROR_MESSAGE);
        } else {
            role = (String)jcmbAddUserRole.getSelectedItem();
            name = jtxtAddUserName.getText();
            
            uniModel.addUser(name, role);
            
            disposeForm();
        }
    }
    private void disposeForm(){
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblAddUserTitle = new javax.swing.JLabel();
        jlblAddUserName = new javax.swing.JLabel();
        jlblAddUserRole = new javax.swing.JLabel();
        jtxtAddUserName = new javax.swing.JTextField();
        jcmbAddUserRole = new javax.swing.JComboBox<>();
        jbtnAddUserConfirm = new javax.swing.JButton();
        jbtnAddUserCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlblAddUserTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlblAddUserTitle.setText("Add user");

        jlblAddUserName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAddUserName.setText("Name");

        jlblAddUserRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAddUserRole.setText("Role");

        jcmbAddUserRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jcmbAddUserRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        jbtnAddUserConfirm.setBackground(new java.awt.Color(0, 240, 0));
        jbtnAddUserConfirm.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnAddUserConfirm.setText("Confirm");
        jbtnAddUserConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddUserConfirmActionPerformed(evt);
            }
        });

        jbtnAddUserCancel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnAddUserCancel.setText("Cancel");
        jbtnAddUserCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddUserCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlblAddUserTitle)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlblAddUserName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxtAddUserName))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlblAddUserRole)
                            .addGap(18, 18, 18)
                            .addComponent(jcmbAddUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jbtnAddUserConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnAddUserCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblAddUserTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblAddUserName)
                    .addComponent(jtxtAddUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblAddUserRole)
                    .addComponent(jcmbAddUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddUserConfirm)
                    .addComponent(jbtnAddUserCancel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAddUserConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddUserConfirmActionPerformed
        addNewUser();
    }//GEN-LAST:event_jbtnAddUserConfirmActionPerformed

    private void jbtnAddUserCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddUserCancelActionPerformed
        disposeForm();
    }//GEN-LAST:event_jbtnAddUserCancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnAddUserCancel;
    private javax.swing.JButton jbtnAddUserConfirm;
    private javax.swing.JComboBox<String> jcmbAddUserRole;
    private javax.swing.JLabel jlblAddUserName;
    private javax.swing.JLabel jlblAddUserRole;
    private javax.swing.JLabel jlblAddUserTitle;
    private javax.swing.JTextField jtxtAddUserName;
    // End of variables declaration//GEN-END:variables
}
