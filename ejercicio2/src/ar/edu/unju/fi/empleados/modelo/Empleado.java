package ar.edu.unju.fi.empleados.modelo;

import java.time.LocalDate;
import java.time.Period;

import ar.edu.unju.fi.empleados.modelo.ctes.Constante;

public abstract class Empleado {
    private Integer legajo; // no se puede repetir (lo controla ManagerEmpleado)
    private String documento;
    private String nombre;
    private LocalDate fechaIngreso;
    private int cantidadHijos;

    public Empleado(Integer legajo, String documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        this.legajo = legajo;
        this.documento = documento;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.cantidadHijos = cantidadHijos;
    }

    /** Adicional propio de cada tipo (título / categoría / insalubridad). */
    public abstract double calcularAdicional();

    public abstract String getTipo();

    public double calcularAntiguedad() {
        int anios = Period.between(fechaIngreso, LocalDate.now()).getYears();
        return anios * Constante.ANTIGUEDAD_POR_ANIO;
    }

    public double calcularRemunerativosBonificables() {
        return Constante.SUELDO_BASICO + calcularAdicional() + calcularAntiguedad();
    }

    public double calcularSalarioFamiliar() {
        return cantidadHijos * Constante.SALARIO_FAMILIAR_POR_HIJO;
    }

    public double calcularDescuentos() {
        return calcularRemunerativosBonificables() * Constante.PORCENTAJE_DESCUENTO;
    }

    public double calcularSueldoNeto() {
        return calcularRemunerativosBonificables() + calcularSalarioFamiliar() - calcularDescuentos();
    }

    public String mostrarDatos() {
        return String.format("Legajo: %d | %s | Doc: %s | Tipo: %s | Ingreso: %s | Hijos: %d%n"
                + "   Rem. bonificables: $%,.2f | Salario familiar: $%,.2f | Descuentos: $%,.2f | SUELDO NETO: $%,.2f",
                legajo, nombre, documento, getTipo(), fechaIngreso, cantidadHijos,
                calcularRemunerativosBonificables(), calcularSalarioFamiliar(),
                calcularDescuentos(), calcularSueldoNeto());
    }

    public Integer getLegajo() { return legajo; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public int getCantidadHijos() { return cantidadHijos; }
}
