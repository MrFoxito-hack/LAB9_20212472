package com.example.lab9_base.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

 //mi contraseña es Foxi1234
        String user = "root";
        String pass = "Foxi1234";
        String url = "jdbc:mysql://localhost:3306/lab9?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, user, pass);
    }

    //probamos conexxion
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
