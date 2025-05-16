package com.astolfo.infrastructure.common.util.component;

import com.astolfo.webinterface.vo.PresignedUrl;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    @Value("#{minioProperties.presignedUrlExpiry}")
    private Integer presignedUrlExpiry;


    public Bucket openBucket(String bucketName) {
        if (bucketExists(bucketName)) {
            return new Bucket(minioClient, presignedUrlExpiry, bucketName);
        } else {
            log.error("存储桶不存在");

            throw new RuntimeException("存储桶不存在" + bucketName);
        }
    }

    public boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception exception) {
             log.error("无法确认存储桶是否存在：{}", bucketName, exception);

            throw new RuntimeException("无法确认存储同是否存在：" + bucketName, exception);
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Bucket {

        private MinioClient minioClient;

        private Integer presignedUrlExpiry;

        private String bucketName;


        public String generatePresignedPutUrl(String objectName, Integer expiry) {
            try {
                GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs
                        .builder()
                        .method(Method.PUT)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(expiry, TimeUnit.SECONDS)
                        .build();

                return minioClient.getPresignedObjectUrl(args);
            } catch (Exception exception) {
                log.error("获取PUT预签名url失败：{}", objectName, exception);

                throw new RuntimeException("获取PUT预签名url失败：" + objectName, exception);
            }
        }

        public String generatePresignedPutUrl(String objectName) {
            return generatePresignedPutUrl(objectName, presignedUrlExpiry);
        }

        public String generatePresignedGetUrl(String objectName, Integer expiry) {
            try {
                GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(expiry, TimeUnit.SECONDS)
                        .build();

                return minioClient.getPresignedObjectUrl(args);
            } catch (Exception exception) {
                log.error("获取GET预签名URL失败：{}", objectName, exception);

                throw new RuntimeException("获取GET预签名URL失败：" + objectName, exception);
            }
        }

        public String generatePresignedGetUrl(String objectName) {
            return generatePresignedGetUrl(objectName, presignedUrlExpiry);
        }

        public void uploadFile(
                String objectName,
                InputStream inputStream,
                String contentType
        ) {
            try {
                PutObjectArgs args = PutObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType(contentType)
                        .build();

                minioClient.putObject(args);
            } catch (Exception exception) {
                log.error("上传文件失败：{}", objectName, exception);

                throw new RuntimeException("上传文件失败：" + objectName, exception);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException exception) {
                        log.error("InputStream关闭失败：{}", objectName, exception);
                    }
                }
            }
        }

        public void uploadFile(
                String objectName,
                InputStream inputStream,
                long size,
                String contentType
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
            } catch (Exception exception) {
                log.error("上传文件失败：{}", objectName, exception);

                throw new RuntimeException("上传文件失败：" + objectName, exception);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException exception) {
                        log.error("InputStream关闭失败: {}", objectName, exception);
                    }
                }
            }
        }

        public InputStream downloadFile(String objectName) {
            try {
                GetObjectArgs args = GetObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build();

                return minioClient.getObject(args);
            } catch (Exception exception) {
                log.error("下载文件失败：{}", objectName, exception);

                throw new RuntimeException("下载文件失败：" + objectName, exception);
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
                log.error("删除文件失败：{}", objectName, exception);

                throw new RuntimeException("删除文件失败：" + objectName, exception);
            }
        }
    }

}