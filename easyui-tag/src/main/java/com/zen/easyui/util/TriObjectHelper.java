package com.zen.easyui.util;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * TriObjectHelper.java
 * <p>
 * comments:  对象转换
 *
 * @author hexuming
 * @version Version_2012
 * @creation date   Oct 22, 2012
 */
public class TriObjectHelper {

    // 尽量用日志记录调试信息
    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 把字符串第一个字符大写.
     *
     * @param fieldName
     * @return
     */
    public static String upperFirstChar(String fieldName) {
        if (fieldName == null)
            return null;
        return fieldName.length() > 0 ? Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) : "";
    }


    /**
     * 获取GET方法名称，如getDeployId.
     *
     * @param fieldName
     * @return
     */
    public static String getGetMethodName(String fieldName) {
        return "get" + upperFirstChar(fieldName);
    }


    /**
     * 获取get方法调用得到的值.
     *
     * @param bean
     * @param getMethod
     * @return
     */
    public static Object getGetMethodValue(Object bean, String getMethod) {
        try {
            Method method = bean.getClass().getMethod(getMethod);
            return method.invoke(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段属性值
     *
     * @param bean
     * @param fieldName
     * @return
     */
    public static Object getFieldValue4Object(Object obj, String fieldName) {
        if (obj instanceof Map) {
            return getFieldValue4Map((Map) obj, fieldName);
        } else if (obj instanceof String) {
            return obj;
        } else {
            return getFieldValue4Bean(obj, fieldName);
        }
    }

    /**
     * 获取BEAN字段属性值
     *
     * @param bean
     * @param fieldName
     * @return
     */
    public static Object getFieldValue4Bean(Object bean, String fieldName) {
        String getMethod = getGetMethodName(fieldName);
        return getGetMethodValue(bean, getMethod);
    }

    /**
     * 获取Map字段属性值
     *
     * @param bean
     * @param fieldName
     * @return
     */
    public static Object getFieldValue4Map(Map map, String fieldName) {
        return map.get(fieldName);
    }

    /**
     * 获取对象（判断是否为空,空则返回"")
     *
     * @param oobj
     * @return
     */
    public static Object getValue(Object o) {
        return !TriRegulation.isEmpty(o) ? o : "";
    }

    /**
     * 获取对象（如果为空，则返回defaultObj)
     *
     * @param o
     * @param defaultObj
     * @return
     */
    public static Object getValue(Object o, Object defaultObj) {
        return !TriRegulation.isEmpty(o) ? o : defaultObj;
    }

}
