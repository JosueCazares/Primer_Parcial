/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

/**
 *
 * @author josue
 */
public class DetalleVenta {

    private Producto producto;
    private Venta venta;
    private int cantidad;
    private float precioVenta;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public DetalleVenta() {
    }

    public DetalleVenta(Producto producto, Venta venta, int cantidad, float precioVenta) {
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public DetalleVenta(Venta venta, int cantidad, float precioVenta) {
        this.venta = venta;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleVenta{");
        sb.append(", producto:").append(producto != null ? producto.toString() : "null");
        sb.append(", venta:").append(venta != null ? venta.toString() : "null");
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precioVenta=").append(precioVenta);
        sb.append('}');
        return sb.toString();
    }

}
