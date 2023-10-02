package model;

import java.time.LocalDate;
import java.util.HashSet;

public class Estudiante implements Comparable <Estudiante> {
    private String nombre;
    private String apellido;
    private String id;

    public Estudiante(String nombre, String apellido, String id) {
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

    @Override
    public int compareTo(Estudiante o) {
        if(o.nombre.compareTo(this.nombre) > 0){
            return 1;
        }else if(o.nombre.compareTo(this.nombre) < 0){
            return -1;
        }
        return 0;
    }
}
