package com.url.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.url.demo.service.UrlShortenerService;
import com.url.demo.model.UrlMapping;
import com.url.demo.repository.UrlMappingRepository;

public class UrlShortenerServiceTest {

    @Mock
    private UrlMappingRepository urlRepository;

    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenUrl() {
        String longUrl = "https://www.example.com";
        String shortUrl = "da760e77";

        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);

        when(urlRepository.save(any(UrlMapping.class))).thenReturn(url);

        String result = urlShortenerService.shortenUrl(longUrl);

        assertThat(result).isEqualTo(shortUrl);
        verify(urlRepository, times(1)).save(any(UrlMapping.class));
    }

    @Test
    public void testGetLongUrl() {
        String longUrl = "https://www.example.com";
        String shortUrl = "da760e77";

        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(java.util.Optional.of(url));

        String result = urlShortenerService.getLongUrl(shortUrl);

        assertThat(result).isEqualTo(longUrl);
        verify(urlRepository, times(1)).findByShortUrl(shortUrl);
    }
}
