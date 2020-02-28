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
import modelo.Administrador;
import modelo.Conexion;

/**
 *
 * @author Dante_Sanchez
 */
//MANEJO DE DATOS Y METODOSPARA MI TABLA ADMINISTRADORES
public class AdministradorDAO extends Conexion{
    
    
    public Integer total;
    public Integer totalregistros;
    //creo mis metodos CRUD
    
    //metodo guardarAdministrador
    public boolean guardarAdministrador(Administrador administrador) {
        
        //llamo a mi conexion
        Connection cn = conectar();
        //preparo mi consulta
        PreparedStatement ps = null;
        
        //almacenno mi consulta sql en un String
       // String sql2 ="INSERT INTO empleado (idpersona,codigoempleado,puesto,sueldo) VALUES ((SELECT idpersona FROM persona ORDER BY idpersona DESC LIMIT 1),?,?,?)";

        String sql ="INSERT INTO administradores (idempleado,acceso,usuario,password) "
                + "VALUES (?,?,?,?)";
        
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            
            ps.setInt(1, administrador.getIdempleado());
            ps.setString(2, administrador.getAcceso());
            ps.setString(3, administrador.getUsuario());
            ps.setString(4, administrador.getPassword());
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                
                return true;
                
            }else {
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("error en guardaradministradorDAO " + e);
            return false;
        }
        
        //cerramos conexion
        finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    
    //metodo modificarEmpleado
    public boolean modificarAdministrador(Administrador administrador) {
        
        //llamamos a la conexion
        Connection cn = conectar();
        PreparedStatement ps = null;
        
        String sql = "UPDATE administradores SET idempleado=?,acceso=?,usuario=?,password=?"
                + " WHERE idadministradores=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1,administrador.getIdempleado());
            ps.setString(2, administrador.getAcceso());
            ps.setString(3, administrador.getUsuario());
            ps.setString(4, administrador.getPassword());
            
            //añadimos el idadministrador para su busqueda
            ps.setInt(5, administrador.getIdadministradores());
            
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                return true;
                
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("error en modificaradmnistradorDAO" + e);
            return false;
        }
        
        //cerramos conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    //metodo eliminarAdministrador
    public boolean eliminarAdministrador(Administrador administrador) {
        Connection cn = conectar();
        
        PreparedStatement ps= null;
        
        String sql ="DELETE FROM administradores WHERE idadministradores=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, administrador.getIdadministradores());
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                
                return true;
                
            }else {
                return false;
            }
                    
        } catch (SQLException e) {
            System.err.println("error en eliminaradministradorDAO " + e);
            return false;
        }
        
        //cierro conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    //metodo mostrartabla
    public DefaultTableModel mostrar(String buscar) {
        
        Connection cn = conectar();
        PreparedStatement ps = null;
        DefaultTableModel tabla;
        
        String [] encabezado = {"ID","ID_EMPLEADO","CODIGO_E.","NOMBRE","PATERNO","PUESTO","ACCESO","USUARIO","PASS"};
        String [] registro = new String[9];
        
        total=0;
        tabla = new DefaultTableModel(null,encabezado);
        
      /*  String sql = "SELECT ad.idadministradores,ad.idempleado,(SELECT nombre FROM persona WHERE idpersona = ad.idempleado) as empeladon,"
                + "(SELECT paterno FROM persona WHERE idpersona=ad.idempleado) as empleadopat,"
                + "(SELECT puesto FROM empleado WHERE idempleado =ad.idempleado) as puestoem,"
                + "ad.acceso,ad.usuario,ad.password "
                + "FROM administradores ad  INNER JOIN empleado em on  ad.idempleado=em.idempleado "
                + "WHERE em.codigoempleado like '%"+ buscar + "%' order by idreserva desc";
        */
        String sql2 ="SELECT ad.idadministradores,(SELECT idpersona FROM persona WHERE idpersona = ad.idempleado) as idempleado,"
                + "(SELECT codigoempleado FROM empleado WHERE idpersona = ad.idempleado) as codigoe,"
                + "(SELECT nombre FROM persona WHERE idpersona = ad.idempleado) as empeladon,"
                + "(SELECT paterno FROM persona WHERE idpersona = ad.idempleado) as empeladpat, "
                + "(SELECT puesto FROM empleado WHERE idpersona = ad.idempleado) as puesto,"
                + "ad.acceso,ad.usuario,ad.password "
                + "FROM administradores ad INNER JOIN empleado em "
                + " ON ad.idempleado=em.idpersona WHERE codigoempleado like  '%"
                + buscar + "%' order  by idempleado desc ";
        
        try {
            
            ps = cn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                registro [0] = rs.getString("idadministradores");
                registro [1] = rs.getString("idempleado");
                registro [2] = rs.getString("codigoe");
                registro [3] = rs.getString("empeladon");
                registro [4] = rs.getString("empeladpat");
                registro [5] = rs.getString("puesto");
                registro [6] = rs.getString("acceso");
                registro [7] = rs.getString("usuario");
                registro [8] = rs.getString("password");

                
                
                total = total + 1;
                tabla.addRow(registro);
            }
            
            return tabla;
                    
            
        } catch (SQLException e) {
            System.err.println("error en tablaDAO " + e);
            return null;
        }
    }
    
    
    //buscar administrador
    public boolean buscarAdministrador(Administrador administrador) {
                //iniciamos la conexion
        Connection cn = conectar();
        
        //preparo consulta como sera solo una creare un inner join de dos tablas
        PreparedStatement ps = null;
        
        //Preparo un ResultSEt que me regreaa infomarcion alojada en mi bd
        ResultSet rs =null;
        
        //guardamos nuestra consulta en un String 
       /* String sql = "SELECT p.idpersona,p.nombre,p.paterno,p.materno,p.telefono,p.correo,e.codigoempleado,"
                + "e.puesto,e.sueldo FROM persona p INNER JOIN empleado e ON p.idpersona=e.idpersona WHERE "
                + "e.codigoempleado=?";
        */
        
       /* String sql= "SELECT e.idempleado,e.codigoempleado,e.nombre,e.paterno,e.puesto,ad.acceso,ad.usuario,adpassword "
                + "FROM empleado e INNER JOIN administrador ad ON e.idempleado=ad.idempleado WHERE "
                + "e.codigoempleado=?";
        */
        String sql2 ="SELECT e.puesto,p.idpersona,p.nombre,p.paterno,ad.idadministradores,ad.acceso,ad.usuario,ad.password "
                + "FROM empleado e INNER JOIN persona p ON e.idpersona=p.idpersona "
                + "INNER JOIN administradores ad ON e.idpersona=ad.idempleado WHERE e.codigoempleado=?";
        
        try {
            
            ps = cn.prepareStatement(sql2);
            
           ps.setString(1, administrador.getCodigoempleado());
           
           //ejecutamos la consulta pero con ResultSet
           rs = ps.executeQuery();
           
           //si cumple la condicion regresara los datos obtenidos de la bd
            if (rs.next()) {
                
                administrador.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setPaterno(rs.getString("paterno"));
                administrador.setPuesto(rs.getString("puesto"));
                administrador.setIdadministradores(Integer.parseInt(rs.getString("idadministradores")));
                administrador.setAcceso(rs.getString("acceso"));
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setPassword(rs.getString("password"));
            
                
                return true;
                
            } else {
                //sino cumple regresara false
                return false;
            }
           
      
            
        } catch (SQLException e) {
            //pinto error
            System.err.println("error en buscarDAO admin " + e);
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
    
    
        public DefaultTableModel login(String login,String password) {
        Connection cn = conectar();
        DefaultTableModel modelo;

        String [] encabezado = {"ID","ID_EMPLEADO","CODIGO_E.","NOMBRE","PATERNO","PUESTO","ACCESO","USUARIO","PASS"};
        String [] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, encabezado);
/*
        String sql = "select p.idpersona,p.nombre,p.paterno,p.materno,"
                + "t.acceso,t.usuario,t.password,from persona p inner join administradores ad "
                + "on p.idpersona=t.idpersona where t.usuario='"
                + login + "' and t.password='" + password;
*//*
        String sql2 = "SELECT p.idpersona,p.nombre,p.paterno,p.materno,ad.acceso,ad.usuario,ad.password "
                + "FROM empleado e INNER JOIN persona p ON e.idpersona=p.idpersona "
                + "INNER JOIN administradores ad ON e.idpersona=ad.idempleado WHERE ad.usuario='"
                + login + "' and ad.password='" + password;
    */    
        
         String sql3 ="SELECT ad.idadministradores,(SELECT idpersona FROM persona WHERE idpersona = ad.idempleado) as idempleado,"
                + "(SELECT codigoempleado FROM empleado WHERE idpersona = ad.idempleado) as codigoe,"
                + "(SELECT nombre FROM persona WHERE idpersona = ad.idempleado) as empeladon,"
                + "(SELECT paterno FROM persona WHERE idpersona = ad.idempleado) as empeladpat, "
                + "(SELECT puesto FROM empleado WHERE idpersona = ad.idempleado) as puesto,"
                + "ad.acceso,ad.usuario,ad.password "
                + "FROM administradores ad INNER JOIN empleado em "
                + " ON ad.idempleado=em.idpersona WHERE WHERE ad.usuario='"
                + login + "' and ad.password='" + password;
         
         
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql3);

            while (rs.next()) {
                /*
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("paterno");
                registro[3] = rs.getString("materno");
                
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("usuario");
                registro[6] = rs.getString("password");
         */
                registro [0] = rs.getString("idadministradores");
                registro [1] = rs.getString("idempleado");
                registro [2] = rs.getString("codigoe");
                registro [3] = rs.getString("empeladon");
                registro [4] = rs.getString("empeladpat");
                registro [5] = rs.getString("puesto");
                registro [6] = rs.getString("acceso");
                registro [7] = rs.getString("usuario");
                registro [8] = rs.getString("password");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (SQLException e) {
            System.err.println("error aqui " + e);
            return null;
        }

    }
        
        
        public boolean login2(Administrador admin) {
            
            System.out.println("entra a dao");
            PreparedStatement ps= null;
            ResultSet rs = null;
            Connection cn = conectar();
            
            String sql ="SELECT e.puesto,p.idpersona,p.nombre,p.paterno,ad.idadministradores,ad.acceso,ad.usuario,ad.password "
                + "FROM empleado e INNER JOIN persona p ON e.idpersona=p.idpersona "
                + "INNER JOIN administradores ad ON e.idpersona=ad.idempleado WHERE ad.usuario=?";
         
             try {
                ps = cn.prepareStatement(sql);
                
                ps.setString(1, admin.getUsuario());
                rs = ps.executeQuery();
                
                            System.out.println("pasa la consulta");

                 if (rs.next()) {
                     System.out.println("si existe usuario");
                     if (admin.getPassword().equals(rs.getString(8))) {
                         
                         admin.setIdadministradores(rs.getInt(5));
                         admin.setAcceso(rs.getString(6));
                         admin.setIdempleado(rs.getInt(2));
                         
                         return true;
                                                 
                     }else {
                         return false;
                     }
                     
                 }
                 //JOptionPane.showMessageDialog(null, "Usuario no registrado");
                 System.out.println("no existe usuario");
               return false; 
                
            } catch (SQLException e) {
                System.err.println("error " + e);
                return false;
            }
            
        }
}
