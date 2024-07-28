package com.url.demo.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.url.demo.controller.UrlShortenerController;
import com.url.demo.service.UrlShortenerService;

@WebMvcTest(UrlShortenerController.class)
public class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    public void setup() {
        // Setup code if necessary
    }


    @Test
    public void testGetLongUrl() throws Exception {
        String longUrl = "https://www.example.com";
        String shortUrl = "da760e77";

        when(urlShortenerService.getLongUrl(shortUrl)).thenReturn(longUrl);

        mockMvc.perform(get("/api/" + shortUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(longUrl));
    }
}
