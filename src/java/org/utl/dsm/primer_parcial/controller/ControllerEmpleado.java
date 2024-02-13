package org.utl.dsm.primer_parcial.controller;

import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.utl.dsm.primer_parcial.bd.ConexionMySql;
import org.utl.dsm.primer_parcial.model.Empleado;
import org.utl.dsm.primer_parcial.model.Persona;
import org.utl.dsm.primer_parcial.model.Sucursal;
import org.utl.dsm.primer_parcial.model.User;

public class ControllerEmpleado {

    /**
     * Metodo que devuelve todos los registros de empleados no recibe parametros
     *
     * @return Lista de objetos employee
     */
    public List<Empleado> getAll() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM v_empleado";
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
                User u = new User(rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        null,
                        null,
                        null);
                //usuario ya 
                Sucursal s = new Sucursal();
                //falta
                s.setIdSucursal(rs.getInt("idSucursal"));
                s.setNombre(rs.getString("nombreSucursal"));
                s.setTitular(rs.getString("titular"));
                s.setRfc(rs.getString("rfcTitular"));
                s.setDomicilio(rs.getString("domicilioSucursal"));
                s.setColonia(rs.getString("coloniaSucursal"));
                s.setCodigoPostal(rs.getString("cpSucursal"));
                s.setCiudad(rs.getString("ciudadSucursal"));
                s.setEstado(rs.getString("estadoSucursal"));
                s.setTelefono(rs.getString("telefonoSucursal"));
                s.setLongitud(rs.getString("longitud"));
                s.setLatitud(rs.getString("latitud"));
                s.setEstatus(rs.getInt("estatusSucursal"));
                //listo 
                Persona p = new Persona();
                //faltan datos 
                p.setNombre(rs.getString("nombre"));
                p.setApPat(rs.getString("apPat"));
                p.setApMat(rs.getString("apMAt"));
                p.setGenero(rs.getString("genero"));
                p.setFechaNac(rs.getString("fechaNacimiento"));
                p.setRfc(rs.getString("rfc"));
                p.setCurp(rs.getString("curp"));
                p.setDomicilio(rs.getString("domicilio"));
                p.setcpPersona(rs.getString("cpPersona"));
                p.setCiudad(rs.getString("ciudad"));
                p.setEstado(rs.getString("estado"));
                p.setTelefono(rs.getString("telefono"));

                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt("idEmpleado"));
                e.setEmail(rs.getString("email"));
                e.setCodigo(rs.getString("codigo"));
                e.setFechaIngreso(rs.getString("fechaIngreso"));
                e.setPuesto(rs.getString("puesto"));
                e.setSalarioBruto(rs.getInt("salarioBruto"));
                e.setActivo(rs.getInt("activo"));
                e.setUsuario(u);
                e.setSucursal(s);
                e.setPersona(p);

                listaEmpleados.add(e);
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

    public List<Sucursal> getAllSucursal() throws SQLException {
        List<Sucursal> listaSucursales = new ArrayList<>();

        //1. Crear la sentencia SQL
        String query = "SELECT * FROM sucursal";
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
            Sucursal s = new Sucursal();
            s.setIdSucursal(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setTitular(rs.getString("titular"));
            s.setCiudad(rs.getString("ciudad"));
            s.setCodigoPostal(rs.getString("codigoPostal"));
            s.setColonia(rs.getString("colonia"));
            s.setDomicilio(rs.getString("domicilio"));
            s.setEstado(rs.getString("estado"));
            s.setEstatus(rs.getInt("estatus"));
            s.setLatitud(rs.getString("latitud"));
            s.setLongitud(rs.getString("longitud"));
            s.setRfc(rs.getString("rfc"));
            s.setTelefono(rs.getString("telefono"));

            listaSucursales.add(s);
        }

        //7. Cerrar todos los objetos
        rs.close();
        pstmt.close();
        conn.close();
        connMySQL.close();

        //8. Devolver la informacion
        return listaSucursales;
    }

    public void delete(int idEmpleado) throws SQLException {
        //1.crear la sentencia SQL
        String query = "UPDATE empleado SET activo=0 WHERE idEmpleado=" + idEmpleado;
        //2.crear un objeto para la conexion con MySQL
        ConexionMySql conMySQL = new ConexionMySql();
        //3.Se abre la conexion
        Connection conn = conMySQL.open();
        //4. Crear un statement para enviar el query 
        Statement stm = conn.createStatement();
        //5. Ejecutar la sentencia 
        stm.execute(query);
        //6.Cerrar los objetos
        stm.close();
        conn.close();
        conMySQL.close();
    }

    public void activar(int idEmpleado) throws SQLException {
        //1.crear la sentencia SQL
        String query = "UPDATE empleado SET activo=1 WHERE idEmpleado=" + idEmpleado;
        //2.crear un objeto para la conexion con MySQL
        ConexionMySql conMySQL = new ConexionMySql();
        //3.Se abre la conexion
        Connection conn = conMySQL.open();
        //4. Crear un statement para enviar el query 
        Statement stm = conn.createStatement();
        //5. Ejecutar la sentencia 
        stm.execute(query);
        //6.Cerrar los objetos
        stm.close();
        conn.close();
        conMySQL.close();
    }

    public int insert(Empleado e) throws SQLException {
        //1.Generar sentencia SQL
        String query = "{CALL insertarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        //2.Crear la conexion con la BD
        ConexionMySql conMySQL = new ConexionMySql();
        //3.Se abre la conexion 
        Connection conn = conMySQL.open();
        //4.Crear el statemen t que llevara la consulta 
        CallableStatement cllb = conn.prepareCall(query);
        //5.LLenar todos los parametros de la llamada al Procedure
        cllb.setString(1, e.getPersona().getNombre());
        cllb.setString(2, e.getPersona().getApPat());
        cllb.setString(3, e.getPersona().getApMat());
        cllb.setString(4, e.getPersona().getGenero());//pendiente
        cllb.setString(5, e.getPersona().getFechaNac());
        cllb.setString(6, e.getPersona().getRfc());
        cllb.setString(7, e.getPersona().getCurp());
        cllb.setString(8, e.getPersona().getDomicilio());
        cllb.setString(9, e.getPersona().getcpPersona());
        cllb.setString(10, e.getPersona().getCiudad());
        cllb.setString(11, e.getPersona().getEstado());
        cllb.setString(12, e.getPersona().getTelefono());
        cllb.setString(13, e.getPersona().getFoto());
        //suursal
        cllb.setInt(14, e.getSucursal().getIdSucursal());

        //usuario
        cllb.setString(15, e.getUsuario().getUsuario());
        cllb.setString(16, e.getUsuario().getPassw());
        cllb.setString(17, e.getUsuario().getRol());
        cllb.setString(18, e.getEmail());
        cllb.setString(19, e.getPuesto());
        cllb.setFloat(20, e.getSalarioBruto());
        cllb.setString(21, e.getFechaIngreso());

        cllb.registerOutParameter(22, Types.INTEGER);
        cllb.registerOutParameter(23, Types.INTEGER);
        cllb.registerOutParameter(24, Types.INTEGER);
        cllb.registerOutParameter(25, Types.VARCHAR);
        //6.Ejecutar la sentencia 
        cllb.executeUpdate();
        //7.Obhtner los valores de los parametros de retorno 
        e.getPersona().setIdPersona(cllb.getInt(22));
        e.getUsuario().setId(cllb.getInt(23));
        e.setIdEmpleado(cllb.getInt(24));
        e.setCodigo(cllb.getString(25));
        //8.Cerrar los objetos
        cllb.close();
        conn.close();
        conMySQL.close();
        return e.getIdEmpleado();
    }

    public List<Empleado> buscar(String codigo) throws SQLException {
        // 1. Crear sentencia con marcador de posición
        String query = "SELECT * FROM v_empleado WHERE codigo = ?";

        List<Empleado> listaEmpleados = new ArrayList<>();

        // 2. Establecer conexión con la BD
        ConexionMySql connMySQL = new ConexionMySql();

        try (
                // 3. Abrir conexión y generar statement con PreparedStatement
                Connection con = connMySQL.open(); PreparedStatement pstmt = con.prepareStatement(query)) {
            // 4. Establecer el valor del parámetro
            pstmt.setString(1, codigo);

            // 5. Se prepara un ResultSet para obtener la respuesta de la base de datos
            try (ResultSet rs = pstmt.executeQuery()) {
                // 6. Recorrer el ResultSet y extraer los datos
                while (rs.next()) {
                    User u = new User(
                            rs.getInt("idUsuario"),
                            rs.getString("nombreUsuario"),
                            null,
                            null,
                            null);
                    //usuario ya 
                    Sucursal s = new Sucursal();
                    //falta
                    s.setIdSucursal(rs.getInt("idSucursal"));
                    s.setNombre(rs.getString("nombreSucursal"));
                    s.setTitular(rs.getString("titular"));
                    s.setRfc(rs.getString("rfcTitular"));
                    s.setDomicilio(rs.getString("domicilioSucursal"));
                    s.setColonia(rs.getString("coloniaSucursal"));
                    s.setCodigoPostal(rs.getString("cpSucursal"));
                    s.setCiudad(rs.getString("ciudadSucursal"));
                    s.setEstado(rs.getString("estadoSucursal"));
                    s.setTelefono(rs.getString("telefonoSucursal"));
                    s.setLongitud(rs.getString("longitud"));
                    s.setLatitud(rs.getString("latitud"));
                    s.setEstatus(rs.getInt("estatusSucursal"));
                    //listo 
                    Persona p = new Persona();
                    //faltan datos 
                    p.setNombre(rs.getString("nombre"));
                    p.setApPat(rs.getString("apPat"));
                    p.setApMat(rs.getString("apMAt"));
                    p.setGenero(rs.getString("genero"));
                    p.setFechaNac(rs.getString("fechaNacimiento"));
                    p.setRfc(rs.getString("rfc"));
                    p.setCurp(rs.getString("curp"));
                    p.setDomicilio(rs.getString("domicilio"));
                    p.setcpPersona(rs.getString("cpPersona"));
                    p.setCiudad(rs.getString("ciudad"));
                    p.setEstado(rs.getString("estado"));
                    p.setTelefono(rs.getString("telefono"));

                    Empleado e = new Empleado();
                    e.setIdEmpleado(rs.getInt("idEmpleado"));
                    e.setEmail(rs.getString("email"));
                    e.setCodigo(rs.getString("codigo"));
                    e.setFechaIngreso(rs.getString("fechaIngreso"));
                    e.setPuesto(rs.getString("puesto"));
                    e.setSalarioBruto(rs.getInt("salarioBruto"));
                    e.setActivo(rs.getInt("activo"));
                    e.setUsuario(u);
                    e.setSucursal(s);
                    e.setPersona(p);

                    listaEmpleados.add(e);
                }
                rs.close();
                pstmt.close();
                con.close();
                connMySQL.close();
            }
        }
        //System.out.println(listaEmpleados);
        return listaEmpleados;
    }

    public List<Empleado> found(String codigo) {
        List<Empleado> listaEmpleados = new ArrayList<>();
        try {
            //1. Crear sentencia SQL
            String query = "SELECT * FROM v_empleado WHERE codigo=" + codigo;
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
                User u = new User(
                        rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        null,
                        null,
                        null);
                //usuario ya 
                Sucursal s = new Sucursal();
                //falta
                s.setIdSucursal(rs.getInt("idSucursal"));
                s.setNombre(rs.getString("nombreSucursal"));
                s.setTitular(rs.getString("titular"));
                s.setRfc(rs.getString("rfcTitular"));
                s.setDomicilio(rs.getString("domicilioSucursal"));
                s.setColonia(rs.getString("coloniaSucursal"));
                s.setCodigoPostal(rs.getString("cpSucursal"));
                s.setCiudad(rs.getString("ciudadSucursal"));
                s.setEstado(rs.getString("estadoSucursal"));
                s.setTelefono(rs.getString("telefonoSucursal"));
                s.setLongitud(rs.getString("longitud"));
                s.setLatitud(rs.getString("latitud"));
                s.setEstatus(rs.getInt("estatusSucursal"));
                //listo 
                Persona p = new Persona();
                //faltan datos 
                p.setNombre(rs.getString("nombre"));
                p.setApPat(rs.getString("apPat"));
                p.setApMat(rs.getString("apMAt"));
                p.setGenero(rs.getString("genero"));
                p.setFechaNac(rs.getString("fechaNacimiento"));
                p.setRfc(rs.getString("rfc"));
                p.setCurp(rs.getString("curp"));
                p.setDomicilio(rs.getString("domicilio"));
                p.setcpPersona(rs.getString("cpPersona"));
                p.setCiudad(rs.getString("ciudad"));
                p.setEstado(rs.getString("estado"));
                p.setTelefono(rs.getString("telefono"));

                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt("idEmpleado"));
                e.setEmail(rs.getString("email"));
                e.setCodigo(rs.getString("codigo"));
                e.setFechaIngreso(rs.getString("fechaIngreso"));
                e.setPuesto(rs.getString("puesto"));
                e.setSalarioBruto(rs.getInt("salarioBruto"));
                e.setActivo(rs.getInt("activo"));
                e.setUsuario(u);
                e.setSucursal(s);
                e.setPersona(p);

                listaEmpleados.add(e);
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

    public Empleado modificarEmp(Empleado e, int idEmpleado) throws SQLException {
        String query = "UPDATE empleado e "
                + "JOIN persona p ON e.idPersona = p.idPersona "
                + "JOIN usuario u ON e.idUsuario = u.idUsuario "
                + "SET "
                + "p.nombre = ?, "
                + "p.apellidoPaterno = ?, "
                + "p.apellidoMaterno = ?, "
                + "p.genero = ?, "
                + "p.fechaNacimiento = ?, "
                + "p.rfc = ?, "
                + "p.curp = ?, "
                + "p.domicilio = ?, "
                + "p.codigoPostal = ?, "
                + "p.ciudad = ?, "
                + "p.estado = ?, "
                + "p.telefono = ?, "
                + "p.foto = ?, "
                + "e.email = ?, "
                + "e.codigo = ?, "
                + "e.fechaIngreso = ?, "
                + "e.puesto = ?, "
                + "e.salarioBruto = ?, "
                + "e.activo = ?, "
                + "u.usuario = ?, "
                + "u.passw = ?, "
                + "u.rol = ? "
                + "WHERE e.idEmpleado = ?";

        ConexionMySql conMySQL = new ConexionMySql();
        
                Connection conn = conMySQL.open(); 
                PreparedStatement stm = conn.prepareStatement(query);

            stm.setString(1, e.getPersona().getNombre());
            stm.setString(2, e.getPersona().getApPat());
            stm.setString(3, e.getPersona().getApMat());
            stm.setString(4, e.getPersona().getGenero());
            stm.setString(5, e.getPersona().getFechaNac());
            stm.setString(6, e.getPersona().getRfc());
            stm.setString(7, e.getPersona().getCurp());
            stm.setString(8, e.getPersona().getDomicilio());
            stm.setString(9, e.getPersona().getcpPersona());
            stm.setString(10, e.getPersona().getCiudad());
            stm.setString(11, e.getPersona().getEstado());
            stm.setString(12, e.getPersona().getTelefono());
            stm.setString(13, e.getPersona().getFoto());
            stm.setString(14, e.getEmail());
            stm.setString(15, e.getCodigo());
            stm.setString(16, e.getFechaIngreso());
            stm.setString(17, e.getPuesto());
            stm.setFloat(18, e.getSalarioBruto());
            stm.setInt(19, e.getActivo());
            stm.setString(20, e.getUsuario().getUsuario());
            stm.setString(21, e.getUsuario().getPassw());
            stm.setString(22, e.getUsuario().getRol());
            stm.setInt(23, e.getIdEmpleado());

            stm.executeUpdate();

            return e;

     
    }

    public List<Sucursal> getAllS() throws SQLException {
        List<Sucursal> listaSucursales = new ArrayList<>();

        //1. Crear la sentencia SQL
        String query = "SELECT * FROM v_sucursal";
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
            Sucursal s = new Sucursal();
            //s.setIdSucursal(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setTitular(rs.getString("titular"));
            //s.setCiudad(rs.getString("ciudad"));
            s.setCodigoPostal(rs.getString("codigoPostal"));
            //s.setColonia(rs.getString("colonia"));
            s.setDomicilio(rs.getString("domicilio"));
            //s.setEstado(rs.getString("estado"));
            //s.setEstatus(rs.getInt("estatus"));
            //s.setLatitud(rs.getString("latitud"));
            //s.setLongitud(rs.getString("longitud"));
            //s.setRfc(rs.getString("rfc"));
            s.setTelefono(rs.getString("telefono"));

            listaSucursales.add(s);
        }

        //7. Cerrar todos los objetos
        rs.close();
        pstmt.close();
        conn.close();
        connMySQL.close();

        //8. Devolver la informacion
        return listaSucursales;
    }

    public void inserEjemplo(Ejemplo e) {
        try {
            String query = "INSERT INTO ejemplo VALUES(0,?,?)";
            ConexionMySql conn = new ConexionMySql();
            Connection cn = conn.open();
            PreparedStatement prs = cn.prepareStatement(query);
            prs.setString(1, e.getNombre());
            prs.setDouble(2, e.getNumero_cuenta());
            prs.execute();
            prs.close();
            conn.close();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }

    }

    public static class Ejemplo {

        private int idEjemplo;
        private String nombre;
        private double numero_cuenta;

        public int getIdEjemplo() {
            return idEjemplo;
        }

        public void setIdEjemplo(int idEjemplo) {
            this.idEjemplo = idEjemplo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getNumero_cuenta() {
            return numero_cuenta;
        }

        public void setNumero_cuenta(double numero_cuenta) {
            this.numero_cuenta = numero_cuenta;
        }

        public Ejemplo() {
        }

        public Ejemplo(int idEjemplo, String nombre, double numero_cuenta) {
            this.idEjemplo = idEjemplo;
            this.nombre = nombre;
            this.numero_cuenta = numero_cuenta;
        }

        public Ejemplo(String nombre, double numero_cuenta) {
            this.nombre = nombre;
            this.numero_cuenta = numero_cuenta;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Ejemplo{");
            sb.append("idEjemplo=").append(idEjemplo);
            sb.append(", nombre=").append(nombre);
            sb.append(", numero_cuenta=").append(numero_cuenta);
            sb.append('}');
            return sb.toString();
        }

    }
}
