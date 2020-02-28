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
import modelo.Reservacion;
import modelo.dao.ReservacionDAO;
import vista.FormPrincipal;
import vista.FormReservacion;

/**
 *
 * @author Dante_Sanchez
 */
public class ReservaControlador implements ActionListener {
 
    private Reservacion modeloReserva;
    private ReservacionDAO modeloReservaDAO;
    private FormReservacion vistaReserva;
    
    public static int idusuario;
    
        public ReservaControlador(Reservacion modeloReserva,ReservacionDAO modeloReservaDAO,FormReservacion vistaReserva){
        
        //igualamos los valores    
        this.modeloReserva = modeloReserva;
        this.modeloReservaDAO = modeloReservaDAO;
        this.vistaReserva = vistaReserva;
        
        
        //iniciamos  los botones
        this.vistaReserva.btnbuscar.addActionListener(this);
        this.vistaReserva.btneliminar.addActionListener(this);
        this.vistaReserva.btnguardar.addActionListener(this);
        this.vistaReserva.btnlimpiar.addActionListener(this);
        this.vistaReserva.btnmodificar.addActionListener(this);
    }
        
        
        public void iniciar(){
        this.vistaReserva.txtidempleado.setVisible(false);
        this.vistaReserva.txtidreservacion.setVisible(false);
        this.vistaReserva.txtidhabitacion.setVisible(false);
        this.vistaReserva.txtidcliente.setVisible(false);
        
        this.vistaReserva.btnbuscar.setVisible(false);
        this.vistaReserva.cboestadoreserva.setSelectedItem(null);
        
        this.vistaReserva.btnrealizarpago.setEnabled(false);
        this.vistaReserva.btnagregarconsumo.setEnabled(false);
        
        
        
        
        //FormPrincipal inicio = new FormPrincipal();
        vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
        vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
        
        
        mostrar("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //empiezan mis condiciones dependiendo de que boton pulse (guardar,modificar,eliminaro buscar empleado)
            
        //si se pulsa en el boton guardar
        if(e.getSource()== vistaReserva.btnguardar) {
            
         
            modeloReserva.setCodigoreserva(vistaReserva.txtcodigoreserva.getText());
            
  
            modeloReserva.setIdempleado(Integer.parseInt(vistaReserva.txtidempleado.getText()));
            modeloReserva.setIdhabitacion(Integer.parseInt(vistaReserva.txtidhabitacion.getText()));
            modeloReserva.setIdcliente(Integer.parseInt(vistaReserva.txtidcliente.getText()));
            
            
            
            
            
                    Calendar cal;
        int d,m,a;
        cal=vistaReserva.dcfechaingreso.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloReserva.setFechaingreso(new Date(a,m,d));
        
       cal=vistaReserva.dcfechasalida.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloReserva.setFechasalida(new Date(a,m,d));
            //
            
            modeloReserva.setPrecioreserva(Double.parseDouble(vistaReserva.txtprecioreserva.getText()));
            modeloReserva.setEstado((String) vistaReserva.cboestadoreserva.getSelectedItem());
            
            
            
            
            //si cumple la condicion damos un mensaje de guardado con exito
            if (modeloReservaDAO.guardarReservacion(modeloReserva)) {
                
                JOptionPane.showMessageDialog(null, "Reserva registrada con Exito!");
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al registrar Reservación");
            }
            
        }
        
        
        if (e.getSource()==vistaReserva.btnmodificar) {
            

            
            //agrego id
            modeloReserva.setIdreservacion(Integer.parseInt(vistaReserva.txtidreservacion.getText()));
            
            modeloReserva.setCodigoreserva(vistaReserva.txtcodigoreserva.getText());
            //modeloReserva.set(vistaReserva.txthabitacion.getText());
         
            modeloReserva.setIdempleado(Integer.parseInt(vistaReserva.txtidempleado.getText()));
            modeloReserva.setIdhabitacion(Integer.parseInt(vistaReserva.txtidhabitacion.getText()));
            modeloReserva.setIdcliente(Integer.parseInt(vistaReserva.txtidcliente.getText()));
            
            
            
            
            
                    Calendar cal;
        int d,m,a;
        cal=vistaReserva.dcfechaingreso.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloReserva.setFechaingreso(new Date(a,m,d));
        
       cal=vistaReserva.dcfechasalida.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        modeloReserva.setFechasalida(new Date(a,m,d));
            //
            
            modeloReserva.setPrecioreserva(Double.parseDouble(vistaReserva.txtprecioreserva.getText()));
            modeloReserva.setEstado((String) vistaReserva.cboestadoreserva.getSelectedItem());
            

            
            
            if(modeloReservaDAO.modificarReservacion(modeloReserva)) {
                
                JOptionPane.showMessageDialog(null, "Reserva Actualizada con Exito");
                //metodo limpiar
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
                mostrar("");
                
            }else {
                JOptionPane.showMessageDialog(null, "Error al modificar Reservación");
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
                mostrar("");
            }
            
            
        }
        
        
        if (e.getSource()==vistaReserva.btneliminar) {
            
            //aqui solo necesito mi idreservacion para eliminarlo

            modeloReserva.setIdreservacion(Integer.parseInt(vistaReserva.txtidreservacion.getText()));

            
            if (modeloReservaDAO.eliminarReservacion(modeloReserva)) {
                
                JOptionPane.showMessageDialog(null, "Reservación eliminada con Exito");
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
                mostrar("");
            }else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Reservación");
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
                mostrar("");
            }
            
        }
        
        
        /*
        if (e.getSource()==vistaReserva.btnbuscar) {
            
            //buscamos por codigoempleado
           /*ejemplo
            modeloEmpleado.setCodigoempleado(vistaEmpleado.txtcodigodeempleado.getText());
            
            
            if (modeloReservaDAO.busca(modelo__XX)) {
                
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
            
                                  
        }       */
        
         if (e.getSource()==vistaReserva.btnlimpiar) {
                
                //ponemos nuestro metodo limpiar
                limpiar();
                vistaReserva.txtidempleado.setText(FormPrincipal.lblidempleado.getText());
                vistaReserva.txtadministrador.setText(FormPrincipal.lblusuario.getText());
            }
        
    }
    
    
        public void limpiar() {
        
            vistaReserva.txtidempleado.setText(null);
            vistaReserva.txtadministrador.setText(null);
            vistaReserva.txtcodigoreserva.setText(null);
            vistaReserva.txtidreservacion.setText(null);
            vistaReserva.txtidhabitacion.setText(null);
            vistaReserva.txthabitacion.setText(null);
            vistaReserva.txtidcliente.setText(null);
            vistaReserva.txtcliente.setText(null);
            vistaReserva.dcfechaingreso.setDate(null);
            vistaReserva.dcfechasalida.setDate(null);
            vistaReserva.txtprecioreserva.setText(null);
           
            vistaReserva.cboestadoreserva.setSelectedItem(null);
            
 
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
            ReservacionDAO func = new ReservacionDAO();
            modelo = func.mostrar(buscar);

            vistaReserva.listareserva.setModel(modelo);
            ocultar_columnas();
            //lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

   
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
      
        void ocultar_columnas() {
       
        vistaReserva.listareserva.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(0).setMinWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        vistaReserva.listareserva.getColumnModel().getColumn(1).setMaxWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(1).setMinWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(1).setPreferredWidth(0);
                
        vistaReserva.listareserva.getColumnModel().getColumn(3).setMaxWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(3).setMinWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        vistaReserva.listareserva.getColumnModel().getColumn(5).setMaxWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(5).setMinWidth(0);
        vistaReserva.listareserva.getColumnModel().getColumn(5).setPreferredWidth(0);
        

    }
    
    
    
    
    
}// fin de clase Controlador