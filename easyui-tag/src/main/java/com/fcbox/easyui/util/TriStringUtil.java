package com.fcbox.easyui.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by 000538 on 2017/4/14.
 */
public class TriStringUtil {

    /**
     * 随即生成指定位数的含数字验证码字符串
     *
     * @author Peltason
     * @date 2007-5-9
     * @param bit
     *            指定生成验证码位数
     * @return String
     */
    public static String numRandom(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        String str = "";
        str = "0123456789";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * 随即生成指定位数的含验证码字符串
     *
     * @author Peltason
     *
     * @date 2007-5-9
     * @param bit
     *            指定生成验证码位数
     * @return String
     */
    public static String random(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        // 因为o和0,l和1很难区分,所以,去掉大小写的o和l
        String str = "";
        str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }
}
