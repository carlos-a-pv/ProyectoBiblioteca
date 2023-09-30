package model;

import java.util.Random;

public class Prestamo {

    private String codigo;
    private DetallePrestamo detallePrestamo;

    public Prestamo( DetallePrestamo detallePrestamo) {
        this.codigo = String.valueOf(Math.random()*100+1);
        this.detallePrestamo = detallePrestamo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
