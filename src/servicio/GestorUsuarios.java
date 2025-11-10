package servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class GestorUsuarios {

    public void registrarUsuario(Usuario u) {
        String sql = "INSERT INTO Usuario (nombre, apellido, usuario, clave, rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getUsuario());
            ps.setString(4, u.getContrasena());
            ps.setString(5, u.getRol());
            ps.executeUpdate();
            System.out.println("✅ Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error registrarUsuario: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("rol")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error listarUsuarios: " + e.getMessage());
        }
        return lista;
    }

    public void modificarUsuario(int id, String nuevoNombre, String nuevoApellido) {
        String sql = "UPDATE Usuario SET nombre = ?, apellido = ? WHERE idUsuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoApellido);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            System.out.println(filas>0 ? "✅ Usuario actualizado." : "⚠️ Usuario no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error modificarUsuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas>0 ? "🗑️ Usuario eliminado." : "⚠️ Usuario no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error eliminarUsuario: " + e.getMessage());
        }
    }
}
