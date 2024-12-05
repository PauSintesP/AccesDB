package com.clientaws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesMethodsToDB {

    public void selectAlumnes (Connection con) {
        String sql = "SELECT * FROM ALUMNE"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            int id = rs.getInt("IdAlumne");
            String nombre = rs.getString("Nom");
            System.out.println("ID: " + id + ", Nom: " + nombre);
        }
        
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
public void insertRegistry(Connection con, String uid) {
    PreparedStatement consultaAlumno = null;
    PreparedStatement insercion = null;
    ResultSet resultado = null;

    try {
        // Verificar si el UID es válido
        if (uid == null || uid.isEmpty()) {
            System.out.println("El UID proporcionado es inválido.");
            return;
        }

        // Buscar el alumno en la tabla ALUMNE usando el uid
        String queryAlumno = "SELECT IdAlumne FROM ALUMNE WHERE Id_Tarjeta = ?";
        consultaAlumno = con.prepareStatement(queryAlumno);
        consultaAlumno.setString(1, uid);

        resultado = consultaAlumno.executeQuery();

        if (resultado.next()) { // Si se encuentra un alumno
            int idAlumne = resultado.getInt("IdAlumne");

            // Realizar el INSERT en la tabla PRESENCIA
            String queryInsert = "INSERT INTO PRESENCIA (IdAlumne, IdClasse, Present, Comentaris) VALUES (?, ?, ?, ?)";
            insercion = con.prepareStatement(queryInsert);

            // Supongamos que los valores de IdClasse, Present y Comentaris se definen aquí
            int idClasse = 1; // Clase predeterminada
            boolean present = true; // Asumimos que está presente
            String comentaris = "Asistencia confirmada"; // Comentarios predeterminados

            insercion.setInt(1, idAlumne);
            insercion.setInt(2, idClasse);
            insercion.setBoolean(3, present);
            insercion.setString(4, comentaris);

            int filasAfectadas = insercion.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Registro insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el registro.");
            }
        } else {
            System.out.println("No se encontró ningún alumno con Id_Tarjeta: " + uid);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultado != null) resultado.close();
            if (consultaAlumno != null) consultaAlumno.close();
            if (insercion != null) insercion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

}
