package com.sixtyrobbers.GSQ.fourm.common.util;

import java.util.Random;

/**
 * <pre>
 * Explain: 字符串工具类
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 14:15
 * Version: V1.0
 * </pre>
 */
public class StringUtil {

    /**
     * <pre>
     * Explain: 根据输入数字和长度随机生成新数字
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 14:16
     * Version: V1.0
     * </pre>
     */
    public static String randomString(String strPool, int length) {
        if (strPool == null || length < 1) {
            return null;
        }
        Random randGen = new Random();
        char[] numbersAndLetters = (strPool).toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(strPool.length())];
        }
        return new String(randBuffer);
    }

    /**
     * <pre>
     * Explain: 生成随机姓名
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:28
     * Version: V1.0
     * </pre>
     */
    public static String getStringRandom() {
        String result = "";
        Random random = new Random();
        //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
        int length = (int)(1+Math.random()*(10-5+1));
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                result += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                result += String.valueOf(random.nextInt(10));
            }
        }
        return result;
    }

}
