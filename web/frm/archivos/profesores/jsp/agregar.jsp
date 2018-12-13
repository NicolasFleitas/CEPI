
<%@page import="controladores.ProfesoresControlador"%>
<%@page import="modelos.Profesores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_profesor = Integer.parseInt(request.getParameter("id_profesor"));
    String nombre_profesor = request.getParameter("nombre_profesor");
    String apellido_profesor = request.getParameter("apellido_profesor");
    String nroci_profesor = request.getParameter("nroci_profesor");
    String telefono_profesor = request.getParameter("telefono_profesor");
    
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);
    profesor.setNombre_profesor(nombre_profesor);
    profesor.setApellido_profesor(apellido_profesor);
    profesor.setNroci_profesor(nroci_profesor);
    profesor.setTelefono_profesor(telefono_profesor);
    
    
    if (ProfesoresControlador.agregar(profesor)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

