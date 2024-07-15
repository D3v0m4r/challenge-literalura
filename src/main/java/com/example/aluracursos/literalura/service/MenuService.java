package com.example.aluracursos.literalura.service;

import com.example.aluracursos.literalura.model.Autor;
import com.example.aluracursos.literalura.model.Libro;
import com.example.aluracursos.literalura.repository.AutorRepository;
import com.example.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("Menú:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir de la aplicación");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    List<Libro> libros = libroRepository.findByTituloContaining(titulo);
                    if (libros.isEmpty()) {
                        System.out.println("Libro no encontrado");
                    } else {
                        libros.forEach(libro -> System.out.println("Titulo: " + libro.getTitulo() + ", Autor: " + libro.getAutor().getNombre() + ", Idioma: " + libro.getIdioma() + ", Número de descargas: " + libro.getNumeroDescargas()));
                    }
                    break;
                case 2:
                    libroRepository.findAll().forEach(libro -> System.out.println("Titulo: " + libro.getTitulo() + ", Autor: " + libro.getAutor().getNombre() + ", Idioma: " + libro.getIdioma() + ", Número de descargas: " + libro.getNumeroDescargas()));
                    break;
                case 3:
                    autorRepository.findAll().forEach(autor -> System.out.println("Nombre: " + autor.getNombre() + ", Vivo: " + autor.isVivo() + ", Año de Nacimiento: " + autor.getAnioNacimiento()));
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int anio = scanner.nextInt();
                    autorRepository.findByVivoAndAnioNacimientoBefore(true, anio).forEach(autor -> System.out.println("Nombre: " + autor.getNombre() + ", Vivo: " + autor.isVivo() + ", Año de Nacimiento: " + autor.getAnioNacimiento()));
                    break;
                case 5:
                    System.out.print("Ingrese el idioma (es, en, fr, pt): ");
                    String idioma = scanner.nextLine();
                    libroRepository.findByIdioma(idioma).forEach(libro -> System.out.println("Titulo: " + libro.getTitulo() + ", Autor: " + libro.getAutor().getNombre() + ", Idioma: " + libro.getIdioma() + ", Número de descargas: " + libro.getNumeroDescargas()));
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
        scanner.close();
    }
}
