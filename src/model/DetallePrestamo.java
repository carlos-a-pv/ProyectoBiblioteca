package model;

import java.time.LocalDate;

public class DetallePrestamo {

    private LocalDate fechaPrestamo;
    private Libro libroPrestado;
    private Estudiante estudiante;

    public DetallePrestamo(LocalDate fechaPrestamo, Libro libroPrestado, Estudiante estudiante) {
        this.fechaPrestamo = fechaPrestamo;
        this.libroPrestado = libroPrestado;
        this.estudiante = estudiante;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Libro getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Libro libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
