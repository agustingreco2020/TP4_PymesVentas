package modelo;

public class Reporte {
    private int idReporte;
    private String tipo;
    private double total;

    public Reporte(String tipo, double total) {
        this.tipo = tipo;
        this.total = total;
    }

    // Getters y Setters
    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Reporte: " + tipo + " | Total: $" + total;
    }
}
