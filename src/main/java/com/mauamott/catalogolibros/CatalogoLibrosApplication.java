package com.mauamott.catalogolibros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CatalogoLibrosApplication {

	public static void main(String[] args) throws IOException {
		FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		// Check if Firebase is not already initialized
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(CatalogoLibrosApplication.class, args);
		log.info("Biblioteca inicializada correctamente");
	}
}
