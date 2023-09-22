package model;

import java.util.ArrayList;

public class Autor {
    private String nombre;
    private String apellido;
    private String id;
    private ArrayList<Libro> librosEscritos;

    public Autor(String nombre, String apellido, String id){
        this.nombre =nombre;
        this.apellido = apellido;
        this.id = id;
        this.librosEscritos = new ArrayList<>();
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

    public ArrayList<Libro> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(ArrayList<Libro> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }
}
