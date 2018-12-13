
<%@page import="modelos.Alumnos"%>
<%@page import="controladores.FichasMedicasControlador"%>
<%@page import="modelos.FichasMedicas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fichamedica = Integer.parseInt(request.getParameter("id_fichamedica"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    FichasMedicas fichamedica = new FichasMedicas();
    fichamedica.setId_fichamedica(id_fichamedica);
    
    
    
   FichasMedicasControlador.buscarId(fichamedica);
    if (fichamedica.getId_fichamedica()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {  
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_fichamedica", fichamedica.getId_fichamedica());    
    obj.put("id_alumno", fichamedica.getAlumno().getId_alumno());    
    obj.put("nombre_alumno", fichamedica.getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", fichamedica.getAlumno().getApellido_alumno());
    obj.put("fecha_nac_alumno", fichamedica.getAlumno().getFecha_nac_alumno());    
    obj.put("peso_actual", fichamedica.getPeso_actual());
    obj.put("peso_nacimiento", fichamedica.getPeso_nacimiento());    
    obj.put("tipo_parto", fichamedica.getTipo_parto());    
    obj.put("descripcion_embarazo", fichamedica.getDescrip_embarazo());    
    obj.put("descripcion_alergias", fichamedica.getDescrip_alergias());    
    obj.put("descripcion_vacunas", fichamedica.getDescrip_vacunas());
    obj.put("descripcion_enfermedades", fichamedica.getDescrip_enfermedades());
    obj.put("obs_importantes", fichamedica.getObs_importantes());
    out.print(obj);
    out.flush();
%>

