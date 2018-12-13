function buscarCedula() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
           // $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            if (json.nroci_alumno === 0) {
                $("#mensajes").html("Ya existe el nro de cedula");
                $("#nroci_alumno").focus();
                $("#nroci_alumno").val("");
                siguienteCampo("#id_alumno", "#botonAgregar", true);
            } else {
                $("#nroci_alumno").val(json.nroci_alumno);
                $("#nombre_alumno").focus();
            }
        },
        error: function (e) {
           // $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}


function agregarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_alumno").focus();
        }
    });
}

function modificarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
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

function buscarIdAlumno() {
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
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);
            $("#fecha_nac_alumno").val(json.fecha_nac_alumno);
            $("#nombre_medico").val(json.nombre_medico);
            $("#telefono_medico").val(json.telefono_medico);
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
            $("#id_tutor").val(json.id_tutor);
            $("#nombre_tutor").val(json.nombre_tutor);

            // console.log(json.nuevo);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_alumno", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_alumno", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });
}

function buscarNombreAlumno() {
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
                $("#id_alumno").val(id);
                $("#nombre_alumno").focus();
                buscarIdAlumno();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

//sexos

function buscarIdSexo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdSexo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });
}

function buscarNombreSexo() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreSexo.jsp',
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
                $("#id_sexo").val(id);
                $("#nombre_sexo").focus();
                buscarIdSexo();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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



function limpiarFormulario() {
    $("#id_alumno").val("");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#nroci_alumno").val("");
    $("#fecha_nac_alumno").val("");
    $("#nombre_medico").val("");
    $("#telefono_medico").val("");
    $("#id_sexo").val("");
    $("#nombre_sexo").val("");

    $("#id_tutor").val("");
    $("#nombre_tutor").val("");
}



function buscarIdTutor() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTutor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tutor").val(json.id_tutor);
            $("#nombre_tutor").val(json.nombre_tutor);
            $("#apellido_tutor").val(json.apellido_tutor);
            $("#ruc_tutor").val(json.ruc_tutor);

            $("#telefono_tutor").val(json.telefono_tutor);
            $("#direccion_tutor").val(json.direccion_tutor);
            $("#email_tutor").val(json.email_tutor);

            $("#fecha_nac_tutor").val(json.fecha_nac_tutor);
            $("#profesion_tutor").val(json.profesion_tutor);
            $("#ocupacion_tutor").val(json.ocupacion_tutor);
            $("#direccion_laboral_tutor").val(json.direccion_laboral_tutor);
            $("#telefono_laboral_tutor").val(json.telefono_laboral_tutor);

            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            $("#id_parentesco").val(json.id_parentesco);
            $("#nombre_parentesco").val(json.nombre_parentesco);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });

}

function buscarNombreTutor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTutor.jsp',
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
                $("#id_tutor").val(id);
                $("#nombre_tutor").focus();
                buscarIdTutor();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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


function validarFormulario() {
    var valor = true;
    if ($("#nroci_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nro de CI no puede estar vacio");
        $("#nroci_alumno").focus();
    } else if ($("#nombre_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre del alumno no puede estar vacio");
        $("#nombre_alumno").focus();
    } else if ($("#apellido_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Apellido del alumno no puede estar vacio");
        $("#apellido_alumno").focus();
    } else if ($("#fecha_nac_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Ingrese la fecha");
        $("#fecha_nac_alumno").focus();
    } else if ($("#nombre_sexo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Favor elija el sexo del alumno");
        $("#id_sexo").focus();
    } else if ($("#nombre_tutor").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Favor elija el tutor del alumno");
        $("#nombre_tutor").focus();
    }
    return valor;
}
