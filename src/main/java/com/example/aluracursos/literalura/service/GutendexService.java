package com.example.aluracursos.literalura.service;

import com.example.aluracursos.literalura.model.Autor;
import com.example.aluracursos.literalura.model.Libro;
import com.example.aluracursos.literalura.repository.AutorRepository;
import com.example.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GutendexService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public void importarLibros() {
        String url = "https://gutendex.com/books";
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        if (response != null) {
            List<GutendexBook> books = response.getResults();
            for (GutendexBook book : books) {
                Autor autor = autorRepository.findByName(book.getAuthors().get(0).getName()).orElseGet(() -> {
                    Autor newAutor = new Autor();
                    newAutor.setName(book.getAuthors().get(0).getName());
                    autorRepository.save(newAutor);
                    return newAutor;
                });

                Libro libro = new Libro();
                libro.setTitulo(book.getTitle());
                libro.setAutor(autor);
                libro.setIdioma(book.getLanguage());
                libro.setNumeroDescargas(book.getDownloadCount());
                libroRepository.save(libro);
            }
        }
    }

    private static class GutendexResponse {
        private List<GutendexBook> results;

        // Getters and Setters

        public List<GutendexBook> getResults() {
            return results;
        }

        public void setResults(List<GutendexBook> results) {
            this.results = results;
        }
    }

    private static class GutendexBook {
        private String title;
        private List<GutendexAuthor> authors;
        private String language;
        private int downloadCount;

        // Getters and Setters

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<GutendexAuthor> getAuthors() {
            return authors;
        }

        public void setAuthors(List<GutendexAuthor> authors) {
            this.authors = authors;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getDownloadCount() {
            return downloadCount;
        }

        public void setDownloadCount(int downloadCount) {
            this.downloadCount = downloadCount;
        }
    }

    private static class GutendexAuthor {
        private String name;

        // Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}