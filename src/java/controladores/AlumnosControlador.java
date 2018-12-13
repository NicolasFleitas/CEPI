/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Alumnos;
import modelos.Sexos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Tutores;

public class AlumnosControlador {
    
     public static Alumnos buscarCedula(Alumnos alumno) {
        if (Conexion.conectar()) {
            String sql = "select * from alumnos where nroci_alumno='" + alumno.getNroci_alumno()+ "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    alumno.setNroci_alumno(0);
                } else {
                    alumno.setNroci_alumno(alumno.getNroci_alumno());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return alumno;
    }
    
    public static boolean agregar(Alumnos alumno){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into alumnos (nombre_alumno , apellido_alumno , nroci_alumno , fecha_nac_alumno,nombre_medico,telefono_medico,id_sexo,id_tutor)" 
                    + "values ('" + alumno.getNombre_alumno() + "','"
                    + alumno.getApellido_alumno() + "','"
                    + alumno.getNroci_alumno()+ "','"
                    + alumno.getFecha_nac_alumno() + "','"                    
                    + alumno.getNombre_medico() + "','"
                    + alumno.getTelefono_medico() + "','"
                    + alumno.getSexo().getId_sexo() + "','" 
                    + alumno.getTutor().getId_tutor()+ "')";
                  System.out.println("sql"+ sql);  
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;        
    }
    
    public static boolean modificar(Alumnos alumno){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update alumnos set nombre_alumno='" + alumno.getNombre_alumno() + "',"
                    + "apellido_alumno='" + alumno.getApellido_alumno() + "',"
                    + "nroci_alumno='" + alumno.getNroci_alumno()+ "',"
                    + "fecha_nac_alumno='" + alumno.getFecha_nac_alumno()+ "',"                    
                    + "nombre_medico='" + alumno.getNombre_medico()+ "',"
                    + "telefono_medico='" + alumno.getTelefono_medico()+ "',"
                    + "id_sexo='" + alumno.getSexo().getId_sexo()+ "',"
                    + "id_tutor='" + alumno.getTutor().getId_tutor()+ "'"
                    + " where id_alumno=" + alumno.getId_alumno();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Alumnos alumno){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from alumnos where id_alumno=" + alumno.getId_alumno();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Alumnos buscarId(Alumnos alumno) {
        if (Conexion.conectar()){
            String sql = "select * from alumnos a, sexos s, tutores t"
                    + " where "
                    + "a.id_sexo = s.id_sexo"
                    + " and "
                    + "a.id_tutor = t.id_tutor"
                    + " and "
                    + "id_alumno ='"+alumno.getId_alumno()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    alumno.setId_alumno(rs.getInt("id_alumno"));
                    alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                    alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                    alumno.setNroci_alumno(rs.getInt("nroci_alumno"));
                    alumno.setFecha_nac_alumno(rs.getString("fecha_nac_alumno"));                    
                    alumno.setNombre_medico(rs.getString("nombre_medico"));
                    alumno.setTelefono_medico(rs.getInt("telefono_medico"));    
                    
                    Sexos sexo = new Sexos();
                    sexo.setId_sexo(rs.getInt("id_sexo"));
                    sexo.setNombre_sexo(rs.getString("nombre_sexo"));
                    
                    Tutores tutor = new Tutores();
                    tutor.setId_tutor(rs.getInt("id_tutor"));                    
                    tutor.setNombre_tutor(rs.getString("nombre_tutor"));
                    
                    alumno.setTutor(tutor);
                    alumno.setSexo(sexo);
                } else {                                     
                    alumno.setId_alumno(0);
                    alumno.setNombre_alumno("");
                    alumno.setApellido_alumno("");
                    alumno.setFecha_nac_alumno(null);
                    alumno.setNombre_medico("");
                    alumno.setTelefono_medico(0);
                    Sexos sexo = new Sexos();
                    sexo.setId_sexo(0);
                    sexo.setNombre_sexo("");
                    
                    Tutores tutor = new Tutores();
                    tutor.setId_tutor(0);
                    tutor.setNombre_tutor("");                    
                    alumno.setSexo(sexo);
                    alumno.setTutor(tutor);
                }
                
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return alumno;
    }
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from alumnos where  upper(nombre_alumno) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_alumno offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
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
    
    
    // 
    
}
