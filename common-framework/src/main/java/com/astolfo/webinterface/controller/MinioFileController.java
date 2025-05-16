package com.astolfo.webinterface.controller;

import com.astolfo.application.service.MinioFileService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.PresignedUrl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/minio/")
@RestController
public class MinioFileController {

    @Resource
    MinioFileService minioFileService;


    @GetMapping("/presigned-url/put")
    public ResponseResult<PresignedUrl> presignedUrlToPut(@RequestParam("bucketName") String bucketName, @RequestParam("objectName") String objectName) {
        return minioFileService.getPresignedUrlToPut(bucketName, objectName);
    }

    @GetMapping("/presigned-url/get")
    public ResponseResult<PresignedUrl> presignedUrlToGet(@RequestParam("bucketName") String bucketName, @RequestParam("objectName") String objectName) {
        return minioFileService.getPresignedUrlToGet(bucketName, objectName);
    }

    @DeleteMapping("/{bucketName}/{objectName}")
    public ResponseResult<Void> deleteFile(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
        return minioFileService.deleteFile(bucketName, objectName);
    }

}
