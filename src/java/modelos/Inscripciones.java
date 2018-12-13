package modelos;

import java.sql.Date;

public class Inscripciones {
    private int id_inscripcion;
    private Date fecha_inscripcion;
    private Date vencimientocuota_inscripcion;
    private Alumnos alumno;
    private Convocatorias convocatoria;
    private int nro_cuotas;

    public Inscripciones() {
    }

    public Inscripciones(int id_inscripcion, Date fecha_inscripcion, Date vencimientocuota_inscripcion, Alumnos alumno, Convocatorias convocatoria, int nro_cuotas) {
        this.id_inscripcion = id_inscripcion;
        this.fecha_inscripcion = fecha_inscripcion;
        this.vencimientocuota_inscripcion = vencimientocuota_inscripcion;
        this.alumno = alumno;
        this.convocatoria = convocatoria;
        this.nro_cuotas = nro_cuotas;
    }


    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public Date getVencimientocuota_inscripcion() {
        return vencimientocuota_inscripcion;
    }

    public void setVencimientocuota_inscripcion(Date vencimientocuota_inscripcion) {
        this.vencimientocuota_inscripcion = vencimientocuota_inscripcion;
    }



    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public int getNro_cuotas() {
        return nro_cuotas;
    }

    public void setNro_cuotas(int nro_cuotas) {
        this.nro_cuotas = nro_cuotas;
    }

    

}



