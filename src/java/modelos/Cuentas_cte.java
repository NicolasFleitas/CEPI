
package modelos;

public class Cuentas_cte {
    private int id_cuenta;
    private int monto_cuota;
    private String fecha_vencimiento;    
    private Inscripciones inscripcion;

    public Cuentas_cte(int id_cuenta, int monto_cuota, String fecha_vencimiento, Inscripciones inscripcion) {
        this.id_cuenta = id_cuenta;
        this.monto_cuota = monto_cuota;
        this.fecha_vencimiento = fecha_vencimiento;
        this.inscripcion = inscripcion;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(int monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }
    
    
}


