/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.clientaws;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class ClientIoT {

    static final String url = "jdbc:mariadb://192.168.45.1:3306/AccesDB";
    static final String usuario = "123admin"; 
    static final String contrasena = "P4assw0rd!";

    public static void main(String[] args) {

        DispositiuIot disp = new DispositiuIot();

        try {
            
            disp.conecta();
            disp.subscriu(); 

            try (Connection con = ConnectDB.getConnection(url, usuario, contrasena)) {
                AccesMethodsToDB access = new AccesMethodsToDB();
                access.selectAlumnes(con);
                access.insertRegistry(con, null);
            } catch (SQLException e) {
                System.out.println("Error de conexión: " + e.getMessage());
            }
            
        }catch(Exception e){
            System.err.println("Error IOT: "+e.getLocalizedMessage());
            System.exit(-1);
        }    
        
        
    }
}