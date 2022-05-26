package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultasProducto;
import modelo.Producto;
import vista.FrmPrincipal;

public class CtrlProducto implements ActionListener {

    public static Producto mod;
    public static ConsultasProducto modC;
    public static FrmPrincipal frm;

    public CtrlProducto(Producto mod, ConsultasProducto modC, FrmPrincipal frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnbuscar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnmodificar.addActionListener(this);
        this.frm.btnlimpiar.addActionListener(this);
        this.frm.btnlistar.addActionListener(this);
        this.frm.btnSalirPr.addActionListener(this);
    }

    public static void iniciar() {
        frm.setTitle("GhoProIc");
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);
        frm.txtid.setVisible(true);
        frm.setVisible(true);
    }

    public void LlenarTabla(JTable TableProducto) {
        DefaultTableModel ModeloPro = new DefaultTableModel();
        TableProducto.setModel(ModeloPro);
        ModeloPro.addColumn("Código producto");
        ModeloPro.addColumn("Modelo");
        ModeloPro.addColumn("Precio");
        ModeloPro.addColumn("Tipo producto");
        ModeloPro.addColumn("Cantidad");
        ModeloPro.addColumn("Marca");

        Object[] columna = new Object[6];
        int numRegistros = modC.obtenerProductos().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modC.obtenerProductos().get(i).getId();
            columna[1] = modC.obtenerProductos().get(i).getModelo();
            columna[2] = modC.obtenerProductos().get(i).getPrecio();
            columna[3] = modC.obtenerProductos().get(i).getTipo();
            columna[4] = modC.obtenerProductos().get(i).getCantidad();
            columna[5] = modC.obtenerProductos().get(i).getMarca();
            ModeloPro.addRow(columna);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnguardar) {
            if (frm.txtmodelo.getText().isEmpty() && frm.txttipo.getText().isEmpty() && frm.txtprecio.getText().isEmpty() && frm.txtcantidad.getText().isEmpty() && frm.txtmarca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete los campos");
            } else {
                mod.setModelo(frm.txtmodelo.getText());
                mod.setTipo(Integer.parseInt(frm.txttipo.getText()));
                mod.setPrecio(Double.parseDouble(frm.txtprecio.getText()));
                mod.setCantidad(Integer.parseInt(frm.txtcantidad.getText()));
                mod.setMarca(frm.txtmarca.getText());
                if (modC.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                    LlenarTabla(frm.TableProducto);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frm.btnmodificar) {
            if (frm.txtmodelo.getText().isEmpty() && frm.txttipo.getText().isEmpty() && frm.txtprecio.getText().isEmpty() && frm.txtcantidad.getText().isEmpty() && frm.txtmarca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete los campos");
            } else {
                mod.setId(Integer.parseInt(frm.txtid.getText()));
                mod.setModelo(frm.txtmodelo.getText());
                mod.setTipo(Integer.parseInt(frm.txttipo.getText()));
                mod.setPrecio(Double.parseDouble(frm.txtprecio.getText()));
                mod.setCantidad(Integer.parseInt(frm.txtcantidad.getText()));
                mod.setMarca(frm.txtmarca.getText());
                if (modC.modificar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                    LlenarTabla(frm.TableProducto);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frm.btneliminar) {
            if (frm.txtid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar el código del producto");
            } else {
                mod.setId(Integer.parseInt(frm.txtid.getText()));
                if (modC.eliminar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                    limpiar();
                    LlenarTabla(frm.TableProducto);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frm.btnbuscar) {
            if (frm.txtid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar el código producto");
            } else {
                mod.setId(Integer.parseInt(frm.txtid.getText()));
                if (modC.buscar(mod)) {
                    frm.txtid.setText(String.valueOf(mod.getId()));
                    frm.txtmodelo.setText(mod.getModelo());
                    frm.txttipo.setText(String.valueOf(mod.getTipo()));
                    frm.txtprecio.setText(String.valueOf(mod.getPrecio()));
                    frm.txtcantidad.setText(String.valueOf(mod.getCantidad()));
                    frm.txtmarca.setText(mod.getMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frm.btnlistar) {
            LlenarTabla(frm.TableProducto);
        }

        if (e.getSource() == frm.btnlimpiar) {
            limpiar();
        }
        
        if (e.getSource() == frm.btnSalirPr) {
            System.exit(0);
        }
    }

    public void limpiar() {
        frm.txtid.setText(null);
        frm.txtmodelo.setText(null);
        frm.txttipo.setText(null);
        frm.txtprecio.setText(null);
        frm.txtcantidad.setText(null);
        frm.txtmarca.setText(null);
    }
}
