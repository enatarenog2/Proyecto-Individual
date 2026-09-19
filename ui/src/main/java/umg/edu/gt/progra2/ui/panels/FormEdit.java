package umg.edu.gt.progra2.ui.panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.math.BigDecimal;
import umg.edu.gt.progra2.dao.LibroDAO;
import umg.edu.gt.progra2.modelo.Libro;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class FormEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textCategory;
	private JTextField textPrice;
	private JTextField textStocks;
	private JTextField textYear;
	private LibroDAO libroDAO;
	private Libro libro;
	private Runnable alGuardar;
	private JPanel panel;
	private JButton btnSave;
	private JButton btnCancel;
	private JLabel lblfechaIngreso;
	private JTextField textfechaIngreso;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public FormEdit(int idLibro, Runnable alGuardar) {
		
		libroDAO = new LibroDAO();
		libro = libroDAO.buscarPorId(idLibro).orElse(null);
		this.alGuardar = alGuardar;

		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Titulo: ");
		lblTitle.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTitle.setBounds(27, 51, 85, 29);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textTitle.setBounds(122, 47, 598, 36);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Autor: ");
		lblAuthor.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblAuthor.setBounds(27, 102, 85, 29);
		contentPane.add(lblAuthor);
		
		textAuthor = new JTextField();
		textAuthor.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textAuthor.setColumns(10);
		textAuthor.setBounds(122, 94, 598, 36);
		contentPane.add(textAuthor);
		
		JLabel lblCategory = new JLabel("Categoria: ");
		lblCategory.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblCategory.setBounds(27, 155, 85, 29);
		contentPane.add(lblCategory);
		
		textCategory = new JTextField();
		textCategory.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textCategory.setColumns(10);
		textCategory.setBounds(122, 151, 598, 36);
		contentPane.add(textCategory);
		
		JLabel lblPrice = new JLabel("Precio: ");
		lblPrice.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblPrice.setBounds(27, 203, 85, 29);
		contentPane.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textPrice.setColumns(10);
		textPrice.setBounds(122, 199, 598, 36);
		contentPane.add(textPrice);
		
		JLabel lblStocks = new JLabel("Existencias: ");
		lblStocks.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblStocks.setBounds(27, 263, 85, 29);
		contentPane.add(lblStocks);
		
		JLabel lblYear = new JLabel("Año de Publicacion: ");
		lblYear.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblYear.setBounds(27, 303, 134, 29);
		contentPane.add(lblYear);
		
		textStocks = new JTextField();
		textStocks.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textStocks.setColumns(10);
		textStocks.setBounds(122, 246, 598, 36);
		contentPane.add(textStocks);
		
		textYear = new JTextField();
		textYear.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textYear.setColumns(10);
		textYear.setBounds(171, 293, 549, 36);
		contentPane.add(textYear);
		
		JLabel lblEditarLibro = new JLabel("Editar Libro");
		lblEditarLibro.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblEditarLibro.setBounds(20, 11, 180, 43);
		contentPane.add(lblEditarLibro);
		
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(0, 388, 864, 105);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSave = new JButton("Guardar");
		btnSave.setBackground(Color.WHITE);
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(164, 30, 180, 45);
		panel.add(btnSave);
		btnSave.addActionListener(e -> actualizarLibro());
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(438, 30, 180, 45);
		panel.add(btnCancel);
		
		lblfechaIngreso = new JLabel("Fecha de Ingreso:");
		lblfechaIngreso.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblfechaIngreso.setBounds(27, 343, 134, 29);
		contentPane.add(lblfechaIngreso);
		
		textfechaIngreso = new JTextField();
		textfechaIngreso.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textfechaIngreso.setColumns(10);
		textfechaIngreso.setBounds(171, 336, 549, 36);
		contentPane.add(textfechaIngreso);
		btnCancel.addActionListener(e -> dispose());
		
		
		
		if (libro != null) {
		    textTitle.setText(libro.getTitulo());
		    textAuthor.setText(libro.getAutor());
		    textCategory.setText(libro.getCategoria());
		    textPrice.setText(libro.getPrecio().toString());
		    textStocks.setText(String.valueOf(libro.getExistencias()));
		    textYear.setText(String.valueOf(libro.getAnioPublicacion()));
		}

	}
	
	
	private void actualizarLibro() {

	    String titulo = textTitle.getText().trim();
	    String autor = textAuthor.getText().trim();
	    String categoria = textCategory.getText().trim();
	    String precioTexto = textPrice.getText().trim();
	    String existenciasTexto = textStocks.getText().trim();
	    String anioTexto = textYear.getText().trim();

	    try {
	        BigDecimal precio = new BigDecimal(precioTexto);
	        int existencias = Integer.parseInt(existenciasTexto);
	        int anioPublicacion = Integer.parseInt(anioTexto);

	        libro.setTitulo(titulo);
	        libro.setAutor(autor);
	        libro.setCategoria(categoria);
	        libro.setPrecio(precio);
	        libro.setExistencias(existencias);
	        libro.setAnioPublicacion(anioPublicacion);

	        libroDAO.actualizar(libro);
	        alGuardar.run();

	        JOptionPane.showMessageDialog(
	            this,
	            "Libro actualizado correctamente.",
	            "Éxito",
	            JOptionPane.INFORMATION_MESSAGE
	        );

	        dispose();

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(
	            this,
	            "Precio, existencias y año deben contener valores válidos.",
	            "Datos inválidos",
	            JOptionPane.ERROR_MESSAGE
	        );
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(
	            this,
	            e.getMessage(),
	            "Datos inválidos",
	            JOptionPane.ERROR_MESSAGE
	        );
	    } catch (RuntimeException e) {
	        JOptionPane.showMessageDialog(
	            this,
	            "Error con la base de datos: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	    
	}

}
