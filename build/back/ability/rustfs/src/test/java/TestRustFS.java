import cn.fyy.rustfs.util.S3DisposeUtil;
import dispose.FileDispose;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.FileInputStream;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.time.Duration;

public class TestRustFS {
    /**
     * 获取本机IP地址
     */
    static String inet = S3DisposeUtil.urlDispose("localhost");


    static String bucketName = "build";

    static String fileName = "user-data/1/head-image/a070f2e333544a61b694cf33a646c698.jpeg";


    public static void main(String[] args) throws Exception {
        get();
    }

    private static void update() throws Exception {
        try (S3Client s3Client = S3Client.builder()
                // 配置文件服务器地址
                .endpointOverride(URI.create("http://" + inet + ":10000"))
                // 可写死，RustFS 不校验 地区，我选择中国北部1
                .region(Region.CN_NORTH_1)
                // 创建凭证
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        "IPBwkfeL3HnUCoqrlZWa",
                                        "GRrD0A46f1zFMpkQundT8eWYBxsSijHcJyLEtoOU"
                                ) //管理后台直接创建的访问密钥
                        )
                )
                // 关键配置！RustFS 需启用 Path-Style
                .forcePathStyle(true)
                .build()) {

            System.out.println("初始化");

            FileInputStream stream = new FileInputStream("/Users/fyy/Downloads/测试图片/1.jpeg");
            FileChannel fileChannel = stream.getChannel();
            long contentLength = fileChannel.size(); // 获取文件大小（字节）


            System.out.println("开始测试");

            s3Client.headBucket(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());


            System.out.println("桶结束");

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(FileDispose.getContextType(fileName))
                    .build();

            System.out.println("request结束");

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(stream, contentLength));
        }
        System.out.println("上传结束");
    }

    private static void get() throws Exception {
        PresignedGetObjectRequest presignedRequest;
        try (S3Presigner presigner = S3Presigner.builder()
                .endpointOverride(URI.create("http://" + inet + ":10000"))
                .region(Region.CN_NORTH_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        "IPBwkfeL3HnUCoqrlZWa",
                                        "GRrD0A46f1zFMpkQundT8eWYBxsSijHcJyLEtoOU"
                                )
                        )
                )
                .build()) {

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(getObjectRequest)
                    .signatureDuration(Duration.ofHours(1))
                    .build();

            presignedRequest = presigner.presignGetObject(presignRequest);
        }
        System.out.println("Presigned URL: " + presignedRequest.url());
    }
}
