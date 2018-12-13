
<%@page import="controladores.FormulariosControlador"%>
<%@page import="modelos.Menus"%>
<%@page import="modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    String nombre_formulario = request.getParameter("nombre_formulario");
    String codigo_formulario = request.getParameter("codigo_formulario");
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
    formulario.setNombre_formulario(nombre_formulario);
    formulario.setCodigo_formulario(codigo_formulario);
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    formulario.setMenu(menu);
    
    if (FormulariosControlador.agregar(formulario)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>