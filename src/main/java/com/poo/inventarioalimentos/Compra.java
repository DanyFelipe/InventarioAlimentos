/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.inventarioalimentos;

public class Compra {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int idSucursal;
    private int cantidad;

    public Compra(int idProducto, String nombre, String descripcion, double precio, int idSucursal, int cantidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idSucursal = idSucursal;
        this.cantidad = cantidad;
    }

    public Compra() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + ", Descripcion: " + getDescripcion() + ",  Precio: " + getPrecio() +
               ", Cantidad: " + getCantidad();
    }
}
