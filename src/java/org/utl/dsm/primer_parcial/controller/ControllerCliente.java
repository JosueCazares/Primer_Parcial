/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
import org.utl.dsm.primer_parcial.model.Cliente;
import org.utl.dsm.primer_parcial.model.Persona;

/**
 *
 * @author josue
 */
public class ControllerCliente {
      public List<Cliente> getAll() {
        List<Cliente> listaEmpleados = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM v_cliente";
            //2. Establecer conexion con la BD
            ConexionMySql connMySQL = new ConexionMySql();
            //3. Abrir conexion
            Connection con = connMySQL.open();
            //4. Se genera el statement para enviar la consulta
            PreparedStatement pstmt = con.prepareStatement(query);
            //5. Se prepara un ResultSetpara obtener la respueta de la base de datos 
            ResultSet rs = pstmt.executeQuery();
            //6. recorrer el rs y extraer los datos
            while (rs.next()) {
                Persona p = new Persona();
                //faltan datos 
                p.setIdPersona(rs.getInt("idPersona"));
                p.setNombre(rs.getString("nombre"));
                p.setApPat(rs.getString("apellidoPaterno"));
                p.setApMat(rs.getString("apellidoMaterno"));
                p.setTelefono(rs.getString("telefono"));
                p.setRfc(rs.getString("rfc"));

                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setEmail(rs.getString("email"));
                c.setEstatus(rs.getInt("estatus"));
                c.setPersona(p);

                listaEmpleados.add(c);
            }
            //7. cerrar todos los objetos
            rs.close();
            pstmt.close();
            con.close();
            connMySQL.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ControllerEmpleado.class.getName()).log(Level.SEVERE, null, ex);   la maestra no le gusta el loger y lo quita
            ex.printStackTrace();
        }

        //8. Devolver lainformacion 
        return listaEmpleados;
    }
      public List<Cliente> buscar(String telefono) {
        List<Cliente> listaEmpleados = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM v_cliente WHERE telefono=?";
            //2. Establecer conexion con la BD
            ConexionMySql connMySQL = new ConexionMySql();
            //3. Abrir conexion
            Connection con = connMySQL.open();
            //4. Se genera el statement para enviar la consulta
            PreparedStatement pstmt = con.prepareStatement(query);
            //4.1 paso de parametro 
            pstmt.setString(1, telefono);
            //5. Se prepara un ResultSetpara obtener la respueta de la base de datos 
            ResultSet rs = pstmt.executeQuery();
            //6. recorrer el rs y extraer los datos
            while (rs.next()) {
                Persona p = new Persona();
                //faltan datos 
                p.setIdPersona(rs.getInt("idPersona"));
                p.setNombre(rs.getString("nombre"));
                p.setApPat(rs.getString("apellidoPaterno"));
                p.setApMat(rs.getString("apellidoMaterno"));
                p.setTelefono(rs.getString("telefono"));
                p.setRfc(rs.getString("rfc"));
                p.setcpPersona(rs.getString("codigoPostal"));
                p.setDomicilio(rs.getString("domicilio"));

                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setEmail(rs.getString("email"));
                c.setEstatus(rs.getInt("estatus"));
                c.setPersona(p);

                listaEmpleados.add(c);
            }
            //7. cerrar todos los objetos
            rs.close();
            pstmt.close();
            con.close();
            connMySQL.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ControllerEmpleado.class.getName()).log(Level.SEVERE, null, ex);   la maestra no le gusta el loger y lo quita
            ex.printStackTrace();
        }

        //8. Devolver lainformacion 
        return listaEmpleados;
    }
}
