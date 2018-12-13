<%@page import="utiles.Utiles"%>
<%@page import="java.sql.Date"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.Alumnos"%>
<%@page import="controladores.InscripcionesControlador"%>
<%@page import="modelos.Inscripciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int nro_cuotas = Integer.parseInt(request.getParameter("nro_cuotas"));
    
    String sfecha_inscripcion = request.getParameter("fecha_inscripcion");
    String svencimientocuota_inscripcion = request.getParameter("vencimientocuota_inscripcion");
    
    java.sql.Date fecha_inscripcion = Utiles.stringToSqlDate(sfecha_inscripcion);
    java.sql.Date vencimientocuota_inscripcion = Utiles.stringToSqlDate(svencimientocuota_inscripcion);
       
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Alumnos alumno = new Alumnos();
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    alumno.setId_alumno(id_alumno);
    
    Convocatorias convocatoria = new Convocatorias();
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion.setFecha_inscripcion(fecha_inscripcion);
    inscripcion.setVencimientocuota_inscripcion(vencimientocuota_inscripcion);
    inscripcion.setAlumno(alumno);
    inscripcion.setConvocatoria(convocatoria);
    inscripcion.setNro_cuotas(nro_cuotas);    
        
    if (InscripcionesControlador.modificar(inscripcion)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
