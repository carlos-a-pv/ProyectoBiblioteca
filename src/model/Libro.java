package model;

import java.time.LocalDate;

public class Libro {

    private Autor autor;
    private LocalDate fechaPublicacion;
    private Tema temario;

    public Libro(Autor autor, LocalDate fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
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
}

