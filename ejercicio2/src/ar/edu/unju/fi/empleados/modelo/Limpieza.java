package ar.edu.unju.fi.empleados.modelo;

import java.time.LocalDate;

import ar.edu.unju.fi.empleados.modelo.ctes.Constante;

public class Limpieza extends Empleado {

    public Limpieza(Integer legajo, String documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
    }

    @Override
    public double calcularAdicional() {
        return Constante.ADICIONAL_INSALUBRIDAD;
    }

    @Override
    public String getTipo() {
        return "Limpieza";
    }
}
