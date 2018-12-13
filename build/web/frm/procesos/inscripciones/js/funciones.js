/*function fechaHoy(){
var hoy = new  new Date().toJSON().slice(0,10);
console.log(hoy);
 $("#fecha_inscripcion").val(hoy);
}*/


function agregarInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_inscripcion").focus();
            $("#id_inscripcion").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_inscripcion").focus();
        }
    });
}

function modificarInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_inscripcion").focus();
            $("#id_inscripcion").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_inscripcion").focus();
            $("#id_inscripcion").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
                
            }
        }
    });
}

function buscarIdInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#fecha_inscripcion").val(json.fecha_inscripcion);
            $("#vencimientocuota_inscripcion").val(json.vencimientocuota_inscripcion);            
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);            
            $("#id_convocatoria").val(json.id_convocatoria);            
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);            
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#codigo_convocatoria").val(json.codigo_convocatoria);
            $("#nro_cuotas").val(json.nro_cuotas);            
           
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
          siguienteCampo("#fecha_inscripcion", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#fecha_inscripcion", "#botonModificar", true);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreInscripcion() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombre.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_inscripcion").val(id);
              $("#nombre_alumno").focus();
              buscarIdInscripcion();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

// ALUMNOS

function buscarIdAlumno() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAlumno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);
            $("#fecha_nac_alumno").val(json.fecha_nac_alumno);            
            $("#nombre_medico").val(json.nombre_medico);
            $("#telefono_medico").val(json.telefono_medico);                        
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
            
            
           // console.log(json.nuevo);
           /* if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
            siguienteCampo("#nombre_alumno", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_alumno", "#botonModificar", true);
           }*/
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreAlumno() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreAlumno.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_alumno").val(id);
              $("#nombre_alumno").focus();
                buscarIdAlumno();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}



function limpiarFormulario() {
        $("#id_inscripcion").val("");
        $("#fecha_inscripcion").val("");  
        $("#vencimientocuota_inscripcion").val("");  
        $("#id_alumno").val("");
        $("#nombre_alumno").val("");
        $("#apellido_alumno").val("");
        $("#nroci_alumno").val("");
        
        $("#id_convocatoria").val("");        
        $("#id_curso").val("");
        $("#nombre_curso").val("");        
        $("#id_turno").val("");
        $("#nombre_turno").val("");
        $("#nro_cuotas").val("");
        $("#codigo_convocatoria").val("");
}




// CONVOCATORIA

function buscarIdConvocatoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").val(json.id_convocatoria);                      
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
           // $("#monto_convocatoria").val(json.monto_convocatoria);            
           $("#codigo_convocatoria").val(json.codigo_convocatoria);
           // $("#cupos_convocatoria").val(json.cupos_convocatoria);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdConvocatoriaCodigo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoriaCodigo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").val(json.id_convocatoria);                      
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
           // $("#monto_convocatoria").val(json.monto_convocatoria);            
          $("#codigo_convocatoria").val(json.codigo_convocatoria);
           // $("#cupos_convocatoria").val(json.cupos_convocatoria);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}



function buscarNombreConvocatoria() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_convocatoria").val(id);
                $("#nombre_curso").focus();
                // TURNO ADD
                buscarIdConvocatoria();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function validarFormulario(){
    var valor=true;
    if ($("#id_inscripcion").val().trim()===""){
        valor=false;
        $("#mensajes").html("Por favor rellene los campos");
        $("#id_inscripcion").focus();
    }else if ($("#fecha_inscripcion").val().trim()===""){
        valor=false;
        $("#mensajes").html("El campo fecha de inscripcion no puede estar vacio");
        $("#fecha_inscripcion").focus();
    }else if ($("#vencimientocuota_inscripcion").val().trim()===""){
        valor=false;
        $("#mensajes").html("El campo fecha de vencimiento no puede estar vacio");
        $("#vencimientocuota_inscripcion").focus();
    }else if ($("#id_alumno").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("El campo ID de Alumno no puede estar vacio");
        $("#id_alumno").focus();
    }else if ($("#id_alumno").val().trim()===""){
        valor=false;
        $("#mensajes").html("El campo ID de Alumno no puede estar vacio");
        $("#id_alumno").focus();
    } else if ($("#id_convocatoria").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Por favor ingrese el plan al que desea inscribir");
        $("#id_convocatoria").focus();
    }  else if ($("#id_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Por favor ingrese el plan al que desea inscribir");
        $("#id_convocatoria").focus();
    } else if ($("#nro_cuotas").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Por favor ingrese la cantidad de cuotas para la inscripcion");
        $("#nro_cuotas").focus();
    } else if ($("#nro_cuotas").val().trim()===""){
        valor=false;
        $("#mensajes").html("Por favor ingrese la cantidad de cuotas para la inscripcion");
        $("#nro_cuotas").focus();
    } 
    
    return valor;
}