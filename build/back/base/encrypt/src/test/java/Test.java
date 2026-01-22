import snowflake.ClockBackwardStrategy;
import snowflake.SnowflakeIdGenerator;
import snowflake.SnowflakeIdInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {
//        System.out.println(Md5Util.encode("admin"));
//
//        String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXRNbrbGzCE9l/n" +
//                "Vqu8ndIOLc1grc/+xmXraG1yTjmbVsLT5FNzkl/7N3IZpJCN/ETbwUXQqQQ7L/oy" +
//                "tIgERF8hXSNg3X/+44n1aRdjfcF7QCYry1tRtRiCs72zCtvXot8J3gz9SqaEZSc3" +
//                "dW5g9PzVuMAoMH9yAsUjUOOpWeHXAgMBAAECgYAhd0u7hB8CFEdBKdTDMamfA2sc" +
//                "1YHIui2MjwMeuh557gTPQa9SSQCVWClBgETRMsFPb1+o2RpzI+3V0MV4D8kBphCO" +
//                "Zygo+Tc2NlY/byPx07mqcybJGpmqLWqbm+iZ9f4hzqeu82dIcupL4U6pPaDSlTXv" +
//                "dkDoTJufLNyM1/9XCQJBAOV9qwLLVdngrGK52e3uoED5Ds3LsduaFbhklLqwdpFf" +
//                "xXMZYvdyYifJEJDT07rxnEHjtWxE8bbDOCPhZMd/FKMCQQDK0cWp7WfOuAyW0COP" +
//                "a6stV9TnbUSK/idgCM8QKN3NZrxdlgFpsF96qlUt2Ik4GoEx4c2h1J9K0PJdWS0q" +
//                "jp09AkBKqcobjKAP1MMBE8pNWNeWi/E/D0h8U/NBHKgu3P6WaXxJ6DaEnxhELWxQ" +
//                "UjqOldDqtvTtlri1Z2o6cjhhl3zVAkEAjOv0S32JAzkehitYie5lobvFUoe5eFnf" +
//                "Qfrc5H2An5ciimQP1z+VF8YCKTirHzBLVD1wg2EYzQMhY3ryxv8QgQJAcLjLIqaa" +
//                "p5WGXKmHaiBnDBpXGMyRboywCvHFKL8F0FrpLfGkZjIq2eRnFdXF2Pi2cCfecQlj" +
//                "kJIS+dFrvpnUPQ==";
//        String publicKeyStr =
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC10TW62xswhPZf51arvJ3SDi3N" +
//                        "YK3P/sZl62htck45m1bC0+RTc5Jf+zdyGaSQjfxE28FF0KkEOy/6MrSIBERfIV0j" +
//                        "YN1//uOJ9WkXY33Be0AmK8tbUbUYgrO9swrb16LfCd4M/UqmhGUnN3VuYPT81bjA" +
//                        "KDB/cgLFI1DjqVnh1wIDAQAB" ;
//
//        String s = RsaUtil.encrypt(publicKeyStr,"fyyain77");
//        System.out.println(s);
//        System.out.println(RsaUtil.decrypt(privateKeyStr,s));
//
//
//        System.out.println(Sha256Util.encode("fyyain77"));
//
//        System.out.println(Sha1Util.encode("fyyain77"));

//        System.out.println(RandomUtil.randomNumberByLength(6));


//        System.out.println(Md5Util.encode("1"));

//        System.out.println(AesUtil.encryptString("appsetr", "EBS"));
//        System.out.println(AesUtil.decryptString("10C615A9E5E6125181F01B6D6F1AD340", "EBS"));
//
//        System.out.println(AesUtil.encryptString("weblogic123", "EBS"));
//        System.out.println(AesUtil.decryptString("A8C709E60599D0610492B079C724674B", "EBS"));

        long epoch = 1767196800000L;

        // 创建ID生成器实例
        SnowflakeIdGenerator authorizationGenerator = new SnowflakeIdGenerator(200L, 1L, epoch, ClockBackwardStrategy.WAIT);
        SnowflakeIdGenerator builderGenerator = new SnowflakeIdGenerator(700L, 1L, epoch, ClockBackwardStrategy.WAIT);
        SnowflakeIdGenerator capabilityGenerator = new SnowflakeIdGenerator(300L, 1L, epoch, ClockBackwardStrategy.WAIT);
        SnowflakeIdGenerator dictionaryGenerator = new SnowflakeIdGenerator(400L, 1L, epoch, ClockBackwardStrategy.WAIT);
        SnowflakeIdGenerator memberGenerator = new SnowflakeIdGenerator(100L, 1L, epoch, ClockBackwardStrategy.WAIT);
        SnowflakeIdGenerator messageGenerator = new SnowflakeIdGenerator(600L, 1L, epoch, ClockBackwardStrategy.WAIT);

        List<Long> l = new ArrayList<>();

        // 生成多个ID
        for (int i = 0; i < 16; i++) {
            long id = authorizationGenerator.nextId();
            System.out.println("authorizationGenerator Generated ID: " + id);
            l.add(id);
        }

        for (int i = 0; i < 9; i++) {
            long id = capabilityGenerator.nextId();
            System.out.println("capabilityGenerator Generated ID: " + id);
            l.add(id);
        }

        for (int i = 0; i < 1; i++) {
            long id = dictionaryGenerator.nextId();
            System.out.println("dictionaryGenerator Generated ID: " + id);
            l.add(id);
        }

        for (int i = 0; i < 1; i++) {
            long id = memberGenerator.nextId();
            System.out.println("memberGenerator Generated ID: " + id);
            l.add(id);
        }

        for (int i = 0; i < 1; i++) {
            long id = messageGenerator.nextId();
            System.out.println("messageGenerator Generated ID: " + id);
            l.add(id);
        }

        // 使用示例
        boolean hasDup = hasDuplicates(l);
        System.out.println("是否有重复: " + hasDup);


        //输出
        for (Long id : l) {
            SnowflakeIdInfo info = SnowflakeIdInfo.parseId(id, epoch);
            System.out.println(info.getCreateTime());
            System.out.println(info.getDataCenterId());
            System.out.println(info.getWorkerId());
            System.out.println(info.getSequence());
        }


    }

    // 验证是否有重复
    public static boolean hasDuplicates(List<Long> list) {
        Set<Long> seen = new HashSet<>();
        for (Long item : list) {
            if (!seen.add(item)) {
                System.out.println("发现重复ID: " + item);
                return true;  // 发现重复立即返回
            }
        }
        return false;  // 无重复
    }


}
