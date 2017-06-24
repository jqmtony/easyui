package com.zen.easyui.util;

import java.util.List;
import java.util.Map;

public class RegulationUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o 对象
     * @return boolean
     */
    public static boolean isEmpty(Object o) {
        if (o instanceof String) {
            return isEmpty((String) o);
        } else {
            return o == null;
        }
    }

    /**
     * 判断List集合是否为空
     *
     * @param list 集合
     * @param <E>  集合类型
     * @return boolean
     */
    public static <E extends Object> boolean isEmpty(List<E> list) {
        if (list == null || list.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断Map对象是否为空
     *
     * @param map 对象
     * @param <V> 键类型
     * @param <K> 值类型
     * @return boolean
     */
    public static <V, K> boolean isEmpty(Map<K, V> map) {
        if (map == null || map.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

}
