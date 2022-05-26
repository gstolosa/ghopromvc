package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultaProveedor;
import modelo.Proveedor;
import vista.FrmPrincipal;

public class Ctrlproveedor implements ActionListener {

    public Proveedor modP;
    public ConsultaProveedor modCP;
    public FrmPrincipal frmP;

    public Ctrlproveedor(Proveedor modP, ConsultaProveedor modCP, FrmPrincipal frmP) {
        this.modP = modP;
        this.modCP = modCP;
        this.frmP = frmP;
        this.frmP.btnbuscarprov.addActionListener(this);
        this.frmP.btnrliminarprov.addActionListener((ActionListener) this);
        this.frmP.btnguardarprov.addActionListener((ActionListener) this);
        this.frmP.btnmodificarprov.addActionListener((ActionListener) this);
        this.frmP.btnlimpiarprov.addActionListener((ActionListener) this);
        this.frmP.btnlistarprov.addActionListener((ActionListener) this);
        this.frmP.btnSalirP.addActionListener((ActionListener) this);
    }

    public void iniciar() {
        frmP.setTitle("GhoProIc");
        frmP.setLocationRelativeTo(null);
        frmP.setResizable(false);
        frmP.txtid.setVisible(true);
    }

    public void LlenarTabla(JTable TableProveedor) {
        DefaultTableModel ModeloProv = new DefaultTableModel();
        TableProveedor.setModel(ModeloProv);
        ModeloProv.addColumn("Código proveedor");
        ModeloProv.addColumn("Razón Social");
        ModeloProv.addColumn("Nombre");
        ModeloProv.addColumn("Dirección");
        ModeloProv.addColumn("Teléfono");
        ModeloProv.addColumn("Cuit");
        ModeloProv.addColumn("Email");

        Object[] columna = new Object[7];
        int numRegistros = modCP.obtenerProveedor().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modCP.obtenerProveedor().get(i).getIdprov();
            columna[1] = modCP.obtenerProveedor().get(i).getRazon();
            columna[2] = modCP.obtenerProveedor().get(i).getNombre();
            columna[3] = modCP.obtenerProveedor().get(i).getDireccion();
            columna[4] = modCP.obtenerProveedor().get(i).getTelefono();
            columna[5] = modCP.obtenerProveedor().get(i).getCuit();
            columna[6] = modCP.obtenerProveedor().get(i).getEmail();
            ModeloProv.addRow(columna);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmP.btnguardarprov) {
            if (frmP.txtrazon.getText().isEmpty() && frmP.txtnombreprov.getText().isEmpty() && frmP.txtdireccionprov.getText().isEmpty() && frmP.txttelefonoprov.getText().isEmpty() && frmP.txtcuitprov.getText().isEmpty() && frmP.txtemailprov.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar los campos");
            } else {
                modP.setRazon(frmP.txtrazon.getText());
                modP.setNombre((frmP.txtnombreprov.getText()));
                modP.setDireccion((frmP.txtdireccionprov.getText()));
                modP.setTelefono(frmP.txttelefonoprov.getText());
                modP.setCuit(Integer.parseInt(frmP.txtcuitprov.getText()));
                modP.setEmail(frmP.txtemailprov.getText());
                if (modCP.registrar(modP)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                    LlenarTabla(frmP.TableProveedor);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frmP.btnmodificarprov) {
            if (frmP.txtrazon.getText().isEmpty() && frmP.txtnombreprov.getText().isEmpty() && frmP.txtdireccionprov.getText().isEmpty() && frmP.txttelefonoprov.getText().isEmpty() && frmP.txtcuitprov.getText().isEmpty() && frmP.txtemailprov.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar los campos");
            } else {
                modP.setIdprov(Integer.parseInt(frmP.txtidprov.getText()));
                modP.setRazon(frmP.txtrazon.getText());
                modP.setNombre((frmP.txtnombreprov.getText()));
                modP.setDireccion((frmP.txtdireccionprov.getText()));
                modP.setTelefono((frmP.txttelefonoprov.getText()));
                modP.setCuit(Integer.parseInt(frmP.txtcuitprov.getText()));
                modP.setEmail(frmP.txtemailprov.getText());
                if (modCP.modificar(modP)) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                    LlenarTabla(frmP.TableProveedor);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frmP.btnrliminarprov) {
            if (frmP.txtidprov.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar el código del proveedor");
            } else {
                modP.setIdprov(Integer.parseInt(frmP.txtidprov.getText()));

                if (modCP.eliminar(modP)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                    limpiar();
                    LlenarTabla(frmP.TableProveedor);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frmP.btnbuscarprov) {
            if (frmP.txtidprov.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar el código del proveedor");
            } else {
                modP.setIdprov(Integer.parseInt(frmP.txtidprov.getText()));
                if (modCP.buscar(modP)) {
                    frmP.txtidprov.setText(String.valueOf(modP.getIdprov()));
                    frmP.txtrazon.setText(modP.getRazon());
                    frmP.txtnombreprov.setText(modP.getNombre());
                    frmP.txtdireccionprov.setText(modP.getDireccion());
                    frmP.txttelefonoprov.setText(modP.getTelefono());
                    frmP.txtcuitprov.setText(String.valueOf(modP.getCuit()));
                    frmP.txtemailprov.setText(modP.getEmail());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                    limpiar();
                }
            }
        }

        if (e.getSource() == frmP.btnlistarprov) {
            LlenarTabla(frmP.TableProveedor);

        }
        if (e.getSource() == frmP.btnlimpiarprov) {
            limpiar();
        }
        
        if (e.getSource() == frmP.btnSalirP) {
            System.exit(0);
        }
    }

    public void limpiar() {
        frmP.txtidprov.setText(null);
        frmP.txtrazon.setText(null);
        frmP.txtnombreprov.setText(null);
        frmP.txtdireccionprov.setText(null);
        frmP.txttelefonoprov.setText(null);
        frmP.txtcuitprov.setText(null);
        frmP.txtemailprov.setText(null);
    }
}
