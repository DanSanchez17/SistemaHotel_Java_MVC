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
import modelo.Cliente;
import modelo.dao.ClienteDAO;
import vista.FormCliente;

/**
 *
 * @author Dante_Sanchez
 */

//SE COMUNICARA CON DEL MODELO Y LA VISTA
//implememantara los actionlistener
public class ClienteControlador implements ActionListener{
    
    //declaro variables de tipo de las clases que cree en modelo(s) y vista
    private Cliente modeloCliente;
    private ClienteDAO modeloClienteDAO;
    private FormCliente vistaCliente;
    
    
    //creo constructor que tenga de parametros mis clases modelo(s)-vista
    public ClienteControlador(Cliente modeloCliente, ClienteDAO modeloClienteDAO, FormCliente vistaCliente){
            
        //igualamos los valores
        this.modeloCliente=modeloCliente;
        this.modeloClienteDAO=modeloClienteDAO;
        this.vistaCliente=vistaCliente;
        
        //iniciaciamos los botones del formulario Cliente
        this.vistaCliente.btnbuscar.addActionListener(this);
        this.vistaCliente.btneliminar.addActionListener(this);
        this.vistaCliente.btnguardar.addActionListener(this);
        this.vistaCliente.btnlimpiar.addActionListener(this);
        this.vistaCliente.btnmodificar.addActionListener(this);
               
                
    }
    
    //creamos un metodo para iniciar la vista
    public void iniciar(){
        
        //hago mi boton idpersona NO-visible
        this.vistaCliente.txtidpersona.setVisible(false);
        mostrar("");
    }
    
    
    //creamos el metodo ActionPErformed que detectara los clics de cada uno de los botones
    public void actionPerformed (ActionEvent e) {
        
        if(e.getSource()==vistaCliente.btnguardar) {
            
            //insertaremos en la capa modelo(Cliente) los datos que esten en las cajas de texto de la vista(FormCliente)
            modeloCliente.setNombre(vistaCliente.txtnombre.getText());
            modeloCliente.setPaterno(vistaCliente.txtpaterno.getText());
            modeloCliente.setMaterno(vistaCliente.txtmaterno.getText());
            modeloCliente.setTelefono(Integer.parseInt(vistaCliente.txttelefono.getText()));
            modeloCliente.setCorreo(vistaCliente.txtcorreo.getText());
            modeloCliente.setCodigocliente(vistaCliente.txtcodigocliente.getText());
            
            
            
            if(modeloClienteDAO.guardarCliente(modeloCliente)) {
                
                JOptionPane.showMessageDialog(null, "Registro  de cliente guardado con exito!");
                limpiar();
                mostrar("");
            }
            else {
                JOptionPane.showMessageDialog(null, "Falló al guardar el cliente");
                limpiar();
            }
        }
        
        
        
        //cuando presionemos boton modificar cliente
        if(e.getSource()==vistaCliente.btnmodificar) {
            
            //agregamos un set al idpersona para qwue cumpla nuestra consulta sql en un update
            modeloCliente.setIdpersona(Integer.parseInt(vistaCliente.txtidpersona.getText()));
            modeloCliente.setCodigocliente(vistaCliente.txtcodigocliente.getText());
            modeloCliente.setNombre(vistaCliente.txtnombre.getText());
            modeloCliente.setPaterno(vistaCliente.txtpaterno.getText());
            modeloCliente.setMaterno(vistaCliente.txtmaterno.getText());
            modeloCliente.setTelefono(Integer.parseInt(vistaCliente.txttelefono.getText()));
            modeloCliente.setCorreo(vistaCliente.txtcorreo.getText());
            
            if (modeloClienteDAO.modificarCliente(modeloCliente)) {
                
                JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
                limpiar();
                mostrar("");
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar cliente");
                limpiar();
                mostrar("");
                
            }
            
        }
        
        
        if(e.getSource()== vistaCliente.btneliminar) {
            
            //aqui solo necesitamos tomar el idpersona de nuestro FormCliente para eliminar el registro
            modeloCliente.setIdpersona(Integer.parseInt(vistaCliente.txtidpersona.getText()));
            
            if(modeloClienteDAO.eliminarCliente(modeloCliente)) {
                JOptionPane.showMessageDialog(null, "EL cliente se elimino correctamente");
                limpiar();
                mostrar("");
                
            }
            else {
                JOptionPane.showMessageDialog(null, "Se eliminao cliente");
                limpiar();
                mostrar("");
                
            }
        }
        
        
        if(e.getSource()== vistaCliente.btnbuscar) {
            
            //buscaremos por codigo de cliente
            modeloCliente.setCodigocliente(vistaCliente.txtcodigocliente.getText());
            
            //si cumple la condicion
            if(modeloClienteDAO.buscarCliente(modeloCliente)) {
                
                //pintaremos los datos obtenidos a las cajas de texto
                vistaCliente.txtnombre.setText(modeloCliente.getNombre());
                vistaCliente.txtpaterno.setText(modeloCliente.getPaterno());
                vistaCliente.txtmaterno.setText(modeloCliente.getMaterno());
                vistaCliente.txttelefono.setText(String.valueOf(modeloCliente.getTelefono()));
                vistaCliente.txtcorreo.setText(modeloCliente.getCorreo());
                
                vistaCliente.txtidpersona.setText(String.valueOf(modeloCliente.getIdpersona()));
                
                JOptionPane.showMessageDialog(null, "Se encontro registro de cliente");
                
            }
            else {
                JOptionPane.showMessageDialog(null, "No se encontro registro de cliente");
                limpiar();
                
            }
            
            
        }
        
        
        if (e.getSource()==vistaCliente.btnlimpiar) {
            //llamamos al metodo limpiar
            limpiar();
            
        }
    }
    
    
    //creo mi metodo limpiar para limpiar cajas de texto de FormCliente
    public void limpiar() {
        vistaCliente.txtcodigocliente.setText(null);
        vistaCliente.txtnombre.setText(null);
        vistaCliente.txtpaterno.setText(null);
        vistaCliente.txtmaterno.setText(null);
        vistaCliente.txttelefono.setText(null);
        vistaCliente.txtcorreo.setText(null);
    }
    
    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            ClienteDAO func = new ClienteDAO();
            modelo = func.mostrar(buscar);
            
            
            vistaCliente.tablacliente.setModel(modelo);
            ocultar_columnas();
                        
        } catch (Exception e) {
        }
    }
    
        void ocultar_columnas() {
        vistaCliente.tablacliente.getColumnModel().getColumn(0).setMaxWidth(0);
        vistaCliente.tablacliente.getColumnModel().getColumn(0).setMinWidth(0);
        vistaCliente.tablacliente.getColumnModel().getColumn(0).setPreferredWidth(0);

    }
}
