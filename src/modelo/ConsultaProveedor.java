package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaProveedor extends Conexion {

    public boolean registrar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO proveedor (razon_social,nombre,direccion,telefono,cuit,email) VALUES(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRazon());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDireccion());
            ps.setString(4, prov.getTelefono());
            ps.setInt(5, prov.getCuit());
            ps.setString(6, prov.getEmail());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public ArrayList<Proveedor> obtenerProveedor() {
        ArrayList obtenerProveedor = new ArrayList<>();
        Proveedor proveedor;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM proveedor order by idproveedor";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdprov(Integer.parseInt(rs.getString(1)));
                proveedor.setRazon(rs.getString(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));
                proveedor.setCuit(Integer.parseInt(rs.getString(6)));
                proveedor.setEmail(rs.getString(7));
                obtenerProveedor.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return obtenerProveedor;
    }

    public boolean modificar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE proveedor SET razon_social=?, nombre=?,direccion=?, telefono=?, cuit=?, email=?  WHERE idproveedor=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRazon());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDireccion());
            ps.setString(4, prov.getTelefono());
            ps.setInt(5, prov.getCuit());
            ps.setString(6, prov.getEmail());
            ps.setInt(7, prov.getIdprov());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM proveedor  WHERE idproveedor=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getIdprov());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean buscar(Proveedor prov) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM proveedor WHERE idproveedor=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getIdprov());
            rs = ps.executeQuery();
            if (rs.next()) {
                prov.setIdprov(Integer.parseInt(rs.getString("idproveedor")));
                prov.setRazon(rs.getString("razon_social"));
                prov.setNombre(rs.getString("nombre"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setCuit(Integer.parseInt(rs.getString("cuit")));
                prov.setEmail(rs.getString("email"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
