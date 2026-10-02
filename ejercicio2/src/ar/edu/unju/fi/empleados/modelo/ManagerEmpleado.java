package ar.edu.unju.fi.empleados.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerEmpleado {
    private List<Empleado> empleados = new ArrayList<Empleado>(); // agregación 0..*

    public ManagerEmpleado() {
        cargarEmpleadosIniciales();
    }

    private void cargarEmpleadosIniciales() {
        Profesional p1 = new Profesional(101, "30111222", "Ana Torres", LocalDate.of(2015, 3, 1), 2);
        p1.agregarTitulo(new Titulo(2012, "Licenciatura en Sistemas", Nivel.UNIVERSITARIO));
        p1.agregarTitulo(new Titulo(2016, "Tecnicatura en Redes", Nivel.TERCIARIO));
        Profesional p2 = new Profesional(102, "28555666", "Luis Pérez", LocalDate.of(2020, 8, 15), 0);
        p2.agregarTitulo(new Titulo(2018, "Ingeniería Industrial", Nivel.UNIVERSITARIO));

        agregarEmpleado(p1);
        agregarEmpleado(p2);
        agregarEmpleado(new Administrativo(201, "33222111", "María Gómez", LocalDate.of(2018, 2, 1), 1, Categoria.A));
        agregarEmpleado(new Administrativo(202, "31444555", "Carlos Díaz", LocalDate.of(2022, 5, 10), 3, Categoria.B));
        agregarEmpleado(new Administrativo(203, "29888777", "Lucía Ruiz", LocalDate.of(2019, 11, 20), 0, Categoria.B));
        agregarEmpleado(new Administrativo(204, "27666999", "Jorge Vega", LocalDate.of(2012, 6, 1), 2, Categoria.C));
        agregarEmpleado(new Limpieza(301, "35777888", "Rosa Medina", LocalDate.of(2023, 1, 9), 4));
        agregarEmpleado(new Limpieza(302, "32999000", "Pedro Luna", LocalDate.of(2017, 9, 18), 1));
    }

    /** Agrega el empleado si el legajo no está repetido. */
    public boolean agregarEmpleado(Empleado empleado) {
        if (buscarEmpleado(empleado.getLegajo()) != null) {
            System.out.println("NO SE AGREGÓ: el legajo " + empleado.getLegajo() + " ya existe.");
            return false;
        }
        return empleados.add(empleado);
    }

    public Empleado buscarEmpleado(Integer legajo) {
        for (Empleado e : empleados) {
            if (e.getLegajo().equals(legajo)) {
                return e;
            }
        }
        return null;
    }

    public Administrativo buscarAdministrativo(Integer legajo) {
        Empleado e = buscarEmpleado(legajo);
        if (e instanceof Administrativo) {
            return (Administrativo) e;
        }
        return null;
    }

    public Profesional buscarProfesional(Integer legajo) {
        Empleado e = buscarEmpleado(legajo);
        if (e instanceof Profesional) {
            return (Profesional) e;
        }
        return null;
    }

    public boolean cambiarCategoria(Integer legajo, Categoria nuevaCategoria) {
        Administrativo a = buscarAdministrativo(legajo);
        if (a == null) {
            return false;
        }
        a.setCategoria(nuevaCategoria);
        return true;
    }

    public boolean agregarTitulo(Integer legajo, Titulo titulo) {
        Profesional p = buscarProfesional(legajo);
        if (p == null) {
            return false;
        }
        p.agregarTitulo(titulo);
        return true;
    }

    public List<Administrativo> obtenerAdministrativosPorCategoria(Categoria categoria) {
        List<Administrativo> resultado = new ArrayList<Administrativo>();
        for (Empleado e : empleados) {
            if (e instanceof Administrativo && ((Administrativo) e).getCategoria() == categoria) {
                resultado.add((Administrativo) e);
            }
        }
        return resultado;
    }

    /** Suma del sueldo neto de todos los empleados del tipo indicado. */
    public double calcularNetoPorTipo(String tipo) {
        double total = 0;
        for (Empleado e : empleados) {
            if (e.getTipo().equalsIgnoreCase(tipo)) {
                total += e.calcularSueldoNeto();
            }
        }
        return total;
    }

    public List<Empleado> getEmpleados() { return empleados; }
}
