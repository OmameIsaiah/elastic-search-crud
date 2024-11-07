package com.elastic.search.service.impl;

import com.elastic.search.model.UrlStores;
import com.elastic.search.repository.UrlStoreRepo;
import com.elastic.search.service.URLShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class URLShortenerServiceImpl implements URLShortenerService {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();
    private static final int SHORT_URL_LENGTH = 6;

    private final UrlStoreRepo urlStoreRepo;

    private Random random = new Random();


    public String shortenURL(String longUrl) {
        UrlStores existingMapping = urlStoreRepo.findByLongUrl(longUrl).orElse(null);
        if (existingMapping != null) {
            return existingMapping.getShortUrl();
        }

        String shortUrl;
        do {
            shortUrl = generateKey();
        } while (urlStoreRepo.findById(shortUrl).isPresent());

        urlStoreRepo.save(UrlStores.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .build());

        return shortUrl;
    }

    public UrlStores getLongURL(String shortUrl) {
        return urlStoreRepo.findByShortUrl(shortUrl).orElse(null);
    }

    private String generateKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            key.append(ALPHABET.charAt(random.nextInt(BASE)));
        }
        return key.toString();
    }
}
