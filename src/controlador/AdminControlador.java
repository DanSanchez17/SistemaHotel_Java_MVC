/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.dao.AdministradorDAO;
import modelo.Empleado;
import modelo.dao.ClienteDAO;
import modelo.dao.EmpleadoDAO;
import vista.FormAdministrador;

/**
 *
 * @author Dante_Sanchez
 */

//COMUNICACION ENTRE VISTA-MODELO
public class AdminControlador implements ActionListener{
    
    private Administrador modeloAdmin;
    private Empleado modeloEmpleado;
    private AdministradorDAO modeloAdminDAO;
    private FormAdministrador vistaAdmin;
    private EmpleadoDAO modeloEmpleadoDAO;

    
    public AdminControlador(Empleado modeloEmpleado,EmpleadoDAO modeloEmpleadoDAO,Administrador modeloAdmin, AdministradorDAO modeloAdminDAO,FormAdministrador vistaAdmin){
        
        this.modeloEmpleado = modeloEmpleado;
        this.modeloEmpleadoDAO = modeloEmpleadoDAO;
        this.modeloAdmin = modeloAdmin;
        this.modeloAdminDAO = modeloAdminDAO;
        this.vistaAdmin = vistaAdmin;
        
        
        //iniciamos  los botones
        this.vistaAdmin.btnbuscar.addActionListener(this);
        this.vistaAdmin.btneliminar.addActionListener(this);
        this.vistaAdmin.btnguardar.addActionListener(this);
        this.vistaAdmin.btnlimpiar.addActionListener(this);
        this.vistaAdmin.btnmodificar.addActionListener(this);
    }
    
    
    public void iniciar(){
        this.vistaAdmin.cboacceso.setSelectedItem(null);
        mostrar("");
        mostrar2("");
        this.vistaAdmin.txtidadministrador.setVisible(false);
        this.vistaAdmin.txtidempleado.setVisible(false);
    }
    

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()== vistaAdmin.btnbuscar) {
            
              //buscamos por codigoempleado
              
           modeloEmpleado.setCodigoempleado(vistaAdmin.txtcodigoempleado.getText());
           // modeloAdmin.setIdadministradores(Integer.parseInt(vistaAdmin.txtidadministrador.getText()));
           
           modeloAdmin.setCodigoempleado(vistaAdmin.txtcodigoempleado.getText());
            
            
            
            if (modeloAdminDAO.buscarAdministrador(modeloAdmin) ) {
                
                //si encuentra el registro lo pintamos en nuestras cajas de texto
                //vistaAdmin.txtidempleado.setText(String.valueOf(modeloEmpleado.getIdpersona()));
                
                limpiar();
                mostrar("");
                
             vistaAdmin.txtidempleado.setText(String.valueOf(modeloAdmin.getIdpersona()));
             //vistaEmpleado.txtidpersona.setText(String.valueOf(modeloEmpleado.getIdpersona()));
             vistaAdmin.txtidadministrador.setText(String.valueOf(modeloAdmin.getIdadministradores()));
                
                vistaAdmin.txtnombre.setText(modeloAdmin.getNombre());
                vistaAdmin.txtpaterno.setText(modeloAdmin.getPaterno());
                vistaAdmin.txtpuesto.setText(modeloAdmin.getPuesto());
                vistaAdmin.cboacceso.setSelectedItem(modeloAdmin.getAcceso());         
                vistaAdmin.txtusuario.setText(modeloAdmin.getUsuario());
                vistaAdmin.txtpassword.setText(modeloAdmin.getPassword());
                
                
               
             
                //busca si existe registro de un administrador
                /*
               vistaAdmin.txtusuario.setText(modeloAdmin.getUsuario());
                vistaAdmin.txtpassword.setText(modeloAdmin.getPassword());
                vistaAdmin.cboacceso.setSelectedItem(modeloAdmin.getAcceso());
                
                */
          
                
                
                //mostramos mensaje de empleado encontrado o no
                JOptionPane.showMessageDialog(null, "Empleado encontrado");
                              
            }  else if(modeloEmpleadoDAO.buscarEmpleado(modeloEmpleado)) {
                
                limpiar();
                mostrar("");
                
                vistaAdmin.txtnombre.setText(modeloEmpleado.getNombre());
                vistaAdmin.txtpaterno.setText(modeloEmpleado.getPaterno());
                vistaAdmin.txtpuesto.setText(modeloEmpleado.getPuesto());
                vistaAdmin.txtusuario.setText(modeloEmpleado.getNombre()+ "_" + modeloEmpleado.getPaterno());
                vistaAdmin.txtidempleado.setText(String.valueOf(modeloEmpleado.getIdpersona()));
                
                
                
            }                   
            else {
                
                
                JOptionPane.showMessageDialog(null, "No se encontro este administrador");
                //limpiamos si no encontramos nada
                limpiar();
            }
            
        }
        
        
        
        
        if(e.getSource()==vistaAdmin.btnguardar) {
            
            
            modeloAdmin.setAcceso((String) vistaAdmin.cboacceso.getSelectedItem());
            modeloAdmin.setUsuario(vistaAdmin.txtusuario.getText());
            modeloAdmin.setPassword(String.valueOf(vistaAdmin.txtpassword.getPassword()));
            modeloAdmin.setIdempleado(Integer.parseInt(vistaAdmin.txtidempleado.getText()));
    
            
            
                                 
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloAdminDAO.guardarAdministrador(modeloAdmin)) {
                
                JOptionPane.showMessageDialog(null, "Empleado registrado con Exito!");
                limpiartodo();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar empleado");
            }
        }
        
        
        if(e.getSource()==vistaAdmin.btnmodificar) {
            
            modeloAdmin.setIdadministradores(Integer.parseInt(vistaAdmin.txtidadministrador.getText()));
                    
            modeloAdmin.setAcceso((String) vistaAdmin.cboacceso.getSelectedItem());
            modeloAdmin.setUsuario(vistaAdmin.txtusuario.getText());
            modeloAdmin.setPassword(String.valueOf(vistaAdmin.txtpassword.getPassword()));
            modeloAdmin.setIdempleado(Integer.parseInt(vistaAdmin.txtidempleado.getText()));
                                                    
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloAdminDAO.modificarAdministrador(modeloAdmin)) {
                
                JOptionPane.showMessageDialog(null, "Administrador editado con Exito!");
                limpiartodo();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar administrador");
            }
        }
        
        if (e.getSource()==vistaAdmin.btneliminar) {
            
            //aqui solo necesito mi idadministradores para eliminarlo
            modeloAdmin.setIdadministradores(Integer.parseInt(vistaAdmin.txtidadministrador.getText()));
            
            if (modeloAdminDAO.eliminarAdministrador(modeloAdmin)) {
                
                JOptionPane.showMessageDialog(null, "Empleado eliminado con Exito");
                limpiartodo();
                mostrar("");
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar empleado");
                limpiartodo();
            }
            
        }
        
        
        if (e.getSource()==vistaAdmin.btnlimpiar) {
            limpiartodo();
            
        }
        
    }
    
    
    public void limpiar() {
        
        vistaAdmin.txtnombre.setText(null);
        vistaAdmin.txtpaterno.setText(null);
        vistaAdmin.txtpuesto.setText(null);
        vistaAdmin.cboacceso.setSelectedItem(null);
        vistaAdmin.txtusuario.setText(null);
        vistaAdmin.txtpassword.setText(null);
        vistaAdmin.txtidadministrador.setText(null);
        vistaAdmin.txtidempleado.setText(null);
        
        
    }
    
  public void limpiartodo() {
        vistaAdmin.txtnombre.setText(null);
        vistaAdmin.txtpaterno.setText(null);
        vistaAdmin.txtpuesto.setText(null);
        vistaAdmin.cboacceso.setSelectedItem(null);
        vistaAdmin.txtusuario.setText(null);
        vistaAdmin.txtpassword.setText(null);
        vistaAdmin.txtidadministrador.setText(null);
        vistaAdmin.txtidempleado.setText(null);
        vistaAdmin.txtcodigoempleado.setText(null);
  }
  
  
      void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            AdministradorDAO func = new AdministradorDAO();
            modelo = func.mostrar(buscar);
            
            vistaAdmin.tablaadmin.setModel(modelo);
            ocultar_columnas();
            
                        
        } catch (Exception e) {
        }
    }
    
        void ocultar_columnas() {
        vistaAdmin.tablaadmin.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(0).setMinWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaAdmin.tablaadmin.getColumnModel().getColumn(1).setMaxWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(1).setMinWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        vistaAdmin.tablaadmin.getColumnModel().getColumn(4).setMaxWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(4).setMinWidth(0);
        vistaAdmin.tablaadmin.getColumnModel().getColumn(4).setPreferredWidth(0);
    }
        
          void mostrar2(String buscar) {
        try {
            DefaultTableModel modelo;
            EmpleadoDAO func = new EmpleadoDAO();
            modelo = func.mostrar(buscar);
            
           // ocultar_columnas();
            vistaAdmin.tablaempleado.setModel(modelo);
                        
        } catch (Exception e) {
        }
    }
  
  
  
    
}
