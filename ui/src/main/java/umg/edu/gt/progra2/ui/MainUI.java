package umg.edu.gt.progra2.ui;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class MainUI {
	
	
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal ventana = new VentanaPrincipal();
                    ventana.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se pudo iniciar la aplicación: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

	
}
