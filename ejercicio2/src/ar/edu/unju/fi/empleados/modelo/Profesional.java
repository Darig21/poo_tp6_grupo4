package ar.edu.unju.fi.empleados.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.empleados.modelo.ctes.Constante;

public class Profesional extends Empleado {
    // Composición: los títulos pertenecen solo a este profesional, se inicializan con la clase
    private List<Titulo> titulos = new ArrayList<Titulo>();

    public Profesional(Integer legajo, String documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
    }

    public void agregarTitulo(Titulo titulo) {
        titulos.add(titulo);
    }

    @Override
    public double calcularAdicional() {
        return titulos.size() * Constante.ADICIONAL_POR_TITULO;
    }

    @Override
    public String getTipo() {
        return "Profesional";
    }

    public List<Titulo> getTitulos() { return titulos; }
}
