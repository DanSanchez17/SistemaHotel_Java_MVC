/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_ds;

import controlador.AdminControlador;
import controlador.ClienteControlador;
import controlador.EmpleadoControlador;
import controlador.PersonaControlador;
import modelo.Administrador;
import modelo.dao.AdministradorDAO;
import modelo.Cliente;
import modelo.dao.ClienteDAO;
import modelo.Conexion;
import modelo.Empleado;
import modelo.dao.EmpleadoDAO;
import modelo.Persona;
import modelo.dao.PersonaDAO;
import vista.FormAdministrador;
import vista.FormCliente;
import vista.FormEmpleado;
import vista.FormPersona;

/**
 *
 * @author Dante_Sanchez
 */
public class Hotel_ds {

    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        
        //probamos si es diferente de vacio sera correcta la conexion
        if(conexion.conectar()!=null) {
            System.out.println("Conexion exitosa");
        }
        //sino la conexion no se ejecuto correctamente
        else {
            System.out.println("No se pudo hacer la conexion");
        }
        
        
        /*
        
        //para llamar a Persona creamos objetos de nuestras clases modelo-vista-controlador
        Persona modelo = new Persona();
        PersonaDAO modeloDAO = new PersonaDAO();
        FormPersona vistaPersona = new FormPersona();
        
        //llamamos al constrolador junto con su constructor que llama al modelo(s) vista
        PersonaControlador controladorPersona = new PersonaControlador(modelo, modeloDAO, vistaPersona);
        //llamamos al metodo iniciar de nuestro controlador
        controladorPersona.iniciar();
        //hacemos visible nuestro formulario
        vistaPersona.setVisible(true);
        
        */
        
       /*
        Cliente modeloCliente = new Cliente();
        ClienteDAO modeloClienteDAO = new ClienteDAO();
        FormCliente vistaCliente = new FormCliente();
        
        ClienteControlador controladorCliente = new ClienteControlador(modeloCliente, modeloClienteDAO, vistaCliente);
        controladorCliente.iniciar();
        vistaCliente.setVisible(true);
       */
        
        
       /*
        Empleado modeloEmpleado = new Empleado();
        EmpleadoDAO modeloEmpleadoDAO = new EmpleadoDAO();
        FormEmpleado vistaEmpleado = new FormEmpleado();
        
        EmpleadoControlador controladorEmpleado = new EmpleadoControlador(modeloEmpleado, modeloEmpleadoDAO, vistaEmpleado);
        controladorEmpleado.iniciar();
        vistaEmpleado.setVisible(true);
       */
       
       
       /*
       
        Administrador modeloAdmin = new Administrador();
        Empleado modeloEmpleado = new Empleado();
        EmpleadoDAO modeloEmpleadoDAO = new EmpleadoDAO();
        AdministradorDAO modeloAdminDAO = new AdministradorDAO();
        FormAdministrador vistaAdmin =  new FormAdministrador();
        
        AdminControlador controladorAdmin = new AdminControlador(modeloEmpleado, modeloEmpleadoDAO, modeloAdmin, modeloAdminDAO, vistaAdmin);
        controladorAdmin.iniciar();
        vistaAdmin.setVisible(true);
       */
       


    }
    
}
