package com.example.aluracursos.literalura.controller;

import com.example.aluracursos.literalura.model.Autor;
import com.example.aluracursos.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @GetMapping("/vivos")
    public List<Autor> listarAutoresVivosEnAnio(@RequestParam int anio) {
        return autorRepository.findByVivoAndAnioNacimientoBefore(true, anio);
    }
}
