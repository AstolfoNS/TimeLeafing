package com.astolfo.application.service.impl;

import com.astolfo.application.service.MinioFileService;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.common.util.component.MinioUtil;
import com.astolfo.webinterface.vo.PresignedUrl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MinioFileServiceImpl implements MinioFileService {

    @Resource
    MinioUtil minioUtil;


    @Override
    public ResponseResult<PresignedUrl> getPresignedUrlToPut(String bucketName, String objectName) {
        try {
            return ResponseResult.okResult(new PresignedUrl(minioUtil.openBucket(bucketName).generatePresignedPutUrl(objectName)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.GET_PRESIGNED_URL_FAILED);
        }
    }

    @Override
    public ResponseResult<PresignedUrl> getPresignedUrlToGet(String bucketName, String objectName) {
        try {
            return ResponseResult.okResult(new PresignedUrl(minioUtil.openBucket(bucketName).generatePresignedGetUrl(objectName)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.GET_PRESIGNED_URL_FAILED);
        }
    }

    @Override
    public ResponseResult<Void> deleteFile(String bucketName, String objectName) {
        try {
            minioUtil.openBucket(bucketName).deleteFile(objectName);

            return ResponseResult.okResult();
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.DELETE_MINIO_FILE_FAILED);
        }
    }
}
