package com.astolfo.infrastructure.config.minio;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MinioProperties {

    @Value("${custom.minio.bucket-name}")
    public String bucketName;

    @Value("${custom.minio.endpoint}")
    public String endpoint;

    @Value("${custom.minio.access-key}")
    public String accessKey;

    @Value("${custom.minio.secret-key}")
    public String secretKey;

}

