/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Persona;
import modelo.dao.PersonaDAO;
import vista.FormPersona;

/**
 *
 * @author Dante_Sanchez
 */

//CREAMOS LA CONEXION DE LA CAPA-VISTA CON LA CAPA-MODELO DESDE NUESTRO CONTROLADOR (CONTROLLER)

//implementa instrucciones para detectar clics, enviar valores de cajas de texto e insertar a base de datos  

//necesitamos implementar ActionListener para escuchar a las acciones de los botones,etc.
public class PersonaControlador implements ActionListener{
    
    //declaramos variables llamando a las clases que creamos
    private Persona modelo;
    private PersonaDAO modeloDAO;
    private FormPersona vistaPersona;
    
    
    //creamos constructor public que recibira el modelo(s) y la vista
    public PersonaControlador(Persona modelo, PersonaDAO modeloDAO, FormPersona vistaPersona) {
        
        //igualamos los valores 
        this.modelo = modelo;
        this.modeloDAO = modeloDAO;
        this.vistaPersona = vistaPersona;
        
        //es necesario declarar los action de cada uno de los botones de nuestro formulario
        this.vistaPersona.btnguardar.addActionListener(this);
        this.vistaPersona.btnmodificar.addActionListener(this);
        this.vistaPersona.btneliminar.addActionListener(this);
        this.vistaPersona.btnbuscar.addActionListener(this);
        this.vistaPersona.btnlimpiar.addActionListener(this);
        
    }
    
    
    //creamos un metodo para iniciar la vista
    public void iniciar() {
        
        //establece el titulo para el formulario
        vistaPersona.setTitle("Registro Persona");
        
        //establece posicion formulario (centrado)
        vistaPersona.setLocationRelativeTo(null);
        
        //establecemos la caja de texto idpersona para que no sea visible
        vistaPersona.txtidpersona.setVisible(false);
    }
    
    
    //hacemos los metodos que escucharan los clics a los botones
    
    //el override lo crea al crear el metodo
    @Override
    public void actionPerformed (ActionEvent e) {
        
        //traera el evento del boton que se haya presionado (guardar,modificar,eliminar,etc)
        
        //si se cumple esta condicion significa que el usuario presiono el boton "Guardar"
        if(e.getSource()== vistaPersona.btnguardar) {
            
            /*la funcion es tomar los valores de la caja de texto y meterlos al modelo
              despues llamar el metodo de guardar y llevara el modelo
              y podra hacer la insercion a la base de datos */
           
           modelo.setNombre(vistaPersona.txtnombre.getText());
           modelo.setPaterno(vistaPersona.txtpaterno.getText());
           modelo.setMaterno(vistaPersona.txtmaterno.getText());
           modelo.setTelefono(Integer.parseInt(vistaPersona.txttelefono.getText()));
           modelo.setCorreo(vistaPersona.txtcorreo.getText());
           
           if(modeloDAO.registrarPersona(modelo)) {
               
               JOptionPane.showMessageDialog(null, "Registro Exitoso.");
               //si se guardo invoco al metodo limpiar
               limpiar();
               
           } else {
               JOptionPane.showMessageDialog(null, "Error al registrar");
               
                //si hay error que tambien limpie el formulario
               limpiar();
           }
            
            
            
        }
        
        
        
       //si se cumple esta condicion significa que el usuario presiono el boton "Modificar"
        if(e.getSource()== vistaPersona.btnmodificar) {
            
            /*la funcion es tomar los valores de la caja de texto y meterlos al modelo
              despues llamar el metodo de guardar y llevara el modelo
              y podra hacer la insercion a la base de datos */
            
           //solo que aqui agregamos el txtidpersona para eso se coloco
           //y a la vez como recibe mi modelo un tipo int lo parseo
           modelo.setIdpersona(Integer.parseInt(vistaPersona.txtidpersona.getText()));
           modelo.setNombre(vistaPersona.txtnombre.getText());
           modelo.setPaterno(vistaPersona.txtpaterno.getText());
           modelo.setMaterno(vistaPersona.txtmaterno.getText());
           modelo.setTelefono(Integer.parseInt(vistaPersona.txttelefono.getText()));
           modelo.setCorreo(vistaPersona.txtcorreo.getText());
           
           if(modeloDAO.modificarPersona(modelo)) {
               
               JOptionPane.showMessageDialog(null, "Registro Modificado.");
               //si se modifico corretamente invoco al metodo limpiar
               limpiar();
               
           } else {
               JOptionPane.showMessageDialog(null, "Error al modificar");
               
                //si hay error que tambien limpie el formulario
               limpiar();
           }
            
            
            
        }
        
       //si se cumple esta condicion significa que el usuario presiono el boton "Eliminar"
        if(e.getSource()== vistaPersona.btneliminar) {
            
            /*la funcion es tomar los valores de la caja de texto y meterlos al modelo
              despues llamar el metodo de guardar y llevara el modelo
              y podra hacer la insercion a la base de datos */
            
           //aqui solo necesitaremos el txtidpersona
           //y a la vez como recibe mi modelo un tipo int lo parseo
           modelo.setIdpersona(Integer.parseInt(vistaPersona.txtidpersona.getText()));
          
           
           if(modeloDAO.eliminarPersona(modelo)) {
               
               JOptionPane.showMessageDialog(null, "Registro Eliminado.");
               //si se modifico corretamente invoco al metodo limpiar
               limpiar();
               
           } else {
               JOptionPane.showMessageDialog(null, "Error al eliminar");
               
                //si hay error que tambien limpie el formulario
               limpiar();
           }
            
            
            
        }
        
        
        //si el evento es igual a btnbuscar significa que se presiono el boton "Buscar"
        if(e.getSource()==vistaPersona.btnbuscar) {
            
            //buscamos por nombre de persona asi que seteamos el txtnombre
            modelo.setNombre(vistaPersona.txtnombre.getText());
            
            //si todo es correcto
            if(modeloDAO.buscarPersona(modelo)) {
                
                //en caso de que encontremos resultado debemos pintarlo en las cajas de texto
                 
                //como traemos un tipo entero(int) en este caso lo convertimos a String de la siguiente forma
                vistaPersona.txtidpersona.setText(String.valueOf(modelo.getIdpersona()));
                //estos son tipo string, lo dejamos normal
               //            vistaPersona.txtnombre.setText(modelo.getNombre());
                vistaPersona.txtpaterno.setText(modelo.getPaterno());
                vistaPersona.txtmaterno.setText(modelo.getMaterno());
                vistaPersona.txttelefono.setText(String.valueOf(modelo.getTelefono()));
                vistaPersona.txtcorreo.setText(modelo.getCorreo());
                 
                                
                JOptionPane.showMessageDialog(null, "Registro encontrado");
                
                
            }else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
            
            
        }
        
        
        //cuando presionemos en boton limpiar solo invocamos e nuestro metodo limpiar
        if(e.getSource()==vistaPersona.btnlimpiar) {
            limpiar();
        }
        
        
    }
    
    
    //creamos un metodo para limpiar las cajas 
    public void limpiar() {
        
        vistaPersona.txtnombre.setText(null);
        vistaPersona.txtpaterno.setText(null);
        vistaPersona.txtmaterno.setText(null);
        vistaPersona.txttelefono.setText(null);
        vistaPersona.txtcorreo.setText(null);
    }
    
}
