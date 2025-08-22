package com.ichin23.salbum.consumers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichin23.salbum.domain.DownloadRequestDTO;
import com.ichin23.salbum.services.musicbrainz.ReleaseImagesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DownloadImagesConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ReleaseImagesService releaseImagesService;

    @RabbitListener(queues = {"download-request-queue"})
    public void downloadImages(@Payload Message message){
        try {
            DownloadRequestDTO request = objectMapper.readValue(message.getPayload().toString(), DownloadRequestDTO.class);

            releaseImagesService.downloadImage(request.id());

            System.out.println("Imagem baixada");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
