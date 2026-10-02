package ar.edu.unju.fi.empleados.modelo;

import java.time.LocalDate;

public class Administrativo extends Empleado {
    private Categoria categoria; // asociación 1

    public Administrativo(Integer legajo, String documento, String nombre, LocalDate fechaIngreso,
            int cantidadHijos, Categoria categoria) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
        this.categoria = categoria;
    }

    @Override
    public double calcularAdicional() {
        return categoria.getMonto();
    }

    @Override
    public String getTipo() {
        return "Administrativo";
    }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
