/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.inventarioalimentos;

public class Compra {
    
    private int id;
    private int idProducto;
    private int cantidad;
    private int idSucursal;
    private double precioUnitario;

    public Compra(int id, int idProducto, int cantidad, int idSucursal, double precioUnitario) {
        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idSucursal = idSucursal;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Compra [ID: " + getId() + ", Producto ID: " + getIdProducto() + ", Sucursal ID: " + getIdSucursal() +
               ", Cantidad: " + getCantidad() + ", Precio Unitario: " + getPrecioUnitario() + "]";
    }
}