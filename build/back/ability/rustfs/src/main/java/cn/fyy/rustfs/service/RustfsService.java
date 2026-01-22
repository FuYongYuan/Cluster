package cn.fyy.rustfs.service;

import cn.fyy.common.bean.bo.BusinessException;

import java.io.InputStream;
import java.time.Duration;

/**
 * RustFS 操作类
 *
 * @author fyy
 */
public interface RustfsService {
    /**
     * 文件上传
     *
     * @param fileName      文件名称
     * @param stream        文件流
     * @param contentLength 文件大小
     */
    void putObject(String fileName, InputStream stream, long contentLength) throws BusinessException;

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    void deleteObject(String fileName) throws BusinessException;

    /**
     * 获取文件 URL
     *
     * @param fileName 文件名称
     * @param duration 签名有效期
     */
    String presignGetObject(String fileName, Duration duration) throws BusinessException;
}
