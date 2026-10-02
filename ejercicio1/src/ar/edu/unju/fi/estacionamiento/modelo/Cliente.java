package ar.edu.unju.fi.estacionamiento.modelo;

public class Cliente {
    private Integer id;
    private String dni;
    private String domicilio;
    private String celular;

    public Cliente() {
    }

    public Cliente(Integer id, String dni, String domicilio, String celular) {
        this.id = id;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
}
