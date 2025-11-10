package servicio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

public class GestorVentas {

    public void registrarVenta(Venta venta) {
        String sqlVenta = "INSERT INTO Venta (idUsuario, idCliente, fecha, total) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO Detalle_Venta (idVenta, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String sqlActualizarStock = "UPDATE Producto SET stock = stock - ? WHERE idProducto = ?";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdUsuario());
                psVenta.setInt(2, venta.getIdCliente());
                psVenta.setDate(3, Date.valueOf(venta.getFecha()));
                psVenta.setDouble(4, venta.calcularTotal());
                psVenta.executeUpdate();

                ResultSet rs = psVenta.getGeneratedKeys();
                int idVenta = 0;
                if (rs.next()) idVenta = rs.getInt(1);
                venta.setIdVenta(idVenta);

                try (PreparedStatement psDet = conn.prepareStatement(sqlDetalle);
                     PreparedStatement psStock = conn.prepareStatement(sqlActualizarStock)) {
                    for (DetalleVenta det : venta.getDetalles()) {
                        psDet.setInt(1, venta.getIdVenta());
                        psDet.setInt(2, det.getProducto().getIdProducto());
                        psDet.setInt(3, det.getCantidad());
                        psDet.setDouble(4, det.getProducto().getPrecio());
                        psDet.setDouble(5, det.getSubtotal());
                        psDet.executeUpdate();

                        // actualizar stock
                        psStock.setInt(1, det.getCantidad());
                        psStock.setInt(2, det.getProducto().getIdProducto());
                        psStock.executeUpdate();
                    }
                }

                conn.commit();
                System.out.println("✅ Venta registrada en MySQL (ID: " + venta.getIdVenta() + ")");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("❌ Error registrarVenta (rollback): " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error conexión registrarVenta: " + e.getMessage());
        }
    }

    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        String sqlVenta = "SELECT * FROM Venta ORDER BY fecha DESC";
        String sqlDetalle = "SELECT * FROM Detalle_Venta WHERE idVenta = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement psVenta = conn.prepareStatement(sqlVenta);
             ResultSet rs = psVenta.executeQuery()) {

            while (rs.next()) {
                Venta v = new Venta(rs.getInt("idCliente"), rs.getInt("idUsuario"));
                v.setIdVenta(rs.getInt("idVenta"));
                v.setFecha(rs.getDate("fecha").toLocalDate());
                v.setTotal(rs.getDouble("total"));

                try (PreparedStatement psDet = conn.prepareStatement(sqlDetalle)) {
                    psDet.setInt(1, v.getIdVenta());
                    try (ResultSet rd = psDet.executeQuery()) {
                        while (rd.next()) {
                            Producto p = new Producto(rd.getInt("idProducto"), rd.getString("idProducto"), "", rd.getDouble("precioUnitario"), 0);
                            DetalleVenta det = new DetalleVenta(p, rd.getInt("cantidad"));
                            v.agregarDetalle(det);
                        }
                    }
                }
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error listarVentas: " + e.getMessage());
        }
        return lista;
    }
}
