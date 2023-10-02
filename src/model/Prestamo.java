package model;

public class Prestamo {

    private String codigo;
    private DetallePrestamo detallePrestamo;

    public Prestamo(DetallePrestamo detallePrestamo) {
        this.codigo = crearCodigo();
        this.detallePrestamo = detallePrestamo;
    }

    public DetallePrestamo getDetallePrestamo() {
        return detallePrestamo;
    }

    public void setDetallePrestamo(DetallePrestamo detallePrestamo) {
        this.detallePrestamo = detallePrestamo;
    }

    private String crearCodigo() {
        String codigo = "";

        for (int i = 0; i < 3; i++) {
            int num = (int) Math.floor(Math.random()*10);
            codigo += String.valueOf(num);
        }
        return codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
