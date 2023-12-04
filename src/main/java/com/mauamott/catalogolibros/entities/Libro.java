package com.mauamott.catalogolibros.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    private String titulo;
    private String autor;
    private String editorial;
    private String anio_Publicacion;
}
