package com.clientaws;

import java.sql.Connection;

public class ClientIoT {

    static final String url = "jdbc:mariadb://127.0.0.1:3306/AccesDB";
    static final String usuario = "123admin";
    static final String contrasena = "P4assw0ord!";

    public static void main(String[] args) {
        DispositiuIot disp = new DispositiuIot();

        try (Connection con = ConnectDB.getConnection(url, usuario, contrasena)) {
            AccesMethodsToDB access = new AccesMethodsToDB();

            System.out.println("Conectando al servicio IoT...");
            disp.conecta();

            System.out.println("Suscribiéndose al tópico IoT...");
            disp.subscriu(access, con);

            System.out.println("Esperando mensajes de IoT...");
            while (true) {
                // Mantén la aplicación viva mientras se procesan mensajes en el tópico
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String extractUid(String message) {
        try {
            JSONObject json = new JSONObject(message);
            return json.getString("uid");
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje JSON: " + e.getMessage());
            return null;
        }
    }
}
