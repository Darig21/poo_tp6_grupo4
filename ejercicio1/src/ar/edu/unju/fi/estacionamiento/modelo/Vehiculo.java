package ar.edu.unju.fi.estacionamiento.modelo;

public class Vehiculo {
    private String patente;
    private String marca;
    private String color;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String color) {
        this.patente = patente;
        this.marca = marca;
        this.color = color;
    }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
