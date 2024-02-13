/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.model;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author josue
 */
public class efirma {
    private String cadena;

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public efirma(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("efirma{");
        sb.append("cadena=").append(cadena);
        sb.append('}');
        return sb.toString();
    }
    
    public void encode(){
        this.cadena=DigestUtils.md5Hex(this.cadena);
    }	
}
