package ar.edu.unju.fi.empleados.principal;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.empleados.modelo.Administrativo;
import ar.edu.unju.fi.empleados.modelo.Categoria;
import ar.edu.unju.fi.empleados.modelo.Limpieza;
import ar.edu.unju.fi.empleados.modelo.ManagerEmpleado;
import ar.edu.unju.fi.empleados.modelo.Nivel;
import ar.edu.unju.fi.empleados.modelo.Profesional;
import ar.edu.unju.fi.empleados.modelo.Titulo;

public class MainEmpleados {

    public static void main(String[] args) {
        ManagerEmpleado manager = new ManagerEmpleado();

        titulo("a) Agregar empleados de distintos tipos");
        System.out.println("Empleados iniciales: " + manager.getEmpleados().size());
        manager.agregarEmpleado(new Profesional(103, "34100200", "Sofía Aguirre", LocalDate.of(2021, 4, 5), 1));
        manager.agregarEmpleado(new Administrativo(205, "36300400", "Diego Paz", LocalDate.of(2024, 2, 12), 0, Categoria.C));
        manager.agregarEmpleado(new Limpieza(303, "38500600", "Elena Castro", LocalDate.of(2025, 7, 1), 2));
        System.out.println("Intento con legajo repetido (101):");
        manager.agregarEmpleado(new Limpieza(101, "99999999", "Repetido", LocalDate.of(2025, 1, 1), 0));
        System.out.println("Total de empleados: " + manager.getEmpleados().size());

        titulo("b) Buscar por legajo y mostrar datos + sueldo neto (legajo 101)");
        System.out.println(manager.buscarEmpleado(101).mostrarDatos());

        titulo("c) Administrativo 202: cambiar categoría B -> C");
        Administrativo a = manager.buscarAdministrativo(202);
        System.out.println("Antes:   " + a.getCategoria() + " -> neto $" + String.format("%,.2f", a.calcularSueldoNeto()));
        manager.cambiarCategoria(202, Categoria.C);
        System.out.println("Después: " + a.getCategoria());
        System.out.println(a.mostrarDatos());

        titulo("d) Profesional 102: agregar nuevo título");
        Profesional p = manager.buscarProfesional(102);
        System.out.println("Antes:   " + p.getTitulos().size() + " título(s) -> neto $" + String.format("%,.2f", p.calcularSueldoNeto()));
        manager.agregarTitulo(102, new Titulo(2023, "Especialización en Gestión", Nivel.UNIVERSITARIO));
        System.out.println("Después: " + p.getTitulos().size() + " título(s)");
        System.out.println(p.mostrarDatos());

        titulo("e) Administrativos de la categoría C + totales");
        List<Administrativo> categoriaC = manager.obtenerAdministrativosPorCategoria(Categoria.C);
        double totalRemun = 0, totalFamiliar = 0, totalDescuentos = 0, totalNeto = 0;
        for (Administrativo adm : categoriaC) {
            System.out.println(adm.mostrarDatos());
            totalRemun += adm.calcularRemunerativosBonificables();
            totalFamiliar += adm.calcularSalarioFamiliar();
            totalDescuentos += adm.calcularDescuentos();
            totalNeto += adm.calcularSueldoNeto();
        }
        System.out.println("--------------------------------------------");
        System.out.println(String.format("TOTALES (%d empleados): Rem. bonificables $%,.2f | Salario familiar $%,.2f | Descuentos $%,.2f | Neto $%,.2f",
                categoriaC.size(), totalRemun, totalFamiliar, totalDescuentos, totalNeto));

        titulo("f) Neto acumulado por tipo de empleado");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tipo (Profesional / Administrativo / Limpieza): ");
        String tipo = scanner.nextLine().trim();
        System.out.println(String.format("Neto acumulado de %s: $%,.2f", tipo, manager.calcularNetoPorTipo(tipo)));
        scanner.close();
    }

    private static void titulo(String texto) {
        System.out.println("\n=== " + texto + " ===");
    }
}
