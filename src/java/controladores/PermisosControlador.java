package controladores;

import modelos.Formularios;
import modelos.Permisos;
import modelos.Roles;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PermisosControlador {

    public static Permisos buscarId(int id) {
        Permisos permiso = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "+
                             "left join roles r on p.id_rol=r.id_rol "+
                             "left join formularios f on p.id_formulario=f.id_formulario "+
                             "where id_permiso=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        permiso = new Permisos();
                        permiso.setId_permiso(rs.getInt("id_permiso"));
                        Roles rol = new Roles();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        permiso.setRol(rol);
                        Formularios formulario = new Formularios();
                        formulario.setId_formulario(rs.getInt("id_formulario"));
                        formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                        permiso.setFormulario(formulario);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return permiso;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "+
                        "left join roles r on p.id_rol=r.id_rol "+
                        "left join formularios f on p.id_formulario=f.id_formulario "+
                        "where upper(nombre_rol) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_permiso "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
              //  System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_permiso") + "</td>"
                               + "<td>" + rs.getString("id_rol") + "</td>"
                               + "<td>" + rs.getString("nombre_rol") + "</td>"
                               + "<td>" + rs.getString("id_formulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into permisos "
                    + "(id_rol,id_formulario) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getRol().getId_rol());
                ps.setInt(2, permiso.getFormulario().getId_formulario());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisos set "
                    + "id_rol=?, "
                    + "id_formulario=? "
                    + "where id_permiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getRol().getId_rol());
                ps.setInt(2, permiso.getFormulario().getId_formulario());
                ps.setInt(3, permiso.getId_permiso());
                ps.executeUpdate();
                ps.close();
         Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisos where id_permiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getId_permiso());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
