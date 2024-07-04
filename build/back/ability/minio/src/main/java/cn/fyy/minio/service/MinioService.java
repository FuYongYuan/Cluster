package cn.fyy.minio.service;

import cn.fyy.common.bean.bo.BusinessException;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;

import java.io.InputStream;

/**
 * MinIO操作类
 *
 * @author fyy
 */
public interface MinioService {
    /**
     * 文件上传
     *
     * @param fileName 文件名称
     * @param stream   文件流
     */
    void putObject(String fileName, InputStream stream) throws BusinessException;

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    void removeObject(String fileName) throws BusinessException;

    /**
     * 获取文件URL
     *
     * @param fileName 文件名称
     */
    String searchObject(String fileName) throws BusinessException;

    /**
     * 文件上传
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     * @param stream   文件流
     */
    void putObject(MinioClient client, BucketExistsArgs bucket, String fileName, InputStream stream) throws BusinessException;

    /**
     * 删除文件
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     */
    void removeObject(MinioClient client, BucketExistsArgs bucket, String fileName) throws BusinessException;

    /**
     * 获取文件URL
     *
     * @param client   客户端连接
     * @param bucket   数据桶
     * @param fileName 文件名称
     */
    String searchObject(MinioClient client, BucketExistsArgs bucket, String fileName) throws BusinessException;
}
