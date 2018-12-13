package controladores;
import java.sql.Connection;
import modelos.Inscripciones;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Alumnos;
import modelos.Convocatorias;
import modelos.Cursos;
import modelos.Turnos;


public class InscripcionesControlador {
    
     public static boolean agregar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into inscripciones ("
                    + "fecha_inscripcion"
                    + ",id_alumno"
                    + ",id_convocatoria"
                    + ",nro_cuotas"
                    + ",vencimientocuota_inscripcion)" 
                    + " values ('" + inscripcion.getFecha_inscripcion()+ "','"
                    + inscripcion.getAlumno().getId_alumno() + "','"
                    + inscripcion.getConvocatoria().getId_convocatoria()+ "','" 
                    + inscripcion.getNro_cuotas()+ "','" 
                    + inscripcion.getVencimientocuota_inscripcion()+ "')";
            
                    System.out.println("sql agregar: "+ sql);
                    
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);                
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();                                 
                if (keyset.next()) {
                    int id_inscripcion = keyset.getInt(1);
                    inscripcion.setId_inscripcion(id_inscripcion);
                    // Cannot commit when autoCommit is enabled.
                    //Conexion.getConn().commit();
                    Conexion.getConn().setAutoCommit(false);
                    // Cannot commit when autoCommit is enabled.
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
     
     public static boolean modificar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update inscripciones set "
                    + "fecha_inscripcion='" + inscripcion.getFecha_inscripcion()+ "',"
                    + "id_alumno='"+ inscripcion.getAlumno().getId_alumno() + "',"
                    + "id_convocatoria='"+ inscripcion.getConvocatoria().getId_convocatoria()+ "',"
                    + "vencimientocuota_inscripcion='"+ inscripcion.getVencimientocuota_inscripcion()+ "',"
                    + "nro_cuotas='"+ inscripcion.getNro_cuotas()+ "'"
                    + " where id_inscripcion=" + inscripcion.getId_inscripcion();
                    System.out.println("sql modificar: "+sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from inscripciones where id_inscripcion=" + inscripcion.getId_inscripcion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Inscripciones buscarId(Inscripciones inscripcion) {
        if (Conexion.conectar()){
            String sql = "select * from inscripciones i,"
                    + "alumnos a,"
                    + "convocatorias cv,"
                    + "cursos c, turnos t"
                    + " where "
                    + "i.id_alumno=a.id_alumno"
                    + " and "
                    + "i.id_convocatoria=cv.id_convocatoria"
                    + " and "
                    + "cv.id_curso=c.id_curso"
                    + " and "
                    + "cv.id_turno=t.id_turno"
                    + " and "                    
                    + " id_inscripcion ='"+inscripcion.getId_inscripcion()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                    inscripcion.setFecha_inscripcion(rs.getDate("fecha_inscripcion"));
                    inscripcion.setVencimientocuota_inscripcion(rs.getDate("vencimientocuota_inscripcion"));
                    
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(rs.getInt("id_alumno"));
                    alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                    alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                    alumno.setNroci_alumno(rs.getInt("nroci_alumno"));
                    
                    Cursos curso = new Cursos();
                    curso.setId_curso(rs.getInt("id_curso"));
                    curso.setNombre_curso(rs.getString("nombre_curso"));                    
                    Turnos turno = new Turnos();
                    turno.setId_turno(rs.getInt("id_turno"));
                    turno.setNombre_turno(rs.getString("nombre_turno"));
                    
                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));                    
                    convocatoria.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));
                    convocatoria.setTurno(turno);
                    convocatoria.setCurso(curso);
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);                    
                    inscripcion.setNro_cuotas(rs.getInt("nro_cuotas"));
                    inscripcion.setFecha_inscripcion(rs.getDate("fecha_inscripcion"));
                } else {    
                   inscripcion.setId_inscripcion(0);
                   //Obtengo la fecha actual para luego asignarla si no se encuentra creada la inscripcion
                   java.sql.Date fecha_inscripcion = new java.sql.Date(new java.util.Date().getTime());
                   inscripcion.setFecha_inscripcion(fecha_inscripcion);
                   //**//
                   inscripcion.setVencimientocuota_inscripcion(null);                    
                   
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(0);
                    alumno.setNombre_alumno("");
                    alumno.setApellido_alumno("");
                    alumno.setNroci_alumno(0);                   
                    
                    Cursos curso = new Cursos();
                    curso.setId_curso(0);
                    curso.setNombre_curso("");                    
                    Turnos turno = new Turnos();
                    turno.setId_turno(0);
                    turno.setNombre_turno("");
                    
                    Convocatorias  convocatoria= new Convocatorias();
                    convocatoria.setId_convocatoria(0);
                    convocatoria.setCurso(curso);
                    convocatoria.setTurno(turno);
                    
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);   
                    inscripcion.setNro_cuotas(0);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return inscripcion;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from inscripciones i,alumnos a, convocatorias cv, cursos c, turnos t"
                        + " where "
                        + "i.id_alumno = a.id_alumno"
                        + " and " 
                        + "i.id_convocatoria = cv.id_convocatoria"
                        + " and " 
                        + "cv.id_curso = c.id_curso"
                         + " and " 
                        + "cv.id_turno = t.id_turno"
                        + " and "
                        + "(a.nombre_alumno) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_inscripcion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_Turno") + "</td>"
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
