/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.FichasMedicas;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Alumnos;


public class FichasMedicasControlador {
    
    
     public static boolean agregar(FichasMedicas fichamedica){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into fichasmedicas (id_alumno,"
                    + "peso_actual,"
                    + "peso_nacimiento,"
                    + "tipo_parto,"
                    + "descripcion_embarazo,"
                    + "descripcion_vacunas,"
                    + "descripcion_alergias,"
                    + "descripcion_enfermedades,"
                    + "obs_importantes) " 
                    + "values ('" 
                    + fichamedica.getAlumno().getId_alumno()
                    + "','" + fichamedica.getPeso_actual()
                    + "','" + fichamedica.getPeso_nacimiento()
                    + "','" + fichamedica.getTipo_parto()
                    + "','" + fichamedica.getDescrip_embarazo()
                    + "','" + fichamedica.getDescrip_vacunas()
                    + "','" + fichamedica.getDescrip_alergias()
                    + "','" + fichamedica.getDescrip_enfermedades()
                    + "','" + fichamedica.getObs_importantes()
                    + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(FichasMedicasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(FichasMedicas fichamedica){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update fichasmedicas set "
                    + "id_alumno='"+ fichamedica.getAlumno().getId_alumno()
                    + "', peso_actual='" + fichamedica.getPeso_actual()
                    + "', peso_nacimiento='" + fichamedica.getPeso_nacimiento()
                    + "', tipo_parto='" + fichamedica.getTipo_parto()
                    + "', descripcion_alergias='" + fichamedica.getDescrip_alergias()
                    + "', descripcion_embarazo='" + fichamedica.getDescrip_embarazo()
                    + "', descripcion_enfermedades='" + fichamedica.getDescrip_enfermedades()
                    + "', descripcion_vacunas='" + fichamedica.getDescrip_vacunas()
                    + "', obs_importantes='" + fichamedica.getObs_importantes()
                    + "' where id_fichamedica=" + fichamedica.getId_fichamedica();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(FichasMedicasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(FichasMedicas fichamedica){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from fichasmedicas where id_fichamedica=" + fichamedica.getId_fichamedica();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(FichasMedicasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static FichasMedicas buscarId(FichasMedicas fichamedica) {
        if (Conexion.conectar()){
            String sql = "select * from fichasmedicas fm, alumnos a"
                    + " where "
                    + "fm.id_alumno = a.id_alumno"
                    + " and "
                    + "fm.id_fichamedica ='"+fichamedica.getId_fichamedica()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    fichamedica.setId_fichamedica(rs.getInt("id_fichamedica"));
                    
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(rs.getInt("id_alumno"));
                    alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                    alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                    alumno.setFecha_nac_alumno(rs.getString("fecha_nac_alumno"));                    
                    fichamedica.setAlumno(alumno);                  
                    fichamedica.setPeso_actual(rs.getInt("peso_actual"));
                    fichamedica.setPeso_nacimiento(rs.getInt("peso_nacimiento"));                    
                    fichamedica.setTipo_parto(rs.getString("tipo_parto"));
                    fichamedica.setDescrip_alergias(rs.getString("descripcion_alergias")); 
                    fichamedica.setDescrip_embarazo(rs.getString("descripcion_embarazo"));
                    fichamedica.setDescrip_enfermedades(rs.getString("descripcion_enfermedades"));
                    fichamedica.setDescrip_vacunas(rs.getString("descripcion_vacunas"));                    
                    fichamedica.setObs_importantes(rs.getString("obs_importantes"));                    
                   
                } else {
                    fichamedica.setId_fichamedica(0);
                                        
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(0);
                    alumno.setNombre_alumno("");
                    alumno.setApellido_alumno("");
                    alumno.setFecha_nac_alumno("");
                    
                    fichamedica.setAlumno(alumno); 
                    fichamedica.setPeso_actual(0);
                    fichamedica.setPeso_nacimiento(0);
                    fichamedica.setTipo_parto("");
                    fichamedica.setDescrip_alergias("");
                    fichamedica.setDescrip_embarazo("");
                    fichamedica.setDescrip_enfermedades("");
                    fichamedica.setDescrip_vacunas("");
                    fichamedica.setObs_importantes("");
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return fichamedica;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from fichasmedicas fm,"
                        + " alumnos a where fm.id_alumno = a.id_alumno and"
                        + " upper(a.nombre_alumno) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_fichamedica offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_fichamedica") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
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
