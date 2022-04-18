package pl.akai.bookcrossing.ebook.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.akai.bookcrossing.ebook.database.EbookRepository;

import java.io.FileInputStream;
import java.util.Objects;

@Configuration
public class GoogleDriveConfig {

    @Bean
    public GoogleDriveService googleDriveService(
            @Value("${spring.application.name}") String applicationName,
            @Value("${spring.application.google-drive.source}") String dataSource,
            @Value("${spring.application.google-drive.credentials}") String pathToCredentials,
            EbookRepository ebookRepository) {
        Drive drive = null;
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        try (var initialFile = new FileInputStream(Objects.requireNonNull(getClass().getResource(pathToCredentials)).getFile())) {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredentials credentials = GoogleCredentials.fromStream(initialFile).createScoped(DriveScopes.DRIVE);
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
            drive = new Drive.Builder(httpTransport, jsonFactory, requestInitializer)
                    .setApplicationName(applicationName)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return new GoogleDriveService(drive, dataSource, ebookRepository);
    }
}
