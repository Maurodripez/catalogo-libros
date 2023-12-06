package com.mauamott.catalogolibros.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mauamott.catalogolibros.dto.LibroDTO;
import com.mauamott.catalogolibros.entities.Libro;
import com.mauamott.catalogolibros.service.LibroService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LibroServiceImpl implements LibroService {

    @Value("${collection.name}")
    private String collectionName;
    Firestore dbFirestore = FirestoreClient.getFirestore();

    @Override
    public String guardarLibro(LibroDTO libroDTO) throws InterruptedException, ExecutionException {

        Libro libro = libroDTO.toLibro();
        ApiFuture<WriteResult> collect = dbFirestore.collection(collectionName).document().set(libro);
        return collect.get().getUpdateTime().toString();
    }

    @Override
    public List<LibroDTO> obtenerLibrosPorTitulo(String titulo) throws InterruptedException, ExecutionException {
        return obtencionGenerica("titulo", titulo);
    }


    @Override
    public List<LibroDTO> obtenerLibrosPorAutor(String autor) throws InterruptedException, ExecutionException {
        System.out.println("Autor : "+ autor);
        return obtencionGenerica("autor", autor);
    }

    @Override
    public List<LibroDTO> obtenerLibrosPorEditorial(String editorial) throws InterruptedException, ExecutionException {
        System.out.println("Editorial : "+ editorial);
        return obtencionGenerica("editorial", editorial);
    }

    @Override
    public String eliminarLibro(String id) throws InterruptedException, ExecutionException {
        DocumentReference documentRef = dbFirestore.collection(collectionName).document(id);

        ApiFuture<DocumentSnapshot> future = documentRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            documentRef.delete();
            return "El registro con el id: " + id + " ha sido eliminado";
        } else {
            return "No se encontró un registro con el id: " + id + ". No se realizó ninguna eliminación.";
        }
    }

    @Override
    public String actualizarLibro(LibroDTO libroDTO) throws InterruptedException, ExecutionException {
        Libro libro = libroDTO.toLibro();
        DocumentReference documentRef = dbFirestore.collection(collectionName).document(libro.getId());

        ApiFuture<DocumentSnapshot> future = documentRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            ApiFuture<WriteResult> writeResult = documentRef.set(libro);
            return "Registro actualizado. Última modificación: " + writeResult.get().getUpdateTime();
        } else {
            return "No se encontró un registro con el id: " + libro.getId()
                    + ". No se realizó ninguna actualización.";
        }
    }

    @Override
    public List<LibroDTO> listarLibros() throws InterruptedException, ExecutionException {
        CollectionReference collectionReference = dbFirestore.collection(collectionName);
        ApiFuture<QuerySnapshot> querySnapshot = collectionReference.get();

        List<LibroDTO> libros = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Libro libro = document.toObject(Libro.class);
            libros.add(libro.toLibroDTO());
        }
        return libros;
    }

    public List<LibroDTO> obtencionGenerica(String referencia, String busqueda) throws ExecutionException, InterruptedException {
        // Obtén una referencia a la colección
        CollectionReference collectionReference = dbFirestore.collection(collectionName);

        // Realiza la consulta para obtener los documentos con el título dado
        Query query = collectionReference.whereEqualTo(referencia, busqueda);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        // Lista para almacenar los resultados
        List<LibroDTO> libros = new ArrayList<>();

        // Itera sobre los documentos que coinciden con la consulta
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            // Convierte cada documento a un objeto LibroDTO y agrégalo a la lista
            LibroDTO libroDTO = document.toObject(LibroDTO.class);
            libros.add(libroDTO);
        }

        return libros;
    }

}
