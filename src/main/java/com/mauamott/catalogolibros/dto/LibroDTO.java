package com.mauamott.catalogolibros.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDTO {

    private String titulo;
    private String autor;
    private String editorial;
    private String anio_Publicacion;
}
