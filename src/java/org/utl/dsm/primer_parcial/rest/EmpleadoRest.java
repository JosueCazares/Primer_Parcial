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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.primer_parcial.controller.ControllerEmpleado;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.efirma;

/**
 *
 * @author josue
 */
@Path("empleado")
public class EmpleadoRest {

    @POST
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@FormParam("t") @DefaultValue("") String token) {
        //System.out.println(token.isEmpty());
        String out="";
        int lenght = token.length();
        System.out.println("Lon: "+lenght);
        if (lenght<=5) {
            out="""
            {"ERROR":"Problemas en el servidor de la base de datos,contacta a tu administrador"}
            """;
        }else{
        ControllerEmpleado objCE = new ControllerEmpleado();
        List<Empleado> listaEmpleados = objCE.getAll();

        Gson objGS = new Gson();
         out = objGS.toJson(listaEmpleados);
        System.out.println("a");
        }
        return Response.ok(out).build();
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idE") @DefaultValue("0") String idE,@FormParam("token") @DefaultValue("") String token) {
        String out = "";
        int lenght = token.length();
        if(lenght>5){
        try {
            ControllerEmpleado objCE = new ControllerEmpleado();
            objCE.delete(Integer.parseInt(idE));
            out = """
                {"result":"Empleado eliminado exitosamente"}
                """;
        } catch (SQLException ex) {
            out = """
                {"result":"Error en la eliminacion"}
                """;
        }
        }else{
         out = """
                {"result":"Sin acceso"}
                """;
        }
        return Response.ok(out).build();
    }

    @POST
    @Path("activar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activar(@FormParam("idE") @DefaultValue("") String idE,@FormParam("token") @DefaultValue("") String token) {
        String out = "";
        int lenght = token.length();
        if(lenght>5){
        try {
            ControllerEmpleado objCE = new ControllerEmpleado();
            objCE.activar(Integer.parseInt(idE));
            out = """
                {"result":"Empleado activado exitosamente"}
                """;
        } catch (SQLException ex) {
            out = """
                {"result":"Error en la activacion"}
                """;
        }
        }else{
         out = """
                {"result":"Sin acceso"}
                """;
        }
        return Response.ok(out).build();
    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("e") @DefaultValue("") String empleado,@FormParam("token") @DefaultValue("") String token) {
        Gson objGson = new Gson();
        Empleado e = objGson.fromJson(empleado, Empleado.class);
        String out = "";
        ControllerEmpleado objCE = new ControllerEmpleado();
         int lenght = token.length();
          System.out.println("Lon: "+lenght);
         if (lenght<=5) {
            out="""
            {"ERROR":"Problemas en el servidor de la base de datos,contacta a tu administrador"}
            """;
        }else{
        try {
            int idEmpleadoGenerado = objCE.insert(e);
            out = """
                  {"result":"Empleado insertado exitosamente con id % s "}
                          
                     """;
           // out = String.format(out, idEmpleadoGenerado);
        } catch (SQLException ex) {
             System.out.println(e);
            out = """
                {"error":"error al insertar empleado"}
                """;
             System.err.println("Error al insertar empleado:");
        ex.printStackTrace(System.err);
        }
         }
        return Response.ok(out).build();
    }

    @POST
    @Path("modificar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(@FormParam("e") @DefaultValue("{}") String jsonEmpleado, @FormParam("idE") @DefaultValue("0") int idEmpleado
            ,@FormParam("token") @DefaultValue("") String token) {
        ControllerEmpleado ct = new ControllerEmpleado();
        Gson objGS = new Gson();
        String out = "";
        int lenght = token.length();
        if(lenght>5){
        Empleado empleado = objGS.fromJson(jsonEmpleado, Empleado.class);
        
        try {
            out = objGS.toJson(ct.modificarEmp(empleado, idEmpleado));
            out="""
                {"result":"EXITO"}
                """;
            System.out.println(idEmpleado);
        } catch (SQLException ex) {
            System.out.println(jsonEmpleado);
            //ex.printStackTrace();
              out = """
                {"result":"Error"}
                """;
        }
        }else{
          out = """
                {"result":"Sin acceso"}
                """;
        }
        // Devuelve una respuesta con el JSON del empleado modificado
        return Response.ok(out).build();
    }
    
    @POST
    @Path("efirma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificar(@FormParam("cadena") @DefaultValue("") String cadena) {
//    Gson obGs = new Gson();
//    efirma e = obGs.fromJson(cadena, efirma.class);
//    e.encode();
//    System.out.println(e.toString());
    String out = "{\"success\":true, \"result\":\"efirma " + cadena + "\"}";
    System.out.println("efirma: "+cadena);
    out = String .format(out, cadena);
    return Response.ok(out).build();
    }
    
}
