package encrypt;

import java.util.Random;

/**
 * 随机数
 *
 * @author fyy
 */
public class RandomUtil {
    //------------------------------------------------------------------------------------------------------------------随机数字

    /**
     * 根据长度获取随机数
     */
    public static String randomNumberByLength(int length) {
        StringBuilder randomStr = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomStr.append(random.nextInt(10));
        }
        return randomStr.toString();
    }

    /**
     * 随机范围数
     */
    public static int randomNumberByWithin(int within) {
        return randomNumberByWithin(0, within);
    }

    /**
     * 随机范围数
     */
    public static int randomNumberByWithin(int startWithin, int endWithin) {
        Random random = new Random();
        return random.nextInt(endWithin - startWithin + 1) + startWithin;
    }
    //------------------------------------------------------------------------------------------------------------------随机字母

    /**
     * 随机字母
     */
    public static String randomAlphabet(int length) {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R'
                , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
                , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random rd = new Random();
        StringBuilder result = new StringBuilder();
        //随即10个拿出来看看
        for (int i = 0; i < length; i++) {
            result.append(alphabet[rd.nextInt(52)]);
        }
        return result.toString();
    }

    //------------------------------------------------------------------------------------------------------------------随机boolean

    /**
     * 随机boolean
     */
    public static boolean randomBoolean() {
        Random rd = new Random();
        return rd.nextBoolean();
    }
}
