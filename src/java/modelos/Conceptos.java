package modelos;


public class Conceptos {
    private int id_concepto;
    private String nombre_concepto;

    public Conceptos() {
    }
    
    
    
    public Conceptos(int id_concepto, String nombre_concepto) {
        this.id_concepto = id_concepto;
        this.nombre_concepto = nombre_concepto;
    }

    public int getId_concepto() {
        return id_concepto;
    }

    public void setId_concepto(int id_concepto) {
        this.id_concepto = id_concepto;
    }

    public String getNombre_concepto() {
        return nombre_concepto;
    }

    public void setNombre_concepto(String nombre_concepto) {
        this.nombre_concepto = nombre_concepto;
    }
    
    
}
