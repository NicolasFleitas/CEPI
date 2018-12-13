<%@page import="modelos.Turnos"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_curso = Integer.parseInt(request.getParameter("id_curso")); 
    int id_turno = Integer.parseInt(request.getParameter("id_turno")); 
    int monto_convocatoria = Integer.parseInt(request.getParameter("monto_convocatoria")); 
    String codigo_convocatoria = (request.getParameter("codigo_convocatoria"));    
    int monto_matricula = Integer.parseInt(request.getParameter("monto_matricula"));
    String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
    java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);
    String convocatoria_estado = request.getParameter("convocatoria_estado");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
        
    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);    
    convocatoria.setCurso(curso);  
    
    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);    
    convocatoria.setTurno(turno);  
    
    convocatoria.setFecha_convocatoria(fecha_convocatoria);    
    
    convocatoria.setMonto_convocatoria(monto_convocatoria);
    convocatoria.setMonto_matricula(monto_matricula);    
    convocatoria.setCodigo_convocatoria(codigo_convocatoria);    
    convocatoria.setConvocatoria_estado(convocatoria_estado);    
    if (ConvocatoriasControlador.modificar(convocatoria)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
