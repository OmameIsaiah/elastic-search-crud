package com.elastic.search.config;

import com.elastic.search.service.ElasticsearchConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticsearchStartupRunner implements CommandLineRunner {

    private final ElasticsearchConnectionService connectionService;

    @Override
    public void run(String... args) {
        connectionService.checkConnection();
    }
}
