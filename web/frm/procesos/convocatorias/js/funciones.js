function validarDetalle(){
    var valor = true;
    if ($("#nombre_profesor").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija un profesor para agregar al curso");
        $("#nombre_profesor").focus();    
    }
    return valor;
}

function validarFormulario() {
    var valor = true;
    if ($("#fecha_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija una fecha por favor");
        $("#fecha_convocatoria").focus();    
    }
    else if ($("#nombre_curso").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija un curso por favor");
        $("#id_curso").focus();    
    }
    else if ($("#nombre_turno").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija un turno por favor");
        $("#id_turno").focus();    
    }
    else if ($("#monto_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija el monto de la cuota por favor");
        $("#monto_convocatoria").focus();    
    }
    else if ($("#monto_convocatoria").val()==="0"){
        valor=false;
        $("#mensajes").html("Elija el monto de la cuota por favor");
        $("#monto_convocatoria").focus();    
    }else if ($("#monto_matricula").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija el monto de la matricula por favor");
        $("#monto_matricula").focus();    
    }else if ($("#monto_matricula").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Elija el monto de la matricula por favor");
        $("#monto_matricula").focus();    
    }
    else if ($("#codigo_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Escriba un codigo para la convocatoria por favor");
        $("#codigo_convocatoria").focus();    
    } else if ($("#convocatoria_estado").val().trim()===""){
        valor=false;
        $("#mensajes").html("Defina el estado de la convocatoria por favor");
        $("#convocatoria_estado").focus();    
    }/*
    else if ($("#codigo_convocatoria").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Escriba un codigo para la convocatoria por favor");
        $("#codigo_convocatoria").focus();    
    }   */          
    return valor;
}

function fechaHoy(){
var hoy = new  new Date().toJSON().slice(0,10);
console.log(hoy);
 $("#fecha_convocatoria").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdConvocatoria() {
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
            $("#id_convocatoria").val(json.id_convocatoria);                   
          //  var fecha = $("#fecha_convocatoria").serialize();
            $("#fecha_convocatoria").val(json.fecha_convocatoria);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#monto_convocatoria").val(json.monto_convocatoria);            
            $("#monto_matricula").val(json.monto_matricula);            
            $("#codigo_convocatoria").val(json.codigo_convocatoria);           
            $("#convocatoria_estado").val(json.convocatoria_estado);           
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_curso", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_curso", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

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
        url: 'jsp/buscarNombre.jsp',
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
function agregarConvocatoria() {
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            
            $("#id_convocatoria").val(json.id_convocatoria);            
            //$("#monto_convocatoria").val(json.monto_convocatoria);
            buscarIdConvocatoria();
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarConvocatoria() {
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
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarConvocatoria() {
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
            eliminarConvocatoriaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function buscarIdCurso() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCurso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);

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
function buscarNombreCurso() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCurso.jsp',
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
                $("#id_curso").val(id);
                $("#nombre_curso").focus();
                buscarIdCurso();
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
function agregarLinea() {
    $("#id_detalleconvocatoria").val("0");
    $("#id_profesor").val("0");
    $("#nombre_profesor").val("");   
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detalleconvocatoria", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleconvocatoria").val(id);
   // alert("linea" + id);    
    $("#id_profesor").val(0);
    $("#nombre_profesor").val("");    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdConvocatoriaDetalle();
    //siguienteCampo("#", "#botonModificarLinea", true);    
}
// convocatoriasprofesoress
function buscarIdConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // no hay en form linea
            // $("#id_convocatoria").val(json.id_convocatoria);
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);
            //*****
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
              alert(datosFormulario);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //var id_convocatoria = $("#id_convocatoria").val();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// profesores
function buscarIdProfesor() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProfesor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);            
            //alert(json.id_profesor);
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
function buscarNombreProfesor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProfesor.jsp',
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
                $("#id_profesor").val(id);
                $("#nombre_profesor").focus();
                buscarIdProfesor();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
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


function buscarIdTurno() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTurno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
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

function buscarNombreTurno() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreTurno.jsp',
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
              $("#id_turno").val(id);
              $("#nombre_turno").focus();
              buscarIdTurno();
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
    $("#id_convocatoria").val("0");    
    $("#fecha_convocatoria").val("");    
    $("#nombre_curso").val("");
    $("#id_curso").val("0");    
    $("#id_turno").val("0");
    $("#nombre_turno").val("");
    $("#monto_convocatoria").val("0");
    $("#codigo_convocatoria").val("");  
    $("#monto_matricula").val("0");
    
  //  $("#id_tipocconvocatoria").val("0");
}