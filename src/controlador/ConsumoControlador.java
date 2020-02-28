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
import modelo.Consumo;
import modelo.dao.ConsumoDAO;
import modelo.dao.HabitacionDAO;
import vista.FormConsumos;

/**
 *
 * @author Dante_Sanchez
 */
public class ConsumoControlador implements ActionListener {
 
    public static String idreserva;
    public static String cliente;

    private Consumo modeloConsumo;
    private ConsumoDAO modeloConsumoDAO;
    private FormConsumos vistaConsumo;
    
        public ConsumoControlador(Consumo modeloConsumo,ConsumoDAO modeloConsumoDAO,FormConsumos vistaConsumo){
        
        //igualamos los valores    
        this.modeloConsumo = modeloConsumo;
        this.modeloConsumoDAO = modeloConsumoDAO;
        this.vistaConsumo = vistaConsumo;
        
        
        //iniciamos  los botones
        this.vistaConsumo.btnbuscar.addActionListener(this);
        this.vistaConsumo.btneliminar.addActionListener(this);
        this.vistaConsumo.btnguardar.addActionListener(this);
        this.vistaConsumo.btnlimpiar.addActionListener(this);
        this.vistaConsumo.btnmodificar.addActionListener(this);
    }
        
        
        public void iniciar(){
       
         
        mostrar(idreserva);
        vistaConsumo.txtcliente.setText(cliente);
        vistaConsumo.txtidreserva.setText(idreserva);
        
        vistaConsumo.txtidconsumo.setVisible(false);
        vistaConsumo.txtidreserva.setVisible(false);
        vistaConsumo.txtidservicio.setVisible(false);
        
        vistaConsumo.btnbuscar.setVisible(false);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaConsumo.btnguardar) {
            
            
            modeloConsumo.setIdreservacion(Integer.parseInt(vistaConsumo.txtidreserva.getText()));
            modeloConsumo.setIdservicio(Integer.parseInt(vistaConsumo.txtidservicio.getText()));
            modeloConsumo.setCantidad(Double.parseDouble(vistaConsumo.txtcantidad.getText()));
            modeloConsumo.setPrecioventa(Double.parseDouble(vistaConsumo.txtprecio.getText()));
                  
           
                     
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloConsumoDAO.guardarConsumo(modeloConsumo)) {
                
                //JOptionPane.showMessageDialog(null, "Consumo registrado con Exito!");
                limpiar();
                mostrar(idreserva);
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar Consumo");
            }
            
        }
        
        
        if (e.getSource()==vistaConsumo.btnmodificar) {
            
            //agrego idconsumo
            modeloConsumo.setIdconsumo(Integer.parseInt(vistaConsumo.txtidconsumo.getText()));
            
            modeloConsumo.setIdreservacion(Integer.parseInt(vistaConsumo.txtidreserva.getText()));
            modeloConsumo.setIdservicio(Integer.parseInt(vistaConsumo.txtidservicio.getText()));
            modeloConsumo.setCantidad(Double.parseDouble(vistaConsumo.txtcantidad.getText()));
            modeloConsumo.setPrecioventa(Double.parseDouble(vistaConsumo.txtprecio.getText()));
            
            
            if(modeloConsumoDAO.modificarConsumo(modeloConsumo)) {
                
                JOptionPane.showMessageDialog(null, "Consumo Actualizado con Exito");
                //metodo limpiar
                limpiar();
                mostrar(idreserva);
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar Consumo");
                limpiar();
                mostrar(idreserva);
            }
            
            
        }
        
        
        if (e.getSource()==vistaConsumo.btneliminar) {
            
            //aqui solo necesito mi idconsumo para eliminarlo
            modeloConsumo.setIdconsumo(Integer.parseInt(vistaConsumo.txtidconsumo.getText()));

            
            if (modeloConsumoDAO.eliminarConsumo(modeloConsumo)) {
                
                JOptionPane.showMessageDialog(null, "Consumo eliminado con Exito");
                limpiar();
                mostrar(idreserva);
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Consumo");
                limpiar();
                mostrar(idreserva);
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
            
                                  
        }  */
        
         if (e.getSource()==vistaConsumo.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
            }
        
    }
    
    
        public void limpiar() {
        
        vistaConsumo.txtidconsumo.setText(null);
        //vistaConsumo.txtcliente.setText(null);
        //vistaConsumo.txtidreserva.setText(null);
        vistaConsumo.txtservicio.setText(null);
        vistaConsumo.txtidservicio.setText(null);
        vistaConsumo.txtcantidad.setText(null);
        vistaConsumo.txtprecio.setText(null);
        
 
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
            ConsumoDAO func = new ConsumoDAO();
            modelo = func.mostrar(buscar);

           vistaConsumo.tablaconsumos.setModel(modelo);
            ocultar_columnas();
            vistaConsumo.lbltotalregistros1.setText("Consumos : " + Integer.toString(func.totalregistros));
            vistaConsumo.lblconsumo1.setText("Total de consumos : $" + func.totalconsumo);
        } catch (Exception e) {
            //JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
        void ocultar_columnas() {
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(0).setMinWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(1).setMaxWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(1).setMinWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(2).setMaxWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(2).setMinWidth(0);
        vistaConsumo.tablaconsumos.getColumnModel().getColumn(2).setPreferredWidth(0);
    }
    
    
    
    
    
}// fin de clase Controlador