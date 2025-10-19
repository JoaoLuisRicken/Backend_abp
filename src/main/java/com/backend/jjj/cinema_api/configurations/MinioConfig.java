package com.backend.jjj.cinema_api.configurations;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${MINIO_USERNAME}")
    private String minioUsername;

    @Value("${MINIO_PASSWORD}")
    private String minioPassword;

    @Value("${MINIO_URL}")
    private String minioUrl;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioUsername, minioPassword)
                .build();
    }
}
