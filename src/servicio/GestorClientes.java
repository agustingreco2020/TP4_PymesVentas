package servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class GestorClientes {

    public void registrarCliente(Cliente c) {
        String sql = "INSERT INTO Cliente (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelefono());
            ps.executeUpdate();
            System.out.println("✅ Cliente registrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error registrarCliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error listarClientes: " + e.getMessage());
        }
        return lista;
    }

    public void eliminarCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas>0 ? "🗑️ Cliente eliminado." : "⚠️ Cliente no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error eliminarCliente: " + e.getMessage());
        }
    }
}
