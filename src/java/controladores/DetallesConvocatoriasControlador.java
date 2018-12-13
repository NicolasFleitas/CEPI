
package controladores;



import modelos.Profesores;
import modelos.Turnos;
import modelos.Convocatorias;
import modelos.DetallesConvocatorias;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class DetallesConvocatoriasControlador {
    
     public static DetallesConvocatorias buscarId(DetallesConvocatorias detalleconvocatoria) {
        if (Conexion.conectar()){
             String sql = "select * from "
                             + "detallesconvocatorias dcv"
                             + ",convocatorias cv"
                             + ",profesores p"
                             + " where "
                             + "dcv.id_convocatoria=cv.id_convocatoria"
                             + " and "                            
                             + "p.id_profesor=dcv.id_profesor"
                             + " and "
                             + "dcv.id_detalleconvocatoria='"+detalleconvocatoria.getId_detalleconvocatoria()+"'";
                
                System.out.println("SQL : " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                        
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));                                                
                        
                        Profesores profesor = new Profesores();
                        profesor.setId_profesor(rs.getInt("id_profesor"));
                        profesor.setNombre_profesor(rs.getString("nombre_profesor")); 
                      
                        detalleconvocatoria.setProfesor(profesor);           
                } else {
                    detalleconvocatoria.setId_detalleconvocatoria(0); 
                    
                    Profesores profesor = new Profesores();
                    profesor.setId_profesor(0);
                    
                    detalleconvocatoria.setProfesor(profesor); 
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return detalleconvocatoria;
    }
    
    
    
    public static DetallesConvocatorias buscarId2(DetallesConvocatorias detalleconvocatoria) {
        
        if (Conexion.conectar()) {
            
                String sql = "select * , p.id_profesor as plaga from "
                             + "detallesconvocatorias dcv"
                             + ",convocatorias cv"
                             + ",profesores p"
                             + " where "
                             + "dcv.id_convocatoria=cv.id_convocatoria"
                             + " and "                            
                             + "p.id_profesor=dcv.id_profesor"
                             + " and "
                             + "dcv.id_detalleconvocatoria='"+detalleconvocatoria.getId_detalleconvocatoria()+"'";
                
                System.out.println("SQL : " + sql);
                
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                   
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleconvocatoria = new DetallesConvocatorias();                        
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));                                                
                        
                        Profesores profesor = new Profesores();
                        profesor.setId_profesor(rs.getInt("plaga"));
                       // profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                        
                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));                        
                        
                        detalleconvocatoria.setConvocatoria(convocatoria);
                        detalleconvocatoria.setProfesor(profesor);                        
                    }
                  
              
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
   
        return detalleconvocatoria;
    }
    //
    
    public static String buscarIdConvocatoria(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesconvocatorias dcv"+
                        ",convocatorias cv"
                        + ",profesores p"
                        + " where "
                        + "cv.id_convocatoria=dcv.id_convocatoria"
                        + " and "
                        + "p.id_profesor=dcv.id_profesor"
                        + " and "
                        + "dcv.id_convocatoria="+id+" "
                        + "order by dcv.id_detalleconvocatoria";
                System.out.println("ID CONV EN DETALLE> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                   // DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                     //   BigDecimal cantidad = rs.getBigDecimal();
                       // total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleconvocatoria") + "</td>"
                               + "<td>" + rs.getString("id_profesor") + "</td>"
                               + "<td>" + rs.getString("nombre_profesor") + "</td>"                                
                             //  + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_detalleconvocatoria")+")'"                                
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else/*{
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
                    }*/
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

    //*-**************************************************
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesconvocatorias dcv "+
                        "left join convocatorias cv on cv.id_convocatoria=dcv.id_convocatoria "+
                        "left join profesores p on p.id_profesor=dcv.id_profesor "+                        
                        "where upper(p.nombre_profesor) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_detalleconvocatoria "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleconvocatoria") + "</td>"
                               + "<td>" + rs.getString("id_convocatoria") + "</td>"
                               + "<td>" + rs.getString("id_profesor") + "</td>"
                               + "<td>" + rs.getString("nombre_profesor") + "</td>"                                                             
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesConvocatorias detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesconvocatorias "
                    + "(id_convocatoria,id_profesor) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getConvocatoria().getId_convocatoria());
                ps.setInt(2, detalleconvocatoria.getProfesor().getId_profesor());
             
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

    public static boolean modificar(DetallesConvocatorias detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesconvocatorias set "
                    + "id_convocatoria=?,"
                    + "id_profesor=?"                    
                    + " where id_detalleconvocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getConvocatoria().getId_convocatoria());
                ps.setInt(2, detalleconvocatoria.getProfesor().getId_profesor());              
                ps.setInt(3,detalleconvocatoria.getId_detalleconvocatoria());
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

    public static boolean eliminar(DetallesConvocatorias detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesconvocatorias where id_detalleconvocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getId_detalleconvocatoria());
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
    
    //*********************************** 
    
    public static boolean eliminarProfesorConvocatoria(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesconvocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + convocatoria.getId_convocatoria());
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
