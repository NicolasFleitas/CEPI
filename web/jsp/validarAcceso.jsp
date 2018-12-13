
<%@page import="modelos.Usuarios"%>
<%@page import="controladores.UsuariosControlador"%>
<%@page import="modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   
    String login_usuario = request.getParameter("login_usuario");
    String password_usuario = request.getParameter("password_usuario");
    
    String acceso = "false";
   
    
    Usuarios usuario = new Usuarios(0, "", login_usuario, password_usuario, new Roles());
    if(UsuariosControlador.validarAcceso(usuario, request)!=null){
        acceso = "true";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>