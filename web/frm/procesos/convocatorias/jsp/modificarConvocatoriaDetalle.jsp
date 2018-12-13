<%@page import="modelos.Turnos"%>
<%@page import="controladores.DetallesConvocatoriasControlador"%>
<%@page import="modelos.Profesores"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.DetallesConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%><%
    
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));   
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_profesor = Integer.parseInt(request.getParameter("id_profesor")); 
    
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesConvocatorias detalleconvocatoria = new DetallesConvocatorias();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);    

    detalleconvocatoria.setConvocatoria(convocatoria);
    detalleconvocatoria.setProfesor(profesor);
    
    if (DetallesConvocatoriasControlador.modificar(detalleconvocatoria)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>