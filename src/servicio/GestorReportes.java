package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import modelo.Reporte;

public class GestorReportes {

    public void generarReporteVentas(GestorVentas gestorVentas) {
        double total = gestorVentas.listarVentas().stream()
                .mapToDouble(v -> v.calcularTotal()).sum();

        Reporte reporte = new Reporte("Ventas totales", total);
        System.out.println("\n--- REPORTE DE VENTAS ---");
        System.out.println("📊 " + reporte);

        // Guardar el reporte en la base de datos
        try (Connection conn = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO Reporte (fechaGeneracion) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            System.out.println("✅ Reporte guardado en la base de datos correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al guardar el reporte: " + e.getMessage());
        }
    }

}
