
package com.poo.inventarioalimentos;

public class Venta {
    
    private int idProducto;
    private int cantidad;
    private int idSucursal;

    public Venta(int idProducto, int cantidad, int idSucursal) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idSucursal = idSucursal;
    }

    public Venta() {
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return "Producto ID: " + getIdProducto() + ", Sucursal ID: " + getIdSucursal() +
               ", Cantidad: " + getCantidad();
    }
    
}
