package umg.edu.gt.progra2.ui.panels;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import umg.edu.gt.progra2.dao.LibroDAO;
import umg.edu.gt.progra2.modelo.Libro;

public class FormCreate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textCategory;
	private JTextField textPrice;
	private JTextField textStocks;
	private JTextField textYear;
	private LibroDAO libroDAO;
	private Runnable alGuardar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormCreate(Runnable alGuardar) {
		libroDAO = new LibroDAO();
		this.alGuardar = alGuardar;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Titulo:");
		lblTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTitle.setBounds(21, 36, 75, 24);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setBounds(106, 40, 428, 24);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Autor:");
		lblAuthor.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblAuthor.setBounds(21, 97, 75, 24);
		contentPane.add(lblAuthor);
		
		JLabel lblCategory = new JLabel("Categoria:");
		lblCategory.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblCategory.setBounds(21, 146, 88, 24);
		contentPane.add(lblCategory);
		
		JLabel lblStocks = new JLabel("Existencias:");
		lblStocks.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblStocks.setBounds(21, 242, 100, 24);
		contentPane.add(lblStocks);
		
		JLabel lblPrice = new JLabel("Precio:");
		lblPrice.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblPrice.setBounds(21, 196, 75, 24);
		contentPane.add(lblPrice);
		
		JLabel lblYear = new JLabel("Año de Publicacion:");
		lblYear.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblYear.setBounds(21, 289, 161, 24);
		contentPane.add(lblYear);
		
		textAuthor = new JTextField();
		textAuthor.setColumns(10);
		textAuthor.setBounds(106, 97, 428, 24);
		contentPane.add(textAuthor);
		
		textCategory = new JTextField();
		textCategory.setColumns(10);
		textCategory.setBounds(106, 146, 428, 24);
		contentPane.add(textCategory);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(106, 200, 428, 24);
		contentPane.add(textPrice);
		
		textStocks = new JTextField();
		textStocks.setColumns(10);
		textStocks.setBounds(119, 246, 415, 24);
		contentPane.add(textStocks);
		
		textYear = new JTextField();
		textYear.setColumns(10);
		textYear.setBounds(180, 293, 354, 24);
		contentPane.add(textYear);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 341, 864, 152);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSave = new JButton("Guardar");
		btnSave.setBackground(Color.WHITE);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(205, 44, 130, 41);
		panel.add(btnSave);
		btnSave.addActionListener(e -> guardarLibro());
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(418, 44, 130, 41);
		panel.add(btnCancel);
		
		JLabel lblAgregarLibro = new JLabel("Agregar Libro");
		lblAgregarLibro.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblAgregarLibro.setBounds(10, -2, 151, 31);
		contentPane.add(lblAgregarLibro);
		btnCancel.addActionListener(e -> dispose());

	}
	
	private void guardarLibro() {

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
	        LocalDate fechaIngreso = LocalDate.now();
	        
	        Libro libro = new Libro(
	        	    titulo,
	        	    autor,
	        	    categoria,
	        	    precio,
	        	    existencias,
	        	    anioPublicacion,
	        	    fechaIngreso
	        	);
	        libroDAO.crear(libro);
	        
	        alGuardar.run();

	        JOptionPane.showMessageDialog(
	            this,
	            "Libro guardado correctamente.",
	            "Éxito",
	            JOptionPane.INFORMATION_MESSAGE
	        );

	        dispose();

	        // excepciones para no romper el programa
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
