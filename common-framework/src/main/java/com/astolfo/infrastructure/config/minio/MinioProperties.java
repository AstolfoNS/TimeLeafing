package com.astolfo.infrastructure.config.minio;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MinioProperties {

    @Value("${custom.minio.endpoint}")
    private String endpoint;

    @Value("${custom.minio.access-key}")
    private String accessKey;

    @Value("${custom.minio.secret-key}")
    private String secretKey;

    @Value("${custom.minio.presigned-url-expiry}")
    private Integer presignedUrlExpiry;

}

