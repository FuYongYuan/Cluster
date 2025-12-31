package cn.fyy.rustfs.config.rustfs;

import cn.fyy.rustfs.util.S3DisposeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

/**
 * 构建基础的连接Bean
 *
 * @author fyy
 */
@Slf4j
@Configuration
public class RustfsConfiguration {
    /**
     * 文件服务器配置
     */
    @Resource
    private RustfsProperties rustfsProperties;

    /**
     * 客户端
     */
    @Bean
    public S3Client rustfsClientFactory() {
        String domain = S3DisposeUtil.urlDispose(rustfsProperties.getDomain());
        log.info("RustFS S3Client启动，访问地址：http://{}:{}", domain, rustfsProperties.getPort());
        // 初始化 S3 客户端
        S3Client s3Client = S3Client.builder()
                // 配置文件服务器地址
                .endpointOverride(URI.create("http://" + domain + ":" + rustfsProperties.getPort()))
                // 可写死，RustFS 不校验 地区，我选择中国北部1
                .region(Region.CN_NORTH_1)
                // 创建凭证
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(rustfsProperties.getAccessKey(), rustfsProperties.getSecretKey())
                        )
                )
                // 关键配置！RustFS 需启用 Path-Style
                .forcePathStyle(true)
                .build();
        // 验证文件服务器是否正常
        s3Client.headBucket(HeadBucketRequest.builder()
                .bucket(rustfsProperties.getBucketName())
                .build());
        log.info("RustFS 启动成功 存储桶：{}", rustfsProperties.getBucketName());
        // 返回客户端
        return s3Client;
    }

    @Bean
    public S3Presigner rustfsPresignerFactory() {
        String domain = S3DisposeUtil.urlDispose(rustfsProperties.getDomain());
        log.info("RustFS S3Presigner启动，访问地址：http://{}:{}", domain, rustfsProperties.getPort());
        // 创建预签名器
        return S3Presigner.builder()
                .endpointOverride(URI.create("http://" + domain + ":10000"))
                .region(Region.CN_NORTH_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(rustfsProperties.getAccessKey(), rustfsProperties.getSecretKey())
                        )
                )
                .build();
    }
}
