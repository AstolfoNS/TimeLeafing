package com.astolfo.webinterface.controller;

import com.astolfo.application.service.MinioFileService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.PresignedUrl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/minio/")
@RestController
public class MinioFileController {

    @Resource
    MinioFileService minioFileService;


    @GetMapping("/presigned-url/{bucketName}/{id}/{filename}")
    public ResponseResult<PresignedUrl> presignedUrlToPut(
            @PathVariable("bucketName") String bucketName,
            @PathVariable("id") Long id,
            @PathVariable("filename") String filename
    ) {
        return minioFileService.getPresignedUrlToPut(bucketName, id, filename);
    }

}
