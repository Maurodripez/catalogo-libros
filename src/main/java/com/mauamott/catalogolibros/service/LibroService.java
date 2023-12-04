package com.mauamott.catalogolibros.service;

import com.mauamott.catalogolibros.dto.LibroDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LibroService {

    String guardarLibro(LibroDTO libro) throws InterruptedException, ExecutionException;

    LibroDTO obtenerLibroPorTitulo(String titulo) throws InterruptedException, ExecutionException;

    List<LibroDTO> obtenerLibrosPorAutor(String autor) throws InterruptedException, ExecutionException;

    List<LibroDTO> obtenerLibrosPorEditorial(String editorial) throws InterruptedException, ExecutionException;

    String eliminarLibro(String id) throws InterruptedException, ExecutionException;

    String actualizarLibro(LibroDTO libro) throws InterruptedException, ExecutionException;
}
