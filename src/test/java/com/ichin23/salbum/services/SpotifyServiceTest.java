package com.ichin23.salbum.services;

import com.ichin23.salbum.services.spotify.SpotifyService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SpotifyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    SpotifyService spotifyService;

    @BeforeEach
    void setUp() {
        // Injeta o valor da variável de ambiente simulada
        ReflectionTestUtils.setField(spotifyService, "api_url", "http://mock-api.com");
        ReflectionTestUtils.setField(spotifyService, "client_secret", "client-secret");
        ReflectionTestUtils.setField(spotifyService, "client_id", "client-id");
    }

    @Test
    public void searchArtist(){
        var result = spotifyService.searchArtist("Billie Eilish");

        assert (result.getArtists().getItems().size()>1);
    }
}
