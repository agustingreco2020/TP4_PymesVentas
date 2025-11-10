package servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class GestorProductos {

    public void registrarProducto(Producto p) {
        String sql = "INSERT INTO Producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
            System.out.println("✅ Producto registrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error registrarProducto: " + e.getMessage());
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error listarProductos: " + e.getMessage());
        }
        return lista;
    }

    public void modificarProducto(int id, String nuevoNombre, int nuevoStock) {
        String sql = "UPDATE Producto SET nombre = ?, stock = ? WHERE idProducto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setInt(2, nuevoStock);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            System.out.println(filas>0 ? "✅ Producto actualizado." : "⚠️ Producto no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error modificarProducto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM Producto WHERE idProducto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas>0 ? "🗑️ Producto eliminado." : "⚠️ Producto no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error eliminarProducto: " + e.getMessage());
        }
    }

    public Producto buscarProducto(int idProducto) {
        String sql = "SELECT * FROM Producto WHERE idProducto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error buscarProducto: " + e.getMessage());
        }
        return null;
    }
}
