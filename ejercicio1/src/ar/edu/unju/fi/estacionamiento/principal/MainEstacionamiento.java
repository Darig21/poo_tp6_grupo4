package ar.edu.unju.fi.estacionamiento.principal;

import java.util.Calendar;
import java.util.Date;

import ar.edu.unju.fi.estacionamiento.modelo.Cliente;
import ar.edu.unju.fi.estacionamiento.modelo.Cupon;
import ar.edu.unju.fi.estacionamiento.modelo.Manager;
import ar.edu.unju.fi.estacionamiento.modelo.Mensual;
import ar.edu.unju.fi.estacionamiento.modelo.PorHora;
import ar.edu.unju.fi.estacionamiento.modelo.RegistroIngresoSalida;
import ar.edu.unju.fi.estacionamiento.modelo.Vehiculo;

public class MainEstacionamiento {

    public static void main(String[] args) {
        Manager manager = new Manager();

        titulo("a) Ingresos por hora sin cupón");
        manager.registrarIngreso(new PorHora(new Vehiculo("AB123CD", "Fiat", "Rojo"), hoy(10, 15), hoy(10, 15)));
        manager.registrarIngreso(new PorHora(new Vehiculo("EF456GH", "Ford", "Azul"), hoy(10, 40), hoy(10, 40)));

        titulo("b) Ingresos por hora con cupón (no vencido y vencido)");
        Cupon noVencido = new Cupon("PROMO10", enDias(10), 10.0);
        Cupon vencido = new Cupon("OLD20", enDias(-5), 20.0);
        manager.registrarIngreso(new PorHora(new Vehiculo("AA111AA", "Renault", "Gris"), hoy(11, 0), hoy(11, 0), noVencido));
        manager.registrarIngreso(new PorHora(new Vehiculo("BB222BB", "Toyota", "Negro"), hoy(11, 0), hoy(11, 0), vencido));

        titulo("c) Ingresos por hora fuera de horario");
        manager.registrarIngreso(new PorHora(new Vehiculo("CC333CC", "VW", "Blanco"), hoy(22, 30), hoy(22, 30)));
        manager.registrarIngreso(new PorHora(new Vehiculo("DD444DD", "Peugeot", "Verde"), hoy(7, 45), hoy(7, 45)));
        System.out.println("(vehículo ya ingresado) intento repetir AB123CD:");
        manager.registrarIngreso(new PorHora(new Vehiculo("AB123CD", "Fiat", "Rojo"), hoy(12, 0), hoy(12, 0)));

        titulo("d) Ingresos de clientes mensuales (sin restricción horaria)");
        Cliente cliente1 = new Cliente(1, "30111222", "Belgrano 123", "3884112233");
        Cliente cliente2 = new Cliente(2, "28999888", "Sarmiento 450", "3884556677");
        manager.registrarIngreso(new Mensual(new Vehiculo("MM001AA", "Chevrolet", "Plata"), hoy(9, 0), hoy(9, 0), cliente1));
        manager.registrarIngreso(new Mensual(new Vehiculo("MM002BB", "Honda", "Rojo"), hoy(23, 30), hoy(23, 30), cliente2));

        titulo("e) Buscar registro por ID y mostrar importe actual (ID 1)");
        RegistroIngresoSalida r1 = manager.obtenerRegistro(1);
        System.out.println(r1.mostrarDatos());
        System.out.println(String.format("Importe actual: $%.2f", r1.obtenerImporte()));

        titulo("f) Buscar por ID y registrar salida de vehículos por hora");
        salidaPorHora(manager, 1, hoy(12, 15)); // exactamente 2 hs
        salidaPorHora(manager, 2, hoy(10, 55)); // menos de 1 h -> se cobra 1 h
        salidaPorHora(manager, 3, hoy(13, 20)); // 3 hs con cupón vigente (10%)
        salidaPorHora(manager, 4, hoy(13, 20)); // 3 hs con cupón vencido (sin descuento)

        titulo("g) Buscar por ID y registrar salida de cliente mensual (ID 5)");
        salidaPorHora(manager, 5, hoy(18, 0));

        titulo("Extra) Vehículo que salió puede volver a ingresar");
        manager.registrarIngreso(new PorHora(new Vehiculo("AB123CD", "Fiat", "Rojo"), hoy(15, 0), hoy(15, 0)));
    }

    private static void salidaPorHora(Manager manager, Integer id, Date horaSalida) {
        RegistroIngresoSalida registro = manager.obtenerRegistro(id);
        Double importe = manager.registrarSalida(registro, horaSalida);
        System.out.println(registro.mostrarDatos() + String.format(" | Importe a pagar: $%.2f", importe));
    }

    private static void titulo(String texto) {
        System.out.println("\n=== " + texto + " ===");
    }

    // Fecha de hoy a la hora indicada.
    private static Date hoy(int hora, int minuto) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hora);
        c.set(Calendar.MINUTE, minuto);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    // Fecha de hoy desplazada la cantidad de días indicada.
    private static Date enDias(int dias) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, dias);
        return c.getTime();
    }
}
