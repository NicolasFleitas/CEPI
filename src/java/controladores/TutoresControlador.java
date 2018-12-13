/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Tutores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Ciudades;
import modelos.Nacionalidades;
import modelos.EstadosCiviles;
import modelos.Parentescos;

public class TutoresControlador {
    public static boolean agregar(Tutores tutor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into tutores (nombre_tutor,apellido_tutor,"
                    + "ruc_tutor,direccion_tutor,"
                    + "telefono_tutor,email_tutor,"
                    + "fecha_nac_tutor,id_nacionalidad,"
                    + "id_ciudad,id_estadocivil,"
                    + "id_parentesco,"
                    + "profesion_tutor,ocupacion_tutor,"
                    + "direccion_laboral_tutor,"
                    + "telefono_laboral_tutor)" 
                    + "values ('" 
                    + tutor.getNombre_tutor() + "','"
                    + tutor.getApellido_tutor() + "','"
                    + tutor.getRuc_tutor()+ "','"    
                    + tutor.getDireccion_tutor()+ "','" 
                    + tutor.getTelefono_tutor()+ "','"
                    + tutor.getEmail() + "','"
                    + tutor.getFecha_nac_tutor()+ "','"
                    + tutor.getNacionalidad().getId_nacionalidad()+"','"
                    + tutor.getCiudad().getId_ciudad() + "','"
                    + tutor.getEstadocivil().getId_estadocivil() + "','"
                    + tutor.getParentesco().getId_parentesco() + "','"
                    + tutor.getProfesion_tutor()+ "','"
                    + tutor.getOcupacion_tutor()+ "','"
                    + tutor.getDireccion_laboral_tutor()+ "','"
                    + tutor.getTelefono_laboral_tutor()+ "')";
                    
                  System.out.println("sql"+ sql);  
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TutoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        
        return valor;
        
    }
    
    public static boolean modificar(Tutores tutor){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update tutores set nombre_tutor='" + tutor.getNombre_tutor() + "',"
                    + "apellido_tutor='" + tutor.getApellido_tutor() + "',"
                    + "ruc_tutor='" + tutor.getRuc_tutor()+ "',"
                    + "telefono_tutor='" + tutor.getTelefono_tutor()+ "',"
                    + "direccion_tutor='" + tutor.getDireccion_tutor()+ "',"
                    + "email_tutor='"+ tutor.getEmail() + "',"
                    + "fecha_nac_tutor='"+ tutor.getFecha_nac_tutor()+ "',"
                    + "profesion_tutor='"+ tutor.getProfesion_tutor()+ "',"
                    + "ocupacion_tutor='"+ tutor.getOcupacion_tutor()+ "',"
                    + "direccion_laboral_tutor='"+ tutor.getDireccion_laboral_tutor()+ "',"
                    + "telefono_laboral_tutor='"+ tutor.getTelefono_laboral_tutor()+ "',"
                    + "id_nacionalidad='"+ tutor.getNacionalidad().getId_nacionalidad()+ "',"
                    + "id_ciudad='"+ tutor.getCiudad().getId_ciudad() + "',"
                    + "id_estadocivil='"+ tutor.getEstadocivil().getId_estadocivil()+ "',"
                    + "id_parentesco='"+ tutor.getParentesco().getId_parentesco()+ "'"
                    + " where id_tutor='" + tutor.getId_tutor()+"'";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TutoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Tutores tutor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from tutores where id_tutor=" + tutor.getId_tutor();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TutoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Tutores buscarId(Tutores tutor) {
        if (Conexion.conectar()){
  
            String sql = "select * from tutores t,nacionalidades n,ciudades c,estadosciviles ec, parentescos p"
                    + " where "
                    + "t.id_nacionalidad = n.id_nacionalidad"
                    + " and "
                    + "t.id_ciudad = c.id_ciudad"
                    + " and "
                    + "t.id_estadocivil = ec.id_estadocivil"
                    + " and "
                    + "t.id_parentesco = p.id_parentesco"
                    + " and "
                    + "id_tutor = '"+tutor.getId_tutor()+"'";
           // System.out.println("SQL : "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    
                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                    
                    Parentescos parentesco = new Parentescos();
                    parentesco.setId_parentesco(rs.getInt("id_parentesco"));
                    parentesco.setNombre_parentesco(rs.getString("nombre_parentesco"));
                                        
                    tutor.setId_tutor(rs.getInt("id_tutor"));
                    tutor.setNombre_tutor(rs.getString("nombre_tutor"));
                    tutor.setApellido_tutor(rs.getString("apellido_tutor"));
                    tutor.setRuc_tutor(rs.getString("ruc_tutor"));
                    tutor.setTelefono_tutor(rs.getInt("telefono_tutor"));
                    tutor.setDireccion_tutor(rs.getString("direccion_tutor"));
                    tutor.setEmail(rs.getString("email_tutor"));
                    
                    tutor.setFecha_nac_tutor(rs.getDate("fecha_nac_tutor"));
                    tutor.setProfesion_tutor(rs.getString("profesion_tutor"));
                    tutor.setOcupacion_tutor(rs.getString("ocupacion_tutor"));
                    tutor.setDireccion_laboral_tutor(rs.getString("direccion_laboral_tutor"));
                    tutor.setTelefono_laboral_tutor(rs.getString("telefono_laboral_tutor"));
                    
                    tutor.setNacionalidad(nacionalidad);
                    tutor.setCiudad(ciudad);
                    tutor.setEstadocivil(estadocivil);
                    tutor.setParentesco(parentesco);
                    
                } else {
                   Nacionalidades nacionalidad = new Nacionalidades();
                   Ciudades ciudad = new Ciudades();
                   EstadosCiviles estadocivil = new EstadosCiviles();
                   Parentescos parentesco = new Parentescos();
                    
                    tutor.setId_tutor(0);
                    tutor.setNombre_tutor("");
                    tutor.setApellido_tutor("");
                    tutor.setRuc_tutor("");
                    tutor.setTelefono_tutor(0);
                    tutor.setDireccion_tutor("");
                    tutor.setEmail("");
                    
                    tutor.setFecha_nac_tutor(null);
                    tutor.setProfesion_tutor("");
                    tutor.setOcupacion_tutor("");
                    tutor.setDireccion_laboral_tutor("");
                    tutor.setTelefono_laboral_tutor("");
                    
                    nacionalidad.setId_nacionalidad(0);
                    nacionalidad.setNombre_nacionalidad("");
                    
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");  
                    
                    parentesco.setId_parentesco(0);
                    parentesco.setNombre_parentesco("");
                   
                    tutor.setNacionalidad(nacionalidad);
                    tutor.setCiudad(ciudad);
                    tutor.setEstadocivil(estadocivil);
                    tutor.setParentesco(parentesco);
                   
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return tutor;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from tutores where upper(nombre_tutor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_tutor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tutor") + "</td>"
                                + "<td>" + rs.getString("nombre_tutor") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
