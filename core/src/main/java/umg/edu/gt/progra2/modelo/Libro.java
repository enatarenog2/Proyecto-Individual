package umg.edu.gt.progra2.modelo;

import java.math.BigDecimal;

public class Libro {
	
	 private int id;
	    private String titulo;
	    private String autor;
	    private String categoria;
	    private BigDecimal precio;
	    private int existencias;
	    private int anioPublicacion;
	    
	    
	    public Libro() {
	    }
	    
	    
		public Libro(String titulo, String autor, String categoria, BigDecimal precio, int existencias,
				int anioPublicacion) {
			
		    setTitulo(titulo);
		    setAutor(autor);
		    setCategoria(categoria);
		    setPrecio(precio);
		    setExistencias(existencias);
		    setAnioPublicacion(anioPublicacion);
		}
	    
		public int getId() {
		    return id;
		}

		public void setId(int id) {
		    this.id = id;
		}
		
		
		public String getTitulo() {
		    return titulo;
		}

		public void setTitulo(String titulo) {
		    if (titulo == null || titulo.trim().isEmpty()) {
		        throw new IllegalArgumentException("El título no puede estar vacío.");
		    }

		    this.titulo = titulo;
		}
		
		public String getAutor() {
		    return autor;
		}

		public void setAutor(String autor) {
		    if (autor == null || autor.trim().isEmpty()) {
		        throw new IllegalArgumentException("El autor no puede estar vacío.");
		    }

		    this.autor = autor;
		}
		
		
		public String getCategoria() {
		    return categoria;
		}

		public void setCategoria(String categoria) {
		    this.categoria = categoria;
		}
	    
		public BigDecimal getPrecio() {
		    return precio;
		}

		public void setPrecio(BigDecimal precio) {
		    if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
		        throw new IllegalArgumentException("El precio debe ser mayor que 0.");
		    }

		    this.precio = precio;
		}
	    
		
		public int getExistencias() {
		    return existencias;
		}

		public void setExistencias(int existencias) {
		    if (existencias < 0) {
		        throw new IllegalArgumentException("Las existencias no pueden ser negativas.");
		    }

		    this.existencias = existencias;
		}
	    
		public int getAnioPublicacion() {
		    return anioPublicacion;
		}

		public void setAnioPublicacion(int anioPublicacion) {
		    int anioActual = java.time.Year.now().getValue();

		    if (anioPublicacion > anioActual) {
		        throw new IllegalArgumentException(
		            "El año de publicación no puede ser mayor al año actual."
		        );
		    }

		    this.anioPublicacion = anioPublicacion;
		}
		
		@Override
		public String toString() {
		    return "Libro{" +
		            "id=" + id +
		            ", titulo='" + titulo + '\'' +
		            ", autor='" + autor + '\'' +
		            ", categoria='" + categoria + '\'' +
		            ", precio=" + precio +
		            ", existencias=" + existencias +
		            ", anioPublicacion=" + anioPublicacion +
		            '}';
		}
		
		
}
