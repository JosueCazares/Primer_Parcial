/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.primer_parcial.controller.ControllerAccess;
import org.utl.dsm.primer_parcial.controller.ControllerEmpleado;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.Persona;
import org.utl.dsm.primer_parcial.model.User;

/**
 *
 * @author josue
 */
@Path("acceso")
public class AccessRest {
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("user") @DefaultValue("") String user){
        Persona p = new Persona();
        Empleado e  =  new Empleado();
        ControllerEmpleado objCe = new ControllerEmpleado();
    Gson objGs = new Gson();
    User u = objGs.fromJson(user, User.class);
    String out ="";
    e.setPersona(p);
    u.getId();
    e.setUsuario(u);
    u.encode();
        ControllerAccess objCa = new ControllerAccess();
        try {
            objCa.logIn(u);
            if(u.getId()>0){
                u.setToken();
                objCa.saveToken(u);
                objCe.identificarEmp(u, e);
                System.out.println("Este es el id: "+e.getIdEmpleado());
            }
            out=objGs.toJson(e);
            System.out.println("RESPUESTA"+out);
        } catch (SQLException ex) {
            out="""
                {"ERROR":"Problemas en el servidor de la base de datos,contacta a tu administrador"}
                """;
        }
    return Response.ok(out).build();
    }
    
    
    @Path("logout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logOut(@FormParam("t") @DefaultValue("") String t) {
    ControllerAccess objCo = new ControllerAccess();
    String out="";
    
        try {
            objCo.deleteToken(t);
            out="""
                {"result":"OK"}
               """;
        } catch (SQLException ex) {
        out="""
            {"error":"Error al cerrar secion,consulta al administrador del sistema"}
            """;   
                }
            return Response.ok(out).build();

    }
    
    @Path("getAllUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser(@FormParam("t") @DefaultValue("") String token){
        String out="";
        if(token.isEmpty()){
        out="""
            {"ERROR":"Problemas en el servidor de la base de datos,contacta a tu administrador"}
            """;
        }else{
        try {
            ControllerAccess objCE= new ControllerAccess();
            List<User> listaClientes = objCE.getAllUser();
            Gson objGS= new Gson();
             out=objGS.toJson(listaClientes);
            return Response.ok(out).build();
        } catch (SQLException ex) {
                        ex.printStackTrace();
        }
        }
        return Response.ok(out).build();
    }
    
    
}
