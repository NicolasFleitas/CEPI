package modelos;


public class Menus {
    private int id_menu;
    private String codigo_menu;
    private String nombre_menu;

    public Menus() {
    }

    public Menus(int id_menu, String codigo_menu, String nombre_menu) {
        this.id_menu = id_menu;
        this.codigo_menu = codigo_menu;
        this.nombre_menu = nombre_menu;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getCodigo_menu() {
        return codigo_menu;
    }

    public void setCodigo_menu(String codigo_menu) {
        this.codigo_menu = codigo_menu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }
    
    
}
