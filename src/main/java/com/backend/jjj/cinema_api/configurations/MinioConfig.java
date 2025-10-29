package com.backend.jjj.cinema_api.configurations;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.access-key}")
    private String minioUsername;

    @Value("${minio.secret-key}")
    private String minioPassword;

    @Value("${minio.url}")
    private String minioUrl;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioUsername, minioPassword)
                .build();
    }
}
