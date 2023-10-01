package model;

import java.time.LocalDate;
import java.util.HashSet;

public class Estudiante implements Comparable <Estudiante> {
    private String nombre;
    private String apellido;
    private String id;
    private HashSet <Libro> librosPrestados;


    public HashSet<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(HashSet<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public Estudiante(String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        librosPrestados = new HashSet<>();
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



    @Override
    public int compareTo(Estudiante o) {
        return this.getNombre().compareTo(o.getNombre());
    }
}
