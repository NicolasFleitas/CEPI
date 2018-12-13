
<%@page import="controladores.TurnosControlador"%>
<%@page import="modelos.Turnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_turno = Integer.parseInt(request.getParameter("id_turno"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);
    
    if (TurnosControlador.eliminar(turno)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>