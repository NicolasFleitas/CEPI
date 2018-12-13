
<%@page import="controladores.TutoresControlador"%>
<%@page import="modelos.Tutores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    
    int id_tutor = Integer.parseInt(request.getParameter("id_tutor"));
    
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Tutores tutor = new Tutores();
    tutor.setId_tutor(id_tutor);
    
   TutoresControlador.buscarId(tutor);
    if (tutor.getId_tutor()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }/*else {
        tutor = new Tutores();
    }*/ 
    //ya esta en el controlador
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_tutor", tutor.getId_tutor());
    obj.put("nombre_tutor", tutor.getNombre_tutor());
    obj.put("apellido_tutor", tutor.getApellido_tutor());
    obj.put("ruc_tutor", tutor.getRuc_tutor());
    obj.put("direccion_tutor", tutor.getDireccion_tutor());
    obj.put("telefono_tutor", tutor.getTelefono_tutor());
    obj.put("email_tutor", tutor.getEmail());    
    obj.put("fecha_nac_tutor", String.valueOf( tutor.getFecha_nac_tutor()));
    obj.put("profesion_tutor", tutor.getProfesion_tutor());
    obj.put("ocupacion_tutor", tutor.getOcupacion_tutor());
    obj.put("direccion_laboral_tutor", tutor.getDireccion_laboral_tutor());
    obj.put("telefono_laboral_tutor", tutor.getTelefono_laboral_tutor());
    
    obj.put("id_nacionalidad",tutor.getNacionalidad().getId_nacionalidad());
    obj.put("nombre_nacionalidad",tutor.getNacionalidad().getNombre_nacionalidad());
    
    obj.put("id_ciudad", tutor.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", tutor.getCiudad().getNombre_ciudad());    
    
    obj.put("id_estadocivil", tutor.getEstadocivil().getId_estadocivil());
    obj.put("nombre_estadocivil", tutor.getEstadocivil().getNombre_estadocivil());
    
    obj.put("id_parentesco", tutor.getParentesco().getId_parentesco());
    obj.put("nombre_parentesco", tutor.getParentesco().getNombre_parentesco());
    

           
    out.print(obj);
    out.flush();
%>
