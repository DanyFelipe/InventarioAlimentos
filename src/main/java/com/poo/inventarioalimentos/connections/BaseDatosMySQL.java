package com.poo.inventarioalimentos.connections;

import com.poo.inventarioalimentos.Compra;
import com.poo.inventarioalimentos.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        int idGenerado = 0;

        try {
            // Iniciar la transacción
            conexion.setAutoCommit(false);

            // Preparar la consulta SQL para agregar un producto
            String sql = "INSERT INTO Productos (idProducto, nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, compra.getIdProducto());
            statement.setString(2, compra.getNombre());
            statement.setString(3, compra.getDescripcion());
            statement.setDouble(4, compra.getPrecio());
            statement.setInt(5, compra.getCantidad());

            // Ejecutar la consulta SQL
            statement.executeUpdate();

            // Preparar la consulta SQL para agregarla a laa tabla intermedia
            String sql2 = "INSERT INTO Sucursal_has_Producto (Sucursal_idSucursal, Producto_idProducto) VALUES (?, ?)";
            PreparedStatement statement2 = conexion.prepareStatement(sql2);
            statement2.setInt(1, compra.getIdSucursal());
            statement2.setInt(2, compra.getIdProducto());

            // Ejecutar la consulta SQL
            statement2.executeUpdate();
            
            // Si todo está bien, confirmar la transacción
            conexion.commit();
            
            //Enviar recibo de compra
            System.out.println("========== RECIBO DE COMPRA ========= ");
            System.out.println(compra.toString());
            

        } catch (SQLException e) {
            // En caso de error, realizar un rollback
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } 
    }

    public void modificarCantidad(Venta venta) {
        try {
            // Iniciar la transacción
            conexion.setAutoCommit(false);
            
            // Preparar la consulta SQL para modificar la cantidad de un producto
            String sql = "UPDATE Productos SET cantidad = cantidad - ? WHERE idProducto = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, venta.getCantidad());
            statement.setInt(2, venta.getIdProducto());

            // Ejecutar la consulta SQL
            statement.executeUpdate();
            
            // Si todo está bien, confirmar la transacción
            conexion.commit();
            
            //Enviar recibo de venta
            System.out.println("========== RECIBO DE VENTA ========= ");
            System.out.println(venta.toString());
            
        } catch (SQLException e) {
            // En caso de error, realizar un rollback
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }
}
