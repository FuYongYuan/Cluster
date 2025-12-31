package cn.fyy.rustfs.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * S3处理
 *
 * @author fyy
 */
public class S3DisposeUtil {
    /**
     * 处理域名
     *
     * @param domain 域名
     * @return 处理后的域名
     */
    public static String urlDispose(String domain) {
        if ("127.0.0.1".equals(domain) || "localhost".equals(domain)) {
            try {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = interfaces.nextElement();
                    if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                        continue;
                    }
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address) {
                            String ip = address.getHostAddress();
                            // 只获取192.168开头的IP地址
                            if (ip.startsWith("192.168")) {
                                return ip;
                            }
                        }
                    }
                }
            } catch (Exception ignored) {

            }
        }
        return "localhost";
    }
}
