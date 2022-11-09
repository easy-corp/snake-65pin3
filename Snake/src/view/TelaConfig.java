/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis
 */
public class TelaConfig extends javax.swing.JFrame {

    /**
     * Creates new form TelaConfig
     */
    public TelaConfig() {
        initComponents();
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbAlg2 = new javax.swing.JComboBox<>();
        cbAlg1 = new javax.swing.JComboBox<>();
        btnAjuda = new javax.swing.JButton();
        btnJogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Snake | Configurações");

        paneConfig.setBackground(new java.awt.Color(255, 255, 255));
        paneConfig.setMaximumSize(new java.awt.Dimension(400, 450));
        paneConfig.setMinimumSize(new java.awt.Dimension(400, 450));
        paneConfig.setName(""); // NOI18N
        paneConfig.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 209, 30));
        jLabel1.setText("SELECIONE SEUS LUTADORES");
        paneConfig.add(jLabel1);
        jLabel1.setBounds(80, 10, 332, 32);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 209, 30));
        jLabel2.setText("Algoritmo 2");
        paneConfig.add(jLabel2);
        jLabel2.setBounds(350, 60, 90, 22);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 209, 30));
        jLabel3.setText("Algoritmo 1");
        paneConfig.add(jLabel3);
        jLabel3.setBounds(60, 60, 90, 22);

        cbAlg2.setBackground(new java.awt.Color(255, 255, 255));
        cbAlg2.setForeground(new java.awt.Color(0, 209, 30));
        cbAlg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlg2ActionPerformed(evt);
            }
        });
        paneConfig.add(cbAlg2);
        cbAlg2.setBounds(320, 90, 140, 30);

        cbAlg1.setBackground(new java.awt.Color(255, 255, 255));
        cbAlg1.setForeground(new java.awt.Color(0, 209, 30));
        cbAlg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlg1ActionPerformed(evt);
            }
        });
        paneConfig.add(cbAlg1);
        cbAlg1.setBounds(30, 90, 140, 30);

        btnAjuda.setBackground(new java.awt.Color(0, 209, 30));
        btnAjuda.setForeground(new java.awt.Color(255, 255, 255));
        btnAjuda.setText("Ajuda");
        paneConfig.add(btnAjuda);
        btnAjuda.setBounds(170, 390, 75, 22);

        btnJogar.setBackground(new java.awt.Color(0, 209, 30));
        btnJogar.setForeground(new java.awt.Color(255, 255, 255));
        btnJogar.setText("Jogar");
        paneConfig.add(btnJogar);
        btnJogar.setBounds(270, 390, 72, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paneConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paneConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAlg1ActionPerformed

    private void cbAlg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlg2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAlg2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjuda;
    private javax.swing.JButton btnJogar;
    private javax.swing.JComboBox<String> cbAlg1;
    private javax.swing.JComboBox<String> cbAlg2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel paneConfig;
    // End of variables declaration//GEN-END:variables

    public void addItemCbAlg(String item) {
        this.cbAlg1.addItem(item);
        this.cbAlg2.addItem(item);
    }
    
    public void addAcaoBtnJogar(ActionListener acao) {
        this.btnJogar.addActionListener(acao);
    }
    
    public void addAcaoBtnAjuda(ActionListener acao) {
        this.btnAjuda.addActionListener(acao);
    }
    
}
