
package modelos;

public class Parentescos {
    private int id_parentesco;
    private String nombre_parentesco;

    public Parentescos() {
    }

    public Parentescos(int id_parentesco, String nombre_parentesco) {
        this.id_parentesco = id_parentesco;
        this.nombre_parentesco = nombre_parentesco;
    }
    
    

    public int getId_parentesco() {
        return id_parentesco;
    }

    public void setId_parentesco(int id_parentesco) {
        this.id_parentesco = id_parentesco;
    }

    public String getNombre_parentesco() {
        return nombre_parentesco;
    }

    public void setNombre_parentesco(String nombre_parentesco) {
        this.nombre_parentesco = nombre_parentesco;
    }

        
    
}
