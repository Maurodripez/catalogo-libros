package com.mauamott.catalogolibros.entities;

import com.google.cloud.firestore.annotation.DocumentId;
import com.mauamott.catalogolibros.dto.LibroDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Libro {

    @DocumentId
    private String id;
    private String titulo;
    private String autor;
    private String editorial;
    private String anio_publicacion;
    private int total;
    private int prestados;
    private int disponibles;

    public LibroDTO toLibroDTO() {
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setId(this.getId());
        libroDTO.setTitulo(this.getTitulo());
        libroDTO.setAutor(this.getAutor());
        libroDTO.setAutor(this.getEditorial());
        libroDTO.setAutor(this.getAnio_publicacion());
        libroDTO.setTotal(this.getTotal());
        libroDTO.setPrestados(this.getPrestados());
        libroDTO.setDisponibles(this.getDisponibles());

        return libroDTO;
    }
}
