package com.example.aluracursos.literalura.controller;

import com.example.aluracursos.literalura.model.Libro;
import com.example.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroRepository.findByTituloContaining(titulo);
    }

    @GetMapping
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/idioma")
    public List<Libro> listarPorIdioma(@RequestParam String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}
