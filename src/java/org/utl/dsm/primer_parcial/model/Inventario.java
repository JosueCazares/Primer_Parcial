/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;



/**
 *
 * @author josue
 */
public class Inventario {

    private int idInventario;
    private Sucursal sucursal;
    private Producto producto;
    private int existencias;

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public Inventario() {
    }

    public Inventario(int idInventario, Sucursal sucursal, Producto producto, int existencias) {
        this.idInventario = idInventario;
        this.sucursal = sucursal;
        this.producto = producto;
        this.existencias = existencias;
    }

    public Inventario(Sucursal sucursal, Producto producto, int existencias) {
        this.sucursal = sucursal;
        this.producto = producto;
        this.existencias = existencias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario{");
        sb.append("idInventario:").append(idInventario);
        sb.append(", sucursal=").append(sucursal);
        sb.append(", producto=").append(producto);
        // Verificar si el objeto sucursal no es nulo antes de invocar toString()
        sb.append(", sucursal:").append(sucursal != null ? sucursal.toString() : "null");
        // Verificar si el objeto producto no es nulo antes de invocar toString()
        sb.append(", producto:").append(producto != null ? producto.toString() : "null");
        sb.append(", existencias:").append(existencias);
        sb.append('}');
        return sb.toString();
    }

}
