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
import modelo.Servicio;
import modelo.dao.HabitacionDAO;
import modelo.dao.ServicioDAO;
import vista.FormServicio;

/**
 *
 * @author Dante_Sanchez
 */
public class ServicioControlador implements ActionListener {
 
    private Servicio modeloServicio;
    private ServicioDAO modeloServicioDAO;
    private FormServicio vistaServicio;
    
    
    
        public ServicioControlador(Servicio modeloServicio,ServicioDAO modeloServicioDAO,FormServicio vistaServicio){
        
        //igualamos los valores    
        this.modeloServicio = modeloServicio;
        this.modeloServicioDAO = modeloServicioDAO;
        this.vistaServicio = vistaServicio;
        
        
        //iniciamos  los botones
        this.vistaServicio.btnbuscar.addActionListener(this);
        this.vistaServicio.btneliminar.addActionListener(this);
        this.vistaServicio.btnguardar.addActionListener(this);
        this.vistaServicio.btnlimpiar.addActionListener(this);
        this.vistaServicio.btnmodificar.addActionListener(this);
    }
        
        
        public void iniciar(){
       this.vistaServicio.txtidservicio.setVisible(false);
       this.vistaServicio.btnbuscar.setVisible(false);
       mostrar("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaServicio.btnguardar) {
            
            
            modeloServicio.setNombre(vistaServicio.txtnombreservicio.getText());
            modeloServicio.setPrecioservicio(Integer.parseInt(vistaServicio.txtprecio.getText()));
            modeloServicio.setDescripcion(vistaServicio.txtdescripcion.getText());

            
            
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloServicioDAO.guardarServicio(modeloServicio)) {
                
                JOptionPane.showMessageDialog(null, "Servicio Extra registrado con Exito!");
                limpiar();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar Servicio Extra");
            }
            
        }
        
        
        if (e.getSource()==vistaServicio.btnmodificar) {
            
            //idservicio
            modeloServicio.setIdservicio(Integer.parseInt(vistaServicio.txtidservicio.getText()));
            
            modeloServicio.setNombre(vistaServicio.txtnombreservicio.getText());
            modeloServicio.setPrecioservicio(Double.parseDouble(vistaServicio.txtprecio.getText()));
            modeloServicio.setDescripcion(vistaServicio.txtdescripcion.getText());
            
            
            if(modeloServicioDAO.modificarServicio(modeloServicio)) {
                
                JOptionPane.showMessageDialog(null, "Servicio Extra Actualizado con Exito");
                //metodo limpiar
                limpiar();
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar Servicio Extra");
                limpiar();
            }
            
            
        }
        
        
        if (e.getSource()==vistaServicio.btneliminar) {
            
            //aqui solo necesito mi idservicio para eliminarlo
            modeloServicio.setIdservicio(Integer.parseInt(vistaServicio.txtidservicio.getText()));
            
            if (modeloServicioDAO.eliminarServicio(modeloServicio)) {
                
                JOptionPane.showMessageDialog(null, "Servicio Extra eliminado con Exito");
                limpiar();
                mostrar("");
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Servicio Extra");
                limpiar();
                mostrar("");
            }
            
        }
        
        /*
        if (e.getSource()==vista_XXX.btnbuscar) {
            
            //buscamos por codigoempleado
           /*ejemplo
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
             
            
            if (modelo__XX__DAO.buscar__XXX(modelo__XX)) {
                
                //si encuentra el registro lo pintamos en nuestras cajas de texto
                /*ejemplo
                    vistaEmpleado.txtidpersona.setText(String.valueOf(modeloEmpleado.getIdpersona()));
                    vistaEmpleado.txtnombre.setText(modeloEmpleado.getNombre());
                    vistaEmpleado.cbopuesto.setSelectedItem(modeloEmpleado.getPuesto());
                
                

                
                
                //mostramos mensaje de empleado encontrado o no
                JOptionPane.showMessageDialog(null, "XXX encontrado");
                              
            }else {
                JOptionPane.showMessageDialog(null, "No se encontro XXXX");
                //limpiamos si no encontramos nada
                limpiar();
            }
            
                                  
        }   */
        
        
        
         if (e.getSource()==vistaServicio.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
            }
        
    }
    
    
        public void limpiar() {
          
        vistaServicio.txtidservicio.setText(null);
        vistaServicio.txtnombreservicio.setText(null);
        vistaServicio.txtprecio.setText(null);
        vistaServicio.txtdescripcion.setText(null);
        
    }
    
  public void limpiartodo() {
      
              /*
            vistaAdmin.txtnombre.setText(null);
            vistaAdmin.cboacceso.setSelectedItem(null);
            */   

  }
  
   public void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            ServicioDAO func = new ServicioDAO();
            modelo = func.mostrar(buscar);
            
            vistaServicio.tablaservicio.setModel(modelo);
            ocultar_columnas();
            
                        
        } catch (Exception e) {
        }
    }
    
        void ocultar_columnas() {
        vistaServicio.tablaservicio.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaServicio.tablaservicio.getColumnModel().getColumn(0).setMinWidth(0);
        vistaServicio.tablaservicio.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    
    
    
    
}// fin de clase Controlador