package cn.fyy.minio.config.minio;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 构建基础的连接Bean
 *
 * @author fyy
 */
@Slf4j
@Configuration
public class MinioConfiguration {
    /**
     * 文件服务器配置
     */
    @Resource
    private MinioProperties minioProperties;

    /**
     * 客户端
     */
    @Bean
    public MinioClient minioClientFactory() {
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    /**
     * 桶
     */
    @Bean
    public BucketExistsArgs minioClientBucketFactory() {
        return BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build();
    }

}
