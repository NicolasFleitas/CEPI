package controladores;
import modelos.Profesores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ProfesoresControlador {
    public static boolean agregar(Profesores profesor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into profesores (nombre_profesor,apellido_profesor,nroci_profesor,telefono_profesor)" 
                    + " values ('" + profesor.getNombre_profesor() + "','"
                    + profesor.getApellido_profesor() + "','"
                    + profesor.getNroci_profesor()+ "','"
                    + profesor.getTelefono_profesor() + "')";
                  
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProfesoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        
        return valor;
        
    }
    
    public static boolean modificar(Profesores profesor){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update profesores set nombre_profesor='" + profesor.getNombre_profesor() + "',"
                    + "apellido_profesor='" + profesor.getApellido_profesor() + "',"
                    + "nroci_profesor='" + profesor.getNroci_profesor()+ "',"
                    + "telefono_profesor='" + profesor.getTelefono_profesor()+ "'"
                    
                    + " where id_profesor=" + profesor.getId_profesor();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProfesoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Profesores profesor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from profesores where id_profesor=" + profesor.getId_profesor();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProfesoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Profesores buscarId(Profesores profesor) {
        if (Conexion.conectar()){
            String sql = "select * from profesores p"
                    + " where "
                   
                    + "p.id_profesor ='"+profesor.getId_profesor()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    
                    profesor.setId_profesor(rs.getInt("id_profesor"));
                    profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                    profesor.setApellido_profesor(rs.getString("apellido_profesor"));
                    profesor.setNroci_profesor(rs.getString("nroci_profesor"));
                    profesor.setTelefono_profesor(rs.getString("telefono_profesor"));
                    
                    
                } else {
                  
                    profesor.setId_profesor(0);
                    profesor.setNombre_profesor("");
                    profesor.setApellido_profesor("");
                    profesor.setNroci_profesor("");
                    profesor.setTelefono_profesor("");
                   ;
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return profesor;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from profesores where upper(nombre_profesor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_profesor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_profesor") + "</td>"
                                + "<td>" + rs.getString("nombre_profesor") + "</td>"
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
