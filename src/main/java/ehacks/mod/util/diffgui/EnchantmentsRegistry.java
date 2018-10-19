/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehacks.mod.util.diffgui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;

/**
 *
 * @author radioegor146
 */
public class EnchantmentsRegistry extends javax.swing.JFrame {

    /**
     * Creates new form DiffFrame
     */
    public EnchantmentsRegistry() {
        initComponents();
        ArrayList<EnchantmentInfo> enchantmentInfos = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.enchantmentsList) {
            if (enchantment == null) {
                continue;
            }
            enchantmentInfos.add(new EnchantmentInfo(enchantment));
        }
        int maxIdLength = 2;
        int maxClassNameLength = 5;
        int maxNameLength = 4;
        for (EnchantmentInfo enchantment : enchantmentInfos) {
            maxIdLength = Math.max(maxIdLength, enchantment.id.length());
            maxClassNameLength = Math.max(maxClassNameLength, enchantment.className.length());
            maxNameLength = Math.max(maxNameLength, enchantment.name.length());
        }
        int margin = 3;
        maxIdLength += margin;
        maxClassNameLength += margin;
        maxNameLength += margin;
        int overallLength = maxIdLength + maxClassNameLength + maxNameLength;
        ((DefaultListModel) this.enchantmentsList.getModel()).clear();
        StringBuilder infoString = new StringBuilder();
        infoString.append("ID");
        for (int i = 0; i < maxIdLength - 2; i++) {
            infoString.append(' ');
        }
        infoString.append("Name");
        for (int i = 0; i < maxNameLength - 4; i++) {
            infoString.append(' ');
        }
        infoString.append("Class");
        for (int i = 0; i < maxClassNameLength - 5; i++) {
            infoString.append(' ');
        }
        ((DefaultListModel<String>) this.enchantmentsList.getModel()).addElement(infoString.toString());
        StringBuilder delimString = new StringBuilder();
        for (int i = 0; i < overallLength; i++) {
            delimString.append("-");
        }
        ((DefaultListModel<String>) this.enchantmentsList.getModel()).addElement(delimString.toString());
        for (EnchantmentInfo enchantment : enchantmentInfos) {
            StringBuilder enchantmentString = new StringBuilder();
            enchantmentString.append(enchantment.id);
            for (int i = 0; i < maxIdLength - enchantment.id.length(); i++) {
                enchantmentString.append(' ');
            }
            enchantmentString.append(enchantment.name);
            for (int i = 0; i < maxNameLength - enchantment.name.length(); i++) {
                enchantmentString.append(' ');
            }
            enchantmentString.append(enchantment.className);
            for (int i = 0; i < maxClassNameLength - enchantment.className.length(); i++) {
                enchantmentString.append(' ');
            }
            ((DefaultListModel<String>) this.enchantmentsList.getModel()).addElement(enchantmentString.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        enchantmentsScrollPane = new javax.swing.JScrollPane();
        enchantmentsList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Enchantments");

        enchantmentsList.setFont(new java.awt.Font("Lucida Console", Font.PLAIN, 11)); // NOI18N
        enchantmentsList.setModel(new javax.swing.DefaultListModel());
        enchantmentsScrollPane.setViewportView(enchantmentsList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(enchantmentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(enchantmentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    public class EnchantmentInfo {

        public String className;
        public String name;
        public String id;

        public EnchantmentInfo(Enchantment enchantment) {
            className = enchantment.getClass().getName();
            name = I18n.format(enchantment.getName());
            id = String.valueOf(enchantment.effectId);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JList<String> enchantmentsList;
    private javax.swing.JScrollPane enchantmentsScrollPane;
    // End of variables declaration                   
}