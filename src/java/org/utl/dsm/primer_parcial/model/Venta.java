/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

/**
 *
 * @author josue
 */
public class Venta {

    private int idVenta;
    private String fechaHora;
    private int estatus;
    private Cliente cliente;
    private Empleado empleado;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Venta() {
    }

    public Venta(int idVenta, String fechaHora, int estatus, Cliente cliente, Empleado empleado) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.estatus = estatus;
        this.cliente = cliente;
        this.empleado = empleado;
    }

    public Venta(String fechaHora, int estatus, Cliente cliente, Empleado empleado) {
        this.fechaHora = fechaHora;
        this.estatus = estatus;
        this.cliente = cliente;
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", fechaHora=").append(fechaHora);
        sb.append(", estatus=").append(estatus);
        sb.append(", cliente:").append(cliente != null ? cliente.toString() : "null");
        sb.append(", empleado:").append(empleado != null ? empleado.toString() : "null");
        sb.append('}');
        return sb.toString();
    }

}
