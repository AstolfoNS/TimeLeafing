package com.astolfo.application.service;


import org.springframework.web.multipart.MultipartFile;

public interface MinioFileService {

    String uploadFile(MultipartFile file, Long userId);

    String getFileUrl(String objectName, int expireSeconds);

    void deleteFile(String objectName, int expireSeconds);

    boolean existsFile(String objectName);

}
