
<%@page import="controladores.ProfesoresControlador"%>
<%@page import="modelos.Profesores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_profesor = Integer.parseInt(request.getParameter("id_profesor"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);
    
   ProfesoresControlador.buscarId(profesor);
    if (profesor.getId_profesor()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    /*else {
        profesor = new Profesores();
    }*/
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_profesor", profesor.getId_profesor());
    obj.put("nombre_profesor", profesor.getNombre_profesor());
    obj.put("apellido_profesor", profesor.getApellido_profesor());
    obj.put("nroci_profesor", profesor.getNroci_profesor());
    obj.put("telefono_profesor", profesor.getTelefono_profesor());    

    out.print(obj);
    out.flush();
%>
