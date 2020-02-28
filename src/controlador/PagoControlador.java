/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Pago;
import modelo.dao.ConsumoDAO;
import modelo.dao.PagoDAO;
import vista.FormConsumos;
import vista.FormPago;

/**
 *
 * @author Dante_Sanchez
 */
public class PagoControlador implements ActionListener {
 
    public static String idreserva;
    public static String cliente;
    public static Double totalreserva;
    public static String idhabitacion;
    public static String habitacion;
    
    private Pago modeloPago;
    private PagoDAO modeloPagoDAO;
    private FormPago vistaPago;
    
        public PagoControlador(Pago modeloPago,PagoDAO modeloPagoDAO,FormPago vistaPago){
        
        //igualamos los valores    
        this.modeloPago = modeloPago;
        this.modeloPagoDAO = modeloPagoDAO;
        this.vistaPago = vistaPago;
        
        
        //iniciamos  los botones
        this.vistaPago.btnbuscar.addActionListener(this);
        this.vistaPago.btneliminar.addActionListener(this);
        this.vistaPago.btnguardar.addActionListener(this);
        this.vistaPago.btnlimpiar.addActionListener(this);
        this.vistaPago.btnmodificar.addActionListener(this);
    }
        
        
        public void iniciar(){
       // this.vistaAdmin.txtidpersona.setVisible(false);
        mostrar(idreserva);
       
        vistaPago.txtidreservacion.setText(idreserva);
        vistaPago.txtclliente.setText(cliente);
        vistaPago.txthabitacion.setText(habitacion);
        vistaPago.txtidhabitacion.setText(idhabitacion);
        vistaPago.txttotalreserva.setText(Double.toString(totalreserva));
        
        vistaPago.txtidpago.setVisible(false);
        vistaPago.txtidreservacion.setVisible(false);
        vistaPago.txtidhabitacion.setVisible(false);
        
        vistaPago.btnbuscar.setVisible(false);

        
        ConsumoDAO func = new ConsumoDAO();
        func.mostrar(idreserva);
        
        vistaPago.txtcuentatotal.setText(Double.toString(totalreserva + func.totalconsumo));
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaPago.btnguardar) {

            
            modeloPago.setIdreservacion(Integer.parseInt(vistaPago.txtidreservacion.getText()));
            modeloPago.setTipopago((String)(vistaPago.cbotipopago.getSelectedItem()));
            
            modeloPago.setPagototal(Double.parseDouble(vistaPago.txtcuentatotal.getText()));
            
            modeloPago.setFolio(vistaPago.txtfolio.getText());
            
            
        Calendar cal;
        int d,m,a;
        cal=vistaPago.dcfechapago.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloPago.setFechapago(new Date(a,m,d));
        
       cal=vistaPago.dcfechapago.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloPago.setFechapago(new Date(a,m,d));

            
            
            

            
            
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloPagoDAO.guardarPago(modeloPago)) {
                
                JOptionPane.showMessageDialog(null, "Pago registrado con Exito!");
                limpiar();
                mostrar(idreserva);
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar Pago");
                limpiar();
                mostrar(idreserva);
            }
            
        }
        
        
        if (e.getSource()==vistaPago.btnmodificar) {
            
            /*ejemplo
            modeloEmpleado.setIdpersona(Integer.parseInt(vistaEmpleado.txtidpersona.getText()));
            
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
            modeloEmpleado.setTelefono(Integer.parseInt(vistaEmpleado.txttelefono.getText()));
            modeloEmpleado.setPuesto((String) vistaEmpleado.cbopuesto.getSelectedItem());
            modeloEmpleado.setSueldo(Double.parseDouble(vistaEmpleado.txtsueldo.getText()));
            */
            
            //agrego idpago
            modeloPago.setIdpago(Integer.parseInt(vistaPago.txtidpago.getText()));
            
            modeloPago.setIdreservacion(Integer.parseInt(vistaPago.txtidreservacion.getText()));
            modeloPago.setTipopago((String)(vistaPago.cbotipopago.getSelectedItem()));
            
            modeloPago.setPagototal(Double.parseDouble(vistaPago.txtcuentatotal.getText()));
            
            modeloPago.setFolio(vistaPago.txtfolio.getText());
            
            
        Calendar cal;
        int d,m,a;
        cal=vistaPago.dcfechapago.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloPago.setFechapago(new Date(a,m,d));
        
       cal=vistaPago.dcfechapago.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloPago.setFechapago(new Date(a,m,d));


            
            
            if(modeloPagoDAO.modificarPago(modeloPago)) {
                
                JOptionPane.showMessageDialog(null, "Pago Actualizado con Exito");
                //metodo limpiar
                limpiar();
                mostrar(idreserva);
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar Pago");
                limpiar();
                mostrar(idreserva);
            }
            
            
        }
        
        
        if (e.getSource()==vistaPago.btneliminar) {
            
            //aqui solo necesito mi idpago para eliminarlo
            modeloPago.setIdpago(Integer.parseInt(vistaPago.txtidpago.getText()));

            
            if (modeloPagoDAO.eliminarPago(modeloPago)) {
                
                JOptionPane.showMessageDialog(null, "Pago eliminado con Exito");
                limpiar();
                mostrar(idreserva);
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Pago");
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
            
                                  
        }   */
        
         if (e.getSource()==vistaPago.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
            }
        
    }
    
    
        public void limpiar() {
        
        /*
            vistaAdmin.txtnombre.setText(null);
            vistaAdmin.cboacceso.setSelectedItem(null);
            */    
 
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
            PagoDAO func = new PagoDAO();
            modelo = func.mostrar(buscar);

            vistaPago.tablapagos.setModel(modelo);
            ocultar_columnas();
            vistaPago.lbltotalregistrosconsumo.setText("Total Cuenta :" + Integer.toString(func.totalregistros));
            
            //Mostrar los datos de los consumos
            ConsumoDAO func2 = new ConsumoDAO();
            modelo= func2.mostrar(buscar);
            vistaPago.tablaconsumos.setModel(modelo);
            ocultar_columnasconsumo();
            
            vistaPago.lbltotalregistrosconsumo.setText("Consumos por Reserva : " + func2.totalregistros);
            vistaPago.lbltotalconsumo.setText("Total de Consumos : $" + func2.totalconsumo );
            

        } catch (Exception e) {
            //JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
      
      
         void ocultar_columnas() {
        vistaPago.tablapagos.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaPago.tablapagos.getColumnModel().getColumn(0).setMinWidth(0);
        vistaPago.tablapagos.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaPago.tablapagos.getColumnModel().getColumn(1).setMaxWidth(0);
        vistaPago.tablapagos.getColumnModel().getColumn(1).setMinWidth(0);
        vistaPago.tablapagos.getColumnModel().getColumn(1).setPreferredWidth(0);
    }
         
        void ocultar_columnasconsumo() {
        vistaPago.tablaconsumos.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(0).setMinWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaPago.tablaconsumos.getColumnModel().getColumn(1).setMaxWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(1).setMinWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        vistaPago.tablaconsumos.getColumnModel().getColumn(2).setMaxWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(2).setMinWidth(0);
        vistaPago.tablaconsumos.getColumnModel().getColumn(2).setPreferredWidth(0);
    }
    
    
    
    
    
}// fin de clase Controlador
