package dispose;

/**
 * 文件处理
 *
 * @author fyy
 */
public class FileDispose {
    /**
     * 获取文件类型
     *
     * @param fileName 文件名称
     */
    public static String getContextType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return switch (suffix) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "bmp" -> "image/bmp";
            case "gif" -> "image/gif";
            case "html" -> "text/html";
            case "xml" -> "text/xml";
            default -> "text/plain";
        };
    }
}
