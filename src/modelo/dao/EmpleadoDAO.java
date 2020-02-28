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
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Empleado;

/**
 *
 * @author Dante_Sanchez
 */

//AQUI ENTRA EL MANEJO DE DATOS Y CONSULTAS SQL
//heredo de la clase Conexion para ocupar sus metodos
public class EmpleadoDAO extends Conexion{
    
    
    
    //creo metodos guardar, modificar, eliminar y buscar Empleado
    
    //metodo guardarEmpleado
    public boolean guardarEmpleado(Empleado empleado) {
        
        //llamo a mi conexion
        Connection cn = conectar();
        
        //preparo mi consulta , como hereda de otra tabla(Persona) debo hacer dos consultas
        PreparedStatement ps = null;
        PreparedStatement ps2 =null;
        
        //almaceno mi consulta en un String
        String sql ="INSERT INTO persona (nombre,paterno,materno,telefono,correo) VALUES (?,?,?,?,?)";
        String sql2 ="INSERT INTO empleado (idpersona,codigoempleado,puesto,sueldo) VALUES ((SELECT idpersona FROM persona ORDER BY idpersona DESC LIMIT 1),?,?,?)";
        
        //metemos nuestra instruccion en un try-catch
        try {
            
            ps =cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            //ingreso datos en mi primer tabla(persona)
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getPaterno());
            ps.setString(3, empleado.getMaterno());
            ps.setInt(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());
            
            //ingreso datos en mi segunda tabla(empleado)
            ps2.setString(1, empleado.getCodigoempleado());
            ps2.setString(2, empleado.getPuesto());
            ps2.setDouble(3, empleado.getSueldo());
            
            
            //ejecuto mi consulta
            int consulta1 = ps.executeUpdate();
            
            if(consulta1 != 0){
                int consulta2 = ps2.executeUpdate();
                
                if(consulta2 != 0) {
                    
                    return true;
                }else {
                    return false;
                }
            }
            else {
                return false;
            }
            
            
            
        } catch (SQLException e) {
            
            //pinto el error
            System.err.println(e);
            
            //retorno falso al metodo porque no se cumplio
            return false;
        }
        
        //cierro mi conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
        
    }
    
    
    //metodo modificarEmpleado
    public boolean modificarEmpleado(Empleado empleado) {
        
        //llamo a mi conexion
        Connection cn = conectar();
        
        //preparo mis consultas
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        //almaceno mis consultas en un String
        String sql = "UPDATE persona SET nombre=?,paterno=?,materno=?,telefono=?,correo=? WHERE idpersona=?";
        String sql2 = "UPDATE empleado SET codigoempleado=?,puesto=?,sueldo=? WHERE idpersona=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getPaterno());
            ps.setString(3, empleado.getMaterno());
            ps.setInt(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());
            //agregamos el idpersona que es por donde buscara para hacer el update
            ps.setInt(6, empleado.getIdpersona());
            
            ps2.setString(1, empleado.getCodigoempleado());
            ps2.setString(2, empleado.getPuesto());
            ps2.setDouble(3, empleado.getSueldo());
            //agregamos idpersona para el update
            ps2.setInt(4, empleado.getIdpersona());
            
            
            int consulta1 = ps.executeUpdate();
            
            if (consulta1 != 0) {
                
                int consulta2 = ps2.executeUpdate();
                
                if (consulta2 != 0) {
                    return true;
                    
                }else {
                    return false;
                }
            }else  {
                return false;
            }
                   
            
            
        } catch (SQLException e) {
            
            //pintamos el error
            System.err.println(e);
            
            //si da error retoranremos false
            return false;
        }
        
        //cerramos conexion
        finally  {
            try {
                cn.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        
    }
    
    
    //metodo eliminarEmpleado
    public boolean eliminarEmpleado(Empleado empleado) {
        
        Connection cn = conectar();
        
        //preparamos consultas
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        //almaceno mis consultas en un String
        String sql = "DELETE FROM persona WHERE idpersona=? ";
        String sql2 = "DELETE FROM empleado WHERE idpersona=? ";
        
        try {
            
            ps = cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            ps.setInt(1, empleado.getIdpersona());
            
            ps2.setInt(1, empleado.getIdpersona());
            
            int consulta1 = ps.executeUpdate();
            
            if (consulta1 != 0) {
                
                int consulta2 = ps2.executeUpdate();
                
                if(consulta2 != 0) {
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
            
        } catch (SQLException e) {
            
            //pinto el error
            System.err.println("aqui error en dao "+e);
            return false;
        }
        
        //cierro mi conexion
        finally {
            try {
                cn.close();
                
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    
    //metodo buscarEmpleado
    public boolean buscarEmpleado(Empleado empleado) {
        
        //iniciamos la conexion
        Connection cn = conectar();
        
        //preparo consulta como sera solo una creare un inner join de dos tablas
        PreparedStatement ps = null;
        
        //Preparo un ResultSEt que me regreaa infomarcion alojada en mi bd
        ResultSet rs =null;
        
        //guardamos nuestra consulta en un String 
        String sql = "SELECT p.idpersona,p.nombre,p.paterno,p.materno,p.telefono,p.correo,e.codigoempleado,"
                + "e.puesto,e.sueldo FROM persona p INNER JOIN empleado e ON p.idpersona=e.idpersona WHERE "
                + "e.codigoempleado=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setString(1, empleado.getCodigoempleado());
           
           //ejecutamos la consulta pero con ResultSet
           rs = ps.executeQuery();
           
           //si cumple la condicion regresara los datos obtenidos de la bd
            if (rs.next()) {
                
                empleado.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setPaterno(rs.getString("paterno"));
                empleado.setMaterno(rs.getString("materno"));
                empleado.setTelefono(Integer.parseInt(rs.getString("telefono")));
                empleado.setCorreo(rs.getString("correo"));
                 empleado.setPuesto(rs.getString("puesto"));
                  empleado.setSueldo(Double.parseDouble(rs.getString("sueldo")));
                //obtenemos tambien el codigo de cliente
                empleado.setCodigoempleado(rs.getString("codigoempleado"));
                
                
                return true;
                
            } else {
                //sino cumple regresara false
                return false;
            }
           
      
            
        } catch (SQLException e) {
            //pinto error
            System.err.println(e);
            //regreso false si hay error
            return false;
        }
        
        //cierro mi conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
        public DefaultTableModel mostrar(String buscar) {
        
        Connection cn = conectar();
        
        DefaultTableModel modelo;
        String[] titulos = {"ID","CÓDIGO_EMPLEADO","NOMBRE","PATERNO","MATERNO","TELEFONO","CORREO","PUESTO","SUELDO"};
        String[] registro = new String[9];
        
        modelo = new DefaultTableModel(null,titulos);
        
        String sql ="SELECT p.idpersona,em.codigoempleado,p.nombre,p.paterno,p.materno,p.telefono,p.correo,em.puesto,em.sueldo FROM persona p INNER JOIN empleado em"
                + " on p.idpersona=em.idpersona WHERE codigoempleado like  '%"
                + buscar + "%' order  by idpersona desc ";

                try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("codigoempleado");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("paterno");
                registro[4] = rs.getString("materno");
                registro[5] = rs.getString("telefono");
                registro[6] = rs.getString("correo");
                registro[7] = rs.getString("puesto");
                registro[8] = rs.getString("sueldo");


                //totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
       
    }
        
        
}
