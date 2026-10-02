package ar.edu.unju.fi.estacionamiento.modelo;

import java.util.Date;

public class Mensual extends RegistroIngresoSalida {
    private Cliente cliente; // agregación (rol "cliente")

    public Mensual(Vehiculo vehiculo, Cliente cliente) {
        super(vehiculo);
        this.cliente = cliente;
    }

    public Mensual(Vehiculo vehiculo, Date fecha, Date hora, Cliente cliente) {
        super(vehiculo, fecha, hora);
        this.cliente = cliente;
    }

    @Override
    public Double obtenerImporte() {
        return 0.0; // la cuota mensual está fuera del alcance de la aplicación
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
