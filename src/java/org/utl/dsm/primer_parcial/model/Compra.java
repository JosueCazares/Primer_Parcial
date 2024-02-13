/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

/**
 *
 * @author josue
 */
public class Compra {
    private int idCompra;
    private String fechaHoraPedido;
    private int estatus;
    private int activo;
    private Empleado empleado;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getFechaHoraPedido() {
        return fechaHoraPedido;
    }

    public void setFechaHoraPedido(String fechaHoraPedido) {
        this.fechaHoraPedido = fechaHoraPedido;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Compra() {
    }

    public Compra(int idCompra, String fechaHoraPedido, int estatus, int activo, Empleado empleado) {
        this.idCompra = idCompra;
        this.fechaHoraPedido = fechaHoraPedido;
        this.estatus = estatus;
        this.activo = activo;
        this.empleado = empleado;
    }

    public Compra(String fechaHoraPedido, int estatus, int activo, Empleado empleado) {
        this.fechaHoraPedido = fechaHoraPedido;
        this.estatus = estatus;
        this.activo = activo;
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra{");
        sb.append("idCompra=").append(idCompra);
        sb.append(", fechaHoraPedido=").append(fechaHoraPedido);
        sb.append(", estatus=").append(estatus);
        sb.append(", activo=").append(activo);
        sb.append(", empleado:").append(empleado != null ? empleado.toString() : "null");
        sb.append('}');
        return sb.toString();
    }
    
}
