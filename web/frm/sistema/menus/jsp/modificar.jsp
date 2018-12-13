
<%@page import="controladores.MenusControlador"%>
<%@page import="modelos.Menus"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));
    String nombre_menu = request.getParameter("nombre_menu");
    String codigo_menu = request.getParameter("codigo_menu");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    menu.setNombre_menu(nombre_menu);
    menu.setCodigo_menu(codigo_menu);
    
    if (MenusControlador.modificar(menu)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>