
<%@page import="modelos.Roles"%>
<%@page import="controladores.UsuariosControlador"%>
<%@page import="modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    
   UsuariosControlador.buscarId(usuario);
    if (usuario.getId_usuario()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }/*else {
        usuario = new Usuarios();       
    }*/
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_usuario", usuario.getId_usuario());
    obj.put("nombre_usuario", usuario.getNombre_usuario());
    obj.put("login_usuario", usuario.getLogin_usuario());
    obj.put("password_usuario", usuario.getPassword_usuario());
    obj.put("id_rol", usuario.getRol().getId_rol());
    obj.put("nombre_rol", usuario.getRol().getNombre_rol());
    out.print(obj);
    out.flush();
%>
