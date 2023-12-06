package com.mauamott.catalogolibros.dto;

import com.google.cloud.firestore.annotation.DocumentId;
import com.mauamott.catalogolibros.entities.Libro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LibroDTO {

    private String id;
    private String titulo;
    private String autor;
    private String editorial;
    private String anio_publicacion;
    private int total;
    private int prestados;
    private int disponibles;

    public Libro toLibro() {
        Libro libro = new Libro();
        libro.setId(this.getId());
        libro.setTitulo(this.getTitulo());
        libro.setAutor(this.getAutor());
        libro.setEditorial(this.getEditorial());
        libro.setAnio_publicacion(this.getAnio_publicacion());
        libro.setTotal(this.getTotal());
        libro.setPrestados(this.getPrestados());
        libro.setDisponibles(this.getDisponibles());
        return libro;
    }
}
