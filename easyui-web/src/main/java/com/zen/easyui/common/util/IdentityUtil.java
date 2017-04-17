package com.zen.easyui.common.util;

import java.util.Random;
import java.util.UUID;

public class IdentityUtil {

    protected static String[] chars = new String[]{ // 要使用生成段字符的字母表
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成32个字符的UUID编号
     */
    public static String uuid32() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成32个字符的UUID编号
     */
    public static String uuid32(String spit) {
        return UUID.randomUUID().toString().replace("-", spit);
    }

    /**
     * 生成数据库32个字符的UUID编号  不带_
     */
    public static String dbUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getShortStr(String hex) {
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] shortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            shortStr[i] = outChars;
        }
        return shortStr[new Random(System.currentTimeMillis()).nextInt(3)];
    }
}
