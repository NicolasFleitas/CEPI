
<%@page import="controladores.PermisosControlador"%>
<%@page import="modelos.Formularios"%>
<%@page import="modelos.Roles"%>
<%@page import="modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Permisos permiso = PermisosControlador.buscarId(id_permiso);
    if(permiso!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        Roles rol = new Roles();
        Formularios formulario = new Formularios();
        permiso = new Permisos();
        permiso.setRol(rol);
        permiso.setFormulario(formulario);
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_rol", permiso.getRol().getId_rol());
    obj.put("nombre_rol", permiso.getRol().getNombre_rol());
    obj.put("id_formulario", permiso.getFormulario().getId_formulario());
    obj.put("nombre_formulario", permiso.getFormulario().getNombre_formulario());
    
    out.print(obj);
    out.flush();
%>