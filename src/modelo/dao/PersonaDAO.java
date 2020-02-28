/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.Persona;

/**
 *
 * @author Dante_Sanchez
 */

//MANEJO DE DATOS DE TABLA PERSONA DAO

                        //heredamos de clase Conexion para no tener que crear objetos ni instancia de la clase Conexion
public class PersonaDAO extends Conexion{
    
    //creamos los metodos registrar, modificar...etc
    
    //tipo booleano que traiga el modelo del tipo que necesitemos
    public boolean registrarPersona(Persona persona){
        
        //preparamos la consulta con PreparedStatement
        PreparedStatement ps = null;
                       //llamamos al metodo directo como ya heredamos en la clase ya solo se pone el metodo de esa clase heredada
        Connection con =conectar();
        
        //aqui guardamos nuestra consulta en este caso sera un INSERT
        //no ponemos nuestro idpersona de la tabla
        String sql="INSERT INTO persona (nombre,paterno,materno,telefono,correo) VALUES (?,?,?,?,?)";
        
        //ahora nuestra conexion la mandamos dentro de un try-catch
        try {
            ps =con.prepareStatement(sql);
            
            //enviamos todos los datos a la posicion y datos de modelo
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getPaterno());
            ps.setString(3, persona.getMaterno());
            ps.setInt(4, persona.getTelefono());
            ps.setString(5, persona.getCorreo());
            
            //ejecutamos la consulta
            ps.execute();
            
            //si se realiza todo correcto retornara true como es un metodo booleano
            return true;
            
            
        } catch (SQLException e) { //nos pinta un SQLException
            
            //si tiene error imprimimos el error
            System.err.println(e);
            
            //y en caso de error retornara falso
            return false;
        }
        //es necesario cerrar la conexion (es buena practica)
        finally {
            try {
                con.close();
            } catch (SQLException e) { //igual forma ocupamos un SQLException
                    System.err.println(e);
            }
        }
        
    }
    
    
    //creamos ahora el metodo de modificar
    public boolean modificarPersona(Persona persona) {
        
        //llamamos al metodo conectar de la clase Conexion
        Connection cn= conectar();
        //preparamos la consulta comeinza en null
        PreparedStatement ps=null;
        
        //almacenamos nuestra consulta para modificar
        String sql ="UPDATE persona SET nombre=?, paterno=?, materno=?, telefono=?, correo=?"
                + "WHERE idpersona=?  ";
        //aqui hacemos un update dependiendo del id
        
        //hacemos el mismo procedimiento que insertar solo que agregaremos el idpersona
        try {
            ps =cn.prepareStatement(sql);
            
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getPaterno());
            ps.setString(3, persona.getMaterno());
            ps.setInt(4, persona.getTelefono());
            ps.setString(5, persona.getCorreo());
            //y aqui agregamos ahora nuestro idpersona
            ps.setInt(6, persona.getIdpersona());
            
            //ejecutamos la consulta
            ps.execute();
            
            //si todo es correcto retornamos un valor true 
            return true;
            
        } catch (SQLException e) {
            //sino se ejecuta correctamente imprimimos el error 
            System.err.println(e);
            return false;
        }
        //cerramos la conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    //creamos metodo eliminar
    public boolean eliminarPersona(Persona persona) {
        
        //llamamos a metodo conectar de clase Conexion
        Connection con = conectar();
        
        //preparamos la consulta con PreparedStatement
        PreparedStatement ps = null;
        
        //almacenamos nuestra consulta para eliminar 
        //eliminamos la fila dependiendo el idpersona
        String sql = "DELETE FROM persona WHERE idpersona=? ";
        
        try {
            ps = con.prepareStatement(sql);
            
            //solo necesitamos nuestro idpersona para eliminar toda esa fila de regsitro
            ps.setInt(1, persona.getIdpersona());
            
            //ejecutamos la consulta eliminar
            ps.execute();
            
            //si todo es correcto retornamos un true
            return true;
            
        } catch (SQLException e) {
            
            //pintamos el error en consola
            System.err.println(e);
            
            //retornamos en false
            return false;
        }
        //cerrramos la consulta sea correcta o no
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    //creamos metodo buscar que traiga el modelo del tipo que necesitamos en este caso de Persona
    public boolean buscarPersona(Persona persona) {
        
        //llamamos al metodo de clase Conexion
        Connection con = conectar();
        
        //declaramos una variable ResultSet para ocuparla mas abajo
        ResultSet rs = null;
        
        //Preparamos consulta
        PreparedStatement ps = null;
        
        //almacenamos la consulta para buscar un registro
        //en este caso haremos una busqueda por el nombre de la persona (deberia poner un codigo para que sea unico)
        String sql = "SELECT * FROM persona WHERE nombre=? ";
        
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, persona.getNombre());
            
            //ejecutamos la consulta  y con ResultSet(rs) para que nos regrese valores
            rs =ps.executeQuery();
            
            //usamos if y next para traer una fila si queremos mas filas sera un while
            if(rs.next()) {
                
                //--si entra al if va a llenar todos nuestros datos y retornara true
                
                //como es un int idpersona hay que parsearlo
                persona.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                persona.setNombre(rs.getString("nombre"));
                persona.setPaterno(rs.getString("paterno"));
                persona.setMaterno(rs.getString("materno"));
                persona.setTelefono(Integer.parseInt(rs.getString("telefono")));
                persona.setCorreo(rs.getString("correo"));
                
                return true;
                
            }
            
            //si no entra al if retornara falso
            return false;
            
        } catch (SQLException e) {
            
            //pintamos el error
            System.err.println(e);
            
            //retornamos false si da error
            return false;
        }
        //cerramos conexion
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                //pintamos error
                System.err.println(e);
            }
        }
        
        
    }
}
