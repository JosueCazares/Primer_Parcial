
package org.utl.dsm.primer_parcial.model;


public class Empleado  {
   // Persona persona;
    private int idEmpleado;
    private String email;
    private String codigo;
    private String fechaIngreso;
    private String puesto;
    private float salarioBruto;
    private int activo;
    private Persona persona;
    private User user;
    private Sucursal sucursal;
   
    public Empleado(){
    }

    public Empleado(String email, String codigo, String fechaIngreso, String puesto, float salarioBruto, int activo, Persona persona, User user, Sucursal sucursal) {
        this.email = email;
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salarioBruto = salarioBruto;
        this.activo = activo;
        this.persona = persona;
        this.user = user;
        this.sucursal = sucursal;
    }

    public Empleado(int idEmpleado, String email, String codigo, String fechaIngreso, String puesto, float salarioBruto, int activo, Persona persona, User user, Sucursal sucursal) {
        this.idEmpleado = idEmpleado;
        this.email = email;
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salarioBruto = salarioBruto;
        this.activo = activo;
        this.persona = persona;
        this.user = user;
        this.sucursal = sucursal;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public float getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(float salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User usuario) {
        this.user = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    //@Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Empleado{");
//        sb.append("idEmpleado:").append(idEmpleado);
//        sb.append(", email:").append(email);
//        sb.append(", codigo:").append(codigo);
//        sb.append(", fechaIngreso:").append(fechaIngreso);
//        sb.append(", puesto:").append(puesto);
//        sb.append(", salarioBruto:").append(salarioBruto);
//        sb.append(", activo:").append(activo);
//        sb.append(", persona:").append(persona.toString());
//        sb.append(", usuario:").append(usuario.toString());
//        sb.append(", sucursal:").append(sucursal.toString());
//        sb.append('}');
//        return sb.toString();
//    }
    
   @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Empleado{");
    sb.append("idEmpleado:").append(idEmpleado);
    sb.append(", email:").append(email);
    sb.append(", codigo:").append(codigo);
    sb.append(", fechaIngreso:").append(fechaIngreso);
    sb.append(", puesto:").append(puesto);
    sb.append(", salarioBruto:").append(salarioBruto);
    sb.append(", activo:").append(activo);
    
    // Verificar si el objeto persona no es nulo antes de invocar toString()
    sb.append(", persona:").append(persona != null ? persona.toString() : "null");

    // Verificar si el objeto usuario no es nulo antes de invocar toString()
    sb.append(", usuario:").append(user != null ? user.toString() : "null");

    // Verificar si el objeto sucursal no es nulo antes de invocar toString()
    sb.append(", sucursal:").append(sucursal != null ? sucursal.toString() : "null");

    sb.append('}');
    return sb.toString();
}

}
