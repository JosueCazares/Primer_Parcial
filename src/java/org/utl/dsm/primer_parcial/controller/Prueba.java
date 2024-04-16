/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
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
 * @author josue
 */
public class Prueba {

    public static void main(String[] args) throws SQLException {
//        probarEncode();
        //tryConnection();
//        probarLogin();
        // probarAut();
//        ControllerAccess obCa = new ControllerAccess();
//        ControllerEmpleado obCE = new ControllerEmpleado();
        ControllerProducto obPd = new ControllerProducto();
        ControllerVenta obdv = new ControllerVenta();
//        ControllerCliente obCc = new ControllerCliente();
//        //obPd.buscarNombre("ibuprofeno");
////        Persona p = new Persona("YAEL", "B", "C", "M", "2008/08/08", "D", "E", "F", "G", "H", "I", "J", "K");
////        Sucursal s = new Sucursal();
////        s.setIdSucursal(1);
////        User u = new User("H", "G","ra");
////        Empleado e = new Empleado("BBBB", "BBBB", "2024/08/01", "BOSsS", 10, 1, p, u, s);
////        //obCE.insert(e);
//        //obCE.modificarEmp(e, 2);
////Persona p = new Persona
//        //obCE.delete(1);
//        //obCE.activar(1);
           List<DetalleVenta> lu = obdv.getAll();
     for(DetalleVenta dv: lu){
         System.out.println(dv);
     }
//        //probarIdentEmp();


//    //probarVenta();
//    Sucursal s = new Sucursal();
//    s.setIdSucursal(1);
//    Empleado e = new Empleado();
//    e.setIdEmpleado(1);
//    e.setSucursal(s);
//    Cliente c = new Cliente();
//    c.setIdCliente(2);
//    Producto p1 = new Producto();
//    Producto p2 = new Producto();
//    Producto p3 = new Producto();
//    Producto p4 = new Producto();
//    p1.setIdProducto(1);
//    p2.setIdProducto(2);
//    p3.setIdProducto(3);
//    p4.setIdProducto(4);
//    List<DetalleVenta> listadetalleVenta = new ArrayList<DetalleVenta>();
//    DetalleVenta dv1 = new DetalleVenta();
//    dv1.setProducto(p1);
//    dv1.setPrecioVenta(150);
//    dv1.setCantidad(6);
//    listadetalleVenta.add(dv1);
//    DetalleVenta dv2 = new DetalleVenta();
//    dv2.setProducto(p2);
//    dv2.setPrecioVenta(150);
//    dv2.setCantidad(6);
//    listadetalleVenta.add(dv2);
//    DetalleVenta dv3 = new DetalleVenta();
//    dv3.setProducto(p3);
//    dv3.setPrecioVenta(150);
//    dv3.setCantidad(6);
//    listadetalleVenta.add(dv3);
//    DetalleVenta dv4 = new DetalleVenta();
//    dv4.setProducto(p4);
//    dv4.setPrecioVenta(150);
//    dv4.setCantidad(6);
//    listadetalleVenta.add(dv4);
//    Venta v = new Venta();
//    v.setCliente(c);
//    v.setEmpleado(e);
//    v.setEstatus(1);
//    v.setListaDV(listadetalleVenta);
//    ControllerVenta cv = new ControllerVenta();
//        System.out.println(cv.generarVenta(v));
//    probarVenta();
    }
    public static boolean probarVenta(){
    Sucursal s = new Sucursal();
    s.setIdSucursal(1);
    Empleado e = new Empleado();
    e.setIdEmpleado(1);
    e.setSucursal(s);
    Cliente c = new Cliente();
    c.setIdCliente(2);
    Producto p1 = new Producto();
    Producto p2 = new Producto();
    Producto p3 = new Producto();
    Producto p4 = new Producto();
    p1.setIdProducto(1);
    p2.setIdProducto(2);
    p3.setIdProducto(3);
    p4.setIdProducto(4);
    List<DetalleVenta> listadetalleVenta = new ArrayList<DetalleVenta>();
    DetalleVenta dv1 = new DetalleVenta();
    dv1.setProducto(p1);
    dv1.setPrecioVenta(150);
    dv1.setCantidad(5);
    listadetalleVenta.add(dv1);
    DetalleVenta dv2 = new DetalleVenta();
    dv2.setProducto(p2);
    dv2.setPrecioVenta(150);
    dv2.setCantidad(5);
    listadetalleVenta.add(dv2);
    DetalleVenta dv3 = new DetalleVenta();
    dv3.setProducto(p3);
    dv3.setPrecioVenta(150);
    dv3.setCantidad(5);
    listadetalleVenta.add(dv3);
    DetalleVenta dv4 = new DetalleVenta();
    dv4.setProducto(p4);
    dv4.setPrecioVenta(150);
    dv4.setCantidad(5);
    listadetalleVenta.add(dv4);
    Venta v = new Venta();
    v.setCliente(c);
    v.setEmpleado(e);
    v.setEstatus(1);
    v.setListaDV(listadetalleVenta);
    ControllerVenta cv = new ControllerVenta();
     // System.out.println();
    return  cv.generarVenta(v);
    }

    public static void probarAut() {
        ControllerAccess objCa = new ControllerAccess();
        try {
            boolean r = objCa.authToken("69E5DEBE6ABEDCA083FF0E117C7CC0F871E2841471A2BB82EEE43E31E4BF43F3");
            System.out.println(r);
        } catch (SQLException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void probarEncode() {
        User u = new User();
        u.setUsuario("CAA10");
        u.setPassw("15189");
        u.encode();
        System.out.println(u.toString());
    }

    public static void tryConnection() {
        ConexionMySql objConMySql = new ConexionMySql();
        Connection conn = objConMySql.open();
        System.out.println(conn.toString());
        objConMySql.close();

    }

    public static void probarLogin() {
        User u = new User();
        u.setUsuario("yael");
        u.setPassw("123");
        u.encode();
        ControllerAccess ca = new ControllerAccess();
        try {
            ca.logIn(u);
            System.out.println(u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void probarIdentEmp() {
        ControllerEmpleado obCe = new ControllerEmpleado();
        Empleado e = new Empleado();
        User u = new User();
        Persona p = new Persona();
        e.setPersona(p);
        e.setUsuario(u);
        u.setId(1);
        try {
            obCe.identificarEmp(u, e);
            System.out.println(e.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
