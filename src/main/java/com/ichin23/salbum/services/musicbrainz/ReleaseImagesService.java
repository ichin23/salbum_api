package com.ichin23.salbum.services.musicbrainz;

import com.ichin23.salbum.services.musicbrainz.dto.releaseImage.ImagesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ReleaseImagesService {

    @Value("${release_images.api_url}")
    String apiUrl;

    private static final String UPLOAD_DIR = "uploads/";

    private final RestTemplate restTemplate;

    public ReleaseImagesService(RestTemplateBuilder restTemplateBuilder){ restTemplate = restTemplateBuilder.build(); };

    public String downloadImage(String id) throws Exception {
        String url = apiUrl+"/release/"+id;

        try{
            ResponseEntity<ImagesDTO> images = restTemplate.getForEntity(url, ImagesDTO.class);

            assert images.getBody() != null;
            if(images.getBody().images().getFirst().image()==null){
                throw new Exception("Image not found");
            }

            String fileName = id + ".jpg";
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Garante que a pasta existe
            Files.createDirectories(filePath.getParent());

            // Baixar imagem
            try (InputStream in = URI.create(images.getBody().images().getFirst().image()).toURL().openStream()) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Retorna caminho salvo
            return filePath.toString();

        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().is4xxClientError()){
                return "image not exist for id: " + id;
            }
        }


        return "error";
    }

    public String pathImage(String id){
        Path filePath = Paths.get(UPLOAD_DIR, id+".jpg");
        System.out.println(filePath.toString());
        if(filePath.toFile().exists())
            return filePath.toAbsolutePath().toString();
        return "loading";
    }

    public boolean imageExist(String id){
        Path filePath = Paths.get(UPLOAD_DIR, id+".jpg");
        return filePath.toFile().exists();
    }

    public byte[] getImage(String id) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, id+".jpg");

        return Files.readAllBytes(filePath);
    }
}
