package com.astolfo.application.service;

import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.PresignedUrl;

public interface MinioFileService {

    ResponseResult<PresignedUrl> getPresignedUrlToPut(String bucketName, String ObjectName);

    ResponseResult<PresignedUrl> getPresignedUrlToGet(String bucketName, String ObjectName);

    ResponseResult<Void> deleteFile(String bucketName, String objectName);

}
