package org.utl.dsm.primer_parcial.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.Sucursal;
import org.utl.dsm.primer_parcial.model.User;

/**
 *
 * @author josue
 */
public class ControllerAccess {

    public void logIn(User u) throws SQLException {
        
        String query = """
                 SELECT idUsuario FROM usuario WHERE usuario='%S' && passw='%S';
                 """;
        query = String.format(query, u.getUsuario(), u.getPassw());

        ConexionMySql cnnMysql = new ConexionMySql();
        Connection conn = cnnMysql.open();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            u.setId(rs.getInt("idUsuario"));
            
        }
        
        rs.close();
        stmt.close();
        conn.close();
        cnnMysql.close();
    }

    public void saveToken(User u) throws SQLException {
        String query = """
                   UPDATE usuario SET token="%S" WHERE idUsuario="%S";
                   """;
        query = String.format(query, u.getToken(), u.getId());

        ConexionMySql conMySQL = new ConexionMySql();
        Connection conn = conMySQL.open();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        stmt.close();
        conn.close();
        conMySQL.close();
    }

    public void deleteToken(String token) throws SQLException {
        String query = """
                   UPDATE usuario SET token="" WHERE token ="%S";
                   """;
        query = String.format(query, token);
        ConexionMySql conMySQL = new ConexionMySql();
        Connection conn = conMySQL.open();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        stmt.close();
        conn.close();
        conMySQL.close();
    }

    public boolean authToken(String token) throws SQLException {
        boolean result = false;
        String query = "SELECT * FROM usuario WHERE token=?";
        ConexionMySql conMySQL = new ConexionMySql();
        Connection conn = conMySQL.open();
        PreparedStatement psmt = conn.prepareStatement(query);
        psmt.setString(1, token);
        ResultSet rs = psmt.executeQuery();

        if (rs.next()) {
            result = true;
        }
        rs.close();
        psmt.close();
        conn.close();
        conMySQL.close();
        return result;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> listUsers = new ArrayList<>();
        String query = "SELECT * FROM usuario;";
        //2. Se establece la conexion con la BD
        ConexionMySql connMySQL = new ConexionMySql();
        //3. Se abre la conexion
        Connection conn = connMySQL.open();
        //4. Se genera el statement para enviar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        //5. Se prepara un ResultSet para obtener la respuesta de la BD
        ResultSet rs = pstmt.executeQuery();
        //6. Recorrer el rs y extraer los datos

        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("idUsuario"));
            u.setUsuario(rs.getString("usuario"));
            u.setPassw(rs.getString("passw"));
            u.setToken(rs.getString("token"));
            listUsers.add(u);
        }
        //7. Cerrar todos los objetos
        rs.close();
        pstmt.close();
        conn.close();
        connMySQL.close();
        return listUsers;
    }
}
