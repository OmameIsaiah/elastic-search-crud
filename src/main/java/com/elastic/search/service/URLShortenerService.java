package com.elastic.search.service;


import com.elastic.search.model.UrlStores;

import java.io.IOException;

public interface URLShortenerService {

    String shortenURL(String longUrl) throws IOException;

    UrlStores getLongURL(String shortUrl) throws IOException;
}
