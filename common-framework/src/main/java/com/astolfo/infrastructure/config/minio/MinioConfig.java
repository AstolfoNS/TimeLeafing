package com.astolfo.infrastructure.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("#{minioProperties.endpoint}")
    private String endpoint;

    @Value("#{minioProperties.accessKey}")
    private String accessKey;

    @Value("#{minioProperties.secretKey}")
    private String secretKey;


    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}
