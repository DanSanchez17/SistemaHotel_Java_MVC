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
import modelo.Empleado;
import modelo.dao.ClienteDAO;
import modelo.dao.EmpleadoDAO;
import vista.FormEmpleado;

/**
 *
 * @author Dante_Sanchez
 */

//AQUI CONECTA MI VITA-MODELO PARA PASAR INSTRUCCIONES
//Asigno listener para que escuche los clics en botones y las acciones de la vista
//implemento ActionListener
public class EmpleadoControlador implements ActionListener {
    
    //creo mis variables de tipo de las calses modelo-vsta
    private Empleado modeloEmpleado;
    private EmpleadoDAO modeloEmpleadoDAO;
    private FormEmpleado vistaEmpleado;
    
    //creo mi constructor que tendra de parametros mis variables de tipo modelo-vista
    public EmpleadoControlador(Empleado modeloEmpleado,EmpleadoDAO modeloEmpleadoDAO,FormEmpleado vistaEmpleado) {
        
        //igualo los valores
        this.modeloEmpleado = modeloEmpleado;
        this.modeloEmpleadoDAO = modeloEmpleadoDAO;
        this.vistaEmpleado = vistaEmpleado;
        
        //añado los listener a los botones
        this.vistaEmpleado.btnbuscar.addActionListener(this);
        this.vistaEmpleado.btneliminar.addActionListener(this);
        this.vistaEmpleado.btnguardar.addActionListener(this);
        this.vistaEmpleado.btnlimpiar.addActionListener(this);
        this.vistaEmpleado.btnmodificar.addActionListener(this);
        
        
    }

    EmpleadoControlador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //creo un metodo de iniciar
    public void iniciar(){
        this.vistaEmpleado.txtidpersona.setVisible(false);
        vistaEmpleado.cbopuesto.setSelectedItem(null);
        mostrar("");
        
   }
    
    
    public void actionPerformed(ActionEvent e) {
        
    //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaEmpleado.btnguardar) {
            
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
            modeloEmpleado.setNombre(vistaEmpleado.txtnombre.getText());
            modeloEmpleado.setPaterno(vistaEmpleado.txtpaterno.getText());
            modeloEmpleado.setMaterno(vistaEmpleado.txtmaterno.getText());
            modeloEmpleado.setTelefono(Integer.parseInt(vistaEmpleado.txttelefono.getText()));
            modeloEmpleado.setCorreo(vistaEmpleado.txtcorreo.getText());
            modeloEmpleado.setPuesto((String) vistaEmpleado.cbopuesto.getSelectedItem());
            modeloEmpleado.setSueldo(Double.parseDouble(vistaEmpleado.txtsueldo.getText()));
            
            
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloEmpleadoDAO.guardarEmpleado(modeloEmpleado)) {
                
                JOptionPane.showMessageDialog(null, "Empleado registrado con Exito!");
                limpiar();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar empleado");
            }
            
        }
        
        
        if (e.getSource()==vistaEmpleado.btnmodificar) {
            
            modeloEmpleado.setIdpersona(Integer.parseInt(vistaEmpleado.txtidpersona.getText()));
            
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
            modeloEmpleado.setNombre(vistaEmpleado.txtnombre.getText());
            modeloEmpleado.setPaterno(vistaEmpleado.txtpaterno.getText());
            modeloEmpleado.setMaterno(vistaEmpleado.txtmaterno.getText());
            modeloEmpleado.setTelefono(Integer.parseInt(vistaEmpleado.txttelefono.getText()));
            modeloEmpleado.setCorreo(vistaEmpleado.txtcorreo.getText());
            modeloEmpleado.setPuesto((String)vistaEmpleado.cbopuesto.getSelectedItem());
            modeloEmpleado.setSueldo(Double.parseDouble(vistaEmpleado.txtsueldo.getText()));
            
            if(modeloEmpleadoDAO.modificarEmpleado(modeloEmpleado)) {
                
                JOptionPane.showMessageDialog(null, "Empleado Actualizado con Exito");
                //metodo limpiar
                limpiar();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
            
            
        }
        
        
        if (e.getSource()==vistaEmpleado.btneliminar) {
            
            //aqui solo necesito mi idpersona para eliminarlo
            modeloEmpleado.setIdpersona(Integer.parseInt(vistaEmpleado.txtidpersona.getText()));
            
            if (modeloEmpleadoDAO.eliminarEmpleado(modeloEmpleado)) {
                
                JOptionPane.showMessageDialog(null, "Empleado eliminado con Exito");
                limpiar();
                mostrar("");
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar empleado");
                limpiar();
                mostrar("");
            }
            
        }
        
        
        if (e.getSource()==vistaEmpleado.btnbuscar) {
            
            //buscamos por codigoempleado
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
            
            if (modeloEmpleadoDAO.buscarEmpleado(modeloEmpleado)) {
                
                //si encuentra el registro lo pintamos en nuestras cajas de texto
                vistaEmpleado.txtidpersona.setText(String.valueOf(modeloEmpleado.getIdpersona()));
                vistaEmpleado.txtnombre.setText(modeloEmpleado.getNombre());
                vistaEmpleado.txtpaterno.setText(modeloEmpleado.getPaterno());
                vistaEmpleado.txtmaterno.setText(modeloEmpleado.getMaterno());
                vistaEmpleado.txttelefono.setText(String.valueOf(modeloEmpleado.getTelefono()));
                vistaEmpleado.txtcorreo.setText(modeloEmpleado.getCorreo());
                vistaEmpleado.cbopuesto.setSelectedItem(modeloEmpleado.getPuesto());
                vistaEmpleado.txtsueldo.setText(String.valueOf(modeloEmpleado.getSueldo()));
                
                
                //mostramos mensaje de empleado encontrado o no
                JOptionPane.showMessageDialog(null, "Empleado encontrado");
                              
            }else {
                JOptionPane.showMessageDialog(null, "No se encontro este empleado");
                //limpiamos si no encontramos nada
                limpiar();
            }
            
                                  
        }
        
         if (e.getSource()==vistaEmpleado.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
            }
        
        
    }
    
    
    //creamos metodo que limpia las cajas de texto en ventana FormEmpleado
    public void limpiar(){
        
        vistaEmpleado.txtcodigodeempleado.setText(null);
        vistaEmpleado.txtnombre.setText(null);
        vistaEmpleado.txtpaterno.setText(null);
        vistaEmpleado.txtmaterno.setText(null);
        vistaEmpleado.txttelefono.setText(null);
        vistaEmpleado.txtcorreo.setText(null);
        // poner bien para un cbo box vistaEmpleado.cbopuesto.setSelectedItem(null);
        vistaEmpleado.txtsueldo.setText(null);
        vistaEmpleado.cbopuesto.setSelectedItem(null);
                        
    }
    
        void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            EmpleadoDAO func = new EmpleadoDAO();
            modelo = func.mostrar(buscar);
            
            vistaEmpleado.tablaempleado.setModel(modelo);
            ocultar_columnas();
            
                        
        } catch (Exception e) {
        }
    }
    
        void ocultar_columnas() {
        vistaEmpleado.tablaempleado.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaEmpleado.tablaempleado.getColumnModel().getColumn(0).setMinWidth(0);
        vistaEmpleado.tablaempleado.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    
}
