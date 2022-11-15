package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TelaResultado extends javax.swing.JFrame {

    private javax.swing.JLabel jlTitulo;

    private javax.swing.JLabel jlTempo;
    private javax.swing.JTextField tfTempo;

    private javax.swing.JLabel jlBot0;
    private javax.swing.JTextField tfBot0;

    private javax.swing.JLabel jlBot1;
    private javax.swing.JTextField tfBot1;

    private javax.swing.JLabel jlTimeOut;
    private javax.swing.JTextField tfTimeOut;

    private javax.swing.JLabel jlVencedor;
    private javax.swing.JTextField tfVencedor;

    private JButton btFechar;

    private javax.swing.JPanel paneConfig;

    private final String bot0;
    private final String bot1;

    public TelaResultado(String bot0, String bot1) {
        this.bot0 = bot0;
        this.bot1 = bot1;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        paneConfig = new javax.swing.JPanel();
        jlTitulo = new javax.swing.JLabel();

        jlTempo = new javax.swing.JLabel();
        tfTempo = new JTextField();

        jlBot0 = new javax.swing.JLabel();
        tfBot0 = new JTextField();

        jlBot1 = new javax.swing.JLabel();
        tfBot1 = new JTextField();

        jlTimeOut = new javax.swing.JLabel();
        tfTimeOut = new JTextField();

        jlVencedor = new javax.swing.JLabel();
        tfVencedor = new JTextField();

        btFechar = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Snake | Resultado");

        paneConfig.setBackground(new java.awt.Color(255, 255, 255));
        paneConfig.setMaximumSize(new java.awt.Dimension(400, 450));
        paneConfig.setMinimumSize(new java.awt.Dimension(400, 450));
        paneConfig.setName(""); // NOI18N
        paneConfig.setLayout(null);

        jlTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(0, 209, 30));
        jlTitulo.setText("RESULTADO");
        paneConfig.add(jlTitulo);
        jlTitulo.setBounds(170, 10, 332, 32);

        jlTempo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlTempo.setForeground(new java.awt.Color(0, 209, 30));
        jlTempo.setText("Tempo:");
        paneConfig.add(jlTempo);
        jlTempo.setBounds(30, 60, 120, 22);

        tfTempo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfTempo.setForeground(new java.awt.Color(0, 209, 30));
        tfTempo.setEditable(false);
        paneConfig.add(tfTempo);
        tfTempo.setBounds(160, 60, 120, 22);

        jlBot0.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlBot0.setForeground(new java.awt.Color(0, 209, 30));
        jlBot0.setText(bot0 + ":");
        paneConfig.add(jlBot0);
        jlBot0.setBounds(30, 100, 120, 22);

        tfBot0.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfBot0.setForeground(new java.awt.Color(0, 209, 30));
        tfBot0.setEditable(false);
        paneConfig.add(tfBot0);
        tfBot0.setBounds(160, 100, 120, 22);

        jlBot1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlBot1.setForeground(new java.awt.Color(0, 209, 30));
        jlBot1.setText(bot1 + ":");
        paneConfig.add(jlBot1);
        jlBot1.setBounds(30, 140, 120, 22);

        tfBot1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfBot1.setForeground(new java.awt.Color(0, 209, 30));
        tfBot1.setEditable(false);
        paneConfig.add(tfBot1);
        tfBot1.setBounds(160, 140, 120, 22);

        jlTimeOut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlTimeOut.setForeground(new java.awt.Color(0, 209, 30));
        jlTimeOut.setText("Time Out:");
        paneConfig.add(jlTimeOut);
        jlTimeOut.setBounds(30, 180, 120, 22);

        tfTimeOut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfTimeOut.setForeground(new java.awt.Color(0, 209, 30));
        tfTimeOut.setEditable(false);
        paneConfig.add(tfTimeOut);
        tfTimeOut.setBounds(160, 180, 120, 22);

        jlVencedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlVencedor.setForeground(new java.awt.Color(0, 209, 30));
        jlVencedor.setText("Vencedor:");
        paneConfig.add(jlVencedor);
        jlVencedor.setBounds(30, 220, 120, 22);

        tfVencedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfVencedor.setForeground(new java.awt.Color(0, 209, 30));
        tfVencedor.setEditable(false);
        paneConfig.add(tfVencedor);
        tfVencedor.setBounds(160, 220, 120, 22);

        btFechar.setBackground(new java.awt.Color(0, 209, 30));
        btFechar.setForeground(new java.awt.Color(255, 255, 255));
        btFechar.setText("Fechar");
        paneConfig.add(btFechar);
        btFechar.setBounds(200, 390, 75, 22);

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
    }// </editor-fold>

    public JTextField getTempo() {
        return tfTempo;
    }

    public JTextField getBot0() {
        return tfBot0;
    }

    public JTextField getBot1() {
        return tfBot1;
    }

    public JTextField getVencedor() {
        return tfVencedor;
    }

    public JTextField getTimeOut() {
        return tfTimeOut;
    }

    public JButton getFechar() {
        return btFechar;
    }

}