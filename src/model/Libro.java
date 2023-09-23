package model;

import java.time.LocalDate;

public class Libro {

    private Autor autor;
    private String nombre;
    private LocalDate fechaPublicacion;
    private Tema temario;
    private EstadoLibro estado;

    public Libro(String nombre, Autor autor){
        this.nombre = nombre;
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Tema getTemario() {
        return temario;
    }

    public void setTemario(Tema temario) {
        this.temario = temario;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

