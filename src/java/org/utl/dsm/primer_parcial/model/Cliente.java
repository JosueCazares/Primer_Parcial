/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

/**
 *
 * @author josue
 */
public class Cliente {
    private int idCliente;
    private String email;
    //private String fechaRegistro;
    private int estatus;
    private Persona persona;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cliente() {
    }

    public Cliente(String email, int estatus, Persona persona) {
        this.email = email;
        this.estatus = estatus;
        this.persona = persona;
    }

    public Cliente(int idCliente, String email, int estatus, Persona persona) {
        this.idCliente = idCliente;
        this.email = email;
        this.estatus = estatus;
        this.persona = persona;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("idCliente=").append(idCliente);
        sb.append(", email=").append(email);
        sb.append(", estatus=").append(estatus);
        sb.append(", persona:").append(persona != null ? persona.toString() : "null");
        sb.append('}');
        return sb.toString();
    }
    
}
