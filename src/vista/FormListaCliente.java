/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ClienteControlador;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.dao.ClienteDAO;
import modelo.dao.HabitacionDAO;
import static vista.FormPrincipal.ventanaprincipal;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Dante_Sanchez
 */
public class FormListaCliente extends javax.swing.JFrame {


    /**
     * Creates new form FormListaHabitacion
     */
    public FormListaCliente() {
        initComponents();
        mostrar("");
        this.setLocationRelativeTo(null);
    }

    
         void ocultar_columnas() {
             
        this.tablalistacliente.getColumnModel().getColumn(0).setMaxWidth(0);
        this.tablalistacliente.getColumnModel().getColumn(0).setMinWidth(0);
        this.tablalistacliente.getColumnModel().getColumn(0).setPreferredWidth(0);
      
    }
     
     void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            ClienteDAO func = new ClienteDAO();
            modelo = func.mostrar(buscar);

            tablalistacliente.setModel(modelo);
            ocultar_columnas();
            //lbltotalregistros.setText(Integer.toString(func.total) + " Clientes Registrados.");
            lbltotalregistros.setText("(" + Integer.toString(func.total) + " Clientes Registrados.)" );

        } catch (Exception e) {
            System.out.println("error" + e);
            JOptionPane.showConfirmDialog(rootPane, e);
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

        ImageIcon icon  = new ImageIcon(getClass().getResource("/imagenes/form_listacliente.jpg"));
        Image image = icon.getImage();
        jPanel2 = new javax.swing.JPanel(){
            public void  paintComponent(Graphics g){

                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jScrollPane3 = new javax.swing.JScrollPane();
        tablalistacliente = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        lbltotalregistros = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablalistacliente.setBackground(new java.awt.Color(255, 102, 102));
        tablalistacliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tablalistacliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablalistacliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablalistaclienteMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablalistaclienteMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tablalistacliente);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Buscar por Nombre :");

        txtbuscar.setBackground(new java.awt.Color(204, 204, 204));
        txtbuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtbuscar.setForeground(new java.awt.Color(51, 51, 51));

        btnbuscar.setBackground(new java.awt.Color(51, 51, 51));
        btnbuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 51));
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnbuscar1.setBackground(new java.awt.Color(51, 51, 51));
        btnbuscar1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnbuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar1.setText("Añadir Cliente Nuevo");
        btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1ActionPerformed(evt);
            }
        });

        lbltotalregistros.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbltotalregistros.setForeground(new java.awt.Color(255, 255, 255));
        lbltotalregistros.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbltotalregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltotalregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar1))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablalistaclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistaclienteMouseClicked

    }//GEN-LAST:event_tablalistaclienteMouseClicked

    private void tablalistaclienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistaclienteMousePressed
        if (evt.getClickCount() == 2) {
            int fila= tablalistacliente.getSelectedRow();
            String cod;
            String valor;
            
            cod=tablalistacliente.getValueAt(fila, 0).toString();
            valor=tablalistacliente.getValueAt(fila, 1).toString();
            
            
            
            FormReservacion.txtidcliente.setText(cod);
            FormReservacion.txtcliente.setText(valor);
            
            this.dispose();
            
        }
    }//GEN-LAST:event_tablalistaclienteMousePressed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        mostrar(txtbuscar.getText());
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        // TODO add your handling code here:
        Cliente modeloCliente = new Cliente();
        ClienteDAO modeloClienteDAO = new ClienteDAO();
        FormCliente vistaCliente = new FormCliente();
        
        ClienteControlador controladorCliente = new ClienteControlador(modeloCliente, modeloClienteDAO, vistaCliente);
        controladorCliente.iniciar();
        //FormCliente cliente= new FormCliente();
        CentrarVentanaInterna(vistaCliente);
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    
        public void CentrarVentanaInterna(JInternalFrame internalFrame) {
        
        int x = (ventanaprincipal.getWidth() / 2) - internalFrame.getWidth() /2;
        int y = (ventanaprincipal.getHeight()/ 2) - internalFrame.getHeight()/2;
        
        if (internalFrame.isShowing()) {
            internalFrame.setLocation(x, y);
            
        }else {
            ventanaprincipal.add(internalFrame);
            internalFrame.setLocation(x, y);
            internalFrame.show();
        }
        
    }
    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JTable tablalistacliente;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
