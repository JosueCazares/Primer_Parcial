/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.primer_parcial.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
import org.utl.dsm.primer_parcial.model.Cliente;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.Persona;
import org.utl.dsm.primer_parcial.model.Sucursal;
import org.utl.dsm.primer_parcial.model.User;

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
        ControllerEmpleado obCE = new ControllerEmpleado();
        ControllerCliente obCc = new ControllerCliente();
//        Persona p = new Persona("YAEL", "B", "C", "M", "2008/08/08", "D", "E", "F", "G", "H", "I", "J", "K");
//        Sucursal s = new Sucursal();
//        s.setIdSucursal(1);
//        User u = new User("H", "G","ra");
//        Empleado e = new Empleado("BBBB", "BBBB", "2024/08/01", "BOSsS", 10, 1, p, u, s);
//        //obCE.insert(e);
        //obCE.modificarEmp(e, 2);
//Persona p = new Persona
        //obCE.delete(1);
        //obCE.activar(1);
           List<Cliente> lu = obCc.getAll();
     for(Cliente cliente: lu){
         System.out.println(cliente);
     }
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
}
