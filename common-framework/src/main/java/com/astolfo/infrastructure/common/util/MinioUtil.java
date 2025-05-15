package com.astolfo.infrastructure.common.util;

import org.springframework.stereotype.Component;

public class MinioUtil {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @PostConstruct
    public void init() {
        try {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build()
            );
            if (!found) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build()
                );
                log.info("MinIO: 创建 Bucket -> {}", minioProperties.getBucketName());
            } else {
                log.info("MinIO: Bucket 已存在 -> {}", minioProperties.getBucketName());
            }
        } catch (Exception e) {
            log.error("MinIO: 初始化 Bucket 失败", e);
            throw new RuntimeException("MinIO Bucket 初始化失败: " + e.getMessage());
        }
    }

    /**
     * 上传文件（支持 MultipartFile）
     */
    public String uploadFile(MultipartFile file, String objectName) {
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(args);
            return objectName;
        } catch (Exception e) {
            log.error("MinIO: 文件上传失败", e);
            throw new RuntimeException("上传失败: " + e.getMessage());
        }
    }

    /**
     * 上传文件（支持 InputStream）
     */
    public String uploadFile(InputStream inputStream, long size, String contentType, String objectName) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .object(objectName)
                    .stream(inputStream, size, -1)
                    .contentType(contentType)
                    .build();

            minioClient.putObject(args);
            return objectName;
        } catch (Exception e) {
            log.error("MinIO: 文件流上传失败", e);
            throw new RuntimeException("上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取预签名访问 URL（有效期单位：分钟）
     */
    public String getPresignedUrl(String objectName, int expireMinutes) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .object(objectName)
                    .method(Method.GET)
                    .expiry(expireMinutes, TimeUnit.MINUTES)
                    .build();

            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            log.error("MinIO: 获取文件访问链接失败", e);
            throw new RuntimeException("获取访问链接失败: " + e.getMessage());
        }
    }

    /**
     * 删除对象
     */
    public void deleteFile(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            log.error("MinIO: 删除文件失败", e);
            throw new RuntimeException("删除失败: " + e.getMessage());
        }
    }

    /**
     * 获取对象（返回 InputStream）
     */
    public InputStream getObject(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            log.error("MinIO: 获取文件流失败", e);
            throw new RuntimeException("获取文件失败: " + e.getMessage());
        }
    }
}