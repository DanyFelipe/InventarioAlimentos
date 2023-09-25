
package com.poo.inventarioalimentos.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatosMySQL {
    private Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/inventarios";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "danylhall117";

    public BaseDatosMySQL() {
        // Inicializa la conexión a la base de datos en el constructor
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
