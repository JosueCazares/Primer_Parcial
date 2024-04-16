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
import org.utl.dsm.primer_parcial.controller.ControllerVenta;
import org.utl.dsm.primer_parcial.model.DetalleVenta;
import org.utl.dsm.primer_parcial.model.Venta;

/**
 *
 * @author josue
 */
@Path("venta")
public class VentaRest {
 
    @Path("getAll")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@FormParam("t") @DefaultValue("") String token){
        String out="";
        int lenght = token.length();
        System.out.println("Lon: "+lenght);
        if (lenght<=5) {
            out="""
            {"ERROR":"Problemas en el servidor de la base de datos,contacta a tu administrador"}
            """;
        }else{
        ControllerVenta objv= new ControllerVenta();
        List<DetalleVenta> listaDetalleveta = objv.getAll();
        Gson objGS= new Gson();
        out=objGS.toJson(listaDetalleveta);}
        return Response.ok(out).build();
    }
@POST
@Path("create")
@Produces(MediaType.APPLICATION_JSON)
public Response create(@FormParam("venta") String venta){
//    System.out.println("hola");
    System.out.println(venta);
    Gson objGson = new Gson();
        Venta v = objGson.fromJson(venta, Venta.class);
        System.out.println(v.toString());
        String out = "";
        ControllerVenta objCE = new ControllerVenta();
            boolean res = objCE.generarVenta(v);
            if(res== true){
            out = """
                  {"success":"Venta insertada exitosamente"}
                          
                     """;
            }else{
                out = """
                  {"error":"error"}
                          
                     """;
            }
//        }catch(){
//        out = """
//                  {"result":"Error insertar "}
//                          
//                     """;
//        }// out = String.format(out, idEmpleadoGenerado);
        out = String.format(out);
        return Response.ok(out).build();
}

}
