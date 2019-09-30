/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.sintactico;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LinkRs
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    ArbolSintactico st = null;
    AFD afd = null;
    /*
        (a|bb*)+aa(ab)?gb+
        (a|b(c|d)*)+
        a+bc?aa*b(((d|e)|&)+)?
        (a|b)*abb
    */
    
    public GUI() {
        initComponents();
    }
    
    private void DFS(Nodo actual, DefaultTableModel tableModel){
        if(actual == null) return;
        String ppos = String.join(", ", actual.getPpos().toString());
        String upos = String.join(", ", actual.getUpos().toString());
        tableModel.addRow(new Object[] {actual.getLabel(), ppos, upos});
        DFS(actual.getHijoIzq(), tableModel);
        DFS(actual.getHijoDer(), tableModel);
    }
    //https://users.cs.fiu.edu/~kraynek/COP3530-assignments/DrawBinaryTreeAssignment.html
    private void paintTree(Nodo actual, Graphics g, int depth, int x, int y){
        if(actual == null) return;
        g.fillOval(x, y, 20, 20);
        paintTree(actual.getHijoIzq(), g, depth + 1, x - 6*actual.getNumSeq(), y+10*depth);
        paintTree(actual.getHijoDer(), g, depth + 1, x + 6*actual.getNumSeq(), y+10*depth);
    }
    
    private void resetValores(){
        Graphics g = lienzoJSP.getGraphics();
        g.clearRect(0, 0, lienzoJSP.getWidth(), lienzoJSP.getHeight());
        alfaLabel.setText("Vacio");
        regexTF.setText("");
        cadenaTF.setText("");
        DefaultTableModel tableModel = (DefaultTableModel)puTable.getModel();
        tableModel.setRowCount(0);
        tableModel = (DefaultTableModel)spTable.getModel();
        tableModel.setRowCount(0);
        tableModel = (DefaultTableModel)trandTable.getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
    }
    
    private void setValores(){
        DefaultTableModel tableModel = (DefaultTableModel)puTable.getModel();
        alfaLabel.setText(afd.getAlfaString());
        DFS(st.getRaiz(), tableModel);
        Set<Integer> sgtPos[] = st.getSgtPos();
        tableModel = (DefaultTableModel)spTable.getModel();
        for(int i = 1; i <= st.getCntCaracteres(); i++){
            String conjPos = sgtPos[i].toString();
            tableModel.addRow(new Object[] {i, conjPos});
        }
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tableModel.addColumn("Estado");
        Set<Character> alfabeto = afd.getAlfabeto();
        for(Character simbolo : alfabeto) tableModel.addColumn(simbolo);
        Hashtable<Character,Integer> tranD[] = afd.getTranD();
        for(int i = 1; i <= afd.getCntEstados(); i++){
            ArrayList<Object> row = new ArrayList<>();
            String estadoFinal = "";
            if(afd.esEstadoFinal(i)) estadoFinal = "*";
            row.add(i + estadoFinal);
            for(Character simbolo : alfabeto) row.add(tranD[i].get(simbolo));
            tableModel.addRow(row.toArray());
        }
        trandTable.setModel(tableModel);
        int x = lienzoJSP.getWidth()/2 + 50;
        int y = 10;
        paintTree(st.getRaiz(), lienzoJSP.getGraphics(), 1, x, y);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lienzoJSP = new javax.swing.JScrollPane();
        panelPestañas = new javax.swing.JTabbedPane();
        puPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        puTable = new javax.swing.JTable();
        spPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        spTable = new javax.swing.JTable();
        trandPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        trandTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        regexTF = new javax.swing.JTextField();
        cadenaTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        arbolButton = new javax.swing.JButton();
        verficarButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        alfaLabel = new javax.swing.JLabel();
        reiniciarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lienzoJSP.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        lienzoJSP.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        puTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nodo", "Ppos", "Upos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(puTable);

        javax.swing.GroupLayout puPanelLayout = new javax.swing.GroupLayout(puPanel);
        puPanel.setLayout(puPanelLayout);
        puPanelLayout.setHorizontalGroup(
            puPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(puPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        puPanelLayout.setVerticalGroup(
            puPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        panelPestañas.addTab("ppos_upos", puPanel);

        spTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posicion", "SgtPos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(spTable);

        javax.swing.GroupLayout spPanelLayout = new javax.swing.GroupLayout(spPanel);
        spPanel.setLayout(spPanelLayout);
        spPanelLayout.setHorizontalGroup(
            spPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        spPanelLayout.setVerticalGroup(
            spPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        panelPestañas.addTab("sgtpos", spPanel);

        trandTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(trandTable);

        javax.swing.GroupLayout trandPanelLayout = new javax.swing.GroupLayout(trandPanel);
        trandPanel.setLayout(trandPanelLayout);
        trandPanelLayout.setHorizontalGroup(
            trandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trandPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trandPanelLayout.setVerticalGroup(
            trandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trandPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelPestañas.addTab("TranD", trandPanel);

        jLabel1.setText("Expresion regular:");

        jLabel2.setText("Cadena a verificar:");

        arbolButton.setText("Arbol Sintactico");
        arbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbolButtonActionPerformed(evt);
            }
        });

        verficarButton.setText("Verificar");
        verficarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verficarButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Alfabeto:");

        alfaLabel.setText("Vacio");

        reiniciarButton.setText("Reiniciar");
        reiniciarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(regexTF, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(arbolButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cadenaTF)
                                    .addComponent(alfaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(reiniciarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(verficarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(lienzoJSP, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lienzoJSP, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(regexTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arbolButton))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(alfaLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cadenaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(verficarButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reiniciarButton)
                        .addGap(9, 9, 9)
                        .addComponent(panelPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbolButtonActionPerformed
        String er = regexTF.getText();
        if(!er.isBlank()){
            resetValores();
            regexTF.setText(er);
            st = new ArbolSintactico(er);
            st.crearArbol();
            st.calculoPosiciones();
            st.inOrden();
            afd = new AFD(st, er);
            afd.crearAFD();
            setValores();
        }
    }//GEN-LAST:event_arbolButtonActionPerformed

    private void verficarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verficarButtonActionPerformed
        String cadena = cadenaTF.getText();
        if(!cadena.isBlank() && afd != null){
            if(afd.reconoceCadena(cadena)) JOptionPane.showMessageDialog(null, "Se ha reconocido la cadena.");
            else JOptionPane.showMessageDialog(null, "No se ha reconocido la cadena.");
        }
    }//GEN-LAST:event_verficarButtonActionPerformed

    private void reiniciarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarButtonActionPerformed
        resetValores();
    }//GEN-LAST:event_reiniciarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alfaLabel;
    private javax.swing.JButton arbolButton;
    private javax.swing.JTextField cadenaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane lienzoJSP;
    private javax.swing.JTabbedPane panelPestañas;
    private javax.swing.JPanel puPanel;
    private javax.swing.JTable puTable;
    private javax.swing.JTextField regexTF;
    private javax.swing.JButton reiniciarButton;
    private javax.swing.JPanel spPanel;
    private javax.swing.JTable spTable;
    private javax.swing.JPanel trandPanel;
    private javax.swing.JTable trandTable;
    private javax.swing.JButton verficarButton;
    // End of variables declaration//GEN-END:variables
}
