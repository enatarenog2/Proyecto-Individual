CREATE DATABASE catalogo_libros;
USE catalogo_libros;
SELECT DATABASE();

-- la tabla de libros, se definio id como primary key y autoincrement, titulo como varchar(150) ya que 
-- no suele haber titulos tan largos, autor igual varchar, categoria como varchar para que puede escribir 
-- la categoria que guste, precio como Decimal y solo con 2 decimales ya que son solo 2 los que se usan 
-- para dinero , existencias como int pues no puede haber 1.4 libros y año de publicacion como Year 
CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    existencias INT NOT NULL,
    anio_publicacion YEAR NOT NULL
);

-- se agrego check a las columnas de precio y exitencia para 
-- las validaciones haci se validan a la hora de entrar a la base de datos, las validaciones son: 
-- 1. precio no puede ser menor que 0
-- 2. existencias tienen que ser mayor o igual a cero
ALTER TABLE libros
ADD CONSTRAINT chk_precio_positivo
CHECK (precio > 0),
ADD CONSTRAINT chk_existencias_validas
CHECK (existencias >= 0);


-- libros de prueba para ver el funcionamiento de las operaciones del DAO 
INSERT INTO libros
    (titulo, autor, categoria, precio, existencias, anio_publicacion)
VALUES
    ('Cien años de soledad', 'Gabriel García Márquez', 'Novela', 145.00, 12, 1967),
    ('Clean Code', 'Robert C. Martin', 'Tecnico', 220.50, 5, 2008),
    ('El principito', 'Antoine de Saint-Exupéry', 'Infantil', 85.00, 0, 1943);