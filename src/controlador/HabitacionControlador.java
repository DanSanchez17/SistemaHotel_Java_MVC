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
import modelo.Habitacion;
import modelo.dao.EmpleadoDAO;
import modelo.dao.HabitacionDAO;
import vista.FormHabitacion;



/**
 *
 * @author Dante_Sanchez
 */
public class HabitacionControlador implements ActionListener {
 
    private Habitacion modeloHabitacion;
    private HabitacionDAO modeloHabitacionDAO;
    private FormHabitacion vistaHabitacion;
    
        public HabitacionControlador(Habitacion modeloHabitacion,HabitacionDAO modeloHabitacionDAO,FormHabitacion vistaHabitacion){
        
        //igualamos los valores    
        this.modeloHabitacion = modeloHabitacion;
        this.modeloHabitacionDAO = modeloHabitacionDAO;
        this.vistaHabitacion = vistaHabitacion;
        
        
        //iniciamos  los botones
        this.vistaHabitacion.btnbuscar.addActionListener(this);
        this.vistaHabitacion.btneliminar.addActionListener(this);
        this.vistaHabitacion.btnguardar.addActionListener(this);
        this.vistaHabitacion.btnlimpiar.addActionListener(this);
        this.vistaHabitacion.btnmodificar.addActionListener(this);
    }
        
        
        public void iniciar(){
      
            
        this.vistaHabitacion.cbocategoria.setSelectedItem(null);
        this.vistaHabitacion.cboestado.setSelectedItem(null);
        this.vistaHabitacion.cbopiso.setSelectedItem(null);
        this.vistaHabitacion.cbotipocama.setSelectedItem(null);
        
        mostrar("");
        
        this.vistaHabitacion.btnbuscar.setVisible(false);
        this.vistaHabitacion.txtidhabitacion.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaHabitacion.btnguardar) {
            
         
            modeloHabitacion.setNumero(Integer.parseInt(vistaHabitacion.txtnumero.getText()));  
            modeloHabitacion.setPiso((String) vistaHabitacion.cbopiso.getSelectedItem());
            modeloHabitacion.setTipocama((String) vistaHabitacion.cbotipocama.getSelectedItem());
            modeloHabitacion.setCategoria((String) vistaHabitacion.cbocategoria.getSelectedItem());
            modeloHabitacion.setEstado((String) vistaHabitacion.cboestado.getSelectedItem());
            modeloHabitacion.setPreciodia(Double.parseDouble(vistaHabitacion.txtprecio.getText()));
            modeloHabitacion.setCaracteristicas(vistaHabitacion.txtcaracteristicas.getText());
        
            
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloHabitacionDAO.guardarHabitacion(modeloHabitacion)) {
                
                JOptionPane.showMessageDialog(null, "Habitación registrada con Exito!");
                limpiar();
                mostrar("");
               
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar Habitación");
                limpiar();
            }
            
        }
        
        
        if (e.getSource()==vistaHabitacion.btnmodificar) {
            
         
            //tomamos el valor del idhabitacion
            modeloHabitacion.setIdhabitacion(Integer.parseInt(vistaHabitacion.txtidhabitacion.getText()));  
            
            modeloHabitacion.setNumero(Integer.parseInt(vistaHabitacion.txtnumero.getText()));  
            modeloHabitacion.setPiso((String) vistaHabitacion.cbopiso.getSelectedItem());
            modeloHabitacion.setTipocama((String) vistaHabitacion.cbotipocama.getSelectedItem());
            modeloHabitacion.setCategoria((String) vistaHabitacion.cbocategoria.getSelectedItem());
            modeloHabitacion.setEstado((String) vistaHabitacion.cboestado.getSelectedItem());
            modeloHabitacion.setPreciodia(Double.parseDouble(vistaHabitacion.txtprecio.getText()));
            modeloHabitacion.setCaracteristicas(vistaHabitacion.txtcaracteristicas.getText());
            

            
            
            if(modeloHabitacionDAO.modificarHabitacion(modeloHabitacion)) {
                
                JOptionPane.showMessageDialog(null, "Habitación Actualizada con Exito");
                //metodo limpiar
                limpiar();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar Habitación");
                limpiar();
            }
            
            
        }
        
        
        if (e.getSource()==vistaHabitacion.btneliminar) {
            
            //aqui solo necesito mi idpersona para eliminarlo
            modeloHabitacion.setIdhabitacion(Integer.parseInt(vistaHabitacion.txtidhabitacion.getText())); 

            
            if (modeloHabitacionDAO.eliminarHabitacion(modeloHabitacion)) {
                
                JOptionPane.showMessageDialog(null, "Habitación eliminada con Exito");
                limpiar();
                mostrar("");
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la Habitación");
                limpiar();
                mostrar("");
            }
            
        }
        
        /*
        if (e.getSource()==vista_XXX.btnbuscar) {
            
            //buscamos por codigoempleado
           //ejemplo
            //modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
             
            
            if (modelo__XX__DAO.buscar__XXX(modelo__XX)) {
                
                //si encuentra el registro lo pintamos en nuestras cajas de texto
                //ejemplo
                  //  vistaEmpleado.txtidpersona.setText(String.valueOf(modeloEmpleado.getIdpersona()));
                    //vistaEmpleado.txtnombre.setText(modeloEmpleado.getNombre());
                    //vistaEmpleado.cbopuesto.setSelectedItem(modeloEmpleado.getPuesto());
                
               

                
                
                //mostramos mensaje de empleado encontrado o no
                JOptionPane.showMessageDialog(null, "XXX encontrado");
                              
            }else {
                JOptionPane.showMessageDialog(null, "No se encontro XXXX");
                //limpiamos si no encontramos nada
                limpiar();
            }
            
                                  
        }   */
        
         if (e.getSource()==vistaHabitacion.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
            }
        
    }
    
    
        public void limpiar() {
        
        vistaHabitacion.cbocategoria.setSelectedItem(null);
        vistaHabitacion.cboestado.setSelectedItem(null);
        vistaHabitacion.cbopiso.setSelectedItem(null);
        vistaHabitacion.cbotipocama.setSelectedItem(null);
        
        vistaHabitacion.txtnumero.setText(null);
        vistaHabitacion.txtcaracteristicas.setText(null);
        vistaHabitacion.txtprecio.setText(null);
        
        
        }
    
  public void limpiartodo() {
      
              /*
            vistaAdmin.txtnombre.setText(null);
            vistaAdmin.cboacceso.setSelectedItem(null);
            */   

  }
  
  
   void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            HabitacionDAO func = new HabitacionDAO();
            modelo = func.mostrar(buscar);
            
            vistaHabitacion.tablahabitacion.setModel(modelo);
            ocultar_columnas();
            
                        
        } catch (Exception e) {
        }
    }
    
        void ocultar_columnas() {
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(0).setMinWidth(0);
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(5).setMaxWidth(0);
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(5).setMinWidth(0);
        vistaHabitacion.tablahabitacion.getColumnModel().getColumn(5).setPreferredWidth(0);
    }
    
    
    
    
    
}// fin de clase Controlador
