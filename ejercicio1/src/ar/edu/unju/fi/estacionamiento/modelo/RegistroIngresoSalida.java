package ar.edu.unju.fi.estacionamiento.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.unju.fi.estacionamiento.modelo.ctes.Constante;

public abstract class RegistroIngresoSalida {
    private Integer id;
    private Date fecha;
    private Date hora;          // instante de ingreso
    private Vehiculo vehiculo;  // agregación (rol "vehiculo")
    private String estado;
    private Date horaSalida;    // agregado al diagrama: permite calcular la permanencia

    public RegistroIngresoSalida(Vehiculo vehiculo) {
        this(vehiculo, new Date(), new Date());
    }

    public RegistroIngresoSalida(Vehiculo vehiculo, Date fecha, Date hora) {
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = Constante.ESTADO_AFUERA;
    }

    // Operación abstracta: cada subclase define cómo calcula el importe.
    public abstract Double obtenerImporte();

    public void cambiarEstado(String estado) {
        this.estado = estado;
    }

    public String mostrarDatos() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        return String.format("ID: %d | Patente: %s | Ingreso: %s | Estado: %s",
                id, vehiculo.getPatente(), sdf.format(hora), estado);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getFecha() { return fecha; }
    public Date getHora() { return hora; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getEstado() { return estado; }
    public Date getHoraSalida() { return horaSalida; }
    public void setHoraSalida(Date horaSalida) { this.horaSalida = horaSalida; }
}
