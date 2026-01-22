package cn.fyy.rustfs.service.impl;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.rustfs.config.rustfs.RustfsProperties;
import cn.fyy.rustfs.service.RustfsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.InputStream;
import java.time.Duration;

/**
 * 文件服务器工具类
 *
 * @author fuyy
 */
@Slf4j
@Service
public class RustfsServiceImpl implements RustfsService {
    /**
     * 客户端连接
     */
    @Resource
    private S3Client s3Client;

    /**
     * 文件服务器配置
     */
    @Resource
    private S3Presigner s3Presigner;

    /**
     * 文件服务器配置
     */
    @Resource
    private RustfsProperties rustfsProperties;

    /**
     * 文件上传
     *
     * @param fileName      文件名称
     * @param stream        文件流
     * @param contentLength 文件大小
     */
    @Override
    public void putObject(String fileName, InputStream stream, long contentLength) throws BusinessException {
        try {
            s3Client.putObject(
                    PutObjectRequest.builder().bucket(rustfsProperties.getBucketName()).key(fileName).build(),
                    RequestBody.fromInputStream(stream, contentLength)
            );
            log.info("文件 {} 服务器工具类上传成功！", fileName);
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类上传失败！", e);
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    @Override
    public void deleteObject(String fileName) throws BusinessException {
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder().bucket(rustfsProperties.getBucketName()).key(fileName).build());
            log.info("文件 {} 删除成功！", fileName);
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类删除失败！", e);
        }
    }

    /**
     * 获取文件 URL
     *
     * @param fileName 文件名称
     * @param duration 签名有效期
     */
    @Override
    public String presignGetObject(String fileName, Duration duration) throws BusinessException {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(rustfsProperties.getBucketName())
                    .key(fileName)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(getObjectRequest)
                    .signatureDuration(duration)
                    .build();

            return s3Presigner.presignGetObject(presignRequest).url().toString();
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类获取失败！", e);
        }
    }
}