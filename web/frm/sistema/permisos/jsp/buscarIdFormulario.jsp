
<%@page import="controladores.FormulariosControlador"%>
<%@page import="modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));

   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Formularios formulario = FormulariosControlador.buscarId(id_formulario);
    if(formulario!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        formulario = new Formularios();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    
    out.print(obj);
    out.flush();
%>