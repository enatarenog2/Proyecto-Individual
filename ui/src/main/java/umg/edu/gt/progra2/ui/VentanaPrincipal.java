package umg.edu.gt.progra2.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.List;
import umg.edu.gt.progra2.dao.LibroDAO;
import umg.edu.gt.progra2.modelo.Libro;
import umg.edu.gt.progra2.ui.panels.FormCreate;
import umg.edu.gt.progra2.ui.panels.FormEdit;


public class VentanaPrincipal extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private LibroDAO libroDAO;
	
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		libroDAO = new LibroDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 532);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelCatalogoLibrosName = new JPanel();
		panelCatalogoLibrosName.setBounds(0, 0, 864, 58);
		panelCatalogoLibrosName.setBackground(Color.BLUE);
		contentPane.add(panelCatalogoLibrosName);
		panelCatalogoLibrosName.setLayout(null);
		
		JLabel lblCatalogoLibrosName = new JLabel("Catalogo Libros");
		lblCatalogoLibrosName.setBounds(10, 11, 223, 35);
		panelCatalogoLibrosName.add(lblCatalogoLibrosName);
		lblCatalogoLibrosName.setForeground(Color.WHITE);
		lblCatalogoLibrosName.setFont(new Font("Arial Black", Font.PLAIN, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 98, 864, 247);
		contentPane.add(scrollPane);

		table = new JTable();

		table.setModel(new DefaultTableModel(
		    new Object[][] {
		    },
		    new String[] {
		        "ID", "Titulo", "Autor", "Categoria", "Precio", "Existencias", "Fecha Ingreso"
		    }
		));

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		cargarLibros();
		
		JButton btnNewbook = new JButton("Agregar Libro");
		btnNewbook.setForeground(Color.WHITE);
		btnNewbook.setBounds(93, 374, 129, 40);
		btnNewbook.setBackground(Color.BLUE);
		btnNewbook.setFont(new Font("Arial Black", Font.PLAIN, 12));
		contentPane.add(btnNewbook);
		btnNewbook.addActionListener(e -> {
		    FormCreate formulario = new FormCreate(() -> cargarLibros());
		    formulario.setLocationRelativeTo(this);
		    formulario.setVisible(true);
		});
		
		JButton btnEditBook = new JButton("Editar Libro");
		btnEditBook.setForeground(Color.WHITE);
		btnEditBook.setBounds(356, 374, 129, 40);
		btnEditBook.setBackground(Color.BLUE);
		btnEditBook.setFont(new Font("Arial Black", Font.PLAIN, 12));
		contentPane.add(btnEditBook);
		btnEditBook.addActionListener(e -> {
		    int filaSeleccionada = table.getSelectedRow();

		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Selecciona un libro para editar.",
		            "Aviso",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    int id = (int) table.getValueAt(filaSeleccionada, 0);

		    FormEdit formulario = new FormEdit(id, () -> cargarLibros());
		    formulario.setLocationRelativeTo(this);
		    formulario.setVisible(true);
		});
		
		
		
		JButton btnDeleteBook = new JButton("Eliminar Libro");
		btnDeleteBook.setForeground(Color.WHITE);
		btnDeleteBook.setBounds(627, 374, 129, 40);
		btnDeleteBook.setBackground(Color.BLUE);
		btnDeleteBook.setFont(new Font("Arial Black", Font.PLAIN, 12));
		contentPane.add(btnDeleteBook);
		
		btnDeleteBook.addActionListener(e -> {
			
		    int filaSeleccionada = table.getSelectedRow();

		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Selecciona un libro para eliminar.",
		                "Aviso",
		                JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    int id = (int) table.getValueAt(filaSeleccionada, 0);

		    int respuesta = JOptionPane.showConfirmDialog(
		            this,
		            "¿Estás seguro de eliminar este libro?",
		            "Confirmar eliminación",
		            JOptionPane.YES_NO_OPTION
		    );

		    if (respuesta == JOptionPane.YES_OPTION) {
		        try {
		            libroDAO.eliminar(id);
		            cargarLibros();

		        } catch (RuntimeException ex) {
		            JOptionPane.showMessageDialog(
		                this,
		                "Error con la base de datos: " + ex.getMessage(),
		                "Error",
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    }
		});

		
	}
		
		
		
	
	private void cargarLibros() {
	    try {
	        List<Libro> libros = libroDAO.listarTodos();

	        DefaultTableModel modelo = (DefaultTableModel) table.getModel();

	        modelo.setRowCount(0);

	        for (Libro libro : libros) {
	            modelo.addRow(new Object[] {
	                libro.getId(),
	                libro.getTitulo(),
	                libro.getAutor(),
	                libro.getCategoria(),
	                libro.getPrecio(),
	                libro.getExistencias(),
	                libro.getFechaIngreso()
	            });
	        }

	    } catch (RuntimeException e) {
	        JOptionPane.showMessageDialog(
	            this,
	            "Error al cargar los libros: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	
	
}
