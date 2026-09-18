# Catálogo de Libros

Proyecto Individual de Programación 2 

El sistema permite registrar, consultar, modificar y eliminar libros utilizando una interfaz gráfica desarrollada con Java Swing y una base de datos MySQL (CRUD).

## Tecnologías utilizadas

- Java 11
- Maven
- MySQL
- Java Swing
- Eclipse WindowBuilder

## Funcionalidades

El sistema permite:

- Listar todos los libros.
- Agregar nuevos libros.
- Editar libros existentes.
- Eliminar libros.
- Confirmar antes de eliminar un libro.
- Validar los datos ingresados.
- Guardar la información de forma permanente en MySQL.

Los datos de cada libro son:

- ID
- Título
- Autor
- Categoría
- Precio
- Existencias
- Año de publicación

## Estructura del proyecto

El proyecto utiliza Maven Multi-Módulo y está dividido en un proyecto principal y dos módulos el modulo ui depende del modulo core 
