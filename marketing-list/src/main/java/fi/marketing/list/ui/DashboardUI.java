/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.marketing.list.ui;

import fi.marketing.list.Operator;
import fi.marketing.list.logic.lists.MarketingList;
import fi.marketing.list.logic.FileReader;
import java.io.InputStream;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class DashboardUI extends javax.swing.JFrame {

    private Operator oper;

    public DashboardUI(Operator op) {
        oper = op;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        db = new javax.swing.JFileChooser();
        contactListName = new javax.swing.JTextField();
        nameNewMarketingList = new javax.swing.JTextField();
        addContactListButton = new javax.swing.JButton();
        createMarketingListButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        selectType = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        infoAreaStatistics = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        db.setControlButtonsAreShown(false);
        String directory = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        db.setCurrentDirectory(new java.io.File(directory));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");

        contactListName.setText("Give a Name to new Contact List");
        contactListName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                contactListName.setText("");
            }
        });

        nameNewMarketingList.setText("Give a Name to new Marketing List");
        nameNewMarketingList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                nameNewMarketingList.setText("");
            }
        });

        addContactListButton.setText("Add");
        addContactListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContactListButtonActionPerformed(evt);
            }
        });

        createMarketingListButton.setText("Create");
        createMarketingListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMarketingListButtonActionPerformed(evt);
            }
        });

        infoArea1.setColumns(20);
        infoArea1.setRows(5);
        infoArea1.setText("There are in the resources directory \n contact files koe1.txt, test1.txt and test2.txt to choose from. \n You can try to upload the same file twice \n and see that duplicates are not produced.");
        infoArea1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                infoArea1.setText("");
            }
        });
        jScrollPane1.setViewportView(infoArea1);

        infoArea2.setColumns(20);
        infoArea2.setRows(5);
        jScrollPane2.setViewportView(infoArea2);

        resultArea.setColumns(20);
        resultArea.setRows(5);
        jScrollPane3.setViewportView(resultArea);

        selectType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Collect Phone Numbers", "Collect Email Addresses" }));

        infoAreaStatistics.setColumns(20);
        infoAreaStatistics.setRows(5);
        jScrollPane4.setViewportView(infoAreaStatistics);

        jTextField1.setText("Upload more Contacts from a File");
        jTextField1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jTextField1.setText("");
                openAFileChooser();
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Open a file");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(selectType, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createMarketingListButton))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(nameNewMarketingList, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(contactListName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addContactListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addContactListButton)
                            .addComponent(contactListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(nameNewMarketingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createMarketingListButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addContactListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContactListButtonActionPerformed
        if (evt.getSource() == addContactListButton && jTextField1.getText().endsWith(".txt")) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(jTextField1.getText());
            FileReader just = oper.fileReaderInputStream(is);
            long startTime = System.currentTimeMillis();
            oper.matchAContactListToCustomers(just, contactListName.getText());
            long endTime = System.currentTimeMillis();
            infoArea1.setText(oper.printTime(startTime, endTime) + "\n" + "There is " + oper.getContactCountNumber() + " rows of contact data to be put on the list " + contactListName.getText() + ".");
            infoAreaStatistics.setText("There are " + oper.getCustomers().numberOfCustomers() + " customers in the system. \n Emails: " + oper.getCustomers().getNumberOfEmails() + "\n Phone numbers: " + oper.getCustomers().getNumberOfPhoneNumbers());
        }
    }//GEN-LAST:event_addContactListButtonActionPerformed

    private void createMarketingListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createMarketingListButtonActionPerformed
        if (evt.getSource() == createMarketingListButton) {
            if (selectType.getSelectedIndex() == 0) {
                setTextAndType(infoArea2, resultArea, fi.marketing.list.logic.Type.phone);
            } else if (selectType.getSelectedIndex() == 1) {
                setTextAndType(infoArea2, resultArea, fi.marketing.list.logic.Type.email);
            }
        }
    }//GEN-LAST:event_createMarketingListButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        openAFileChooser();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void setTextAndType(javax.swing.JTextArea two, javax.swing.JTextArea three, fi.marketing.list.logic.Type tp) {
        MarketingList list = oper.createAMarketingList(nameNewMarketingList.getText(), tp);
        two.setText(oper.getWrittenCount() + " rows were saved to the " + nameNewMarketingList.getText() + ".txt file.");
        three.setText(list.printString());
    }

    private void openAFileChooser() {
        FileFilter ft = new FileNameExtensionFilter("Text Files", "txt");
        db.addChoosableFileFilter(ft);
        int returnVal = db.showOpenDialog(this);     

        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = db.getSelectedFile();
            if (file.isFile()) {
                String file_name = file.toString();
                //InputStream is = getClass().getClassLoader().getResourceAsStream(file_name);
                //FileReader just = oper.fileReaderInputStream(is);
                String name = FilenameUtils.getName(file_name);
                jTextField1.setText(name);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addContactListButton;
    private javax.swing.JTextField contactListName;
    private javax.swing.JButton createMarketingListButton;
    private javax.swing.JFileChooser db;
    private javax.swing.JTextArea infoArea1;
    private javax.swing.JTextArea infoArea2;
    private javax.swing.JTextArea infoAreaStatistics;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nameNewMarketingList;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JComboBox<String> selectType;
    // End of variables declaration//GEN-END:variables
}
