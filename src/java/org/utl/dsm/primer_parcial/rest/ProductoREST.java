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
import org.utl.dsm.primer_parcial.controller.ControllerProducto;
import org.utl.dsm.primer_parcial.model.Producto;

/**
 *
 * @author josue
 */
@Path("producto")
public class ProductoREST {
    @Path("getAllPr")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        
        ControllerProducto objPr= new ControllerProducto();
        List<Producto> listaClientes = objPr.getAll();
        Gson objGS= new Gson();
        String out=objGS.toJson(listaClientes);
        return Response.ok(out).build();
    }
    @Path("buscar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@FormParam("idP") @DefaultValue("") String idP){
        //System.out.println(idP);
//        String telefon = "477";
//        System.out.println("El telefono: "+telefon);
        ControllerProducto objPr= new ControllerProducto();
        List<Producto> listaProducto = objPr.buscar(idP);
        //System.out.println(listaProducto);
        Gson objGS= new Gson();
        String out=objGS.toJson(listaProducto);
        return Response.ok(out).build();
    }
}
