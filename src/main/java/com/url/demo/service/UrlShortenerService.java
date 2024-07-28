package com.url.demo.service;


import com.url.demo.model.UrlMapping;
import com.url.demo.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {



    private static final String BASE_URL = "http://short.url/";

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public String shortenUrl(String longUrl) {
        String shortUrl = Integer.toHexString(longUrl.hashCode());
        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        urlMappingRepository.save(url);
        return shortUrl;
    }



    public String getLongUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

}
