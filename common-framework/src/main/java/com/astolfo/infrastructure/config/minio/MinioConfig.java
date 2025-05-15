package com.astolfo.infrastructure.config.minio;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Resource
    private MinioProperties minioProperties;


    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

}
