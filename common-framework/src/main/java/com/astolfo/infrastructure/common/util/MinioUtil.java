package com.astolfo.infrastructure.common.util;

import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    @Value("#{minioProperties.bucketName}")
    private String bucketName;


    public String uploadFile(MultipartFile file, String objectName) {
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectArgs args = PutObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(args);

            return objectName;
        } catch (Exception exception) {
            log.error("MinIO: 文件上传失败", exception);

            throw new RuntimeException("上传失败: " + exception.getMessage());
        }
    }

    public String uploadFile(
            InputStream inputStream,
            long size,
            String contentType,
            String objectName
    ) {
        try {
            PutObjectArgs args = PutObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, size, -1)
                    .contentType(contentType)
                    .build();

            minioClient.putObject(args);

            return objectName;
        } catch (Exception exception) {
            log.error("MinIO: 文件流上传失败", exception);

            throw new RuntimeException("上传失败: " + exception.getMessage());
        }
    }

    public String getPreSignedUrl(String objectName, int expireMinutes) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .method(Method.GET)
                    .expiry(expireMinutes, TimeUnit.MINUTES)
                    .build();

            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception exception) {
            log.error("MinIO: 获取文件访问链接失败", exception);

            throw new RuntimeException("获取访问链接失败: " + exception.getMessage());
        }
    }

    public void deleteFile(String objectName) {
        try {
            RemoveObjectArgs args = RemoveObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            minioClient.removeObject(args);
        } catch (Exception exception) {
            log.error("MinIO: 删除文件失败", exception);

            throw new RuntimeException("删除失败: " + exception.getMessage());
        }
    }

    public InputStream getObject(String objectName) {
        try {
            GetObjectArgs args = GetObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            return minioClient.getObject(args);
        } catch (Exception exception) {
            log.error("MinIO: 获取文件流失败", exception);

            throw new RuntimeException("获取文件失败: " + exception.getMessage());
        }
    }
}