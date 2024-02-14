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
import javax.swing.JOptionPane;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
import org.utl.dsm.primer_parcial.model.Producto;

/**
 *
 * @author josue
 */
public class ControllerProducto {
    public List<Producto> getAll() {
        List<Producto> listaProducto = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM producto";
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
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setNombreGenerico(rs.getString("nombreGenerico"));
                p.setFormaFarmaceutica(rs.getString("formaFarmaceutica"));
                p.setUnidadMedida(rs.getString("unidadMedida"));
                p.setPresentacion(rs.getString("presentacion"));
                p.setPrincipalIndicacion(rs.getString("indicaciones"));
                p.setContraindicaciones(rs.getString("contraindicaciones"));
                p.setConcentracion(rs.getString("concentracion"));
                p.setUnidadesEnvase(rs.getInt("unidadesEnvase"));
                p.setPrecioCompra(rs.getFloat("precioCompra"));
                p.setPrecioVenta(rs.getFloat("precioVenta"));
                p.setFoto(rs.getString("foto"));
                p.setRutaFoto(rs.getString("rutaFoto"));
                p.setCodigoBarras(rs.getString("codigoBarras"));
                p.setEstatus(rs.getInt("estatus"));
              

                listaProducto.add(p);
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
        return listaProducto;
    }
    public List<Producto> buscar(String id) {
        List<Producto> listaProducto = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM producto WHERE id=?";
            //2. Establecer conexion con la BD
            ConexionMySql connMySQL = new ConexionMySql();
            //3. Abrir conexion
            Connection con = connMySQL.open();
            //4. Se genera el statement para enviar la consulta
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            //5. Se prepara un ResultSetpara obtener la respueta de la base de datos 
            ResultSet rs = pstmt.executeQuery();
            //6. recorrer el rs y extraer los datos
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setNombreGenerico(rs.getString("nombreGenerico"));
                p.setFormaFarmaceutica(rs.getString("formaFarmaceutica"));
                p.setUnidadMedida(rs.getString("unidadMedida"));
                p.setPresentacion(rs.getString("presentacion"));
                p.setPrincipalIndicacion(rs.getString("indicaciones"));
                p.setContraindicaciones(rs.getString("contraindicaciones"));
                p.setConcentracion(rs.getString("concentracion"));
                p.setUnidadesEnvase(rs.getInt("unidadesEnvase"));
                p.setPrecioCompra(rs.getFloat("precioCompra"));
                p.setPrecioVenta(rs.getFloat("precioVenta"));
                p.setFoto(rs.getString("foto"));
                p.setRutaFoto(rs.getString("rutaFoto"));
                p.setCodigoBarras(rs.getString("codigoBarras"));
                p.setEstatus(rs.getInt("estatus"));
              

                listaProducto.add(p);
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
        return listaProducto;
    }
}
