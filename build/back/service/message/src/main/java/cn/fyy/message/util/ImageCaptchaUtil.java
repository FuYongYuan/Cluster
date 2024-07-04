package cn.fyy.message.util;


import encrypt.RandomUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;


/**
 * 验证码生成工具类
 *
 * @author fuyy
 */
public class ImageCaptchaUtil {
    /**
     * 验证码类，用于最后返回此对象，包含验证码图片base64和真值
     */
    private static Validate validate = null;
    /**
     * 随机类，用于生成随机参数
     */
    private static final Random RANDOM = new Random();
    /**
     * 随机生成字符串的取值范围
     */
    private static final String RAND_STRING = "2456789ACDEFGHJKLPQRSTUVXYZ";
    /**
     * 图片宽度
     */
    private static final int WIDTH = 100;
    /**
     * 图片高度
     */
    private static final int HEIGHT = 40;
    /**
     * 字符的数量
     */
    private static final int STRING_NUM = 4;
    /**
     * 干扰线数量
     */
    private static final int LINE_SIZE = 100;

    /**
     * 颜色最大值
     */
    private static final int COLOR_MAX_VALUE = 255;

    // 将构造函数私有化 禁止new创建

    /**
     * 验证码
     */
    private ImageCaptchaUtil() {
        super();
    }

    /**
     * 获取随机字符,并返回字符的String格式
     *
     * @param index (指定位置)
     */
    private static String getRandomChar(int index) {
        // 获取指定位置index的字符，并转换成字符串表示形式
        return String.valueOf(RAND_STRING.charAt(index));
    }

    /**
     * 获取随机指定区间的随机数
     *
     * @param min (指定最小数)
     * @param max (指定最大数)
     */
    private static int getRandomNum(int min, int max) {
        return RandomUtil.randomNumberByWithin(min, max);
    }

    /**
     * 获得字体
     */
    private static Font getFont() {
        // 名称、样式、磅值
        return new Font("Fixedsys", Font.BOLD, 30);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int frontColor, int backColor) {
        if (frontColor > COLOR_MAX_VALUE) {
            frontColor = 255;
        }
        if (backColor > COLOR_MAX_VALUE) {
            backColor = 255;
        }
        // 红
        int red = frontColor + RANDOM.nextInt(backColor - frontColor - 16);
        // 绿
        int green = frontColor + RANDOM.nextInt(backColor - frontColor - 14);
        // 蓝
        int blue = frontColor + RANDOM.nextInt(backColor - frontColor - 18);
        return new Color(red, green, blue);
    }

    /**
     * 绘制字符串,返回绘制的字符串
     */
    private static String drawString(Graphics g, String randomString, int i) {
        Graphics2D g2d = (Graphics2D) g;
        // 设置字体
        g2d.setFont(getFont());
//        g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));//设置颜色  RGB(90,98,104)
        // 设置文字颜色  #5A6268
        g2d.setColor(new Color(127, 127, 127));
        String randChar = getRandomChar(RANDOM.nextInt(RAND_STRING.length()));
        // 组装
        randomString += randChar;
        int rot = getRandomNum(5, 10);
        // 位置上下移
        g2d.translate(RANDOM.nextInt(3), RANDOM.nextInt(3));
        g2d.rotate(rot * Math.PI / 180);
        // X间隔 y上下
        g2d.drawString(randChar, 18 * i, 20);
        g2d.rotate(-rot * Math.PI / 180);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drawLine(Graphics g) {
        // 起点(x,y)  偏移量x1、y1
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        int xl = RANDOM.nextInt(13);
        int yl = RANDOM.nextInt(15);
        g.setColor(new Color(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat()));
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * @return 生成Base64图形验证码
     */
    public static Validate getRandomCode() {
        validate = validate == null ? new Validate() : validate;

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类  
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        // 设置背景透明
        Graphics2D graphics = image.createGraphics();
        image = graphics.getDeviceConfiguration().createCompatibleImage(WIDTH, HEIGHT, Transparency.TRANSLUCENT);
        // 填充杂物
        // 获得BufferedImage对象的Graphics对象
        Graphics g = image.getGraphics();
        //填充矩形背景白色
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 设置字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 设置颜色
        g.setColor(getRandColor(160, 183));
        // 绘制干扰线
        for (int i = 0; i <= LINE_SIZE; i++) {
            drawLine(g);
        }
        // 绘制字符
        String randomString = "";
        for (int i = 1; i <= STRING_NUM; i++) {
            randomString = drawString(g, randomString, i);
            validate.setValue(randomString);
        }
        // 释放绘图资源
        g.dispose();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            // 将绘制得图片输出到流
            ImageIO.write(image, "png", bs);
            String imgsrc = byteArrayToBase64(bs.toByteArray(), false);
            validate.setBase64Str(imgsrc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return validate;
    }

    /**
     * 验证码类
     */
    public static class Validate implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        /**
         * Base64 值
         */
        private String base64Str;
        /**
         * 验证码值
         */
        private String value;

        public String getBase64Str() {
            return base64Str;
        }

        public void setBase64Str(String base64Str) {
            this.base64Str = base64Str;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * This array is a lookup table that translates 6-bit positive integer index values into their "Base64 Alphabet"
     * equivalents as specified in Table 1 of RFC 2045.
     */
    private static final char[] INT_TO_BASE64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * This array is a lookup table that translates 6-bit positive integer index values into their
     * "Alternate Base64 Alphabet" equivalents. This is NOT the real Base64 Alphabet as per in Table 1 of RFC 2045. This
     * alternate alphabet does not use the capital letters. It is designed for use in environments where "case folding"
     * occurs.
     */
    private static final char[] INT_TO_ALT_BASE64 = {'!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
            ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '?'};

    private static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuilder result = new StringBuilder(resultLen);
        char[] intToAlpha = (alternate ? INT_TO_ALT_BASE64 : INT_TO_BASE64);

        // Translate all full groups from byte array elements to Base64
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = a[inCursor++] & 0xff;
            int byte1 = a[inCursor++] & 0xff;
            int byte2 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
            result.append(intToAlpha[byte2 & 0x3f]);
        }

        // Translate partial group if present
        if (numBytesInPartialGroup != 0) {
            int byte0 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4) & 0x3f]);
                result.append("==");
            } else {
                // assert numBytesInPartialGroup == 2;
                int byte1 = a[inCursor++] & 0xff;
                result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2) & 0x3f]);
                result.append('=');
            }
        }
        // assert inCursor == a.length;
        // assert result.length() == resultLen;
        return result.toString();
    }
}  
 
