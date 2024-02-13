
package org.utl.dsm.primer_parcial.model;


public class Persona {
    private int idPersona;
    private String nombre;
    private String apPat;
    private String apMat;
    private String genero;
    private String fechaNac;
    private String rfc;
    private String curp;
    private String domicilio;
    private String cpPersona;
    private String ciudad;
    private String estado;
    private String telefono;
    private String foto;

 

    public Persona() {
    }

    public Persona(String nombre, String apPat, String apMat, String genero, String fechaNac, String rfc, String curp, String domicilio, String cpPersona, String ciudad, String estado, String telefono, String foto) {
        this.nombre = nombre;
        this.apPat = apPat;
        this.apMat = apMat;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.rfc = rfc;
        this.curp = curp;
        this.domicilio = domicilio;
        this.cpPersona = cpPersona;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
        this.foto = foto;
    }
    
    public Persona(int idPersona, String nombre, String apPat, String apMat, String genero, String fechaNac, String rfc, String curp, String domicilio, String cpPersona, String ciudad, String estado, String telefono, String foto) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apPat = apPat;
        this.apMat = apMat;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.rfc = rfc;
        this.curp = curp;
        this.domicilio = domicilio;
        this.cpPersona = cpPersona;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
        this.foto = foto;
    }

    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPat() {
        return apPat;
    }

    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    public String getApMat() {
        return apMat;
    }

    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getcpPersona() {
        return cpPersona;
    }

    public void setcpPersona(String cpPersona) {
        this.cpPersona = cpPersona;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{");
        sb.append("idPersona:").append(idPersona);
        sb.append(", nombre:").append(nombre);
        sb.append(", apPat:").append(apPat);
        sb.append(", apMat:").append(apMat);
        sb.append(", genero:").append(genero);
        sb.append(", fechaNac:").append(fechaNac);
        sb.append(", rfc:").append(rfc);
        sb.append(", curp:").append(curp);
        sb.append(", domicilio:").append(domicilio);
        sb.append(", cpPersona:").append(cpPersona);
        sb.append(", ciudad:").append(ciudad);
        sb.append(", estado:").append(estado);
        sb.append(", telefono:").append(telefono);
        sb.append(", foto:").append(foto);
        sb.append('}');
        return sb.toString();
    }
    
    
}
