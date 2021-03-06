/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsys.gui;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import universityroomsystem.University;
import urs.areas.Building;
import urs.areas.Room;
import urs.cards.Card;

/**
 *
 * @author Toby
 */
public class AccessRoom extends javax.swing.JFrame {
    private University uniModel;
    private Card selectedUser;
    
    private DefaultListModel buildingListModel;
    private DefaultListModel roomListModel;
    
    private Building selectedBuilding;
    private Room selectedRoom;
    
    private Boolean accessGranted;
    /**
     * Creates new form AccessRoom
     */
    public AccessRoom() {
        
        buildingListModel = new DefaultListModel();
        roomListModel = new DefaultListModel();
        
        accessGranted = false;
        
        initComponents();
    }
    
    public void refreshBuildingListModel() {
        buildingListModel.clear();
        
        for(Building b : uniModel.getBuildingList()){
            buildingListModel.addElement(b.getBuildingName() + "(" + b.getBuildingCode() + ")");
        }
        
    }   
    
    public void refreshRoomListModel() {
        roomListModel.clear();
        
        for(Room r : selectedBuilding.getRoomList()){
           roomListModel.addElement(r.getRoomCode() + "(" + r.getRoomType() + ")" );
        }
    }
    public void setUniversity(University objTarget){
        this.uniModel = objTarget;
    }
    
    public void setSelectedUser(Card user) {
        this.selectedUser = user;
        jlblAccessRoomDispName.setText(this.selectedUser.getName());
        jlblAccessRoomDispRole.setText(this.selectedUser.getRole().name());
    }
    private void showSelectedUser(){
        jlblAccessRoomDispName.setText(this.selectedUser.getName());
        jlblAccessRoomDispRole.setText(this.selectedUser.getRole().toString());
    }
    private void showSelectedRoom(){
        this.selectedRoom =
                selectedBuilding.getRoomList().get(jlstRoomList.getSelectedIndex());
        
        jlblAccessRoomDispRoomName.setText(this.selectedRoom.getRoomCode());
        jlblAccessRoomDispRoomType.setText(this.selectedRoom.getRoomType().name());
    }
    private void showSelectedBuilding(){
        this.selectedBuilding =
                uniModel.getBuildingList().get(jlstBuildingList.getSelectedIndex());
        refreshRoomListModel();
    }
    private void disposeForm(){
        this.dispose();
    }
    private void accessRoom(){
        try {
           if(this.selectedRoom.Access(this.selectedUser)){
            jlblAccessRoomAccess.setText("ACCESS GRANTED");
            jlblAccessRoomAccess.setForeground(Color.GREEN);
            accessGranted = true;
        } else {
            jlblAccessRoomAccess.setText("ACCESS DENIED");
            jlblAccessRoomAccess.setForeground(Color.RED);
        }
            logAccessToFile(); 
        } catch (NullPointerException ex){
            JOptionPane.showMessageDialog(this,"Please select a room",
                                          "No selection", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void logAccessToFile(){
        try{
            
            LocalDateTime time = LocalDateTime.now();
            String dateTime = time.toString();
            String userNameID = this.selectedUser.getName() + " (Card ID: "+ this.selectedUser.getCardID() +")";
            String buildingCode = this.selectedBuilding.getBuildingCode();
            String roomCode = this.selectedRoom.getRoomCode();
            String roomState = this.selectedRoom.getState().toString();
            
            File file = new File("ActivityLog.txt");
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            
            //bw.write(content);
            bw.write("ATTEMPTED ACCESS DETAILS: ");
            bw.newLine();
            bw.write("Date: " + dateTime);
            bw.newLine();
            bw.write("User: " + userNameID);
            bw.newLine();
            bw.write("Room: " + buildingCode + roomCode);
            bw.newLine();
            bw.write("State: " + roomState);
            bw.newLine();
            bw.write("Access granted: " + accessGranted);
            bw.newLine();
            bw.newLine();
            
            
            bw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        accessGranted = false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspBuildingPane = new javax.swing.JScrollPane();
        jlstBuildingList = new javax.swing.JList<>();
        jspRoomListPane = new javax.swing.JScrollPane();
        jlstRoomList = new javax.swing.JList<>();
        jlblBuildingsListTitle = new javax.swing.JLabel();
        jlblRoomsListTitle = new javax.swing.JLabel();
        jlblAccessRoomTitle = new javax.swing.JLabel();
        jlblAccessRoomSelectedUser = new javax.swing.JLabel();
        jlblAccessRoomUserName = new javax.swing.JLabel();
        jlblAccessRoomUserRole = new javax.swing.JLabel();
        jlblAccessRoomDispName = new javax.swing.JLabel();
        jlblAccessRoomDispRole = new javax.swing.JLabel();
        jbtnAccessRoomSwipe = new javax.swing.JButton();
        jbtnAccessRoomCancel = new javax.swing.JButton();
        jlblAccessRoomSelectedName = new javax.swing.JLabel();
        jlblAccessRoomSelectedRoomTitle = new javax.swing.JLabel();
        jlblAccessRoomSelectedType = new javax.swing.JLabel();
        jlblAccessRoomDispRoomName = new javax.swing.JLabel();
        jlblAccessRoomDispRoomType = new javax.swing.JLabel();
        jlblAccessRoomAccess = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Access Room");
        setName("jfrmAccessRoom"); // NOI18N

        jlstBuildingList.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlstBuildingList.setModel(buildingListModel);
        jlstBuildingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstBuildingListMouseClicked(evt);
            }
        });
        jspBuildingPane.setViewportView(jlstBuildingList);

        jlstRoomList.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlstRoomList.setModel(roomListModel);
        jlstRoomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstRoomListMouseClicked(evt);
            }
        });
        jspRoomListPane.setViewportView(jlstRoomList);

        jlblBuildingsListTitle.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblBuildingsListTitle.setText("Buildings");

        jlblRoomsListTitle.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblRoomsListTitle.setText("Rooms");

        jlblAccessRoomTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlblAccessRoomTitle.setText("Access Room");

        jlblAccessRoomSelectedUser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblAccessRoomSelectedUser.setText("Selected user");

        jlblAccessRoomUserName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomUserName.setText("Name:");

        jlblAccessRoomUserRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomUserRole.setText("Role:");

        jlblAccessRoomDispName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomDispName.setText("jLabel1");

        jlblAccessRoomDispRole.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomDispRole.setText("jLabel1");

        jbtnAccessRoomSwipe.setText("Swipe");
        jbtnAccessRoomSwipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAccessRoomSwipeActionPerformed(evt);
            }
        });

        jbtnAccessRoomCancel.setText("Cancel");
        jbtnAccessRoomCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAccessRoomCancelActionPerformed(evt);
            }
        });

        jlblAccessRoomSelectedName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomSelectedName.setText("Code:");

        jlblAccessRoomSelectedRoomTitle.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblAccessRoomSelectedRoomTitle.setText("Selected Room");

        jlblAccessRoomSelectedType.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomSelectedType.setText("Room type:");

        jlblAccessRoomDispRoomName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomDispRoomName.setText("Please select a room.");

        jlblAccessRoomDispRoomType.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomDispRoomType.setText("Please select a room.");

        jlblAccessRoomAccess.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblAccessRoomAccess.setText("Press 'Swipe' to try and access this room.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblAccessRoomTitle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblBuildingsListTitle)
                            .addComponent(jspBuildingPane, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblRoomsListTitle)
                            .addComponent(jspRoomListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblAccessRoomSelectedUser)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jlblAccessRoomUserRole)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jlblAccessRoomDispRole))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jlblAccessRoomUserName)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jlblAccessRoomDispName)))
                                            .addComponent(jlblAccessRoomSelectedRoomTitle)))
                                    .addComponent(jbtnAccessRoomSwipe, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblAccessRoomSelectedName, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblAccessRoomSelectedType, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlblAccessRoomDispRoomType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jlblAccessRoomDispRoomName, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                                    .addComponent(jlblAccessRoomAccess))
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtnAccessRoomCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblAccessRoomTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblBuildingsListTitle)
                            .addComponent(jlblRoomsListTitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspRoomListPane, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspBuildingPane, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblAccessRoomSelectedUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAccessRoomUserName)
                            .addComponent(jlblAccessRoomDispName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAccessRoomUserRole)
                            .addComponent(jlblAccessRoomDispRole))
                        .addGap(27, 27, 27)
                        .addComponent(jlblAccessRoomSelectedRoomTitle)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAccessRoomSelectedName)
                            .addComponent(jlblAccessRoomDispRoomName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAccessRoomSelectedType)
                            .addComponent(jlblAccessRoomDispRoomType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jlblAccessRoomAccess)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAccessRoomCancel)
                            .addComponent(jbtnAccessRoomSwipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlstBuildingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstBuildingListMouseClicked
        showSelectedBuilding();
    }//GEN-LAST:event_jlstBuildingListMouseClicked

    private void jlstRoomListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstRoomListMouseClicked
        showSelectedRoom();
    }//GEN-LAST:event_jlstRoomListMouseClicked

    private void jbtnAccessRoomCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAccessRoomCancelActionPerformed
        disposeForm();
    }//GEN-LAST:event_jbtnAccessRoomCancelActionPerformed

    private void jbtnAccessRoomSwipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAccessRoomSwipeActionPerformed
        accessRoom();
    }//GEN-LAST:event_jbtnAccessRoomSwipeActionPerformed

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
            java.util.logging.Logger.getLogger(AccessRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccessRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccessRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccessRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccessRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnAccessRoomCancel;
    private javax.swing.JButton jbtnAccessRoomSwipe;
    private javax.swing.JLabel jlblAccessRoomAccess;
    private javax.swing.JLabel jlblAccessRoomDispName;
    private javax.swing.JLabel jlblAccessRoomDispRole;
    private javax.swing.JLabel jlblAccessRoomDispRoomName;
    private javax.swing.JLabel jlblAccessRoomDispRoomType;
    private javax.swing.JLabel jlblAccessRoomSelectedName;
    private javax.swing.JLabel jlblAccessRoomSelectedRoomTitle;
    private javax.swing.JLabel jlblAccessRoomSelectedType;
    private javax.swing.JLabel jlblAccessRoomSelectedUser;
    private javax.swing.JLabel jlblAccessRoomTitle;
    private javax.swing.JLabel jlblAccessRoomUserName;
    private javax.swing.JLabel jlblAccessRoomUserRole;
    private javax.swing.JLabel jlblBuildingsListTitle;
    private javax.swing.JLabel jlblRoomsListTitle;
    private javax.swing.JList<String> jlstBuildingList;
    private javax.swing.JList<String> jlstRoomList;
    private javax.swing.JScrollPane jspBuildingPane;
    private javax.swing.JScrollPane jspRoomListPane;
    // End of variables declaration//GEN-END:variables
}
