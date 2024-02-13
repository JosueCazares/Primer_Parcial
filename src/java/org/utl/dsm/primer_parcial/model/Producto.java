
package org.utl.dsm.primer_parcial.model;

public class Producto {

    private int idProducto;
    private String nombre;
    private String nombreGenerico;
    private String formaFarmaceutica;
    private String unidadMedida;
    private String presentacion;
    private String principalIndicacion;
    private String contraindicaciones;
    private String concentracion;
    private int unidadesEnvase;
    private float precioCompra;
    private float precioVenta;
    private String foto;  // Puedes cambiar esto a un tipo más adecuado según tus necesidades
    private String rutaFoto;
    private String codigoBarras;
    private int estatus;

    public Producto() {
        // Constructor vacío
    }

    public Producto(int idProducto, String nombre, String nombreGenerico, String formaFarmaceutica,
                    String unidadMedida, String presentacion, String principalIndicacion,
                    String contraindicaciones, String concentracion, int unidadesEnvase,
                    float precioCompra, float precioVenta, String foto, String rutaFoto,
                    String codigoBarras, int estatus) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.nombreGenerico = nombreGenerico;
        this.formaFarmaceutica = formaFarmaceutica;
        this.unidadMedida = unidadMedida;
        this.presentacion = presentacion;
        this.principalIndicacion = principalIndicacion;
        this.contraindicaciones = contraindicaciones;
        this.concentracion = concentracion;
        this.unidadesEnvase = unidadesEnvase;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.foto = foto;
        this.rutaFoto = rutaFoto;
        this.codigoBarras = codigoBarras;
        this.estatus = estatus;
    }

    // Getters y setters para todos los campos
  public int getIdProducto() {   return idProducto;   }
    public void setIdProducto(int idProducto) {  this.idProducto = idProducto;  }

    public String getNombre() {   return nombre;  }

    public void setNombre(String nombre) {   this.nombre = nombre; }

    public String getNombreGenerico() {  return nombreGenerico; }
    public void setNombreGenerico(String nombreGenerico) {   this.nombreGenerico = nombreGenerico; }

    public String getFormaFarmaceutica() {    return formaFarmaceutica; }
    public void setFormaFarmaceutica(String formaFarmaceutica) {    this.formaFarmaceutica = formaFarmaceutica;  }

    public String getUnidadMedida() {   return unidadMedida;  }
    public void setUnidadMedida(String unidadMedida) {    this.unidadMedida = unidadMedida;   }

    public String getPresentacion() {  return presentacion; }  
    public void setPresentacion(String presentacion) {   this.presentacion = presentacion;  }

    public String getPrincipalIndicacion() {   return principalIndicacion; }
    public void setPrincipalIndicacion(String principalIndicacion) {     this.principalIndicacion = principalIndicacion; }

    public String getContraindicaciones() {    return contraindicaciones; }
    public void setContraindicaciones(String contraindicaciones) {  this.contraindicaciones = contraindicaciones;  }

    public String getConcentracion() {     return concentracion;  }
    public void setConcentracion(String concentracion) {    this.concentracion = concentracion;  }

    public int getUnidadesEnvase() {   return unidadesEnvase;  }
    public void setUnidadesEnvase(int unidadesEnvase) {   this.unidadesEnvase = unidadesEnvase;   }

    public float getPrecioCompra() {  return precioCompra;  }
    public void setPrecioCompra(float precioCompra) {  this.precioCompra = precioCompra;   }

    public float getPrecioVenta() {  return precioVenta; }
    public void setPrecioVenta(float precioVenta) {   this.precioVenta = precioVenta; }

    public String getFoto() {  return foto; }
    public void setFoto(String foto) {      this.foto = foto;  }
    
    public String getRutaFoto() { return rutaFoto;   }
    public void setRutaFoto(String rutaFoto) {  this.rutaFoto = rutaFoto;  }

    public String getCodigoBarras() {      return codigoBarras;  }
    public void setCodigoBarras(String codigoBarras) {   this.codigoBarras = codigoBarras;  }

    public int getEstatus() {    return estatus; }
    public void setEstatus(int estatus) {  this.estatus = estatus;  }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("idProducto:").append(idProducto);
        sb.append(", nombre:'").append(nombre).append('\'');
        sb.append(", nombreGenerico:'").append(nombreGenerico).append('\'');
        sb.append(", formaFarmaceutica:'").append(formaFarmaceutica).append('\'');
        sb.append(", unidadMedida:'").append(unidadMedida).append('\'');
        sb.append(", presentacion:'").append(presentacion).append('\'');
        sb.append(", principalIndicacion:'").append(principalIndicacion).append('\'');
        sb.append(", contraindicaciones:'").append(contraindicaciones).append('\'');
        sb.append(", concentracion:'").append(concentracion).append('\'');
        sb.append(", unidadesEnvase:").append(unidadesEnvase);
        sb.append(", precioCompra:").append(precioCompra);
        sb.append(", precioVenta:").append(precioVenta);
        sb.append(", foto:'").append(foto).append('\'');
        sb.append(", rutaFoto:'").append(rutaFoto).append('\'');
        sb.append(", codigoBarras:'").append(codigoBarras).append('\'');
        sb.append(", estatus:").append(estatus);
        sb.append('}');
        return sb.toString();
    }

  
}
