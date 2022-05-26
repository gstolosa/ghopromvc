package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasUsuario {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Usuario ValidarUsuario(String nombre, String password) throws ClassNotFoundException {
        Usuario usu = new Usuario();
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND password =?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                usu.setIdusuario(rs.getInt("idusuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return usu;
    }
}
