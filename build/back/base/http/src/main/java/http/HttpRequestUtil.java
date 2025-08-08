package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

/**
 * Http请求封装工具类（基于 Apache HttpClient 5.x）
 * 提供 GET 和 POST 请求的封装方法，支持添加 headers、参数和自定义字符集。
 *
 * @author fyy
 */
@Slf4j
public class HttpRequestUtil {
    /**
     * 是否输出日志
     */
    public static boolean isOutputLog = false;

    // Jackson ObjectMapper 实例
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //------------------------------------------------------------------------------------------------------------------单例 httpClient

    /**
     * 内部静态单例类，用于提供全局唯一的 CloseableHttpClient 实例
     */
    private static class HttpClientSingle {
        private static final CloseableHttpClient INSTANCE = buildHttpClient();

        private static CloseableHttpClient buildHttpClient() {
            log.info("创建 HttpClient 实例");
            try {
                // Step 1: 创建连接配置（设置连接最大存活时间）
                ConnectionConfig connectionConfig = ConnectionConfig.custom()
                        // 设置连接最大存活时间，默认为 -1，表示不限制连接最大存活时间
                        .setTimeToLive(TimeValue.ofMinutes(5))
                        .build();

                // Step 2: 创建连接池管理器并设置参数
                PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
                // 总最大连接数
                connectionManager.setMaxTotal(500);
                // 每个路由最大连接数
                connectionManager.setDefaultMaxPerRoute(100);
                // 设置连接存活时间
                connectionManager.setDefaultConnectionConfig(connectionConfig);

                // Step 3: 设置请求超时配置
                RequestConfig requestConfig = RequestConfig.custom()
                        // 连接超时
                        .setConnectionRequestTimeout(Timeout.ofSeconds(5))
                        // 响应超时
                        .setResponseTimeout(Timeout.ofSeconds(10))
                        .build();

                // Step 4: 构建 httpClient 实例
                return HttpClients.custom()
                        // 设置连接池管理器
                        .setConnectionManager(connectionManager)
                        // 设置默认请求配置
                        .setDefaultRequestConfig(requestConfig)
                        // 定期清理空闲连接
                        .evictIdleConnections(TimeValue.ofSeconds(30))
                        // 添加自定义重试策略
                        .setRetryStrategy(new DefaultHttpRequestRetryStrategy(3, TimeValue.ofSeconds(1)))
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("创建 HttpClient 失败", e);
            }
        }

        public static CloseableHttpClient getInstance() {
            log.info("获取 HttpClient 实例");
            return INSTANCE;
        }
    }

    //------------------------------------------------------------------------------------------------------------------POST 请求方法

    /**
     * 发送 POST 请求，携带参数
     *
     * @param url    请求地址
     * @param params 请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doPost(String url, Map<String, Object> params) {
        return doPost(url, null, params);
    }

    /**
     * 发送 POST 请求，携带 headers 和 参数
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, Object> params) {
        return doPost(url, headers, params, StandardCharsets.UTF_8);
    }

    /**
     * 发送 POST 请求，携带 headers、参数和指定字符集
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数 (Map格式)
     * @param charset 字符集名称（如 UTF-8）
     * @return 响应内容
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, Object> params, Charset charset) {
        try {
            HttpPost httpPost = new HttpPost(url);

            if (params != null && !params.isEmpty()) {
                String jsonBody = OBJECT_MAPPER.writeValueAsString(params);
                StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }

            addHeader(httpPost, headers);

            ClassicHttpResponse response = HttpClientSingle.getInstance()
                    .executeOpen(null, httpPost, null);

            return handleResponse(charset, response);
        } catch (IOException | URISyntaxException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重载 doPost 方法，允许直接传入 JSON 字符串
     *
     * @param url      请求地址
     * @param jsonBody 请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doPost(String url, String jsonBody) {
        return doPost(url, null, jsonBody);
    }

    /**
     * 重载 doPost 方法，允许直接传入 JSON 字符串
     *
     * @param url      请求地址
     * @param headers  请求头信息
     * @param jsonBody 请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doPost(String url, Map<String, String> headers, String jsonBody) {
        return doPost(url, headers, jsonBody, StandardCharsets.UTF_8);
    }

    /**
     * 重载 doPost 方法，允许直接传入 JSON 字符串
     *
     * @param url      请求地址
     * @param headers  请求头信息
     * @param jsonBody 请求参数 (Map格式)
     * @param charset  字符集名称（如 UTF-8）
     * @return 响应内容
     */
    public static String doPost(String url, Map<String, String> headers, String jsonBody, Charset charset) {
        try {
            HttpPost httpPost = new HttpPost(url);

            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            addHeader(httpPost, headers);

            ClassicHttpResponse response = HttpClientSingle.getInstance()
                    .executeOpen(null, httpPost, null);

            return handleResponse(charset, response);
        } catch (IOException | URISyntaxException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------GET 请求方法

    /**
     * 发送 GET 请求
     *
     * @param url 请求地址
     * @return 响应内容
     */
    public static String doGet(String url) {
        return doGet(url, null, null, StandardCharsets.UTF_8);
    }

    /**
     * 发送 GET 请求，携带参数
     *
     * @param url    请求地址
     * @param params 请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, null, params, StandardCharsets.UTF_8);
    }

    /**
     * 发送 GET 请求，携带 headers 和 参数
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数 (Map格式)
     * @return 响应内容
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        return doGet(url, headers, params, StandardCharsets.UTF_8);
    }

    /**
     * 发送 GET 请求，携带 headers、参数和指定字符集
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数 (Map格式)
     * @param charset 字符集名称（如 UTF-8）
     * @return 响应内容
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> params, Charset charset) {
        try {
            if (params != null && !params.isEmpty()) {
                url = jointUrl(url, params);
            }

            HttpGet httpGet = new HttpGet(url);

            addHeader(httpGet, headers);

            ClassicHttpResponse response = HttpClientSingle.getInstance()
                    .executeOpen(null, httpGet, null);

            return handleResponse(charset, response);
        } catch (IOException | URISyntaxException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------私有辅助方法

    /**
     * 获取响应内容
     *
     * @param charset  字符集名称（如 UTF-8）
     * @param response 响应对象
     * @return 响应内容
     */
    private static String handleResponse(Charset charset, ClassicHttpResponse response) throws IOException, URISyntaxException, ParseException {
        int statusCode = response.getCode();

        if (isOutputLog) {
            log.info("相应码：{}:{}", statusCode, HttpStatusChinese.getChineseDescriptionByHttpStatus(HttpStatus.valueOf(statusCode)));
            log.info("响应头：{}", Arrays.toString(response.getHeaders()));
        }

        if (statusCode == HttpStatus.OK.value()) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
            return content;
        }
        return "";
    }

    /**
     * 向请求中添加多个 Header
     *
     * @param request 请求对象（HttpGet 或 HttpPost）
     * @param headers 请求头信息
     */
    private static void addHeader(HttpRequest request, Map<String, String> headers) throws URISyntaxException {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (request.containsHeader(entry.getKey())) {
                    request.setHeader(entry.getKey(), entry.getValue());
                } else {
                    request.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        if (isOutputLog) {
            log.info("请求地址：{}", request.getUri());
            log.info("请求头：{}", Arrays.toString(request.getHeaders()));
        }
    }

    /**
     * 将参数拼接到 URL 上
     *
     * @param url    原始 URL
     * @param params 参数 Map
     * @return 拼接后的 URL
     */
    public static String jointUrl(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if (!url.contains("?")) {
            sb.append("?");
        } else if (!url.endsWith("&")) {
            sb.append("&");
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .append("&");
        }
        if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
