/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

/**
 *
 * @author josue
 */
public class DetalleCompra {

    private Compra compra;
    private Producto producto;
    private int cantidad;
    private float precioCompra;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public DetalleCompra(Compra compra, Producto producto, int cantidad, float precioCompra) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    public DetalleCompra() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleCompra{");
        //sb.append("compra=").append(compra);
        sb.append(", compra:").append(compra != null ? compra.toString() : "null");
        //sb.append(", producto=").append(producto);
        sb.append(", producto:").append(producto != null ? producto.toString() : "null");
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precioCompra=").append(precioCompra);
        sb.append('}');
        return sb.toString();
    }

}
