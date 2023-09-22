package model;

public class Prestamo {

    private String codigo;
    private DetallePrestamo detallePrestamo;

    public Prestamo(String codigo, DetallePrestamo detallePrestamo) {
        this.codigo = codigo;
        this.detallePrestamo = detallePrestamo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
