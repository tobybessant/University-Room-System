/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsys.gui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import universityroomsystem.University;
import urs.cards.Card;
import urs.observerinterfaces.IObserver;

/**
 *
 * @author Toby
 */
public class RoomSystemMenu extends javax.swing.JFrame implements IObserver {

    /**
     * Creates new form RoomSystemMenu
     */
    
    private University uniModel;
    private DefaultListModel userListModel;
    
    public RoomSystemMenu() {
        uniModel = new University();
        uniModel.createTestData();
        
        userListModel = new DefaultListModel();
        refreshUserListModel();
        
        uniModel.registerObserver(this);
        
        initComponents();
    }

    private void refreshUserListModel(){
        userListModel.clear();
        for(Card c : uniModel.getUserList()){
            userListModel.addElement(c.getName());
        }
    }
    private void showSelectedUser(){
        Card selectedUser =
                uniModel.getUserList().get(jlstRoomSystemMenu.getSelectedIndex());
        jlblDispName.setText(selectedUser.getName());
        jlblDispID.setText(selectedUser.getCardID());
        jlblDispRole.setText(selectedUser.getRole().toString());
    }
    private void deleteSelectedUser(){
        int position = jlstRoomSystemMenu.getSelectedIndex();
        if(position == -1){
            JOptionPane.showMessageDialog(this, "Please select a user", "No selection", JOptionPane.ERROR_MESSAGE);
        } else {
            
            Card selectedUser = uniModel.getUserList().get(position);
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                          "Are you sure you want to delete " + 
                           selectedUser.getName(), "Confirm delete", 
                           JOptionPane.YES_NO_OPTION);
            
            if(confirm == JOptionPane.YES_OPTION) {
                
                logToFile("Delete operation.");
                
                uniModel.removeItemAt(position);
                
            }
        }
    }
    private void editSelectedUser(){
        if(validSelection()){
            
            EditUser objWindow = new EditUser();
            objWindow.setUniversity(uniModel);
            objWindow.setSelectedUser(uniModel.getUserList().get(jlstRoomSystemMenu.getSelectedIndex()));
            objWindow.setVisible(true);
            
        }
    }
    private Boolean validSelection(){
        Boolean result = false;
        
        int position = jlstRoomSystemMenu.getSelectedIndex();
        if(position == -1){
            JOptionPane.showMessageDialog(this, "Please select a user", "No selection", JOptionPane.ERROR_MESSAGE);
            result = false;
        } else {
            result = true;
        }
        
        return result;
    }
    private void saveUniversityData() throws IOException {
        JFileChooser objFileDialogue = new JFileChooser();
        int intDialogResult = JFileChooser.CANCEL_OPTION;
        intDialogResult = objFileDialogue.showSaveDialog(this);
        
        if(intDialogResult == JFileChooser.APPROVE_OPTION){
            
            File objFile = objFileDialogue.getSelectedFile();
            
            try(ObjectOutputStream objOut = new ObjectOutputStream(
                            new BufferedOutputStream(
                            new FileOutputStream(objFile)))) 
            {
                objOut.writeObject(uniModel);
                JOptionPane.showMessageDialog(this, "University data saved.", "Save completed", JOptionPane.INFORMATION_MESSAGE);
                
            
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(RoomSystemMenu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error saving data.", "File save error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                
            }
        
        } else {
            JOptionPane.showMessageDialog(this, "Save operation cancelled.", "Operation aborted", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadUniversityData() throws IOException {
        JFileChooser objFileDialogue = new JFileChooser();
        int intDialogResult = JFileChooser.CANCEL_OPTION;
        intDialogResult = objFileDialogue.showOpenDialog(this);
        
        if(intDialogResult == JFileChooser.APPROVE_OPTION){
            
            File objFile = objFileDialogue.getSelectedFile();
            
            if(objFile.exists() && objFile.canRead()){
                
                try(ObjectInputStream objIn = new ObjectInputStream(
                            new BufferedInputStream(
                            new FileInputStream(objFile)))) 
                {
                    Object objData = objIn.readObject();
                    
                    University objNewUniversity = (University)objData;
                    
                    if(objNewUniversity != null){
                        
                        ArrayList<IObserver> objUniObservers = uniModel.getObservers();
                        
                        for(IObserver currObserver : objUniObservers){
                            uniModel.removeObserver(currObserver);
                        }
                        
                        for(IObserver currObserver : objUniObservers){
                            objNewUniversity.registerObserver(currObserver);
                        }
                      
                        uniModel = objNewUniversity;
                        uniModel.notifyObservers();
                    } else {
                        JOptionPane.showMessageDialog(this, "No university data found.", "Error reading file", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }   catch (ClassNotFoundException |IOException | ClassCastException ex) {
                    JOptionPane.showMessageDialog(this, "Data file could not be read.", "Operation aborted", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "File not found / unreadable", "Error accessing file", JOptionPane.ERROR_MESSAGE);
            }
        } else {
             JOptionPane.showMessageDialog(this, "Load file cancelled.", "Operation cancelled", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void logToFile(String operation){
        try{
            Card selectedUser =
                uniModel.getUserList().get(jlstRoomSystemMenu.getSelectedIndex());
            
            LocalDateTime time = LocalDateTime.now();
            String dateTime = time.toString();
            String userNameID = selectedUser.getName() + " (Card ID: "+ selectedUser.getCardID() +")";
            
            File file = new File("ActivityLog.txt");
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            
            //bw.write(content);
            bw.write("OPERATION DETAILS: ");
            bw.newLine();
            bw.write(operation);
            bw.newLine();
            bw.write("Date: " + dateTime);
            bw.newLine();
            bw.write("User: " + userNameID);
            bw.newLine();
            bw.newLine();
            
            bw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void openActivityLog(){
        try{

        if ((new File("ActivityLog.txt")).exists()) {

            Process p = Runtime
               .getRuntime()
               .exec("rundll32 url.dll,FileProtocolHandler ActivityLog.txt");
            p.waitFor();

        } else {

            System.out.println("File does not exist");

        }

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    private void exitApp(){
        System.exit(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspRoomSystemMenu = new javax.swing.JScrollPane();
        jlstRoomSystemMenu = new javax.swing.JList<>();
        jlblUsersTitle = new java.awt.Label();
        jlblUserInformation = new javax.swing.JLabel();
        jlblInfoName = new javax.swing.JLabel();
        jlblDispName = new javax.swing.JLabel();
        jlblInfoID = new javax.swing.JLabel();
        jlblDispID = new javax.swing.JLabel();
        jlblInfoRole = new javax.swing.JLabel();
        jlblDispRole = new javax.swing.JLabel();
        jbtnAddUser = new javax.swing.JButton();
        jbtnEditUser = new javax.swing.JButton();
        jbtnDeleteUser = new javax.swing.JButton();
        jbtnAccessRoom = new javax.swing.JButton();
        jbtnEmergencyControls = new javax.swing.JButton();
        jbtnActivityLog = new javax.swing.JButton();
        jbtnExitApp = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("University Room System");

        jlstRoomSystemMenu.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlstRoomSystemMenu.setModel(userListModel);
        jlstRoomSystemMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstRoomSystemMenuMouseClicked(evt);
            }
        });
        jspRoomSystemMenu.setViewportView(jlstRoomSystemMenu);

        jlblUsersTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlblUsersTitle.setText("Users");

        jlblUserInformation.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblUserInformation.setText("User Information");

        jlblInfoName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblInfoName.setText("Name:");

        jlblDispName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispName.setText("Please select a user.");

        jlblInfoID.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblInfoID.setText("ID:");

        jlblDispID.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispID.setText("Please select a user.");

        jlblInfoRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblInfoRole.setText("Role:");

        jlblDispRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispRole.setText("Please select a user.");

        jbtnAddUser.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnAddUser.setText("Add");
        jbtnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddUserActionPerformed(evt);
            }
        });

        jbtnEditUser.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnEditUser.setText("Edit");
        jbtnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditUserActionPerformed(evt);
            }
        });

        jbtnDeleteUser.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnDeleteUser.setText("Delete");
        jbtnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteUserActionPerformed(evt);
            }
        });

        jbtnAccessRoom.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnAccessRoom.setText("Access Room");
        jbtnAccessRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAccessRoomActionPerformed(evt);
            }
        });

        jbtnEmergencyControls.setBackground(new java.awt.Color(240, 0, 0));
        jbtnEmergencyControls.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnEmergencyControls.setForeground(new java.awt.Color(255, 255, 255));
        jbtnEmergencyControls.setText("Emergency Controls");
        jbtnEmergencyControls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEmergencyControlsActionPerformed(evt);
            }
        });

        jbtnActivityLog.setText("Open Activity Log");
        jbtnActivityLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActivityLogActionPerformed(evt);
            }
        });

        jbtnExitApp.setText("Exit");
        jbtnExitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitAppActionPerformed(evt);
            }
        });

        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jbtnOpen.setText("Open");
        jbtnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblUsersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jbtnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnOpen))
                    .addComponent(jspRoomSystemMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlblInfoName)
                                .addComponent(jlblInfoID))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jlblInfoRole)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblDispID)
                            .addComponent(jlblUserInformation)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblDispRole)
                                    .addComponent(jlblDispName)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnAddUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnDeleteUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jbtnAccessRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEmergencyControls, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtnActivityLog)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnExitApp)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jlblUserInformation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblInfoName)
                            .addComponent(jlblDispName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblInfoID)
                            .addComponent(jlblDispID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblInfoRole)
                            .addComponent(jlblDispRole))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAddUser)
                            .addComponent(jbtnEditUser)
                            .addComponent(jbtnDeleteUser))
                        .addGap(11, 11, 11)
                        .addComponent(jbtnAccessRoom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnEmergencyControls, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnActivityLog)
                            .addComponent(jbtnExitApp)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblUsersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbtnSave)
                                .addComponent(jbtnOpen)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspRoomSystemMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlstRoomSystemMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstRoomSystemMenuMouseClicked
        showSelectedUser();
    }//GEN-LAST:event_jlstRoomSystemMenuMouseClicked

    private void jbtnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteUserActionPerformed
        deleteSelectedUser();
    }//GEN-LAST:event_jbtnDeleteUserActionPerformed

    private void jbtnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddUserActionPerformed
        AddUser objWindow = new AddUser();
        objWindow.setUniversity(uniModel);
        objWindow.setVisible(true);
    }//GEN-LAST:event_jbtnAddUserActionPerformed

    private void jbtnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditUserActionPerformed
        editSelectedUser();
    }//GEN-LAST:event_jbtnEditUserActionPerformed

    private void jbtnAccessRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAccessRoomActionPerformed
        if(validSelection()) {
            AccessRoom objWindow = new AccessRoom();
            objWindow.setUniversity(uniModel);
            objWindow.setSelectedUser(uniModel.getUserList().get(jlstRoomSystemMenu.getSelectedIndex()));
            objWindow.refreshBuildingListModel();
            objWindow.setVisible(true);
        }
        
        
    }//GEN-LAST:event_jbtnAccessRoomActionPerformed

    private void jbtnEmergencyControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEmergencyControlsActionPerformed
        EmergencyControls objWindow = new EmergencyControls();
            objWindow.setUniversity(uniModel);
            objWindow.refreshBuildingListModel();
            objWindow.setVisible(true);
    }//GEN-LAST:event_jbtnEmergencyControlsActionPerformed

    private void jbtnActivityLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActivityLogActionPerformed
        openActivityLog();
    }//GEN-LAST:event_jbtnActivityLogActionPerformed

    private void jbtnExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitAppActionPerformed
        exitApp();
    }//GEN-LAST:event_jbtnExitAppActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        try {
            saveUniversityData();
        } catch (IOException ex) {
            Logger.getLogger(RoomSystemMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOpenActionPerformed
        try {
            loadUniversityData();
        } catch (IOException ex) {
            Logger.getLogger(RoomSystemMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnOpenActionPerformed

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
            java.util.logging.Logger.getLogger(RoomSystemMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomSystemMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomSystemMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomSystemMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomSystemMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnAccessRoom;
    private javax.swing.JButton jbtnActivityLog;
    private javax.swing.JButton jbtnAddUser;
    private javax.swing.JButton jbtnDeleteUser;
    private javax.swing.JButton jbtnEditUser;
    private javax.swing.JButton jbtnEmergencyControls;
    private javax.swing.JButton jbtnExitApp;
    private javax.swing.JButton jbtnOpen;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JLabel jlblDispID;
    private javax.swing.JLabel jlblDispName;
    private javax.swing.JLabel jlblDispRole;
    private javax.swing.JLabel jlblInfoID;
    private javax.swing.JLabel jlblInfoName;
    private javax.swing.JLabel jlblInfoRole;
    private javax.swing.JLabel jlblUserInformation;
    private java.awt.Label jlblUsersTitle;
    private javax.swing.JList<String> jlstRoomSystemMenu;
    private javax.swing.JScrollPane jspRoomSystemMenu;
    // End of variables declaration//GEN-END:variables


    @Override
    public void Update() {
        refreshUserListModel();
       
        jlblDispName.setText("");
        jlblDispID.setText("");
        jlblDispRole.setText("");
    }
}
