package com.example.aluracursos.literalura.repository;

import com.example.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByVivoAndAnioNacimientoBefore(boolean vivo, int anio);

    Optional<Autor> findByName(String name);
}
