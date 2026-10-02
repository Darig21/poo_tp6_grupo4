package ar.edu.unju.fi.empleados.modelo;

public class Titulo {
    private int anio;
    private String nombreCarrera;
    private Nivel nivel;

    public Titulo(int anio, String nombreCarrera, Nivel nivel) {
        this.anio = anio;
        this.nombreCarrera = nombreCarrera;
        this.nivel = nivel;
    }

    public int getAnio() { return anio; }
    public String getNombreCarrera() { return nombreCarrera; }
    public Nivel getNivel() { return nivel; }
}
