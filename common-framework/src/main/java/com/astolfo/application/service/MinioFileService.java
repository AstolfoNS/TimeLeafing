package com.astolfo.application.service;

import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.PresignedUrl;

public interface MinioFileService {

    ResponseResult<PresignedUrl> getPresignedUrlToPut(
            String bucketName,
            Long id,
            String fileName
    );

}
