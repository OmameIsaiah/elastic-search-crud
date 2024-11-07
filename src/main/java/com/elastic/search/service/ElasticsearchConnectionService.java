package com.elastic.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchConnectionService {
    private final RestHighLevelClient restHighLevelClient;
    private final URLShortenerService urlShortenerService;

    public void checkConnection() {
        try {
            MainResponse response = restHighLevelClient.info(RequestOptions.DEFAULT);
            String clusterName = response.getClusterName().toUpperCase();
            String nodeName = response.getNodeName();
            String clusterUuid = response.getClusterUuid();
            log.info("Connected to Elasticsearch cluster {} with UUID {} on node {}", clusterName, clusterUuid, nodeName);
            shortUrl();
        } catch (Exception e) {
            log.info("Failed to connect to Elasticsearch");
            e.printStackTrace();
        }
    }

    private void shortUrl() {
        try {
            String shortenUrl = urlShortenerService.shortenURL("https://swipe.ng/web-main");
            log.info("The Short Url is:::{}", shortenUrl);
            log.info("The Long Url retrieve is:::: {}", urlShortenerService.getLongURL(shortenUrl));
        } catch (Exception e) {
            log.info("Error creating short URL:::{}", e.getMessage());
        }
    }
}
