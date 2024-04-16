/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
//import org.utl.dsm.primer_parcial.controller.mongo.Usuario;
import org.utl.dsm.primer_parcial.model.Cliente;
import org.utl.dsm.primer_parcial.model.DetalleVenta;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.Persona;
import org.utl.dsm.primer_parcial.model.Producto;
import org.utl.dsm.primer_parcial.model.Sucursal;
import org.utl.dsm.primer_parcial.model.User;
import org.utl.dsm.primer_parcial.model.Venta;
/**
 *
 * @author LOL, QUE MAL
 */
public class ControllerVenta {
    
    public void generarVFail(Venta v) {
    Connection con = null;
    try {
        ConexionMySql connMySQL = new ConexionMySql();
        con = connMySQL.open();
        con.setAutoCommit(false);

        String insertVentaQuery = "INSERT INTO venta(fechaHora,estatus,idCliente,idEmpleado) VALUES(NOW(),1,?,?)";
        try (PreparedStatement ventaStatement = con.prepareStatement(insertVentaQuery, Statement.RETURN_GENERATED_KEYS)) {
            ventaStatement.setInt(1, v.getCliente().getIdCliente());
            ventaStatement.setInt(2, v.getEmpleado().getIdEmpleado());
            ventaStatement.executeUpdate();

            try (ResultSet rs = ventaStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setIdVenta(rs.getInt(1));
                }
            }
        }

        String insertDetalleVentaQuery = "INSERT INTO detalleVenta(idVenta,idProducto,precioVenta,cantidad) VALUES(?,?,?,?)";
        try (PreparedStatement detalleVentaStatement = con.prepareStatement(insertDetalleVentaQuery)) {
            for (DetalleVenta dv : v.getListaDV()) {
                detalleVentaStatement.setInt(1, v.getIdVenta());
                detalleVentaStatement.setInt(2, dv.getProducto().getIdProducto());
                detalleVentaStatement.setFloat(3, dv.getPrecioVenta());
                detalleVentaStatement.setInt(4, dv.getCantidad());
                detalleVentaStatement.addBatch();
            }
            detalleVentaStatement.executeBatch();
        }

        String updateInventarioQuery = "UPDATE inventario SET existencias = existencias - ? WHERE idProducto = ? AND idSucursal = ?";
        try (PreparedStatement updateInventarioStatement = con.prepareStatement(updateInventarioQuery)) {
            for (DetalleVenta dv : v.getListaDV()) {
                updateInventarioStatement.setInt(1, dv.getCantidad());
                updateInventarioStatement.setInt(2, dv.getProducto().getIdProducto());
                updateInventarioStatement.setInt(3, v.getEmpleado().getSucursal().getIdSucursal());
                updateInventarioStatement.addBatch();
            }
            updateInventarioStatement.executeBatch();
        }

        con.commit();
    } catch (SQLException ex) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }
        ex.printStackTrace();
    } finally {
        try {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
public List<DetalleVenta> getAll() {
        List<DetalleVenta> listaDetalleVenta = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM detalleVenta_View";
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
                Producto prd = new Producto();
                    prd.setIdProducto(rs.getInt("idProducto"));
                    prd.setNombre(rs.getString("nombreProducto"));
                    prd.setNombreGenerico(rs.getString("nombreGenericoProducto"));
                    prd.setFormaFarmaceutica(rs.getString("formaFarmaceuticaProducto"));
                    prd.setUnidadMedida(rs.getString("unidadMedidaProducto"));
                    prd.setPresentacion(rs.getString("presentacionProducto"));
                    prd.setPrecioVenta(rs.getInt("precioVenta"));
                Persona pe = new Persona();
                    pe.setNombre(rs.getString("nombreEmpleado"));
                    pe.setApPat(rs.getString("apellidoPaternoEmpleado"));
                    pe.setApMat(rs.getString("apellidoMaternoEmpleado"));
                    pe.setTelefono(rs.getString("telefonoEmpleado"));
                    pe.setFoto(rs.getString("fotoEmpleado"));
                Persona pcl = new Persona();
                    pcl.setNombre(rs.getString("nombreCliente"));
                    pcl.setApPat(rs.getString("apellidoPaternoCliente"));
                    pcl.setApMat(rs.getString("apellidoMaternoCliente"));
                    pcl.setCiudad(rs.getString("ciudadCliente"));
                    pcl.setEstado(rs.getString("estadoCliente"));
                    pcl.setTelefono(rs.getString("telefonoCliente"));
                    pcl.setFoto(rs.getString("fotoCliente"));
                Sucursal s = new Sucursal();
                    s.setIdSucursal(rs.getInt("idSucursalEmpleado"));
                    s.setNombre(rs.getString("nombreSucursalEmpleado"));
                    s.setTelefono(rs.getString("telefonoSucursalEmpleado"));
                User usr = new User();
                usr.setId(rs.getInt("idUsuarioEmpleado"));
                usr.setUsuario(rs.getString("nombreUsuarioEmpleado"));
                Empleado e = new Empleado();
                    e.setIdEmpleado(rs.getInt("idEmpleado"));
                    e.setEmail(rs.getString("emailEmpleado"));
                    e.setCodigo(rs.getString("codigoEmpleado"));
                    e.setFechaIngreso(rs.getString("fechaIngresoEmpleado"));
                    e.setPuesto(rs.getString("puestoEmpleado"));
                    e.setActivo(rs.getInt("activoEmpleado"));
                    e.setSucursal(s);
                    e.setUsuario(usr);
                    e.setPersona(pe);
                Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("idCliente"));
                    c.setEmail(rs.getString("emailCliente"));
                    c.setEstatus(rs.getInt("estatusCliente"));
                    c.setPersona(pcl);
                Venta v = new Venta();
                    v.setCliente(c);
                    v.setEmpleado(e);
                    v.setFechaHora(rs.getString("fechaVenta"));
                    v.setEstatus(rs.getInt("estatusVenta"));
                DetalleVenta dt = new DetalleVenta();
                    dt.setCantidad(rs.getInt("cantidad"));
                    dt.setPrecioVenta(rs.getInt("precioVenta"));
                    dt.setProducto(prd);
                    dt.setVenta(v);
                listaDetalleVenta.add(dt);
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
        return listaDetalleVenta;
    }
    public boolean generarVenta(Venta v)
    {
        Connection conn = null;
        
        try
        {
            ConexionMySql connMySQL = new ConexionMySql();
            conn = connMySQL.open();
            conn.setAutoCommit(false);
            String query1 = "INSERT INTO venta(fechaHora,idCliente,idEmpleado,estatus) VALUES(NOW(),?,?,?);";
            PreparedStatement pstmt1 = conn.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS);
            String query2 = "INSERT INTO detalleVenta(idVenta,idProducto,precioVenta,cantidad) VALUES(?,?,?,?);";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            String query3 = "UPDATE inventario SET existencias=existencias-? WHERE idProducto=? AND idSucursal=?;";
            PreparedStatement pstmt3 = conn.prepareStatement(query3);
            String query4 ="SELECT existencias FROM inventario WHERE idProducto=? AND idSucursal=?;";
            PreparedStatement pstmt4 = conn.prepareStatement(query4);
            ResultSet rs;
             ResultSet rs2;
            pstmt1.setInt(1, v.getCliente().getIdCliente());
            pstmt1.setInt(2, v.getEmpleado().getIdEmpleado());
            pstmt1.setInt(3, 1);
            pstmt1.executeUpdate();
            rs = pstmt1.getGeneratedKeys();
            if (rs.next())
            {
                v.setIdVenta(rs.getInt(1));
 
                for (int i = 0; i < v.getListaDV().size(); i++)
                {
                    pstmt4.setInt(1, v.getListaDV().get(i).getProducto().getIdProducto());
                    pstmt4.setInt(2, v.getEmpleado().getSucursal().getIdSucursal());
                    rs2=pstmt4.executeQuery();
                   int existenciasP=0;
                    if (rs2.next()) {
                    existenciasP=rs2.getInt(1);
                    }
                    if (v.getListaDV().get(i).getCantidad()<=existenciasP) {
                        
                    
                    pstmt2.setInt(1, v.getIdVenta());
                    pstmt2.setInt(2, v.getListaDV().get(i).getProducto().getIdProducto());
                    pstmt2.setFloat(3, v.getListaDV().get(i).getPrecioVenta());
                    pstmt2.setInt(4, v.getListaDV().get(i).getCantidad());
                    pstmt2.addBatch();
                    pstmt3.setInt(1, v.getListaDV().get(i).getCantidad());
                    pstmt3.setInt(2, v.getListaDV().get(i).getProducto().getIdProducto());
                    pstmt3.setInt(3, v.getEmpleado().getSucursal().getIdSucursal());
                    pstmt3.addBatch();
                }else{
                   conn.rollback();
                   return false;
                    }
                    }
                pstmt2.executeBatch();
                pstmt3.executeBatch();
                conn.commit();
            }
            else
            {
                conn.rollback();
            }
            rs.close();
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        } finally
        {
            try
            {
                if (!conn.isClosed())
                    conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        //System.out.println("aqui");
        return true;
    }
    
    
}
