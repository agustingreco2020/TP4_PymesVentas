package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private int idCliente;
    private int idUsuario;
    private LocalDate fecha;
    private double total;
    private List<DetalleVenta> detalles = new ArrayList<>();

    // Constructor para nueva venta
    public Venta(int idCliente, int idUsuario) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fecha = LocalDate.now();
    }

    // Constructor con datos desde BD
    public Venta(int idVenta, int idCliente, int idUsuario, LocalDate fecha, double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
    }

    // Métodos
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal() {
        return detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public List<DetalleVenta> getDetalles() { return detalles; }

    @Override
    public String toString() {
        return "Venta #" + idVenta + " - Cliente ID: " + idCliente +
                " | Usuario ID: " + idUsuario +
                " | Fecha: " + fecha +
                " | Total: $" + calcularTotal();
    }
}

