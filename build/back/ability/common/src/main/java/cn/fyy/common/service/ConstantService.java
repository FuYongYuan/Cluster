package cn.fyy.common.service;

import cn.fyy.jwt.config.jwt.JwtProperties;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 静态方法类
 *
 * @author fuyy
 */
public class ConstantService {
    //------------------------------------------------------------------------------------------------------------------日志输出

    /**
     * 日志信息
     *
     * @param e 错误内容
     * @return 日志 -> 类:***   方法:***   行:***  错误:***
     */
    public static String logMessage(Exception e) {
        return "类:" + e.getStackTrace()[0].getClassName() +
                "   方法:" + e.getStackTrace()[0].getMethodName() +
                "   行:" + e.getStackTrace()[0].getLineNumber() +
                "   错误:" + e.getMessage();
    }

    //------------------------------------------------------------------------------------------------------------------服务器相关内容获取
    /**
     * 空
     */
    private static final String UNKNOWN = "unknown";

    /**
     * 获取访问者ip地址
     */
    public static String getVisitorIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取本机IP
     */
    public static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            logMessage(e);
        }
        return null;
    }

    /**
     * 快速获取Request中的Token
     */
    public static String getRequestToken(JwtProperties jwtProperties) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(jwtProperties.getTokenHeader())
                .substring(jwtProperties.getTokenHead().length());
    }

    //------------------------------------------------------------------------------------------------------------------查询语句排序拼装

    /**
     * 获取排序数组
     *
     * @param root            查询对象
     * @param criteriaBuilder 查询条件生成器
     * @param pageSort        排序
     * @return 排序数组
     */
    public static Order[] getSort(Root<?> root, CriteriaBuilder criteriaBuilder, String pageSort) {
        if (StringUtils.hasText(pageSort)) {
            String[] pageSorts = pageSort.split(",");
            Order[] result = new Order[pageSorts.length];
            for (int i = 0; i < pageSorts.length; i++) {
                if (pageSorts[i] != null) {
                    String[] sortAndOrder = pageSorts[i].split("-");
                    if (sortAndOrder.length == 2) {
                        String sort = sortAndOrder[0];
                        String order = sortAndOrder[1];
                        if (order.contains("asc")) {
                            result[i] = criteriaBuilder.asc(root.get(sort));
                        } else if (order.contains("desc")) {
                            result[i] = criteriaBuilder.desc(root.get(sort));
                        }
                    }
                }
            }
            return result;
        }
        return null;
    }
}
