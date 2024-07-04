package cn.fyy.minio.service.impl;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.minio.service.MinioService;
import dispose.FileDispose;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 文件服务器工具类
 *
 * @author fuyy
 */
@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

    /**
     * 客户端连接
     */
    @Resource
    private MinioClient minioClient;

    /**
     * 数据桶
     */
    @Resource
    private BucketExistsArgs minioClientBucket;

    /**
     * 文件上传
     *
     * @param fileName 文件名称
     * @param stream   文件流
     */
    @Override
    public void putObject(String fileName, InputStream stream) throws BusinessException {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioClientBucket.bucket())
                    .object(fileName)
                    .stream(stream, -1, 10485760)
                    .contentType(FileDispose.getContextType(fileName))
                    .build();

            minioClient.putObject(putObjectArgs);
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
    public void removeObject(String fileName) throws BusinessException {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(minioClientBucket.bucket())
                    .object(fileName)
                    .build();

            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类删除失败！", e);
        }
    }

    /**
     * 获取文件URL
     *
     * @param fileName 文件名称
     */
    @Override
    public String searchObject(String fileName) throws BusinessException {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioClientBucket.bucket())
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类获取失败！", e);
        }
    }

    /**
     * 文件上传
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     * @param stream   文件流
     */
    @Override
    public void putObject(MinioClient client, BucketExistsArgs bucket, String fileName, InputStream stream) throws BusinessException {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket.bucket())
                    .object(fileName)
                    .stream(stream, -1, 10485760)
                    .contentType(FileDispose.getContextType(fileName))
                    .build();

            client.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类上传失败！", e);
        }
    }

    /**
     * 删除文件
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     */
    @Override
    public void removeObject(MinioClient client, BucketExistsArgs bucket, String fileName) throws BusinessException {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucket.bucket())
                    .object(fileName)
                    .build();

            client.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类删除失败！", e);
        }
    }

    /**
     * 获取文件URL
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     */
    @Override
    public String searchObject(MinioClient client, BucketExistsArgs bucket, String fileName) throws BusinessException {
        try {
            return client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket.bucket())
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            throw new BusinessException("文件服务器工具类获取失败！", e);
        }
    }
}