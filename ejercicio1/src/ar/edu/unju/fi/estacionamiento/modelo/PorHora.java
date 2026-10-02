package ar.edu.unju.fi.estacionamiento.modelo;

import java.util.Date;

public class PorHora extends RegistroIngresoSalida {
    private Cupon cupon;                                   // asociación "usa" (puede ser null)
    private static final double VALOR_HORA = 1000;         // atributo de clase (subrayado en el diagrama)

    public PorHora(Vehiculo vehiculo) {
        super(vehiculo);
    }

    public PorHora(Vehiculo vehiculo, Cupon cupon) {
        super(vehiculo);
        this.cupon = cupon;
    }

    public PorHora(Vehiculo vehiculo, Date fecha, Date hora) {
        super(vehiculo, fecha, hora);
    }

    public PorHora(Vehiculo vehiculo, Date fecha, Date hora, Cupon cupon) {
        super(vehiculo, fecha, hora);
        this.cupon = cupon;
    }

    @Override
    public Double obtenerImporte() {
        Date salida = (getHoraSalida() != null) ? getHoraSalida() : new Date();
        long milisegundos = salida.getTime() - getHora().getTime();
        // no se permite pago fraccionado: toda hora comenzada se cobra, mínimo 1 hora
        double horas = Math.ceil(milisegundos / (1000.0 * 60 * 60));
        if (horas < 1) {
            horas = 1;
        }
        double importe = horas * VALOR_HORA;
        if (cupon != null && !cupon.estaVencido(salida)) {
            importe = importe - importe * cupon.getPorcentajeDescuento() / 100;
        }
        return importe;
    }

    public Cupon getCupon() { return cupon; }
    public void setCupon(Cupon cupon) { this.cupon = cupon; }
}
