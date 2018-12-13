
<%@page import="controladores.RolesControlador"%>
<%@page import="modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));

    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Roles rol = RolesControlador.buscarId(id_rol);
    if(rol!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        rol = new Roles();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("nombre_rol", rol.getNombre_rol());
    
    out.print(obj);
    out.flush();
%>