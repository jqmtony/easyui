package com.fcbox.easyui.util;

import java.util.List;
import java.util.Map;

/**
 * @author hexuming
 */
public class TriRegulation {

    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o instanceof String) {
            return isEmpty((String) o);
        } else {
            return o == null;
        }
    }

    public static <E extends Object> boolean isEmpty(List<E> l) {
        if (l == null || l.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static <E extends Object, V, K> boolean isEmpty(Map<K, V> l) {
        if (l == null || l.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

}
