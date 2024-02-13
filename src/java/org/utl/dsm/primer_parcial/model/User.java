/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author josue
 */
public class User {
    private int id;
    private String usuario;
    private String passw;
    private String token;
    private String rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public User() {
    }

    public User(String usuario, String passw, String token, String rol) {
        this.usuario = usuario;
        this.passw = passw;
        this.token = token;
        this.rol = rol;
    }

    public User(int id, String usuario, String passw, String token, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.passw = passw;
        this.token = token;
        this.rol = rol;
    }
        public User( String usuario, String passw, String rol) {
        this.usuario = usuario;
        this.passw = passw;
        this.rol = rol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", usuario=").append(usuario);
        sb.append(", passw=").append(passw);
        sb.append(", token=").append(token);
        sb.append(", rol=").append(rol);
        sb.append('}');
        return sb.toString();
    }

 public void encode(){
        this.usuario=DigestUtils.md5Hex(this.usuario);
        this.passw=DigestUtils.md5Hex(this.passw);
    }	
  public void setToken(){
    String p1=this.usuario;
    String p2="DSM501";
    Date hora = new Date();
    String p3=hora.toString();
    
    String token=p1+"-"+p2+"-"+p3;
    this.token= DigestUtils.sha512_256Hex(token);
    }
}
