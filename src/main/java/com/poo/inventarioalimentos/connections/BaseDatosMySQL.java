
package com.poo.inventarioalimentos.connections;

import com.poo.inventarioalimentos.Compra;
import com.poo.inventarioalimentos.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public void agregarProducto(Compra compra) {
        System.out.println("AQUI SI !!!");
        try {
            System.out.println("LLEGO???.");
            // Preparar la consulta SQL para agregar un producto
            String sql = "INSERT INTO Productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, compra.getNombre());
            statement.setString(2, compra.getDescripcion());
            statement.setDouble(3, compra.getPrecio());
            statement.setInt(4, compra.getCantidad());

            // Ejecutar la consulta SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarCantidad(Venta venta) {
        try {
            // Preparar la consulta SQL para modificar la cantidad de un producto
            String sql = "UPDATE Productos SET cantidad = cantidad - ? WHERE idProducto = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, venta.getCantidad());
            statement.setInt(2, venta.getIdProducto());

            // Ejecutar la consulta SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
