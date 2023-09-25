package model;

public class Bibliotecario implements Comparable <Bibliotecario> {

    private String nombre;
    private String apellido;
    private String id;

    public Bibliotecario(String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void prestarLibro( ){}
    public void recibirLibro( ){}

    @Override
    public int compareTo(Bibliotecario o) {
        return this.getNombre().compareTo(o.getNombre());
    }
}
