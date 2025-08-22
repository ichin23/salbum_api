package com.ichin23.salbum.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichin23.salbum.domain.DownloadRequestDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadImagesProducer {
    @Autowired private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void request(DownloadRequestDTO download) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "download-request-exchange",
                "download-request-route-key",
                objectMapper.writeValueAsString(download)
        );
    }
}
