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
import java.util.List;
import org.utl.dsm.primer_parcial.controller.ControllerCliente;
import org.utl.dsm.primer_parcial.model.Cliente;

/**
 *
 * @author josue
 */
@Path("cliente")
public class ClienteREST {
    @Path("getAllClie")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        
        ControllerCliente objCE= new ControllerCliente();
        List<Cliente> listaClientes = objCE.getAll();
        Gson objGS= new Gson();
        String out=objGS.toJson(listaClientes);
        return Response.ok(out).build();
    }
    
    @Path("buscar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@FormParam("telefono") @DefaultValue("") String telefono){
        System.out.println(telefono);
//        String telefon = "477";
//        System.out.println("El telefono: "+telefon);
        ControllerCliente objCE= new ControllerCliente();
        List<Cliente> listaClientes = objCE.buscar(telefono);
        System.out.println(listaClientes);
        Gson objGS= new Gson();
        String out=objGS.toJson(listaClientes);
        return Response.ok(out).build();
    }
}
