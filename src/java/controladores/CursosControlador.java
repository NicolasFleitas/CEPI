/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author nicof
 */
import modelos.Cursos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class CursosControlador {
    
    
     public static boolean agregar(Cursos curso){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into cursos (nombre_curso)" 
                    + "values ('" + curso.getNombre_curso() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CursosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Cursos curso){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update cursos set nombre_curso='" + curso.getNombre_curso() + "'"
                    + " where id_curso=" + curso.getId_curso();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CursosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Cursos curso){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from cursos where id_curso=" + curso.getId_curso();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CursosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Cursos buscarId(Cursos curso) {
        if (Conexion.conectar()){
            String sql = "select * from cursos where id_curso ='"+curso.getId_curso()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    curso.setId_curso(rs.getInt("id_curso"));
                    curso.setNombre_curso(rs.getString("nombre_curso"));
                } else {
                    curso.setId_curso(0);
                    curso.setNombre_curso("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return curso;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from cursos where upper(nombre_curso) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_curso offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
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
