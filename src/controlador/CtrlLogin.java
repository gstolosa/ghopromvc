package controlador;

import crudmvc2.CRUDMVC2;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import modelo.*;
import vista.*;

public class CtrlLogin {

    public static FrmLogin frml = new FrmLogin();

    public static void mostrar() {
        frml.setVisible(true);
        frml.setTitle("GhoPro.Inc");
        frml.setResizable(false);
        frml.setLocationRelativeTo(null);
    }

    public static void ocultar() {
        frml.setVisible(false);
    }

    public static void validar() throws ClassNotFoundException {
        String nombre = frml.txtUser.getText();
        String password = frml.txtPass.getText();
        ConsultasUsuario cu = new ConsultasUsuario();
        Usuario usu = new Usuario();

        if (frml.txtUser.getText().equals("") || frml.txtPass.getText().equals("")) {
            JOptionPane.showMessageDialog(frml, "Debe completar todos los campos");
            frml.txtUser.requestFocus();
        } else {
            usu = cu.ValidarUsuario(nombre, password);
            if (usu.getNombre() != null && usu.getPassword() != null) {
                ocultar();
                CtrlProducto.iniciar();
            } else {
                JOptionPane.showMessageDialog(frml, "Debe ingresar credenciales válidas");
                frml.txtUser.requestFocus();
            }
        }
    }
}
