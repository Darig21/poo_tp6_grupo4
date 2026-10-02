package ar.edu.unju.fi.estacionamiento.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.edu.unju.fi.estacionamiento.modelo.ctes.Constante;

public class Manager {
    private List<RegistroIngresoSalida> registros = new ArrayList<RegistroIngresoSalida>(); // "Gestiona" 1..*
    private int ultimoId = 0;

    // true si la patente NO se encuentra actualmente dentro de la playa (puede ingresar).
    public boolean validarPatente(String patente) {
        for (RegistroIngresoSalida r : registros) {
            if (r.getVehiculo().getPatente().equalsIgnoreCase(patente)
                    && r.getEstado().equals(Constante.ESTADO_INGRESADO)) {
                return false;
            }
        }
        return true;
    }

    public void registrarIngreso(RegistroIngresoSalida registro) {
        String patente = registro.getVehiculo().getPatente();
        if (!validarPatente(patente)) {
            System.out.println("INGRESO RECHAZADO [" + patente + "]: el vehículo ya está en la playa.");
            return;
        }
        if (registro instanceof PorHora && !ingresoEnHorario(registro.getHora())) {
            System.out.println("INGRESO RECHAZADO [" + patente + "]: fuera de horario (por hora: 8 a 21 hs).");
            return;
        }
        ultimoId++;
        registro.setId(ultimoId);
        registro.cambiarEstado(Constante.ESTADO_INGRESADO);
        registros.add(registro);
        System.out.println("INGRESO OK -> " + registro.mostrarDatos());
    }

    public Double registrarSalida(RegistroIngresoSalida registro) {
        if (registro == null || !registro.getEstado().equals(Constante.ESTADO_INGRESADO)) {
            System.out.println("SALIDA RECHAZADA: el vehículo no está dentro de la playa.");
            return 0.0;
        }
        if (registro.getHoraSalida() == null) {
            registro.setHoraSalida(new Date());
        }
        Double importe = registro.obtenerImporte();
        registro.cambiarEstado(Constante.ESTADO_AFUERA);
        return importe;
    }

    // Sobrecarga: permite indicar la hora de salida (útil para casos de prueba).
    public Double registrarSalida(RegistroIngresoSalida registro, Date horaSalida) {
        if (registro != null) {
            registro.setHoraSalida(horaSalida);
        }
        return registrarSalida(registro);
    }

    public RegistroIngresoSalida obtenerRegistro(Integer id) {
        for (RegistroIngresoSalida r : registros) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    private boolean ingresoEnHorario(Date hora) {
        Calendar c = Calendar.getInstance();
        c.setTime(hora);
        int minutos = c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
        return minutos >= Constante.HORA_APERTURA * 60 && minutos <= Constante.HORA_ULTIMO_INGRESO * 60;
    }
}
