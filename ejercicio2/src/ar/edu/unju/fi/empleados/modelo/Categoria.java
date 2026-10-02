package ar.edu.unju.fi.empleados.modelo;

public enum Categoria {
    A("Auxiliar", 30000),
    B("Ventas", 45000),
    C("Gerencia", 55000);

    private final String descripcion;
    private final double monto;

    private Categoria(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getDescripcion() { return descripcion; }
    public double getMonto() { return monto; }
}
