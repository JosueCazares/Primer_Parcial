/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller.mongo;

/**
 *
 * @author josue
 */
public class Usuario {
    private String nombre;
    private int matricula;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Usuario(String nombre, int matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("nombre=").append(nombre);
        sb.append(", matricula=").append(matricula);
        sb.append('}');
        return sb.toString();
    }
    
    
}
