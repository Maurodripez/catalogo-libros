package com.mauamott.catalogolibros.controller;

import com.mauamott.catalogolibros.dto.LibroDTO;
import com.mauamott.catalogolibros.service.LibroService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class BibliotecaController {

    private final LibroService libroService;

    BibliotecaController(LibroService libroService){
        this.libroService = libroService;
    }

    @MutationMapping
    String guardarLibro(@Argument LibroDTO libroDTO) throws ExecutionException, InterruptedException {
        return libroService.guardarLibro(libroDTO);
    }

    @QueryMapping
    List<LibroDTO> listarLibros() throws ExecutionException, InterruptedException {
        return libroService.listarLibros();
    }

    @QueryMapping
    List<LibroDTO> obtenerLibrosPorTitulo(@Argument String titulo) throws ExecutionException, InterruptedException {
        return libroService.obtenerLibrosPorTitulo(titulo);
    }

    @QueryMapping
    List<LibroDTO> obtenerLibrosPorEditorial(@Argument String editorial) throws ExecutionException, InterruptedException {
        return libroService.obtenerLibrosPorEditorial(editorial);
    }

    @QueryMapping
    List<LibroDTO> obtenerLibrosPorAutor(@Argument String autor) throws ExecutionException, InterruptedException {
        return libroService.obtenerLibrosPorAutor(autor);
    }

    @MutationMapping
    String eliminarLibro(@Argument String id) throws ExecutionException, InterruptedException {
        return libroService.eliminarLibro(id);
    }

    @MutationMapping
    String actualizarLibro(@Argument LibroDTO libroDTO) throws ExecutionException, InterruptedException {
        return libroService.actualizarLibro(libroDTO);
    }
}
