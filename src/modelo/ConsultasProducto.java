package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasProducto extends Conexion {

    public boolean registrar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO producto ( modelo,  precio_unitario,idtipo_producto, cantidad,marca) VALUES(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getModelo());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getTipo());
            ps.setInt(4, pro.getCantidad());
            ps.setString(5, pro.getMarca());
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

    public ArrayList<Producto> obtenerProductos() {
        ArrayList obtenerProductos = new ArrayList<>();
        Producto producto;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM producto order by idproducto";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(Integer.parseInt(rs.getString(1)));
                producto.setModelo(rs.getString(2));
                producto.setPrecio(Double.parseDouble(rs.getString(3)));
                producto.setTipo(Integer.parseInt(rs.getString(4)));
                producto.setCantidad(Integer.parseInt(rs.getString(5)));
                producto.setMarca(rs.getString(6));
                obtenerProductos.add(producto);
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
        return obtenerProductos;
    }

    public boolean modificar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE producto SET modelo=?, precio_unitario=?,idtipo_producto=?, cantidad=?, marca=?  WHERE idproducto=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getModelo());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getTipo());
            ps.setInt(4, pro.getCantidad());
            ps.setString(5, pro.getMarca());
            ps.setInt(6, pro.getId());
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

    public boolean eliminar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM producto  WHERE idproducto=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
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

    public boolean buscar(Producto pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM producto  WHERE idproducto=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(Integer.parseInt(rs.getString("idproducto")));
                pro.setModelo(rs.getString("modelo"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio_unitario")));
                pro.setTipo(Integer.parseInt(rs.getString("idtipo_producto")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                pro.setMarca(rs.getString("marca"));
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
