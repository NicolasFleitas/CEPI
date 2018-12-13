
<%@page import="controladores.FichasMedicasControlador"%>
<%@page import="modelos.FichasMedicas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fichamedica = Integer.parseInt(request.getParameter("id_fichamedica"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    FichasMedicas fichamedica = new FichasMedicas();
    fichamedica.setId_fichamedica(id_fichamedica);
    
    if (FichasMedicasControlador.eliminar(fichamedica)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>