/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsys.gui;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.DateFormatter;
import universityroomsystem.University;
import urs.areas.Building;
import urs.areas.Room;
import urs.states.EmergencyState;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author Toby
 */
public class EmergencyControls extends javax.swing.JFrame {
    private University uniModel;
    
    private DefaultListModel buildingListModel;
    private DefaultListModel roomListModel;
    private States _state;
    private Building selectedBuilding;
    private Room selectedRoom;
    File dir;
    /**
     * Creates new form EmergencyControls
     */
    public EmergencyControls() {
        buildingListModel = new DefaultListModel();
        roomListModel = new DefaultListModel();
        
        initComponents();
    }
    
    public void refreshBuildingListModel() {
        this.buildingListModel.clear();
        
        for(Building b : this.uniModel.getBuildingList()){
            this.buildingListModel.addElement(b.getBuildingName() + "(" + b.getBuildingCode() + ")");
        }
        
    }   
    
    public void refreshRoomListModel() {
        this.roomListModel.clear();
        
        for(Room r : this.selectedBuilding.getRoomList()){
           this.roomListModel.addElement(r.getRoomCode() + "(" + r.getRoomType() + ")" );
        }
    }
    
    public void setUniversity(University objTarget){
        this.uniModel = objTarget;
    }
    
    private void showSelectedRoom(){
        this.selectedRoom =
                this.selectedBuilding.getRoomList().get(jlstEmRoomList.getSelectedIndex());
        
        jlblDispRoomName.setText(this.selectedRoom.getRoomCode());
        jlblDispRoomType.setText(this.selectedRoom.getRoomType().name());
        jlblDispRoomState.setText(this.selectedRoom.getState().toString());
    }
    private void showSelectedBuilding(){
        this.selectedBuilding =
                this.uniModel.getBuildingList().get(jlstEmBuildingList.getSelectedIndex());
        
        jlblDispBuildingName.setText(this.uniModel.getBuildingList().get(jlstEmBuildingList.getSelectedIndex()).getBuildingCode());
        jlblDispNoOfRooms.setText(Integer.toString(this.uniModel.getBuildingList().get(jlstEmBuildingList.getSelectedIndex()).getRoomList().size()));
        jlblDispBuildingState.setText(this.uniModel.getBuildingList().get(jlstEmBuildingList.getSelectedIndex()).getState().toString());
        
        refreshRoomListModel();
    }
    private void setCampus(){
        
        States s = selectState();
        
        if (s.toString() == new EmergencyState().toString()){
            String reason = JOptionPane.showInputDialog(this, "Enter the reason: ", "Emergency confirmation", JOptionPane.OK_CANCEL_OPTION);
            
            if(!reason.isEmpty()) {
                this.uniModel.getCampus().setState(s); 
                logEmergencyToFile("Campus", reason);
                JOptionPane.showMessageDialog(this, "The campus is now in emergency mode.", "State change successful", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "Please enter a reason.", "Empty Field", JOptionPane.ERROR_MESSAGE);
            }
        } else if (s.toString() == new NormalState().toString()) {
            this.uniModel.getCampus().setState(s);
            JOptionPane.showMessageDialog(this, "The campus is now in normal mode.", "State change successful", JOptionPane.INFORMATION_MESSAGE);
        }
        
        refreshRoomListModel();
    }
    private void setBuilding(){
        try {
            
            States s = selectState();
        
            if (s.toString() == new EmergencyState().toString()){
                String reason = JOptionPane.showInputDialog(this, "Enter the reason: ", 
                                                        "Emergency confirmation", 
                                                        JOptionPane.OK_CANCEL_OPTION);
            
                if(!reason.isEmpty()) {
                    this.selectedBuilding.setState(s);
                    logEmergencyToFile("Building",reason);
                    JOptionPane.showMessageDialog(this, this.selectedBuilding.getBuildingCode() +" is now in emergency mode.",  
                                           " State change successful", JOptionPane.INFORMATION_MESSAGE);
                                            
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a reason.", 
                                                "Empty Field", JOptionPane.ERROR_MESSAGE);
                }
            } else if (s.toString() == new NormalState().toString()) {
                this.selectedBuilding.setState(s);
                JOptionPane.showMessageDialog(this, this.selectedBuilding.getBuildingName() +" is now in normal mode.", 
                                          " State change successful", 
                                          JOptionPane.INFORMATION_MESSAGE);
            
            }
                refreshRoomListModel();
        } catch (NullPointerException ex){
            
            JOptionPane.showMessageDialog(this,"Please select a bulding",
                                          "No selection", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void setRoom(){
        try{
            States s = selectState();
        
        if (s.toString() == new EmergencyState().toString()){
            String reason = JOptionPane.showInputDialog(this, "Enter the reason: ",
                                                        "Emergency confirmation", 
                                                        JOptionPane.OK_CANCEL_OPTION);
            
            if(!reason.isEmpty()) {
                this.selectedRoom.setState(s);
                logEmergencyToFile("Room", reason);
                JOptionPane.showMessageDialog(this, this.selectedBuilding.getBuildingCode() + 
                                          this.selectedRoom.getRoomCode() + " is now in emergency mode", "State change successful",
                                            JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a reason.", 
                                              "Empty Field", JOptionPane.ERROR_MESSAGE);
            }
        } else if (s.toString() == new NormalState().toString()){
            
            this.selectedRoom.setState(s);
            JOptionPane.showMessageDialog(this, this.selectedBuilding.getBuildingCode() + 
                                          this.selectedRoom.getRoomCode() + " is now in normal mode.", "State change successful",
                                            JOptionPane.INFORMATION_MESSAGE);
            
        }
        refreshRoomListModel();
        } catch (NullPointerException ex){
            
            JOptionPane.showMessageDialog(this, "Please select a bulding",
                                          "No selection: " + ex.getMessage(), 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private States selectState() {
        final JPanel stateSelectPanel = new JPanel();
        final JLabel lSelectState = new JLabel("Please select a state:");
        final JRadioButton rButtonNrm = new JRadioButton("Normal");
        final JRadioButton rButtonEmg = new JRadioButton("Emergency");
        
        stateSelectPanel.add(lSelectState);
        stateSelectPanel.add(rButtonNrm);
        stateSelectPanel.add(rButtonEmg);
        
        JOptionPane.showMessageDialog(this, stateSelectPanel);
        
        States selectedState = null;
        
        if (rButtonNrm.isSelected()) {
                selectedState = new NormalState();
            }
        if(rButtonEmg.isSelected()){
            selectedState = new EmergencyState();
        }
        return selectedState;
        
    }
    private void disposeForm(){
        this.dispose();
    }
    
    private void logEmergencyToFile(String whatCalled, String reason){
        try{
            
            LocalDateTime time = LocalDateTime.now();
            String dateTime = time.toString();
            
            if(this.selectedRoom != null){
                String buildingCode = this.selectedBuilding.getBuildingCode();
            }
            
            if(this.selectedRoom != null){
                String roomCode = this.selectedRoom.getRoomCode();
            }
            
            File file = new File("ActivityLog.txt");
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("EMERGENCY STATE ACTIVATED: ");
            bw.newLine();
            bw.write("Level: " + whatCalled);
            bw.newLine();
            bw.write(dateTime);
            bw.newLine();
            bw.write("Reason: " + reason);
            bw.newLine();
            bw.newLine();
            
            bw.close();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
            String formattedDateTime = time.format(formatter);
            
            dir = new File("EM_" + formattedDateTime);
            dir.mkdir();
            
            File target = new File( dir.getAbsoluteFile() + "/EM_ActivityLog.txt");
            
            copyFile(file.getAbsoluteFile(),target);
            
            saveUniversityData();
                    
        }
        catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void saveUniversityData() throws IOException {
        
        
            File dataModel = new File( dir.getAbsoluteFile() + "/EM_UniversityData");
            
            try(ObjectOutputStream objOut = new ObjectOutputStream(
                            new BufferedOutputStream(
                            new FileOutputStream(dataModel)))) 
            {
                objOut.writeObject(uniModel);
                JOptionPane.showMessageDialog(this, "University data saved.", "Save completed", JOptionPane.INFORMATION_MESSAGE);
                
            
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(RoomSystemMenu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error saving data.", "File save error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                
            }
    }
    public  void copyFile(File from, File to ) throws IOException {
        Files.copy(from.toPath(), to.toPath());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscpBuildingListPane = new javax.swing.JScrollPane();
        jlstEmBuildingList = new javax.swing.JList<>();
        jscpRoomListPane = new javax.swing.JScrollPane();
        jlstEmRoomList = new javax.swing.JList<>();
        jlblEmergencyControlsTitle = new javax.swing.JLabel();
        jbtnEmSetCampus = new javax.swing.JButton();
        jbtnSetBuilding = new javax.swing.JButton();
        jbtnSetRoom = new javax.swing.JButton();
        jbtnEmCancel = new javax.swing.JButton();
        jbtnRoomInfo = new javax.swing.JLabel();
        jbtnBuildingInfo = new javax.swing.JLabel();
        jbtnBuildingName = new javax.swing.JLabel();
        jbtnRoomName = new javax.swing.JLabel();
        jbtnRoomNoOfRooms = new javax.swing.JLabel();
        jbtnRoomType = new javax.swing.JLabel();
        jlblDispRoomName = new javax.swing.JLabel();
        jlblDispRoomType = new javax.swing.JLabel();
        jlblDispBuildingName = new javax.swing.JLabel();
        jlblDispNoOfRooms = new javax.swing.JLabel();
        jlblBuildingState = new javax.swing.JLabel();
        jlblDispBuildingState = new javax.swing.JLabel();
        jlblRoomState = new javax.swing.JLabel();
        jlblDispRoomState = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlstEmBuildingList.setModel(buildingListModel);
        jlstEmBuildingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstEmBuildingListMouseClicked(evt);
            }
        });
        jscpBuildingListPane.setViewportView(jlstEmBuildingList);

        jlstEmRoomList.setModel(roomListModel);
        jlstEmRoomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstEmRoomListMouseClicked(evt);
            }
        });
        jscpRoomListPane.setViewportView(jlstEmRoomList);

        jlblEmergencyControlsTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlblEmergencyControlsTitle.setText("Emergency Controls");

        jbtnEmSetCampus.setText("Set Campus");
        jbtnEmSetCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEmSetCampusActionPerformed(evt);
            }
        });

        jbtnSetBuilding.setText("Set Building");
        jbtnSetBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSetBuildingActionPerformed(evt);
            }
        });

        jbtnSetRoom.setText("Set Room");
        jbtnSetRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSetRoomActionPerformed(evt);
            }
        });

        jbtnEmCancel.setText("Cancel");
        jbtnEmCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEmCancelActionPerformed(evt);
            }
        });

        jbtnRoomInfo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnRoomInfo.setText("Room Information");

        jbtnBuildingInfo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnBuildingInfo.setText("Building Information");

        jbtnBuildingName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnBuildingName.setText("Name:");

        jbtnRoomName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnRoomName.setText("Name:");

        jbtnRoomNoOfRooms.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnRoomNoOfRooms.setText("Rooms:");

        jbtnRoomType.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnRoomType.setText("Type:");

        jlblDispRoomName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispRoomName.setText("select a room");

        jlblDispRoomType.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispRoomType.setText("select a room");

        jlblDispBuildingName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispBuildingName.setText("select a building");

        jlblDispNoOfRooms.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispNoOfRooms.setText("select a building");

        jlblBuildingState.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblBuildingState.setText("State:");

        jlblDispBuildingState.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispBuildingState.setText("select a building");

        jlblRoomState.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblRoomState.setText("State:");

        jlblDispRoomState.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblDispRoomState.setText("select a room");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblEmergencyControlsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblBuildingState)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jscpBuildingListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jscpRoomListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnBuildingName)
                                    .addComponent(jbtnRoomNoOfRooms))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblDispNoOfRooms)
                                    .addComponent(jlblDispBuildingName)
                                    .addComponent(jlblDispBuildingState)))
                            .addComponent(jbtnBuildingInfo)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnEmSetCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnSetBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnRoomInfo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblRoomState)
                                            .addComponent(jbtnRoomType))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblDispRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblDispRoomState)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnEmCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnSetRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jbtnRoomName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlblDispRoomName)))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblEmergencyControlsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jscpBuildingListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jscpRoomListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnRoomInfo)
                    .addComponent(jbtnBuildingInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnBuildingName)
                            .addComponent(jlblDispBuildingName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnRoomNoOfRooms)
                            .addComponent(jlblDispNoOfRooms)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnRoomName)
                            .addComponent(jlblDispRoomName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnRoomType)
                            .addComponent(jlblDispRoomType))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblBuildingState)
                        .addComponent(jlblDispBuildingState))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblRoomState)
                        .addComponent(jlblDispRoomState)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSetRoom)
                    .addComponent(jbtnSetBuilding))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEmCancel)
                    .addComponent(jbtnEmSetCampus))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlstEmBuildingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstEmBuildingListMouseClicked
        showSelectedBuilding();
    }//GEN-LAST:event_jlstEmBuildingListMouseClicked

    private void jlstEmRoomListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstEmRoomListMouseClicked
        showSelectedRoom();
    }//GEN-LAST:event_jlstEmRoomListMouseClicked

    private void jbtnEmSetCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEmSetCampusActionPerformed
        setCampus();
    }//GEN-LAST:event_jbtnEmSetCampusActionPerformed

    private void jbtnEmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEmCancelActionPerformed
        disposeForm();
    }//GEN-LAST:event_jbtnEmCancelActionPerformed

    private void jbtnSetBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSetBuildingActionPerformed
        setBuilding();
    }//GEN-LAST:event_jbtnSetBuildingActionPerformed

    private void jbtnSetRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSetRoomActionPerformed
        setRoom();
    }//GEN-LAST:event_jbtnSetRoomActionPerformed

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
            java.util.logging.Logger.getLogger(EmergencyControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmergencyControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmergencyControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmergencyControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmergencyControls().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jbtnBuildingInfo;
    private javax.swing.JLabel jbtnBuildingName;
    private javax.swing.JButton jbtnEmCancel;
    private javax.swing.JButton jbtnEmSetCampus;
    private javax.swing.JLabel jbtnRoomInfo;
    private javax.swing.JLabel jbtnRoomName;
    private javax.swing.JLabel jbtnRoomNoOfRooms;
    private javax.swing.JLabel jbtnRoomType;
    private javax.swing.JButton jbtnSetBuilding;
    private javax.swing.JButton jbtnSetRoom;
    private javax.swing.JLabel jlblBuildingState;
    private javax.swing.JLabel jlblDispBuildingName;
    private javax.swing.JLabel jlblDispBuildingState;
    private javax.swing.JLabel jlblDispNoOfRooms;
    private javax.swing.JLabel jlblDispRoomName;
    private javax.swing.JLabel jlblDispRoomState;
    private javax.swing.JLabel jlblDispRoomType;
    private javax.swing.JLabel jlblEmergencyControlsTitle;
    private javax.swing.JLabel jlblRoomState;
    private javax.swing.JList<String> jlstEmBuildingList;
    private javax.swing.JList<String> jlstEmRoomList;
    private javax.swing.JScrollPane jscpBuildingListPane;
    private javax.swing.JScrollPane jscpRoomListPane;
    // End of variables declaration//GEN-END:variables
}
