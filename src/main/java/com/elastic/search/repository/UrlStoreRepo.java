package com.elastic.search.repository;

import com.elastic.search.model.UrlStores;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlStoreRepo extends ElasticsearchRepository<UrlStores, String> {
    Optional<UrlStores> findByShortUrl(String shortUrl);

    Optional<UrlStores> findByLongUrl(String longUrl);
}
