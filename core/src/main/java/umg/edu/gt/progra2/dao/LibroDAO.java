package umg.edu.gt.progra2.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import umg.edu.gt.progra2.modelo.Libro;

public class LibroDAO {
	
	private static final String URL ="jdbc:mysql://localhost:3306/catalogo_libros?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    
    
    public Libro crear(Libro libro) {

        String sql = "INSERT INTO libros "
                + "(titulo, autor, categoria, precio, existencias, anio_publicacion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getCategoria());
            statement.setBigDecimal(4, libro.getPrecio());
            statement.setInt(5, libro.getExistencias());
            statement.setInt(6, libro.getAnioPublicacion());

            statement.executeUpdate();

            try (ResultSet claves = statement.getGeneratedKeys()) {

                if (claves.next()) {
                    libro.setId(claves.getInt(1));
                }
            }

            return libro;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el libro.", e);
        }
    }
    
    
    public List<Libro> listarTodos() {

        String sql = "SELECT id, titulo, autor, categoria, precio, "
                + "existencias, anio_publicacion "
                + "FROM libros ORDER BY id";

        List<Libro> libros = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                libros.add(mapearFila(resultado));
            }

            return libros;

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los libros.", e);
        }
    }

    
    
    public Optional<Libro> buscarPorId(int id) {

        String sql = "SELECT id, titulo, autor, categoria, precio, "
                + "existencias, anio_publicacion "
                + "FROM libros WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultado = statement.executeQuery()) {

                if (resultado.next()) {
                    return Optional.of(mapearFila(resultado));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el libro.", e);
        }
    }
    
    
    public boolean actualizar(Libro libro) {

        String sql = "UPDATE libros SET "
                + "titulo = ?, autor = ?, categoria = ?, precio = ?, "
                + "existencias = ?, anio_publicacion = ? "
                + "WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getCategoria());
            statement.setBigDecimal(4, libro.getPrecio());
            statement.setInt(5, libro.getExistencias());
            statement.setInt(6, libro.getAnioPublicacion());
            statement.setInt(7, libro.getId());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el libro.", e);
        }
    }
    
    
    
    public boolean eliminar(int id) {

        String sql = "DELETE FROM libros WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el libro.", e);
        }
    }
    
    
    
    
    private Libro mapearFila(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("id");
        String titulo = resultado.getString("titulo");
        String autor = resultado.getString("autor");
        String categoria = resultado.getString("categoria");
        BigDecimal precio = resultado.getBigDecimal("precio");
        int existencias = resultado.getInt("existencias");
        int anioPublicacion = resultado.getInt("anio_publicacion");
        
        Libro libro = new Libro(
                titulo,
                autor,
                categoria,
                precio,
                existencias,
                anioPublicacion
        );

        libro.setId(id);

        return libro;

       
    }
    
    
    
    
}
