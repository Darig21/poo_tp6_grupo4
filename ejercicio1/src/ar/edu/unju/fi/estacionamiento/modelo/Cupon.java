package ar.edu.unju.fi.estacionamiento.modelo;

import java.util.Date;

public class Cupon {
    private String codigo;
    private Date fechaVencimiento;
    private Double porcentajeDescuento;

    public Cupon() {
    }

    public Cupon(String codigo, Date fechaVencimiento, Double porcentajeDescuento) {
        this.codigo = codigo;
        this.fechaVencimiento = fechaVencimiento;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // true si el cupón ya venció a la fecha indicada.
    public boolean estaVencido(Date fecha) {
        return fechaVencimiento.before(fecha);
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public Double getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(Double porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
}
