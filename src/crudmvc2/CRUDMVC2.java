package crudmvc2;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import controlador.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import modelo.*;
import vista.*;

public class CRUDMVC2 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new HiFiLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Producto mod = new Producto();
        Proveedor modP = new Proveedor();
        ConsultasProducto modC = new ConsultasProducto();
        ConsultaProveedor modCP = new ConsultaProveedor();
        FrmPrincipal frm = new FrmPrincipal();

        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm);
        Ctrlproveedor ctrlprov = new Ctrlproveedor(modP, modCP, frm);
        CtrlLogin.mostrar();
    }
}
