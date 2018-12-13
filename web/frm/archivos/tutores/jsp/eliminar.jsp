
<%@page import="controladores.TutoresControlador"%>
<%@page import="modelos.Tutores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tutor = Integer.parseInt(request.getParameter("id_tutor"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Tutores tutor = new Tutores();
    tutor.setId_tutor(id_tutor);
    
    if (TutoresControlador.eliminar(tutor)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>