package com.astolfo.application.service.impl;

import com.astolfo.application.service.MinioFileService;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.common.util.MinioUtil;
import com.astolfo.webinterface.vo.PresignedUrl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MinioFileServiceImpl implements MinioFileService {

    @Resource
    MinioUtil minioUtil;


    @Override
    public ResponseResult<PresignedUrl> getPresignedUrlToPut(
            String bucketName,
            Long id,
            String fileName
    ) {
        try {
            return ResponseResult.okResult(minioUtil.openBucket(bucketName).getPresignedUrlToPut(id, fileName));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.GET_PRESIGNED_URL_FAILED);
        }
    }
}
