<%@page import="utiles.Utiles"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.Alumnos"%>
<%@page import="modelos.Cuentas_cte"%>
<%@page import="controladores.InscripcionesControlador"%>
<%@page import="controladores.CuentasControlador"%>
<%@page import="modelos.Inscripciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));  
    
    String sfecha_inscripcion = request.getParameter("fecha_inscripcion");
    String svencimientocuota_inscripcion = request.getParameter("vencimientocuota_inscripcion");
    java.sql.Date fecha_inscripcion = Utiles.stringToSqlDate(sfecha_inscripcion); 
    java.sql.Date vencimientocuota_inscripcion = Utiles.stringToSqlDate(svencimientocuota_inscripcion);
    int nro_cuotas = Integer.parseInt(request.getParameter("nro_cuotas"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Alumnos alumno = new Alumnos();    
    alumno.setId_alumno(id_alumno);    
    Convocatorias convocatoria = new Convocatorias();    
    convocatoria.setId_convocatoria(id_convocatoria);    
    
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion.setFecha_inscripcion(fecha_inscripcion);
    inscripcion.setVencimientocuota_inscripcion(vencimientocuota_inscripcion); 
    inscripcion.setAlumno(alumno);
    inscripcion.setConvocatoria(convocatoria);
    inscripcion.setNro_cuotas(nro_cuotas);

if (InscripcionesControlador.agregar(inscripcion)) {
        tipo = "success";
        mensaje = "Datos agregados.";         
    }
    
   //String cuotas = CuentasControlador.agregarCuota(inscripcion.getId_inscripcion(), inscripcion.getNro_cuotas());    
        
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>